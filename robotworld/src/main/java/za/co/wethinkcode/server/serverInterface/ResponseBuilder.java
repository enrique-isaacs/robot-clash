package za.co.wethinkcode.server.serverInterface;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import za.co.wethinkcode.server.robotLab.AbstractBot;
import za.co.wethinkcode.server.serverInterface.ResponseObjects.*;
import za.co.wethinkcode.server.world.WORLD;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The ResponseBuilder class is responsible for building response messages based on the request type and status.
 */
public class ResponseBuilder {

    private String responseStatus;
    private String errorMessage;
    private String dataMessage;

    private int fireDistance;
    private String robotHitName;

    private AbstractBot robotHit;

    protected Map<String, Object> dataMap;
    protected Map<String, Object> responseMessage;
    protected final WORLD world;
    protected Gson gson;
    protected Map<String, String> dataMessageMap;

    /**
     * Constructs a ResponseBuilder object.
     * @param world the world object representing the game world
     */
    public ResponseBuilder(WORLD world){
        this.dataMap = new LinkedHashMap<>();
        this.responseMessage = new LinkedHashMap<>();
        this.world = world;
        gson = new Gson();
        dataMessageMap = new LinkedHashMap<>();
    }

    /**
     * Sets the response status.
     * @param processResult the response status to set
     */
    public synchronized void setResponseStatus(String processResult){
        responseStatus = processResult;
    }

    /**
     * Sets the error message.
     * @param errorMessageX the error message to set
     */
    public synchronized void setErrorMessage(String errorMessageX){
        errorMessage = errorMessageX;
    }

    /**
     * Sets the data message.
     * @param dataMessageX the data message to set
     */
    public synchronized void setDataMessage(String dataMessageX){
        dataMessage = dataMessageX;
    }

    /**
     * Retrieves the response status.
     * @return the response status
     */
    public synchronized String getResponseStatus() {
        return responseStatus;
    }

    /**
     * Retrieves the error message.
     * @return the error message
     */
    public synchronized String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Retrieves the data message.
     * @return the data message
     */
    public synchronized String getDataMessage() {
        return dataMessage;
    }

    /**
     * Builds the data message map.
     * @return the data message map
     */
    public synchronized Map<String, String> buildDataMessage(){
        this.dataMessageMap.put("message", getDataMessage());
        return dataMessageMap;
    }

    /**
     * Builds the appropriate response object based on the request type.
     * @param request the request type
     * @return the appropriate response object
     */
    public synchronized ResponseBuilder buildResponse(String request){
        if(request.equals("launch")){
            return new LaunchResponse(this.world);
        }
        else if(request.equals("forward")){
            return new ForwardResponse(this.world);
        }
        else if(request.equals("back")){
            return new BackResponse(this.world);
        }
        else if(request.equals("turn")){
            return new TurnResponse(this.world);
        }
        else if(request.equals("reload")){
            return new ReloadResponse(this.world);
        }
        else if(request.equals("repair")){
            return new RepairResponse(this.world);
        }
        else if(request.equals("Non-command")){
            return new CommandErrorResponse(this.world);
        }
        else if(request.equals("fire")){
            return new FireResponse(this.world);
        }
        else{
            return new ArgumentErrorResponse(this.world);
        }
    }

    /**
     * Builds the data map for the response.
     * @param name the name of the robot
     * @return the data map
     */
    private Map<String, Object> buildDataMap(String name){
        return this.dataMap;
    }

    /**
     * Builds the response message.
     * @param name the name of the robot
     * @return the response message as a JsonObject
     */
    public JsonObject buildResponseMessage(String name){
        this.responseMessage.put("result", getResponseStatus());
        this.responseMessage.put("data", buildDataMap(name));
        this.responseMessage.put("state", this.world.getBot(name).getState());
        return gson.fromJson(gson.toJsonTree(this.responseMessage), JsonObject.class);
    }

    public void setRobotHit(AbstractBot bot){
        this.robotHit = bot;
    }

    private AbstractBot getRobotHit(){
        return this.robotHit;
    }

    public void setRobotHitName(AbstractBot bot){
        this.robotHitName = bot.getRobotName();
    }

    public void setFireDistance(int distance){
        this.fireDistance = distance;
    }

    private String getRobotHitName(){
        return this.robotHitName;
    }
}
