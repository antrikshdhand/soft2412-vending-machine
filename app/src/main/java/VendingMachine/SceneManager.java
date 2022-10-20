package VendingMachine;

import javafx.scene.Scene;

public class SceneManager {

    private App app;
    private Database database;

    private OwnerPortal ownerPortal;
    private DefaultPage defaultPage;
    private SellerPortal cashierPortal;
    private SellerPortal sellerPortal;

    public SceneManager(App app) {
        this.app = app;

        database = new Database();

        /// Example Query ///

//        database.openConn();
//        if (database.dropAllTables() == 0) {
//            System.out.println("App.Java - Dropped All Tables");
//        }
//        database.closeConn();

        /// Example Query ///


        defaultPage = new DefaultPage(this);
        ownerPortal = new OwnerPortal(this);
        cashierPortal = new SellerPortal(this);
        // cashierPortal = new CashierPortal(this);
        sellerPortal = new SellerPortal(this);
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

    public Database getDatabase() {
        return database;
    }

}
