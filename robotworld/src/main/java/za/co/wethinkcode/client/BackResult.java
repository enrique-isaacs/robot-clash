package za.co.wethinkcode.client;

import java.io.ObjectInputStream;

public class BackResult extends ResponseHandler{
    public BackResult(ObjectInputStream inputStream, String userCommand){

        super(inputStream, userCommand);
    }
}
