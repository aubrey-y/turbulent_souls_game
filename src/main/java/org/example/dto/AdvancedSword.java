package org.example.dto;

import org.example.enums.WeaponType;

import static org.example.util.ResourcePathUtility.ADVANCED_SWORD_PATH;
import static org.example.util.ResourcePathUtility.ADVANCED_SWORD_PLAYER_LEFT;
import static org.example.util.ResourcePathUtility.ADVANCED_SWORD_PLAYER_RIGHT;

public class AdvancedSword extends Weapon {
    public AdvancedSword() {
        this.name = "Agil's Axe";
        this.type = WeaponType.SWORD;
        this.attack = 15; // Basic 10
        this.range = 2.0;
        this.quantity = 1;
        this.imagePath = ADVANCED_SWORD_PATH;
        this.description = "A powerful axe that relies on brute strength to wield."; //Change
        this.price = 300; // Basic 200
        this.animationLeft = ADVANCED_SWORD_PLAYER_LEFT;
        this.animationRight = ADVANCED_SWORD_PLAYER_RIGHT;
    }
}