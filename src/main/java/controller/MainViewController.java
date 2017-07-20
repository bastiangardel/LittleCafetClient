package controller;

/**
 * Created by bastiangardel on 17.07.17.
 */

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import login.LoginManager;
import restclient.core.CookieClientRequestFilter;
import restclient.core.RestClient;
import restclient.core.Session;
import restclient.dto.UserInfoDTO;
import restclient.serviceinterfaces.UsersInterface;

/** Controls the main application screen */
public class MainViewController {

    @FXML private MenuItem logoutButton;



    public void initialize() {}

    public void initView(final LoginManager loginManager) {
        logoutButton.setOnAction(event -> loginManager.logout());


/*        infoTab.setOnSelectionChanged(event -> {

            new Thread(() -> {
                RestClient.getInstance().getClient().register(new CookieClientRequestFilter(Session.getInstance().getCookies().get("JSESSIONID")));
                UsersInterface proxy = RestClient.getInstance().getTarget().proxy(UsersInterface.class);

                UserInfoDTO userInfoDTO = proxy.getInfo();

            }).start();



        });*/

    }
}
