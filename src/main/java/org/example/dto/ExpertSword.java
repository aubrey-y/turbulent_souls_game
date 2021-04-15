package org.example.dto;

import org.example.enums.WeaponType;

import static org.example.util.ResourcePathUtility.EXPERT_SWORD_PATH;

public class ExpertSword extends Weapon {
    public ExpertSword() {
        this.name = "Expert Sword";
        this.type = WeaponType.SWORD;
        this.attack = 23;
        this.range = 2.0;
        this.quantity = 1;
        this.imagePath = EXPERT_SWORD_PATH;
        this.description = "War-torn, this sword utilizes the strength of its defeated enemies to increase its power."; //Change
        this.price = 450;
    }
}
