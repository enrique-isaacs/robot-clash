package za.co.wethinkcode.server.commands;

import za.co.wethinkcode.server.robotLab.AbstractBot;
import za.co.wethinkcode.server.serverInterface.ResponseBuilder;

public class ForwardCommand extends Command {

    /**
     * Executes the "forward" command on the target bot.
     *
     * @param target           The target bot to execute the command on.
     * @param responseBuilder  The response builder to construct the response.
     * @return True if the command is executed successfully, false otherwise.
     */
    @Override
    public boolean execute(AbstractBot target, ResponseBuilder responseBuilder) {
        int nrSteps = Integer.parseInt(getArgument());
        String updatePositionResult = target.updatePosition(nrSteps);

        switch (updatePositionResult.toUpperCase()) {
            case "SUCCESS":
                responseBuilder.setResponseStatus("OK");
                responseBuilder.setDataMessage("Done");
                break;
            case "OBSTRUCTED":
            case "OUTSIDE_WORLD":
                responseBuilder.setResponseStatus("ERROR");
                responseBuilder.setErrorMessage("Obstructed");
                break;
            default:
                break;
        }

        return true;
    }

    /**
     * Initializes a "forward" command with the specified argument.
     *
     * @param argument The argument of the command.
     */
    public ForwardCommand(String argument) {
        super("forward", argument);
    }
}
