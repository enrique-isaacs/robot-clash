package za.co.wethinkcode.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
// import za.co.wethinkcode.server.world.ReadJSONFile;

public class runClient {
    private static String SERVER_IP = "192.168.18.6"; // Store the server IP address
    private static int SERVER_PORT = 8080; // Store the server port number
    private static String playerName = ""; // Store the player name

    /**
     * Reads user input from the console and establishes a connection to the server.
     * Starts a separate thread to receive messages from the server.
     * Sends messages to the server based on user input.
     */
    public static void main(String[] args) {
        try {

            BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the server IP address: ");
            SERVER_IP = userInputReader.readLine(); // Read server IP from user input
            System.out.print("Enter the server port number: ");
            SERVER_PORT = Integer.parseInt(userInputReader.readLine()); // Read server port from user input

            Socket socket = new Socket(SERVER_IP, SERVER_PORT);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // Start a separate thread to receive messages from the server
            Thread receiveThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String message;
                        while ((message = reader.readLine()) != null) {
                            System.out.println(message);

                            // Extract and display relevant information from the response
                            Gson gson = new Gson();
                            JsonObject response = gson.fromJson(message, JsonObject.class);
                            System.out.println(playerName);
                            if (response.has("data")) {
                                JsonObject data = response.getAsJsonObject("data");
                                if (data.has("message")) {
                                    // Extract individual values from the 'state' object
                                    int[] position = gson.fromJson(response.getAsJsonObject("state").getAsJsonArray("position"), int[].class);
                                    String direction = response.getAsJsonObject("state").get("direction").getAsString();
                                    int shields = response.getAsJsonObject("state").get("shields").getAsInt();
                                    int shots = response.getAsJsonObject("state").get("shots").getAsInt();
                                    String status = response.getAsJsonObject("state").get("status").getAsString();

                                    // Display the extracted information
                                    System.out.println("\t\"position\": " + Arrays.toString(position));
                                    System.out.println("\t\"direction\": " + direction);
                                    System.out.println("\t\"shields\": " + shields);
                                    System.out.println("\t\"shots\": " + shots);
                                    System.out.println("\t\"status\": " + status);
                                } else {
                                    // Extract individual values from the 'data' object
                                    int[] position = gson.fromJson(data.getAsJsonArray("position"), int[].class);
                                    int visibility = data.get("visibility").getAsInt();
                                    int reload = data.get("reload").getAsInt();
                                    int repair = data.get("repair").getAsInt();
                                    int shields = data.get("shields").getAsInt();
                                    String direction = response.getAsJsonObject("state").get("direction").getAsString();
                                    int shots = response.getAsJsonObject("state").get("shots").getAsInt();
                                    String status = response.getAsJsonObject("state").get("status").getAsString();

                                    // Display the extracted information
                                    System.out.println("\t\"position\": [" + position[0] + "," + position[1] + "]");
                                    System.out.println("\t\"visibility\": " + visibility);
                                    System.out.println("\t\"reload\": " + reload);
                                    System.out.println("\t\"repair\": " + repair);
                                    System.out.println("\t\"shields\": " + shields);
                                    System.out.println("\t\"direction\": " + direction);
                                    System.out.println("\t\"shots\": " + shots);
                                    System.out.println("\t\"status\": " + status);
                                    System.out.println();
                                }
                            } else {
                                // Display the entire response if no 'data' object is present
                                System.out.println(message);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            receiveThread.start();

            // Send messages to the server
            String userInput;
            System.out.println("I can understand these commands:\n" +
                    "QUIT  - Shut down robot\n" +
                    "FORWARD - move forward by specified number of steps, e.g. 'FORWARD 10'\n"+
                    "BACKWARD - move backward by specified number of steps, e.g. 'BACK 10'\n" +
                    "LOOK - Look whats around you 'look'");
            System.out.println("Launch me:\nlaunch kind name etc launch sniper hal");
            boolean isFirstInput = true; // Flag to track initial robot name input
            while ((userInput = userInputReader.readLine()) != null) {
                if (isFirstInput) {
                    extractPlayerName(userInput); // Extract player name from the first input
                    isFirstInput = false; // Update the flag after the first input
                }

                if (userInput.equalsIgnoreCase("quit")) {
                    break; // Exit the loop if the user inputs "quit"
                }

                String jsonInput = formatUserInputAsJson(userInput);
                writer.write(jsonInput);
                writer.newLine();
                writer.flush();
            }

            // Close the socket and streams when done
            socket.close();
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Extracts the player name from the user input.
     *
     * @param userInput The user input containing the player name.
     */
    private static void extractPlayerName(String userInput) {
        String[] tings = userInput.split(" ");
        playerName = tings[2]; // Extract player name from the user input
    }

    /**
     * Formats the user input as a JSON message to be sent to the server.
     *
     * @param userInput The user input to be formatted.
     * @return The formatted user input as a JSON string.
     */
    private static String formatUserInputAsJson(String userInput) {
        String[] tings = userInput.split(" ");
        String command = tings[0];
        int distance = 0;

        if(command.equals("look") && tings.length==1) {
            ArrayList<Object> arguments = new ArrayList<>();
            Map<String, Object> requestMessage = new LinkedHashMap<>();
            requestMessage.put("robot", playerName);
            requestMessage.put("command", command);
            requestMessage.put("arguments", arguments);

            Gson gson = new Gson();
            JsonObject jO = gson.fromJson(gson.toJsonTree(requestMessage), JsonObject.class);
            return jO.toString();
        }else if(command.equals("repair") && tings.length==1) {
            ArrayList<Object> arguments = new ArrayList<>();
            Map<String, Object> requestMessage = new LinkedHashMap<>();
            requestMessage.put("robot", playerName);
            requestMessage.put("command", command);
            requestMessage.put("arguments", arguments);

            Gson gson = new Gson();
            JsonObject jO = gson.fromJson(gson.toJsonTree(requestMessage), JsonObject.class);
            return jO.toString();
        }else if(command.equals("fire") && tings.length==1) {
            ArrayList<Object> arguments = new ArrayList<>();
            Map<String, Object> requestMessage = new LinkedHashMap<>();
            requestMessage.put("robot", playerName);
            requestMessage.put("command", command);
            requestMessage.put("arguments", arguments);

            Gson gson = new Gson();
            JsonObject jO = gson.fromJson(gson.toJsonTree(requestMessage), JsonObject.class);
            return jO.toString();
        }else if(command.equals("reload") && tings.length==1){
            ArrayList<Object> arguments = new ArrayList<>();
            Map<String, Object> requestMessage = new LinkedHashMap<>();
            requestMessage.put("robot", playerName);
            requestMessage.put("command", command);
            requestMessage.put("arguments", arguments);

            Gson gson = new Gson();
            JsonObject jO = gson.fromJson(gson.toJsonTree(requestMessage), JsonObject.class);
            return jO.toString();

        }else if(command.equals("turn")){
            String args= tings[1];
            if(args.contains("left")){
//                return "left";
                ArrayList<Object> arguments = new ArrayList<>();
                arguments.add(args);

                Map<String, Object> requestMessage = new LinkedHashMap<>();
                requestMessage.put("robot", playerName);
                requestMessage.put("command", command);
                requestMessage.put("arguments", arguments);
                System.out.println(playerName + ": turned left.");
                Gson gson = new Gson();
                JsonObject jO = gson.fromJson(gson.toJsonTree(requestMessage), JsonObject.class);
                return jO.toString();
            }else if (args.contains("right")){
                ArrayList<Object> arguments = new ArrayList<>();
                arguments.add(args);

                Map<String, Object> requestMessage = new LinkedHashMap<>();
                requestMessage.put("robot", playerName);
                requestMessage.put("command", command);
                requestMessage.put("arguments", arguments);
                System.out.println(playerName + ": turned right.");

                Gson gson = new Gson();
                JsonObject jO = gson.fromJson(gson.toJsonTree(requestMessage), JsonObject.class);
                return jO.toString();
            }else{
                System.out.println("invalid direction");
            }

            ArrayList<Object> arguments = new ArrayList<>();
            arguments.add(args);

//            System.out.println(arguments);
            Map<String, Object> requestMessage = new LinkedHashMap<>();
            requestMessage.put("robot", playerName);
            requestMessage.put("command", command);
            requestMessage.put("arguments",arguments);

            Gson gson = new Gson();
            JsonObject jO = gson.fromJson(gson.toJsonTree(requestMessage), JsonObject.class);
            return jO.toString();

        }else if (command.equals("launch")) {
            if (tings.length < 2) {
                System.out.println("Invalid command. Please specify the kind of robot to launch (sniper, basic, tank).");
                return null;
            }
            String kind = tings[1];
            int maxShields = 0;
            int maxShots = 0;

            switch (kind) {
                case "sniper":
                    maxShields = 1;
                    maxShots = 4;
                    System.out.println(playerName + " Launched "+kind+".");
                    break;
                case "basic":
                    maxShields = 3;
                    maxShots = 3;
                    System.out.println(playerName + " Launched "+kind+".");
                    break;
                case "tank":
                    maxShields = 5;
                    maxShots = 1;
                    System.out.println(playerName + " Launched "+kind+".");
                    break;
                default:
                    System.out.println("Invalid robot kind. Please choose from sniper, basic, tank.");
                    return null;
            }

            ArrayList<Object> arguments = new ArrayList<>();
            arguments.add(kind);
            arguments.add(maxShields);
            arguments.add(maxShots);

            Map<String, Object> requestMessage = new LinkedHashMap<>();
            requestMessage.put("robot", playerName);
            requestMessage.put("command", command);
            requestMessage.put("arguments", arguments);

            Gson gson = new Gson();
            JsonObject jO = gson.fromJson(gson.toJsonTree(requestMessage), JsonObject.class);
            return jO.toString();
        } else {
            if (tings.length > 1) {
                try {
                    distance = Integer.parseInt(tings[1]);
                } catch (NumberFormatException e) {
                    // Ignore if the distance is not a valid integer
                }
            }

            ArrayList<Object> arguments = new ArrayList<>();
            arguments.add(distance);

            Map<String, Object> requestMessage = new LinkedHashMap<>();
            requestMessage.put("robot", playerName);
            requestMessage.put("command", command);
            requestMessage.put("arguments", arguments);

            Gson gson = new Gson();
            JsonObject jO = gson.fromJson(gson.toJsonTree(requestMessage), JsonObject.class);
            return jO.toString();
        }
//        return command;
    }
}
