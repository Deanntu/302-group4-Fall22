package com.gurup.domain.room.buildingobjects;

import java.awt.Rectangle;

public interface BuildingObject {
    String getName();

    int[] rectArray();

    int getXLen();

    void setXLen(int xLen);

    int getYLen();

    void setYLen(int yLen);

    int getXCurrent();

    int getYCurrent();

    void setXCurrent(int xCurrent);

    void setYCurrent(int yCurrent);

    Rectangle getRectangle();

}
