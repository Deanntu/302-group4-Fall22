package com.gurup.domain.room;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import com.gurup.domain.Game;
import com.gurup.domain.Player;
import com.gurup.domain.TimerOperationResults;
import com.gurup.domain.aliens.Alien;
import com.gurup.domain.aliens.AlienConstants;
import com.gurup.domain.aliens.BlindAlien;
import com.gurup.domain.aliens.ShooterAlien;
import com.gurup.domain.aliens.TimeWastingAlien;
import com.gurup.domain.powerups.BottlePowerUp;
import com.gurup.domain.powerups.HealthPowerUp;
import com.gurup.domain.powerups.HintPowerUp;
import com.gurup.domain.powerups.PowerUp;
import com.gurup.domain.powerups.ThrownBottlePowerUp;
import com.gurup.domain.powerups.TimePowerUp;
import com.gurup.domain.powerups.VestPowerUp;
import com.gurup.domain.room.buildingobjects.BuildingObject;
import com.gurup.domain.room.buildingobjects.BuildingObjectConstants;
import com.gurup.domain.room.buildingobjects.BuildingObjectFactory;

public class Room {

    private static int xStart;
    private static int yStart;
    private static int xLimit;
    private static int yLimit;
    private String name;
    private BuildingObject object1, object2;
    private static ArrayList<BuildingObject> objects;
    private ArrayList<PowerUp> powerUps;
    private Key key;
    private Player player;
    private Rectangle pauseButton;
    private Rectangle exitButton;
    private int powerUpCreationCounter = 0;
    private int timeCounter = 1;
    private PowerUp created;
    private Alien[] createdAliens; // 0 -> blind, 1 -> shooter, 2 -> time-wasting
    private int alienCreationCounter;
    private static boolean isPlayerFoundKeyForRoom;
    private static boolean isPlayerFoundKeyBefore;
    private final int keyDrawSeconds = 2;
    private int powerUpDeletionCounter = 0;


    public Room(String name, int xStart, int yStart, int xLimit, int yLimit, Player player) {
        this.name = name;
        Room.xStart = xStart;
        Room.yStart = yStart;
        Room.xLimit = xLimit;
        Room.yLimit = yLimit;
        Room.objects = new ArrayList<>();
        this.key = Key.getInstance();
        this.player = player;
        Room.isPlayerFoundKeyForRoom = false;
        Room.isPlayerFoundKeyBefore = false;
        createdAliens = new Alien[3];

        BuildingObjectFactory buildingObjectFactory = new BuildingObjectFactory();
        BuildingObject bin = buildingObjectFactory.createBuildingObject("Bin", 500, 300, BuildingObjectConstants.binXLen.getValue(), BuildingObjectConstants.binYLen.getValue());
        BuildingObject table = buildingObjectFactory.createBuildingObject("Table", 800, 100, BuildingObjectConstants.tableXLen.getValue(), BuildingObjectConstants.tableYLen.getValue());
        BuildingObject book = buildingObjectFactory.createBuildingObject("Book", 100, 100, BuildingObjectConstants.bookXLen.getValue(), BuildingObjectConstants.bookYLen.getValue());
        BuildingObject pen = buildingObjectFactory.createBuildingObject("Pen", 100, 300, BuildingObjectConstants.penXLen.getValue(), BuildingObjectConstants.penYLen.getValue());
        BuildingObject printer = buildingObjectFactory.createBuildingObject("Printer", 800, 300, BuildingObjectConstants.printerXLen.getValue(), BuildingObjectConstants.printerYLen.getValue());

        objects.add(bin);
        objects.add(table);
        objects.add(book);
        objects.add(pen);
        objects.add(printer);
        Key.hideKey(objects);
        initPowerUps();

    }

    public Room(String name, int xStart, int yStart, int xLimit, int yLimit, Player player, ArrayList<BuildingObject> buildingObjects) {
        this.name = name;
        Room.xStart = xStart;
        Room.yStart = yStart;
        Room.xLimit = xLimit;
        Room.yLimit = yLimit;
        this.key = Key.getInstance();
        this.player = player;
        Room.isPlayerFoundKeyForRoom = false;
        Room.isPlayerFoundKeyBefore = false;
        Room.objects = new ArrayList<>();
        Room.objects.addAll(buildingObjects);
        createdAliens = new Alien[3];

        Key.hideKey(objects);
        initPowerUps();

    }

    private void drawKeyForAMoment() {
        player.setDrawKeyStatus(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(keyDrawSeconds * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setDrawKeyStatusToFalse();
            }

        }).start();
    }

    private void setDrawKeyStatusToFalse() {
        player.setDrawKeyStatus(false);
    }



    public static Boolean getIsPlayerFoundKeyForRoom() {
        return isPlayerFoundKeyForRoom;
    }

    public static boolean isPlayerFoundKeyBefore() {
        return isPlayerFoundKeyBefore;
    }

    public static void setPlayerFoundKeyBefore(boolean isPlayerFoundKeyBefore) {
        Room.isPlayerFoundKeyBefore = isPlayerFoundKeyBefore;
    }

    public static void setPlayerFoundKeyForRoom(boolean isPlayerFoundKeyForRoom) {
        Room.isPlayerFoundKeyForRoom = isPlayerFoundKeyForRoom;
    }

    public Boolean isKeyFound(Rectangle rectMouseClick) {
        // MODIFIES: nothing
        // REQUIRES: rectangle that represents the mouse click
        // EFFECTS: Returns true if the key is found, false otherwise:
        // If the mouseRectangle does not intersect with the key, return false
        // If the game is paused, return false
        // If the mouseRectangle intersects with the key and player is near to object that contains the key, return true
        // If the mouseRectangle intersects with the key and player is not near to object that contains the key, return false

        if (Room.isPlayerFoundKeyBefore) {
            //System.out.println("You have already found the key for this room");
            return false;
        }
        if (!rectMouseClick.intersects(new Rectangle(xStart, yStart, xLimit, yLimit))) {
            // System.out.println("Did not click inside the room");
            return false;
        }

        if (Game.getIsPaused()) {
            // System.out.println("Cannot look for key if the game is paused. ");
            return false;
        }

        BuildingObject containerObject = key.getBuildingObject();
        Rectangle playerRect = player.getRectangle();
        for (BuildingObject bo : objects) {
            if (bo.getRectangle().intersects(rectMouseClick)) {
                if (!playerRect.intersects(bo.getRectangle())) {
                    return false;
                }
                if (bo.equals(containerObject)) {
                    Room.isPlayerFoundKeyForRoom = true;
                    Room.isPlayerFoundKeyBefore = true;
                    player.setIsKeyFound(true);
                    drawKeyForAMoment();
                    player.setRemainingTime(player.getRemainingTime() + 50);
                    for (Alien a : createdAliens) {
                        if(a != null) {
                            a.setActive(false);
                        }
                    }
                    createdAliens = new Alien[3];
                    return true;
                }
                return false; // will be changed to bo.shake() to shake object
            }
        }
        return false;
    }

    public void checkPowerUp(Rectangle mouseRect) {
        if (Game.getIsPaused())
            return;
        for (PowerUp p : powerUps) {
            if (!p.getRectangle().intersects(mouseRect))
                continue;
            if (p.isActive()) {
                if (p.isStorable()) {
                    Game.getBag().storePowerUp(p);
                } else {
                    p.usePowerUp();
                }
                p.setIsActive(false);
            }
        }
    }

    public TimerOperationResults createPowerUp(int delayMiliSeconds) {
        if (Game.getIsPaused())
            return TimerOperationResults.PAUSED;
        Random random = new Random();

        if (timeCounter % (1000 / delayMiliSeconds) == 0) {
            timeCounter = 1;
            if (powerUpCreationCounter == 12) {
                if (created != null)
                    created.setIsActive(false);
                int randomIndex = random.nextInt(powerUps.size());
                created = powerUps.get(3);
                int[] newXandY = getRandomLocation(created.getRectangle().width, created.getRectangle().height);
                created.setXCurrent(newXandY[0]);
                created.setYCurrent(newXandY[1]);
                created.setIsActive(true);
                powerUpCreationCounter = 1;
                powerUpDeletionCounter = 1;
                /*
                 * if(randomIndex == 0) { created = new TimePowerUp(player);
                 * created.setIsActive(true);
                 *
                 * } else if(randomIndex == 1) { created = new HealthPowerUp(player);
                 * created.setIsActive(true); } created.setX(420); created.setxLimit(50);
                 * created.setY(320); created.setyLimit(50); powerUps.add(created);
                 */
            }else {
                powerUpCreationCounter++;    
            }
            if (powerUpDeletionCounter == 6) {
                if (created != null) {
                    created.setIsActive(false);
                }

            } else {
                powerUpDeletionCounter++;
            }
        } else {
            timeCounter++;
        }
        return TimerOperationResults.TIME_UP;
    }

    private int[] getRandomLocation(int xLen, int yLen) {
        Random random = new Random();
        boolean isLocationValid = false;
        ArrayList<Rectangle> allRectangles = new ArrayList<>();

        Rectangle doorRect = new Rectangle(RoomConstants.doorXStart.getValue(), RoomConstants.doorYStart.getValue(), RoomConstants.doorXLen.getValue(), RoomConstants.doorYLen.getValue());

        for (BuildingObject bo : objects) {
            allRectangles.add(bo.getRectangle());
        }
        for (PowerUp p : powerUps) {
            allRectangles.add(p.getRectangle());
        }
        for (Alien createdAlien : createdAliens) {
            if (createdAlien != null) {
                allRectangles.add(createdAlien.getRectangle());
            }
        }
        allRectangles.add(player.getRectangle());
        allRectangles.add(doorRect);

        int xCurrent = 0;
        int yCurrent = 0;

        while (isLocationValid == false) {
            xCurrent = random.nextInt(xLimit - xLen - xStart);
            xCurrent+= xStart;
            yCurrent = random.nextInt(yLimit - yLen - yStart);
            yCurrent+= yStart;
            Rectangle objectRect = new Rectangle(xCurrent, yCurrent, xLen, yLen);
            for (Rectangle r : allRectangles) {
                if (r.intersects(objectRect)) {
                    isLocationValid = false;
                    break;
                }
                isLocationValid = true;
            }

        }

        return new int[] {xCurrent, yCurrent};
    }

    public TimerOperationResults createAlien(int delayMiliSeconds) {
        if (Game.getIsPaused())
            return TimerOperationResults.PAUSED;
        if (isPlayerFoundKeyForRoom) {
            return TimerOperationResults.KEYFOUND;
        }
        Random random = new Random();
        if (timeCounter % (1000 / delayMiliSeconds) == 0) {
            if (alienCreationCounter == 10) { 
                Alien createdAlien = null;
                boolean goodIndexFound = false;
                boolean alienCanBeCreated = false;
                for (int i = 0; i < 3; i++) {
                    // if all aliens exist and they are active, return without creating new alien
                    if ((createdAliens[i] == null || !createdAliens[i].isActive())) {
                        if (i == 2) {
                            if (!isPlayerFoundKeyBefore) {
                                alienCanBeCreated = true;
                            }
                        }
                        else {
                            alienCanBeCreated = true;
                        }
                    }
                }
                if (!alienCanBeCreated) {
                    alienCreationCounter = 1;
                    return TimerOperationResults.TIME_UP;
                }
                while(!goodIndexFound) {
                    int randomIndex = random.nextInt(3);
                    switch (randomIndex) {
                        case 0:
                            if (createdAliens[0] == null || !createdAliens[0].isActive()) {
                                System.out.println("Blind alien created");
                                createdAlien = new BlindAlien(10, 10, AlienConstants.xLen.getValue(), AlienConstants.yLen.getValue());
                                int[] newXandY = getRandomLocation(AlienConstants.xLen.getValue(), AlienConstants.yLen.getValue());
                                createdAlien.setXCurrent(newXandY[0]);
                                createdAlien.setYCurrent(newXandY[1]);
                                createdAlien.setActive(true);
                                createdAliens[0] = createdAlien;
                                goodIndexFound = true;



                            }
                            break;
                        case 1:
                            if (createdAliens[1] == null || !createdAliens[1].isActive()) {
                                System.out.println("Shooter alien created");
                                createdAlien = new ShooterAlien(10, 10, AlienConstants.xLen.getValue(), AlienConstants.yLen.getValue(), player);
                                int[] newXandY = getRandomLocation(AlienConstants.xLen.getValue(), AlienConstants.yLen.getValue());
                                createdAlien.setXCurrent(newXandY[0]);
                                createdAlien.setYCurrent(newXandY[1]);
                                createdAlien.setActive(true);
                                createdAliens[1] = createdAlien;
                                goodIndexFound = true;
                            }
                            break;
                        case 2:
                            if (!isPlayerFoundKeyForRoom && (createdAliens[2] == null || !createdAliens[2].isActive())) {
                                System.out.println("TimeWasting alien created");
                                createdAlien = new TimeWastingAlien(10, 10, AlienConstants.xLen.getValue(),
                                        AlienConstants.yLen.getValue(), player);
                                int[] newXandY = getRandomLocation(AlienConstants.xLen.getValue(), AlienConstants.yLen.getValue());
                                createdAlien.setXCurrent(newXandY[0]);
                                createdAlien.setYCurrent(newXandY[1]);
                                createdAlien.setActive(true);
                                createdAliens[2] = createdAlien;
                                goodIndexFound = true;
                            }
                            break;
                        default:
                            if (createdAliens[2] == null || !createdAliens[2].isActive()) {
                                createdAlien = new TimeWastingAlien(10, 10, AlienConstants.xLen.getValue(),
                                        AlienConstants.yLen.getValue(), player);
                                int[] newXandY = getRandomLocation(AlienConstants.xLen.getValue(), AlienConstants.yLen.getValue());
                                createdAlien.setXCurrent(newXandY[0]);
                                createdAlien.setYCurrent(newXandY[1]);
                                createdAlien.setActive(true);
                                createdAliens[2] = createdAlien;
                                goodIndexFound = true;
                            }
                    }
                }
                alienCreationCounter = 1;
            } else {
                alienCreationCounter++;
            }
        }

        return TimerOperationResults.TIME_UP;
    }

    // Getters/Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getXLimit() {
        return xLimit;
    }

    public void setXLimit(int xLimit) {
        Room.xLimit = xLimit;
    }

    public static int getYLimit() {
        return yLimit;
    }

    public void setYLimit(int yLimit) {
        Room.yLimit = yLimit;
    }

    public static int getstartX() {
        return xStart;
    }

    public static int getstartY() {
        return yStart;
    }

    public BuildingObject getObject1() {
        return object1;
    }

    public void setObject1(BuildingObject object1) {
        this.object1 = object1;
    }

    public BuildingObject getObject2() {
        return object2;
    }

    public void setObject2(BuildingObject object2) {
        this.object2 = object2;
    }

    public static ArrayList<BuildingObject> getObjects() {
        return objects;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Rectangle getPauseButton() {
        return pauseButton;
    }

    public void setPauseButton(Rectangle pauseButton) {
        this.pauseButton = pauseButton;
    }

    public Rectangle getExirButton() {
        return exitButton;
    }

    public void setExitButton(Rectangle exitButton) {
        this.exitButton = exitButton;
    }

    public PowerUp getCreated() {
        return created;
    }

    public ArrayList<PowerUp> getPowerUps() {
        return powerUps;
    }

    // private methods
    private void initPowerUps() {
        powerUps = new ArrayList<>();
        TimePowerUp t = TimePowerUp.getInstance(player);
        HealthPowerUp h = HealthPowerUp.getInstance(player);
        VestPowerUp v = VestPowerUp.getInstance(player);
        BottlePowerUp b = BottlePowerUp.getInstance(player);
        ThrownBottlePowerUp.getInstance(player);
        HintPowerUp hint = HintPowerUp.getInstance(player);




        t.setXLen(RoomConstants.timePowerUpXLen.getValue());
        t.setYLen(RoomConstants.timePowerUpYLen.getValue());



        h.setXLen(RoomConstants.healthPowerUpXLen.getValue());
        h.setYLen(RoomConstants.healthPowerUpYLen.getValue());




        v.setXLen(RoomConstants.vestPowerUpXLen.getValue());
        v.setYLen(RoomConstants.vestPowerUpYLen.getValue());


        b.setXLen(RoomConstants.bottlePowerUpXLen.getValue());
        b.setYLen(RoomConstants.bottlePowerUpYLen.getValue());




        hint.setXLen(RoomConstants.hintPowerUpXLen.getValue());
        hint.setYLen(RoomConstants.hintPowerUpYLen.getValue());


        powerUps.add(t);
        powerUps.add(h);
        powerUps.add(v);
        powerUps.add(b);
        powerUps.add(hint);

    }

    public Alien[] getCreatedAliens() {
        // TODO Auto-generated method stub
        return createdAliens;
    }

    public Alien getCreatedAlien(int i) {
        // TODO Auto-generated method stub
        return createdAliens[i];
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public void setCreated(PowerUp powerUp) {
        this.created = powerUp;
    }

    public void setCreatedAliens(Alien[] aliens) {
        this.createdAliens = aliens;
    }

    public void setCreatedAlien(int i, Alien alien) {
        this.createdAliens[i] = alien;
    }

    public void setObjects(ArrayList<BuildingObject> loadedObjects) {
        // TODO Auto-generated method stub
        objects = loadedObjects;

    }
}
