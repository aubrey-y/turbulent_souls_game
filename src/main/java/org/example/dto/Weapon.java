package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.example.enums.WeaponType;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = BasicSword.class, name = "SWORD"),
        @JsonSubTypes.Type(value = BasicStaff.class, name = "STAFF"),
        @JsonSubTypes.Type(value = BasicMagic.class, name = "MAGIC")
})
public abstract class Weapon extends Item {

    protected WeaponType type;

    protected int attack;

    protected double range;

    public Weapon() {

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
