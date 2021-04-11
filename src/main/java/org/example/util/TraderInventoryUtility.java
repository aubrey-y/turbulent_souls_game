package org.example.util;

import org.example.dto.BasicHealthPotion;
import org.example.dto.BasicSpeedPotion;
import org.example.dto.BasicStaff;
import org.example.dto.BasicStrengthPotion;
import org.example.dto.BasicSword;
import org.example.dto.Item;

import java.util.Map;

import static org.example.util.ResourcePathUtility.BASIC_HEALTH_PATH;
import static org.example.util.ResourcePathUtility.BASIC_SPEED_PATH;
import static org.example.util.ResourcePathUtility.BASIC_STAFF_PATH;
import static org.example.util.ResourcePathUtility.BASIC_STRENGTH_PATH;
import static org.example.util.ResourcePathUtility.BASIC_SWORD_PATH;

public class TraderInventoryUtility {

    public static Map<String, Item> forestTraderInventory = Map.of(
            BASIC_HEALTH_PATH, new BasicHealthPotion(),
            BASIC_SPEED_PATH, new BasicSpeedPotion(),
            BASIC_STRENGTH_PATH, new BasicStrengthPotion(),
            BASIC_SWORD_PATH, new BasicSword());

    public static Map<String, Item> castleTraderInventory = Map.of(
            BASIC_HEALTH_PATH, new BasicHealthPotion(),
            BASIC_SPEED_PATH, new BasicSpeedPotion(),
            BASIC_STRENGTH_PATH, new BasicStrengthPotion(),
            BASIC_STAFF_PATH, new BasicStaff());

    public static Map<String, Item> gardenTraderInventory = Map.of(
            BASIC_HEALTH_PATH, new BasicHealthPotion(),
            BASIC_SPEED_PATH, new BasicSpeedPotion(),
            BASIC_STRENGTH_PATH, new BasicStrengthPotion(),
            BASIC_STAFF_PATH, new BasicStaff());
}
