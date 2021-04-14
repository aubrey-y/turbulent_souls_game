package org.example.dto;

import org.example.enums.WeaponType;

import static org.example.util.ResourcePathUtility.ADVANCED_STAFF_PATH;

public class AdvancedStaff extends Weapon {
    public AdvancedStaff() {
        this.name = "Advanced Staff";
        this.type = WeaponType.STAFF;
        this.attack = 9; // Basic 7
        this.range = 100.0;
        this.quantity = 1;
        this.imagePath = ADVANCED_STAFF_PATH;
        this.description = "A delicate staff that harness ice.";
        this.price = 300; // Basic 200
    }
}