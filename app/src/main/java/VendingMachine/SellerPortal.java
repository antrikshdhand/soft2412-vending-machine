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

public class SellerPortal extends Page {


    public SellerPortal(App app)  {

        var root = new StackPane();

        VBox buttons = new VBox(10);
        // var root = new Pane();
        // root.setHgap(10);

        Button bn1 = new Button("Manage Items");
        // bn1.relocate(0, 50);
        Button bn2 = new Button("Generate List of Available items");
        Button bn3 = new Button("Generate Summary");
        Button bn4 = new Button("Return to Default Page");

        bn4.setOnAction(e -> {
            app.switchScenes(app.getSceneManager().getDeafultPageScene());
        });

        scene = new Scene(root, WIDTH, HEIGHT);

        var lbl = new Label("Seller Portal");
        // lbl.relocate(0, 20);
        lbl.setFont(Font.font("Serif", FontWeight.NORMAL, 20));
        root.getChildren().addAll(lbl, bn4);
        root.setAlignment(lbl, Pos.TOP_CENTER);
        root.setAlignment(bn4, Pos.BOTTOM_LEFT);
        lbl.relocate(0, 30);

        buttons.getChildren().addAll(bn1, bn2, bn3);
        buttons.setAlignment(Pos.CENTER);
        buttons.setAlignment(Pos.CENTER);
        root.getChildren().add(buttons);

        // stage.setTitle("Seller Portal");
        // stage.setScene(scene);
        // stage.show();
    }

}
