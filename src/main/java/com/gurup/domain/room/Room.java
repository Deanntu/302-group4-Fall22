package com.gurup.domain.room;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import com.gurup.domain.room.buildingobjects.Object;



public class Room {
	int xStart, yStart;
	private int xLimit;
	private int yLimit;
	private int x;
	private int y;
	private String name;
	private Object object1, object2;


	public Room(String name, int xStart, int yStart, int xLimit, int yLimit) {
		this.name = name;
		this.xStart = xStart;
		this.yStart = yStart;
		this.setxLimit(xLimit);
		this.setyLimit(yLimit);
		this.setX(xStart);
		this.setY(yStart);		
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
	    Font font = new Font("Courier New", Font.BOLD, 20);
	    FontMetrics metrics = g.getFontMetrics(font);
	    int x = xStart + (xLimit - metrics.stringWidth(name)) / 2;
	    int y = yStart - 5;
	    g.setFont(font);
	    g.drawString(name, x, y);
		g.draw3DRect(xStart,  yStart, xLimit, yLimit, true);
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

	public Object getObject1() {
		return object1;
	}

	public void setObject1(Object object1) {
		this.object1 = object1;
	}

	public Object getObject2() {
		return object2;
	}

	public void setObject2(Object object2) {
		this.object2 = object2;
	}
	
}
