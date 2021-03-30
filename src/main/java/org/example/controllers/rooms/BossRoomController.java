package org.example.controllers.rooms;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
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
import java.util.ResourceBundle;

import static org.example.enums.MonsterType.DARK_KNIGHT;

public class BossRoomController extends GameScreenController implements Initializable {

    private MonsterService monsterService;

    private Timeline boss1AttackSchedule;
    private Timeline resetPlayerSchedule;

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
        this.initGameScreenController(this.monsterService);
        this.resetPlayerSchedule = ScheduleUtility.generatePlayerResetSchedule(0.5,
                this.playerService);
        this.playerService.registerTimeline(this.resetPlayerSchedule);
        if(!this.appService.getMonstersKilled().contains(this.boss1Key)) {
            this.setupBoss1();
            this.playerService.registerTimeline(this.boss1AttackSchedule);
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
                new Monster()
                        .setHealth(this.boss1HealthCapacity)
                        .setHealthCapacity(this.boss1HealthCapacity)
                        .setRange(5.0)
                        .setAttack(2)
                        .setAccuracy(0.5)
                        .setMonsterType(DARK_KNIGHT)
                        .setImageView(this.boss1)
                        .setHealthBar(this.boss1HealthBar));
        this.boss1AttackSchedule = ScheduleUtility.generateMonsterAttackSchedule(
                1.0,
                this.appService,
                this.boss1Key,
                this.playerService,
                this.monsterService,
                this.healthService,
                this.resetPlayerSchedule,
                Timeline.INDEFINITE
        );
        this.boss1AttackSchedule.play();
    }
}
