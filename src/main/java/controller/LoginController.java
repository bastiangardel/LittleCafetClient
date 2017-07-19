package controller;

import restclient.core.RestClient;
import restclient.core.Session;
import restclient.dto.CredentialDTO;
import restclient.serviceinterfaces.UsersInterface;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import login.LoginManager;
import tools.Popup;

import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.Map;


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