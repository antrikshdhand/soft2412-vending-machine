package VendingMachine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class App extends Application {

    private Stage primaryStage;
     SceneManager sceneManager = new SceneManager();

    @Override
    public void start(Stage primaryStage0) throws Exception {

        // sceneManager.setApp(this);
        // sceneManager.setUp();


        primaryStage = primaryStage0;
        // Stage is basically the window, and you are given the name of the window.
        primaryStage.setTitle("Vending Machine");

        // we do not want the window to be resizable/
        primaryStage.setResizable(false);

        // If you want to add an icon for the program
        // Image icon = new Image('File path')
        // primaryStage.setIcon(icon);

        // with javafx, by default with window will appear in the middle. Unlike Swing so no changes needed.

        primaryStage.setScene(sceneManager.getInputCashPageScene());
        primaryStage.show();
        URL url = new File("src/main/java/VendingMachine/fxml/Main.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

//        primaryStage.setScene(sceneManager.getDefaultPageScene());

        Scene scene = new Scene(root);
        String css = new File("src/main/java/VendingMachine/css/style.css").toURI().toURL().toExternalForm();

        scene.getStylesheets().add(css);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


    public void switchScenes(Scene scene) {
        primaryStage.setScene(scene);
    }

}