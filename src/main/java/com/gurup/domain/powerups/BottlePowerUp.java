package com.gurup.domain.powerups;

import java.awt.Rectangle;

import com.gurup.domain.Game;
import com.gurup.domain.Player;

public class BottlePowerUp implements PowerUp {

    private static BottlePowerUp bottlePowerUp;
    private final Player player;
    private final String name = "bottle";
    private final boolean storable = true;
    private int xLen;
    private int yLen;
    private int xCurrent;
    private int yCurrent;
    private boolean isActive = false;
    private boolean isUsable = false;
    private boolean isUsed = false;
    private final Integer slotId = 2;

    private BottlePowerUp(Player player) {
        this.player = player;
    }

    public static synchronized BottlePowerUp getInstance(Player player) {
        if (bottlePowerUp == null) {
            bottlePowerUp = new BottlePowerUp(player);
        }
        return bottlePowerUp;
    }

    @Override
    public void usePowerUp() {
        activatePowerUp();
    }

    private void activatePowerUp() {
        this.isActive = false;
        ThrownBottlePowerUp.getInstance(Game.getPlayer()).setUsable(true);
        ThrownBottlePowerUp.getInstance(Game.getPlayer()).setUsed(false);
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

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public boolean isUsable() {
        return isUsable;
    }

    public void setUsable(boolean isUsable) {
        this.isUsable = isUsable;
    }
}
