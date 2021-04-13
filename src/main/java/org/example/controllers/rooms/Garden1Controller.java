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
import static org.example.enums.MonsterType.SERPENT;
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
    private final int serpent1HealthCapacity = 10;

    @FXML
    private ProgressBar serpent1HealthBar;

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
        this.goldService = new GoldService(this.appService, this.getGoldAmount());
        this.monsterService = new MonsterService(this.goldService);
        this.playerService.setMonsterService(this.monsterService);
        this.initGameScreenController(this.monsterService);
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
        if (!this.appService.getMonstersKilled().contains(this.serpent1Key)) {
            this.setupSerpent1();
            this.playerService.registerTimeline(this.serpent1AttackSchedule);
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
                        .setAttack(2)
                        .setAccuracy(0.5)
                        .setKillReward(400)
                        .setMonsterType(SERPENT)
                        .setImageView(this.serpent1)
                        .setHealthBar(this.serpent1HealthBar)
                        .setOrientation(LEFT)
                        .setDeathAnimationLeft(
                                new Image(Paths.get(SERPENT_DEATH_LEFT_PATH).toUri().toString()))
                        .setDeathAnimationRight(
                                new Image(Paths.get(SERPENT_DEATH_RIGHT_PATH).toUri().toString())));
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
