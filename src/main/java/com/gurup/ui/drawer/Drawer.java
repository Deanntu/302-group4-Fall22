package com.gurup.ui.drawer;

import java.awt.Graphics;

public class Drawer {
	DrawAdapter drawAdapter;
	public Drawer(String type) {
		if(type.equals("PowerUp")) {
			drawAdapter = new PowerUpDrawerAdapter();
		}
		else if(type.equals("Object")) {
			drawAdapter = new ObjectDrawerAdapter();
		}
	}
	public void draw(Graphics g, int[] rectValues, String name) {
		drawAdapter.draw(g, rectValues, name);
	}
}
