package VendingMachine;

import javafx.scene.Scene;

public class SceneManager {

    private App app;
    private Database database;

    private OwnerPortal ownerPortal;
    private DefaultPage defaultPage;
    private CashierPortal cashierPortal;
    private SellerPortal sellerPortal;
    private Login login;

    private Session session = new Session();

    public SceneManager() {
        database = new Database();
        defaultPage = new DefaultPage(this);

    }

    public void setUp() {
        database = new Database();
        defaultPage = new DefaultPage(this);
        ownerPortal = new OwnerPortal(this);
        cashierPortal = new CashierPortal(this);
        sellerPortal = new SellerPortal(this);
        login = new Login(this);
    }

    public void setApp(App app) {
        this.app = app;
    }

    public void switchScenes(Scene scene) {
        app.switchScenes(scene);
    }
    
    public Scene getDefaultPageScene() {
        return defaultPage.getScene();
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

    public Database getDatabase() {
        return database;
    }

    public Session getSession() {
        return session;
    }

    public void createNewDefaultPage() {
        defaultPage = new DefaultPage(this);
    }

}
