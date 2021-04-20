package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.dao.PlayerState;
import org.example.dto.util.Room;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Parent root;

    private static Scene scene;

    private static MediaPlayer mediaPlayer;

    private static boolean soundPlaying = true;

    private static boolean devMode = false;

    private static AudioClip clickSound;

    private static PlayerState playerState = new PlayerState();

    private static long sessionStartMillis;

    private static Room activeRoom;

    private static FXMLLoader activeLoader;

    @Override
    public void start(Stage stage) throws IOException {
        root = FXMLLoader.load(getClass().getResource("primary.fxml"));
        scene = new Scene(root, 1920, 1080);
        Media media = new Media(Paths.get("src/main/resources/static/music/bardsadventure.mp3")
                .toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
        mediaPlayer.play();
        clickSound = new AudioClip(Paths.get("src/main/resources/static/music/buttonPress.wav")
                .toUri().toString());
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> clickSound.play());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.show();
    }

    public static void toggleSound(ImageView muteDisabled, ImageView muteEnabled) {
        if (soundPlaying) {
            mediaPlayer.setVolume(0.0);
            muteDisabled.setOpacity(0.0);
            muteEnabled.setOpacity(1.0);
        } else {
            mediaPlayer.setVolume(1.0);
            muteDisabled.setOpacity(1.0);
            muteEnabled.setOpacity(0.0);
        }
        soundPlaying = !soundPlaying;
    }

    public static void main(String[] args) {
        launch();
    }

    public static Parent getRoot() {
        return root;
    }

    public static void setRoot(Parent root) {
        scene.setRoot(root);
    }

    public static Scene getScene() {
        return scene;
    }

    public static void setScene(Scene scene) {
        App.scene = scene;
    }

    public static PlayerState getPlayerState() {
        return playerState;
    }

    public static void setPlayerState(PlayerState state) {
        playerState = state;
    }

    public static long getSessionStartMillis() {
        return sessionStartMillis;
    }

    public static void setSessionStartMillis(long sessionStartMillis) {
        App.sessionStartMillis = sessionStartMillis;
    }

    public static boolean getSoundPlaying() {
        return soundPlaying;
    }

    public static void setSoundPlaying(boolean soundPlaying) {
        App.soundPlaying = soundPlaying;
    }

    public static boolean isDevMode() {
        return devMode;
    }

    public static void setDevMode(boolean devMode) {
        App.devMode = devMode;
    }

    public static Room getActiveRoom() {
        return activeRoom;
    }

    public static void setActiveRoom(Room activeRoom) {
        App.activeRoom = activeRoom;
    }

    public static FXMLLoader getActiveLoader() {
        return activeLoader;
    }

    public static void setActiveLoader(FXMLLoader activeLoader) {
        App.activeLoader = activeLoader;
    }

}