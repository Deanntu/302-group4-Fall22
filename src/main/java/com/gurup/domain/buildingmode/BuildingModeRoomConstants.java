package com.gurup.domain.buildingmode;

public enum BuildingModeRoomConstants {
    minObjectsForStudentCenter(5),
    minObjectsForCASE(7),
    minObjectsForSOS(10),
    minObjectsForSCI(14),
    minObjectsForENG(19),
    minObjectsForSNA(25);
    private final int value;

    BuildingModeRoomConstants(int value) {
        // TODO Auto-generated constructor stub
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
