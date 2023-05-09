package za.co.wethinkcode.client;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.ObjectInputStream;

public class FireResult extends ResponseHandler{

    private JsonObject response;
    private JsonElement data, state, objects;

    public FireResult(ObjectInputStream inputStream, String userCommand){

        super(inputStream, userCommand);
    }

    @Override
    public String getData() {
        data = response.get("data");
        JsonObject dataObject = data.getAsJsonObject();

        String message = dataObject.get("message").getAsString();
        String distance = dataObject.get("distance").getAsString();
        String robot = dataObject.get("robot").getAsString();

        return message + " " + distance + " " + robot;
    }

    @Override
    public String getState() {
        state = response.get("state");

        JsonObject stateObject = state.getAsJsonObject();
        String shots = stateObject.get("shots").getAsString();

        return shots;
    }
}
