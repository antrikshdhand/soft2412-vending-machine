package VendingMachine;

import VendingMachine.controllers.DefaultPageController;
import VendingMachine.pages.*;
import javafx.scene.*;
import javafx.stage.Stage;

public class SceneManager {

    private Database database;
    private Stage stage;

    private OwnerPortal ownerPortal;
    private Scene defaultPage;
    private CashierPortal cashierPortal;
    private SellerPortal sellerPortal;
    private Login login;
    private CheckoutPage checkoutPage;
    private InputCashPage inputCashPage;

    protected Session session = new Session();

    private DefaultPageController defaultPageController;

    public SceneManager() {
        setUp();
    }

    public void setUp() {
        database = new Database();
        ownerPortal = new OwnerPortal(this);
        cashierPortal = new CashierPortal(this);
        sellerPortal = new SellerPortal(this);
        login = new Login(this);
        checkoutPage = new CheckoutPage(this);
        inputCashPage = new InputCashPage(this);
    }

    public void switchScenes(Scene scene) {
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

    public Scene getInputCashPageScene() {
        return inputCashPage.getScene();
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
