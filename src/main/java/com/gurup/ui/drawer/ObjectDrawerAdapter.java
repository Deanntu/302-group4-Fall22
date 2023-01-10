package com.gurup.ui.drawer;

import java.awt.Graphics;

public class ObjectDrawerAdapter implements DrawAdapter {
    final ObjectDrawer objectDrawer;

    public ObjectDrawerAdapter() {
        objectDrawer = new ObjectDrawer();
    }

    @Override
    public void draw(Graphics g, int[] rectValues, String name) {
        // TODO Auto-generated method stub
        objectDrawer.drawObject(g, rectValues, name);
    }

}
