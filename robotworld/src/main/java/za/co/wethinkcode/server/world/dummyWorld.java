package za.co.wethinkcode.server.world;


import za.co.wethinkcode.server.maze.DefaultMaze;
import za.co.wethinkcode.server.maze.Maze;

import java.util.List;

public class dummyWorld extends AbstractWorld{

    Maze maze;

    @Override
    public void showObstacles() {

        if(this.maze instanceof DefaultMaze){

        }
        else{
            System.out.println("There are some obstacles:");
            for (Obstacle ob: getObstacles()){
                System.out.println("- At position "+ob.getBottomLeftX()+","+ob.getBottomLeftY()+
                        " (to "+ (ob.getBottomLeftX()+4)+","+(ob.getBottomLeftY()+4)+")");
            }
        }

    }

    public dummyWorld(Maze maze){
        super(maze);
    }
}
