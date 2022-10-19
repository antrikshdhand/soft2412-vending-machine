package VendingMachine;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import javax.swing.plaf.ViewportUI;
import java.awt.*;
import java.sql.Statement;
import java.util.Stack;

public class OwnerPortal extends Page{


    private Pane pane;

    private SceneManager sm;

    private Button cashierPortal;
    private Button sellerPortal;
    private Button manageSCO;
    private Button summary;
    private Button cancelledTransactions;
    private Button returnToDp;


    private scene manageCSOPage;
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

        VBox box = new VBox();
        box.setSpacing(5);
        box.setPrefWidth(190.00);
        box.setAlignment(Pos.CENTER);

        cashierPortal = new Button("Cashier portal");

        cashierPortal.setOnAction(e -> this.goToCashierPortal());

        sellerPortal = new Button("Seller portal");

        sellerPortal.setOnAction(e ->  this.goToSellerPortal());

        manageSCO = new Button("Managed privileged users");

        manageSCO.setOnAction(e -> this.createManageCSO());

        summary = new Button("Generate Users Summary");

        summary.setOnAction(e -> this.createSummary());

        cancelledTransactions = new Button("View unsuccessful transaction");

        cancelledTransactions.setOnAction(e -> createCancelledTransaction());

        returnToDp = new Button("Return to default page");

        returnToDp.setOnAction(e -> this.retToDefaultPortal());



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
     * Funciton to switch scenes to the cashier portal
     */
    public void goToCashierPortal(){
        sm.switchScenes(sm.getCashierPortalScene());
    }

    /**
     * Funciton to switch scenes to the seller portal
     */

    public void goToSellerPortal(){
        sm.switchScenes(sm.getSellerPortalScene());
    }

    /**
     * Funciton to switch scenes to the default page portal
     */

    public void retToDefaultPortal(){
        sm.switchScenes(sm.getDeafultPageScene());
    }

    /**
     * Funciton to switch scenes to the default page portal
     */
    public  void goToOwnerPortal(){
        sm.switchScenes(sm.getOwnerPortalScene());
    }

    /**
     * Funciton to returns sence.
     * @return
     */
    public Scene getScene(){
        return this.scene;
    }

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
        bn.setOnAction(e -> this.goToOwnerPortal());
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
        bn.setOnAction(e -> this.goToOwnerPortal());
    }

    public void createCancelledTransaction() {
        StackPane pane = new StackPane();
        manageCSOPage = new Scene(pane, WIDTH, HEIGHT);

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
        bn.setOnAction(e -> this.goToOwnerPortal());
    }


}
