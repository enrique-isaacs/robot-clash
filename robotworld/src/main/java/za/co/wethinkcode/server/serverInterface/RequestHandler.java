package za.co.wethinkcode.server.serverInterface;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Arrays;
import java.util.ArrayList;

/**
 * The RequestHandler class handles parsing and validation of incoming requests.
 */
public class RequestHandler {

    private String name;
    private String command;
    private String[] validCommands = {"launch","turn" ,"forward", "back", "fire","reload","repair","look"};
    private String[] movementCommands = {"forward", "back"};
    private String[] arguments;
    private JsonObject request;
    private String[] returnArguments;

    /**
     * Constructs a RequestHandler object.
     * @param request the incoming request to handle
     */
    public RequestHandler(String request){
        this.request = requestToJson(request);
    }

    /**
     * Converts the incoming request string to a JsonObject.
     * @param request the incoming request string
     * @return the JsonObject representation of the request
     */
    private JsonObject requestToJson(String request){
        try{
            this.request = JsonParser.parseString(request).getAsJsonObject();
        }
        catch(IllegalStateException e){
            String nullRequest = "{\"null\": \"null\"}";
            this.request = JsonParser.parseString(nullRequest).getAsJsonObject();
        }
        catch(NullPointerException n){
            String nullRequest = "{\"null\": \"null\"}";
            this.request = JsonParser.parseString(nullRequest).getAsJsonObject();
        }

        return this.request;
    }

    /**
     * Retrieves the name from the request.
     * @return the name from the request, or an empty string if not present
     */
    public String getName(){
        if (this.request.has("robot")) {
            this.name = this.request.get("robot").getAsString();
        }
        return this.name;
    }

    /**
     * Retrieves the command from the request.
     * @return the command from the request, or an empty string if not present or invalid
     */
    public String getCommand(){
        if (this.request.has("command")) {
            this.command = this.request.get("command").getAsString();
            if(commandIsValid(this.command)){
                return this.command;
            }
        }
        return "";
    }

    /**
     * Checks if the provided command is valid.
     * @param command the command to validate
     * @return true if the command is valid, false otherwise
     */
    private boolean commandIsValid(String command){
        if(Arrays.asList(validCommands).contains(command)){
            return true;
        }
        return false;
    }

    /**
     * Retrieves the arguments from the request.
     * @return the arguments from the request as an array, or an array with a single "Arguments invalid" element if not present or invalid
     */
    public String[] getArguments() {
        if (this.request.has("arguments")) {
            JsonArray jsonArray = this.request.get("arguments").getAsJsonArray();
            ArrayList<String> requestArguments = new ArrayList<>();
            for(JsonElement jsonItems : jsonArray){
                requestArguments.add(jsonItems.toString());
            }
            if(argumentIsValid(requestArguments)){
                return formattedArguments(requestArguments);
            }
        }
        return new String[]{"Arguments invalid"};
    }

    /**
     * Formats the request arguments based on the command.
     * @param requestArguments the request arguments to format
     * @return the formatted request arguments as an array
     */
    private String[] formattedArguments(ArrayList<String> requestArguments){
        String command = this.request.get("command").getAsString();
        String[] emptyArguments = new String[0];

        switch(command){
            case "launch":
                returnArguments = new String[requestArguments.size()];
                for (int i = 0; i < requestArguments.size(); i++) {
                    Object arg = requestArguments.get(i);
                    if (i == 0) {
                        returnArguments[i] = arg.toString();
                    } else {
                        returnArguments[i] = String.valueOf(arg);
                    }
                }
                return returnArguments;

            case "forward":
            case "back":
            case "turn":
                String[] stringArray = new String[requestArguments.size()];
                for (int i = 0; i < requestArguments.size(); i++) {
                    stringArray[i] = requestArguments.get(i).toString();
                }
                return stringArray;

            default:
                return emptyArguments;
        }
    }

    /**
     * Validates the request arguments based on the command.
     * @param arguments the request arguments to validate
     * @return true if the arguments are valid, false otherwise
     */
    private boolean argumentIsValid(ArrayList<String> arguments){
        String command = this.request.get("command").getAsString();

        if(command.equals("launch") && arguments.size() == 3){
            if((arguments.get(0).getClass() == String.class) &&
                    (isInt(arguments.get(1))) &&
                    (isInt(arguments.get(2)))){
                return true;
            }
        }
        else if(Arrays.asList(movementCommands).contains(command) && arguments.size() == 1){
            if((isInt(arguments.get(0))) && arguments.size() == 1){
                return true;
            }
        }
        else if(command.equals("turn") && arguments.size() == 1){
            if((arguments.get(0) instanceof String)){
                return true;
            }
        }
        else if(Arrays.asList(validCommands).contains(command) && (arguments.size() == 0)){
            return true;
        }
        return false;
    }

    /**
     * Checks if the request is valid.
     * @return true if the request is valid, false otherwise
     */
    public boolean requestIsValid(){
        if(!getCommand().equals("") || (!getArguments()[0].equals("Arguments invalid")) ){
            return true;
        }
        return false;
    }

    /**
     * Checks if a string can be parsed as an integer.
     * @param value the string value to check
     * @return true if the value can be parsed as an integer, false otherwise
     */
    private boolean isInt(String value){
        try{
            int val = Integer.parseInt(value);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}
