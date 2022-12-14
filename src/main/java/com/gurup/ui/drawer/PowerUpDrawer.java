package com.gurup.ui.drawer;

import java.awt.Color;
import java.awt.Graphics;

public class PowerUpDrawer {
	public void drawPowerUp(Graphics g, int[] rectValues, String name) {
		// TODO Auto-generated method stub
		if (name.equals("health")) {
			g.setColor(Color.cyan);
		} else if (name.equals("time")) {
			g.setColor(Color.black);
		} else if (name.equals("vest")) {
			g.setColor(Color.orange);
		}
		g.fillOval(rectValues[0],rectValues[1],rectValues[2],rectValues[3]);
	}

}
