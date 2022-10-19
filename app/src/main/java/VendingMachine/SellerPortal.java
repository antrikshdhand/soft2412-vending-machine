package VendingMachine;

import javafx.application.Application; // check
import javafx.scene.layout.Pane;
import javafx.scene.Scene; // check
import javafx.scene.control.Label; // check
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font; // check
import javafx.scene.text.FontWeight; // check
import javafx.stage.Stage; // check
import javafx.geometry.Pos;

public class SellerPortal {


    private Scene scene;
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


    public SellerPortal(Stage stage) {
        this.window = stage;
        createMainPage();
        createManageItems();
        createGenerateList();
        createGenerateSummary();

//        window.setScene(manageItems);

    }


    public void createMainPage() {
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

        bn4.setOnAction(e -> {
            app.switchScenes(app.getSceneManager().getDeafultPageScene());
        });

        scene = new Scene(root, WIDTH, HEIGHT);


        Label lbl = new Label("Seller Portal");
        lbl.setTranslateY(20);
        lbl.setFont(Font.font("Serif", FontWeight.NORMAL, 20));

        pane.setAlignment(lbl, Pos.TOP_CENTER);
        pane.setAlignment(bn4, Pos.BOTTOM_LEFT);

//        bn4.setTranslateX(-550);
//        bn4.setTranslateY(320);

//        lbl.relocate(0, 30);

        buttons.getChildren().addAll(bn1, bn2, bn3);
        buttons.setAlignment(Pos.CENTER);
        buttons.setAlignment(Pos.CENTER);
        pane.getChildren().add(buttons);
        pane.getChildren().addAll(lbl, bn4);
        bn1.setOnMouseClicked(e -> this.gotoCreateItems());
        bn2.setOnMouseClicked(e -> this.gotoGenerateList());
        bn3.setOnMouseClicked(e -> this.gotoGenerateSummary());
    }


    public void createManageItems() {
        manageItemsPane = new StackPane();
        manageItems = new Scene(manageItemsPane, width, height);

        Button bn = new Button("Return to Seller Portal");

        Label lbl = new Label("Manage Items Portal");
        lbl.setFont(Font.font("Serif", FontWeight.NORMAL, 20));

        manageItemsPane.setAlignment(lbl, Pos.TOP_CENTER);
        lbl.setTranslateY(20);
//        generateListPane.setAlignment(bn, Pos.BOTTOM_LEFT);

        bn.setTranslateX(-550);
        bn.setTranslateY(320);

        lbl.relocate(0, 30);

        manageItemsPane.getChildren().addAll(lbl, bn);
        bn.setOnMouseClicked(e -> this.gotoSeller());
    }

    public void createGenerateList() {
        generateListPane = new StackPane();
        generateList = new Scene(generateListPane, width, height);

        Button bn = new Button("Return to Seller Portal");

        Label lbl = new Label("Generate List Portal");
        lbl.setFont(Font.font("Serif", FontWeight.NORMAL, 20));

        generateListPane.setAlignment(lbl, Pos.TOP_CENTER);
        lbl.setTranslateY(20);
//        pane.setAlignment(bn, Pos.BOTTOM_LEFT);

        bn.setTranslateX(-550);
        bn.setTranslateY(320);

        lbl.relocate(0, 30);

        generateListPane.getChildren().addAll(lbl, bn);
        bn.setOnMouseClicked(e -> this.gotoSeller());
    }

    public void createGenerateSummary() {
        generateSummaryPane = new StackPane();
        generateSummary = new Scene(generateSummaryPane, width, height);

        Button bn = new Button("Return to Seller Portal");

        Label lbl = new Label("Generate Summary Portal");
        lbl.setFont(Font.font("Serif", FontWeight.NORMAL, 20));

        generateSummaryPane.setAlignment(lbl, Pos.TOP_CENTER);
        lbl.setTranslateY(20);
//        pane.setAlignment(bn, Pos.BOTTOM_LEFT);

        bn.setTranslateX(-550);
        bn.setTranslateY(320);

        lbl.relocate(0, 30);

        generateSummaryPane.getChildren().addAll(lbl, bn);
        bn.setOnMouseClicked(e -> this.gotoSeller());
    }

    public void gotoCreateItems() {
        window.hide();
        window.setScene(manageItems);
        window.show();
    }

    public void gotoSeller() {
        window.hide();
        window.setScene(scene);
        window.show();
    }

    public void gotoGenerateList() {
        window.hide();
        window.setScene(generateList);
        window.show();
    }

    public void gotoGenerateSummary() {
        window.hide();
        window.setScene(generateSummary);
        window.show();
    }

    public Scene getScene(){
        return this.scene;
    }

    public void showScene() {
        this.window.setScene(scene);
    }
}
