package VendingMachine;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
    /**
     * This method gets the user a lovely greeting.
     * @return "Hello World!"
     */
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {

        // View screen
        Application.launch(args);

        Database db = new Database();

        /// Example Query ///
        
        db.openConn();
        if (db.dropAllTables() == 0) {
            System.out.println("App.Java - Dropped All Tables");
        }
        db.closeConn();

        /// Example Query ///

        System.out.println(new App().getGreeting());
    }

    // Stub code
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}