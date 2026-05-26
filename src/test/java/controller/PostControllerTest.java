package controller;

import model.User;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.*;

public class PostControllerTest {

    @Test
    void testCreatePost(){
        String input = "Hello\n#test\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        PostController controller = new PostController();
        User user = new User();
        user.setId(1);
        assertDoesNotThrow(() -> controller.createPost(user));
    }

    @Test
    void testViewFeed(){
        PostController controller = new PostController();
        assertDoesNotThrow(controller::viewFeed);
    }

    @Test
    void testUpdatePost(){
        String input = "1\nUpdated\n#tag\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        PostController controller = new PostController();
        User user = new User();
        user.setId(1);
        assertDoesNotThrow(() -> controller.updatePost(user));
    }
}