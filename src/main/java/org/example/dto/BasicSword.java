package org.example.dto;

import org.example.enums.WeaponType;

import static org.example.util.ResourcePathUtility.BASIC_SWORD_PATH;

public class BasicSword extends Weapon {
    public BasicSword() {
        this.name = "Basic Sword";
        this.type = WeaponType.SWORD;
        this.attack = 10;
        this.range = 2.0;
        this.quantity = 1;
        this.imagePath = BASIC_SWORD_PATH;
        this.description = "Forged in the castles of Mordor... a rudimentary battle-worn sword.";
    }
}
