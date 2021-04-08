package org.example.util;

import org.example.dto.Item;
import org.example.dto.Weapon;

import java.util.Map;

public class FieldComplianceUtility {

    public static final String PERIOD = ".";

    public static final String PERIOD_REGEX = "\\" + PERIOD;

    public static final String LEGAL_PERIOD = "<DOT>";

    public static void cleanseIllegalCharacters(Map<String, Item> dirtyMap) {
        for (String key : dirtyMap.keySet()) {
            if (key.contains(PERIOD)) {
                dirtyMap.put(key.replaceAll(PERIOD_REGEX, LEGAL_PERIOD), dirtyMap.get(key));
                dirtyMap.remove(key);
            } else if (key.contains(LEGAL_PERIOD)) {
                throw new RuntimeException("Dirty map contains intended replacement character");
            }
        }
    }

    public static void restoreIllegalCharacters(Map<String, Item> cleanMap) {
        for (String key : cleanMap.keySet()) {
            if (key.contains(LEGAL_PERIOD)) {
                cleanMap.put(key.replaceAll(LEGAL_PERIOD, PERIOD), cleanMap.get(key));
                cleanMap.remove(key);
            }
        }
    }
}
