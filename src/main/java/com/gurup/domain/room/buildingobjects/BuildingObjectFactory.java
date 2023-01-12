package com.gurup.domain.room.buildingobjects;

public class BuildingObjectFactory {
    public BuildingObject createBuildingObject(String name, int xCurrent, int yCurrent, int xLen, int yLen) {
        return switch (name) {
            case "Bin" -> new Bin(xCurrent, yCurrent, xLen, yLen);
            case "Book" -> new Book(xCurrent, yCurrent, xLen, yLen);
            case "Pen" -> new Pen(xCurrent, yCurrent, xLen, yLen);
            case "Printer" -> new Printer(xCurrent, yCurrent, xLen, yLen);
            case "Table" -> new Table(xCurrent, yCurrent, xLen, yLen);
            default -> throw new IllegalArgumentException("Unknown Building Object " + name);
        };
    }
}
