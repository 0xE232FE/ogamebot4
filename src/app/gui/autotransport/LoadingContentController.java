package app.gui.autotransport;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoadingContentController
{
    @FXML
    private Label labelAlert;

    int a = 0;
    private final String ALERT = "Wczytuję planety.";

    void labelAlertUpdate()
    {
        switch (a)
        {
            case 0:
                labelAlert.setText(ALERT);
                break;
            case 1:
                labelAlert.setText(ALERT+".");
                break;
            case 2:
                labelAlert.setText(ALERT+"..");
                break;
            case 3:
                labelAlert.setText(ALERT+"...");
                break;
        }

        if(a < 3)
            a++;
        else
            a = 0;
    }
}
