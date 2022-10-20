package VendingMachine;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        db.openConn();
        db.dropAllTables();
        db.closeConn();
        db = null;
    }

    @Test
    void testinsertNewUser(){

        // Simple test to see if it can add a new owner, seller, cashier, and register customer to the db to the database.
        // through the Gui the guest should not be allowed to add in a new guest.
        db.openConn();
        int owner = db.insertNewUser("Suli", "Hello","Owner");
        int seller = db.insertNewUser("Udit", "Hello", "Seller" );
        int cashier = db.insertNewUser("Ankit", "Hello", "Cashier" );
        int registeredCustomer = db.insertNewUser("Antriksh", "Hello", "Registered Customer" );
        //int guest = db.insertNewUser("Nemo", "Hello", "g");

        db.closeConn();
        System.out.println(owner);

        assertEquals(0, owner);
        assertEquals( 0, seller);
        assertEquals( 0, cashier);
        assertEquals( 0,registeredCustomer);
        // Test to see what happens when the userName is longer then 15 char.

        db.openConn();
        String userName = "SULAVMALALISAMAZING12345";
        int result1 = db.insertNewUser("SULAVMALALISAMAZING12345", "Hello","0");
        db.closeConn();

        assertEquals(-1, result1);


        // Test to see what happens when the password is longer than 20 chars.

        db.openConn();
        int result2 = db.insertNewUser("Smal", "SOFT2412ISANAMAZINGTUTORTHISISGREAT", "O");
        db.closeConn();

        assertEquals(-1, result2);

        // Inserting a userName that is already in the db. Should not be allowed.
        db.openConn();
        int result4 = db.insertNewUser("Suli","Yelloo", "O");
        db.closeConn();
        assertEquals(-1, result4);

        // checking that there is already a guest account in the database.
        // trying to see if I can input a new user by the name of guest.
        db.openConn();
        int insertGuest = db.insertNewUser("guest", "guest", "g");
        db.closeConn();

        assertEquals(-1, insertGuest);


    }

    @Test
    void testCheckRole(){

        // This is where you should check role.

        // Setting up the database, using dummy accounts.

        // inserting dummy data.


        db.openConn();
        int owner = db.insertNewUser("Suli", "Hello","Owner");
        int seller = db.insertNewUser("Udit", "Hello", "Seller" );
        int cashier = db.insertNewUser("Ankit", "Hello", "Cashier" );
        int registeredCustomer = db.insertNewUser("Antriksh", "Hello", "Registered Customer" );
        //int guest = db.insertNewUser("Nemo", "Hello", "g");

        db.closeConn();
        System.out.println(owner);

        // checking roles of the inserted functions.

        db.openConn();
        boolean ownerCheck = db.checkRole("Suli", "Owner");
        boolean sellerCheck = db.checkRole("Udit", "Seller" );
        boolean cashierCheck = db.checkRole("Ankit", "Cashier" );
        boolean registeredCustomerCheck = db.checkRole("Antriksh", "Registered Customer" );
        db.closeConn();

        assertTrue(ownerCheck);
        assertTrue(sellerCheck);
        assertTrue(cashierCheck);
        assertTrue(registeredCustomerCheck);

        // checking if it can return false.
        db.openConn();
        boolean onwerFalseCheck = db.checkRole("Udit", "Owner");
        boolean cashierFalseCheck = db.checkRole("Antriksh", "Cashier");
        boolean registerFalseCheck = db.checkRole("Ankit", "Registered Customer");
        db.closeConn();

        assertFalse(onwerFalseCheck);
        assertFalse(cashierFalseCheck);
        assertFalse(registerFalseCheck);

        // checking fot guest, name user that do no exist.

        db.openConn();
        boolean guestCheck = db.checkRole("guest", "Guest");
        boolean unknownUserCheck = db.checkRole("Sam", "Registered Customer");
        db.openConn();

        assertTrue(guestCheck);
        assertFalse(unknownUserCheck);



    }
}
