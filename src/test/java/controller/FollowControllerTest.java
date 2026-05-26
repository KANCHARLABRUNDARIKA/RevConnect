package controller;

import model.User;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.*;

public class FollowControllerTest {

    @Test
    void testFollowUser(){
        String input = "2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        FollowController controller = new FollowController();
        User user = new User();
        user.setId(1);
        assertDoesNotThrow(() -> controller.followUser(user));
    }

    @Test
    void testUnfollowUser(){
        String input = "2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        FollowController controller = new FollowController();
        User user = new User();
        user.setId(1);
        assertDoesNotThrow(() -> controller.unfollowUser(user));
    }

    @Test
    void testViewFollowers(){
        FollowController controller = new FollowController();
        User user = new User();
        user.setId(1);
        assertDoesNotThrow(() -> controller.viewFollowers(user));
    }

    @Test
    void testViewFollowing(){
        FollowController controller = new FollowController();
        User user = new User();
        user.setId(1);
        assertDoesNotThrow(() -> controller.viewFollowing(user));
    }
}