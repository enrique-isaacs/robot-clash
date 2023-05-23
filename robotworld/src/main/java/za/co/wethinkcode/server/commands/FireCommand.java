package za.co.wethinkcode.server.commands;

import za.co.wethinkcode.server.robotLab.AbstractBot;
import za.co.wethinkcode.server.serverInterface.ResponseBuilder;
import za.co.wethinkcode.server.world.Direction;
import za.co.wethinkcode.server.world.Obstacle;
import za.co.wethinkcode.server.world.Position;
import za.co.wethinkcode.server.world.SquareObstacle;

import java.util.ArrayList;
import java.util.List;

public class FireCommand extends Command {

    public FireCommand(){
        super("fire");
    }

    @Override
    public boolean execute(AbstractBot target, ResponseBuilder responseBuilder) {

        if(!target.getStatus().equals("DEAD")){
            AbstractBot bot = null;
            String typeOfObjectHit = "";
            int distanceOfBullet = 0;
            boolean HIT = false;

            if(hitARobot(target)){
                responseBuilder.setResponseStatus("OK");
                if(getRobotHitByBullet(target).size()>0){
                    bot = getRobotHitByBullet(target).get(0);
                    typeOfObjectHit = "Robot";
                    HIT = true;
                    distanceOfBullet = getDistanceOfBulletTravelled(bot, target);
                    bot.takeHit();
                    target.reduceShots(distanceOfBullet);
                }

            }
            else if(hitAnObstacle(target)){
                typeOfObjectHit = "Obstacle";
            }

            // add in response building methods here..
            if(bot != null){
                responseBuilder.setDataMessage("Hit");
                responseBuilder.setRobotHit(bot);
                responseBuilder.setFireDistance(distanceOfBullet);
                responseBuilder.setRobotHitName(bot);
            }
            else if(!HIT || bot == null){
                responseBuilder.setResponseStatus("OK");
                responseBuilder.setDataMessage("Miss");
                responseBuilder.buildDataMessage();
            }

            return true;
        }
        return false;

    }

    private ArrayList<Obstacle> convertRobotPositionsToObstacles(AbstractBot target){
        // Converts robot positions to Obstacles

        ArrayList<Obstacle> robotPositionsAsObstacles = new ArrayList<>();
        ArrayList<Position> robotsInRange = robotPositionsInSpecifiedRange(target.getCurrentDirection(), target);

        if(robotsInRange.size() > 0){
            for(Position position : robotsInRange){
                robotPositionsAsObstacles.add(new SquareObstacle(position.getX(), position.getY()));
            }
        }

        return robotPositionsAsObstacles;
    }

    private ArrayList<Position> robotPositionsInSpecifiedRange(Direction currentDirection, AbstractBot target){
        ArrayList<Position> robotsInRange = new ArrayList<>();
        if(currentDirection == Direction.NORTH){
            for(AbstractBot bots : target.getWorld().getMapOfRobots().values()){
                if((bots.getCurrentPosition().getX() == target.getCurrentPosition().getX()) && ((bots.getCurrentPosition().getY() > target.getCurrentPosition().getY()))){
                    robotsInRange.add(bots.getCurrentPosition());
                }

            }
        }
        else if(currentDirection == Direction.SOUTH){
            for(AbstractBot bots : target.getWorld().getMapOfRobots().values()){
                if((bots.getCurrentPosition().getX() == target.getCurrentPosition().getX()) && ((bots.getCurrentPosition().getY() < target.getCurrentPosition().getY()))){
                    robotsInRange.add(bots.getCurrentPosition());
                }

            }
        }
        else if(currentDirection == Direction.EAST){
            for(AbstractBot bots : target.getWorld().getMapOfRobots().values()){
                if((bots.getCurrentPosition().getX() > target.getCurrentPosition().getX()) && ((bots.getCurrentPosition().getY() == target.getCurrentPosition().getY()))){
                    robotsInRange.add(bots.getCurrentPosition());
                }

            }
        }
        else if(currentDirection == Direction.WEST){
            for(AbstractBot bots : target.getWorld().getMapOfRobots().values()){
                if((bots.getCurrentPosition().getX() < target.getCurrentPosition().getX()) && ((bots.getCurrentPosition().getY() == target.getCurrentPosition().getY()))){
                    robotsInRange.add(bots.getCurrentPosition());
                }

            }
        }

        return robotsInRange;
    }

    private ArrayList<AbstractBot> getRobotHitByBullet(AbstractBot target){

        ArrayList<AbstractBot> listOfBotHit = new ArrayList<>();

        for(AbstractBot bot : target.getWorld().getMapOfRobots().values()){
            if(bot.getCurrentPosition().equals(positionOfRobotHit)){
                listOfBotHit.add(bot);
                break;
            }
        }

        if(listOfBotHit.size()>0){
            return listOfBotHit;
        }
        return listOfBotHit;

    }

    private Position convertObstacleToPosition(Obstacle obstacle){
        return new Position(obstacle.getBottomLeftX(), obstacle.getBottomLeftY());
    }

    private boolean hitARobot(AbstractBot target){

        Position bulletEnd = bulletEnd(target.getCurrentDirection(), target);
        ArrayList<Obstacle> robotsAsObstacles = convertRobotPositionsToObstacles(target);


        if(robotsAsObstacles.size() > 0){

            for(Obstacle robotObstacle : robotsAsObstacles){
                if(!(new SquareObstacle(target.getCurrentPosition().getX(), target.getCurrentPosition().getY()).equals(robotObstacle))){
                    if(robotObstacle.blocksPath(target.getCurrentPosition(), bulletEnd)){
                        setPositionOfRobotHit(convertObstacleToPosition(robotObstacle));
                        return true;
                    }
                }
            }
        }
        return false;

    }

    private Position bulletEnd(Direction direction, AbstractBot target){
        // Gets the end position of the bullet after being fired, based off the direction the robot is facing
        Position bullet = null;

        if(direction == Direction.NORTH){
            bullet = new Position(target.getCurrentPosition().getX(), target.getCurrentPosition().getY() + target.getVisibility());
        }
        else if(direction == Direction.SOUTH){
            bullet = new Position(target.getCurrentPosition().getX(), target.getCurrentPosition().getY() - target.getVisibility());
        }
        else if(direction == Direction.EAST){
            bullet = new Position(target.getCurrentPosition().getX() + target.getVisibility(), target.getCurrentPosition().getY());
        }
        else if(direction == Direction.WEST){
            bullet = new Position(target.getCurrentPosition().getX() - target.getVisibility(), target.getCurrentPosition().getY());
        }

        return bullet;
    }

    private boolean hitAnObstacle(AbstractBot target){

        Position bulletEnd = bulletEnd(target.getCurrentDirection(), target);
        List<Obstacle> obstacleList = target.getWorld().getObstacles();
        for(Obstacle ob : obstacleList){
            if(ob.blocksPath(target.getCurrentPosition(), bulletEnd)){
                return true;
            }
        }
        return false;
    }

    private int getDistanceOfBulletTravelled(AbstractBot botThatGotHit, AbstractBot target){
        Position positionOfBotThatGotHit = botThatGotHit.getCurrentPosition();
        Position currentRobotsPosition = target.getCurrentPosition();

        int distance = 0;

        if(target.getCurrentDirection() == Direction.NORTH){
            distance = positionOfBotThatGotHit.getY() - (currentRobotsPosition.getY()+4);
        }
        else if(target.getCurrentDirection() == Direction.SOUTH){
            distance = currentRobotsPosition.getY() - positionOfBotThatGotHit.getY() ;
        }
        else if(target.getCurrentDirection() == Direction.EAST){
            distance = positionOfBotThatGotHit.getX() - (currentRobotsPosition.getX()+4);
        }
        else if(target.getCurrentDirection() == Direction.WEST){
            distance = currentRobotsPosition.getX() - positionOfBotThatGotHit.getX();
        }

        return distance;

    }

//    private AbstractBot robotThatBlocksBulletPath(Position bulletPosition){
//        // checks whether a robot is in the path of the bullet
//
//
//    }

//    private boolean robotInPathOfBullet(AbstractBot target, Position bulletPosition){
//        boolean yes = false;
//        for(Obstacle robotPosition : convertRobotPositionsToObstacles(AbstractBot target)){
//            if(robotPosition.blocks)
//        }
//    }


}
