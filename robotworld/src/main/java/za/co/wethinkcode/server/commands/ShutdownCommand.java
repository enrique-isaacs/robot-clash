package za.co.wethinkcode.server.commands;

import za.co.wethinkcode.server.robotLab.AbstractBot;

public class ShutdownCommand extends Command{

    public ShutdownCommand() {
        super("off");
    }

    @Override
    public boolean execute(AbstractBot target) {
        target.setStatus("Shutting down...");
        return false;
    }
}
