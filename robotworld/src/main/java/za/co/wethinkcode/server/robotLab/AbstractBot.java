package za.co.wethinkcode.server.robotLab;

import za.co.wethinkcode.server.commands.Command;
import za.co.wethinkcode.server.serverInterface.ResponseBuilder;
import za.co.wethinkcode.server.world.Direction;
import za.co.wethinkcode.server.world.Obstacle;
import za.co.wethinkcode.server.world.Position;
import za.co.wethinkcode.server.world.WORLD;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;


public abstract class AbstractBot{

    private String status; //operational mode , eg. NORMAL

    private Map<String, Object> robotState;
    private int visibility;
    private int shields;
    private int repair;
    private int reload;
    private int shots;

    private int firingDistance;

    private String botName;
    private Direction currentDirection;
    private Position currentPosition;

    private WORLD world;

    private String response;

    private Integer[] robotConfigs;

    protected String model;


    public AbstractBot(WORLD world,String name) {

        robotConfigs = world.getRobotConfigs();

        this.status = "NORMAL";
        this.robotState = new LinkedHashMap<>();
        this.world = world;
        this.botName = name;
        this.currentDirection = Direction.NORTH;
        this.visibility = robotConfigs[0];
        this.firingDistance = robotConfigs[1];
        this.repair = robotConfigs[2];
        this.reload = robotConfigs[3];
        this.shots = robotConfigs[4];
        this.shields = robotConfigs[5];
        this.currentPosition = this.world.generateSpawnPosition();
        this.model = "basic";

    }

    protected abstract void setType(String make);

    public void takeHit(){
        setShields(getShields()-1);
        if(getShields() == 0){
            this.status = "DEAD";
        }
    }

    public String getModel(){
        return this.model;
    }

    public void setShields(int val){
        this.shields = val;
    }

    public void setShots(int val){
        this.shots = val;
    }

    public WORLD getWorld(){
        return this.world;
    }

    public int getVisibility(){
        return this.visibility;
    }

    public int getReload(){
        return this.reload;
    }

    public int getRepair(){
        return this.repair;
    }


    public String getStatus() {
        return this.status;
    }

    public String getRobotName(){
        return this.botName;
    }


    public void setStatus(String status) {
        this.status = status;
    } // normal, reload, dead, repair

    public static AbstractBot make(WORLD world, String model, String name, int maxShields, int maxShots){
        switch(model){
            case "sniper":
                AbstractBot sniperRobot = new SniperBot(world, name);
                sniperRobot.setShields(maxShields);
                sniperRobot.setShots(maxShots);
                return sniperRobot;
            default:
                AbstractBot basicRobot = new BasicBot(world, name);
                return basicRobot;
            // make bot classes
        }
    }

    public String updatePosition(int nrSteps){
        int newX = this.currentPosition.getX();
        int newY = this.currentPosition.getY();
        Position oldPosition = this.currentPosition;

        if (Direction.NORTH.equals(this.currentDirection)) {
            newY = newY + nrSteps;
        }
        else if (Direction.SOUTH.equals(this.currentDirection)) {
            newY = newY - nrSteps;
        }
        else if (Direction.WEST.equals(this.currentDirection)) {
            newX = newX - nrSteps;
        }
        else if (Direction.EAST.equals(this.currentDirection)) {
            newX = newX + nrSteps;
        }

        Position newPosition = new Position(newX, newY);


        if (newPosition.isIn(world.getTOPLEFT(),world.getBOTTOMRIGHT())){
            this.currentPosition = newPosition;
            response = "SUCCESS";
        }
        else if(newPosition.isIn(world.getTOPLEFT(),world.getBOTTOMRIGHT()) && isRobotObstructed(newPosition) && getWorld().positionObstructedByRobot(newPosition)){
            response = "OBSTRUCTED";
        }

        else{response = "OUTSIDE_WORLD";}
        return response;
    }

    private boolean isRobotObstructed(Position newPosition){
        boolean isObstructed = false;

        for(Obstacle ob : world.getObstacles()){
            if(ob.blocksPath(currentPosition,newPosition)|| ob.blocksPosition(newPosition)){
                isObstructed = true;
                break;

            }
        }
        return isObstructed;

    }

    public void updateDirection(boolean turnRight) {
        if (turnRight) {
            switch (getCurrentDirection()) {
                case NORTH:
                    currentDirection = Direction.EAST;
                    break;
                case EAST:
                    currentDirection = Direction.SOUTH;
                    break;
                case SOUTH:
                    currentDirection = Direction.WEST;
                    break;
                case WEST:
                    currentDirection = Direction.NORTH;
                    break;
                default:
                    break;
            }
        } else {
            switch (currentDirection) {
                case NORTH:
                    currentDirection = Direction.WEST;
                    break;
                case WEST:
                    currentDirection = Direction.SOUTH;
                    break;
                case SOUTH:
                    currentDirection = Direction.EAST;
                    break;
                case EAST:
                    currentDirection = Direction.NORTH;
                    break;
                default:
                    break;
            }
        }
    }

    public int getShields(){
        return this.shields;
    }

    public int getShots(){
        return this.shots;
    }

    public Position getCurrentPosition(){
        return this.currentPosition;
    }

    public Direction getCurrentDirection(){
        return this.currentDirection;
    }

    public Map<String, Object> getState(){

        int x = this.getCurrentPosition().getX();
        int y = this.getCurrentPosition().getY();
        Integer[] positionArray = new Integer[]{x,y};

        robotState.put("position", positionArray);
        robotState.put("direction", getCurrentDirection());
        robotState.put("shields", getShields());
        robotState.put("shots", getShots());
        robotState.put("status", getStatus());

        return robotState;

    }

    public boolean handleCommand(Command command, ResponseBuilder responseBuilder){
        return command.execute(this, responseBuilder);
    }

}
