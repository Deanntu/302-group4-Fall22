package com.gurup.domain.room.buildingobjects;

public class BuildingObjectFactory {
    public BuildingObject createBuildingObject(String name, int xCurrent, int yCurrent, int xLen, int yLen) {
        BuildingObject buildingObject = null;
        switch (name) {
            case "Bin":
                buildingObject = new Bin(xCurrent, yCurrent, xLen, yLen);
                break;
            case "Book":
                buildingObject = new Book(xCurrent, yCurrent, xLen, yLen);
                break;
            case "Pen":
                buildingObject = new Pen(xCurrent, yCurrent, xLen, yLen);
                break;
            case "Printer":
                buildingObject = new Printer(xCurrent, yCurrent, xLen, yLen);
                break;
            case "Table":
                buildingObject = new Table(xCurrent, yCurrent, xLen, yLen);
                break;
            default:
                throw new IllegalArgumentException("Unknown Building Object " + name);
        }
        return buildingObject;
    }
}
