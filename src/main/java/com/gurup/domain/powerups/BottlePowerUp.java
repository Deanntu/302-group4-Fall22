package com.gurup.domain.powerups;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import com.gurup.domain.Player;

public class BottlePowerUp implements PowerUp {

	private static BottlePowerUp bottlePowerUp;
	private Player player;
	private String name = "bottle";
	private boolean storable = true;
	private int xLimit;
	private int yLimit;
	private int x;
	private int y;
	private boolean isActive = false;
	private boolean isUsable = false;
	private boolean isUsed = false;
	private Integer slotId = 2;

	private BottlePowerUp(Player player) {
		this.player = player;
	}

	public static synchronized BottlePowerUp getInstance(Player player) {
		if (bottlePowerUp == null) {
			bottlePowerUp = new BottlePowerUp(player);
		}
		return bottlePowerUp;
	}

	@Override
	public void usePowerUp() {
		activatePowerUp();
	}

	public void usePowerUp(String direction) {
		if(isUsable) {	
		switch (direction) {
			case "up":
				moveUp();
				isUsable = false;
				isUsed = true;
				break;
			case "down":
				moveDown();
				isUsable = false;
				isUsed = true;
				break;
			case "left":
				moveLeft();
				//System.out.println("Moved to left");
				isUsable = false;
				isUsed = true;
				break;
			case "right":
				moveRight();
				isUsable = false;
				isUsed = true;
				break;
			}
		}
	}

	private void activatePowerUp() {
		this.isActive = false;
		ThrownBottlePowerUp.getInstance(null).setUsable(true);
		ThrownBottlePowerUp.getInstance(null).setUsed(false);
	}

	@Override
	public boolean isStorable() {
		return storable;
	}

	@Override
	public Rectangle getRectangle() {
		return new Rectangle(x, y, xLimit, yLimit);
	}

	@Override
	public boolean isActive() {
		return isActive;
	}

	@Override
	public void setIsActive(boolean b) {
		isActive = b;
	}

	private int getstartX() {
		return Toolkit.getDefaultToolkit().getScreenSize().width - 100 + 50;
	}

	private int getstartY() {
		return Toolkit.getDefaultToolkit().getScreenSize().height - 175 + 50;
	}

	public int getxLimit() {
		return xLimit;
	}

	public void setXLimit(int xLimit) {
		this.xLimit = xLimit;
	}

	public int getyLimit() {
		return yLimit;
	}

	public void setYLimit(int yLimit) {
		this.yLimit = yLimit;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int[] rectArray() {
		Point pos = new Point(getX(), getY());
		int[] rectValues = { (int) pos.getX(), (int) pos.getY(), this.getxLimit(), this.getyLimit() };
		return rectValues;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getSlotId() {
		return slotId;
	}

	public void moveRight() {
		if (player.getXCurrent() >= this.getstartX()) {
			this.setX(player.getXCurrent());
		} else {
			this.setX(player.getXCurrent() + 100);
		}
		this.setY(player.getYCurrent());
	}

	public void moveLeft() {
		if (player.getXCurrent() <= this.getxLimit()) {
			this.setX(this.getxLimit());
		} else {
			this.setX(player.getXCurrent() - 100);
		}
		this.setY(player.getYCurrent());
		System.out.printf("%d , %d, %d, %d",this.x,this.y,this.xLimit,this.yLimit);
	}

	public void moveUp() {
		if (player.getYCurrent()  <= this.getyLimit()) {
			this.setY(this.getyLimit());
		} else {
			this.setY(player.getYCurrent() - 100);
		}
		this.setY(player.getXCurrent());
	}

	public void moveDown() {
		if (player.getYCurrent()  >= this.getstartY()) {
			this.setY(player.getYCurrent());
		} else {
			this.setY(player.getYCurrent() + 100);
		}
		this.setY(player.getXCurrent());
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	public boolean isUsable() {
		return isUsable;
	}

	public void setUsable(boolean isUsable) {
		this.isUsable = isUsable;
	}
}
