/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.dnta;

public interface DateAndTimeApiService {

    String getNowLocalDate();

    String getNowLocalTime();

    String getNowLocalDateTime();

    String getSpecifiedLocalDate(int year, int month, int day);

    String getSpecifiedLocalTime(int hour, int minute, int second);

    String getSpecifiedLocalDateTime(int year, int month, int day, int hour,
            int minute, int second);

    String getSpecifiedOffsetTime(int hour, int minute, int second,
            int nanoSecond, int zoneOffset);

    String getSpecifiedOffsetDateTime(int year, int month, int day, int hour,
            int minute, int second, int nanoSecond, int zoneOffset);

    String getSpecifiedZonedDateTime(int year, int month, int day, int hour,
            int minute, int second, int nanoSecond, String area);

    String getLastDateOfSpecifiedMonth(int year, int month, int day);

    String getNextMondayOfSpecifiedMonth(int year, int month, int day);

    String getNowOffsetTime();

    String getNowOffsetDateTime();

    String getNowZonedDateTime();

    String getNowSpecifiedZonedDateTime(String zone);

    int[] compareDate(int year1, int month1, int day1, int year2, int month2,
            int day2);

    long[] compareTime(int year1, int month1, int day1, int hour1, int minute1,
            int second1, int nanoSecond1, String area1, int year2, int month2,
            int day2, int hour2, int minute2, int second2, int nanoSecond2,
            String area2);

    long[] compareTime(int hour1, int minute1, int second1, int hour2,
            int minute2, int second2);

    String toLocalDateTimeFromLocalTime(int hour, int minute, int second,
            int year, int month, int day);

    String toLocalDateTimeFromLocalDate(int year, int month, int day, int hour,
            int minute, int second);

    String toLocalDate(int year, int month, int day, int hour, int minute,
            int second);

    String toLocalTime(int year, int month, int day, int hour, int minute,
            int second);

    String toOffsetDateTime(int hour, int minute, int second, int nanoSecond,
            int zoneOffset, int year, int month, int day);

    String toZonedDateTime(int year, int month, int day, int hour, int minute,
            int second, int nanoSecond, int zoneOffset, String area);

    String toOffsetDateTime(int year, int month, int day, int hour, int minute,
            int second, int nanoSecond, String area);

    String toOffsetTime(int year, int month, int day, int hour, int minute,
            int second, int nanoSecond, String area);

    String toOffsetTime(int hour, int minute, int second, int zoneOffset);

    java.util.Date toUtilDate(int year, int month, int day, int hour,
            int minute, int second, int zoneOffset);

    String toLocalDateTime(java.util.Date date);

    java.sql.Date toSqlDate(int year, int month, int day);

    String toLocalDate(java.sql.Date date);

    java.sql.Timestamp toSqlTimestamp(int year, int month, int day, int hour,
            int minute, int second);

    String toLocalDateTime(java.sql.Timestamp timestamp);

    java.sql.Time toSqlTime(int hour, int minute, int second);

    String toLocalTime(java.sql.Time time);

    String createLocalDateFromDateFactory();

    String createDateString(int year, int month, int day);

    String createDateStringWithPredefinedFormat(int year, int month, int day);

    String createDateStringWithAnyFormat(int year, int month, int day);

    String parseToDate(String dateString);

    String parseToTime(String timeString);

    String plusYear(int year, int month, int day, int plus);

    String plusMonth(int year, int month, int day, int plus);

    String plusDay(int year, int month, int day, int plus);

    String plusHour(int hour, int minute, int second, int plus);

    String plusMinute(int hour, int minute, int second, int plus);

    String plusSecond(int hour, int minute, int second, int plus);

    String minusYear(int year, int month, int day, int minus);

    String minusMonth(int year, int month, int day, int minus);

    String minusDay(int year, int month, int day, int minus);

    String minusHour(int hour, int minute, int second, int minus);

    String minusMinute(int hour, int minute, int second, int minus);

    String minusSecond(int hour, int minute, int second, int minus);

    boolean isBeforeDate(int year1, int month1, int day1, int year2, int month2,
            int day2);

    boolean isAfterDate(int year1, int month1, int day1, int year2, int month2,
            int day2);

    boolean equalsDate(int year1, int month1, int day1, int year2, int month2,
            int day2);

    boolean isBeforeTime(int hour1, int minute1, int second1, int hour2,
            int minute2, int second2);

    boolean isAfterTime(int hour1, int minute1, int second1, int hour2,
            int minute2, int second2);

    boolean equalsTime(int hour1, int minute1, int second1, int hour2,
            int minute2, int second2);

    boolean isLeapYear(int year, int month, int day);

    String getNowDate();

    String getSpecifiedDate(int year, int month, int day);

    String getSpecifiedJapaneseDate(int year, int month, int day);

    String createJapaneseDateString(int year, int month, int day);

    String parse(String dateString);

    String toJapaneseDate(int year, int month, int day);

    int[] parseAndGetDateElement(String dateString);

    int[] parseAndGetTimeElement(String timeString);

}
