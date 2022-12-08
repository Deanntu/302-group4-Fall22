package com.gurup.domain;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Scanner;
import java.util.Timer;

import javax.swing.SwingUtilities;

import com.gurup.controller.KeyClickController;
import com.gurup.controller.MovementController;
import com.gurup.domain.account.entity.AccountOperationResults;
import com.gurup.domain.account.manager.AccountManager;
import com.gurup.domain.room.Room;
import com.gurup.ui.ScreenMaker;
import com.gurup.ui.gamescreen.LoginScreen;
import com.gurup.ui.gamescreen.RunningModeScreen;

public class Game {

	private static ScreenMaker screenMaker;
	private static Player player;
	private static Room room; // TODO there will be more than one room
	private static MovementController movementController;
	private static KeyClickController keyClickController;
	private static RunningModeScreen runningModeScreen;
	private static LoginScreen loginScreen;
	private static final int PLAYER_SIZE = 25;;
	private static AccountManager accountManager;
	private static String session;
	private static Boolean isPaused;
  
	public static void main(String[] args) throws Exception {
		screenMaker = new ScreenMaker();
		accountManager = new AccountManager();
		loginScreen = screenMaker.createMainModeScreen();
		boolean isLoginSuccesful = mainScreen();

		if (isLoginSuccesful) {
			player = new Player(Color.blue, 50, 50,
					Toolkit.getDefaultToolkit().getScreenSize().width - 100 + PLAYER_SIZE,
					Toolkit.getDefaultToolkit().getScreenSize().height - 175 + PLAYER_SIZE, PLAYER_SIZE,
					60);
			room = new Room("Student Center", 50, 50, Toolkit.getDefaultToolkit().getScreenSize().width - 100,
					Toolkit.getDefaultToolkit().getScreenSize().height - 175, player);

			runningModeScreen = screenMaker.createRunningModeScreen(player, movementController, keyClickController,
					room);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					screenMaker.showRunningModeGUI(runningModeScreen);
				}
			});
			movementController = new MovementController(player, runningModeScreen);
			keyClickController = new KeyClickController(player, runningModeScreen, room);
			// running timer task as daemon thread
			Timer timer = new Timer(true);
			System.out.println(Thread.currentThread().getName() + " TimerTask started");
			setIsPaused(false);
			// cancel after sometime
			try {
				Thread.sleep(100000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			timer.cancel();
		}
	}

	private static boolean mainScreen() throws Exception {
		boolean registerFlag = loginScreen.isRegisterPressed();
		boolean loginFlag = loginScreen.isLoginPressed();

		do {
			registerFlag = loginScreen.isRegisterPressed();
			loginFlag = loginScreen.isLoginPressed();
			Thread.sleep(10);

		} while (!registerFlag && !loginFlag);

		String username = loginScreen.getEnteredUsermame();
		String password1 = loginScreen.getEnteredPassword();
		String password2 = loginScreen.getEnteredPassword();
		String mail = loginScreen.getEnteredMail();

		if (registerFlag) {
			loginScreen.setRegisterPressed(false);
			AccountOperationResults res = accountManager.createAccount(username, password1, password2, mail);
			System.out.println("Register Result: " + res.toString());
		} else if (loginFlag) {
			loginScreen.setLoginPressed(false);
			AccountOperationResults res = accountManager.loginAccount(username, password1, password2, mail);
			System.out.println("Login Result: " + res.toString());
			if (res.equals(AccountOperationResults.SUCCESS)) {
				session = username;
				return true;
			} else {
				System.out.println(res);
			}
		}
		return mainScreen();
	}
	
	public static Boolean tryPauseGame(Rectangle rectMouseClick) {
		Rectangle pauseRect = room.getPauseButton();
		if (pauseRect.intersects(rectMouseClick)) {
			// pause timer DONE in player.decrementTime()
			// stop checking for clicks in RunningModeScreen DONE in Room.isKeyFound()
			// TODO show pause menu, waiting for UI
			// stop moving the character, DONE in MovementController.keyPressed(), TODO move to Domain layer
			setIsPaused(true);
			return true;
		}
		return false;
	}
	// TODO tryUnpauseGame
	public static Boolean getIsPaused() {
		return isPaused;
	}
	public static void setIsPaused(Boolean isPaused) {
		Game.isPaused = isPaused;
	}
}
