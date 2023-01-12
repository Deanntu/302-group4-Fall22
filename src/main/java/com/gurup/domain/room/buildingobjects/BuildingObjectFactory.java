package com.gurup.domain.room.buildingobjects;

public class BuildingObjectFactory {
    public BuildingObject createBuildingObject(String name, int xCurrent, int yCurrent, int xLen, int yLen) {
        BuildingObject returnValue;
        switch (name) {
            case "Bin":
                returnValue = new Bin(xCurrent, yCurrent, xLen, yLen);
                break;
            case "Book":
                returnValue = new Book(xCurrent, yCurrent, xLen, yLen);
                break;
            case "Pen":
                returnValue = new Pen(xCurrent, yCurrent, xLen, yLen);
                break;
            case "Printer":
                returnValue = new Printer(xCurrent, yCurrent, xLen, yLen);
                break;
            case "Table":
                returnValue = new Table(xCurrent, yCurrent, xLen, yLen);
                break;
            default:
                throw new IllegalArgumentException("Unknown Building Object " + name);
        }
        return returnValue;
    }
}
