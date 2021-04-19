package org.example.dto.weapons;

import org.example.dao.Weapon;
import org.example.enums.WeaponType;

import static org.example.util.ResourcePathUtility.BASIC_SWORD_PATH;
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
        this.description = "Forged in the castles of Mordor... a rudimentary battle-worn sword.";
        this.price = 200;
        this.animationLeft = BASIC_SWORD_PLAYER_LEFT;
        this.animationRight = BASIC_SWORD_PLAYER_RIGHT;
    }
}
