package controller;

import model.User;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.*;

public class SearchControllerTest {

    @Test
    void testSearchUsers(){
        String input = "kumar\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        SearchController controller = new SearchController();
        User user = new User();
        user.setId(1);
        assertDoesNotThrow(() -> controller.searchUsers(user));
    }
}