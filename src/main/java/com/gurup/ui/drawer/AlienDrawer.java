package com.gurup.ui.drawer;

import java.awt.*;

public class AlienDrawer {
    public void drawAlien(Graphics g, int[] rectValues, String name) {
        // TODO Auto-generated method stub

        if (name.equals("Blind")) {
            g.setColor(Color.GRAY);
        }
        if (name.equals("Shooter")) {
            g.setColor(Color.RED);
        }
        g.fillOval(rectValues[0],rectValues[1],rectValues[2],rectValues[3]);
    }
}
