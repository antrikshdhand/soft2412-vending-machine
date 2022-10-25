package VendingMachine;

import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PayCardTest {

    // private SceneManager sceneManager = new SceneManager();
    // private PayCard payCard = new PayCard(sceneManager);

    // Test for valid card number
    @Test
    void testCardNumber(){

        Random rnd = new Random();

        for (int i = 0; i <= 100; i++) {
            int number = 10000000 + rnd.nextInt(90000000);
        
            assertTrue(payCard.checkCardNumber(Integer.toString(number)));
        }

    }

}
