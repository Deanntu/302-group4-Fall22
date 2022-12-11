package com.gurup.controller;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.gurup.domain.Player;
import com.gurup.domain.room.Room;
import com.gurup.ui.gamescreen.RunningModeScreen;

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
		int xMouse = e.getX();
		int yMouse = e.getY();
		Rectangle mouseRect = new Rectangle(xMouse, yMouse, 1, 1);
		if (e.getButton() == e.BUTTON1) { // left click
			room.isKeyFound(mouseRect);
            //Boolean pauseButtonClicked = false;
            //pauseButtonClicked = Game.pauseUnpause(mouseRect);
            /*if (Game.getIsPaused()) {
                pauseButtonClicked = Game.tryUnpauseGame(mouseRect);
            }
            else {
                pauseButtonClicked = Game.tryPauseGame(mouseRect);
            }*/
			//if (pauseButtonClicked) System.out.println("Pause Button Clicked");

			// TODO exit button click here
			/*Boolean exitButtonClicked = false;
			if (Game.getIsPaused()) {
				exitButtonClicked = Game.tryUnpauseGame(mouseRect);
			}
			else {
				exitButtonClicked = Game.tryPauseGame(mouseRect);
			}
			if (exitButtonClicked) System.out.println("Exit Button Clicked");*/
		}
		else if (e.getButton() == e.BUTTON3) {
			room.checkPowerUp(mouseRect);
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
