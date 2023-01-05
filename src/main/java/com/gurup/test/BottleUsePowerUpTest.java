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
	 */
	
	private Player player;
	private ThrownBottlePowerUp bottle;
	@Before
	public void runsBeforeEachTest() {
		// initializes a player and a bottle
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
		// sets the bottle to null to allow multiple initializations
		ThrownBottlePowerUp.setNull();
	}
	@Test
	public void fullRange() {
		// The bottle does not interact with walls in these cases
		// Case - 1.1: use towards the top 
		bottle.setUsable(true);
		bottle.usePowerUp("up");
		assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() - 100);
		// Case - 1.2: use towards the bottom 
		bottle.setUsable(true);
		bottle.usePowerUp("down");
		assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() + 100);
		// Case - 1.3: use towards the left 
		bottle.setUsable(true);
		bottle.usePowerUp("left");
		assertTrue(bottle.getX() == player.getX() - 100 && bottle.getY() == player.getY());
		// Case - 1.4: use towards the right 
		bottle.setUsable(true);
		bottle.usePowerUp("right");
		assertTrue(bottle.getX() == player.getX() + 100 && bottle.getY() == player.getY());
	}
	@Test
	public void nearLeftWall() {
		// The bottle interacts with the left wall in these cases
		// iterate over positions close to the left wall in order to miss no cases
		for (int i = player.getstartX(); i < player.getstartX()+100; i++) {
			player.setX(i);
			ThrownBottlePowerUp bottle = ThrownBottlePowerUp.getInstance(player);
			// Case 2.1: use towards the top when near left wall
			bottle.setUsable(true);
			bottle.usePowerUp("up");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() - 100);
			// Case 2.2: use towards the bottom when near left wall
			bottle.setUsable(true);
			bottle.usePowerUp("down");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() + 100);
			// Case 2.3: use towards the left when near left wall, should hit the wall
			bottle.setUsable(true);
			bottle.usePowerUp("left");
			assertTrue(bottle.getX() == player.getstartX() && bottle.getY() == player.getY());
			// Case 2.4: use towards the right when near left wall
			bottle.setUsable(true);
			bottle.usePowerUp("right");
			assertTrue(bottle.getX() == player.getX() + 100 && bottle.getY() == player.getY());
			ThrownBottlePowerUp.setNull();
		}
	}
	@Test
	public void notSoNearLeftWall() {
		// The bottle should not interact with the left wall in these cases
		// iterate over positions not-so-close to the left wall in order to miss no cases
		for (int i = player.getstartX()+150; i < player.getstartX()+200; i++) {
			player.setX(i);
			ThrownBottlePowerUp bottle = ThrownBottlePowerUp.getInstance(player);
			// Case 3.1: use towards the top when not-so-near left wall
			bottle.setUsable(true);
			bottle.usePowerUp("up");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() - 100);
			// Case 3.2: use towards the bottom when not-so-near left wall
			bottle.setUsable(true);
			bottle.usePowerUp("down");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() + 100);
			// Case 3.3: use towards the left when not-so-near left wall, should not hit the wall
			bottle.setUsable(true);
			bottle.usePowerUp("left");
			assertTrue(bottle.getX() == player.getX() - 100 && bottle.getY() == player.getY());
			// Case 3.4: use towards the right when not-so-near left wall
			bottle.setUsable(true);
			bottle.usePowerUp("right");
			assertTrue(bottle.getX() == player.getX() + 100 && bottle.getY() == player.getY());
			ThrownBottlePowerUp.setNull();
		}
	}
	@Test
	public void nearRightWall() {
		// The bottle interacts with the right wall in these cases
		// iterate over positions close to the right wall in order to miss no cases
		for (int i = player.getxLimit(); i > player.getxLimit()-100; i--) {
			player.setX(i);
			ThrownBottlePowerUp bottle = ThrownBottlePowerUp.getInstance(player);
			// Case 4.1: use towards the top when near right wall
			bottle.setUsable(true);
			bottle.usePowerUp("up");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() - 100);
			// Case 4.2: use towards the bottom when near right wall
			bottle.setUsable(true);
			bottle.usePowerUp("down");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() + 100);
			// Case 4.3: use towards the left when near right wall
			bottle.setUsable(true);
			bottle.usePowerUp("left");
			assertTrue(bottle.getX() == player.getX() - 100 && bottle.getY() == player.getY());
			// Case 4.5: use towards the right when near right wall, should hit the wall
			bottle.setUsable(true);
			bottle.usePowerUp("right");
			assertTrue(bottle.getX() == player.getxLimit() && bottle.getY() == player.getY());
			ThrownBottlePowerUp.setNull();
		}
	}
	@Test
	public void notSoNearRightWall() {
		// The bottle should not interact with the right wall in these cases
		// iterate over positions not-so-close to the right wall in order to miss no cases
		for (int i = player.getxLimit()-150; i > player.getxLimit()-200; i--) {
			player.setX(i);
			ThrownBottlePowerUp bottle = ThrownBottlePowerUp.getInstance(player);
			// Case 5.1: use towards the top when not-so-near right wall
			bottle.setUsable(true);
			bottle.usePowerUp("up");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() - 100);
			// Case 5.2: use towards the bottom when not-so-near right wall
			bottle.setUsable(true);
			bottle.usePowerUp("down");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() + 100);
			// Case 5.3: use towards the left when not-so-near right wall
			bottle.setUsable(true);
			bottle.usePowerUp("left");
			assertTrue(bottle.getX() == player.getX() - 100 && bottle.getY() == player.getY());
			// Case 5.4: use towards the right when not-so-near right wall, should not hit the wall
			bottle.setUsable(true);
			bottle.usePowerUp("right");
			assertTrue(bottle.getX() == player.getX() + 100 && bottle.getY() == player.getY());
			ThrownBottlePowerUp.setNull();
		}
	}
	@Test
	public void nearTopWall() {
		// The bottle interacts with the top wall in these cases
		// iterate over positions close to the top wall in order to miss no cases
		for (int i = player.getstartY(); i < player.getstartY()+100; i++) {
			player.setY(i);
			ThrownBottlePowerUp bottle = ThrownBottlePowerUp.getInstance(player);
			// Case 6.1: use towards the top when near top wall, should hit the wall
			bottle.setUsable(true);
			bottle.usePowerUp("up");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getstartY());
			// Case 6.2: use towards the bottom when near top wall
			bottle.setUsable(true);
			bottle.usePowerUp("down");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() + 100);
			// Case 6.3: use towards the left when near top wall
			bottle.setUsable(true);
			bottle.usePowerUp("left");
			assertTrue(bottle.getX() == player.getX() - 100 && bottle.getY() == player.getY());
			// Case 6.4: use towards the right when near top wall
			bottle.setUsable(true);
			bottle.usePowerUp("right");
			assertTrue(bottle.getX() == player.getX() + 100 && bottle.getY() == player.getY());
			ThrownBottlePowerUp.setNull();
		}
	}
	@Test
	public void notSoNearTopWall() {
		// The bottle should not interact with the top wall in these cases
		// iterate over positions not-so-close to the top wall in order to miss no cases
		for (int i = player.getstartY()+150; i < player.getstartY()+200; i++) {
			player.setY(i);
			ThrownBottlePowerUp bottle = ThrownBottlePowerUp.getInstance(player);
			// Case 7.1: use towards the top when not-so-near top wall, should not hit the wall
			bottle.setUsable(true);
			bottle.usePowerUp("up");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() - 100);
			// Case 7.2: use towards the bottom when not-so-near top wall
			bottle.setUsable(true);
			bottle.usePowerUp("down");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() + 100);
			// Case 7.3: use towards the left when not-so-near top wall
			bottle.setUsable(true);
			bottle.usePowerUp("left");
			assertTrue(bottle.getX() == player.getX() - 100 && bottle.getY() == player.getY());
			// Case 7.4: use towards the right when not-so-near top wall
			bottle.setUsable(true);
			bottle.usePowerUp("right");
			assertTrue(bottle.getX() == player.getX() + 100 && bottle.getY() == player.getY());
			ThrownBottlePowerUp.setNull();
		}
	}
	@Test
	public void nearBottomWall() {
		// The bottle interacts with the bottom wall in these cases
		// iterate over positions close to the bottom wall in order to miss no cases
		for (int i = player.getyLimit(); i > player.getyLimit()-100; i--) {
			player.setY(i);
			ThrownBottlePowerUp bottle = ThrownBottlePowerUp.getInstance(player);
			// Case 8.1: use towards the top when near bottom wall
			bottle.setUsable(true);
			bottle.usePowerUp("up");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() - 100);
			// Case 8.2: use towards the bottom when near bottom wall, should hit the wall
			bottle.setUsable(true);
			bottle.usePowerUp("down");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getyLimit());
			// Case 8.3: use towards the left when near bottom wall
			bottle.setUsable(true);
			bottle.usePowerUp("left");
			assertTrue(bottle.getX() == player.getX() - 100 && bottle.getY() == player.getY());
			// Case 8.4: use towards the right when near bottom wall
			bottle.setUsable(true);
			bottle.usePowerUp("right");
			assertTrue(bottle.getX() == player.getX() + 100 && bottle.getY() == player.getY());
			ThrownBottlePowerUp.setNull();
		}
	}
	@Test
	public void notSoNearBottomWall() {
		for (int i = player.getyLimit()-150; i > player.getyLimit()-200; i--) {
			player.setY(i);
			ThrownBottlePowerUp bottle = ThrownBottlePowerUp.getInstance(player);
			// Case 9.1: use towards the top when not-so-near bottom wall
			bottle.setUsable(true);
			bottle.usePowerUp("up");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() - 100);
			// Case 9.2: use towards the bottom when not-so-near bottom wall, should not hit the wall
			bottle.setUsable(true);
			bottle.usePowerUp("down");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() + 100);
			// Case 9.3: use towards the left when not-so-near bottom wall
			bottle.setUsable(true);
			bottle.usePowerUp("left");
			assertTrue(bottle.getX() == player.getX() - 100 && bottle.getY() == player.getY());
			// Case 9.4: use towards the right when not-so-near bottom wall
			bottle.setUsable(true);
			bottle.usePowerUp("right");
			assertTrue(bottle.getX() == player.getX() + 100 && bottle.getY() == player.getY());
			ThrownBottlePowerUp.setNull();
		}
	}
	@Test
	public void notUsable() {
		// Case 10.1.1-4: try to use it when you cannot for all directions
		bottle.setUsable(false);
		bottle.usePowerUp("up");
		assertFalse(bottle.isUsed());
		bottle.setUsable(false);
		bottle.usePowerUp("down");
		assertFalse(bottle.isUsed());
		bottle.setUsable(false);
		bottle.usePowerUp("left");
		assertFalse(bottle.isUsed());
		bottle.setUsable(false);
		bottle.usePowerUp("right");
		assertFalse(bottle.isUsed());
		
		// then use it when you can
		bottle.setUsable(true);
		bottle.usePowerUp("up");
		assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() - 100);
		
		// Case 10.2.1-4 then when you try to use it when you cannot, the position cannot change
		bottle.setUsable(false);
		bottle.usePowerUp("up");
		assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() - 100);
		bottle.setUsable(false);
		bottle.usePowerUp("down");
		assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() - 100);
		bottle.setUsable(false);
		bottle.usePowerUp("left");
		assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() - 100);
		bottle.setUsable(false);
		bottle.usePowerUp("right");
		assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() - 100);
	}
}
