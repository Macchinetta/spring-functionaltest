/*
 * Copyright(c) 2014 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.selenium.dnta;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.openqa.selenium.By.id;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import jp.co.ntt.fw.spring.functionaltest.selenium.BrowserLocale;
import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

public class DateAndTimeApiTest extends FunctionTestSupport {

    private static WebDriver deDriver;

    public DateAndTimeApiTest() {
        disableDefaultWebDriver();
    }

    @Before
    public void setUp() {

        if (deDriver == null) {
            deDriver = webDriverCreator.createLocaleSpecifiedDriver(BrowserLocale.JAPAN);
        }

        setCurrentWebDriver(deDriver);

        // トップ画面での操作
        {
            webDriverOperations.getWebDriver().manage().window().maximize();
        }
    }

    /**
     * <ul>
     * <li>現在日時を取得する場合に、時刻を除いた日付だけ取得することができることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0101001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0101001"));
        }

        // DateFactoryから現在時刻のミリ秒まで取得した文字列を取得
        {
            webDriverOperations.click(id("getNowLocalDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is("2015-12-25"));
        }
    }

    /**
     * <ul>
     * <li>現在日時を取得する場合に、日付を除いた時刻だけ取得することができることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0101002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0101002"));
        }

        // DateFactoryから現在時刻のミリ秒まで取得した文字列を取得
        {
            webDriverOperations.click(id("getNowLocalTime"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is("23:30:59"));
        }
    }

    /**
     * <ul>
     * <li>現在日時を取得する場合に、日付・時刻の両方を取得することができることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0101003() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0101003"));
        }

        // DateFactoryから現在時刻のミリ秒まで取得した文字列を取得
        {
            webDriverOperations.click(id("getNowLocalDateTime"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is("2015-12-25T23:30:59"));
        }
    }

    /**
     * <ul>
     * <li>指定した日付を取得することができることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0102001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0102001"));
        }

        // 日付を設定する
        {
            webDriverOperations.appendText(id("year"), "2012");
            webDriverOperations.appendText(id("month"), "2");
            webDriverOperations.appendText(id("day"), "1");
            webDriverOperations.click(id("getSpecifiedDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is("2012-02-01"));
        }
    }

    /**
     * <ul>
     * <li>指定した時刻を取得することができることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0102002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0102002"));
        }

        // 時刻を設定する
        {
            webDriverOperations.appendText(id("hour"), "1");
            webDriverOperations.appendText(id("minute"), "2");
            webDriverOperations.appendText(id("second"), "3");
            webDriverOperations.click(id("getSpecifiedTime"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is("01:02:03"));
        }
    }

    /**
     * <ul>
     * <li>指定した日付・時刻の両方を取得することができることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0102003() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0102003"));
        }

        // 日付と時刻を設定する
        {
            webDriverOperations.appendText(id("year"), "2012");
            webDriverOperations.appendText(id("month"), "2");
            webDriverOperations.appendText(id("day"), "1");
            webDriverOperations.appendText(id("hour"), "1");
            webDriverOperations.appendText(id("minute"), "2");
            webDriverOperations.appendText(id("second"), "3");
            webDriverOperations.click(id("getSpecifiedDateTime"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is("2012-02-01T01:02:03"));
        }
    }

    /**
     * <ul>
     * <li>ある月の最終日の日付を取得することができることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0102004() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0102004"));
        }

        // 日付を設定する
        {
            webDriverOperations.appendText(id("year"), "2012");
            webDriverOperations.appendText(id("month"), "2");
            webDriverOperations.appendText(id("day"), "1");
            webDriverOperations.click(id("getLastDateOfSpecifiedMonth"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is("2012-02-29"));
        }
    }

    /**
     * <ul>
     * <li>ある日付の次の月曜日の日付を取得することができることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0102005() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0102005"));
        }

        // 日付を設定する
        {
            webDriverOperations.appendText(id("year"), "2012");
            webDriverOperations.appendText(id("month"), "2");
            webDriverOperations.appendText(id("day"), "1");
            webDriverOperations.click(id("getNextMondayOfSpecifiedDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is("2012-02-06"));
        }
    }

    /**
     * <ul>
     * <li>現在日時を取得する場合に、日付を除いた時刻とUTCとの時差のみを取得することができることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0103001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0103001"));
        }

        // DateFactoryから現在時刻のミリ秒まで取得した文字列を取得
        {
            webDriverOperations.click(id("getOffsetTime"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")),
                    is("23:30:59.000000567+09:00"));
        }
    }

    /**
     * <ul>
     * <li>現在日時を取得する場合に、日付・時刻・UTCとの時差のすべてを取得することができることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0103002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0103002"));
        }

        // DateFactoryから現在時刻のミリ秒まで取得した文字列を取得
        {
            webDriverOperations.click(id("getOffsetDateTime"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")),
                    is("2015-12-25T23:30:59.000000567+09:00"));
        }
    }

    /**
     * <ul>
     * <li>現在日時を取得する場合に、日付・時刻・UTCとの時差・地域のすべてを取得することができることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0103003() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0103003"));
        }

        // DateFactoryから現在時刻のミリ秒まで取得した文字列を取得
        {
            webDriverOperations.click(id("getZonedDateTime"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")),
                    is("2015-12-25T23:30:59.000000567+09:00[Asia/Tokyo]"));
        }
    }

    /**
     * <ul>
     * <li>TimeZoneに地域名を指定して日付を除いた時刻とUTCとの時差のみを取得できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0103004() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0103004"));
        }

        // Timezoneに地域名を指定して、DateFactoryから現在時刻のミリ秒まで取得した文字列を取得
        {
            webDriverOperations.appendText(id("zone"), "America/Los_Angeles");
            webDriverOperations.click(id("getDateTimeSpecifiedTimezoneByArea"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")),
                    is("2015-12-25T23:30:59.000000567-08:00[America/Los_Angeles]"));
        }
    }

    /**
     * <ul>
     * <li>TimeZoneにUTCとの時差を指定して日付を除いた時刻とUTCとの時差のみを取得できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0103005() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0103005"));
        }

        // TimezoneにUTCとの時差を指定して、DateFactoryから現在時刻のミリ秒まで取得した文字列を取得
        {
            webDriverOperations.appendText(id("zone"), "UTC-08:00");
            webDriverOperations.click(id("getDateTimeSpecifiedTimezoneByTimeDifference"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")),
                    is("2015-12-25T23:30:59.000000567-08:00[UTC-08:00]"));
        }
    }

    /**
     * <ul>
     * <li>Date and Time APIで提供されるPeriodクラスを用いて日付ベースの期間を取得できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0201001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0201001"));
        }

        // 比較する日付を設定する
        {
            webDriverOperations.appendText(id("year1"), "2010");
            webDriverOperations.appendText(id("month1"), "1");
            webDriverOperations.appendText(id("day1"), "15");
            webDriverOperations.appendText(id("year2"), "2011");
            webDriverOperations.appendText(id("month2"), "3");
            webDriverOperations.appendText(id("day2"), "18");
            webDriverOperations.click(id("compareDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getYear")), is("1"));
            assertThat(webDriverOperations.getText(id("getMonth")), is("2"));
            assertThat(webDriverOperations.getText(id("getDay")), is("3"));
        }
    }

    /**
     * <ul>
     * <li>Date and Time APIで提供されるPeriodクラスを用いて日付ベースの期間を取得できることを確認する(サマータイム考慮あり)</li>
     * </ul>
     */
    @Test
    public void testDNTA0201002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0201002"));
        }

        // 比較する日付を設定する
        {
            webDriverOperations.appendText(id("year1"), "2014");
            webDriverOperations.appendText(id("month1"), "3");
            webDriverOperations.appendText(id("day1"), "8");
            webDriverOperations.appendText(id("year2"), "2014");
            webDriverOperations.appendText(id("month2"), "3");
            webDriverOperations.appendText(id("day2"), "10");
            webDriverOperations.click(id("compareDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getYear")), is("0"));
            assertThat(webDriverOperations.getText(id("getMonth")), is("0"));
            assertThat(webDriverOperations.getText(id("getDay")), is("2"));
        }
    }

    /**
     * <ul>
     * <li>Date and Time APIで提供されるDurationクラスを用いて時刻ベースの期間を取得できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0202001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0202001"));
        }

        // 比較する時刻を設定する
        {
            webDriverOperations.appendText(id("hour1"), "11");
            webDriverOperations.appendText(id("minute1"), "50");
            webDriverOperations.appendText(id("second1"), "50");
            webDriverOperations.appendText(id("hour2"), "12");
            webDriverOperations.appendText(id("minute2"), "52");
            webDriverOperations.appendText(id("second2"), "53");
            webDriverOperations.click(id("compareTime"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getHour")), is("1"));
            assertThat(webDriverOperations.getText(id("getMinute")), is("2"));
            assertThat(webDriverOperations.getText(id("getSecond")), is("3"));
        }
    }

    /**
     * <ul>
     * <li>Date and Time APIで提供されるDurationクラスを用いて時刻ベースの期間を取得できることを確認する(サマータイム考慮あり)</li>
     * </ul>
     */
    @Test
    public void testDNTA0202002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0202002"));
        }

        // 比較する日時、Timezoneを設定する
        {
            webDriverOperations.appendText(id("zonedDateTimeyYear1"), "2014");
            webDriverOperations.appendText(id("zonedDateTimeMonth1"), "3");
            webDriverOperations.appendText(id("zonedDateTimeDay1"), "9");
            webDriverOperations.appendText(id("zonedDateTimeHour1"), "1");
            webDriverOperations.appendText(id("zonedDateTimeMinute1"), "2");
            webDriverOperations.appendText(id("zonedDateTimeSecond1"), "3");
            webDriverOperations.appendText(id("zonedDateTimeZone1"), "America/Los_Angeles");
            webDriverOperations.appendText(id("zonedDateTimeYear2"), "2014");
            webDriverOperations.appendText(id("zonedDateTimeMonth2"), "3");
            webDriverOperations.appendText(id("zonedDateTimeDay2"), "9");
            webDriverOperations.appendText(id("zonedDateTimeHour2"), "4");
            webDriverOperations.appendText(id("zonedDateTimeMinute2"), "5");
            webDriverOperations.appendText(id("zonedDateTimeSecond2"), "6");
            webDriverOperations.appendText(id("zonedDateTimeZone2"), "America/Los_Angeles");
            webDriverOperations.click(id("compareTimeWithSummerTime"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getHour")), is("2"));
            assertThat(webDriverOperations.getText(id("getMinute")), is("3"));
            assertThat(webDriverOperations.getText(id("getSecond")), is("3"));
        }
    }

    /**
     * <ul>
     * <li>LocalTime型のオブジェクトからLocalDateTime型のオブジェクトに変換できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0301001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0301001"));
        }

        // 不足する日付情報を設定する
        {
            webDriverOperations.appendText(id("year"), "2012");
            webDriverOperations.appendText(id("month"), "12");
            webDriverOperations.appendText(id("day"), "15");
            webDriverOperations.click(id("getLocalTimeToLocalDateTime"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is("2012-12-15T12:10:30"));
        }
    }

    /**
     * <ul>
     * <li>LocalDate型のオブジェクトからLocalDateTime型のオブジェクトに変換できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0301002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0301002"));
        }

        // 不足する時刻情報を設定する
        {
            webDriverOperations.appendText(id("hour"), "12");
            webDriverOperations.appendText(id("minute"), "10");
            webDriverOperations.appendText(id("second"), "30");
            webDriverOperations.click(id("getLocalDateToLocalDateTime"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is("2012-12-15T12:10:30"));
        }
    }

    /**
     * <ul>
     * <li>LocalDateTime型のオブジェクトからLocalTime型のオブジェクトに変換できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0301003() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0301003"));
        }

        // 指定の日時から時刻を取得する
        {
            webDriverOperations.click(id("getLocalDateTimeToLocalTime"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is("12:10:30"));
        }
    }

    /**
     * <ul>
     * <li>LocalDateTime型のオブジェクトからLocalDate型のオブジェクトに変換できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0301004() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0301004"));
        }

        // 指定の日時から時刻を取得する
        {
            webDriverOperations.click(id("getLocalDateTimeToLocalDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is("2012-12-15"));
        }
    }

    /**
     * <ul>
     * <li>OffsetTime型のオブジェクトからOffsetDateTime型のオブジェクトに変換できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0301005() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0301005"));
        }

        // 不足する日付情報を設定する
        {
            webDriverOperations.appendText(id("year"), "2012");
            webDriverOperations.appendText(id("month"), "12");
            webDriverOperations.appendText(id("day"), "15");
            webDriverOperations.click(id("getOffseTimeToOffsetDateTime"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")),
                    is("2012-12-15T12:10:30.000000567+09:00"));
        }
    }

    /**
     * <ul>
     * <li>OffsetDateTime型のオブジェクトからZonedDateTime 型のオブジェクトに変換できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0301006() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0301006"));
        }

        // TimeZone情報を設定する
        {
            webDriverOperations.appendText(id("zone"), "Asia/Tokyo");
            webDriverOperations.click(id("getOffsetDateTimeToZonedDateTime"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")),
                    is("2012-12-15T12:10:30.000000567+09:00[Asia/Tokyo]"));
        }
    }

    /**
     * <ul>
     * <li>ZonedDateTime型のオブジェクトからOffsetDateTime型のオブジェクトに変換できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0301007() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0301007"));
        }

        // 指定の日時・Timezoneから、日付・時刻・UTCとの時差を取得する
        {
            webDriverOperations.click(id("getZonedDateTimeToOffsetDateTime"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")),
                    is("2012-12-15T12:10:30.000000567+09:00"));
        }
    }

    /**
     * <ul>
     * <li>ZonedDateTime型のオブジェクトからOffsetTime型のオブジェクトに変換できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0301008() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0301008"));
        }

        // 指定の日時・Timezoneから、時刻・UTCとの時差を取得する
        {
            webDriverOperations.click(id("getZonedDateTimeToOffsetTime"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")),
                    is("12:10:30.000000567+09:00"));
        }
    }

    /**
     * <ul>
     * <li>LocalTime型のオブジェクトからOffsetTime型のオブジェクトに変換できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0301009() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0301009"));
        }

        // 指定の時刻から、時刻・UTCとの時差を取得する
        {
            webDriverOperations.click(id("getLocalTimeToOffsetTime"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is("12:10:30+09:00"));
        }
    }

    /**
     * <ul>
     * <li>LocalDateTime型のオブジェクトからjava.util.Date型のオブジェクトに変換できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0302001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0302001"));
        }

        // 指定時刻のLocalDateオブジェクトから、java.util.Date型のオブジェクトに変換する
        {
            webDriverOperations.click(id("getLocalDateTimeToUtilDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is("2012-12-15T12:10:30"));
        }
    }

    /**
     * <ul>
     * <li>java.util.Date型のオブジェクトからLocalDateTime型のオブジェクトに変換できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0302002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0302002"));
        }

        // 指定時刻のjava.util.Dateオブジェクトから、LocalDateTime型のオブジェクトに変換する
        {
            webDriverOperations.click(id("getUtilDateToLocalDateTime"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is("2012-12-15T12:10:30"));
        }
    }

    /**
     * <ul>
     * <li>LocalDate型のオブジェクトからjava.sql.Date型のオブジェクトに変換できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0303001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0303001"));
        }

        // 指定時刻のLocalDateオブジェクトから、java.sql.Date型のオブジェクトに変換する
        {
            webDriverOperations.click(id("getLocalDateToSqlDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is("2012-12-15"));
        }
    }

    /**
     * <ul>
     * <li>java.sql.Date型のオブジェクトからLocalDate型のオブジェクトに変換できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0303002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0303002"));
        }

        // 指定時刻のjava.sql.Dateオブジェクトから、LocalDate型のオブジェクトに変換する
        {
            webDriverOperations.click(id("getSqlDateToLocalDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is("2012-12-15"));
        }
    }

    /**
     * <ul>
     * <li>LocalDateTime型のオブジェクトからjava.sql.Timestamp型のオブジェクトに変換できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0303003() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0303003"));
        }

        // 指定時刻のjava.sql.Timestampオブジェクトから、LocalDateTime型のオブジェクトに変換する
        {
            webDriverOperations.click(id("getLocalDateTimeToSqlTimestamp"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is("2012-12-15T12:10:30"));
        }
    }

    /**
     * <ul>
     * <li>java.sql.Timestanp型のオブジェクトからLocalDateTime型のオブジェクトに変換できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0303004() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0303004"));
        }

        // 指定時刻のLocalDateTimeオブジェクトから、java.sql.Timestamp型のオブジェクトに変換する
        {
            webDriverOperations.click(id("getSqlTimestampToLocalDateTime"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is("2012-12-15T12:10:30"));
        }
    }

    /**
     * <ul>
     * <li>LocaTime型のオブジェクトからjava.sql.Time型のオブジェクトに変換できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0303005() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0303005"));
        }

        // 指定時刻のjava.sql.Timeオブジェクトから、LocaTime型のオブジェクトに変換する
        {
            webDriverOperations.click(id("getLocalTimeToSqlTime"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is("12:10:30"));
        }
    }

    /**
     * <ul>
     * <li>java.sql.Time型のオブジェクトからLocalTime型のオブジェクトに変換できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0303006() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0303006"));
        }

        // 指定時刻のLocalDateTimeオブジェクトから、java.sql.Timestamp型のオブジェクトに変換する
        {
            webDriverOperations.click(id("getSqlTimeToLocalTime"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is("12:10:30"));
        }
    }

    /**
     * <ul>
     * <li>ClassicDateFactoryからDate and Time APIのクラスを取得できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0304001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0304001"));
        }

        // DateFactoryから現在時刻を取得する
        {
            webDriverOperations.click(id("getDateFromDateFactory"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is("2013-12-09"));
        }
    }

    /**
     * <ul>
     * <li>Date and Time APIのクラスを文字列に変換できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0305001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0305001"));
        }

        // デフォルトの形式で日付文字列を取得する
        {
            webDriverOperations.click(id("getNormalFormat"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is("2012-12-15"));
        }
    }

    /**
     * <ul>
     * <li>Date and Time APIのクラスを事前定義されたフォーマットで文字列に変換できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0305002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0305002"));
        }

        // 事前定義された形式を指定して日付文字列を取得する
        {
            webDriverOperations.click(id("getPredefinedFormat"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is("20121215"));
        }
    }

    /**
     * <ul>
     * <li>Date and Time APIのクラスを任意のパターンのフォーマットで文字列に変換できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0305003() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0305003"));
        }

        // 任意のパターンで形式を指定して日付文字列を取得する
        {
            webDriverOperations.click(id("getAnyFormat"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is("西暦 2012/12/15 土"));
        }
    }

    /**
     * <ul>
     * <li>日付文字列からDate and Time APIのクラスであるLocalDateを生成できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0306001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0306001"));
        }

        // 読み取る日付文字列を設定する
        {
            webDriverOperations.appendText(id("targetDate"), "2015/12/25");
            webDriverOperations.click(id("getParseDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is("2015-12-25"));
        }
    }

    /**
     * <ul>
     * <li>時刻文字列からDate and Time APIのクラスであるLocalTimeを生成できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0306002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0306002"));
        }

        // 読み取る時間文字列を設定する
        {
            webDriverOperations.appendText(id("targetDate"), "14:09:20");
            webDriverOperations.click(id("getParseTime"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is("14:09:20"));
        }
    }

    /**
     * <ul>
     * <li>ある日付から指定した日数を増減した場合に、指定した値分増減した日付が取得できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0401001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0401001"));
        }

        // 日付と増減させる値を設定する
        {
            webDriverOperations.appendText(id("inputDateTime"), "2015/12/25");
            webDriverOperations.appendText(id("plus"), "10");
            webDriverOperations.appendText(id("minus"), "5");
            webDriverOperations.click(id("calcDay"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getPlus")), is("2016-01-04"));
            assertThat(webDriverOperations.getText(id("getMinus")), is("2015-12-20"));
        }
    }

    /**
     * <ul>
     * <li>ある日付から指定した月数を増減した場合に、指定した値分増減した日付が取得できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0401002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0401002"));
        }

        // 日付と増減させる値を設定する
        {
            webDriverOperations.appendText(id("inputDateTime"), "2015/12/25");
            webDriverOperations.appendText(id("plus"), "10");
            webDriverOperations.appendText(id("minus"), "5");
            webDriverOperations.click(id("calcMonth"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getPlus")), is("2016-10-25"));
            assertThat(webDriverOperations.getText(id("getMinus")), is("2015-07-25"));
        }
    }

    /**
     * <ul>
     * <li>ある日付から指定した年数を増減した場合に、指定した値分増減した日付が取得できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0401003() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0401003"));
        }

        // 日付と増減させる値を設定する
        {
            webDriverOperations.appendText(id("inputDateTime"), "2015/12/25");
            webDriverOperations.appendText(id("plus"), "10");
            webDriverOperations.appendText(id("minus"), "5");
            webDriverOperations.click(id("calcYear"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getPlus")), is("2025-12-25"));
            assertThat(webDriverOperations.getText(id("getMinus")), is("2010-12-25"));
        }
    }

    /**
     * <ul>
     * <li>閏年のある日付から指定した日数を増減した場合に、指定した値分増減した日付が取得できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0401004() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0401004"));
        }

        // 増減後閏年となる日付と増減させる値を設定する
        {
            webDriverOperations.appendText(id("inputDateTime"), "2012/02/28");
            webDriverOperations.appendText(id("plus"), "1");
            webDriverOperations.appendText(id("minus"), "1");
            webDriverOperations.click(id("calcDay"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getPlus")), is("2012-02-29"));
        }

        // 前画面に戻る
        {
            webDriverOperations.back();
            webDriverOperations.back();
            webDriverOperations.click(id("dnta0401004"));
        }

        // 増減後閏年となる日付と増減させる値を設定する
        {
            webDriverOperations.appendText(id("inputDateTime"), "2012/03/01");
            webDriverOperations.appendText(id("plus"), "1");
            webDriverOperations.appendText(id("minus"), "1");
            webDriverOperations.click(id("calcDay"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getMinus")), is("2012-02-29"));
        }
    }

    /**
     * <ul>
     * <li>閏年のある日付から指定した月数を増減した場合に、指定した値分増減した日付が取得できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0401005() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0401005"));
        }

        // 増減後閏年となる日付と増減させる値を設定する
        {
            webDriverOperations.appendText(id("inputDateTime"), "2012/01/31");
            webDriverOperations.appendText(id("plus"), "1");
            webDriverOperations.appendText(id("minus"), "1");
            webDriverOperations.click(id("calcMonth"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getPlus")), is("2012-02-29"));
        }

        // 前画面に戻る
        {
            webDriverOperations.back();
            webDriverOperations.back();
            webDriverOperations.click(id("dnta0401005"));
        }

        // 増減後閏年となる日付と増減させる値を設定する
        {
            webDriverOperations.appendText(id("inputDateTime"), "2012/03/31");
            webDriverOperations.appendText(id("plus"), "1");
            webDriverOperations.appendText(id("minus"), "1");
            webDriverOperations.click(id("calcMonth"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getMinus")), is("2012-02-29"));
        }
    }

    /**
     * <ul>
     * <li>閏年のある日付から指定した年数を増減した場合に、指定した値分増減した日付が取得できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0401006() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0401006"));
        }

        // 閏年となる日付と増減させる値を設定する
        {
            webDriverOperations.appendText(id("inputDateTime"), "2012/02/29");
            webDriverOperations.appendText(id("plus"), "1");
            webDriverOperations.appendText(id("minus"), "1");
            webDriverOperations.click(id("calcYear"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getPlus")), is("2013-02-28"));
            assertThat(webDriverOperations.getText(id("getMinus")), is("2011-02-28"));
        }
    }

    /**
     * <ul>
     * <li>ある時刻から指定した秒数を増減した場合に、指定した値分増減した時刻が取得できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0401007() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0401007"));
        }

        // 時刻と増減させる値を設定する
        {
            webDriverOperations.appendText(id("inputDateTime"), "12:50:55");
            webDriverOperations.appendText(id("plus"), "10");
            webDriverOperations.appendText(id("minus"), "5");
            webDriverOperations.click(id("calcSecond"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getPlus")), is("12:51:05"));
            assertThat(webDriverOperations.getText(id("getMinus")), is("12:50:50"));
        }
    }

    /**
     * <ul>
     * <li>ある時刻から指定した分数を増減した場合に、指定した値分増減した時刻が取得できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0401008() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0401008"));
        }

        // 時刻と増減させる値を設定する
        {
            webDriverOperations.appendText(id("inputDateTime"), "12:50:55");
            webDriverOperations.appendText(id("plus"), "10");
            webDriverOperations.appendText(id("minus"), "5");
            webDriverOperations.click(id("calcMinute"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getPlus")), is("13:00:55"));
            assertThat(webDriverOperations.getText(id("getMinus")), is("12:45:55"));
        }
    }

    /**
     * <ul>
     * <li>ある時刻から指定した時間数を増減した場合に、指定した値分増減した時刻が取得できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0401009() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0401009"));
        }

        // 時刻と増減させる値を設定する
        {
            webDriverOperations.appendText(id("inputDateTime"), "12:50:55");
            webDriverOperations.appendText(id("plus"), "10");
            webDriverOperations.appendText(id("minus"), "5");
            webDriverOperations.click(id("calcHour"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getPlus")), is("22:50:55"));
            assertThat(webDriverOperations.getText(id("getMinus")), is("07:50:55"));
        }
    }

    /**
     * <ul>
     * <li>ある時刻に対して、別の時刻を与えてそれが過去・未来・同時かを判定できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0402001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0402001"));
        }

        // 比較する時刻情報を設定する
        {
            webDriverOperations.appendText(id("hour1"), "7");
            webDriverOperations.appendText(id("minute1"), "30");
            webDriverOperations.appendText(id("second1"), "00");
            webDriverOperations.appendText(id("hour2"), "12");
            webDriverOperations.appendText(id("minute2"), "00");
            webDriverOperations.appendText(id("second2"), "00");
            webDriverOperations.appendText(id("hour3"), "17");
            webDriverOperations.appendText(id("minute3"), "30");
            webDriverOperations.appendText(id("second3"), "00");
            webDriverOperations.click(id("compareTime"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("NowIsBeforePast")), is("false"));
            assertThat(webDriverOperations.getText(id("NowIsBeforeNow")), is("false"));
            assertThat(webDriverOperations.getText(id("NowIsBeforeFuture")), is("true"));
            assertThat(webDriverOperations.getText(id("NowIsAfterPast")), is("true"));
            assertThat(webDriverOperations.getText(id("NowIsAfterNow")), is("false"));
            assertThat(webDriverOperations.getText(id("NowIsAfterFuture")), is("false"));
            assertThat(webDriverOperations.getText(id("NowEqualsPast")), is("false"));
            assertThat(webDriverOperations.getText(id("NowEqualsNow")), is("true"));
            assertThat(webDriverOperations.getText(id("NowEqualsFuture")), is("false"));
        }
    }

    /**
     * <ul>
     * <li>ある日付に対して、別の日付を与えてそれが過去・未来・同時かを判定できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0402002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0402002"));
        }

        // 比較する日付情報を設定する
        {
            webDriverOperations.appendText(id("year1"), "2015");
            webDriverOperations.appendText(id("month1"), "6");
            webDriverOperations.appendText(id("day1"), "1");
            webDriverOperations.appendText(id("year2"), "2015");
            webDriverOperations.appendText(id("month2"), "7");
            webDriverOperations.appendText(id("day2"), "1");
            webDriverOperations.appendText(id("year3"), "2015");
            webDriverOperations.appendText(id("month3"), "8");
            webDriverOperations.appendText(id("day3"), "1");
            webDriverOperations.click(id("compareDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("NowIsBeforePast")), is("false"));
            assertThat(webDriverOperations.getText(id("NowIsBeforeNow")), is("false"));
            assertThat(webDriverOperations.getText(id("NowIsBeforeFuture")), is("true"));
            assertThat(webDriverOperations.getText(id("NowIsAfterPast")), is("true"));
            assertThat(webDriverOperations.getText(id("NowIsAfterNow")), is("false"));
            assertThat(webDriverOperations.getText(id("NowIsAfterFuture")), is("false"));
            assertThat(webDriverOperations.getText(id("NowEqualsPast")), is("false"));
            assertThat(webDriverOperations.getText(id("NowEqualsNow")), is("true"));
            assertThat(webDriverOperations.getText(id("NowEqualsFuture")), is("false"));
        }
    }

    /**
     * <ul>
     * <li>与えられた日付文字列が妥当であることを判定できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0403001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0403001"));
        }

        // 妥当な日付文字列を設定する
        {
            webDriverOperations.appendText(id("targetDate"), "2015/12/25");
            webDriverOperations.click(id("checkDateString"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("checkResult")), is("true"));
        }
    }

    /**
     * <ul>
     * <li>与えられた日付文字列に不正な文字種が含まれる場合、日付文字列が妥当でないことを判定できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0403002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0403002"));
        }

        // 不正な文字列が含まれる日付文字列を設定する
        {
            webDriverOperations.appendText(id("targetDate"), "2017/02/29");
            webDriverOperations.click(id("checkDateString"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("checkResult")), is("false"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(
                    "^org\\.terasoluna\\.gfw\\.web\\.logging\\.TraceLoggingInterceptor$",
                    ".*getResult=java\\.time\\.format\\.DateTimeParseException.*");
        }
    }

    /**
     * <ul>
     * <li>与えられた日付文字列が存在しない日付である場合、日付文字列が妥当でないことを判定できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0403003() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0403003"));
        }

        // 存在しない日付を設定する
        {
            webDriverOperations.appendText(id("targetDate"), "aaa");
            webDriverOperations.click(id("checkDateString"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("checkResult")), is("false"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(
                    "^org\\.terasoluna\\.gfw\\.web\\.logging\\.TraceLoggingInterceptor$",
                    ".*getResult=java\\.time\\.format\\.DateTimeParseException.*");
        }

    }

    /**
     * <ul>
     * <li>与えられた時刻文字列が妥当であることか判定できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0403004() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0403004"));
        }

        // 妥当な時刻文字列を設定する
        {
            webDriverOperations.appendText(id("targetDate"), "12:10:30");
            webDriverOperations.click(id("checkTimeString"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("checkResult")), is("true"));
        }
    }

    /**
     * <ul>
     * <li>与えられた時刻文字列に不正な文字種が含まれる場合、時刻文字列妥当でないことを判定できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0403005() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0403005"));
        }

        // 不正な文字列が含まれる時刻文字列を設定する
        {
            webDriverOperations.appendText(id("targetDate"), "aaa");
            webDriverOperations.click(id("checkTimeString"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("checkResult")), is("false"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(
                    "^org\\.terasoluna\\.gfw\\.web\\.logging\\.TraceLoggingInterceptor$",
                    ".*getResult=java\\.time\\.format\\.DateTimeParseException.*");
        }

    }

    /**
     * <ul>
     * <li>与えられた時刻文字列が存在しない時刻である場合、時刻文字列が妥当でないことを判定できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0403006() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0403006"));
        }

        // 存在しない時刻を設定する
        {
            webDriverOperations.appendText(id("targetDate"), "25:01:02");
            webDriverOperations.click(id("checkTimeString"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("checkResult")), is("false"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(
                    "^org\\.terasoluna\\.gfw\\.web\\.logging\\.TraceLoggingInterceptor$",
                    ".*getResult=java\\.time\\.format\\.DateTimeParseException.*");
        }

    }

    /**
     * <ul>
     * <li>与えられた日付文字列が閏年に該当するか判定できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0403007() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0403007"));
        }

        // 閏年となる日付を設定する
        {
            webDriverOperations.appendText(id("targetDate"), "2012/02/01");
            webDriverOperations.click(id("checkLeapYear"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("checkResult")), is("true"));
        }
    }

    /**
     * <ul>
     * <li>Date and Time APIのクラスから年月日をそれぞれ取得できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0404001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0404001"));
        }

        // 日付情報を設定する
        {
            webDriverOperations.appendText(id("target"), "2015/12/25");
            webDriverOperations.click(id("getEachValueOfDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("year")), is("2015"));
            assertThat(webDriverOperations.getText(id("month")), is("12"));
            assertThat(webDriverOperations.getText(id("dayOfMonth")), is("25"));
            assertThat(webDriverOperations.getText(id("dayOfYear")), is("359"));
        }
    }

    /**
     * <ul>
     * <li>Date and Time APIのクラスから時分秒をそれぞれ取得できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0404002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0404002"));
        }

        // 時刻情報を設定する
        {
            webDriverOperations.appendText(id("target"), "12:10:30");
            webDriverOperations.click(id("getEachValueOfTime"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("hour")), is("12"));
            assertThat(webDriverOperations.getText(id("minute")), is("10"));
            assertThat(webDriverOperations.getText(id("second")), is("30"));
        }
    }

    /**
     * <ul>
     * <li>Date and Time APIのクラスを用いて和暦を取得できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0501001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0501001"));
        }

        // DateFactoryから現在時刻を取得する
        {
            webDriverOperations.click(id("getNowDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")),
                    is("Japanese Heisei 27-12-25"));
        }

        // 前画面に戻る
        {
            webDriverOperations.back();
            webDriverOperations.back();
            webDriverOperations.click(id("dnta0501001"));
        }

        // 西暦で日付情報を設定する
        {
            webDriverOperations.appendText(id("year1"), "2014");
            webDriverOperations.appendText(id("month1"), "12");
            webDriverOperations.appendText(id("day1"), "25");
            webDriverOperations.click(id("getSpecifiedDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")),
                    is("Japanese Heisei 26-12-25"));
        }

        // 前画面に戻る
        {
            webDriverOperations.back();
            webDriverOperations.back();
            webDriverOperations.click(id("dnta0501001"));
        }

        // 和暦で日付情報を設定する
        {
            webDriverOperations.appendText(id("year1"), "26");
            webDriverOperations.appendText(id("month1"), "12");
            webDriverOperations.appendText(id("day1"), "25");
            webDriverOperations.click(id("getSpecifiedJapaneseDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")),
                    is("Japanese Heisei 26-12-25"));
        }
    }

    /**
     * <ul>
     * <li>Date and Time APIのJapaneseDateクラスを用いて和暦を取得する際に、明治6年よりも前を指定すると例外が発生することを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0501002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0501002"));
        }

        // 西暦で明治6年よりも前の日付情報を設定する
        {
            webDriverOperations.appendText(id("year1"), "1500");
            webDriverOperations.appendText(id("month1"), "12");
            webDriverOperations.appendText(id("day1"), "25");
            webDriverOperations.click(id("getSpecifiedDate"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(
                    "^org\\.terasoluna\\.gfw\\.web\\.logging\\.TraceLoggingInterceptor$",
                    ".*getResult=java\\.time\\.DateTimeException.*");
        }
    }

    /**
     * <ul>
     * <li>Date and Time APIのクラスを用いて取得した和暦を任意のパターンで文字列に変換できることを確認する(空白パディングなし)</li>
     * </ul>
     */
    @Test
    public void testDNTA0502001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0502001"));
        }

        // 西暦で空白パディングされない日付情報を設定する
        {
            webDriverOperations.appendText(id("year2"), "2015");
            webDriverOperations.appendText(id("month2"), "12");
            webDriverOperations.appendText(id("day2"), "25");
            webDriverOperations.click(id("format"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is("平成27年12月25日"));
        }
    }

    /**
     * <ul>
     * <li>Date and Time APIのクラスを用いて取得した和暦を任意のパターンで文字列に変換できることを確認する(空白パディングあり)</li>
     * </ul>
     */
    @Test
    public void testDNTA0502002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0502002"));
        }

        // 西暦で空白パディングされる日付情報を設定する
        {
            webDriverOperations.appendText(id("year2"), "1992");
            webDriverOperations.appendText(id("month2"), "1");
            webDriverOperations.appendText(id("day2"), "1");
            webDriverOperations.click(id("format"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is("平成 4年 1月 1日"));
        }
    }

    /**
     * <ul>
     * <li>和暦文字列からDate and Time APIのクラスを用いて和暦を取得できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0503001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0503001"));
        }

        // 和暦文字列を設定する
        {
            webDriverOperations.appendText(id("targetDate"), "平成5年01月01日");
            webDriverOperations.click(id("parse"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")),
                    is("Japanese Heisei 5-01-01"));
        }
    }

    /**
     * <ul>
     * <li>西暦から和暦の変換が行えることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0504001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0504001"));
        }

        // 西暦で日付情報を設定する
        {
            webDriverOperations.appendText(id("year3"), "2015");
            webDriverOperations.appendText(id("month3"), "12");
            webDriverOperations.appendText(id("day3"), "25");
            webDriverOperations.click(id("toJapaneseDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")),
                    is("Japanese Heisei 27-12-25"));
        }
    }
}
