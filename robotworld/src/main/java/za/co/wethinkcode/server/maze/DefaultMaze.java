package za.co.wethinkcode.server.maze;

import za.co.wethinkcode.server.world.Position;
import za.co.wethinkcode.server.world.Obstacle;
import za.co.wethinkcode.server.world.SquareObstacle;

import java.util.ArrayList;
import java.util.List;

public class DefaultMaze extends BaseMaze {

    /**
     * Gets the list of obstacles in the maze.
     *
     * @return The list of obstacles.
     */
    @Override
    public List<Obstacle> getObstacles() {
        return new ArrayList<>(List.of(new SquareObstacle(1, 1)));
    }

    /**
     * Checks if the path between two positions is blocked by any obstacles.
     *
     * @param a The starting position.
     * @param b The destination position.
     * @return True if the path is blocked, false otherwise.
     */
    @Override
    public boolean blocksPath(Position a, Position b) {
        return false;
    }
}
