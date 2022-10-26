package VendingMachine.controllers;

import VendingMachine.OwnerPortal;
import VendingMachine.SceneManager;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

public class DefaultPageController {

    private Stage stage;
    private Scene scene;

    public void proceedToPortal(ActionEvent event) throws IOException {

        OwnerPortal ownerPortal = new OwnerPortal(new SceneManager());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = ownerPortal.getScene();
        stage.setScene(scene);
        stage.show();

    }
}
