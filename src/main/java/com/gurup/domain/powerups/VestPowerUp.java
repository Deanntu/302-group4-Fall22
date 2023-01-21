package com.gurup.domain.powerups;

import java.awt.Rectangle;

import com.gurup.domain.Player;

public class VestPowerUp implements PowerUp {

    private static VestPowerUp vestPowerUp;
    private final Player player;
    private final String name = "vest";
    private final int protectionDurationSeconds = 20;
    private final boolean storable = true;
    private int xLen;
    private int yLen;
    private int xCurrent;
    private int yCurrent;
    private boolean isActive = false;
    private final Integer slotId = 0;

    private VestPowerUp(Player player) {
        this.player = player;
    }

    public static synchronized VestPowerUp getInstance(Player player) {
        if (vestPowerUp == null) {
            vestPowerUp = new VestPowerUp(player);
        }
        return vestPowerUp;
    }

    @Override
    public void usePowerUp() {
        activatePowerUp();
    }

    private void activatePowerUp() {
        player.setProtected(true);
        // TODO later set to false
    }

    @Override
    public boolean isStorable() {
        return storable;
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(xCurrent, yCurrent, xLen, yLen);
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void setIsActive(boolean b) {
        isActive = b;
    }

    public int getXLen() {
        return xLen;
    }

    public void setXLen(int xLen) {
        this.xLen = xLen;
    }

    public int getYLen() {
        return yLen;
    }

    public void setYLen(int yLen) {
        this.yLen = yLen;
    }

    public int getXCurrent() {
        return xCurrent;
    }

    public void setXCurrent(int x) {
        this.xCurrent = x;
    }

    public int getYCurrent() {
        return yCurrent;
    }

    public void setYCurrent(int y) {
        this.yCurrent = y;
    }

    @Override
    public int[] rectArray() {
        return new int[]{this.xCurrent, this.yCurrent, this.xLen, this.yLen};
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSlotId() {
        return slotId;
    }

    public int getProtectionDurationSeconds() {
        return protectionDurationSeconds;
    }

}
