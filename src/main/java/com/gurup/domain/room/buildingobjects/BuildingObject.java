package com.gurup.domain.room.buildingobjects;

import java.awt.Rectangle;

public interface BuildingObject {
	public String getName();

	public int[] rectArray();

	public int getxLimit();

	public void setxLimit(int xLimit);

	public int getyLimit();

	public void setyLimit(int yLimit);

	public int getstartX();

	public int getstartY();

	public Rectangle getRectangle();

}
