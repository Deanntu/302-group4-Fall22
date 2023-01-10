package com.gurup.domain;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.gurup.domain.powerups.VestPowerUp;
import com.gurup.domain.room.Room;

public class Player {
    private int xStart;
    private int yStart;
    private int xLen;
    private int yLen;
    private int xCurrent;
    private int yCurrent;
    BufferedImage student_image;


    private int remainingTime;
    private int timeCounter;
    private int life;
    private boolean isProtected;
    private int remainingProtectionSeconds;
    private String objectToBuild;


    public Player(int xStart, int yStart, int xLen, int yLen, int startingTime) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xLen = xLen;
        this.yLen = yLen;
        this.xCurrent = xStart;
        this.yCurrent = yStart;
        this.remainingTime = startingTime;
        timeCounter = 1;
        life = 3;
        setProtected(false);
    }


    public TimerOperationResults decrementTime(int delaymiliseconds) {
        if (Game.getIsPaused()) return TimerOperationResults.PAUSED;
        if (timeCounter % (1000 / delaymiliseconds) == 0) {
            timeCounter = 1;
            if (remainingTime <= 0) {
                // TODO game over
                return TimerOperationResults.TIME_UP;
            } else {
                if (!isProtected) {
                    remainingProtectionSeconds = Integer.MIN_VALUE;
                } else {
                    if (remainingProtectionSeconds == Integer.MIN_VALUE) {
                        remainingProtectionSeconds = VestPowerUp.getInstance(this).getProtectionDurationSeconds();
                    } else {
                        remainingProtectionSeconds--;
                    }
                    if (remainingProtectionSeconds < 0) {
                        isProtected = false;
                    }
                }
                remainingTime--;
            }
        } else {
            timeCounter++;
        }
        return TimerOperationResults.TIME_DECREMENTED;
    }

    public void moveRight() {
        // MODIFIES: this
        // REQUIRES: none
        //
        // EFFECTS: Decrements the Integer corresponding to the players current y position
        // if new position outside the room does nothing
        if (this.xCurrent >= Room.getXLimit()) {
            this.xCurrent = (Room.getXLimit());
        } else {
            this.xCurrent = (this.xCurrent + 10);
        }
    }

    public void moveLeft() {
        // MODIFIES: this
        // REQUIRES: none
        //
        // EFFECTS: Increments the Integer corresponding to the players current x position
        // if new position outside the room does nothing
        if (this.xCurrent <= Room.getstartX()) {
            this.xCurrent = (Room.getstartX());
        } else {
            this.xCurrent = (this.xCurrent - 10);
        }
    }

    public void moveUp() {
        // MODIFIES: this
        // REQUIRES: none
        //
        // EFFECTS: Decrements the Integer corresponding to the players current y position
        // if new position outside the room does nothing
        if (this.yCurrent <= Room.getstartY()) {
            this.yCurrent = (Room.getstartY());
        } else {
            this.yCurrent = (this.yCurrent - 10);
        }
    }

    public void moveDown() {
        // MODIFIES: this
        // REQUIRES: none
        //
        // EFFECTS: Increments the Integer corresponding to the players current y position
        // if new position outside the room does nothing
        if (this.yCurrent >= Room.getYLimit()) {
            this.yCurrent = (Room.getYLimit());
        } else {
            this.yCurrent = (this.yCurrent + 10);
        }
    }


    public int getXStart() {
        return xStart;
    }

    public void setXStart(int xStart) {
        this.xStart = xStart;
    }

    public int getYStart() {
        return yStart;
    }

    public void setYStart(int yStart) {
        this.yStart = yStart;
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

    public void setXCurrent(int xCurrent) {
        this.xCurrent = xCurrent;
    }

    public int getYCurrent() {
        return yCurrent;
    }

    public void setYCurrent(int yCurrent) {
        this.yCurrent = yCurrent;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public int getTimeCounter() {
        return timeCounter;
    }

    public void setTimeCounter(int timeCounter) {
        this.timeCounter = timeCounter;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isProtected() {
        return isProtected;
    }

    public void setProtected(boolean isProtected) {
        this.isProtected = isProtected;
    }

    public int getRemainingProtectionSeconds() {
        return remainingProtectionSeconds;
    }

    public void setRemainingProtectionSeconds(int remainingProtectionSeconds) {
        this.remainingProtectionSeconds = remainingProtectionSeconds;
    }

    public Rectangle getRectangle() {
        return new Rectangle(xCurrent, yCurrent, xLen, yLen);
    }

    public BufferedImage getImage() {
        try {
            student_image = ImageIO.read(new File("src/assets/", "player.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return student_image;
    }

    public void setCurrentSelectedObject(String name) {
        this.objectToBuild = name;
    }

    public String getCurrentSelectedObject() {
        return objectToBuild;
    }

}
