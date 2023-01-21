package com.gurup.ui.gamescreen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.gurup.controller.KeyClickController;
import com.gurup.controller.MovementController;
import com.gurup.controller.PowerUpController;
import com.gurup.domain.Bag;
import com.gurup.domain.Game;
import com.gurup.domain.Player;
import com.gurup.domain.aliens.Alien;
import com.gurup.domain.aliens.BlindAlien;
import com.gurup.domain.powerups.BottlePowerUp;
import com.gurup.domain.powerups.HintPowerUp;
import com.gurup.domain.powerups.ThrownBottlePowerUp;
import com.gurup.domain.powerups.VestPowerUp;
import com.gurup.domain.room.Key;
import com.gurup.domain.room.Room;
import com.gurup.domain.room.RoomConstants;
import com.gurup.domain.room.buildingobjects.BuildingObject;
import com.gurup.ui.ImageLoader;
import com.gurup.ui.drawer.Drawer;

public class RunningModeScreen extends JPanel {

    private Player player;
    private ThrownBottlePowerUp thrownBottlePowerUp = ThrownBottlePowerUp.getInstance(player);
    private MovementController movementController;
    private KeyClickController keyClickController;
    private PowerUpController powerUpController;
    private final Room room;
    private final int delayMiliSeconds;
    private final Drawer powerUpDrawer = new Drawer("PowerUp");
    private final Drawer buildObjectDrawer = new Drawer("Object");
    private final Drawer alienDrawer = new Drawer("Alien");
    private final int magicBagNumber = 5; // this is needed to make the bottle in the bag appear in correct position
    FontMetrics metrics;
    Font font;
    final Bag bag;
    private Timer timer;


    public RunningModeScreen(Game game, Player player, MovementController movementController,
                             KeyClickController keyClickController, PowerUpController powerUpController, Room room) {

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        this.player = player;
        this.room = room;
        this.bag = Game.getBag();
        this.setMovementController(movementController);
        this.setKeyClickController(keyClickController);
        this.setPowerUpController(powerUpController);
        this.delayMiliSeconds = 20;

        JButton pauseButton = new JButton("Pause");

        pauseButton.addActionListener(e -> {
            Game.pauseUnpause();
            if (Game.getIsPaused())
                pauseButton.setText("Resume");
            else {
                pauseButton.setText("Pause");
            }
        });
        add(pauseButton);
        pauseButton.setFocusable(false);
        add(pauseButton);
        pauseButton.setFocusable(false);
        JButton saveButton = new JButton("Save");

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game.saveGame();
            }
        });
        add(saveButton);
        saveButton.setFocusable(false);
        timer = new Timer(this.delayMiliSeconds, e -> {
            repaint();
            player.decrementTime(this.delayMiliSeconds);
            room.createPowerUp(this.delayMiliSeconds);
            room.createAlien(this.delayMiliSeconds);
        });
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // player.move();
        setFont(g);
        paintRoomName(g);
        paintLifeAndTime(g);
        paintPlayer(g);
        drawObjects(g);
        paintWall(g);
        paintDoor(g);
        drawKey(g);
        drawBag(g);
        animateBottle(g);
    }

    private void drawKey(Graphics g) {

        Rectangle keyRect = Key.getKeyRectangle();

        // TODO - Remove this later
        g.drawRect(keyRect.x - 5, keyRect.y - 5, keyRect.width + 10, keyRect.height + 10);
        // End of remove

        if (player.getHintStatus() == true) {
            g.setColor(Color.RED);
            int xRandom = player.getHintXRandom();
            int yRandom = player.getHintYRandom();
            g.drawRect(keyRect.x - 5 - xRandom, keyRect.y - 5 - yRandom, keyRect.width + 110, keyRect.height + 110);
            g.setColor(Color.BLACK);
        }


    }

    public Boolean isPlayerPassNextLevel() {
        Rectangle doorRectangle = new Rectangle(RoomConstants.doorXStart.getValue(), RoomConstants.doorYStart.getValue(), RoomConstants.doorXLen.getValue(), RoomConstants.doorYLen.getValue());
        if (player.getRectangle().intersects(doorRectangle)) {
            if (player.getRemainingTime() > 0 && player.getLife() > 0) {
                if (Room.getIsPlayerFoundKeyForRoom() == true) {
                    return true;
                }
            }
        }
        return false;
    }

    public Boolean isPlayerDeadOrTimeIsOver() {
        if (player.getRemainingTime() <= 0 || player.getLife() <= 0) {
            return true;
        }
        return false;
    }

    private void paintDoor(Graphics g) {
        if (Room.getIsPlayerFoundKeyForRoom() == false || player.getIsKeyFound() == false) {
            g.drawImage(ImageLoader.closed_door_image, RoomConstants.doorXStart.getValue(), RoomConstants.doorYStart.getValue(), RoomConstants.doorXLen.getValue(), RoomConstants.doorYLen.getValue(), null);
        } else if (Room.getIsPlayerFoundKeyForRoom() == true || player.getIsKeyFound()) {
            g.drawImage(ImageLoader.open_door_image, RoomConstants.doorXStart.getValue(), RoomConstants.doorYStart.getValue(), RoomConstants.doorXLen.getValue(), RoomConstants.doorYLen.getValue(), null);
        }
    }

    private void drawBag(Graphics g) {
        int slotSizeX = 140;
        int slotSizeY = 45;
        int itemSize = 30;
        int slotStartX = Room.getstartX() + Room.getXLimit() / 2 - slotSizeX / 2;
        int slotStartY = Room.getstartY() + Room.getYLimit();
        int fontSize = 14;
        int numberOffsetX = 25;
        int numberOffsetY = 14;
        g.setColor(Color.BLACK);
        g.draw3DRect(slotStartX, slotStartY, slotSizeX, slotSizeY, true);
        g.draw3DRect(slotStartX - slotSizeX, slotStartY, slotSizeX, slotSizeY, true);
        g.draw3DRect(slotStartX + slotSizeX, slotStartY, slotSizeX, slotSizeY, true);
        g.setColor(Color.PINK);
        g.drawImage(ImageLoader.hint_image, slotStartX + slotSizeX / 2 - itemSize / 2, slotStartY + slotSizeY / 2 - itemSize / 2, itemSize,
                itemSize, null);
        g.drawImage(ImageLoader.vest_image, slotStartX + slotSizeX / 2 - itemSize / 2 - slotSizeX,
                slotStartY + slotSizeY / 2 - itemSize / 2, itemSize, itemSize, null);
        // TODO: update bottle image in bag
        g.drawImage(ImageLoader.plastic_bottle_image, slotStartX + slotSizeX / 2 - itemSize / 2 + slotSizeX + magicBagNumber,
                slotStartY + slotSizeY / 2 - itemSize / 2, itemSize / 2, itemSize, null);
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Courier New", Font.BOLD, fontSize));
        Integer vestCount = bag.getPowerUps().get(VestPowerUp.getInstance(player));
        Integer bottleCount = bag.getPowerUps().get(BottlePowerUp.getInstance(player));
        Integer hintCount = bag.getPowerUps().get(HintPowerUp.getInstance(player));


        g.drawString(vestCount.toString(), slotStartX + slotSizeX - numberOffsetX - slotSizeX,
                slotStartY + numberOffsetY);

        g.drawString(hintCount.toString(), slotStartX + slotSizeX - numberOffsetX,
                slotStartY + numberOffsetY);

        g.drawString(bottleCount.toString(), slotStartX + slotSizeX - numberOffsetX + slotSizeX,
                slotStartY + numberOffsetY);
        // TODO get other power up counts from the bag
        setFont(g);
    }

    private void drawObjects(Graphics g) {
        Rectangle keyRect = Key.getKeyRectangle();

        if (room.getCreated() != null && room.getCreated().isActive()) {
            powerUpDrawer.draw(g, room.getCreated().rectArray(), room.getCreated().getName());
        }
        for (Alien a : room.getCreatedAliens()) {
            if (a != null && a.isActive()) {

                alienDrawer.draw(g, a.rectArray(), a.getName());

                if (a.getName().equals("Blind") && Game.getIsPaused() == false) {

                    if (a.getRectangle().intersects(player.getRectangle())) {
                        player.setLife(player.getLife() - 1);
                        a.setActive(false);
                    }

                    if (thrownBottlePowerUp.isUsed()) {
                        ((BlindAlien) a).moveToBottle();
                    } else {

                    ((BlindAlien) a).randomMove();

                    }
                }

            }
        }
        for (BuildingObject bo : Room.getObjects()) {
            if (keyRect.intersects(bo.getRectangle()) && player.getDrawKeyStatus() == true) {
                g.drawImage(ImageLoader.key_image, bo.getXCurrent() + bo.getXLen() / 2 - RoomConstants.keyXLen.getValue() / 2, bo.getYCurrent() + bo.getYLen() / 2 - RoomConstants.keyYLen.getValue() / 2, RoomConstants.keyXLen.getValue(), RoomConstants.keyYLen.getValue(), this);
                continue;
            }
            buildObjectDrawer.draw(g, bo.rectArray(), bo.getName());
        }


    }

    private void setFont(Graphics g) {
        font = new Font("Courier New", Font.BOLD, 20);
        metrics = g.getFontMetrics(font);
    }

    private void paintRoomName(Graphics g) {
        g.setColor(Color.BLACK);
        int x = Room.getstartX() + (Room.getXLimit() - metrics.stringWidth(room.getName())) / 2;
        int y = Room.getstartY() - 5;
        g.setFont(font);
        g.drawString(room.getName(), x, y);
    }

    private void paintLifeAndTime(Graphics g) {
        String remainingTime = "Remaining time: " + player.getRemainingTime();
        String remainingLife = "Remaining life: " + player.getLife();
        int timeX = Room.getstartX();
        int timeY = Room.getstartY() - 5;
        int lifeX = Room.getstartX() + (Room.getXLimit() - metrics.stringWidth(remainingLife));
        int lifeY = Room.getstartY() - 5;
        g.drawString(remainingTime, timeX, timeY);
        g.drawString(remainingLife, lifeX, lifeY);
    }

    private void paintPlayer(Graphics g) {
        g.drawImage(ImageLoader.player_image, player.getXCurrent(), player.getYCurrent(), player.getXLen(), player.getYLen(), null);

    }

    private void paintWall(Graphics g) {
        g.setColor(Color.BLACK);
        g.draw3DRect(Room.getstartX(), Room.getstartY(), Room.getXLimit(), Room.getYLimit(), true);
    }

    private void animateBottle(Graphics g) {
        if (ThrownBottlePowerUp.getInstance(Game.getPlayer()).isUsed()) {
            powerUpDrawer.draw(g, ThrownBottlePowerUp.getInstance(Game.getPlayer()).rectArray(),
                    ThrownBottlePowerUp.getInstance(Game.getPlayer()).getName());
        }
    }

    public Dimension getPreferredSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public MovementController getMovementController() {
        return movementController;
    }

    public void setMovementController(MovementController movementController) {
        this.movementController = movementController;
    }

    public KeyClickController getKeyClickController() {
        return keyClickController;
    }

    public void setKeyClickController(KeyClickController keyClickController) {
        this.keyClickController = keyClickController;
    }

    public PowerUpController getPowerUpController() {
        return powerUpController;
    }

    public void setPowerUpController(PowerUpController powerUpController) {
        this.powerUpController = powerUpController;
    }

    public Timer getTimer() {
        return timer;
    }
}
