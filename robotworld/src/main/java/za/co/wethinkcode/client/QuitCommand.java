//package za.co.wethinkcode.client;
//
//import com.google.gson.JsonObject;
//
//import java.io.ObjectInputStream;
//
//public class QuitCommand extends ResponseHandler {
//
//    private JsonObject response;
//
//    public QuitCommand(ObjectInputStream inputStream, String userCommand) {
//        super(inputStream, userCommand);
//    }
//
//    @Override
//    public String getData() {
//        return "";
//    }
//
//    @Override
//    public String getState() {
//        response = getResponseJsonObject();
//        String state = response.get("state").getAsString();
//
//        if (state.equalsIgnoreCase("quit")) {
//            System.out.println("GoodBytes");
//            System.exit(0); // Exit the program
//        }
//
//        return state;
//    }
//}