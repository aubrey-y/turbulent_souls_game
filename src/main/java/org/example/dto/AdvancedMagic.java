package org.example.dto;

import org.example.enums.WeaponType;

import static org.example.util.ResourcePathUtility.ADVANCED_MAGIC_PATH;

public class AdvancedMagic extends Weapon {
    public AdvancedMagic() {
        this.name = "Advanced Magic";
        this.type = WeaponType.MAGIC;
        this.attack = 12;
        this.range = 5.0;
        this.quantity = 1;
        this.imagePath = ADVANCED_MAGIC_PATH;
        this.description = "Unpredictable and powerful.";
        this.price = 300;
    }
}
