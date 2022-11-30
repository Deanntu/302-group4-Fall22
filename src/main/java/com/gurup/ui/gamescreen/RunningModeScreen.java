package com.gurup.ui.gamescreen;

import com.gurup.domain.Player;
import com.gurup.domain.room.Room;
import com.gurup.domain.room.buildingobjects.Object;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.gurup.controller.MovementController;

public class RunningModeScreen extends JPanel{
	
	private Player player;
	private MovementController movementController;
	private Room room;
	private Object object1, object2;


	
	public RunningModeScreen(Player player, MovementController movementController, Room room, Object object1, Object object2) {
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		this.player = player;
		this.room = room;
		this.movementController = movementController;
		this.object1 = object1;
		this.object2 = object2;

		new Timer(20, e -> {
			repaint();
		}).start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// player.move();
		player.draw(g);
		room.draw(g);
		object1.draw(g);
		object2.draw(g);

	}

	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}
}
