package com.gurup.domain.powerups;

import java.awt.Rectangle;

import com.gurup.domain.Player;

public class TimePowerUp implements PowerUp {

    private static TimePowerUp timePowerUp;
    private final Player player;
    private final String name = "time";
    private final int timeIncreaseConstant = 5;
    private final boolean storable = false;
    private int xLen;
    private int yLen;
    private int xCurrent;
    private int yCurrent;
    private boolean isActive = false;
    private final Integer slotId = null;

    private TimePowerUp(Player player) {
        this.player = player;
    }

    public static synchronized TimePowerUp getInstance(Player player) {
        if (timePowerUp == null) {
            timePowerUp = new TimePowerUp(player);
        }
        return timePowerUp;
    }

    @Override
    public void usePowerUp() {
        // TODO Auto-generated method stub
        activatePowerUp();
    }

    private void activatePowerUp() {
        // TODO Auto-generated method stub
        player.setRemainingTime(player.getRemainingTime() + timeIncreaseConstant);
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
}
