package VendingMachine;

import java.util.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

import javax.swing.plaf.ViewportUI;
import java.awt.*;

public class CashierPortal {

    ArrayList<Button> buttons = new ArrayList<Button>();

    private Scene scene;
    private final int width = App.WIDTH;
    private final int height = App.HEIGHT;
    private Pane pane;

    public CashierPortal(StackPane root) {

        pane = new StackPane();
        scene = new Scene(pane, width, height);

        VBox box = new VBox();
        box.setSpacing(5);
        box.setPrefWidth(190.00);
        box.setAlignment(Pos.CENTER);

        // Create Buttons
        Button modifyAvailableCash = new Button();
        Button generateSummaryOfChange = new Button();
        Button generateSummaryOfTransaction = new Button();
        Button returnButton = new Button();

        // Add to button array
        buttons.add(modifyAvailableCash);
        buttons.add(generateSummaryOfChange);
        buttons.add(generateSummaryOfTransaction);
        buttons.add(returnButton);

        // Set global values
        for (Button button : buttons) {
            button.setMinWidth(box.getPrefWidth());
        }

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

        root.getChildren().add(modifyAvailableCash);
        
    }
}