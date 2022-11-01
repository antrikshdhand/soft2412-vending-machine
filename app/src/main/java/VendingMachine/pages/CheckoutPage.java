package VendingMachine.pages;

import VendingMachine.SceneManager;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.animation.*;
import javafx.application.*;
import javafx.event.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.beans.property.*;
import javafx.beans.value.*;

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



        // Elements for timer

        Text timerText = new Text();
        timerText.setTranslateY(-320);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 35));

        int refreshCountdown = 5;
        IntegerProperty countDown = new SimpleIntegerProperty(refreshCountdown);

        countDown.addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // What is done once decremented
                System.out.println(newValue.intValue());
                timerText.setText("Time left: " + Integer.toString(newValue.intValue()));

            }

        });

        final Timeline timeToRefresh = new Timeline();
        timeToRefresh.getKeyFrames().addAll(new KeyFrame(Duration.ZERO, new KeyValue(countDown, refreshCountdown)),
                new KeyFrame(Duration.seconds(refreshCountdown), new KeyValue(countDown, 0)));
        timeToRefresh.playFromStart();




        // Adding child object references to parent objects

        box.getChildren().addAll(title, payCard, payCash, timerText);
        pane.getChildren().add(box);
        pane.getChildren().add(returnToDp);


        // 'Pay by card' button

        PayCard payCardPage = new PayCard(sm);

        payCard.setOnAction(e -> {
            payCardPage.setScene();
            sm.switchScenes(payCardPage.getScene());
        });

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
