package za.co.wethinkcode.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;



public class LookResult extends ResponseHandler{

    private JsonObject response;
    private JsonElement data, state, objects;

    public LookResult(ObjectInputStream inputStream, String userCommand){

        super(inputStream, userCommand);
    }

    @Override
    public String getData() {
        data = response.get("data");
        JsonObject dataObject = data.getAsJsonObject();
        return dataObject.get("message").getAsString();
    }

    @Override
    public ArrayList<String> getObjects() {
        objects = response.get("objects");

//        JsonArray stateObject = objects.getAsJsonArray();
//        ArrayList<String> object = new ArrayList<>();
//
//        Map<String, String> objectMap = new LinkedHashMap<>();
//
//        for (int i= 0; i < stateObject.size(); i++){
//
//
//        }
//
//        String direction = stateObject.get("direction").getAsString();
//        String type = stateObject.get("type").getAsString();
//        String distance = stateObject.get("distance").getAsString();

        return new ArrayList<>();
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
