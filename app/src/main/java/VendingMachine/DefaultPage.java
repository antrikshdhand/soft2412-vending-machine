package VendingMachine;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

 
public class DefaultPage {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    private BorderPane rootPane = new BorderPane();
    public Button proceedToPortalBtn = new Button();

    private Scene scene;

    public DefaultPage(App app) {
        proceedToPortalBtn.setText("Proceed to Portal");

        proceedToPortalBtn.setOnAction(e -> {
            app.switchScenes(app.getSceneManager().getOwnerPortalScene());
        });

        // proceedToPortalBtn.setOnAction(e -> {
        //     app.switchScenes(owner.getScene());
        // });

        // proceedToPortalBtn.setOnAction(e -> {
        //     SceneController sc = new SceneController();
        //     try {
        //         sc.switchToScene1(e);
        //     } catch (IOException ee) {
        //         System.out.println("Something went wrong.");
        //     }
            
        // });
        
        // StackPane root = new StackPane();
        // root.getChildren().add(btn);

        // ToolBar toolbar = new ToolBar();
        HBox statusbar = new HBox();
        // borderPane.setTop(toolbar);
        // borderPane.setCenter();

        VBox categoriesVBox = new VBox(8); // spacing = 8
        categoriesVBox.getChildren().addAll(new Button("Recently bought"), new Button("Drinks"), new Button("Chocolate"), new Button("Candies"), new Button("Chips"));
        rootPane.setLeft(categoriesVBox);

        VBox defaultPageRightVbox = new VBox(8); // spacing = 8
        defaultPageRightVbox.getChildren().addAll(new Button("Log Out"), proceedToPortalBtn, new Button("Proceed to Checkout"));
        rootPane.setRight(defaultPageRightVbox);

        scene = new Scene(rootPane, WIDTH, HEIGHT);

    }

    public Scene getScene() {
        return scene;
    }
}