package VendingMachine;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Side;

import java.io.IOException;

import VendingMachine.SceneManager;

 
public class DefaultPage extends Page {

    public DefaultPage(SceneManager sceneManager) {

        SplitPane splitPane = new SplitPane();
        splitPane.setDividerPosition(0, 0.668);

        TabPane tabPane = new TabPane();
        tabPane.setSide(Side.LEFT);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab tab1 = new Tab("Recently Bought", new Label("Show all Recently Bought available"));
        Tab tab2 = new Tab("Drinks"  , new Label("Show all Drinks available"));
        Tab tab3 = new Tab("Chocolate", new Label("Show all Chocolate available"));
        Tab tab4 = new Tab("Candies"  , new Label("Show all Candies available"));
        Tab tab5 = new Tab("Chips", new Label("Show all Chips available"));

        tabPane.getTabs().addAll(tab1, tab2, tab3, tab4, tab5);
        

        AnchorPane rightAnchorPane = new AnchorPane();

        Button proceedToPortalBtn = new Button();
        proceedToPortalBtn.setText("Proceed to Portal");
        
        proceedToPortalBtn.setOnAction(e -> {
            sceneManager.switchScenes(sceneManager.getOwnerPortalScene());
        });

        rightAnchorPane.getChildren().add(proceedToPortalBtn);

        splitPane.getItems().addAll(tabPane, rightAnchorPane);

        scene = new Scene(splitPane, WIDTH, HEIGHT);

    }

}