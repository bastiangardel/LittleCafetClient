import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import login.LoginManager;
import restclient.core.Session;
import tools.ConfigLoader;

import java.io.IOException;

public class LittleCafetClient extends Application {


    public static void main(String[] args) { launch(args); }
    @Override public void start(Stage stage) throws IOException {
        Scene scene = new Scene(new ScrollPane(),1000, 600);

        LoginManager loginManager = new LoginManager(scene);
        loginManager.showLoginScreen();

        stage.setScene(scene);
        stage.show();

        ConfigLoader.getInstance();
    }


    @Override
    public void stop(){
        Session.getInstance().logout();
    }
}
