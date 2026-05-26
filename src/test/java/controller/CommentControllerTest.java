package controller;

import model.User;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.*;

public class CommentControllerTest {

    @Test
    void testAddComment(){
        String input = "1\nNice post\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        CommentController controller = new CommentController();
        User user = new User();
        user.setId(1);
        assertDoesNotThrow(() -> controller.addComment(user));
    }

    @Test
    void testViewComments(){
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        CommentController controller = new CommentController();
        assertDoesNotThrow(() -> controller.viewComments());
    }

    @Test
    void testDeleteComment(){
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        CommentController controller = new CommentController();
        assertDoesNotThrow(() -> controller.deleteComment());
    }
}