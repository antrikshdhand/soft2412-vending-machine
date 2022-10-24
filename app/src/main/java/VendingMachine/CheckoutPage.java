package VendingMachine;

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
     * @param sceneManager
     */
    public CheckoutPage(SceneManager sceneManager) {

        sm = sceneManager;

        pane = new StackPane();
        scene = new Scene(pane, WIDTH, HEIGHT);

        this.createPayCard();
        this.createPayCash();


        VBox box = new VBox();
        box.setSpacing(5);
        box.setPrefWidth(190.00);
        box.setAlignment(Pos.CENTER);

        payCard = new Button("Pay by Card");

        payCard.setOnAction(e -> {
        sm.switchScenes(payCardPage);});

        payCash = new Button("Pay by Cash");

        payCash.setOnAction(e -> {
            sm.switchScenes(payCashPage);
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

     }


    /**
     * Function to return scene.
     * @return
     */

    public void createPayCard() {
        StackPane pane = new StackPane();
        payCardPage = new Scene(pane, WIDTH, HEIGHT);

        Button bn = new Button("Return to checkout page");

        Label lbl = new Label("Pay by card");
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

    public void payByCard(SceneManager sceneManager) {

        // this.sceneManager = sceneManager;

        // GridPane grid = new GridPane();
        
        // grid.setHgap(10);
        // grid.setVgap(10);
        // grid.setPadding(new Insets(0, 10, 0, 10));
        // grid.setAlignment(Pos.CENTER); 
        // this.scene = new Scene(grid, WIDTH, HEIGHT);

        // Text scenetitle = new Text("Welcome");
        // scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        // grid.add(scenetitle, 0, 0, 2, 1);

        // Label usernameLabel = new Label("User Name:");
        // grid.add(usernameLabel, 0, 1);

        // TextField userTextField = new TextField();
        // grid.add(userTextField, 1, 1);

        // Label pwLabel = new Label("Password:");
        // grid.add(pwLabel, 0, 2);

        // PasswordField pwBox = new PasswordField();
        // grid.add(pwBox, 1, 2);

        // Button signInButton = new Button("Sign in");

        // pwBox.setOnKeyPressed(
        //         e -> {
        //             if (e.getCode().equals(KeyCode.ENTER))
        //                 signInButton.fire();
        //         });

        // HBox hbBtn = new HBox(10);
        // hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        // grid.add(hbBtn, 1, 4);
        // signInButton.setOnAction(e -> {
        //     this.sceneManager.getDatabase().openConn();

        //     // set username and password variables
        //     String username = userTextField.getText();
        //     String password = pwBox.getText();

        //     int validUsername = sceneManager.getDatabase().validateUsername(username);
        //     if (validUsername == -1) {                
        //         Alert invalidUsernameAlert = new Alert(AlertType.ERROR);
        //         invalidUsernameAlert.setTitle("Invalid username");
        //         invalidUsernameAlert.setHeaderText(String.format("A user with username '%s' does not exist!", username));
        //         invalidUsernameAlert.setContentText("Please try again.");
        //         invalidUsernameAlert.showAndWait();

        //         return;
        //     }
            
        //     int validLogin = sceneManager.getDatabase().login(username, password);
        //     if (validLogin == -1) {
        //         System.out.println(password);
        //         Alert incorrectPassAlert = new Alert(AlertType.ERROR);
        //         incorrectPassAlert.setTitle("Incorrect Password");
        //         incorrectPassAlert.setHeaderText("Your password is incorrect!");
        //         incorrectPassAlert.setContentText("Please try again.");
        //         incorrectPassAlert.showAndWait();
        //         return;
        //     }

        //     // successful login
            
        //     System.out.println("Successful login!");
        //     String role = sceneManager.getDatabase().getRole(username);
        //     System.out.println(role);

        //     sceneManager.getDatabase().closeConn();

        //     sceneManager.getSession().resetSession();
        //     sceneManager.getSession().setLoggedIn(true);
        //     sceneManager.getSession().setUserName(username);
        //     sceneManager.getSession().setRole(role);
            
        //     sceneManager.createNewDefaultPage();
        //     sceneManager.switchScenes(sceneManager.getDefaultPageScene());
            
        // });

        // Button backButton = new Button("Back");
        // backButton.setOnAction(e -> {
        //     sceneManager.switchScenes(sceneManager.getDefaultPageScene());
        // });

        // // add buttons to Hbox
        // hbBtn.getChildren().add(backButton);
        // hbBtn.getChildren().add(signInButton);

    }


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
