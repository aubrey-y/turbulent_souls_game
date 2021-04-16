package org.example.dto;

import static org.example.enums.PotionType.SPEED;
import static org.example.util.ResourcePathUtility.BASIC_SPEED_PATH;

public class BasicSpeedPotion extends Potion {
    public BasicSpeedPotion() {
        this.name = "Basic Speed Potion";
        this.type = SPEED;
        this.statLabel = "Speed Increase %:";
        this.statValue = 100;
        this.quantity = 1;
        this.imagePath = BASIC_SPEED_PATH;
        this.description = "This will enhance your movement speed. The effects may stack a "
            + "maximum of (1) times.";
        this.price = 150;
    }
}
