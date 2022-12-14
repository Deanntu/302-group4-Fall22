package com.gurup.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.gurup.domain.Bag;
import com.gurup.domain.Game;
import com.gurup.domain.powerups.VestPowerUp;
import com.gurup.ui.gamescreen.RunningModeScreen;

public class PowerUpController implements KeyListener{
	
	private Bag bag;

	private RunningModeScreen runningModeScreen;
	
	public PowerUpController(Bag bag, RunningModeScreen runningModeScreen) {
		this.runningModeScreen = runningModeScreen;
		this.bag = bag;
		runningModeScreen.addKeyListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (Game.getIsPaused()) {
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_P) {
			bag.selectPowerUp(VestPowerUp.getInstance(null));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
	
}
