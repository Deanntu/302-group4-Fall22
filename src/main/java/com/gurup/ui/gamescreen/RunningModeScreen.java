package com.gurup.ui.gamescreen;

import com.gurup.domain.Player;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.gurup.controller.MovementController;

public class RunningModeScreen extends JPanel{
	
	private Player player;
	private MovementController movementController;
	
	public RunningModeScreen(Player player, MovementController movementController) {
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		this.player = player;
		this.movementController = movementController;
		new Timer(20, e -> {
			repaint();
		}).start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// player.move();
		player.draw(g);
	}

	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}
}
