package com.gurup.domain;

import java.awt.Color;
import java.awt.Graphics;

import com.gurup.domain.powerups.VestPowerUp;

public class Player {
	Color playerColor;
	int xStart, yStart;
	private int xLimit;
	private int yLimit;
	private int x;
	private int y;
	private int size;
	private int xPosition;
	private int yPosition;
	Position positions;



	private int remainingTime;
	private int timeCounter;
	private int life;
	private boolean isProtected;
	private int remainingProtectionSeconds;



	public Player(Color playerColor, int xStart, int yStart, int xLimit, int yLimit, int size, int startingTime) {
		this.playerColor = playerColor;
		this.xStart = xStart;
		this.yStart = yStart;
		this.setxLimit(xLimit);
		this.setyLimit(yLimit);
		this.setX(xStart);
		this.setY(yStart);
		this.size = size;
		this.remainingTime = startingTime;
		timeCounter = 1;
		positions = new Position();
		this.xPosition = positions.getxPosition();
		this.yPosition = positions.getyPosition();
		life = 3;
		setProtected(false);
	}

	public void draw(Graphics g) {
	/*	Point pos = new Point(getX(), getY());
		g.setColor(playerColor);
		g.fillOval((int) pos.getX(), (int) pos.getY(), size, size);*/
	}
	
	public TimerOperationResults decrementTime(int delaymiliseconds) {
		if (Game.getIsPaused()) return TimerOperationResults.PAUSED;
		if (timeCounter%(1000/delaymiliseconds) == 0) {
			timeCounter = 1;
			if (remainingTime <= 0) {
				// TODO game over
				return TimerOperationResults.TIME_UP;
			}
			else {
				if (!isProtected) {
					remainingProtectionSeconds = Integer.MIN_VALUE;
				}
				else {
					if (remainingProtectionSeconds == Integer.MIN_VALUE) {
						remainingProtectionSeconds = VestPowerUp.getInstance(this).getProtectionDurationSeconds();
					}
					else {
						remainingProtectionSeconds--;
					}
					if (remainingProtectionSeconds < 0) {
						isProtected = false;
					}
				}
				// System.out.println(remainingTime);
				System.out.printf("Is protected: %b%n",isProtected);
				remainingTime--;
			}
		}
		else {
			timeCounter++;
		}
		return TimerOperationResults.TIME_DECREMENTED;
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

	public int getRemainingLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public Color getPlayerColor() {
		return playerColor;
	}

	public boolean getIsProtected() {
		return isProtected;
	}

	public void setProtected(boolean isProtected) {
		this.isProtected = isProtected;
	}

}
