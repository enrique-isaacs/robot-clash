package za.co.wethinkcode.server;

import za.co.wethinkcode.server.serverInterface.Server;

import java.io.IOException;


public class Play {

    public static void main(String[] args) throws IOException {

        Server server = new Server(8888);
        server.start();

    }

}