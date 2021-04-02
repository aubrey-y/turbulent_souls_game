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
import org.example.enums.Direction;
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
import static org.example.enums.MonsterType.WATER_BULL;

public class Garden2Controller extends GameScreenController implements Initializable {

    private Timeline waterbull1AttackSchedule;
    private Timeline waterbull1ResetSchedule;
    private Timeline resetPlayerSchedule;

    @FXML
    private ImageView waterbull1;
    private final String waterbull1Key = "garden2waterbull1";
    private final int waterbull1HealthCapacity = 10;

    @FXML
    private ProgressBar waterbull1HealthBar;

    public Garden2Controller(AppService appService,
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
        this.waterbull1ResetSchedule = ScheduleUtility.generateMonsterResetSchedule(
                0.5,
                this.monsterService,
                this.waterbull1Key,
                this.waterbull1,
                "src/main/resources/static/images/monsters/idle/water_bull_left.png",
                null
        );
        if(!this.appService.getMonstersKilled().contains(this.waterbull1Key)) {
            this.setupWaterbull1();
            this.playerService.registerTimeline(this.waterbull1AttackSchedule);
        }
    }

    private void setupWaterbull1() {
        this.waterbull1.setTranslateX(1000);
        this.waterbull1.setTranslateY(400);
        this.waterbull1.setVisible(true);
        this.waterbull1HealthBar.setTranslateX(945);
        this.waterbull1HealthBar.setTranslateY(360);
        this.waterbull1HealthBar.setVisible(true);
        this.monsterService.addMonster(
                this.waterbull1Key,
                new Monster()
                        .setHealth(this.waterbull1HealthCapacity)
                        .setHealthCapacity(this.waterbull1HealthCapacity)
                        .setRange(5.0)
                        .setAttack(2)
                        .setAccuracy(0.5)
                        .setMonsterType(WATER_BULL)
                        .setImageView(this.waterbull1)
                        .setHealthBar(this.waterbull1HealthBar)
                        .setOrientation(LEFT)
                        .setDeathAnimationLeft(new Image(Paths.get("src/main/resources/static/images/monsters/gifs/waterbull_death_left.gif").toUri().toString())));
        this.waterbull1AttackSchedule = ScheduleUtility.generateMonsterAttackSchedule(
                1.0,
                this.appService,
                this.waterbull1Key,
                this.playerService,
                this.monsterService,
                this.healthService,
                this.resetPlayerSchedule,
                this.waterbull1ResetSchedule,
                Timeline.INDEFINITE,
                "src/main/resources/static/images/monsters/gifs/waterbull_attack_left.gif",
                null
        );
        this.waterbull1AttackSchedule.play();
    }
}
