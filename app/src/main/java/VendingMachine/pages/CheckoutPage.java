package VendingMachine.pages;

import VendingMachine.SceneManager;
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

import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.SECONDS;

public class CheckoutPage extends Page {

    private Pane pane;
    private SceneManager sm;

    private Button payCard;
    private Button payCash;
    private Button returnToDp;

    private Scene payCardPage;
    private Scene payCashPage;

    /**
     * The Constructor for the Checkout Page, sets the scene for the checkout page.
     * 
     * @param sceneManager
     */
    public CheckoutPage(SceneManager sceneManager) {

        sm = sceneManager;

        pane = new StackPane();
        scene = new Scene(pane, WIDTH, HEIGHT);

        this.createPayCash();

        VBox box = new VBox();
        box.setSpacing(5);
        box.setPrefWidth(190.00);
        box.setAlignment(Pos.CENTER);

        payCard = new Button("Pay by Card");

        payCash = new Button("Pay by Cash");

        payCash.setOnAction(e -> {
            sm.switchScenes(sm.getInputCashPageScene());;
        });

        returnToDp = new Button("Return to default page");

        returnToDp.setOnAction(e -> sm.switchScenes(sm.getDefaultPageScene()));

        payCard.setMinWidth(box.getPrefWidth());
        payCash.setMinWidth(box.getPrefWidth());

        returnToDp.setTranslateX(-550);
        returnToDp.setTranslateY(320);

        Text title = new Text();
        title.setText("Checkout");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 35));

        box.getChildren().addAll(title, payCard, payCash);
        pane.getChildren().add(box);
        pane.getChildren().add(returnToDp);

        PayCard payCardPage = new PayCard(sm);

        payCard.setOnAction(e -> {
            payCardPage.setScene();
            sm.switchScenes(payCardPage.getScene());
        });


        // Create timer
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable runnable = new Runnable() {
            int countdownStarter = 120;

            public void run() {
                // When counter is above limit
                System.out.println(countdownStarter);
                countdownStarter--;

                if (countdownStarter < 0) {
                    // When counter is at or below limit
                    scheduler.shutdown();
                }
            }
        };

        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);

    }

    /**
     * Method to build createPayCash scene for cash payments
     */
    public void createPayCash() {
        StackPane pane = new StackPane();
        payCashPage = new Scene(pane, WIDTH, HEIGHT);

        Button bn = new Button("Return to checkout page");

        Label lbl = new Label("Pay by cash");
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
