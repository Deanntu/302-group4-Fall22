package com.gurup.ui.drawer;

import java.awt.Graphics;

public class Drawer {
    DrawAdapter drawAdapter;

    public Drawer(String type) {
        switch (type) {
            case "PowerUp" -> drawAdapter = new PowerUpDrawerAdapter();
            case "Object" -> drawAdapter = new ObjectDrawerAdapter();
            case "Alien" -> drawAdapter = new AlienDrawerAdapter();
        }
    }

    public void draw(Graphics g, int[] rectValues, String name) {
        drawAdapter.draw(g, rectValues, name);
    }
}
