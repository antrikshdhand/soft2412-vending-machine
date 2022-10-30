package VendingMachine;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransactionTest {
    private Transaction t;

    // You want to open a new API to the database
    // and drop all tables, you are working with a empty relations for each test.
    @BeforeEach
    void setUp(){
        t = new Transaction();
    }


    // Get rid of the database
    @AfterEach
    void tearDown(){
        t = null;
    }


    // simple test for testing inital total = 0
    @Test
    void testInitialTotal() {
        assertEquals(t.getTotal(), 0.0);
    }

    @Test
    void testAddToTotal() {
        t.addToTotal(12);
        assertEquals(t.getTotal(), 12.0);
    }

    @Test
    void testGetItems() {
        assertNull(t.getItems().get("Sprite"));
        t.addItem("Sprite");
        assertEquals(t.getItems().get("Sprite"), 1);
        t.addItem("Sprite");
        assertEquals(t.getItems().get("Sprite"), 2);
        t.addItem("Sprite");
        assertEquals(t.getItems().get("Sprite"), 3);

    }

    @Test
    void testRemoveItem() {
        t.addItem("Sprite");
        t.addItem("Sprite");
        t.addItem("Sprite");
        t.removeItem("Sprite");
        assertEquals(t.getItems().get("Sprite"), 2);
        t.removeItem("Sprite");
        assertEquals(t.getItems().get("Sprite"), 1);
        t.removeItem("Sprite");
        assertNull(t.getItems().get("Sprite"));

    }

    // Testing if change is initially is 0.
    @Test
    void testGetChangeInitial(){
        double change = t.getChange();

        assertEquals(0, change);
    }

    // Testing if due amount is initially is 0;\
    @Test
    void testGetDueInitial(){
        double due = t.getDue();

        assertEquals(0, due);
    }

    // Testing that Paid is 0 initially
    @Test
    void testGetPaid(){
        double paid = t.getPaid();

        assertEquals(0, paid);
    }

    // Testing setting the paid amount initial.
    @Test
    void testSetPaid(){
        t.setPaid(20);
        double paid = t.getPaid();

        assertEquals(20, paid);
    }

    // Testing Calculate change and due simple
    @Test
    void testCalculateChangDue(){
        // the total price is 0 so anything that is paid should be returned as change,
        // and the due amount should till be 0.
        t.setPaid(20);
        double change = t.getChange();
        double due = t.getDue();


        assertEquals(20, change);
        assertEquals(0,due);
    }

    // Testing reset simple
    @Test
    void testReset(){
        t.addToTotal(200);
        t.setPaid(30);

        t.reset();

        double total = t.getTotal();
        double paid = t.getPaid();
        double change = t.getChange();
        double  due = t.getDue();

        assertEquals(0, total);
        assertEquals(0, paid);
        assertEquals(0, change);
        assertEquals(0, due);
    }


}
