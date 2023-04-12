package za.co.wethinkcode.server.maze;

import za.co.wethinkcode.server.world.Obstacle;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMaze implements Maze{

    public List<Obstacle> getObstacles(){

        return new ArrayList<Obstacle>();
    }

}
