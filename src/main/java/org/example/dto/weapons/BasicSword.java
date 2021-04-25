package org.example.dto.weapons;

import org.example.dao.Weapon;
import org.example.enums.WeaponType;

import static org.example.util.ResourcePathUtility.BASIC_SWORD_PATH;
import static org.example.util.ResourcePathUtility.BASIC_SWORD_PLAYER_ATTACK_LEFT;
import static org.example.util.ResourcePathUtility.BASIC_SWORD_PLAYER_ATTACK_RIGHT;
import static org.example.util.ResourcePathUtility.BASIC_SWORD_PLAYER_LEFT;
import static org.example.util.ResourcePathUtility.BASIC_SWORD_PLAYER_RIGHT;

public class BasicSword extends Weapon {
    public BasicSword() {
        this.name = "Basic Sword";
        this.type = WeaponType.SWORD;
        this.attack = 10;
        this.range = 2.0;
        this.quantity = 1;
        this.imagePath = BASIC_SWORD_PATH;
        this.description = this.type + ": Forged in the castles of Mordor...";
        this.price = 200;
        this.animationLeft = BASIC_SWORD_PLAYER_LEFT;
        this.animationRight = BASIC_SWORD_PLAYER_RIGHT;
        this.attackAnimationLeft = BASIC_SWORD_PLAYER_ATTACK_LEFT;
        this.attackAnimationRight = BASIC_SWORD_PLAYER_ATTACK_RIGHT;
    }
}
