package VendingMachine;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.Scene;

import VendingMachine.Page;
import VendingMachine.SceneManager;

public class Login extends Page {

    private SceneManager sceneManager;

    public Login(SceneManager sceneManager) {
        
        this.sceneManager = sceneManager;

        GridPane grid = new GridPane();
        grid.gridLinesVisibleProperty(true);
        
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        this.scene = new Scene(grid, WIDTH, HEIGHT);

        Label loginLabel = new Label("LOGIN!");
        grid.add(loginLabel, 3, 3);




        


    }

    public Scene getScene() {
        return this.scene;
    }

}
