package app.gui.controller;

import app.data.configuration.Configuration;
import com.Log;
import com.URLFactory;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FirstStartController
{
    @FXML
    private TextField editTextMainPath;

    @FXML
    private TextField editTextDriverPath;

    @FXML
    private Button buttonRun;

    @FXML
    private Label labelError;


    @FXML
    void save() throws IOException {
        Stage stage = (Stage) buttonRun.getScene().getWindow();
        String s = editTextMainPath.getText();
        String s2 = editTextDriverPath.getText();

        File file = new File(s);
        File file2 = new File(s2);
        // ścieżka do sterowników
        if(file2.exists())
        {
            if(file.exists())
            {
                Configuration.saveConfiguration(s,s2);
                Configuration.save();

                labelError.setText("Zapisano wprowadzone dane.");

                Stage primaryStage = new Stage();
                URLFactory urlFactory = new URLFactory(getClass().getResource("").toString());
                URL url = new URL(urlFactory.path(1,"start.fxml"));
                Log.printLog(FirstStartController.class.getName(),"URL : "+url.toString());
                Parent root = FXMLLoader.load(url);
                primaryStage.setTitle("OgameBot");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
                primaryStage.setOnCloseRequest(e ->
                {
                    Platform.exit();
                    System.exit(0);
                });

                stage.close();
            }
            else
            {
                file.mkdir();
                labelError.setText("Utworzono nową ścieżke gry "+ s);
                Log.printLog(FirstStartController.class.getName(),"Utworzono nową ścieżke gry "+ s);

                Configuration.saveConfiguration(s,s2);
                Configuration.save();

                labelError.setText("Zapisano wprowadzone dane.");

                buttonRun.setDisable(false);
            }
        }
        else
        {
            labelError.setText("Scieżka do sterowników nie istnieje. Wprowadzono \n " + s2);
            Log.printLog(FirstStartController.class.getName(),"Scieżka do sterowników nie istnieje.");
        }
    }

    @FXML
    void run() throws IOException {
        Stage stage = (Stage) buttonRun.getScene().getWindow();

        labelError.setText("Zapisano wprowadzone dane.");
        Stage primaryStage = new Stage();
        URLFactory urlFactory = new URLFactory(getClass().getResource("").toString());
        URL url = new URL(urlFactory.path(1,"start.fxml"));
        Log.printLog(FirstStartController.class.getName(),"URL : "+url.toString());
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("OgameBot");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setOnCloseRequest(e ->
        {
            Platform.exit();
            System.exit(0);
        });

        stage.close();
    }
}
