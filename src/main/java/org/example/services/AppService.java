package org.example.services;

import org.example.App;
import org.example.dto.PlayerState;

import java.io.IOException;

/**
 * All interactions with App.java should route through this wrapper class.
 * This enables Mockito to mock these (static) methods, without the need of a
 * difficult framework like PowerMock.
 */
public class AppService {

    public void toggleSound() {
        App.toggleSound();
    }

    public void setRoot(String root) throws IOException {
        App.setRoot(root);
    }

    public PlayerState getPlayerState() {
        return App.getPlayerState();
    }

    public void setPlayerState(PlayerState playerState) {
        App.setPlayerState(playerState);
    }

    public boolean getSoundPlaying() { return App.getSoundPlaying(); }
}
