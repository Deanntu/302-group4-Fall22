package com.gurup.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Rectangle;
import java.awt.Toolkit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.gurup.domain.Game;
import com.gurup.domain.Player;
import com.gurup.domain.PlayerConstants;
import com.gurup.domain.room.Room;
import com.gurup.domain.room.buildingobjects.BuildingObject;

public class IsKeyFoundTest {

    private Room room;
    private Player player;
    private int mouseWidth, mouseHeight, right_wall_x, bottom_wall_y;

    @BeforeEach
    public void setRoom() {
        // Initialize mouse width and height
        mouseWidth = 1;
        mouseHeight = 1;
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
    public void nearAndHasKey() {
        // Get objects
        BuildingObject object1 = room.getObjects().get(0);
        BuildingObject object2 = room.getObjects().get(1);

        // Case - 1.1: Player is near to object 1
        player.setXCurrent(object1.getXCurrent());
        player.setYCurrent(object1.getYCurrent());
        // Case - 1.2: Player clicks on object 1
        Rectangle object1Area = new Rectangle(object1.getXCurrent(), object1.getYCurrent(), mouseWidth, mouseHeight);
        /*
         * Case - 1.3: Simulate clicking on object 1 while player is near to it, which
         * could return true or false depending on the whether the key is in the object
         * 1 or not
         */
        boolean isKeyFoundInObject1WhileNear = room.isKeyFound(object1Area);

        // Case - 2.1: Player is near to object 2
        player.setXCurrent(object2.getXCurrent());
        player.setYCurrent(object2.getYCurrent());
        // Case - 2.2: Player clicks on object 2
        Rectangle object2Area = new Rectangle(object2.getXCurrent(), object2.getYCurrent(), mouseWidth, mouseHeight);
        /*
         * Case - 2.3: Simulate clicking on object 2 while player is near to it, which
         * could return true or false depending on the whether the key is in the object
         * 2 or not
         */
        boolean isKeyFoundInObject2WhileNear = room.isKeyFound(object2Area);

        // Result: Assert that the key should be found in either object 1 or object 2
        // while player is near to them
        assertTrue(isKeyFoundInObject1WhileNear || isKeyFoundInObject2WhileNear);
    }

    @Test
    public void nearAndHasNotKey() {
        // Get objects
        BuildingObject object1 = room.getObjects().get(0);
        BuildingObject object2 = room.getObjects().get(1);

        // Case - 1.1: Player is near to object 1
        player.setXCurrent(object1.getXCurrent());
        player.setYCurrent(object1.getYCurrent());
        // Case - 1.2: Player clicks on object 1
        Rectangle object1Area = new Rectangle(object1.getXCurrent(), object1.getYCurrent(), mouseWidth, mouseHeight);
        /*
         * Case - 1.3: Simulate clicking on object 1 while player is near to it, which
         * could return true or false depending on the whether the key is in the object
         * 1 or not
         */
        boolean isKeyFoundInObject1WhileNear = room.isKeyFound(object1Area);

        // Case - 2.1: Player is near to object 2
        player.setXCurrent(object2.getXCurrent());
        player.setYCurrent(object2.getYCurrent());
        // Case - 2.2: Player clicks on object 2
        Rectangle object2Area = new Rectangle(object2.getXCurrent(), object2.getYCurrent(), mouseWidth, mouseHeight);
        /*
         * Case - 2.3: Simulate clicking on object 2 while player is near to it, which
         * could return true or false depending on the whether the key is in the object
         * 2 or not
         */
        boolean isKeyFoundInObject2WhileNear = room.isKeyFound(object2Area);
        // We know that either object 1 or object 2 has the key, so can safely say that
        // the other object does not have the key
        // So clicking the other object should return false
        // Result: Assert that the key should not be found in both object 1 and object 2
        // while player is near to them
        assertFalse(isKeyFoundInObject1WhileNear && isKeyFoundInObject2WhileNear);
    }

    @Test
    public void farAndHasKey() {
        // Get objects
        BuildingObject object1 = room.getObjects().get(0);
        BuildingObject object2 = room.getObjects().get(1);

        // Case - 1.1: Player is far from object 1
        player.setXCurrent(object1.getXCurrent() - 100);
        player.setYCurrent(object1.getYCurrent() - 100);
        // Case - 1.2: Player clicks on object 1
        Rectangle object1Area = new Rectangle(object1.getXCurrent(), object1.getYCurrent(), mouseWidth, mouseHeight);
        /*
         * Case - 1.3: Simulate clicking on object 1 while player is far from it, which
         * should return false
         */
        boolean isKeyFoundInObject1WhileFar = room.isKeyFound(object1Area);

        // Case - 2.1: Player is far from object 2
        player.setXCurrent(object2.getXCurrent() - 100);
        player.setYCurrent(object2.getYCurrent() - 100);
        // Case - 2.2: Player clicks on object 2
        Rectangle object2Area = new Rectangle(object2.getXCurrent(), object2.getYCurrent(), mouseWidth, mouseHeight);
        /*
         * Case - 2.3: Simulate clicking on object 2 while player is far from it, which
         * should return false
         */
        boolean isKeyFoundInObject2WhileFar = room.isKeyFound(object2Area);

        // Result: Assert that the key should not be found in either object 1 or object
        // 2 while player is far from them
        assertFalse(isKeyFoundInObject1WhileFar || isKeyFoundInObject2WhileFar);
    }

    @Test
    public void farAndHasNotKey() {
        // Get objects
        BuildingObject object1 = room.getObjects().get(0);
        BuildingObject object2 = room.getObjects().get(1);

        // Case - 1.1: Player is far from object 1
        player.setXCurrent(object1.getXCurrent() - 100);
        player.setYCurrent(object1.getYCurrent() - 100);
        // Case - 1.2: Player clicks on object 1
        Rectangle object1Area = new Rectangle(object1.getXCurrent(), object1.getYCurrent(), mouseWidth, mouseHeight);
        /*
         * Case - 1.3: Simulate clicking on object 1 while player is far from it, which
         * should return false
         */
        boolean isKeyFoundInObject1WhileFar = room.isKeyFound(object1Area);

        // Case - 2.1: Player is far from object 2
        player.setXCurrent(object2.getXCurrent() - 100);
        player.setYCurrent(object2.getYCurrent() - 100);
        // Case - 2.2: Player clicks on object 2
        Rectangle object2Area = new Rectangle(object2.getXCurrent(), object2.getYCurrent(), mouseWidth, mouseHeight);
        /*
         * Case - 2.3: Simulate clicking on object 2 while player is far from it, which
         * should return false
         */
        boolean isKeyFoundInObject2WhileFar = room.isKeyFound(object2Area);

        // Result: Assert that the key should not be found in either object 1 or object
        // 2 while player is far from them
        assertFalse(isKeyFoundInObject1WhileFar && isKeyFoundInObject2WhileFar);
    }

    @Test
    public void clickEmptyArea() {
        // We now that the Rectangle(20, 20, 1, 1) does not intersect with any object.
        Rectangle emptyArea = new Rectangle(20, 20, mouseWidth, mouseHeight);
        // Result: Assert that the key should not be found in empty area
        assertFalse(room.isKeyFound(emptyArea));
    }
}
