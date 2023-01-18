package com.gurup.domain.aliens;

import java.awt.Rectangle;

import com.gurup.domain.Player;
import com.gurup.domain.powerups.ThrownBottlePowerUp;
import com.gurup.domain.room.Room;

public class ShooterAlien implements Alien {

	private String name = "Shooter";
	private int xStart;
	private int yStart;
	private int xLen;
	private int yLen;
	private int xCurrent;
	private int yCurrent;
	private boolean isActive = false;

	private int diff = 40;
	private Player player;
	private ThrownBottlePowerUp thrownBottlePowerUp = ThrownBottlePowerUp.getInstance(player);

	public ShooterAlien(int xStart, int yStart, int xLen, int yLen) {
		this.xStart = xStart;
		this.yStart = yStart;
		this.xLen = xLen;
		this.yLen = yLen;
	}

	public ShooterAlien() {
		// TODO Auto-generated constructor stub
		shoot();
	}

	public void shoot(){
		if(!player.isProtected()&& playerInsideRange()){
			shootAnimation();
			player.setLife(player.getLife() - 1);
		}
	}
	public void shootAnimation(){
		int x = getXCurrent();
		int y = getYCurrent();
		int playerX = player.getXCurrent();
		int playerY = player.getYCurrent();




	}

	public boolean playerInsideRange(){
		int playerX = player.getXCurrent();
		int playerY = player.getYCurrent();

		int diffX = playerX - getXCurrent();
		int diffY = playerY - getYCurrent();

		if(diffX < diff  && diffY < diff){
			return true;
		}
		return false;

	}

	public void moveRight() {
		if (this.xCurrent >= Room.getXLimit()) {
			this.xCurrent = (Room.getXLimit());
		} else {
			this.xCurrent = (this.xCurrent + 10);
		}
	}
	
	public void moveLeft() {
		if (this.xCurrent <= Room.getstartX()) {
			this.xCurrent = (Room.getstartX());
		} else {
			this.xCurrent = (this.xCurrent - 10);
		}
	}
	
	public void moveUp() {
		if (this.yCurrent <= Room.getstartY()) {
			this.yCurrent = (Room.getstartY());
		} else {
			this.yCurrent = (this.yCurrent - 10);
		}
	}
	
	public void moveDown() {
		if (this.yCurrent >= Room.getYLimit()) {
			this.yCurrent = (Room.getYLimit());
		} else {
			this.yCurrent = (this.yCurrent + 10);
		}
	}

	public Rectangle getRectangle() {
		return new Rectangle(xCurrent, yCurrent, xLen, yLen);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getXStart() {
		return xStart;
	}

	public void setXStart(int xStart) {
		this.xStart = xStart;
	}

	public int getYStart() {
		return yStart;
	}

	public void setYStart(int yStart) {
		this.yStart = yStart;
	}

	public int getXLen() {
		return xLen;
	}

	public void setXLen(int xLen) {
		this.xLen = xLen;
	}

	public int getYLen() {
		return yLen;
	}

	public void setYLen(int yLen) {
		this.yLen = yLen;
	}

	public int getXCurrent() {
		return xCurrent;
	}

	public void setXCurrent(int xCurrent) {
		this.xCurrent = xCurrent;
	}

	public int getYCurrent() {
		return yCurrent;
	}

	public void setYCurrent(int yCurrent) {
		this.yCurrent = yCurrent;
	}

	@Override
	public int[] rectArray() {
		int[] rectValues = {xCurrent, yCurrent, xLen, yLen};
		return rectValues;
	}
}
