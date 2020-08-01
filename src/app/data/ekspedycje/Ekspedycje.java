package app.data.ekspedycje;

import app.Wspolrzedne;
import app.czas.CzasGry;
import app.czas.CzasLotu;
import app.czas.Data;
import app.data.MatMadFile;
import app.data.accounts.Accounts;
import app.ruchflot.Lot;
import app.ruchflot.Loty;
import com.Log;
import ogame.flota.Flota;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Ekspedycje
{
    public static Config configuration = load();
    private static final String CONFIG_FILE_NAME = "config.eks";
    private static final String FOLDER_NAME = "ekspedycje\\";
    public static List<Ekspedycja> listaEkspedycji = new ArrayList<>();
    public static int aktualnaIloscEkspedycji = -1;
    public static int maxIloscEkspedycji = -1;
    public static Ekspedycja najblizszaEkspedycja;
    public static boolean updateOpoznionaVariable = false;

    /**
     * Dodaje nową ekspedycję do listy.
     * @param ekspedycja Nowa ekspedycja.
     */
    public static void dodajEkspedycje(Ekspedycja ekspedycja)
    {
        if(exists(ekspedycja.getId()))
            Log.printLog(Ekspedycje.class.getName(),"Ekspedycja o ID " + ekspedycja.getId()+  " istnieje na liście.");
        else
        {
            listaEkspedycji.add(ekspedycja);
            Log.printLog(Ekspedycje.class.getName(),"Dodano nową ekspedycję do listy.");
            najblizszaEkspedycja = najblizszaEkspedycja();
        }
    }

    /**
     * Sprawdza czy podana ekspedycja w argumencie już znajduje się na liście.
     * @param ekspedycja Ekspedycja która ma być sprawdzona czy już się znajduje na liście.
     */
    private static  boolean exists(Ekspedycja ekspedycja)
    {
        if(listaEkspedycji.size() > 0)
            for(Ekspedycja e : listaEkspedycji)
                if(e.getPrzylot().getData().equals(ekspedycja.getPrzylot().getData()) &&
                e.getPrzylot().getCzas().equals(ekspedycja.getPrzylot().getCzas()))
                    return true;

        return false;
    }

    /**
     * Sprawdza czy podana ekspedycja w argumencie już znajduje się na liście.
     * @param id Id nowej ekpsedycji
     * @return Jeżeli id będzie puste ("") to zwróci <b>false</b>  oraz zwróci <b>true</b>, gdy na liście będzie znajdować
     * się Ekspedycja z podanym id na liście.
     */
    private static  boolean exists(String id)
    {
        if(id.equals(""))
            return false;

        if(listaEkspedycji.size() > 0)
            for(Ekspedycja e : listaEkspedycji)
                if(e.getId().equals(id))
                    return true;

        return false;
    }

    public static void usunMisje(int ilosc)
    {
        int a = 0;
        while(a < ilosc)
        {
            if(listaEkspedycji.size() > 0)
            {
                Log.printLog(Ekspedycje.class.getName(),"Usuwam ekspedycję z listy. \n" +
                        listaEkspedycji.get(0).toString());
                listaEkspedycji.remove(0);
            }
            a++;
        }
        najblizszaEkspedycja = najblizszaEkspedycja();
    }

    /**
     * Usuwa wprowadzone misje.
     * @param ekspedycja Ekspedycja do usunięcia.
     */
    public static void usunMisje(List<Ekspedycja> ekspedycja)
    {
        for(Ekspedycja e : ekspedycja)
        {
            listaEkspedycji.remove(e);
            Log.printLog(Ekspedycje.class.getName(),"Usuwam ekspedycję z listy. " + e.getId());
        }
        najblizszaEkspedycja = najblizszaEkspedycja();
    }

    /**
     * Sprawdza czy na liście znajduje się ekpedycja której minął czas powrotu.
     * @return Zwróci <b>true</b> jeżeli,
     */
    public static boolean sprawdzCzyWrocila()
    {
        if(listaEkspedycji.size() > 0)
        {
            for(Ekspedycja e : listaEkspedycji)
            {
                Log.printLog(Ekspedycje.class.getName(),"Data " + e.getPowrot().getData().toString() +"\n Czas "+
                        e.getPowrot().getCzas().toString());
                if(e.getPowrot().getData().equals(CzasGry.getData()) && e.getPowrot().getCzas().czasWSekundach() <=
                        CzasGry.getCzas().czasWSekundach())
                    return true;
            }

        }
        else
            return true;

        return false;
    }

    /**
     * Zwraca ekspedycję o okreslonym ID.
     * @param id ID ekspedycjia, która ma być zwrócona.
     * @return Zwraca ekspedycję, jeżeli istnieje na liście w innym wypadku zwróci null.
     */
    private static Ekspedycja getEkspedycje(String id)
    {
        for(Ekspedycja e : listaEkspedycji)
        {
            if(e.getId().equals(id))
                return e;
        }
        return null;
    }

    /**
     * Sprawdza listę, czy występują w niej obiekt bez przypisanego ID.
     * @return Jeżeli na liście znajduje się obiekt bez przypisanego ID, zwróci <b>true</b>
     */
    public static boolean uzupelnicIdWLiscie()
    {
        for(Ekspedycja e : listaEkspedycji)
        {
            if(e.getId().equals(""))
                return true;
        }
        return false;
    }

//    /**
//     * Oblicza czas w sekundach jaki pozostał do powrotu najbliższej Ekspedycji.
//     * @return Ilość sekund do powrotu.
//     */
//    public static int iloscSekundDoPowrotuNajblizszejEkspedycji()
//    {
//        Data data = CzasGry.getData();
//        Ekspedycja tmp = null;
//        List<Ekspedycja> tmpList = new ArrayList<>();
//
////        Log.printLog1("Rozpoczynam uzupełnianie tymczasowej listy w Ekspedycje z datą "+id,WrogieMisje.class,"najblizszaMisja(int,List<WrogaMisja>",237);
////        for(WrogaMisja wrogaMisja : misjeNaWskazaneWspolrzedne)
////        {
////            if(wrogaMisja.getId() != id)
////                tmpList2.add(wrogaMisja);
////        }
////
////        Log.printLog1("Zakończyłem uzupełnianie tymczasowej listy w misje z ID != "+id +
////                ". Odnaleziona ilość misji to "+tmpList2.size(),WrogieMisje.class,"najblizszaMisja(int,List<WrogaMisja>",244);
//
////        if(tmpList2.size() > 0)
////        {
//        //Sprawdzanie czy są ekspedycje, których powrót jest dzisiaj
//        Log.printLog(Ekspedycje.class.getName(),"Rozpoczynam uzupełnianie tymczasowej listy w misje z datą = "+data.toString());
//        for(Ekspedycja  ekspedycja : listaEkspedycji)
//        {
//            if(ekspedycja.getPowrot().getData().equals(data))
//                tmpList.add(ekspedycja);
//        }
//        Log.printLog(Ekspedycje.class.getName(),"Rozpoczynam uzupełnianie tymczasowej listy w misje z datą = "+data.toString()+
//                ". Odnaleziona ilość misji to " +tmpList.size());
//
////        }
////        else
////            return null;
//
//        //Gdy nie znaleziono ekspedycji, których powrot jest dzisiaj, sprawdzane są ekspedycje których powrót jest jutro.
//        if(tmpList.size() == 0)
//        {
//            data = data.getTommorowDate();
//            Log.printLog(Ekspedycje.class.getName(),"Rozpoczynam uzupełnianie tymczasowej listy w misje z datą = "+data.toString()+
//                    ", ponieważ z poprzednią datą nie znaleziono żadnej misji.");
//
//            for(Ekspedycja  ekspedycja : listaEkspedycji)
//            {
//                if(ekspedycja.getPowrot().getData().equals(data))
//                    tmpList.add(ekspedycja);
//            }
//        }
//
//        //Zwracanie najbliższej ekspedycji
//        if(tmpList.size() > 0)
//        {
//            tmp = tmpList.get(0);
//            for(Ekspedycja e : tmpList)
//            {
//                if(e.getPowrot().getCzas().czasWSekundach() < tmp.getPowrot().getCzas().czasWSekundach())
//                    tmp = e;
//            }
//        }
//        int sekundy;
//        if(tmp != null)
//            sekundy = tmp.getPowrot().getCzas().czasWSekundach() - CzasGry.getCzas().czasWSekundach();
//        else
//            sekundy = 300;
//
//        Log.printLog(Ekspedycje.class.getName(),"Zwracam " + (tmp != null ? tmp.toString():"null") + ".\n Najbliższa ekspedycja wróci za "
//        + sekundy + " sek.");
////        Log.printLog1("Zwracam " + tmp.toString(),WrogieMisje.class,"najblizszaMisja(int,List<WrogaMisja>",308);
//        return sekundy;
//    }

    /**
     * Zwraca tablicę z ekspedycjami, które zostały ukończone. Sprawdza czy te ekspedycje nie zostały opoźnione.
     * @return Zwraca listę ekspedycji, ktorę powróciły - zakończyły się.
     */
    public static List<Ekspedycja> ukonczoneEkspedycje()
    {
        List<Ekspedycja> tmp = new ArrayList<>();
        //Ekspedycje które zostały ukonczone
        for(Ekspedycja e : listaEkspedycji)
        {
            if(e.getPowrot().getCzas().czasWSekundach() < CzasGry.getCzas().czasWSekundach())
                tmp.add(e);
        }

        List<Lot> ekspedycje = Loty.getLoty("Ekspedycja");
        List<Ekspedycja> toRemove = new ArrayList<>();
        //Sprawdza czy ukonczone ekspedycje nie zostały opoźnione, poprzez sprawdzenie czy nie ma ich na liście ruchu flot.
        for(Ekspedycja e : tmp)
        {
            for(Lot l : ekspedycje)
            {
                if(e.getId().equals(l.getId()))
                {
                    e.setOpozniona(true);
                    toRemove.add(e);
                    getEkspedycje(e.getId()).setOpozniona(true);
                    updateOpoznionaVariable = true;
                }
            }
        }

        //Usuwanie opóźnionych ekspedycji
        for(Ekspedycja e : toRemove)
        {
            tmp.remove(e);
        }

        return tmp;
    }

    /**
     * Oblicza czas w sekundach jaki pozostał do powrotu najbliższej Ekspedycji.
     * @return Ilość sekund do powrotu.
     */
    private static Ekspedycja najblizszaEkspedycja()
    {
        Data data = CzasGry.getData();
        Ekspedycja tmp = new Ekspedycja(new CzasLotu(),new CzasLotu(),new CzasLotu());
        List<Ekspedycja> tmpList = new ArrayList<>();

        //Sprawdzanie czy są ekspedycje, których powrót jest dzisiaj
        Log.printLog(Ekspedycje.class.getName(),"Rozpoczynam uzupełnianie tymczasowej listy w ekspedycje z datą = "+data.toString());
        for(Ekspedycja  ekspedycja : listaEkspedycji)
        {
            if(ekspedycja.getPowrot().getData().equals(data))
                tmpList.add(ekspedycja);
        }
        Log.printLog(Ekspedycje.class.getName(),"Zakończyłem uzupełnianie tymczasowej listy w ekspedycje z datą = "+data.toString()+
                ". Odnaleziona ilość misji to " +tmpList.size());



        //Gdy nie znaleziono ekspedycji, których powrot jest dzisiaj, sprawdzane są ekspedycje których powrót jest jutro.
        if(tmpList.size() == 0)
        {
            data = data.getTommorowDate();
            Log.printLog(Ekspedycje.class.getName(),"Rozpoczynam uzupełnianie tymczasowej listy w ekspedycje z datą = "+data.toString()+
                    ", ponieważ z poprzednią datą nie znaleziono żadnej misji.");

            for(Ekspedycja  ekspedycja : listaEkspedycji)
            {
                if(ekspedycja.getPowrot().getData().equals(data))
                    tmpList.add(ekspedycja);
            }
        }

        //Zwracanie najbliższej ekspedycji
        if(tmpList.size() > 0)
        {
            tmp = tmpList.get(0);
            for(Ekspedycja e : tmpList)
            {
                if(e.getPowrot().getCzas().czasWSekundach() < tmp.getPowrot().getCzas().czasWSekundach())
                    tmp = e;
            }
        }

        return tmp;
    }
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
                Log.printLog(Ekspedycje.class.getName(),"Zapisano dane konfiguracji Ekspedycji pod ścieżką "+pathName);
                return true;
            }
            else
            {
                com.Log.printLog(Ekspedycje.class.getName(),
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

    public static Wspolrzedne getWspolrzedneStartu()
    {
        return new Wspolrzedne(configuration.getGalaktyka(),configuration.getUklad(),configuration.getPlaneta());
    }

    public static class Config implements Serializable
    {
        private static final long serialVersionUID = 1L;
        private int galaktyka;
        private int planeta;
        private int uklad;
        private int czas;
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

        public int getCzas() {
            return czas;
        }

        public boolean isPlanet() {
            return planet;
        }

        public boolean isKsiezyc() {
            return ksiezyc;
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

        public void setCzas(int czas) {
            this.czas = czas;
        }

        public void setPlanet(boolean planet) {
            this.planet = planet;
        }

        public void setKsiezyc(boolean ksiezyc) {
            this.ksiezyc = ksiezyc;
        }
    }
}
