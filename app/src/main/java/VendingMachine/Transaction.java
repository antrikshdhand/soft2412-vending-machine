package VendingMachine;

import java.util.HashMap;

public class Transaction {

    private HashMap<String, Integer> items = new HashMap<>();
    private double total = 0.00;

    public boolean addItem(String item) {
        if (items.containsKey(item)) {
            items.put(item, items.get(item) + 1);
        }
        else {
            items.put(item, 1);
        }
        return true;
    }

    public void addToTotal(double n) {
        total += n;
    }

    public HashMap<String, Integer> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public void removeItem(String item) {
        if (items.containsKey(item)) {
            int n = items.get(item) - 1;
            if (n <= 0) {
                items.remove(item);
            } else {
                items.put(item, n);
            }

        }


    }
}
