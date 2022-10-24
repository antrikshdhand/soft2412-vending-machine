package VendingMachine;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

import java.awt.*;

public class InputCashPage extends Page {

    private GridPane pane;
    private Scene scene;

    // All the buttons displaying the notes.
    private Button hundredDollars;
    private Button fiftyDollars;
    private Button twentyDollars;
    private Button tenDollars;
    private Button fiveDollars;

    // Buttons for the coins;
    private Button twoDollars;
    private Button oneDollars;
    private Button fiftyCents;
    private Button twentyCents;
    private Button tenCents;
    private Button fiveCents;

    public InputCashPage(){

        pane = new GridPane();
        scene = new Scene( pane, WIDTH, HEIGHT);


        //
        this.initialiseButton();



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
        twoDollars = new Button("$ 2  ");
        oneDollars = new Button("$ 1  ");
        fiftyCents = new Button("¢ 50 ");
        twentyCents = new Button("¢ 20 ");
        tenCents = new Button("¢ 10 ");
        fiveCents = new Button("¢ 5  ");

    }

    public void setButtonLayout(){


    }


}
