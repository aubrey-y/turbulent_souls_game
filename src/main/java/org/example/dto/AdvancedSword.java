package org.example.dto;

import org.example.enums.WeaponType;

import static org.example.util.ResourcePathUtility.ADVANCED_SWORD_PATH;

public class AdvancedSword extends Weapon {
    public AdvancedSword() {
        this.name = "Advanced Sword";
        this.type = WeaponType.SWORD;
        this.attack = 15; // Basic 10
        this.range = 2.0;
        this.quantity = 1;
        this.imagePath = ADVANCED_SWORD_PATH;
        this.description = "A powerful axe that relies on brute strength to wield."; //Change
        this.price = 300; // Basic 200
    }
}
