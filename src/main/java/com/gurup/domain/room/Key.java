package com.gurup.domain.room;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import com.gurup.domain.room.buildingobjects.BuildingObject;

public class Key {

    private static Key key;
    private static BuildingObject buildingObject;
    
    private Key() {
        
    }
    
    public static synchronized Key getInstance() {
        if (key == null) {
            key = new Key();
        }
        return key;
    }

    public BuildingObject getBuildingObject() {
        return buildingObject;
    }

    public static void setBuildingObject(BuildingObject buildingObject) {
        Key.buildingObject = buildingObject;
    }

    public static void hideKey(ArrayList<BuildingObject> objects) {
        int index = getRandomNumber(0, objects.size());
        Key.buildingObject = objects.get(index);
    }

    public static Rectangle getKeyRectangle (){
        return Key.buildingObject.getRectangle();
    }

    private static int getRandomNumber(int min, int max) {
        return new Random().nextInt(max) + min;
        // return (int) ((Math.random() * (max - min)) + min);
    }

}