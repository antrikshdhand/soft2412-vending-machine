package VendingMachine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.HashMap;

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
            openStatement.setQueryTimeout(30);  // set timeout to 30 sec.

            openStatement.executeUpdate("create table if not exists roles (username varchar(15) primary key, password varchar(20), role char(1))");
            openStatement.executeUpdate("create table if not exists categories (category_id serial, category_name varchar(20))");
            openStatement.executeUpdate("create table if not exists items (item_id serial, category_id int references categories(category_id))");  

            // Owner - O
            // Cashier - C
            // Guest - G
            // Registered Customer - R
            // 

            return 0;

        } catch(SQLException e) {
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
            if(dbConn != null) dbConn.close();
            this.dbConn = null;
            this.openStatement = null;
            return 0;

        } catch(SQLException e) {
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
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("drop table roles; drop table categories; drop table items");

            //error handelling
            openConn();

            return 0;

        } catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
            return -1;
        }

    }


    /// TO DO ADD YOUR FUNCTIONS HERE



    ///////////// EXAMPLES /////////////
    // An example from the previous assignment
    // Have a look at how you insert values

    /**
     * Adds a currency to the currency table.
     * Need to call openConn before this function.
     *
     * @param exchCode the currency code for the currency as a string: e.g. "AUD"
     * @param currName the currency name of the code: e.g. "Australian Dollar"
     * @return 0 if successful and -1 if unsuccessful
     */
    public int addItem(String exchCode, String currName) {

        // Add error handling
        try {

            Statement statement = dbConn.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate(String.format("insert into currency values('%s', '%s')", exchCode, currName));

        } catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
            return -1;
        }
        return 0;

    }


    // An example from the previous assignment
    // Have a look at how you search values

    /**
     * Given a currency code returns the currency name see {@link #addCurrency(String, String) addCurrency}.
     *
     * @param exchCode the currency code of a currency to be queried
     * @return the currency name
     */
    public String getCurrName(String exchCode) {

        try{
            ResultSet query = openStatement.executeQuery(String.format("select currency_name from currency where currency_code = '%s'", exchCode));

            if(query.next()) {
                return query.getString("currency_name");

            } else {
                return null;

            }

        } catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());

        }

        return null;

    }

    //////////////////////
}