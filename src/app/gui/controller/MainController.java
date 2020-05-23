package app.gui.controller;

import app.Run;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController
{
    private Run run;
    @FXML
    public void initialize()
    {
        run = new Run(this);
        run.setRun(true);
    }

    /**
     *  Czas Gry
     */

    @FXML
    private Label labelGameTime;

    public void setTime(String time)
    {
        labelGameTime.setText(time);
    }
}
