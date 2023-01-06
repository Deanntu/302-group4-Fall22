package com.gurup.ui.gamescreen;

import com.gurup.controller.BuildingModeKeyClickController;

import com.gurup.domain.Game;
import com.gurup.domain.Player;
import com.gurup.domain.buildingmode.BuildingModeRoom;
import com.gurup.domain.room.RoomConstants;
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
    JButton tableButton = new JButton(new ImageIcon(ImageLoader.table_image.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH)));
    JButton binButton = new JButton(new ImageIcon(ImageLoader.bin_image.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH)));


    public BuildingModeScreen(Game game, Player player, BuildingModeKeyClickController buildingModeKeyClickController, BuildingModeRoom buildingModeRoom) {
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        this.player = player;
        this.buildingModeRoom = buildingModeRoom;
        this.buildingModeKeyClickController = buildingModeKeyClickController;
        this.delayMiliSeconds = 20;


        tableButton.setBounds(RoomConstants.xLimit.getValue()/2, RoomConstants.yLimit.getValue()+RoomConstants.yStart.getValue(), 30, 30);
        binButton.setBounds(RoomConstants.xLimit.getValue()/2, RoomConstants.yLimit.getValue()+RoomConstants.yStart.getValue(), 30, 30);


        tableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buildingModeKeyClickController.setObjectToBuild("Table");
            }
        });

        binButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buildingModeKeyClickController.setObjectToBuild("Bin");
            }
        });

        binButton.setBounds(RoomConstants.xLimit.getValue()/2, RoomConstants.yLimit.getValue()+RoomConstants.yStart.getValue(), 30, 30);

        // TODO: Button location fix
        add(tableButton);
        add(binButton);
        binButton.setBounds(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);

        tableButton.setFocusable(false);
        binButton.setFocusable(false);



        Timer timer = new Timer(delayMiliSeconds, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setFont(g);
        //paintRoomName(g);
        paintPlayer(g);
        paintWall(g);

    }

    private void setFont(Graphics g) {
        font = new Font("Courier New", Font.BOLD, 20);
        metrics = g.getFontMetrics(font);
    }
  // TODO
    /*
    private void paintRoomName(Graphics g) {
        g.setColor(Color.BLACK);
        int x = buildingModeRoom.getXStart() + (buildingModeRoom.getXLimit() - metrics.stringWidth(buildingModeRoom.getName())) / 2;
        int y = buildingModeRoom.getYStart() - 5;
        g.setFont(font);
        g.drawString(buildingModeRoom.getName(), x, y);
    }

 */

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
