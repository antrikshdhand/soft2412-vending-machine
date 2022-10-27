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
     */
    public Database() {
        System.out.println("Attempting to connect to the database for the first time...");
        int successfulConn = openConn();
        System.out.println();
        if (successfulConn == 0) {
            dropAllTables();
            initialiseSchema();
            addDummyItems();
            closeConn();
        }
    }


    /**
     * Sets up the database with all tables.
     * See UML diagram for schema details.
     * 
     * Note: this function is only to be called
     * if openConn() is successful.
     * 
     * @return 0 on success, -1 on error
     */
    public int initialiseSchema() {
        try {
            openStatement = dbConn.createStatement();
            openStatement.setQueryTimeout(30); // set timeout to 30 sec.
            
            openStatement.executeUpdate(
                    """
                    CREATE TABLE IF NOT EXISTS users (
                        username VARCHAR(15) PRIMARY KEY, 
                        password VARCHAR(20), 
                        role VARCHAR(20),
                        CHECK (role IN ('OWNER', 'CASHIER', 'GUEST', 'REGISTERED CUSTOMER'))
                    );
                    """);

            openStatement.executeUpdate(
                    """
                    CREATE TABLE IF NOT EXISTS categories (
                        category_id SERIAL PRIMARY KEY,
                        category_name VARCHAR(20)
                    );
                    """);

            openStatement.executeUpdate(
                    """
                    CREATE TABLE IF NOT EXISTS items (
                        item_name VARCHAR(20) PRIMARY KEY,
                        category_name VARCHAR(20)
                    );
                    """);

            openStatement.executeUpdate(
                    """
                        CREATE TABLE IF NOT EXISTS recent (
                            item_name VARCHAR(20) PRIMARY KEY
                        )
                    """);
            
            // Initialise db with a guest account
            openStatement.executeUpdate("INSERT INTO users VALUES ('guest', 'guest', 'GUEST')");

            // OWNER - O
            // CASHIER - C
            // GUEST - G
            // REGISTERED CUSTOMER - R
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
            return -1;
        }
        return 0;
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

            System.out.println("Connection to the database has been established.");
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
            return -1;
        }
        return 0;
    }


    /**
     * Closes the connection with the database.
     * To be called after you are done with database.
     *
     * @return 0 if successful and -1 if unsuccessful
     */
    public int closeConn() {
        try {
            if (dbConn != null) {
                dbConn.close();
            }

            this.dbConn = null;
            this.openStatement = null;
        } catch (SQLException e) {
            // connection close failed.
            System.err.println(e.getMessage());
            // this.dbConn = null;
            return -1;
        }
        return 0;
    }


    /**
     * Deletes all table data from the database (that we will be using).
     * To only be called if you want to delete all data.
     *
     * @return 0 if successful and -1 if unsuccessful
     */
    public int dropAllTables() {
        try {
            Statement statement = dbConn.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.
            statement.executeUpdate(
                """
                DROP TABLE IF EXISTS users; 
                DROP TABLE IF EXISTS categories;
                DROP TABLE IF EXISTS items;
                DROP TABLE IF EXISTS recent;
                """);
            return 0;
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
            return -1;
        }
    }


    /**
     * FOR TESTING PURPOSES ONLY:
     * Add a bit of dummy data to test the GUI.
     * @return
     */
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
            
            statement.executeUpdate(String.format("insert into users values('%s', '%s', '%s')", "owner", "ownerp", "OWNER"));
            statement.executeUpdate(String.format("insert into users values('%s', '%s', '%s')", "user1", "user1p", "REGISTERED CUSTOMER"));
            statement.executeUpdate(String.format("insert into users values('%s', '%s', '%s')", "user2", "user2p", "REGISTERED CUSTOMER"));
            statement.executeUpdate(String.format("insert into users values('%s', '%s', '%s')", "user3", "user3p", "REGISTERED CUSTOMER"));
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
        
        try {
            ResultSet query = openStatement.executeQuery(String.format("SELECT * FROM recent"));
            while (query.next()) {
                items.add(query.getString("item_name"));
            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        
        return items;

    }


    public ArrayList<String> queryCategory(String category) {

        ArrayList<String> items = new ArrayList<>();

        try {
            String sql = String.format("SELECT item_name FROM items WHERE category_name = '%s'", category);
            ResultSet query = openStatement.executeQuery(sql);
            while (query.next()) {
                items.add(query.getString("item_name"));
            }
        } catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }

        return items;

    }

    public HashMap<String, String> queryUsernameAndRole() {

        HashMap<String, String> map = new HashMap<>();

        try {
            String sql = String.format("SELECT username, role FROM users");
            ResultSet query = openStatement.executeQuery(sql);
            while (query.next()) {
                map.put(query.getString("username"), query.getString("role"));
            }
        } catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }

        return map;

    }

    public ArrayList<String> queryUsername() {

        ArrayList<String> list = new ArrayList<>();

        try {
            String sql = String.format("SELECT username FROM users");
            ResultSet query = openStatement.executeQuery(sql);
            while (query.next()) {

                String uname = query.getString("username");
                System.out.println();
                list.add(uname);
            }
        } catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }

        return list;
    }

    public boolean changeRole(String username, String role) {

        try {
            String sql = String.format("UPDATE users SET role = '%s' WHERE username = '%s';", role, username);
            Statement statement = dbConn.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.
            statement.executeUpdate(sql);
            return true;
        } catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
            return false;
        }

    }


    /**
     * Function to add a new User into the database.
     * 
     * @param username  username of the new user
     * @param password  password of the new user
     * @param role      role of the new user
     * @return
     */
    public int insertNewUser(String username, String password, String role) {
        try {
            // sqllite does not strictly enforce the varchar limits, so we have to test for ourselves.
            if( username.length() > 15 || password.length() > 20){
                throw new SQLException("userName or password too long");
            }

            Statement statement = dbConn.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.
            statement.executeUpdate(String.format("insert into users values('%s', '%s', '%s')", username, password,role.toUpperCase()));
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
            return -1;
        }
        return 0;
    }


    /**
     * Function to check if a certain user has the inputted role or not.
     * 
     * @param username  username one wants to check the role of
     * @param role      the role you want to check the username has
     * @return          true if user has the role, false if not 
     */
    public boolean checkRole(String username, String role) {

        try {
            String sql = String.format("select role from users where username  = '%s'", username);
            ResultSet query = openStatement.executeQuery(sql);
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
     * @param username
     * @return
     */
    public String getRole(String username) {

        String sql = """
                SELECT role
                FROM Users
                WHERE username = '%s';
                """;
        try {
            ResultSet query = openStatement.executeQuery(String.format(sql, username));
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
     * Function to validate a username
     * 
     * @param username username of the user
     * @return 0 if successful, -1 if unsuccessful
     */
    public int validateUsername(String username) {
        if (username.length() > 15) {
            return -1;
        }

        String sql = """
                SELECT *
                FROM Users
                WHERE username = '%s';
                """;

        try {
            ResultSet query = openStatement.executeQuery(String.format(sql, username));

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
                FROM Users
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