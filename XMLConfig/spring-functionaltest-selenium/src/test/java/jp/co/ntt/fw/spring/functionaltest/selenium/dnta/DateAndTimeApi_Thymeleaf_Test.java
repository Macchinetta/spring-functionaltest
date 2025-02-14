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
package jp.co.ntt.fw.spring.functionaltest.selenium.dnta;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.openqa.selenium.By.id;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import jp.co.ntt.fw.spring.functionaltest.selenium.BrowserLocale;
import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

//JSP版未実装のためThymeleafのみ実行
public class DateAndTimeApi_Thymeleaf_Test extends FunctionTestSupport {

    private static WebDriver driver;

    private static final String VIEW_TYPE = "thymeleaf";

    public DateAndTimeApi_Thymeleaf_Test() {
        disableDefaultWebDriver();
    }

    @Before
    public void setUp() {

        if (driver == null) {
            driver = webDriverCreator.createLocaleSpecifiedDriver(BrowserLocale.JAPAN);
        }

        setCurrentWebDriver(driver);

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
            webDriverOperations.click(id("dnta0101001_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0101002_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0101003_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0102001_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0102002_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0102003_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0102004_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0102005_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0103001_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0103002_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0103003_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0103004_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0103005_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0201001_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0201002_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0202001_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0202002_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0301001_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0301002_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0301003_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0301004_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0301005_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0301006_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0301007_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0301008_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0301009_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0302001_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0302002_" + VIEW_TYPE));
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
    @Ignore("Junitと重複しているため")
    @Test
    public void testDNTA0303001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0303001_" + VIEW_TYPE));
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
    @Ignore("Junitと重複しているため")
    @Test
    public void testDNTA0303002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0303002_" + VIEW_TYPE));
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
    @Ignore("Junitと重複しているため")
    @Test
    public void testDNTA0303003() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0303003_" + VIEW_TYPE));
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
    @Ignore("Junitと重複しているため")
    @Test
    public void testDNTA0303004() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0303004_" + VIEW_TYPE));
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
    @Ignore("Junitと重複しているため")
    @Test
    public void testDNTA0303005() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0303005_" + VIEW_TYPE));
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
    @Ignore("Junitと重複しているため")
    @Test
    public void testDNTA0303006() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0303006_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0304001_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0305001_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0305002_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0305003_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0306001_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0306002_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0401001_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0401002_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0401003_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0401004_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0401004_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0401005_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0401005_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0401006_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0401007_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0401008_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0401009_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0402001_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0402002_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0403001_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0403002_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0403003_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0403004_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0403005_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0403006_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0403007_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0404001_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0404002_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0501001_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0501001_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0501001_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0501002_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0502001_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0502002_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0503001_" + VIEW_TYPE));
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
            webDriverOperations.click(id("dnta0504001_" + VIEW_TYPE));
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

    /**
     * <ul>
     * <li>LocalDateTime型のオブジェクトが表示できることを確認する</li>
     * <li>Thymeleafのみ実施</li>
     * </ul>
     */
    @Test
    public void testDNTA0601001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0601001_" + VIEW_TYPE));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDate")), is("2015/12/25 23:30:59"));
        }
    }

    /**
     * <ul>
     * <li>LocalDate型のオブジェクトが表示できることを確認する</li>
     * <li>Thymeleafのみ実施</li>
     * </ul>
     */
    @Test
    public void testDNTA0601002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0601002_" + VIEW_TYPE));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDate")), is("2015/12/25"));
        }
    }

    /**
     * <ul>
     * <li>LocalTime型のオブジェクトが表示できることを確認する</li>
     * <li>Thymeleafのみ実施</li>
     * </ul>
     */
    @Test
    public void testDNTA0601003() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0601003_" + VIEW_TYPE));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDate")), is("23:30:59"));
        }
    }

    /**
     * <ul>
     * <li>Date and Time API以外のオブジェクトを指定して表示した場合に例外が発生することを確認する</li>
     * <li>Thymeleafのみ実施</li>
     * </ul>
     */
    @Test
    public void testDNTA0601004() {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0601004_" + VIEW_TYPE));
        }

        // 取得した文字列を確認
        {
            // システムエラー画面に遷移すること
            String expectedTitle = "Unhandled System Error!";
            webDriverOperations.waitForDisplayed(ExpectedConditions.titleContains(expectedTitle));

            // エラーログが出力されていること
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    "org.thymeleaf.TemplateEngine", "[THYMELEAF]..*",
                    "..*TemplateProcessingException..*");
        }
    }

    /**
     * <ul>
     * <li>LocalDateTime型のオブジェクトが#temporals.formatメソッドで表示できることを確認する</li>
     * <li>Thymeleafのみ実施</li>
     * </ul>
     */
    @Test
    public void testDNTA0602001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0602001_" + VIEW_TYPE));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDate")), is("2015/12/25 23:30:59"));
            assertThat(webDriverOperations.getText(id("getDateFormat")), is("西暦 2015/12/25 金"));
            assertThat(webDriverOperations.getText(id("getDateLocale")),
                    is("December 25, 2015 11:30:59 PM"));
            assertThat(webDriverOperations.getText(id("getDateFormatLocale")),
                    is("AD 2015/12/25 Fri"));
        }
    }

    /**
     * <ul>
     * <li>配列形式のLocalDateTime型のオブジェクトがフォーマット文字列を指定して期待どおりにフォーマットできることを確認する</li>
     * <li>Thymeleafのみ実施</li>
     * </ul>
     */
    @Test
    public void testDNTA0602002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0602002_" + VIEW_TYPE));
        }

        // 取得した文字列を確認
        {
            // 結果要素格納用リスト
            List<String> resultList = new ArrayList<>();

            // arrayFormat(Temporal)の確認
            resultList.add(webDriverOperations.getText(id("getDate1")));
            resultList.add(webDriverOperations.getText(id("getDate2")));
            resultList.add(webDriverOperations.getText(id("getDate3")));
            resultList.add(webDriverOperations.getText(id("getDate4")));
            resultList.add(webDriverOperations.getText(id("getDate5")));

            assertThat(resultList, contains("2014/01/01 1:01:01", "2015/04/08 6:11:14",
                    "2016/07/15 11:21:27", "2017/10/22 16:31:40", "2018/01/29 21:41:53"));

            resultList.clear();

            // arrayFormat(Temporal, フォーマット文字列)の確認
            resultList.add(webDriverOperations.getText(id("getDateFormat1")));
            resultList.add(webDriverOperations.getText(id("getDateFormat2")));
            resultList.add(webDriverOperations.getText(id("getDateFormat3")));
            resultList.add(webDriverOperations.getText(id("getDateFormat4")));
            resultList.add(webDriverOperations.getText(id("getDateFormat5")));

            assertThat(resultList, contains("西暦 2014/01/01 水", "西暦 2015/04/08 水", "西暦 2016/07/15 金",
                    "西暦 2017/10/22 日", "西暦 2018/01/29 月"));

            resultList.clear();

            // arrayFormat(Temporal, ロケール)の確認
            resultList.add(webDriverOperations.getText(id("getDateLocale1")));
            resultList.add(webDriverOperations.getText(id("getDateLocale2")));
            resultList.add(webDriverOperations.getText(id("getDateLocale3")));
            resultList.add(webDriverOperations.getText(id("getDateLocale4")));
            resultList.add(webDriverOperations.getText(id("getDateLocale5")));

            assertThat(resultList,
                    contains("January 1, 2014 1:01:01 AM", "April 8, 2015 6:11:14 AM",
                            "July 15, 2016 11:21:27 AM", "October 22, 2017 4:31:40 PM",
                            "January 29, 2018 9:41:53 PM"));

            resultList.clear();

            // arrayFormat(Temporal, フォーマット文字列, ロケール)の確認
            resultList.add(webDriverOperations.getText(id("getDateFormatLocale1")));
            resultList.add(webDriverOperations.getText(id("getDateFormatLocale2")));
            resultList.add(webDriverOperations.getText(id("getDateFormatLocale3")));
            resultList.add(webDriverOperations.getText(id("getDateFormatLocale4")));
            resultList.add(webDriverOperations.getText(id("getDateFormatLocale5")));

            assertThat(resultList, contains("AD 2014/01/01 Wed", "AD 2015/04/08 Wed",
                    "AD 2016/07/15 Fri", "AD 2017/10/22 Sun", "AD 2018/01/29 Mon"));
        }
    }

    /**
     * <ul>
     * <li>リスト形式のLocalDateTime型のオブジェクトが#temporals.listFormatメソッドで表示できることを確認する</li>
     * <li>Thymeleafのみ実施</li>
     * </ul>
     */
    @Test
    public void testDNTA0602003() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0602003_" + VIEW_TYPE));
        }

        // 取得した文字列を確認
        {
            // 結果要素格納用リスト
            List<String> resultList = new ArrayList<>();

            // listFormat(Temporal)の確認
            resultList.add(webDriverOperations.getText(id("getDate1")));
            resultList.add(webDriverOperations.getText(id("getDate2")));
            resultList.add(webDriverOperations.getText(id("getDate3")));
            resultList.add(webDriverOperations.getText(id("getDate4")));
            resultList.add(webDriverOperations.getText(id("getDate5")));

            assertThat(resultList, contains("2015/04/08 6:11:14", "2016/07/15 11:21:27",
                    "2017/10/22 16:31:40", "2018/01/29 21:41:53", "2014/01/01 1:01:01"));

            resultList.clear();

            // listFormat(Temporal, フォーマット文字列)の確認
            resultList.add(webDriverOperations.getText(id("getDateFormat1")));
            resultList.add(webDriverOperations.getText(id("getDateFormat2")));
            resultList.add(webDriverOperations.getText(id("getDateFormat3")));
            resultList.add(webDriverOperations.getText(id("getDateFormat4")));
            resultList.add(webDriverOperations.getText(id("getDateFormat5")));

            assertThat(resultList, contains("西暦 2015/04/08 水", "西暦 2016/07/15 金", "西暦 2017/10/22 日",
                    "西暦 2018/01/29 月", "西暦 2014/01/01 水"));

            resultList.clear();

            // listFormat(Temporal, ロケール)の確認
            resultList.add(webDriverOperations.getText(id("getDateLocale1")));
            resultList.add(webDriverOperations.getText(id("getDateLocale2")));
            resultList.add(webDriverOperations.getText(id("getDateLocale3")));
            resultList.add(webDriverOperations.getText(id("getDateLocale4")));
            resultList.add(webDriverOperations.getText(id("getDateLocale5")));

            assertThat(resultList,
                    contains("April 8, 2015 6:11:14 AM", "July 15, 2016 11:21:27 AM",
                            "October 22, 2017 4:31:40 PM", "January 29, 2018 9:41:53 PM",
                            "January 1, 2014 1:01:01 AM"));

            resultList.clear();

            // listFormat(Temporal, フォーマット文字列, ロケール)の確認
            resultList.add(webDriverOperations.getText(id("getDateFormatLocale1")));
            resultList.add(webDriverOperations.getText(id("getDateFormatLocale2")));
            resultList.add(webDriverOperations.getText(id("getDateFormatLocale3")));
            resultList.add(webDriverOperations.getText(id("getDateFormatLocale4")));
            resultList.add(webDriverOperations.getText(id("getDateFormatLocale5")));

            assertThat(resultList, contains("AD 2015/04/08 Wed", "AD 2016/07/15 Fri",
                    "AD 2017/10/22 Sun", "AD 2018/01/29 Mon", "AD 2014/01/01 Wed"));
        }
    }

    /**
     * <ul>
     * <li>セット形式のLocalDateTime型のオブジェクトが#temporals.setFormatメソッドで表示できることを確認する</li>
     * <li>Thymeleafのみ実施</li>
     * </ul>
     */
    @Test
    public void testDNTA0602004() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0602004_" + VIEW_TYPE));
        }

        // 取得した文字列を確認
        {
            // 結果要素格納用リスト
            List<String> resultList = new ArrayList<>();

            // setFormat(Temporal)の確認
            resultList.add(webDriverOperations.getText(id("getDate1")));
            resultList.add(webDriverOperations.getText(id("getDate2")));
            resultList.add(webDriverOperations.getText(id("getDate3")));
            resultList.add(webDriverOperations.getText(id("getDate4")));
            resultList.add(webDriverOperations.getText(id("getDate5")));

            assertThat(resultList, hasSize(5));
            assertThat(resultList, hasItems("2014/01/01 1:01:01", "2015/04/08 6:11:14",
                    "2016/07/15 11:21:27", "2017/10/22 16:31:40", "2018/01/29 21:41:53"));

            resultList.clear();

            // setFormat(Temporal, フォーマット文字列)の確認
            resultList.add(webDriverOperations.getText(id("getDateFormat1")));
            resultList.add(webDriverOperations.getText(id("getDateFormat2")));
            resultList.add(webDriverOperations.getText(id("getDateFormat3")));
            resultList.add(webDriverOperations.getText(id("getDateFormat4")));
            resultList.add(webDriverOperations.getText(id("getDateFormat5")));

            assertThat(resultList, hasSize(5));
            assertThat(resultList, hasItems("西暦 2014/01/01 水", "西暦 2015/04/08 水", "西暦 2016/07/15 金",
                    "西暦 2017/10/22 日", "西暦 2018/01/29 月"));

            resultList.clear();

            // setFormat(Temporal, ロケール)の確認
            resultList.add(webDriverOperations.getText(id("getDateLocale1")));
            resultList.add(webDriverOperations.getText(id("getDateLocale2")));
            resultList.add(webDriverOperations.getText(id("getDateLocale3")));
            resultList.add(webDriverOperations.getText(id("getDateLocale4")));
            resultList.add(webDriverOperations.getText(id("getDateLocale5")));

            assertThat(resultList, hasSize(5));
            assertThat(resultList,
                    hasItems("January 1, 2014 1:01:01 AM", "April 8, 2015 6:11:14 AM",
                            "July 15, 2016 11:21:27 AM", "October 22, 2017 4:31:40 PM",
                            "January 29, 2018 9:41:53 PM"));

            resultList.clear();

            // setFormat(Temporal, フォーマット文字列, ロケール)の確認
            resultList.add(webDriverOperations.getText(id("getDateFormatLocale1")));
            resultList.add(webDriverOperations.getText(id("getDateFormatLocale2")));
            resultList.add(webDriverOperations.getText(id("getDateFormatLocale3")));
            resultList.add(webDriverOperations.getText(id("getDateFormatLocale4")));
            resultList.add(webDriverOperations.getText(id("getDateFormatLocale5")));

            assertThat(resultList, hasSize(5));
            assertThat(resultList, hasItems("AD 2014/01/01 Wed", "AD 2015/04/08 Wed",
                    "AD 2016/07/15 Fri", "AD 2017/10/22 Sun", "AD 2018/01/29 Mon"));
        }
    }

    /**
     * <ul>
     * <li>単一のLocalDateTime型のオブジェクトが#temporals.formatISOメソッドで表示できることを確認する</li>
     * <li>Thymeleafのみ実施</li>
     * </ul>
     */
    @Test
    public void testDNTA0602005() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0602005_" + VIEW_TYPE));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateISO")),
                    is("2015-12-25T23:30:59.000+0900"));
        }

    }

    /**
     * <ul>
     * <li>配列形式のLocalDateTime型のオブジェクトが#temporals.formatISOメソッドで表示できることを確認する</li>
     * <li>Thymeleafのみ実施</li>
     * </ul>
     */
    @Test
    public void testDNTA0602006() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0602006_" + VIEW_TYPE));
        }

        // 取得した文字列を確認
        {
            // 結果要素格納用リスト
            List<String> resultList = new ArrayList<>();

            resultList.add(webDriverOperations.getText(id("getDateISO1")));
            resultList.add(webDriverOperations.getText(id("getDateISO2")));
            resultList.add(webDriverOperations.getText(id("getDateISO3")));
            resultList.add(webDriverOperations.getText(id("getDateISO4")));
            resultList.add(webDriverOperations.getText(id("getDateISO5")));

            assertThat(resultList,
                    contains("2014-01-01T01:01:01.000+0900", "2015-04-08T06:11:14.000+0900",
                            "2016-07-15T11:21:27.000+0900", "2017-10-22T16:31:40.000+0900",
                            "2018-01-29T21:41:53.000+0900"));
        }
    }

    /**
     * <ul>
     * <li>リスト形式のLocalDateTime型のオブジェクトが#temporals.formatISOメソッドで表示できることを確認する</li>
     * <li>Thymeleafのみ実施</li>
     * </ul>
     */
    @Test
    public void testDNTA0602007() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0602007_" + VIEW_TYPE));
        }

        // 取得した文字列を確認
        {
            // 結果要素格納用リスト
            List<String> resultList = new ArrayList<>();

            resultList.add(webDriverOperations.getText(id("getDateISO1")));
            resultList.add(webDriverOperations.getText(id("getDateISO2")));
            resultList.add(webDriverOperations.getText(id("getDateISO3")));
            resultList.add(webDriverOperations.getText(id("getDateISO4")));
            resultList.add(webDriverOperations.getText(id("getDateISO5")));

            assertThat(resultList,
                    contains("2015-04-08T06:11:14.000+0900", "2016-07-15T11:21:27.000+0900",
                            "2017-10-22T16:31:40.000+0900", "2018-01-29T21:41:53.000+0900",
                            "2014-01-01T01:01:01.000+0900"));
        }
    }

    /**
     * <ul>
     * <li>セット形式のLocalDateTime型のオブジェクトが#temporals.formatISOメソッドで表示できることを確認する</li>
     * <li>Thymeleafのみ実施</li>
     * </ul>
     */
    @Test
    public void testDNTA0602008() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0602008_" + VIEW_TYPE));
        }

        // 取得した文字列を確認
        {
            // 結果要素格納用リスト
            List<String> resultList = new ArrayList<>();

            resultList.add(webDriverOperations.getText(id("getDateISO1")));
            resultList.add(webDriverOperations.getText(id("getDateISO2")));
            resultList.add(webDriverOperations.getText(id("getDateISO3")));
            resultList.add(webDriverOperations.getText(id("getDateISO4")));
            resultList.add(webDriverOperations.getText(id("getDateISO5")));

            assertThat(resultList,
                    hasItems("2016-07-15T11:21:27.000+0900", "2017-10-22T16:31:40.000+0900",
                            "2018-01-29T21:41:53.000+0900", "2014-01-01T01:01:01.000+0900",
                            "2015-04-08T06:11:14.000+0900"));
        }
    }

    /**
     * <ul>
     * <li>単一のTemporalオブジェクトに対してフィールドを返すメソッドを利用して期待する値が取得できることを確認する</li>
     * <li>Thymeleafのみ実施</li>
     * </ul>
     */
    @Test
    public void testDNTA0603001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0603001_" + VIEW_TYPE));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDay")), is("25"));
            assertThat(webDriverOperations.getText(id("getMonth")), is("12"));
            assertThat(webDriverOperations.getText(id("getMonthName")), is("12月"));
            assertThat(webDriverOperations.getText(id("getMonthNameShort")), is("12"));
            assertThat(webDriverOperations.getText(id("getYear")), is("2015"));
            assertThat(webDriverOperations.getText(id("getDayOfWeek")), is("5"));
            assertThat(webDriverOperations.getText(id("getDayOfWeekName")), is("金曜日"));
            assertThat(webDriverOperations.getText(id("getDayOfWeekNameShort")), is("金"));
            assertThat(webDriverOperations.getText(id("getHour")), is("23"));
            assertThat(webDriverOperations.getText(id("getMinute")), is("30"));
            assertThat(webDriverOperations.getText(id("getSecond")), is("59"));
            assertThat(webDriverOperations.getText(id("getNanosecond")), is("345"));
        }
    }

    /**
     * <ul>
     * <li>配列形式のTemporalオブジェクトに対してフィールドを返すメソッドを利用して期待する値が取得できることを確認する</li>
     * <li>Thymeleafのみ実施</li>
     * </ul>
     */
    @Test
    public void testDNTA0603002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0603002_" + VIEW_TYPE));
        }

        // 取得した文字列を確認
        {
            // 結果要素格納用リスト
            List<String> resultList = new ArrayList<>();

            // dayメソッドの確認
            resultList.add(webDriverOperations.getText(id("getDay1")));
            resultList.add(webDriverOperations.getText(id("getDay2")));
            resultList.add(webDriverOperations.getText(id("getDay3")));
            resultList.add(webDriverOperations.getText(id("getDay4")));
            resultList.add(webDriverOperations.getText(id("getDay5")));

            assertThat(resultList, contains("1", "8", "15", "22", "29"));

            resultList.clear();

            // monthメソッドの確認
            resultList.add(webDriverOperations.getText(id("getMonth1")));
            resultList.add(webDriverOperations.getText(id("getMonth2")));
            resultList.add(webDriverOperations.getText(id("getMonth3")));
            resultList.add(webDriverOperations.getText(id("getMonth4")));
            resultList.add(webDriverOperations.getText(id("getMonth5")));

            assertThat(resultList, contains("1", "4", "7", "10", "1"));

            resultList.clear();

            // monthNameメソッドの確認
            resultList.add(webDriverOperations.getText(id("getMonthName1")));
            resultList.add(webDriverOperations.getText(id("getMonthName2")));
            resultList.add(webDriverOperations.getText(id("getMonthName3")));
            resultList.add(webDriverOperations.getText(id("getMonthName4")));
            resultList.add(webDriverOperations.getText(id("getMonthName5")));

            assertThat(resultList, contains("1月", "4月", "7月", "10月", "1月"));

            resultList.clear();

            // monthNameShortメソッドの確認
            resultList.add(webDriverOperations.getText(id("getMonthNameShort1")));
            resultList.add(webDriverOperations.getText(id("getMonthNameShort2")));
            resultList.add(webDriverOperations.getText(id("getMonthNameShort3")));
            resultList.add(webDriverOperations.getText(id("getMonthNameShort4")));
            resultList.add(webDriverOperations.getText(id("getMonthNameShort5")));

            assertThat(resultList, contains("1", "4", "7", "10", "1"));

            resultList.clear();

            // yearメソッドの確認
            resultList.add(webDriverOperations.getText(id("getYear1")));
            resultList.add(webDriverOperations.getText(id("getYear2")));
            resultList.add(webDriverOperations.getText(id("getYear3")));
            resultList.add(webDriverOperations.getText(id("getYear4")));
            resultList.add(webDriverOperations.getText(id("getYear5")));

            assertThat(resultList, contains("2014", "2015", "2016", "2017", "2018"));

            resultList.clear();

            // dayOfWeekメソッドの確認
            resultList.add(webDriverOperations.getText(id("getDayOfWeek1")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeek2")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeek3")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeek4")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeek5")));

            assertThat(resultList, contains("3", "3", "5", "7", "1"));

            resultList.clear();

            // dayOfWeekNameメソッドの確認
            resultList.add(webDriverOperations.getText(id("getDayOfWeekName1")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeekName2")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeekName3")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeekName4")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeekName5")));

            assertThat(resultList, contains("水曜日", "水曜日", "金曜日", "日曜日", "月曜日"));

            resultList.clear();

            // dayOfWeekNameShortメソッドの確認
            resultList.add(webDriverOperations.getText(id("getDayOfWeekNameShort1")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeekNameShort2")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeekNameShort3")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeekNameShort4")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeekNameShort5")));

            assertThat(resultList, contains("水", "水", "金", "日", "月"));

            resultList.clear();

            // hourメソッドの確認
            resultList.add(webDriverOperations.getText(id("getHour1")));
            resultList.add(webDriverOperations.getText(id("getHour2")));
            resultList.add(webDriverOperations.getText(id("getHour3")));
            resultList.add(webDriverOperations.getText(id("getHour4")));
            resultList.add(webDriverOperations.getText(id("getHour5")));

            assertThat(resultList, contains("1", "6", "11", "16", "21"));

            resultList.clear();

            // minuteメソッドの確認
            resultList.add(webDriverOperations.getText(id("getMinute1")));
            resultList.add(webDriverOperations.getText(id("getMinute2")));
            resultList.add(webDriverOperations.getText(id("getMinute3")));
            resultList.add(webDriverOperations.getText(id("getMinute4")));
            resultList.add(webDriverOperations.getText(id("getMinute5")));

            assertThat(resultList, contains("1", "11", "21", "31", "41"));

            resultList.clear();

            // secondメソッドの確認
            resultList.add(webDriverOperations.getText(id("getSecond1")));
            resultList.add(webDriverOperations.getText(id("getSecond2")));
            resultList.add(webDriverOperations.getText(id("getSecond3")));
            resultList.add(webDriverOperations.getText(id("getSecond4")));
            resultList.add(webDriverOperations.getText(id("getSecond5")));

            assertThat(resultList, contains("1", "14", "27", "40", "53"));

            resultList.clear();

            // nanosecondメソッドの確認
            resultList.add(webDriverOperations.getText(id("getNanosecond1")));
            resultList.add(webDriverOperations.getText(id("getNanosecond2")));
            resultList.add(webDriverOperations.getText(id("getNanosecond3")));
            resultList.add(webDriverOperations.getText(id("getNanosecond4")));
            resultList.add(webDriverOperations.getText(id("getNanosecond5")));

            assertThat(resultList, contains("123", "234", "345", "456", "567"));
        }
    }

    /**
     * <ul>
     * <li>リスト形式のTemporalオブジェクトに対してフィールドを返すメソッドを利用して期待する値が取得できることを確認する</li>
     * <li>Thymeleafのみ実施</li>
     * </ul>
     */
    @Test
    public void testDNTA0603003() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0603003_" + VIEW_TYPE));
        }

        // 取得した文字列を確認
        {
            // 結果要素格納用リスト
            List<String> resultList = new ArrayList<>();

            // dayメソッドの確認
            resultList.add(webDriverOperations.getText(id("getDay1")));
            resultList.add(webDriverOperations.getText(id("getDay2")));
            resultList.add(webDriverOperations.getText(id("getDay3")));
            resultList.add(webDriverOperations.getText(id("getDay4")));
            resultList.add(webDriverOperations.getText(id("getDay5")));

            assertThat(resultList, contains("8", "15", "22", "29", "1"));

            resultList.clear();

            // monthメソッドの確認
            resultList.add(webDriverOperations.getText(id("getMonth1")));
            resultList.add(webDriverOperations.getText(id("getMonth2")));
            resultList.add(webDriverOperations.getText(id("getMonth3")));
            resultList.add(webDriverOperations.getText(id("getMonth4")));
            resultList.add(webDriverOperations.getText(id("getMonth5")));

            assertThat(resultList, contains("4", "7", "10", "1", "1"));

            resultList.clear();

            // monthNameメソッドの確認
            resultList.add(webDriverOperations.getText(id("getMonthName1")));
            resultList.add(webDriverOperations.getText(id("getMonthName2")));
            resultList.add(webDriverOperations.getText(id("getMonthName3")));
            resultList.add(webDriverOperations.getText(id("getMonthName4")));
            resultList.add(webDriverOperations.getText(id("getMonthName5")));

            assertThat(resultList, contains("4月", "7月", "10月", "1月", "1月"));

            resultList.clear();

            // monthNameShortメソッドの確認
            resultList.add(webDriverOperations.getText(id("getMonthNameShort1")));
            resultList.add(webDriverOperations.getText(id("getMonthNameShort2")));
            resultList.add(webDriverOperations.getText(id("getMonthNameShort3")));
            resultList.add(webDriverOperations.getText(id("getMonthNameShort4")));
            resultList.add(webDriverOperations.getText(id("getMonthNameShort5")));

            assertThat(resultList, contains("4", "7", "10", "1", "1"));

            resultList.clear();

            // yearメソッドの確認
            resultList.add(webDriverOperations.getText(id("getYear1")));
            resultList.add(webDriverOperations.getText(id("getYear2")));
            resultList.add(webDriverOperations.getText(id("getYear3")));
            resultList.add(webDriverOperations.getText(id("getYear4")));
            resultList.add(webDriverOperations.getText(id("getYear5")));

            assertThat(resultList, contains("2015", "2016", "2017", "2018", "2014"));

            resultList.clear();

            // dayOfWeekメソッドの確認
            resultList.add(webDriverOperations.getText(id("getDayOfWeek1")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeek2")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeek3")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeek4")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeek5")));

            assertThat(resultList, contains("3", "5", "7", "1", "3"));

            resultList.clear();

            // dayOfWeekNameメソッドの確認
            resultList.add(webDriverOperations.getText(id("getDayOfWeekName1")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeekName2")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeekName3")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeekName4")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeekName5")));

            assertThat(resultList, contains("水曜日", "金曜日", "日曜日", "月曜日", "水曜日"));

            resultList.clear();

            // dayOfWeekNameShortメソッドの確認
            resultList.add(webDriverOperations.getText(id("getDayOfWeekNameShort1")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeekNameShort2")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeekNameShort3")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeekNameShort4")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeekNameShort5")));

            assertThat(resultList, contains("水", "金", "日", "月", "水"));

            resultList.clear();

            // hourメソッドの確認
            resultList.add(webDriverOperations.getText(id("getHour1")));
            resultList.add(webDriverOperations.getText(id("getHour2")));
            resultList.add(webDriverOperations.getText(id("getHour3")));
            resultList.add(webDriverOperations.getText(id("getHour4")));
            resultList.add(webDriverOperations.getText(id("getHour5")));

            assertThat(resultList, contains("6", "11", "16", "21", "1"));

            resultList.clear();

            // minuteメソッドの確認
            resultList.add(webDriverOperations.getText(id("getMinute1")));
            resultList.add(webDriverOperations.getText(id("getMinute2")));
            resultList.add(webDriverOperations.getText(id("getMinute3")));
            resultList.add(webDriverOperations.getText(id("getMinute4")));
            resultList.add(webDriverOperations.getText(id("getMinute5")));

            assertThat(resultList, contains("11", "21", "31", "41", "1"));

            resultList.clear();

            // secondメソッドの確認
            resultList.add(webDriverOperations.getText(id("getSecond1")));
            resultList.add(webDriverOperations.getText(id("getSecond2")));
            resultList.add(webDriverOperations.getText(id("getSecond3")));
            resultList.add(webDriverOperations.getText(id("getSecond4")));
            resultList.add(webDriverOperations.getText(id("getSecond5")));

            assertThat(resultList, contains("14", "27", "40", "53", "1"));

            resultList.clear();

            // nanosecondメソッドの確認
            resultList.add(webDriverOperations.getText(id("getNanosecond1")));
            resultList.add(webDriverOperations.getText(id("getNanosecond2")));
            resultList.add(webDriverOperations.getText(id("getNanosecond3")));
            resultList.add(webDriverOperations.getText(id("getNanosecond4")));
            resultList.add(webDriverOperations.getText(id("getNanosecond5")));

            assertThat(resultList, contains("234", "345", "456", "567", "123"));
        }
    }

    /**
     * <ul>
     * <li>セット形式のTemporalオブジェクトに対してフィールドを返すメソッドを利用して期待する値が取得できることを確認する</li>
     * <li>Thymeleafのみ実施</li>
     * </ul>
     */
    @Test
    public void testDNTA0603004() {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0603004_" + VIEW_TYPE));
        }

        // 取得した文字列を確認
        {
            // 結果要素格納用リスト
            List<String> resultList = new ArrayList<>();

            // dayメソッドの確認
            resultList.add(webDriverOperations.getText(id("getDay1")));
            resultList.add(webDriverOperations.getText(id("getDay2")));
            resultList.add(webDriverOperations.getText(id("getDay3")));
            resultList.add(webDriverOperations.getText(id("getDay4")));
            resultList.add(webDriverOperations.getText(id("getDay5")));

            assertThat(resultList, hasSize(5));
            assertThat(resultList, hasItems("1", "8", "15", "22", "29"));

            resultList.clear();

            // monthメソッドの確認
            resultList.add(webDriverOperations.getText(id("getMonth1")));
            resultList.add(webDriverOperations.getText(id("getMonth2")));
            resultList.add(webDriverOperations.getText(id("getMonth3")));
            resultList.add(webDriverOperations.getText(id("getMonth4")));

            assertThat(resultList, hasSize(4));
            assertThat(resultList, hasItems("1", "4", "7", "10"));

            resultList.clear();

            // monthNameメソッドの確認
            resultList.add(webDriverOperations.getText(id("getMonthName1")));
            resultList.add(webDriverOperations.getText(id("getMonthName2")));
            resultList.add(webDriverOperations.getText(id("getMonthName3")));
            resultList.add(webDriverOperations.getText(id("getMonthName4")));

            assertThat(resultList, hasSize(4));
            assertThat(resultList, hasItems("1月", "4月", "7月", "10月"));

            resultList.clear();

            // monthNameShortメソッドの確認
            resultList.add(webDriverOperations.getText(id("getMonthNameShort1")));
            resultList.add(webDriverOperations.getText(id("getMonthNameShort2")));
            resultList.add(webDriverOperations.getText(id("getMonthNameShort3")));
            resultList.add(webDriverOperations.getText(id("getMonthNameShort4")));

            assertThat(resultList, hasSize(4));
            assertThat(resultList, hasItems("1", "4", "7", "10"));

            resultList.clear();

            // yearメソッドの確認
            resultList.add(webDriverOperations.getText(id("getYear1")));
            resultList.add(webDriverOperations.getText(id("getYear2")));
            resultList.add(webDriverOperations.getText(id("getYear3")));
            resultList.add(webDriverOperations.getText(id("getYear4")));
            resultList.add(webDriverOperations.getText(id("getYear5")));

            assertThat(resultList, hasSize(5));
            assertThat(resultList, hasItems("2014", "2015", "2016", "2017", "2018"));

            resultList.clear();

            // dayOfWeekメソッドの確認
            resultList.add(webDriverOperations.getText(id("getDayOfWeek1")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeek2")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeek3")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeek4")));

            assertThat(resultList, hasSize(4));
            assertThat(resultList, hasItems("1", "3", "5", "7"));

            resultList.clear();

            // dayOfWeekNameメソッドの確認
            resultList.add(webDriverOperations.getText(id("getDayOfWeekName1")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeekName2")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeekName3")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeekName4")));

            assertThat(resultList, hasSize(4));
            assertThat(resultList, hasItems("月曜日", "水曜日", "金曜日", "日曜日"));

            resultList.clear();

            // dayOfWeekNameShortメソッドの確認
            resultList.add(webDriverOperations.getText(id("getDayOfWeekNameShort1")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeekNameShort2")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeekNameShort3")));
            resultList.add(webDriverOperations.getText(id("getDayOfWeekNameShort4")));

            assertThat(resultList, hasSize(4));
            assertThat(resultList, hasItems("月", "水", "金", "日"));

            resultList.clear();

            // hourメソッドの確認
            resultList.add(webDriverOperations.getText(id("getHour1")));
            resultList.add(webDriverOperations.getText(id("getHour2")));
            resultList.add(webDriverOperations.getText(id("getHour3")));
            resultList.add(webDriverOperations.getText(id("getHour4")));
            resultList.add(webDriverOperations.getText(id("getHour5")));

            assertThat(resultList, hasSize(5));
            assertThat(resultList, hasItems("1", "6", "11", "16", "21"));

            resultList.clear();

            // minuteメソッドの確認
            resultList.add(webDriverOperations.getText(id("getMinute1")));
            resultList.add(webDriverOperations.getText(id("getMinute2")));
            resultList.add(webDriverOperations.getText(id("getMinute3")));
            resultList.add(webDriverOperations.getText(id("getMinute4")));
            resultList.add(webDriverOperations.getText(id("getMinute5")));

            assertThat(resultList, hasSize(5));
            assertThat(resultList, hasItems("1", "11", "21", "31", "41"));

            resultList.clear();

            // secondメソッドの確認
            resultList.add(webDriverOperations.getText(id("getSecond1")));
            resultList.add(webDriverOperations.getText(id("getSecond2")));
            resultList.add(webDriverOperations.getText(id("getSecond3")));
            resultList.add(webDriverOperations.getText(id("getSecond4")));
            resultList.add(webDriverOperations.getText(id("getSecond5")));

            assertThat(resultList, hasSize(5));
            assertThat(resultList, hasItems("1", "14", "27", "40", "53"));

            resultList.clear();

            // nanosecondメソッドの確認
            resultList.add(webDriverOperations.getText(id("getNanosecond1")));
            resultList.add(webDriverOperations.getText(id("getNanosecond2")));
            resultList.add(webDriverOperations.getText(id("getNanosecond3")));
            resultList.add(webDriverOperations.getText(id("getNanosecond4")));
            resultList.add(webDriverOperations.getText(id("getNanosecond5")));

            assertThat(resultList, hasSize(5));
            assertThat(resultList, hasItems("123", "234", "345", "456", "567"));
        }
    }
}
