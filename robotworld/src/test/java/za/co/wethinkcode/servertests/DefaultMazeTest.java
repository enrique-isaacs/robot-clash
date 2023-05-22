package za.co.wethinkcode.servertests;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.server.maze.DefaultMaze;
import za.co.wethinkcode.server.world.Obstacle;
import za.co.wethinkcode.server.world.SquareObstacle;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the DefaultMaze class.
 */
class DefaultMazeTest {

    /**
     * Test that the default maze has one obstacle.
     */
    @Test
    void testDefaultMazeHasOneObstacle() {
        DefaultMaze maze = new DefaultMaze();
        List<Obstacle> obstacles = maze.getObstacles();

        assertEquals(1, obstacles.size());
    }

    /**
     * Test the position of the obstacle in the default maze.
     */
    @Test
    void testDefaultMazeObstaclePosition() {
        DefaultMaze maze = new DefaultMaze();
        List<Obstacle> obstacles = maze.getObstacles();
        Obstacle obstacle = obstacles.get(0);

        assertTrue(obstacle instanceof SquareObstacle);
        assertEquals(0, obstacle.getBottomLeftX());
        assertEquals(0, obstacle.getBottomLeftY());
    }
}
