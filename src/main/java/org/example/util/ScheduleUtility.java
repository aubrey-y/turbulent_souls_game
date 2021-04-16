package org.example.util;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.example.dto.Item;
import org.example.dto.Monster;
import org.example.App;
import org.example.dto.PlayerState;
import org.example.dto.Potion;
import org.example.dto.Weapon;
import org.example.services.AppService;
import org.example.services.HealthService;
import org.example.services.MonsterService;
import org.example.services.PlayerService;

import java.nio.file.Paths;
import java.util.Map;

import static org.example.enums.Direction.LEFT;
import static org.example.services.PlayerService.DEFAULT_MOVE_SIZE;
import static org.example.util.ResourcePathUtility.GOLD_POUCH_PATH;


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

    public static Timeline generatePlayerAttackResetSchedule(double duration,
                                                             PlayerService playerService,
                                                             PlayerState playerState) {
        Timeline timeline = new Timeline();
        Timeline finalTimeline = timeline;
        timeline = new Timeline(new KeyFrame(Duration.seconds(duration), actionEvent -> {
            String imagePath;
            if (playerState.getSpawnOrientation() == LEFT) {
                imagePath = playerState.getActiveWeapon().getAnimationLeft();
            } else {
                imagePath = playerState.getActiveWeapon().getAnimationRight();
            }
            playerService.getImageView()
                    .setImage(new Image(Paths.get(imagePath).toUri().toString()));
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
            monster.getImageView().setImage(
                    new Image(Paths.get(GOLD_POUCH_PATH).toUri().toString()));
            monster.setRange(1.0);
            monster.getImageView().setFitHeight(100.0);
            monster.getImageView().setFitWidth(100.0);
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

    public static Timeline generateSpeedPotionSchedule(AppService appService,
                                                       PlayerService playerService) {
        Timeline timeline = new Timeline();
        Timeline finalTimeLine = timeline;
        timeline = new Timeline(new KeyFrame(Duration.seconds(30), actionEvent -> {
            playerService.setMoveSize(DEFAULT_MOVE_SIZE);
        }));
        timeline.setCycleCount(1);
        return timeline;
    }

    public static Timeline generateStrengthPotionSchedule(AppService appService, Potion potion) {
        Timeline timeline = new Timeline();
        Timeline finalTimeLine = timeline;
        timeline = new Timeline(new KeyFrame(Duration.seconds(30), actionEvent -> {
            Map<String, Item> weaponInventory = appService.getPlayerState().getWeaponInventory();
            for (String key : weaponInventory.keySet()) {
                Weapon weapon = (Weapon) weaponInventory.get(key);
                weapon.setAttack(weapon.getAttack() - potion.getStatValue());
                weaponInventory.put(key, weapon);
            }
            appService.setPlayerState(appService.getPlayerState()
                    .setWeaponInventory(weaponInventory));
        }));
        timeline.setCycleCount(1);
        return timeline;
    }
}
