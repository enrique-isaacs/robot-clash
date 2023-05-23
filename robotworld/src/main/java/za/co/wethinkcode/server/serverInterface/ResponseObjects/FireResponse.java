package za.co.wethinkcode.server.serverInterface.ResponseObjects;

import com.google.gson.JsonObject;
import za.co.wethinkcode.server.robotLab.AbstractBot;
import za.co.wethinkcode.server.serverInterface.ResponseBuilder;
import za.co.wethinkcode.server.world.WORLD;

import java.util.Map;

/**
 * The FireResponse class represents a response builder for firing actions.
 * It builds the response message indicating the result of a fire action.
 */
public class FireResponse extends ResponseBuilder {

    private int fireDistance;
    private String robotHitName;
    private AbstractBot robotHit;

    /**
     * Constructs a FireResponse instance with the specified world.
     *
     * @param world The WORLD instance representing the game world.
     */
    public FireResponse(WORLD world) {
        super(world);
    }

    /**
     * Sets the distance of the fire action.
     *
     * @param distance The distance of the fire action.
     */
    public void setFireDistance(int distance) {
        this.fireDistance = distance;
    }

    /**
     * Returns the distance of the fire action.
     *
     * @return The distance of the fire action.
     */
    private int getFireDistance() {
        return this.fireDistance;
    }

    /**
     * Sets the name of the robot hit by the fire action.
     *
     * @param bot The AbstractBot instance representing the hit robot.
     */
    public void setRobotHitName(AbstractBot bot) {
        this.robotHitName = bot.getRobotName();
    }

    /**
     * Sets the AbstractBot instance representing the hit robot.
     *
     * @param bot The AbstractBot instance representing the hit robot.
     */
    public void setRobotHit(AbstractBot bot) {
        this.robotHit = bot;
    }

    /**
     * Returns the AbstractBot instance representing the hit robot.
     *
     * @return The AbstractBot instance representing the hit robot.
     */
    private AbstractBot getRobotHit() {
        return this.robotHit;
    }

    /**
     * Returns the name of the robot hit by the fire action.
     *
     * @return The name of the robot hit by the fire action.
     */
    private String getRobotHitName() {
        return this.robotHitName;
    }

    /**
     * Builds the data map for the response message.
     *
     * @param name The name of the player.
     * @return The data map representing the response data.
     */
    private Map<String, Object> buildDataMap(String name) {

        this.dataMap.put("message", getDataMessage());
        this.dataMap.put("distance", getFireDistance());
        this.dataMap.put("robot", getRobotHitName());
        this.dataMap.put("state", getRobotHit().getState());

        return this.dataMap;
    }

    /**
     * Builds the response message for the fire action.
     *
     * @param name The name of the player.
     * @return The JsonObject representing the response message.
     */
    public JsonObject buildResponseMessage(String name) {
        if (getDataMessage().equals("Hit")) {
            this.responseMessage.put("result", getResponseStatus());
            this.responseMessage.put("data", buildDataMap(name));
            this.responseMessage.put("state", this.world.getBot(name).getState());
        } else if (getDataMessage().equals("Miss")) {
            this.responseMessage.put("result", getResponseStatus());
            this.responseMessage.put("data", buildDataMessage());
            this.responseMessage.put("state", this.world.getBot(name).getState());
        }

        return gson.fromJson(gson.toJsonTree(this.responseMessage), JsonObject.class);
    }
}
