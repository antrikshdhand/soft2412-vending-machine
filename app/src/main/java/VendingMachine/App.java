package VendingMachine;

public class App {
    /**
     * This method gets the user a lovely greeting.
     * @return "Hello World!"
     */
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
    }
}