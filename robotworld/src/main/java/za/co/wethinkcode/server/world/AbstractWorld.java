package za.co.wethinkcode.server.world;

import za.co.wethinkcode.server.world.Position;
import za.co.wethinkcode.server.maze.DefaultMaze;
import za.co.wethinkcode.server.maze.Maze;


import java.util.List;

public abstract class AbstractWorld implements IWorld{

    private final Position TOP_LEFT = new Position(-100,200);
    private final Position TOP_RIGHT = new Position(100,200);
    private final Position BOTTOM_RIGHT = new Position(100,-200);
    private final Position BOTTOM_LEFT = new Position(-100,-200);



    private Position position;
    Position CENTRE = new Position(0,0);
    private Direction currentDirection;

    private Maze maze;

    public AbstractWorld(){
        this.position = CENTRE;
        this.currentDirection = Direction.UP;
        this.maze = new DefaultMaze();
    }

    public AbstractWorld(Maze maze){
        this.position = CENTRE;
        this.currentDirection = Direction.UP;
        this.maze = maze;
    }

    public UpdateResponse updatePosition(int nrSteps){
        int newX = this.position.getX();
        int newY = this.position.getY();

        if (Direction.UP.equals(this.currentDirection)) {
            newY = newY + nrSteps;
        }
        else if (Direction.DOWN.equals(this.currentDirection)){
            newY = newY - nrSteps;
        }
        else if (Direction.RIGHT.equals(this.currentDirection)){
            newX = newX + nrSteps;
        }
        else if (Direction.LEFT.equals(this.currentDirection)){
            newX = newX - nrSteps;
        }

        Position newPosition = new Position(newX, newY);
        if (isNewPositionAllowed(newPosition)){
            this.position = newPosition;
            return UpdateResponse.SUCCESS;
        }
        return UpdateResponse.FAILED_OUTSIDE_WORLD;
    }

    public Direction getCurrentDirection() {
        return this.currentDirection;
    }

    public void updateDirection(boolean turnRight){

        if(turnRight){
            if (getCurrentDirection() == Direction.UP) {
                this.currentDirection = Direction.RIGHT;
            } else if (getCurrentDirection() == Direction.RIGHT) {
                this.currentDirection = Direction.DOWN;
            } else if (getCurrentDirection() == Direction.DOWN) {
                this.currentDirection = Direction.LEFT;
            } else if (getCurrentDirection() == Direction.LEFT) {
                this.currentDirection = Direction.UP;
            }
        }
        else{
            if (getCurrentDirection() == Direction.UP) {
                this.currentDirection = Direction.LEFT;
            } else if (getCurrentDirection() == Direction.RIGHT) {
                this.currentDirection = Direction.UP;
            } else if (getCurrentDirection() == Direction.DOWN) {
                this.currentDirection = Direction.RIGHT;
            } else if (getCurrentDirection() == Direction.LEFT) {
                this.currentDirection = Direction.DOWN;
            }
        }


    }

    public boolean isNewPositionAllowed(Position position){
        return position.isIn(TOP_LEFT,BOTTOM_RIGHT);
    }

    public Position getPosition(){
        return this.position;
    }

    public boolean isAtEdge(){

        boolean atTOP_EDGE = getPosition().getY() == TOP_LEFT.getY()
                && getPosition().getX() >= TOP_LEFT.getX()
                && getPosition().getX() <= TOP_RIGHT.getX();
        boolean atBOTTOM_EDGE = getPosition().getY() == BOTTOM_LEFT.getY()
                && getPosition().getX() > BOTTOM_LEFT.getX()
                && getPosition().getX() < BOTTOM_RIGHT.getX();
        boolean atLEFT_EDGE = getPosition().getX() == TOP_LEFT.getX()
                && getPosition().getY() < TOP_LEFT.getY()
                && getPosition().getY() > BOTTOM_LEFT.getY();
        boolean atRIGHT_EDGE = getPosition().getX() == TOP_RIGHT.getX()
                && getPosition().getY() > BOTTOM_RIGHT.getY()
                && getPosition().getY() < TOP_RIGHT.getY();

        return atTOP_EDGE || atRIGHT_EDGE || atBOTTOM_EDGE || atLEFT_EDGE;

    }

    public void reset(){
        this.position = new Position(0,0);
        this.currentDirection = Direction.UP;
        getObstacles().clear();
    }

    public List<Obstacle> getObstacles(){
        return this.maze.getObstacles();
    }

    public abstract void showObstacles();

    public Maze getMaze(){
        return this.maze;
    }


}
