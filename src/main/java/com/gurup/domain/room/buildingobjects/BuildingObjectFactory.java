package com.gurup.domain.room.buildingobjects;

public class BuildingObjectFactory {
    public BuildingObject createBuildingObject(String name, int xStart, int yStart, int xLimit, int yLimit) {
        BuildingObject buildingObject = null;
        switch (name) {
            case "TABLE":
                buildingObject = new Table(xStart, yStart, xLimit, yLimit);
                break;
            case "BIN":
                buildingObject = new Bin(xStart, yStart, xLimit, yLimit);
                break;
            default:
                throw new IllegalArgumentException("Unknown Building Object "+name);
        }
        return buildingObject;
    }
}
