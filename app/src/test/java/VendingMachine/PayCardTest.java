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

    // // Test for valid card number
    // @Test
    // void testCardNumber() {

    //     for (int i = 0; i <= 1000; i++) {

    //         int cardNumber = getRandomNumber(10000000, 99999999);

    //         assertTrue(PayCard.checkCardNumber(Integer.toString(cardNumber)));

    //     }

    // }

    // Test for valid CVV
    @Test
    void testCVV() {
        for (int i = 0; i <= 100; i++) {
            int CVV = getRandomNumber(100, 999);
            assertTrue(PayCard.checkCVV(Integer.toString(CVV)));
        }
    }

}
