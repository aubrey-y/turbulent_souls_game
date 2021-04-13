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
import org.example.services.GoldService;
import org.example.services.HealthService;
import org.example.services.MonsterService;
import org.example.services.PlayerService;
import org.example.services.RoomDirectionService;
import org.example.services.SaveService;
import org.example.util.ScheduleUtility;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import static org.example.enums.Direction.LEFT;
import static org.example.enums.MonsterType.KING_NAHTAN;
import static org.example.util.ResourcePathUtility.DARK_KNIGHT_ATTACK_LEFT_PATH;
import static org.example.util.ResourcePathUtility.DARK_KNIGHT_ATTACK_RIGHT_PATH;
import static org.example.util.ResourcePathUtility.KING_NAHTAN_DEATH_LEFT_PATH;
import static org.example.util.ResourcePathUtility.KING_NAHTAN_DEATH_RIGHT_PATH;
import static org.example.util.ResourcePathUtility.KING_NAHTAN_IDLE_LEFT_PATH;
import static org.example.util.ResourcePathUtility.KING_NAHTAN_IDLE_RIGHT_PATH;
import static org.example.util.ResourcePathUtility.KING_NAHTAN_ATTACK_LEFT_PATH;
import static org.example.util.ResourcePathUtility.KING_NAHTAN_ATTACK_RIGHT_PATH;
import static org.example.util.ResourcePathUtility.DARK_KNIGHT_LEFT_PATH;
import static org.example.util.ResourcePathUtility.DARK_KNIGHT_RIGHT_PATH;
import static org.example.util.ResourcePathUtility.DARK_KNIGHT_DEATH_LEFT_PATH;
import static org.example.util.ResourcePathUtility.DARK_KNIGHT_DEATH_RIGHT_PATH;

public class BossRoomController extends GameScreenController implements Initializable {

    private Timeline boss1AttackSchedule;
    private Timeline boss1ResetSchedule;
    private Timeline resetPlayerSchedule;
    private Timeline boss1DeadSchedule;

    @FXML
    private ImageView boss1;
    private final String boss1Key = "boss1";
    private final int boss1HealthCapacity = 10;

    @FXML
    private ProgressBar boss1HealthBar;

    public BossRoomController(AppService appService,
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
        this.goldService = new GoldService(this.appService, this.getGoldAmount());
        this.monsterService = new MonsterService(this.goldService);
        this.playerService.setMonsterService(this.monsterService);
        this.initGameScreenController(this.monsterService);
        this.resetPlayerSchedule = ScheduleUtility.generatePlayerResetSchedule(0.5,
                this.playerService);
        this.playerService.registerTimeline(this.resetPlayerSchedule);
        this.boss1ResetSchedule = ScheduleUtility.generateMonsterResetSchedule(
                0.5,
                this.monsterService,
                this.boss1Key,
                this.boss1,
                KING_NAHTAN_ATTACK_LEFT_PATH,
                KING_NAHTAN_ATTACK_RIGHT_PATH
        );
        if (!this.appService.getMonstersKilled().contains(this.boss1Key)) {
            this.setupBoss1();
            this.playerService.registerTimeline(this.boss1AttackSchedule);
            this.playerService.registerTimeline(this.boss1DeadSchedule);
        }
    }

    private void setupBoss1() {
        this.boss1.setTranslateX(1000);
        this.boss1.setTranslateY(400);
        this.boss1.setVisible(true);
        this.boss1HealthBar.setTranslateX(975);
        this.boss1HealthBar.setTranslateY(350);
        this.boss1HealthBar.setVisible(true);
        this.monsterService.addMonster(
                this.boss1Key,
                this.createBoss1());
        this.boss1AttackSchedule = ScheduleUtility.generateMonsterAttackSchedule(
                1.0,
                this.appService,
                this.boss1Key,
                this.playerService,
                this.monsterService,
                this.healthService,
                this.resetPlayerSchedule,
                this.boss1ResetSchedule,
                Timeline.INDEFINITE,
                KING_NAHTAN_IDLE_LEFT_PATH,
                KING_NAHTAN_IDLE_RIGHT_PATH
        );
        this.boss1AttackSchedule.play();

        this.boss1DeadSchedule = ScheduleUtility.generateBossCheckSchedule(
                this.appService,
                this.monsterService
        );
        this.boss1DeadSchedule.play();
    }

    private Monster createBoss1() {
        return new Monster()
                .setHealth(this.boss1HealthCapacity)
                .setHealthCapacity(this.boss1HealthCapacity)
                .setRange(5.0)
                .setAttack(2)
                .setAccuracy(0.5)
                .setKillReward(6400)
                .setMonsterType(KING_NAHTAN)
                .setImageView(this.boss1)
                .setHealthBar(this.boss1HealthBar)
                .setOrientation(LEFT)
                .setDeathAnimationLeft(
                        new Image(Paths.get(KING_NAHTAN_DEATH_LEFT_PATH).toUri().toString()))
                .setDeathAnimationRight(
                        new Image(Paths.get(KING_NAHTAN_DEATH_RIGHT_PATH).toUri().toString()));

    }
}
