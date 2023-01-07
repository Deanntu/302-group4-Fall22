package com.gurup.domain.room;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

import com.gurup.domain.Game;
import com.gurup.domain.Player;
import com.gurup.domain.TimerOperationResults;
import com.gurup.domain.aliens.Alien;
import com.gurup.domain.aliens.AlienConstants;
import com.gurup.domain.aliens.BlindAlien;
import com.gurup.domain.aliens.ShooterAlien;
import com.gurup.domain.powerups.BottlePowerUp;
import com.gurup.domain.powerups.HealthPowerUp;
import com.gurup.domain.powerups.PowerUp;
import com.gurup.domain.powerups.ThrownBottlePowerUp;
import com.gurup.domain.powerups.TimePowerUp;
import com.gurup.domain.powerups.VestPowerUp;
import com.gurup.domain.room.buildingobjects.BuildingObject;
import com.gurup.domain.room.buildingobjects.BuildingObjectFactory;

public class Room {
	private static int xStart;
	private static int yStart;
	private static int xLimit;
	private static int yLimit;
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
	private Alien createdAlien;
	private int alienCreationCounter;

	public Room(String name, int xStart, int yStart, int xLimit, int yLimit, Player player) {
		this.name = name;
		Room.xStart = xStart;
		Room.yStart = yStart;
		Room.xLimit = xLimit;
		Room.yLimit = yLimit;
		this.objects = new ArrayList<>();
		this.key = new Key();
		this.player = player;

		BuildingObjectFactory buildingObjectFactory = new BuildingObjectFactory();
		BuildingObject object1 = buildingObjectFactory.createBuildingObject("Bin", 500, 300, 30, 35);
		BuildingObject object2 = buildingObjectFactory.createBuildingObject("Table", 800, 100, 60, 40);
		objects.add(object1);
		objects.add(object2);
		key.hideKey(objects);
		pauseButton = new Rectangle(0, 0, 50, 50);
		exitButton = new Rectangle(0, 0, 50, 50);
		initPowerUps();

	}

	public Boolean isKeyFound(Rectangle rectMouseClick) {
		// MODIFIES: nothing
		// REQUIRES: rectangle that represents the mouse click
		// EFFECTS: Returns true if the key is found, false otherwise:
		// If the mouseRectangle does not intersect with the key, return false
		// If the game is paused, return false
		// If the mouseRectangle intersects with the key and player is near to object that contains the key, return true
		// If the mouseRectangle intersects with the key and player is not near to object that contains the key, return false
		if (!rectMouseClick.intersects(new Rectangle(xStart, yStart, xLimit, yLimit))) {
			// System.out.println("Did not click inside the room");
			return false;
		}

		if (Game.getIsPaused()) {
			// System.out.println("Cannot look for key if the game is paused. ");
			return false;
		}

		BuildingObject containerObject = key.getBuildingObject();
		Rectangle playerRect = player.getRectangle();
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
		if (Game.getIsPaused())
			return;
		for (PowerUp p : powerUps) {
			if (!p.getRectangle().intersects(mouseRect))
				continue;
			if (p.isActive()) {
				if (p.isStorable()) {
					Game.getBag().storePowerUp(p);
				} else {
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
			if (powerUpCreationCounter == 2) {
				int[] newXandY = getRandomLocation();
				if (created != null)
					created.setIsActive(false);
				int randomIndex = random.nextInt(powerUps.size());
				randomIndex = 3;
				System.out.println(randomIndex);
				created = powerUps.get(randomIndex);
				created.setXCurrent(newXandY[0]);
				created.setYCurrent(newXandY[1]);
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

	private int[] getRandomLocation() {
		Random random = new Random();
		int tempX = random.nextInt(Toolkit.getDefaultToolkit().getScreenSize().width - 100 - 50);
		int tempY = random.nextInt(Toolkit.getDefaultToolkit().getScreenSize().height - 175 - 50);
		tempX += 50; // These are added since random.nextInt with 2 arguments does not work on older
						// versions of Java.
		tempY += 50;
		int[] locations = { tempX, tempY };
		return locations;
	}

	public TimerOperationResults createAlien(int delayMiliSeconds) {
		if (Game.getIsPaused())
			return TimerOperationResults.PAUSED;
		Random random = new Random();
		if (timeCounter % (1000 / delayMiliSeconds) == 0) {
			if (alienCreationCounter == 2) {
				int randomIndex = random.nextInt(2);
				int[] newXandY = getRandomLocation();
				switch (randomIndex) {
				case 0:
					createdAlien = new BlindAlien(10, 10, AlienConstants.xLen.getValue(), AlienConstants.yLen.getValue());
					break;
				case 1:
					createdAlien = new ShooterAlien(10, 10, AlienConstants.xLen.getValue(), AlienConstants.yLen.getValue());
				}
				createdAlien.setXCurrent(newXandY[0]);
				createdAlien.setYCurrent(newXandY[1]);
				createdAlien.setActive(true);
				alienCreationCounter = 1;
			} else {
				alienCreationCounter++;
			}
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



	public static int getXLimit() {
		return xLimit;
	}

	public void setXLimit(int xLimit) {
		Room.xLimit = xLimit;
	}

	public static int getYLimit() {
		return yLimit;
	}

	public void setYLimit(int yLimit) {
		Room.yLimit = yLimit;
	}

	public static int getstartX() {
		return xStart;
	}

	public static int getstartY() {
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

	public ArrayList<PowerUp> getPowerUps() {
		return powerUps;
	}

	// private methods
	private void initPowerUps() {
		powerUps = new ArrayList<PowerUp>();
		TimePowerUp t = TimePowerUp.getInstance(player);
		HealthPowerUp h = HealthPowerUp.getInstance(player);
		VestPowerUp v = VestPowerUp.getInstance(player);
		BottlePowerUp b = BottlePowerUp.getInstance(player);
		ThrownBottlePowerUp.getInstance(player);
		t.setXCurrent(420);
		t.setXLen(50);
		t.setYCurrent(320);
		t.setYLen(50);

		h.setXCurrent(420);
		h.setXLen(50);
		h.setYCurrent(320);
		h.setYLen(50);

		v.setXCurrent(420);
		v.setXLen(50);
		v.setYCurrent(320);
		v.setYLen(50);

		b.setXCurrent(420);
		b.setXLen(50);
		b.setYCurrent(320);
		b.setYLen(50);

		powerUps.add(t);
		powerUps.add(h);
		powerUps.add(v);
		powerUps.add(b);

	}

	public Alien getCreatedAlien() {
		// TODO Auto-generated method stub
		return createdAlien;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}
	


}
