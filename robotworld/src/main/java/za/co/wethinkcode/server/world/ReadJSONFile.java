package za.co.wethinkcode.server.world;

import java.io.FileReader;

// add org json to pom file
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadJSONFile {

    private static JSONObject jsonObject;

    static {
        JSONParser parser = new JSONParser();
        try {
// fix file path string
//            Object obj = parser.parse(new FileReader("src/main/java/za/co/wethinkcode/server_side/world_configs.json"));
            Object obj = parser.parse(new FileReader("src/main/java/za/co/wethinkcode/server/world/world_configs.json"));
            jsonObject = (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return jsonObject.get(key).toString();
    }

}
