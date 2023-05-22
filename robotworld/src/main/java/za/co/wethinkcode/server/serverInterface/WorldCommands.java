package za.co.wethinkcode.server.serverInterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.net.ServerSocket;

import za.co.wethinkcode.server.robotLab.AbstractBot;
import za.co.wethinkcode.server.world.Obstacle;
import za.co.wethinkcode.server.world.Position;
import za.co.wethinkcode.server.world.WORLD;

/**
 * The WorldCommands class handles the commands entered through the console for managing the world.
 * It provides functionality to list the robots in the world, dump the current state of the world, and
 * shut down the server.
 */
public class WorldCommands extends Thread {

    private WORLD world;
    private ServerSocket socket;

    /**
     * Constructs a WorldCommands instance with the specified world and server socket.
     *
     * @param world  The WORLD instance representing the game world.
     * @param socket The ServerSocket instance for communication with clients.
     */
    public WorldCommands(WORLD world, ServerSocket socket) {
        this.world = world;
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String[] validArgs = new String[]{"robots", "dump", "quit"};
        String input;
        boolean shouldContinue = true;
        while (shouldContinue) {
            try {
                System.out.print("Enter command: ");
                input = consoleReader.readLine();
                while (!Arrays.asList(validArgs).contains(input.toLowerCase())) {
                    System.out.print("Invalid command. Enter command: ");
                    input = consoleReader.readLine();
                }
                shouldContinue = manageWorld(input);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stopServer();
    }

    /**
     * Stops the server and closes the server socket.
     */
    private void stopServer() {
        try {
            Server.serverIsRunning = false;

            sleep(1);
            socket.close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }

    /**
     * Executes the specified instruction for managing the world.
     *
     * @param instruction The instruction for managing the world.
     * @return True if the server should continue running, false otherwise.
     */
    private boolean manageWorld(String instruction) {
        switch (instruction) {
            case "robots":
                return listRobots();
            case "dump":
                return dump();
            case "quit":
                return shutdownServer();
            default:
                return false;
        }
    }

    /**
     * Lists the robots in the world.
     *
     * @return True to continue running the server.
     */
    private boolean listRobots() {
        if (world.getListOfRobots().size() > 0) {
            System.out.println("Robots in the world:");

            for (AbstractBot robot : world.getListOfRobots()) {
                System.out.println(robot.getRobotName() + " : " + robot.getState());
                System.out.println(robot.getModel());
            }

        } else {
            System.out.println("There are no robots currently in the world.");
        }
        return true;
    }

    /**
     * Dumps the current state of the world.
     *
     * @return True to continue running the server.
     */
    private boolean dump() {
        boolean onlyRobots = (world.getListOfRobots().size() > 0 && world.getObstacles().size() == 0);
        boolean onlyObstacles = (world.getObstacles().size() > 0 && world.getListOfRobots().size() == 0);
        boolean fullWorld = (world.getListOfRobots().size() > 0 && world.getObstacles().size() > 0);

        if (onlyRobots) {
            System.out.println("The world consists of:");
            System.out.println("Robots:");

            for (AbstractBot robot : this.world.getListOfRobots()) {
                System.out.println(robot.getRobotName() + " : " + "[" + robot.getCurrentPosition().getX() + "," + robot.getCurrentPosition().getY() + "]");
            }
        } else if (onlyObstacles) {
            for (Obstacle ob : this.world.getObstacles()) {
                System.out.print("There are obstacles at: ");
                System.out.println("[" + ob.getBottomLeftX() + "," + ob.getBottomLeftY() + "] to [" + (ob.getBottomLeftX() + 4) + "," + (ob.getBottomLeftY() - 4) + "]");
            }
        } else if (fullWorld) {
            System.out.println("The world consists of:");
            System.out.println("Robots:");

            for (AbstractBot bots : this.world.getListOfRobots()) {
                System.out.println(bots.getRobotName() + " : " + "[" + bots.getCurrentPosition().getX() + "," + bots.getCurrentPosition().getY() + "]");
            }

            for (Obstacle ob : this.world.getObstacles()) {
                System.out.print("There are obstacles at:");
                System.out.println("[" + ob.getBottomLeftX() + "," + ob.getBottomLeftY() + "] to [" + (ob.getBottomLeftX() + 4) + "," + (ob.getBottomLeftY() - 4) + "]");
            }
        } else {
            System.out.println("The World seems to be empty..");
        }

        return true;
    }

    /**
     * Shuts down the server.
     *
     * @return False to stop running the server.
     */
    private boolean shutdownServer() {
        return false;
    }
}
