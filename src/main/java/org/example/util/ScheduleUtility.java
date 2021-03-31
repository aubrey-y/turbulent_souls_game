package org.example.util;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.effect.ColorAdjust;
import javafx.util.Duration;
import org.example.App;
import org.example.services.AppService;
import org.example.services.HealthService;
import org.example.services.MonsterService;
import org.example.services.PlayerService;


public class ScheduleUtility {

    public static Timeline generateMonsterAttackSchedule(double attacksPerSecond,
                                                         AppService appService,
                                                         String monsterKey,
                                                         PlayerService playerService,
                                                         MonsterService monsterService,
                                                         HealthService healthService,
                                                         Timeline resetPlayerHitSchedule,
                                                         int cycleCount) {
        Timeline timeline = new Timeline();
        Timeline finalTimeline = timeline;
        timeline = new Timeline(new KeyFrame(Duration.seconds(1/attacksPerSecond), actionEvent -> {
            if(appService.getMonstersKilled().contains(monsterKey)) {
                finalTimeline.stop();
                return;
            }
            if(monsterService.playerIsInRangeOfMonster(
                    monsterKey,
                    playerService.getImageView().getTranslateX(),
                    playerService.getImageView().getTranslateY())) {
                Integer attack = monsterService.rollMonsterAttack(monsterKey);
                if(attack != null) {
                    healthService.applyHealthModifier(-1 * attack);
                    playerService.getImageView().setEffect(new ColorAdjust(-0.17, 0.0, 0.0, 0.0));
                    resetPlayerHitSchedule.play();
                }
            }
        }));
        timeline.setCycleCount(cycleCount);
        return timeline;
    }

    public static Timeline generatePlayerResetSchedule(double duration,
                                                       PlayerService playerService) {
        Timeline timeline = new Timeline();
        Timeline finalTimeline = timeline;
        timeline = new Timeline(new KeyFrame(Duration.seconds(duration), actionEvent -> {
            playerService.getImageView().setEffect(
                    new ColorAdjust(0.0, 0.0, 0.0, 0.0));
        }));
        timeline.setCycleCount(1);
        return timeline;
    }

    public static Timeline checkBoss(AppService appService, MonsterService monsterService) {
        Timeline timeline = new Timeline();
        Timeline finalTimeline = timeline;
        timeline = new Timeline(new KeyFrame(Duration.seconds(1.0), actionEvent -> {
            if (monsterService.getMonstersRemaining() == 0) {
                FXMLLoader loader = new FXMLLoader(App.class.getResource("winScreen.fxml"));
                appService.setRoot(loader);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        return timeline;
    }
}
