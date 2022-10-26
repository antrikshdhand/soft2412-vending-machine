package VendingMachine;

import VendingMachine.controllers.DefaultPageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.zip.Deflater;

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

    protected Session session = new Session();

    private DefaultPageController defaultPageController;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public SceneManager() {
        database = new Database();
        setUp();
    }


    public void setUp() {
        database = new Database();
        // defaultPage = new DefaultPage(this);
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
        // app.switchScenes(scene);
        stage.setScene(scene);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setDefaultPage(Scene defaultPage) {
        this.defaultPage = defaultPage;
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
        defaultPageController.updateSessionBox();
    }

    public void setDefaultPageController(DefaultPageController defaultPageController) {
        this.defaultPageController = defaultPageController;
    }
}
