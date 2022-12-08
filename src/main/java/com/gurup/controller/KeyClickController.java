package com.gurup.controller;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.gurup.domain.Game;
import com.gurup.domain.Player;
import com.gurup.domain.room.Room;
import com.gurup.ui.gamescreen.RunningModeScreen;
import com.gurup.domain.room.buildingobjects.BuildingObject;

public class KeyClickController implements MouseListener{
	
	private Player player;
	
	private RunningModeScreen runningModeScreen;
	
	private Room room;
	
	public KeyClickController(Player player, RunningModeScreen runningModeScreen, Room room) {
		this.runningModeScreen = runningModeScreen;
		this.player = player;
		this.room = room;
		runningModeScreen.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == e.BUTTON1) { // left click
			int xMouse = e.getX();
			int yMouse = e.getY();
			Rectangle mouseRect = new Rectangle(xMouse, yMouse, 1, 1);
			room.isKeyFound(mouseRect);
			Boolean pauseButtonClicked = Game.tryPauseGame(mouseRect);
			System.out.println(pauseButtonClicked ? "Pause Button Clicked" : "");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
