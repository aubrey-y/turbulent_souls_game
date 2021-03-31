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

public class BossRoomController extends GameScreenController implements Initializable {

    private MonsterService monsterService;

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
        this.boss1ResetSchedule = ScheduleUtility.generateMonsterResetSchedule(
                0.5,
                this.monsterService,
                this.boss1Key,
                this.boss1,
                "src/main/resources/static/images/monsters/gifs/dark_knight_attack_left.gif",
                null
        );
        if(!this.appService.getMonstersKilled().contains(this.boss1Key)) {
            this.setupBoss1();
            this.playerService.registerTimeline(this.boss1AttackSchedule);
            this.playerService.registerTimeline(this.boss1DeadSchedule);
        }
    }

    private void setupBoss1() {
        this.boss1.setTranslateX(1000);
        this.boss1.setTranslateY(400);
        this.boss1.setVisible(true);
        this.boss1HealthBar.setTranslateX(875);
        this.boss1HealthBar.setTranslateY(300);
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
                "src/main/resources/static/images/monsters/gifs/dark_knight_left.gif",
                "src/main/resources/static/images/monsters/gifs/dark_knight_right.gif"
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
                .setMonsterType(DARK_KNIGHT)
                .setImageView(this.boss1)
                .setHealthBar(this.boss1HealthBar)
                .setOrientation(LEFT)
                .setDeathAnimationLeft(
                        new Image(Paths.get("src/main/resources/static/images/monsters/gifs/dark_knight_death_left.gif").toUri().toString()))
                .setDeathAnimationRight(
                        new Image(Paths.get("src/main/resources/static/images/monsters/gifs/dark_knight_death_right.gif").toUri().toString()));

    }
}
