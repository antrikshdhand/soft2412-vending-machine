package VendingMachine;

public class Session {

    private String userName = "Guest";
    private String role = "Guest";
    private boolean startedTransaction;
    private final int TICK_LIMIT = 120;
    private int tick;
    private boolean loggedIn;

    /**
     * Returns the user Name of the session
     * @return user Name ( String )
     */
    public String getUserName() {
        return this.userName;
    }


    /**
     * Function to return the role of current session user
     * @return role ( String )
     */
    public String getRole() {
        return this.role;
    }


    /**
     * Function to return the state of the transaction, has it been started or not.
     * @return whether the transaction has been started or not ( boolean )
     */
    public boolean getTransactionState() {
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
        if (role.equalsIgnoreCase("guest")) {
            this.setLoggedIn( false);
        }
        else this.setLoggedIn(true);
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
    public void resetSession() {
        userName = "Guest";
        role = "Guest";
    }


    public void setLoggedIn(boolean bool) {
        this.loggedIn = bool;
    }


    /**
     * Returns the logged in value
     * @return
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }


}
