package com.gurup.ui.drawer;

import java.awt.Color;
import java.awt.Graphics;

public class ObjectDrawer {

	public void drawObject(Graphics g, int[] rectValues, String name) {
		// TODO Auto-generated method stub

		if (name.equals("Bin")) {
			g.setColor(Color.MAGENTA);
			g.fillOval(rectValues[0],rectValues[1],rectValues[2],rectValues[3]);

		}
		if (name.equals("Table")) {
			g.setColor(Color.RED);
			g.fillRect(rectValues[0],rectValues[1],rectValues[2],rectValues[3]);
		}
	}
}
