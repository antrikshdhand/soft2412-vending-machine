package VendingMachine.pages;

import VendingMachine.SceneManager;
import VendingMachine.pages.Page;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.collections.FXCollections;

import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ArrayList;

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
        this.createSummaryChange();

        VBox box = new VBox();
        box.setSpacing(5);
        box.setPrefWidth(190.00);
        box.setAlignment(Pos.CENTER);

        modifyCash = new Button("Modify Available Cash");

        modifyCash.setOnAction(e -> {
        sm.switchScenes(modifyCashPage);});

        summaryChange = new Button("Generate Summary of Change");

        summaryChange.setOnAction(e -> {
        sm.switchScenes(summaryChangePage);});

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

        VBox modifyCashBox = new VBox();
        pane.getChildren().add(modifyCashBox);

        ArrayList<String> denomsArr = new ArrayList<String>();
        denomsArr.add("100");
        denomsArr.add("50");
        denomsArr.add("20");
        denomsArr.add("10");
        denomsArr.add("5");
        denomsArr.add("2");
        denomsArr.add("1");
        denomsArr.add("0.5");
        denomsArr.add("0.2");
        denomsArr.add("0.1");
        denomsArr.add("0.05");

        StackPane pane = new StackPane();
        modifyCashPage = new Scene(pane, WIDTH, HEIGHT);

        Label lbl = new Label("Modify Available Cash");
        lbl.setFont(Font.font("Serif", FontWeight.NORMAL, 20));
        
        pane.setAlignment(lbl, Pos.TOP_CENTER);
        lbl.setTranslateY(20);
        lbl.relocate(0, 30);
        
        sm.getDatabase().openConn();
        HashMap<String, Integer> current_cash = sm.getDatabase().getCashSummary();
        sm.getDatabase().closeConn();

        for (Entry<String, Integer> e : current_cash.entrySet()) {
            System.out.println(e.getKey());
            System.out.println(e.getValue());
            System.out.println();
        }

        ComboBox denominations = new ComboBox();
        denominations.getItems().addAll(FXCollections.observableArrayList(denomsArr));

        Button checkCurrent = new Button("Check current numbers");
        checkCurrent.setOnAction(event -> {
            
        });


        Button bn = new Button("Return to Cashier Portal");
        bn.setTranslateX(-550);
        bn.setTranslateY(320);


        pane.getChildren().addAll(lbl, denominations, checkCurrent, bn);
        bn.setOnAction(e -> sm.switchScenes(sm.getCashierPortalScene()));
    }

    /**
     * Function to create a summary of change
     */
    public void createSummaryChange() {
        StackPane pane = new StackPane();
        summaryChangePage = new Scene(pane, WIDTH, HEIGHT);

        Button bn = new Button("Return to Cashier Portal");

        Label lbl = new Label("Generate Summary of Change");
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
