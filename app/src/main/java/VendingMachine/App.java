package VendingMachine;

import VendingMachine.SellerPortal;

import javafx.application.Application; //check
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class App extends Application {

    private Parent createContent() {
        return new StackPane(new Text("Hello World"));
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent(), 1280, 720));
        stage.show();
    }

    public static void main(String[] args) {

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
    }
}