package za.co.wethinkcode.server.serverInterface;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class RequestHandler {

    public String name;

    public String command;

    public String[] validCommands = {"forward","back","turn","launch","fire","reload","quit","robots","state","look"};

    public String[] validArguments = {"left","right"};

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
            JsonArray jsonArray = this.request.get("arguments").getAsJsonArray();
            String[] arguments = new String[jsonArray.size()];
            for (int i = 0; i < jsonArray.size(); i++) {
                arguments[i] = jsonArray.get(i).getAsString();
            }
            this.arguments = arguments;
        }
        return this.arguments;
    }

    public String[] validRequest(){
        boolean validCommand = false;
        for(String command: this.validCommands){
            if(getCommand().contains(command)){
                validCommand = true;
                break;
            }
        }

        if (!validCommand) {
            return new String[] {"error","Unsupported command"};
        }

        boolean validArgument = true;
        for(String argument: getArguments()){
            if(!argument.matches("\\d+") && !Arrays.asList(validArguments).contains(argument)){
                validArgument = false;
                break;
            }
        }

        if (!validArgument) {
            return new String[] {"error","Could not parse arguments"};
        }

        return new String[] {"ok"};
    }


}