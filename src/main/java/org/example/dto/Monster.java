package org.example.dto;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import org.example.enums.MonsterType;

public class Monster {
    private double health;

    private double healthCapacity;

    private MonsterType monster;

    private ProgressBar healthBar;

    private Label healthText;

    public Monster(MonsterType monster,
                   double health,
                   double healthCapacity,
                   ProgressBar healthBar,
                   Label healthText) {
        this.monster = monster;
        this.health = health;
        this.healthCapacity = healthCapacity;
        this.healthBar = healthBar;
        this.healthText = healthText;
    }


}
