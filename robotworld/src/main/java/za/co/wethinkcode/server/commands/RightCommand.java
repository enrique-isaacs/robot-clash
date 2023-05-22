package za.co.wethinkcode.server.commands;


import za.co.wethinkcode.server.commands.Command;
import za.co.wethinkcode.server.robotLab.AbstractBot;
import za.co.wethinkcode.server.serverInterface.ResponseBuilder;

public class RightCommand extends Command {
    @Override
    public boolean execute(AbstractBot target, ResponseBuilder responseBuilder) {
        target.updateDirection(true);
        responseBuilder.setResponseStatus("OK");
        responseBuilder.setDataMessage("Done");
        return true;
    }
    public RightCommand() {super("right");}
}
