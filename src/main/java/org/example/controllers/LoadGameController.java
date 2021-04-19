package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import org.example.App;
import org.example.controllers.rooms.Forest1Controller;
import org.example.dao.PlayerState;
import org.example.services.AppService;
import org.example.services.DirectionService;
import org.example.services.HealthService;
import org.example.services.PlayerService;
import org.example.services.RoomDirectionService;
import org.example.services.SaveService;

import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

import static org.example.controllers.SecondaryController.STARTING_ROOM;
import static org.example.util.ResourcePathUtility.FONT_STYLE_PATH;
import static org.example.util.ResourcePathUtility.TOGGLE_BUTTON_STYLE_PATH;

public class LoadGameController implements Initializable {

    private AppService appService;

    private SaveService saveService;

    private Scene scene;

    @FXML
    private VBox savesVBox;

    private ToggleGroup savesToggleGroup = new ToggleGroup();

    private static PlayerState selectedPlayerState;
    private static int selectedSaveIndex;

    public LoadGameController(AppService appService,
                              SaveService saveService,
                              Scene scene) {
        this.appService = appService;
        this.saveService = saveService;
        this.scene = scene;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (!this.appService.getLoggedIn()) {
            this.setMessage("You are not logged in. To load a save file, log in via Settings.");
        } else {
            List<PlayerState> playerStates = this.saveService.findPlayerStates(
                    this.appService.getPlayerState().getEmail());
            if (playerStates.isEmpty()) {
                this.setMessage(String.format("No save files found for %s",
                        this.appService.getPlayerState().getEmail()));
            }
            for (int i = 0; i < playerStates.size(); i++) {
                PlayerState playerState = playerStates.get(i);
                ToggleButton toggleButton = new ToggleButton();
                toggleButton.setToggleGroup(this.savesToggleGroup);
                toggleButton.setFocusTraversable(false);
                toggleButton.getStylesheets()
                        .add(Paths.get(TOGGLE_BUTTON_STYLE_PATH).toUri().toString());
                toggleButton.getStylesheets()
                        .add(Paths.get(FONT_STYLE_PATH).toUri().toString());
                toggleButton.setTextFill(Paint.valueOf("white"));
                toggleButton.setPrefWidth(1780);
                toggleButton.setPrefHeight(100);
                toggleButton.setFont(new Font(60));
                int finalI = i;
                toggleButton.setOnAction(actionEvent -> this.selectToggleButton(
                        playerState, finalI));
                toggleButton.setText(
                        String.format("Username: %s | Last saved: %s",
                                playerState.getUsername(), playerState.getLastUpdated()));
                this.savesVBox.getChildren().add(toggleButton);
            }
            this.scene.setOnKeyReleased(e -> {
                if (selectedPlayerState != null) {
                    if (e.getCode() == KeyCode.E) {
                        this.appService.setPlayerState(selectedPlayerState);
                        this.appService.setActiveRoom(STARTING_ROOM);
                        FXMLLoader loader = new FXMLLoader(
                                App.class.getResource("gameScreen.fxml"));
                        DirectionService directionService = new DirectionService();
                        RoomDirectionService roomDirectionService = new RoomDirectionService(
                                directionService);
                        HealthService healthService = new HealthService(this.appService);

                        loader.setControllerFactory(GameScreenController -> new Forest1Controller(
                                this.appService,
                                new PlayerService(
                                        this.appService,
                                        roomDirectionService,
                                        healthService,
                                        this.saveService),
                                directionService,
                                roomDirectionService,
                                healthService,
                                this.saveService,
                                this.scene));
                        this.appService.setRoot(loader);
                    } else if (e.getCode() == KeyCode.BACK_SPACE) {
                        boolean success = this.saveService.removePlayerStateSave(
                                selectedPlayerState.getEmail(),
                                selectedPlayerState.getUsername());
                        if (success) {
                            this.savesVBox.getChildren().clear();
                            this.initialize(url, resourceBundle);
                        }
                    }
                }
            });
        }
    }

    @FXML
    private void switchToPrimary() {
        this.scene.setOnKeyReleased(e -> { });
        FXMLLoader loader = new FXMLLoader(App.class.getResource("primary.fxml"));
        this.appService.setRoot(loader);
    }

    private void selectToggleButton(PlayerState playerState, int index) {
        selectedPlayerState = playerState;
        selectedSaveIndex = index;
    }

    private void setMessage(String message) {
        Label label = new Label(message);
        label.setWrapText(true);
        label.getStylesheets().add(Paths.get(FONT_STYLE_PATH).toUri().toString());
        label.setFont(new Font(68.0));
        label.setTextFill(Paint.valueOf("white"));
        this.savesVBox.getChildren().add(label);
    }
}
