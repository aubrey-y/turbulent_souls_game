package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    private static MediaPlayer mediaPlayer;

    private static boolean soundPlaying = true;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 1920, 1080);
//        scene.getStylesheets().add(getClass().getResource("/static/fontstyle.css").toExternalForm());

        Media media = new Media(Paths.get("src/main/resources/static/music/bardsadventure.mp3").toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
        mediaPlayer.play();

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.show();
    }

    public static void buttonSound() {
        AudioClip buttonPress = new AudioClip(new File("src/main/resources/static/soundeffects/buttonPress.wav").toURI().toString());
        buttonPress.play();
    }

    public static void toggleSound() {
        buttonSound();
        if(soundPlaying) {
            mediaPlayer.setOnEndOfMedia(() -> {});
            mediaPlayer.stop();
        }
        else {
            mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
            mediaPlayer.play();
        }
        soundPlaying = !soundPlaying;
    }

    public static void setRoot(String fxml) throws IOException {
        buttonSound();
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}