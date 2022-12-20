package com.gurup.controller;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.gurup.domain.Bag;
import com.gurup.domain.Game;
import com.gurup.domain.powerups.BottlePowerUp;
import com.gurup.domain.powerups.ThrownBottlePowerUp;
import com.gurup.domain.powerups.VestPowerUp;
import com.gurup.ui.gamescreen.RunningModeScreen;


public class PowerUpController implements KeyListener{
	
	private Bag bag;

	private RunningModeScreen runningModeScreen;
	private boolean bottleFlag = false;
	private Graphics g;
	
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
		if (e.getKeyCode() == KeyEvent.VK_B) {
			bag.selectPowerUp(BottlePowerUp.getInstance(null));
			if(BottlePowerUp.getInstance(null).isUsable()) {
				BottlePowerUp.getInstance(null).usePowerUp();
			}
		}
		// TODO Movement of Bottle;
		if (ThrownBottlePowerUp.getInstance(null).isUsable()){
			if (e.getKeyCode() == KeyEvent.VK_A) {
				ThrownBottlePowerUp.getInstance(null).usePowerUp("left");
			}
			if (e.getKeyCode() == KeyEvent.VK_W) {
				ThrownBottlePowerUp.getInstance(null).usePowerUp("up");
			}
			if (e.getKeyCode() == KeyEvent.VK_X) {
				ThrownBottlePowerUp.getInstance(null).usePowerUp("down");
			}
			if (e.getKeyCode() == KeyEvent.VK_D) {
				ThrownBottlePowerUp.getInstance(null).usePowerUp("right");
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
	
}
