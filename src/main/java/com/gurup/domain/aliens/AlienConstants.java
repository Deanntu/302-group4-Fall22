package com.gurup.domain.aliens;

public enum AlienConstants {
    xLen(40),
    yLen(40);
    private final int value;

    AlienConstants(int value) {
        // TODO Auto-generated constructor stub
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
