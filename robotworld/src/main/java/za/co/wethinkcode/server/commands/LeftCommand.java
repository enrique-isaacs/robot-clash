package za.co.wethinkcode.server.commands;

import za.co.wethinkcode.server.robotLab.AbstractBot;
import za.co.wethinkcode.server.serverInterface.ResponseBuilder;

public class LeftCommand extends Command {

    /**
     * Initializes a "left" command.
     */
    public LeftCommand() {
        super("left");
    }

    /**
     * Executes the "left" command on the target bot.
     *
     * @param target           The target bot to execute the command on.
     * @param responseBuilder  The response builder to construct the response.
     * @return True if the command is executed successfully, false otherwise.
     */
    @Override
    public boolean execute(AbstractBot target, ResponseBuilder responseBuilder) {
        target.updateDirection(false);
        responseBuilder.setResponseStatus("OK");
        responseBuilder.setDataMessage("Done");
        return true;
    }
}
