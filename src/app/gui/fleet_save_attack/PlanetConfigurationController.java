package app.gui.fleet_save_attack;

import app.data.fleet_save_attack.FleetSave;
import app.data.fleet_save_attack.FleetSaveAttackMissionConfiguration;
import com.Log;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import ogame.planety.Planeta;

import java.util.ArrayList;
import java.util.List;

public class PlanetConfigurationController
{
    @FXML
    private TextField editTextWspolrzedneM;

    @FXML
    private CheckBox chceckBoxPlanetaAuto;

    @FXML
    private TextField editTextWspolrzedneP;

    @FXML
    private Button buttonDodajM;

    @FXML
    private Label labelWspolrzedne;

    @FXML
    private Label labelNazwa;

    @FXML
    private TextField editTextObiektP;

    @FXML
    private VBox vBoxPlaneta;

    @FXML
    private TextField editTextMisjaP;

    @FXML
    private VBox vBoxKsiezyc;

    @FXML
    private TextField editTextObiektM;

    @FXML
    private Button buttonUsunP;

    @FXML
    private Button buttonUsunM;

    @FXML
    private VBox vBoxKsiezycRoot;

    @FXML
    private Button buttonDodajP;

    @FXML
    private CheckBox checkBoxKsiezycAuto;

    @FXML
    private TextField editTextMisjaM;

    private boolean flagEmptyVboxPlaneta = false;

    @FXML
    public void initialize()
    {
        // akcja na checkBox auto fleet save planeta
        chceckBoxPlanetaAuto.selectedProperty().addListener((observable, oldValue, newValue) -> {
            vBoxPlaneta.setDisable(newValue);
            buttonDodajP.setDisable(newValue);
            buttonUsunP.setDisable(newValue);
            editTextMisjaP.setDisable(newValue);
            editTextObiektP.setDisable(newValue);
            editTextWspolrzedneP.setDisable(newValue);

            if(FleetSaveAttackRootController.getSelectedToogleButtonPlaneta() != null)
            {
                Planeta p = FleetSaveAttackRootController.getSelectedToogleButtonPlaneta().getPlaneta();
                p.getAttackFleetSaveConfiguration().setAutoFleetSavePlaneta(newValue);
                // zapis zmian
                p.getAttackFleetSaveConfiguration().save(p.getPlanetFileName());
            }
        });

        // akcja na checkBox auto fleet save ksiezyc
        checkBoxKsiezycAuto.selectedProperty().addListener((observable, oldValue, newValue) -> {
            vBoxKsiezyc.setDisable(newValue);
            buttonDodajM.setDisable(newValue);
            buttonUsunM.setDisable(newValue);
            editTextMisjaM.setDisable(newValue);
            editTextObiektM.setDisable(newValue);
            editTextWspolrzedneM.setDisable(newValue);

            if(FleetSaveAttackRootController.getSelectedToogleButtonPlaneta() != null)
            {
                Planeta p = FleetSaveAttackRootController.getSelectedToogleButtonPlaneta().getPlaneta();
                p.getAttackFleetSaveConfiguration().setAutoFleetSaveMoon(newValue);
                // zapis zmian
                p.getAttackFleetSaveConfiguration().save(p.getPlanetFileName());
            }
        });

    }


    @FXML
    void dodajP() {
        boolean a = !editTextMisjaP.getText().equals("");
        boolean b = !editTextObiektP.getText().equals("");
        boolean c = !editTextWspolrzedneP.getText().equals("");

        if(a && b && c)
        {
            FleetSave fleetSave = new FleetSave(editTextMisjaP.getText(),editTextWspolrzedneP.getText(),
                    editTextObiektP.getText().equals("Planeta") ? 0:1);

            MissionConfiguration missionConfiguration = new MissionConfiguration(fleetSave);
            Planeta p = FleetSaveAttackRootController.getSelectedToogleButtonPlaneta().getPlaneta();
            if(p.getAttackFleetSaveConfiguration().addPlanetFleetSaveObject(missionConfiguration, p))
            {
                if(flagEmptyVboxPlaneta)
                {
                    // ustawia domyślny style VBox
                    vBoxPlaneta.setStyle("");
                    vBoxPlaneta.getChildren().clear();
                    flagEmptyVboxPlaneta = false;
                }
                vBoxPlaneta.getChildren().add(missionConfiguration.gethBox());
            }

        }
        else
            Log.printLog(PlanetConfigurationController.class.getName(),"Uzupełnij pola danych.");

    }

    @FXML
    void usunP() {
        List<FleetSaveAttackMissionConfiguration.MissionConfigurationFile> tmp = new ArrayList<>();
        Planeta p = FleetSaveAttackRootController.getSelectedToogleButtonPlaneta().getPlaneta();

        FleetSaveAttackMissionConfiguration fsa = p.getAttackFleetSaveConfiguration();

        // Wyszukiwanie zaznaczonych obiektów
        for(FleetSaveAttackMissionConfiguration.MissionConfigurationFile missionConfiguration : fsa.getPlanetFleetSaveObject())
        {
            if(missionConfiguration.isSelected())
                tmp.add(missionConfiguration);
        }

        // Usuwanie zaznaczonych obiektów
        if(tmp.size() > 0)
        {
            for(FleetSaveAttackMissionConfiguration.MissionConfigurationFile missionConfiguration : tmp)
                fsa.removePlanetFleetSaveObject(missionConfiguration,p);
        }
        else
            Log.printLog(PlanetConfigurationController.class.getName(),"Nie wskazano żadnych obiektów do usunięcia.");

        // Update danych misji fleet save z planety
        vBoxPlaneta.getChildren().clear();
        for(FleetSaveAttackMissionConfiguration.MissionConfigurationFile missionConfiguration : fsa.getPlanetFleetSaveObject())
        {
            vBoxPlaneta.getChildren().add(missionConfiguration.configuration().gethBox());
        }
        // Brak misji fleet save oraz księżyca. Ustaw alert!
        if(vBoxPlaneta.getChildren().size() == 0 & !p.isMoon())
        {
            flagEmptyVboxPlaneta = true;
            Label label = new Label("Set Mission Object");
            label.setStyle("-fx-font-size: 18px; -fx-text-fill: white");
            vBoxPlaneta.setStyle("-fx-background-color: tomato; -fx-alignment: center");
            vBoxPlaneta.getChildren().add(label);
        }
    }

    @FXML
    void dodajM() {
        boolean a = !editTextMisjaM.getText().equals("");
        boolean b = !editTextObiektM.getText().equals("");
        boolean c = !editTextWspolrzedneM.getText().equals("");

        if(a && b && c)
        {
            FleetSave fleetSave = new FleetSave(editTextMisjaM.getText(),editTextWspolrzedneM.getText(),
                    editTextObiektM.getText().equals("Planeta") ? 0:1);
            MissionConfiguration missionConfiguration = new MissionConfiguration(fleetSave);

            Planeta p = FleetSaveAttackRootController.getSelectedToogleButtonPlaneta().getPlaneta();
            if(p.getAttackFleetSaveConfiguration().addMoonFleetSaveObject(missionConfiguration, p))
                vBoxKsiezyc.getChildren().add(missionConfiguration.gethBox());
        }
        else
            Log.printLog(PlanetConfigurationController.class.getName(),"Uzupełnij pola danych.");
    }

    @FXML
    void usunM() {
        List<FleetSaveAttackMissionConfiguration.MissionConfigurationFile> tmp = new ArrayList<>();
        Planeta p = FleetSaveAttackRootController.getSelectedToogleButtonPlaneta().getPlaneta();

        FleetSaveAttackMissionConfiguration fsa = p.getAttackFleetSaveConfiguration();

        // Wyszukiwanie zaznaczonych obiektów
        for(FleetSaveAttackMissionConfiguration.MissionConfigurationFile missionConfiguration : fsa.getMoonFleetSaveObject())
        {
            if(missionConfiguration.isSelected())
                tmp.add(missionConfiguration);
        }

        // Usuwanie zaznaczonych obiektów
        if(tmp.size() > 0)
        {
            for(FleetSaveAttackMissionConfiguration.MissionConfigurationFile missionConfiguration : tmp)
                fsa.removeMoonFleetSaveObject(missionConfiguration,p);
        }
        else
            Log.printLog(PlanetConfigurationController.class.getName(),"Nie wskazano żadnych obiektów do usunięcia.");

        // Update danych misji fleet save z planety
        vBoxKsiezyc.getChildren().clear();
        for(FleetSaveAttackMissionConfiguration.MissionConfigurationFile missionConfiguration : fsa.getMoonFleetSaveObject())
        {
            vBoxKsiezyc.getChildren().add(missionConfiguration.configuration().gethBox());
        }
    }


    void update(Planeta p)
    {
        vBoxPlaneta.setStyle("");
        labelNazwa.setText(p.getNazwa());
        labelWspolrzedne.setText(p.getWspolrzedne());

        // Ustawienie checkBox auto fleet save planeta
        if(p.getAttackFleetSaveConfiguration().isAutoFleetSavePlaneta())
            chceckBoxPlanetaAuto.setSelected(true);
        else
            chceckBoxPlanetaAuto.setSelected(false);

        // Ustawienie checkBox auto fleet save moon
        if(p.getAttackFleetSaveConfiguration().isAutoFleetSaveMoon())
            checkBoxKsiezycAuto.setSelected(true);
        else
            checkBoxKsiezycAuto.setSelected(false);

        // Blokowanie okna dla ustawień księżyca, jeżeli planeta nie ma księżyca
        if(p.isMoon())
            vBoxKsiezycRoot.setDisable(false);
        else
            vBoxKsiezycRoot.setDisable(true);

//         Dodawanie danych misji fleet save z planety
        vBoxPlaneta.getChildren().clear();
        for(FleetSaveAttackMissionConfiguration.MissionConfigurationFile missionConfiguration : p.getAttackFleetSaveConfiguration().getPlanetFleetSaveObject())
        {
            vBoxPlaneta.getChildren().add(missionConfiguration.configuration().gethBox());
        }

        // Dodawanie danych misji fleet save z księżyca
        vBoxKsiezyc.getChildren().clear();
        for(FleetSaveAttackMissionConfiguration.MissionConfigurationFile missionConfiguration : p.getAttackFleetSaveConfiguration().getMoonFleetSaveObject())
        {
            vBoxKsiezyc.getChildren().add(missionConfiguration.configuration().gethBox());
        }

        // Brak misji fleet save oraz księżyca. Ustaw alert!
        if(vBoxPlaneta.getChildren().size() == 0 & !p.isMoon())
        {
            flagEmptyVboxPlaneta = true;
            Label label = new Label("Set Mission Object");
            label.setStyle("-fx-font-size: 18px; -fx-text-fill: white");
            vBoxPlaneta.setStyle("-fx-background-color: tomato; -fx-alignment: center");
            vBoxPlaneta.getChildren().add(label);
        }
    }
}
