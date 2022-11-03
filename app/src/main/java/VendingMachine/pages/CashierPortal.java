package VendingMachine.pages;

import VendingMachine.SceneManager;
import VendingMachine.pages.Page;
import com.opencsv.CSVWriter;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CashierPortal extends Page {

    private Pane pane;

    private SceneManager sm;

    private Button modifyCash;
    private Button summaryChange;
    private Button summaryTransaction;
    private Button returnToDp;

    private Scene modifyCashPage;
    private Scene summaryChangePage;
    private Scene summaryTransactionPage;

    /**
     * The Constructor for the Cashier Portal, sets the scene for the cashier portal.
     * @param sceneManager
     */
    public CashierPortal(SceneManager sceneManager) {

        sm = sceneManager;

        pane = new StackPane();
        scene = new Scene(pane, WIDTH, HEIGHT);

        this.createModifyCash();
        this.createSummaryTransaction();

        VBox box = new VBox();
        box.setSpacing(5);
        box.setPrefWidth(190.00);
        box.setAlignment(Pos.CENTER);

        modifyCash = new Button("Modify Available Cash");

        modifyCash.setOnAction(e -> {
        sm.switchScenes(modifyCashPage);});

        summaryChange = new Button("Generate Summary of Change");

        summaryChange.setOnAction(e -> {
        createSummaryChange();});

        summaryTransaction = new Button("Generate Summary of Transactions");

        summaryTransaction.setOnAction(e -> {
        sm.switchScenes(summaryTransactionPage);});

        returnToDp = new Button("Return to default page");

        returnToDp.setOnAction(e -> sm.switchScenes(sm.getDefaultPageScene()));

        modifyCash.setMinWidth(box.getPrefWidth());
        summaryChange.setMinWidth(box.getPrefWidth());
        summaryTransaction.setMinWidth(box.getPrefWidth());

        returnToDp.setTranslateX(-550);
        returnToDp.setTranslateY(320);

        Text title = new Text();
        title.setText("Cashier's Portal");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 35));

        box.getChildren().addAll(title, modifyCash, summaryChange, summaryTransaction);
        pane.getChildren().add(box);
        pane.getChildren().add(returnToDp);

    }

    /**
     * Function to create modify cash interface
     */
    public void createModifyCash() {
        StackPane pane = new StackPane();
        modifyCashPage = new Scene(pane, WIDTH, HEIGHT);

        Button bn = new Button("Return to Cashier Portal");

        Label lbl = new Label("Modify Available Cash");
        lbl.setFont(Font.font("Serif", FontWeight.NORMAL, 20));

        pane.setAlignment(lbl, Pos.TOP_CENTER);
        lbl.setTranslateY(20);
        // pane.setAlignment(bn, Pos.BOTTOM_LEFT);

        bn.setTranslateX(-550);
        bn.setTranslateY(320);

        lbl.relocate(0, 30);

        pane.getChildren().addAll(lbl, bn);
        bn.setOnAction(e -> sm.switchScenes(sm.getCashierPortalScene()));
    }

    /**
     * Function to create a summary of change
     */
    public void createSummaryChange() {
        sm.getDatabase().openConn();
        HashMap<String, Integer> hm = sm.getDatabase().getCashSummary();

        File file = new File("./reports/changeSummary.csv");

        try {
            // Create FileWriter object with file as parameter
            FileWriter outputFile = new FileWriter(file);

            // Create CSVWriter object file writer object as parameter
            CSVWriter writer = new CSVWriter(outputFile);

            // Add header to ownerUsersSummary.csv if empty
            if (file.length() == 0) {
                String[] header = {"Currency", "Quantity"};
                writer.writeNext(header);
            }

            // Add data to transactions.csv
            for(Map.Entry<String, Integer> entry : hm.entrySet()) {
                String[] data = {entry.getKey(), entry.getValue() +""};
                writer.writeNext(data);
            }

            writer.close();
            outputFile.close();


            Alert successfulRegisterAlert = new Alert(Alert.AlertType.INFORMATION);
            successfulRegisterAlert.setTitle("Success");
            successfulRegisterAlert.setHeaderText(String.format("Summary generation successful!"));
            successfulRegisterAlert.setContentText("You can view the summary of change available as a csv.");
            successfulRegisterAlert.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();

        }

        sm.getDatabase().closeConn();

    }

    /**
     * Function to create a summary of the transactions
     */
    public void createSummaryTransaction() {
        StackPane pane = new StackPane();
        summaryTransactionPage = new Scene(pane, WIDTH, HEIGHT);

        Button bn = new Button("Return to Cashier Portal");

        Label lbl = new Label("Generate Summary of Transaction");
        lbl.setFont(Font.font("Serif", FontWeight.NORMAL, 20));

        pane.setAlignment(lbl, Pos.TOP_CENTER);
        lbl.setTranslateY(20);
        // pane.setAlignment(bn, Pos.BOTTOM_LEFT);

        bn.setTranslateX(-550);
        bn.setTranslateY(320);

        lbl.relocate(0, 30);

        pane.getChildren().addAll(lbl, bn);
        bn.setOnAction(e -> sm.switchScenes(sm.getCashierPortalScene()));
    }


    /**
     * Function to return scene.
     * @return scene
     */
    public Scene getScene() {
        return scene;
    }

}
