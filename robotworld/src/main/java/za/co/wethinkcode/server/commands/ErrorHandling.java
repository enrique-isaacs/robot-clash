package za.co.wethinkcode.server.commands;

import za.co.wethinkcode.server.robotLab.AbstractBot;
import za.co.wethinkcode.server.serverInterface.ResponseBuilder;

public class ErrorHandling extends Command {

    /**
     * Initializes an "error" command.
     */
    public ErrorHandling() {
        super("error");
    }

    /**
     * Executes the "error" command on the target bot.
     *
     * @param target           The target bot to execute the command on.
     * @param responseBuilder  The response builder to construct the response.
     * @return True if the command is executed successfully, false otherwise.
     */
    @Override
    public boolean execute(AbstractBot target, ResponseBuilder responseBuilder) {
        responseBuilder.setResponseStatus("ERROR");
        responseBuilder.setErrorMessage("Unsupported Command.");
        return true;
    }
}
