package com.gurup.domain.room.buildingobjects;

import java.awt.Rectangle;

public class Table implements BuildingObject {

    private int xCurrent, yCurrent;
    private int xLen, yLen;
    private final String NAME = "Table";

    public Table(int xCurrent, int yCurrent, int xLen, int yLen) {
        this.xCurrent = xCurrent;
        this.yCurrent = yCurrent;
        this.xLen = xLen;
        this.yLen = yLen;
    }


    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public int[] rectArray() {
        return new int[]{xCurrent, yCurrent, xLen, yLen};
    }

    public int getXLen() {
        return xLen;
    }

    public void setXLen(int xLen) {
        this.xLen = xLen;
    }

    public int getYLen() {
        return yLen;
    }

    public void setYLen(int yLen) {
        this.yLen = yLen;
    }

    public int getXCurrent() {
        return xCurrent;
    }

    public int getYCurrent() {
        return yCurrent;
    }

    @Override
    public void setXCurrent(int xCurrent) {
        this.xCurrent = xCurrent;
    }

    @Override
    public void setYCurrent(int yCurrent) {
        this.yCurrent = yCurrent;

    }

    public Rectangle getRectangle() {
        // TODO Auto-generated method stub
        return new Rectangle(xCurrent, yCurrent, xLen, yLen);
    }
}
