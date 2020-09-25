package app.gui.imperium;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import ogame.planety.Planeta;

import java.io.IOException;

public class Imperium {

    private final Planeta planeta;
    private ImperiumPlanetaController controller;
    private VBox vBox;

    public Imperium(Planeta planeta) {
        this.planeta = planeta;


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("imperium_planeta.fxml"));
        try
        {
            vBox = fxmlLoader.load();
            controller = fxmlLoader.getController();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        if(controller != null)
        {
            controller.getLabelWspolrzedne().setText(planeta.getWspolrzedne());
            controller.getLabelNazwa().setText(planeta.getNazwa());
            controller.getCheckBoxKsiezyc().setSelected(planeta.isMoon());
            controller.getCheckBoxKsiezycWBudowie().setSelected(planeta.isMoonConstructionInProgress());
            controller.getCheckBoxPlanetaWBudowie().setSelected(planeta.isConstructionInProgress());
        }
    }

    public Planeta getPlaneta() {
        return planeta;
    }

    public ImperiumPlanetaController getController() {
        return controller;
    }

    public VBox getVBox() {
        return vBox;
    }
}
