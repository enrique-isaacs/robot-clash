package za.co.wethinkcode.server.world;

import za.co.wethinkcode.server.robotLab.AbstractBot;
import za.co.wethinkcode.server.maze.DefaultMaze;
import za.co.wethinkcode.server.maze.Maze;

import java.util.*;

/**
 * Represents the game world.
 */
public class WORLD {

    private int visibility;
    private Position TOPLEFT;
    private Position BOTTOMRIGHT;
    private ArrayList<AbstractBot> listOfRobots = new ArrayList<>();
    private List<Obstacle> listOfObstacles;
    private Maze maze;
    private Map<String, AbstractBot> robotMap;
    private Random random;
    private Position botSpawn;
    private ArrayList<Position> robotPositions;
    private ArrayList<Obstacle> robotPositionsAsObstaclesArray;

    /**
     * Constructs a WORLD object representing the game world.
     */
    public WORLD() {
        Integer[] worldConfigs = getWorldConfigs();
        int HEIGHT = worldConfigs[0];
        int WIDTH = worldConfigs[1];

        this.TOPLEFT = new Position(-(WIDTH / 2), HEIGHT / 2);
        this.BOTTOMRIGHT = new Position(WIDTH / 2, -(HEIGHT / 2));

        this.maze = new DefaultMaze();
        this.listOfObstacles = this.maze.getObstacles();
        robotMap = new HashMap<>();
        random = new Random();
        robotPositions = new ArrayList<>();
        robotPositionsAsObstaclesArray = new ArrayList<>();
    }

    /**
     * Retrieves the world configuration values from a JSON file.
     *
     * @return an array containing the height and width of the world
     */
    private Integer[] getWorldConfigs() {
        // Retrieve world configuration values from JSON file
        int height = ReadJSONFile.get("height");
        int width = ReadJSONFile.get("width");
        return new Integer[]{height, width};
    }

    /**
     * Retrieves the robot configuration values from a JSON file.
     *
     * @return an array containing the visibility, firing distance, repair, reload, shots, and shields values
     */
    public Integer[] getRobotConfigs() {
        // Retrieve robot configuration values from JSON file
        int visibility = ReadJSONFile.get("visibility");
        int firingDistance = ReadJSONFile.get("firingDistance");
        int repair = ReadJSONFile.get("repair");
        int reload = ReadJSONFile.get("reload");
        int shots = ReadJSONFile.get("shots");
        int shields = ReadJSONFile.get("shields");
        return new Integer[]{visibility, firingDistance, repair, reload, shots, shields};
    }

    private ArrayList<Position> setRobotPositionList() {
        for (AbstractBot bot : getListOfRobots()) {
            this.robotPositions.add(bot.getCurrentPosition());
        }
        return this.robotPositions;
    }

    private ArrayList<Position> getRobotPositions() {
        this.robotPositions = setRobotPositionList();
        return this.robotPositions;
    }

    public Position getTOPLEFT() {
        return this.TOPLEFT;
    }

    public Position getBOTTOMRIGHT() {
        return this.BOTTOMRIGHT;
    }

    public void addRobots(String type, String name, int shields, int shots) {
        // Add a robot to the game world
        AbstractBot robot = AbstractBot.make(this, type, name, shields, shots);
        this.listOfRobots.add(robot);
        this.robotMap.put(name, robot);
    }

    public void removeBot(String name) {
        // Remove a robot from the game world
//        removeRobotPositionFromPositionsList(name);
        removeRobotFromRobotList(name);
        this.robotMap.remove(name);
    }

    private void removeRobotFromRobotList(String name) {
        // Remove the specified robot from the robot list
        int indexToRemoveBotFromRobotList = 0;
        int index = 0;

        for (AbstractBot bot : this.listOfRobots) {
            if (bot.getRobotName().equals(name)) {
                indexToRemoveBotFromRobotList = index;
            }
            index++;
        }
        this.listOfRobots.remove(indexToRemoveBotFromRobotList);
    }

//    private void removeRobotPositionFromPositionsList(String name) {
//        // Remove the position of the specified robot from the robot position list
//        int indexToRemoveBotPositionFromRobotPositionList = 0;
//        int index = 0;
//
//        Position positionOfThisRobot = this.robotMap.get(name).getCurrentPosition();
//
//        for (Position botPosition : this.robotPositions) {
//            if (positionOfThisRobot.equals(botPosition)) {
//                indexToRemoveBotPositionFromRobotPositionList = index;
//            }
//            index++;
//        }
//        this.robotPositions.remove(indexToRemoveBotPositionFromRobotPositionList);
//    }

    public List<Obstacle> getObstacles() {
        // Get the list of obstacles in the game world
        return this.listOfObstacles;
    }

    public ArrayList<AbstractBot> getListOfRobots() {
        // Get the list of robots in the game world
        return listOfRobots;
    }

    private ArrayList<Obstacle> getRobotPositionsAsObstacles() {
        // Convert robot positions to obstacles and store them in an array
        for (AbstractBot bot : getListOfRobots()) {
            robotPositionsAsObstaclesArray.add(new SquareObstacle(bot.getCurrentPosition().getX(), bot.getCurrentPosition().getY()));
        }
        return robotPositionsAsObstaclesArray;
    }

    public boolean containsBot(String name) {
        // Check if a robot with the specified name exists in the game world
        for (AbstractBot robot : getListOfRobots()) {
            if (robot.getRobotName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public AbstractBot getBot(String name) {
        // Retrieve the robot object with the specified name
        return robotMap.get(name);
    }

    public Position generateSpawnPosition() {
        // Generate a random spawn position for a robot
        while (true) {
            int x = this.random.nextInt(201) - 100;
            int y = this.random.nextInt(401) - 200;
            Position newSpawn = new Position(x, y);
            if ((!positionObstructedByRobot(newSpawn)) && (!spawnPointObstructedByObstacle(newSpawn))) {
                return newSpawn;
            }
        }
    }

    public boolean positionObstructedByRobot(Position position) {
        // Check if the specified position is obstructed by a robot
        boolean obstructed = false;
        for (Obstacle robotPositionAsObstacle : getRobotPositionsAsObstacles()) {
            if (robotPositionAsObstacle.blocksPosition(position)) {
                obstructed = true;
                break;
            }
        }
        return obstructed;
    }

    public AbstractBot robotInFireRange(Position bulletPosition) {
        // Check if a robot is in the firing range of the specified bullet position
        int index = 0;
        for (Obstacle robotPositionAsObstacle : getRobotPositionsAsObstacles()) {
            if (robotPositionAsObstacle.blocksPosition(bulletPosition)) {
                System.out.println("up index:" +index);
                break;
            }

            index++;
        }
        ArrayList<AbstractBot> botsHit = new ArrayList<>();
        for (AbstractBot bot : this.robotMap.values()) {
            System.out.println("index: " + index);
            System.out.println("size" + getRobotPositions().size());
            if (bot.getCurrentPosition().equals(getRobotPositions().get(index))) {
                botsHit.add(bot);
                break;
            }
        }

        return botsHit.get(0);
    }

    private boolean spawnPointObstructedByObstacle(Position botSpawnPoint) {
        // Check if the specified bot spawn point is obstructed by an obstacle
        boolean obstructed = false;
        for (Obstacle obstacle : getObstacles()) {
            if (obstacle.blocksPosition(botSpawnPoint)) {
                obstructed = true;
                break;
            }
        }
        return obstructed;
    }
}
