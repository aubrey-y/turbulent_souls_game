package org.example.dto;

import org.example.enums.WeaponType;

public class BasicMagic extends Weapon {
    public BasicMagic() {
        this.name = "Basic Magic";
        this.type = WeaponType.MAGIC;
        this.attack = 8;
        this.range = 10.0;
    }
}
