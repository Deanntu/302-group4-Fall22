package com.gurup.domain.aliens;

import java.awt.Rectangle;
import java.util.Random;

import com.gurup.domain.Player;
import com.gurup.domain.powerups.ThrownBottlePowerUp;
import com.gurup.domain.room.Room;

public class BlindAlien implements Alien {


	private String name = "Blind";
	private int xStart;
	private int yStart;
	private int xLen;
	private int yLen;
	private int xCurrent;
	private int yCurrent;
	private boolean isActive = false;
	private Random random = new Random();

	private Player player;
	private ThrownBottlePowerUp thrownBottlePowerUp = ThrownBottlePowerUp.getInstance(player);
	public BlindAlien(int xStart, int yStart, int xLen, int yLen) {
		this.xStart = xStart;
		this.yStart = yStart;
		this.xLen = xLen;
		this.yLen = yLen;



	}

	public BlindAlien() {
		// TODO Auto-generated constructor stub


	}

	public void moveToBottle(){
		if (thrownBottlePowerUp.isActive() && thrownBottlePowerUp.isUsed()) {
			int bottleX = thrownBottlePowerUp.getXCurrent();
			int bottleY = thrownBottlePowerUp.getYCurrent();
			int blindX = getXCurrent();
			int blindY = getYCurrent();

			while(blindX == bottleX && blindY == bottleY){
				if(blindX <= bottleX){
					moveRight();
				}else{
					moveLeft();
				}

				if(blindY <= bottleY){
					moveDown();
				}else{
					moveUp();
				}
			}

		}
	}

	public void moveRight() {
		System.out.println("MOVE RIGHT START: xCurrent: " + xCurrent + " yCurrent:" + yCurrent);
		if (this.xCurrent >= Room.getXLimit()) {
			this.xCurrent = (Room.getXLimit());
		} else {
			this.xCurrent = (this.xCurrent + 1);
		}
		System.out.println("MOVE RIGHT END: xCurrent: " + xCurrent + " yCurrent:" + yCurrent);

	}

	public void moveLeft() {
		System.out.println("MOVE LEFT START: xCurrent: " + xCurrent + " yCurrent:" + yCurrent);
		if (this.xCurrent <= Room.getstartX()) {
			this.xCurrent = (Room.getstartX());
		} else {
			this.xCurrent = (this.xCurrent - 1);
		}
		System.out.println("MOVE LEFT END: xCurrent: " + xCurrent + " yCurrent:" + yCurrent);

	}

	public void moveUp() {
		System.out.println("MOVE UP START: xCurrent: " + xCurrent + " yCurrent:" + yCurrent);

		if (this.yCurrent <= Room.getstartY()) {
			this.yCurrent = (Room.getstartY());
		} else {
			this.yCurrent = (this.yCurrent - 1);
		}
		System.out.println("MOVE UP END: xCurrent: " + xCurrent + " yCurrent:" + yCurrent);

	}

	public void moveDown() {
		System.out.println("MOVE DOWN START: xCurrent: " + xCurrent + " yCurrent:" + yCurrent);

		if (this.yCurrent >= Room.getYLimit()) {
			this.yCurrent = (Room.getYLimit());
		} else {
			this.yCurrent = (this.yCurrent + 1);
		}
		System.out.println("MOVE DOWN START: xCurrent: " + xCurrent + " yCurrent:" + yCurrent);

	}
	public void randomMove() {
		int direction = random.nextInt(4);
		int counter = 5;
		System.out.println("RANDOM MOVE START: xCurrent: " + xCurrent + " yCurrent:" + yCurrent);
		switch (direction) {
			case 0:
				while (counter!=0){
					moveRight();
					counter-=1;
				}
			case 1:
				while (counter!=0){
					moveLeft();
					counter-=1;
				}
			case 2:
				while (counter!=0){
					moveUp();
					counter-=1;
				}
			case 3:
				while (counter!=0){
					moveDown();
					counter-=1;
				}

		}
		System.out.println("RANDOM MOVE END: xCurrent: " + xCurrent + " yCurrent:" + yCurrent);

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
