package com.gurup.domain.aliens;

import java.awt.Color;

import com.gurup.domain.Position;

public class BlindAlien implements Alien{

	Color alienColor;
	private String name = "Blind";
	private int xStart;
	private int yStart;
	private int xLimit = 40;
	private int yLimit = 40;
	
	private int x;
	private int y;
	private int size;
	private int xPosition;
	private int yPosition;
	Position positions;
	private int remainingTime;
	private boolean isActive = false;

    public BlindAlien(int xStart, int yStart, int xLimit, int yLimit, int size, int startingTime) {
        this.alienColor = alienColor;
        this.xStart = xStart;
        this.yStart = yStart;
        this.setxLimit(xLimit);
        this.setyLimit(yLimit);
        this.setX(xStart);
        this.setY(yStart);
        this.size = size;
        this.remainingTime = startingTime;
        positions = new Position();
        this.xPosition = positions.getxPosition();
        this.yPosition = positions.getyPosition();
    }

    public BlindAlien() {
		// TODO Auto-generated constructor stub
	}

	public void moveRight() {
        if (this.getX() >= this.getxLimit()) {
            this.setX(this.getxLimit());
        } else {
            this.setX(this.getX() + 10);
        }
    }

    public void moveLeft() {
        if (this.getX() <= this.getstartX()) {
            this.setX(this.getstartX());
        } else {
            this.setX(this.getX() - 10);
        }
    }

    public void moveUp() {
        if (this.getY() <= this.getstartY()) {
            this.setY(this.getstartY());
        } else {
            this.setY(this.getY() - 10);
        }
    }

    public void moveDown() {
        if (this.getY() >= this.getyLimit()) {
            this.setY(this.getyLimit());
        } else {
            this.setY(this.getY() + 10);
        }
    }
    
	public int[] rectArray() {
		int[] rectValues = { getX(), getY(), this.getxLimit(), this.getyLimit() };
		return rectValues;
	}
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getxLimit() {
        return xLimit;
    }

    public void setxLimit(int xLimit) {
        this.xLimit = xLimit;
    }
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getyLimit() {
        return yLimit;
    }

    public void setyLimit(int yLimit) {
        this.yLimit = yLimit;
    }

    public int getstartX() {
        return xStart;
    }

    public int getstartY() {
        return yStart;
    }
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getRemainingTime() {
        return remainingTime;
    }
    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }
    public Color getAlienColor() {
        return alienColor;
    }

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
