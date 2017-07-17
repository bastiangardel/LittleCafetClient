package controller;

/**
 * Created by bastiangardel on 17.07.17.
 */
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import login.LoginManager;

/** Controls the main application screen */
public class MainViewController {
    @FXML private Button logoutButton;
    @FXML private Label  sessionLabel;

    public void initialize() {}

    public void initSessionID(final LoginManager loginManager, String sessionID) {
        sessionLabel.setText(sessionID);
        logoutButton.setOnAction(event -> loginManager.logout());
    }
}
