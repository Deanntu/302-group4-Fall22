package com.gurup.domain.room;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

import com.gurup.domain.Game;
import com.gurup.domain.Player;
import com.gurup.domain.TimerOperationResults;
import com.gurup.domain.aliens.Alien;
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
	private Alien createdAlien;
	private int alienCreationCounter;

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
			if (powerUpCreationCounter == 12) {
				int[] newXandY = getRandomLocation();
				if (created != null)
					created.setIsActive(false);
				int randomIndex = random.nextInt(powerUps.size());
				System.out.println(randomIndex);
				created = powerUps.get(randomIndex);
				created.setX(newXandY[0]);
				created.setY(newXandY[1]);
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
		int tempX = random.nextInt(Toolkit.getDefaultToolkit().getScreenSize().width-100-50);
		int tempY = random.nextInt(Toolkit.getDefaultToolkit().getScreenSize().height-175-50);
		tempX+=50; // These are added since random.nextInt with 2 arguments does not work on older versions of Java.
		tempY+=50;
		int[] locations = {tempX,tempY};
		return locations;
	}
	public TimerOperationResults createAlien(int delayMiliSeconds) {
		if (Game.getIsPaused())
			return TimerOperationResults.PAUSED;
		Random random = new Random();
		if (timeCounter % (1000 / delayMiliSeconds) == 0) {
			if (alienCreationCounter == 10) {
				int randomIndex = random.nextInt(2);
				int[] newXandY = getRandomLocation();
				switch(randomIndex){
					case 0:
						createdAlien = new BlindAlien(10, 10, 40, 40);
						break;
					case 1:
						createdAlien = new ShooterAlien(10, 10, 40, 40);
				}
				createdAlien.setX(newXandY[0]);
				createdAlien.setY(newXandY[1]);
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

	public ArrayList <PowerUp> getPowerUps() {
		return powerUps;
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
		b.setxLimit(30);
		b.setY(320);
		b.setyLimit(50);

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
