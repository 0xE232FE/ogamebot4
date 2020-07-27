package app.gui.ekspedycje;

import app.data.ekspedycje.Ekspedycja;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class EkspedycjeTabController {
    @FXML
    private TextField textFieldGS;

    @FXML
    private TextField textFieldREC;

    @FXML
    private TextField textFieldDT;

    @FXML
    private TextField textFieldPlaneta;

    @FXML
    private TextField textFieldBMB;

    @FXML
    private TextField textFieldOW;

    @FXML
    private TextField textFieldRZ;

    @FXML
    private TextField textFieldKR;

    @FXML
    private TextField textFieldMT;

    @FXML
    private TextField textFieldCM;

    @FXML
    private TextField textFieldNI;

    @FXML
    private TextField textFieldCzas;

    @FXML
    private TextField textFieldPN;

    @FXML
    private TextField textFieldGalaktyka;

    @FXML
    private TextField textFieldSS;

    @FXML
    private TextField textFieldUklad;

    @FXML
    private TextField textFieldLM;

    @FXML
    private VBox vBoxAktywneEkspedycje;

    @FXML
    private TextField textFieldSK;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private TextField textFieldPI;

    private static final String [] COMBOBOX_ITEMS = {"Planeta","Księżyc"};

    @FXML
    void initialize()
    {
        comboBox.setItems(FXCollections
                .observableArrayList(COMBOBOX_ITEMS));
        comboBox.setValue(COMBOBOX_ITEMS[0]);
        comboBox.setStyle("-fx-font-size: 10px");

        //Wczytanie ustawień
        //Combo box
        if(Ekspedycja.configuration.isPlanet())
            comboBox.setValue(COMBOBOX_ITEMS[0]);
        else if(Ekspedycja.configuration.isKsiezyc())
            comboBox.setValue(COMBOBOX_ITEMS[1]);
        else
            comboBox.setValue(COMBOBOX_ITEMS[0]);
        // Współrzędne
        textFieldGalaktyka.setText(String.valueOf(Ekspedycja.configuration.getGalaktyka()));
        textFieldUklad.setText(String.valueOf(Ekspedycja.configuration.getUklad()));
        textFieldPlaneta.setText(String.valueOf(Ekspedycja.configuration.getPlaneta()));
        textFieldCzas.setText(String.valueOf(Ekspedycja.configuration.getCzas()));

        // Skład floty
        textFieldLM.setText(String.valueOf(Ekspedycja.configuration.getFlota().getLm().getIlosc()));
        textFieldCM.setText(String.valueOf(Ekspedycja.configuration.getFlota().getCm().getIlosc()));
        textFieldKR.setText(String.valueOf(Ekspedycja.configuration.getFlota().getKr().getIlosc()));
        textFieldOW.setText(String.valueOf(Ekspedycja.configuration.getFlota().getOw().getIlosc()));
        textFieldPN.setText(String.valueOf(Ekspedycja.configuration.getFlota().getPanc().getIlosc()));
        textFieldBMB.setText(String.valueOf(Ekspedycja.configuration.getFlota().getBomb().getIlosc()));
        textFieldNI.setText(String.valueOf(Ekspedycja.configuration.getFlota().getNiszcz().getIlosc()));
        textFieldGS.setText(String.valueOf(Ekspedycja.configuration.getFlota().getGs().getIlosc()));
        textFieldRZ.setText(String.valueOf(Ekspedycja.configuration.getFlota().getRoz().getIlosc()));
        textFieldPI.setText(String.valueOf(Ekspedycja.configuration.getFlota().getPion().getIlosc()));
        textFieldMT.setText(String.valueOf(Ekspedycja.configuration.getFlota().getMt().getIlosc()));
        textFieldDT.setText(String.valueOf(Ekspedycja.configuration.getFlota().getDt().getIlosc()));
        textFieldSK.setText(String.valueOf(Ekspedycja.configuration.getFlota().getSk().getIlosc()));
        textFieldREC.setText(String.valueOf(Ekspedycja.configuration.getFlota().getRec().getIlosc()));
        textFieldSS.setText(String.valueOf(Ekspedycja.configuration.getFlota().getSs().getIlosc()));
//        textFieldLM.setText(String.valueOf(Ekspedycja.configuration.getLm()));
//        textFieldCM.setText(String.valueOf(Ekspedycja.configuration.getCm()));
//        textFieldKR.setText(String.valueOf(Ekspedycja.configuration.getKr()));
//        textFieldOW.setText(String.valueOf(Ekspedycja.configuration.getOw()));
//        textFieldPN.setText(String.valueOf(Ekspedycja.configuration.getPanc()));
//        textFieldBMB.setText(String.valueOf(Ekspedycja.configuration.getBomb()));
//        textFieldNI.setText(String.valueOf(Ekspedycja.configuration.getNiszcz()));
//        textFieldGS.setText(String.valueOf(Ekspedycja.configuration.getGs()));
//        textFieldRZ.setText(String.valueOf(Ekspedycja.configuration.getRoz()));
//        textFieldPI.setText(String.valueOf(Ekspedycja.configuration.getPion()));
//        textFieldMT.setText(String.valueOf(Ekspedycja.configuration.getMt()));
//        textFieldDT.setText(String.valueOf(Ekspedycja.configuration.getDt()));
//        textFieldSK.setText(String.valueOf(Ekspedycja.configuration.getSk()));
//        textFieldREC.setText(String.valueOf(Ekspedycja.configuration.getRec()));
//        textFieldSS.setText(String.valueOf(Ekspedycja.configuration.getSs()));
    }

    @FXML
    void saveConfig() {
        Ekspedycja.configuration.setGalaktyka(Integer.valueOf(textFieldGalaktyka.getText()));
        Ekspedycja.configuration.setUklad(Integer.valueOf(textFieldUklad.getText()));
        Ekspedycja.configuration.setPlaneta(Integer.valueOf(textFieldPlaneta.getText()));
        Ekspedycja.configuration.setCzas(Integer.valueOf(textFieldCzas.getText()));
        Ekspedycja.configuration.setPlanet(comboBox.getValue().equals("Planeta"));
        Ekspedycja.configuration.setKsiezyc(comboBox.getValue().equals("Księżyc"));
        Ekspedycja.configuration.getFlota().setLm(Integer.valueOf(textFieldLM.getText()));
        Ekspedycja.configuration.getFlota().setCm(Integer.valueOf(textFieldCM.getText()));
        Ekspedycja.configuration.getFlota().setKr(Integer.valueOf(textFieldKR.getText()));
        Ekspedycja.configuration.getFlota().setOw(Integer.valueOf(textFieldOW.getText()));
        Ekspedycja.configuration.getFlota().setPanc(Integer.valueOf(textFieldPN.getText()));
        Ekspedycja.configuration.getFlota().setBomb(Integer.valueOf(textFieldBMB.getText()));
        Ekspedycja.configuration.getFlota().setNiszcz(Integer.valueOf(textFieldNI.getText()));
        Ekspedycja.configuration.getFlota().setGs(Integer.valueOf(textFieldGS.getText()));
        Ekspedycja.configuration.getFlota().setRoz(Integer.valueOf(textFieldRZ.getText()));
        Ekspedycja.configuration.getFlota().setPion(Integer.valueOf(textFieldPI.getText()));
        Ekspedycja.configuration.getFlota().setMt(Integer.valueOf(textFieldMT.getText()));
        Ekspedycja.configuration.getFlota().setDt(Integer.valueOf(textFieldDT.getText()));
        Ekspedycja.configuration.getFlota().setSk(Integer.valueOf(textFieldSK.getText()));
        Ekspedycja.configuration.getFlota().setRec(Integer.valueOf(textFieldREC.getText()));
        Ekspedycja.configuration.getFlota().setSs(Integer.valueOf(textFieldSS.getText()));
//        Ekspedycja.configuration.setLm(Integer.valueOf(textFieldLM.getText()));
//        Ekspedycja.configuration.setCm(Integer.valueOf(textFieldCM.getText()));
//        Ekspedycja.configuration.setKr(Integer.valueOf(textFieldKR.getText()));
//        Ekspedycja.configuration.setOw(Integer.valueOf(textFieldOW.getText()));
//        Ekspedycja.configuration.setPanc(Integer.valueOf(textFieldPN.getText()));
//        Ekspedycja.configuration.setBomb(Integer.valueOf(textFieldBMB.getText()));
//        Ekspedycja.configuration.setNiszcz(Integer.valueOf(textFieldNI.getText()));
//        Ekspedycja.configuration.setGs(Integer.valueOf(textFieldGS.getText()));
//        Ekspedycja.configuration.setRoz(Integer.valueOf(textFieldRZ.getText()));
//        Ekspedycja.configuration.setPion(Integer.valueOf(textFieldPI.getText()));
//        Ekspedycja.configuration.setMt(Integer.valueOf(textFieldMT.getText()));
//        Ekspedycja.configuration.setDt(Integer.valueOf(textFieldDT.getText()));
//        Ekspedycja.configuration.setSk(Integer.valueOf(textFieldSK.getText()));
//        Ekspedycja.configuration.setRec(Integer.valueOf(textFieldREC.getText()));
//        Ekspedycja.configuration.setSs(Integer.valueOf(textFieldSS.getText()));

        Ekspedycja.save();
    }

}
