package com.gurup.ui.gamescreen;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.gurup.controller.KeyClickController;
import com.gurup.controller.MovementController;
import com.gurup.domain.Player;
import com.gurup.domain.room.Room;

public class RunningModeScreen extends JPanel{
	
	private Player player;
	private MovementController movementController;
	private KeyClickController keyClickController;
	private Room room;
	private int delayMiliSeconds;

	
	public RunningModeScreen(Player player, MovementController movementController, KeyClickController keyClickController, Room room) {
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		this.player = player;
		this.room = room;
		this.setMovementController(movementController);
		this.setKeyClickController(keyClickController);
		this.delayMiliSeconds = 20;
		new Timer(this.delayMiliSeconds, e -> {
			repaint();
			player.decrementTime(this.delayMiliSeconds);
			room.createPowerUp(this.delayMiliSeconds);
		}).start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// player.move();
		player.draw(g);
		room.draw(g);

	}

	public Dimension getPreferredSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}

	public MovementController getMovementController() {
		return movementController;
	}

	public void setMovementController(MovementController movementController) {
		this.movementController = movementController;
	}

	public KeyClickController getKeyClickController() {
		return keyClickController;
	}

	public void setKeyClickController(KeyClickController keyClickController) {
		this.keyClickController = keyClickController;
	}
}
