package za.co.wethinkcode.server.serverInterface;

import com.google.gson.JsonObject;
import za.co.wethinkcode.server.world.WORLD;


import java.io.*;
import java.net.Socket;

/**
 * The ClientHandler class handles communication with a single client connected to the server.
 * It receives requests from the client, processes them, and sends back responses.
 */
class ClientHandler extends Thread {
    private Socket socket;
    private Processor processor;
    private boolean shouldContinue;
    private WORLD world;
    private ResponseBuilder responseBuilder;
    private BufferedWriter outputStream;
    private BufferedReader inputStream;

    /**
     * Constructs a ClientHandler instance with the specified socket and world.
     *
     * @param socket The Socket instance representing the client connection.
     * @param world  The WORLD instance representing the game world.
     */
    public ClientHandler(Socket socket, WORLD world) {
        this.socket = socket;
        this.world = world;
        this.responseBuilder = new ResponseBuilder(this.world);
        this.shouldContinue = true;
    }

    /**
     * Sends the response to the client.
     *
     * @param outputStream The BufferedWriter for writing the response.
     * @param responseType The JsonObject representing the response.
     * @throws IOException If an I/O error occurs while sending the response.
     */
    public void responseSender(BufferedWriter outputStream, JsonObject responseType) throws IOException {
        System.out.println(responseType.toString());
        outputStream.write(responseType.toString());
        outputStream.newLine();
        outputStream.flush();
    }

    @Override
    public void run() {
        try {
            outputStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message = null;
            String playerName = null;

            while (shouldContinue) {
                message = inputStream.readLine();
                if (message == null) {
                    break;
                }

                System.out.println(message);
                RequestHandler request = new RequestHandler(message);
                playerName = request.getName();

                if (request.requestIsValid()) {
                    responseBuilder = responseBuilder.buildResponse(request.getCommand());
                    if (request.getCommand().equals("repair") || request.getCommand().equals("reload")) {
                        sleep(5 * 1000);
                    }
                    Processor processor = new Processor(world, responseBuilder);
                    shouldContinue = processor.commandProcessor(request);
                } else {
                    if (request.getArguments()[0].equals("Arguments invalid")) {
                        responseBuilder = responseBuilder.buildResponse(" ");
                    }
                    responseBuilder = responseBuilder.buildResponse("Non-command");
                }

                System.out.println(responseBuilder);
                responseSender(outputStream, responseBuilder.buildResponseMessage(request.getName()));
            }

            if (playerName != null && this.world.getListOfRobots().size() > 0) {
                this.world.removeBot(playerName);
            }

            inputStream.close();
            outputStream.close();
            socket.close();
            System.out.println("Client disconnected: " + socket);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
