package com.gurup.ui;


import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.gurup.controller.MovementController;
import com.gurup.domain.Player;
import com.gurup.domain.room.Room;
import com.gurup.ui.gamescreen.RunningModeScreen;

public class ScreenMaker {
	public RunningModeScreen createScreen(Player player, MovementController movementController, Room room) {
		RunningModeScreen runningModeScreen = new RunningModeScreen(player, movementController, room);
		return runningModeScreen;
	}
	public RunningModeScreen createAndShowGUI(RunningModeScreen runningModeScreen) {

		System.out.println(Thread.currentThread().getName() + " Created GUI " + SwingUtilities.isEventDispatchThread());

		JFrame f = new JFrame("OYUN");
		f.setExtendedState(f.getExtendedState() | JFrame.MAXIMIZED_BOTH);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Player player = new Player(Color.blue, 20, 20, 450, 450);
		f.add(runningModeScreen);
		
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		return runningModeScreen;
	}
}
