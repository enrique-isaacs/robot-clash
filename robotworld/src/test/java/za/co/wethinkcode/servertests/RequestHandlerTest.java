package za.co.wethinkcode.servertests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import za.co.wethinkcode.server.serverInterface.RequestHandler;

/**
 * Unit tests for the RequestHandler class.
 */
public class RequestHandlerTest {

    /**
     * Test a valid command request.
     */
    @Test
    public void testValidCommand() {
        String request = "{\"robot\":\"robot1\", \"command\":\"forward\", \"arguments\":[10]}";
        RequestHandler handler = new RequestHandler(request);
        assertTrue(handler.requestIsValid());
    }

    /**
     * Test an unsupported command request.
     */
    @Test
    public void testUnsupportedCommand() {
        String request = "{\"robot\":\"robot1\", \"command\":\"invalid\", \"arguments\":[10]}";
        RequestHandler handler = new RequestHandler(request);
        assertFalse(handler.requestIsValid());
    }

    /**
     * Test a request with valid arguments.
     */
    @Test
    public void testValidArguments() {
        String request = "{\"robot\":\"robot1\", \"command\":\"turn\", \"arguments\":[\"left\"]}";
        RequestHandler handler = new RequestHandler(request);
        assertTrue(handler.requestIsValid());
    }
}
