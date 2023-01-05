package com.gurup.domain;

import java.awt.Graphics;
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
				//System.out.printf("Is protected: %b%n",isProtected);
				remainingTime--;
			}
		}
		else {
			timeCounter++;
		}
		return TimerOperationResults.TIME_DECREMENTED;
	}
	
	public void moveRight() {
		if (this.xCurrent >= Room.getxLimit()) {
			this.xCurrent = (Room.getxLimit());
		} else {
			this.xCurrent = (this.xCurrent + 10);
		}
	}
	
	public void moveLeft() {
		if (this.xCurrent <= Room.getstartX()) {
			this.xCurrent = (Room.getstartX());
		} else {
			this.xCurrent = (this.xCurrent - 10);
		}
	}
	
	public void moveUp() {
		if (this.yCurrent <= Room.getstartY()) {
			this.yCurrent = (Room.getstartY());
		} else {
			this.yCurrent = (this.yCurrent - 10);
		}
	}
	
	public void moveDown() {
		if (this.yCurrent >= Room.getyLimit()) {
			this.yCurrent = (Room.getyLimit());
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
}
