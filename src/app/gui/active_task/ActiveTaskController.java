package app.gui.active_task;

import app.LeafTask;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ActiveTaskController {

    private LeafTask task;


    @FXML
    private Button buttonOnOff;

    @FXML
    private Label labelTaskName;



    void setTextLabelTaskName(String textLabelTaskName) {
        labelTaskName.setText(textLabelTaskName);
    }

    @FXML
    void click()
    {
            if(task.isRun())
            {
                task.setRun(false);
                buttonOnOff.setText("OFF");
            }
            else
            {
                task.setRun(true);
                buttonOnOff.setText("ON");
            }
    }

    void initButtonOnOff()
    {
        if(task.isRun())
        {
//            task.setRun(false);
            buttonOnOff.setText("ON");
        }
        else
        {
//            task.setRun(true);
            buttonOnOff.setText("OFF");
        }
    }

    public void setTask(LeafTask task) {
        this.task = task;
    }

}

