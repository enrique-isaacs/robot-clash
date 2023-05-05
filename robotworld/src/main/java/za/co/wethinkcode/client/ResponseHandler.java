package za.co.wethinkcode.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import com.google.gson.*;

public class ResponseHandler {

    private String result, data, shields ,jsonRequest, userCommand;
    private int[] position;
    private int visibility, reload, repair;
    private ObjectInputStream inputStream;
    private JsonObject response;

    public ResponseHandler(ObjectInputStream inputStream, String userCommand) throws IOException, ClassNotFoundException {
        this.inputStream = inputStream;
        jsonRequest = (String) inputStream.readObject();
        JsonObject response = JsonParser.parseString(jsonRequest).getAsJsonObject();
    }

//    public void convertToJsonObject(String jsonRequest) {
//
//    }

    public String getResult() {
        return result;
    }

    public String getData() {return data;}

    public int geVisibility() {return visibility;}

    public int getReload() {return reload;}

    public int getRepair() {return repair;}
}
//
//ResponseaHandler(stirng userCommand, string messServer)
//    condition(userComm)
//
//
