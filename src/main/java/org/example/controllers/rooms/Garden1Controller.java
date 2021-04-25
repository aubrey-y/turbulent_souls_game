package org.example.controllers.rooms;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.example.controllers.GameScreenController;
import org.example.dao.Monster;
import org.example.dto.util.Coordinate;
import org.example.services.AppService;
import org.example.services.DirectionService;
import org.example.services.HealthService;
import org.example.services.MonsterService;
import org.example.services.PlayerService;
import org.example.services.RoomDirectionService;
import org.example.services.SaveService;
import org.example.util.ScheduleUtility;

import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import static org.example.dto.util.HealthBarConstants.GREEN_HP_BAR;
import static org.example.enums.Direction.LEFT;
import static org.example.enums.MonsterType.SERPENT;
import static org.example.util.ResourcePathUtility.FONT_STYLE_PATH;
import static org.example.util.ResourcePathUtility.SERPENT_ATTACK_LEFT_PATH;
import static org.example.util.ResourcePathUtility.SERPENT_ATTACK_RIGHT_PATH;
import static org.example.util.ResourcePathUtility.SERPENT_DEATH_LEFT_PATH;
import static org.example.util.ResourcePathUtility.SERPENT_DEATH_RIGHT_PATH;
import static org.example.util.ResourcePathUtility.SERPENT_LEFT_PATH;
import static org.example.util.ResourcePathUtility.SERPENT_RIGHT_PATH;

public class Garden1Controller extends GameScreenController implements Initializable {

    private Timeline serpent1AttackSchedule;
    private Timeline serpent1ResetSchedule;
    private Timeline resetPlayerSchedule;

    @FXML
    private ImageView serpent1;
    private final String serpent1Key = "garden1serpent1";
    private final int serpent1HealthCapacity = 30;

    @FXML
    private ProgressBar serpent1HealthBar;

    @FXML
    private Pane parentPane;

    private static final int CHALLENGE_ROOM_INDEX = 1;

    public Garden1Controller(AppService appService,
                             PlayerService playerService,
                             DirectionService directionService,
                             RoomDirectionService roomDirectionService,
                             HealthService healthService,
                             SaveService saveService, Scene scene) {
        super(
                appService,
                playerService,
                directionService,
                roomDirectionService,
                healthService,
                saveService,
                scene
        );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.monsterService = new MonsterService();
        this.playerService.setMonsterService(this.monsterService);
        this.initGameScreenController(this.monsterService, null);
        this.resetPlayerSchedule = ScheduleUtility.generatePlayerResetSchedule(0.5,
                this.playerService);
        this.playerService.registerTimeline(this.resetPlayerSchedule);
        this.serpent1ResetSchedule = ScheduleUtility.generateMonsterResetSchedule(
                0.5,
                this.monsterService,
                this.serpent1Key,
                this.serpent1,
                SERPENT_LEFT_PATH,
                SERPENT_RIGHT_PATH
        );
        if (!this.appService.getPlayerState().getChallengeRoomsComplete()[CHALLENGE_ROOM_INDEX]
                && this.initializeChallengeRoom()) {
            this.playerService.setChallengeRoomLockOn(true);
            this.generateMonstersForRoom(this.parentPane,
                    Arrays.asList(new Coordinate().setX(200).setY(200),
                            new Coordinate().setX(500).setY(500),
                            new Coordinate().setX(1400).setY(500),
                            new Coordinate().setX(1500).setY(200)),
                    Arrays.asList("garden1serpent2", "garden1serpent3",
                            "garden1serpent4", "garden1serpent5")
            );
            this.appService.addMonsterKilled(this.serpent1Key);
        } else if (!this.appService
                .getPlayerState().getMonstersKilled().contains(this.serpent1Key)) {
            this.setupSerpent1();
            this.playerService.registerTimeline(this.serpent1AttackSchedule);
        }
        boolean[] challengeRoomsComplete = this.appService
                .getPlayerState().getChallengeRoomsComplete();
        challengeRoomsComplete[CHALLENGE_ROOM_INDEX] = true;
        this.appService.setPlayerState(this.appService
                .getPlayerState().setChallengeRoomsComplete(challengeRoomsComplete));
    }

    private void generateMonstersForRoom(Pane pane,
                                         List<Coordinate> monsterCoordinates,
                                         List<String> monsterKeys) {
        for (int i = 0; i < monsterKeys.size(); i++) {
            ImageView imageView = new ImageView(
                    new Image(Paths.get(SERPENT_LEFT_PATH).toUri().toString()));
            imageView.setTranslateX(monsterCoordinates.get(i).getX());
            imageView.setTranslateY(monsterCoordinates.get(i).getY());
            imageView.setFitHeight(150);
            imageView.setFitWidth(150);

            ProgressBar healthBar = new ProgressBar();
            healthBar.setOpacity(0.8);
            healthBar.setProgress(1.0);
            healthBar.setPrefWidth(150);
            healthBar.setPrefHeight(10);
            healthBar.getStylesheets().add(FONT_STYLE_PATH);
            healthBar.getStyleClass().add(GREEN_HP_BAR);
            healthBar.setLayoutX(monsterCoordinates.get(i).getX());
            healthBar.setLayoutY(monsterCoordinates.get(i).getY());

            this.monsterService.addMonster(monsterKeys.get(i), new Monster()
                    .setHealth(this.serpent1HealthCapacity)
                    .setHealthCapacity(this.serpent1HealthCapacity)
                    .setRange(4.0)
                    .setAttack(20)
                    .setAccuracy(0.5)
                    .setKillReward(500)
                    .setMonsterType(SERPENT)
                    .setImageView(imageView)
                    .setHealthBar(healthBar)
                    .setOrientation(LEFT)
                    .setDeathAnimationLeft(
                            new Image(
                                    Paths.get(SERPENT_DEATH_LEFT_PATH).toUri().toString()))
                    .setDeathAnimationRight(
                            new Image(
                                    Paths.get(SERPENT_DEATH_RIGHT_PATH).toUri().toString()))
                    .setKey(monsterKeys.get(i)));

            Timeline monsterSchedule = ScheduleUtility.generateMonsterAttackSchedule(1.0,
                    this.appService,
                    monsterKeys.get(i),
                    this.playerService,
                    this.monsterService,
                    this.healthService,
                    this.resetPlayerSchedule,
                    ScheduleUtility.generateMonsterResetSchedule(0.5,
                            this.monsterService,
                            monsterKeys.get(i),
                            imageView,
                            SERPENT_LEFT_PATH,
                            SERPENT_RIGHT_PATH),
                    Timeline.INDEFINITE,
                    SERPENT_ATTACK_LEFT_PATH,
                    SERPENT_ATTACK_RIGHT_PATH);
            this.playerService.registerTimeline(monsterSchedule);
            monsterSchedule.play();

            pane.getChildren().add(0, imageView);
            pane.getChildren().add(0, healthBar);
        }
    }

    private void setupSerpent1() {
        this.serpent1.setTranslateX(1000);
        this.serpent1.setTranslateY(400);
        this.serpent1.setVisible(true);
        this.serpent1HealthBar.setTranslateX(1010);
        this.serpent1HealthBar.setTranslateY(360);
        this.serpent1HealthBar.setVisible(true);
        this.monsterService.addMonster(
                this.serpent1Key,
                new Monster()
                        .setHealth(this.serpent1HealthCapacity)
                        .setHealthCapacity(this.serpent1HealthCapacity)
                        .setRange(5.0)
                        .setAttack(10)
                        .setAccuracy(0.5)
                        .setKillReward(150)
                        .setMonsterType(SERPENT)
                        .setImageView(this.serpent1)
                        .setHealthBar(this.serpent1HealthBar)
                        .setOrientation(LEFT)
                        .setDeathAnimationLeft(
                                new Image(Paths.get(SERPENT_DEATH_LEFT_PATH).toUri().toString()))
                        .setDeathAnimationRight(
                                new Image(Paths.get(SERPENT_DEATH_RIGHT_PATH).toUri().toString()))
                        .setKey(this.serpent1Key));
        this.serpent1AttackSchedule = ScheduleUtility.generateMonsterAttackSchedule(
                1.0,
                this.appService,
                this.serpent1Key,
                this.playerService,
                this.monsterService,
                this.healthService,
                this.resetPlayerSchedule,
                this.serpent1ResetSchedule,
                Timeline.INDEFINITE,
                SERPENT_ATTACK_LEFT_PATH,
                SERPENT_ATTACK_RIGHT_PATH
        );
        this.serpent1AttackSchedule.play();
    }
}
