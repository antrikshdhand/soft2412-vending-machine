package VendingMachine;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


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

    public InputCashPage(SceneManager sceneManager){

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
        ColumnConstraints col3 = new ColumnConstraints();
        ColumnConstraints col4 = new ColumnConstraints(coins.getPrefWidth());

        pane.getColumnConstraints().add(col1);
        pane.getColumnConstraints().add(col2);
        pane.getColumnConstraints().add(col1);
        pane.getColumnConstraints().add(col3);

        // Setting up row Constraints

        RowConstraints row1 = new RowConstraints(40);
        RowConstraints row2 = new RowConstraints(640);
        RowConstraints row3 = new RowConstraints( 20 );
        RowConstraints row4 = new RowConstraints(20);

        pane.getRowConstraints().addAll(row1,row2,row3, row4);



        // Make all the buttons
        this.initialiseButton();


        // setting the button layOut.
        this.setButtonLayout(notes, coins);



    }


    /**
     * Function to set up all the buttons.
     */
    public void initialiseButton(){

        // notes
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

        // Cancel Button
        cancel = new Button("Cancel");




    }

    /**
     * Function to set the layout of the buttons on the page.
     * @param notes
     * @param coins
     */

    public void setButtonLayout(VBox notes, VBox coins){


        // All the Notes Layout
        Text selectNotes = new Text("Select Note");
        selectNotes.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        hundredDollars.setMinWidth(notes.getPrefWidth());
        fiftyDollars.setMinWidth(notes.getPrefWidth());
        twentyDollars.setMinWidth(notes.getPrefWidth());
        tenDollars.setMinWidth(notes.getPrefWidth());
        fiveDollars.setMinWidth(notes.getPrefWidth());

        notes.getChildren().addAll(selectNotes, fiftyDollars, twentyDollars, tenDollars, fiveDollars);


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

        pane.add(cancel,1,2);

    }


}
