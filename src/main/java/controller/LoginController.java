package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import login.LoginManager;
import restclient.core.Session;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;


/** Controls the login screen */
public class LoginController {
    @FXML private TextField user;
    @FXML private TextField password;
    @FXML private Button loginButton;
    @FXML private ProgressIndicator loading;

    public void initialize() {}

    public void initManager(final LoginManager loginManager) {
        loginButton.setOnAction(event -> {

            loading.setVisible(true);

            new Thread(() -> {
                if (authorize()) {
                    loginManager.authenticated();
                }
                else{
                    Platform.runLater(() ->  loading.setVisible(false));
                }
            }).start();


        });
    }

    /**
     * Check authorization credentials.
     *
     * If accepted, return a sessionID for the authorized session
     * otherwise, return null.
     */
    private Boolean authorize() {

        return Session.getInstance().login(user.getText(), password.getText());
    }

}