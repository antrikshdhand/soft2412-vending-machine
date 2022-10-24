package VendingMachine;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

public class CheckoutPage extends Page {

    private Pane pane;

    private SceneManager sm;

    private Button manageSCO;
    private Button summary;
    private Button cancelledTransactions;
    private Button returnToDp;

    private Scene manageCSOPage;

    private Scene summaryPage;
    private Scene cancelledTransactionPage;

    /**
     * The Constructor for the Checkout Page, sets the scene for the checkout page.
     * @param sceneManager
     */
    public CheckoutPage(SceneManager sceneManager) {

        sm = sceneManager;

        pane = new StackPane();
        scene = new Scene(pane, WIDTH, HEIGHT);

        this.createManageCSO();
        this.createCancelledTransaction();
        this.createSummary();


        VBox box = new VBox();
        box.setSpacing(5);
        box.setPrefWidth(190.00);
        box.setAlignment(Pos.CENTER);

        manageSCO = new Button("Modify Available Cash");

        manageSCO.setOnAction(e -> {
        sm.switchScenes(manageCSOPage);});

        summary = new Button("Generate Summary of Change");

        summary.setOnAction(e -> {
        sm.switchScenes(summaryPage);});

        cancelledTransactions = new Button("Generate Summary of Transaction");

        cancelledTransactions.setOnAction(e -> {
        sm.switchScenes(cancelledTransactionPage);});

        returnToDp = new Button("Return to default page");

        returnToDp.setOnAction(e -> sm.switchScenes(sm.getDefaultPageScene()));

        manageSCO.setMinWidth(box.getPrefWidth());
        summary.setMinWidth(box.getPrefWidth());
        cancelledTransactions.setMinWidth(box.getPrefWidth());

        returnToDp.setTranslateX(-550);
        returnToDp.setTranslateY(320);

        Text title = new Text();
        title.setText("Checkout");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 35));

        box.getChildren().addAll(title, manageSCO, summary, cancelledTransactions);
        pane.getChildren().add(box);
        pane.getChildren().add(returnToDp);

     }


    /**
     * Function to return scene.
     * @return
     */

    public void createManageCSO() {
        StackPane pane = new StackPane();
        manageCSOPage = new Scene(pane, WIDTH, HEIGHT);

        Button bn = new Button("Return to Checkout Page");

        Label lbl = new Label("Modify Available Cash");
        lbl.setFont(Font.font("Serif", FontWeight.NORMAL, 20));

        pane.setAlignment(lbl, Pos.TOP_CENTER);
        lbl.setTranslateY(20);
        // pane.setAlignment(bn, Pos.BOTTOM_LEFT);

        bn.setTranslateX(-550);
        bn.setTranslateY(320);

        lbl.relocate(0, 30);

        pane.getChildren().addAll(lbl, bn);
        bn.setOnAction(e -> sm.switchScenes(sm.getCheckoutPageScene()));
    }


    public void createSummary() {
        StackPane pane = new StackPane();
        summaryPage = new Scene(pane, WIDTH, HEIGHT);

        Button bn = new Button("Return to Checkout Page");

        Label lbl = new Label("Generate Summary of Change");
        lbl.setFont(Font.font("Serif", FontWeight.NORMAL, 20));

        pane.setAlignment(lbl, Pos.TOP_CENTER);
        lbl.setTranslateY(20);
        // pane.setAlignment(bn, Pos.BOTTOM_LEFT);

        bn.setTranslateX(-550);
        bn.setTranslateY(320);

        lbl.relocate(0, 30);

        pane.getChildren().addAll(lbl, bn);
        bn.setOnAction(e -> sm.switchScenes(sm.getCheckoutPageScene()));
    }

    public void createCancelledTransaction() {
        StackPane pane = new StackPane();
        cancelledTransactionPage = new Scene(pane, WIDTH, HEIGHT);

        Button bn = new Button("Return to Checkout Page");

        Label lbl = new Label("Generate Summary of Transaction");
        lbl.setFont(Font.font("Serif", FontWeight.NORMAL, 20));

        pane.setAlignment(lbl, Pos.TOP_CENTER);
        lbl.setTranslateY(20);
        // pane.setAlignment(bn, Pos.BOTTOM_LEFT);

        bn.setTranslateX(-550);
        bn.setTranslateY(320);

        lbl.relocate(0, 30);

        pane.getChildren().addAll(lbl, bn);
        bn.setOnAction(e -> sm.switchScenes(sm.getCheckoutPageScene()));
    }

}
