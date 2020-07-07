package app.gui.fleet_save_attack;

import app.data.fleet_save_attack.FleetSave;
import app.data.fleet_save_attack.FleetSaveAttackMissionConfiguration;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.io.Serializable;

public class MissionConfiguration implements Serializable
{
    private static final long serialVersionUID = 1L;
    private final FleetSave fleetSave;
    private boolean selected = false;
    private MissionConfigurationController missionConfigurationController;
    private FleetSaveAttackMissionConfiguration.MissionConfigurationFile  missionConfigurationFile;


    public MissionConfiguration(FleetSave fleetSave)
    {
        this.fleetSave = fleetSave;
    }

    /*
    GETTERS
     */
    HBox gethBox() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mission_configuration.fxml"));
        HBox hBox = new HBox();
        try
        {
            hBox = fxmlLoader.load();
            missionConfigurationController = fxmlLoader.getController();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        if (missionConfigurationController != null)
        {
            missionConfigurationController.setMissionConfiguration(this);
            missionConfigurationController.setText(fleetSave);
        }

        return hBox;
    }

    public boolean isSelected() {
        return selected;
    }

    public FleetSave getFleetSave() {
        return fleetSave;
    }

    FleetSaveAttackMissionConfiguration.MissionConfigurationFile getMissionConfigurationFile() {
        return missionConfigurationFile;
    }

    /*
    SETTERS
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setMissionConfigurationFile(FleetSaveAttackMissionConfiguration.MissionConfigurationFile missionConfigurationFile) {
        this.missionConfigurationFile = missionConfigurationFile;
    }

}
