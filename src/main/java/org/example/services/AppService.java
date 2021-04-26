package org.example.services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Window;
import org.example.App;
import org.example.dao.PlayerState;
import org.example.dto.util.Room;

import java.time.LocalDateTime;
import java.util.Set;

import static org.example.util.DateTimeFormatUtility.DATE_TIME_FORMATTER;

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

    public MediaPlayer getMediaPlayer() {
        return App.getMediaPlayer();
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        App.setMediaPlayer(mediaPlayer);
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

    public boolean getLoggedIn() {
        return App.getPlayerState().getEmail() != null;
    }

    public void setLoggedInEmail(String loggedInEmail) {
        App.setPlayerState(App.getPlayerState().setEmail(loggedInEmail));
    }

    public void setRoot(FXMLLoader loader) {
        try {
            App.setRoot(loader.load());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePlayerStateLastSaved() {
        PlayerState playerState = App.getPlayerState();
        playerState.setLastUpdated(DATE_TIME_FORMATTER.format(LocalDateTime.now()));
        App.setPlayerState(playerState);
    }

    public PlayerState getPlayerState() {
        return App.getPlayerState();
    }

    public void setPlayerState(PlayerState playerState) {
        App.setPlayerState(playerState);
    }

    public void setSessionStartMillis(long sessionStartMillis) {
        App.setSessionStartMillis(sessionStartMillis);
    }

    public long getSessionStartMillis() {
        return App.getSessionStartMillis();
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
        return App.getPlayerState().getMonstersKilled();
    }

    public void setMonstersKilled(Set<String> monstersKilled) {
        App.setPlayerState(App.getPlayerState().setMonstersKilled(monstersKilled));
    }

    public void addMonsterKilled(String id) {
        App.getPlayerState().getMonstersKilled().add(id);
    }

    public Window getStage() {
        return App.getPrimaryStage();
    }
}
