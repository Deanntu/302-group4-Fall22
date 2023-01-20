package com.gurup.domain.aliens;

import java.awt.Rectangle;

public interface Alien {

    void setActive(boolean b);

    void setXCurrent(int i);

    void setYCurrent(int i);
    
    void setXLen(int i);
    
    void setYLen(int i);

    boolean isActive();

    String getName();

    int[] rectArray();

    Rectangle getRectangle();

}
