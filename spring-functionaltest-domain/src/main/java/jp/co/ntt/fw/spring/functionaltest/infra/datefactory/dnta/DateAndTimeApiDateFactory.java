/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.infra.datefactory.dnta;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.JapaneseDate;

//固定の日付を返す
public class DateAndTimeApiDateFactory {

    private static final int YEAR = 2015;

    private static final int MONTH = 12;

    private static final int DAY = 25;

    private static final int HOUR = 23;

    private static final int MINUTE = 30;

    private static final int SECOND = 59;

    private static final int NANO_OF_SECOND = 567;

    private static final ZoneOffset ZONE_OFFSET = ZoneOffset.ofHours(9);

    private static final ZoneId ZONE_ID = ZoneId.of("Asia/Tokyo");

    public static LocalDate newLocalDate() {
        return LocalDate.of(YEAR, MONTH, DAY);
    }

    public static LocalTime newLocalTime() {
        return LocalTime.of(HOUR, MINUTE, SECOND);
    }

    public static LocalDateTime newLocalDateTime() {
        return LocalDateTime.of(YEAR, MONTH, DAY, HOUR, MINUTE, SECOND);
    }

    public static OffsetTime newOffsetTime() {
        return OffsetTime.of(HOUR, MINUTE, SECOND, NANO_OF_SECOND, ZONE_OFFSET);
    }

    public static OffsetDateTime newOffsetDateTime() {
        return OffsetDateTime.of(YEAR, MONTH, DAY, HOUR, MINUTE, SECOND,
                NANO_OF_SECOND, ZONE_OFFSET);
    }

    public static ZonedDateTime newZonedDateTime() {
        return ZonedDateTime.of(YEAR, MONTH, DAY, HOUR, MINUTE, SECOND,
                NANO_OF_SECOND, ZONE_ID);
    }

    public static ZonedDateTime newSpecifiedZonedDateTime(String zone) {
        return ZonedDateTime.of(YEAR, MONTH, DAY, HOUR, MINUTE, SECOND,
                NANO_OF_SECOND, ZoneId.of(zone));
    }

    public static JapaneseDate newSpecifiedJapaneseDate() {
        return JapaneseDate.of(YEAR, MONTH, DAY);
    }
}
