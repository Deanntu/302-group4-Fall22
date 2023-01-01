package com.gurup.domain.saver;

import com.gurup.domain.DatabaseRequirements;
import com.gurup.domain.Game;
import com.gurup.domain.Player;


public class PlayerDatabaseGameSaver {
	public PlayerDatabaseGameSaver() {
		try {
			Class.forName(DatabaseRequirements.driver.getValue());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public GameSaverOperationResults savePlayer(String username, Player player) throws Exception {
		//TODO Create table according to the requirements below.
		player.getIsProtected();
		player.getRemainingTime();
		player.getRemainingLife();
		player.getX();
		player.getY();
		Game.getBag().getPowerUps();		
		
		return trySavePlayer();
	}
	private GameSaverOperationResults trySavePlayer() {
		// TODO Auto-generated method stub
		return null;
	}
}
