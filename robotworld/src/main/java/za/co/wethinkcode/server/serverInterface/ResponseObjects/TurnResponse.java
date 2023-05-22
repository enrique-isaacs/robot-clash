package za.co.wethinkcode.server.serverInterface.ResponseObjects;

import com.google.gson.JsonObject;
import za.co.wethinkcode.server.serverInterface.ResponseBuilder;
import za.co.wethinkcode.server.world.WORLD;

public class TurnResponse extends ResponseBuilder{

    public TurnResponse(WORLD world) {
        super(world);
    }

    public JsonObject buildResponseMessage(String name){

        if(getResponseStatus().equals("OK")){
            this.responseMessage.put("result", getResponseStatus());
            this.responseMessage.put("data", buildDataMessage());
            this.responseMessage.put("state", this.world.getBot(name).getState());
        }
        else if(getResponseStatus().equals("ERROR")){
            this.responseMessage.put("result", getResponseStatus());
            this.responseMessage.put("data", getErrorMessage());
        }
        // if statements wont be necessary for turn ..


        return gson.fromJson(gson.toJsonTree(this.responseMessage), JsonObject.class);

    }

}