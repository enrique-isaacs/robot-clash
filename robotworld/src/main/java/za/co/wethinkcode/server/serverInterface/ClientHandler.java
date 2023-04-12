package za.co.wethinkcode.server.serverInterface;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
                System.out.println("Received message from client: " + message);

                if (message.equals("quit")) {
                    break;
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
}
