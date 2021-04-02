package org.example.controllers.rooms;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.controllers.GameScreenController;
import org.example.dto.Monster;
import org.example.services.AppService;
import org.example.services.DirectionService;
import org.example.services.HealthService;
import org.example.services.MonsterService;
import org.example.services.PlayerService;
import org.example.services.RoomDirectionService;
import org.example.util.ScheduleUtility;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import static org.example.enums.Direction.LEFT;
import static org.example.enums.MonsterType.CASTLE_BULL;
import static org.example.util.ResourcePathUtility.CASTLE_BULL_ATTACK_LEFT_PATH;
import static org.example.util.ResourcePathUtility.CASTLE_BULL_DEATH_LEFT_PATH;
import static org.example.util.ResourcePathUtility.CASTLE_BULL_IDLE_LEFT_PATH;

public class Castle2Controller extends GameScreenController implements Initializable {

    private Timeline castlebull1AttackSchedule;
    private Timeline castlebull1ResetSchedule;
    private Timeline resetPlayerSchedule;

    @FXML
    private ImageView castlebull1;
    private final String castlebull1Key = "castle2castlebull1";
    private final int castlebull1HealthCapacity = 10;

    @FXML
    private ProgressBar castlebull1HealthBar;

    public Castle2Controller(AppService appService,
                             PlayerService playerService,
                             DirectionService directionService,
                             RoomDirectionService roomDirectionService,
                             HealthService healthService, Scene scene) {
        super(
                appService,
                playerService,
                directionService,
                roomDirectionService,
                healthService, scene
        );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.monsterService = new MonsterService();
        this.playerService.setMonsterService(this.monsterService);
        this.initGameScreenController(this.monsterService);
        this.resetPlayerSchedule = ScheduleUtility.generatePlayerResetSchedule(0.5,
                this.playerService);
        this.playerService.registerTimeline(this.resetPlayerSchedule);
        this.castlebull1ResetSchedule = ScheduleUtility.generateMonsterResetSchedule(
                0.5,
                this.monsterService,
                this.castlebull1Key,
                this.castlebull1,
                CASTLE_BULL_ATTACK_LEFT_PATH,
                null
        );
        if (!this.appService.getMonstersKilled().contains(this.castlebull1Key)) {
            this.setupCastlebull1();
            this.playerService.registerTimeline(this.castlebull1AttackSchedule);
        }
    }

    private void setupCastlebull1() {
        this.castlebull1.setTranslateX(1000);
        this.castlebull1.setTranslateY(400);
        this.castlebull1.setVisible(true);
        this.castlebull1HealthBar.setTranslateX(965);
        this.castlebull1HealthBar.setTranslateY(360);
        this.castlebull1HealthBar.setVisible(true);
        this.monsterService.addMonster(
                this.castlebull1Key,
                new Monster()
                        .setHealth(this.castlebull1HealthCapacity)
                        .setHealthCapacity(this.castlebull1HealthCapacity)
                        .setRange(5.0)
                        .setAttack(2)
                        .setAccuracy(0.5)
                        .setMonsterType(CASTLE_BULL)
                        .setImageView(this.castlebull1)
                        .setHealthBar(this.castlebull1HealthBar)
                        .setOrientation(LEFT)
                        .setDeathAnimationLeft(
                                new Image(
                                        Paths.get(CASTLE_BULL_DEATH_LEFT_PATH).toUri().toString()))
        );
        this.castlebull1AttackSchedule = ScheduleUtility.generateMonsterAttackSchedule(
                1.0,
                this.appService,
                this.castlebull1Key,
                this.playerService,
                this.monsterService,
                this.healthService,
                this.resetPlayerSchedule,
                this.castlebull1ResetSchedule,
                Timeline.INDEFINITE,
                CASTLE_BULL_IDLE_LEFT_PATH,
                null
        );
        this.castlebull1AttackSchedule.play();
    }
}
