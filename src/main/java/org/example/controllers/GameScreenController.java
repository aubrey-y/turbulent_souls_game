package org.example.controllers;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import org.example.dao.PlayerState;
import org.example.dto.util.Mutex;
import org.example.services.AppService;
import org.example.services.ConsumableService;
import org.example.services.DirectionService;
import org.example.services.GoldService;
import org.example.services.HealthService;
import org.example.services.InventoryService;
import org.example.services.MonsterService;
import org.example.services.PlayerService;
import org.example.services.RoomDirectionService;
import org.example.services.SaveService;
import org.example.services.TraderService;
import org.example.util.SFXUtility;
import org.example.util.ScheduleUtility;

import java.util.Optional;

import static javafx.scene.input.KeyCode.SHIFT;
import static org.example.enums.Direction.LEFT;
import static org.example.enums.Direction.RIGHT;


public class GameScreenController extends InventoryController {

    protected AppService appService;

    protected PlayerService playerService;

    protected DirectionService directionService;

    protected RoomDirectionService roomDirectionService;

    protected HealthService healthService;

    protected MonsterService monsterService;

    protected InventoryService inventoryService;

    protected ConsumableService consumableService;

    protected TraderService traderService;

    protected GoldService goldService;

    private Scene scene;

    protected SaveService saveService;

    private Mutex movementMutex = new Mutex();

    @FXML
    private Button closeButton;

    @FXML
    private Button saveButton;

    @FXML
    private Label goldAmountLabel;

    @FXML
    private Label goldAmount;

    @FXML
    private ImageView player;

    @FXML
    private ProgressBar healthBar;

    @FXML
    private Label healthText;

    @FXML
    private Label username;

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
                                SaveService saveService,
                                Scene scene) {
        this.appService = appService;
        this.playerService = playerService;
        this.directionService = directionService;
        this.roomDirectionService = roomDirectionService;
        this.healthService = healthService;
        this.saveService = saveService;
        this.scene = scene;
    }

    public GameScreenController() {

    }

    protected void initGameScreenController(MonsterService monsterService, ImageView trader) {

        PlayerState playerState = this.appService.getPlayerState();
        this.initializeUIElements(playerState);
        if (playerState.getEmail() == null) {
            this.saveButton.setDisable(true);
        }
        this.goldAmount.setText(String.valueOf(this.appService.getPlayerState().getGoldAmount()));
        this.goldService = new GoldService(this.appService, this.goldAmount);
        if (this.traderService != null) {
            this.initializeTraderService(this.traderService, this.goldService, trader);
        }
        this.playerService.setGoldService(this.goldService);
        this.initializePlayerHealth(playerState);
        this.initializePlayerImageView(playerState);
        this.inventoryService = new InventoryService(this.appService);
        this.initializeInventoryService(this.inventoryService);
        this.consumableService = new ConsumableService(
                this.healthService, this.playerService, this.appService);
        this.setupKeyInteraction();
    }

    private void setupKeyInteraction() {
        this.scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
            case TAB:
                if (this.inventoryService.getInventoryOpen()) {
                    this.inventoryService.toggleInventoryOpen();
                } else if (this.traderService != null && this.traderService.isTraderOpen()) {
                    this.traderService.toggleTraderOpen();
                }
                break;
            case W:
                if (!playerService.getAnimatingAttack()) {
                    this.wPressed.set(true);
                }
                break;
            case A:
                if (!playerService.getAnimatingAttack()) {
                    this.aPressed.set(true);
                    this.playerService.setPlayerSpawnOrientation(LEFT);
                    this.playerService
                            .displayPlayerLeftOrientation(this.appService.getPlayerState());
                }
                break;
            case S:
                if (!playerService.getAnimatingAttack()) {
                    this.sPressed.set(true);
                }
                break;
            case D:
                if (!playerService.getAnimatingAttack()) {
                    this.dPressed.set(true);
                    this.playerService.setPlayerSpawnOrientation(RIGHT);
                    this.playerService
                            .displayPlayerRightOrientation(this.appService.getPlayerState());
                }
                break;
            case P:
                if (this.appService.getDevMode()) {
                    this.healthService.applyHealthModifier(-10);
                }
                break;
            case B:
                if (this.traderService == null || !this.traderService.isTraderOpen()) {
                    this.inventoryService.toggleInventoryOpen();
                }
                break;
            case SPACE:
                if (!playerService.getAnimatingAttack()) {
                    this.playerService.playAttackAnimation();
                    switch (this.appService.getPlayerState().getActiveWeapon().getType()) {
                    case MAGIC:
                        AudioClip magicSound = SFXUtility.getRandomMagicSound();
                        magicSound.setVolume(0.3);
                        magicSound.play();
                        break;
                    case STAFF:
                        AudioClip staffSound = SFXUtility.getRandomStaffSound();
                        staffSound.setVolume(0.3);
                        staffSound.play();
                        break;
                    case SWORD:
                        SFXUtility.getRandomSwordSound().play();
                        ScheduleUtility.generateAdditionalAudioSchedule(
                            SFXUtility.getRandomSwordSound(), 0.5).play();
                        break;
                    default:
                        break;
                    }
                    String monsterKilled = monsterService.attackNearestMonster(
                            this.appService.getPlayerState().getActiveWeapon(),
                            this.player.getTranslateX(), this.player.getTranslateY(),
                            this.appService.getDevMode());
                    if (monsterKilled != null) {
                        if (monsterService.getMonstersRemaining() < 1) {
                            this.playerService.setChallengeRoomLockOn(false);
                        }
                        monsterService.initiateDeathAnimation(monsterKilled);
                        this.appService.addMonsterKilled(monsterKilled);
                    }
                }
                break;
            case E:
                if (this.inventoryService.getInventoryOpen()) {
                    this.inventoryService.consumeItem(this.consumableService);
                } else if (this.traderService != null && this.traderService.isTraderOpen()) {
                    this.traderService.purchaseItem();
                } else if (this.traderService != null
                        && this.playerService.playerInRangeOfTrader()
                        && !this.inventoryService.getInventoryOpen()
                        && !this.traderService.isTraderOpen()) {
                    this.traderService.toggleTraderOpen();
                } else {
                    if (this.playerService.attemptToClaimGold()) {
                        SFXUtility.COLLECT_GOLD.play();
                    }
                }
                break;
            default:
                break;
            }
            if (this.appService.getDevMode() && e.getCode() == SHIFT) {
                this.shiftPressed.set(true);
            }
            if (this.wPressed.get() || this.aPressed.get()
                    || this.sPressed.get() || this.dPressed.get()) {
                if (this.movementMutex.acquireLock()) {
                    AudioClip audioClip = SFXUtility.getRandomMovementSound(
                            this.appService.getActiveRoom().getRoomType());
                    if (audioClip != null) {
                        audioClip.setVolume(0.3);
                        audioClip.play();
                    }
                    ScheduleUtility.generateMovementMutexReleaseSchedule(
                            this.movementMutex, 0.5).play();
                }
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

    private void initializeUIElements(PlayerState playerState) {
        this.username.setText(playerState.getUsername());
        this.username.setLayoutX(35);
        this.username.setLayoutY(2);
        this.goldAmountLabel.setLayoutX(35);
        this.goldAmountLabel.setLayoutY(83);
        this.goldAmount.setLayoutX(135);
        this.goldAmount.setLayoutY(83);
        this.healthBar.setLayoutX(35);
        this.healthBar.setLayoutY(53);
        this.healthText.setLayoutX(140);
        this.healthText.setLayoutY(58);
    }

    private void initializeInventoryService(InventoryService inventoryService) {
        this.inventoryPreviewBackground.setLayoutX(1400);
        this.inventoryPreviewBackground.setLayoutY(100);
        this.inventoryPreviewImage.setLayoutX(1400);
        this.inventoryPreviewImage.setLayoutY(150);
        this.inventoryPreviewTitle.setLayoutX(1410);
        this.inventoryPreviewTitle.setLayoutY(100);
        this.inventoryPreviewStat.setLayoutX(1410);
        this.inventoryPreviewStat.setLayoutY(593);
        this.inventoryPreviewQty.setLayoutX(1410);
        this.inventoryPreviewQty.setLayoutY(638);
        this.inventoryPreviewDescription.setLayoutX(1410);
        this.inventoryPreviewDescription.setLayoutY(693);
        this.inventoryVBox.setLayoutX(50);
        this.inventoryVBox.setLayoutY(150);
        inventoryService
                .setInventoryBackground(this.inventoryBackground)
                .setInventoryPreviewBackground(this.inventoryPreviewBackground)
                .setInventoryPreviewImage(this.inventoryPreviewImage)
                .setInventoryPreviewTitle(this.inventoryPreviewTitle)
                .setInventoryPreviewStat(this.inventoryPreviewStat)
                .setInventoryPreviewQty(this.inventoryPreviewQty)
                .setInventoryPreviewDescription(this.inventoryPreviewDescription)
                .setInventoryVBox(this.inventoryVBox)
                .setInventoryRow1(this.inventoryRow1)
                .setInventoryRow2(this.inventoryRow2)
                .setInventoryRow3(this.inventoryRow3)
                .setInventoryRow4(this.inventoryRow4)
                .setInventoryRow5(this.inventoryRow5);
    }

    protected void initializeTraderService(TraderService traderService,
                                           GoldService goldService,
                                           ImageView trader) {
        traderService
                .setAppService(this.appService)
                .setGoldService(goldService)
                .setTraderVBox(this.inventoryVBox)
                .setTraderPreviewTitle(this.inventoryPreviewTitle)
                .setTraderPreviewStat(this.inventoryPreviewStat)
                .setTraderPreviewDescription(this.inventoryPreviewDescription)
                .setTraderPreviewBackground(this.inventoryPreviewBackground)
                .setTraderPreviewImage(this.inventoryPreviewImage)
                .setTraderBackground(this.inventoryBackground);
        trader.setTranslateX(913);
        trader.setTranslateY(500);
        trader.setVisible(true);
    }

    private void initializePlayerImageView(PlayerState playerState) {
        this.playerService.setImageView(this.player);
        this.playerService.setImageViewDimensions(this.player);
        this.displayCorrectPlayerOrientation(playerState);
        this.playerService.moveX(playerState.getSpawnCoordinates().getX());
        this.playerService.moveY(playerState.getSpawnCoordinates().getY());
        this.playerService.setVisible(true);
    }

    private void initializePlayerHealth(PlayerState playerState) {
        this.healthBar.setProgress(playerState.getHealth() / playerState.getHealthCapacity());
        this.healthText.setText(
                (int) playerState.getHealth() + "/" + (int) playerState.getHealthCapacity());
        this.healthService.setHealthBar(this.healthBar).setHealthText(this.healthText);
        this.healthService.applyHealthModifier(0.0);
    }

    protected void displayCorrectPlayerOrientation(PlayerState playerState) {
        if (playerState.getSpawnOrientation() == LEFT) {
            this.playerService.displayPlayerLeftOrientation(playerState);
        } else {
            this.playerService.displayPlayerRightOrientation(playerState);
        }
    }

    private void updatePlayerStateSessionLength() {
        PlayerState playerState = this.appService.getPlayerState();
        long sessionLength = System.currentTimeMillis() - this.appService.getSessionStartMillis();
        playerState.setSessionLength(playerState.getSessionLength() + sessionLength);
        this.appService.setPlayerState(playerState);
        this.appService.setSessionStartMillis(System.currentTimeMillis());
    }

    protected boolean initializeChallengeRoom() {
        AudioClip challengeSound = SFXUtility.CHALLENGE_SOUND;
        challengeSound.setVolume(0.2);
        challengeSound.play();
        PlayerState playerState = this.appService.getPlayerState();
        Optional<ButtonType> result;
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "The next room can be increased in difficulty for more rewards, "
                        + "do you accept this challenge?",
                yes,
                no);
        alert.setTitle("Challenge Room");
        alert.initOwner(this.appService.getStage());
        result = alert.showAndWait();
        this.appService.setPlayerState(playerState);

        return result.isPresent() && result.orElse(no) == yes;
    }

    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void saveGameAction() {
        this.appService.updatePlayerStateLastSaved();
        this.updatePlayerStateSessionLength();
        this.saveService.upsertPlayerStateSave(this.appService.getPlayerState());
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

    public HealthService getHealthService() {
        return healthService;
    }

    public GameScreenController setHealthService(HealthService healthService) {
        this.healthService = healthService;
        return this;
    }

    public MonsterService getMonsterService() {
        return monsterService;
    }

    public GameScreenController setMonsterService(MonsterService monsterService) {
        this.monsterService = monsterService;
        return this;
    }

    public InventoryService getInventoryService() {
        return inventoryService;
    }

    public GameScreenController setInventoryService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
        return this;
    }
}


