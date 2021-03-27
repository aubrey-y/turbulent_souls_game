package org.example.controllers.rooms;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.example.controllers.GameScreenController;
import org.example.dto.Monster;
import org.example.services.AppService;
import org.example.services.DirectionService;
import org.example.services.HealthService;
import org.example.services.MonsterService;
import org.example.services.PlayerService;
import org.example.services.RoomDirectionService;

import java.net.URL;
import java.util.ResourceBundle;

import static org.example.enums.MonsterType.SLIME;

public class Forest1Controller extends GameScreenController implements Initializable {

    private MonsterService monsterService;

    private Timeline slime1AttackSchedule;
    private Timeline resetPlayerHueSchedule;

    @FXML
    private ImageView slime1;
    private final int slime1Key = 1;
    private final int slime1HealthCapacity = 10;

    @FXML
    private ProgressBar slime1HealthBar;

    public Forest1Controller(AppService appService,
                             PlayerService playerService,
                             DirectionService directionService,
                             RoomDirectionService roomDirectionService,
                             HealthService healthService, Scene scene) {
        super(
                appService,
                playerService,
                directionService,
                roomDirectionService,
                healthService,
                scene
        );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.monsterService = new MonsterService();
        this.initGameScreenController(this.monsterService);
        slime1.setVisible(true);
        slime1.setTranslateX(1500);
        slime1.setTranslateY(400);
        slime1HealthBar.setVisible(true);
        slime1HealthBar.setTranslateX(1570);
        slime1HealthBar.setTranslateY(400);
        this.resetPlayerHueSchedule = new Timeline((new KeyFrame(Duration.seconds(0.5),
                resetActionEvent -> this.getPlayer().setEffect(
                        new ColorAdjust(0.0, 0.0, 0.0, 0.0)))));
        this.resetPlayerHueSchedule.setCycleCount(1);
        this.monsterService.addMonster(
                this.slime1Key,
                new Monster()
                        .setHealth(this.slime1HealthCapacity)
                        .setHealthCapacity(this.slime1HealthCapacity)
                        .setRange(5.0)
                        .setAttack(2)
                        .setAccuracy(0.5)
                        .setMonsterType(SLIME)
                        .setImageView(this.slime1)
                        .setHealthBar(this.slime1HealthBar));
        this.slime1AttackSchedule = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> {
            if(this.monstersKilled.contains(this.slime1Key)) {
                this.slime1AttackSchedule.stop();
                return;
            }
            if(this.monsterService.playerIsInRangeOfMonster(
                    this.slime1Key,
                    this.playerService.getImageView().getTranslateX(),
                    this.playerService.getImageView().getTranslateY())) {
                Integer attack = this.monsterService.rollMonsterAttack(this.slime1Key);
                if(attack != null) {
                    this.healthService.applyHealthModifier(-1 * attack);
                    this.getPlayer().setEffect(new ColorAdjust(-0.17, 0.0, 0.0, 0.0));
                    this.resetPlayerHueSchedule.play();
                }
            }
        }));
        this.slime1AttackSchedule.setCycleCount(Timeline.INDEFINITE);
        this.slime1AttackSchedule.play();
    }
}
