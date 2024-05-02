package edu.aua.common.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

public class TimeService {
    private static final ZoneId ZONE_ID = ZoneOffset.UTC;

    public static LocalDateTime getUtcNow() {
        return LocalDateTime.now(ZONE_ID).truncatedTo(ChronoUnit.MILLIS);
    }
}
