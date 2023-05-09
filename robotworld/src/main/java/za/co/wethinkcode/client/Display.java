package za.co.wethinkcode.client;

public class Display {

    public void showResponse(ResponseHandler responseHandler) {
        String state = responseHandler.getState();
        String message = responseHandler.getData();

        String[] stateParts = state.split(" ");
        String position = stateParts[0];
        String steps = stateParts[1];

        String action = "unknown";
        if (responseHandler instanceof ForwardResult) {
            action = "forward";
        } else if (responseHandler instanceof BackResult) {
            action = "back";
        }

        String formattedMessage = String.format("[%s] name moved %s by %s steps", position, action, steps);

        // Display the message to the console
        System.out.println(formattedMessage);
    }
}