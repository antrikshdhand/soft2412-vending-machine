package VendingMachine;

public class App {
    /**
     * This method gets the user a lovely greeting.
     * @return "Hello World!"
     */
    public String getGreeting() {
        return "Hello Worrld!";
    }

    public static void main(String[] args) {

        Database db = new Database();

        /// Example Query ///
        
        db.openConn();
        if (db.dropAllTables() == 0) {
            System.out.println("App.Java - Dropped All Tables");
        }
        db.closeConn();

        /// Example Query ///

        System.out.println(new App().getGreeting());
    }
}