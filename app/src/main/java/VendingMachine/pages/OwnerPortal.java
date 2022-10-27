package VendingMachine.pages;

import VendingMachine.SceneManager;
import VendingMachine.pages.Page;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.HashMap;
import java.util.Map;

public class OwnerPortal extends Page {

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
    public OwnerPortal(SceneManager sceneManager) {

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

        this.setButtons(box);// sets up all the buttons.

        Text title = new Text();
        title.setText("Owner's Portal");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 35));

        box.getChildren().addAll(title, sellerPortal, cashierPortal, manageSCO, summary, cancelledTransactions);
        pane.getChildren().add(box);
        pane.getChildren().add(returnToDp);


     }


    /**
     * Function to set up all the buttons required on the owner's page.
     */

    public void setButtons( VBox box){

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


     }

    public void createManageCSO() {
        StackPane pane = new StackPane();
        manageCSOPage = new Scene(pane, WIDTH, HEIGHT);
        HBox buttons = new HBox();
        VBox userList = new VBox();
        VBox menu = new VBox();


        Button bn = new Button("Return to Owner Portal");

        Label lbl = new Label("Manage Privileged Users");
        lbl.setFont(Font.font("Serif", FontWeight.NORMAL, 20));

        pane.setAlignment(lbl, Pos.TOP_CENTER);

        ComboBox<String> users = new ComboBox<String>();
        sm.getDatabase().openConn();
        users.getItems().addAll(FXCollections.observableArrayList(sm.getDatabase().queryUsername()));
        sm.getDatabase().closeConn();

        Button ctu = new Button("Change to Customer");

        Button ctc = new Button("Change to Cashier");

        Button cts = new Button("Change to Seller");

        buttons.getChildren().addAll(ctu, ctc, cts);
        menu.getChildren().addAll(users, buttons);

        menu.setTranslateY(550);
        menu.setTranslateX(470);

        sm.getDatabase().openConn();
        HashMap<String, String> hm = sm.getDatabase().queryUsernameAndRole();
        for (Map.Entry<String, String> usernameRole : hm.entrySet()) {
             userList.getChildren().add(new Label(usernameRole.getKey() + " - " + usernameRole.getValue()));
        }

        sm.getDatabase().closeConn();

        ctu.setOnAction(event -> {
            sm.getDatabase().openConn();
            sm.getDatabase().changeRole((String) users.getValue(), "REGISTERED CUSTOMER");
            sm.getDatabase().closeConn();
        });
        ctc.setOnAction(event -> {
            sm.getDatabase().openConn();
            sm.getDatabase().changeRole((String) users.getValue(), "CASHIER");
            sm.getDatabase().closeConn();
        });
        cts.setOnAction(event -> {
            sm.getDatabase().openConn();
            sm.getDatabase().changeRole((String) users.getValue(), "SELLER");
            sm.getDatabase().closeConn();
        });


        lbl.setTranslateY(20);
//        pane.setAlignment(bn, Pos.BOTTOM_LEFT);

        bn.setTranslateX(-550);
        bn.setTranslateY(320);

        lbl.relocate(0, 30);

        pane.getChildren().addAll(lbl, bn, userList, menu);
        bn.setOnAction(e -> sm.switchScenes(sm.getOwnerPortalScene()));
    }


    public void createSummary() {
        StackPane pane = new StackPane();
        summaryPage = new Scene(pane, WIDTH, HEIGHT);

        Button bn = new Button("Return to Owner Portal");

        Label lbl = new Label("Generate Summary");

        TableView table = new TableView();
        TableColumn firstNameCol = new TableColumn("Username");
        TableColumn lastNameCol = new TableColumn("Role");



        sm.getDatabase().openConn();
        HashMap<String, String> hm = sm.getDatabase().queryUsernameAndRole();

        for (Map.Entry<String, String> usernameRole : hm.entrySet()) {
            firstNameCol.setCellValueFactory(
                    new PropertyValueFactory<>(usernameRole.getKey()));

            lastNameCol.setCellFactory(
                    new PropertyValueFactory<>(usernameRole.getValue()));
        }

        sm.getDatabase().closeConn();

        table.getColumns().addAll(firstNameCol, lastNameCol);

//        Button perms = new Button("Change user permissions");
        lbl.setFont(Font.font("Serif", FontWeight.NORMAL, 20));

        pane.setAlignment(lbl, Pos.TOP_CENTER);
//        perms.setTranslateY(100);
        lbl.setTranslateY(20);
//        pane.setAlignment(bn, Pos.BOTTOM_LEFT);

        bn.setTranslateX(-550);
        bn.setTranslateY(320);

        lbl.relocate(0, 30);

        pane.getChildren().addAll(lbl, bn, table);
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
