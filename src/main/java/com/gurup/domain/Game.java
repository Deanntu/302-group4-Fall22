package com.gurup.domain;

import java.awt.Color;
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
import com.gurup.ui.gamescreen.RunningModeScreen;

public class Game {
	
	private static ScreenMaker screenMaker;
	private static Player player;
	private static Room room;
	private static MovementController movementController;
	private static KeyClickController keyClickController;
	private static RunningModeScreen runningModeScreen;
	private static final int PLAYER_SIZE = 25;;
	private static Scanner input;
	private static AccountManager accountManager;
	private static String session;

	
	
	public static void main(String[] args) throws Exception {
		input = new Scanner(System.in);
		accountManager = new AccountManager();
		if(mainScreen()) {
			screenMaker = new ScreenMaker();
			player = new Player(Color.blue, 50, 50,
					Toolkit.getDefaultToolkit().getScreenSize().width - 100 + PLAYER_SIZE,
					Toolkit.getDefaultToolkit().getScreenSize().height - 175 + PLAYER_SIZE, PLAYER_SIZE);
			room = new Room("Student Center", 50, 50, Toolkit.getDefaultToolkit().getScreenSize().width - 100,
					Toolkit.getDefaultToolkit().getScreenSize().height - 175, player);

			runningModeScreen = screenMaker.createScreen(player, movementController, keyClickController, room);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					screenMaker.createAndShowGUI(runningModeScreen);
				}
			});
			movementController = new MovementController(player, runningModeScreen);
			keyClickController = new KeyClickController(player, runningModeScreen, room);
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
	private static boolean mainScreen() throws Exception {
		System.out.println("Please write your username:");
		String username = input.nextLine();
		System.out.println("Please write your password:");
		String password1 = input.nextLine();
		System.out.println("Please write your password again:");
		String password2 = input.nextLine();
		System.out.println("Please write your mail:");
		String mail = input.nextLine();
		System.out.println("Please choose your action:");
		System.out.println("Register");
		System.out.println("Login");
		String choice = input.nextLine();
		if(choice.equals("Quit")) return false;
		if(choice.equals("Register")) {
			
			AccountOperationResults res = accountManager.createAccount(username, password1, password2, mail);
			if(res.equals(AccountOperationResults.SUCCESS)) {
				System.out.println(res);
			}else {
				System.out.println(res);
			}
		}else if(choice.equals("Login")) {
			AccountOperationResults res = accountManager.loginAccount(username, password1, password2, mail);
			if(res.equals(AccountOperationResults.SUCCESS)) {
				System.out.println(res);
				session = username;
				return true;
			}else {
				System.out.println(res);
			}
		}else {
			System.out.println("Failed try again!");
		}
		return mainScreen();
	}
}
