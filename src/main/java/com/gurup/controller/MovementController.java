package com.gurup.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.gurup.domain.Player;
import com.gurup.ui.gamescreen.RunningModeScreen;

public class MovementController implements KeyListener{
	
	private Player player;

	private RunningModeScreen runningModeScreen;
	
	public MovementController(Player player, RunningModeScreen runningModeScreen) {
		this.runningModeScreen = runningModeScreen;
		this.player = player;
		runningModeScreen.addKeyListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (player.getX() >= player.getxLimit()) {
				player.setX(player.getxLimit());
			} else {
				player.setX(player.getX() + 10);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (player.getX() <= 0) {
				player.setX(0);
			} else {
				player.setX(player.getX() - 10);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (player.getY() <= 0) {
				player.setY(0);
			} else {
				player.setY(player.getY() - 10);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (player.getY() >= player.getyLimit()) {
				player.setY(player.getyLimit());
			} else {
				player.setY(player.getY() + 10);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
	
}
