package com.gurup.domain.buildingmode;
import com.gurup.domain.Player;

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

}

