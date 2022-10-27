package VendingMachine;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatabaseTest {
    private Database db;

    // You want to open a new API to the database
    // and drop all tables, you are working with a empty relations for each test.
    @BeforeEach
    void setUp(){
        db = new Database();
        db.openConn();

        // adding in temp Dummy Data
        db.dropAllTables();
        db.initialiseSchema();
        db.addDummyItems();
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


    // simple test for openConn()
    @Test
    void openConnTest(){
        int value = db.openConn();
        db.closeConn();
        //System.out.println(value);

        //temp giving exception as you are adding guest multiple times.
        // assertEquals(-1,value);

    }


    // simple test for closeConn()
    @Test
    void closeConnTest(){
         db.openConn();
        int value = db.closeConn();
        assertEquals(0,value);
    }

    // simple test for dropAllTables()
    @Test
    void dropAllTablesTest(){
        db.openConn();
        int value = db.dropAllTables();
        db.closeConn();

        assertEquals(0, value);
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

        assertEquals(0, owner);

        System.out.println(seller);
        assertEquals( 0, seller);
        assertEquals( 0, cashier);
        assertEquals( 0,registeredCustomer);
        // Test to see what happens when the userName is longer then 15 char.

        db.openConn();
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
        //System.out.println(owner);

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

    // Temp test to see if the dummy data works.
    @Test
    public void simpleDummyTest(){
        db.openConn();
        int value = db.addDummyItems();
        db.closeConn();

        assertEquals(-1, value);
    }

    // Few advanced test for adding the Dummy Data.
    @Test
    void advancedDummyTest1(){


        db.openConn();
        boolean value = db.checkRole("owner", "Owner");
        db.closeConn();

        assertTrue(value);
    }

    @Test
    void advancedDummyTest2(){


        db.openConn();
        boolean value = db.checkRole("user1", "Registered Customer");
        db.closeConn();

        assertTrue(value);
    }

    // Simple test to see if query recent works properly.
    @Test
    void simpleQueryRecent(){

        db.openConn();
        ArrayList<String> q = db.queryRecent();
        db.closeConn();

        assertNotNull(q);
    }

    // more advanced tests for query recent
    @Test
    void advancedQueryRecetn1(){
        db.openConn();
        ArrayList<String> q = db.queryRecent();
        db.closeConn();

        // System.out.println(q.get(0));
        assertTrue(q.get(0).equalsIgnoreCase("gummy"));
    }

    @Test
    void advancedQueryRecetn2(){
        db.openConn();
        ArrayList<String> q = db.queryRecent();
        db.closeConn();


        assertTrue(q.get(2).equalsIgnoreCase("juice"));
    }

    // simple test to see if the queryCategory function is working.
    @Test
    void simpleQueryCategory(){
        db.openConn();
        ArrayList<String> c = db.queryCategory("Drinks");
        db.closeConn();


        assertNotNull(c);
    }

    // Advanced Tests to for queryCategory.
    @Test
    void advancedQueryCategoryDrinks(){
        db.openConn();
        ArrayList<String> c = db.queryCategory("Drinks");
        db.closeConn();

        assertTrue(c.get(0).equalsIgnoreCase("Coke"));
        assertTrue(c.get(2).equalsIgnoreCase("juice"));
        assertFalse(c.get(1).equalsIgnoreCase("dark"));

    }

    @Test
    void advancedQueryCategoryChoco() {
        db.openConn();
        ArrayList<String> c = db.queryCategory("Chocolate");
        db.closeConn();

        assertTrue(c.get(0).equalsIgnoreCase("dark"));
        assertFalse(c.get(1).equalsIgnoreCase("salt"));
    }

    @Test
    void advancedQueryCategoryCandies(){
        db.openConn();
        ArrayList<String> c = db.queryCategory("Candies");
        db.closeConn();

        assertTrue(c.get(0).equalsIgnoreCase("mars"));
        assertFalse(c.get(1).equalsIgnoreCase("Onion"));
    }

    @Test
    void advancedQueryCategoryChips(){
        db.openConn();
        ArrayList<String> c = db.queryCategory("Chips");
        db.closeConn();

        assertTrue(c.get(0).equalsIgnoreCase("salt"));
        assertTrue(c.get(1).equalsIgnoreCase("onion"));
    }

    // Simple test for getRole()
    @Test
    void simpleGetRole(){
        db.openConn();
        String user1 = db.getRole("user1");
        db.closeConn();

        assertNotNull(user1);
    }


    // Advanced test for getRole()
    @Test
    void advancedGetRole(){
        db.openConn();
        String user1 = db.getRole("user1");
        db.closeConn();

        assertTrue(user1.equalsIgnoreCase("Registered Customer"));
    }

    @Test
    void advancedGetRole1(){
        db.openConn();
        String user1 = db.getRole("owner");
        db.closeConn();

        assertTrue(user1.equalsIgnoreCase("owner"));
    }

    @Test
    void advancedGetRole2(){
        db.openConn();
        String user1 = db.getRole("NotInDb");
        db.closeConn();

        // not in db should return null
        assertNull(user1);

    }

    // simple Test for validateUsername()
    @Test
    void simpleValidateUsername(){
        db.openConn();
        int value = db.validateUsername("Helll00");
        db.closeConn();

        assertNotNull(value);
    }

    // advanced Test for validateUsername()
    @Test
    void AdvancedValidateUsername(){
        db.openConn();
        int value1 = db.validateUsername("HHHHHEEEEEELLLLOOOOOLLLE");
        db.closeConn();

        assertEquals(-1, value1);
    }

    @Test
    void AdvancedValidateUsername1(){
        db.openConn();
        int value = db.validateUsername("user1");
        db.closeConn();

        assertEquals( 0, value);
    }

    // simple test login
    @Test
    void simpleLogin(){
        db.openConn();
        int value = db.login("user1", "user1p");
        db.closeConn();

        assertNotNull(value);
    }

    // advanced testing for login function
    @Test
    void AdvancedLogin1(){
        db.openConn();
        int value = db.login("user1", "user1p");
        db.closeConn();

        assertEquals(0, value);
    }

    @Test
    void AdvancedLogin2(){
        db.openConn();
        int value = db.login("owner", "ownerp");
        db.closeConn();

        assertEquals(0, value);
    }


    @Test
    void AdvancedLogin3(){
        db.openConn();
        int value = db.login("owner", "onwerp1111");
        db.closeConn();

        assertEquals(-1, value);
    }

    @Test
    void AdvancedLogin4(){
        db.openConn();
        int value = db.login("owner555", "onwerp1111");
        db.closeConn();

        assertEquals(-1, value);
    }


    @Test
    void AdvancedLogin5(){
        db.openConn();
        int value = db.login("owner555", "onwerp");
        db.closeConn();

        assertEquals(-1, value);
    }

}
