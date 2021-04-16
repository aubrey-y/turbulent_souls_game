package org.example.dto;

import org.example.enums.WeaponType;

import static org.example.util.ResourcePathUtility.ADVANCED_STAFF_PATH;
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
        this.description = "A delicate staff that harness ice.";
        this.price = 300;
        this.animationLeft = ADVANCED_STAFF_PLAYER_LEFT;
        this.animationRight = ADVANCED_STAFF_PLAYER_RIGHT;
    }
}
