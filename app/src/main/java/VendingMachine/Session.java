package VendingMachine;

public class Session {

    private String userName;
    private String role;
    private boolean startedTransaction;
    private final int tickLimit = 120;
    private boolean loggedIn;


    /**
     * Returns the user Name of the session
     * @return user Name ( String )
     */
    public String getUserName() {
        return userName;
    }


    /**
     * Function to return the role of current session user
     * @return role ( String )
     */
    public String getRole() {
        return role;
    }


    /**
     * Function to return the state of the transaction, has it been started or not.
     * @return whether the transaction has been started or not ( boolean )
     */
    public boolean getTransactionState(){
        return this.startedTransaction;
    }

    /**
     * Function to set the state of the transaction.
     * @param startedTransaction ( Boolean )
     */
    public void setStartedTransaction(boolean startedTransaction) {
        this.startedTransaction = startedTransaction;
    }


    /**
     * Function to set the role for a session.
     * @param role ( String )
     */
    public void setRole(String role) {
        this.role = role;
    }


    /**
     * Function to set the userName for the session.
     * @param userName ( String )
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }



    // todo, it is hardcoded atm.


    /**
     * Function to reset the role and user back to guest.
     */
    public void resetSession(){
        userName = "Guest";
        role = "G";
    }

    public void setLoggedIn(boolean b) {
        this.loggedIn = b;
    }
}
