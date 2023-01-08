package com.gurup.domain.room.buildingobjects;

import java.awt.Rectangle;

public interface BuildingObject {
	public String getName();

	public int[] rectArray();

	public int getXLen();

	public void setXLen(int xLen);

	public int getYLen();

	public void setYLen(int yLen);

	public int getXCurrent();

	public int getYCurrent();

	public void setXCurrent(int xCurrent);

	public void setYCurrent(int yCurrent);

	public Rectangle getRectangle();

}
