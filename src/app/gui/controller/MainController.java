package app.gui.controller;

import app.Run;
import app.gui.ekspedycje.EkspedycjeTabController;
import app.gui.fleet_save_attack.FleetSaveAttackRootController;
import app.tasks.BotLogic;
import com.Log;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainController
{
    private BotLogic botLogic;

    @FXML
    private VBox vBoxActiveTask;

    @FXML
    public void initialize()
    {
        Run run = new Run(this);
        run.setRun(true);
    }

//    @FXML
//    public void initialize()
//    {
//        run = new Run(this);
//        run.setRun(true);
//    }

    @FXML
    private Button buttonStopBot;

    @FXML
    void stopBot() {

        if(botLogic.isRun())
        {
            botLogic.setRun(false);
            buttonStopBot.setText("OFF");
            Log.printLog(MainController.class.getName(),"Zatrzymuję działanie bota.");
        }
        else
        {
            botLogic.setRun(true);
            buttonStopBot.setText("ON");
            Log.printLog(MainController.class.getName(),"Wznawiam działanie bota.");
        }
    }

    /**
     *  Czas Gry
     */

    @FXML
    private Label labelGameTime;

    /*
        Ekspedycje
     */
    @FXML private EkspedycjeTabController ekspedycjeTabController;

    public EkspedycjeTabController getEkspedycjeTabController() {
        return ekspedycjeTabController;
    }

    /*
            Fleet Save Attack GUI
         */
    @FXML
    private FleetSaveAttackRootController fleetSaveAttackRootController;

    public FleetSaveAttackRootController getFleetSaveAttackRootController() {
        return fleetSaveAttackRootController;
    }

    public void setTime(String time)
    {
        labelGameTime.setText(time);
    }

    /*
        GETTERS
     */

    public VBox getvBoxActiveTask() {
        return vBoxActiveTask;
    }

    /*
        SETTERS
     */

    public void setBotLogic(BotLogic botLogic) {
        this.botLogic = botLogic;
    }
}
