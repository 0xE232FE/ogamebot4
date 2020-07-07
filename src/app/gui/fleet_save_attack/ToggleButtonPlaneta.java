package app.gui.fleet_save_attack;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Font;
import ogame.planety.Planeta;


public class ToggleButtonPlaneta
{
    private ToggleButton toggleButton;
    private Planeta planeta;
    private int id;
    private static int idStatic = 1;

    ToggleButtonPlaneta(Planeta planeta, ToggleGroup toggleGroup) {

        toggleButton = new ToggleButton(planeta.getWspolrzedne() + (planeta.isMoon() ? " M":""));
        toggleButton.setToggleGroup(toggleGroup);
        toggleButton.setPrefWidth(75.0);
        toggleButton.setFont(Font.font(10));
        this.planeta = planeta;

        id = idStatic;
        idStatic++;
    }

    ToggleButton getToggleButton() {
        return toggleButton;
    }

    public Planeta getPlaneta() {
        return planeta;
    }

    public int getId() {
        return id;
    }
}
