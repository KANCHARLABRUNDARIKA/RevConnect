package controller;

import model.User;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.*;

public class LikeControllerTest {

    @Test
    void testLikePost(){
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        LikeController controller = new LikeController();
        User user = new User();
        user.setId(1);
        assertDoesNotThrow(() -> controller.likePost(user));
    }

    @Test
    void testUnlikePost(){
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        LikeController controller = new LikeController();
        User user = new User();
        user.setId(1);
        assertDoesNotThrow(() -> controller.unlikePost(user));
    }
}