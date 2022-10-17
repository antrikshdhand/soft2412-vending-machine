package VendingMachine;

public class VendingMachine extends Application {
    
    Database db = new Database();

    /// Example Query ///
    
    db.openConn();
    if (db.dropAllTables() == 0) {
        System.out.println("App.Java - Dropped All Tables");
    }
    db.closeConn();

    /// Example Query ///

}