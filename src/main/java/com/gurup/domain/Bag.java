package com.gurup.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.gurup.domain.powerups.PowerUp;

public class Bag {
    // OVERVIEW: Bag wraps a Map that has PowerUps as keys
    // and Integers (that show how many of said PowerUps are stored) as values

    // the rep
    private final Map<PowerUp, Integer> powerUps;

    // AF: The Bag is made out of key-value pairs where the key-value pair <p, i>
    // represents
    // that there are i PowerUps of type p available to the player.

    // The rep invariant is
    // c.powerUps not null &&
    // all elements of c.powerUps.values() are Integers &&
    // all elements of c.powerUps.values() are nonnegative &&
    // there are no duplicates in c.powerUps.keySet()

    // constructors
    public Bag(Player player) {
        // EFFECTS: Initializes this with an empty HashMap as powerUps
        powerUps = new HashMap<>();
    }

    // methods
    public void storePowerUp(PowerUp p) {
        // MODIFIES: this
        // REQUIRES: p is a storable powerUp &&
        // setupBag was previously called
        // EFFECTS: Increments the Integer corresponding to the PowerUp in powerUps
        powerUps.put(p, powerUps.get(p) + 1);
    }

    public void selectPowerUp(PowerUp powerUp) {
        // MODIFIES: this
        // REQUIRES: powerUp is a storable powerUp &&
        // setupBag was previously called
        // EFFECTS: Calls usePowerUp if powerUp is available in the Bag.
        // Decrements the Integer corresponding to the PowerUp in powerUps if powerUp is
        // available in the Bag.
        // Otherwise, does nothing (so far)
        if (powerUp == null || !powerUp.isStorable()) {
            System.out.println("Null/non-storable power up"); // should be unreachable
            return;
        }
        Integer powerUpCount = powerUps.get(powerUp);
        if (powerUpCount > 0) {
            powerUps.put(powerUp, powerUpCount - 1);
            powerUp.usePowerUp();
        }
    }

	public void setupBag(ArrayList<PowerUp> powerUpList) {
		// MODIFIES: this
		// EFFECTS: Initializes this so that the storable powerUps are put as the keys
		// of powerUps
		for (PowerUp p : powerUpList) {
			if (p.isStorable() && !powerUps.containsKey(p)) {
				powerUps.put(p, 0);
			}
		}
	}

    public Map<PowerUp, Integer> getPowerUps() {
        // EFFECTS: returns the Map called powerUps
        return powerUps;
    }

    public boolean repOk() {
        if (powerUps == null) {
            System.out.println("powerUps is null");
            return false;
        }
        for (Integer value : powerUps.values()) {
            if (!(value instanceof Integer)) {
                System.out.println("value is not Integer");
                return false;
            }
            if (value < 0) {
                System.out.println("value < 0");
                return false;
            }
        }
        ArrayList<PowerUp> duplicateCheck = new ArrayList<>(powerUps.keySet());
        for (int i = 0; i < duplicateCheck.size(); i++) {
            for (int j = i + 1; j < duplicateCheck.size(); j++) {
                if (duplicateCheck.get(i).equals(duplicateCheck.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
