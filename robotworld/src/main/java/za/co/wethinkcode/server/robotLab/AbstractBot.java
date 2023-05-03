package za.co.wethinkcode.server.robotLab;

import za.co.wethinkcode.server.commands.Command;
import za.co.wethinkcode.server.world.Direction;
import za.co.wethinkcode.server.world.Position;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractBot {

    private String status; //operational mode , eg. NORMAL
    private static int visibility;
    private static int shields;
    private String botName;
    private Direction currentDirection;
    private Position currentPosition;
    private Position spawn;

    public AbstractBot(String name, int visibilityX, int shieldsX, Position spawn) {
        this.botName = name;
        this.status = "NORMAL";
        this.visibility = visibilityX;
        this.shields = shieldsX;
        this.spawn = spawn;
        this.currentDirection = null;
        this.currentPosition = null;
    }


    public String getStatus() {
        return this.status;
    }

    public String getRobotName(){
        return this.botName;
    }

    public boolean handleCommand(Command command) {
        return command.execute(this);
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public static AbstractBot make(String model, String name){
        switch(model){
            case "Sniper":
                return new SniperBot(name, visibility, shields);
            default:
                return new BasicBot(name, visibility, shields);
                // make bot classes
        }
    }

    // update position method
    // update direction
    // get direction
    // get current position

    // make edits to Abstract Robot class
}
