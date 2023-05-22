package za.co.wethinkcode.server.commands;

import za.co.wethinkcode.server.commands.Command;
import za.co.wethinkcode.server.robotLab.AbstractBot;
import za.co.wethinkcode.server.serverInterface.ResponseBuilder;

public class BackCommand extends Command {
    @Override
    public boolean execute(AbstractBot target, ResponseBuilder responseBuilder) {
        int nrSteps = Integer.parseInt(getArgument());
        String updatePositionResult = target.updatePosition(-nrSteps);
        switch (updatePositionResult.toUpperCase()) {
            case "SUCCESS":
                responseBuilder.setResponseStatus("OK");
                responseBuilder.setDataMessage("Done");
                break;
            case "OBSTRUCTED":
            case "FAILED_OUTSIDE_WORLD":
                responseBuilder.setResponseStatus("ERROR");
                responseBuilder.setErrorMessage("Obstructed");
                break;
            default:
                // Handle any other cases here
                break;
        }

        return true;
    }

    public BackCommand(String argument) {
        super("back", argument);
    }
}


