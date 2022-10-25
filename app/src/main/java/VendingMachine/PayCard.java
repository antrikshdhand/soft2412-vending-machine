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

public class PayCard extends Page {

    private SceneManager sceneManager;

    public PayCard(SceneManager sceneManager) {
        
        this.sceneManager = sceneManager;

        GridPane grid = new GridPane();
        
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        grid.setAlignment(Pos.CENTER); 
        this.scene = new Scene(grid, WIDTH, HEIGHT);

        Text scenetitle = new Text("Checkout");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label usernameLabel = new Label("Card No:");
        grid.add(usernameLabel, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pwLabel = new Label("CVV:");
        grid.add(pwLabel, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button signInButton = new Button("Pay");

        pwBox.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER))
                signInButton.fire();
        });

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
                invalidUsernameAlert.setTitle("Invalid card number.");
                invalidUsernameAlert.setHeaderText(String.format("The card number inputted is invalid.", username));
                invalidUsernameAlert.setContentText("Please try again.");
                invalidUsernameAlert.showAndWait();
                return;
            }
            
            int validPayCard = sceneManager.getDatabase().login(username, password);
            if (validPayCard == -1) {
                System.out.println(password);
                Alert incorrectPassAlert = new Alert(AlertType.ERROR);
                incorrectPassAlert.setTitle("Incorrect CVV");
                incorrectPassAlert.setHeaderText("The CVV inputted was invalid.");
                incorrectPassAlert.setContentText("Please try again.");
                incorrectPassAlert.showAndWait();
                return;
            }

            // Successful payment
            System.out.println("Payment successful!");
            
            // TODO: Save to txt file, print receipt
            
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            sceneManager.switchScenes(sceneManager.getCheckoutPageScene());
        });

        // add buttons to Hbox
        hbBtn.getChildren().add(backButton);
        hbBtn.getChildren().add(signInButton);

    }

    /**
     * Checks if card number is valid.
     * @param cardNumber
     */
    public static boolean checkCardNumber(String cardNumber) {

        long number;

        // Checks if integer
        try {
            number = Long.parseLong(cardNumber);
        } catch (Exception e) {
            return false;
        }

        // Checks if 16 digits long
        int numDigits = String.valueOf(number).length();
        if (numDigits != 16) {
            return false;
        }

        // If all conditions are met
        return true;
    }

    /**
     * Checks if CVV is valid.
     * @param CVV
     */
    public static boolean checkCVV(String CVV) {

        int number;

        // Checks if integer
        try {
            number = Integer.parseInt(CVV);
        } catch (Exception e) {
            return false;
        }

        // Checks if 16 digits long
        int numDigits = String.valueOf(number).length();
        if (numDigits != 3) {
            return false;
        }

        // If all conditions are met
        return true;
    }

    public Scene getScene() {
        return this.scene;
    }

}
