package VendingMachine;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SceneManager {

    private App app;

    private OwnerPortal ownerPortal;
    private DefaultPage defaultPage;
    private SellerPortal cashierPortal;
    private SellerPortal sellerPortal;

    public SceneManager(App app) {

        this.app = app;

        defaultPage = new DefaultPage(this);
        ownerPortal = new OwnerPortal(this);
        cashierPortal = new SellerPortal(this);
        // cashierPortal = new CashierPortal(this);
        sellerPortal = new SellerPortal(this);
    }

    public void switchScenes(Scene scene) {
        app.switchScenes(scene);
    }
    
    public Scene getDeafultPageScene() {
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
    
}
