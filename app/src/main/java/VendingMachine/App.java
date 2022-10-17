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
    public static final int WIDTH = 300;
    public static final int HEIGHT = 250;

    public static final int BUTTONWIDTH = WIDTH / 8;
    public static final int BUTTONHEIGHT = HEIGHT / 8;

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

        // launch();
        Application.launch(SellerPortal.class);
        Application.launch(CashierPortal.class);
    }
}