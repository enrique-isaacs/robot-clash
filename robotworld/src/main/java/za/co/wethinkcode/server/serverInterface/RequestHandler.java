package za.co.wethinkcode.server.serverInterface;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class RequestHandler {

    public String name;

    public String command;

    public String[] arguments;

    public JsonObject request;
    public RequestHandler(String request){
        this.request = requestToJson(request);
    }
    public JsonObject requestToJson(String request){
        this.request = JsonParser.parseString(request).getAsJsonObject();
        return this.request;
    }

    public String getName(){
        if (this.request.has("robot")) {
            this.name = this.request.get("robot").getAsString();
        }
        return this.name;
    }

    public String getCommand(){
        if (this.request.has("command")) {
            this.command = this.request.get("command").getAsString();
        }
        return this.command;
    }

    public String[] getArguments() {
        if (this.request.has("arguments")) {
            this.arguments = this.request.get("arguments").getAsJsonArray();
        }
        return this.arguments;
    }
}