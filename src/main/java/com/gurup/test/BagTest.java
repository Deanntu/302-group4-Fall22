package com.gurup.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.gurup.domain.Bag;
import com.gurup.domain.Player;
import com.gurup.domain.PlayerConstants;
import com.gurup.domain.powerups.BottlePowerUp;
import com.gurup.domain.powerups.HealthPowerUp;
import com.gurup.domain.powerups.PowerUp;
import com.gurup.domain.powerups.ThrownBottlePowerUp;
import com.gurup.domain.powerups.TimePowerUp;
import com.gurup.domain.powerups.VestPowerUp;

public class BagTest {
    static Bag bag;
    static Player player;
    static final ArrayList<PowerUp> setupPowerUps = new ArrayList<>();



    @BeforeEach
    public void setUp() {
        player = new Player(PlayerConstants.xStart.getValue(), PlayerConstants.yStart.getValue(),
                PlayerConstants.xLen.getValue(), PlayerConstants.xLen.getValue(), 60);
        bag = new Bag(player);
        TimePowerUp t = TimePowerUp.getInstance(player);
        HealthPowerUp h = HealthPowerUp.getInstance(player);
        VestPowerUp v = VestPowerUp.getInstance(player);
        BottlePowerUp b = BottlePowerUp.getInstance(player);
        ThrownBottlePowerUp.getInstance(player);
        t.setXCurrent(420);
        t.setXLen(50);
        t.setYCurrent(320);
        t.setYLen(50);

        h.setXCurrent(420);
        h.setXLen(50);
        h.setYCurrent(320);
        h.setYLen(50);

        v.setXCurrent(420);
        v.setXLen(50);
        v.setYCurrent(320);
        v.setYLen(50);

        b.setXCurrent(420);
        b.setXLen(50);
        b.setYCurrent(320);
        b.setYLen(50);

        setupPowerUps.add(t);
        setupPowerUps.add(h);
        setupPowerUps.add(v);
        setupPowerUps.add(b);
        bag.setupBag(setupPowerUps);
    }

    @Test
    public void testStorePowerUp() {
        // Store two vest powerups and one bottle power up
        assertTrue(bag.repOk());
        bag.storePowerUp(setupPowerUps.get(2));
        assertTrue(bag.repOk());
        bag.storePowerUp(setupPowerUps.get(2));
        assertTrue(bag.repOk());
        bag.storePowerUp(setupPowerUps.get(3));
        assertTrue(bag.repOk());

        // Get the number of powerups in the bag
        int numVestPowerUps = bag.getPowerUps().get(setupPowerUps.get(2));
        assertTrue(bag.repOk());
        int numBottlePowerUps = bag.getPowerUps().get(setupPowerUps.get(3));
        assertTrue(bag.repOk());

        // Check if the number of powerups in the bag is correct
        assertEquals(2, numVestPowerUps);
        assertEquals(1, numBottlePowerUps);
    }

    @Test
    public void testSelectExistingPowerUp() {
        // Store two vest powerups and one bottle power up
        assertTrue(bag.repOk());
        bag.storePowerUp(setupPowerUps.get(2));
        assertTrue(bag.repOk());
        bag.storePowerUp(setupPowerUps.get(2));
        assertTrue(bag.repOk());
        bag.storePowerUp(setupPowerUps.get(3));
        assertTrue(bag.repOk());


        // Select two vest powerups
        bag.selectPowerUp(setupPowerUps.get(2));
        assertTrue(bag.repOk());
        bag.selectPowerUp(setupPowerUps.get(2));
        assertTrue(bag.repOk());

        // Get the number of powerups in the bag
        int numVestPowerUps = bag.getPowerUps().get(setupPowerUps.get(2));
        assertTrue(bag.repOk());
        int numBottlePowerUps = bag.getPowerUps().get(setupPowerUps.get(3));
        assertTrue(bag.repOk());


        // Check if the number of powerups in the bag is correct
        assertEquals(0, numVestPowerUps);
        assertEquals(1, numBottlePowerUps);

    }

    @Test
    public void testSelectNonExistingPowerUp() {
        // Store two vest powerups
        assertTrue(bag.repOk());
        bag.storePowerUp(setupPowerUps.get(2));
        assertTrue(bag.repOk());
        bag.storePowerUp(setupPowerUps.get(2));

        // Select non-existing bottle powerup
        assertTrue(bag.repOk());
        bag.selectPowerUp(setupPowerUps.get(3));

        // Get the number of powerups in the bag
        assertTrue(bag.repOk());
        int numVestPowerUps = bag.getPowerUps().get(setupPowerUps.get(2));
        assertTrue(bag.repOk());
        int numBottlePowerUps = bag.getPowerUps().get(setupPowerUps.get(3));
        assertTrue(bag.repOk());

        // Check if the number of powerups in the bag is correct
        assertEquals(2, numVestPowerUps);
        assertEquals(0, numBottlePowerUps);
    }

    @Test
    public void testSelectNull() {
        // Store two vest powerups and one bottle power up
        assertTrue(bag.repOk());
        bag.storePowerUp(setupPowerUps.get(2));
        assertTrue(bag.repOk());
        bag.storePowerUp(setupPowerUps.get(2));
        assertTrue(bag.repOk());
        bag.storePowerUp(setupPowerUps.get(3));
        assertTrue(bag.repOk());


        // Select null
        bag.selectPowerUp(null);
        assertTrue(bag.repOk());


        // Get the number of powerups in the bag
        int numVestPowerUps = bag.getPowerUps().get(setupPowerUps.get(2));
        assertTrue(bag.repOk());
        int numBottlePowerUps = bag.getPowerUps().get(setupPowerUps.get(3));
        assertTrue(bag.repOk());

        // Check if the number of powerups in the bag is correct
        assertEquals(2, numVestPowerUps);
        assertEquals(1, numBottlePowerUps);
    }

    @Test
    public void testSelectNonStorablePowerUp() {
        // Store two vest powerups and one bottle power up
        assertTrue(bag.repOk());
        bag.storePowerUp(setupPowerUps.get(2));
        assertTrue(bag.repOk());
        bag.storePowerUp(setupPowerUps.get(2));
        assertTrue(bag.repOk());
        bag.storePowerUp(setupPowerUps.get(3));
        assertTrue(bag.repOk());

        // Select non-storable time powerup
        bag.selectPowerUp(setupPowerUps.get(0));
        assertTrue(bag.repOk());
        // Select non-storable health powerup
        bag.selectPowerUp(setupPowerUps.get(1));
        assertTrue(bag.repOk());

        // Get the number of powerups in the bag
        int numVestPowerUps = bag.getPowerUps().get(setupPowerUps.get(2));
        assertTrue(bag.repOk());
        int numBottlePowerUps = bag.getPowerUps().get(setupPowerUps.get(3));
        assertTrue(bag.repOk());

        // Check if the number of powerups in the bag is correct
        assertEquals(2, numVestPowerUps);
        assertEquals(1, numBottlePowerUps);
    }
}
