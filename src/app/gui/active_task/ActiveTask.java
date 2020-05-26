package app.gui.active_task;

import app.LeafTask;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class ActiveTask {

    private HBox hBox;
    private ActiveTaskController controller;

    public ActiveTask(LeafTask task)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("activetask.fxml"));
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
            this.controller.setTask(task);
            this.controller.setTextLabelTaskName(task.getName());
            this.controller.initButtonOnOff();
        }
    }

    public ActiveTaskController getController() {
        return controller;
    }

    public HBox gethBox() {
        return hBox;
    }
}
