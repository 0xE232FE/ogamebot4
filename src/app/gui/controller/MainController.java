package app.gui.controller;

import app.Run;
import javafx.fxml.FXML;

public class MainController
{
    private Run run;
    @FXML
    public void initialize()
    {
        run = new Run(this);
        run.setRun(true);
    }
}
