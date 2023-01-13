package com.gurup.domain.powerups;

import java.awt.Rectangle;

import com.gurup.domain.Player;

public class HealthPowerUp implements PowerUp {

    private static HealthPowerUp healthPowerUp;
    private final Player player;
    private final String name = "health";
    private final int healthIncreaseConstant = 1;
    private final boolean storable = false;
    private int xLen;
    private int yLen;
    private int xCurrent;
    private int yCurrent;
    private boolean isActive = false;
    private final Integer slotId = null;

    private HealthPowerUp(Player player) {
        this.player = player;
    }

    public static synchronized HealthPowerUp getInstance(Player player) {
        if (healthPowerUp == null) {
            healthPowerUp = new HealthPowerUp(player);
        }
        return healthPowerUp;
    }

    @Override
    public void usePowerUp() {
        // TODO Auto-generated method stub
        activatePowerUp();
    }

    private void activatePowerUp() {
        // TODO Auto-generated method stub
        player.setLife(player.getLife() + healthIncreaseConstant);
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
