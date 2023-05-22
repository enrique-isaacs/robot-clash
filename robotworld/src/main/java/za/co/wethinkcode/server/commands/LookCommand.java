package za.co.wethinkcode.server.commands.commands;

import za.co.wethinkcode.server.commands.Command;
import za.co.wethinkcode.server.robotLab.AbstractBot;
import za.co.wethinkcode.server.serverInterface.ResponseBuilder;

public class LookCommand extends Command {

    public LookCommand(){
        super("look");
    }

    @Override
    public boolean execute(AbstractBot target, ResponseBuilder responseBuilder){
        return true;
    }
}
