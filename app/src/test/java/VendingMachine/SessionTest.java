package VendingMachine;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class SessionTest {

    Session sess;

    // setting up before each test
    @BeforeEach
    void setUp(){
        sess = new Session();
    }

    @AfterEach
    void tearDown(){
        sess = null;
    }

    // Setting set role.
    // Test through in the next sprint.
}
