package VendingMachine.pages;

import VendingMachine.SceneManager;
import VendingMachine.pages.Page;
import javafx.scene.Scene; // check
import javafx.scene.control.Label; // check
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font; // check
import javafx.scene.text.FontWeight; // check
import javafx.stage.Stage; // check
import javafx.geometry.Pos;

public class SellerPortal extends Page {

    private Scene scene;
    private SceneManager sm;
    
    private final int width = 1280;
    private final int height = 720;
    
    private StackPane pane;
    private Stage window;

    public Scene manageItems;
    private Scene generateList;
    private Scene generateSummary;

    private StackPane manageItemsPane;
    private StackPane generateListPane;
    private StackPane generateSummaryPane;


    /**
     * Constructor for Seller Portal page
     * @param sm
     */
    public SellerPortal(SceneManager sm) {

        this.sm = sm;
        createMainPage();
        createManageItems();
        //createGenerateList();
        createGenerateSummary();

        // window.setScene(manageItems);

    }


    /**
     * Constructor for main page
     */
    private void createMainPage() {
        pane = new StackPane();
        scene = new Scene(pane, width, height);

        VBox buttons = new VBox(10);
        buttons.setSpacing(5);
        buttons.setPrefWidth(190.00);
        buttons.setAlignment(Pos.CENTER);
        // var pane = new Pane();
        // pane.setHgap(10);

        Button bn1 = new Button("Manage Items");
        // bn1.relocate(0, 50);
        Button bn2 = new Button("Generate List of Available items");
        Button bn3 = new Button("Generate Summary");
        Button bn4 = new Button("Return to Default Page");

        // bn4.setOnAction(e -> {
        //     app.switchScenes(app.getSceneManager().getDefaultPageScene());
        // });
        //
        // scene = new Scene(pane, width, height);


        Label lbl = new Label("Seller Portal");
        lbl.setTranslateY(20);
        lbl.setFont(Font.font("Serif", FontWeight.NORMAL, 20));

        pane.setAlignment(lbl, Pos.TOP_CENTER);
        pane.setAlignment(bn4, Pos.BOTTOM_LEFT);

        // bn4.setTranslateX(-550);
        // bn4.setTranslateY(320);
        //
        // lbl.relocate(0, 30);

        buttons.getChildren().addAll(bn1, bn2, bn3);
        buttons.setAlignment(Pos.CENTER);
        buttons.setAlignment(Pos.CENTER);
        pane.getChildren().add(buttons);
        pane.getChildren().addAll(lbl, bn4);
        bn1.setOnAction(e -> this.sm.switchScenes(manageItems));
        bn2.setOnAction(e -> createItemSummary());
        bn3.setOnAction(e -> this.sm.switchScenes(generateSummary));
        bn4.setOnAction(e -> this.sm.switchScenes(this.sm.getDefaultPageScene())) ;

    }

    /**
     * Function to create the 'manage items' feature
     */
    private void createManageItems() {
        manageItemsPane = new StackPane();
        manageItems = new Scene(manageItemsPane, width, height);

        Button bn = new Button("Return to Seller Portal");

        Label lbl = new Label("Manage Items Portal");
        lbl.setFont(Font.font("Serif", FontWeight.NORMAL, 20));

        manageItemsPane.setAlignment(lbl, Pos.TOP_CENTER);
        lbl.setTranslateY(20);
        // generateListPane.setAlignment(bn, Pos.BOTTOM_LEFT);

        bn.setTranslateX(-550);
        bn.setTranslateY(320);

        lbl.relocate(0, 30);

        manageItemsPane.getChildren().addAll(lbl, bn);
        bn.setOnAction(e -> this.sm.switchScenes(scene));
    }

    private void createItemSummary() {

        sm.getDatabase().openConn();
        sm.getDatabase().getAllItems();

        // File file = new File("./src/main/resources/reports/usersReport.csv");
        
        // try {
        //     // Create FileWriter object with file as parameter
        //     FileWriter outputFile = new FileWriter(file, true);

        //     // Create CSVWriter object file writer object as parameter
        //     CSVWriter writer = new CSVWriter(outputFile);

        //     // Add header to ownerUsersSummary.csv if empty
        //     if (file.length() == 0) {
        //         String[] header = {"USERNAME", "PASSWORD"};
        //         writer.writeNext(header);
        //     }

        //     // Add data to transactions.csv
        //     for(Map.Entry<String, String> usernamePassword : sm.getDatabase().queryUsernameAndRole().entrySet()) {
        //         String[] data = {usernamePassword.getKey(), usernamePassword.getValue()};
        //         writer.writeNext(data);
        //     }

        //     writer.close();
        //     outputFile.close();

        // } catch (IOException e) {
        //     e.printStackTrace();

        // }

        // sm.getDatabase().closeConn();

        // Alert successfulRegisterAlert = new Alert(Alert.AlertType.INFORMATION);
        // successfulRegisterAlert.setTitle("Success");
        // successfulRegisterAlert.setHeaderText(String.format("Summary generation successful!"));
        // successfulRegisterAlert.setContentText("You view the summary of users and roles as a csv.");
        // successfulRegisterAlert.showAndWait();

    }

    // /**
    //  * Function to create the 'generate list' feature
    //  */
    // private void createGenerateList() {
    //     generateListPane = new StackPane();
    //     generateList = new Scene(generateListPane, width, height);

    //     Button bn = new Button("Return to Seller Portal");

    //     Label lbl = new Label("Generate List Portal");
    //     lbl.setFont(Font.font("Serif", FontWeight.NORMAL, 20));

    //     generateListPane.setAlignment(lbl, Pos.TOP_CENTER);
    //     lbl.setTranslateY(20);
    //     // pane.setAlignment(bn, Pos.BOTTOM_LEFT);

    //     bn.setTranslateX(-550);
    //     bn.setTranslateY(320);

    //     lbl.relocate(0, 30);

    //     generateListPane.getChildren().addAll(lbl, bn);
    //     bn.setOnAction(e -> this.sm.switchScenes(scene));
    // }

    /**
     * Function to create the 'generate summary' feature
     */
    private void createGenerateSummary() {
        generateSummaryPane = new StackPane();
        generateSummary = new Scene(generateSummaryPane, width, height);

        Button bn = new Button("Return to Seller Portal");

        Label lbl = new Label("Generate Summary Portal");
        lbl.setFont(Font.font("Serif", FontWeight.NORMAL, 20));

        generateSummaryPane.setAlignment(lbl, Pos.TOP_CENTER);
        lbl.setTranslateY(20);
        // pane.setAlignment(bn, Pos.BOTTOM_LEFT);

        bn.setTranslateX(-550);
        bn.setTranslateY(320);

        lbl.relocate(0, 30);

        generateSummaryPane.getChildren().addAll(lbl, bn);
        bn.setOnAction(e -> this.sm.switchScenes(scene));
    }

    /**
     * Function to return the scene
     */
    public Scene getScene() {
        return this.scene;
    }

}
