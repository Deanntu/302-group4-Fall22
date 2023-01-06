package com.gurup.controller;


import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.gurup.domain.buildingmode.BuildingModeRoom;
import com.gurup.ui.gamescreen.BuildingModeScreen;

public class BuildingModeKeyClickController implements MouseListener {

    private BuildingModeRoom buildingModeRoom;

    public BuildingModeKeyClickController(BuildingModeScreen buildingModeScreen, BuildingModeRoom buildingModeRoom) {
        this.buildingModeRoom = buildingModeRoom;
        buildingModeScreen.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int xMouse = e.getX();
        int yMouse = e.getY();
        Rectangle mouseRect = new Rectangle(xMouse, yMouse, 1, 1);
        if (e.getButton() == e.BUTTON1) {

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void setObjectToBuild(String table) {
    }
}
