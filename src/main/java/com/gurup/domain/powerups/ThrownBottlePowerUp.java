package com.gurup.domain.powerups;

import java.awt.Rectangle;

import com.gurup.domain.Player;
import com.gurup.domain.room.Room;
import com.gurup.domain.room.RoomConstants;

public class ThrownBottlePowerUp implements PowerUp {

    private static ThrownBottlePowerUp thrownBottlePowerUp;
    private final Player player;
    private String name = "thrownbottle";
    private int xLen;
    private int yLen;
    private int xCurrent;
    private int yCurrent;
    private boolean isUsable = false;
    private boolean isUsed = false;
    private int[] throwDestination;
    private int[] throwSource;

    private ThrownBottlePowerUp(Player player) {
        this.player = player;
    }

    public static synchronized ThrownBottlePowerUp getInstance(Player player) {
        if (thrownBottlePowerUp == null) {
            thrownBottlePowerUp = new ThrownBottlePowerUp(player);
        }
        return thrownBottlePowerUp;
    }

    public void usePowerUp(String direction) {
        // MODIFIES: this
        // EFFECTS: if not used yet, create a thrown bottle at the target direction
        // else, do nothing
        // if the range extends further than a wall, put it within the borders of the
        // room
        if (isUsable) {
            setThrowDestinationAndSource();
            switch (direction) {
                case "up":
                    moveUp();
                    setUsable(false);
                    setUsed(true);
                    break;
                case "down":
                    moveDown();
                    setUsable(false);
                    setUsed(true);
                    break;
                case "left":
                    moveLeft();
                    setUsable(false);
                    setUsed(true);
                    break;
                case "right":
                    moveRight();
                    setUsable(false);
                    setUsed(true);
                    break;
            }
        }

    }

    public boolean isUsable() {
        return isUsable;
    }

    public void setUsable(boolean isUsable) {
        this.isUsable = isUsable;
    }

    public void moveRight() {
        this.xCurrent = Math.min(this.xCurrent + 100, Room.getXLimit() + 50 - RoomConstants.bottlePowerUpXLen.getValue());
        this.yCurrent = player.getYCurrent();
    }

    public void moveLeft() {
        this.xCurrent = Math.max(this.xCurrent - 100, Room.getstartX());
        this.yCurrent = player.getYCurrent();
    }

    public void moveUp() {
        this.yCurrent = Math.max(this.yCurrent - 100, Room.getstartY());
        this.xCurrent = player.getXCurrent();
    }

    public void moveDown() {
        this.yCurrent = Math.min(this.yCurrent + 100, Room.getYLimit());
        this.xCurrent = player.getXCurrent();
    }

    @Override
    public void usePowerUp() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isStorable() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(xCurrent, yCurrent, xLen, yLen);
    }

    @Override
    public boolean isActive() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setIsActive(boolean b) {
        // TODO Auto-generated method stub

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
        // TODO Auto-generated method stub
        return name;
    }

    @Override
    public int getSlotId() {
        // TODO Auto-generated method stub
        return 0;
    }

    private void setThrowDestinationAndSource() {
        this.throwDestination = rectArray();
        this.xCurrent = player.getXCurrent();
        this.yCurrent = player.getYCurrent();
        this.xLen = BottlePowerUp.getInstance(null).getXLen();
        this.yLen = BottlePowerUp.getInstance(null).getYLen();
        this.throwSource = new int[]{xCurrent, yCurrent, xLen, yLen};
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getThrowDestination() {
        return throwDestination;
    }

    public int[] getThrowSource() {
        return throwSource;
    }

    public static void setNull() {
        thrownBottlePowerUp = null;
    }

}
