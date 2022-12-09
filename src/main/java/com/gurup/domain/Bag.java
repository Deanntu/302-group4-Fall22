package com.gurup.domain;

import java.util.ArrayList;
import java.util.Map;

import com.gurup.domain.powerups.PowerUp;

public class Bag {
	private Map<PowerUp, Integer> powerUps;

	public Bag(ArrayList<PowerUp> init) {
		for (PowerUp p : init) {
			powerUps.put(p, 0);
		}
	}

	public void collectPowerUp(PowerUp p) {
		if (p.isStorable()) {
			powerUps.put(p, powerUps.get(p) + 1);
		} else {
			p.usePowerUp();
		}
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

	private void shakeSlot(int i) {

	}
}
