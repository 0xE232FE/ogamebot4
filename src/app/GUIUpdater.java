package app;


import app.czas.CzasGry;
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
                };
                Platform.runLater(updater);
            }
            Waiter.sleep(100,250);
        }
    }


    private void updateCzasGry()
    {
       getController().setTime(CzasGry.getData().toString() + " " + CzasGry.getCzas().toString());
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


    private void updateBadania()
    {

    }

    private long lastUpdateMojePlanety = 0;
    private void updateMojePlanety()
    {

    }

    private long lastUpdateAktywnosc = 0;
    private void updateAktywnosc()
    {

    }

    private long lastUpdateAutoTransport = 0;
    public void updateAutoTransport()
    {


    }

    private long lastUpdateAutoFarming = 0;

    public void updateAutoFarming()
    {

    }
}
