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

}
