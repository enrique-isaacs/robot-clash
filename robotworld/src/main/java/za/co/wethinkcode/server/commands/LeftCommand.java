package za.co.wethinkcode.server.commands;

import za.co.wethinkcode.server.robotLab.AbstractBot;

public class LeftCommand extends Command {
    @Override
    public boolean execute(AbstractBot target) {
        target.getWorld().updateDirection(false);
        target.setStatus("Turned left.");
        return true;
    }
    public LeftCommand() {super("left");}
}
