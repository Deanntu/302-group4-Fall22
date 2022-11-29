package com.gurup.timerbasedanimation;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Building extends JPanel implements ActionListener, KeyListener {

	private Player player;

	public Building(Player player) {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		this.player = player;
		new Timer(20, e -> {
			repaint();
		}).start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// player.move();
		player.draw(g);
	}

	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			if (player.x >= player.xLimit) {
				player.x = player.xLimit;
			} else {
				player.x += 3;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			if (player.x <= 0) {
				player.x = 0;
			} else {
				player.x -= 3;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			if (player.y >= player.yLimit) {
				player.y = player.yLimit;
			} else {
				player.y -= 3;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			if (player.y <= 0) {
				player.y = 0;
			} else {
				player.y += 3;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
