package com.gurup.domain.room.buildingobjects;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;



public class Object {
	
	int xStart, yStart;
	private int xLimit;
	private int yLimit;
	private int x;
	private int y;
	private String name;

	public Object(String name, int xStart, int yStart, int xLimit, int yLimit) {
		this.name = name;
		this.xStart = xStart;
		this.yStart = yStart;
		this.setxLimit(xLimit);
		this.setyLimit(yLimit);
		this.setX(xStart);
		this.setY(yStart);
	}

	public void draw(Graphics g) {
		Point pos = new Point(getX(), getY());
		if (name.equals("oval")) {
			g.setColor(Color.MAGENTA);			
			g.fillOval((int) pos.getX(), (int) pos.getY(), this.getxLimit(), this.getyLimit());
			
		}if (name.equals("rect")) {
			g.setColor(Color.RED);	
			g.fillRect((int) pos.getX(), (int) pos.getY(), this.getxLimit(), this.getyLimit());
			
		}
	
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
	
}
