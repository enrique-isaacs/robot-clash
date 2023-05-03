package za.co.wethinkcode.server.robotLab;

import za.co.wethinkcode.server.commands.Command;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractBot {

    private String status; //operational mode , eg. NORMAL
    private static int visibility;
    private static int shields;
    private String botName;



    public AbstractBot(String name, int visibilityX, int shieldsX) {
        this.botName = name;
        this.status = "NORMAL";
        this.visibility = visibilityX;
        this.shields = shieldsX;
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
