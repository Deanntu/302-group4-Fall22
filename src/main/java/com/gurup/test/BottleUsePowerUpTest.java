package com.gurup.test;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Toolkit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.gurup.domain.Player;
import com.gurup.domain.powerups.ThrownBottlePowerUp;

public class BottleUsePowerUpTest {
	
	
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
	
	private Player player;
	private ThrownBottlePowerUp bottle;
	@Before
	public void runsBeforeEachTest() {
		player = new Player(Color.blue, 50, 50,
				Toolkit.getDefaultToolkit().getScreenSize().width - 100,
				Toolkit.getDefaultToolkit().getScreenSize().height - 175, 50,
				60);
		player.setX(player.getxLimit()/2);
		player.setY(player.getyLimit()/2);
		bottle = ThrownBottlePowerUp.getInstance(player);
	}
	@After
	public void runsAfterEachTest() {
		ThrownBottlePowerUp.setNull();
	}
	@BeforeClass
	public static void runsOnceAtStartup() {
		
	}
	@AfterClass
	public static void runsOnceAtTheEnd() {
		
	}
	
	
	
	@Test
	public void fullRange() {
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
		for (int i = player.getstartX(); i < player.getstartX()+100; i++) {
			player.setX(i);
			ThrownBottlePowerUp bottle = ThrownBottlePowerUp.getInstance(player);
			bottle.setUsable(true);
			bottle.usePowerUp("up");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() - 100);
			bottle.setUsable(true);
			bottle.usePowerUp("down");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() + 100);
			bottle.setUsable(true);
			bottle.usePowerUp("left");
			assertTrue(bottle.getX() == player.getstartX() && bottle.getY() == player.getY());
			bottle.setUsable(true);
			bottle.usePowerUp("right");
			assertTrue(bottle.getX() == player.getX() + 100 && bottle.getY() == player.getY());
			ThrownBottlePowerUp.setNull();
		}
	}
	@Test
	public void notSoNearLeftWall() {
		for (int i = player.getstartX()+150; i < player.getstartX()+200; i++) {
			player.setX(i);
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
			ThrownBottlePowerUp.setNull();
		}
	}
	@Test
	public void nearRightWall() {
		for (int i = player.getxLimit(); i > player.getxLimit()-100; i--) {
			player.setX(i);
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
			assertTrue(bottle.getX() == player.getxLimit() && bottle.getY() == player.getY());
			ThrownBottlePowerUp.setNull();
		}
	}
	@Test
	public void nearTopWall() {
		for (int i = player.getstartY(); i < player.getstartY()+100; i++) {
			player.setY(i);
			ThrownBottlePowerUp bottle = ThrownBottlePowerUp.getInstance(player);
			bottle.setUsable(true);
			bottle.usePowerUp("up");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getstartY());
			bottle.setUsable(true);
			bottle.usePowerUp("down");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() + 100);
			bottle.setUsable(true);
			bottle.usePowerUp("left");
			assertTrue(bottle.getX() == player.getX() - 100 && bottle.getY() == player.getY());
			bottle.setUsable(true);
			bottle.usePowerUp("right");
			assertTrue(bottle.getX() == player.getX() + 100 && bottle.getY() == player.getY());
			ThrownBottlePowerUp.setNull();
		}
	}
	@Test
	public void nearBottomWall() {
		for (int i = player.getyLimit(); i > player.getyLimit()-100; i--) {
			player.setY(i);
			ThrownBottlePowerUp bottle = ThrownBottlePowerUp.getInstance(player);
			bottle.setUsable(true);
			bottle.usePowerUp("up");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() - 100);
			bottle.setUsable(true);
			bottle.usePowerUp("down");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getyLimit());
			bottle.setUsable(true);
			bottle.usePowerUp("left");
			assertTrue(bottle.getX() == player.getX() - 100 && bottle.getY() == player.getY());
			bottle.setUsable(true);
			bottle.usePowerUp("right");
			assertTrue(bottle.getX() == player.getX() + 100 && bottle.getY() == player.getY());
			ThrownBottlePowerUp.setNull();
		}
	}
	@Test
	public void notUsable() {
		
	}
	@Test
	public void multipleUses() {
		
	}
}
