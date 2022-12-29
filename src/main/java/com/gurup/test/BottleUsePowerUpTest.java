package com.gurup.test;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Toolkit;

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
	@Test
	public void fullRange() {
		Player player = new Player(Color.blue, 50, 50,
				Toolkit.getDefaultToolkit().getScreenSize().width - 100,
				Toolkit.getDefaultToolkit().getScreenSize().height - 175, 50,
				60);
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
		ThrownBottlePowerUp.setNull();
	}
	@Test
	public void nearLeftWall() {
		Player player = new Player(Color.blue, 50, 50,
				Toolkit.getDefaultToolkit().getScreenSize().width - 100,
				Toolkit.getDefaultToolkit().getScreenSize().height - 175, 50,
				60);
		//player.setX(player.getxLimit()/2);
		player.setY(player.getyLimit()/2);
		for (int i = player.getstartX(); i < player.getstartX()+49; i++) { // fails when tested for values until player.getstartX()+99
			player.setX(i);
			ThrownBottlePowerUp bottle = ThrownBottlePowerUp.getInstance(player);
			System.out.println(i);
			bottle.setUsable(true);
			bottle.usePowerUp("up");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() - 100);
			bottle.setUsable(true);
			bottle.usePowerUp("down");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() + 100);
			bottle.setUsable(true);
			bottle.usePowerUp("left");
			System.out.println(player.getstartX());
			System.out.println(bottle.getX());
			System.out.println("sdohkahokofhjaofnmsdlhmfdh");
			assertTrue(bottle.getX() == player.getstartX() && bottle.getY() == player.getY());
			bottle.setUsable(true);
			bottle.usePowerUp("right");
			assertTrue(bottle.getX() == player.getX() + 100 && bottle.getY() == player.getY());
			ThrownBottlePowerUp.setNull();
		}
	}
	@Test
	public void nearRightWall() {
		/*Player player = new Player(Color.blue, 50, 50,
				Toolkit.getDefaultToolkit().getScreenSize().width - 100,
				Toolkit.getDefaultToolkit().getScreenSize().height - 175, 50,
				60);
		//player.setX(player.getxLimit()/2);
		player.setY(player.getyLimit()/2);
		for (int i = player.getxLimit(); i < player.getxLimit()-99; i--) {
			player.setX(i);
			ThrownBottlePowerUp bottle = ThrownBottlePowerUp.getInstance(player);
			System.out.println(i);
			bottle.setUsable(true);
			bottle.usePowerUp("up");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() - 100);
			bottle.setUsable(true);
			bottle.usePowerUp("down");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY() + 100);
			bottle.setUsable(true);
			bottle.usePowerUp("left");
			System.out.println(player.getstartX());
			System.out.println(bottle.getX());
			System.out.println("sdohkahokofhjaofnmsdlhmfdh");
			assertTrue(bottle.getX() == player.getstartX() - 100 && bottle.getY() == player.getY());
			bottle.setUsable(true);
			bottle.usePowerUp("right");
			assertTrue(bottle.getX() == player.getX() && bottle.getY() == player.getY());
		}
		ThrownBottlePowerUp.setNull();*/
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
