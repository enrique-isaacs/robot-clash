package za.co.wethinkcode.client;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import za.co.wethinkcode.server.world.Position;

import java.io.ObjectInputStream;
import java.util.Arrays;

public class ForwardResult extends ResponseHandler{

    private JsonObject response;
    private JsonElement data, state;

    public ForwardResult(ObjectInputStream inputStream, String userCommand){

        super(inputStream, userCommand);
    }

    @Override
    public String getData() {
        data = response.get("data");
        JsonObject dataObject = data.getAsJsonObject();

        return dataObject.get("message").getAsString();
    }

    @Override
    public String getState() {
        state = response.get("state");
        JsonObject stateObject = state.getAsJsonObject();

        String position = stateObject.get("position").getAsString();
        String status = stateObject.get("status").getAsString();
        String direction = stateObject.get("direction").getAsString();

        return position + " " + status + " " + direction;
    }
}

