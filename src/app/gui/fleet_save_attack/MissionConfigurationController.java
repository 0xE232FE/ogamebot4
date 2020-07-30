package app.gui.fleet_save_attack;

import app.data.fleet_save_attack.FleetSave;
import com.Log;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class MissionConfigurationController
{
    private MissionConfiguration missionConfiguration;

    @FXML
    private Label labelObiekt;

    @FXML
    private Label labelMisja;

    @FXML
    private Label labelWspolrzedne;


    /**
     * Ustawia tekst paska rodzajem misji, wspołrzednymi i obiektem.
     * @param fleetSave Dane FleetSave'a
     */
    public void setText(FleetSave fleetSave)
    {
//        Log.printLog1(fleetSave.toString(),MissionConfigurationController.class,"setText(FleetSave)"
//                ,33);
        labelMisja.setText(fleetSave.getMisja());
        labelWspolrzedne.setText(fleetSave.getWspolrzedne());
        labelObiekt.setText(fleetSave.getObiekt() == 0 ? "Planeta":"Księżyc");
    }

    void setMissionConfiguration(MissionConfiguration missionConfiguration) {
        this.missionConfiguration = missionConfiguration;
    }

    /**
     * Podświetla lub wyłącza podświetlenie paska.
     */
    @FXML
    public void onClick(MouseEvent mouseEvent) {
        HBox hBox = (HBox) mouseEvent.getSource();
        if(missionConfiguration.getMissionConfigurationFile().isSelected())
        {
            missionConfiguration.getMissionConfigurationFile().setSelected(false);
            hBox.setStyle("");
        }
        else
        {
            missionConfiguration.getMissionConfigurationFile().setSelected(true);
            hBox.setStyle("-fx-background-color: tomato");
        }
    }
}
