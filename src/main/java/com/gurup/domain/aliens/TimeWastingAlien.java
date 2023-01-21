package com.gurup.domain.aliens;

import java.awt.Rectangle;

import com.gurup.domain.Game;
import com.gurup.domain.Player;
import com.gurup.domain.aliens.wastingstrategies.ConfusedWastingStrategy;
import com.gurup.domain.aliens.wastingstrategies.DoingWellWastingStrategy;
import com.gurup.domain.aliens.wastingstrategies.MercifulWastingStrategy;
import com.gurup.domain.aliens.wastingstrategies.WastingStrategy;
import com.gurup.domain.room.Room;

public class TimeWastingAlien implements Alien {

	private String name = "TimeWasting";
	private int xStart;
	private int yStart;
	private int xLen;
	private int yLen;
	// no need for x/yCurrent since TW Aliens don't move
	private WastingStrategy wastingStrategy;
	private boolean isActive = false;
	private Player player;
	
	public TimeWastingAlien(int xStart, int yStart, int xLen, int yLen, Player player) {
		this.xStart = xStart;
		this.yStart = yStart;
		this.xLen = xLen;
		this.yLen = yLen;
		this.player = player;
		setWastingStrategy(player.getRemainingTime(), player.getStartingTime());
	}
	
	private void setWastingStrategy(int currentTime, int totalTime) {
		double ratio = ((double) currentTime) / ((double) totalTime);
		if (ratio > 0.7) {
			wastingStrategy = new DoingWellWastingStrategy();
			System.out.println("You are Doing Well");
		}
		else if (ratio < 0.3) {
			wastingStrategy = new MercifulWastingStrategy();
			System.out.println("Alien became Merciful");
		}
		else {
			wastingStrategy = new ConfusedWastingStrategy();
			System.out.println("Alien became Confused");
		}
	}
	
	private void wasteTime() {
	    // called in setActive
		if (!isActive) return; // cannot waste time if not active 
		// false means remove the alien
		// true means all is good
		Player player = this.player;
		TimeWastingAlien alien = this;
		new Thread (new Runnable() {
			@Override
			public void run() {
				boolean alienLives = true;
				while (alienLives && alien.isActive()) {
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
				    if (Room.getIsPlayerFoundKeyForRoom()) {
				        alienLives = false;
				        break;
				    }
					setWastingStrategy(player.getRemainingTime(), player.getStartingTime());
					alienLives = wastingStrategy.wasteTime();
					try {
						Thread.sleep(1000); // sleep 1 second to check if strategy should change
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				alien.remove();
			}
		}).start();
	}
	
	private void remove() {
	    System.out.println("TW Alien removed");
	    setActive(false);
	}

	@Override
	public void setActive(boolean isActive) {
		this.isActive = isActive;
		if (isActive) {
		    wasteTime();
		}
	}

	@Override
	public void setXCurrent(int xCurrent) {
		// no xCurrent in TW Alien
		this.xStart = xCurrent;
	}

	@Override
	public void setYCurrent(int yCurrent) {
		// no yCurrent in TW Alien
		this.yStart = yCurrent;
	}

	@Override
	public boolean isActive() {
		return isActive;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int[] rectArray() {
		int[] rectValues = {xStart, yStart, xLen, yLen};
		return rectValues;
	}

	@Override
	public Rectangle getRectangle() {
		return new Rectangle(xStart, yStart, xLen, yLen);
	}

    public void setXLen(int xLen) {
        this.xLen = xLen;
    }

    public void setYLen(int yLen) {
        this.yLen = yLen;
    }

}
