package VendingMachine;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import VendingMachine.Page;
import VendingMachine.SceneManager;

public class Login extends Page {

    private SceneManager sceneManager;

    public Login(SceneManager sceneManager) {
        
        this.sceneManager = sceneManager;

        GridPane grid = new GridPane();
        
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        grid.setAlignment(Pos.CENTER); 
        this.scene = new Scene(grid, WIDTH, HEIGHT);

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button signInButton = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        grid.add(hbBtn, 1, 4);
        signInButton.setOnAction(e -> {
            this.sceneManager.getDatabase().openConn();
            int validUsername = sceneManager.getDatabase().validateUsername(userTextField.getText());
            if (validUsername == -1) {
                System.out.println(String.format("A user with username %s does not exist!", userTextField.getText()));
                return;
            }
            
            int validLogin = sceneManager.getDatabase().login(userTextField.getText(), pwBox.getText());
            if (validLogin == -1) {
                System.out.println("Your password is incorrect!");
                return;
            }

            // successful login
            
            System.out.println("Successful login!");

            this.sceneManager.getDatabase().closeConn();
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            sceneManager.switchScenes(sceneManager.getDefaultPageScene());
        });

        // add buttons to Hbox
        hbBtn.getChildren().add(backButton);
        hbBtn.getChildren().add(signInButton);
        

        


    }

    public Scene getScene() {
        return this.scene;
    }

}