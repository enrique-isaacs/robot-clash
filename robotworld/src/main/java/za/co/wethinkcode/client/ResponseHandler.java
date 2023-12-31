package za.co.wethinkcode.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import com.google.gson.*;

public abstract class ResponseHandler {

    private String result, data, shields ,jsonRequest, userCommand, state;
    private ArrayList<String> objects;
    private int[] position;
    private int visibility, reload, repair, shots;
    private ObjectInputStream inputStream;
    private JsonObject response;

    public ResponseHandler(ObjectInputStream inputStream, String userCommand)  {
        this.inputStream = inputStream;
        this.userCommand = userCommand;
        try{
            jsonRequest = (String) inputStream.readObject();
            JsonObject response = JsonParser.parseString(jsonRequest).getAsJsonObject();
        }
        catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public ResponseHandler createHandler(){
        if (getResult().equalsIgnoreCase("ok")){
            switch(userCommand){
                case "forward":
                    return new ForwardResult(this.inputStream, this.userCommand);
                case "back":
                   return new BackResult(this.inputStream, this.userCommand);
                case "turn":
                    return new TurnResult(this.inputStream, this.userCommand);
            }
        } // else return error object
        return new ForwardResult(this.inputStream, this.userCommand);
    }

    public String getResult() {
        String result = response.get("result").getAsString();
        return result;
    }

    public String getData() {return data;}

    public int geVisibility() {return visibility;}

    public int getReload() {return reload;}

    public int getRepair() {return repair;}

    public ArrayList<String> getObjects() {return objects;};

    public String getState() {return state;}

}
