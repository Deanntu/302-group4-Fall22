package com.gurup.domain;

public enum PlayerConstants {
    xStart(50),
    yStart(50),
    xLen(50),
    yLen(50);
    private final int value;

    PlayerConstants(int value) {
        // TODO Auto-generated constructor stub
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
