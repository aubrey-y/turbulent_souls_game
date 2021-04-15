package org.example.dto;

import org.example.enums.WeaponType;

import static org.example.util.ResourcePathUtility.BASIC_STAFF_PATH;

public class BasicStaff extends Weapon {
    public BasicStaff() {
        this.name = "Basic Staff";
        this.type = WeaponType.STAFF;
        this.attack = 5;
        this.range = 12.0;
        this.quantity = 1;
        this.imagePath = BASIC_STAFF_PATH;
        this.description = "A handcrafted stick with magical properties...";
        this.price = 200;
    }
}
