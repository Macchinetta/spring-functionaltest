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
package jp.co.ntt.fw.spring.functionaltest.domain.service.dnta;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.terasoluna.gfw.common.time.ClockFactory;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.infra.datefactory.dnta.DateAndTimeApiDateFactory;

@Service
public class DateAndTimeApiServiceImpl implements DateAndTimeApiService {

    @Qualifier("clockFactory")
    @Inject
    ClockFactory dateFactory;

    @Override
    public String getNowLocalDate() {
        return DateAndTimeApiDateFactory.newLocalDate().toString();
    }

    @Override
    public String getNowLocalTime() {
        return DateAndTimeApiDateFactory.newLocalTime().toString();
    }

    @Override
    public String getNowLocalDateTime() {
        return DateAndTimeApiDateFactory.newLocalDateTime().toString();
    }

    @Override
    public String getSpecifiedLocalDate(int year, int month, int day) {
        return java.time.LocalDate.of(year, month, day).toString();
    }

    @Override
    public String getSpecifiedLocalTime(int hour, int minute, int second) {
        return java.time.LocalTime.of(hour, minute, second).toString();
    }

    @Override
    public String getSpecifiedLocalDateTime(int year, int month, int day, int hour, int minute,
            int second) {
        return java.time.LocalDateTime.of(year, month, day, hour, minute, second).toString();
    }

    @Override
    public String getSpecifiedOffsetTime(int hour, int minute, int second, int nanoSecond,
            int zoneOffset) {
        return java.time.OffsetTime
                .of(hour, minute, second, nanoSecond, java.time.ZoneOffset.ofHours(zoneOffset))
                .toString();
    }

    @Override
    public String getSpecifiedOffsetDateTime(int year, int month, int day, int hour, int minute,
            int second, int nanoSecond, int zoneOffset) {
        return java.time.OffsetDateTime.of(year, month, day, hour, minute, second, nanoSecond,
                java.time.ZoneOffset.ofHours(zoneOffset)).toString();
    }

    @Override
    public String getSpecifiedZonedDateTime(int year, int month, int day, int hour, int minute,
            int second, int nanoSecond, String area) {
        return java.time.ZonedDateTime
                .of(year, month, day, hour, minute, second, nanoSecond, java.time.ZoneId.of(area))
                .toString();
    }

    @Override
    public String getLastDateOfSpecifiedMonth(int year, int month, int day) {
        return java.time.LocalDate.of(year, month, day)
                .with(java.time.temporal.TemporalAdjusters.lastDayOfMonth()).toString();
    }

    @Override
    public String getNextMondayOfSpecifiedMonth(int year, int month, int day) {
        return java.time.LocalDate.of(year, month, day)
                .with(java.time.temporal.TemporalAdjusters.next(java.time.DayOfWeek.MONDAY))
                .toString();
    }

    @Override
    public String getNowOffsetTime() {
        return DateAndTimeApiDateFactory.newOffsetTime().toString();
    }

    @Override
    public String getNowOffsetDateTime() {
        return DateAndTimeApiDateFactory.newOffsetDateTime().toString();
    }

    @Override
    public String getNowZonedDateTime() {
        return DateAndTimeApiDateFactory.newZonedDateTime().toString();
    }

    @Override
    public String getNowSpecifiedZonedDateTime(String zone) {
        return DateAndTimeApiDateFactory.newSpecifiedZonedDateTime(zone).toString();
    }

    @Override
    public int[] compareDate(int year1, int month1, int day1, int year2, int month2, int day2) {

        java.time.LocalDate date1 = java.time.LocalDate.of(year1, month1, day1);
        java.time.LocalDate date2 = java.time.LocalDate.of(year2, month2, day2);
        java.time.Period period = java.time.Period.between(date1, date2);
        return new int[] {period.getYears(), period.getMonths(), period.getDays()};
    }

    @Override
    public long[] compareTime(int hour1, int minute1, int second1, int hour2, int minute2,
            int second2) {

        java.time.LocalTime time1 = java.time.LocalTime.of(hour1, minute1, second1);
        java.time.LocalTime time2 = java.time.LocalTime.of(hour2, minute2, second2);
        java.time.Duration duration = java.time.Duration.between(time1, time2);
        return new long[] {duration.toHours(), duration.toMinutes() % 60,
                duration.getSeconds() % 60};
    }

    @Override
    public long[] compareTime(int year1, int month1, int day1, int hour1, int minute1, int second1,
            int nanoSecond1, String area1, int year2, int month2, int day2, int hour2, int minute2,
            int second2, int nanoSecond2, String area2) {

        java.time.ZonedDateTime zonedDateTime1 = java.time.ZonedDateTime.of(year1, month1, day1,
                hour1, minute1, second1, nanoSecond1, java.time.ZoneId.of(area1));
        java.time.ZonedDateTime zonedDateTime2 = java.time.ZonedDateTime.of(year2, month2, day2,
                hour2, minute2, second2, nanoSecond2, java.time.ZoneId.of(area2));
        java.time.Duration duration = java.time.Duration.between(zonedDateTime1, zonedDateTime2);
        return new long[] {duration.toHours(), duration.toMinutes() % 60,
                duration.getSeconds() % 60};
    }

    @Override
    public String toLocalDateTimeFromLocalTime(int hour, int minute, int second, int year,
            int month, int day) {
        java.time.LocalTime time = java.time.LocalTime.of(hour, minute, second);
        return time.atDate(java.time.LocalDate.of(year, month, day)).toString();
    }

    @Override
    public String toLocalDateTimeFromLocalDate(int year, int month, int day, int hour, int minute,
            int second) {
        java.time.LocalDate date = java.time.LocalDate.of(year, month, day);
        return date.atTime(java.time.LocalTime.of(hour, minute, second)).toString();
    }

    @Override
    public String toLocalDate(int year, int month, int day, int hour, int minute, int second) {
        java.time.LocalDateTime dateTime =
                java.time.LocalDateTime.of(year, month, day, hour, minute, second);
        return dateTime.toLocalDate().toString();
    }

    @Override
    public String toLocalTime(int year, int month, int day, int hour, int minute, int second) {
        java.time.LocalDateTime dateTime =
                java.time.LocalDateTime.of(year, month, day, hour, minute, second);
        return dateTime.toLocalTime().toString();
    }

    @Override
    public String toOffsetDateTime(int hour, int minute, int second, int nanoSecond, int zoneOffset,
            int year, int month, int day) {
        java.time.OffsetTime offsetTime = java.time.OffsetTime.of(hour, minute, second, nanoSecond,
                java.time.ZoneOffset.ofHours(zoneOffset));
        return offsetTime.atDate(java.time.LocalDate.of(year, month, day)).toString();
    }

    @Override
    public String toZonedDateTime(int year, int month, int day, int hour, int minute, int second,
            int nanoSecond, int zoneOffset, String area) {
        java.time.OffsetDateTime offsetDateTime = java.time.OffsetDateTime.of(year, month, day,
                hour, minute, second, nanoSecond, java.time.ZoneOffset.ofHours(zoneOffset));
        return offsetDateTime.atZoneSameInstant(java.time.ZoneId.of(area)).toString();
    }

    @Override
    public String toOffsetDateTime(int year, int month, int day, int hour, int minute, int second,
            int nanoSecond, String area) {
        java.time.ZonedDateTime zonedDateTime = java.time.ZonedDateTime.of(year, month, day, hour,
                minute, second, nanoSecond, java.time.ZoneId.of(area));
        return zonedDateTime.toOffsetDateTime().toString();
    }

    @Override
    public String toOffsetTime(int year, int month, int day, int hour, int minute, int second,
            int nanoSecond, String area) {
        java.time.ZonedDateTime zonedDateTime = java.time.ZonedDateTime.of(year, month, day, hour,
                minute, second, nanoSecond, java.time.ZoneId.of(area));
        return zonedDateTime.toOffsetDateTime().toOffsetTime().toString();
    }

    @Override
    public String toOffsetTime(int hour, int minute, int second, int zoneOffset) {
        java.time.LocalTime time = java.time.LocalTime.of(hour, minute, second);
        return time.atOffset(java.time.ZoneOffset.ofHours(zoneOffset)).toString();
    }

    @Override
    public Date toUtilDate(int year, int month, int day, int hour, int minute, int second,
            int zoneOffset) {
        java.time.LocalDateTime dateTime =
                java.time.LocalDateTime.of(year, month, day, hour, minute, second);
        java.time.Instant instant = dateTime.toInstant(java.time.ZoneOffset.ofHours(zoneOffset));
        return Date.from(instant);
    }

    @Override
    public String toLocalDateTime(Date date) {
        java.time.Instant instant = date.toInstant();
        return java.time.LocalDateTime.ofInstant(instant, java.time.ZoneId.systemDefault())
                .toString();
    }

    @Override
    public java.sql.Date toSqlDate(int year, int month, int day) {
        java.time.LocalDate date = java.time.LocalDate.of(year, month, day);
        return java.sql.Date.valueOf(date);
    }

    @Override
    public String toLocalDate(java.sql.Date date) {
        return date.toLocalDate().toString();
    }

    @Override
    public Timestamp toSqlTimestamp(int year, int month, int day, int hour, int minute,
            int second) {
        java.time.LocalDateTime dateTime =
                java.time.LocalDateTime.of(year, month, day, hour, minute, second);
        return Timestamp.valueOf(dateTime);
    }

    @Override
    public String toLocalDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime().toString();
    }

    @Override
    public Time toSqlTime(int hour, int minute, int second) {
        java.time.LocalTime time = java.time.LocalTime.of(hour, minute, second);
        return Time.valueOf(time);
    }

    @Override
    public String toLocalTime(Time time) {
        return time.toLocalTime().toString();
    }

    @Override
    public String createLocalDateFromDateFactory() {
        return LocalDate.now(this.dateFactory.fixed()).toString();
    }

    @Override
    public String createDateString(int year, int month, int day) {
        java.time.LocalDate date = java.time.LocalDate.of(year, month, day);
        return date.toString();
    }

    @Override
    public String createDateStringWithPredefinedFormat(int year, int month, int day) {
        java.time.LocalDate date = java.time.LocalDate.of(year, month, day);
        java.time.format.DateTimeFormatter formatter =
                java.time.format.DateTimeFormatter.BASIC_ISO_DATE;
        return formatter.format(date);
    }

    @Override
    public String createDateStringWithAnyFormat(int year, int month, int day) {
        java.time.LocalDate date = java.time.LocalDate.of(year, month, day);
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter
                .ofPattern("G uuuu/MM/dd E").withLocale(Locale.JAPANESE)
                .withResolverStyle(java.time.format.ResolverStyle.STRICT);
        return formatter.format(date);
    }

    @Override
    public String parseToDate(String dateString) {
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter
                .ofPattern("uuuu/MM/dd").withLocale(Locale.JAPANESE)
                .withResolverStyle(java.time.format.ResolverStyle.STRICT);
        return java.time.LocalDate.parse(dateString, formatter).toString();
    }

    @Override
    public String parseToTime(String timeString) {
        java.time.format.DateTimeFormatter formatter =
                java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss").withLocale(Locale.JAPANESE)
                        .withResolverStyle(java.time.format.ResolverStyle.STRICT);
        return java.time.LocalTime.parse(timeString, formatter).toString();
    }

    @Override
    public int[] parseAndGetDateElement(String dateString) {
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter
                .ofPattern("uuuu/MM/dd").withLocale(Locale.JAPANESE)
                .withResolverStyle(java.time.format.ResolverStyle.STRICT);
        java.time.LocalDate date = java.time.LocalDate.parse(dateString, formatter);
        return new int[] {date.getYear(), date.getMonthValue(), date.getDayOfMonth(),
                date.getDayOfYear()};
    }

    @Override
    public int[] parseAndGetTimeElement(String timeString) {
        java.time.format.DateTimeFormatter formatter =
                java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss").withLocale(Locale.JAPANESE)
                        .withResolverStyle(java.time.format.ResolverStyle.STRICT);
        java.time.LocalTime time = java.time.LocalTime.parse(timeString, formatter);
        return new int[] {time.getHour(), time.getMinute(), time.getSecond()};
    }

    @Override
    public String plusYear(int year, int month, int day, int plus) {
        java.time.LocalDate date = java.time.LocalDate.of(year, month, day);
        return date.plusYears(plus).toString();
    }

    @Override
    public String plusMonth(int year, int month, int day, int plus) {
        java.time.LocalDate date = java.time.LocalDate.of(year, month, day);
        return date.plusMonths(plus).toString();
    }

    @Override
    public String plusDay(int year, int month, int day, int plus) {
        java.time.LocalDate date = java.time.LocalDate.of(year, month, day);
        return date.plusDays(plus).toString();
    }

    @Override
    public String plusHour(int hour, int minute, int second, int plus) {
        java.time.LocalTime time = java.time.LocalTime.of(hour, minute, second);
        return time.plusHours(plus).toString();
    }

    @Override
    public String plusMinute(int hour, int minute, int second, int plus) {
        java.time.LocalTime time = java.time.LocalTime.of(hour, minute, second);
        return time.plusMinutes(plus).toString();
    }

    @Override
    public String plusSecond(int hour, int minute, int second, int plus) {
        java.time.LocalTime time = java.time.LocalTime.of(hour, minute, second);
        return time.plusSeconds(plus).toString();
    }

    @Override
    public String minusYear(int year, int month, int day, int minus) {
        java.time.LocalDate date = java.time.LocalDate.of(year, month, day);
        return date.minusYears(minus).toString();
    }

    @Override
    public String minusMonth(int year, int month, int day, int minus) {
        java.time.LocalDate date = java.time.LocalDate.of(year, month, day);
        return date.minusMonths(minus).toString();
    }

    @Override
    public String minusDay(int year, int month, int day, int minus) {
        java.time.LocalDate date = java.time.LocalDate.of(year, month, day);
        return date.minusDays(minus).toString();
    }

    @Override
    public String minusHour(int hour, int minute, int second, int minus) {
        java.time.LocalTime time = java.time.LocalTime.of(hour, minute, second);
        return time.minusHours(minus).toString();
    }

    @Override
    public String minusMinute(int hour, int minute, int second, int minus) {
        java.time.LocalTime time = java.time.LocalTime.of(hour, minute, second);
        return time.minusMinutes(minus).toString();
    }

    @Override
    public String minusSecond(int hour, int minute, int second, int minus) {
        java.time.LocalTime time = java.time.LocalTime.of(hour, minute, second);
        return time.minusSeconds(minus).toString();
    }

    @Override
    public boolean isBeforeDate(int year1, int month1, int day1, int year2, int month2, int day2) {
        java.time.LocalDate date1 = java.time.LocalDate.of(year1, month1, day1);
        java.time.LocalDate date2 = java.time.LocalDate.of(year2, month2, day2);
        return date1.isBefore(date2);
    }

    @Override
    public boolean isAfterDate(int year1, int month1, int day1, int year2, int month2, int day2) {
        java.time.LocalDate date1 = java.time.LocalDate.of(year1, month1, day1);
        java.time.LocalDate date2 = java.time.LocalDate.of(year2, month2, day2);
        return date1.isAfter(date2);
    }

    @Override
    public boolean equalsDate(int year1, int month1, int day1, int year2, int month2, int day2) {
        java.time.LocalDate date1 = java.time.LocalDate.of(year1, month1, day1);
        java.time.LocalDate date2 = java.time.LocalDate.of(year2, month2, day2);
        return date1.equals(date2);
    }

    @Override
    public boolean isBeforeTime(int hour1, int minute1, int second1, int hour2, int minute2,
            int second2) {
        java.time.LocalTime time1 = java.time.LocalTime.of(hour1, minute1, second1);
        java.time.LocalTime time2 = java.time.LocalTime.of(hour2, minute2, second2);
        return time1.isBefore(time2);
    }

    @Override
    public boolean isAfterTime(int hour1, int minute1, int second1, int hour2, int minute2,
            int second2) {
        java.time.LocalTime time1 = java.time.LocalTime.of(hour1, minute1, second1);
        java.time.LocalTime time2 = java.time.LocalTime.of(hour2, minute2, second2);
        return time1.isAfter(time2);
    }

    @Override
    public boolean equalsTime(int hour1, int minute1, int second1, int hour2, int minute2,
            int second2) {
        java.time.LocalTime time1 = java.time.LocalTime.of(hour1, minute1, second1);
        java.time.LocalTime time2 = java.time.LocalTime.of(hour2, minute2, second2);
        return time1.equals(time2);
    }

    @Override
    public boolean isLeapYear(int year, int month, int day) {
        java.time.LocalDate date = java.time.LocalDate.of(year, month, day);
        return date.isLeapYear();
    }

    @Override
    public String getNowDate() {
        return DateAndTimeApiDateFactory.newSpecifiedJapaneseDate().toString();
    }

    @Override
    public String getSpecifiedDate(int year, int month, int day) {
        return java.time.chrono.JapaneseDate.of(year, month, day).toString();
    }

    @Override
    public String getSpecifiedJapaneseDate(int year, int month, int day) {
        return java.time.chrono.JapaneseDate
                .of(java.time.chrono.JapaneseEra.HEISEI, year, month, day).toString();
    }

    @Override
    public String createJapaneseDateString(int year, int month, int day) {
        java.time.chrono.JapaneseDate date = java.time.chrono.JapaneseDate.of(year, month, day);
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter
                .ofPattern("Gppy年ppM月ppd日").withLocale(Locale.JAPANESE)
                .withResolverStyle(java.time.format.ResolverStyle.STRICT);
        return formatter.format(date);
    }

    @Override
    public String parse(String dateString) {
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter
                .ofPattern("Gy年MM月dd日").withLocale(Locale.JAPANESE)
                .withResolverStyle(java.time.format.ResolverStyle.STRICT)
                .withChronology(java.time.chrono.JapaneseChronology.INSTANCE);
        return java.time.chrono.JapaneseDate.from(formatter.parse(dateString)).toString();
    }

    @Override
    public String toJapaneseDate(int year, int month, int day) {
        java.time.LocalDate date = java.time.LocalDate.of(year, month, day);
        return java.time.chrono.JapaneseDate.from(date).toString();
    }
}
