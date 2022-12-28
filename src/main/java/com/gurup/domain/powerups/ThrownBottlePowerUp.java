package com.gurup.domain.powerups;

import java.awt.Rectangle;
import java.awt.Toolkit;

import com.gurup.domain.Player;

public class ThrownBottlePowerUp implements PowerUp {

	private static ThrownBottlePowerUp thrownBottlePowerUp;
	private Player player;
	private String name = "thrownbottle";
	private int xLimit;
	private int yLimit;
	private int x;
	private int y;
	private boolean isUsable = false;
	private boolean isUsed = false;
	private int[] throwDestination;
	private int[] throwSource;

	private ThrownBottlePowerUp(Player player) {
		this.player = player;
	}

	public static synchronized ThrownBottlePowerUp getInstance(Player player) {
		if (thrownBottlePowerUp == null) {
			thrownBottlePowerUp = new ThrownBottlePowerUp(player);
		}
		return thrownBottlePowerUp;
	}

	public void usePowerUp(String direction) {
		// EFFECTS: if not used yet, create a thrown bottle at the target direction
		// else, do nothing
		// if the range extends further than a wall, put it within the borders of the room
		if (isUsable) {
			setThrowDestinationAndSource();
			switch (direction) {
			case "up":
				moveUp();
				setUsable(false);
				setUsed(true);
				break;
			case "down":
				moveDown();
				setUsable(false);
				setUsed(true);
				break;
			case "left":
				moveLeft();
				System.out.println("Moved to left");
				setUsable(false);
				setUsed(true);
				break;
			case "right":
				moveRight();
				setUsable(false);
				setUsed(true);
				break;
			}
		}
		
	}

	public boolean isUsable() {
		return isUsable;
	}

	public void setUsable(boolean isUsable) {
		this.isUsable = isUsable;
	}

	public void moveRight() {
		if (player.getX() >= this.getstartX()) {
			this.setX(player.getX());
		} else {
			this.setX(player.getX() + 100);
		}
		this.setY(player.getY());
	}

	public void moveLeft() {
		if (player.getX() <= this.getxLimit()) {
			this.setX(this.getxLimit());
		} else {
			this.setX(player.getX() - 100);
		}
		this.setY(player.getY());
		System.out.printf("%d , %d, %d, %d", this.x, this.y, this.xLimit, this.yLimit);
	}

	public void moveUp() {
		if (player.getY() <= this.getyLimit()) {
			this.setY(this.getyLimit());
		} else {
			this.setY(player.getY() - 100);
		}
		this.setX(player.getX());
	}

	public void moveDown() {
		if (player.getY() >= this.getstartY()) {
			this.setY(player.getY());
		} else {
			this.setY(player.getY() + 100);
		}
		this.setX(player.getX());
	}

	@Override
	public void usePowerUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isStorable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Rectangle getRectangle() {
		return new Rectangle(x, y, xLimit, yLimit);
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setIsActive(boolean b) {
		// TODO Auto-generated method stub

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
		int[] rectValues = { getX(), getY(), this.getxLimit(), this.getyLimit() };
		return rectValues;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public int getSlotId() {
		// TODO Auto-generated method stub
		return 0;
	}

	private void setThrowDestinationAndSource() {
		this.throwDestination = rectArray();
		this.x = player.getX();
		this.y = player.getY();
		this.xLimit = BottlePowerUp.getInstance(null).getxLimit();
		this.yLimit = BottlePowerUp.getInstance(null).getyLimit();
		this.throwSource = new int[] { x, y, xLimit, yLimit };
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int[] getThrowDestination() {
		return throwDestination;
	}

	public int[] getThrowSource() {
		return throwSource;
	}

}
