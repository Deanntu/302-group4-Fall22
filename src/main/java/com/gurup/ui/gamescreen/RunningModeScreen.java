package com.gurup.ui.gamescreen;

import com.gurup.domain.Player;
import com.gurup.domain.room.Room;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.gurup.controller.MovementController;

public class RunningModeScreen extends JPanel{
	
	private Player player;
	private MovementController movementController;
	private Room room;

	
	public RunningModeScreen(Player player, MovementController movementController, Room room) {
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		this.player = player;
		this.room = room;
		this.setMovementController(movementController);

		new Timer(20, e -> {
			repaint();
		}).start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// player.move();
		player.draw(g);
		room.draw(g);

	}

	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}

	public MovementController getMovementController() {
		return movementController;
	}

	public void setMovementController(MovementController movementController) {
		this.movementController = movementController;
	}
}
