package org.example.dto;

import org.example.enums.WeaponType;

import static org.example.util.ResourcePathUtility.EXPERT_STAFF_PATH;
import static org.example.util.ResourcePathUtility.EXPERT_STAFF_PLAYER_LEFT;
import static org.example.util.ResourcePathUtility.EXPERT_STAFF_PLAYER_RIGHT;

public class ExpertStaff extends Weapon {
    public ExpertStaff() {
        this.name = "Expert Staff";
        this.type = WeaponType.STAFF;
        this.attack = 18;
        this.range = 12.0;
        this.quantity = 1;
        this.imagePath = EXPERT_STAFF_PATH;
        this.description = "An evolved ice staff that freezes its enemies in their tracks.";
        this.price = 450;
        this.animationLeft = EXPERT_STAFF_PLAYER_LEFT;
        this.animationRight = EXPERT_STAFF_PLAYER_RIGHT;
    }
}
