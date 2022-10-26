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

import java.io.*;
import com.opencsv.*;

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

        Text scenetitle = new Text("Card Payment");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label cardNumberLabel = new Label("Card No:");
        grid.add(cardNumberLabel, 0, 1);

        TextField cardNumberTextField = new TextField();
        grid.add(cardNumberTextField, 1, 1);

        Label cvvLabel = new Label("CVV:");
        grid.add(cvvLabel, 0, 2);

        PasswordField cvvBox = new PasswordField();
        grid.add(cvvBox, 1, 2);

        Button payButton = new Button("Pay");

        cvvBox.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER))
                payButton.fire();
        });

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        grid.add(hbBtn, 1, 4);
        payButton.setOnAction(e -> {
            this.sceneManager.getDatabase().openConn();

            // Set cardNumber and cvv variables
            String cardNumber = cardNumberTextField.getText();
            String cvv = cvvBox.getText();

            // Write to transactions.csv if valid
            if (checkCardNumber(cardNumber) == true && checkCVV(cvv) == true) {
                writeTransaction(cardNumber, cvv);
            }

            int validUsername = sceneManager.getDatabase().validateUsername(cardNumber);
            if (validUsername == -1) {                
                Alert invalidUsernameAlert = new Alert(AlertType.ERROR);
                invalidUsernameAlert.setTitle("Invalid card number.");
                invalidUsernameAlert.setHeaderText(String.format("The card number inputted is invalid.", cardNumber));
                invalidUsernameAlert.setContentText("Please try again.");
                invalidUsernameAlert.showAndWait();
                return;
            }
            
            int validPayCard = sceneManager.getDatabase().login(cardNumber, cvv);
            if (validPayCard == -1) {
                System.out.println(cvv);
                Alert incorrectPassAlert = new Alert(AlertType.ERROR);
                incorrectPassAlert.setTitle("Incorrect CVV");
                incorrectPassAlert.setHeaderText("The CVV inputted was invalid.");
                incorrectPassAlert.setContentText("Please try again.");
                incorrectPassAlert.showAndWait();
                return;
            }

            // Successful payment
            System.out.println("Payment successful!");
            
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            sceneManager.switchScenes(sceneManager.getCheckoutPageScene());
        });

        // add buttons to Hbox
        hbBtn.getChildren().add(backButton);
        hbBtn.getChildren().add(payButton);

    }

    /**
     * Writes transaction details to transactions.csv file
     * (found in resources directory)
     * @param cardNumber
     * @param CVV
     */
    public void writeTransaction(String cardNumber, String cvv) {

        // Stub values for username and cost
        String username = "Stub";
        Double amount = 420.69;


        File file = new File("transactions.csv");
        try {
            // Create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file, true);
    
            // Create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);
    
            // Add header to transactions.csv if empty
            if (file.length() == 0) {
                String[] header = {"USERNAME", "CARD_NUMBER", "CVV", "TRANSACTION_AMOUNT"};
                writer.writeNext(header);
            }
    
            // Add data to transactions.csv
            String[] data = {
                username, 
                cardNumber, 
                cvv,
                Double.toString(amount)
            };
            
            writer.writeNext(data);
    
            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Checks if card number is valid.
     * @param cardNumber
     */
    public static boolean checkCardNumber(String cardNumber) {

        long number;

        // Checks if long
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