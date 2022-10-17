package VendingMachine;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DatabaseTest {
    private Database db;

    // You want to open a new API to the database
    // and drop all tables so you are working with a empty relations for each test.
    @BeforeEach
    void setUp(){
        db = new Database();
        db.openConn();
        db.dropAllTables();
        db.closeConn();

    }


    // Get rid of the database
    @AfterEach
    void tearDown(){
        db = null;
    }

    @Test
    void testInsertNewOwner(){

        // Simple test to see if it can add a new owner to the database.
        db.openConn();
        int result = db.insertNewOwner("Suli", "Hello");
        db.closeConn();
        System.out.println(result);

        assertEquals(0, result);


    }

    @Test
    void testCheckRole(){

        // This is where you should check role.

    }
}
