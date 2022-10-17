package VendingMachine;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class CashierPortal extends App {

    public CashierPortal() {

        // Create Buttons
        Button modifyAvailableCash = new Button();
        Button generateSummaryOfChange = new Button();
        Button generateSummaryOfTransaction = new Button();
        Button returnButton = new Button();

        // Setting text to the button
        modifyAvailableCash.setText("Modify Available Cash");
        generateSummaryOfChange.setText("Generate Summary of Change");
        generateSummaryOfTransaction.setText("Generate Summary of Transaction");
        returnButton.setText("Return to Default Page");

        // Setting the location of the button
        modifyAvailableCash.setTranslateX(150);
        modifyAvailableCash.setTranslateY(60);

        generateSummaryOfChange.setTranslateX(150);
        generateSummaryOfChange.setTranslateY(60);

        generateSummaryOfTransaction.setTranslateX(150);
        generateSummaryOfTransaction.setTranslateY(60);

        returnButton.setTranslateX(150);
        returnButton.setTranslateY(60);
        
    }
}