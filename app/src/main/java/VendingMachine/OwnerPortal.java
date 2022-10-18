package VendingMachine;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import javax.swing.plaf.ViewportUI;
import java.awt.*;

public class OwnerPortal{

    private Scene scene;
    private final int width = 1280;
    private final int height = 720;
    private Pane pane;

    private Button cashierPortal;
    private Button sellerPortal;
    private Button manageSCO;
    private Button summary;
    private Button cancelledTransactions;
    private Button returnToDp;

    public OwnerPortal(){
        pane = new StackPane();
        scene = new Scene(pane, width, height);

        VBox box = new VBox();
        box.setSpacing(5);
        box.setPrefWidth(190.00);
        box.setAlignment(Pos.CENTER);

        cashierPortal = new Button("Cashier portal");
        sellerPortal = new Button("Seller portal");
        manageSCO = new Button("Managed privileged users");
        summary = new Button("Generate Users Summary");
        cancelledTransactions = new Button("View unsuccessful transaction");
        returnToDp = new Button("Return to default page");

        cashierPortal.setMinWidth(box.getPrefWidth());
        sellerPortal.setMinWidth(box.getPrefWidth());
        manageSCO.setMinWidth(box.getPrefWidth());
        summary.setMinWidth(box.getPrefWidth());
        cancelledTransactions.setMinWidth(box.getPrefWidth());

        returnToDp.setTranslateX(- 550);
        returnToDp.setTranslateY(320);

        Text title = new Text();
        title.setText("Owner's Portal");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 35));

        box.getChildren().addAll(title, sellerPortal, cashierPortal, manageSCO, summary, cancelledTransactions);
        pane.getChildren().add(box);
        pane.getChildren().add(returnToDp);






    }

    public Scene getScene(){
        return this.scene;
    }



}
