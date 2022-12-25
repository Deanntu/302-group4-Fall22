package com.gurup.domain.powerups;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import com.gurup.domain.Player;

public class VestPowerUp implements PowerUp {
	
	private static VestPowerUp vestPowerUp;
	private Player player;
	private String name = "vest";
	private int protectionDurationSeconds = 20;
	private boolean storable = true;
	private int xLimit;
	private int yLimit;
	private int x;
	private int y;
	private boolean isActive = false;
	private Integer slotId = 0;
	
	private VestPowerUp(Player player) {
		this.player = player;
	}
	
	public static synchronized VestPowerUp getInstance(Player player) {
		if (vestPowerUp == null) {
			vestPowerUp = new VestPowerUp(player);
		}
		return vestPowerUp;
	}
	
	@Override
	public void usePowerUp() {
		activatePowerUp();
	}

	private void activatePowerUp() {
		player.setProtected(true);
		// TODO later set to false
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

	/*@Override
	public void draw(Graphics g) {
		Point pos = new Point(getX(), getY());
		if(isActive) {
			g.setColor(Color.ORANGE);
			g.fillOval((int) pos.getX(), (int) pos.getY(), this.getxLimit(), this.getyLimit());
		}
	}*/

	public int getxLimit() {
		return xLimit;
	}

	public void setxLimit(int xLimit) {
		this.xLimit = xLimit;
	}

	public int getyLimit() {
		return yLimit;
	}

	public void setyLimit(int yLimit) {
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
		int[] rectValues = {(int) pos.getX(), (int) pos.getY(), this.getxLimit(), this.getyLimit()};
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

	public int getProtectionDurationSeconds() {
		return protectionDurationSeconds;
	}

}
