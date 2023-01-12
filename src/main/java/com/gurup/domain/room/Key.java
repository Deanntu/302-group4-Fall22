package com.gurup.domain.room;

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

    public void setBuildingObject(BuildingObject buildingObject) {
        Key.buildingObject = buildingObject;
    }

    public static void hideKey(ArrayList<BuildingObject> objects) {
        int index = getRandomNumber(0, objects.size());
        System.out.println(index);
        Key.buildingObject = objects.get(index);
    }

    private static int getRandomNumber(int min, int max) {
        return new Random().nextInt(max) + min;
        // return (int) ((Math.random() * (max - min)) + min);
    }

}