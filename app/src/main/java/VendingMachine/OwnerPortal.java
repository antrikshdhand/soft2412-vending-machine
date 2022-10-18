package VendingMachine;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;

public class OwnerPortal{

    private Scene scene;
    private final int width = 1280;
    private final int height = 720;
    private Pane pane;

    private Button cashierPortal;
    private Button sellerPortal;
    private Button manageSCO;
    private Button summary;
    private Button cancelledTransaction;

    public OwnerPortal(){
        pane = new Pane();
        scene = new Scene(pane, width, height);


    }

    public Scene getScene(){
        return this.scene;
    }



}
