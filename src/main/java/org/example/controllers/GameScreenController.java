package org.example.controllers;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.dto.PlayerState;
import org.example.services.AppService;
import org.example.services.DirectionService;
import org.example.services.HealthService;
import org.example.services.MonsterService;
import org.example.services.PlayerService;
import org.example.services.RoomDirectionService;

import static javafx.scene.input.KeyCode.SHIFT;


public class GameScreenController {

    protected AppService appService;

    protected PlayerService playerService;

    protected DirectionService directionService;

    protected RoomDirectionService roomDirectionService;

    protected HealthService healthService;

    private Scene scene;

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    private Label goldAmount;

    @FXML
    private ImageView player;

    @FXML
    private ProgressBar healthBar;

    @FXML
    private Label healthText;

    private final BooleanProperty wPressed = new SimpleBooleanProperty(false);
    private final BooleanProperty aPressed = new SimpleBooleanProperty(false);
    private final BooleanProperty sPressed = new SimpleBooleanProperty(false);
    private final BooleanProperty dPressed = new SimpleBooleanProperty(false);
    private final BooleanProperty shiftPressed = new SimpleBooleanProperty(false);

    public GameScreenController(AppService appService,
                                PlayerService playerService,
                                DirectionService directionService,
                                RoomDirectionService roomDirectionService,
                                HealthService healthService,
                                Scene scene) {
        this.appService = appService;
        this.playerService = playerService;
        this.directionService = directionService;
        this.roomDirectionService = roomDirectionService;
        this.healthService = healthService;
        this.scene = scene;
    }

    protected void initGameScreenController(MonsterService monsterService) {
        PlayerState playerState = this.appService.getPlayerState();
        this.goldAmount.setText(String.valueOf(this.appService.getPlayerState().getGoldAmount()));
        this.playerService.setImageView(this.player);
        this.initializePlayerHealth(playerState);
        this.playerService.moveX(playerState.getSpawnCoordinates()[0]);
        this.playerService.moveY(playerState.getSpawnCoordinates()[1]);
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
            //solely for testing hp
            case P:
                this.healthService.applyHealthModifier(-10.0);
                break;
            case SPACE:
                String monsterKilled = monsterService.attackNearestMonster(
                        this.appService.getPlayerState().getActiveWeapon(),
                        this.player.getTranslateX(), this.player.getTranslateY());
                if(monsterKilled != null) {
                    this.appService.addMonsterKilled(monsterKilled);
                }
                break;
            default:
                break;
            }

            if (this.appService.getDevMode() && e.getCode() == SHIFT) {
                this.shiftPressed.set(true);
            }

            if (this.wPressed.get()) {
                this.playerService.moveUp(this.shiftPressed.get());
            } else if (this.aPressed.get()) {
                this.playerService.moveLeft(this.shiftPressed.get());
            } else if (this.sPressed.get()) {
                this.playerService.moveDown(this.shiftPressed.get());
            } else if (this.dPressed.get()) {
                this.playerService.moveRight(this.shiftPressed.get());
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
            default:
                break;
            }
        });
    }

    private void initializePlayerHealth(PlayerState playerState) {
        this.healthBar.setProgress(playerState.getHealth()/playerState.getHealthCapacity());
        this.healthText.setText(
                (int) playerState.getHealth() + "/" + (int) playerState.getHealthCapacity());
        this.healthService.setHealthBar(this.healthBar).setHealthText(this.healthText);
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





