package com.gurup.domain.powerups;

import java.awt.Rectangle;

import com.gurup.domain.Player;

public class HealthPowerUp implements PowerUp {
	private Player player;
	private int healthIncreaseConstant = 1;
	private boolean storable = false;
	private int xLimit;
	private int yLimit;
	private int x;
	private int y;
	@Override
	public void usePowerUp() {
		// TODO Auto-generated method stub
		activatePowerUp();
		refreshLocations();
	}
	private void refreshLocations() {
		// TODO Auto-generated method stub
		xLimit = 0;
		yLimit = 0;
		x = 0;
		y = 0;
	}

	@Override
	public void activatePowerUp() {
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

}