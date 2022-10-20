package VendingMachine;

public class Session {

    private String userName;

    private String role;

    private boolean startedTransaction;

    private final int tickLimit = 120;

    public String getUserName() {
        return userName;
    }

    public String getRole() {
        return role;
    }

    public boolean getTransactionState(){
        return this.startedTransaction;
    }

    public void setStartedTransaction(boolean startedTransaction) {
        this.startedTransaction = startedTransaction;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
