package za.co.wethinkcode.server.serverInterface.ResponseObjects;

import com.google.gson.JsonObject;
import za.co.wethinkcode.server.serverInterface.ResponseBuilder;
import za.co.wethinkcode.server.world.WORLD;

/**
 * The BackResponse class represents a response builder for the "back" command.
 * It builds the response message for moving the robot backward in the game world.
 */
public class BackResponse extends ResponseBuilder {

    /**
     * Constructs a BackResponse instance with the specified world.
     *
     * @param world The WORLD instance representing the game world.
     */
    public BackResponse(WORLD world) {
        super(world);
    }

    /**
     * Builds the response message for the "back" command.
     *
     * @param name The name of the player.
     * @return The JsonObject representing the response message.
     */
    public JsonObject buildResponseMessage(String name) {
        if (getResponseStatus().equals("OK")) {
            this.responseMessage.put("result", getResponseStatus());
            this.responseMessage.put("data", buildDataMessage());
            this.responseMessage.put("state", this.world.getBot(name).getState());
        } else if (getResponseStatus().equals("ERROR")) {
            this.responseMessage.put("result", getResponseStatus());
            this.responseMessage.put("data", getErrorMessage());
        }

        return gson.fromJson(gson.toJsonTree(this.responseMessage), JsonObject.class);
    }
}
