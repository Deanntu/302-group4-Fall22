package com.gurup.domain;

import java.awt.Color;
import java.awt.Toolkit;
import java.util.Timer;

import javax.swing.SwingUtilities;

import com.gurup.controller.MovementController;
import com.gurup.domain.room.Room;
import com.gurup.ui.ScreenMaker;
import com.gurup.ui.gamescreen.RunningModeScreen;

public class Game {
	
	private static ScreenMaker screenMaker;
	private static Player player;
	private static Room room;
	private static MovementController movementController;
	private static RunningModeScreen runningModeScreen;



	
	
	public static void main(String[] args) {
		screenMaker = new ScreenMaker();
		player = new Player(Color.blue, 50, 50, Toolkit.getDefaultToolkit().getScreenSize().width-100, Toolkit.getDefaultToolkit().getScreenSize().height-150);
		room = new Room ("Student Center", 50, 50, Toolkit.getDefaultToolkit().getScreenSize().width-100, Toolkit.getDefaultToolkit().getScreenSize().height-175);
		

		runningModeScreen = screenMaker.createScreen(player, movementController, room);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				screenMaker.createAndShowGUI(runningModeScreen);
			}
		});
		movementController = new MovementController(player, runningModeScreen);
		
		// running timer task as daemon thread
		Timer timer = new Timer(true);
		System.out.println(Thread.currentThread().getName() + " TimerTask started");
		// cancel after sometime
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.cancel();
	}
}
