package za.co.wethinkcode.server.commands;

import za.co.wethinkcode.server.commands.Command;
import za.co.wethinkcode.server.robotLab.AbstractBot;
import za.co.wethinkcode.server.serverInterface.ResponseBuilder;
import za.co.wethinkcode.server.world.Direction;
import za.co.wethinkcode.server.world.Position;

public class FireCommand extends Command {

    public FireCommand(){
        super("fire");
    }

    @Override
    public boolean execute(AbstractBot target, ResponseBuilder responseBuilder) {
        AbstractBot bot = null;
        int distance = 0;

        if(target.getCurrentDirection() == Direction.NORTH){
            for(int i = 1; i < target.getVisibility(); i++){
                bot = target.getWorld().robotInFireRange(new Position(target.getCurrentPosition().getX(), target.getCurrentPosition().getY()+i));
                distance = distance + i;
                if(bot!=null){
                    break;
                }
            }

            if(bot != null){
                bot.takeHit();
            }



        }
        else if (target.getCurrentDirection() == Direction.SOUTH) {
            for(int i = 1; i < target.getVisibility(); i++){
                target.getWorld().robotInFireRange(new Position(target.getCurrentPosition().getX(), target.getCurrentPosition().getY()-i));
                distance = distance + i;
                if(bot!=null){
                    break;
                }
            }
            if(bot != null){
                bot.takeHit();
            }
        }
        else if (target.getCurrentDirection() == Direction.EAST){
            for(int i = 1; i < target.getVisibility(); i++){
                target.getWorld().robotInFireRange(new Position(target.getCurrentPosition().getX()+i, target.getCurrentPosition().getY()));
                distance = distance + i;
                if(bot!=null){
                    break;
                }
            }
            if(bot != null){
                bot.takeHit();
            }
        }
        else if (target.getCurrentDirection() == Direction.WEST){
            for(int i = 1; i < target.getVisibility(); i++){
                target.getWorld().robotInFireRange(new Position(target.getCurrentPosition().getX()-i, target.getCurrentPosition().getY()));
                distance = distance + i;
                if(bot!=null){
                    break;
                }
            }
            if(bot != null){
                bot.takeHit();
            }
        }


        responseBuilder.setResponseStatus("OK");
        if(bot==null){
            responseBuilder.setDataMessage("Miss");
        }
        else if(bot!=null){
            responseBuilder.setDataMessage("Hit");
            responseBuilder.setRobotHit(bot);
            responseBuilder.setFireDistance(distance);
            responseBuilder.setRobotHitName(bot);
        }


        return true;
    }
}
