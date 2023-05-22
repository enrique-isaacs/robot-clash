package za.co.wethinkcode.server.world;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.Reader;

/**
 * Utility class for reading JSON configuration files.
 */
public class ReadJSONFile {

    private static JsonObject jsonObject;

    static {
        try {
            Reader reader = new FileReader("dummyWorld/robotworld/src/main/java/za/co/wethinkcode/server/world/world_configs.json");

            jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

            reader.close();

        } catch (Exception e) {
            // In case the file is not found or there's an error, use default values
            String jsonThing = "{\n" +
                    "\t\"height\": 400,\n" +
                    "\t\"width\" : 200,\n" +
                    "\t\"visibility\" : 5,\n" +
                    "\t\"shields\" : 5,\n" +
                    "\t\"repair\" : 5,\n" +
                    "\t\"reload\" : 5,\n" +
                    "\t\"firingDistance\" : 10,\n" +
                    "\t\"shots\" : 5\n" +
                    "}";
            jsonObject = JsonParser.parseString(jsonThing).getAsJsonObject();
        }
    }

    /**
     * Retrieves the value associated with the given key from the JSON configuration file.
     *
     * @param key the key to retrieve the value for
     * @return the integer value associated with the key
     */
    public static int get(String key) {
        return Integer.parseInt(jsonObject.get(key).getAsString());
    }
}
