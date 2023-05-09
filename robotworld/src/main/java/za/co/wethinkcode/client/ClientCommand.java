package za.co.wethinkcode.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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

    public JSONObject getCommand(String name) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("I can understand these commands:\n" +
                "QUIT  - Shut down robot\n" +
                "FORWARD - move forward by specified number of steps, e.g. 'FORWARD 10'\n"+
                "BACKWARD - move backward by specified number of steps, e.g. 'BACK 10'\n" +
                "LOOK - Look whats around you 'look'");
        System.out.print("Enter a command and argument (e.g. 'forward 10' or 'launch sniper'): ");
        String userInput = scanner.nextLine().trim();

        String[] parts = userInput.split(" ");
        String command = parts[0];

        LinkedHashMap<String, Object> jsonMap = new LinkedHashMap<>();
        jsonMap.put("robot", name);
        jsonMap.put("command", command);

        if (command.equals("quit")) {
            ArrayList<Object> arguments = new ArrayList<Object>();
            jsonMap.put("arguments", arguments);
        } else if (command.equals("launch")) {
            if (parts.length < 2) {
                System.out.println("Invalid command. Please specify the kind of robot to launch (sniper, basic, tank).");
                return null;
            }
            String kind = parts[1];
            int maxShields = 0;
            int maxShots = 0;

            switch (kind) {
                case "sniper":
                    maxShields = 1;
                    maxShots = 4;
                    break;
                case "basic":
                    maxShields = 3;
                    maxShots = 3;
                    break;
                case "tank":
                    maxShields = 5;
                    maxShots = 1;
                    break;
                default:
                    System.out.println("Invalid robot kind. Please choose from sniper, basic, tank.");
                    return null;
            }

            ArrayList<Object> arguments = new ArrayList<Object>();
            arguments.add(kind);
            arguments.add(maxShields);
            arguments.add(maxShots);
            jsonMap.put("arguments", arguments);
        } else if (command.equals("look")) {
            ArrayList<Object> arguments = new ArrayList<Object>();
            jsonMap.put("arguments", arguments);

        } else if (command.equals("state")) {
            ArrayList<Object> arguments = new ArrayList<Object>();
            jsonMap.put("arguments", arguments);

        } else if (command.equals("reload")) {
            ArrayList<Object> arguments = new ArrayList<Object>();
            jsonMap.put("arguments", arguments);

        } else if (command.equals("repair")) {
            ArrayList<Object> arguments = new ArrayList<Object>();
            jsonMap.put("arguments", arguments);

        } else if (command.equals("fire")) {
            ArrayList<Object> arguments = new ArrayList<Object>();
            jsonMap.put("arguments", arguments);
        } else {
            ArrayList<String> arguments = new ArrayList<String>();
            for (int i = 1; i < parts.length; i++) {
                arguments.add(parts[i]);
            }
            jsonMap.put("arguments", arguments);
        }

        JSONObject json = new JSONObject(jsonMap);
        return json;
    }


    public void sendCommand(JSONObject command) throws IOException {
        String userInput = command.toString();
        sendMessage(userInput);
    }

    public static void main(String[] args) throws IOException {
        int serverPort = 8888;
        String serverHost = "10.200.108.210";

        ClientCommand client = new ClientCommand(serverHost, serverPort);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();

        while (true) {
            JSONObject json = client.getCommand(name);
            if (json != null) {
                String command = json.getString("command");
                if (command.equals("quit")) {
                    System.out.println("GoodBytes");
                    break;
                }
                client.sendCommand(json);
            } else {
                break;
            }
            System.out.println();
        }

        // Keep asking for input after sending information to the server
        while (true) {
            System.out.println();
            System.out.println("Enter a new command or 'quit' to exit: ");
            String userInput = scanner.nextLine().trim();

            JSONObject json = client.getCommand(name);
            if (json != null) {
                String command = json.getString("command");
                if (command.equals("quit")) {
                    System.out.println("GoodBytes");
                    break;
                }
                client.sendCommand(json);
            } else {
                break;
            }
        }

        scanner.close();
        client.close();
    }
}
