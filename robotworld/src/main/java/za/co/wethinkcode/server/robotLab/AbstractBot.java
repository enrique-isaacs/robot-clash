package za.co.wethinkcode.server.robotLab;

import za.co.wethinkcode.server.commands.Command;
import za.co.wethinkcode.server.maze.DefaultMaze;
import za.co.wethinkcode.server.world.IWorld;
import za.co.wethinkcode.server.world.dummyWorld;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractBot {

    private String status; //operational mode , eg. NORMAL
    private static int visibility;
    private static int shields;
    private String botName;



    public AbstractBot(String name, int visibilityX, int shieldsX) {
        this.name = name;
        this.status = "NORMAL";
        this.visibility = visibilityX;
        this.shieldsX = shieldsX;
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


    public void setStatus(String status) {
        this.status = status;
    }


    public String getName() {
        return name;
    }

    // make edits to Abstract Robot class
}
