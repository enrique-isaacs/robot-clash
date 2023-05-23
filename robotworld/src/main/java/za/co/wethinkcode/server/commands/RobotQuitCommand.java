package za.co.wethinkcode.server.commands;

import za.co.wethinkcode.server.robotLab.AbstractBot;
import za.co.wethinkcode.server.serverInterface.ResponseBuilder;

public class RobotQuitCommand extends Command {

    /**
     * Initializes a "quit" command.
     */
    public RobotQuitCommand() {
        super("quit");
    }

    /**
     * Executes the "quit" command on the target bot.
     *
     * @param target           The target bot to execute the command on.
     * @param responseBuilder  The response builder to construct the response.
     * @return False to indicate that the command is not executed and the bot should quit.
     */
    @Override
    public boolean execute(AbstractBot target, ResponseBuilder responseBuilder) {
        return false;
    }
}
