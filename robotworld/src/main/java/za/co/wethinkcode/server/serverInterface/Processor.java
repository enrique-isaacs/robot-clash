package za.co.wethinkcode.server.serverInterface;

import za.co.wethinkcode.server.commands.Command;
import za.co.wethinkcode.server.world.WORLD;

import java.util.Arrays;

/**
 * The Processor class handles processing of incoming commands and requests.
 */
public class Processor {

    private WORLD world;
    private ResponseBuilder responseBuilder;

    private String[] commandsWithArguments = new String[]{"forward", "back", "turn"};

    /**
     * Constructs a Processor object.
     * @param world the instance of the game world
     * @param responseBuilder the response builder to generate responses
     */
    public Processor(WORLD world, ResponseBuilder responseBuilder){
        this.world = world;
        this.responseBuilder = responseBuilder;
    }

    /**
     * Processes a command based on the provided request.
     * @param request the request handler containing the command details
     * @return true if the command was processed successfully, false otherwise
     */
    public boolean commandProcessor(RequestHandler request){

        boolean isCommandProcessed = false;

        if(request.getCommand().contains("launch")){
            isCommandProcessed = launchCommand(request);
        }
        else{
            if(this.world.containsBot(request.getName())){

                if(Arrays.asList(commandsWithArguments).contains(request.getCommand())){
                    Command command = Command.create(request.getCommand() + " " + request.getArguments()[0]);
                    isCommandProcessed = this.world.getBot(request.getName()).handleCommand(command, responseBuilder);
                }
                else{
                    Command command = Command.create(request.getCommand());
                    isCommandProcessed = this.world.getBot(request.getName()).handleCommand(command, responseBuilder);
                }
            }
        }

        return isCommandProcessed;
    }

    /**
     * Processes the "launch" command.
     * @param request the request handler containing the launch command details
     * @return true if the launch command was processed successfully, false otherwise
     */
    private boolean launchCommand(RequestHandler request){

        if(!this.world.containsBot(request.getName())){
            String make = request.getArguments()[0];
            int shields = Integer.parseInt(request.getArguments()[1]);
            int shots = Integer.parseInt(request.getArguments()[2]);
            this.world.addRobots(make,request.getName(), shields, shots);
            responseBuilder.setResponseStatus("OK");
        }
        else{
            responseBuilder.setResponseStatus("ERROR");
            responseBuilder.setErrorMessage("Too many of you in this world");
        }
        return true;
    }
}
