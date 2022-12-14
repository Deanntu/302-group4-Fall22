package com.gurup.domain.powerups;

import com.gurup.domain.Player;

import java.awt.*;

public class BottlePowerUp implements PowerUp{

    private static BottlePowerUp bottlePowerUp;
    private Player player;
    private String name = "bottle";
    private boolean storable = true;
    private int xLimit;
    private int yLimit;
    private int x;
    private int y;
    private boolean isActive = false;
    private Integer slotId = 2;

    private BottlePowerUp(Player player) {
        this.player = player;
    }

    public static BottlePowerUp getInstance(Player player) {
        if (bottlePowerUp == null) {
            bottlePowerUp = new BottlePowerUp(player);
        }
        return bottlePowerUp;
    }

    @Override
    public void usePowerUp() {
        activatePowerUp();
    }

    private void activatePowerUp() {

    }

    @Override
    public boolean isStorable() {
        return storable;
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(x, y, xLimit, yLimit);
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void setIsActive(boolean b) {
        isActive = b;
    }

    private int getstartX() {
        return Toolkit.getDefaultToolkit().getScreenSize().width - 100 + 50;
    }


    private int getstartY() {
        return Toolkit.getDefaultToolkit().getScreenSize().height - 175 + 50;
    }



    public int getxLimit() {
        return xLimit;
    }

    public void setxLimit(int xLimit) {
        this.xLimit = xLimit;
    }

    public int getyLimit() {
        return yLimit;
    }

    public void setyLimit(int yLimit) {
        this.yLimit = yLimit;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int[] rectArray() {
        Point pos = new Point(getX(), getY());
        int[] rectValues = {(int) pos.getX(), (int) pos.getY(), this.getxLimit(), this.getyLimit()};
        return rectValues;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSlotId() {
        return slotId;
    }

    public void moveRight() {
        if (this.getX() >= this.getstartX()) {
            this.setX(this.getstartX());
        } else {
            this.setX(this.getX() + 10);
        }
    }

    public void moveLeft() {
        if (this.getX() <= this.getxLimit()) {
            this.setX(this.getxLimit());
        } else {
            this.setX(this.getX() - 10);
        }
    }

    public void moveUp() {
        if (this.getY() <= this.getyLimit()) {
            this.setY(this.getyLimit());
        } else {
            this.setY(this.getY() - 10);
        }
    }

    public void moveDown() {
        if (this.getY() >= this.getstartY()) {
            this.setY(this.getstartY());
        } else {
            this.setY(this.getY() + 10);
        }
    }
}
