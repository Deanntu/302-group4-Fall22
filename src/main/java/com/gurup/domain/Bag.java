package com.gurup.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.gurup.domain.powerups.PowerUp;

public class Bag {
	private Map<PowerUp, Integer> powerUps;

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
