package com.gurup.ui.gamescreen;

import com.gurup.controller.BuildingModeKeyClickController;

import com.gurup.domain.Game;
import com.gurup.domain.Player;
import com.gurup.domain.buildingmode.BuildingModeRoom;
import com.gurup.domain.room.Room;
import com.gurup.ui.ImageLoader;
import com.gurup.ui.drawer.Drawer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuildingModeScreen extends JPanel {
    private Player player;
    private BuildingModeKeyClickController buildingModeKeyClickController;
    private BuildingModeRoom buildingModeRoom;
    private int delayMiliSeconds;
    private Drawer buildObjectDrawer = new Drawer("Object");
    FontMetrics metrics;
    Font font;

    public BuildingModeScreen(Game game, Player player, BuildingModeKeyClickController buildingModeKeyClickController, BuildingModeRoom buildingModeRoom) {
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        this.player = player;
        this.buildingModeRoom = buildingModeRoom;
        this.buildingModeKeyClickController = buildingModeKeyClickController;
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
        }).start();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setFont(g);
        paintRoomName(g);
        paintPlayer(g);
        paintWall(g);
    }

    private void setFont(Graphics g) {
        font = new Font("Courier New", Font.BOLD, 20);
        metrics = g.getFontMetrics(font);
    }

    private void paintRoomName(Graphics g) {
        g.setColor(Color.BLACK);
        int x = Room.getstartX() + (Room.getXLimit() - metrics.stringWidth(buildingModeRoom.getName())) / 2;
        int y = Room.getstartY() - 5;
        g.setFont(font);
        g.drawString(buildingModeRoom.getName(), x, y);
    }

    private void paintPlayer(Graphics g) {
        g.drawImage(ImageLoader.player_image, player.getXCurrent(), player.getYCurrent(), player.getXLen(), player.getYLen(), null);
    }

    private void paintWall(Graphics g) {
        g.setColor(Color.BLACK);
        g.draw3DRect(BuildingModeRoom.getXStart(), BuildingModeRoom.getYStart(), BuildingModeRoom.getXLimit(), BuildingModeRoom.getYLimit(), true);
    }

    public Dimension getPreferredSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }



    public BuildingModeKeyClickController getKeyClickController() {
        return buildingModeKeyClickController;
    }

    public void setBuildingModeKeyClickController(BuildingModeKeyClickController buildingModeKeyClickController) {
        this.buildingModeKeyClickController = buildingModeKeyClickController;
    }


}
