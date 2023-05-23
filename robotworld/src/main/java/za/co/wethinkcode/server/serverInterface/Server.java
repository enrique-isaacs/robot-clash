package za.co.wethinkcode.server.serverInterface;

import za.co.wethinkcode.server.world.WORLD;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;


public class Server {

    private ServerSocket serverSocket;
    private WORLD world;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server has started on port: "+ port);
        this.world = new WORLD();
    }

    public void start() throws IOException {

        WorldCommands consoleCommands = new WorldCommands(this.world, serverSocket);
        consoleCommands.start();

        try{
            while(true){
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket, this.world);
                clientHandler.start();
            }
        }
        catch(Exception e){
            System.out.println("System shutting down");
            System.exit(0);
        }

    }


}
