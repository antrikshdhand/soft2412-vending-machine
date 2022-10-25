package VendingMachine;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;

import java.sql.*;
import java.util.*;

//////////////////////// README ////////////////////////
/**
 * This is a basic instruction manual for this Vending-
 * Machine.
 * 
 * Everytime you would like to access any of these function
 * , please openConn(), then after you are done
 * closeConn().
 * 
 * Each function has comments explaining all of the
 * parameters and what the function does.
 */
///////////////////////////////////////////////////////

public class Database {

    private Connection dbConn = null;
    private Statement openStatement = null;

    /**
     * Constructor for this class.
     * Attempts to access the database.
     */
    public Database() {

        openConn();
        dropAllTables();
        addDummyItems();
        closeConn();

    }


    /**
     * Opens connection with a database.
     * To be called before any other function by calling class.
     *
     * @return 0 if successful and -1 if unsuccessful
     */
    public int openConn() {
        try {
            // create a database connection
            dbConn = DriverManager.getConnection("jdbc:sqlite:vending_machine.db");
            openStatement = dbConn.createStatement();
            openStatement.setQueryTimeout(30); // set timeout to 30 sec.

            openStatement.executeUpdate(
                    "create table if not exists roles (username varchar(15) primary key, password varchar(20), role varchar(20))");
            openStatement.executeUpdate(
                    "create table if not exists categories (category_id serial primary key, category_name varchar(20))");
            openStatement.executeUpdate(
                    "create table if not exists items (item_name varchar(20) primary key, category_name varchar(20))");
            openStatement.executeUpdate(
                    "create table if not exists recent (item_name varchar(20) primary key)");
            // There is already guest account in the db when it is created.
            openStatement.executeUpdate("insert into roles values('guest', 'guest', 'Guest')");

            // Owner - O
            // Cashier - C
            // Guest - G
            // Registered Customer - R
            //

            return 0;

        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
            return -1;

        }

    }


    /**
     * Closes the connection with the database.
     * To be called after you are done with database.
     *
     * @return 0 if successful and -1 if unsuccessful
     */
    public int closeConn() {

        try {
            if (dbConn != null)
                dbConn.close();
            this.dbConn = null;
            this.openStatement = null;
            return 0;

        } catch (SQLException e) {
            // connection close failed.
            System.err.println(e.getMessage());
            // this.dbConn = null;
            return -1;
        }
    }


    /**
     * Deletes all table data from the database (that we will be using).
     * To only be called if you want to delete all data.
     *
     * @return 0 if successful and -1 if unsuccessful
     */
    public int dropAllTables() {

        // Add error handling
        try {

            Statement statement = dbConn.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.
            statement.executeUpdate("drop table roles; drop table categories; drop table items; drop table recent");

            // error handelling
            openConn();

            return 0;

        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
            return -1;
        }

    }


    /**/
    //  This is dummy data that was put in to test some the code and to see if the gui is function well.
    public int addDummyItems() {

        try {
            Statement statement = dbConn.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.
            statement.executeUpdate(String.format("insert into items values('%s', '%s')", "Coke", "Drinks"));
            statement.executeUpdate(String.format("insert into items values('%s', '%s')", "Water", "Drinks"));
            statement.executeUpdate(String.format("insert into items values('%s', '%s')", "Juice", "Drinks"));
            statement.executeUpdate(String.format("insert into items values('%s', '%s')", "Dark", "Chocolate"));
            statement.executeUpdate(String.format("insert into items values('%s', '%s')", "Light", "Chocolate"));
            statement.executeUpdate(String.format("insert into items values('%s', '%s')", "Mars", "Candies"));
            statement.executeUpdate(String.format("insert into items values('%s', '%s')", "Salt", "Chips"));
            statement.executeUpdate(String.format("insert into items values('%s', '%s')", "Gummy", "Candies"));
            statement.executeUpdate(String.format("insert into items values('%s', '%s')", "Onion", "Chips"));

            statement.executeUpdate(String.format("insert into recent values('%s')", "Gummy"));
            statement.executeUpdate(String.format("insert into recent values('%s')", "Onion"));
            statement.executeUpdate(String.format("insert into recent values('%s')", "Juice"));
            
            statement.executeUpdate(String.format("insert into roles values('%s', '%s', '%s')", "owner", "ownerp", "Owner"));
            statement.executeUpdate(String.format("insert into roles values('%s', '%s', '%s')", "user1", "user1p", "Registered Customer"));
            statement.executeUpdate(String.format("insert into roles values('%s', '%s', '%s')", "user2", "user2p", "Registered Customer"));
            statement.executeUpdate(String.format("insert into roles values('%s', '%s', '%s')", "user3", "user3p", "Registered Customer"));


        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
            return -1;
        }


        return 0;
    }


    public ArrayList<String> queryRecent() {

        ArrayList<String> items = new ArrayList<>();

        try{
            ResultSet query = openStatement.executeQuery(String.format("select * from recent"));

            while(query.next()) {
                items.add(query.getString("item_name"));
            }


        } catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());

        }

        return items;

    }


    public ArrayList<String> queryCategory(String category) {

        ArrayList<String> items = new ArrayList<>();

        try{
            ResultSet query = openStatement.executeQuery(String.format("select item_name from items where category_name = '%s'", category));

            while(query.next()) {
                items.add(query.getString("item_name"));
            }


        } catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());

        }

        return items;


    }


    /**
     * Function to add a new User into the database.
     * 
     * @param userName userName of the new user
     * @param password password of the new user
     * @param role role of the new user
     * @return
     */
    public int insertNewUser(String userName, String password, String role) {

        // sqllite does not strictly enforce the varchar limits, so we have to test for ourselves.

        // Add error handling
        try {

            // sqllite does not strictly enforce the varchar limits, so we have to test for ourselves.
            if( userName.length() > 15 || password.length() > 20){
                throw new SQLException("userName or password too long");
            }

            Statement statement = dbConn.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.
            statement.executeUpdate(String.format("insert into roles values('%s', '%s', '%s')", userName, password,role.toUpperCase()));

        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
            System.out.println(userName);
            return -1;
        }
        return 0;

    }


    /**
     * Funciton to check if a certain user has the wanted role, of not. Returns true
     * if the user has the wanted role, false if it doesn't.
     * 
     * @param userName Username one wants to check the role of
     * @param role     the role you want to check the username has
     * @return true if successful, false if not successful
     */
    public boolean checkRole(String userName, String role) {

        try {
            ResultSet query = openStatement
                    .executeQuery(String.format("select role from roles where username  = '%s'", userName));
            if (query.getString("role").equalsIgnoreCase(role)) {
                return true;
            }

        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
            return false;
        }

        return false;
    }


    /**
     * Function to return the role of a specified user
     * 
     * @param userName
     * @return
     */
    public String getRole(String userName) {

        String sql = """
                SELECT role
                FROM Roles
                WHERE username = '%s';
                """;
        try {
            ResultSet query = openStatement.executeQuery(String.format(sql, userName));

            if (query.next()) {
                return query.getString("role");
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Error while querying for the role :(");
            return "Error";
        }
    }


    /**
     * Function to validate username
     * 
     * @param userName username of the user
     * @return 0 if successful, -1 if unsuccessful
     */
    public int validateUsername(String userName) {
        if (userName.length() > 15) {
            return -1;
        }

        String sql = """
                SELECT *
                FROM Roles
                WHERE username = '%s';
                """;

        try {
            ResultSet query = openStatement.executeQuery(String.format(sql, userName));

            if (query.next()) {
                return 0;
            } else {
                return -1;
            }

        } catch (SQLException e) {
            System.out.println("Error while querying username :(");
            return -1;
        }
    }


    /**
     * Login function
     * 
     * @param username username of the user
     * @param password password of the user
     * @return 0 if successful, -1 if unsuccessful
     */
    public int login(String username, String password) {
        String sql = """
                SELECT *
                FROM Roles
                WHERE username = '%s' AND password = '%s';
                """;
        try {
            ResultSet query = openStatement.executeQuery(String.format(sql, username, password));
            
            if (query.next()) {
                return 0;
            } else {
                return -1;
            }
        } catch (SQLException e) {
            System.out.println("Error while querying for user :(");
            return -1;
        }
    }

}