package com.gurup.ui.gamescreen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.gurup.controller.BuildingModeKeyClickController;
import com.gurup.domain.Game;
import com.gurup.domain.Player;
import com.gurup.domain.buildingmode.BuildingModeRoom;
import com.gurup.domain.room.RoomConstants;
import com.gurup.domain.room.buildingobjects.Bin;
import com.gurup.domain.room.buildingobjects.BuildingObjectConstants;
import com.gurup.ui.ImageLoader;
import com.gurup.ui.drawer.Drawer;

public class BuildingModeScreen extends JPanel {

    private Player player;
    private BuildingModeKeyClickController buildingModeKeyClickController;
    private BuildingModeRoom buildingModeRoom;
    private int delayMiliSeconds;
    private Drawer buildObjectDrawer = new Drawer("Object");
    private FontMetrics metrics;
    private Font font;
    private JButton binButton = new JButton(new ImageIcon(ImageLoader.bin_image.getScaledInstance(BuildingObjectConstants.allObjectsXLenForButtons.getValue(), BuildingObjectConstants.allObjectsYLenForButtons.getValue(), Image.SCALE_SMOOTH)));
    private JButton bookButton = new JButton(new ImageIcon(ImageLoader.book_image.getScaledInstance(BuildingObjectConstants.allObjectsXLenForButtons.getValue(), BuildingObjectConstants.allObjectsYLenForButtons.getValue(), Image.SCALE_SMOOTH)));
    private JButton penButton = new JButton(new ImageIcon(ImageLoader.pen_image.getScaledInstance(BuildingObjectConstants.allObjectsXLenForButtons.getValue(), BuildingObjectConstants.allObjectsYLenForButtons.getValue(), Image.SCALE_SMOOTH)));
    private JButton printerButton = new JButton(new ImageIcon(ImageLoader.printer_image.getScaledInstance(BuildingObjectConstants.allObjectsXLenForButtons.getValue(), BuildingObjectConstants.allObjectsYLenForButtons.getValue(), Image.SCALE_SMOOTH)));
    private JButton tableButton = new JButton(new ImageIcon(ImageLoader.table_image.getScaledInstance(BuildingObjectConstants.allObjectsXLenForButtons.getValue(), BuildingObjectConstants.allObjectsYLenForButtons.getValue(), Image.SCALE_SMOOTH)));
    private JButton randomButton = new JButton("Random");
    private JButton startButton = new JButton("Start");

    private final int xCurrentInitialForButtons = RoomConstants.xStart.getValue();
    private final int yCurrentForButtons = RoomConstants.yStart.getValue() + RoomConstants.yLimit.getValue() + 5;
    private final int xLenForButtons = BuildingObjectConstants.allObjectsXLenForButtons.getValue() + 35; // magical number
    private final int yLenForButtons = BuildingObjectConstants.allObjectsXLenForButtons.getValue() + 20; // magical number
    private final int buffer = xLenForButtons + 10;


    public BuildingModeScreen(Game game, Player player, BuildingModeKeyClickController buildingModeKeyClickController, BuildingModeRoom buildingModeRoom) {
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        this.player = player;
        this.buildingModeRoom = buildingModeRoom;
        this.buildingModeKeyClickController = buildingModeKeyClickController;
        this.delayMiliSeconds = 20;
        this.setLayout(null); // absolute layout for the buttons
        randomButton.setBounds(xCurrentInitialForButtons, yCurrentForButtons, xLenForButtons, yLenForButtons);
        binButton.setBounds(randomButton.getX() + buffer, yCurrentForButtons, xLenForButtons, yLenForButtons);
        bookButton.setBounds(binButton.getX() + buffer, yCurrentForButtons, xLenForButtons, yLenForButtons);
        penButton.setBounds(bookButton.getX() + buffer, yCurrentForButtons, xLenForButtons, yLenForButtons);
        printerButton.setBounds(penButton.getX() + buffer, yCurrentForButtons, xLenForButtons, yLenForButtons);
        tableButton.setBounds(printerButton.getX() + buffer, yCurrentForButtons, xLenForButtons, yLenForButtons);
        startButton.setBounds(tableButton.getX() + buffer, yCurrentForButtons, xLenForButtons, yLenForButtons);

        randomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Randomize the objects
            }
        });
        binButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buildingModeRoom.setObjectToBuild("Bin");
            }
        });

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buildingModeRoom.setObjectToBuild("Book");
            }
        });

        penButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buildingModeRoom.setObjectToBuild("Pen");
            }
        });

        printerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buildingModeRoom.setObjectToBuild("Printer");
            }
        });

        tableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buildingModeRoom.setObjectToBuild("Table");
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        // TODO: Button location fix
        add(randomButton);
        add(binButton);
        add(bookButton);
        add(penButton);
        add(printerButton);
        add(tableButton);
        add(startButton);

        randomButton.setFocusable(false);
        binButton.setFocusable(false);
        bookButton.setFocusable(false);
        penButton.setFocusable(false);
        printerButton.setFocusable(false);
        tableButton.setFocusable(false);
        startButton.setFocusable(false);


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
        paintRoomName(g);
        paintWall(g);
    }

    private void setFont(Graphics g) {
        font = new Font("Courier New", Font.BOLD, 20);
        metrics = g.getFontMetrics(font);
    }

    private void paintRoomName(Graphics g) {
        g.setColor(Color.BLACK);
        int x = buildingModeRoom.getXStart() + (buildingModeRoom.getXLimit() - metrics.stringWidth(buildingModeRoom.getName())) / 2;
        int y = buildingModeRoom.getYStart() - 5;
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
