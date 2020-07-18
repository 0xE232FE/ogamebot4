package app.gui.fleet_save_attack;

import app.data.fleet_save_attack.KliknijPodglad;
import app.log.LogFleetSaveAttack;
import app.planety.Planety;
import com.Log;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import ogame.planety.Planeta;

import java.util.ArrayList;
import java.util.List;

public class FleetSaveAttackRootController
{
    private ToggleGroup toggleGroup = new ToggleGroup();
    private ToggleGroup czasOdswiezaniaStronyToggleGroup = new ToggleGroup();
    private List<ToggleButtonPlaneta> toggleButtonPlanetaList = new ArrayList<>();
    private static ToggleButtonPlaneta selectedToogleButtonPlaneta;
    private boolean initPlanetButtons = true;

    @FXML
    PlanetConfigurationController planetConfigurationController;

    @FXML
    private HBox hBoxCzasOdswiezaniaStronyToggleGroup;
    @FXML
    public void initialize()
    {
       toggleGroup.selectedToggleProperty().addListener((observable, oldValue, selectedToggle) -> {
           if(selectedToggle != null) {
                ToggleButton tmp = (ToggleButton) selectedToggle;
                selectedToogleButtonPlaneta =  getToggleButtonPlaneta(tmp);
                if(selectedToogleButtonPlaneta != null)
                 planetConfigurationController.update(selectedToogleButtonPlaneta.getPlaneta());
           }
       });

       // Tworzenie Buttonów do ustawienia czasu odświeżania strony.
       int a = 60;
       while (a <= 600)
       {
           ToggleButton toggleButton = new ToggleButton(String.valueOf(a));
           a += 60;
           toggleButton.setPrefWidth(50.0);
           toggleButton.setFont(Font.font(10));
           toggleButton.setToggleGroup(czasOdswiezaniaStronyToggleGroup);
           hBoxCzasOdswiezaniaStronyToggleGroup.getChildren().add(toggleButton);
       }

       // Ustawiania czasu odswieżania strony z ToggleButton
       czasOdswiezaniaStronyToggleGroup.selectedToggleProperty().addListener((observable, oldValue, selectedToggle) -> {
            if(selectedToggle != null) {
                KliknijPodglad.setCoIleSekundOdswiezyc(Integer.valueOf(((ToggleButton) selectedToggle).getText()));
            }
       });
    }
    // Planety od 1 do 10
    @FXML
    private HBox hBoxToggleButtonPlaneta;

    // Planety od 11 do 20
    @FXML
    private HBox hBoxToggleButtonPlaneta2;

    public void update()
    {
        if(!Planety.init && initPlanetButtons)
        {
            Log.printLog(FleetSaveAttackRootController.class.getName(),"Rozpoczynam tworzenie obiektów klasy ToggleButtonPlaneta.");
            for(Planeta p : Planety.planety)
            {
                toggleButtonPlanetaList.add(new ToggleButtonPlaneta(p,toggleGroup));
            }
            Log.printLog(FleetSaveAttackRootController.class.getName(),"Zakończyłem tworzenie obiektów klasy ToggleButtonPlaneta.");

            Log.printLog(FleetSaveAttackRootController.class.getName(),"Rozpoczynam dodawanie ToggleButton.");

            for(int i = 0; i<toggleButtonPlanetaList.size(); i++)
            {
                if(i < 10)
                    hBoxToggleButtonPlaneta.getChildren().add(toggleButtonPlanetaList.get(i).getToggleButton());
                else
                    hBoxToggleButtonPlaneta2.getChildren().add(toggleButtonPlanetaList.get(i).getToggleButton());
            }

            Log.printLog(FleetSaveAttackRootController.class.getName(),"Zakończyłrm dodawanie ToggleButton.");
            initPlanetButtons = false;
        }
    }

    private ToggleButtonPlaneta getToggleButtonPlaneta(ToggleButton toggleButton)
    {
        for(ToggleButtonPlaneta toggleButtonPlaneta : toggleButtonPlanetaList)
        {
            if(toggleButtonPlaneta.getToggleButton().equals(toggleButton))
                return toggleButtonPlaneta;
        }
        return null;
    }

    @FXML
    private VBox vBoxLog;
    private int logSize = 0;

    public void updateLog()
    {
        Label label;
//        Log.printLog1("TMP log size : " + logSize + " Size log list: " + LogFleetSaveAttack.getLogList().size());
        if(logSize < LogFleetSaveAttack.getLogList().size())
        {
            vBoxLog.getChildren().clear();
            for(String s : LogFleetSaveAttack.get50LastLog())
            {
                label = new Label();
                label.setStyle("-fx-font-size: 10px");
                label.setText(s);
                vBoxLog.getChildren().add(label);
            }
            logSize = LogFleetSaveAttack.getLogList().size();
        }
    }

    static ToggleButtonPlaneta getSelectedToogleButtonPlaneta() {
        return selectedToogleButtonPlaneta;
    }
}
