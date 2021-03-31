package org.example.util;

import org.example.enums.MonsterType;

import java.util.Map;

import static org.example.enums.MonsterType.CASTLE_BULL;
import static org.example.enums.MonsterType.DARK_KNIGHT;
import static org.example.enums.MonsterType.GUINEA_PIG;
import static org.example.enums.MonsterType.SERPENT;
import static org.example.enums.MonsterType.SLIME;
import static org.example.enums.MonsterType.WATER_BULL;
import static org.example.enums.MonsterType.WHITE_DRAGON;

public class DeathDurationUtility {

    private static Map<MonsterType, Double> durationMap = Map.of(
            SLIME, 1.0,
            GUINEA_PIG, 1.0,
            WHITE_DRAGON, 1.0,
            CASTLE_BULL, 1.0,
            SERPENT, 1.0,
            WATER_BULL, 1.0,
            DARK_KNIGHT, 1.0
    );

    public static Double getDurationForMonsterType(MonsterType monsterType) {
        return durationMap.get(monsterType);
    }
}
