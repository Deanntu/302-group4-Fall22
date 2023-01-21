package com.gurup.domain;

import javax.swing.SwingUtilities;

import com.gurup.controller.BuildingModeKeyClickController;
import com.gurup.controller.KeyClickController;
import com.gurup.controller.MovementController;
import com.gurup.controller.PowerUpController;
import com.gurup.domain.account.entity.AccountOperationResults;
import com.gurup.domain.account.manager.AccountManager;
import com.gurup.domain.buildingmode.BuildingModeRoom;
import com.gurup.domain.loader.GameLoader;
import com.gurup.domain.powerups.ThrownBottlePowerUp;
import com.gurup.domain.room.Room;
import com.gurup.domain.room.RoomConstants;
import com.gurup.domain.saver.GameSaver;
import com.gurup.domain.saver.SaverType;
import com.gurup.ui.ScreenMaker;
import com.gurup.ui.gamescreen.BuildingModeScreen;
import com.gurup.ui.gamescreen.HelpScreen;
import com.gurup.ui.gamescreen.LoginScreen;
import com.gurup.ui.gamescreen.MainMenuScreen;
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
    private static SaverType saverType = SaverType.NOTINITIALIZED;
    private static HelpScreen helpScreen;
   
    private static AccountManager accountManager;
    private static String username;
    private static Boolean isPaused;
    private static Boolean isLoadButtonPressed = false;
    
    private static BuildingModeScreen buildingModeScreen;
    private static BuildingModeKeyClickController buildingModeKeyClickController;

    private static String[] rooms = {"Student Center", "CASE", "SOS", "SCI", "ENG", "SNA"};

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
                isLoadButtonPressed = mainMenuScreen.showLoadPressed();
                while (!isPlayButtonPressed && !isLoadButtonPressed) {
                    do {
                        isPlayButtonPressed = mainMenuScreen.showPlayPressed();
                        isHelpButtonPressed = mainMenuScreen.showHelpPressed();
                        isLoadButtonPressed = mainMenuScreen.showLoadPressed();
                        Thread.sleep(10);
                    } while (!isPlayButtonPressed && !isHelpButtonPressed && !isLoadButtonPressed);
                    if (isPlayButtonPressed) {
                        mainMenuScreen.dispose();
                        player = new Player(PlayerConstants.xStart.getValue(), PlayerConstants.yStart.getValue(),
                                PlayerConstants.xLen.getValue(), PlayerConstants.xLen.getValue(), 60);
                        player.setLevel(0);
                        stepByStepGame();
                    }
                    if (isLoadButtonPressed) {
                        mainMenuScreen.dispose();
                        stepByStepLoadedGame();
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
    private static Boolean loadAndPlayGame() throws Exception {
        if(username == "DEFAULT" || saverType == SaverType.NOTINITIALIZED) {
            player = new Player(PlayerConstants.xStart.getValue(), PlayerConstants.yStart.getValue(),
                    PlayerConstants.xLen.getValue(), PlayerConstants.xLen.getValue(), 60);
            player.setLevel(0);
            stepByStepGame();
        }
        if (bag == null) {
            bag = new Bag(player);
        }
       
        player = (Player) new GameLoader(saverType, SaverType.PLAYER).load(username);
        room = (Room) new GameLoader(saverType, SaverType.ROOM).load(username);
        
        if(room.getObjects() == null) {
            player = new Player(PlayerConstants.xStart.getValue(), PlayerConstants.yStart.getValue(),
                    PlayerConstants.xLen.getValue(), PlayerConstants.xLen.getValue(), 60);
            player.setLevel(0);
            stepByStepGame();
            return false;
        }
        System.out.println("InGame:"+player.getLevel());
        Room.setPlayerFoundKeyBefore(player.getIsKeyFound());
        Room.setPlayerFoundKeyForRoom(player.getIsKeyFound());
        room.setName(rooms[player.getLevel()]);
        return playGame();
    }
    private static void stepByStepLoadedGame() throws Exception {
        boolean gameState = loadAndPlayGame();
        player.setIsKeyFound(false);
        while(gameState) {
            player.setLevel(player.getLevel()+1);
            if(player.getLevel() > 5) {
                System.out.println("You win!");
                System.exit(0);
            }
            gameState = oneStep(rooms[player.getLevel()]);
        }
    }
    private static void stepByStepGame() {
        // If you comment out the following lines, players timer will be fixed, but aliens and powerups timers will not be fixed.
        // So it is better to have fixed timers for all objects.
        boolean studentCenterStep = oneStep("Student Center");
        if (studentCenterStep) {
            player.setLevel(player.getLevel()+1);
            // player = new Player(PlayerConstants.xStart.getValue(), PlayerConstants.yStart.getValue(),
            // PlayerConstants.xLen.getValue(), PlayerConstants.xLen.getValue(), 60);
            boolean caseStep = oneStep("CASE");
            if (caseStep) {
                player.setLevel(player.getLevel()+1);
                // player = new Player(PlayerConstants.xStart.getValue(), PlayerConstants.yStart.getValue(),
                // PlayerConstants.xLen.getValue(), PlayerConstants.xLen.getValue(), 60);
                boolean sosStep = oneStep("SOS");
                if (sosStep) {
                    player.setLevel(player.getLevel()+1);
                    // player = new Player(PlayerConstants.xStart.getValue(), PlayerConstants.yStart.getValue(),
                    // PlayerConstants.xLen.getValue(), PlayerConstants.xLen.getValue(), 60);
                    boolean sciStep = oneStep("SCI");
                    if (sciStep) {
                        player.setLevel(player.getLevel()+1);
                        // player = new Player(PlayerConstants.xStart.getValue(), PlayerConstants.yStart.getValue(),
                        // PlayerConstants.xLen.getValue(), PlayerConstants.xLen.getValue(), 60);
                        boolean engStep = oneStep("ENG");
                        if (engStep) {
                            player.setLevel(player.getLevel()+1);
                            // player = new Player(PlayerConstants.xStart.getValue(), PlayerConstants.yStart.getValue(),
                            // PlayerConstants.xLen.getValue(), PlayerConstants.xLen.getValue(), 60);
                            boolean snaStep = oneStep("SNA");
                            if (snaStep) {
                                player.setLevel(player.getLevel()+1);
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
        player.setStartingTime(playerRemainingTime);
        buildingModeRoom.findRandomLocationFoPlayer(player);
        boolean isLevelPassed = inGame(buildingModeRoom);
        return isLevelPassed;
    }

    public static void saveGame() { // TODO i am used, do not delete me

        // TODO Change SaverType DATABASE to Variable
        if (saverType == SaverType.NOTINITIALIZED) {
            if (username.equals("DEFAULT")) {
                System.out.println("Player did not login!");
            } else {
                System.out.println("Player did not specify save location!");
            }

            return;
        }
        GameSaver roomSaver = new GameSaver(saverType, SaverType.ROOM);
        GameSaver playerSaver = new GameSaver(saverType, SaverType.PLAYER);
        try {
            roomSaver.save(username, room);
            playerSaver.save(username, player);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
    private static Boolean playGame() {
        Game.getBag().setupBag(room.getPowerUps());
        if (runningModeScreen != null) {
            runningModeScreen.getTimer().stop();
        }
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

        //Timer timer = new Timer(true);
        //System.out.println(Thread.currentThread().getName() + " TimerTask started");

        while (isStudentCenterFinished == false && isPlayerDeadOrTimeIsOver == false) {
            isStudentCenterFinished = runningModeScreen.isPlayerPassNextLevel();
            isPlayerDeadOrTimeIsOver = runningModeScreen.isPlayerDeadOrTimeIsOver();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        screenMaker.stopRunningModeGUI(runningModeScreen);
        ThrownBottlePowerUp.setNull();
        return isStudentCenterFinished;
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
        if (bag == null) {
            bag = new Bag(player);
        }
        room = new Room(buildingModeRoom.getName(), RoomConstants.xStart.getValue(), RoomConstants.yStart.getValue(), RoomConstants.xLimit.getValue(),
                RoomConstants.yLimit.getValue(), player, buildingModeRoom.getBuildingObjects());
        return playGame();
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
                Game.username = username;
                return true;
            }
        } else if (withoutLoginFlag) {
            Game.username = "DEFAULT";
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

    public static Player getPlayer() {
        // TODO Auto-generated method stub
        return player;
    }

    public static String getUsername() {
        // TODO Auto-generated method stub
        return Game.username;
    }

}
