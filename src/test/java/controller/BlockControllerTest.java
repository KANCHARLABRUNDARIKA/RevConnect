package controller;

import model.User;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.*;
public class BlockControllerTest {

    @Test
    void testBlock(){
        String input = "2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        BlockController controller = new BlockController();
        User user = new User();
        user.setId(1);
        assertDoesNotThrow(() -> controller.block(user));
    }

    @Test
    void testViewBlocked(){
        BlockController controller = new BlockController();
        User user = new User();
        user.setId(1);
        assertDoesNotThrow(() -> controller.viewBlocked(user));
    }
}