package app.gui.autotransport;

import app.czas.Czas;
import app.czas.CzasGry;
import app.data.autotransport.Autotransport;
import app.data.autotransport.AutotransportData;
import app.gui.IntegerOnlyInputTextFieldListener;
import app.planety.Planety;
import com.DifferentMethods;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import ogame.planety.Planeta;

import java.util.ArrayList;
import java.util.List;

public class AutotransportTabController {


    @FXML
    private TextField textFieldDT;

    @FXML
    private TextField textFieldGodziny;

    @FXML
    private TextField textFieldPlaneta;

    @FXML
    private TextField textFieldMT;

    @FXML
    private TextField textFieldGalaktyka;

    @FXML
    private TextField textFieldUklad;

    @FXML
    private TextField textFieldMinuty;

    @FXML
    private VBox vBoxDaneOTransportach;

    @FXML
    private TextField textFieldTechnologiaNadprzestrzenna;

    @FXML
    private TextField textFieldSekundy;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private TextField textFieldIloscSurowcow;



    private static final String [] COMBOBOX_ITEMS = {"Planeta","Księżyc"};

    @FXML
    void initialize() {
        comboBox.setItems(FXCollections
                .observableArrayList(COMBOBOX_ITEMS));
        comboBox.setStyle("-fx-font-size: 10px");

//        Wczytanie ustawień
//        Combo box
        if (AutotransportData.configuration.isPlanet())
            comboBox.setValue(COMBOBOX_ITEMS[0]);
        else if (AutotransportData.configuration.isKsiezyc())
            comboBox.setValue(COMBOBOX_ITEMS[1]);
        else
            comboBox.setValue(COMBOBOX_ITEMS[0]);

        // Współrzędne
        textFieldGalaktyka.setText(String.valueOf(AutotransportData.configuration.getGalaktyka()));
        textFieldUklad.setText(String.valueOf(AutotransportData.configuration.getUklad()));
        textFieldPlaneta.setText(String.valueOf(AutotransportData.configuration.getPlaneta()));

        // Skład floty
        textFieldMT.setText(String.valueOf(AutotransportData.configuration.getFlota().getMt().getIlosc()));
        textFieldDT.setText(String.valueOf(AutotransportData.configuration.getFlota().getDt().getIlosc()));

        //Technologia nadprzestrzenna
        textFieldTechnologiaNadprzestrzenna.setText(String.valueOf(AutotransportData.configuration.getTechnologiaNadprzestrzenna()));

        //Interwał czasowy
        textFieldGodziny.setText(String.valueOf(AutotransportData.configuration.getGodzina()));
        textFieldMinuty.setText(String.valueOf(AutotransportData.configuration.getMinuta()));
        textFieldSekundy.setText(String.valueOf(AutotransportData.configuration.getSekunda()));

        //Minimalna ilość surowców
        textFieldIloscSurowcow.setText(String.valueOf(AutotransportData.configuration.getIloscSurowcow()));

        //Dodwanie listenerów
        textFieldGalaktyka.textProperty().addListener(new IntegerOnlyInputTextFieldListener(textFieldGalaktyka,1));
        textFieldUklad.textProperty().addListener(new IntegerOnlyInputTextFieldListener(textFieldUklad,3));
        textFieldPlaneta.textProperty().addListener(new IntegerOnlyInputTextFieldListener(textFieldPlaneta,2));
        textFieldGodziny.textProperty().addListener(new IntegerOnlyInputTextFieldListener(textFieldGodziny,2));
        textFieldMinuty.textProperty().addListener(new IntegerOnlyInputTextFieldListener(textFieldMinuty,2));
        textFieldSekundy.textProperty().addListener(new IntegerOnlyInputTextFieldListener(textFieldSekundy,2));
        textFieldMT.textProperty().addListener(new IntegerOnlyInputTextFieldListener(textFieldMT,5));
        textFieldDT.textProperty().addListener(new IntegerOnlyInputTextFieldListener(textFieldDT,5));
        textFieldTechnologiaNadprzestrzenna.textProperty().addListener(new IntegerOnlyInputTextFieldListener(textFieldTechnologiaNadprzestrzenna,2));
        textFieldIloscSurowcow.textProperty().addListener(new IntegerOnlyInputTextFieldListener(textFieldIloscSurowcow,10));

    }

    private final long SAVE_COMUNICATE_VISIBLE_TIME = 5*1000;
    private boolean saveComunicateVisible = false;
    private long lastSaveTime = -1;

    @FXML
    private Label labelSaveComunicate;

    @FXML
    void saveConfig() {
        AutotransportData.configuration.setGalaktyka(Integer.valueOf(textFieldGalaktyka.getText()));
        AutotransportData.configuration.setUklad(Integer.valueOf(textFieldUklad.getText()));
        AutotransportData.configuration.setPlaneta(Integer.valueOf(textFieldPlaneta.getText()));
        AutotransportData.configuration.setPlanet(comboBox.getValue().equals("Planeta"));
        AutotransportData.configuration.setKsiezyc(comboBox.getValue().equals("Księżyc"));
        AutotransportData.configuration.getFlota().setMt(Integer.valueOf(textFieldMT.getText()));
        AutotransportData.configuration.getFlota().setDt(Integer.valueOf(textFieldDT.getText()));
        AutotransportData.configuration.setGodzina(Integer.valueOf(textFieldGodziny.getText()));
        AutotransportData.configuration.setMinuta(Integer.valueOf(textFieldMinuty.getText()));
        AutotransportData.configuration.setSekunda(Integer.valueOf(textFieldSekundy.getText()));
        AutotransportData.configuration.setTechnologiaNadprzestrzenna(Integer.valueOf(textFieldTechnologiaNadprzestrzenna.getText()));
        AutotransportData.configuration.setIloscSurowcow(Integer.valueOf(textFieldIloscSurowcow.getText()));
        AutotransportData.save();
        labelSaveComunicate.setVisible(true);
        saveComunicateVisible = true;
        lastSaveTime = System.currentTimeMillis();
    }

    @FXML
    private LoadingContentController loadingContentController;
    private boolean wyczyscVBoxZLoadingContent = true; // Flaga do informacji o wczytywaniu planet.
    //Lista z danymi lotów do autotransportu.
    private static List<AutotransportInfo> autoTransportInfoList = new ArrayList<>();

    public void update()
    {
        // Komunikat o zapisaniu pliku.
        if(saveComunicateVisible)
        {
            if(System.currentTimeMillis() - lastSaveTime > SAVE_COMUNICATE_VISIBLE_TIME)
            {
                saveComunicateVisible = false;
                labelSaveComunicate.setVisible(false);
            }
        }

        // Dane o tranportach
        if(Planety.init)
            loadingContentController.labelAlertUpdate();
        else
        {
            // Usuwanie komunikatu o wczytywaniu planet.
            if(wyczyscVBoxZLoadingContent)
            {
                vBoxDaneOTransportach.getChildren().clear();
                wyczyscVBoxZLoadingContent = false;
            }

            // Tworzenie danych do autotransportu
            if(autoTransportInfoList.size() == 0)
            {
                for(Planeta p : Planety.planety)
                    autoTransportInfoList.add(new AutotransportInfo(new Autotransport(p)));

                for(AutotransportInfo a : autoTransportInfoList)
                    vBoxDaneOTransportach.getChildren().add(a.gethBox());
            }
            // Update danych o autotransporcie
            else
            {
                for(AutotransportInfo a : autoTransportInfoList)
                {
                    Autotransport autotransport = a.getAutotransport();
                    AutotransportInfoController controller = a.getController();
                    if(autotransport.isWyslijUser())
                    {
                        controller.getLabelCzasDostarczenia().setText(autotransport.getCzasDostarczenia() != null ? autotransport.getCzasDostarczenia().toString():"");
                        controller.getLabelCzasPowrotu().setText(autotransport.getCzasPowrotu() != null ? autotransport.getCzasPowrotu().toString():"");
                        controller.getLabelZabranoSurowcow().setText(autotransport.getZabranoSurowcow() != -1 ? DifferentMethods.addDots(String.valueOf(autotransport.getZabranoSurowcow())):"");
                        controller.getLabelPozostaloSurowcow().setText(autotransport.getPozostaloSurowcow() != -1 ? DifferentMethods.addDots(String.valueOf(autotransport.getPozostaloSurowcow())):"");

                        Czas tmp  = autotransport.getNastepneWykonanie().pozostaloCzasu(CzasGry.getCzas(),CzasGry.getData());
                        if(tmp.czasWSekundach() < 0)
                            autotransport.setWyslijPonownie(true);
                        else
                            controller.getLabelPozostaloDoNastepnego().setText(tmp.toString());
                    }
                }
            }
        }
    }

    /*
    GETTERS
     */

    public static List<AutotransportInfo> getAutoTransportInfoList() {
        return autoTransportInfoList;
    }
}
