package za.co.wethinkcode.server.world;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;
import za.co.wethinkcode.server.robotLab.AbstractBot;


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

    int height = Integer.parseInt(ReadJSONFile.get("height"));
    int width = Integer.parseInt(ReadJSONFile.get("width"));
    int visibility = Integer.parseInt(ReadJSONFile.get("visibility"));
    
    Integer[] worldConfigs = {height, width, visibility};
    return worldConfigs;
    
    }

    public Position getTOPLEFT(){
        return this.TOPLEFT;
    }
    public Position getBOTTOMRIGHT(){
        return this.BOTTOMRIGHT;
    }

    public static void addRobots(String type, String name){
        // add robots to list
        listOfRobots.add(AbstractBot.make(type, name));

    }

    public ArrayList<Obstacle> getObstacles(){
        return listOfObstacles;
    }

    public ArrayList<AbstractBot> getRobots(){
        return listOfRobots;
    }

    
}
