package za.co.wethinkcode.server;


import za.co.wethinkcode.server.commands.Command;
import za.co.wethinkcode.server.maze.*;
import za.co.wethinkcode.server.world.IWorld;
import za.co.wethinkcode.server.world.dummyWorld;
import za.co.wethinkcode.server.robotLab.AbstractBot;
import za.co.wethinkcode.server.robotLab.DefaultBot;

import java.util.Scanner;

public class Play {
    static Scanner scanner;
    private IWorld world;



    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        AbstractBot robot;

        String name = getInput("What do you want to name your robot?");


        robot = new DefaultBot(name);

        System.out.println("Hello Kiddo!");
//        System.out.println("Loaded " + worldName.getWorldName());

        System.out.println(robot.toString());

        Command command;

        boolean shouldContinue = true;
        do {
            String instruction = getInput(robot.getName() + "> What must I do next?").strip().toLowerCase();
            try {

                command = Command.create(instruction);
                shouldContinue = robot.handleCommand(command);
            } catch (IllegalArgumentException e) {
                robot.setStatus("Sorry, I did not understand '" + instruction + "'.");
            }
            System.out.println(robot);
        } while (shouldContinue);
//        Command.resetHistoryGlobal();

    }

    private static String getInput(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();

        while (input.isBlank()) {
            System.out.println(prompt);
            input = scanner.nextLine();
        }
        return input.toUpperCase();
    }


}
