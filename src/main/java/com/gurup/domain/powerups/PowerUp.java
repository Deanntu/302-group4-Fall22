package com.gurup.domain.powerups;

import java.awt.Rectangle;

public interface PowerUp {
	void usePowerUp();
	boolean isStorable();
	Rectangle getRectangle();
	boolean isActive();
	void setIsActive(boolean b);
	//void draw(Graphics g);
	void setX(int i);
	void setXLimit(int i);
	void setY(int i);
	void setYLimit(int i);
	int[] rectArray();
	String getName();
	int getSlotId();
}
