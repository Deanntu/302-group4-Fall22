package com.gurup.domain.loader;

import com.gurup.domain.saver.SaverType;

public class GameLoader {
	GameLoaderAdapter gameLoaderAdapter;

	public GameLoader(SaverType type, SaverType state) {
		if (type.equals(SaverType.DATABASE)) {
			if (state.equals(SaverType.PLAYER)) {
				gameLoaderAdapter = new PlayerDatabaseGameLoaderAdapter();
			} else if (state.equals(SaverType.ROOM)) {
				gameLoaderAdapter = new RoomDatabaseGameLoaderAdapter();
			}

		} else if (type.equals(SaverType.TXT)) {
		    if (state.equals(SaverType.PLAYER)) {
                gameLoaderAdapter = new PlayerTXTGameLoaderAdapter();
            } else if (state.equals(SaverType.ROOM)) {
                gameLoaderAdapter = new RoomTXTeGameLoaderAdapter();
            }
		}
	}

	public Object load(String username) throws Exception {
		return gameLoaderAdapter.load(username);
	}
}
