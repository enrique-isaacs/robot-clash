package za.co.wethinkcode.server.serverInterface.ResponseObjects;

import com.google.gson.JsonObject;
import za.co.wethinkcode.server.serverInterface.ResponseBuilder;
import za.co.wethinkcode.server.world.WORLD;

import java.util.Map;

/**
 * The LaunchResponse class represents a response builder for the launch action.
 * It builds the response message indicating the result of a launch action.
 */
public class LaunchResponse extends ResponseBuilder {
    /**
     * Constructs a LaunchResponse instance with the specified world.
     *
     * @param world The WORLD instance representing the game world.
     */
    public LaunchResponse(WORLD world) {
        super(world);
    }

    /**
     * Builds the data map for the launch action.
     *
     * @param name The name of the player.
     * @return The map containing the data for the launch action.
     */
    private Map<String, Object> buildDataMap(String name) {
        int x = this.world.getBot(name).getCurrentPosition().getX();
        int y = this.world.getBot(name).getCurrentPosition().getY();
        Integer[] robotPosition = new Integer[]{x, y};
        int robotVisibility = this.world.getBot(name).getVisibility();
        int robotReloadTime = this.world.getBot(name).getReload();
        int robotRepairTime = this.world.getBot(name).getRepair();
        int robotShieldStrength = this.world.getBot(name).getShields();

        this.dataMap.put("position", robotPosition);
        this.dataMap.put("visibility", robotVisibility);
        this.dataMap.put("reload", robotReloadTime);
        this.dataMap.put("repair", robotRepairTime);
        this.dataMap.put("shields", robotShieldStrength);

        return this.dataMap;
    }

    /**
     * Builds the response message for the launch action.
     *
     * @param name The name of the player.
     * @return The JsonObject representing the response message.
     */
    public JsonObject buildResponseMessage(String name) {
        if (getResponseStatus().equals("OK")) {
            this.responseMessage.put("result", getResponseStatus());
            this.responseMessage.put("data", buildDataMap(name));
            this.responseMessage.put("state", this.world.getBot(name).getState());
        } else if (getResponseStatus().equals("ERROR")) {
            this.responseMessage.put("result", getResponseStatus());
            this.responseMessage.put("data", getErrorMessage());
        }

        return gson.fromJson(gson.toJsonTree(this.responseMessage), JsonObject.class);
    }
}
