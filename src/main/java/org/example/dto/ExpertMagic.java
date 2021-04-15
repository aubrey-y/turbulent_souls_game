package org.example.dto;

import org.example.enums.WeaponType;

import static org.example.util.ResourcePathUtility.EXPERT_MAGIC_PATH;

public class ExpertMagic extends Weapon {
    public ExpertMagic() {
        this.name = "Expert Magic";
        this.type = WeaponType.MAGIC;
        this.attack = 20;
        this.range = 5.0;
        this.quantity = 1;
        this.imagePath = EXPERT_MAGIC_PATH;
        this.description = "Channeled from within, controlled and reliable.";
        this.price = 450;
        this.listingPriority = 10;
    }
}
