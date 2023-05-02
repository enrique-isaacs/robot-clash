package za.co.wethinkcode.server.serverInterface;

import za.co.wethinkcode.server.commands.Command;
import za.co.wethinkcode.server.world.WORLD;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;


public class Server {

    private ServerSocket serverSocket;
    private WORLD world;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server has started on port: "+ port);
        this.world = new WORLD();
    }

    public void start() throws IOException {

       
       

        while(true){
            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket);
            updateGame(shouldContinue, robot);

            ClientHandler clientHandler = new ClientHandler(socket);
            clientHandler.start();
        }
    }


}
