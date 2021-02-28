package org.example.dto;

import org.example.enums.WeaponType;

public class BasicStaff extends Weapon {
    public BasicStaff() {
        this.name = "Basic Staff";
        this.type = WeaponType.STAFF;
        this.attack = 7;
    }
}
