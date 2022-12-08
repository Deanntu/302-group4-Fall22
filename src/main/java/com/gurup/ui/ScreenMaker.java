package com.gurup.ui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.gurup.controller.KeyClickController;
import com.gurup.controller.MovementController;
import com.gurup.domain.Player;
import com.gurup.domain.room.Room;
import com.gurup.ui.gamescreen.LoginScreen;
import com.gurup.ui.gamescreen.MainMenuScreen;
import com.gurup.ui.gamescreen.RunningModeScreen;

public class ScreenMaker {

	public LoginScreen createMainModeScreen() {
		LoginScreen loginScreen = new LoginScreen();
		loginScreen.setTitle("Login Form");

		loginScreen.setBounds(10, 10, 370, 600);
		loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		loginScreen.setResizable(false);

		loginScreen.setLocationRelativeTo(null);
		loginScreen.setVisible(true);
		return loginScreen;
	}

	public MainMenuScreen createMainMenuScreen() {
		MainMenuScreen mainMenuScreen = new MainMenuScreen();
        mainMenuScreen.setTitle("Main Menu");

        mainMenuScreen.setBounds(10, 10, 370, 600);
		mainMenuScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainMenuScreen.setResizable(false);

        mainMenuScreen.setLocationRelativeTo(null);
		mainMenuScreen.setVisible(true);
        return mainMenuScreen;
	}

	public RunningModeScreen createRunningModeScreen(Player player, MovementController movementController,
			KeyClickController keyClickController, Room room) {
		RunningModeScreen runningModeScreen = new RunningModeScreen(player, movementController, keyClickController,
				room);
		return runningModeScreen;
	}

	public RunningModeScreen showRunningModeGUI(RunningModeScreen runningModeScreen) {

		System.out.println(
				Thread.currentThread().getName() + " Created GUI for Run " + SwingUtilities.isEventDispatchThread());

		JFrame f = new JFrame("OYUN");
		f.setExtendedState(f.getExtendedState() | JFrame.MAXIMIZED_BOTH);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Player player = new Player(Color.blue, 20, 20, 450, 450);
		f.add(runningModeScreen);

		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		return runningModeScreen;
	}
}
