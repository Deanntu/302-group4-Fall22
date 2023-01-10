package com.gurup.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.gurup.domain.Game;
import com.gurup.domain.Player;
import com.gurup.ui.gamescreen.RunningModeScreen;

public class MovementController implements KeyListener {

    private final Player player;


    public MovementController(Player player, RunningModeScreen runningModeScreen) {
        this.player = player;
        runningModeScreen.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        if (Game.getIsPaused()) {
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            player.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.moveDown();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
