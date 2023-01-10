package com.gurup.ui.drawer;

import java.awt.Graphics;

public class AlienDrawerAdapter implements DrawAdapter {
    final AlienDrawer alienDrawer;

    public AlienDrawerAdapter() {
        alienDrawer = new AlienDrawer();
    }

    public void draw(Graphics g, int[] rectValues, String name) {
        // TODO Auto-generated method stub
        alienDrawer.drawAlien(g, rectValues, name);
    }
}
