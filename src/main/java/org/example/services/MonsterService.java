package org.example.services;

import org.example.dto.Monster;
import org.example.dto.Weapon;

import java.util.HashMap;
import java.util.Map;
import static org.example.dto.HealthBarConstants.GREEN_HP_BAR;
import static org.example.dto.HealthBarConstants.RED_HP_BAR;
import static org.example.dto.HealthBarConstants.HP_BAR_THRESHOLD;
import static org.example.dto.HealthBarConstants.BAR_CSS_CLASSES;


public class MonsterService {

    private Map<Integer, Monster> monsterMapping = new HashMap<>();

    public static final double TILE_SIZE = 100.0;

    public MonsterService() {

    }

    public void addMonster(Integer key, Monster value) {
        this.monsterMapping.put(key, value);
    }

    public void attackNearestMonster(Weapon weapon, double playerX, double playerY) {
        Integer nearestMonsterKey = this.getNearestMonster(playerX, playerY);
        Monster nearestMonster = this.monsterMapping.get(nearestMonsterKey);
        if(nearestMonster == null || distanceBetween(
                nearestMonster.getImageView().getTranslateX(),
                nearestMonster.getImageView().getTranslateY(),
                playerX,
                playerY
        )/TILE_SIZE > weapon.getRange()) {
            return;
        }
        double currentHealth = nearestMonster.getHealth();
        currentHealth += (-1 * weapon.getAttack());
        currentHealth = Math.max(0.0, currentHealth);
        nearestMonster.setHealth(currentHealth);
        nearestMonster.getHealthBar().getStyleClass().removeAll(BAR_CSS_CLASSES);
        if(currentHealth <= HP_BAR_THRESHOLD * nearestMonster.getHealthCapacity()) {
            nearestMonster.getHealthBar().getStyleClass().add(RED_HP_BAR);
        } else {
            nearestMonster.getHealthBar().getStyleClass().add(GREEN_HP_BAR);
        }
        nearestMonster.getHealthBar().setProgress(currentHealth / nearestMonster.getHealthCapacity());
        addMonster(nearestMonsterKey, nearestMonster);
    }

    private Integer getNearestMonster(double playerX, double playerY) {
        Double minDistance = Double.MAX_VALUE;
        Integer closestMonsterKey = null;
        for(Integer key : this.monsterMapping.keySet()) {
            Monster currentMonster = this.monsterMapping.get(key);
            double currentDistance = this.distanceBetween(
                    currentMonster.getImageView().getTranslateX(),
                    currentMonster.getImageView().getTranslateY(),
                    playerX,
                    playerY);
            if(currentDistance < minDistance) {
                minDistance = currentDistance;
                closestMonsterKey = key;
            }
        }
        return closestMonsterKey;
    }

    private double distanceBetween(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
//
//    public void moveX(double value) {
//        this.imageView.setTranslateX(value);
//    }
//
//    public void moveY(double value) {
//        this.imageView.setTranslateY(value);
//    }
//
//    public ImageView getImageView() {
//        return imageView;
//    }
//
//    public MonsterService setImageView(ImageView imageView) {
//        this.imageView = imageView;
//        return this;
//    }
}
