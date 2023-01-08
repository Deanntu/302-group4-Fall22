package com.gurup.ui.drawer;

import java.awt.Graphics;

import com.gurup.ui.ImageLoader;

public class ObjectDrawer {

	public void drawObject(Graphics g, int[] rectValues, String name) {
		// TODO Auto-generated method stub
		if (name.equals("Bin")) {
			g.drawImage(ImageLoader.bin_image, rectValues[0], rectValues[1], rectValues[2], rectValues[3], null);
		}
		if (name.equals("Table")) {
			g.drawImage(ImageLoader.table_image, rectValues[0], rectValues[1], rectValues[2], rectValues[3], null);
		}
	}
}
