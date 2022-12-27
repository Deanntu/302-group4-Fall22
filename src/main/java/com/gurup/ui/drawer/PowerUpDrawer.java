package com.gurup.ui.drawer;

import com.gurup.ui.ImageLoader;

import java.awt.Color;
import java.awt.Graphics;

public class PowerUpDrawer {
	public void drawPowerUp(Graphics g, int[] rectValues, String name) {
		// TODO Auto-generated method stub
		if (name.equals("health")) {
			g.drawImage(ImageLoader.health_image, rectValues[0], rectValues[1], rectValues[2], rectValues[3], null);
		} else if (name.equals("time")) {
			g.drawImage(ImageLoader.time_image, rectValues[0], rectValues[1], rectValues[2], rectValues[3], null);
		} else if (name.equals("vest")) {
			g.drawImage(ImageLoader.vest_image, rectValues[0], rectValues[1], rectValues[2], rectValues[3], null);
		} else if (name.equals("bottle")) {
			g.drawImage(ImageLoader.plastic_bottle_image, rectValues[0], rectValues[1], rectValues[2], rectValues[3], null);
		} else if (name.equals("thrownbottle")) {
			g.drawImage(ImageLoader.thrown_plastic_bottle_image, rectValues[0], rectValues[1], rectValues[2], rectValues[3], null);
		}
	}
}
