package org.example.dto;

import org.example.enums.WeaponType;

public class BasicBow extends Weapon {
    public BasicBow() {
        this.name = "Basic Bow";
        this.type = WeaponType.BOW;
        this.attack = 8;
        this.range = 10.0;
    }
}
