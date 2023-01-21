package com.gurup.ui;

import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.gurup.controller.BuildingModeKeyClickController;
import com.gurup.controller.KeyClickController;
import com.gurup.controller.MovementController;
import com.gurup.controller.PowerUpController;
import com.gurup.domain.Game;
import com.gurup.domain.Player;
import com.gurup.domain.buildingmode.BuildingModeRoom;
import com.gurup.domain.room.Room;
import com.gurup.ui.gamescreen.BuildingModeScreen;
import com.gurup.ui.gamescreen.HelpScreen;
import com.gurup.ui.gamescreen.LoginScreen;
import com.gurup.ui.gamescreen.MainMenuScreen;
import com.gurup.ui.gamescreen.RunningModeScreen;

public class ScreenMaker {
    public static JFrame frame = new JFrame("OYUN");


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

    public HelpScreen createHelpScreen() {
        HelpScreen helpScreen = new HelpScreen();
        helpScreen.setTitle("Help");

        helpScreen.setBounds(10, 10, 1000, 900);
        helpScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        helpScreen.setResizable(false);

        helpScreen.setLocationRelativeTo(null);
        helpScreen.setVisible(true);

        return helpScreen;
    }

    public RunningModeScreen createRunningModeScreen(Game game, Player player, MovementController movementController,
                                                     KeyClickController keyClickController, PowerUpController powerUpController, Room room) {
        return new RunningModeScreen(game, player, movementController,
                keyClickController, powerUpController, room);
    }

    public RunningModeScreen showRunningModeGUI(RunningModeScreen runningModeScreen) {

        System.out.println(Thread.currentThread().getName() + " Created GUI for Run " + SwingUtilities.isEventDispatchThread());

        frame = new JFrame("OYUN");
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(runningModeScreen);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return runningModeScreen;
    }

    public BuildingModeScreen createBuildingModeScreen(Game game, Player player, BuildingModeKeyClickController buildingModeKeyClickController, BuildingModeRoom buildingModeRoom) {
        return new BuildingModeScreen(game, player, buildingModeKeyClickController, buildingModeRoom);
    }


    public BuildingModeScreen showBuildingModeGUI(BuildingModeScreen buildingModeScreen) {

        System.out.println(Thread.currentThread().getName() + " Created GUI for Run " + SwingUtilities.isEventDispatchThread());

        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(buildingModeScreen);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return buildingModeScreen;
    }

    public void stopBuildingModeGUI(BuildingModeScreen buildingModeScreen) {
        // remove the screen from the frame
        Window win = SwingUtilities.getWindowAncestor(buildingModeScreen);
        win.dispose();
        frame.remove(buildingModeScreen);

    }

    public void stopRunningModeGUI(RunningModeScreen runningModeScreen) {
        // remove the screen from the frame
        Window win = SwingUtilities.getWindowAncestor(runningModeScreen);
        win.dispose();
        frame.remove(runningModeScreen);
    }
}
