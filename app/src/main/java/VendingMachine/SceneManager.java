package VendingMachine;

import VendingMachine.CashierPortal;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SceneManager {


    private OwnerPortal ownerPortal;
    private DefaultPage defaultPage;

    public SceneManager(App app) {
        defaultPage = new DefaultPage(app);
        ownerPortal = new OwnerPortal(app);
    }
    
    public Scene getDeafultPageScene() {
        return defaultPage.getScene();
    }

    public Scene getOwnerPortalScene() {
        return ownerPortal.getScene();
    }
    
}
