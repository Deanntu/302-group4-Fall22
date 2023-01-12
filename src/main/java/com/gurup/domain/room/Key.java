package com.gurup.domain.room;

import java.util.ArrayList;

import com.gurup.domain.room.buildingobjects.BuildingObject;

public class Key {

    private static BuildingObject buildingObject;

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
        return (int) ((Math.random() * (max - min)) + min);
    }

}