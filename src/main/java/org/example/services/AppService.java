package org.example.services;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import org.example.App;
import org.example.dto.PlayerState;

import java.io.IOException;

/**
 * All interactions with App.java should route through this wrapper class.
 * This enables Mockito to mock these (static) methods, without the need of a
 * difficult framework like PowerMock.
 */
public class AppService {

    public Parent getRoot() {
        return App.getRoot();
    }

    public Scene getScene() {
        return App.getScene();
    }

    public void toggleSound(ImageView muteDisabled, ImageView muteEnabled) {
        App.toggleSound(muteDisabled, muteEnabled);
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

    public boolean getSoundPlaying() {
        return App.getSoundPlaying();
    }
}
