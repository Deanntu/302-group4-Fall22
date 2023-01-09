package com.gurup.domain.buildingmode;

import com.gurup.domain.Player;
import com.gurup.domain.room.RoomConstants;
import com.gurup.domain.room.buildingobjects.BuildingObject;
import com.gurup.domain.room.buildingobjects.BuildingObjectConstants;
import com.gurup.domain.room.buildingobjects.BuildingObjectFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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

    public void leftClick(Rectangle mouseRect) {
        String buildingObjectName = this.player.getCurrentSelectedObject();
        if (buildingObjectName != null) {
            int[] lenArray = this.getXLenAndYLen(buildingObjectName);
            int xLen = lenArray[0];
            int yLen = lenArray[1];
            Rectangle objectRect = new Rectangle(mouseRect.x, mouseRect.y, xLen, yLen);
            Rectangle roomRect = new Rectangle(xStart, yStart, xLimit, yLimit);
            Rectangle doorRect = new Rectangle(RoomConstants.doorXStart.getValue(), RoomConstants.doorYStart.getValue(), RoomConstants.doorXLen.getValue(), RoomConstants.doorYLen.getValue());
            if (objectRect.intersects(doorRect)) {
                JOptionPane.showMessageDialog(null, "You can't build on the door!");
            } else if (roomRect.contains(objectRect)) {
                if (buildingObjects.size() == 0) {
                    BuildingObject firstBuildingObject = buildingObjectFactory.createBuildingObject(buildingObjectName, mouseRect.x, mouseRect.y, xLen, yLen);
                    buildingObjects.add(firstBuildingObject);
                } else {
                    for (BuildingObject buildingObject : buildingObjects) {
                        if (buildingObject.getRectangle().intersects(mouseRect.x, mouseRect.y, 50, 50)) {
                            JOptionPane.showMessageDialog(null, "You can't build on another object!");
                            return;
                        }
                    }
                    BuildingObject newBuildingObject = buildingObjectFactory.createBuildingObject(player.getCurrentSelectedObject(), mouseRect.x, mouseRect.y, xLen, yLen);
                    buildingObjects.add(newBuildingObject);
                }

            } else {
                JOptionPane.showMessageDialog(null, "You can't build outside the room!");
            }
        }
    }


    public ArrayList<BuildingObject> getBuildingObjects() {
        return buildingObjects;
    }

    public int[] getXLenAndYLen(String tempBuildingObjectName) {
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
}

