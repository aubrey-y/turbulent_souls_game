package org.example.dto;

import org.example.enums.WeaponType;

import static org.example.util.ResourcePathUtility.MASTER_SWORD_PATH;
import static org.example.util.ResourcePathUtility.MASTER_SWORD_PLAYER_ATTACK_LEFT;
import static org.example.util.ResourcePathUtility.MASTER_SWORD_PLAYER_ATTACK_RIGHT;
import static org.example.util.ResourcePathUtility.MASTER_SWORD_PLAYER_LEFT;
import static org.example.util.ResourcePathUtility.MASTER_SWORD_PLAYER_RIGHT;

public class MasterSword extends Weapon {
    public MasterSword() {
        this.name = "Master Sword";
        this.type = WeaponType.SWORD;
        this.attack = 35;
        this.range = 2.0;
        this.quantity = 1;
        this.imagePath = MASTER_SWORD_PATH;
        this.description = "An extremely powerful double-sided scythe that harnesses electricity.";
        this.price = 600;
        this.animationLeft = MASTER_SWORD_PLAYER_LEFT;
        this.animationRight = MASTER_SWORD_PLAYER_RIGHT;
        this.attackAnimationLeft = MASTER_SWORD_PLAYER_ATTACK_LEFT;
        this.attackAnimationRight = MASTER_SWORD_PLAYER_ATTACK_RIGHT;
    }
}
