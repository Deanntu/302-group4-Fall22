package com.gurup.domain.room.buildingobjects;

import java.awt.*;

public class Table implements BuildingObject {

    int xStart, yStart;
    private int xLimit;
    private int yLimit;
    private static int counter = 0;
    private final String TABLE = "Table";

    public Table(int xStart, int yStart, int xLimit, int yLimit) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xLimit = xLimit;
        this.yLimit = yLimit;
        counter++;
    }


    public static int getCounter() {
        return counter;
    }

    @Override
    public String getName() {
        return TABLE;
    }

    @Override
    public int[] rectArray() {
        int[] rectValues = {xStart, yStart, xLimit, yLimit};
        return rectValues;
    }


    public int getxLimit() {
        return xLimit;
    }

    public int getyLimit() {
        return yLimit;
    }

    public void setxLimit(int xLimit) {
        this.xLimit = xLimit;
    }



    public void setyLimit(int yLimit) {
        this.yLimit = yLimit;
    }

    public int getstartX() {
        return xStart;
    }

    public int getstartY() {
        return yStart;
    }

    public Rectangle getRectangle() {
        // TODO Auto-generated method stub
        return new Rectangle(xStart,yStart,xLimit,yLimit);
    }

}

