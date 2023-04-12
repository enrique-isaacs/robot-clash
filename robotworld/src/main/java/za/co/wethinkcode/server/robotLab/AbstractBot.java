package za.co.wethinkcode.server.robotLab;

import za.co.wethinkcode.server.commands.Command;
import za.co.wethinkcode.server.maze.DefaultMaze;
import za.co.wethinkcode.server.world.IWorld;
import za.co.wethinkcode.server.world.dummyWorld;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractBot {

    private String status;
    private String name;

    private IWorld world;


    public AbstractBot(String name) {
        this.name = name;
        this.status = "Ready";
        this.world = new dummyWorld(new DefaultMaze());
    }

    public AbstractBot(String name, IWorld world){
        this.name = name;
        this.status = "Ready";
        this.world = world;
    }

    public IWorld getWorld(){
        return this.world;
    }

    public String getStatus() {
        return this.status;
    }

    public String getRobotName(){
        return this.name;
    }

    public boolean handleCommand(Command command) {
        return command.execute(this);
    }


    @Override
    public String toString() {
        return "[" + this.getWorld().getPosition().getX() + "," + this.getWorld().getPosition().getY() + "] "
                + this.name + "> " + this.status;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }
}