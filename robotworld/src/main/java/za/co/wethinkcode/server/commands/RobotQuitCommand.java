package za.co.wethinkcode.server.commands;

import za.co.wethinkcode.server.commands.Command;
import za.co.wethinkcode.server.robotLab.AbstractBot;
import za.co.wethinkcode.server.serverInterface.ResponseBuilder;

public class RobotQuitCommand extends Command {

    public RobotQuitCommand() {
        super("quit");
    }

    @Override
    public boolean execute(AbstractBot target, ResponseBuilder responseBuilder) {
        return false;
    }
}
