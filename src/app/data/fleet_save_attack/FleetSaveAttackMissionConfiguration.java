package app.data.fleet_save_attack;

import app.data.MatMadFile;
import app.data.accounts.Accounts;
import app.gui.fleet_save_attack.MissionConfiguration;
import com.Log;
import ogame.planety.Planeta;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FleetSaveAttackMissionConfiguration implements Serializable
{
    private static final long serialVersionUID = 1L;
    private List<MissionConfigurationFile> planetFleetSaveObject = new ArrayList<>();
    private List<MissionConfigurationFile> moonFleetSaveObject = new ArrayList<>();
    private boolean autoFleetSavePlaneta = false;
    private boolean autoFleetSaveMoon = false;


    /*
    EXECUTING
     */

    /**
     * Zwraca losową wybraną konfigurację FS'a z księżyca.
     */
    public FleetSave getRandomMoonMissionConfiguration()
    {
        Random r = new Random();
        if(moonFleetSaveObject.size() > 0)
            return moonFleetSaveObject.get(r.nextInt(moonFleetSaveObject.size())).getFleetSave();
        else
        {
            Log.printLog(FleetSaveAttackMissionConfiguration.class.getName(),"Nie zwrocno konfiguracji FleetSave dla ksieżyca," +
                    " ponieważ lista jest pusta.");
            return null;
        }
    }

    /**
     * Zwraca losową wybraną konfigurację FS'a z księżyca.
     */
    public FleetSave getRandomPlanetaMissionConfiguration()
    {
        Random r = new Random();
        if(planetFleetSaveObject.size() > 0)
            return planetFleetSaveObject.get(r.nextInt(planetFleetSaveObject.size())).getFleetSave();
        else
        {
            Log.printLog(FleetSaveAttackMissionConfiguration.class.getName(),"Nie zwrocno konfiguracji FleetSave dla planety," +
                    " ponieważ lista jest pusta.");
            return null;
        }
    }

    /**
     * Dodaje konfigurację misji podaną w argumencie do listy dla kplanety. Jeżeli nie znajduje się na liście.
     * @return true - gdy doda do listy.
     */
    public boolean addPlanetFleetSaveObject(MissionConfiguration missionConfiguration,Planeta p)
    {
        for(MissionConfigurationFile mission : planetFleetSaveObject)
        {
            if(mission.getFleetSave().equals(missionConfiguration.getFleetSave()))
            {
                Log.printLog(FleetSaveAttackMissionConfiguration.class.getName(),"Nie dodano obiektu MissionConfiguration do listy PlanetFleetSaveObject." +
                        "Obiekt znajduję się już na liście.");
                return false;
            }
        }
        planetFleetSaveObject.add(new MissionConfigurationFile(missionConfiguration));
        Log.printLog(Planeta.class.getName(),"Dodano obiekt MissionConfiguration do listy PlanetFleetSaveObject.");
        save(p.getPlanetFileName());
        return true;
    }

    /**
     * Dodaje konfigurację misji podaną w argumencie do listy dla księżyca. Jeżeli nie znajduje się na liście.
     * @return true - gdy doda do listy.
     */
    public boolean addMoonFleetSaveObject(MissionConfiguration missionConfiguration, Planeta p)
    {
        for(MissionConfigurationFile mission : moonFleetSaveObject)
        {
            if(mission.getFleetSave().equals(missionConfiguration.getFleetSave()))
            {
                Log.printLog(Planeta.class.getName(),"Nie dodano obiektu MissionConfiguration do listy MoonFleetSaveObject." +
                        "Obiekt znajduję się już na liście.");
                return false;
            }
        }
        moonFleetSaveObject.add(new MissionConfigurationFile(missionConfiguration));
        Log.printLog(Planeta.class.getName(),"Dodano obiekt MissionConfiguration do listy MoonFleetSaveObject.");
        save(p.getPlanetFileName());
        return true;
    }

    /**
     * Usuwa wskazaną misję z listy dla planety, jeżeli misja jest na liście. Po usunięciu zapsiuje ponownie plik.
     * @param missionConfiguration Konfiguracja misji.
     * @param p Planeta - potrzebna do wskazania ściezki pliku.
     * @return Zwróci true jeżeli usunięcie się uda.
     */
    public boolean removePlanetFleetSaveObject(MissionConfigurationFile missionConfiguration,Planeta p)
    {
        for(MissionConfigurationFile mission : planetFleetSaveObject)
        {
            if(mission.getFleetSave().equals(missionConfiguration.getFleetSave()))
            {
                if(planetFleetSaveObject.remove(missionConfiguration))
                {
                    Log.printLog(Planeta.class.getName(),"Usunięto obiekt MissionConfiguration z listy PlanetFleetSaveObject.");
                    save(p.getPlanetFileName());
                    return true;
                }
                else
                {
                    Log.printLog(Planeta.class.getName(),"Nie usunięto obiektu MissionConfiguration z listy PlanetFleetSaveObject." +
                            " Powód nieznany.");
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Usuwa wskazaną misję z listy dla księżyca, jeżeli misja jest na liście. Po usunięciu zapsiuje ponownie plik.
     * @param missionConfiguration Konfiguracja misji.
     * @param p Planeta - potrzebna do wskazania ściezki pliku.
     * @return Zwróci true jeżeli usunięcie się uda.
     */
    public boolean removeMoonFleetSaveObject(MissionConfigurationFile missionConfiguration, Planeta p)
    {
        for(MissionConfigurationFile mission : moonFleetSaveObject)
        {
            if(mission.getFleetSave().equals(missionConfiguration.getFleetSave()))
            {
                if(moonFleetSaveObject.remove(missionConfiguration))
                {
                    Log.printLog(Planeta.class.getName(),"Usunięto obiekt MissionConfiguration z listy MoonFleetSaveObject.");
                    save(p.getPlanetFileName());
                    return true;
                }
                else
                {
                    Log.printLog(Planeta.class.getName(),"Nie usunięto obiektu MissionConfiguration z listy MoonFleetSaveObject." +
                            " Powód nieznany.");
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Zapisuje konfigurację misji fleet save do pliku.
     * @param planetFileName Scieżka do pliku do którego ma być zapisana konfiguracja.
     * @return Jeżeli zapisywanie się uda zwróci true.
     */
    public boolean save(String planetFileName)
    {
        String pathName = Accounts.accountDataSavePath() + planetFileName+".fso";

        try
        {
            FileOutputStream f = new FileOutputStream(new File(pathName));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(this);
            o.close();
            f.close();
            Log.printLog(Planeta.class.getName(),"Zapisano dane konfiguracji fleet save podczas ataku pod ścieżką "+pathName);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Wczytuje konfigurację misji fleet save z pliku.
     * @param planetFileName Scieżka do pliku z którego ma być wczytana konfiguracja.
     * @return Jeżeli nie ma pliku zwróci nową instancję.
     */
    public static FleetSaveAttackMissionConfiguration load(String planetFileName)
    {
        if(MatMadFile.isFileExists(Accounts.accountDataSavePath() + planetFileName+".fso"))
        {
            File file = new File(Accounts.accountDataSavePath() + planetFileName+".fso");

            FileInputStream f;
            try
            {
                f = new FileInputStream(file);
                ObjectInputStream o = new ObjectInputStream(f);

                FleetSaveAttackMissionConfiguration object = (FleetSaveAttackMissionConfiguration) o.readObject();
                o.close();
                f.close();

                return object;

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return new FleetSaveAttackMissionConfiguration();
    }

    /*
    GETTERS
     */
    public boolean isAutoFleetSavePlaneta() {
        return autoFleetSavePlaneta;
    }

    public boolean isAutoFleetSaveMoon() {
        return autoFleetSaveMoon;
    }

    public List<MissionConfigurationFile> getPlanetFleetSaveObject() {
        return planetFleetSaveObject;
    }

    public List<MissionConfigurationFile> getMoonFleetSaveObject() {
        return moonFleetSaveObject;
    }

    /*
    SETTERS
     */
    public void setAutoFleetSavePlaneta(boolean autoFleetSavePlaneta) {
        this.autoFleetSavePlaneta = autoFleetSavePlaneta;
    }

    public void setAutoFleetSaveMoon(boolean autoFleetSaveMoon) {
        this.autoFleetSaveMoon = autoFleetSaveMoon;
    }

    /**
     * Klasa przechowuje dane do pasków misji FleetSave w pliku.
     */
    public class MissionConfigurationFile implements Serializable
    {
        private FleetSave fleetSave;
        private boolean selected;

        MissionConfigurationFile(MissionConfiguration missionConfiguration) {
            fleetSave = missionConfiguration.getFleetSave();
            selected = missionConfiguration.isSelected();
            missionConfiguration.setMissionConfigurationFile(this);
        }

        /*
        EXECUTING
         */

        /**
         * Zwraca określoną konfigurację misji, wczytaną z pliku.
         */
        public MissionConfiguration configuration()
        {
            MissionConfiguration tmp = new MissionConfiguration(fleetSave);
            tmp.setSelected(selected);
            tmp.setMissionConfigurationFile(this);
            return tmp;
        }
        /*
        SETTERS
         */

        public void setFleetSave(FleetSave fleetSave) {
            this.fleetSave = fleetSave;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        /*
                GETTERS
                 */
        FleetSave getFleetSave() {
            return fleetSave;
        }

        public boolean isSelected() {
            return selected;
        }
    }
}

