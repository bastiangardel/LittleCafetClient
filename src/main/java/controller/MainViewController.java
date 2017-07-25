package controller;

/**
 * Created by bastiangardel on 17.07.17.
 */

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import login.LoginManager;
import restclient.core.CookieClientRequestFilter;
import restclient.core.RestClient;
import restclient.core.Session;
import restclient.dto.Product;
import restclient.dto.UserInfoDTO;
import restclient.serviceinterfaces.ProductsInterface;
import restclient.serviceinterfaces.UsersInterface;
import tools.Popup;
import tools.ProductsListCell;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

/**
 * Controls the main application screen
 */
public class MainViewController {

    @FXML
    private MenuItem logoutButton;

    @FXML
    private TreeView<String> infostree;

    @FXML
    private Label balance;

    @FXML
    private ListView<Product> productsListView;

    @FXML
    private ListView<Product> basketlistView;

    @FXML
    private Tab buyTab;

    @FXML
    private ProgressIndicator progress;

    @FXML
    private Button eraseButton;

    @FXML
    private Label totalLabel;

    private ObservableList<Product> productsObservableList = FXCollections.observableArrayList();
    private ObservableList<Product> bascket = FXCollections.observableArrayList();
    private BigDecimal total = BigDecimal.valueOf(0.00);

    private void productsUpdate() {
        progress.setVisible(true);

        RestClient.getInstance().getClient().register(new CookieClientRequestFilter(Session.getInstance().getCookies().get("JSESSIONID")));
        ProductsInterface proxy = RestClient.getInstance().getTarget().proxy(ProductsInterface.class);

        try {
            productsObservableList.setAll(proxy.getProducts());
            Platform.runLater(() -> progress.setVisible(false));
        } catch (Exception e) {
            Platform.runLater(() -> Popup.showErrorAlert("!! Error !!", "Loading Products Error", e));
        }
    }


    public void initialize() {
    }

    public void initView(final LoginManager loginManager) {

        productsListView.setCellFactory(list -> new ProductsListCell());
        basketlistView.setCellFactory(list2 -> new ProductsListCell());
        productsListView.setItems(productsObservableList);
        basketlistView.setItems(bascket);

        new Thread(this::productsUpdate).start();

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
                    rfidItem.getChildren().add(new TreeItem<>("---"));

                    balance.setText("CHF " + userInfoDTO.getSolde());
                });
            }
        };

        Timer timer = new Timer("MyTimer");//create a new Timer
        timer.scheduleAtFixedRate(userInfoTask, 30, 3000);//this line starts the timer at the same time its executed


        logoutButton.setOnAction(event -> {
            loginManager.logout();
            if (Session.getInstance().getCookies() == null) timer.cancel();
        });

        buyTab.setOnSelectionChanged((Event event) -> new Thread(this::productsUpdate).start());

        productsListView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                if (mouseEvent.getClickCount() == 2) {

                    Product product = productsListView.getItems().get(productsListView.getSelectionModel().getSelectedIndex());

                    total = total.add(product.getPrice());

                    bascket.add(product);

                    totalLabel.setText("CHF " + total.toString());
                }
            }
        });

        eraseButton.setOnAction(event -> {
            bascket.clear();
            total = BigDecimal.valueOf(0.00);
            totalLabel.setText("CHF " + total.toString());
        });

        basketlistView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                if (mouseEvent.getClickCount() == 2) {

                    total = total.subtract(basketlistView.getItems().get(basketlistView.getSelectionModel().getSelectedIndex()).getPrice());

                    bascket.remove(basketlistView.getSelectionModel().getSelectedIndex());

                    totalLabel.setText("CHF " + total.toString());
                }
            }
        });
    }
}
