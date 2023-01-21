package com.gurup.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.gurup.domain.Bag;
import com.gurup.domain.Game;
import com.gurup.domain.powerups.BottlePowerUp;
import com.gurup.domain.powerups.HintPowerUp;
import com.gurup.domain.powerups.ThrownBottlePowerUp;
import com.gurup.domain.powerups.VestPowerUp;
import com.gurup.ui.gamescreen.RunningModeScreen;


public class PowerUpController implements KeyListener {

    private final Bag bag;

    public PowerUpController(Bag bag, RunningModeScreen runningModeScreen) {
        this.bag = bag;
        runningModeScreen.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (Game.getIsPaused()) {
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            bag.selectPowerUp(VestPowerUp.getInstance(null));
        }
        if (e.getKeyCode() == KeyEvent.VK_H) {
            bag.selectPowerUp(HintPowerUp.getInstance(null));
        }
        if (e.getKeyCode() == KeyEvent.VK_B) {
            bag.selectPowerUp(BottlePowerUp.getInstance(null));
            if (BottlePowerUp.getInstance(null).isUsable()) {
                boolean temp = BottlePowerUp.getInstance(null).isActive();// TODO
                // TODO
                // TODO
                // TODO
                BottlePowerUp.getInstance(null).usePowerUp();
                if (temp) { //TODO
                    BottlePowerUp.getInstance(null).setIsActive(true);// TODO
                }// TODO
            }
        }
        // TODO Movement of Bottle;
        if (ThrownBottlePowerUp.getInstance(null).isUsable()) {
            if (e.getKeyCode() == KeyEvent.VK_A) {
                ThrownBottlePowerUp.getInstance(Game.getPlayer()).usePowerUp("left");
            }
            if (e.getKeyCode() == KeyEvent.VK_W) {
                ThrownBottlePowerUp.getInstance(Game.getPlayer()).usePowerUp("up");
            }
            if (e.getKeyCode() == KeyEvent.VK_X) {
                ThrownBottlePowerUp.getInstance(Game.getPlayer()).usePowerUp("down");
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                ThrownBottlePowerUp.getInstance(Game.getPlayer()).usePowerUp("right");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
