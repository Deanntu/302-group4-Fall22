package com.gurup.domain.powerups;

import java.awt.Rectangle;

public interface PowerUp {
    void usePowerUp();

    boolean isStorable();

    Rectangle getRectangle();

    boolean isActive();

    void setIsActive(boolean b);

    //void draw(Graphics g);
    void setXCurrent(int i);

    void setXLen(int i);

    void setYCurrent(int i);

    void setYLen(int i);

    int[] rectArray();

    String getName();

    int getSlotId();
}
