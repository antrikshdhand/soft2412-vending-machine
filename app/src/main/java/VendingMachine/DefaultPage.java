package VendingMachine;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Side;
import java.io.IOException;
import java.util.ArrayList;

public class DefaultPage extends Page {

    public DefaultPage(SceneManager sceneManager) {

        SplitPane splitPane = new SplitPane();
        splitPane.setDividerPosition(0, 0.668);

        TabPane tabPane = new TabPane();
        tabPane.setSide(Side.LEFT);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        ArrayList<String> categories = new ArrayList<>();
        categories.add("Drinks");
        categories.add("Chocolate");
        categories.add("Candies");
        categories.add("Chips");

        VBox tab1VBox = new VBox();
        Tab tab1 = new Tab("Recently Bought", tab1VBox);

        sceneManager.getDatabase().openConn();
        ArrayList<String> recent = sceneManager.getDatabase().queryRecent();
        sceneManager.getDatabase().closeConn();

        // System.out.println(recent);

        for (String item : recent) {
            tab1VBox.getChildren().add(new Label(item));
        }

        tab1VBox.setAlignment(Pos.CENTER);

        // tab1VBox.getChildren().addAll(new Label("Show all Recently Bought available"), new Label("Show all Recently Bought available2"));
        tabPane.getTabs().add(tab1);

        for (String category : categories) {
            VBox vBox = new VBox();
            Tab tab = new Tab(category, vBox);

            sceneManager.getDatabase().openConn();
            ArrayList<String> items = sceneManager.getDatabase().queryCategory(category);
            sceneManager.getDatabase().closeConn();

            for (String item : items) {
                vBox.getChildren().add(new Label(item));
            }

            vBox.setAlignment(Pos.CENTER);

            tabPane.getTabs().add(tab);
        }

//        Tab tab2 = new Tab("Drinks"  , new Label("Show all Drinks available"));
//        Tab tab3 = new Tab("Chocolate", new Label("Show all Chocolate available"));
//        Tab tab4 = new Tab("Candies"  , new Label("Show all Candies available"));
//        Tab tab5 = new Tab("Chips", new Label("Show all Chips available"));


        AnchorPane rightAnchorPane = new AnchorPane();
        VBox rightVBox = new VBox();
        rightAnchorPane.getChildren().add(rightVBox);

        VBox sessionDetails = new VBox();
        sessionDetails.getChildren().addAll(new Label("Role: " + sceneManager.getSession().getRole()), new Label("Account: " + sceneManager.getSession().getUserName()));
        sessionDetails.setPadding(new Insets(15));
        rightVBox.getChildren().add(sessionDetails);

        /**
         * Proceed to portal button
         */
        Button proceedToPortalBtn = new Button();
        proceedToPortalBtn.setText("Proceed to Portal");
        proceedToPortalBtn.setOnAction(e -> {
            sceneManager.switchScenes(sceneManager.getOwnerPortalScene());
        });

        /**
         * Checkout button
         */
        Button checkoutBtn = new Button();
        checkoutBtn.setText("Checkout");
        checkoutBtn.setOnAction(e -> {
            sceneManager.switchScenes(sceneManager.getCheckoutPageScene());
        });

        proceedToPortalBtn.setPadding(new Insets(15));
        rightVBox.getChildren().add(proceedToPortalBtn);


        Button loginBtn = new Button("Login");
        loginBtn.setOnAction(e -> {
            sceneManager.switchScenes(sceneManager.getLoginScene());
        });
        loginBtn.setPadding(new Insets(15));
        rightVBox.getChildren().add(loginBtn);

        splitPane.getItems().addAll(tabPane, rightAnchorPane);

        scene = new Scene(splitPane, WIDTH, HEIGHT);


        // Add button to pane with padding
        rightVBox.getChildren().add(checkoutBtn);

    }

}