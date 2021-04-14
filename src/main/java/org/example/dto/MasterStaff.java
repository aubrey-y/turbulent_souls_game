package org.example.dto;

import org.example.enums.WeaponType;

import static org.example.util.ResourcePathUtility.MASTER_STAFF_PATH;

public class MasterStaff extends Weapon {
    public MasterStaff() {
        this.name = "Master Staff";
        this.type = WeaponType.STAFF;
        this.attack = 13; // Expert 11
        this.range = 100.0;
        this.quantity = 1;
        this.imagePath = MASTER_STAFF_PATH;
        this.description = "A dangerous weapon only master wizards can wield.";
        this.price = 500; // Expert 400
    }
}