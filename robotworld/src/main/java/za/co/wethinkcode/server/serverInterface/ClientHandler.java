package za.co.wethinkcode.server.serverInterface;

import za.co.wethinkcode.client.ClientCommand;
import za.co.wethinkcode.server.world.WORLD;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class ClientHandler extends Thread {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            String message = null;

            while (true) {
                message = (String) inputStream.readObject();
                RequestHandler r = new RequestHandler(message);
                String cmd = r.getCommand();
                // temp fix to add robots to list of robots
//                List<String> arguments = parseArguments(message);

                if(cmd.contains("launch")){
                    WORLD.addRobots(r.getCommand(),r.getName());
                }

                System.out.println("Received message from client: " + message);

                // this is where the commands should be handled

                if (r.getCommand().equalsIgnoreCase("quit")) {

                    break;
                } else if (r.getCommand().equalsIgnoreCase("dump")) {

                }

                Scanner scn = new Scanner(System.in);
                System.out.print("Enter message for client: ");
                String response = scn.nextLine();
                outputStream.writeObject(response);
                System.out.println("Sent message to client: " + response);
            }

            inputStream.close();
            outputStream.close();
            socket.close();
            System.out.println("Client disconnected: " + socket);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//    public static boolean isIN(String word, String[] args){
//        for(String arg : args){
//            if(arg.equalsIgnoreCase(word)){
//                return true;
//            }
//        }return false;
//    }

}
