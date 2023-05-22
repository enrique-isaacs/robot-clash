package za.co.wethinkcode.servertests;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.server.world.Position;
import za.co.wethinkcode.server.world.SquareObstacle;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the SquareObstacle class.
 */
class SquareObstacleTest {

    /**
     * Test the dimensions of the obstacle.
     */
    @Test
    void testObstacleDimensions() {
        SquareObstacle obstacle = new SquareObstacle(1, 1);
        assertEquals(1, obstacle.getBottomLeftX());
        assertEquals(1, obstacle.getBottomLeftY());
        assertEquals(5, obstacle.getSize());
    }

    /**
     * Test if the obstacle blocks a specific position.
     */
    @Test
    void testBlocksPosition() {
        SquareObstacle obstacle = new SquareObstacle(1, 1);
        assertTrue(obstacle.blocksPosition(new Position(1, 1)));
        assertTrue(obstacle.blocksPosition(new Position(5, 1)));
        assertTrue(obstacle.blocksPosition(new Position(1, 5)));
        assertFalse(obstacle.blocksPosition(new Position(0, 5)));
        assertFalse(obstacle.blocksPosition(new Position(6, 5)));
    }

    /**
     * Test if the obstacle blocks a path between two positions.
     */
    @Test
    void testBlocksPath() {
        SquareObstacle obstacle = new SquareObstacle(1, 1);
        assertTrue(obstacle.blocksPath(new Position(1, 0), new Position(1, 50)));
        assertTrue(obstacle.blocksPath(new Position(2, -10), new Position(2, 100)));
        assertTrue(obstacle.blocksPath(new Position(-10, 5), new Position(20, 5)));
        assertFalse(obstacle.blocksPath(new Position(0, 1), new Position(0, 100)));
        assertFalse(obstacle.blocksPath(new Position(1, 6), new Position(1, 100)));
    }
}
