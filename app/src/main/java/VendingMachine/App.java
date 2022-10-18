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
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    public static final int BUTTONWIDTH = WIDTH / 8;
    public static final int BUTTONHEIGHT = HEIGHT / 8;

    public static final double PREFWIDTH = 190.00;
    public static final int SPACING = 5;

    @Override
    public void start(Stage primaryStage) {

        // Stage is basically the window, and you are given the name of the window.
        primaryStage.setTitle("Vending Machine");

        // we do not want the window to be resizable/
        primaryStage.setResizable(false);

        // Setting the height and the width of the window.
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);

        // If you wanna add an icon for the program
        // Image icon = new Image('File path')
        // primaryStage.setIcon(icon);

        // with javafx, by default with window will appear in the middle. Unlike Swing so no changes needed.

        // Test CASHIERPORTAL
        OwnerPortal portal = new OwnerPortal();
        primaryStage.setScene(portal.getScene());

        // DefaultPage defaultPage = new DefaultPage();
        // primaryStage.setScene(defaultPage.getScene());

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
}