package za.co.wethinkcode.server.commands;

import za.co.wethinkcode.server.commands.Command;
import za.co.wethinkcode.server.robotLab.AbstractBot;
import za.co.wethinkcode.server.serverInterface.ResponseBuilder;

public class ErrorHandling extends Command {

    public ErrorHandling() {
        super("error");
    }

    @Override
    public boolean execute(AbstractBot target, ResponseBuilder responseBuilder) {
        responseBuilder.setResponseStatus("ERROR");
        responseBuilder.setErrorMessage("Unsupported Command.");
        return true;
    }
}
