package controller;

import model.User;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.*;

public class ConnectionControllerTest {

    @Test
    void testSendRequest(){
        String input = "2\n";
        System.setIn(
                new ByteArrayInputStream(input.getBytes())
        );
        ConnectionController controller = new ConnectionController();
        User user = new User();
        user.setId(1);
        assertDoesNotThrow(
                () -> controller.sendRequest(user)
        );
    }


    @Test
    void testViewRequests(){

        ConnectionController controller =
                new ConnectionController();

        User user = new User();

        user.setId(1);

        assertDoesNotThrow(
                () -> controller.viewRequests(user)
        );
    }


    @Test
    void testAccept(){
        String input = "1\n";
        System.setIn(
                new ByteArrayInputStream(input.getBytes())
        );
        ConnectionController controller =
                new ConnectionController();
        User user = new User();
        user.setId(1);
        assertDoesNotThrow(
                () -> controller.accept(user)
        );
    }


    @Test
    void testReject(){
        String input = "1\n";
        System.setIn(
                new ByteArrayInputStream(input.getBytes())
        );
        ConnectionController controller =
                new ConnectionController();
        User user = new User();
        user.setId(1);
        assertDoesNotThrow(
                () -> controller.reject(user)
        );
    }
}