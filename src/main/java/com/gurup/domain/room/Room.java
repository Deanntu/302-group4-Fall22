package com.gurup.domain.room;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import com.gurup.domain.Game;
import com.gurup.domain.Player;
import com.gurup.domain.TimerOperationResults;
import com.gurup.domain.powerups.BottlePowerUp;
import com.gurup.domain.powerups.HealthPowerUp;
import com.gurup.domain.powerups.PowerUp;
import com.gurup.domain.powerups.ThrownBottlePowerUp;
import com.gurup.domain.powerups.TimePowerUp;
import com.gurup.domain.powerups.VestPowerUp;
import com.gurup.domain.room.buildingobjects.BuildingObject;
import com.gurup.domain.room.buildingobjects.BuildingObjectFactory;

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


		BuildingObjectFactory buildingObjectFactory = new BuildingObjectFactory();
		BuildingObject object1 = buildingObjectFactory.createBuildingObject("BIN", 500, 300, 100, 50);
		BuildingObject object2 = buildingObjectFactory.createBuildingObject("TABLE", 800, 100, 60, 30);
		objects.add(object1);
		objects.add(object2);
		key.hideKey(objects);
		pauseButton = new Rectangle(0, 0, 50, 50);
		exitButton = new Rectangle(0, 0, 50, 50);
		initPowerUps();
		Game.getBag().setupBag(powerUps);
	}

	public void draw(Graphics g) { // TODO move this into UI layer //MOVED BY TUGRA KEPT FOR BACKUP

		
		  
		  // pause button g.draw3DRect(xStart, yStart, xLimit, yLimit, true);
		  pauseButton.height = 20; pauseButton.width = 60; pauseButton.x = xLimit-
		  2*pauseButton.width; pauseButton.y = yStart-pauseButton.height-20;
		  
		  String pause = "Pause"; int pauseX = pauseButton.x; int pauseY =
		  pauseButton.y+15; g.drawRect(pauseButton.x, pauseButton.y, pauseButton.width,
		  pauseButton.height); g.drawString(pause, pauseX, pauseY);
		  
		  // exit button g.draw3DRect(xStart, yStart, xLimit, yLimit, true);
		  exitButton.height = 20; exitButton.width = 60; exitButton.x = xLimit-
		  exitButton.width; exitButton.y = yStart-exitButton.height-20;
		  
		  String exit = "Exit"; int exitX = exitButton.x; int exitY = exitButton.y+15;
		  g.drawRect(exitButton.x, exitButton.y, exitButton.width, exitButton.height);
		  g.drawString(exit, exitX, exitY);
		 

	}

	public Boolean isKeyFound(Rectangle rectMouseClick) {
		if (!rectMouseClick.intersects(new Rectangle(xStart, yStart, xLimit, yLimit))) {
			// System.out.println("Did not click inside the room");
			return false;
		}
		if (Game.getIsPaused()) {
			// System.out.println("Cannot look for key if the game is paused. ");
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

	public void checkPowerUp(Rectangle mouseRect) {
		if(Game.getIsPaused()) return;
		for (PowerUp p : powerUps) {
			if (!p.getRectangle().intersects(mouseRect))
				continue;
			if (p.isActive()) {
				if (p.isStorable()) {
					Game.getBag().storePowerUp(p);
				}
				else {
					p.usePowerUp();
				}
				p.setIsActive(false);
			}
		}
	}

	public TimerOperationResults createPowerUp(int delayMiliSeconds) {
		if (Game.getIsPaused())
			return TimerOperationResults.PAUSED;
		Random random = new Random();
		if (timeCounter % (1000 / delayMiliSeconds) == 0) {
			timeCounter = 1;
			if (powerUpCreationCounter == 12) {
				if (created != null)
					created.setIsActive(false);
				int randomIndex = random.nextInt(powerUps.size());
				System.out.println(randomIndex);
				created = powerUps.get(randomIndex);
				created.setIsActive(true);
				powerUpCreationCounter = 1;
				/*
				 * if(randomIndex == 0) { created = new TimePowerUp(player);
				 * created.setIsActive(true);
				 * 
				 * } else if(randomIndex == 1) { created = new HealthPowerUp(player);
				 * created.setIsActive(true); } created.setX(420); created.setxLimit(50);
				 * created.setY(320); created.setyLimit(50); powerUps.add(created);
				 */
			} else {
				powerUpCreationCounter++;
			}
		} else {
			timeCounter++;
		}
		return TimerOperationResults.TIME_UP;

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
	public ArrayList<BuildingObject> getObjects() {
		return objects;
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

	public PowerUp getCreated() {
		return created;
	}

	// private methods
	private void initPowerUps() {
		powerUps = new ArrayList<PowerUp>();
		TimePowerUp t = TimePowerUp.getInstance(player);
		HealthPowerUp h = HealthPowerUp.getInstance(player);
		VestPowerUp v = VestPowerUp.getInstance(player);
		BottlePowerUp b = BottlePowerUp.getInstance(player);
		ThrownBottlePowerUp tbp = ThrownBottlePowerUp.getInstance(player);
		t.setX(420);
		t.setxLimit(50);
		t.setY(320);
		t.setyLimit(50);

		h.setX(420);
		h.setxLimit(50);
		h.setY(320);
		h.setyLimit(50);

		v.setX(420);
		v.setxLimit(50);
		v.setY(320);
		v.setyLimit(50);

		b.setX(420);
		b.setxLimit(50);
		b.setY(320);
		b.setyLimit(50);

		powerUps.add(t);
		powerUps.add(h);
		powerUps.add(v);
		powerUps.add(b);
		powerUps.add(tbp);

	}


}
