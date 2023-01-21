package com.gurup.domain.loader;

public class RoomDatabaseGameLoaderAdapter implements GameLoaderAdapter {
	RoomDatabaseGameLoader roomDatabaseGameLoader;

	public RoomDatabaseGameLoaderAdapter() {
		roomDatabaseGameLoader = new RoomDatabaseGameLoader();
	}
	@Override
	public Object load(String username) throws Exception {
		// TODO Auto-generated method stub
	    return roomDatabaseGameLoader.loadRoam(username);
	}

}
