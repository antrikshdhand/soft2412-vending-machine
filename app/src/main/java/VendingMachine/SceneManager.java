package VendingMachine;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class SceneManager {

    private App app;
    private Database database;
    private Stage stage;

    private OwnerPortal ownerPortal;
    private Scene defaultPage;
    private CashierPortal cashierPortal;
    private SellerPortal sellerPortal;
    private Login login;
    private CheckoutPage checkoutPage;

    private Session session = new Session();

    @FXML
    Label roleLabel;

    @FXML
    Label accountLabel;


    @FXML
    ScrollPane scrollPane;

    public SceneManager() {
        database = new Database();
        setUp();
    }


    public void setUp() {
        database = new Database();
//        defaultPage = new DefaultPage(this);
        ownerPortal = new OwnerPortal(this);
        cashierPortal = new CashierPortal(this);
        sellerPortal = new SellerPortal(this);
        login = new Login(this);
        checkoutPage = new CheckoutPage(this);

    }


    public void setApp(App app) {
        this.app = app;
    }


    public void switchScenes(Scene scene) {
//        app.switchScenes(scene);
        stage.setScene(scene);
    }
    

    public Scene getDefaultPageScene() {
        return defaultPage;
    }


    public Scene getOwnerPortalScene() {
        return ownerPortal.getScene();
    }


    public Scene getCashierPortalScene() {
        return cashierPortal.getScene();
    }


    public Scene getSellerPortalScene() {
        return sellerPortal.getScene();
    }


    public Scene getLoginScene() {
        return login.getScene();
    }


    public Scene getCheckoutPageScene() {
        return checkoutPage.getScene();
    }


    public Database getDatabase() {
        return database;
    }


    public Session getSession() {
        return session;
    }


    public void createNewDefaultPage() {
//        defaultPage = new DefaultPage(this);
        roleLabel.setText("Role: " + session.getRole());
        accountLabel.setText("Account: " + session.getUserName());
//        leftVBox.getChildren().add(new Label("Hello label"));
    }

    public void proceedToPortal(ActionEvent event) {
        defaultPage = ((Node)event.getSource()).getScene();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        switchScenes(ownerPortal.getScene());
    }

    public void proceedToCheckout(ActionEvent event) {
        defaultPage = ((Node)event.getSource()).getScene();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        switchScenes(checkoutPage.getScene());
    }

    public void login(ActionEvent event) {
        defaultPage = ((Node)event.getSource()).getScene();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        switchScenes(login.getScene());
    }

    public void showRecentlyBought(ActionEvent event) {

        database.openConn();
        ArrayList<String> recent = database.queryRecent();
        database.closeConn();

        VBox items = new VBox();
        scrollPane.setContent(items);

        for (String r : recent) {
            HBox item = new HBox();
            item.getChildren().addAll(new Label(r), new Button("Add to Cart"));
            items.getChildren().add(item);
        }

    }


}
