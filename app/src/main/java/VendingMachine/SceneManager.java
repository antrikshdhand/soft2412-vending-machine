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
    private SuccessfulPage successfulPage;
    private Session session;

    private DefaultPageController defaultPageController;

    public SceneManager() {
        setUp();
    }

    public void setUp() {
        database = new Database();
        session = new Session();
        ownerPortal = new OwnerPortal(this);
        cashierPortal = new CashierPortal(this);
        sellerPortal = new SellerPortal(this);
        login = new Login(this);
        inputCashPage = new InputCashPage(this);
        successfulPage = new SuccessfulPage(this);

        database.openConn();
        database.queryCancelledTransactions();
        database.closeConn();
    }

    public void switchScenes(Scene scene) {
        stage.setScene(scene);
    }

    public Scene getScene() {
        return stage.getScene();
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


    // This is a way to update the values on screen for the inputCashPage.
    //    public void changeInputCashPage( InputCashPage page){
    //        inputCashPage = page;
    //    }

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

    public SuccessfulPage getSuccessfulPage(){return successfulPage;}
    public Scene getCheckoutPageScene() {
        checkoutPage = new CheckoutPage(this);
        return checkoutPage.getScene();
    }

    public Scene getSuccessfulPageScene(){return this.successfulPage.getScene();};

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

    public DefaultPageController getDefaultPageController() {
        return defaultPageController;
    }
}
