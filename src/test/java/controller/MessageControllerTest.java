package controller;

import model.User;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.*;

public class MessageControllerTest {

    @Test
    void testSendMessage(){
        String input = "2\nHello\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        MessageController controller = new MessageController();
        User user = new User();
        user.setId(1);
        assertDoesNotThrow(() -> controller.sendMessage(user));
    }

    @Test
    void testViewMessages(){
        String input = "2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        MessageController controller = new MessageController();
        User user = new User();
        user.setId(1);
        assertDoesNotThrow(() -> controller.viewMessages(user));
    }

    @Test
    void testDeleteChat(){
        String input = "2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        MessageController controller = new MessageController();
        User user = new User();
        user.setId(1);
        assertDoesNotThrow(() -> controller.deleteChat(user));
    }
}