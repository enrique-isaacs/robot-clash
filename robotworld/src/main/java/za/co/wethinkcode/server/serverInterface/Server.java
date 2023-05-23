package za.co.wethinkcode.server.serverInterface;

import za.co.wethinkcode.server.world.WORLD;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

/**
 * The Server class represents the game server that listens for client connections
 * and handles them by creating individual client handlers.
 */
public class Server {

    private ServerSocket serverSocket;
    private WORLD world;

    /**
     * Constructs a Server instance with the specified port.
     *
     * @param port The port number on which the server will listen for connections.
     * @throws IOException if an I/O error occurs when opening the server socket.
     */
    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server has started on port: " + port);
        this.world = new WORLD();
    }

    /**
     * Starts the server by accepting client connections and creating client handlers.
     *
     * @throws IOException if an I/O error occurs when accepting a client connection.
     */
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
