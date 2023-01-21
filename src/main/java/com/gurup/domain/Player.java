package com.gurup.domain;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

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

    private int level;
    private int remainingTime;
    private int startingTime;
    private int timeCounter;
    private int life;
    private boolean isProtected;
    private int remainingProtectionSeconds;
    private String objectToBuild;
    private boolean canSeeHintRect;
    private boolean canSeeKeyImage;
    private int hintXRandom;
    private int hintYRandom;
    private Boolean isKeyFound = false;

    public Player(int xStart, int yStart, int xLen, int yLen, int startingTime) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xLen = xLen;
        this.yLen = yLen;
        this.xCurrent = xStart;
        this.yCurrent = yStart;
        this.remainingTime = startingTime;
        this.startingTime = startingTime;
        timeCounter = 1;
        life = 3;
        Random random = new Random();
        hintXRandom = random.nextInt(100);
        hintYRandom = random.nextInt(100);
        setProtected(false);
        setHintStatus(false);

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
        if (this.xCurrent + 20 >= Room.getXLimit()) {
            this.xCurrent = (Room.getXLimit());
        } else {
            this.xCurrent = (this.xCurrent + 20);
        }
    }

    public void moveLeft() {
        // MODIFIES: this
        // REQUIRES: none
        //
        // EFFECTS: Increments the Integer corresponding to the players current x position
        // if new position outside the room does nothing
        if (this.xCurrent - 20 <= Room.getstartX()) {
            this.xCurrent = (Room.getstartX());
        } else {
            this.xCurrent = (this.xCurrent - 20);
        }
    }

    public void moveUp() {
        // MODIFIES: this
        // REQUIRES: none
        //
        // EFFECTS: Decrements the Integer corresponding to the players current y position
        // if new position outside the room does nothing
        if (this.yCurrent - 20 <= Room.getstartY()) {
            this.yCurrent = (Room.getstartY());
        } else {
            this.yCurrent = (this.yCurrent - 20);
        }
    }

    public void moveDown() {
        // MODIFIES: this
        // REQUIRES: none
        //
        // EFFECTS: Increments the Integer corresponding to the players current y position
        // if new position outside the room does nothing
        if (this.yCurrent + 20 >= Room.getYLimit()) {
            this.yCurrent = (Room.getYLimit());
        } else {
            this.yCurrent = (this.yCurrent + 20);
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
        if (isProtected) {
            remainingProtectionSeconds = Integer.MIN_VALUE;
        }
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

    public int getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(int startingTime) {
        this.startingTime = startingTime;
    }


    public int getLevel() {
        return level;
    }


    public void setLevel(int level) {
        this.level = level;
    }


    public void setHintStatus(boolean b) {
        this.canSeeHintRect = b;
    }

    public boolean getHintStatus() {
        return this.canSeeHintRect;
    }

    public void setDrawKeyStatus(boolean b) {
        this.canSeeKeyImage = b;
    }

    public boolean getDrawKeyStatus() {
        return this.canSeeKeyImage;
    }


	public int getHintXRandom() {
		return hintXRandom;
	}


	public void setHintXRandom(int hintXRandom) {
		this.hintXRandom = hintXRandom;
	}


	public int getHintYRandom() {
		return hintYRandom;
	}


	public void setHintYRandom(int hintYRandom) {
		this.hintYRandom = hintYRandom;
	}


    public Boolean getIsKeyFound() {
        return isKeyFound;
    }


    public void setIsKeyFound(Boolean isKeyFound) {
        this.isKeyFound = isKeyFound;
    }
}
