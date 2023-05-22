package za.co.wethinkcode.server.commands;


import za.co.wethinkcode.server.commands.Command;
import za.co.wethinkcode.server.robotLab.AbstractBot;
import za.co.wethinkcode.server.serverInterface.ResponseBuilder;

public class ReloadCommand extends Command {
    public ReloadCommand() {
        super("reload");
    }

    @Override
    public boolean execute(AbstractBot target, ResponseBuilder responseBuilder) {


        switch(target.getModel()){
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