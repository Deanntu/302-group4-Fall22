package com.gurup.domain.room;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.gurup.domain.Game;
import com.gurup.domain.Player;
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
	private Player player;
	private Rectangle pauseButton;



	public Room(String name, int xStart, int yStart, int xLimit, int yLimit, Player player) {
		this.name = name;
		this.xStart = xStart;
		this.yStart = yStart;
		this.setxLimit(xLimit);
		this.setyLimit(yLimit);
		this.setX(xStart);
		this.setY(yStart);
		this.objects = new ArrayList<>();
		this.key = new Key();
		this.player = player;
		object1 = new BuildingObject ("oval", 500, 300, 100, 50);
		object2 = new BuildingObject ("rect", 800, 100, 60, 30);
		objects.add(object1);
		objects.add(object2);
		key.hideKey(objects);
		pauseButton = new Rectangle(0,0,50,50);
	}

	public void draw(Graphics g) { // TODO move this into UI layer
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
		g.drawRect(pauseButton.x, pauseButton.y, pauseButton.width, pauseButton.height);
	}
	public Boolean isKeyFound(Rectangle rectMouseClick) {
		if (!rectMouseClick.intersects(new Rectangle(xStart, yStart, xLimit, yLimit))) {
			//System.out.println("Did not click inside the room");
			return false;
		}
		if (Game.getIsPaused()) {
			//System.out.println("Cannot look for key if the game is paused. ");
			return false;
		}
		BuildingObject containerObject = key.getBuildingObject();
		Rectangle playerRect = new Rectangle(player.getX(), player.getY(), player.getSize(), player.getSize());
		for (BuildingObject bo : objects) {
			if (bo.getRectangle().intersects(rectMouseClick)) {
				if (!playerRect.intersects(bo.getRectangle())) {
                    System.out.println("Player is not next to the object");
                    return false;
                }
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
	
	// Getters/Setters
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
	public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public Rectangle getPauseButton() {
        return pauseButton;
    }

    public void setPauseButton(Rectangle pauseButton) {
        this.pauseButton = pauseButton;
    }
}
