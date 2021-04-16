package org.example.dto;

import org.example.enums.WeaponType;

import static org.example.util.ResourcePathUtility.MASTER_STAFF_PATH;
import static org.example.util.ResourcePathUtility.MASTER_STAFF_PLAYER_ATTACK_LEFT;
import static org.example.util.ResourcePathUtility.MASTER_STAFF_PLAYER_ATTACK_RIGHT;
import static org.example.util.ResourcePathUtility.MASTER_STAFF_PLAYER_LEFT;
import static org.example.util.ResourcePathUtility.MASTER_STAFF_PLAYER_RIGHT;

public class MasterStaff extends Weapon {
    public MasterStaff() {
        this.name = "King Karthik's Staff";
        this.type = WeaponType.STAFF;
        this.attack = 25;
        this.range = 12.0;
        this.quantity = 1;
        this.imagePath = MASTER_STAFF_PATH;
        this.description = "A dangerous weapon only master wizards can wield.";
        this.price = 600;
        this.animationLeft = MASTER_STAFF_PLAYER_LEFT;
        this.animationRight = MASTER_STAFF_PLAYER_RIGHT;
        this.attackAnimationLeft = MASTER_STAFF_PLAYER_ATTACK_LEFT;
        this.attackAnimationRight = MASTER_STAFF_PLAYER_ATTACK_RIGHT;
    }
}
