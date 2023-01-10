package com.gurup.domain.room.buildingobjects;

public enum BuildingObjectConstants {
    binXLen(30),
    binYLen(30),
    tableXLen(100),
    tableYLen(50),
    bookXLen(50),
    bookYLen(40),
    penXLen(25),
    penYLen(25),
    printerXLen(50),
    printerYLen(50),
    allObjectsXLenForButtons(30),
    allObjectsYLenForButtons(30);

    private final int value;

    BuildingObjectConstants(int value) {
        // TODO Auto-generated constructor stub
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
