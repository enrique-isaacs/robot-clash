package za.co.wethinkcode.server.serverInterface.ResponseObjects;

import com.google.gson.JsonObject;
import za.co.wethinkcode.server.serverInterface.ResponseBuilder;
import za.co.wethinkcode.server.world.WORLD;

//yo quick thing, can you leave the lookResponse blank for now.. while i work on the look command.. cos you will need to get some data
//from the robot in order to build this response
//
//??okay

import java.util.Map;

public class LookResponse extends ResponseBuilder {

    public LookResponse(WORLD world) {
        super(world);
    }

    private Map<String, Object> buildDataMap(String name){

        System.out.println(name);

        int x = this.world.getBot(name).getCurrentPosition().getX();
        int y = this.world.getBot(name).getCurrentPosition().getY();
        Integer[] robotPosition = new Integer[]{x,y};
        int robotVisibility = this.world.getBot(name).getVisibility();
        int robotReloadTime = this.world.getBot(name).getReload();
        int robotRepairTime = this.world.getBot(name).getRepair();
        int robotShieldStrength = this.world.getBot(name).getShields();

        this.dataMap.put("position", robotPosition);
        this.dataMap.put("visibility", robotVisibility);
        this.dataMap.put("reload", robotReloadTime);
        this.dataMap.put("repair", robotRepairTime);
        this.dataMap.put("shields", robotShieldStrength);

        return this.dataMap;

    }

    public JsonObject buildResponseMessage(String name){

        if(getResponseStatus().equals("OK")){
            this.responseMessage.put("result", getResponseStatus());
            this.responseMessage.put("data", buildDataMap(name));
            this.responseMessage.put("state", this.world.getBot(name).getState());
        }
        else if(getResponseStatus().equals("ERROR")){
            this.responseMessage.put("result", getResponseStatus());
            this.responseMessage.put("data", getErrorMessage());
        }


        return gson.fromJson(gson.toJsonTree(this.responseMessage), JsonObject.class);

    }


}
