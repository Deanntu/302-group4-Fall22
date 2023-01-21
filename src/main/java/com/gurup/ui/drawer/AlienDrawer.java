package com.gurup.ui.drawer;

import java.awt.Color;
import java.awt.Graphics;

import com.gurup.ui.ImageLoader;

public class AlienDrawer {
    public void drawAlien(Graphics g, int[] rectValues, String name) {
        if (name.equals("Blind")) {
            g.drawImage(ImageLoader.blind_alien_image, rectValues[0], rectValues[1], rectValues[2], rectValues[3],
                    null);
        }
        if (name.equals("Shooter")) {
            g.drawImage(ImageLoader.shooter_alien_image, rectValues[0], rectValues[1], rectValues[2], rectValues[3],
                    null);
            g.setColor(Color.RED);
            g.drawRect(rectValues[0] - 100, rectValues[1] - 100, rectValues[2] + 200, rectValues[3] + 200);
        }
        if (name.equals("TimeWasting")) {
            g.drawImage(ImageLoader.time_wasting_alien_image, rectValues[0], rectValues[1], rectValues[2],rectValues[3],
                    null);
        }
    }
}
