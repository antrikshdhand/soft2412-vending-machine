package VendingMachine;

import javafx.scene.input.KeyCode;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Dialog;

import java.util.Optional;

public class Login extends Page {

    private SceneManager sceneManager;
    private GridPane grid;

    private Button signInButton;
    private Button registerButton;
    private Button backButton;

    private HBox hbBtn;
    private Text scenetitle;
    private Label usernameLabel;
    private TextField userTextField;
    private Label pwLabel;
    private PasswordField pwBox;

    private String username;
    private String password;

    public Login(SceneManager sceneManager) {
        
        this.sceneManager = sceneManager;

        setupScene();

        setupLoginForm();

        setupActions();

    }


    private void setupScene() {
        grid = new GridPane();
        grid.setGridLinesVisible(true);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        grid.setAlignment(Pos.CENTER); 

        super.scene = new Scene(grid, WIDTH, HEIGHT);
    }


    private void setupLoginForm() {
        scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        usernameLabel = new Label("User Name:");
        grid.add(usernameLabel, 0, 1);

        userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        pwLabel = new Label("Password:");
        grid.add(pwLabel, 0, 2);

        pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        // setup hbox for buttons
        hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        grid.add(hbBtn, 1, 4);

        signInButton = new Button("Sign In");
        hbBtn.getChildren().add(signInButton);

        registerButton = new Button("Register");
        hbBtn.getChildren().add(registerButton);

        backButton = new Button("Back");
        hbBtn.getChildren().add(backButton);
    }


    private void setupActions() {

        // ACTION 1: if user presses enter key on password box, it fires the sign in button
        pwBox.setOnKeyPressed(
            e -> {
                if (e.getCode().equals(KeyCode.ENTER))
                    signInButton.fire();
            }
        );

        // ACTION 2: if user presses login button, attempt to login
        signInButton.setOnAction(e -> {
            attemptLogin();
        });

        // ACTION 3: if user presses back button, return to default page
        backButton.setOnAction(e -> {
            sceneManager.switchScenes(sceneManager.getDefaultPageScene());
        });

        // ACTION 4: if user presses register button, show register pop up
        registerButton.setOnAction(e -> {
            displayRegisterDialog();
        });

    }

    private void attemptLogin() {
        // set username and password variables
        username = userTextField.getText();
        password = pwBox.getText();

        // if user has inputted nothing, return error immediately
        if (username == null || password == null) {
            showNullEntryError();
        }

        // otherwise check if the username is valid
        this.sceneManager.getDatabase().openConn();
        int validUsername = sceneManager.getDatabase().validateUsername(username);
        if (validUsername == -1) {                
            showUsernameError();
        }
        
        // if username is valid, try and log in
        int validLogin = sceneManager.getDatabase().login(username, password);
        if (validLogin == -1) {
            showIncorrectPasswordError();            
        }

        /* SUCCESSFUL LOGIN */
        
        // update session
        String role = sceneManager.getDatabase().getRole(username);
        
        sceneManager.getSession().resetSession();
        sceneManager.getSession().setLoggedIn(true);
        sceneManager.getSession().setUserName(username);
        sceneManager.getSession().setRole(role);

        // change back to the defualt page, but this time its logged in
        sceneManager.createNewDefaultPage();
        sceneManager.switchScenes(sceneManager.getDefaultPageScene()); 
        
        sceneManager.getDatabase().closeConn();
    }


    private void showNullEntryError() {
        Alert nullUsernameAlert = new Alert(AlertType.ERROR);
        nullUsernameAlert.setTitle("No input entered");
        nullUsernameAlert.setHeaderText("You have not entered any input.");
        nullUsernameAlert.setContentText("Please try again.");
        nullUsernameAlert.showAndWait();

        return;
    }


    private void showUsernameError() {
        Alert invalidUsernameAlert = new Alert(AlertType.ERROR);
        invalidUsernameAlert.setTitle("Invalid username");
        invalidUsernameAlert.setHeaderText(String.format("A user with username '%s' does not exist!", username));
        invalidUsernameAlert.setContentText("Please try again.");
        invalidUsernameAlert.showAndWait();

        return;
    }


    private void showIncorrectPasswordError() {
        Alert incorrectPassAlert = new Alert(AlertType.ERROR);
        incorrectPassAlert.setTitle("Incorrect Password");
        incorrectPassAlert.setHeaderText("Your password is incorrect!");
        incorrectPassAlert.setContentText("Please try again.");
        incorrectPassAlert.showAndWait();
        return;
    }

    
    private void displayRegisterDialog() {
        // initialise dialog pane
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Register new user");
        dialog.setHeaderText("Eshays lad");
        dialog.setResizable(true);
        
        // widgets
        Label label1 = new Label("Name: ");
        Label label2 = new Label("Phone: ");
        TextField text1 = new TextField();
        TextField text2 = new TextField();
                
        // create layout and add to dialog
        GridPane gridRegister = new GridPane();
        gridRegister.add(label1, 1, 1);
        gridRegister.add(text1, 2, 1);
        gridRegister.add(label2, 1, 2);
        gridRegister.add(text2, 2, 2);
        dialog.getDialogPane().setContent(gridRegister);
        
        // add button to dialog
        ButtonType buttonTypeOk = new ButtonType("Okay", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        
        // show dialog
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            System.out.println(result.get());
        }
    }


    public Scene getScene() {
        return this.scene;
    }

}
