package za.co.wethinkcode.server.commands;

import za.co.wethinkcode.server.robotLab.AbstractBot;

public class ForwardCommand extends Command {
    @Override
    public boolean execute(AbstractBot target) {
        int nrSteps = Integer.parseInt(getArgument());
//        if (target.updatePosition(-nrSteps) == target.UpdateResponse.SUCCESS){
//            target.setStatus("Moved forward by "+nrSteps+" steps.");
//        } else {
//            target.setStatus("Sorry, I cannot go outside my safe zone.");
//        }
        return true;
    }

    public ForwardCommand(String argument) {
        super("forward", argument);
    }
}

