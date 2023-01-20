package com.gurup.domain.loader;

public class PlayerTXTGameLoaderAdapter implements GameLoaderAdapter {
    PlayerTXTGameLoader playerTXTGameLoader;

    public PlayerTXTGameLoaderAdapter() {
        playerTXTGameLoader = new PlayerTXTGameLoader();
    }
    @Override
    public Object load(String username) throws Exception {
        // TODO Auto-generated method stub
        return playerTXTGameLoader.readData(username);
    }

}
