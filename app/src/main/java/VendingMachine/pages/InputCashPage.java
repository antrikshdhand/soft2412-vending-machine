package VendingMachine.pages;

import VendingMachine.SceneManager;

import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.beans.property.DoubleProperty;

import org.w3c.dom.css.Rect;

import com.sun.source.doctree.AttributeTree;


/**
 * This is the page for when a users want to pay by cash.
 * They can select the amount by clicking the appropriate buttons.
 */
public class InputCashPage extends Page {

    private SceneManager sm;

    private GridPane pane;

    // All the buttons displaying the notes.
    private Button hundredDollars;
    private Button fiftyDollars;
    private Button twentyDollars;
    private Button tenDollars;
    private Button fiveDollars;

    // Buttons for the coins;
    private javafx.scene.control.Button twoDollars;
    private Button oneDollars;
    private Button fiftyCents;
    private Button twentyCents;
    private Button tenCents;
    private Button fiveCents;
    private Button cancel;
    private Button completeTransaction;


    // Label required on screen
    private Text totalAmount;
    private Text dueAmount;
    private Text changeAmount;

    // Amount of each label
    private Label totalAmountDouble;
    private Label dueAmountDouble;
    private Label changeAmountDouble;


    /**
     * Constructor for InputCashPage.
     * Allows for cash payments using various denominations.
     * 
     * @param sceneManager
     */
    public InputCashPage(SceneManager sceneManager) {

        sm = sceneManager;

        pane = new GridPane();
        scene = new Scene( pane, WIDTH, HEIGHT);

        // Vbox For all the notes.
        VBox notes = new VBox();
        notes.setSpacing(10);
        notes.setPrefWidth(100);

        // Vbox for all the coins.
        VBox coins = new VBox();
        coins.setSpacing(10);
        coins.setPrefWidth(100);





        // Setting up all the rows and columns of the gridPane.
        ColumnConstraints col1 = new ColumnConstraints(10);
        ColumnConstraints col2 = new ColumnConstraints(notes.getPrefWidth());
        ColumnConstraints col3 = new ColumnConstraints(10);
        ColumnConstraints col4 = new ColumnConstraints(coins.getPrefWidth());
        ColumnConstraints col5 = new ColumnConstraints(700);
        ColumnConstraints col6 = new ColumnConstraints(200);

        pane.getColumnConstraints().add(col1);
        pane.getColumnConstraints().add(col2);
        pane.getColumnConstraints().add(col3);
        pane.getColumnConstraints().add(col4);
        pane.getColumnConstraints().add(col5);
        pane.getColumnConstraints().add(col6);

        // Setting up row Constraints
        RowConstraints row1 = new RowConstraints(40);
        RowConstraints row2 = new RowConstraints(640);
        RowConstraints row3 = new RowConstraints( 20 );
        RowConstraints row4 = new RowConstraints(20);

        pane.getRowConstraints().addAll(row1, row2, row3, row4);


        // Make all the buttons
        this.initialiseButton();

        // Setting up the button layout.
        this.setButtonLayout(notes, coins);

        // Setting up the labels.
        this.setUpTextAndAmount();

        // Setting up the Amount().
        this.refreshAmounts();


        VBox amountDisplay = new VBox();
        amountDisplay.setSpacing(10);

        pane.add(amountDisplay, 5, 1);

        this.setUpButtonsAction();

        amountDisplay.getChildren().addAll(totalAmount, totalAmountDouble, dueAmount, dueAmountDouble, changeAmount, changeAmountDouble, completeTransaction);


        // Setting Action for the Cancel Button
        cancel.setOnAction((e) -> sceneManager.switchScenes(sceneManager.getCheckoutPageScene()));

        // Setting up Action for the complete Transaction button.
        completeTransaction.setOnAction(e -> {
            if (sm.getSession().getTransaction().getDue() > 0) {
               notEnoughPaid();
            }

            // Getting the total change available in the vending machine
            sm.getDatabase().openConn();
            double change = sm.getDatabase().getTotalChange();
            sm.getDatabase().closeConn();


            // If the change is greater than change in the vending machine, then could be an error
            if (sm.getSession().getTransaction().getChange() > change) {

                // If the transaction was done by guest, insert as anonymous in transaction relation.
                String name = sm.getSession().getUserName();
                if (name.equalsIgnoreCase("guest")) {
                    name = "anonymous";
                }


                sm.getSession().getTransaction().initialHashMap();
                sm.getDatabase().openConn();
                sm.getDatabase().insertNewTransaction("unsuccessful", name, "Change not Available");
                sm.getDatabase().closeConn();
                notEnoughChange();

            }
        });

    }


    /**
     * Function to display alert when user has tried complete the transaction without paying the full amount.
     */
    private void notEnoughPaid() {
        Alert nullUsernameAlert = new Alert(Alert.AlertType.ERROR);
        nullUsernameAlert.setTitle("Not enough cash paid");
        nullUsernameAlert.setHeaderText("You haven't paid the full amount");
        nullUsernameAlert.setContentText("Please try again.");
        nullUsernameAlert.showAndWait();

        return;
    }

    
    /**
     * Function to display alert when there is not enough change in the machine.
     */
    private void notEnoughChange() {

        Alert nullUsernameAlert = new Alert(Alert.AlertType.ERROR);
        nullUsernameAlert.setTitle("Not enough change available");
        nullUsernameAlert.setHeaderText("There is not enough change in the vending machine for the money you have put in");
        nullUsernameAlert.setContentText("Continue to default page");
        nullUsernameAlert.showAndWait();
        sm.switchScenes(sm.getDefaultPageScene());

    }


    /**
     * Function to set up all the buttons.
     */
    public void initialiseButton() {

        // Notes
        hundredDollars = new Button("$ 100");
        fiftyDollars = new Button("$ 50 ");
        twentyDollars = new  Button("$ 20 ");
        tenDollars =  new Button("$ 10 ");
        fiveDollars = new Button("$ 5  ");

        // Coins
        twoDollars = new Button(" $ 2 ");
        oneDollars = new Button(" $ 1 ");
        fiftyCents = new Button("¢ 50 ");
        twentyCents = new Button("¢ 20 ");
        tenCents = new Button("¢ 10 ");
        fiveCents = new Button(" ¢ 5 ");

        // Complete Transaction Button
        completeTransaction = new Button("Finish and Pay");

        // Cancel Button
        cancel = new Button("Cancel");

    }


    /**
     * Function to set the layout of the buttons on the page.
     * @param notes
     * @param coins
     */
    public void setButtonLayout(VBox notes, VBox coins) {

        // All the Notes Layout
        Text selectNotes = new Text("Select Note");
        selectNotes.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        hundredDollars.setMinWidth(notes.getPrefWidth());
        fiftyDollars.setMinWidth(notes.getPrefWidth());
        twentyDollars.setMinWidth(notes.getPrefWidth());
        tenDollars.setMinWidth(notes.getPrefWidth());
        fiveDollars.setMinWidth(notes.getPrefWidth());

        notes.getChildren().addAll(selectNotes, hundredDollars, fiftyDollars, twentyDollars, tenDollars, fiveDollars);


        pane.add(notes, 1, 1);


        // All the Coin Layout
        Text selectCoins = new Text("Select Coin");
        selectCoins.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        twoDollars.setMinWidth(coins.getPrefWidth());
        oneDollars.setMinWidth(coins.getPrefWidth());
        fiftyCents.setMinWidth(coins.getPrefWidth());
        twentyCents.setMinWidth(coins.getPrefWidth());
        tenCents.setMinWidth(coins.getPrefWidth());
        fiveCents.setMinWidth(coins.getPrefWidth());

        coins.getChildren().addAll(selectCoins, twoDollars, oneDollars, fiftyCents, twentyCents, tenCents, fiveCents );


        pane.add(coins, 3, 1);

        pane.add(cancel, 1, 2);

    }


    /**
     * Function to set Up the labels.
     */
    public void setUpTextAndAmount() {
        totalAmount = new Text("Total Amount:");
        totalAmount.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        dueAmount = new Text("Amount Due: ");
        dueAmount.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        changeAmount = new Text("Change required: ");
        changeAmount.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        this.refreshAmounts();
    }


    /**
     * Function to refresh all the Amounts display on screen.
     */
    public void refreshAmounts() {
        totalAmountDouble = new Label("$ " + sm.getSession().getTransaction().getTotal() + "");
        totalAmountDouble.setFont(Font.font("Arial", 14));

        changeAmountDouble = new Label();
        changeAmountDouble.setFont(Font.font("Arial", 14));
        changeAmountDouble.textProperty().bind(sm.getSession().getTransaction().getChangeAmount().asString("$ %.2f"));

        dueAmountDouble = new Label();
        dueAmountDouble.setFont(Font.font("Arial", 14));
        dueAmountDouble.textProperty().bind(sm.getSession().getTransaction().getDueAmount().asString("$ %.2f"));
    }


    /**
     * Method with lambda functions that set up actions for all currency buttons
     */
    public void setUpButtonsAction() {

        // Setting action for the $ 100
        hundredDollars.setOnAction((e) -> {
            sm.getSession().getTransaction().setPaid(sm.getSession().getTransaction().getPaid() + 100);
            sm.getSession().getTransaction().addToCurrencyPaid("100");
            this.refreshAmounts();
        });

        // Setting up action for $ 50
        fiftyDollars.setOnAction((e) -> {
            sm.getSession().getTransaction().setPaid(sm.getSession().getTransaction().getPaid() + 50);
            sm.getSession().getTransaction().addToCurrencyPaid("50");
            this.refreshAmounts();
        });

        // Setting action for the $ 20
        twentyDollars.setOnAction((e) -> {
            sm.getSession().getTransaction().setPaid(sm.getSession().getTransaction().getPaid() + 20);
            sm.getSession().getTransaction().addToCurrencyPaid("20");
            this.refreshAmounts();
        });

        // Setting up action for $ 10
        tenDollars.setOnAction((e) -> {
            sm.getSession().getTransaction().setPaid(sm.getSession().getTransaction().getPaid() + 10);
            sm.getSession().getTransaction().addToCurrencyPaid("10");
            this.refreshAmounts();
        });

        // Setting action for the $ 5
        fiveDollars.setOnAction((e) -> {
            sm.getSession().getTransaction().setPaid(sm.getSession().getTransaction().getPaid() + 5);
            sm.getSession().getTransaction().addToCurrencyPaid("5");
            this.refreshAmounts();
        });

        // Setting up action for $ 2
        twoDollars.setOnAction((e) -> {
            sm.getSession().getTransaction().setPaid(sm.getSession().getTransaction().getPaid() + 2);
            sm.getSession().getTransaction().addToCurrencyPaid("2");
            this.refreshAmounts();
        });

        // Setting action for the $ 1
        oneDollars.setOnAction((e) -> {
            sm.getSession().getTransaction().setPaid(sm.getSession().getTransaction().getPaid() + 1);
            sm.getSession().getTransaction().addToCurrencyPaid("1");
            this.refreshAmounts();
        });

        // Setting up action for ¢ 50
        fiftyCents.setOnAction((e) -> {
            sm.getSession().getTransaction().setPaid(sm.getSession().getTransaction().getPaid() + 0.5);
            sm.getSession().getTransaction().addToCurrencyPaid("0.5");
            this.refreshAmounts();
        });

        // Setting action for the ¢ 20
        twentyCents.setOnAction((e) -> {
            sm.getSession().getTransaction().setPaid(sm.getSession().getTransaction().getPaid() + 0.2);
            sm.getSession().getTransaction().addToCurrencyPaid("0.2");
            this.refreshAmounts();
        });

        // Setting up action for ¢ 10
        tenCents.setOnAction((e) -> {
            sm.getSession().getTransaction().setPaid(sm.getSession().getTransaction().getPaid() + 0.1);
            sm.getSession().getTransaction().addToCurrencyPaid("0.1");
            this.refreshAmounts();
        });

        // Setting up action for ¢ 5
        fiveCents.setOnAction((e) -> {
            sm.getSession().getTransaction().setPaid(sm.getSession().getTransaction().getPaid() + 0.05);
            sm.getSession().getTransaction().addToCurrencyPaid("0.05");
            this.refreshAmounts();
        });

    }

}
