package com.gurup.domain.buildingmode;
import com.gurup.domain.Player;

import java.awt.*;

public class BuildingModeRoom {
    private static int xStart;
    private static int yStart;
    private static int xLimit;
    private static int yLimit;
    private String name;
    private Player player;

    public BuildingModeRoom(String name, int xStart, int yStart, int xLimit, int yLimit, Player player) {
        this.name = name;
        this.xStart = xStart;
        this.yStart = yStart;
        this.xLimit = xLimit;
        this.yLimit = yLimit;
        this.player = player;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public static int getXLimit() {return xLimit;}
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
        System.out.println(player.getCurrentSelectedObject());
    }

    public void rightClick(Rectangle mouseRect) {
        System.out.println(player.getCurrentSelectedObject());
    }
}

