package com.gurup.domain.room;

import java.awt.Toolkit;

public enum RoomConstants {
    xStart(50),
    yStart(50),
    xLimit(Toolkit.getDefaultToolkit().getScreenSize().width - 100),
    yLimit(Toolkit.getDefaultToolkit().getScreenSize().height - 175),
    timePowerUpXLen(50),
    timePowerUpYLen(50),
    healthPowerUpXLen(50),
    healthPowerUpYLen(50),
    vestPowerUpXLen(50),
    vestPowerUpYLen(50),
    bottlePowerUpXLen(25),
    bottlePowerUpYLen(50),
    hintPowerUpXLen(50),
    hintPowerUpYLen(50),
    doorXLen(60),
    doorYLen(100),
    keyXLen(25),
    keyYLen(25),
    doorXStart(xLimit.getValue() - doorXLen.getValue() + xStart.getValue()),
    doorYStart(yLimit.getValue() - doorYLen.getValue() + yStart.getValue());


    private final int value;

    RoomConstants(int value) {
        // TODO Auto-generated constructor stub
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
