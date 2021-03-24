package org.example.dto;

import org.example.enums.WeaponType;

public abstract class Weapon {

    protected String name;

    protected WeaponType type;

    protected int attack;

    protected double range;

    public String getName() {
        return name;
    }

    public Weapon setName(String name) {
        this.name = name;
        return this;
    }

    public WeaponType getType() {
        return type;
    }

    public Weapon setType(WeaponType type) {
        this.type = type;
        return this;
    }

    public int getAttack() {
        return attack;
    }

    public Weapon setAttack(int attack) {
        this.attack = attack;
        return this;
    }

    public double getRange() {
        return range;
    }

    public Weapon setRange(double range) {
        this.range = range;
        return this;
    }
}
