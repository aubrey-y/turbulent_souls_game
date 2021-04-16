package org.example.dto;

import org.example.enums.WeaponType;

import static org.example.util.ResourcePathUtility.MASTER_MAGIC_PATH;

public class MasterMagic extends Weapon {
    public MasterMagic() {
        this.name = "Musaev's Master Flame";
        this.type = WeaponType.MAGIC;
        this.attack = 30;
        this.range = 5.0;
        this.quantity = 1;
        this.imagePath = MASTER_MAGIC_PATH;
        this.description = "Unleashes the full extent of a mage's power.";
        this.price = 600;
    }
}
