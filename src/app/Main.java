package app;

import app.data.configuration.Configuration;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Wczytywanie z folderu resources
//        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/start.fx"));

        if(Configuration.firstConfiguration)
        {
            Parent root = FXMLLoader.load(getClass().getResource("gui/first_start.fxml"));
            primaryStage.setTitle("Pierwsze uruchomienie.");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            primaryStage.setOnCloseRequest(e ->
            {
                Platform.exit();
                System.exit(0);
            });
        }
        else
        {
            Parent root = FXMLLoader.load(getClass().getResource("gui/start.fxml"));
            primaryStage.setTitle("OgameBot");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            primaryStage.setOnCloseRequest(e ->
            {
                Platform.exit();
                System.exit(0);
            });
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
