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
    private static Position spawn;

    public AbstractBot(String name, int visibilityX, int shieldsX, Position spawnx) {
        this.botName = name;
        this.status = "NORMAL";
        visibility = visibilityX;
        this.shields = shieldsX;
        spawn = spawnx;
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
    } // normal, reload, dead, repair

    public static AbstractBot make(String model, String name){
        switch(model){
            case "Sniper":
                return new SniperBot(name, visibility, shields, spawn);
            default:
                return new BasicBot(name, visibility, shields, spawn);
                // make bot classes
        }
    }

//    public UpdateResponse updatePosition(int nrSteps){
//        int newX = this.currentPosition.getX();
//        int newY = this.currentPosition.getY();
//
//        if (Direction.UP.equals(this.currentDirection)) {
//            newY = newY + nrSteps;
//        }
//        else if (Direction.DOWN.equals(this.currentDirection)) {
//            newY = newY - nrSteps;
//        }
//        else if (Direction.LEFT.equals(this.currentDirection)) {
//            newX = newX - nrSteps;
//        }
//        else if (Direction.RIGHT.equals(this.currentDirection)) {
//            newX = newX + nrSteps;
//        }
//
//        Position newPosition = new Position(newX, newY);
//        return this.response;
//    }

    public void updateDirection(boolean turnRight) {
        if (turnRight) {
            switch (currentDirection) {
                case UP:
                    currentDirection = Direction.RIGHT;
                    break;
                case RIGHT:
                    currentDirection = Direction.DOWN;
                    break;
                case DOWN:
                    currentDirection = Direction.LEFT;
                    break;
                case LEFT:
                    currentDirection = Direction.UP;
                    break;
                default:
                    break;
            }
        } else {
            switch (currentDirection) {
                case UP:
                    currentDirection = Direction.LEFT;
                    break;
                case LEFT:
                    currentDirection = Direction.DOWN;
                    break;
                case DOWN:
                    currentDirection = Direction.RIGHT;
                    break;
                case RIGHT:
                    currentDirection = Direction.UP;
                    break;
                default:
                    break;
            }
        }
    }

    public Position getCurrentPosition(){
        return this.currentPosition;
    }

    public Direction getCurrentDirection(){
        return this.currentDirection;
    }

}
