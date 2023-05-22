package za.co.wethinkcode.servertests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.server.robotLab.AbstractBot;
import za.co.wethinkcode.server.robotLab.BasicBot;
import za.co.wethinkcode.server.robotLab.SniperBot;
import za.co.wethinkcode.server.world.Direction;
import za.co.wethinkcode.server.world.Position;
import za.co.wethinkcode.server.world.WORLD;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the AbstractBot class.
 */
public class AbstractBotTest {
    private AbstractBot bot;
    private WORLD world;

    /**
     * Set up the test environment before each test case.
     */
    @BeforeEach
    public void setUp() {
        world = new WORLD();
        bot = AbstractBot.make(world, "basic", "TestBot", 5, 5);
    }

    /**
     * Test the initialization of the bot.
     */
    @Test
    public void testInitialization() {
        assertEquals("NORMAL", bot.getStatus());
        assertNotNull(bot.getRobotName());
        assertNotNull(bot.getCurrentDirection());
        assertNotNull(bot.getCurrentPosition());
        assertEquals(5, bot.getVisibility());
        assertEquals(5, bot.getReload());
        assertEquals(5, bot.getRepair());
        assertEquals(5, bot.getShots());
        assertEquals(5, bot.getShields());
    }

    /**
     * Test the update of the bot's position.
     * The position should change while the direction remains the same.
     */
    @Test
    public void testUpdatePosition_Success() {
        Position currentPosition = bot.getCurrentPosition();
        Direction currentDirection = bot.getCurrentDirection();

        bot.updatePosition(3);

        Position newPosition = bot.getCurrentPosition();

        assertNotEquals(currentPosition, newPosition);
        assertEquals(currentDirection, bot.getCurrentDirection());
    }

    /**
     * Test the update of the bot's position when obstructed or outside the world.
     * The position should not change, but the direction remains the same.
     */
    @Test
    public void testUpdatePosition_ObstructedAndOutsideWorld() {
        Position currentPosition = bot.getCurrentPosition();
        Direction currentDirection = bot.getCurrentDirection();

        bot.updatePosition(1);

        Position newPosition = bot.getCurrentPosition();

        assertNotEquals(currentPosition, bot.getCurrentPosition());
        assertEquals(newPosition, bot.getCurrentPosition());
        assertEquals(currentDirection, bot.getCurrentDirection());

        bot.updatePosition(100);

        Position newPosition1 = bot.getCurrentPosition();

        assertNotEquals(currentPosition, bot.getCurrentPosition());
        assertEquals(newPosition1, bot.getCurrentPosition());
        assertEquals(currentDirection, bot.getCurrentDirection());
    }

    /**
     * Test the update of the bot's direction by turning right.
     * The direction should change.
     */
    @Test
    public void testUpdateDirection_TurnRight() {
        Direction currentDirection = bot.getCurrentDirection();

        bot.updateDirection(true);

        Direction newDirection = bot.getCurrentDirection();

        assertNotEquals(currentDirection, newDirection);
    }

    /**
     * Test the update of the bot's direction by turning left.
     * The direction should change.
     */
    @Test
    public void testUpdateDirection_TurnLeft() {
        Direction currentDirection = bot.getCurrentDirection();

        bot.updateDirection(false);

        Direction newDirection = bot.getCurrentDirection();

        assertNotEquals(currentDirection, newDirection);
    }

    /**
     * Test that the make method returns an instance of BasicBot.
     */
    @Test
    void testMakeReturnsBasicBotInstance() {
        WORLD world = new WORLD();
        String model = "basic";
        String name = "Bot1";

        AbstractBot bot = AbstractBot.make(world, model, name, 5, 5);

        assertNotNull(bot);
        assertTrue(bot instanceof BasicBot);
    }

    /**
     * Test that the make method returns an instance of SniperBot.
     */
    @Test
    void testMakeReturnsSniperBotInstance() {
        WORLD world = new WORLD();
        String model = "sniper";
        String name = "Bot1";

        AbstractBot bot = AbstractBot.make(world, model, name, 5, 5);

        assertNotNull(bot);
        assertTrue(bot instanceof SniperBot);
    }
}
