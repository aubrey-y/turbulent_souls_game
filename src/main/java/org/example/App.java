package org.example;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    private static MediaPlayer mediaPlayer;

    private static boolean soundPlaying = true;

    private static AudioClip clickSound;


    @Override
    public void start(Stage stage) throws IOException {
        //scene = new Scene(loadFXML("primary"), 1920, 1080);
        Parent root = FXMLLoader.load(getClass().getResource("primary.fxml"));
        scene = new Scene(root, 1920, 1080);
//        scene.getStylesheets().add(getClass().getResource("/static/fontstyle.css").toExternalForm());

        Media media = new Media(Paths.get("src/main/resources/static/music/bardsadventure.mp3").toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
        mediaPlayer.play();
        clickSound = new AudioClip(Paths.get("src/main/resources/static/music/buttonPress.wav").toUri().toString());
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> clickSound.play());
        scene.addEventFilter(KeyEvent.KEY_PRESSED, mouseEvent -> clickSound.play());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.show();
    }

    public static void toggleSound() {
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