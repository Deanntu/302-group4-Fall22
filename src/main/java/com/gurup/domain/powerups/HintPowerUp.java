package com.gurup.domain.powerups;

import java.awt.Rectangle;
import java.util.Random;

import com.gurup.domain.Player;

public class HintPowerUp implements PowerUp {

    private static HintPowerUp hintPowerUp;
    private final Player player;
    private final String name = "hint";
    private final int hintSeconds = 10;
    private final boolean storable = true;
    private int xLen;
    private int yLen;
    private int xCurrent;
    private int yCurrent;
    private boolean isActive = false;
    private final Integer slotId = 1;

    private HintPowerUp(Player player) {
        this.player = player;
    }

    public static synchronized HintPowerUp getInstance(Player player) {
        if (hintPowerUp == null) {
            hintPowerUp = new HintPowerUp(player);
        }
        return hintPowerUp;
    }

    @Override
    public void usePowerUp() {
        activatePowerUp();
    }

    private void activatePowerUp() {
        player.setHintStatus(true);
        Random random = new Random();
        player.setHintXRandom(random.nextInt(100));
        player.setHintYRandom(random.nextInt(100));
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(hintSeconds * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                deactivatePowerUp();
            }

        }).start();
    }

    private void deactivatePowerUp() {
        player.setHintStatus(false);
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
