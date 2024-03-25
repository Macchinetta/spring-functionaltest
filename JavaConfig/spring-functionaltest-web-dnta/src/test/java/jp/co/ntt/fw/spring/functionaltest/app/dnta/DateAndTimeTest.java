/*
 * Copyright(c) 2024 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dnta;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;

import java.time.Clock;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.JapaneseChronology;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.ThreadUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;
import org.junit.Test.None;

public class DateAndTimeTest {

    // 7.3.2.1.1

    @Test
    public void testDNTA0101_001() throws InterruptedException {
        LocalTime localTimeBefore = LocalTime.now();
        ThreadUtils.sleep(Duration.ofMillis(1L));
        LocalTime localTimeNow = LocalTime.now();
        ThreadUtils.sleep(Duration.ofMillis(1L));
        LocalTime localTimeAfter = LocalTime.now();

        assertThat(localTimeBefore.isBefore(localTimeNow), is(true));
        assertThat(localTimeNow.isBefore(localTimeAfter), is(true));

        // 日付が存在しないこと
        assertThrows(DateTimeException.class, () -> localTimeNow.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        // 時刻でフォーマット可能なこと
        assertThat(localTimeNow.format(DateTimeFormatter.ofPattern(
                "HH:mm:ss.SSSSSSSSS")), is(notNullValue()));
    }

    @Test
    public void testDNTA0101_002() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        LocalDate localDate = LocalDate.now();

        assertThat(localDate.getYear(), is(year));
        assertThat(localDate.getMonthValue(), is(month));
        assertThat(localDate.getDayOfMonth(), is(day));

        // 時刻が存在しないこと
        assertThrows(DateTimeException.class, () -> localDate.format(
                DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSSSSS")));

        // 日付でフォーマット可能なこと
        assertThat(localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                is(notNullValue()));
    }

    @Test
    public void testDNTA0101_003() throws InterruptedException {
        LocalDateTime localDateTimeBefore = LocalDateTime.now();
        ThreadUtils.sleep(Duration.ofMillis(1L));
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        ThreadUtils.sleep(Duration.ofMillis(1L));
        LocalDateTime localDateTimeAfter = LocalDateTime.now();

        assertThat(localDateTimeBefore.isBefore(localDateTimeNow), is(true));
        assertThat(localDateTimeNow.isBefore(localDateTimeAfter), is(true));

        // 日時でフォーマット可能なこと
        assertThat(localDateTimeNow.format(DateTimeFormatter.ofPattern(
                "yyyy-MM-dd HH:mm:ss.SSSSSSSSS")), is(notNullValue()));
    }

    // 7.3.2.1.2

    @Test
    public void testDNTA0102_001() {
        // 23:30:59
        LocalTime localTime = LocalTime.of(23, 30, 59);

        // 23:30:59.000000000 となっていること
        assertThat(localTime.getHour(), is(23));
        assertThat(localTime.getMinute(), is(30));
        assertThat(localTime.getSecond(), is(59));
        assertThat(localTime.getNano(), is(0));
    }

    @Test
    public void testDNTA0102_002() {
        // 2015/12/25
        LocalDate localDate = LocalDate.of(2015, 12, 25);

        assertThat(localDate.getYear(), is(2015));
        assertThat(localDate.getMonthValue(), is(12));
        assertThat(localDate.getDayOfMonth(), is(25));
    }

    @Test
    public void testDNTA0102_003() {
        // 2015/12/25 23:30:59
        LocalDateTime localDateTime = LocalDateTime.of(2015, 12, 25, 23, 30,
                59);

        assertThat(localDateTime.getYear(), is(2015));
        assertThat(localDateTime.getMonthValue(), is(12));
        assertThat(localDateTime.getDayOfMonth(), is(25));
        assertThat(localDateTime.getHour(), is(23));
        assertThat(localDateTime.getMinute(), is(30));
        assertThat(localDateTime.getSecond(), is(59));
        assertThat(localDateTime.getNano(), is(0));
    }

    @Test
    public void testDNTA0102_004() {
        // LeapYear(2012/2)
        LocalDate localDate1 = LocalDate.of(2012, 2, 1);

        // Last day of month(2012/2/29)
        LocalDate localDate2 = localDate1.with(TemporalAdjusters
                .lastDayOfMonth());

        // うるう年の29日になっていること
        assertThat(localDate2.getYear(), is(2012));
        assertThat(localDate2.getMonthValue(), is(2));
        assertThat(localDate2.getDayOfMonth(), is(29));
    }

    @Test
    public void testDNTA0102_005() {
        // LeapYear(2012/2)
        LocalDate localDate1 = LocalDate.of(2012, 2, 1);

        // Next monday（2012/2/6）
        LocalDate localDate2 = localDate1.with(TemporalAdjusters.next(
                DayOfWeek.MONDAY));

        // 次の週の月曜になっていること(2012/2/1は水曜日)
        assertThat(localDate2.getYear(), is(2012));
        assertThat(localDate2.getMonthValue(), is(2));
        assertThat(localDate2.getDayOfMonth(), is(6));
    }

    // 7.3.2.1.3

    @Test
    public void testDNTA0103_001() throws InterruptedException {
        // Ex, 12:30:11.567+09:00
        OffsetTime offsetTimeBefore = OffsetTime.now();
        ThreadUtils.sleep(Duration.ofMillis(1L));
        OffsetTime offsetTimeNow = OffsetTime.now();
        ThreadUtils.sleep(Duration.ofMillis(1L));
        OffsetTime offsetTimeAfter = OffsetTime.now();

        assertThat(offsetTimeBefore.isBefore(offsetTimeNow), is(true));
        assertThat(offsetTimeNow.isBefore(offsetTimeAfter), is(true));

        // 日付が存在しないこと
        assertThrows(DateTimeException.class, () -> offsetTimeNow.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        // 時刻でフォーマット可能なこと
        assertThat(offsetTimeNow.format(DateTimeFormatter.ofPattern(
                "HH:mm:ss.SSSSSSSSS")), is(notNullValue()));
        // +09:00
        assertThat(offsetTimeNow.getOffset(), is(ZoneOffset.of("+09:00")));
    }

    @Test
    public void testDNTA0103_002() throws InterruptedException {
        // Ex, 2015-12-25T12:30:11.567+09:00
        OffsetDateTime offsetDateTimeBefore = OffsetDateTime.now();
        ThreadUtils.sleep(Duration.ofMillis(1L));
        OffsetDateTime offsetDateTimeNow = OffsetDateTime.now();
        ThreadUtils.sleep(Duration.ofMillis(1L));
        OffsetDateTime offsetDateTimeAfter = OffsetDateTime.now();

        assertThat(offsetDateTimeBefore.isBefore(offsetDateTimeNow), is(true));
        assertThat(offsetDateTimeNow.isBefore(offsetDateTimeAfter), is(true));

        // 日時でフォーマット可能なこと
        assertThat(offsetDateTimeNow.format(DateTimeFormatter.ofPattern(
                "yyyy-MM-dd HH:mm:ss.SSSSSSSSS")), is(notNullValue()));
        // +09:00
        assertThat(offsetDateTimeNow.getOffset(), is(ZoneOffset.of("+09:00")));
    }

    @Test
    public void testDNTA0103_003() throws InterruptedException {
        // Ex, 2015-12-25T12:30:11.567+09:00[Asia/Tokyo]
        ZonedDateTime zonedDateTimeBefore = ZonedDateTime.now();
        ThreadUtils.sleep(Duration.ofMillis(1L));
        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now();
        ThreadUtils.sleep(Duration.ofMillis(1L));
        ZonedDateTime zonedDateTimeAfter = ZonedDateTime.now();

        assertThat(zonedDateTimeBefore.isBefore(zonedDateTimeNow), is(true));
        assertThat(zonedDateTimeNow.isBefore(zonedDateTimeAfter), is(true));

        // 日時でフォーマット可能なこと
        assertThat(zonedDateTimeNow.format(DateTimeFormatter.ofPattern(
                "yyyy-MM-dd HH:mm:ss.SSSSSSSSS")), is(notNullValue()));
        // +09:00
        assertThat(zonedDateTimeNow.getOffset(), is(ZoneOffset.of("+09:00")));
        // Asia/Tokyo
        assertThat(zonedDateTimeNow.getZone(), is(ZoneId.of("Asia/Tokyo")));
    }

    @Test
    public void testDNTA0103_004() throws InterruptedException {
        ZoneId zoneIdLos = ZoneId.of("Asia/Tokyo");
        OffsetTime offsetTimeBefore = OffsetTime.now(zoneIdLos);
        ThreadUtils.sleep(Duration.ofMillis(1L));
        OffsetTime offsetTimeNow = OffsetTime.now(zoneIdLos);
        ThreadUtils.sleep(Duration.ofMillis(1L));
        OffsetTime offsetTimeAfter = OffsetTime.now(zoneIdLos);

        assertThat(offsetTimeBefore.isBefore(offsetTimeNow), is(true));
        assertThat(offsetTimeNow.isBefore(offsetTimeAfter), is(true));

        // 日付が存在しないこと
        assertThrows(DateTimeException.class, () -> offsetTimeNow.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        // 時間でフォーマット可能なこと
        assertThat(offsetTimeNow.format(DateTimeFormatter.ofPattern(
                "HH:mm:ss.SSSSSSSSS")), is(notNullValue()));
        // -08:00
        assertThat(offsetTimeNow.getOffset(), is(ZoneOffset.of("+09:00")));
    }

    @Test
    public void testDNTA0103_005() throws InterruptedException {
        ZoneId zoneIdUTC0100 = ZoneId.of("UTC+01:00");
        OffsetTime offsetTimeBefore = OffsetTime.now(zoneIdUTC0100);
        ThreadUtils.sleep(Duration.ofMillis(1L));
        OffsetTime offsetTimeNow = OffsetTime.now(zoneIdUTC0100);
        ThreadUtils.sleep(Duration.ofMillis(1L));
        OffsetTime offsetTimeAfter = OffsetTime.now(zoneIdUTC0100);

        assertThat(offsetTimeBefore.isBefore(offsetTimeNow), is(true));
        assertThat(offsetTimeNow.isBefore(offsetTimeAfter), is(true));

        // 日付が存在しないこと
        assertThrows(DateTimeException.class, () -> offsetTimeNow.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        // 時間でフォーマット可能なこと
        assertThat(offsetTimeNow.format(DateTimeFormatter.ofPattern(
                "HH:mm:ss.SSSSSSSSS")), is(notNullValue()));
        // +01:00
        assertThat(offsetTimeNow.getOffset(), is(ZoneOffset.of("+01:00")));
    }

    // 7.3.2.1.4

    @SuppressWarnings("unused")
    @Test(expected = None.class)
    public void testDNTA0104_001() {
        Clock clock = Clock.systemDefaultZone();

        LocalDate localDate = LocalDate.now(clock);
        LocalTime localTime = LocalTime.now(clock);
        LocalDateTime localDateTime = LocalDateTime.now(clock);
        OffsetTime offsetTime = OffsetTime.now(clock);
        OffsetDateTime offsetDateTime = OffsetDateTime.now(clock);
        ZonedDateTime zonedDateTime = ZonedDateTime.now(clock);
        JapaneseDate japaneseDate = JapaneseDate.now(clock);

        // Clock操作に関する試験はシステム日時で実施するので割愛
    }

    // 7.3.2.2.1

    @Test
    public void testDNTA0201_001() {
        LocalDate date1 = LocalDate.of(2010, 01, 15);
        LocalDate date2 = LocalDate.of(2011, 03, 18);

        // One year, two months and three days.
        Period pd = Period.between(date1, date2);

        assertThat(pd.getYears(), is(1));
        assertThat(pd.getMonths(), is(2));
        assertThat(pd.getDays(), is(3));
    }

    @Test
    public void testDNTA0201_002() {
        LocalTime time1 = LocalTime.of(11, 50, 50);
        LocalTime time2 = LocalTime.of(12, 52, 53);

        // One hour, two minutes and three seconds.
        Duration dn = Duration.between(time1, time2);

        assertThat(dn.toHoursPart(), is(1));
        assertThat(dn.toMinutesPart(), is(2));
        assertThat(dn.toSecondsPart(), is(3));
    }

    @Test
    public void testDNTA0201_003() {
        ZonedDateTime zonedDateTime1 = ZonedDateTime.of(2014, 3, 9, 1, 2, 3, 0,
                ZoneId.of("America/Los_Angeles"));
        ZonedDateTime zonedDateTime2 = ZonedDateTime.of(2014, 3, 9, 4, 5, 6, 0,
                ZoneId.of("America/Los_Angeles"));

        // One hour, two minutes and three seconds.
        Duration dn = Duration.between(zonedDateTime1, zonedDateTime2);

        assertThat(dn.toHoursPart(), is(2));
        assertThat(dn.toMinutesPart(), is(3));
        assertThat(dn.toSecondsPart(), is(3));
    }

    // 7.3.2.3.1

    @Test
    public void testDNTA0301_001() {
        // Ex. 12:10:30
        LocalTime localTime = LocalTime.now();

        // 2015-12-25 12:10:30
        LocalDateTime localDateTime = localTime.atDate(LocalDate.of(2015, 12,
                25));

        assertThat(localDateTime.getYear(), is(2015));
        assertThat(localDateTime.getMonthValue(), is(12));
        assertThat(localDateTime.getDayOfMonth(), is(25));
        assertThat(localDateTime.getHour(), is(localTime.getHour()));
        assertThat(localDateTime.getMinute(), is(localTime.getMinute()));
        assertThat(localDateTime.getSecond(), is(localTime.getSecond()));
        assertThat(localDateTime.getNano(), is(localTime.getNano()));
    }

    @Test
    public void testDNTA0301_002() {
        // Ex. 2012-12-25
        LocalDate localDate = LocalDate.now();

        // 2015-12-25 12:10:30
        LocalDateTime localDateTime = localDate.atTime(LocalTime.of(12, 10,
                30));

        assertThat(localDateTime.getYear(), is(localDate.getYear()));
        assertThat(localDateTime.getMonthValue(), is(localDate
                .getMonthValue()));
        assertThat(localDateTime.getDayOfMonth(), is(localDate
                .getDayOfMonth()));
        assertThat(localDateTime.getHour(), is(12));
        assertThat(localDateTime.getMinute(), is(10));
        assertThat(localDateTime.getSecond(), is(30));
        assertThat(localDateTime.getNano(), is(0));
    }

    @Test
    public void testDNTA0301_003() {
        // Ex. 2015-12-25 12:10:30
        LocalDateTime localDateTime = LocalDateTime.now();

        // 12:10:30
        LocalTime localTime = localDateTime.toLocalTime();

        assertThat(localTime.getHour(), is(localDateTime.getHour()));
        assertThat(localTime.getMinute(), is(localDateTime.getMinute()));
        assertThat(localTime.getSecond(), is(localDateTime.getSecond()));
        assertThat(localTime.getNano(), is(localDateTime.getNano()));
    }

    @Test
    public void testDNTA0301_004() {
        // Ex. 2015-12-25 12:10:30
        LocalDateTime localDateTime = LocalDateTime.now();

        LocalDate localDate = localDateTime.toLocalDate();

        assertThat(localDate.getYear(), is(localDateTime.getYear()));
        assertThat(localDate.getMonthValue(), is(localDateTime
                .getMonthValue()));
        assertThat(localDate.getDayOfMonth(), is(localDateTime
                .getDayOfMonth()));
    }

    @Test
    public void testDNTA0301_005() {
        // Ex, 12:30:11.567+09:00
        OffsetTime offsetTime = OffsetTime.now();

        // 2015-12-25T12:30:11.567+09:00
        OffsetDateTime offsetDateTime = offsetTime.atDate(LocalDate.of(2015, 12,
                25));

        assertThat(offsetDateTime.getYear(), is(2015));
        assertThat(offsetDateTime.getMonthValue(), is(12));
        assertThat(offsetDateTime.getDayOfMonth(), is(25));
        assertThat(offsetDateTime.getHour(), is(offsetTime.getHour()));
        assertThat(offsetDateTime.getMinute(), is(offsetTime.getMinute()));
        assertThat(offsetDateTime.getSecond(), is(offsetTime.getSecond()));
        assertThat(offsetDateTime.getNano(), is(offsetTime.getNano()));
        assertThat(offsetDateTime.getOffset(), is(offsetTime.getOffset()));
    }

    @Test
    public void testDNTA0301_006() {
        // Ex, 2015-12-25T12:30:11.567+09:00
        OffsetDateTime offsetDateTime = OffsetDateTime.now();

        // 2015-12-25T12:30:11.567+09:00[Asia/Tokyo]
        ZonedDateTime zonedDateTime = offsetDateTime.atZoneSameInstant(ZoneId
                .of("Asia/Tokyo"));

        assertThat(zonedDateTime.getYear(), is(offsetDateTime.getYear()));
        assertThat(zonedDateTime.getMonthValue(), is(offsetDateTime
                .getMonthValue()));
        assertThat(zonedDateTime.getDayOfMonth(), is(offsetDateTime
                .getDayOfMonth()));
        assertThat(zonedDateTime.getHour(), is(offsetDateTime.getHour()));
        assertThat(zonedDateTime.getMinute(), is(offsetDateTime.getMinute()));
        assertThat(zonedDateTime.getSecond(), is(offsetDateTime.getSecond()));
        assertThat(zonedDateTime.getNano(), is(offsetDateTime.getNano()));
        assertThat(zonedDateTime.getOffset(), is(offsetDateTime.getOffset()));
        assertThat(zonedDateTime.getZone(), is(ZoneId.of("Asia/Tokyo")));
    }

    @Test
    public void testDNTA0301_007() {
        // Ex, 2015-12-25T12:30:11.567+09:00[Asia/Tokyo]
        ZonedDateTime zonedDateTime = ZonedDateTime.now();

        // 2015-12-25T12:30:11.567+09:00
        OffsetDateTime offsetDateTime = zonedDateTime.toOffsetDateTime();

        assertThat(offsetDateTime.getYear(), is(zonedDateTime.getYear()));
        assertThat(offsetDateTime.getMonthValue(), is(zonedDateTime
                .getMonthValue()));
        assertThat(offsetDateTime.getDayOfMonth(), is(zonedDateTime
                .getDayOfMonth()));
        assertThat(offsetDateTime.getHour(), is(zonedDateTime.getHour()));
        assertThat(offsetDateTime.getMinute(), is(zonedDateTime.getMinute()));
        assertThat(offsetDateTime.getSecond(), is(zonedDateTime.getSecond()));
        assertThat(offsetDateTime.getNano(), is(zonedDateTime.getNano()));
        assertThat(offsetDateTime.getOffset(), is(zonedDateTime.getOffset()));
    }

    @Test
    public void testDNTA0301_008() {
        // Ex, 2015-12-25T12:30:11.567+09:00[Asia/Tokyo]
        ZonedDateTime zonedDateTime = ZonedDateTime.now();

        // 12:30:11.567+09:00
        OffsetTime offsetTime = zonedDateTime.toOffsetDateTime().toOffsetTime();

        assertThat(offsetTime.getHour(), is(zonedDateTime.getHour()));
        assertThat(offsetTime.getMinute(), is(zonedDateTime.getMinute()));
        assertThat(offsetTime.getSecond(), is(zonedDateTime.getSecond()));
        assertThat(offsetTime.getNano(), is(zonedDateTime.getNano()));
        assertThat(offsetTime.getOffset(), is(zonedDateTime.getOffset()));
    }

    @Test
    public void testDNTA0301_009() {
        // Ex, 12:30:11.567
        LocalTime localTime = LocalTime.now();

        // 12:30:11.567+09:00
        OffsetTime offsetTime = localTime.atOffset(ZoneOffset.ofHours(9));

        assertThat(localTime.getHour(), is(offsetTime.getHour()));
        assertThat(localTime.getMinute(), is(offsetTime.getMinute()));
        assertThat(localTime.getSecond(), is(offsetTime.getSecond()));
        assertThat(localTime.getNano(), is(offsetTime.getNano()));
        assertThat(offsetTime.getOffset(), is(ZoneOffset.ofHours(9)));
    }

    // 7.3.2.3.2

    @Test
    public void testDNTA0302_001() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Instant instant = localDateTime.toInstant(ZoneOffset.ofHours(9));
        Date date = Date.from(instant);

        FastDateFormat dateFormat = FastDateFormat.getInstance(
                "yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dateAndTimeFormatter = DateTimeFormatter.ofPattern(
                "uuuu-MM-dd HH:mm:ss");

        assertThat(dateFormat.format(date), is(localDateTime.format(
                dateAndTimeFormatter)));
    }

    @Test
    public void testDNTA0302_002() {
        Date date = new Date();
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId
                .systemDefault());

        FastDateFormat dateFormat = FastDateFormat.getInstance(
                "yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dateAndTimeFormatter = DateTimeFormatter.ofPattern(
                "uuuu-MM-dd HH:mm:ss");

        assertThat(localDateTime.format(dateAndTimeFormatter), is(dateFormat
                .format(date)));
    }

    @Test
    public void testDNTA0302_003() {
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime1 = localTime.atDate(LocalDate.of(2015, 12,
                25));

        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime2 = localDate.atTime(LocalTime.of(12, 10,
                30));

        Instant instant1 = localDateTime1.toInstant(ZoneOffset.ofHours(9));
        Date date1 = Date.from(instant1);

        Instant instant2 = localDateTime2.toInstant(ZoneOffset.ofHours(9));
        Date date2 = Date.from(instant2);

        FastDateFormat dateFormat = FastDateFormat.getInstance(
                "yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dateAndTimeFormatter = DateTimeFormatter.ofPattern(
                "yyyy-MM-dd HH:mm:ss");

        assertThat(dateFormat.format(date1), is(localDateTime1.format(
                dateAndTimeFormatter)));
        assertThat(dateFormat.format(date2), is(localDateTime2.format(
                dateAndTimeFormatter)));
    }

    @SuppressWarnings("unused")
    @Test(expected = None.class)
    public void testDNTA0302_004() {
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        Instant instant = clock.instant();
        Date date = Date.from(instant);
    }

    @SuppressWarnings("unused")
    @Test(expected = None.class)
    public void testDNTA0302_005() {
        Date date = new Date();
        Instant instant = date.toInstant();
        Clock clock = Clock.fixed(instant, ZoneId.systemDefault());
    }

    @SuppressWarnings("unused")
    @Test(expected = None.class)
    public void testDNTA0303_001() {
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        LocalDate localDate = date.toLocalDate();
    }

    @SuppressWarnings("unused")
    @Test(expected = None.class)
    public void testDNTA0303_002() {
        LocalDate localDate = LocalDate.now();
        java.sql.Date date = java.sql.Date.valueOf(localDate);
    }

    @SuppressWarnings("unused")
    @Test(expected = None.class)
    public void testDNTA0303_003() {
        java.sql.Time time = new java.sql.Time(System.currentTimeMillis());
        LocalTime localTime = time.toLocalTime();
    }

    @SuppressWarnings("unused")
    @Test(expected = None.class)
    public void testDNTA0303_004() {
        LocalTime localTime = LocalTime.now();
        java.sql.Time time = java.sql.Time.valueOf(localTime);
    }

    @SuppressWarnings("unused")
    @Test(expected = None.class)
    public void testDNTA0303_005() {
        java.sql.Timestamp timestamp = new java.sql.Timestamp(System
                .currentTimeMillis());
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
    }

    @SuppressWarnings("unused")
    @Test(expected = None.class)
    public void testDNTA0303_006() {
        LocalDateTime localDateTime = LocalDateTime.now();
        java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(
                localDateTime);
    }

    @SuppressWarnings("unused")
    @Test(expected = None.class)
    public void testDNTA0303_007() {
        java.sql.Timestamp timestamp = new java.sql.Timestamp(System
                .currentTimeMillis());
        Instant instant = timestamp.toInstant();
        Clock clock = Clock.fixed(instant, ZoneId.systemDefault());
    }

    @SuppressWarnings("unused")
    @Test(expected = None.class)
    public void testDNTA0303_008() {
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        Instant instant = clock.instant();
        java.sql.Timestamp timestamp = java.sql.Timestamp.from(instant);
    }

    // 7.3.2.3.4

    @Test
    public void testDNTA0304_001() {
        LocalDate localDate = LocalDate.of(2015, 12, 25);
        assertThat(localDate.toString(), is("2015-12-25"));
    }

    @Test
    public void testDNTA0304_002() {
        DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
        LocalDate localDate = LocalDate.of(2015, 12, 25);
        assertThat(formatter.format(localDate), is("20151225"));
    }

    @Test
    public void testDNTA0304_003() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "G uuuu/MM/dd E").withLocale(Locale.JAPANESE).withResolverStyle(
                        ResolverStyle.STRICT);
        LocalDate localDate = LocalDate.of(2015, 12, 25);
        assertThat(formatter.format(localDate), is("西暦 2015/12/25 金"));
    }

    // 7.3.2.3.5

    @Test
    public void testDNTA0305_001() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu/MM/dd")
                .withLocale(Locale.JAPANESE).withResolverStyle(
                        ResolverStyle.STRICT);

        LocalDate localDate = LocalDate.parse("2015/12/25", formatter);

        assertThat(localDate.toString(), is("2015-12-25"));
    }

    @Test
    public void testDNTA0305_002() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
                .withLocale(Locale.JAPANESE).withResolverStyle(
                        ResolverStyle.STRICT);
        LocalTime localTime = LocalTime.parse("14:09:20", formatter);

        assertThat(localTime.toString(), is("14:09:20"));
    }

    // 7.3.2.4.1

    @Test
    public void testDNTA04_01_001() {
        LocalTime localTime = LocalTime.of(20, 30, 50);
        LocalTime plusHoursTime = localTime.plusHours(2);
        LocalTime plusMinutesTime = localTime.plusMinutes(10);
        LocalTime minusSecondsTime = localTime.minusSeconds(15);

        assertThat(plusHoursTime.toString(), is("22:30:50"));
        assertThat(plusMinutesTime.toString(), is("20:40:50"));
        assertThat(minusSecondsTime.toString(), is("20:30:35"));
    }

    @Test
    public void testDNTA0401_002() {
        LocalDate localDate = LocalDate.of(2015, 12, 25);
        LocalDate plusYearsDate = localDate.plusYears(10);
        LocalDate minusMonthsTime = localDate.minusMonths(1);
        LocalDate plusDaysTime = localDate.plusDays(3);

        assertThat(plusYearsDate.toString(), is("2025-12-25"));
        assertThat(minusMonthsTime.toString(), is("2015-11-25"));
        assertThat(plusDaysTime.toString(), is("2015-12-28"));
    }

    @Test
    public void testDNTA0401_003() {
        LocalDateTime localDateTime1 = LocalDateTime.of(2015, 12, 25, 22, 30,
                50);
        Instant instant = localDateTime1.toInstant(ZoneOffset.of("+09:00"));

        Clock clock = Clock.fixed(instant, ZoneId.systemDefault());
        Clock offsetDaysClock = Clock.offset(clock, Duration.ofDays(1L));

        LocalDateTime localDateTime2 = LocalDateTime.now(offsetDaysClock);

        assertThat(localDateTime2.toString(), is("2015-12-26T22:30:50"));

        assertThrows(UnsupportedTemporalTypeException.class, () -> Clock.offset(
                clock, Duration.of(1L, ChronoUnit.MONTHS)));
    }

    @Test
    public void testDNTA0402_001() {
        LocalTime morning = LocalTime.of(7, 30, 00);
        LocalTime daytime = LocalTime.of(12, 00, 00);
        LocalTime evening = LocalTime.of(17, 30, 00);

        assertThat(daytime.isBefore(morning), is(false));
        assertThat(morning.isAfter(evening), is(false));
        assertThat(evening.equals(LocalTime.of(17, 30, 00)), is(true));

        assertThat(daytime.isBefore(daytime), is(false));
        assertThat(morning.isAfter(morning), is(false));
    }

    @Test
    public void testDNTA0402_002() {
        LocalDate may = LocalDate.of(2015, 6, 1);
        LocalDate june = LocalDate.of(2015, 7, 1);
        LocalDate july = LocalDate.of(2015, 8, 1);

        assertThat(may.isBefore(june), is(true));
        assertThat(june.isAfter(july), is(false));
        assertThat(july.equals(may), is(false));

        assertThat(may.isBefore(may), is(false));
        assertThat(june.isAfter(june), is(false));
    }

    @Test
    public void testDNTA0402_003() {
        Clock clock1 = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        Clock clock2 = Clock.fixed(Instant.now().plusSeconds(1L), ZoneId
                .systemDefault());

        assertThat(clock1.instant().isBefore(clock2.instant()), is(true));
        assertThat(clock1.instant().isAfter(clock2.instant()), is(false));
    }

    @Test
    public void testDNTA0403_001() {
        String strDateTime = "aabbcc";
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss")
                .withLocale(Locale.JAPANESE).withResolverStyle(
                        ResolverStyle.STRICT);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuMMdd")
                .withLocale(Locale.JAPANESE).withResolverStyle(
                        ResolverStyle.STRICT);

        assertThrows(DateTimeParseException.class, () -> LocalTime.parse(
                strDateTime, timeFormatter));

        assertThrows(DateTimeParseException.class, () -> LocalDate.parse(
                strDateTime, dateFormatter));
    }

    @Test
    public void testDNTA0403_002() {
        LocalDate date1 = LocalDate.of(2012, 1, 1);
        LocalDate date2 = LocalDate.of(2015, 1, 1);

        assertThat(date1.isLeapYear(), is(true));
        assertThat(date2.isLeapYear(), is(false));
    }

    @Test
    public void testDNTA0404_001() {
        LocalDateTime localDateTime = LocalDateTime.of(2015, 2, 1, 12, 30, 50,
                567);

        int year = localDateTime.getYear(); // 2015
        int month = localDateTime.getMonthValue(); // 2
        int dayOfMonth = localDateTime.getDayOfMonth(); // 1
        int dayOfYear = localDateTime.getDayOfYear(); // 32
        int hour = localDateTime.getHour(); // 12
        int minute = localDateTime.getMinute(); // 30
        int second = localDateTime.getSecond(); // 50
        int nano = localDateTime.getNano(); // 567

        assertThat(year, is(2015));
        assertThat(month, is(2));
        assertThat(dayOfMonth, is(1));
        assertThat(dayOfYear, is(32));
        assertThat(hour, is(12));
        assertThat(minute, is(30));
        assertThat(second, is(50));
        assertThat(nano, is(567));
    }

    @Test
    public void testDNTA0501_001() {
        assertThat(JapaneseDate.of(1873, 1, 1), is(notNullValue()));
        assertThrows(DateTimeException.class, () -> JapaneseDate.of(1872, 12,
                31));
    }

    @Test
    public void testDNTA0502_001() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "Gppy年ppM月ppd日").withLocale(Locale.JAPANESE).withResolverStyle(
                        ResolverStyle.STRICT).withChronology(
                                JapaneseChronology.INSTANCE);

        JapaneseDate japaneseDate = JapaneseDate.of(1992, 1, 1);

        assertThat(formatter.format(japaneseDate), is("平成 4年 1月 1日"));
    }

    @Test
    public void testDNTA0503_001() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("Gy年MM月dd日")
                .withLocale(Locale.JAPANESE).withResolverStyle(
                        ResolverStyle.STRICT).withChronology(
                                JapaneseChronology.INSTANCE);

        JapaneseDate japaneseDate1 = JapaneseDate.from(formatter.parse(
                "平成27年12月25日"));
        JapaneseDate japaneseDate2 = JapaneseDate.from(formatter.parse(
                "明治6年01月01日"));

        assertThat(japaneseDate1.toString(), is("Japanese Heisei 27-12-25"));
        assertThat(japaneseDate2.toString(), is("Japanese Meiji 6-01-01"));
    }

    @Test
    public void testDNTA0504_001() {
        LocalDate localDate = LocalDate.of(2015, 12, 25);
        JapaneseDate japaneseDate = JapaneseDate.from(localDate);

        assertThat(japaneseDate.toString(), is("Japanese Heisei 27-12-25"));
    }

    // 7.3.2.6.1 ~ 7.3.2.6.2 selenium
}
