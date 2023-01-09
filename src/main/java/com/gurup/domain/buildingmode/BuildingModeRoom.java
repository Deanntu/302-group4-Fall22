package com.gurup.domain.buildingmode;

import com.gurup.domain.Main;
import com.gurup.domain.Player;
import com.gurup.domain.room.RoomConstants;
import com.gurup.domain.room.buildingobjects.BuildingObject;
import com.gurup.domain.room.buildingobjects.BuildingObjectConstants;
import com.gurup.domain.room.buildingobjects.BuildingObjectFactory;

import java.math.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class BuildingModeRoom {
    private static int xStart;
    private static int yStart;
    private static int xLimit;
    private static int yLimit;
    private String name;
    private Player player;
    private ArrayList<BuildingObject> buildingObjects;
    private BuildingObjectFactory buildingObjectFactory = new BuildingObjectFactory();


    public BuildingModeRoom(String name, int xStart, int yStart, int xLimit, int yLimit, Player player) {
        this.name = name;
        this.xStart = xStart;
        this.yStart = yStart;
        this.xLimit = xLimit;
        this.yLimit = yLimit;
        this.player = player;
        this.buildingObjects = new ArrayList<BuildingObject>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getXLimit() {
        return xLimit;
    }

    public static int getYLimit() {
        return yLimit;
    }

    public static int getXStart() {
        return xStart;
    }

    public static int getYStart() {
        return yStart;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setObjectToBuild(String name) {
        player.setCurrentSelectedObject(name);
    }

    public void addBuildingObjects(Rectangle mouseRect) {
        String buildingObjectName = this.player.getCurrentSelectedObject();
        if (buildingObjectName != null) {
            int[] lenArray = this.getXAndYForCandidateObject(buildingObjectName);
            int xLen = lenArray[0];
            int yLen = lenArray[1];
            int xCurrent = mouseRect.x;
            int yCurrent = mouseRect.y;
            Rectangle objectRect = new Rectangle(xCurrent, yCurrent, xLen, yLen);
            Rectangle roomRect = new Rectangle(xStart, yStart, xLimit, yLimit);
            Rectangle doorRect = new Rectangle(RoomConstants.doorXStart.getValue(), RoomConstants.doorYStart.getValue(), RoomConstants.doorXLen.getValue(), RoomConstants.doorYLen.getValue());
            for (BuildingObject buildingObject : buildingObjects) {
                if (buildingObject.getRectangle().intersects(objectRect)) {
                    JOptionPane.showMessageDialog(null, "You can't build on another object!");
                    return;
                }
            }
            if (doorRect.intersects(objectRect)) {
                JOptionPane.showMessageDialog(null, "You can't build on the door!");
                return;
            } else if (!roomRect.contains(objectRect)) {
                JOptionPane.showMessageDialog(null, "You can't build outside the room!");
                return;
            } else {
                BuildingObject newBuildingObject = buildingObjectFactory.createBuildingObject(buildingObjectName, xCurrent, yCurrent, xLen, yLen);
                buildingObjects.add(newBuildingObject);
                return;
            }
        }
    }


    public ArrayList<BuildingObject> getBuildingObjects() {
        return buildingObjects;
    }

    private int[] getXAndYForCandidateObject(String tempBuildingObjectName) {
        int[] lenArray = new int[2];

        switch (tempBuildingObjectName) {
            case "Bin":
                lenArray[0] = BuildingObjectConstants.binXLen.getValue();
                lenArray[1] = BuildingObjectConstants.binYLen.getValue();
                break;
            case "Book":
                lenArray[0] = BuildingObjectConstants.bookXLen.getValue();
                lenArray[1] = BuildingObjectConstants.bookYLen.getValue();
                break;
            case "Pen":
                lenArray[0] = BuildingObjectConstants.penXLen.getValue();
                lenArray[1] = BuildingObjectConstants.penYLen.getValue();
                break;
            case "Printer":
                lenArray[0] = BuildingObjectConstants.printerXLen.getValue();
                lenArray[1] = BuildingObjectConstants.printerYLen.getValue();
                break;
            case "Table":
                lenArray[0] = BuildingObjectConstants.tableXLen.getValue();
                lenArray[1] = BuildingObjectConstants.tableYLen.getValue();
                break;
            default:
                throw new IllegalArgumentException("Unknown Building Object " + tempBuildingObjectName);
        }
        return lenArray;
    }

    public void addRandomBuildingObjects() {
        Random random = new Random();
        int numberOfObjects = getNumberOfObjects();
        int i = 0;
        while (i < numberOfObjects) {
            boolean canBePlaced = true;
            int objectID = random.nextInt(1, 6);
            String buildingObjectName = getBuildingObjectName(objectID);
            int[] lenArray = this.getXAndYForCandidateObject(buildingObjectName);
            int xLen = lenArray[0];
            int yLen = lenArray[1];
            int xCurrent = random.nextInt(xStart, xLimit - xLen);
            int yCurrent = random.nextInt(yStart, yLimit - yLen);
            Rectangle objectRect = new Rectangle(xCurrent, yCurrent, xLen, yLen);
            Rectangle roomRect = new Rectangle(xStart, yStart, xLimit, yLimit);
            Rectangle doorRect = new Rectangle(RoomConstants.doorXStart.getValue(), RoomConstants.doorYStart.getValue(), RoomConstants.doorXLen.getValue(), RoomConstants.doorYLen.getValue());
            for (BuildingObject buildingObject : buildingObjects) {
                if (buildingObject.getRectangle().intersects(objectRect)) {
                    canBePlaced = false;
                }
            }
            if (doorRect.intersects(objectRect)) {
                canBePlaced = false;
            } else if (!roomRect.contains(objectRect)) {
                canBePlaced = false;
            } else {
                if (canBePlaced) {
                    BuildingObject newBuildingObject = buildingObjectFactory.createBuildingObject(buildingObjectName, xCurrent, yCurrent, xLen, yLen);
                    buildingObjects.add(newBuildingObject);
                    i++;
                }
            }
        }
        return;
    }

    private int getNumberOfObjects() {
        int numberOfObjects;
        switch (this.name) {
            case "Student Center":
                numberOfObjects = BuildingModeRoomConstants.minObjectsForStudentCenter.getValue();
                break;
            case "CASE":
                numberOfObjects = BuildingModeRoomConstants.minObjectsForCASE.getValue();
                break;
            case "SOS":
                numberOfObjects = BuildingModeRoomConstants.minObjectsForSOS.getValue();
                break;
            case "SCI":
                numberOfObjects = BuildingModeRoomConstants.minObjectsForSCI.getValue();
                break;
            case "ENG":
                numberOfObjects = BuildingModeRoomConstants.minObjectsForENG.getValue();
                break;
            case "SNA":
                numberOfObjects = BuildingModeRoomConstants.minObjectsForSNA.getValue();
                break;
            default:
                throw new IllegalArgumentException("Unknown Room " + this.name);
        }
        return numberOfObjects;
    }

    private String getBuildingObjectName(int objectID) {
        switch (objectID) {
            case 1:
                return "Bin";
            case 2:
                return "Book";
            case 3:
                return "Pen";
            case 4:
                return "Printer";
            case 5:
                return "Table";
            default:
                throw new IllegalArgumentException("Unknown Building Object ID " + objectID);
        }
    }
}

