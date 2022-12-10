package com.gurup.domain.room.buildingobjects;


import java.awt.Point;
import java.awt.Rectangle;

import com.gurup.domain.Position;



public class BuildingObject {
	
	int xStart, yStart;
	private int xLimit;
	private int yLimit;
	private int x;
	private int y;
	private String name;
	private int xPosition;
	private int yPosition;
	Position positions;

	public BuildingObject(String name, int xStart, int yStart, int xLimit, int yLimit) {
		this.name = name;
		this.xStart = xStart;
		this.yStart = yStart;
		this.setxLimit(xLimit);
		this.setyLimit(yLimit);
		this.setX(xStart);
		this.setY(yStart);

		positions = new Position();
		this.xPosition = positions.getxPosition();
		this.yPosition = positions.getyPosition();
	}

	public int[] rectArray() {
		Point pos = new Point(getX(), getY());
		int[] rectValues = {(int) pos.getX(), (int) pos.getY(), this.getxLimit(), this.getyLimit()};
		return rectValues;
	
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

	public Rectangle getRectangle() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,xLimit,yLimit);
	}

	
}
