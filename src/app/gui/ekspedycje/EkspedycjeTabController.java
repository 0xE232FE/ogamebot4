package app.gui.ekspedycje;

import app.czas.Czas;
import app.czas.CzasGry;
import app.data.ekspedycje.Ekspedycja;
import app.data.ekspedycje.Ekspedycje;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class EkspedycjeTabController {

    @FXML
    private TextField textFieldDT;

    @FXML
    private TextField textFieldPlaneta;

    @FXML
    private TextField textFieldBMB;

    @FXML
    private TextField textFieldRZ;

    @FXML
    private TextField textFieldNI;

    @FXML
    private TextField textFieldCzas;

    @FXML
    private TextField textFieldPN;

    @FXML
    private TextField textFieldGalaktyka;

    @FXML
    private TextField textFieldUklad;

    @FXML
    private TextField textFieldLM;

    @FXML
    private VBox vBoxAktywneEkspedycje;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Label labelMaxIloscEkspedycji;

    @FXML
    private TextField textFieldPI;

    @FXML
    private Label labelCzasPowrotu;

    @FXML
    private TextField textFieldGS;

    @FXML
    private TextField textFieldREC;

    @FXML
    private Label labelIdLotu;

    @FXML
    private TextField textFieldOW;

    @FXML
    private TextField textFieldKR;

    @FXML
    private TextField textFieldMT;

    @FXML
    private TextField textFieldCM;

    @FXML
    private TextField textFieldSS;

    @FXML
    private Label labelIloscEkspedycji;

    @FXML
    private Label labelPozostalo;

    @FXML
    private TextField textFieldSK;

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
        if(Ekspedycje.configuration.isPlanet())
            comboBox.setValue(COMBOBOX_ITEMS[0]);
        else if(Ekspedycje.configuration.isKsiezyc())
            comboBox.setValue(COMBOBOX_ITEMS[1]);
        else
            comboBox.setValue(COMBOBOX_ITEMS[0]);
        // Współrzędne
        textFieldGalaktyka.setText(String.valueOf(Ekspedycje.configuration.getGalaktyka()));
        textFieldUklad.setText(String.valueOf(Ekspedycje.configuration.getUklad()));
        textFieldPlaneta.setText(String.valueOf(Ekspedycje.configuration.getPlaneta()));
//        textFieldCzas.setText(String.valueOf(Ekspedycje.configuration.getCzas()));

        // Skład floty
        textFieldLM.setText(String.valueOf(Ekspedycje.configuration.getFlota().getLm().getIlosc()));
        textFieldCM.setText(String.valueOf(Ekspedycje.configuration.getFlota().getCm().getIlosc()));
        textFieldKR.setText(String.valueOf(Ekspedycje.configuration.getFlota().getKr().getIlosc()));
        textFieldOW.setText(String.valueOf(Ekspedycje.configuration.getFlota().getOw().getIlosc()));
        textFieldPN.setText(String.valueOf(Ekspedycje.configuration.getFlota().getPanc().getIlosc()));
        textFieldBMB.setText(String.valueOf(Ekspedycje.configuration.getFlota().getBomb().getIlosc()));
        textFieldNI.setText(String.valueOf(Ekspedycje.configuration.getFlota().getNiszcz().getIlosc()));
        textFieldGS.setText(String.valueOf(Ekspedycje.configuration.getFlota().getGs().getIlosc()));
        textFieldRZ.setText(String.valueOf(Ekspedycje.configuration.getFlota().getRoz().getIlosc()));
        textFieldPI.setText(String.valueOf(Ekspedycje.configuration.getFlota().getPion().getIlosc()));
        textFieldMT.setText(String.valueOf(Ekspedycje.configuration.getFlota().getMt().getIlosc()));
        textFieldDT.setText(String.valueOf(Ekspedycje.configuration.getFlota().getDt().getIlosc()));
        textFieldSK.setText(String.valueOf(Ekspedycje.configuration.getFlota().getSk().getIlosc()));
        textFieldREC.setText(String.valueOf(Ekspedycje.configuration.getFlota().getRec().getIlosc()));
        textFieldSS.setText(String.valueOf(Ekspedycje.configuration.getFlota().getSs().getIlosc()));
    }

    @FXML
    void saveConfig() {
        Ekspedycje.configuration.setGalaktyka(Integer.valueOf(textFieldGalaktyka.getText()));
        Ekspedycje.configuration.setUklad(Integer.valueOf(textFieldUklad.getText()));
        Ekspedycje.configuration.setPlaneta(Integer.valueOf(textFieldPlaneta.getText()));
        Ekspedycje.configuration.setCzas(Integer.valueOf(textFieldCzas.getText()));
        Ekspedycje.configuration.setPlanet(comboBox.getValue().equals("Planeta"));
        Ekspedycje.configuration.setKsiezyc(comboBox.getValue().equals("Księżyc"));
        Ekspedycje.configuration.getFlota().setLm(Integer.valueOf(textFieldLM.getText()));
        Ekspedycje.configuration.getFlota().setCm(Integer.valueOf(textFieldCM.getText()));
        Ekspedycje.configuration.getFlota().setKr(Integer.valueOf(textFieldKR.getText()));
        Ekspedycje.configuration.getFlota().setOw(Integer.valueOf(textFieldOW.getText()));
        Ekspedycje.configuration.getFlota().setPanc(Integer.valueOf(textFieldPN.getText()));
        Ekspedycje.configuration.getFlota().setBomb(Integer.valueOf(textFieldBMB.getText()));
        Ekspedycje.configuration.getFlota().setNiszcz(Integer.valueOf(textFieldNI.getText()));
        Ekspedycje.configuration.getFlota().setGs(Integer.valueOf(textFieldGS.getText()));
        Ekspedycje.configuration.getFlota().setRoz(Integer.valueOf(textFieldRZ.getText()));
        Ekspedycje.configuration.getFlota().setPion(Integer.valueOf(textFieldPI.getText()));
        Ekspedycje.configuration.getFlota().setMt(Integer.valueOf(textFieldMT.getText()));
        Ekspedycje.configuration.getFlota().setDt(Integer.valueOf(textFieldDT.getText()));
        Ekspedycje.configuration.getFlota().setSk(Integer.valueOf(textFieldSK.getText()));
        Ekspedycje.configuration.getFlota().setRec(Integer.valueOf(textFieldREC.getText()));
        Ekspedycje.configuration.getFlota().setSs(Integer.valueOf(textFieldSS.getText()));

        Ekspedycje.save();
    }

    //Wyświetlanie aktualnych ekspedycji
    private List<EkspedycjaInfo> aktualneEkspedycje = new ArrayList<>();
    public void update()
    {
        //Ustawienie dany o ilości ekspedycji
        labelMaxIloscEkspedycji.setText(String.valueOf(Ekspedycje.maxIloscEkspedycji));
        labelIloscEkspedycji.setText(String.valueOf(Ekspedycje.aktualnaIloscEkspedycji));
        //Sprawdzam czy ilość wyświetlanych ekspedycji jest taka sama jak w liście ekspedycji
        if(vBoxAktywneEkspedycje.getChildren().size() != Ekspedycje.listaEkspedycji.size())
        {
            //Ilość Ekspedycji wyświetlanych jest większa od ilości w liście. Należy usunąć nieaktualne.
            if(vBoxAktywneEkspedycje.getChildren().size() > Ekspedycje.listaEkspedycji.size())
            {
                //Lista tymczasowa z ekspedycjami do usunięcia
                List<EkspedycjaInfo> tmp = new ArrayList<>();
                //Wyświetlane ekspedycje
                for(EkspedycjaInfo e : aktualneEkspedycje)
                {
                    boolean usunEkspedycjeZWidoku = true;
                    //Ekspedycje pobrane do listy
                    for(Ekspedycja ekspedycja : Ekspedycje.listaEkspedycji)
                    {
                        //Ekspedycja jest wyświetlana
                        if(ekspedycja.getId().equals(e.getEkspedycja().getId()))
                        {
                            usunEkspedycjeZWidoku = false;
                            break;
                        }
                    }

                    if(usunEkspedycjeZWidoku)
                        tmp.add(e);
                }
                //Usuwanie ekspedycji z widoku
                for(EkspedycjaInfo e : tmp)
                {
                    aktualneEkspedycje.remove(e);
                }
                //updateGUI vBoxAktywneEkspedycje
                vBoxAktywneEkspedycje.getChildren().clear();
                for(EkspedycjaInfo e : aktualneEkspedycje)
                {
                    vBoxAktywneEkspedycje.getChildren().add(e.gethBox());
                }
            }
            //Ilość Ekspedycji wyświetlanych jest mniejsza od ilości w liście. Należy dodać nowe.
            else
            {
                    //Ekspedycje pobrane do listy
                    for(Ekspedycja ekspedycja : Ekspedycje.listaEkspedycji)
                    {
                        boolean tmp = true;
                        //Wyświetlane ekspedycje
                        for(EkspedycjaInfo e : aktualneEkspedycje)
                        {
                            //Ekspedycja jest wyświetlana
                            if(ekspedycja.getId().equals(e.getEkspedycja().getId()))
                            {
                                tmp = false;
                                break;
                            }
                        }
                        //Ekspedycja nie jest wyświetlana, dodaj do GUI
                        if(tmp)
                        {
                            aktualneEkspedycje.add(new EkspedycjaInfo(ekspedycja));
                        }
                    }
            }
            //updateGUI vBoxAktywneEkspedycje
            vBoxAktywneEkspedycje.getChildren().clear();
            for(EkspedycjaInfo e : aktualneEkspedycje)
            {
                vBoxAktywneEkspedycje.getChildren().add(e.gethBox());
            }

        }

        //Update najbliższa misja
        if(Ekspedycje.najblizszaEkspedycja != null)
        {
            if(!labelIdLotu.getText().equals(Ekspedycje.najblizszaEkspedycja.getId()))
            {
                labelIdLotu.setText(Ekspedycje.najblizszaEkspedycja.getId());
                labelCzasPowrotu.setText(Ekspedycje.najblizszaEkspedycja.getPowrot().toString());
            }
            //Update pozostało czasu
            int czas;
            if(Ekspedycje.najblizszaEkspedycja.getPowrot().getData().toString().equals(CzasGry.getData().toString()))
            {
                czas = Ekspedycje.najblizszaEkspedycja.getPowrot().getCzas().czasWSekundach() - CzasGry.getCzas().czasWSekundach();
            }
            else
            {
                czas = (Czas.MAX_SECONDS_DAY - CzasGry.getCzas().czasWSekundach()) + Ekspedycje.najblizszaEkspedycja.getPowrot().getCzas().czasWSekundach();
            }

            labelPozostalo.setText(new Czas(czas).toString());
        }

        //Update statusu opóźnienia floty
        if(Ekspedycje.updateOpoznionaVariable)
        {
            for(EkspedycjaInfo e : aktualneEkspedycje)
            {
                if(e.getEkspedycja().isOpozniona())
                    e.getController().getLabelZostalaOpozniona().setText(String.valueOf(true));
            }
            Ekspedycje.updateOpoznionaVariable = false;
        }
    }

}
