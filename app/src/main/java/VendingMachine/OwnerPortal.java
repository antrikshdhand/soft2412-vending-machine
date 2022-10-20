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

public class OwnerPortal extends Page{


     private Pane pane;

    private SceneManager sm;

    private Button cashierPortal;
    private Button sellerPortal;
    private Button manageSCO;
    private Button summary;
    private Button cancelledTransactions;
    private Button returnToDp;

    private Scene manageCSOPage;

    private Scene summaryPage;
    private Scene cancelledTransactionPage;
    /**
     * The Constructor for the Owner Portal, sets the scene for the seller portal.
     * @param sceneManager
     */
    public OwnerPortal(SceneManager sceneManager){

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

         cashierPortal = new Button("Cashier portal");


        cashierPortal.setOnAction(e -> sm.switchScenes(sm.getCashierPortalScene()));


         sellerPortal = new Button("Seller portal");


        sellerPortal.setOnAction(e ->  sm.switchScenes(sm.getSellerPortalScene()));

        manageSCO = new Button("Managed privileged users");

        manageSCO.setOnAction(e -> {
        sm.switchScenes(manageCSOPage);});

        summary = new Button("Generate Users Summary");

        summary.setOnAction(e -> {
        sm.switchScenes(summaryPage);});

        cancelledTransactions = new Button("View unsuccessful transaction");

        cancelledTransactions.setOnAction(e -> {
        sm.switchScenes(cancelledTransactionPage);});

        returnToDp = new Button("Return to default page");

        returnToDp.setOnAction(e -> sm.switchScenes(sm.getDefaultPageScene()));


         cashierPortal.setMinWidth(box.getPrefWidth());
         sellerPortal.setMinWidth(box.getPrefWidth());
         manageSCO.setMinWidth(box.getPrefWidth());
         summary.setMinWidth(box.getPrefWidth());
         cancelledTransactions.setMinWidth(box.getPrefWidth());

         returnToDp.setTranslateX(-550);
         returnToDp.setTranslateY(320);

         Text title = new Text();
         title.setText("Owner's Portal");
         title.setFont(Font.font("Arial", FontWeight.BOLD, 35));

         box.getChildren().addAll(title, sellerPortal, cashierPortal, manageSCO, summary, cancelledTransactions);
         pane.getChildren().add(box);
         pane.getChildren().add(returnToDp);


     }



    /**
     * Funciton to returns sence.
     * @return
     */

    public void createManageCSO() {
        StackPane pane = new StackPane();
        manageCSOPage = new Scene(pane, WIDTH, HEIGHT);

        Button bn = new Button("Return to Owner Portal");

        Label lbl = new Label("Manage Privileged Users");
        lbl.setFont(Font.font("Serif", FontWeight.NORMAL, 20));

        pane.setAlignment(lbl, Pos.TOP_CENTER);
        lbl.setTranslateY(20);
//        pane.setAlignment(bn, Pos.BOTTOM_LEFT);

        bn.setTranslateX(-550);
        bn.setTranslateY(320);

        lbl.relocate(0, 30);

        pane.getChildren().addAll(lbl, bn);
        bn.setOnAction(e -> sm.switchScenes(sm.getOwnerPortalScene()));
    }


    public void createSummary() {
        StackPane pane = new StackPane();
        summaryPage = new Scene(pane, WIDTH, HEIGHT);

        Button bn = new Button("Return to Owner Portal");

        Label lbl = new Label("Generate Summary");
        lbl.setFont(Font.font("Serif", FontWeight.NORMAL, 20));

        pane.setAlignment(lbl, Pos.TOP_CENTER);
        lbl.setTranslateY(20);
//        pane.setAlignment(bn, Pos.BOTTOM_LEFT);

        bn.setTranslateX(-550);
        bn.setTranslateY(320);

        lbl.relocate(0, 30);

        pane.getChildren().addAll(lbl, bn);
        bn.setOnAction(e -> sm.switchScenes(sm.getOwnerPortalScene()));
    }

    public void createCancelledTransaction() {
        StackPane pane = new StackPane();
        cancelledTransactionPage = new Scene(pane, WIDTH, HEIGHT);

        Button bn = new Button("Return to Owner Portal");

        Label lbl = new Label("CancelledTransaction");
        lbl.setFont(Font.font("Serif", FontWeight.NORMAL, 20));

        pane.setAlignment(lbl, Pos.TOP_CENTER);
        lbl.setTranslateY(20);
//        pane.setAlignment(bn, Pos.BOTTOM_LEFT);

        bn.setTranslateX(-550);
        bn.setTranslateY(320);

        lbl.relocate(0, 30);

        pane.getChildren().addAll(lbl, bn);
        bn.setOnAction(e -> sm.switchScenes(sm.getOwnerPortalScene()));
    }


}
