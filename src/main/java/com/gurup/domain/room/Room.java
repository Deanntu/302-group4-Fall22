package com.gurup.domain.room;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.gurup.domain.room.buildingobjects.BuildingObject;



public class Room {
	int xStart, yStart;
	private int xLimit;
	private int yLimit;
	private int x;
	private int y;
	private String name;
	private BuildingObject object1, object2;
	private ArrayList<BuildingObject> objects;
	private Key key;
	


	public Room(String name, int xStart, int yStart, int xLimit, int yLimit) {
		this.name = name;
		this.xStart = xStart;
		this.yStart = yStart;
		this.setxLimit(xLimit);
		this.setyLimit(yLimit);
		this.setX(xStart);
		this.setY(yStart);
		this.objects = new ArrayList<>();
		this.key = new Key();
		object1 = new BuildingObject ("oval", 500, 300, 100, 50);
		object2 = new BuildingObject ("rect", 800, 100, 60, 30);
		objects.add(object1);
		objects.add(object2);
		key.hideKey(objects);
	}

	public void draw(Graphics g) {
		object1.draw(g);
		object2.draw(g);
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

	public BuildingObject getObject1() {
		return object1;
	}

	public void setObject1(BuildingObject object1) {
		this.object1 = object1;
	}

	public BuildingObject getObject2() {
		return object2;
	}

	public void setObject2(BuildingObject object2) {
		this.object2 = object2;
	}
	public Boolean isKeyFound(Rectangle rectMouseClick) {
		BuildingObject containerObject = key.getBuildingObject();

		for (BuildingObject bo : objects) {
			if (bo.getRectangle().intersects(rectMouseClick)) {
				if (bo.equals(containerObject)) {
					System.out.println("Key Found");
					return true;
				}
				System.out.println("Key not found");
				return false; // will be changed to bo.shake() to shake object
			}
		}
		System.out.println("Not an object!");
		return false;
	}
	
}
