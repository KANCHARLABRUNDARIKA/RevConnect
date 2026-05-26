package controller;

import model.User;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.*;

public class NotificationControllerTest {

    @Test
    void testViewNotifications(){
        String input = "no\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        NotificationController controller = new NotificationController();
        User user = new User();
        user.setId(1);
        assertDoesNotThrow(() -> controller.viewNotifications(user));
    }
}