package app.tasks;

import app.OgameWeb;
import app.Task;
import app.TaskManager;
import app.czas.CzasGry;
import app.czas.CzasWykonania;
import com.Log;
import com.Waiter;
import ogame.LeftMenu;
import org.openqa.selenium.WebDriver;

public class BotLogic extends Task {

    public BotLogic(WebDriver w)
    {
        super(w);
        setThread(new Thread(this));
        startThread();
    }

    @Override
    public void run() {
        while(true)
        {
            if(isRun())
            {
                TaskManager.getTasks()[0].execute();
                TaskManager.getTasks()[1].execute();
                TaskManager.getTasks()[2].execute();
                TaskManager.getTasks()[3].execute();
                klikPodglad();
            }
            Waiter.sleep(10,40);
        }
    }

    boolean init = true;
    CzasWykonania czasWykonania = new CzasWykonania();

    private void klikPodglad()
    {
        if(init)
        {
            if(CzasGry.getCzas().czasWSekundach() > 0)
            {
                czasWykonania.setCzasString(CzasGry.getCzas().toString());
                czasWykonania.setDataString(CzasGry.getData().toString());
                init = false;
                Log.printLog(BotLogic.class.getName(),"Inicjalizuję dane do metody klikPodglad().");
            }
        }
        else
        {
//            Log.printLog(BotLogic.class.getName(),"Aktualny data "+CzasGry.getData().toString() + " Data ostatniego wykonania"
//            + czasWykonania.getData() + " Aktualnu czas " + CzasGry.getCzas().toString() + " Czas ostatniego wykonania " +
//                    czasWykonania.getCzas().toString() +" \n Różnica " + (CzasGry.getCzas().czasWSekundach() - czasWykonania.getCzas().czasWSekundach()));
            if(CzasGry.getData().toString().equals(czasWykonania.getData()) &&
                    CzasGry.getCzas().czasWSekundach() - czasWykonania.getCzas().czasWSekundach() >=  210)
            {
                LeftMenu.pressPodglad(OgameWeb.webDriver,BotLogic.class.getName(),true);
                Log.printLog(BotLogic.class.getName(),"Kliknięto Podgląd, żeby odświeżyć stronę.");
                czasWykonania.setCzasString(CzasGry.getCzas().toString());
                czasWykonania.setDataString(CzasGry.getData().toString());
            }
        }
    }
}
