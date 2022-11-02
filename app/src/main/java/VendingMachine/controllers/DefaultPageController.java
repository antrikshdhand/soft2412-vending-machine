package VendingMachine.controllers;

import VendingMachine.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

public class DefaultPageController {


    private SceneManager sceneManager = new SceneManager();
    private Database database;
    private Session session;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    @FXML
    Label roleLabel;

    @FXML
    Label accountLabel;

    @FXML
    ScrollPane scrollPane;

    @FXML
    ScrollPane cartScrollPane;

    @FXML
    Label totalLabel;

    @FXML
    Button loginBtn;

    @FXML
    Button proceedToPortalBtn;

    public DefaultPageController() {
        sceneManager.setDefaultPageController(this);
        database = sceneManager.getDatabase();
        session = sceneManager.getSession();
        // ((VBox) proceedToPortalBtn.getParent()).getChildren().remove(proceedToPortalBtn);
        // proceedToPortalBtn.setDisable(false);
        // database.openConn();
        // ArrayList<String> c = database.queryUsername();
        // database.closeConn();
        // System.out.println(c);
    }

    public void setDefaultPageAndStage(ActionEvent event) {
        sceneManager.setDefaultPage(((Node)event.getSource()).getScene());
        sceneManager.setStage((Stage)((Node)event.getSource()).getScene().getWindow());
    }

    public void proceedToPortal(ActionEvent event) {
        setDefaultPageAndStage(event);
        if(session.isLoggedIn()){

            database.openConn();
            String role = database.getRole(session.getUserName());
            database.closeConn();

            if(role.equalsIgnoreCase("owner")) {
                sceneManager.switchScenes(sceneManager.getOwnerPortalScene());
            }
            else if(role.equalsIgnoreCase("seller")) {
                sceneManager.switchScenes(sceneManager.getSellerPortalScene());
            }
            else if(role.equalsIgnoreCase("cashier")) {
                sceneManager.switchScenes(sceneManager.getCashierPortalScene());
            }
        }
    }

    public void proceedToCheckout(ActionEvent event) {
        setDefaultPageAndStage(event);
        sceneManager.switchScenes(sceneManager.getCheckoutPageScene());
    }

    public void login(ActionEvent event) {
        if (!session.isLoggedIn()) {
            setDefaultPageAndStage(event);
            sceneManager.switchScenes(sceneManager.getLoginScene());
        }
        else {
            session.resetSession();
            updateSessionBox();
            loginBtn.setText("Log In");
            proceedToPortalBtn.setText("Please login first");
            // proceedToPortalBtn.setDisable(false);

        }
    }

    public void logout() {
        session.resetSession();
        updateSessionBox();
        loginBtn.setText("Log In");
        proceedToPortalBtn.setText("Please login first");
    }

    public void updateSessionBox() {
        roleLabel.setText("Role: " + session.getRole());
        accountLabel.setText("Account: " + session.getUserName());
        loginBtn.setText("Log Out");
        proceedToPortalBtn.setText("Proceed to Portal");
            // proceedToPortalBtn.setDisable(true);
    }

    public void displayItemStrings(ArrayList<String> itemStrings) {

        VBox items = new VBox();
        items.setSpacing(30);
        scrollPane.setContent(items);

        database.openConn();
        for (String itemCode : itemStrings) {

            HBox item = new HBox();
            item.setPadding(new Insets(10));
            HBox.setMargin(item, new Insets(50));
            item.setPrefSize(500, 100);

            Font font = new Font("Arial", 15);

            String itemName = database.queryItemName(itemCode);
            Label nameLabel = new Label(itemName);
            nameLabel.setFont(font);

            Region region1 = new Region();
            HBox.setHgrow(region1, Priority.ALWAYS);

            Double itemPrice = database.queryItemPrice(itemCode);
            Label priceLabel = new Label("$" + df.format(itemPrice));
            priceLabel.setFont(font);

            Region region2 = new Region();
            HBox.setHgrow(region2, Priority.ALWAYS);

            int itemQuantity = database.queryItemQuantity(itemCode);
            Label quantityLabel = new Label("Stock: " + itemQuantity);
            quantityLabel.setFont(font);

            Region region3 = new Region();
            HBox.setHgrow(region3, Priority.ALWAYS);

            Button button = new Button("Add to Cart");
            button.setFont(font);

            button.setOnAction(event -> {
                session.getTransaction().addItem(itemName);
                session.getTransaction().addToTotal(itemPrice);
                updateCart();
            });

            item.getChildren().addAll(
                    nameLabel,
                    region1,
                    priceLabel,
                    region2,
                    quantityLabel,
                    region3,
                    button
            );

            item.setStyle("-fx-background-color:#98aded");
            items.getChildren().add(item);

        }
        database.closeConn();
        

    }

    public void displayRecentlyBought(ActionEvent event) {

        database.openConn();
        ArrayList<String> itemStrings = database.queryRecent();
        database.closeConn();

        displayItemStrings(itemStrings);

    }

    public void displayCategory(String category) {

        database.openConn();
        ArrayList<String> itemStrings = database.queryAllItemsByCategory(category);
        database.closeConn();

        displayItemStrings(itemStrings);

    }

    public void displayDrinks(ActionEvent event) {
        displayCategory("Drinks");
    }

    public void displayChocolate(ActionEvent event) {
        displayCategory("Chocolate");
    }

    public void displayCandies(ActionEvent event) {
        displayCategory("Candies");
    }

    public void displayChips(ActionEvent event) {
        displayCategory("Chips");
    }

    public void updateCart(){

        VBox items = new VBox();
        items.setSpacing(30);
        cartScrollPane.setContent(items);

        totalLabel.setText("Total: $" + df.format(session.getTransaction().getTotal()));

        for (Map.Entry<String,Integer> entry : session.getTransaction().getItems().entrySet()) {
            // System.out.println("Key = " + entry.getKey() +
            //         ", Value = " + entry.getValue());

            HBox item = new HBox();
            item.setPadding(new Insets(10));
            HBox.setMargin(item, new Insets(20));
            item.setPrefSize(250, 50);

            Button button = new Button("Remove");

            Label label = new Label(entry.getKey() + " - qty: " + entry.getValue());

            button.setOnAction(event -> {
                session.getTransaction().removeItem(entry.getKey());
                label.setText(entry.getKey() + " - qty: " + session.getTransaction().getItems().get(entry.getKey()));
                if (session.getTransaction().getItems().get(entry.getKey()) == null) {
                    items.getChildren().remove(item);
                }
                session.getTransaction().addToTotal(-1);
                totalLabel.setText("Total: $" + df.format(session.getTransaction().getTotal()));
            });

            Region region1 = new Region();
            HBox.setHgrow(region1, Priority.ALWAYS);

            item.getChildren().addAll(label, region1, button);

            item.setStyle("-fx-background-color:#98aded");
            items.getChildren().add(item);


        }

    }


}

