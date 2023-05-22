package za.co.wethinkcode.server.serverInterface.ResponseObjects;

import za.co.wethinkcode.server.serverInterface.ResponseBuilder;
import za.co.wethinkcode.server.world.WORLD;
import com.google.gson.JsonObject;

/**
 * The CommandErrorResponse class represents a response builder for unsupported commands.
 * It builds the response message indicating an unsupported command error.
 */
public class CommandErrorResponse extends ResponseBuilder {

    /**
     * Constructs a CommandErrorResponse instance with the specified world.
     *
     * @param world The WORLD instance representing the game world.
     */
    public CommandErrorResponse(WORLD world) {
        super(world);
    }

    /**
     * Builds the response message for unsupported commands.
     *
     * @param name The name of the player.
     * @return The JsonObject representing the response message.
     */
    public JsonObject buildResponseMessage(String name) {
        this.responseMessage.put("result", "ERROR");
        setDataMessage("Unsupported Command");
        this.responseMessage.put("data", buildDataMessage());

        return gson.fromJson(gson.toJsonTree(this.responseMessage), JsonObject.class);
    }
}
