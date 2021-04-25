package org.example.dto.weapons;

import org.example.dao.Weapon;
import org.example.enums.WeaponType;

import static org.example.util.ResourcePathUtility.ALL_MAGIC_PLAYER_LEFT;
import static org.example.util.ResourcePathUtility.ALL_MAGIC_PLAYER_RIGHT;
import static org.example.util.ResourcePathUtility.EXPERT_MAGIC_PATH;
import static org.example.util.ResourcePathUtility.EXPERT_MAGIC_PLAYER_ATTACK_LEFT;
import static org.example.util.ResourcePathUtility.EXPERT_MAGIC_PLAYER_ATTACK_RIGHT;

public class ExpertMagic extends Weapon {
    public ExpertMagic() {
        this.name = "Tokoyami's Claw";
        this.type = WeaponType.MAGIC;
        this.attack = 20;
        this.range = 5.0;
        this.quantity = 1;
        this.imagePath = EXPERT_MAGIC_PATH;
        this.description = "Wizard: Unpredictable and powerful";
        this.price = 450;
        this.animationLeft = ALL_MAGIC_PLAYER_LEFT;
        this.animationRight = ALL_MAGIC_PLAYER_RIGHT;
        this.attackAnimationLeft = EXPERT_MAGIC_PLAYER_ATTACK_LEFT;
        this.attackAnimationRight = EXPERT_MAGIC_PLAYER_ATTACK_RIGHT;
    }
}
