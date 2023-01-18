package com.gurup.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gurup.domain.Player;
import com.gurup.domain.PlayerConstants;
import com.gurup.domain.powerups.ThrownBottlePowerUp;
import com.gurup.domain.room.Room;
import com.gurup.domain.room.RoomConstants;

public class BottleUsePowerUpTest {

    /*
     * what to test
     *
     * full range behavior when thrown to the left wall, when close and far
     * behavior when thrown to the right wall, when close and far behavior when
     * thrown to the top wall, when close and far behavior when thrown to the bottom
     * wall, when close and far behavior when not usable
     */

    private Player player;
    private ThrownBottlePowerUp bottle;
    @SuppressWarnings("unused")
    private Room room; // Room needs to be initialized for the PowerUp to function properly

    @Before
    public void runsBeforeEachTest() {
        // initializes a player and a bottle
        player = new Player(PlayerConstants.xStart.getValue(), PlayerConstants.yStart.getValue(),
                PlayerConstants.xLen.getValue(), PlayerConstants.yLen.getValue(), 60);
        player.setXCurrent(RoomConstants.xLimit.getValue() / 2);
        player.setYCurrent(RoomConstants.yLimit.getValue() / 2);
        room = new Room("Student Center", RoomConstants.xStart.getValue(), RoomConstants.yStart.getValue(), RoomConstants.xLimit.getValue(),
                RoomConstants.yLimit.getValue(), player);
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
        assertTrue(bottle.getXCurrent() == player.getXCurrent() && bottle.getYCurrent() == player.getYCurrent() - 100);
        // Case - 1.2: use towards the bottom
        bottle.setUsable(true);
        bottle.usePowerUp("down");
        assertTrue(bottle.getXCurrent() == player.getXCurrent() && bottle.getYCurrent() == player.getYCurrent() + 100);
        // Case - 1.3: use towards the left
        bottle.setUsable(true);
        bottle.usePowerUp("left");
        assertTrue(bottle.getXCurrent() == player.getXCurrent() - 100 && bottle.getYCurrent() == player.getYCurrent());
        // Case - 1.4: use towards the right
        bottle.setUsable(true);
        bottle.usePowerUp("right");
        assertTrue(bottle.getXCurrent() == player.getXCurrent() + 100 && bottle.getYCurrent() == player.getYCurrent());
    }

    @Test
    public void nearLeftWall() {
        // The bottle interacts with the left wall in these cases
        // iterate over positions close to the left wall in order to miss no cases
        for (int i = RoomConstants.xStart.getValue(); i < RoomConstants.xStart.getValue() + 100; i++) {
            player.setXCurrent(i);
            ThrownBottlePowerUp bottle = ThrownBottlePowerUp.getInstance(player);
            // Case 2.1: use towards the top when near left wall
            bottle.setUsable(true);
            bottle.usePowerUp("up");
            assertTrue(bottle.getXCurrent() == player.getXCurrent() && bottle.getYCurrent() == player.getYCurrent() - 100);
            // Case 2.2: use towards the bottom when near left wall
            bottle.setUsable(true);
            bottle.usePowerUp("down");
            assertTrue(bottle.getXCurrent() == player.getXCurrent() && bottle.getYCurrent() == player.getYCurrent() + 100);
            // Case 2.3: use towards the left when near left wall, should hit the wall
            bottle.setUsable(true);
            bottle.usePowerUp("left");
            assertTrue(bottle.getXCurrent() == RoomConstants.xStart.getValue() && bottle.getYCurrent() == player.getYCurrent());
            // Case 2.4: use towards the right when near left wall
            bottle.setUsable(true);
            bottle.usePowerUp("right");
            assertTrue(bottle.getXCurrent() == player.getXCurrent() + 100 && bottle.getYCurrent() == player.getYCurrent());
            ThrownBottlePowerUp.setNull();
        }
    }

    @Test
    public void notSoNearLeftWall() {
        // The bottle should not interact with the left wall in these cases
        // iterate over positions not-so-close to the left wall in order to miss no
        // cases
        for (int i = RoomConstants.xStart.getValue() + 150; i < RoomConstants.xStart.getValue() + 200; i++) {
            player.setXCurrent(i);
            ThrownBottlePowerUp bottle = ThrownBottlePowerUp.getInstance(player);
            // Case 3.1: use towards the top when not-so-near left wall
            bottle.setUsable(true);
            bottle.usePowerUp("up");
            assertTrue(bottle.getXCurrent() == player.getXCurrent() && bottle.getYCurrent() == player.getYCurrent() - 100);
            // Case 3.2: use towards the bottom when not-so-near left wall
            bottle.setUsable(true);
            bottle.usePowerUp("down");
            assertTrue(bottle.getXCurrent() == player.getXCurrent() && bottle.getYCurrent() == player.getYCurrent() + 100);
            // Case 3.3: use towards the left when not-so-near left wall, should not hit the
            // wall
            bottle.setUsable(true);
            bottle.usePowerUp("left");
            assertTrue(bottle.getXCurrent() == player.getXCurrent() - 100 && bottle.getYCurrent() == player.getYCurrent());
            // Case 3.4: use towards the right when not-so-near left wall
            bottle.setUsable(true);
            bottle.usePowerUp("right");
            assertTrue(bottle.getXCurrent() == player.getXCurrent() + 100 && bottle.getYCurrent() == player.getYCurrent());
            ThrownBottlePowerUp.setNull();
        }
    }

    @Test
    public void nearRightWall() {
        // The bottle interacts with the right wall in these cases
        // iterate over positions close to the right wall in order to miss no cases
        for (int i = RoomConstants.xLimit.getValue(); i > RoomConstants.xLimit.getValue() - 100; i--) {
            player.setXCurrent(i);
            ThrownBottlePowerUp bottle = ThrownBottlePowerUp.getInstance(player);
            // Case 4.1: use towards the top when near right wall
            bottle.setUsable(true);
            bottle.usePowerUp("up");
            assertTrue(bottle.getXCurrent() == player.getXCurrent() && bottle.getYCurrent() == player.getYCurrent() - 100);
            // Case 4.2: use towards the bottom when near right wall
            bottle.setUsable(true);
            bottle.usePowerUp("down");
            assertTrue(bottle.getXCurrent() == player.getXCurrent() && bottle.getYCurrent() == player.getYCurrent() + 100);
            // Case 4.3: use towards the left when near right wall
            bottle.setUsable(true);
            bottle.usePowerUp("left");
            assertTrue(bottle.getXCurrent() == player.getXCurrent() - 100 && bottle.getYCurrent() == player.getYCurrent());
            // Case 4.5: use towards the right when near right wall, should hit the wall
            bottle.setUsable(true);
            bottle.usePowerUp("right");
            assertTrue(bottle.getXCurrent() == RoomConstants.xLimit.getValue() && bottle.getYCurrent() == player.getYCurrent());
            ThrownBottlePowerUp.setNull();
        }
    }

    @Test
    public void notSoNearRightWall() {
        // The bottle should not interact with the right wall in these cases
        // iterate over positions not-so-close to the right wall in order to miss no
        // cases
        for (int i = RoomConstants.xLimit.getValue() - 150; i > RoomConstants.xLimit.getValue() - 200; i--) {
            player.setXCurrent(i);
            ThrownBottlePowerUp bottle = ThrownBottlePowerUp.getInstance(player);
            // Case 5.1: use towards the top when not-so-near right wall
            bottle.setUsable(true);
            bottle.usePowerUp("up");
            assertTrue(bottle.getXCurrent() == player.getXCurrent() && bottle.getYCurrent() == player.getYCurrent() - 100);
            // Case 5.2: use towards the bottom when not-so-near right wall
            bottle.setUsable(true);
            bottle.usePowerUp("down");
            assertTrue(bottle.getXCurrent() == player.getXCurrent() && bottle.getYCurrent() == player.getYCurrent() + 100);
            // Case 5.3: use towards the left when not-so-near right wall
            bottle.setUsable(true);
            bottle.usePowerUp("left");
            assertTrue(bottle.getXCurrent() == player.getXCurrent() - 100 && bottle.getYCurrent() == player.getYCurrent());
            // Case 5.4: use towards the right when not-so-near right wall, should not hit
            // the wall
            bottle.setUsable(true);
            bottle.usePowerUp("right");
            assertTrue(bottle.getXCurrent() == player.getXCurrent() + 100 && bottle.getYCurrent() == player.getYCurrent());
            ThrownBottlePowerUp.setNull();
        }
    }

    @Test
    public void nearTopWall() {
        // The bottle interacts with the top wall in these cases
        // iterate over positions close to the top wall in order to miss no cases
        for (int i = RoomConstants.yStart.getValue(); i < RoomConstants.yStart.getValue() + 100; i++) {
            player.setYCurrent(i);
            ThrownBottlePowerUp bottle = ThrownBottlePowerUp.getInstance(player);
            // Case 6.1: use towards the top when near top wall, should hit the wall
            bottle.setUsable(true);
            bottle.usePowerUp("up");
            assertTrue(bottle.getXCurrent() == player.getXCurrent() && bottle.getYCurrent() == RoomConstants.yStart.getValue());
            // Case 6.2: use towards the bottom when near top wall
            bottle.setUsable(true);
            bottle.usePowerUp("down");
            assertTrue(bottle.getXCurrent() == player.getXCurrent() && bottle.getYCurrent() == player.getYCurrent() + 100);
            // Case 6.3: use towards the left when near top wall
            bottle.setUsable(true);
            bottle.usePowerUp("left");
            assertTrue(bottle.getXCurrent() == player.getXCurrent() - 100 && bottle.getYCurrent() == player.getYCurrent());
            // Case 6.4: use towards the right when near top wall
            bottle.setUsable(true);
            bottle.usePowerUp("right");
            assertTrue(bottle.getXCurrent() == player.getXCurrent() + 100 && bottle.getYCurrent() == player.getYCurrent());
            ThrownBottlePowerUp.setNull();
        }
    }

    @Test
    public void notSoNearTopWall() {
        // The bottle should not interact with the top wall in these cases
        // iterate over positions not-so-close to the top wall in order to miss no cases
        for (int i = RoomConstants.yStart.getValue() + 150; i < RoomConstants.yStart.getValue() + 200; i++) {
            player.setYCurrent(i);
            ThrownBottlePowerUp bottle = ThrownBottlePowerUp.getInstance(player);
            // Case 7.1: use towards the top when not-so-near top wall, should not hit the
            // wall
            bottle.setUsable(true);
            bottle.usePowerUp("up");
            assertTrue(bottle.getXCurrent() == player.getXCurrent() && bottle.getYCurrent() == player.getYCurrent() - 100);
            // Case 7.2: use towards the bottom when not-so-near top wall
            bottle.setUsable(true);
            bottle.usePowerUp("down");
            assertTrue(bottle.getXCurrent() == player.getXCurrent() && bottle.getYCurrent() == player.getYCurrent() + 100);
            // Case 7.3: use towards the left when not-so-near top wall
            bottle.setUsable(true);
            bottle.usePowerUp("left");
            assertTrue(bottle.getXCurrent() == player.getXCurrent() - 100 && bottle.getYCurrent() == player.getYCurrent());
            // Case 7.4: use towards the right when not-so-near top wall
            bottle.setUsable(true);
            bottle.usePowerUp("right");
            assertTrue(bottle.getXCurrent() == player.getXCurrent() + 100 && bottle.getYCurrent() == player.getYCurrent());
            ThrownBottlePowerUp.setNull();
        }
    }

    @Test
    public void nearBottomWall() {
        // The bottle interacts with the bottom wall in these cases
        // iterate over positions close to the bottom wall in order to miss no cases
        for (int i = RoomConstants.yLimit.getValue(); i > RoomConstants.yLimit.getValue() - 100; i--) {
            player.setYCurrent(i);
            ThrownBottlePowerUp bottle = ThrownBottlePowerUp.getInstance(player);
            // Case 8.1: use towards the top when near bottom wall
            bottle.setUsable(true);
            bottle.usePowerUp("up");
            assertTrue(bottle.getXCurrent() == player.getXCurrent() && bottle.getYCurrent() == player.getYCurrent() - 100);
            // Case 8.2: use towards the bottom when near bottom wall, should hit the wall
            bottle.setUsable(true);
            bottle.usePowerUp("down");
            assertTrue(bottle.getXCurrent() == player.getXCurrent() && bottle.getYCurrent() == RoomConstants.yLimit.getValue());
            // Case 8.3: use towards the left when near bottom wall
            bottle.setUsable(true);
            bottle.usePowerUp("left");
            assertTrue(bottle.getXCurrent() == player.getXCurrent() - 100 && bottle.getYCurrent() == player.getYCurrent());
            // Case 8.4: use towards the right when near bottom wall
            bottle.setUsable(true);
            bottle.usePowerUp("right");
            assertTrue(bottle.getXCurrent() == player.getXCurrent() + 100 && bottle.getYCurrent() == player.getYCurrent());
            ThrownBottlePowerUp.setNull();
        }
    }

    @Test
    public void notSoNearBottomWall() {
        for (int i = RoomConstants.yLimit.getValue() - 150; i > RoomConstants.yLimit.getValue() - 200; i--) {
            player.setYCurrent(i);
            ThrownBottlePowerUp bottle = ThrownBottlePowerUp.getInstance(player);
            // Case 9.1: use towards the top when not-so-near bottom wall
            bottle.setUsable(true);
            bottle.usePowerUp("up");
            assertTrue(bottle.getXCurrent() == player.getXCurrent() && bottle.getYCurrent() == player.getYCurrent() - 100);
            // Case 9.2: use towards the bottom when not-so-near bottom wall, should not hit
            // the wall
            bottle.setUsable(true);
            bottle.usePowerUp("down");
            assertTrue(bottle.getXCurrent() == player.getXCurrent() && bottle.getYCurrent() == player.getYCurrent() + 100);
            // Case 9.3: use towards the left when not-so-near bottom wall
            bottle.setUsable(true);
            bottle.usePowerUp("left");
            assertTrue(bottle.getXCurrent() == player.getXCurrent() - 100 && bottle.getYCurrent() == player.getYCurrent());
            // Case 9.4: use towards the right when not-so-near bottom wall
            bottle.setUsable(true);
            bottle.usePowerUp("right");
            assertTrue(bottle.getXCurrent() == player.getXCurrent() + 100 && bottle.getYCurrent() == player.getYCurrent());
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
        assertTrue(bottle.getXCurrent() == player.getXCurrent() && bottle.getYCurrent() == player.getYCurrent() - 100);

        // Case 10.2.1-4 then when you try to use it when you cannot, the position
        // cannot change
        bottle.setUsable(false);
        bottle.usePowerUp("up");
        assertTrue(bottle.getXCurrent() == player.getXCurrent() && bottle.getYCurrent() == player.getYCurrent() - 100);
        bottle.setUsable(false);
        bottle.usePowerUp("down");
        assertTrue(bottle.getXCurrent() == player.getXCurrent() && bottle.getYCurrent() == player.getYCurrent() - 100);
        bottle.setUsable(false);
        bottle.usePowerUp("left");
        assertTrue(bottle.getXCurrent() == player.getXCurrent() && bottle.getYCurrent() == player.getYCurrent() - 100);
        bottle.setUsable(false);
        bottle.usePowerUp("right");
        assertTrue(bottle.getXCurrent() == player.getXCurrent() && bottle.getYCurrent() == player.getYCurrent() - 100);
    }
}
