package org.example.util;

import org.example.dto.Item;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FieldComplianceUtility {

    public static final String PERIOD = ".";

    public static final String PERIOD_REGEX = "\\" + PERIOD;

    public static final String LEGAL_PERIOD = "<DOT>";

    public static void cleanseIllegalCharacters(Map<String, Item> dirtyMap) {
        Iterator<Map.Entry<String, Item>> iterator = dirtyMap.entrySet().iterator();
        Map<String, Item> cleanEntries = new HashMap<>();
        while (iterator.hasNext()) {
            Map.Entry<String, Item> entry = iterator.next();
            if (entry.getKey().contains(PERIOD)) {
                cleanEntries.put(entry.getKey().replaceAll(PERIOD_REGEX, LEGAL_PERIOD), entry.getValue());
                iterator.remove();
            }
        }
        dirtyMap.putAll(cleanEntries);
    }

    public static void restoreIllegalCharacters(Map<String, Item> cleanMap) {
        Iterator<Map.Entry<String, Item>> iterator = cleanMap.entrySet().iterator();
        Map<String, Item> dirtyEntries = new HashMap<>();
        while (iterator.hasNext()) {
            Map.Entry<String, Item> entry = iterator.next();
            if (entry.getKey().contains(LEGAL_PERIOD)) {
                dirtyEntries.put(entry.getKey().replaceAll(LEGAL_PERIOD, PERIOD), entry.getValue());
                iterator.remove();
            }
        }
        cleanMap.putAll(dirtyEntries);
    }
}
