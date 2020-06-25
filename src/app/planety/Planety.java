package app.planety;

import app.Wspolrzedne;
import com.Log;
import ogame.planety.Planeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Planety
{
    public static List<Planeta> planety = new ArrayList<>();
    public static boolean init = true;

    /**
     * Dodaje nową planetę do listy podaną w argumencie.
     * @param planeta Planeta do dodania do listy.
     */
    public static void add(Planeta planeta)
    {
        if(!exists(planeta))
        {
            planety.add(planeta);
            Log.printLog(Planety.class.getName(), "Dodałem nową planetę do listy.");
        }
        else
            Log.printLog(Planety.class.getName(), "Planeta znajduję się na liście.");
    }

    /**
     * Ustawia nową listę planet.
     * @param planety Lista planet.
     */
    public static void setPlanety(List<Planeta> planety) {
        for(Planeta planeta : planety)
            add(planeta);
    }

    /**
     * Sprawdza czy wskazana Planeta w argumencie istnieje już na liście.
     * @param planeta Szukana Planeta.
     * @return Jeżeli znajduję się na liście to zwraca <b>true</b>.
     */
    private static  boolean exists(Planeta planeta)
    {
        if(planety.size() > 0)
        {
            for(Planeta p : planety)
            {
                if(p.getId() == planeta.getId())
                    return true;
            }
        }
        return false;
    }

    /**
     * Usuwa podaną w argumencie planetę.
     * @param planeta Planeta do usunięcia.
     */
    public static void remove(Planeta planeta)
    {
        planety.remove(planeta);
        Log.printLog(Planety.class.getName(), "Usunięto planetę " + planeta.getPozycjaNaLiscie() + " z listy.");
    }

    /**
     * Wypisuje dane o Planetach, użycie metody toString()
     */
    public static void print()
    {
        for(Planeta p : planety)
        {
            Log.printLog(Planety.class.getName(), p.toString());
        }
    }

    /**
     * Pobiera z listy Planetę o wskazanych współrzędnych w argumencie.
     * @param wspolrzedne Współrzędne planety.
     */
    public static Planeta getPlaneta(String wspolrzedne)
    {
        Planeta planeta = null;

        for(Planeta p : planety)
        {
            if(wspolrzedne.equals(p.getWspolrzedne()))
            {
                planeta = p;
                break;
            }
        }
        return planeta;
    }

    /**
     * Zwraca losowe współrzędne własnej planety z wykluczeniem planety wporwadzonej jako argument.
     * @param p Planeta, która nie ma być wylosowana.
     * @return Współrzędne wylosowanej Planety.
     */
    public static Wspolrzedne random (Planeta p)
    {
        List<Planeta> tmp = planety;
        tmp.remove(p);

        return tmp.get(new Random().nextInt(tmp.size())).wspolrzedne();
    }
}
