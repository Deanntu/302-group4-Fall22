package com.gurup.ui.drawer;

import java.awt.Graphics;

public class PowerUpDrawerAdapter implements DrawAdapter {
    final PowerUpDrawer powerDrawer;

    public PowerUpDrawerAdapter() {
        powerDrawer = new PowerUpDrawer();
    }

    @Override
    public void draw(Graphics g, int[] rectValues, String name) {
        // TODO Auto-generated method stub
        powerDrawer.drawPowerUp(g, rectValues, name);
    }

}
