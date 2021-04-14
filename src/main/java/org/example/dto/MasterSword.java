package org.example.dto;

import org.example.enums.WeaponType;

import static org.example.util.ResourcePathUtility.MASTER_SWORD_PATH;

public class MasterSword extends Weapon {
    public MasterSword() {
        this.name = "Master Sword";
        this.type = WeaponType.SWORD;
        this.attack = 16; // Expert 14
        this.range = 2.0;
        this.quantity = 1;
        this.imagePath = MASTER_SWORD_PATH;
        this.description = "An extremely powerful double-sided scythe that harnesses electricity.";
        this.price = 5000; // Expert 400
    }
}