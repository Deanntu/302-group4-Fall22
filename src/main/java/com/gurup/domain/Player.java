package com.gurup.domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Player {
	Color playerColor;
	int xStart, yStart;
	private int xLimit;
	private int yLimit;
	private int x;
	private int y;

	public Player(Color playerColor, int xStart, int yStart, int xLimit, int yLimit) {
		this.playerColor = playerColor;
		this.xStart = xStart;
		this.yStart = yStart;
		this.setxLimit(xLimit);
		this.setyLimit(yLimit);
		this.setX(xStart);
		this.setY(yStart);
	}

	public void draw(Graphics g) {
		Point pos = new Point(getX(), getY());
		g.setColor(playerColor);
		g.fillOval((int) pos.getX(), (int) pos.getY(), 25, 25);
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
	
}
