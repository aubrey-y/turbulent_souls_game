package org.example.dto;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import org.example.enums.MonsterType;

public class Monster {
    private double health;

    private double healthCapacity;

    private double range;

    private int attack;

    private double accuracy;

    private MonsterType monsterType;

    private ImageView imageView;

    private ProgressBar healthBar;

    private Label healthText;

    public Monster() {

    }

    public Monster(MonsterType monsterType,
                   double health,
                   double healthCapacity,
                   double range,
                   int attack,
                   double accuracy,
                   ImageView imageView,
                   ProgressBar healthBar,
                   Label healthText) {
        this.monsterType = monsterType;
        this.health = health;
        this.healthCapacity = healthCapacity;
        this.range = range;
        this.attack = attack;
        this.accuracy = accuracy;
        this.imageView = imageView;
        this.healthBar = healthBar;
        this.healthText = healthText;
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
}
