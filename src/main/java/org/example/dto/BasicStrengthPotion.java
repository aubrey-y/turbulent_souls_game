package org.example.dto;

import static org.example.enums.PotionType.STRENGTH;
import static org.example.util.ResourcePathUtility.BASIC_STRENGTH_PATH;

public class BasicStrengthPotion extends Potion {
    public BasicStrengthPotion() {
        this.name = "Basic Strength Potion";
        this.type = STRENGTH;
        this.statLabel = "Strength Increase:";
        this.statValue = 5;
        this.quantity = 1;
        this.imagePath = BASIC_STRENGTH_PATH;
        this.description = "Steroids, but without the needles";
    }
}
