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

import static org.example.enums.MonsterType.GUINEA_PIG;

public class Forest2Controller extends GameScreenController implements Initializable {

    private MonsterService monsterService;

    private Timeline guineapig1AttackSchedule;
    private Timeline resetPlayerSchedule;

    @FXML
    private ImageView guineapig1;
    private final String guineapig1Key = "forest2guineapig1";
    private final int guineapig1HealthCapacity = 10;

    @FXML
    private ProgressBar guineapig1HealthBar;

    public Forest2Controller(AppService appService,
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
        if(!this.appService.getMonstersKilled().contains(this.guineapig1Key)) {
            this.setupGuineapig1();
            this.playerService.registerTimeline(this.guineapig1AttackSchedule);
        }
    }

    private void setupGuineapig1() {
        this.guineapig1.setTranslateX(1000);
        this.guineapig1.setTranslateY(400);
        this.guineapig1.setVisible(true);
        this.guineapig1HealthBar.setTranslateX(1015);
        this.guineapig1HealthBar.setTranslateY(360);
        this.guineapig1HealthBar.setVisible(true);
        this.monsterService.addMonster(
                this.guineapig1Key,
                new Monster()
                        .setHealth(this.guineapig1HealthCapacity)
                        .setHealthCapacity(this.guineapig1HealthCapacity)
                        .setRange(5.0)
                        .setAttack(2)
                        .setAccuracy(0.5)
                        .setMonsterType(GUINEA_PIG)
                        .setImageView(this.guineapig1)
                        .setHealthBar(this.guineapig1HealthBar));
        this.guineapig1AttackSchedule = ScheduleUtility.generateMonsterAttackSchedule(
                1.0,
                this.appService,
                this.guineapig1Key,
                this.playerService,
                this.monsterService,
                this.healthService,
                this.resetPlayerSchedule,
                Timeline.INDEFINITE
        );
        this.guineapig1AttackSchedule.play();
    }
}
