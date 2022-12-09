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

	public void selectPowerUp(int selectedSlotIndex) {
		PowerUp selectedPowerUp = new ArrayList<PowerUp>(powerUps.keySet()).get(selectedSlotIndex);
		Integer powerUpCount = powerUps.get(selectedPowerUp);
		if (powerUpCount > 0) {
			powerUps.put(selectedPowerUp, powerUpCount - 1);
			selectedPowerUp.usePowerUp();
		} else {
			shakeSlot(selectedSlotIndex);
		}
	}
	public void setupBag (ArrayList<PowerUp> powerUpList) {
		for(PowerUp p: powerUpList) {
			powerUps.put(p, 0);
		}
	}
	private void shakeSlot(int i) {

	}

}
