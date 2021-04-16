package org.example.dto;

import org.example.enums.WeaponType;

import static org.example.util.ResourcePathUtility.EXPERT_SWORD_PATH;
import static org.example.util.ResourcePathUtility.EXPERT_SWORD_PLAYER_ATTACK_LEFT;
import static org.example.util.ResourcePathUtility.EXPERT_SWORD_PLAYER_ATTACK_RIGHT;
import static org.example.util.ResourcePathUtility.EXPERT_SWORD_PLAYER_LEFT;
import static org.example.util.ResourcePathUtility.EXPERT_SWORD_PLAYER_RIGHT;

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
        this.animationLeft = EXPERT_SWORD_PLAYER_LEFT;
        this.animationRight = EXPERT_SWORD_PLAYER_RIGHT;
        this.attackAnimationLeft = EXPERT_SWORD_PLAYER_ATTACK_LEFT;
        this.attackAnimationRight = EXPERT_SWORD_PLAYER_ATTACK_RIGHT;
    }
}
