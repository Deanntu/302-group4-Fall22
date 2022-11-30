package com.gurup.controller;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.gurup.domain.Player;
import com.gurup.domain.room.Room;
import com.gurup.ui.gamescreen.RunningModeScreen;
import com.gurup.domain.room.buildingobjects.Object;

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
		// TODO Auto-generated method stub
		System.out.println(e.toString());
		if (e.getButton() == e.BUTTON1) { // left click
			System.out.println("Leftclick");
			int xMouse = e.getX();
			int yMouse = e.getY();
			Rectangle mouseRect = new Rectangle(xMouse, yMouse, 1, 1);
			Object keyObj = room.getObject1();
			int xStartKey = keyObj.getX();
			int yStartKey = keyObj.getY();
			int xEndKey = keyObj.getxLimit();
			int yEndKey = keyObj.getyLimit();
			Object nonKeyObj = room.getObject2();
			int xStartNonKey = nonKeyObj.getX();
			int yStartNonKey = nonKeyObj.getY();
			int xEndNonKey = nonKeyObj.getxLimit();
			int yEndNonKey = nonKeyObj.getyLimit();
			Rectangle rectKey = new Rectangle(xStartKey, yStartKey, xEndKey, yEndKey);
			Rectangle rectNonKey = new Rectangle(xStartNonKey, yStartNonKey, xEndNonKey, yEndNonKey);
			if (rectKey.intersects(mouseRect)) {
				System.out.println("Key Found");
			}
			else if (rectNonKey.intersects(mouseRect)) {
				System.out.println("Key Not Found");
			}
			else {
				System.out.println("not an object");
			}
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
