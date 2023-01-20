package com.gurup.domain.loader;

public class PlayerDatabaseGameLoaderAdapter implements GameLoaderAdapter {
	PlayerDatabaseGameLoader playerDatabaseGameLoader;

	public PlayerDatabaseGameLoaderAdapter() {
		playerDatabaseGameLoader = new PlayerDatabaseGameLoader();
	}
	@Override
	public Object load(String username) throws Exception {
		// TODO Auto-generated method stub
	    return playerDatabaseGameLoader.loadPlayer(username);
	}

}
