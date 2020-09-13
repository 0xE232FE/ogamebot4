package app.gui.autotransport;

import app.data.autotransport.Autotransport;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class AutotransportInfo
{
    private final Autotransport autotransport;
    private AutotransportInfoController controller;
    private HBox hBox;

    AutotransportInfo(Autotransport autotransport) {
        this.autotransport = autotransport;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("autotransport_info.fxml"));
        try
        {
            hBox = fxmlLoader.load();
            controller = fxmlLoader.getController();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        if(controller != null)
        {
            controller.getLabelWspolrzedneStartu().setText(autotransport.getPlaneta().getWspolrzedne());
            controller.setAutotransportInfo(this);
        }
    }

    public Autotransport getAutotransport() {
        return autotransport;
    }

    public AutotransportInfoController getController() {
        return controller;
    }

    HBox gethBox() {
        return hBox;
    }
}
