package com.gurup.domain;

import java.util.ArrayList;
import java.util.Timer;

import javax.swing.SwingUtilities;

import com.gurup.controller.BuildingModeKeyClickController;
import com.gurup.controller.KeyClickController;
import com.gurup.controller.MovementController;
import com.gurup.controller.PowerUpController;
import com.gurup.domain.account.entity.AccountOperationResults;
import com.gurup.domain.account.manager.AccountManager;
import com.gurup.domain.buildingmode.BuildingModeRoom;
import com.gurup.domain.room.Room;
import com.gurup.domain.room.RoomConstants;
import com.gurup.domain.room.buildingobjects.BuildingObject;
import com.gurup.domain.saver.GameSaver;
import com.gurup.domain.saver.SaverType;
import com.gurup.ui.ScreenMaker;
import com.gurup.ui.gamescreen.*;

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
    private static SaverType saverType = SaverType.NOTINITIALIZED;
    private static HelpScreen helpScreen;


    private static AccountManager accountManager;
    private static String session;
    private static Boolean isPaused;

    private static BuildingModeScreen buildingModeScreen;
    private static BuildingModeKeyClickController buildingModeKeyClickController;


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
                boolean isHelpButtonPressed = mainMenuScreen.showHelpPressed();

                while (!isPlayButtonPressed) {
                    do {
                        isPlayButtonPressed = mainMenuScreen.showPlayPressed();
                        isHelpButtonPressed = mainMenuScreen.showHelpPressed();
                        Thread.sleep(10);
                    } while (!isPlayButtonPressed && !isHelpButtonPressed);
                    if (isPlayButtonPressed) {
                        mainMenuScreen.dispose();
                        player = new Player(PlayerConstants.xStart.getValue(), PlayerConstants.yStart.getValue(),
                                PlayerConstants.xLen.getValue(), PlayerConstants.xLen.getValue(), 60);
                        stepByStepGame();
                    }
                    if (isHelpButtonPressed) {
                        mainMenuScreen.dispose();
                        helpScreen = screenMaker.createHelpScreen();
                        boolean isBackButtonPressed;
                        do {
                            isBackButtonPressed = helpScreen.showBackPressed();
                            Thread.sleep(10);
                        } while (!isBackButtonPressed);
                        helpScreen.dispose();
                        mainMenuScreen = screenMaker.createMainMenuScreen();

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void stepByStepGame() {
        // If you comment out the following lines, players timer will be fixed, but aliens and powerups timers will not be fixed.
        // So it is better to have fixed timers for all objects.
        boolean studentCenterStep = oneStep("Student Center");
        if (studentCenterStep) {
            // player = new Player(PlayerConstants.xStart.getValue(), PlayerConstants.yStart.getValue(),
            // PlayerConstants.xLen.getValue(), PlayerConstants.xLen.getValue(), 60);
            boolean caseStep = oneStep("CASE");
            if (caseStep) {
                // player = new Player(PlayerConstants.xStart.getValue(), PlayerConstants.yStart.getValue(),
                // PlayerConstants.xLen.getValue(), PlayerConstants.xLen.getValue(), 60);
                boolean sosStep = oneStep("SOS");
                if (sosStep) {
                    // player = new Player(PlayerConstants.xStart.getValue(), PlayerConstants.yStart.getValue(),
                    // PlayerConstants.xLen.getValue(), PlayerConstants.xLen.getValue(), 60);
                    boolean sciStep = oneStep("SCI");
                    if (sciStep) {
                        // player = new Player(PlayerConstants.xStart.getValue(), PlayerConstants.yStart.getValue(),
                        // PlayerConstants.xLen.getValue(), PlayerConstants.xLen.getValue(), 60);
                        boolean engStep = oneStep("ENG");
                        if (engStep) {
                            // player = new Player(PlayerConstants.xStart.getValue(), PlayerConstants.yStart.getValue(),
                            // PlayerConstants.xLen.getValue(), PlayerConstants.xLen.getValue(), 60);
                            boolean snaStep = oneStep("SNA");
                            if (snaStep) {
                                System.out.println("You won!");
                                System.exit(0);
                            }
                        }
                    }
                }
            }
        }
        System.out.println("You lost!");
        System.exit(0);
    }

    private static boolean oneStep(String buildingModeName) {
        BuildingModeRoom buildingModeRoom = buildMode(buildingModeName);
        int playerRemainingTime = buildingModeRoom.getBuildingObjects().size() * 5;
        player.setRemainingTime(playerRemainingTime);
        buildingModeRoom.findRandomLocationFoPlayer(player);
        boolean isLevelPassed = inGame(buildingModeRoom);
        return isLevelPassed;
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

    private static BuildingModeRoom buildMode(String roomName) {
        boolean isBuildModeFinished = false;
        BuildingModeRoom buildingModeRoom = new BuildingModeRoom(roomName, RoomConstants.xStart.getValue(), RoomConstants.yStart.getValue(), RoomConstants.xLimit.getValue(),
                RoomConstants.yLimit.getValue(), player);
        buildingModeScreen = screenMaker.createBuildingModeScreen(game, player, buildingModeKeyClickController, buildingModeRoom);
        buildingModeKeyClickController = new BuildingModeKeyClickController(buildingModeScreen, buildingModeRoom);
        SwingUtilities.invokeLater(() -> screenMaker.showBuildingModeGUI(buildingModeScreen));

        while (!isBuildModeFinished) {
            isBuildModeFinished = buildingModeScreen.getIsFinished();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        screenMaker.stopBuildingModeGUI(buildingModeScreen);
        return buildingModeRoom;
    }

    private static Boolean inGame(BuildingModeRoom buildingModeRoom) {
        System.out.println();
        if (bag == null) {
            bag = new Bag(player);
        }
        room = new Room(buildingModeRoom.getName(), RoomConstants.xStart.getValue(), RoomConstants.yStart.getValue(), RoomConstants.xLimit.getValue(),
                RoomConstants.yLimit.getValue(), player, buildingModeRoom.getBuildingObjects());
        Game.getBag().setupBag(room.getPowerUps());
        runningModeScreen = screenMaker.createRunningModeScreen(game, player, movementController, keyClickController,
                powerUpController, room);
        SwingUtilities.invokeLater(() -> screenMaker.showRunningModeGUI(runningModeScreen));
        movementController = new MovementController(player, runningModeScreen);
        keyClickController = new KeyClickController(runningModeScreen, room);
        powerUpController = new PowerUpController(bag, runningModeScreen);
        isPaused = false;

        // running timer task as daemon thread
        boolean isStudentCenterFinished = false;
        boolean isPlayerDeadOrTimeIsOver = false;

        Timer timer = new Timer(true);
        System.out.println(Thread.currentThread().getName() + " TimerTask started");

        while (isStudentCenterFinished == false && isPlayerDeadOrTimeIsOver == false) {
            isStudentCenterFinished = runningModeScreen.isPlayerPassNextLevel();
            isPlayerDeadOrTimeIsOver = runningModeScreen.isPlayerDeadOrTimeIsOver();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        timer.cancel();
        screenMaker.stopRunningModeGUI(runningModeScreen);
        return isStudentCenterFinished;
    }

    private static boolean registerAndLoginScreen() throws Exception {
        boolean registerFlag;
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
        saverType = loginScreen.getSaverType();


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
        } catch (Exception ignored) {

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
        } catch (Exception ignored) {

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
