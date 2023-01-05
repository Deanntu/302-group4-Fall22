package com.gurup.ui.drawer;
import com.gurup.ui.ImageLoader;

import java.awt.Color;
import java.awt.Graphics;


public class AlienDrawer {
    public void drawAlien(Graphics g, int[] rectValues, String name) {
        if (name.equals("Blind")) {
            g.drawImage(ImageLoader.blind_alien_image, rectValues[0], rectValues[1], rectValues[2], rectValues[3], null);
        }
        if (name.equals("Shooter")) {
            g.drawImage(ImageLoader.shooter_alien_image, rectValues[0], rectValues[1], rectValues[2], rectValues[3], null);
            g.setColor(Color.RED);
            g.drawOval(rectValues[0]-25, rectValues[1]-25, rectValues[2]+50, rectValues[3]+50);
        }
    }
}
