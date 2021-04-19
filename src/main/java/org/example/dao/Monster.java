package org.example.dao;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.enums.Direction;
import org.example.enums.MonsterType;

public class Monster {
    private double health;

    private double healthCapacity;

    private double range;

    private int attack;

    private double accuracy;

    private int killReward;

    private MonsterType monsterType;

    private ImageView imageView;

    private ProgressBar healthBar;

    private Label healthText;

    private Direction orientation;

    private Image deathAnimationLeft;

    private Image deathAnimationRight;

    private String key;

    public Monster() {

    }

    public Monster(MonsterType monsterType,
                   double health,
                   double healthCapacity,
                   double range,
                   int attack,
                   double accuracy,
                   Object... varargs) {
        this.monsterType = monsterType;
        this.health = health;
        this.healthCapacity = healthCapacity;
        this.range = range;
        this.attack = attack;
        this.accuracy = accuracy;
        this.killReward = (int) varargs[0];
        this.imageView = (ImageView) varargs[1];
        this.healthBar = (ProgressBar) varargs[2];
        this.healthText = (Label) varargs[3];
        this.key = (String) varargs[4];
    }

    public boolean isAlive() {
        return this.health > 0.0;
    }

    public double getHealth() {
        return health;
    }

    public Monster setHealth(double health) {
        this.health = health;
        return this;
    }

    public double getHealthCapacity() {
        return healthCapacity;
    }

    public Monster setHealthCapacity(double healthCapacity) {
        this.healthCapacity = healthCapacity;
        return this;
    }

    public double getRange() {
        return range;
    }

    public Monster setRange(double range) {
        this.range = range;
        return this;
    }

    public int getAttack() {
        return attack;
    }

    public Monster setAttack(int attack) {
        this.attack = attack;
        return this;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public Monster setAccuracy(double accuracy) {
        this.accuracy = accuracy;
        return this;
    }

    public int getKillReward() {
        return killReward;
    }

    public Monster setKillReward(int killReward) {
        this.killReward = killReward;
        return this;
    }

    public MonsterType getMonsterType() {
        return monsterType;
    }

    public Monster setMonsterType(MonsterType monsterType) {
        this.monsterType = monsterType;
        return this;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Monster setImageView(ImageView imageView) {
        this.imageView = imageView;
        return this;
    }

    public ProgressBar getHealthBar() {
        return healthBar;
    }

    public Monster setHealthBar(ProgressBar healthBar) {
        this.healthBar = healthBar;
        return this;
    }

    public Label getHealthText() {
        return healthText;
    }

    public Monster setHealthText(Label healthText) {
        this.healthText = healthText;
        return this;
    }

    public Direction getOrientation() {
        return orientation;
    }

    public Monster setOrientation(Direction orientation) {
        this.orientation = orientation;
        return this;
    }

    public Image getDeathAnimationLeft() {
        return deathAnimationLeft;
    }

    public Monster setDeathAnimationLeft(Image deathAnimationLeft) {
        this.deathAnimationLeft = deathAnimationLeft;
        return this;
    }

    public Image getDeathAnimationRight() {
        return deathAnimationRight;
    }

    public Monster setDeathAnimationRight(Image deathAnimationRight) {
        this.deathAnimationRight = deathAnimationRight;
        return this;
    }

    public String getKey() {
        return key;
    }

    public Monster setKey(String key) {
        this.key = key;
        return this;
    }
}
