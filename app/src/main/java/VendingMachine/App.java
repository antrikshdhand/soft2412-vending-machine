package VendingMachine;

import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;

public class App extends Application {

    private Stage primaryStage;
    SceneManager sceneManager = new SceneManager(this);

    @Override
    public void start(Stage primaryStage0) throws Exception {

        primaryStage = primaryStage0;
        // Stage is basically the window, and you are given the name of the window.
        primaryStage.setTitle("Vending Machine");

        // we do not want the window to be resizable/
        primaryStage.setResizable(false);

        // If you wanna add an icon for the program
        // Image icon = new Image('File path')
        // primaryStage.setIcon(icon);

        // with javafx, by default with window will appear in the middle. Unlike Swing so no changes needed.

        primaryStage.setScene(sceneManager.getDefaultPageScene());
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public void switchScenes(Scene scene) {
        primaryStage.setScene(scene);
    }

}