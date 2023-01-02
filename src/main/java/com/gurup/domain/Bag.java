package com.gurup.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.gurup.domain.powerups.PowerUp;

public class Bag {
	// OVERVIEW: Bag wraps a Map that has PowerUps as keys 
	// and Integers (that show how many of said PowerUps are stored) as values
	
	// the rep
	private Map<PowerUp, Integer> powerUps;
	
	// AF: The Bag is made out of key-value pairs where the key-value pair <p, i> represents
	//     that there are i PowerUps of type p available to the player. 
	
	// The rep invariant is
	// c.powerUps not null &&
	// all elements of c.powerUps.values() are Integers &&
	// all elements of c.powerUps.values() are nonnegative &&
	// there are no duplicates in c.powerUps.keySet()
	
	// constructors
	public Bag(Player player) {
		powerUps = new HashMap<PowerUp, Integer>();
		
	}

	public void storePowerUp(PowerUp p) {
		powerUps.put(p, powerUps.get(p) + 1);
	}

	public void selectPowerUp(PowerUp powerUp) {
		if (powerUp == null || powerUp.isStorable() == false) {
			System.out.println("Null/non-storable power up"); // should be unreachable
			return;
		}
		Integer powerUpCount = powerUps.get(powerUp);
		if (powerUpCount > 0) {
			powerUps.put(powerUp, powerUpCount - 1);
			powerUp.usePowerUp();
		} else {
			shakeSlot(powerUp.getSlotId());
		}
	}
	public void setupBag (ArrayList<PowerUp> powerUpList) {
		for(PowerUp p: powerUpList) {
			if (p.isStorable()) {
				powerUps.put(p, 0);
			}
		}
	}
	private void shakeSlot(int slot) {

	}
	
	public Map<PowerUp, Integer> getPowerUps(){
		return powerUps;
	}

}
