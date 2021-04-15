package org.example.util;

import org.example.dto.BasicHealthPotion;
import org.example.dto.BasicSpeedPotion;
import org.example.dto.BasicStrengthPotion;
import org.example.dto.Item;
import org.example.dto.BasicSword;
import org.example.dto.BasicMagic;
import org.example.dto.BasicStaff;
import org.example.dto.AdvancedMagic;
import org.example.dto.AdvancedStaff;
import org.example.dto.AdvancedSword;
import org.example.dto.ExpertMagic;
import org.example.dto.ExpertStaff;
import org.example.dto.ExpertSword;
import org.example.dto.MasterMagic;
import org.example.dto.MasterStaff;
import org.example.dto.MasterSword;
import org.example.enums.RoomType;

import java.util.Map;

import static org.example.util.ResourcePathUtility.BASIC_HEALTH_PATH;
import static org.example.util.ResourcePathUtility.BASIC_SPEED_PATH;
import static org.example.util.ResourcePathUtility.BASIC_STRENGTH_PATH;
import static org.example.util.ResourcePathUtility.BASIC_SWORD_PATH;
import static org.example.util.ResourcePathUtility.BASIC_STAFF_PATH;
import static org.example.util.ResourcePathUtility.BASIC_MAGIC_PATH;
import static org.example.util.ResourcePathUtility.ADVANCED_SWORD_PATH;
import static org.example.util.ResourcePathUtility.ADVANCED_STAFF_PATH;
import static org.example.util.ResourcePathUtility.ADVANCED_MAGIC_PATH;
import static org.example.util.ResourcePathUtility.EXPERT_SWORD_PATH;
import static org.example.util.ResourcePathUtility.EXPERT_STAFF_PATH;
import static org.example.util.ResourcePathUtility.EXPERT_MAGIC_PATH;
import static org.example.util.ResourcePathUtility.MASTER_SWORD_PATH;
import static org.example.util.ResourcePathUtility.MASTER_STAFF_PATH;
import static org.example.util.ResourcePathUtility.MASTER_MAGIC_PATH;

public class TraderInventoryUtility {

    private static Map<String, Item> forestTraderInventory = Map.of(
            BASIC_HEALTH_PATH, new BasicHealthPotion(),
            BASIC_SPEED_PATH, new BasicSpeedPotion(),
            BASIC_STRENGTH_PATH, new BasicStrengthPotion(),
            BASIC_SWORD_PATH, new BasicSword(),
            BASIC_STAFF_PATH, new BasicStaff(),
            BASIC_MAGIC_PATH, new BasicMagic(),
            ADVANCED_SWORD_PATH, new AdvancedSword(),
            ADVANCED_MAGIC_PATH, new AdvancedMagic(),
            ADVANCED_STAFF_PATH, new AdvancedStaff()
    );

    private static Map<String, Item> castleTraderInventory = Map.of(
            BASIC_HEALTH_PATH, new BasicHealthPotion(),
            BASIC_SPEED_PATH, new BasicSpeedPotion(),
            BASIC_STRENGTH_PATH, new BasicStrengthPotion(),
            EXPERT_SWORD_PATH, new ExpertSword(),
            EXPERT_MAGIC_PATH, new ExpertMagic(),
            EXPERT_STAFF_PATH, new ExpertStaff()
    );

    private static Map<String, Item> gardenTraderInventory = Map.of(
            BASIC_HEALTH_PATH, new BasicHealthPotion(),
            BASIC_SPEED_PATH, new BasicSpeedPotion(),
            BASIC_STRENGTH_PATH, new BasicStrengthPotion(),
            MASTER_SWORD_PATH, new MasterSword(),
            MASTER_MAGIC_PATH, new MasterMagic(),
            MASTER_STAFF_PATH, new MasterStaff()
    );

    public static Map<String, Item> getTraderInventoryForRoomType(RoomType roomType) {
        switch (roomType) {
        case FOREST_TRADER:
            return forestTraderInventory;
        case GARDEN_TRADER:
            return gardenTraderInventory;
        case CASTLE_TRADER:
            return castleTraderInventory;
        default:
            return null;
        }
    }
}
