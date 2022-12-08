package com.gurup.domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.Timer;

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
	}

	public void draw(Graphics g) {
		Point pos = new Point(getX(), getY());
		g.setColor(playerColor);
		g.fillOval((int) pos.getX(), (int) pos.getY(), size, size);
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
				System.out.println(remainingTime);
				remainingTime--;
			}
		}
		else {
			timeCounter++;
		}
		return TimerOperationResults.TIME_DECREMENTED;
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

}
