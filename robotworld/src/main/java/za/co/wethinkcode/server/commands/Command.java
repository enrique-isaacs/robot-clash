package za.co.wethinkcode.server.commands;

import za.co.wethinkcode.server.robotLab.AbstractBot;
import za.co.wethinkcode.server.serverInterface.ResponseBuilder;
import za.co.wethinkcode.server.world.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public abstract class Command {

   

    private final String name;
    private String argument;

    protected Position positionOfRobotHit;

    protected int distanceBulletTraveled;


    public abstract boolean execute(AbstractBot target, ResponseBuilder responseBuilder);

    public Command(String name){
        this.name = name.trim().toLowerCase();
        this.argument = "";

    }

    public Command(String name, String argument) {
        this(name);
        this.argument = argument.trim();
    }

    protected void setDistanceBulletTraveled(int distance){
        this.distanceBulletTraveled = distance;
    }

    protected void setPositionOfRobotHit(Position position){
        this.positionOfRobotHit = position;
    }

    public String getName() {                                                                           //<2>
        return name;
    }

    public String getArgument() {
        return this.argument;
    }

    public static Command create(String instruction) {
        System.out.println(instruction);
        String[] args = instruction.toLowerCase().trim().split(" ");

        // Add more cases for new commands
        switch (args[0]){
            case "quit":
                return new RobotQuitCommand();

            case "turn":

                if(args[1].equalsIgnoreCase("right")){
                    return new RightCommand();
                }
                else{
                    return new LeftCommand();
                }

            case "look":
                return new LookCommand();

            case "fire":
                return new FireCommand();

            case "forward":
                return new ForwardCommand(args[1]);

            case "back":
                return new BackCommand(args[1]);

            case "reload":
                System.out.println("In here");
                return new ReloadCommand();

            case "repair":
                return new RepairCommand();

            default:
                return new ErrorHandling();
        }
    }
}

