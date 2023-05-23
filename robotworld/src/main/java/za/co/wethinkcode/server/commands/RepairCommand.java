package za.co.wethinkcode.server.commands;

import za.co.wethinkcode.server.robotLab.AbstractBot;
import za.co.wethinkcode.server.serverInterface.ResponseBuilder;

public class RepairCommand extends Command {

    /**
     * Initializes a "repair" command.
     */
    public RepairCommand() {
        super("repair");
    }

    /**
     * Executes the "repair" command on the target bot.
     *
     * @param target           The target bot to execute the command on.
     * @param responseBuilder  The response builder to construct the response.
     * @return True if the command is executed successfully, false otherwise.
     */
    @Override
    public boolean execute(AbstractBot target, ResponseBuilder responseBuilder) {
        switch(target.getModel()) {
            case "sniper":
                target.setShields(1);
                break;
            default:
                target.setShields(4);
                break;
        }

        responseBuilder.setResponseStatus("OK");
        responseBuilder.setDataMessage("Done");
        target.setStatus("REPAIR");

        return true;
    }
}
