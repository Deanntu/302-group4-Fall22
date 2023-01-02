package com.gurup.test;

import org.junit.Test;

import com.gurup.domain.Bag;
import com.gurup.domain.Player;
import com.gurup.domain.powerups.BottlePowerUp;
import com.gurup.domain.powerups.HealthPowerUp;
import com.gurup.domain.powerups.PowerUp;
import com.gurup.domain.powerups.ThrownBottlePowerUp;
import com.gurup.domain.powerups.TimePowerUp;
import com.gurup.domain.powerups.VestPowerUp;

import org.junit.Before;

import java.awt.Color;
import java.awt.Toolkit;
import java.util.ArrayList;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.AfterClass;

public class BagTest {
	static Bag bag;
	static Player player;
	static ArrayList<PowerUp> setupPowerUps = new ArrayList<PowerUp>();
	
	@BeforeClass
	public static void setUp() {
		player = new Player(Color.blue, 50, 50,
				Toolkit.getDefaultToolkit().getScreenSize().width - 100,
				Toolkit.getDefaultToolkit().getScreenSize().height - 175, 50,
				60);
		bag = new Bag(player);
		TimePowerUp t = TimePowerUp.getInstance(player);
		HealthPowerUp h = HealthPowerUp.getInstance(player);
		VestPowerUp v = VestPowerUp.getInstance(player);
		BottlePowerUp b = BottlePowerUp.getInstance(player);
		ThrownBottlePowerUp tbp = ThrownBottlePowerUp.getInstance(player);
		t.setX(420);
		t.setxLimit(50);
		t.setY(320);
		t.setyLimit(50);

		h.setX(420);
		h.setxLimit(50);
		h.setY(320);
		h.setyLimit(50);

		v.setX(420);
		v.setxLimit(50);
		v.setY(320);
		v.setyLimit(50);

		b.setX(420);
		b.setxLimit(50);
		b.setY(320);
		b.setyLimit(50);

		setupPowerUps.add(t);
		setupPowerUps.add(h);
		setupPowerUps.add(v);
		setupPowerUps.add(b);
		bag.setupBag(setupPowerUps);
	}
}
