package org.example.util;

import org.example.dao.PlayerState;

import java.time.LocalDateTime;
import java.util.Comparator;

import static org.example.util.DateTimeFormatUtility.DATE_TIME_FORMATTER;

public class PlayerStateByLastUpdatedComparator implements Comparator<PlayerState> {

    @Override
    public int compare(PlayerState p1, PlayerState p2) {
        return -1 * compare(LocalDateTime.parse(p1.getLastUpdated(), DATE_TIME_FORMATTER),
                LocalDateTime.parse(p2.getLastUpdated(), DATE_TIME_FORMATTER));
    }

    private static int compare(LocalDateTime a, LocalDateTime b) {
        return a.isBefore(b) ? -1
                : a.isAfter(b) ? 1
                : 0;
    }
}
