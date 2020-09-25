package app.gui.controller;

import app.Run;
import app.TaskManager;
import app.gui.autotransport.AutotransportTabController;
import app.gui.ekspedycje.EkspedycjeTabController;
import app.gui.fleet_save_attack.FleetSaveAttackRootController;
import app.gui.imperium.ImperiumController;
import app.tasks.BotLogic;
import com.Log;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
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

    @FXML
    private Button buttonStopBot;

    @FXML
    private TabPane tabPane;

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

    @FXML
    private Label labelStartGameTime;

    @FXML
    private Label labelAlertModulTest;

    /*
        Ekspedycje
     */
    @FXML private EkspedycjeTabController ekspedycjeTabController;

    public EkspedycjeTabController getEkspedycjeTabController() {
        return ekspedycjeTabController;
    }

    /*
        Autotransport
     */
    @FXML private ImperiumController imperiumController;

    public ImperiumController getImperiumController() {
        return imperiumController;
    }

    /*
       Autotransport
    */
    @FXML private AutotransportTabController autotransportTabController;

    public AutotransportTabController getAutotransportTabController() {
        return autotransportTabController;
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

    private boolean initStartGameTime = true;
    public void setStartGameTime(String time) {
        labelStartGameTime.setText(time);
        initStartGameTime = false;
    }

    /*
        GETTERS
     */

    public VBox getvBoxActiveTask() {
        return vBoxActiveTask;
    }

    public Label getLabelAlertModulTest() {
        return labelAlertModulTest;
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    /*
        SETTERS
     */

    public void setBotLogic(BotLogic botLogic) {
        this.botLogic = botLogic;
    }

    public boolean isInitStartGameTime() {
        return initStartGameTime;
    }

    /*
        EXECUTING
     */
    public void setTestModule() {
        TaskManager.stopTasks(new int []{0,1,2,3,4});
//        botLogic.setRun(false);
//        buttonStopBot.setText("OFF");
        Log.printLog(MainController.class.getName(),"Uruchomiono moduł testowy. Zatrzymuję działanie bota.");
    }


}
