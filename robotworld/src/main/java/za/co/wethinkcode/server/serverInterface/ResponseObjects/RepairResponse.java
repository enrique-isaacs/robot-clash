package za.co.wethinkcode.server.serverInterface.ResponseObjects;

import com.google.gson.JsonObject;
import za.co.wethinkcode.server.serverInterface.ResponseBuilder;
import za.co.wethinkcode.server.world.WORLD;

/**
 * The RepairResponse class represents a response builder for the repair action.
 * It builds the response message indicating the result of a repair action.
 */
public class RepairResponse extends ResponseBuilder {

    /**
     * Constructs a RepairResponse instance with the specified world.
     *
     * @param world The WORLD instance representing the game world.
     */
    public RepairResponse(WORLD world) {
        super(world);
    }

    /**
     * Builds the response message for the repair action.
     *
     * @param name The name of the player.
     * @return The JsonObject representing the response message.
     */
    public JsonObject buildResponseMessage(String name) {
        this.responseMessage.put("result", getResponseStatus());
        this.responseMessage.put("data", getDataMessage());
        this.responseMessage.put("state", this.world.getBot(name).getState());

        return gson.fromJson(gson.toJsonTree(this.responseMessage), JsonObject.class);
    }
}
