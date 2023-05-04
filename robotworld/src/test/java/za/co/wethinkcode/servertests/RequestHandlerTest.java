package za.co.wethinkcode.servertests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import za.co.wethinkcode.server.serverInterface.RequestHandler;

public class RequestHandlerTest {

    @Test
    public void testValidCommand() {
        String request = "{\"robot\":\"robot1\", \"command\":\"forward\", \"arguments\":[\"10\"]}";
        RequestHandler handler = new RequestHandler(request);
        String[] result = handler.validRequest();
        assertArrayEquals(new String[] {"ok"}, result);
    }

    @Test
    public void testUnsupportedCommand() {
        String request = "{\"robot\":\"robot1\", \"command\":\"invalid\", \"arguments\":[\"10\"]}";
        RequestHandler handler = new RequestHandler(request);
        String[] result = handler.validRequest();
        assertArrayEquals(new String[] {"error", "Unsupported command"}, result);
    }

    @Test
    public void testInvalidArguments() {
        String request = "{\"robot\":\"robot1\", \"command\":\"forward\", \"arguments\":[\"invalid\"]}";
        RequestHandler handler = new RequestHandler(request);
        String[] result = handler.validRequest();
        assertArrayEquals(new String[] {"error", "Could not parse arguments"}, result);
    }

    @Test
    public void testValidArguments() {
        String request = "{\"robot\":\"robot1\", \"command\":\"turn\", \"arguments\":[\"left\"]}";
        RequestHandler handler = new RequestHandler(request);
        String[] result = handler.validRequest();
        assertArrayEquals(new String[] {"ok"}, result);
    }

}
