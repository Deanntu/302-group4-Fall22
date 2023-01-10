package com.gurup.ui.drawer;

import java.awt.Graphics;

import com.gurup.ui.ImageLoader;

public class PowerUpDrawer {
    public void drawPowerUp(Graphics g, int[] rectValues, String name) {
        // TODO Auto-generated method stub
        switch (name) {
            case "health" ->
                    g.drawImage(ImageLoader.health_image, rectValues[0], rectValues[1], rectValues[2], rectValues[3], null);
            case "time" ->
                    g.drawImage(ImageLoader.time_image, rectValues[0], rectValues[1], rectValues[2], rectValues[3], null);
            case "vest" ->
                    g.drawImage(ImageLoader.vest_image, rectValues[0], rectValues[1], rectValues[2], rectValues[3], null);
            case "bottle" ->
                    g.drawImage(ImageLoader.plastic_bottle_image, rectValues[0], rectValues[1], rectValues[2], rectValues[3],
                            null);
            case "thrownbottle" ->
                    g.drawImage(ImageLoader.thrown_plastic_bottle_image, rectValues[0], rectValues[1], rectValues[2],
                            rectValues[3], null);
        }
    }
}
