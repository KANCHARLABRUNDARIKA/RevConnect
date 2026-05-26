package controller;

import model.User;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.*;

public class ProfileControllerTest {

    @Test
    void testViewProfile(){
        ProfileController controller = new ProfileController();
        User user = new User();
        user.setId(1);
        assertDoesNotThrow(() -> controller.viewProfile(user));
    }

    @Test
    void testSetPrivacy(){
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ProfileController controller = new ProfileController();
        User user = new User();
        user.setId(1);
        assertDoesNotThrow(() -> controller.setPrivacy(user));
    }
}