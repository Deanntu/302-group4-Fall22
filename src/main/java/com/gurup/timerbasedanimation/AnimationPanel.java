package com.gurup.timerbasedanimation;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;


public class AnimationPanel extends JPanel implements ActionListener, KeyListener{
	
	private BallControlledBySwing ball2;

	
	public AnimationPanel(BallControlledBySwing ball2) {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		this.ball2 = ball2;		
		new Timer(20, e-> {repaint();} ).start();  
	}
	
	 
	 public void paintComponent(Graphics g) {
         super.paintComponent(g);      
         //ball2.move();
         ball2.draw(g);
	 }
	 
	 public Dimension getPreferredSize() {
	        return new Dimension(500,500);
	    }


	@Override
	public void keyTyped(KeyEvent e) {}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
    	if (e.getKeyCode() == KeyEvent.VK_RIGHT||e.getKeyCode() ==KeyEvent.VK_D) {
			if (ball2.x >= ball2.xLimit) {
				ball2.x = ball2.xLimit;
			}
			else {
				ball2.x+=3;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT||e.getKeyCode() ==KeyEvent.VK_A) {
			if (ball2.x <= 0) {
				ball2.x = 0;
			}
			else {
				ball2.x-=3;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP||e.getKeyCode() ==KeyEvent.VK_W) {
			if (ball2.y >= ball2.yLimit) {
				ball2.y = ball2.yLimit;
			}
			else {
				ball2.y-=3;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN||e.getKeyCode() ==KeyEvent.VK_S) {
			if (ball2.y <= 0) {
				ball2.y = 0;
			}
			else {
				ball2.y+=3;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}


	@Override
	public void actionPerformed(ActionEvent e) {}

}
