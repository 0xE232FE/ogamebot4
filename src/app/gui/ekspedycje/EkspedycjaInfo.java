package app.gui.ekspedycje;

import app.data.ekspedycje.Ekspedycja;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class EkspedycjaInfo {
    private Ekspedycja ekspedycja;
    private EkspedycjaInfoController controller;
    private HBox hBox;


    EkspedycjaInfo(Ekspedycja ekspedycja)
    {
        this.ekspedycja = ekspedycja;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ekspedycja_info.fxml"));
        try
        {
            hBox = fxmlLoader.load();
            this.controller = fxmlLoader.getController();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        if (this.controller != null)
        {
            this.controller.getLabelCzasStartu().setText(ekspedycja.getStart().toString());
            this.controller.getLabelCzasRozpoczecia().setText(ekspedycja.getPrzylot().toString());
            this.controller.getLabelCzasPowrotu().setText(ekspedycja.getPowrot().toString());
            this.controller.getLabelIdLotu().setText(ekspedycja.getId());
            this.controller.getLabelZostalaOpozniona().setText(String.valueOf(ekspedycja.isOpozniona()));
        }
    }

    public EkspedycjaInfoController getController() {
        return controller;
    }

    HBox gethBox() {
        return hBox;
    }

    Ekspedycja getEkspedycja() {
        return ekspedycja;
    }
}
