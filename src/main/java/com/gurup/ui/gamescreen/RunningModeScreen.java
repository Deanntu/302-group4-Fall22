package com.gurup.ui.gamescreen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.gurup.controller.KeyClickController;
import com.gurup.controller.MovementController;
import com.gurup.domain.Game;
import com.gurup.domain.Player;
import com.gurup.domain.room.Room;
import com.gurup.domain.room.buildingobjects.BuildingObject;
import com.gurup.ui.drawer.Drawer;
import com.gurup.domain.Bag;

public class RunningModeScreen extends JPanel {

	private Player player;
	private MovementController movementController;
	private KeyClickController keyClickController;
	private Room room;
	private int delayMiliSeconds;
	FontMetrics metrics;
	Font font;
	Bag bag;
	public RunningModeScreen(Game game, Player player, MovementController movementController,
			KeyClickController keyClickController, Room room) {

		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		this.player = player;
		this.room = room;
		this.bag= Game.getBag();
		this.setMovementController(movementController);
		this.setKeyClickController(keyClickController);
		this.delayMiliSeconds = 20;
		
		JButton pauseButton = new JButton("Pause");
		
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game.pauseUnpause();
				if(Game.getIsPaused()) pauseButton.setText("Resume");
				else {
					pauseButton.setText("Pause");
				}
			}
		});
		add(pauseButton);
		pauseButton.setFocusable(false);
		new Timer(this.delayMiliSeconds, e -> {
			repaint();
			player.decrementTime(this.delayMiliSeconds);
			room.createPowerUp(this.delayMiliSeconds);
		}).start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// player.move();
		setFont(g);
		paintRoomName(g);
		paintLifeAndTime(g);
		paintPlayer(g);
		drawObjects(g);
		paintWall(g);
		drawBag(g);
	}

	private void drawBag(Graphics g) { 
		int slotSizeX = 140;
		int slotSizeY = 45;
		int itemSize = 30;
		int slotStartX = room.getstartX()+room.getxLimit()/2-slotSizeX/2;
		int slotStartY = room.getstartY()+room.getyLimit();
		int fontSize = 14;
		int numberOffsetX = 25;
		int numberOffsetY = 14;
		g.setColor(Color.BLACK);
		g.draw3DRect(slotStartX, slotStartY, slotSizeX, slotSizeY, true);
		g.draw3DRect(slotStartX-slotSizeX, slotStartY, slotSizeX, slotSizeY, true);
		g.draw3DRect(slotStartX+slotSizeX, slotStartY, slotSizeX, slotSizeY, true);
		g.setColor(Color.PINK );
		g.fillOval(slotStartX+slotSizeX/2-itemSize/2, slotStartY+slotSizeY/2-itemSize/2, itemSize, itemSize);
		g.setColor(Color.ORANGE);
		g.fillOval(slotStartX+slotSizeX/2-itemSize/2-slotSizeX, slotStartY+slotSizeY/2-itemSize/2, itemSize, itemSize);
		g.setColor(Color.DARK_GRAY);
		g.fillOval(slotStartX+slotSizeX/2-itemSize/2+slotSizeX, slotStartY+slotSizeY/2-itemSize/2, itemSize, itemSize);
	}
	private void drawObjects(Graphics g) {
		Drawer powerUpDrawer = new Drawer("PowerUp");
		Drawer buildObjectDrawer = new Drawer("Object");
		if (room.getCreated() != null && room.getCreated().isActive()) {
			powerUpDrawer.draw(g, room.getCreated().rectArray(), room.getCreated().getName());
		}
		for (BuildingObject bo : room.getObjects()) {
			buildObjectDrawer.draw(g, bo.rectArray(), bo.getName());
		}
	}

	private void setFont(Graphics g) {
		font = new Font("Courier New", Font.BOLD, 20);
		metrics = g.getFontMetrics(font);
	}

	private void paintRoomName(Graphics g) {
		g.setColor(Color.BLACK);
		int x = room.getstartX() + (room.getxLimit() - metrics.stringWidth(room.getName())) / 2;
		int y = room.getstartY() - 5;
		g.setFont(font);
		g.drawString(room.getName(), x, y);
	}

	private void paintLifeAndTime(Graphics g) {
		String remainingTime = "Remaining time: " + player.getRemainingTime();
		String remainingLife = "Remaining life: " + player.getRemainingLife();
		int timeX = room.getstartX();
		int timeY = room.getstartY() - 5;
		;
		int lifeX = room.getstartX() + (room.getxLimit() - metrics.stringWidth(remainingLife));
		int lifeY = room.getstartY() - 5;
		g.drawString(remainingTime, timeX, timeY);
		g.drawString(remainingLife, lifeX, lifeY);
	}

	private void paintPlayer(Graphics g) {
		Point pos = new Point(player.getX(), player.getY());
		g.setColor(player.getPlayerColor());
		g.fillOval((int) pos.getX(), (int) pos.getY(), player.getSize(), player.getSize());
	}

	private void paintWall(Graphics g) {
		g.setColor(Color.BLACK);
		g.draw3DRect(room.getstartX(), room.getstartY(), room.getxLimit(), room.getyLimit(), true);
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
