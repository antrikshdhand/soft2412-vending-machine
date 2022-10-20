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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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

        Label usernameLabel = new Label("User Name:");
        grid.add(usernameLabel, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pwLabel = new Label("Password:");
        grid.add(pwLabel, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button signInButton = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        grid.add(hbBtn, 1, 4);
        signInButton.setOnAction(e -> {
            this.sceneManager.getDatabase().openConn();

            // set username and password variables
            String username = userTextField.getText();
            String password = pwBox.getText();

            int validUsername = sceneManager.getDatabase().validateUsername(username);
            if (validUsername == -1) {                
                Alert invalidUsernameAlert = new Alert(AlertType.ERROR);
                invalidUsernameAlert.setTitle("Invalid username");
                invalidUsernameAlert.setHeaderText(String.format("A user with username '%s' does not exist!", username));
                invalidUsernameAlert.setContentText("Please try again.");
                invalidUsernameAlert.showAndWait();

                return;
            }
            
            int validLogin = sceneManager.getDatabase().login(username, password);
            if (validLogin == -1) {
                System.out.println(password);
                Alert incorrectPassAlert = new Alert(AlertType.ERROR);
                incorrectPassAlert.setTitle("Incorrect Password");
                incorrectPassAlert.setHeaderText("Your password is incorrect!");
                incorrectPassAlert.setContentText("Please try again.");
                incorrectPassAlert.showAndWait();
                return;
            }

            // successful login
            
            System.out.println("Successful login!");
            String role = sceneManager.getDatabase().getRole(username);
            System.out.println(role);

            sceneManager.getDatabase().closeConn();

            sceneManager.getSession().resetSession();
            sceneManager.getSession().setLoggedIn(true);
            sceneManager.getSession().setUserName(username);
            sceneManager.getSession().setRole(role);
            
            sceneManager.createNewDefaultPage();
            sceneManager.switchScenes(sceneManager.getDefaultPageScene());
            
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
