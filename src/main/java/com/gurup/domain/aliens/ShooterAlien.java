package com.gurup.domain.aliens;

import java.awt.Rectangle;

import com.gurup.domain.Game;
import com.gurup.domain.Player;

public class ShooterAlien implements Alien {


	private String name = "Shooter";
	private int xStart;
	private int yStart;
	private int xLen;
	private int yLen;
	private boolean isActive = false;

	private Player player;

	public ShooterAlien(int xStart, int yStart, int xLen, int yLen, Player player) {
		this.xStart = xStart;
		this.yStart = yStart;
		this.xLen = xLen;
		this.yLen = yLen;
		this.player = player;
	}

	public ShooterAlien() {
		// TODO Auto-generated constructor stub

	}

	private void shoot(){
	    if (!isActive) return;
	    Player player = this.player;
	    ShooterAlien alien = this;
        new Thread (new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if(!(alien.isActive())) {
                        return;
                    }
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    if (Game.getIsPaused()) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        continue;
                    }
                    if(!player.isProtected() && playerInsideRange()){
                        player.setLife(player.getLife() - 1);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }).start();
	}

	private boolean playerInsideRange(){
		Rectangle range = new Rectangle(xStart-100, yStart-100, xLen+200, yLen+200);
	    return range.intersects(player.getRectangle());
	}

	public Rectangle getRectangle() {
		return new Rectangle(xStart, yStart, xLen, yLen);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
		if (isActive) {
            shoot();
        }
	}

	public int getXStart() {
		return xStart;
	}

	public void setXStart(int xStart) {
		this.xStart = xStart;
	}

	public int getYStart() {
		return yStart;
	}

	public void setYStart(int yStart) {
		this.yStart = yStart;
	}

	public int getXLen() {
		return xLen;
	}

	public void setXLen(int xLen) {
		this.xLen = xLen;
	}

	public int getYLen() {
		return yLen;
	}

	public void setYLen(int yLen) {
		this.yLen = yLen;
	}

	public int getXCurrent() {
		return xStart;
	}

	public void setXCurrent(int xStart) {
		this.xStart = xStart;
	}

	public int getYCurrent() {
		return yStart;
	}

	public void setYCurrent(int yStart) {
		this.yStart = yStart;
	}

	@Override
	public int[] rectArray() {
		int[] rectValues = {xStart, yStart, xLen, yLen};
		return rectValues;
	}

}
