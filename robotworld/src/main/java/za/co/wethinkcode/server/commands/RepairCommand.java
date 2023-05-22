package za.co.wethinkcode.server.commands;

import za.co.wethinkcode.server.commands.Command;
import za.co.wethinkcode.server.robotLab.AbstractBot;
import za.co.wethinkcode.server.serverInterface.ResponseBuilder;

public class RepairCommand extends Command {
    public RepairCommand() {
        super("repair");
    }

    @Override
    public boolean execute(AbstractBot target, ResponseBuilder responseBuilder) {


        switch(target.getModel()){
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
