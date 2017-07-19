package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import login.LoginManager;
import restclient.core.Session;


/** Controls the login screen */
public class LoginController {
    @FXML private TextField user;
    @FXML private TextField password;
    @FXML private Button loginButton;

    public void initialize() {}

    public void initManager(final LoginManager loginManager) {
        loginButton.setOnAction(event -> {
            if (authorize()) {
                loginManager.authenticated();
            }
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