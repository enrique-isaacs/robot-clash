package za.co.wethinkcode.server.serverInterface.ResponseObjects;

import za.co.wethinkcode.server.serverInterface.ResponseBuilder;
import za.co.wethinkcode.server.world.WORLD;
import com.google.gson.JsonObject;

/**
 * The ArgumentErrorResponse class represents a response builder for handling invalid arguments error.
 * It builds an error response message when the provided arguments in the request are invalid.
 */
public class ArgumentErrorResponse extends ResponseBuilder {

    /**
     * Constructs an ArgumentErrorResponse instance with the specified world.
     *
     * @param world The WORLD instance representing the game world.
     */
    public ArgumentErrorResponse(WORLD world) {
        super(world);
    }

    /**
     * Builds the response message for the argument error.
     *
     * @param name The name of the player.
     * @return The JsonObject representing the response message.
     */
    public JsonObject buildResponseMessage(String name) {
        this.responseMessage.put("result", "ERROR");
        setDataMessage("Could not parse arguments");
        this.responseMessage.put("data", buildDataMessage());
        return gson.fromJson(gson.toJsonTree(this.responseMessage), JsonObject.class);
    }
}
