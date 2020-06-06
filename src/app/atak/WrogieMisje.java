package app.atak;

import app.czas.CzasGry;
import com.Log;
import ogame.ruchflotzdarzenia.WrogaMisja;

import java.util.ArrayList;
import java.util.List;

public class WrogieMisje
{
    public static List<WrogaMisja> misje = new ArrayList<>();

    public static void add(WrogaMisja wrogaMisja)
    {
        if(!exists(wrogaMisja))
        {
            misje.add(wrogaMisja);
            Log.printLog(WrogieMisje.class.getName(), "Dodałem nową wrogą misję do listy.");
        }
        else
            Log.printLog(WrogieMisje.class.getName(), "Wroga misja znajduję się na liście.");
    }

    public static void setMisje(List<WrogaMisja> misje) {
        for(WrogaMisja wrogaMisja : misje)
            add(wrogaMisja);
    }

    public static  boolean exists(WrogaMisja wrogaMisja)
    {
        if(misje.size() > 0)
        {
            for(WrogaMisja misja : misje)
            {
                if(misja.getId() == wrogaMisja.getId())
                    return true;
            }
        }
        return false;
    }

    public static void remove(WrogaMisja wrogaMisja)
    {
        misje.remove(wrogaMisja);
        Log.printLog(WrogieMisje.class.getName(), "Usunięto wrogą misję z listy.");
    }

    public static void print()
    {
        for(WrogaMisja misja : misje)
        {
            Log.printLog(WrogieMisje.class.getName(), misja.toString());
        }
    }

    public static void printLeftTime()
    {
        for(WrogaMisja misja : misje)
        {

            Log.printLog(WrogieMisje.class.getName(),"Pozostało " +misja.pozostałoDoAtaku(CzasGry.getCzas()).toString()
                    + " do atakua na " + misja.getWspolrzedne() + " - " + (misja.isNaPlanete() ? "PLANETA" : "KSIEŻYC"));
        }
    }
}
