package app.gui.ekspedycje;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EkspedycjaInfoController {

    @FXML
    private Label labelIdLotu;

    @FXML
    private Label labelZostalaOpozniona;

    @FXML
    private Label labelCzasZakonczenia;

    @FXML
    private Label labelCzasStartu;

    @FXML
    private Label labelCzasRozpoczecia;

    @FXML
    private Label labelCzasPowrotu;

    Label getLabelIdLotu() {
        return labelIdLotu;
    }

    Label getLabelZostalaOpozniona() {
        return labelZostalaOpozniona;
    }

    public Label getLabelCzasZakonczenia() {
        return labelCzasZakonczenia;
    }

    Label getLabelCzasStartu() {
        return labelCzasStartu;
    }

    Label getLabelCzasRozpoczecia() {
        return labelCzasRozpoczecia;
    }

    Label getLabelCzasPowrotu() {
        return labelCzasPowrotu;
    }
}
