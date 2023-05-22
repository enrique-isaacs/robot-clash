package za.co.wethinkcode.servertests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.server.serverInterface.Processor;
import za.co.wethinkcode.server.serverInterface.RequestHandler;
import za.co.wethinkcode.server.serverInterface.ResponseBuilder;
import za.co.wethinkcode.server.world.WORLD;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Processor class.
 */
public class ProcessorTest {
    private Processor processor;
    private ResponseBuilder responseBuilder;
    private WORLD world;

    @BeforeEach
    public void setUp() {
        world = new WORLD();
        responseBuilder = new ResponseBuilder(world);
        processor = new Processor(world, responseBuilder);
    }

    /**
     * Test the commandProcessor method with a successful launch command.
     */
    @Test
    public void testCommandProcessor_LaunchCommand_Success() {
        RequestHandler request = new RequestHandler("{\"robot\":\"TestBot\", \"command\":\"launch\", \"arguments\":[\"sniper\", 5, 5]}");

        boolean isCommandProcessed = processor.commandProcessor(request);

        assertTrue(isCommandProcessed);
        assertTrue(world.containsBot("TestBot"));
        assertEquals("OK", responseBuilder.getResponseStatus());
        assertNull(responseBuilder.getErrorMessage());
    }

    /**
     * Test the commandProcessor method with a launch command when there are already too many bots in the world.
     */
    @Test
    public void testCommandProcessor_LaunchCommand_TooManyBots() {
        world.addRobots("BasicBot", "Bot1", 5, 5);
        world.addRobots("BasicBot", "Bot2", 5, 5);
        RequestHandler request = new RequestHandler("{\"robot\":\"Bot2\", \"command\":\"launch\", \"arguments\":[\"sniper\"]}");

        boolean isCommandProcessed = processor.commandProcessor(request);

        assertTrue(isCommandProcessed);
        assertTrue(world.containsBot("Bot2"));
        assertEquals("ERROR", responseBuilder.getResponseStatus());
        assertEquals("Too many of you in this world", responseBuilder.getErrorMessage());
    }

    /**
     * Test the commandProcessor method with a command for an existing bot.
     */
    @Test
    public void testCommandProcessor_CommandWithExistingBot() {
        world.addRobots("BasicBot", "TestBot", 5, 5);
        RequestHandler request = new RequestHandler("{\"robot\":\"TestBot\", \"command\":\"forward\", \"arguments\":[10]}");

        boolean isCommandProcessed = processor.commandProcessor(request);

        assertTrue(isCommandProcessed);
    }

    /**
     * Test the commandProcessor method with a command for a nonexistent bot.
     */
    @Test
    public void testCommandProcessor_CommandWithNonexistentBot() {
        RequestHandler request = new RequestHandler("{\"robot\":\"TestBot\", \"command\":\"forward\", \"arguments\":[\"10\"]}");

        boolean isCommandProcessed = processor.commandProcessor(request);

        assertFalse(isCommandProcessed);
    }
}
