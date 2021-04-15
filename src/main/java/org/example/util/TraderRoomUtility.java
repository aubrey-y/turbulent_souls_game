package org.example.util;

import org.example.enums.RoomType;

import java.util.Map;

import static org.example.enums.RoomType.CASTLE_TRADER;
import static org.example.enums.RoomType.FOREST_TRADER;
import static org.example.enums.RoomType.GARDEN_TRADER;
import static org.example.util.ResourcePathUtility.CASTLE_TRADER_PATH;
import static org.example.util.ResourcePathUtility.FOREST_TRADER_PATH;
import static org.example.util.ResourcePathUtility.GARDEN_TRADER_PATH;

public class TraderRoomUtility {
    private static Map<RoomType, String> traderImageMapping = Map.of(
            GARDEN_TRADER, GARDEN_TRADER_PATH,
            FOREST_TRADER, FOREST_TRADER_PATH,
            CASTLE_TRADER, CASTLE_TRADER_PATH
    );

    public static String correctTraderPathUtility(RoomType roomType) {
        return traderImageMapping.get(roomType);
    }
}
