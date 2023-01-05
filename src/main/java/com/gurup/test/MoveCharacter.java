package com.gurup.test;

import static org.junit.jupiter.api.Assertions.*;

import com.gurup.domain.Game;
import com.gurup.domain.Player;
import com.gurup.domain.room.Room;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class MoveCharacter {

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
        player = new Player(Color.blue, 10, 10, right_wall_x, bottom_wall_y, 50, 60);
        room = new Room("Student Center", 50, 50, right_wall_x, bottom_wall_y, player);
    }




    @Test
    public void regularMoveTest() {
        int initialX = player.getX();
        int initialY = player.getY();
        player.moveDown();
        assertEquals(initialX, player.getX());
        assertEquals(initialY + 10, player.getY());

        initialX = player.getX();
        initialY = player.getY();

        player.moveRight();
        assertEquals(initialX + 10, player.getX());
        assertEquals(initialY, player.getY());
    }

    @Test
    public void rightWallMoveTest() {
        player.setX(right_wall_x);
        int initialX = player.getX();
        int initialY = player.getY();
        player.moveRight();
        assertEquals(initialX, player.getX());
        assertEquals(initialY, player.getY());
    }

    @Test
    public void leftWallMoveTest() {
        int initialX = player.getX();
        int initialY = player.getY();
        player.moveLeft();
        assertEquals(initialX, player.getX());
        assertEquals(initialY, player.getY());

    }

    @Test
    public void topWallMoveTest() {
        int initialX = player.getX();
        int initialY = player.getY();
        player.moveUp();
        assertEquals(initialX, player.getX());
        assertEquals(initialY, player.getY());

    }

    @Test
    public  void bottomWallMoveTest() {
        player.setY(bottom_wall_y);
        int initialX = player.getX();
        int initialY = player.getY();
        player.moveDown();
        assertEquals(initialX, player.getX());
        assertEquals(initialY, player.getY());

    }
}
