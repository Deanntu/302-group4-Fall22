package com.gurup.ui.gamescreen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.gurup.controller.BuildingModeKeyClickController;
import com.gurup.domain.Game;
import com.gurup.domain.Player;
import com.gurup.domain.buildingmode.BuildingModeRoom;
import com.gurup.domain.buildingmode.BuildingModeRoomConstants;
import com.gurup.domain.room.RoomConstants;
import com.gurup.domain.room.buildingobjects.BuildingObject;
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
    private JButton nextButton = new JButton("Next");

    private final int xLenForButtons = BuildingObjectConstants.allObjectsXLenForButtons.getValue() + 35; // magical number
    private final int yLenForButtons = BuildingObjectConstants.allObjectsXLenForButtons.getValue() + 20; // magical number
    private final int buffer = 10;
    private final int numOfButtons = 7;
    private final int buttonsTotalLen = numOfButtons * xLenForButtons + (numOfButtons - 1) * buffer;
    private final int xCurrentInitialForButtons = RoomConstants.xStart.getValue() + (RoomConstants.xLimit.getValue() - buttonsTotalLen) / 2;
    private final int yCurrentForButtons = RoomConstants.yStart.getValue() + RoomConstants.yLimit.getValue() + 5;

    private boolean isFinished = false;
    private boolean isRandomValid = true;


    public BuildingModeScreen(Game game, Player player, BuildingModeKeyClickController buildingModeKeyClickController, BuildingModeRoom buildingModeRoom) {
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        this.player = player;
        this.buildingModeRoom = buildingModeRoom;
        this.buildingModeKeyClickController = buildingModeKeyClickController;
        this.delayMiliSeconds = 5;
        this.setLayout(null); // absolute layout for the buttons
        if (buildingModeRoom.getName().equals("SNA")) {
            nextButton.setText("Start");
        }

        randomButton.setBounds(xCurrentInitialForButtons, yCurrentForButtons, xLenForButtons, yLenForButtons);
        binButton.setBounds(randomButton.getX() + buffer + xLenForButtons, yCurrentForButtons, xLenForButtons, yLenForButtons);
        bookButton.setBounds(binButton.getX() + buffer + xLenForButtons, yCurrentForButtons, xLenForButtons, yLenForButtons);
        penButton.setBounds(bookButton.getX() + buffer + xLenForButtons, yCurrentForButtons, xLenForButtons, yLenForButtons);
        printerButton.setBounds(penButton.getX() + buffer + xLenForButtons, yCurrentForButtons, xLenForButtons, yLenForButtons);
        tableButton.setBounds(printerButton.getX() + buffer + xLenForButtons, yCurrentForButtons, xLenForButtons, yLenForButtons);
        nextButton.setBounds(tableButton.getX() + buffer + xLenForButtons, yCurrentForButtons, xLenForButtons, yLenForButtons);

        randomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                  if (isRandomValid) {
                    buildingModeRoom.addRandomBuildingObjects();
                  isRandomValid = false;
                 }

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

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buildingModeRoom.getName().equals("Student Center")) {
                    if (buildingModeRoom.getBuildingObjects().size() < BuildingModeRoomConstants.minObjectsForStudentCenter.getValue()) {
                        JOptionPane.showMessageDialog(null, "Please add " + (BuildingModeRoomConstants.minObjectsForStudentCenter.getValue() - buildingModeRoom.getBuildingObjects().size()) + " more objects");
                    } else {
                        isFinished = true;
                    }
                } else if (buildingModeRoom.getName().equals("CASE")) {
                    if (buildingModeRoom.getBuildingObjects().size() < BuildingModeRoomConstants.minObjectsForCASE.getValue()) {
                        JOptionPane.showMessageDialog(null, "Please add " + (BuildingModeRoomConstants.minObjectsForCASE.getValue() - buildingModeRoom.getBuildingObjects().size()) + " more objects");
                    } else {
                        isFinished = true;
                    }
                } else if (buildingModeRoom.getName().equals("SOS")) {
                    if (buildingModeRoom.getBuildingObjects().size() < BuildingModeRoomConstants.minObjectsForSOS.getValue()) {
                        JOptionPane.showMessageDialog(null, "Please add " + (BuildingModeRoomConstants.minObjectsForSOS.getValue() - buildingModeRoom.getBuildingObjects().size()) + " more objects");
                    } else {
                        isFinished = true;
                    }
                } else if (buildingModeRoom.getName().equals("SCI")) {
                    if (buildingModeRoom.getBuildingObjects().size() < BuildingModeRoomConstants.minObjectsForSCI.getValue()) {
                        JOptionPane.showMessageDialog(null, "Please add " + (BuildingModeRoomConstants.minObjectsForSCI.getValue() - buildingModeRoom.getBuildingObjects().size()) + " more objects");
                    } else {
                        isFinished = true;
                    }
                } else if (buildingModeRoom.getName().equals("ENG")) {
                    if (buildingModeRoom.getBuildingObjects().size() < BuildingModeRoomConstants.minObjectsForENG.getValue()) {
                        JOptionPane.showMessageDialog(null, "Please add " + (BuildingModeRoomConstants.minObjectsForENG.getValue() - buildingModeRoom.getBuildingObjects().size()) + " more objects");
                    } else {
                        isFinished = true;
                    }
                } else if (buildingModeRoom.getName().equals("SNA")) {
                    if (buildingModeRoom.getBuildingObjects().size() < BuildingModeRoomConstants.minObjectsForSNA.getValue()) {
                        JOptionPane.showMessageDialog(null, "Please add " + (BuildingModeRoomConstants.minObjectsForSNA.getValue() - buildingModeRoom.getBuildingObjects().size()) + " more objects");
                    } else {
                        isFinished = true;
                    }
                }
            }

        });


        // TODO: Button location fix
        add(randomButton);
        add(binButton);
        add(bookButton);
        add(penButton);
        add(printerButton);
        add(tableButton);
        add(nextButton);

        randomButton.setFocusable(false);
        binButton.setFocusable(false);
        bookButton.setFocusable(false);
        penButton.setFocusable(false);
        printerButton.setFocusable(false);
        tableButton.setFocusable(false);
        nextButton.setFocusable(false);


        new Timer(this.delayMiliSeconds, e -> {
            repaint();
        }).start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setFont(g);
        paintRoomName(g);
        paintWall(g);
        paintDoor(g);
        drawObjects(g);
    }

    private void drawObjects(Graphics g) {
        for (BuildingObject bo : buildingModeRoom.getBuildingObjects()) {
            buildObjectDrawer.draw(g, bo.rectArray(), bo.getName());
        }
    }

    private void paintDoor(Graphics g) {
        g.drawImage(ImageLoader.closed_door_image, RoomConstants.doorXStart.getValue(), RoomConstants.doorYStart.getValue(), RoomConstants.doorXLen.getValue(), RoomConstants.doorYLen.getValue(), null);
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

    public boolean getIsFinished() {
        return isFinished;

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

    public BuildingModeRoom getBuildingModeRoom(){
        return buildingModeRoom;
    }
}
