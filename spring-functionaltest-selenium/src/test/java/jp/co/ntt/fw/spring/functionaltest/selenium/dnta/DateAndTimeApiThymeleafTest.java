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

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.id;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.test.annotation.IfProfileValue;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

//JSP版未実装のためThymeleafのみ実行
@IfProfileValue(name = "test.environment.view", values = { "thymeleaf" })
public class DateAndTimeApiThymeleafTest extends FunctionTestSupport {

    private static WebDriver deDriver;

    public DateAndTimeApiThymeleafTest() {
        disableDefaultWebDriver();
    }

    @Before
    public void setUp() {

        if (deDriver == null) {
            deDriver = webDriverCreator.createLocaleSpecifiedDriver("ja, JP");
        }

        setCurrentWebDriver(deDriver);

        // トップ画面での操作
        {
            webDriverOperations.getWebDriver().manage().window().maximize();
        }
    }

    /**
     * <ul>
     * <li>LocalDateTime型のオブジェクトが表示できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0601001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0601001"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDate")), is(
                    "2015/12/25 23:30:59"));
        }
    }

    /**
     * <ul>
     * <li>LocalDate型のオブジェクトが表示できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0601002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0601002"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDate")), is(
                    "2015/12/25"));
        }
    }

    /**
     * <ul>
     * <li>LocalTime型のオブジェクトが表示できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0601003() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0601003"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDate")), is(
                    "23:30:59"));
        }
    }

    /**
     * <ul>
     * <li>Date and Time API以外のオブジェクトを指定して表示した場合に例外が発生することを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0601004() {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0601004"));
        }

        // 取得した文字列を確認
        {
            // システムエラー画面に遷移すること
            String expectedTitle = "Unhandled System Error!";
            webDriverOperations.waitForDisplayed(ExpectedConditions
                    .titleContains(expectedTitle));

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
     * </ul>
     */
    @Test
    public void testDNTA0602001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0602001"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDate")), is(
                    "2015/12/25 23:30:59"));
            assertThat(webDriverOperations.getText(id("getDateFormat")), is(
                    "西暦 2015/12/25 金"));
            assertThat(webDriverOperations.getText(id("getDateLocale")), is(
                    "December 25, 2015 11:30:59 PM"));
            assertThat(webDriverOperations.getText(id("getDateFormatLocale")),
                    is("AD 2015/12/25 Fri"));
        }
    }

    /**
     * <ul>
     * <li>配列形式のLocalDateTime型のオブジェクトがフォーマット文字列を指定して期待どおりにフォーマットできることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0602002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0602002"));
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

            assertThat(resultList, contains("2014/01/01 1:01:01",
                    "2015/04/08 6:11:14", "2016/07/15 11:21:27",
                    "2017/10/22 16:31:40", "2018/01/29 21:41:53"));

            resultList.clear();

            // arrayFormat(Temporal, フォーマット文字列)の確認
            resultList.add(webDriverOperations.getText(id("getDateFormat1")));
            resultList.add(webDriverOperations.getText(id("getDateFormat2")));
            resultList.add(webDriverOperations.getText(id("getDateFormat3")));
            resultList.add(webDriverOperations.getText(id("getDateFormat4")));
            resultList.add(webDriverOperations.getText(id("getDateFormat5")));

            assertThat(resultList, contains("西暦 2014/01/01 水",
                    "西暦 2015/04/08 水", "西暦 2016/07/15 金", "西暦 2017/10/22 日",
                    "西暦 2018/01/29 月"));

            resultList.clear();

            // arrayFormat(Temporal, ロケール)の確認
            resultList.add(webDriverOperations.getText(id("getDateLocale1")));
            resultList.add(webDriverOperations.getText(id("getDateLocale2")));
            resultList.add(webDriverOperations.getText(id("getDateLocale3")));
            resultList.add(webDriverOperations.getText(id("getDateLocale4")));
            resultList.add(webDriverOperations.getText(id("getDateLocale5")));

            assertThat(resultList, contains("January 1, 2014 1:01:01 AM",
                    "April 8, 2015 6:11:14 AM", "July 15, 2016 11:21:27 AM",
                    "October 22, 2017 4:31:40 PM",
                    "January 29, 2018 9:41:53 PM"));

            resultList.clear();

            // arrayFormat(Temporal, フォーマット文字列, ロケール)の確認
            resultList.add(webDriverOperations.getText(id(
                    "getDateFormatLocale1")));
            resultList.add(webDriverOperations.getText(id(
                    "getDateFormatLocale2")));
            resultList.add(webDriverOperations.getText(id(
                    "getDateFormatLocale3")));
            resultList.add(webDriverOperations.getText(id(
                    "getDateFormatLocale4")));
            resultList.add(webDriverOperations.getText(id(
                    "getDateFormatLocale5")));

            assertThat(resultList, contains("AD 2014/01/01 Wed",
                    "AD 2015/04/08 Wed", "AD 2016/07/15 Fri",
                    "AD 2017/10/22 Sun", "AD 2018/01/29 Mon"));
        }
    }

    /**
     * <ul>
     * <li>リスト形式のLocalDateTime型のオブジェクトが#temporals.listFormatメソッドで表示できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0602003() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0602003"));
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

            assertThat(resultList, contains("2015/04/08 6:11:14",
                    "2016/07/15 11:21:27", "2017/10/22 16:31:40",
                    "2018/01/29 21:41:53", "2014/01/01 1:01:01"));

            resultList.clear();

            // listFormat(Temporal, フォーマット文字列)の確認
            resultList.add(webDriverOperations.getText(id("getDateFormat1")));
            resultList.add(webDriverOperations.getText(id("getDateFormat2")));
            resultList.add(webDriverOperations.getText(id("getDateFormat3")));
            resultList.add(webDriverOperations.getText(id("getDateFormat4")));
            resultList.add(webDriverOperations.getText(id("getDateFormat5")));

            assertThat(resultList, contains("西暦 2015/04/08 水",
                    "西暦 2016/07/15 金", "西暦 2017/10/22 日", "西暦 2018/01/29 月",
                    "西暦 2014/01/01 水"));

            resultList.clear();

            // listFormat(Temporal, ロケール)の確認
            resultList.add(webDriverOperations.getText(id("getDateLocale1")));
            resultList.add(webDriverOperations.getText(id("getDateLocale2")));
            resultList.add(webDriverOperations.getText(id("getDateLocale3")));
            resultList.add(webDriverOperations.getText(id("getDateLocale4")));
            resultList.add(webDriverOperations.getText(id("getDateLocale5")));

            assertThat(resultList, contains("April 8, 2015 6:11:14 AM",
                    "July 15, 2016 11:21:27 AM", "October 22, 2017 4:31:40 PM",
                    "January 29, 2018 9:41:53 PM",
                    "January 1, 2014 1:01:01 AM"));

            resultList.clear();

            // listFormat(Temporal, フォーマット文字列, ロケール)の確認
            resultList.add(webDriverOperations.getText(id(
                    "getDateFormatLocale1")));
            resultList.add(webDriverOperations.getText(id(
                    "getDateFormatLocale2")));
            resultList.add(webDriverOperations.getText(id(
                    "getDateFormatLocale3")));
            resultList.add(webDriverOperations.getText(id(
                    "getDateFormatLocale4")));
            resultList.add(webDriverOperations.getText(id(
                    "getDateFormatLocale5")));

            assertThat(resultList, contains("AD 2015/04/08 Wed",
                    "AD 2016/07/15 Fri", "AD 2017/10/22 Sun",
                    "AD 2018/01/29 Mon", "AD 2014/01/01 Wed"));
        }
    }

    /**
     * <ul>
     * <li>セット形式のLocalDateTime型のオブジェクトが#temporals.setFormatメソッドで表示できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0602004() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0602004"));
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
            assertThat(resultList, hasItems("2014/01/01 1:01:01",
                    "2015/04/08 6:11:14", "2016/07/15 11:21:27",
                    "2017/10/22 16:31:40", "2018/01/29 21:41:53"));

            resultList.clear();

            // setFormat(Temporal, フォーマット文字列)の確認
            resultList.add(webDriverOperations.getText(id("getDateFormat1")));
            resultList.add(webDriverOperations.getText(id("getDateFormat2")));
            resultList.add(webDriverOperations.getText(id("getDateFormat3")));
            resultList.add(webDriverOperations.getText(id("getDateFormat4")));
            resultList.add(webDriverOperations.getText(id("getDateFormat5")));

            assertThat(resultList, hasSize(5));
            assertThat(resultList, hasItems("西暦 2014/01/01 水",
                    "西暦 2015/04/08 水", "西暦 2016/07/15 金", "西暦 2017/10/22 日",
                    "西暦 2018/01/29 月"));

            resultList.clear();

            // setFormat(Temporal, ロケール)の確認
            resultList.add(webDriverOperations.getText(id("getDateLocale1")));
            resultList.add(webDriverOperations.getText(id("getDateLocale2")));
            resultList.add(webDriverOperations.getText(id("getDateLocale3")));
            resultList.add(webDriverOperations.getText(id("getDateLocale4")));
            resultList.add(webDriverOperations.getText(id("getDateLocale5")));

            assertThat(resultList, hasSize(5));
            assertThat(resultList, hasItems("January 1, 2014 1:01:01 AM",
                    "April 8, 2015 6:11:14 AM", "July 15, 2016 11:21:27 AM",
                    "October 22, 2017 4:31:40 PM",
                    "January 29, 2018 9:41:53 PM"));

            resultList.clear();

            // setFormat(Temporal, フォーマット文字列, ロケール)の確認
            resultList.add(webDriverOperations.getText(id(
                    "getDateFormatLocale1")));
            resultList.add(webDriverOperations.getText(id(
                    "getDateFormatLocale2")));
            resultList.add(webDriverOperations.getText(id(
                    "getDateFormatLocale3")));
            resultList.add(webDriverOperations.getText(id(
                    "getDateFormatLocale4")));
            resultList.add(webDriverOperations.getText(id(
                    "getDateFormatLocale5")));

            assertThat(resultList, hasSize(5));
            assertThat(resultList, hasItems("AD 2014/01/01 Wed",
                    "AD 2015/04/08 Wed", "AD 2016/07/15 Fri",
                    "AD 2017/10/22 Sun", "AD 2018/01/29 Mon"));
        }
    }

    /**
     * <ul>
     * <li>単一のLocalDateTime型のオブジェクトが#temporals.formatISOメソッドで表示できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0602005() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0602005"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateISO")), is(
                    "2015-12-25T23:30:59.000+0900"));
        }

    }

    /**
     * <ul>
     * <li>配列形式のLocalDateTime型のオブジェクトが#temporals.formatISOメソッドで表示できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0602006() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0602006"));
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

            assertThat(resultList, contains("2014-01-01T01:01:01.000+0900",
                    "2015-04-08T06:11:14.000+0900",
                    "2016-07-15T11:21:27.000+0900",
                    "2017-10-22T16:31:40.000+0900",
                    "2018-01-29T21:41:53.000+0900"));
        }
    }

    /**
     * <ul>
     * <li>リスト形式のLocalDateTime型のオブジェクトが#temporals.formatISOメソッドで表示できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0602007() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0602007"));
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

            assertThat(resultList, contains("2015-04-08T06:11:14.000+0900",
                    "2016-07-15T11:21:27.000+0900",
                    "2017-10-22T16:31:40.000+0900",
                    "2018-01-29T21:41:53.000+0900",
                    "2014-01-01T01:01:01.000+0900"));
        }
    }

    /**
     * <ul>
     * <li>セット形式のLocalDateTime型のオブジェクトが#temporals.formatISOメソッドで表示できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0602008() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0602008"));
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

            assertThat(resultList, hasItems("2016-07-15T11:21:27.000+0900",
                    "2017-10-22T16:31:40.000+0900",
                    "2018-01-29T21:41:53.000+0900",
                    "2014-01-01T01:01:01.000+0900",
                    "2015-04-08T06:11:14.000+0900"));
        }
    }

    /**
     * <ul>
     * <li>単一のTemporalオブジェクトに対してフィールドを返すメソッドを利用して期待する値が取得できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0603001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0603001"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDay")), is("25"));
            assertThat(webDriverOperations.getText(id("getMonth")), is("12"));
            assertThat(webDriverOperations.getText(id("getMonthName")), is(
                    "12月"));
            assertThat(webDriverOperations.getText(id("getMonthNameShort")), is(
                    "12"));
            assertThat(webDriverOperations.getText(id("getYear")), is("2015"));
            assertThat(webDriverOperations.getText(id("getDayOfWeek")), is(
                    "5"));
            assertThat(webDriverOperations.getText(id("getDayOfWeekName")), is(
                    "金曜日"));
            assertThat(webDriverOperations.getText(id("getDayOfWeekNameShort")),
                    is("金"));
            assertThat(webDriverOperations.getText(id("getHour")), is("23"));
            assertThat(webDriverOperations.getText(id("getMinute")), is("30"));
            assertThat(webDriverOperations.getText(id("getSecond")), is("59"));
            assertThat(webDriverOperations.getText(id("getNanosecond")), is(
                    "345"));
        }
    }

    /**
     * <ul>
     * <li>配列形式のTemporalオブジェクトに対してフィールドを返すメソッドを利用して期待する値が取得できることを確認する</li>
     * </ul>
     */
    @Test
    public void testDNTA0603002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0603002"));
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
            resultList.add(webDriverOperations.getText(id(
                    "getMonthNameShort1")));
            resultList.add(webDriverOperations.getText(id(
                    "getMonthNameShort2")));
            resultList.add(webDriverOperations.getText(id(
                    "getMonthNameShort3")));
            resultList.add(webDriverOperations.getText(id(
                    "getMonthNameShort4")));
            resultList.add(webDriverOperations.getText(id(
                    "getMonthNameShort5")));

            assertThat(resultList, contains("1", "4", "7", "10", "1"));

            resultList.clear();

            // yearメソッドの確認
            resultList.add(webDriverOperations.getText(id("getYear1")));
            resultList.add(webDriverOperations.getText(id("getYear2")));
            resultList.add(webDriverOperations.getText(id("getYear3")));
            resultList.add(webDriverOperations.getText(id("getYear4")));
            resultList.add(webDriverOperations.getText(id("getYear5")));

            assertThat(resultList, contains("2014", "2015", "2016", "2017",
                    "2018"));

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
            resultList.add(webDriverOperations.getText(id(
                    "getDayOfWeekName1")));
            resultList.add(webDriverOperations.getText(id(
                    "getDayOfWeekName2")));
            resultList.add(webDriverOperations.getText(id(
                    "getDayOfWeekName3")));
            resultList.add(webDriverOperations.getText(id(
                    "getDayOfWeekName4")));
            resultList.add(webDriverOperations.getText(id(
                    "getDayOfWeekName5")));

            assertThat(resultList, contains("水曜日", "水曜日", "金曜日", "日曜日", "月曜日"));

            resultList.clear();

            // dayOfWeekNameShortメソッドの確認
            resultList.add(webDriverOperations.getText(id(
                    "getDayOfWeekNameShort1")));
            resultList.add(webDriverOperations.getText(id(
                    "getDayOfWeekNameShort2")));
            resultList.add(webDriverOperations.getText(id(
                    "getDayOfWeekNameShort3")));
            resultList.add(webDriverOperations.getText(id(
                    "getDayOfWeekNameShort4")));
            resultList.add(webDriverOperations.getText(id(
                    "getDayOfWeekNameShort5")));

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
     * </ul>
     */
    @Test
    public void testDNTA0603003() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0603003"));
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
            resultList.add(webDriverOperations.getText(id(
                    "getMonthNameShort1")));
            resultList.add(webDriverOperations.getText(id(
                    "getMonthNameShort2")));
            resultList.add(webDriverOperations.getText(id(
                    "getMonthNameShort3")));
            resultList.add(webDriverOperations.getText(id(
                    "getMonthNameShort4")));
            resultList.add(webDriverOperations.getText(id(
                    "getMonthNameShort5")));

            assertThat(resultList, contains("4", "7", "10", "1", "1"));

            resultList.clear();

            // yearメソッドの確認
            resultList.add(webDriverOperations.getText(id("getYear1")));
            resultList.add(webDriverOperations.getText(id("getYear2")));
            resultList.add(webDriverOperations.getText(id("getYear3")));
            resultList.add(webDriverOperations.getText(id("getYear4")));
            resultList.add(webDriverOperations.getText(id("getYear5")));

            assertThat(resultList, contains("2015", "2016", "2017", "2018",
                    "2014"));

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
            resultList.add(webDriverOperations.getText(id(
                    "getDayOfWeekName1")));
            resultList.add(webDriverOperations.getText(id(
                    "getDayOfWeekName2")));
            resultList.add(webDriverOperations.getText(id(
                    "getDayOfWeekName3")));
            resultList.add(webDriverOperations.getText(id(
                    "getDayOfWeekName4")));
            resultList.add(webDriverOperations.getText(id(
                    "getDayOfWeekName5")));

            assertThat(resultList, contains("水曜日", "金曜日", "日曜日", "月曜日", "水曜日"));

            resultList.clear();

            // dayOfWeekNameShortメソッドの確認
            resultList.add(webDriverOperations.getText(id(
                    "getDayOfWeekNameShort1")));
            resultList.add(webDriverOperations.getText(id(
                    "getDayOfWeekNameShort2")));
            resultList.add(webDriverOperations.getText(id(
                    "getDayOfWeekNameShort3")));
            resultList.add(webDriverOperations.getText(id(
                    "getDayOfWeekNameShort4")));
            resultList.add(webDriverOperations.getText(id(
                    "getDayOfWeekNameShort5")));

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
     * </ul>
     */
    @Test
    public void testDNTA0603004() {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("dnta0603004"));
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
            resultList.add(webDriverOperations.getText(id(
                    "getMonthNameShort1")));
            resultList.add(webDriverOperations.getText(id(
                    "getMonthNameShort2")));
            resultList.add(webDriverOperations.getText(id(
                    "getMonthNameShort3")));
            resultList.add(webDriverOperations.getText(id(
                    "getMonthNameShort4")));

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
            assertThat(resultList, hasItems("2014", "2015", "2016", "2017",
                    "2018"));

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
            resultList.add(webDriverOperations.getText(id(
                    "getDayOfWeekName1")));
            resultList.add(webDriverOperations.getText(id(
                    "getDayOfWeekName2")));
            resultList.add(webDriverOperations.getText(id(
                    "getDayOfWeekName3")));
            resultList.add(webDriverOperations.getText(id(
                    "getDayOfWeekName4")));

            assertThat(resultList, hasSize(4));
            assertThat(resultList, hasItems("月曜日", "水曜日", "金曜日", "日曜日"));

            resultList.clear();

            // dayOfWeekNameShortメソッドの確認
            resultList.add(webDriverOperations.getText(id(
                    "getDayOfWeekNameShort1")));
            resultList.add(webDriverOperations.getText(id(
                    "getDayOfWeekNameShort2")));
            resultList.add(webDriverOperations.getText(id(
                    "getDayOfWeekNameShort3")));
            resultList.add(webDriverOperations.getText(id(
                    "getDayOfWeekNameShort4")));

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
