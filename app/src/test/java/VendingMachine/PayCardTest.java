package VendingMachine;

import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PayCardTest {

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public long getRandomLong() {
        long leftLimit = 1000000000000000L;
        long rightLimit = 9999999999999999L;
        long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
        return generatedLong;
    }

    // Test for valid card number
    @Test
    void testCardNumber() {
        for (int i = 0; i <= 100000; i++) {
            long cardNumber = getRandomLong();
            assertTrue(PayCard.checkCardNumber(Long.toString(cardNumber)));
        }
    }

    // Test for valid CVV
    @Test
    void testCVV() {
        for (int i = 0; i <= 100; i++) {
            int CVV = getRandomNumber(100, 999);
            assertTrue(PayCard.checkCVV(Integer.toString(CVV)));
        }
    }

}
