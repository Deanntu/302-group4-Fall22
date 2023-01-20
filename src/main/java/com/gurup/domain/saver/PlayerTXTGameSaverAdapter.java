package com.gurup.domain.saver;

import com.gurup.domain.Player;

public class PlayerTXTGameSaverAdapter implements GameSaverAdapter {
    final PlayerTXTGameSaver playerTXTGameSaver;

    public PlayerTXTGameSaverAdapter() {
        playerTXTGameSaver = new PlayerTXTGameSaver();
    }

    @Override
    public void save(String username, Object o) throws Exception {
        // TODO Auto-generated method stub
        playerTXTGameSaver.writeData(username, (Player) o);
    }
}
