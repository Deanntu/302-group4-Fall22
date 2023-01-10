package com.gurup.controller;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.gurup.domain.room.Room;
import com.gurup.ui.gamescreen.RunningModeScreen;

public class KeyClickController implements MouseListener {

    private final Room room;

    public KeyClickController(RunningModeScreen runningModeScreen, Room room) {
        this.room = room;
        runningModeScreen.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int xMouse = e.getX();
        int yMouse = e.getY();
        Rectangle mouseRect = new Rectangle(xMouse, yMouse, 1, 1);
        if (e.getButton() == MouseEvent.BUTTON1) { // left click
            room.isKeyFound(mouseRect);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            room.checkPowerUp(mouseRect);
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
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
