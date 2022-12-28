package com.gurup.testing_ali;

import static org.junit.jupiter.api.Assertions.*;
import com.gurup.domain.Player;
import com.gurup.domain.room.Room;
import com.gurup.domain.room.buildingobjects.BuildingObject;
import org.junit.Test;
import java.awt.*;

public class IsKeyFoundTest {
    @Test
    public void nearAndHasKey() {
        // Initialize mouse width and height
        int mouseWidth = 1;
        int mouseHeight = 1;

        // Create a new room
        int right_wall_x = Toolkit.getDefaultToolkit().getScreenSize().width - 100;
        int bottom_wall_y = Toolkit.getDefaultToolkit().getScreenSize().width - 175;
        Player player = new Player(Color.blue, 10, 10, right_wall_x, bottom_wall_y, 50, 60);
        Room room = new Room("Student Center", 50, 50, right_wall_x, bottom_wall_y, player);
        BuildingObject object1 = room.getObjects().get(0);
        BuildingObject object2 = room.getObjects().get(1);

        // Step - 1.1: Player is near to object 1
        player.setX(object1.getstartX());
        player.setY(object1.getstartY());
        // Step - 1.2: Player clicks on object 1
        Rectangle object1Area = new Rectangle(object1.getstartX(), object1.getstartY(), mouseWidth, mouseHeight);
        /*
         Step - 1.3: Simulate clicking on object 1 while player is near to it,
         which could return true or false depending on the whether the key is in the object 1 or not
        */
        boolean isKeyFoundInObject1WhileNear = room.isKeyFound(object1Area);

        // Step - 2.1: Player is near to object 2
        player.setX(object2.getstartX());
        player.setY(object2.getstartY());
        // Step - 2.2: Player clicks on object 2
        Rectangle object2Area = new Rectangle(object2.getstartX(), object2.getstartY(), mouseWidth, mouseHeight);
        /*
         Step - 2.3: Simulate clicking on object 2 while player is near to it,
         which could return true or false depending on the whether the key is in the object 2 or not
        */
        boolean isKeyFoundInObject2WhileNear = room.isKeyFound(object2Area);

        // Result: Assert that the key should be found in either object 1 or object 2 while player is near to them
        assertTrue(isKeyFoundInObject1WhileNear || isKeyFoundInObject2WhileNear);
    }

    @Test
    public void nearAndHasNotKey() {
        // Initialize mouse width and height
        int mouseWidth = 1;
        int mouseHeight = 1;

        // Create a new room
        int right_wall_x = Toolkit.getDefaultToolkit().getScreenSize().width - 100;
        int bottom_wall_y = Toolkit.getDefaultToolkit().getScreenSize().width - 175;
        Player player = new Player(Color.blue, 10, 10, right_wall_x, bottom_wall_y, 50, 60);
        Room room = new Room("Student Center", 50, 50, right_wall_x, bottom_wall_y, player);
        BuildingObject object1 = room.getObjects().get(0);
        BuildingObject object2 = room.getObjects().get(1);

        // Step - 1.1: Player is near to object 1
        player.setX(object1.getstartX());
        player.setY(object1.getstartY());
        // Step - 1.2: Player clicks on object 1
        Rectangle object1Area = new Rectangle(object1.getstartX(), object1.getstartY(), mouseWidth, mouseHeight);
        /*
         Step - 1.3: Simulate clicking on object 1 while player is near to it,
         which could return true or false depending on the whether the key is in the object 1 or not
        */
        boolean isKeyFoundInObject1WhileNear = room.isKeyFound(object1Area);

        // Step - 2.1: Player is near to object 2
        player.setX(object2.getstartX());
        player.setY(object2.getstartY());
        // Step - 2.2: Player clicks on object 2
        Rectangle object2Area = new Rectangle(object2.getstartX(), object2.getstartY(), mouseWidth, mouseHeight);
        /*
         Step - 2.3: Simulate clicking on object 2 while player is near to it,
         which could return true or false depending on the whether the key is in the object 2 or not
        */
        boolean isKeyFoundInObject2WhileNear = room.isKeyFound(object2Area);
        // We know that either object 1 or object 2 has the key, so can safely say that the other object does not have the key
        // So clicking the other object should return false
        // Result: Assert that the key should not be found in both object 1 and object 2 while player is near to them
        assertFalse(isKeyFoundInObject1WhileNear && isKeyFoundInObject2WhileNear);
    }

    @Test
    public void farAndHasKey(){
        // Initialize mouse width and height
        int mouseWidth = 1;
        int mouseHeight = 1;

        // Create a new room
        int right_wall_x = Toolkit.getDefaultToolkit().getScreenSize().width - 100;
        int bottom_wall_y = Toolkit.getDefaultToolkit().getScreenSize().width - 175;
        Player player = new Player(Color.blue, 10, 10, right_wall_x, bottom_wall_y, 50, 60);
        Room room = new Room("Student Center", 50, 50, right_wall_x, bottom_wall_y, player);
        BuildingObject object1 = room.getObjects().get(0);
        BuildingObject object2 = room.getObjects().get(1);

        // Step - 1.1: Player is far from object 1
        player.setX(object1.getstartX() - 100);
        player.setY(object1.getstartY() - 100);
        // Step - 1.2: Player clicks on object 1
        Rectangle object1Area = new Rectangle(object1.getstartX(), object1.getstartY(), mouseWidth, mouseHeight);
        /*
         Step - 1.3: Simulate clicking on object 1 while player is far from it,
         which should return false
        */
        boolean isKeyFoundInObject1WhileFar = room.isKeyFound(object1Area);

        // Step - 2.1: Player is far from object 2
        player.setX(object2.getstartX() - 100);
        player.setY(object2.getstartY() - 100);
        // Step - 2.2: Player clicks on object 2
        Rectangle object2Area = new Rectangle(object2.getstartX(), object2.getstartY(), mouseWidth, mouseHeight);
        /*
         Step - 2.3: Simulate clicking on object 2 while player is far from it,
         which should return false
        */
        boolean isKeyFoundInObject2WhileFar = room.isKeyFound(object2Area);

        // Result: Assert that the key should not be found in either object 1 or object 2 while player is far from them
        assertFalse(isKeyFoundInObject1WhileFar || isKeyFoundInObject2WhileFar);


    }

    @Test
    public void farAndHasNotKey(){
        // Initialize mouse width and height
        int mouseWidth = 1;
        int mouseHeight = 1;

        // Create a new room
        int right_wall_x = Toolkit.getDefaultToolkit().getScreenSize().width - 100;
        int bottom_wall_y = Toolkit.getDefaultToolkit().getScreenSize().width - 175;
        Player player = new Player(Color.blue, 10, 10, right_wall_x, bottom_wall_y, 50, 60);
        Room room = new Room("Student Center", 50, 50, right_wall_x, bottom_wall_y, player);
        BuildingObject object1 = room.getObjects().get(0);
        BuildingObject object2 = room.getObjects().get(1);

        // Case - 1.1: Player is far from object 1
        player.setX(object1.getstartX() - 100);
        player.setY(object1.getstartY() - 100);
        // Case - 1.2: Player clicks on object 1
        Rectangle object1Area = new Rectangle(object1.getstartX(), object1.getstartY(), mouseWidth, mouseHeight);
        /*
         Case - 1.3: Simulate clicking on object 1 while player is far from it,
         which should return false
        */
        boolean isKeyFoundInObject1WhileFar = room.isKeyFound(object1Area);

        // Case - 2.1: Player is far from object 2
        player.setX(object2.getstartX() - 100);
        player.setY(object2.getstartY() - 100);
        // Case - 2.2: Player clicks on object 2
        Rectangle object2Area = new Rectangle(object2.getstartX(), object2.getstartY(), mouseWidth, mouseHeight);
        /*
         Case - 2.3: Simulate clicking on object 2 while player is far from it,
         which should return false
        */
        boolean isKeyFoundInObject2WhileFar = room.isKeyFound(object2Area);

        // Result: Assert that the key should not be found in either object 1 or object 2 while player is far from them
        assertFalse(isKeyFoundInObject1WhileFar && isKeyFoundInObject2WhileFar);
    }
    @Test
    public void clickEmptyArea() {
        // Initialize mouse width and height
        int mouseWidth = 1;
        int mouseHeight = 1;
        // Create a new room
        int right_wall_x = Toolkit.getDefaultToolkit().getScreenSize().width - 100;
        int bottom_wall_y = Toolkit.getDefaultToolkit().getScreenSize().width - 175;
        Player player = new Player(Color.blue, 10, 10, right_wall_x, bottom_wall_y, 50, 60);
        Room room = new Room("Student Center", 50, 50, right_wall_x, bottom_wall_y, player);
        // We now that the Rectangle(20, 20, 1, 1) does not intersect with any object.
        Rectangle emptyArea = new Rectangle(20, 20, mouseWidth, mouseHeight);
        // Simulate clicking on empty area, which should return false
        assertFalse(room.isKeyFound(emptyArea));
    }
}
