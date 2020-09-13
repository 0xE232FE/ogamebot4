package app.data.autotransport;

import app.czas.Czas;
import app.data.MatMadFile;
import app.data.accounts.Accounts;
import com.Log;
import ogame.flota.Flota;

import java.io.*;

public class AutotransportData
{
    public static Config configuration = load();
    private static final String CONFIG_FILE_NAME = "config.atd";
    private static final String FOLDER_NAME = "atd\\";


    /**
     * Zapisuje konfigurację wysyłania ekspedycji.
     * @return Jeżeli zapisywanie się uda zwróci true.
     */
    public static boolean save()
    {
        String pathName = Accounts.accountDataSavePath() +FOLDER_NAME +CONFIG_FILE_NAME;

        try
        {
            if(MatMadFile.isFolderExists(Accounts.accountDataSavePath()+FOLDER_NAME))
            {
                FileOutputStream f = new FileOutputStream(new File(pathName));
                ObjectOutputStream o = new ObjectOutputStream(f);

                o.writeObject(configuration);
                o.close();
                f.close();
                Log.printLog(AutotransportData.class.getName(),"Zapisano dane konfiguracji Autotransport pod ścieżką "+pathName);
                return true;
            }
            else
            {
                com.Log.printLog(AutotransportData.class.getName(),
                        "Scieżka " + Accounts.accountDataSavePath()+FOLDER_NAME + " nie istnieje.");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * Wczytuje konfigurację wysyłania ekspedycji.
     * @return Jeżeli zapisywanie się uda zwróci true.
     */
    public static Config load()
    {
        if(MatMadFile.isFileExists(Accounts.accountDataSavePath() + FOLDER_NAME +CONFIG_FILE_NAME))
        {
            File file = new File(Accounts.accountDataSavePath() + FOLDER_NAME +CONFIG_FILE_NAME);

            FileInputStream f;
            try
            {
                f = new FileInputStream(file);
                ObjectInputStream o = new ObjectInputStream(f);

                Config object = (Config) o.readObject();
                o.close();
                f.close();

                return object;

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return new Config();
    }

    public static Czas getInterwalCzasowy()
    {
        return new Czas(configuration.godzina,configuration.minuta,configuration.sekunda);
    }



    public static class Config implements Serializable
    {
        private static final long serialVersionUID = 1L;
        private int galaktyka, uklad, planeta;
        private int technologiaNadprzestrzenna;
        private int godzina, minuta, sekunda;
        private int iloscSurowcow;
        private boolean planet;
        private boolean ksiezyc;
        private Flota flota = new Flota();


        /*
        GETTERS
         */
        public Flota getFlota() {
            return flota;
        }

        public int getGalaktyka() {
            return galaktyka;
        }

        public int getPlaneta() {
            return planeta;
        }

        public int getUklad() {
            return uklad;
        }

        public boolean isPlanet() {
            return planet;
        }

        public boolean isKsiezyc() {
            return ksiezyc;
        }

        public int getTechnologiaNadprzestrzenna() {
            return technologiaNadprzestrzenna;
        }

        public int getGodzina() {
            return godzina;
        }

        public int getMinuta() {
            return minuta;
        }

        public int getSekunda() {
            return sekunda;
        }

        public int getIloscSurowcow() {
            return iloscSurowcow;
        }

        /*
                        SETTERS
                         */
        public void setPlaneta(int planeta) {
            this.planeta = planeta;
        }

        public void setGalaktyka(int galaktyka) {
            this.galaktyka = galaktyka;
        }

        public void setUklad(int uklad) {
            this.uklad = uklad;
        }

        public void setPlanet(boolean planet) {
            this.planet = planet;
        }

        public void setKsiezyc(boolean ksiezyc) {
            this.ksiezyc = ksiezyc;
        }

        public void setTechnologiaNadprzestrzenna(int technologiaNadprzestrzenna) {
            this.technologiaNadprzestrzenna = technologiaNadprzestrzenna;
        }

        public void setGodzina(int godzina) {
            this.godzina = godzina;
        }

        public void setMinuta(int minuta) {
            this.minuta = minuta;
        }

        public void setSekunda(int sekunda) {
            this.sekunda = sekunda;
        }

        public void setIloscSurowcow(int iloscSurowcow) {
            this.iloscSurowcow = iloscSurowcow;
        }
    }
}
