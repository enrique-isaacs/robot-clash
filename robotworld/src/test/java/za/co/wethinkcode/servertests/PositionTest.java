package za.co.wethinkcode.servertests;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.server.world.Position;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Position class.
 */
class PositionTest {

    /**
     * Test getting the X and Y coordinates of a position.
     */
    @Test
    void testGetXAndY() {
        Position p = new Position(10, 20);
        assertEquals(10, p.getX());
        assertEquals(20, p.getY());
    }

    /**
     * Test the equality of two positions.
     */
    @Test
    void testEquals() {
        assertEquals(new Position(-44, 63), new Position(-44, 63));
        assertNotEquals(new Position(-44, 63), new Position(0, 63));
        assertNotEquals(new Position(-44, 63), new Position(-44, 0));
        assertNotEquals(new Position(-44, 63), new Position(0, 0));
    }

    /**
     * Test if a position is within a given range.
     */
    @Test
    void testIsIn() {
        Position topLeft = new Position(-20, 20);
        Position bottomRight = new Position(20, -20);
        assertTrue(new Position(10, 10).isIn(topLeft, bottomRight), "Should be inside");
        assertFalse(new Position(10, 30).isIn(topLeft, bottomRight), "Should be beyond top boundary");
        assertFalse(new Position(10, -30).isIn(topLeft, bottomRight), "Should be beyond bottom boundary");
        assertFalse(new Position(30, 10).isIn(topLeft, bottomRight), "Should be beyond right boundary");
        assertFalse(new Position(-30, 10).isIn(topLeft, bottomRight), "Should be beyond left boundary");
    }
}
