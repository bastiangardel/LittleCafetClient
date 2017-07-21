package controller;

/**
 * Created by bastiangardel on 17.07.17.
 */

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import login.LoginManager;
import restclient.core.CookieClientRequestFilter;
import restclient.core.RestClient;
import restclient.core.Session;
import restclient.dto.UserInfoDTO;
import restclient.serviceinterfaces.UsersInterface;

/** Controls the main application screen */
public class MainViewController {

    @FXML private MenuItem logoutButton;

    @FXML private TreeView<String> infostree;



    public void initialize() {}

    public void initView(final LoginManager loginManager) {
        logoutButton.setOnAction(event -> loginManager.logout());

        TreeItem<String> rootItem = new TreeItem<String> ("Name");
        TreeItem<String> rootItem2 = new TreeItem<String> ("Bastian Gardel");

        rootItem.getChildren().add(rootItem2);

        rootItem.setExpanded(true);


        infostree.setRoot(rootItem);

/*        infoTab.setOnSelectionChanged(event -> {

            new Thread(() -> {
                RestClient.getInstance().getClient().register(new CookieClientRequestFilter(Session.getInstance().getCookies().get("JSESSIONID")));
                UsersInterface proxy = RestClient.getInstance().getTarget().proxy(UsersInterface.class);

                UserInfoDTO userInfoDTO = proxy.getInfo();

            }).start();



        });*/

    }
}
