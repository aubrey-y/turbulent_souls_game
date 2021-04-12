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
import static org.example.enums.MonsterType.DARK_KNIGHT;
import static org.example.util.ResourcePathUtility.DARK_KNIGHT_ATTACK_LEFT_PATH;
import static org.example.util.ResourcePathUtility.DARK_KNIGHT_ATTACK_RIGHT_PATH;
import static org.example.util.ResourcePathUtility.DARK_KNIGHT_DEATH_LEFT_PATH;
import static org.example.util.ResourcePathUtility.DARK_KNIGHT_DEATH_RIGHT_PATH;
import static org.example.util.ResourcePathUtility.DARK_KNIGHT_LEFT_PATH;
import static org.example.util.ResourcePathUtility.DARK_KNIGHT_RIGHT_PATH;

public class Castle3Controller extends GameScreenController implements Initializable {

    private Timeline darkknight1AttackSchedule;
    private Timeline darkknight1ResetSchedule;
    private Timeline resetPlayerSchedule;

    @FXML
    private ImageView darkknight1;
    private final String darkknight1Key = "castle3darkknight1";
    private final int darkknight1HealthCapacity = 10;

    @FXML
    private ProgressBar darkknight1HealthBar;

    public Castle3Controller(AppService appService,
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
        this.darkknight1ResetSchedule = ScheduleUtility.generateMonsterResetSchedule(
                0.5,
                this.monsterService,
                this.darkknight1Key,
                this.darkknight1,
                DARK_KNIGHT_ATTACK_LEFT_PATH,
                DARK_KNIGHT_ATTACK_RIGHT_PATH
        );
        if (!this.appService.getMonstersKilled().contains(this.darkknight1Key)) {
            this.setupDarkknight1();
            this.playerService.registerTimeline(this.darkknight1AttackSchedule);
        }
    }

    private void setupDarkknight1() {
        this.darkknight1.setTranslateX(1000);
        this.darkknight1.setTranslateY(400);
        this.darkknight1.setVisible(true);
        this.darkknight1HealthBar.setTranslateX(995);
        this.darkknight1HealthBar.setTranslateY(350);
        this.darkknight1HealthBar.setVisible(true);
        this.monsterService.addMonster(
                this.darkknight1Key,
                new Monster()
                        .setHealth(this.darkknight1HealthCapacity)
                        .setHealthCapacity(this.darkknight1HealthCapacity)
                        .setRange(5.0)
                        .setAttack(2)
                        .setAccuracy(0.5)
                        .setMonsterType(DARK_KNIGHT)
                        .setImageView(this.darkknight1)
                        .setHealthBar(this.darkknight1HealthBar)
                        .setOrientation(LEFT)
                        .setDeathAnimationLeft(
                                new Image(
                                        Paths.get(DARK_KNIGHT_DEATH_LEFT_PATH).toUri().toString()))
                        .setDeathAnimationRight(
                                new Image(
                                        Paths.get(DARK_KNIGHT_DEATH_RIGHT_PATH).toUri().toString()))
        );
        this.darkknight1AttackSchedule = ScheduleUtility.generateMonsterAttackSchedule(
                1.0,
                this.appService,
                this.darkknight1Key,
                this.playerService,
                this.monsterService,
                this.healthService,
                this.resetPlayerSchedule,
                this.darkknight1ResetSchedule,
                Timeline.INDEFINITE,
                DARK_KNIGHT_LEFT_PATH,
                DARK_KNIGHT_RIGHT_PATH
        );
        this.darkknight1AttackSchedule.play();
    }
}
