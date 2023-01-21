package com.gurup.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Toolkit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.gurup.domain.Game;
import com.gurup.domain.Player;
import com.gurup.domain.PlayerConstants;
import com.gurup.domain.room.Room;

public class MoveCharacterTest {

    private Room room;
    private Player player;
    private int right_wall_x, bottom_wall_y;

    @BeforeEach
    void setUp() {
        // Create a new room and a new player
        right_wall_x = Toolkit.getDefaultToolkit().getScreenSize().width - 100;
        bottom_wall_y = Toolkit.getDefaultToolkit().getScreenSize().width - 175;
        Game.getInstance();
        Game.setIsPaused(false);
        player = new Player(PlayerConstants.xStart.getValue(), PlayerConstants.yStart.getValue(),
                PlayerConstants.xLen.getValue(), PlayerConstants.xLen.getValue(), 60);
        room = new Room("Student Center", 50, 50, right_wall_x, bottom_wall_y, player);
    }

    @Test
    public void regularMoveTest() {
        int initialX = player.getXCurrent();
        int initialY = player.getYCurrent();
        player.moveDown();
        assertEquals(initialX, player.getXCurrent());
        assertEquals(initialY + 10, player.getYCurrent());

        initialX = player.getXCurrent();
        initialY = player.getYCurrent();

        player.moveRight();
        assertEquals(initialX + 10, player.getXCurrent());
        assertEquals(initialY, player.getYCurrent());
    }

    @Test
    public void rightWallMoveTest() {
        player.setXCurrent(right_wall_x);
        int initialX = player.getXCurrent();
        int initialY = player.getYCurrent();
        player.moveRight();
        assertEquals(initialX, player.getXCurrent());
        assertEquals(initialY, player.getYCurrent());
    }

    @Test
    public void leftWallMoveTest() {
        int initialX = player.getXCurrent();
        int initialY = player.getYCurrent();
        player.moveLeft();
        assertEquals(initialX, player.getXCurrent());
        assertEquals(initialY, player.getYCurrent());

    }

    @Test
    public void topWallMoveTest() {
        int initialX = player.getXCurrent();
        int initialY = player.getYCurrent();
        player.moveUp();
        assertEquals(initialX, player.getXCurrent());
        assertEquals(initialY, player.getYCurrent());

    }

    @Test
    public void bottomWallMoveTest() {
        player.setYCurrent(bottom_wall_y);
        int initialX = player.getXCurrent();
        int initialY = player.getYCurrent();
        player.moveDown();
        assertEquals(initialX, player.getXCurrent());
        assertEquals(initialY, player.getYCurrent());

    }
}
