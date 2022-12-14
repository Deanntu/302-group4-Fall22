package com.gurup.ui.drawer;

import java.awt.*;

public class AlienDrawerAdapter {
    AlienDrawer alienDrawer;

    public AlienDrawerAdapter() {
        alienDrawer = new AlienDrawer();
    }

    public void draw(Graphics g, int[] rectValues, String name) {
        // TODO Auto-generated method stub
        alienDrawer.drawAlien(g, rectValues, name);
    }
}
