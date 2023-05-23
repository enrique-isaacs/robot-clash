package za.co.wethinkcode.server.serverInterface.ResponseObjects;

import com.google.gson.JsonObject;
import za.co.wethinkcode.server.serverInterface.ResponseBuilder;
import za.co.wethinkcode.server.world.WORLD;

/**
 * The ForwardResponse class represents a response builder for the forward action.
 * It builds the response message indicating the result of a forward action.
 */
public class ForwardResponse extends ResponseBuilder {

    /**
     * Constructs a ForwardResponse instance with the specified world.
     *
     * @param world The WORLD instance representing the game world.
     */
    public ForwardResponse(WORLD world) {
        super(world);
    }

    /**
     * Builds the response message for the forward action.
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
            this.responseMessage.put("data", buildErrorMessage());
        }

        return gson.fromJson(gson.toJsonTree(this.responseMessage), JsonObject.class);
    }
}
