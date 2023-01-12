package com.gurup.ui.gamescreen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
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
import com.gurup.domain.powerups.BottlePowerUp;
import com.gurup.domain.powerups.ThrownBottlePowerUp;
import com.gurup.domain.powerups.VestPowerUp;
import com.gurup.domain.room.Room;
import com.gurup.domain.room.buildingobjects.BuildingObject;
import com.gurup.ui.ImageLoader;
import com.gurup.ui.drawer.Drawer;

public class RunningModeScreen extends JPanel {

    private Player player;
    private MovementController movementController;
    private KeyClickController keyClickController;
    private PowerUpController powerUpController;
    private Room room;
    private int delayMiliSeconds;
    private Drawer powerUpDrawer = new Drawer("PowerUp");
    private Drawer buildObjectDrawer = new Drawer("Object");
    private Drawer alienDrawer = new Drawer("Alien");
    FontMetrics metrics;
    Font font;
    Bag bag;

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

        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game.pauseUnpause();
                if (Game.getIsPaused())
                    pauseButton.setText("Resume");
                else {
                    pauseButton.setText("Pause");
                }
            }
        });
        add(pauseButton);
        pauseButton.setFocusable(false);
        new Timer(this.delayMiliSeconds, e -> {
            repaint();
            player.decrementTime(this.delayMiliSeconds);
            room.createPowerUp(this.delayMiliSeconds);
            room.createAlien(this.delayMiliSeconds);
        }).start();
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
        drawBag(g);
        animateBottle(g);
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
        g.fillOval(slotStartX + slotSizeX / 2 - itemSize / 2, slotStartY + slotSizeY / 2 - itemSize / 2, itemSize,
                itemSize);
        g.drawImage(ImageLoader.vest_image, slotStartX + slotSizeX / 2 - itemSize / 2 - slotSizeX,
                slotStartY + slotSizeY / 2 - itemSize / 2, itemSize, itemSize, null);
        // TODO: update bottle image in bag
        g.drawImage(ImageLoader.plastic_bottle_image, slotStartX + slotSizeX / 2 - itemSize / 2 + slotSizeX,
                slotStartY + slotSizeY / 2 - itemSize / 2, itemSize / 2, itemSize, null);
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Courier New", Font.BOLD, fontSize));
        g.drawString("0", slotStartX + slotSizeX - numberOffsetX, slotStartY + numberOffsetY);
        Integer vestCount = bag.getPowerUps().get(VestPowerUp.getInstance(player));
        Integer bottleCount = bag.getPowerUps().get(BottlePowerUp.getInstance(player));
        g.drawString(vestCount.toString(), slotStartX + slotSizeX - numberOffsetX - slotSizeX,
                slotStartY + numberOffsetY);
        g.drawString(bottleCount.toString(), slotStartX + slotSizeX - numberOffsetX + slotSizeX,
                slotStartY + numberOffsetY);
        // TODO get other power up counts from the bag
        setFont(g);
    }

    private void drawObjects(Graphics g) {
        if (room.getCreated() != null && room.getCreated().isActive()) {
            powerUpDrawer.draw(g, room.getCreated().rectArray(), room.getCreated().getName());
        }
        if (room.getCreatedAlien() != null && room.getCreatedAlien().isActive()) {
            alienDrawer.draw(g, room.getCreatedAlien().rectArray(), room.getCreatedAlien().getName());
        }
        for (BuildingObject bo : Room.getObjects()) {
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
        ;
        int lifeX = Room.getstartX() + (Room.getXLimit() - metrics.stringWidth(remainingLife));
        int lifeY = Room.getstartY() - 5;
        g.drawString(remainingTime, timeX, timeY);
        g.drawString(remainingLife, lifeX, lifeY);
    }

    private void paintPlayer(Graphics g) {
        g.drawImage(ImageLoader.player_image, player.getXCurrent(), player.getYCurrent(), player.getXLen(),
                player.getYLen(), null);
    }

    private void paintWall(Graphics g) {
        g.setColor(Color.BLACK);
        g.draw3DRect(Room.getstartX(), Room.getstartY(), Room.getXLimit(), Room.getYLimit(), true);
    }

    private void animateBottle(Graphics g) {
        if (ThrownBottlePowerUp.getInstance(null).isUsed()) {
            powerUpDrawer.draw(g, ThrownBottlePowerUp.getInstance(null).rectArray(),
                    ThrownBottlePowerUp.getInstance(null).getName());
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
}
