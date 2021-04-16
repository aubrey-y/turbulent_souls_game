package org.example.services;

import org.example.dto.Monster;
import org.example.dto.Weapon;
import org.example.enums.Direction;
import org.example.util.AnimationDurationUtility;
import org.example.util.ScheduleUtility;

import java.util.HashMap;
import java.util.Map;
import static org.example.dto.HealthBarConstants.GREEN_HP_BAR;
import static org.example.dto.HealthBarConstants.RED_HP_BAR;
import static org.example.dto.HealthBarConstants.HP_BAR_THRESHOLD;
import static org.example.dto.HealthBarConstants.BAR_CSS_CLASSES;
import static org.example.enums.Direction.LEFT;


public class MonsterService {

    private Map<String, Monster> monsterMapping = new HashMap<>();

    public static final double TILE_SIZE = 100.0;

    private int monstersKilled;

    public MonsterService() {

    }

    public void addMonster(String key, Monster value) {
        this.monsterMapping.put(key, value);
    }

    public String attackNearestMonster(Weapon weapon,
                                       double playerX,
                                       double playerY,
                                       boolean devMode) {
        String monsterKeyIfDead = null;
        String nearestMonsterKey = this.getNearestLivingMonster(playerX, playerY);
        Monster nearestMonster = this.monsterMapping.get(nearestMonsterKey);
        if (nearestMonster == null || distanceBetween(
                nearestMonster.getImageView().getTranslateX(),
                nearestMonster.getImageView().getTranslateY(),
                playerX,
                playerY
        ) / TILE_SIZE > weapon.getRange()) {
            return null;
        }
        double currentHealth = nearestMonster.getHealth();
        if (!devMode) {
            currentHealth += (-1 * weapon.getAttack());
        } else {
            currentHealth += (-1 * weapon.getAttack() * 100);
        }
        if (currentHealth <= 0.0) {
            this.monstersKilled++;
            monsterKeyIfDead = nearestMonsterKey;
            currentHealth = 0.0;
        }
        nearestMonster.setHealth(currentHealth);
        nearestMonster.getHealthBar().getStyleClass().removeAll(BAR_CSS_CLASSES);
        if (currentHealth <= HP_BAR_THRESHOLD * nearestMonster.getHealthCapacity()) {
            nearestMonster.getHealthBar().getStyleClass().add(RED_HP_BAR);
        } else {
            nearestMonster.getHealthBar().getStyleClass().add(GREEN_HP_BAR);
        }
        nearestMonster
                .getHealthBar()
                .setProgress(currentHealth / nearestMonster.getHealthCapacity());
        addMonster(nearestMonsterKey, nearestMonster);
        return monsterKeyIfDead;
    }

    private String getNearestLivingMonster(double playerX, double playerY) {
        Double minDistance = Double.MAX_VALUE;
        String closestMonsterKey = null;
        for (String key : this.monsterMapping.keySet()) {
            Monster currentMonster = this.monsterMapping.get(key);
            if (!currentMonster.isAlive()) {
                continue;
            }
            double currentDistance = this.distanceBetween(
                    currentMonster.getImageView().getTranslateX(),
                    currentMonster.getImageView().getTranslateY(),
                    playerX,
                    playerY);
            if (currentDistance < minDistance) {
                minDistance = currentDistance;
                closestMonsterKey = key;
            }
        }
        return closestMonsterKey;
    }

    private double distanceBetween(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public boolean playerIsInRangeOfMonster(String monsterKey, double playerX, double playerY) {
        Monster monster = this.monsterMapping.get(monsterKey);
        return this.distanceBetween(
                monster.getImageView().getTranslateX(),
                monster.getImageView().getTranslateY(),
                playerX,
                playerY) / TILE_SIZE <= monster.getRange();
    }

    public Integer rollMonsterAttack(String monsterKey) {
        Monster monster = this.monsterMapping.get(monsterKey);
        if (Math.random() <= monster.getAccuracy()) {
            return monster.getAttack();
        } else {
            return null;
        }
    }

    public int getMonstersRemaining() {
        return this.monsterMapping.size() - this.monstersKilled;
    }

    public Direction getMonsterOrientation(String key) {
        return this.monsterMapping.get(key).getOrientation();
    }

    public Monster getMonsterForKey(String key) {
        return this.monsterMapping.get(key);
    }

    public void initiateDeathAnimation(String key) {
        Monster monster = this.monsterMapping.get(key);
        monster.getHealthBar().setVisible(false);
        if (monster.getOrientation() == LEFT) {
            monster.getImageView().setImage(monster.getDeathAnimationLeft());
        } else {
            monster.getImageView().setImage(monster.getDeathAnimationRight());
        }
        ScheduleUtility.generateMonsterDeathResetSchedule(
                AnimationDurationUtility.getDeathDurationForMonsterType(monster.getMonsterType()),
                monster
        ).play();
    }

    public Map<String, Monster> getMonsterMapping() {
        return monsterMapping;
    }

    public MonsterService setMonsterMapping(Map<String, Monster> monsterMapping) {
        this.monsterMapping = monsterMapping;
        return this;
    }
}
