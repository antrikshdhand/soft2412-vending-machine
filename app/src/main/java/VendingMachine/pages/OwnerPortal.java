package VendingMachine.pages;

import VendingMachine.SceneManager;

import com.opencsv.CSVWriter;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.collections.FXCollections;
import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

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
        // this.createSummary();

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
     * @param box
     */
    public void setButtons(VBox box) {

        cashierPortal = new Button("Cashier portal");

        cashierPortal.setOnAction(e -> sm.switchScenes(sm.getCashierPortalScene()));

        sellerPortal = new Button("Seller portal");

        sellerPortal.setOnAction(e ->  sm.switchScenes(sm.getSellerPortalScene()));

        manageSCO = new Button("Managed privileged users");

        manageSCO.setOnAction(e -> {
            sm.switchScenes(manageCSOPage);
        });

        summary = new Button("Generate Users Summary");

        summary.setOnAction(e -> {
            createSummary();
        });

        cancelledTransactions = new Button("Generate cancelled transactions");

        cancelledTransactions.setOnAction(e -> {
            createSummary();
        });

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

    /**
     * Function to create the manage CSO UI
     */
    public void createManageCSO() {
        StackPane pane = new StackPane();
        manageCSOPage = new Scene(pane, WIDTH, HEIGHT);
        HBox buttons = new HBox();
        VBox menu = new VBox();
        VBox create = new VBox();

        create.setSpacing(10);

        Button bn = new Button("Return to Owner Portal");

        Label lbl = new Label("Manage Privileged Users");
        AtomicReference<Label> userData = new AtomicReference<>(new Label(""));

        Label usernameLabel = new Label("Enter Username");
        TextField usernameText = new TextField();
        usernameText.setPrefWidth(180);
        usernameText.setMaxWidth(180);

        Label roleLabel = new Label("Enter Role");
        TextField roleText = new TextField();
        roleText.setPrefWidth(180);
        roleText.setMaxWidth(180);

        Button submit = new Button("Create User");

        submit.setOnAction(event -> {
            sm.getDatabase().openConn();

            sm.getDatabase().insertNewUser(usernameText.getText(), "1234", roleText.getText());
            Alert success = new Alert(Alert.AlertType.CONFIRMATION);
            success.setTitle("User Created!");
            success.setHeaderText(String.format("User has been successfully created."));
            success.setContentText("The default password set is '1234'");
            success.showAndWait();

            sm.getDatabase().closeConn();
        });

        create.getChildren().addAll(usernameLabel, usernameText, roleLabel, roleText, submit);
        create.setTranslateY(170);
        create.setTranslateX(520);
        pane.getChildren().add(create);

        userData.get().setTranslateY(80);
        // pane.getChildren().add(userData.get());
        lbl.setFont(Font.font("Serif", FontWeight.NORMAL, 20));

        pane.setAlignment(lbl, Pos.TOP_CENTER);

        ComboBox<String> users = new ComboBox<String>();
        sm.getDatabase().openConn();
        users.getItems().addAll(FXCollections.observableArrayList(sm.getDatabase().queryUsername()));
        sm.getDatabase().closeConn();

        Button ctu = new Button("Change to Customer");

        Button ctc = new Button("Change to Cashier");

        Button cts = new Button("Change to Seller");

        Button removeUser = new Button("Remove User");

        buttons.getChildren().addAll(ctu, ctc, cts, removeUser);
        buttons.setSpacing(2);
        menu.setSpacing(3);
        menu.getChildren().addAll(users, buttons);

        menu.setTranslateY(550);
        menu.setTranslateX(470);


        ctu.setOnAction(event -> {
            sm.getDatabase().openConn();
            sm.getDatabase().changeRole(users.getValue(), "REGISTERED CUSTOMER");
            sm.getDatabase().closeConn();
        });

        ctc.setOnAction(event -> {
            sm.getDatabase().openConn();
            sm.getDatabase().changeRole(users.getValue(), "CASHIER");
            sm.getDatabase().closeConn();
        });

        cts.setOnAction(event -> {
            sm.getDatabase().openConn();
            sm.getDatabase().changeRole(users.getValue(), "SELLER");
            sm.getDatabase().closeConn();
        });

        removeUser.setOnAction(event -> {

        });

        lbl.setTranslateY(20);
        // pane.setAlignment(bn, Pos.BOTTOM_LEFT);

        bn.setTranslateX(-550);
        bn.setTranslateY(320);

        lbl.relocate(0, 30);

        pane.getChildren().addAll(lbl, bn, menu);
        bn.setOnAction(e -> sm.switchScenes(sm.getOwnerPortalScene()));

        users.setOnAction(event -> {

            // createManageCSO();

            sm.getDatabase().openConn();

            userData.set(new Label("USERNAME: " + users.getValue() +
                    ", ROLE:" + sm.getDatabase().getRole(users.getValue())));


            pane.getChildren().add(userData.get());


            sm.getDatabase().closeConn();

            sm.switchScenes(manageCSOPage);

        });
    }


    /**
     * Function to implement the 'create summary' feature
     */
    public void createSummary() {

        sm.getDatabase().openConn();
        HashMap<String, String> hm = sm.getDatabase().queryUsernameAndRole();

        File file = new File("reports/ownerUsersSummary.csv");
        try {
            // Create FileWriter object with file as parameter
            FileWriter outputFile = new FileWriter(file, true);

            // Create CSVWriter object file writer object as parameter
            CSVWriter writer = new CSVWriter(outputFile);

            // Add header to ownerUsersSummary.csv if empty
            if (file.length() == 0) {
                String[] header = {"USERNAME", "PASSWORD"};
                writer.writeNext(header);
            }

            // Add data to transactions.csv
            for(Map.Entry<String, String> usernamePassword : sm.getDatabase().queryUsernameAndRole().entrySet()) {
                String[] data = {usernamePassword.getKey(), usernamePassword.getValue()};
                writer.writeNext(data);
            }

            writer.close();
            outputFile.close();

        } catch (IOException e) {
            e.printStackTrace();

        }

        sm.getDatabase().closeConn();

        Alert successfulRegisterAlert = new Alert(Alert.AlertType.INFORMATION);
        successfulRegisterAlert.setTitle("Success");
        successfulRegisterAlert.setHeaderText(String.format("Summary generation successful!"));
        successfulRegisterAlert.setContentText("You view the summary of users and roles as a csv.");
        successfulRegisterAlert.showAndWait();
    }

    /**
     * Function to view cancelled transaction
     */
    public void createCancelledTransaction() {
        StackPane pane = new StackPane();
        cancelledTransactionPage = new Scene(pane, WIDTH, HEIGHT);

        Button bn = new Button("Return to Owner Portal");

        Label lbl = new Label("CancelledTransaction");
        lbl.setFont(Font.font("Serif", FontWeight.NORMAL, 20));

        pane.setAlignment(lbl, Pos.TOP_CENTER);
        lbl.setTranslateY(20);
        // pane.setAlignment(bn, Pos.BOTTOM_LEFT);

        bn.setTranslateX(-550);
        bn.setTranslateY(320);

        lbl.relocate(0, 30);

        pane.getChildren().addAll(lbl, bn);
        bn.setOnAction(e -> sm.switchScenes(sm.getOwnerPortalScene()));
    }

}