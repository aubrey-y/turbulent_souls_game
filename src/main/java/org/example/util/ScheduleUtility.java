package org.example.util;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.example.dto.Monster;
import org.example.App;
import org.example.services.AppService;
import org.example.services.HealthService;
import org.example.services.MonsterService;
import org.example.services.PlayerService;

import java.nio.file.Paths;

import static org.example.enums.Direction.LEFT;


public class ScheduleUtility {

    public static Timeline generateMonsterAttackSchedule(double attacksPerSecond,
                                                         AppService appService,
                                                         String monsterKey,
                                                         PlayerService playerService,
                                                         MonsterService monsterService,
                                                         HealthService healthService,
                                                         Object... varargs) {
        Timeline resetPlayerHitSchedule = (Timeline) varargs[0];
        Timeline resetMonsterSchedule = (Timeline) varargs[1];
        Integer cycleCount = (Integer) varargs[2];
        String leftUri = (String) varargs[3];
        String rightUri = (String) varargs[4];
        Timeline timeline = new Timeline();
        Timeline finalTimeline = timeline;
        timeline = new Timeline(new KeyFrame(Duration.seconds(1 / attacksPerSecond), actionEvnt -> {
            if (appService.getMonstersKilled().contains(monsterKey)) {
                finalTimeline.stop();
                return;
            }
            if (monsterService.playerIsInRangeOfMonster(
                    monsterKey,
                    playerService.getImageView().getTranslateX(),
                    playerService.getImageView().getTranslateY())) {
                Integer attack = monsterService.rollMonsterAttack(monsterKey);
                if (attack != null) {
                    boolean playerAlive = healthService.applyHealthModifier(-1 * attack);
                    if (!playerAlive) {
                        playerService.terminateExistingTimelines();
                        FXMLLoader loader = new FXMLLoader(App.class.getResource("gameOver.fxml"));
                        appService.setRoot(loader);
                    }
                    playerService.getImageView().setEffect(new ColorAdjust(-0.17, 0.0, 0.0, 0.0));
                    Monster monster = monsterService.getMonsterForKey(monsterKey);
                    if (monster.getOrientation() == LEFT) {
                        monster.getImageView()
                                .setImage(new Image(Paths.get(leftUri).toUri().toString()));
                    } else {
                        monster.getImageView()
                                .setImage(new Image(Paths.get(rightUri).toUri().toString()));
                    }
                    resetPlayerHitSchedule.play();
                    resetMonsterSchedule.play();
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

    public static Timeline generateMonsterResetSchedule(double duration,
                                                        MonsterService monsterService,
                                                        String monsterKey,
                                                        ImageView monster,
                                                        String leftUri,
                                                        String rightUri) {
        Timeline timeline = new Timeline();
        Timeline finalTimeline = timeline;
        timeline = new Timeline(new KeyFrame(Duration.seconds(duration), actionEvent -> {
            if (monsterService.getMonsterOrientation(monsterKey) == LEFT) {
                monster.setImage(new Image(Paths.get(leftUri).toUri().toString()));
            } else {
                monster.setImage(new Image(Paths.get(rightUri).toUri().toString()));
            }
        }));
        timeline.setCycleCount(1);
        return timeline;
    }

    public static Timeline generateMonsterDeathResetSchedule(double duration,
                                                             Monster monster) {
        Timeline timeline = new Timeline();
        Timeline finalTimeline = timeline;
        timeline = new Timeline(new KeyFrame(Duration.seconds(duration), actionEvent -> {
            monster.getImageView().setVisible(false);
        }));
        timeline.setCycleCount(1);
        return timeline;
    }

    public static Timeline generateBossCheckSchedule(AppService appService,
                                                     MonsterService monsterService) {
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
