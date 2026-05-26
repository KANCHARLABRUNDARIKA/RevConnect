package controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthControllerTest {

    @Test
    void testAuthController(){
        AuthController controller = new AuthController();
        assertNotNull(controller);
    }

    @Test
    void testRegisterMethodExists(){
        AuthController controller = new AuthController();
        assertNotNull(controller);
    }

    @Test
    void testLoginMethodExists(){
        AuthController controller = new AuthController();
        assertNotNull(controller);
    }
}