package app.gui.autotransport;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class AutotransportInfoController
{
    @FXML
    private Label labelWspolrzedneStartu;

    @FXML
    private Label labelCzasDostarczenia;

    @FXML
    private Label labelPozostaloSurowcow;

    @FXML
    private Label labelPozostaloDoNastepnego;

    @FXML
    private Label labelZabranoSurowcow;

    @FXML
    private Label labelCzasPowrotu;

    @FXML
    private CheckBox checkBoxWyslij;

//    private AutotransportInfo autotransportInfo;

    /*
    GETTERS
     */


    Label getLabelWspolrzedneStartu() {
        return labelWspolrzedneStartu;
    }

    Label getLabelCzasDostarczenia() {
        return labelCzasDostarczenia;
    }

    Label getLabelPozostaloSurowcow() {
        return labelPozostaloSurowcow;
    }

    Label getLabelPozostaloDoNastepnego() {
        return labelPozostaloDoNastepnego;
    }

    Label getLabelZabranoSurowcow() {
        return labelZabranoSurowcow;
    }

    Label getLabelCzasPowrotu() {
        return labelCzasPowrotu;
    }

    /*
    SETTERS
     */
    void setAutotransportInfo(AutotransportInfo autotransportInfo) {
//        this.autotransportInfo = autotransportInfo;
        checkBoxWyslij.selectedProperty().addListener((observable, oldValue, newValue) ->
                autotransportInfo.getAutotransport().setWyslijUser(newValue));
    }
}
