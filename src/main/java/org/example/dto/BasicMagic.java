package org.example.dto;

import org.example.enums.WeaponType;

import static org.example.util.ResourcePathUtility.BASIC_MAGIC_PATH;

public class BasicMagic extends Weapon {
    public BasicMagic() {
        this.name = "Basic Magic";
        this.type = WeaponType.MAGIC;
        this.attack = 7;
        this.range = 5.0;
        this.quantity = 1;
        this.imagePath = BASIC_MAGIC_PATH;
        this.description = "Handed down from a veteran wizard, can cast basic spells.";
        this.price = 200;
        this.listingPriority = 8;
    }
}
