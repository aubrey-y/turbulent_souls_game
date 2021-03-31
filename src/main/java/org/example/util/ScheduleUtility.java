package org.example.util;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.example.dto.Monster;
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
                                                         Timeline resetPlayerHitSchedule,
                                                         Timeline resetMonsterSchedule,
                                                         int cycleCount,
                                                         String leftUri,
                                                         String rightUri) {
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
                    Monster monster = monsterService.getMonsterForKey(monsterKey);
                    if(monster.getOrientation() == LEFT) {
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
            if(monsterService.getMonsterOrientation(monsterKey) == LEFT) {
                monster.setImage(new Image(Paths.get(leftUri).toUri().toString()));
            } else {
                monster.setImage(new Image(Paths.get(rightUri).toUri().toString()));
            }
        }));
        timeline.setCycleCount(1);
        return timeline;
    }
}
