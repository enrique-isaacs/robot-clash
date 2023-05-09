package za.co.wethinkcode.server.serverInterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.net.ServerSocket;

import za.co.wethinkcode.server.robotLab.AbstractBot;
import za.co.wethinkcode.server.world.WORLD;

public class WorldCommands extends Thread{

    WORLD world;
    private ServerSocket socket;

    public WorldCommands(WORLD world, ServerSocket socket){

        this.world = world;
        this.socket = socket;

    }
    
    @Override
    public void run(){
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String[] validArgs = new String[]{"robots", "dump", "quit"};
        String input;
        boolean shouldContinue = true;
        while (shouldContinue) {
            try {
                System.out.println("Enter command: ");
                input = consoleReader.readLine();
                while(!Arrays.asList(validArgs).contains(input.toLowerCase())){
                    System.out.println("Invalid command. Enter command: ");
                    input = consoleReader.readLine();
                }
                shouldContinue = manageWorld(input);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stopServer();
    }

    private void stopServer(){
        try{
            socket.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        System.exit(0);
    }

    private boolean manageWorld(String instruction){
        switch (instruction){
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


    private boolean listRobots(){

        if (this.world.getRobots().size() > 0) {

            System.out.println("Robots in the world:");
        
            for(AbstractBot robot: this.world.getRobots()){
                System.out.println(robot.getRobotName() + " : " + robot.getStatus());
            }
            return true;
        }

      else {

           System.out.println("There are no robots currently in the world.");
           return true;
        }
     }


    private boolean dump(){
        System.out.println("the world consists of:");
        for(AbstractBot robot: this.world.getRobots()){
            System.out.println(robot.getCurrentPosition() + " : " + robot.getRobotName());
        }
        return true;
    }

    private boolean shutdownServer(){
        return false;
    }
    
    
}
