package com.gurup.test;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Toolkit;

import org.junit.Test;

import com.gurup.domain.Player;
import com.gurup.domain.powerups.ThrownBottlePowerUp;

public class BottleUsePowerUpTest {
	Player player = new Player(Color.blue, 50, 50,
			Toolkit.getDefaultToolkit().getScreenSize().width - 100,
			Toolkit.getDefaultToolkit().getScreenSize().height - 175, 50,
			60);
	/* what to test
	 * 
	 * full range behavior
	 * behavior when thrown to the left wall, when close and far
	 * behavior when thrown to the right wall, when close and far
	 * behavior when thrown to the top wall, when close and far
	 * behavior when thrown to the bottom wall, when close and far
	 * behavior when not usable
	 * behavior when usable but we try to use it multiple times
	 */
	@Test
	public void fullRange() {
		player.setX(player.getxLimit()/2);
		player.setY(player.getyLimit()/2);
		ThrownBottlePowerUp bottle = ThrownBottlePowerUp.getInstance(player);
		bottle.setUsable(true);
		bottle.usePowerUp("up");
		assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() - 100);
		bottle.setUsable(true);
		bottle.usePowerUp("down");
		assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() + 100);
		bottle.setUsable(true);
		bottle.usePowerUp("left");
		assertTrue(bottle.getX() == player.getX() - 100 && bottle.getY() == player.getY());
		bottle.setUsable(true);
		bottle.usePowerUp("right");
		assertTrue(bottle.getX() == player.getX() + 100 && bottle.getY() == player.getY());
	}
	@Test
	public void nearLeftWall() {
		ThrownBottlePowerUp bottle = ThrownBottlePowerUp.getInstance(player);
		player.setX(player.getxLimit()/2);
		player.setY(player.getyLimit()/2);
		for (int i = player.getstartX(); i < player.getstartX()+100; i++) {
			player.setX(i);
			
			bottle.setUsable(true);
			bottle.usePowerUp("up");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() - 100);
			bottle.setUsable(true);
			bottle.usePowerUp("down");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() + 100);
			bottle.setUsable(true);
			bottle.usePowerUp("left");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY());
			bottle.setUsable(true);
			bottle.usePowerUp("right");
			assertTrue(bottle.getX() == player.getX() + 100 && bottle.getY() == player.getY());
		}
	}
	@Test
	public void nearRightWall() {
		ThrownBottlePowerUp bottle = ThrownBottlePowerUp.getInstance(player);
		player.setX(player.getxLimit()/2);
		player.setY(player.getyLimit()/2);
		for (int i = player.getxLimit(); i > player.getxLimit()-100; i--) {
			player.setX(i);
			
			bottle.setUsable(true);
			bottle.usePowerUp("up");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() - 100);
			bottle.setUsable(true);
			bottle.usePowerUp("down");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() + 100);
			bottle.setUsable(true);
			bottle.usePowerUp("left");
			assertTrue(bottle.getX() == player.getX() - 100 && bottle.getY() == player.getY());
			bottle.setUsable(true);
			bottle.usePowerUp("right");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY());
		}
	}
	@Test
	public void nearTopWall() {
		
	}
	@Test
	public void nearBottomWall() {
		
	}
	@Test
	public void notUsable() {
		
	}
	@Test
	public void multipleUses() {
		
	}
}
