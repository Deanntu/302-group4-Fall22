package com.gurup.domain;

import java.awt.Color;
import java.awt.Toolkit;
import java.util.Timer;

import javax.swing.SwingUtilities;

import com.gurup.controller.KeyClickController;
import com.gurup.controller.MovementController;
import com.gurup.controller.PowerUpController;
import com.gurup.domain.account.entity.AccountOperationResults;
import com.gurup.domain.account.manager.AccountManager;
import com.gurup.domain.room.Room;
import com.gurup.domain.saver.GameSaver;
import com.gurup.domain.saver.SaverType;
import com.gurup.ui.ScreenMaker;
import com.gurup.ui.gamescreen.LoginScreen;
import com.gurup.ui.gamescreen.MainMenuScreen;
import com.gurup.ui.gamescreen.PauseAndResumeScreen;
import com.gurup.ui.gamescreen.RunningModeScreen;

public class Game {
	private static Game game;
	private static ScreenMaker screenMaker;
	private static Player player;
	private static Room room; // TODO there will be more than one room
	private static Bag bag;
	private static MovementController movementController;
	private static KeyClickController keyClickController;
	private static PowerUpController powerUpController;
	private static RunningModeScreen runningModeScreen;
	private static LoginScreen loginScreen;
	private static MainMenuScreen mainMenuScreen;

	private static PauseAndResumeScreen pauseAndResumeScreen;
	private static final int PLAYER_SIZE = 50;;
	private static AccountManager accountManager;
	private static String session;
	private static Boolean isPaused;

	private Game() {

	}

	public static synchronized Game getInstance() {
		if (game == null) {
			game = new Game();
		}
		return game;
	}

	public static void play() {
		Game.screenMaker = new ScreenMaker();
		Game.accountManager = new AccountManager();
		loginScreen = screenMaker.createMainModeScreen();
		try {
			boolean isLoginSuccesful = registerAndLoginScreen();
			if (isLoginSuccesful) {
				loginScreen.dispose();
				mainMenuScreen = screenMaker.createMainMenuScreen();
				boolean isPlayButtonPressed = mainMenuScreen.showPlayPressed();

				do {
					isPlayButtonPressed = mainMenuScreen.showPlayPressed();
					Thread.sleep(10);
				} while (!isPlayButtonPressed);
				// System.out.println(isPlayButtonPressed);
				if (isPlayButtonPressed) {
					mainMenuScreen.dispose();
					inGame();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void saveGame() { // TODO i am used, do not delete me

		// TODO Change SaverType DATABASE to Variable
		GameSaver roomSaver = new GameSaver(SaverType.DATABASE, SaverType.ROOM);
		GameSaver playerSaver = new GameSaver(SaverType.DATABASE, SaverType.PLAYER);
		try {
			roomSaver.save("deantu", room);
			playerSaver.save("deantu", player);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private static void inGame() {
		player = new Player(Color.blue, 50, 50, Toolkit.getDefaultToolkit().getScreenSize().width - 100,
				Toolkit.getDefaultToolkit().getScreenSize().height - 175, PLAYER_SIZE, 60);
		bag = new Bag(player);
		room = new Room("Student Center", 50, 50, Toolkit.getDefaultToolkit().getScreenSize().width - 100,
				Toolkit.getDefaultToolkit().getScreenSize().height - 175, player);
		Game.getBag().setupBag(room.getPowerUps());
		runningModeScreen = screenMaker.createRunningModeScreen(game, player, movementController, keyClickController,
				powerUpController, room);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				screenMaker.showRunningModeGUI(runningModeScreen);
			}
		});
		movementController = new MovementController(player, runningModeScreen);
		keyClickController = new KeyClickController(runningModeScreen, room);
		powerUpController = new PowerUpController(bag, runningModeScreen);
		isPaused = false;
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

	private static boolean registerAndLoginScreen() throws Exception {
		boolean registerFlag = loginScreen.isRegisterPressed();
		boolean loginFlag = loginScreen.isLoginPressed();
		boolean withoutLoginFlag = loginScreen.isWithoutLoginPressed();

		do {
			registerFlag = loginScreen.isRegisterPressed();
			loginFlag = loginScreen.isLoginPressed();
			withoutLoginFlag = loginScreen.isWithoutLoginPressed();
			Thread.sleep(10);

		} while (!registerFlag && !loginFlag && !withoutLoginFlag);

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
			}
		} else if (withoutLoginFlag) {
			session = "DEFAULT";
			return true;
		}
		return registerAndLoginScreen();
	}

	private static Boolean tryPauseGame() {
		try {
			// pause timer DONE in player.decrementTime()
			// stop checking for clicks in RunningModeScreen DONE in Room.isKeyFound()
			// TODO show pause menu, waiting for UI
			// stop moving the character, DONE in MovementController.keyPressed(), TODO move
			// to Domain layer
			// saveGame();//TODO Game will be saved in pause screen by user request please
			// change and delete this line
			setIsPaused(true);
			return true;
		} catch (Exception e) {

		}
		return false;
	}

	private static Boolean tryUnpauseGame() {
		try {
			// unpause timer DONE in player.decrementTime()
			// start checking for clicks in RunningModeScreen DONE in Room.isKeyFound()
			// TODO show game menu, waiting for UI
			// start moving the character, DONE in MovementController.keyPressed(), TODO
			// move to Domain layer
			setIsPaused(false);
			return true;
		} catch (Exception e) {

		}
		return false;
	}

	public static Boolean pauseUnpause() {
		Boolean pauseButtonClicked;
		if (Game.getIsPaused()) {
			pauseButtonClicked = Game.tryUnpauseGame();
		} else {
			pauseButtonClicked = Game.tryPauseGame();
		}
		return pauseButtonClicked;
	}

	public static Boolean getIsPaused() {
		return isPaused;
	}

	public static void setIsPaused(Boolean isPaused) {
		Game.isPaused = isPaused;
	}

	public static Bag getBag() {
		return bag;
	}

	public static void setBag(Bag bag) {
		Game.bag = bag;
	}
}
