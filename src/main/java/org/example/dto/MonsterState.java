package org.example.dto;
import org.example.enums.MonsterType;

public class MonsterState {
    private double health;

    private double healthCapacity;

    private MonsterType monster;


    public MonsterState(MonsterType monster, double health, double healthCapacity) {
        this.monster = monster;
        this.health = health;
        this.healthCapacity = healthCapacity;
    }

    public double getHealth() {
        return this.health;
    }

    public double getHealthCapacity() {
        return this.healthCapacity;
    }
}
