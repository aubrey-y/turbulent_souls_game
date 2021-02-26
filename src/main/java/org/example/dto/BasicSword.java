package org.example.dto;

import org.example.enums.WeaponType;

public class BasicSword extends Weapon {
    public BasicSword() {
        this.name = "Basic Sword";
        this.type = WeaponType.SWORD;
        this.attack = 10;
    }
}
