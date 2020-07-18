package app.atak;

import app.czas.Czas;
import app.czas.CzasGry;
import app.czas.Data;
import app.log.LogFleetSaveAttack;
import com.Log;
import ogame.ruchflotzdarzenia.WrogaMisja;

import java.util.ArrayList;
import java.util.List;

public class WrogieMisje
{
    public static List<WrogaMisja> misje = new ArrayList<>();


    /**
     * Dodaje misję podaną w argumencie do listy misji.
     */
    public static void add(WrogaMisja wrogaMisja)
    {
        if(!exists(wrogaMisja))
        {
            misje.add(wrogaMisja);
            LogFleetSaveAttack.addLog(new app.log.Log(WrogieMisje.class.getName(),"Wykryto nową wrogą misję. Planeta - "+wrogaMisja.isNaPlanete() + " Księżyc - "+wrogaMisja.isNaKsiezyc()
                    + " Współrzędne - "+wrogaMisja.getWspolrzedne() + " Data - "+wrogaMisja.getData().toString() +
                    " Czas - "+ wrogaMisja.getCzas().toString()));
//            LogFleetSaveAttack.addLog(LogFleetSaveAttack.log(WrogieMisje.class,
//                    "Wykryto nową wrogą misję. Planeta - "+wrogaMisja.isNaPlanete() + " Księżyc - "+wrogaMisja.isNaKsiezyc()
//            + " Współrzędne - "+wrogaMisja.getWspolrzedne() + " Data - "+wrogaMisja.getData().toString() +
//                    " Czas - "+ wrogaMisja.getCzas().toString()));
            Log.printLog(WrogieMisje.class.getName(), "Dodałem nową wrogą misję do listy.");
        }
        else
            Log.printLog(WrogieMisje.class.getName(), "Wroga misja znajduję się na liście.");
    }

    /**
     * Z listy wprowadzonej jako parametr, dodaje WrogeMisje, które nie istnieją na liście.
     * @param misje Lista misji.
     */
    public static void setMisje(List<WrogaMisja> misje) {

        for(WrogaMisja wrogaMisja : misje)
            add(wrogaMisja);
    }

    /**
     * Sprawdza czy podana misja w argumencie już znajduje się na liście.
     * @param wrogaMisja Misja która ma być sprawdzona czy już się znajduje na liście.
     */
    private static  boolean exists(WrogaMisja wrogaMisja)
    {

        if(misje.size() > 0)
        {
            for(WrogaMisja misja : misje)
            {
                if(misja.getId() == wrogaMisja.getId())
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Usuwa wskazaną w argumencie misję z listy misji.
     */
    public static void remove(WrogaMisja wrogaMisja)
    {
        misje.remove(wrogaMisja);
        Log.printLog(WrogieMisje.class.getName(), "Usunięto wrogą misję z listy.");
        LogFleetSaveAttack.addLog(new app.log.Log(WrogieMisje.class.getName(),"Usunięto wrogą misję z listy. Planeta - "+wrogaMisja.isNaPlanete() + " Księżyc - "+wrogaMisja.isNaKsiezyc()
                + " Współrzędne - "+wrogaMisja.getWspolrzedne() + " Data - "+wrogaMisja.getData().toString() +
                " Czas - "+ wrogaMisja.getCzas().toString()));
//        LogFleetSaveAttack.addLog(LogFleetSaveAttack.log(WrogieMisje.class,
//                "Usunięto wrogą misję z listy. Planeta - "+wrogaMisja.isNaPlanete() + " Księżyc - "+wrogaMisja.isNaKsiezyc()
//                        + " Współrzędne - "+wrogaMisja.getWspolrzedne() + " Data - "+wrogaMisja.getData().toString() +
//                        " Czas - "+ wrogaMisja.getCzas().toString()));
    }


    /**
     * Zwraca listę misji w których celem są współrzedne podane w argumencie.
     * @return Jeżeli nie ma misji o określonych argumentach to zwróci pustą listę.
     */
    public static List<WrogaMisja> getMisje (String wspolrzedne)
    {
        List<WrogaMisja> tmp = new ArrayList<>();

        if(misje.size() > 0)
        {
            for(WrogaMisja misja : misje)
            {
                if(misja.getWspolrzedne().equals(wspolrzedne))
                    tmp.add(misja);
            }
        }

        return tmp;
    }

    /**
     * Zwraca listę misji w których celem są współrzedne podane w argumencie.
     * @return Jeżeli nie ma misji o określonych argumentach to zwróci pustą listę.
     */
    public static List<WrogaMisja> getMisje (String wspolrzedne, boolean moon)
    {
        List<WrogaMisja> tmp = new ArrayList<>();

        if(misje.size() > 0)
        {
            for(WrogaMisja misja : misje)
            {
                if(misja.getWspolrzedne().equals(wspolrzedne) && moon ? misja.isNaKsiezyc():misja.isNaPlanete())
                    tmp.add(misja);
            }
        }

        return tmp;
    }

    /**
     * Zwraca misję o podanym ID. Z listy misji.
     * @return Jeżeli nie ma misji o podanym ID zwróci null.
     */
    public static WrogaMisja misja(int id)
    {
        if(misje.size() > 0)
        {
            for(WrogaMisja misja : misje)
            {
                if(misja.getId() == id)
                    return misja;
            }
        }
        return null;
    }

    /**
     * Zwraca misję o podanym ID. Z listy misji podanej jako drugi parametr.
     * @return Jeżeli nie ma misji o podanym ID zwróci null.
     */
    public static WrogaMisja misja(int id, List<WrogaMisja> listaMisji)
    {
        if(listaMisji.size() > 0)
        {
            for(WrogaMisja misja : listaMisji)
            {
                if(misja.getId() == id)
                    return misja;
            }
        }
        return null;
    }

    /**
     * Sprawdza czy lista wymaga ponownego wczytania. Jeżeli czas ataku minął, to lista zostanie ponownie wczytana.
     * @return Jeżeli od wejścia ataku mineło conajmniej 5 sek. to zwróci <b>true</b>.
     */
    public static boolean needReload()
    {
        if(misje.size() > 0)
        {
            for(WrogaMisja misja : misje)
            {
                if(misja.atakMinal(CzasGry.getCzas(),5))
                    return true;
            }
        }
        return false;
    }
    /**
     * Zwraca listę misji, którym minął czas - atak wszedł.
     * Jeżeli nie ma takich misjis zwróci pustą listę.
     */
    private static List<WrogaMisja> misjePoCzasie()
    {
        List<WrogaMisja> tmp = new ArrayList<>();

        for(WrogaMisja misja : misje)
        {
            if(misja.atakMinal(CzasGry.getCzas(),1))
                tmp.add(misja);
        }

        return tmp;
    }

    /**
     * Zwraca z listy misję wrogą na planetę, która wchodzi najwcześniej. Misja ta musi być różna od misji do której jest
     * porównywana, tzn. że nie będzie to misja po opóźnieniu tylko inna wroga misja.
     * @param id Id misji wyjściowej.
     * @param misjeNaWskazaneWspolrzedne Lista misji wrogi na wskazane współrzędne.
     */
    public static WrogaMisja najblizszaMisja(int id, List<WrogaMisja> misjeNaWskazaneWspolrzedne)
    {

        Data data = CzasGry.getData();
        WrogaMisja tmp = null;
        List<WrogaMisja> tmpList = new ArrayList<>();
        List<WrogaMisja> tmpList2 = new ArrayList<>();

        Log.printLog1("Rozpoczynam uzupełnianie tymczasowej listy w misje z ID != "+id,WrogieMisje.class,"najblizszaMisja(int,List<WrogaMisja>",237);
        for(WrogaMisja wrogaMisja : misjeNaWskazaneWspolrzedne)
        {
            if(wrogaMisja.getId() != id)
                tmpList2.add(wrogaMisja);
        }

        Log.printLog1("Zakończyłem uzupełnianie tymczasowej listy w misje z ID != "+id +
                ". Odnaleziona ilość misji to "+tmpList2.size(),WrogieMisje.class,"najblizszaMisja(int,List<WrogaMisja>",244);

        if(tmpList2.size() > 0)
        {
            Log.printLog1("Rozpoczynam uzupełnianie tymczasowej listy w misje z datą = "+data,WrogieMisje.class,"najblizszaMisja(int,List<WrogaMisja>",279);
            for(WrogaMisja wrogaMisja : tmpList2)
            {
                if(wrogaMisja.getData().equals(data))
                    tmpList.add(wrogaMisja);
            }
            Log.printLog1("Zakończyłem uzupełnianie tymczasowej listy w misje z datą = "+data +
                    ". Odnaleziona ilość misji to "+tmpList.size(),WrogieMisje.class,"najblizszaMisja(int,List<WrogaMisja>",286);
        }
        else
            return null;


        if(tmpList.size() == 0)
        {
            data = data.getTommorowDate();
            Log.printLog1("Rozpoczynam uzupełnianie tymczasowej listy w misje z datą = "+data+
                    ", ponieważ z poprzednią datą nie znaleziono żadnej misji.",WrogieMisje.class,"najblizszaMisja(int,List<WrogaMisja>",291);
            for(WrogaMisja wrogaMisja : tmpList2)
            {
                if(wrogaMisja.getData().equals(data))
                {
                    if(wrogaMisja.getCzas().czasWSekundach() - CzasGry.getCzas().czasWSekundach() > 0)
                        tmpList.add(wrogaMisja);
                }
            }
        }

        if(tmpList.size() > 0)
        {
            tmp = tmpList.get(0);
            for(WrogaMisja wrogaMisja : tmpList)
            {
                if(wrogaMisja.getCzas().czasWSekundach() < tmp.getCzas().czasWSekundach())
                    tmp = wrogaMisja;
            }
        }
        Log.printLog1("Zwracam " + tmp.toString(),WrogieMisje.class,"najblizszaMisja(int,List<WrogaMisja>",308);
        return tmp;
    }

    /**
     * Zwraca z listy misję wrogą na planetę, która wchodzi najwcześniej. Misja ta musi być różna od misji do której jest
     * porównywana, tzn. że nie będzie to misja po opóźnieniu tylko inna wroga misja.
     * @param id Id misji wyjściowej.
     * @param misjeNaWskazaneWspolrzedne Lista misji wrogi na wskazane współrzędne.
     * @param moon  Czy atak jest na księżyc.
     */
    public static WrogaMisja najblizszaMisja(int id, List<WrogaMisja> misjeNaWskazaneWspolrzedne, boolean moon)
    {

        Data data = CzasGry.getData();
        WrogaMisja tmp = null;
        List<WrogaMisja> tmpList = new ArrayList<>();
        List<WrogaMisja> tmpList2 = new ArrayList<>();

        Log.printLog1("Rozpoczynam uzupełnianie tymczasowej listy w misje z ID != "+id,WrogieMisje.class,"najblizszaMisja(int,List<WrogaMisja>",237);
        for(WrogaMisja wrogaMisja : misjeNaWskazaneWspolrzedne)
        {
            if(wrogaMisja.getId() != id)
                tmpList2.add(wrogaMisja);
        }

        Log.printLog1("Zakończyłem uzupełnianie tymczasowej listy w misje z ID != "+id +
                ". Odnaleziona ilość misji to "+tmpList2.size(),WrogieMisje.class,"najblizszaMisja(int,List<WrogaMisja>",244);

        if(tmpList2.size() > 0)
        {
            Log.printLog1("Rozpoczynam uzupełnianie tymczasowej listy w misje z datą = "+data,WrogieMisje.class,"najblizszaMisja(int,List<WrogaMisja>",279);
            for(WrogaMisja wrogaMisja : tmpList2)
            {
                if(wrogaMisja.getData().equals(data) && moon ? wrogaMisja.isNaKsiezyc():wrogaMisja.isNaPlanete())
                    tmpList.add(wrogaMisja);
            }
            Log.printLog1("Zakończyłem uzupełnianie tymczasowej listy w misje z datą = "+data +
                    ". Odnaleziona ilość misji to "+tmpList.size(),WrogieMisje.class,"najblizszaMisja(int,List<WrogaMisja>",286);
        }
        else
            return null;


        if(tmpList.size() == 0)
        {
            data = data.getTommorowDate();
            Log.printLog1("Rozpoczynam uzupełnianie tymczasowej listy w misje z datą = "+data+
                    ", ponieważ z poprzednią datą nie znaleziono żadnej misji.",WrogieMisje.class,"najblizszaMisja(int,List<WrogaMisja>",291);
            for(WrogaMisja wrogaMisja : tmpList2)
            {
                if(wrogaMisja.getData().equals(data) && moon ? wrogaMisja.isNaKsiezyc():wrogaMisja.isNaPlanete())
                {
                    if(wrogaMisja.getCzas().czasWSekundach() - CzasGry.getCzas().czasWSekundach() > 0)
                        tmpList.add(wrogaMisja);
                }
            }
        }

        if(tmpList.size() > 0)
        {
            tmp = tmpList.get(0);
            for(WrogaMisja wrogaMisja : tmpList)
            {
                if(wrogaMisja.getCzas().czasWSekundach() < tmp.getCzas().czasWSekundach())
                    tmp = wrogaMisja;
            }
        }
        Log.printLog1("Zwracam " + tmp.toString(),WrogieMisje.class,"najblizszaMisja(int,List<WrogaMisja>",308);
        return tmp;
    }

    /**
     * Zwraca z listy misję wrogą na planetę, która wchodzi najwcześniej.
     * @param misjeNaWskazaneWspolrzedne - lista misji wrogi wchodzących na te same współrzędne.
     * @return Wroga Misja.
     */
    public static WrogaMisja najblizszaMisja(List<WrogaMisja> misjeNaWskazaneWspolrzedne)
    {
        Data data = CzasGry.getData();
        WrogaMisja tmp = null;
        List<WrogaMisja> tmpList = new ArrayList<>();

        Log.printLog1("Rozpoczynam uzupełnianie tymczasowej listy w misje z datą = "+data,WrogieMisje.class,"najblizszaMisja(List<WrogaMisja>",279);
        for(WrogaMisja wrogaMisja : misjeNaWskazaneWspolrzedne)
        {
            if(wrogaMisja.getData().equals(data))
                tmpList.add(wrogaMisja);
        }
        Log.printLog1("Zakończyłem uzupełnianie tymczasowej listy w misje z datą = "+data +
                ". Odnaleziona ilość misji to "+tmpList.size(),WrogieMisje.class,"najblizszaMisja(List<WrogaMisja>",286);
        if(tmpList.size() == 0)
        {
            data = data.getTommorowDate();
            Log.printLog1("Rozpoczynam uzupełnianie tymczasowej listy w misje z datą = "+data+
                    ", ponieważ z poprzednią datą nie znaleziono żadnej misji.",WrogieMisje.class,"najblizszaMisja(List<WrogaMisja>",291);
            for(WrogaMisja wrogaMisja : misjeNaWskazaneWspolrzedne)
            {
                if(wrogaMisja.getData().equals(data))
                {
                    if(wrogaMisja.getCzas().czasWSekundach() - CzasGry.getCzas().czasWSekundach() > 0)
                        tmpList.add(wrogaMisja);
                }
            }
        }

        if(tmpList.size() > 0)
        {
            tmp = misjeNaWskazaneWspolrzedne.get(0);
            for(WrogaMisja wrogaMisja : tmpList)
            {
                    if(wrogaMisja.getCzas().czasWSekundach() < tmp.getCzas().czasWSekundach())
                        tmp = wrogaMisja;
            }
        }
        Log.printLog1("Zwracam " + tmp.toString(),WrogieMisje.class,"najblizszaMisja(List<WrogaMisja>",308);
        return tmp;
    }

    /**
     * Wypisuję dane misji z listy.
     */
    public static void print()
    {
        for(WrogaMisja misja : misje)
        {
            Log.printLog(WrogieMisje.class.getName(), misja.toString());
        }
    }

    /**
     * Wypisuje czas jaki pozostał do wejścia ataku. Poniżej 15 sek co 1 sek. Poniżej 3 min. co 5 sek.
     * Poniżej 10 min. co 15 sek. Powyżej 10 min. co 1 min.
     */
    public static void printLeftTime()
    {
        for(WrogaMisja misja : misje)
        {
            Czas czas = misja.pozostaloDoAtaku(CzasGry.getCzas(),CzasGry.getData());
            int a  = czas.czasWSekundach();

            if(a <= 15)
                Log.printLog(WrogieMisje.class.getName(),"Pozostało " +czas.toString()
                    + " do ataku na " + misja.getWspolrzedne() + " - " + (misja.isNaPlanete() ? "PLANETA" : "KSIEŻYC") + " - " + misja.getId());
            else if(a <= 180 && a % 5 == 0)
                Log.printLog(WrogieMisje.class.getName(),"Pozostało " +czas.toString()
                        + " do ataku na " + misja.getWspolrzedne() + " - " + (misja.isNaPlanete() ? "PLANETA" : "KSIEŻYC") + " - " + misja.getId());
            else if(a <= 600 && a > 180 && a%15 == 0)
                Log.printLog(WrogieMisje.class.getName(),"Pozostało " +czas.toString()
                        + " do ataku na " + misja.getWspolrzedne() + " - " + (misja.isNaPlanete() ? "PLANETA" : "KSIEŻYC") + " - " + misja.getId());
            else if(a > 600 && a%60 == 0)
                Log.printLog(WrogieMisje.class.getName(),"Pozostało " +czas.toString()
                        + " do ataku na " + misja.getWspolrzedne() + " - " + (misja.isNaPlanete() ? "PLANETA" : "KSIEŻYC") + " - " + misja.getId());
        }
    }
}
