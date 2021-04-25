package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.App;
import org.example.dao.PlayerState;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class EndScreenController implements Initializable {

    @FXML
    private ImageView winImage;

    @FXML
    private Label endLabel;

    @FXML
    private Label stat1;

    @FXML
    private Label stat2;

    @FXML
    private Label stat3;

    @FXML
    private Button closeButton;

    @FXML
    private Button returnButton;

    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void returnButtonAction() throws IOException {
        App.setRoot(new FXMLLoader(App.class.getResource("primary.fxml")).load());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PlayerState playerState = App.getPlayerState();
        if (playerState.getHealth() > 0) {
            this.winImage.setVisible(true);
            this.endLabel.setText(
                    "Game over! Exit Game to leave the dungeon or return to main menu.");
        }
        long currentSessionLength = System.currentTimeMillis() - App.getSessionStartMillis();
        double minutesSpent = ((double) playerState.getSessionLength()
                + currentSessionLength) / 60000;
        this.stat1.setText(
                String.format(
                        "Time Spent: %s minutes",
                        new DecimalFormat("#.####").format(minutesSpent)));
        this.stat2.setText(
                String.format(
                        "Monsters Killed: %s", playerState.getMonstersKilled().size()));
        this.stat3.setText(String.format("Gold Spent: %s", playerState.getGoldSpent()));
    }
}
