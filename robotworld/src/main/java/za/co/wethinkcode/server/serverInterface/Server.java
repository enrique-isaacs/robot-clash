package za.co.wethinkcode.server.serverInterface;

import za.co.wethinkcode.server.commands.Command;
import za.co.wethinkcode.server.robotLab.AbstractBot;
import za.co.wethinkcode.server.robotLab.DefaultBot;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;


public class Server {

    static Scanner scn;

    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server has started on port: "+ port);
    }

    public void start() throws IOException {
        AbstractBot robot;

//        String name = getInput("What do you want to name your robot?");


        robot = new DefaultBot("Elheffe");

        System.out.println("Hello User");
//        System.out.println("Loaded " + worldName.getWorldName());

        System.out.println(robot.toString());



        boolean shouldContinue = true;

        while(true){
            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket);
            updateGame(shouldContinue, robot);

            ClientHandler clientHandler = new ClientHandler(socket);
            clientHandler.start();
        }
    }

    public static void updateGame(boolean shouldContinue, AbstractBot robot){
        Command command;


        do {
            String instruction = scn.nextLine();
            try {

                command = Command.create(instruction);
                shouldContinue = robot.handleCommand(command);
            } catch (IllegalArgumentException e) {
                robot.setStatus("Sorry, I did not understand '" + instruction + "'.");
            }
            System.out.println(robot);
        } while (shouldContinue);
    }


}
