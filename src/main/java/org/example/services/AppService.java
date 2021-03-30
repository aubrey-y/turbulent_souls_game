package org.example.services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import org.example.App;
import org.example.dto.PlayerState;
import org.example.dto.Room;

import java.util.Set;

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

    public void setDevMode(boolean devMode) {
        App.setDevMode(devMode);
    }

    public boolean getDevMode() {
        return App.isDevMode();
    }

    public void setRoot(FXMLLoader loader) {
        try {
            App.setRoot(loader.load());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    public Room getActiveRoom() {
        return App.getActiveRoom();
    }

    public void setActiveRoom(Room room) {
        App.setActiveRoom(room);
    }

    public Set<String> getMonstersKilled() {
        return App.getMonstersKilled();
    }

    public void setMonstersKilled(Set<String> monstersKilled) {
        App.setMonstersKilled(monstersKilled);
    }

    public void addMonsterKilled(String id) {
        App.getMonstersKilled().add(id);
    }
}
