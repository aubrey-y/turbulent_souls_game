package org.example.dto.weapons;

import org.example.dao.Weapon;
import org.example.enums.WeaponType;

import static org.example.util.ResourcePathUtility.ADVANCED_STAFF_PATH;
import static org.example.util.ResourcePathUtility.ADVANCED_STAFF_PLAYER_ATTACK_LEFT;
import static org.example.util.ResourcePathUtility.ADVANCED_STAFF_PLAYER_ATTACK_RIGHT;
import static org.example.util.ResourcePathUtility.ADVANCED_STAFF_PLAYER_LEFT;
import static org.example.util.ResourcePathUtility.ADVANCED_STAFF_PLAYER_RIGHT;

public class AdvancedStaff extends Weapon {
    public AdvancedStaff() {
        this.name = "Icicle Staff of Genshin";
        this.type = WeaponType.STAFF;
        this.attack = 10;
        this.range = 12.0;
        this.quantity = 1;
        this.imagePath = ADVANCED_STAFF_PATH;
        this.description = this.type + ": Delicate, icy, mysterious";
        this.price = 300;
        this.animationLeft = ADVANCED_STAFF_PLAYER_LEFT;
        this.animationRight = ADVANCED_STAFF_PLAYER_RIGHT;
        this.attackAnimationLeft = ADVANCED_STAFF_PLAYER_ATTACK_LEFT;
        this.attackAnimationRight = ADVANCED_STAFF_PLAYER_ATTACK_RIGHT;
    }
}
