package controller;

/**
 * Created by bastiangardel on 17.07.17.
 */

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;
import login.LoginManager;
import restclient.core.CookieClientRequestFilter;
import restclient.core.RestClient;
import restclient.core.Session;
import restclient.dto.UserInfoDTO;
import restclient.serviceinterfaces.UsersInterface;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Controls the main application screen
 */
public class MainViewController {

    @FXML
    private MenuItem logoutButton;

    @FXML
    private TreeView<String> infostree;

    @FXML Label balance;

    private Integer i = 0;


    public void initialize() {
    }

    public void initView(final LoginManager loginManager) {


        TreeItem<String> rootItem = new TreeItem<>("User Info");

        ObservableList<TreeItem<String>> list = rootItem.getChildren();

        TreeItem<String> nameItem = new TreeItem<>("Name");
        TreeItem<String> emailItem = new TreeItem<>("Email");
        TreeItem<String> rfidItem = new TreeItem<>("RFID");
        TreeItem<String> roleItem = new TreeItem<>("Role");


        list.add(nameItem);
        list.add(emailItem);
        list.add(roleItem);
        list.add(rfidItem);

        rootItem.setExpanded(true);
        infostree.setRoot(rootItem);

        TimerTask userInfoTask = new TimerTask() {

            @Override
            public void run() {
                RestClient.getInstance().getClient().register(new CookieClientRequestFilter(Session.getInstance().getCookies().get("JSESSIONID")));
                UsersInterface proxy = RestClient.getInstance().getTarget().proxy(UsersInterface.class);

                UserInfoDTO userInfoDTO = proxy.getInfo();

                Platform.runLater(() -> {

                    nameItem.getChildren().clear();
                    nameItem.getChildren().add(new TreeItem<>(userInfoDTO.getName()));
                    emailItem.getChildren().clear();
                    emailItem.getChildren().add(new TreeItem<>(userInfoDTO.getUsername()));
                    roleItem.getChildren().clear();
                    roleItem.getChildren().add(new TreeItem<>("---"));
                    rfidItem.getChildren().clear();
                    rfidItem.getChildren().add(new TreeItem<>(Integer.toString(++i)));

                    balance.setText("CHF" + userInfoDTO.getSolde());



                });

            }
        };
        Timer timer = new Timer("MyTimer");//create a new Timer
        timer.scheduleAtFixedRate(userInfoTask, 30, 3000);//this line starts the timer at the same time its executed


        logoutButton.setOnAction(event -> {

            loginManager.logout();

            if (Session.getInstance().getCookies() == null) timer.cancel();

        });


    }
}
