package com.gurup.domain.saver;

import com.gurup.domain.Player;

public class PlayerDatabaseGameSaverAdapter implements GameSaverAdapter {
	final PlayerDatabaseGameSaver playerDatabaseGameSaver;

	public PlayerDatabaseGameSaverAdapter() {
		playerDatabaseGameSaver = new PlayerDatabaseGameSaver();
	}

	@Override
	public void save(String username, Object o) throws Exception {
		// TODO Auto-generated method stub
		playerDatabaseGameSaver.trySavePlayer(username, (Player) o);
	}

}
