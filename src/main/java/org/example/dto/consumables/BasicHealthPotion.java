package org.example.dto.consumables;


import static org.example.enums.PotionType.HEALTH;
import static org.example.util.ResourcePathUtility.BASIC_HEALTH_PATH;

public class BasicHealthPotion extends Potion {
    public BasicHealthPotion() {
        this.name = "Basic Health Potion";
        this.type = HEALTH;
        this.statLabel = "Health Increase:";
        this.statValue = 10;
        this.quantity = 1;
        this.imagePath = BASIC_HEALTH_PATH;
        this.description = "This is that feel good juice";
        this.price = 100;
    }
}
