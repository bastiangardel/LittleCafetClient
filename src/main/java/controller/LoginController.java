package controller;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import restclient.core.RestClient;
import restclient.dto.CredentialDTO;
import restclient.serviceinterfaces.UsersInterface;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import login.LoginManager;
import tools.ErrorAlert;

import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.io.PrintWriter;
import java.io.StringWriter;
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

        int status = 0;

        UsersInterface proxy = RestClient.getInstance().getTarget().proxy(UsersInterface.class);

        CredentialDTO credentialDTO = new CredentialDTO();
        credentialDTO.setUsername(user.getText());
        credentialDTO.setPassword(password.getText());

        try{
            Response response = proxy.authenticate(credentialDTO);

            status = response.getStatus();

            System.out.println("HTTP code: " + status);

            Map<String, NewCookie> cookies = response.getCookies();

            switch (status){
                case 200: break;
                case 401:
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("!! Access Deny!!");
                    alert.setHeaderText(Integer.toString(status));
                    alert.setContentText(response.getStatusInfo().getReasonPhrase());
                    alert.showAndWait();
                    break;
                default:
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("!! Authentication Failure!!");
                    alert2.setHeaderText(Integer.toString(status));
                    alert2.setContentText(response.getStatusInfo().getReasonPhrase());
                    alert2.showAndWait();
            }

            response.close();

        } catch (Exception e)
        {
            ErrorAlert.showAlert("!! Error !!","Authentication Error", e);
        }


        return status == 200;
    }

}