package com.gurup.ui.drawer;

import java.awt.Graphics;

public class Drawer {
    DrawAdapter drawAdapter;

    public Drawer(String type) {
        switch (type) {
            case "PowerUp":
                drawAdapter = new PowerUpDrawerAdapter();
                break;
            case "Object":
                drawAdapter = new ObjectDrawerAdapter();
                break;
            case "Alien":
                drawAdapter = new AlienDrawerAdapter();
                break;
        }
    }

    public void draw(Graphics g, int[] rectValues, String name) {
        drawAdapter.draw(g, rectValues, name);
    }
}
