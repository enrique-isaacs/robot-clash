package za.co.wethinkcode.server.commands;

import za.co.wethinkcode.server.robotLab.AbstractBot;
import za.co.wethinkcode.server.serverInterface.ResponseBuilder;

public class RightCommand extends Command {

    /**
     * Initializes a "right" command.
     */
    public RightCommand() {
        super("right");
    }

    /**
     * Executes the "right" command on the target bot.
     *
     * @param target           The target bot to execute the command on.
     * @param responseBuilder  The response builder to construct the response.
     * @return True if the command is executed successfully, false otherwise.
     */
    @Override
    public boolean execute(AbstractBot target, ResponseBuilder responseBuilder) {
        target.updateDirection(true);
        responseBuilder.setResponseStatus("OK");
        responseBuilder.setDataMessage("Done");
        return true;
    }
}
