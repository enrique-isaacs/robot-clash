package za.co.wethinkcode.server.world;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;
import za.co.wethinkcode.server_side.robot_lab.AbstractBot.java;


public class WORLD {

    private int visibility;
    private Position spawn;
    private Position TOPLEFT;
    private Position BOTTOMRIGHT;
    private static ArrayList<AbstractBot> listOfRobots = new ArrayList<>();
    private ArrayList<Obstacle> listOfObstacles;


    public WORLD() {

        Integer[] worldConfigs = getWorldConfigs();
        int HEIGHT = worldConfigs[0];
        int WIDTH = worldConfigs[1];
        
        this.TOPLEFT = new Position(-(WIDTH/2),HEIGHT/2);
        this.BOTTOMRIGHT = new Position(WIDTH/2, -(HEIGHT/2));

    }

    private Integer[] getWorldConfigs(){

    int height = Integer.parseInt(ReadDataFromJSONFile.get("height"));
    int width = Integer.parseInt(ReadDataFromJSONFile.get("width"));
    int visibility = Integer.parseInt(ReadDataFromJSONFile.get("visibility"));
    
    Integer[] worldConfigs = {height, width, visibility};
    return worldConfigs;
    
    }

    public static void addRobots(String type, String name){
        // add robots to list
        listOfRobots.add(Bots.make(type, name));

    }

    public ArrayList<Bots> getRobots(){
        return listOfRobots;
    }

    
}
