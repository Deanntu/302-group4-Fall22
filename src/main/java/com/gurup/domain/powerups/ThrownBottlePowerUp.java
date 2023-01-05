package com.gurup.domain.powerups;

import java.awt.Rectangle;

import com.gurup.domain.Player;
import com.gurup.domain.room.RoomConstants;

public class ThrownBottlePowerUp implements PowerUp {

	private static ThrownBottlePowerUp thrownBottlePowerUp;
	private Player player;
	private String name = "thrownbottle";
	private int xLimit;
	private int yLimit;
	private int x;
	private int y;
	private int minX;
	private int minY;
	private int maxX;
	private int maxY;
	private boolean isUsable = false;
	private boolean isUsed = false;
	private int[] throwDestination;
	private int[] throwSource;

	private ThrownBottlePowerUp(Player player) {
		this.player = player;
		minX = RoomConstants.xStart.getValue();
		minY = RoomConstants.yStart.getValue();
		maxX = RoomConstants.xLimit.getValue();
		maxY = RoomConstants.yLimit.getValue();
		// System.out.printf("player's minx: %d, miny: %d, maxx: %d, maxy: %d%n",
		// player.getstartX(), player.getstartY(), player.getxLimit(),
		// player.getyLimit());
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
		// if the range extends further than a wall, put it within the borders of the
		// room
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
				// System.out.println("Moved to left");
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
		if (player.getXCurrent() + 100 >= this.maxX) {
			this.setX(RoomConstants.xLimit.getValue());
		} else {
			this.setX(player.getXCurrent() + 100);
		}
		this.setY(player.getYCurrent());
	}

	public void moveLeft() {
		if (player.getXCurrent() - 100 <= this.minX) {
			this.setX(RoomConstants.xStart.getValue());
		} else {
			this.setX(player.getXCurrent() - 100);
		}
		this.setY(player.getYCurrent());
		// System.out.printf("bottle x: %d , bottle y: %d, bottle xlimit: %d, bottle
		// ylimit: %d%n", this.x, this.y, this.xLimit, this.yLimit);
	}

	public void moveUp() {
		if (player.getYCurrent() - 100 <= this.minY) {
			this.setY(RoomConstants.yStart.getValue());
		} else {
			this.setY(player.getYCurrent() - 100);
		}
		this.setX(player.getXCurrent());
	}

	public void moveDown() {
		if (player.getYCurrent() + 100 >= this.maxY) {
			this.setY(RoomConstants.yLimit.getValue());
		} else {
			this.setY(player.getYCurrent() + 100);
		}
		this.setX(player.getXCurrent());
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
		this.x = player.getXCurrent();
		this.y = player.getYCurrent();
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

	public static void setNull() {
		thrownBottlePowerUp = null;
	}

}
