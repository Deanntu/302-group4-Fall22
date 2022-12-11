package com.gurup.domain.powerups;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface PowerUp {
	void usePowerUp();
	boolean isStorable();
	Rectangle getRectangle();
	boolean isActive();
	void setIsActive(boolean b);
	void draw(Graphics g);
	void setX(int i);
	void setxLimit(int i);
	void setY(int i);
	void setyLimit(int i);
}
