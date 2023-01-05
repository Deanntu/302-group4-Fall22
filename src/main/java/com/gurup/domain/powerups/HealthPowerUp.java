package com.gurup.domain.powerups;

import java.awt.Point;
import java.awt.Rectangle;

import com.gurup.domain.Player;

public class HealthPowerUp implements PowerUp {

	private static HealthPowerUp healthPowerUp;
	private Player player;
	private String name = "health";
	private int healthIncreaseConstant = 1;
	private boolean storable = false;
	private int xLimit;
	private int yLimit;
	private int x;
	private int y;
	private boolean isActive = false;
	private Integer slotId = null;

	private HealthPowerUp(Player player) {
		this.player = player;
	}

	public static synchronized HealthPowerUp getInstance(Player player) {
		if (healthPowerUp == null) {
			healthPowerUp = new HealthPowerUp(player);
		}
		return healthPowerUp;
	}

	@Override
	public void usePowerUp() {
		// TODO Auto-generated method stub
		activatePowerUp();
	}

	private void activatePowerUp() {
		// TODO Auto-generated method stub
		player.setLife(player.getRemainingLife() + healthIncreaseConstant);
	}

	@Override
	public boolean isStorable() {
		// TODO Auto-generated method stub
		return storable;
	}

	public Rectangle getRectangle() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, xLimit, yLimit);
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

	public boolean isActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int[] rectArray() {
		Point pos = new Point(getX(), getY());
		int[] rectValues = { (int) pos.getX(), (int) pos.getY(), this.getxLimit(), this.getyLimit() };
		return rectValues;

	}
	/*
	 * public void draw(Graphics g) { // TODO Auto-generated method stub Point pos =
	 * new Point(getX(), getY()); if(isActive) { g.setColor(Color.cyan);
	 * g.fillOval((int) pos.getX(), (int) pos.getY(), this.getxLimit(),
	 * this.getyLimit()); } }
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getSlotId() {
		return slotId;
	}

}
