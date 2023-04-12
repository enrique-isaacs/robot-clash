package za.co.wethinkcode.server.maze;

import za.co.wethinkcode.server.world.Position;
import za.co.wethinkcode.server.world.Obstacle;
import za.co.wethinkcode.server.world.SquareObstacle;

import java.util.ArrayList;
import java.util.List;

public class DefaultMaze extends BaseMaze{

    @Override
    public List<Obstacle> getObstacles(){
        return new ArrayList<>(List.of(new SquareObstacle(1, 1)));
    }


    @Override
    public boolean blocksPath(Position a, Position b) {
        return false;
    }
}
