package org.example.controllers;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.services.AppService;
import org.example.services.DirectionService;
import org.example.services.PlayerService;
import org.example.services.RoomDirectionService;

import java.net.URL;
import java.util.ResourceBundle;


public class GameScreenController implements Initializable {

    private AppService appService;

    private PlayerService playerService;

    private DirectionService directionService;

    private RoomDirectionService roomDirectionService;

    private Scene scene;

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    private Label goldAmount;

    @FXML
    private ImageView player;

    private final BooleanProperty wPressed = new SimpleBooleanProperty(false);
    private final BooleanProperty aPressed = new SimpleBooleanProperty(false);
    private final BooleanProperty sPressed = new SimpleBooleanProperty(false);
    private final BooleanProperty dPressed = new SimpleBooleanProperty(false);

    public GameScreenController(AppService appService,
                                PlayerService playerService,
                                DirectionService directionService,
                                RoomDirectionService roomDirectionService,
                                Scene scene) {
        this.appService = appService;
        this.playerService = playerService;
        this.directionService = directionService;
        this.roomDirectionService = roomDirectionService;
        this.scene = scene;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.goldAmount.setText(String.valueOf(this.appService.getPlayerState().getGoldAmount()));
        this.playerService.setImageView(this.player);
        this.playerService.moveX(this.appService.getPlayerState().getSpawnCoordinates()[0]);
        this.playerService.moveY(this.appService.getPlayerState().getSpawnCoordinates()[1]);
        this.playerService.setVisible(true);
        this.scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W:
                    this.wPressed.set(true);
                    break;
                case A:
                    this.aPressed.set(true);
                    break;
                case S:
                    this.sPressed.set(true);
                    break;
                case D:
                    this.dPressed.set(true);
                    break;
                default:
                    break;
            }

            if(this.wPressed.get()) {
                this.playerService.moveUp();
            } else if(this.aPressed.get()) {
                this.playerService.moveLeft();
            } else if(this.sPressed.get()) {
                this.playerService.moveDown();
            } else if(this.dPressed.get()){
                this.playerService.moveRight();
            }
        });
        this.scene.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case W:
                    this.wPressed.set(false);
                    break;
                case A:
                    this.aPressed.set(false);
                    break;
                case S:
                    this.sPressed.set(false);
                    break;
                case D:
                    this.dPressed.set(false);
                    break;
            }
        });
    }
    
    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public AppService getAppService() {
        return appService;
    }

    public GameScreenController setAppService(AppService appService) {
        this.appService = appService;
        return this;
    }

    public PlayerService getPlayerService() {
        return playerService;
    }

    public GameScreenController setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
        return this;
    }

    public DirectionService getDirectionService() {
        return directionService;
    }

    public GameScreenController setDirectionService(DirectionService directionService) {
        this.directionService = directionService;
        return this;
    }

    public RoomDirectionService getRoomDirectionService() {
        return roomDirectionService;
    }

    public GameScreenController setRoomDirectionService(RoomDirectionService roomDirectionService) {
        this.roomDirectionService = roomDirectionService;
        return this;
    }

    public Scene getScene() {
        return scene;
    }

    public GameScreenController setScene(Scene scene) {
        this.scene = scene;
        return this;
    }

    public Label getGoldAmount() {
        return goldAmount;
    }

    public GameScreenController setGoldAmount(Label goldAmount) {
        this.goldAmount = goldAmount;
        return this;
    }

    public ImageView getPlayer() {
        return player;
    }

    public GameScreenController setPlayer(ImageView player) {
        this.player = player;
        return this;
    }
}





