package VendingMachine;

import VendingMachine.CashierPortal;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    // Constants

    private SceneManager sceneManager = new SceneManager(this);

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage0) {

        primaryStage = primaryStage0;
        // Stage is basically the window, and you are given the name of the window.
        primaryStage.setTitle("Vending Machine");

        // we do not want the window to be resizable/
        primaryStage.setResizable(false);


        // If you wanna add an icon for the program
        // Image icon = new Image('File path')
        // primaryStage.setIcon(icon);

        // with javafx, by default with window will appear in the middle. Unlike Swing so no changes needed.

        primaryStage.setScene(sceneManager.getDeafultPageScene());

        primaryStage.show();

    }

    public static void main(String[] args) {

        // View screen
        // Application.launch(args);

        Database db = new Database();

        /// Example Query ///

        db.openConn();
        if (db.dropAllTables() == 0) {
            System.out.println("App.Java - Dropped All Tables");
        }
        db.closeConn();

        /// Example Query ///

        launch(args);
    }

    public void switchScenes(Scene scene) {
        primaryStage.setScene(scene);
    }

    public SceneManager getSceneManager() {
        return this.sceneManager;
    }
}