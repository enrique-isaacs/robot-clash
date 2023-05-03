package za.co.wethinkcode.server.commands;

import za.co.wethinkcode.server.robotLab.AbstractBot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public abstract class Command {
    private final String name;
    private String argument;

    // History stores commands for use (replay) - No use for now
    private static ArrayList<Command> history = new ArrayList<>();

    public abstract boolean execute(AbstractBot target);

    public Command(String name){
        this.name = name.trim().toLowerCase();
        this.argument = "";

    }

    public Command(String name, String argument) {
        this(name);
        this.argument = argument.trim();
    }

    public String getName() {                                                                           //<2>
        return name;
    }

    public String getArgument() {
        return this.argument;
    }

    public static void resetHistoryGlobal(){
        history.clear();
    }

    public static ArrayList<Command> getReverseHistory(){
        ArrayList<Command> reversedHistory = (ArrayList<Command>)history.clone();
        Collections.reverse(reversedHistory);
        return reversedHistory;
    }

    public static ArrayList<Command> getHistory(){
        return history;
    }

    public static Command create(String instruction) {
        String[] args = instruction.toLowerCase().trim().split(" ");

        // Add more cases for new commands
        switch (args[0]){
            case "shutdown":
                return new ShutdownCommand();
            case "forward":
                return new ForwardCommand(args[1]);
            case "back":
                return new BackCommand(args[1]);
            case "left":
                return new LeftCommand();
            case "right":
                return new RightCommand();
            default:
                throw new IllegalArgumentException("Unsupported command: " + instruction);
        }
    }
}

