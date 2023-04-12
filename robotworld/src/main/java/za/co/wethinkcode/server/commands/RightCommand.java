package za.co.wethinkcode.server.commands;


import za.co.wethinkcode.server.robotLab.AbstractBot;

public class RightCommand extends Command {
    @Override
    public boolean execute(AbstractBot target) {
        target.getWorld().updateDirection(true);
        target.setStatus("Turned right.");
        return true;
    }
    public RightCommand() {super("right");}
}
