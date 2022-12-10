package com.gurup.domain.drawer;

import java.awt.Color;
import java.awt.Graphics;

public class ObjectDrawer {

	public void drawObject(Graphics g, int[] rectValues, String name) {
		// TODO Auto-generated method stub
		if (name.equals("oval")) {
			g.setColor(Color.MAGENTA);
			g.fillOval(rectValues[0],rectValues[1],rectValues[2],rectValues[3]);

		} else if (name.equals("rect")) {
			g.setColor(Color.RED);
			g.fillRect(rectValues[0],rectValues[1],rectValues[2],rectValues[3]);
		}
	}

}
