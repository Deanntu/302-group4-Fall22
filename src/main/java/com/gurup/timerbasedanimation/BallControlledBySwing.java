package com.gurup.timerbasedanimation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class BallControlledBySwing {
    Color ballColor;
    int xStart, yStart, xLimit, yLimit;
    int x, y;
    private static final int numSteps = 200;
   
    public BallControlledBySwing(Color ballColor, int xStart, int yStart,
        int xLimit, int yLimit) {
        this.ballColor = ballColor;
        this.xStart = xStart;
        this.yStart = yStart;
        this.xLimit = xLimit;
        this.yLimit = yLimit;
        this.x = xStart;
        this.y = yStart;
    }

    public void draw(Graphics g) {
        Point pos = new Point(x,y);
        g.setColor(ballColor);
        g.fillOval((int)pos.getX(), (int)pos.getY(), 5, 5);
    }
    
    //public void move() {
    	/*Point pos = myPath.nextPosition();
    	if (! myPath.hasMoreSteps()) {
            if (pos.getX() == xStart)
                myPath = new StraightLinePath(xStart, yStart, xLimit, yLimit, numSteps);
            else
                myPath = new StraightLinePath(xLimit, yLimit, xStart, yStart, numSteps);
        }*/
    	
    //}
    
}