package com.gurup.domain.powerups;

import java.awt.Rectangle;

public interface PowerUp {
	void usePowerUp();
	void activatePowerUp();
	boolean isStorable();
	Rectangle getRectangle();
}
