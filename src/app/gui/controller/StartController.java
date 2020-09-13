package app.gui.controller;

import app.data.accounts.Account;
import app.data.accounts.Accounts;
import app.data.configuration.Configuration;
import com.URLFactory;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class StartController {

    @FXML
    private TextField editTextLogin;

    @FXML
    private TextField editTextOgameWebAdress;

    @FXML
    private TextField editTextServer;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label labelError;

    @FXML
    private void initialize()
    {
        if(Accounts.getSelected() != null)
        {
            String s = Accounts.getSelected().getLogin();
            String s2 = Accounts.getSelected().getPassword();
            String s3 = Accounts.getSelected().getSerwer();
            String s4 = Accounts.getSelected().getWeb();
            editTextLogin.setText(s == null ? "" : s);
            passwordField.setText(s2 == null ? "" : s2);
            editTextServer.setText(s3 == null ? "" : s3 );
            editTextOgameWebAdress.setText(s4 == null ? "" : s4);
        }
    }

    @FXML
    void start() throws IOException {
        Stage stage = (Stage) editTextLogin.getScene().getWindow();
        if(emptyTextFields())
        {
            labelError.setText("Nie wprowadzono danych we wszystkich polach.");
        }
        else
        {
            Account a = new Account(editTextLogin.getText(),passwordField.getText(), editTextServer.getText(),
                    editTextOgameWebAdress.getText());
            Accounts.setSelected(a);
            Accounts.addAccount(a);
            Accounts.save();
            Accounts.saveSelected();

            // Uruchamianie nowego okna
            URLFactory urlFactory = new URLFactory(getClass().getResource("").toString());
            URL url = new URL(urlFactory.path(1,"main.fxml"));
            Stage primaryStage = new Stage();
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

    @FXML
    void runTest() throws IOException {
        Stage stage = (Stage) editTextLogin.getScene().getWindow();
        if(emptyTextFields())
        {
            labelError.setText("Nie wprowadzono danych we wszystkich polach.");
        }
        else
        {
            //Ustawienie, że uruchamia się test module
            Configuration.setTestModule(true);
            Account a = new Account(editTextLogin.getText(),passwordField.getText(), editTextServer.getText(),
                    editTextOgameWebAdress.getText());
            Accounts.setSelected(a);
            Accounts.addAccount(a);
            Accounts.save();
            Accounts.saveSelected();

            // Uruchamianie nowego okna
            URLFactory urlFactory = new URLFactory(getClass().getResource("").toString());
            URL url = new URL(urlFactory.path(1,"main.fxml"));
            Stage primaryStage = new Stage();
            Parent root  = FXMLLoader.load(url);

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

    /**
     * Sprawdza czy nie ma pustego pola.
     */
    private boolean emptyTextFields()
    {
        return editTextLogin.getText().isEmpty() && passwordField.getText().isEmpty() && editTextServer.getText().isEmpty()
                && editTextOgameWebAdress.getText().isEmpty();
    }
}
