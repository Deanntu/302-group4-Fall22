package com.gurup.domain.room;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import com.gurup.domain.Game;
import com.gurup.domain.Player;
import com.gurup.domain.powerups.HealthPowerUp;
import com.gurup.domain.powerups.PowerUp;
import com.gurup.domain.powerups.TimePowerUp;
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
	private ArrayList<PowerUp> powerUps;
	private Key key;
	private Player player;
	private Rectangle pauseButton;
	private Rectangle exitButton;
	private int powerUpCreationCounter = 0;
	private int timeCounter = 1;
	private PowerUp created;



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
		exitButton = new Rectangle(0,0,50,50);
		initPowerUps();
		Game.getBag().setupBag(powerUps);
	}

	public void draw(Graphics g) { // TODO move this into UI layer
		object1.draw(g);
		object2.draw(g);
		if(created != null) {
			created.draw(g);
		}
		g.setColor(Color.BLACK);
	    Font font = new Font("Courier New", Font.BOLD, 20);
	    FontMetrics metrics = g.getFontMetrics(font);
	    int x = xStart + (xLimit - metrics.stringWidth(name)) / 2;
	    int y = yStart - 5;
	    g.setFont(font);
	    g.drawString(name, x, y);

		// life and time edit
		String remainingTime = "Remaining time: " + player.getRemainingTime();
		String remainingLife = "Remaining life: " + player.getRemainingLife();
		int timeX = xStart;
		int timeY = yStart - 5;;
		int lifeX = xStart + (xLimit - metrics.stringWidth(remainingLife));
		int lifeY = yStart - 5;
		g.drawString(remainingTime,timeX,timeY);
		g.drawString(remainingLife,lifeX,lifeY);

		// pause button
		g.draw3DRect(xStart,  yStart, xLimit, yLimit, true);
		pauseButton.height = 20;
		pauseButton.width = 60;
		pauseButton.x = xLimit- 2*pauseButton.width;
		pauseButton.y = yStart-pauseButton.height-20;

		String pause = "Pause";
		int pauseX = pauseButton.x;
		int pauseY = pauseButton.y+15;
		g.drawRect(pauseButton.x, pauseButton.y, pauseButton.width, pauseButton.height);
		g.drawString(pause, pauseX, pauseY);

		// exit button
		g.draw3DRect(xStart,  yStart, xLimit, yLimit, true);
		exitButton.height = 20;
		exitButton.width = 60;
		exitButton.x = xLimit- exitButton.width;
		exitButton.y = yStart-exitButton.height-20;

		String exit = "Exit";
		int exitX = exitButton.x;
		int exitY = exitButton.y+15;
		g.drawRect(exitButton.x, exitButton.y, exitButton.width, exitButton.height);
		g.drawString(exit, exitX, exitY);

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
	public void checkPowerUp() {
		for(PowerUp p: powerUps) {
			if(p.isActive()) {
				if(p.isStorable()) {
					Game.getBag().storePowerUp(p);
				}
				p.usePowerUp();
				p.setIsActive(false);
			}
		}
	}
	public void createPowerUp(int delayMiliSeconds) {
		if (timeCounter%(1000/delayMiliSeconds) == 0) {
			timeCounter = 1;
			if (powerUpCreationCounter == 10) {
				if(created != null) created.setIsActive(false);
				Random random = new Random();
				System.out.println(powerUps.size()+1);
				int randomIndex = random.nextInt(powerUps.size());
				System.out.println(randomIndex);
				created = powerUps.get(randomIndex);
				created.setIsActive(true);
				powerUpCreationCounter = 0;
			}
			else {
				
				powerUpCreationCounter++;
			}
		}
		else {
			timeCounter++;
		}

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
	public Rectangle getExirButton() {
		return exitButton;
	}
	public void setExitButton(Rectangle exitButton) {
		this.exitButton = exitButton;
	}
	// private methods
	private void initPowerUps() {
		powerUps = new ArrayList<PowerUp>();
		TimePowerUp t = new TimePowerUp(player);
		HealthPowerUp h = new HealthPowerUp(player);
		t.setX(420);
		t.setxLimit(50);
		t.setY(320);
		t.setxLimit(50);
		h.setX(420);
		h.setxLimit(50);
		h.setY(320);
		h.setxLimit(50);
		powerUps.add(t);
		powerUps.add(h);
	}
}
