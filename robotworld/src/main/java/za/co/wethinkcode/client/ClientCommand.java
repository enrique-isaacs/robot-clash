package za.co.wethinkcode.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import org.json.JSONObject;

public class ClientCommand {
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public ClientCommand(String host, int port) throws IOException {
        socket = new Socket(host, port);
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    public void sendMessage(String message) throws IOException {
        outputStream.writeObject(message);
        outputStream.flush();

        String response = null;
        try {
            response = (String) inputStream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Received response from server: " + response);
    }

    public void close() throws IOException {
        inputStream.close();
        outputStream.close();
        socket.close();
    }

    public JSONObject getCommand() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter a command and argument (e.g. 'forward 10'): ");
        String userInput = scanner.nextLine().trim();

        // Split the user input into command and argument
        String[] parts = userInput.split(" ");
        String command = parts[0];
        String argument = parts.length > 1 ? parts[1] : "";

        // Create a JSON object with the name, command, and argument fields
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("command", command);
        json.put("argument", argument);

        return json;
    }

    public void SendCommand(JSONObject command) throws IOException {
        String userInput = command.toString();
        sendMessage(userInput);
    }

    public static void main(String[] args) throws IOException {
        int serverPort = 1999;
        String serverHost = "10.200.108.107";

        ClientCommand client = new ClientCommand(serverHost, serverPort);

        while (true) {
            JSONObject json = client.getCommand();
            client.SendCommand(json);
            if (json.getString("").equals("look")) {
                break;
            }else if (json.getString("").equals("state")){

            }
        }

        client.close();
    }
}