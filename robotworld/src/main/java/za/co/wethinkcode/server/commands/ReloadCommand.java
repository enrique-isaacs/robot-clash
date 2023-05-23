package za.co.wethinkcode.server.commands;

import za.co.wethinkcode.server.robotLab.AbstractBot;
import za.co.wethinkcode.server.serverInterface.ResponseBuilder;

public class ReloadCommand extends Command {

    /**
     * Initializes a "reload" command.
     */
    public ReloadCommand() {
        super("reload");
    }

    /**
     * Executes the "reload" command on the target bot.
     *
     * @param target           The target bot to execute the command on.
     * @param responseBuilder  The response builder to construct the response.
     * @return True if the command is executed successfully, false otherwise.
     */
    @Override
    public boolean execute(AbstractBot target, ResponseBuilder responseBuilder) {
        switch(target.getModel()) {
            case "sniper":
                target.setShots(4);
                break;
            default:
                target.setShots(3);
                break;
        }

        responseBuilder.setResponseStatus("OK");
        responseBuilder.setDataMessage("Done");
        target.setStatus("RELOAD");

        return true;
    }
}
