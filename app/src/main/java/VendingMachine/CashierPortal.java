package VendingMachine;

import java.util.ArrayList;

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

public class CashierPortal extends Page {

    ArrayList<Button> buttons = new ArrayList<Button>();

    private Pane pane;

    public CashierPortal(SceneManager sceneManager) {

        pane = new StackPane();
        scene = new Scene(pane, WIDTH, HEIGHT);

        VBox box = new VBox();
        box.setSpacing(SPACING);
        box.setPrefWidth(PREFWIDTH);
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

        // Set universal values
        for (Button button : buttons) {
            button.setMinWidth(box.getPrefWidth());
        }

        // Setting text to the button
        modifyAvailableCash.setText("Modify Available Cash");
        generateSummaryOfChange.setText("Generate Summary of Change");
        generateSummaryOfTransaction.setText("Generate Summary of Transaction");
        returnButton.setText("Return to Default Page");

        returnButton.setOnAction(e -> {
            sceneManager.switchScenes(sceneManager.getDefaultPageScene());
        });

        // Setting the location of the button
//        modifyAvailableCash.setTranslateX(WIDTH/2);
//        modifyAvailableCash.setTranslateY(HEIGHT*1/6);
//
//        generateSummaryOfChange.setTranslateX(WIDTH/2);
//        generateSummaryOfChange.setTranslateY(HEIGHT*2/6);
//
//        generateSummaryOfTransaction.setTranslateX(WIDTH/2);
//        generateSummaryOfTransaction.setTranslateY(HEIGHT*4/6);
//
//        returnButton.setTranslateX(150);
//        returnButton.setTranslateY(60);

        // Add objects to pane
        for (Button button : buttons) {
            pane.getChildren().add(button);
        }

    }

}