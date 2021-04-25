package org.example.dto.weapons;

import org.example.dao.Weapon;
import org.example.enums.WeaponType;

import static org.example.util.ResourcePathUtility.ALL_MAGIC_PLAYER_LEFT;
import static org.example.util.ResourcePathUtility.ALL_MAGIC_PLAYER_RIGHT;
import static org.example.util.ResourcePathUtility.BASIC_MAGIC_PATH;
import static org.example.util.ResourcePathUtility.BASIC_MAGIC_PLAYER_ATTACK_LEFT;
import static org.example.util.ResourcePathUtility.BASIC_MAGIC_PLAYER_ATTACK_RIGHT;

public class BasicMagic extends Weapon {
    public BasicMagic() {
        this.name = "Basic Magic";
        this.type = WeaponType.MAGIC;
        this.attack = 7;
        this.range = 5.0;
        this.quantity = 1;
        this.imagePath = BASIC_MAGIC_PATH;
        this.description = this.type + ": Handed down from a veteran wizard...";
        this.price = 200;
        this.animationLeft = ALL_MAGIC_PLAYER_LEFT;
        this.animationRight = ALL_MAGIC_PLAYER_RIGHT;
        this.attackAnimationLeft = BASIC_MAGIC_PLAYER_ATTACK_LEFT;
        this.attackAnimationRight = BASIC_MAGIC_PLAYER_ATTACK_RIGHT;
    }
}
