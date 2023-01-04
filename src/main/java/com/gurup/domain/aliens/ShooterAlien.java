package com.gurup.domain.aliens;

import java.awt.Color;

import com.gurup.domain.Position;

public class ShooterAlien implements Alien{

    private String name = "Shooter";
    private int xStart;
    private int yStart;
    private int xLimit;
    private int yLimit;
    private int x;
    private int y;
    private boolean isActive = false;

    public ShooterAlien(int xStart, int yStart, int xLimit, int yLimit) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xLimit = xLimit;
        this.yLimit = yLimit;
    }

    public ShooterAlien() {
        // TODO Auto-generated constructor stub
    }

    public void moveRight() {
        if (this.getX() >= this.getxLimit()) {
            this.setX(this.getxLimit());
        } else {
            this.setX(this.getX() + 10);
        }
    }

    public void moveLeft() {
        if (this.getX() <= this.getstartX()) {
            this.setX(this.getstartX());
        } else {
            this.setX(this.getX() - 10);
        }
    }

    public void moveUp() {
        if (this.getY() <= this.getstartY()) {
            this.setY(this.getstartY());
        } else {
            this.setY(this.getY() - 10);
        }
    }

    public void moveDown() {
        if (this.getY() >= this.getyLimit()) {
            this.setY(this.getyLimit());
        } else {
            this.setY(this.getY() + 10);
        }
    }

    public int[] rectArray() {
        int[] rectValues = { getX(), getY(), this.getxLimit(), this.getyLimit() };
        return rectValues;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getxLimit() {
        return xLimit;
    }

    public void setxLimit(int xLimit) {
        this.xLimit = xLimit;
    }
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getyLimit() {
        return yLimit;
    }

    public void setyLimit(int yLimit) {
        this.yLimit = yLimit;
    }

    public int getstartX() {
        return xStart;
    }

    public int getstartY() {
        return yStart;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void shoot(){
    //TODO
    }
}
