package org.example.dto.weapons;

import org.example.dao.Weapon;
import org.example.enums.WeaponType;

import static org.example.util.ResourcePathUtility.EXPERT_STAFF_PATH;
import static org.example.util.ResourcePathUtility.EXPERT_STAFF_PLAYER_ATTACK_LEFT;
import static org.example.util.ResourcePathUtility.EXPERT_STAFF_PLAYER_ATTACK_RIGHT;
import static org.example.util.ResourcePathUtility.EXPERT_STAFF_PLAYER_LEFT;
import static org.example.util.ResourcePathUtility.EXPERT_STAFF_PLAYER_RIGHT;

public class ExpertStaff extends Weapon {
    public ExpertStaff() {
        this.name = "Arctic Spear";
        this.type = WeaponType.STAFF;
        this.attack = 18;
        this.range = 12.0;
        this.quantity = 1;
        this.imagePath = EXPERT_STAFF_PATH;
        this.description = "Expert Mage: \nLeaves enemies as frozen as their souls";
        this.price = 450;
        this.animationLeft = EXPERT_STAFF_PLAYER_LEFT;
        this.animationRight = EXPERT_STAFF_PLAYER_RIGHT;
        this.attackAnimationLeft = EXPERT_STAFF_PLAYER_ATTACK_LEFT;
        this.attackAnimationRight = EXPERT_STAFF_PLAYER_ATTACK_RIGHT;
    }
}
