package app.planety;

import com.Log;
import ogame.planety.Planeta;

import java.util.ArrayList;
import java.util.List;

public class Planety
{
    public static List<Planeta> planety = new ArrayList<>();
    public static boolean init = true;

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

    public static void setPlanety(List<Planeta> planety) {
        for(Planeta planeta : planety)
            add(planeta);
    }

    public static  boolean exists(Planeta planeta)
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

    public static void remove(Planeta planeta)
    {
       planety.remove(planeta);
        Log.printLog(Planety.class.getName(), "Usunięto planetę " + planeta.getPozycjaNaLiscie() + " z listy.");
    }

    public static void print()
    {
        for(Planeta p : planety)
        {
            Log.printLog(Planety.class.getName(), p.toString());
        }
    }
}
