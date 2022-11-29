package com.gurup.timerbasedanimation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Player {
    Color playerColor;
    int xStart, yStart, xLimit, yLimit;
    int x, y;
    private static final int numSteps = 200;
   
    public Player(Color playerColor, int xStart, int yStart,
        int xLimit, int yLimit) {
        this.playerColor = playerColor;
        this.xStart = xStart;
        this.yStart = yStart;
        this.xLimit = xLimit;
        this.yLimit = yLimit;
        this.x = xStart;
        this.y = yStart;
    }

    public void draw(Graphics g) {
        Point pos = new Point(x,y);
        g.setColor(playerColor);
        g.fillOval((int)pos.getX(), (int)pos.getY(), 10, 10);
    }

    
}