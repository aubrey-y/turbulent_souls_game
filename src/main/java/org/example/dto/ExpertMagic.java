package org.example.dto;

import org.example.enums.WeaponType;

import static org.example.util.ResourcePathUtility.ALL_MAGIC_PLAYER_LEFT;
import static org.example.util.ResourcePathUtility.ALL_MAGIC_PLAYER_RIGHT;
import static org.example.util.ResourcePathUtility.EXPERT_MAGIC_PATH;

public class ExpertMagic extends Weapon {
    public ExpertMagic() {
        this.name = "Tokoyami's Claw";
        this.type = WeaponType.MAGIC;
        this.attack = 20;
        this.range = 5.0;
        this.quantity = 1;
        this.imagePath = EXPERT_MAGIC_PATH;
        this.description = "Unpredictable and powerful.";
        this.price = 450;
        this.animationLeft = ALL_MAGIC_PLAYER_LEFT;
        this.animationRight = ALL_MAGIC_PLAYER_RIGHT;
    }
}