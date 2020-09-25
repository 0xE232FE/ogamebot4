package app;


import app.czas.CzasGry;
import app.data.configuration.Configuration;
import app.gui.active_task.ActiveTask;
import app.gui.controller.MainController;
import com.Waiter;
import javafx.application.Platform;
import org.openqa.selenium.WebDriver;

public class GUIUpdater extends Task
{


    GUIUpdater(WebDriver w, MainController controller) {
        super(w, controller);
        setThread(new Thread(this));
        startThread();
    }

    @Override
    public void run() {
        while(true)
        {
            if(isRun())
            {
                Runnable updater = () -> {
                    updateCzasGry();
                    updateAktywneWatki();
                    updateFleetSaveAttack();
                    updateAktualneEkspedycje();
                    updateAutoTransport();
                    updateAlertModuleTest();
                    updateImperium();
                };
                Platform.runLater(updater);
            }
            Waiter.sleep(100,250);
        }
    }


    private void updateCzasGry()
    {
       getController().setTime(CzasGry.getData().toString() + " " + CzasGry.getCzas().toString());

       //Ustawienie czasu uruchomienia.
       if(getController().isInitStartGameTime() && CzasGry.getCzas().czasWSekundach() != 0)
           getController().setStartGameTime(CzasGry.getData().toString() + " " + CzasGry.getCzas().toString());

    }

    private void updateAlertModuleTest()
    {
        if(Configuration.testModule)
            getController().getLabelAlertModulTest().setVisible(true);
        else
            getController().getLabelAlertModulTest().setVisible(false);

    }

    private boolean initAktywneWatki = true;
    private void updateAktywneWatki()
    {

        if(initAktywneWatki && getController() != null && TaskManager.getTasks() != null)
        {
            for(LeafTask leafTask : TaskManager.getTasks())
            {
                ActiveTask activeTask = new ActiveTask(leafTask);
                getController().getvBoxActiveTask().getChildren().add(activeTask.gethBox());
                initAktywneWatki = false;
            }
        }
    }

    private void updateFleetSaveAttack()
    {
        if(getController() != null)
        {
            getController().getFleetSaveAttackRootController().update();
            getController().getFleetSaveAttackRootController().updateLog();
        }
    }

    private void updateAktualneEkspedycje()
    {
        if(getController() != null)
        {
            getController().getEkspedycjeTabController().update();
        }
    }

    private void updateImperium()
    {
        if(getController() != null)
        {
            getController().getImperiumController().update();
            getController().getImperiumController().updateTime();
        }
    }

    private void updateBadania()
    {

    }


    private void updateAutoTransport()
    {
        if(getController() != null)
        {
            getController().getAutotransportTabController().update();
        }
    }

}
