package za.co.wethinkcode.client;

import org.json.JSONObject;

public class Display {
    public static void main(String[] args) {
        String jsonResponse = "{\n" +
                "        \"result\": \"ERROR\",\n" +
                "        \"data\": {\n" +
                "        \"message\": \"No more space in this world\"\n" +
                "        }\n" +
                "        }";

        JSONObject json = new JSONObject(jsonResponse);
        String message = json.getJSONObject("data").getString("message");

        String userName = null;
        try {
            ClientCommand client = new ClientCommand("localhost", 1999); // replace with your server host and port
            JSONObject command = client.getCommand();
            userName = command.getString("name");
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(userName + " : " + message);
    }
}