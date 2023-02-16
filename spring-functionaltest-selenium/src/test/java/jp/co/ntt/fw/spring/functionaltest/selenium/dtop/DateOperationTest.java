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
package jp.co.ntt.fw.spring.functionaltest.selenium.dtop;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.openqa.selenium.By.id;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

public class DateOperationTest extends FunctionTestSupport {

    private static WebDriver deDriver;

    public DateOperationTest() {
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
     * <li>現在日時を取得する場合に、ミリ秒まで取得することができることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0101001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0101001"));
        }

        // DataTimeクラスから現在時刻のミリ秒まで取得した文字列を取得
        {
            webDriverOperations.click(id("getNowDateTime"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is(
                    "2013/12/09 13:50:12.100 +09:00"));
        }
    }

    /**
     * <ul>
     * <li>現在日時を取得する場合に、TimeZoneと時間を除いた日付だけ取得することができることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0101002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0101002"));
        }

        // LocalDateクラスから時間を除いた現在日付まで取得した文字列を取得
        {
            webDriverOperations.click(id("getNowLocalDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is(
                    "2013/12/09"));
        }
    }

    /**
     * <ul>
     * <li>現在日時を取得する場合に、TimeZoneと日付を除いた時間だけ取得することができることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0101003() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0101003"));
        }

        // LocalTimeクラスから日付を除いた現在時刻のミリ秒まで取得した文字列を取得
        {
            webDriverOperations.click(id("getNowLocalTime"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is(
                    "13:50:12"));
        }
    }

    /**
     * <ul>
     * <li>現在日時を取得する場合に、現在日付の真夜中(0時)を取得することができることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0101004() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0101004"));
        }

        // 現在日付の真夜中(0時)を取得する
        {
            webDriverOperations.click(id("getNowDateMidnight"));
        }

        // 取得した文字列を確認
        {
            // DateMidnight#toString()参照
            assertThat(webDriverOperations.getText(id("getDateResult")), is(
                    "2013/12/09 00:00:00.000 +09:00"));
        }
    }

    /**
     * <ul>
     * <li>現在日時を取得する場合に、現在日付の開始時刻(0時)を取得することができることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0101005() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0101005"));
        }

        // 現在日付の開始時刻(0時)を取得した文字列を取得
        {
            webDriverOperations.click(id("getWithTimeAtStartOfDay"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is(
                    "2013/12/09 00:00:00.000 +09:00"));
        }
    }

    /**
     * <ul>
     * <li>TimeZoneを指定して現在時刻を取得する場合に、異なるTimezoneに変換することができることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0102001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0102001"));
        }

        // DataTimeクラスを利用して取得するとき、異なるTimezoneを指定して現在時刻を取得
        {
            webDriverOperations.appendText(id("targetTimeZone"),
                    "America/Los_Angeles");
            webDriverOperations.click(id("getDateTimeSpecifiedTimezone"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is(
                    "2013/12/08 20:50:12.100 -08:00"));
        }
    }

    /**
     * <ul>
     * <li>DateTimeで日付を指定した場合、指定した日付の年月日等を個別に取得することができることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0103001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0103001"));
        }

        // DataTimeクラスを利用して、特定の日時の年月日時分秒ミリ秒を設定したDateTimeについて、それぞれ個別に取得
        {
            webDriverOperations.click(id("getPartOfDateTime"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResultYear")), is(
                    "2013"));
            assertThat(webDriverOperations.getText(id("getDateResultMonth")),
                    is("12"));
            assertThat(webDriverOperations.getText(id("getDateResultDay")), is(
                    "9"));
            assertThat(webDriverOperations.getText(id("getDateResultWeek")), is(
                    "1"));
            assertThat(webDriverOperations.getText(id("getDateResultHour")), is(
                    "13"));
            assertThat(webDriverOperations.getText(id("getDateResultMinute")),
                    is("50"));
            assertThat(webDriverOperations.getText(id("getDateResultSecond")),
                    is("12"));
            assertThat(webDriverOperations.getText(id("getDateResultMillis")),
                    is("100"));
        }
    }

    /**
     * <ul>
     * <li>Date型の日時生成した場合に、DateTimeへの型変換ができることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0201001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0201001"));
        }

        // 生成したDateTimeを"yyyy/MM/dd HH:mm:ss.SSS ZZ"形式で文字列日時を取得
        {
            webDriverOperations.click(id("getDateToDateTimeObject"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is(
                    "2013/12/09 13:50:12.100 +09:00"));
        }
    }

    /**
     * <ul>
     * <li>DateTimeの日時生成した場合に、Date型への型変換ができることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0201002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0201002"));
        }

        // 生成したDateを"yyyy/MM/dd HH:mm:ss.SSS"形式で文字列日時を取得
        {
            webDriverOperations.click(id("getDateTimeToDateObject"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is(
                    "2013/12/09 13:50:12.100"));
        }
    }

    /**
     * <ul>
     * <li>日付文字列をパースする場合に、DateTimeに変換できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0202001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0202001"));
        }

        // 日付文字列を、DateTimeでパースして日付に変換
        {
            webDriverOperations.appendText(id("targetDate"), "2013/12/09");
            webDriverOperations.click(id("getParseDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is(
                    "2013/12/09"));
        }
    }

    /**
     * <ul>
     * <li>指定した日数を増減した場合に、指定した値分の日付が取得できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0301001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0301001"));
        }

        // 指定した日時を設定したDateTimeから指定した日数を増減した場合に、指定した値分増減した日付を取得する。
        {
            webDriverOperations.appendText(id("targetIncDecDate"),
                    "2013/12/09");
            webDriverOperations.appendText(id("targetIncreaseNum"), "1");
            webDriverOperations.appendText(id("targetDecreaseNum"), "1");
            webDriverOperations.click(id("calcDayDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getStartDateResult")),
                    is("2013/12/10"));
            assertThat(webDriverOperations.getText(id("getEndDateResult")), is(
                    "2013/12/08"));
        }
    }

    /**
     * <ul>
     * <li>指定した月数を増減した場合に、指定した値分の日付が取得できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0301002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0301002"));
        }

        // 指定した日時を設定したDateTimeから指定した月数を増減した場合に、指定した値分増減した日付が取得する。
        {
            webDriverOperations.appendText(id("targetIncDecDate"),
                    "2013/12/09");
            webDriverOperations.appendText(id("targetIncreaseNum"), "1");
            webDriverOperations.appendText(id("targetDecreaseNum"), "1");
            webDriverOperations.click(id("calcMonthDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getStartDateResult")),
                    is("2014/01/09"));
            assertThat(webDriverOperations.getText(id("getEndDateResult")), is(
                    "2013/11/09"));
        }
    }

    /**
     * <ul>
     * <li>指定した年数を増減した場合に、指定した値分の日付が取得できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0301003() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0301003"));
        }

        // 指定した日時を設定したDateTimeから指定した年数を増減した場合に、指定した値分増減した日付が取得する。
        {
            webDriverOperations.appendText(id("targetIncDecDate"),
                    "2013/12/09");
            webDriverOperations.appendText(id("targetIncreaseNum"), "1");
            webDriverOperations.appendText(id("targetDecreaseNum"), "1");
            webDriverOperations.click(id("calcYearDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getStartDateResult")),
                    is("2014/12/09"));
            assertThat(webDriverOperations.getText(id("getEndDateResult")), is(
                    "2012/12/09"));
        }
    }

    /**
     * <ul>
     * <li>閏年にて指定した日数を増減した場合に、指定した値分の日付が取得できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0301004() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0301004"));
        }

        // 閏年を設定したDateTimeから指定した日数を増加した場合に、指定した値分増した日付が取得する。
        {
            webDriverOperations.appendText(id("targetIncDecDate"),
                    "2012/02/28");
            webDriverOperations.appendText(id("targetIncreaseNum"), "1");
            webDriverOperations.appendText(id("targetDecreaseNum"), "1");
            webDriverOperations.click(id("calcDayDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getStartDateResult")),
                    is("2012/02/29"));
        }

        webDriverOperations.back();
        clearDateManipulationText();

        // 閏年を設定したDateTimeから指定した日数を減少した場合に、指定した値分減らした日付が取得する。
        {
            webDriverOperations.appendText(id("targetIncDecDate"),
                    "2012/03/01");
            webDriverOperations.appendText(id("targetIncreaseNum"), "1");
            webDriverOperations.appendText(id("targetDecreaseNum"), "1");
            webDriverOperations.click(id("calcDayDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getEndDateResult")), is(
                    "2012/02/29"));
        }
    }

    /**
     * <ul>
     * <li>閏年にて指定した月数を増減した場合に、指定した値分の日付が取得できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0301005() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0301005"));
        }

        // 閏年を設定したDateTimeから指定した月数を増加した場合に、指定した値分増した日付が取得する。
        {
            webDriverOperations.appendText(id("targetIncDecDate"),
                    "2012/01/31");
            webDriverOperations.appendText(id("targetIncreaseNum"), "1");
            webDriverOperations.appendText(id("targetDecreaseNum"), "1");
            webDriverOperations.click(id("calcMonthDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getStartDateResult")),
                    is("2012/02/29"));
        }

        webDriverOperations.back();
        clearDateManipulationText();

        // 閏年を設定したDateTimeから指定した月数を減少した場合に、指定した値分減らした日付が取得する。
        {
            webDriverOperations.appendText(id("targetIncDecDate"),
                    "2012/03/31");
            webDriverOperations.appendText(id("targetIncreaseNum"), "1");
            webDriverOperations.appendText(id("targetDecreaseNum"), "1");
            webDriverOperations.click(id("calcMonthDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getEndDateResult")), is(
                    "2012/02/29"));
        }
    }

    /**
     * <ul>
     * <li>閏年にて指定した年数を増減した場合に、指定した値分の日付が取得できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0301006() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0301006"));
        }

        // 閏年を設定したDateTimeから指定した年数を増減した場合に、指定した値分増減した日付が取得する。
        {
            webDriverOperations.appendText(id("targetIncDecDate"),
                    "2012/02/29");
            webDriverOperations.appendText(id("targetIncreaseNum"), "1");
            webDriverOperations.appendText(id("targetDecreaseNum"), "1");
            webDriverOperations.click(id("calcYearDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getStartDateResult")),
                    is("2013/02/28"));
            assertThat(webDriverOperations.getText(id("getEndDateResult")), is(
                    "2011/02/28"));
        }
    }

    /**
     * <ul>
     * <li>指定した日時を基準日とした場合に、月末月始の値を取得することができることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0302001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0302001"));
        }

        // 指定した日付から、指定した日時の月を示すPropertyを取得して月末と月始の値を取得する。
        {
            webDriverOperations.appendText(id("targetStartEndDate"),
                    "2013/12/09");
            webDriverOperations.click(id("calcMonthStartEndDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getEndDateResult")), is(
                    "2013/12/31"));
            assertThat(webDriverOperations.getText(id("getStartDateResult")),
                    is("2013/12/01"));
        }
    }

    /**
     * <ul>
     * <li>閏年を基準日とした場合に、月末月始の値を取得することができることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0302002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0302002"));
        }

        // 閏年の日付から、現在日時の月を示すPropertyを取得して月末と月始の値を取得する。
        {
            webDriverOperations.appendText(id("targetStartEndDate"),
                    "2012/02/15");
            webDriverOperations.click(id("calcMonthStartEndDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getEndDateResult")), is(
                    "2012/02/29"));
            assertThat(webDriverOperations.getText(id("getStartDateResult")),
                    is("2012/02/01"));
        }
    }

    /**
     * <ul>
     * <li>指定した日時を基準日とした場合に、週末週始の値を取得することができることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0303001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0303001"));
        }

        // 指定した日付から、指定した日時の週を示すPropertyを取得して週末と週始の値を取得する。
        {
            webDriverOperations.appendText(id("targetStartEndDate"),
                    "2012/02/15");
            webDriverOperations.click(id("calcWeekStartEndDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getEndDateResult")), is(
                    "2012/02/19"));
            assertThat(webDriverOperations.getText(id("getStartDateResult")),
                    is("2012/02/13"));
        }
    }

    /**
     * <ul>
     * <li>閏年を基準日とした場合に、週末週始の値を取得することができることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0303002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0303002"));
        }

        // 閏年の日付から、現在日時の週を示すPropertyを取得して週末が閏年になる値を取得する。
        {
            webDriverOperations.appendText(id("targetStartEndDate"),
                    "2004/02/27");
            webDriverOperations.click(id("calcWeekStartEndDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getEndDateResult")), is(
                    "2004/02/29"));
            assertThat(webDriverOperations.getText(id("getStartDateResult")),
                    is("2004/02/23"));
        }

        webDriverOperations.back();
        clearDateManipulationText();

        // 閏年の日付から、現在日時の週を示すPropertyを取得して週初が閏年になる値を取得する。
        {
            webDriverOperations.appendText(id("targetStartEndDate"),
                    "2016/03/01");
            webDriverOperations.click(id("calcWeekStartEndDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getEndDateResult")), is(
                    "2016/03/06"));
            assertThat(webDriverOperations.getText(id("getStartDateResult")),
                    is("2016/02/29"));
        }
    }

    /**
     * 入力項目（増減）削除処理
     */
    private void clearDateManipulationText() {
        webDriverOperations.clearText(id("targetIncDecDate"));
        webDriverOperations.clearText(id("targetIncreaseNum"));
        webDriverOperations.clearText(id("targetDecreaseNum"));
        webDriverOperations.clearText(id("targetStartEndDate"));
    }

    /**
     * <ul>
     * <li>2つの日付による期間が存在する場合に、特定の日付が期間内に含まれるかチェックできることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0401001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0401001"));
        }

        // 「期間クラス1」、「日付クラス」を取得。(確認1)
        {
            webDriverOperations.appendText(id("targetTermFrom"), "2013/11/01");
            webDriverOperations.appendText(id("targetTermTo"), "2013/11/11");
            webDriverOperations.appendText(id("targetCheckDate"), "2013/11/01");
            webDriverOperations.click(id("checkContainTermToDate"));
        }

        // 「期間クラス1」の期間に、「日付クラス」の期間が含まれているかチェックを行う。
        {
            assertThat(webDriverOperations.getText(id("getResult")), is(
                    "true"));
        }

        webDriverOperations.back();
        // 入力項目消し込み
        clearCheckTermText();

        // 「期間クラス1」、「日付クラス」を取得。(確認2)
        {
            webDriverOperations.appendText(id("targetTermFrom"), "2013/11/01");
            webDriverOperations.appendText(id("targetTermTo"), "2013/11/11");
            webDriverOperations.appendText(id("targetCheckDate"), "2013/11/11");
            webDriverOperations.click(id("checkContainTermToDate"));
        }

        // 「期間クラス1」の期間に、「日付クラス」の期間が含まれているかチェックを行う。
        {
            assertThat(webDriverOperations.getText(id("getResult")), is(
                    "false"));
        }

        webDriverOperations.back();
        // 入力項目消し込み
        clearCheckTermText();

        // 「期間クラス1」、「期間クラス2」を取得。(確認3)
        {
            webDriverOperations.appendText(id("targetTermFrom"), "2013/11/01");
            webDriverOperations.appendText(id("targetTermTo"), "2013/11/11");
            webDriverOperations.appendText(id("targetCheckTermFrom"),
                    "2013/11/01");
            webDriverOperations.appendText(id("targetCheckTermTo"),
                    "2013/11/02");
            webDriverOperations.click(id("checkContainTermToTerm"));
        }

        // 「期間クラス1」の期間に、「期間クラス2」の期間が含まれているかチェックを行う。
        {
            assertThat(webDriverOperations.getText(id("getResult")), is(
                    "true"));
        }

        webDriverOperations.back();
        // 入力項目消し込み
        clearCheckTermText();

        // 「期間クラス1」、「期間クラス3」を取得。(確認4)
        {
            webDriverOperations.appendText(id("targetTermFrom"), "2013/11/01");
            webDriverOperations.appendText(id("targetTermTo"), "2013/11/11");
            webDriverOperations.appendText(id("targetCheckTermFrom"),
                    "2013/11/12");
            webDriverOperations.appendText(id("targetCheckTermTo"),
                    "2013/11/13");
            webDriverOperations.click(id("checkContainTermToTerm"));
        }

        // 「期間クラス1」の期間に、「期間クラス3」の期間が含まれているかチェックを行う。
        {
            assertThat(webDriverOperations.getText(id("getResult")), is(
                    "false"));
        }

    }

    /**
     * <ul>
     * <li>2つの期間が存在する場合に、期間同士が連続しているかチェックできることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0401002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0401002"));
        }

        // 「期間クラス1」、「期間クラス2」を取得。(確認1)
        {
            webDriverOperations.appendText(id("targetTermFrom"), "2013/11/01");
            webDriverOperations.appendText(id("targetTermTo"), "2013/11/11");
            webDriverOperations.appendText(id("targetCheckTermFrom"),
                    "2013/11/11");
            webDriverOperations.appendText(id("targetCheckTermTo"),
                    "2013/11/13");
            webDriverOperations.click(id("checkAbutsTerm"));
        }

        // 「期間クラス1」の期間に、「期間クラス2」の期間が連続しているかチェックを行う。
        {
            assertThat(webDriverOperations.getText(id("getResult")), is(
                    "true"));
        }

        webDriverOperations.back();
        // 入力項目消し込み
        clearCheckTermText();

        // 「期間クラス1」、「期間クラス3」を取得。(確認2)
        {
            webDriverOperations.appendText(id("targetTermFrom"), "2013/11/01");
            webDriverOperations.appendText(id("targetTermTo"), "2013/11/11");
            webDriverOperations.appendText(id("targetCheckTermFrom"),
                    "2013/11/12");
            webDriverOperations.appendText(id("targetCheckTermTo"),
                    "2013/11/13");
            webDriverOperations.click(id("checkAbutsTerm"));
        }

        // 「期間クラス1」の期間に、「期間クラス3」の期間が含まれているかチェックを行う。
        {
            assertThat(webDriverOperations.getText(id("getResult")), is(
                    "false"));
        }

        webDriverOperations.back();
        // 入力項目消し込み
        clearCheckTermText();

        // 「期間クラス2」、「期間クラス3」を取得。(確認3)
        {
            webDriverOperations.appendText(id("targetTermFrom"), "2013/11/11");
            webDriverOperations.appendText(id("targetTermTo"), "2013/11/13");
            webDriverOperations.appendText(id("targetCheckTermFrom"),
                    "2013/11/12");
            webDriverOperations.appendText(id("targetCheckTermTo"),
                    "2013/11/13");
            webDriverOperations.click(id("checkAbutsTerm"));
        }

        // 「期間クラス2」の期間に、「期間クラス3」の期間が含まれているかチェックを行う。
        {
            assertThat(webDriverOperations.getText(id("getResult")), is(
                    "false"));
        }
    }

    /**
     * <ul>
     * <li>2つの期間が存在する場合に、期間の差が取得できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0401003() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0401003"));
        }

        // 「期間クラス1」、「期間クラス2」を取得。(確認1)
        {
            webDriverOperations.appendText(id("targetTermFrom"), "2013/11/01");
            webDriverOperations.appendText(id("targetTermTo"), "2013/11/11");
            webDriverOperations.appendText(id("targetCheckTermFrom"),
                    "2013/11/12");
            webDriverOperations.appendText(id("targetCheckTermTo"),
                    "2013/11/13");
            webDriverOperations.click(id("getGapTerm"));
        }

        // 「期間クラス1」の期間と「期間クラス2」の期間との差の期間を取得出来ることを確認。
        {
            assertThat(webDriverOperations.getText(id("getStartDateResult")),
                    is("2013/11/11"));
            assertThat(webDriverOperations.getText(id("getEndDateResult")), is(
                    "2013/11/12"));
        }

        webDriverOperations.back();
        // 入力項目消し込み
        clearCheckTermText();

        // 「期間クラス1」、「期間クラス3」を取得。(確認2)
        {
            webDriverOperations.appendText(id("targetTermFrom"), "2013/11/01");
            webDriverOperations.appendText(id("targetTermTo"), "2013/11/11");
            webDriverOperations.appendText(id("targetCheckTermFrom"),
                    "2013/11/11");
            webDriverOperations.appendText(id("targetCheckTermTo"),
                    "2013/11/13");
            webDriverOperations.click(id("getGapTerm"));
        }

        // 「期間クラス1」の期間と「期間クラス3」の期間との差が0の為、nullが返却されることを確認。
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is(
                    "なし"));
        }

        webDriverOperations.back();
        // 入力項目消し込み
        clearCheckTermText();

        // 「期間クラス2」、「期間クラス3」を取得。(確認3)
        {
            webDriverOperations.appendText(id("targetTermFrom"), "2013/11/12");
            webDriverOperations.appendText(id("targetTermTo"), "2013/11/13");
            webDriverOperations.appendText(id("targetCheckTermFrom"),
                    "2013/11/11");
            webDriverOperations.appendText(id("targetCheckTermTo"),
                    "2013/11/13");
            webDriverOperations.click(id("getGapTerm"));
        }

        // 「期間クラス2」の期間と「期間クラス3」の期間との差がマイナス(重複)の為、nullが返却されることを確認。
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is(
                    "なし"));
        }
    }

    /**
     * <ul>
     * <li>2つの期間が存在する場合に、重なった期間が取得できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0401004() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0401004"));
        }

        // 「期間クラス1」、「期間クラス2」を取得。(確認1)
        {
            webDriverOperations.appendText(id("targetTermFrom"), "2013/11/01");
            webDriverOperations.appendText(id("targetTermTo"), "2013/11/11");
            webDriverOperations.appendText(id("targetCheckTermFrom"),
                    "2013/11/11");
            webDriverOperations.appendText(id("targetCheckTermTo"),
                    "2013/11/13");
            webDriverOperations.click(id("getOverlapTerm"));
        }

        // 「期間クラス1」の期間と「期間クラス2」の期間が境界で重複しない為、nullが返却されることを確認。
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is(
                    "なし"));
        }

        webDriverOperations.back();
        // 入力項目消し込み
        clearCheckTermText();

        // 「期間クラス1」、「期間クラス3」を取得。(確認2)
        {
            webDriverOperations.appendText(id("targetTermFrom"), "2013/11/01");
            webDriverOperations.appendText(id("targetTermTo"), "2013/11/11");
            webDriverOperations.appendText(id("targetCheckTermFrom"),
                    "2013/11/12");
            webDriverOperations.appendText(id("targetCheckTermTo"),
                    "2013/11/13");
            webDriverOperations.click(id("getOverlapTerm"));
        }

        // 「期間クラス1」の期間と「期間クラス3」の期間が重複しない為、nullが返却されることを確認。
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is(
                    "なし"));
        }

        webDriverOperations.back();
        // 入力項目消し込み
        clearCheckTermText();

        // 「期間クラス2」、「期間クラス3」を取得。(確認3)
        {
            webDriverOperations.appendText(id("targetTermFrom"), "2013/11/11");
            webDriverOperations.appendText(id("targetTermTo"), "2013/11/13");
            webDriverOperations.appendText(id("targetCheckTermFrom"),
                    "2013/11/12");
            webDriverOperations.appendText(id("targetCheckTermTo"),
                    "2013/11/13");
            webDriverOperations.click(id("getOverlapTerm"));
        }

        // 「期間クラス2」の期間と「期間クラス3」の期間との重複した期間を取得出来ることを確認。
        {
            assertThat(webDriverOperations.getText(id("getStartDateResult")),
                    is("2013/11/12"));
            assertThat(webDriverOperations.getText(id("getEndDateResult")), is(
                    "2013/11/13"));
        }
    }

    /**
     * <ul>
     * <li>閏年と他の日付が存在する場合に、特定の日付が期間内に含まれるかチェックできることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0401005() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0401005"));
        }

        // 「期間クラス1」、「日付クラス」を取得。(確認1)
        {
            webDriverOperations.appendText(id("targetTermFrom"), "2012/02/27");
            webDriverOperations.appendText(id("targetTermTo"), "2012/02/29");
            webDriverOperations.appendText(id("targetCheckDate"), "2012/02/27");
            webDriverOperations.click(id("checkContainTermToDate"));
        }

        // 「期間クラス1」の期間に、「日付クラス」の期間が含まれているかチェックを行う。
        {
            assertThat(webDriverOperations.getText(id("getResult")), is(
                    "true"));
        }

        webDriverOperations.back();
        // 入力項目消し込み
        clearCheckTermText();

        // 「期間クラス1」、「日付クラス」を取得。(確認2)
        {
            webDriverOperations.appendText(id("targetTermFrom"), "2012/02/27");
            webDriverOperations.appendText(id("targetTermTo"), "2012/02/29");
            webDriverOperations.appendText(id("targetCheckDate"), "2012/02/29");
            webDriverOperations.click(id("checkContainTermToDate"));
        }

        // 「期間クラス1」の期間に、「日付クラス」の期間が含まれているかチェックを行う。
        {
            assertThat(webDriverOperations.getText(id("getResult")), is(
                    "false"));
        }

        webDriverOperations.back();
        // 入力項目消し込み
        clearCheckTermText();

        // 「期間クラス1」、「期間クラス2」を取得。(確認3)
        {
            webDriverOperations.appendText(id("targetTermFrom"), "2012/02/27");
            webDriverOperations.appendText(id("targetTermTo"), "2012/02/29");
            webDriverOperations.appendText(id("targetCheckTermFrom"),
                    "2012/02/28");
            webDriverOperations.appendText(id("targetCheckTermTo"),
                    "2012/02/29");
            webDriverOperations.click(id("checkContainTermToTerm"));
        }

        // 「期間クラス1」の期間に、「期間クラス2」の期間が含まれているかチェックを行う。
        {
            assertThat(webDriverOperations.getText(id("getResult")), is(
                    "true"));
        }

        webDriverOperations.back();
        // 入力項目消し込み
        clearCheckTermText();

        // 「期間クラス1」、「期間クラス3」を取得。(確認4)
        {
            webDriverOperations.appendText(id("targetTermFrom"), "2012/02/27");
            webDriverOperations.appendText(id("targetTermTo"), "2012/02/29");
            webDriverOperations.appendText(id("targetCheckTermFrom"),
                    "2012/02/29");
            webDriverOperations.appendText(id("targetCheckTermTo"),
                    "2012/03/01");
            webDriverOperations.click(id("checkContainTermToTerm"));
        }

        // 「期間クラス1」の期間に、「期間クラス3」の期間が含まれているかチェックを行う。
        {
            assertThat(webDriverOperations.getText(id("getResult")), is(
                    "false"));
        }

    }

    /**
     * <ul>
     * <li>閏年と他の日付の期間が存在する場合に、期間同士が連続しているかチェックできることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0401006() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0401006"));
        }

        // 「期間クラス1」、「期間クラス2」を取得。(確認1)
        {
            webDriverOperations.appendText(id("targetTermFrom"), "2012/02/28");
            webDriverOperations.appendText(id("targetTermTo"), "2012/02/29");
            webDriverOperations.appendText(id("targetCheckTermFrom"),
                    "2012/02/29");
            webDriverOperations.appendText(id("targetCheckTermTo"),
                    "2012/03/02");
            webDriverOperations.click(id("checkAbutsTerm"));
        }

        // 「期間クラス1」の期間に、「期間クラス2」の期間が連続しているかチェックを行う。
        {
            assertThat(webDriverOperations.getText(id("getResult")), is(
                    "true"));
        }

        webDriverOperations.back();
        // 入力項目消し込み
        clearCheckTermText();

        // 「期間クラス1」、「期間クラス3」を取得。(確認2)
        {
            webDriverOperations.appendText(id("targetTermFrom"), "2012/02/28");
            webDriverOperations.appendText(id("targetTermTo"), "2012/02/29");
            webDriverOperations.appendText(id("targetCheckTermFrom"),
                    "2012/03/01");
            webDriverOperations.appendText(id("targetCheckTermTo"),
                    "2012/03/03");
            webDriverOperations.click(id("checkAbutsTerm"));
        }

        // 「期間クラス1」の期間に、「期間クラス3」の期間が含まれているかチェックを行う。
        {
            assertThat(webDriverOperations.getText(id("getResult")), is(
                    "false"));
        }

        webDriverOperations.back();
        // 入力項目消し込み
        clearCheckTermText();

        // 「期間クラス2」、「期間クラス3」を取得。(確認3)
        {
            webDriverOperations.appendText(id("targetTermFrom"), "2012/02/29");
            webDriverOperations.appendText(id("targetTermTo"), "2012/03/02");
            webDriverOperations.appendText(id("targetCheckTermFrom"),
                    "2012/03/01");
            webDriverOperations.appendText(id("targetCheckTermTo"),
                    "2012/03/03");
            webDriverOperations.click(id("checkAbutsTerm"));
        }

        // 「期間クラス2」の期間に、「期間クラス3」の期間が含まれているかチェックを行う。
        {
            assertThat(webDriverOperations.getText(id("getResult")), is(
                    "false"));
        }
    }

    /**
     * <ul>
     * <li>閏年と他の日付の期間が存在する場合に、期間の差が取得できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0401007() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0401007"));
        }

        // 「期間クラス1」、「期間クラス2」を取得。(確認1)
        {
            webDriverOperations.appendText(id("targetTermFrom"), "2012/02/28");
            webDriverOperations.appendText(id("targetTermTo"), "2012/02/29");
            webDriverOperations.appendText(id("targetCheckTermFrom"),
                    "2012/03/01");
            webDriverOperations.appendText(id("targetCheckTermTo"),
                    "2012/03/03");
            webDriverOperations.click(id("getGapTerm"));
        }

        // 「期間クラス1」の期間と「期間クラス2」の期間との差の期間を取得出来ることを確認。
        {
            assertThat(webDriverOperations.getText(id("getStartDateResult")),
                    is("2012/02/29"));
            assertThat(webDriverOperations.getText(id("getEndDateResult")), is(
                    "2012/03/01"));
        }

        webDriverOperations.back();
        // 入力項目消し込み
        clearCheckTermText();

        // 「期間クラス1」、「期間クラス3」を取得。(確認2)
        {
            webDriverOperations.appendText(id("targetTermFrom"), "2012/02/28");
            webDriverOperations.appendText(id("targetTermTo"), "2012/02/29");
            webDriverOperations.appendText(id("targetCheckTermFrom"),
                    "2012/02/29");
            webDriverOperations.appendText(id("targetCheckTermTo"),
                    "2012/03/02");
            webDriverOperations.click(id("getGapTerm"));
        }

        // 「期間クラス1」の期間と「期間クラス3」の期間との差が0の為、nullが返却されることを確認。
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is(
                    "なし"));
        }

        webDriverOperations.back();
        // 入力項目消し込み
        clearCheckTermText();

        // 「期間クラス2」、「期間クラス3」を取得。(確認3)
        {
            webDriverOperations.appendText(id("targetTermFrom"), "2012/03/01");
            webDriverOperations.appendText(id("targetTermTo"), "2012/03/03");
            webDriverOperations.appendText(id("targetCheckTermFrom"),
                    "2012/02/29");
            webDriverOperations.appendText(id("targetCheckTermTo"),
                    "2012/03/02");
            webDriverOperations.click(id("getGapTerm"));
        }

        // 「期間クラス2」の期間と「期間クラス3」の期間との差がマイナス(重複)の為、nullが返却されることを確認。
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is(
                    "なし"));
        }
    }

    /**
     * <ul>
     * <li>閏年と他の日付の期間が存在する場合に、重なった期間が取得できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0401008() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0401008"));
        }

        // 「期間クラス1」、「期間クラス2」を取得。(確認1)
        {
            webDriverOperations.appendText(id("targetTermFrom"), "2012/02/27");
            webDriverOperations.appendText(id("targetTermTo"), "2012/02/29");
            webDriverOperations.appendText(id("targetCheckTermFrom"),
                    "2012/02/28");
            webDriverOperations.appendText(id("targetCheckTermTo"),
                    "2012/03/01");
            webDriverOperations.click(id("getOverlapTerm"));
        }

        // 「期間クラス1」の期間と「期間クラス2」の期間との重複した期間を取得出来ることを確認。
        {
            assertThat(webDriverOperations.getText(id("getStartDateResult")),
                    is("2012/02/28"));
            assertThat(webDriverOperations.getText(id("getEndDateResult")), is(
                    "2012/02/29"));
        }

        webDriverOperations.back();
        // 入力項目消し込み
        clearCheckTermText();

        // 「期間クラス1」、「期間クラス3」を取得。(確認2)
        {
            webDriverOperations.appendText(id("targetTermFrom"), "2012/02/27");
            webDriverOperations.appendText(id("targetTermTo"), "2012/02/29");
            webDriverOperations.appendText(id("targetCheckTermFrom"),
                    "2012/03/01");
            webDriverOperations.appendText(id("targetCheckTermTo"),
                    "2012/03/03");
            webDriverOperations.click(id("getOverlapTerm"));
        }

        // 「期間クラス1」の期間と「期間クラス3」の期間が重複しない為、nullが返却されることを確認。
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is(
                    "なし"));
        }

        webDriverOperations.back();
        // 入力項目消し込み
        clearCheckTermText();

        // 「期間クラス2」、「期間クラス3」を取得。(確認3)
        {
            webDriverOperations.appendText(id("targetTermFrom"), "2012/02/28");
            webDriverOperations.appendText(id("targetTermTo"), "2012/03/01");
            webDriverOperations.appendText(id("targetCheckTermFrom"),
                    "2012/03/01");
            webDriverOperations.appendText(id("targetCheckTermTo"),
                    "2012/03/03");
            webDriverOperations.click(id("getOverlapTerm"));
        }

        // 「期間クラス2」の期間と「期間クラス3」の期間が境界で重複しない為、nullが返却されることを確認。
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is(
                    "なし"));
        }
    }

    /**
     * 入力項目（期間）削除処理
     */
    private void clearCheckTermText() {
        webDriverOperations.clearText(id("targetTermFrom"));
        webDriverOperations.clearText(id("targetTermTo"));
        webDriverOperations.clearText(id("targetCheckTermFrom"));
        webDriverOperations.clearText(id("targetCheckTermTo"));
        webDriverOperations.clearText(id("targetCheckDate"));
    }

    /**
     * <ul>
     * <li>Periodクラスを使用して単一項目(Single field Period)を指定した日数を増減した場合に、 指定した値分の日付が取得できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0402001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0402001"));
        }

        // 指定した日時を設定したPeriodクラスから指定した単一期間（月のみ）を増減した場合に、指定した値分増減した日付が取得する。
        {
            webDriverOperations.appendText(id("targetIncDecDate"),
                    "2013/03/01");
            webDriverOperations.appendText(id("targetIncreaseMonthNum"), "1");
            webDriverOperations.appendText(id("targetDecreaseMonthNum"), "1");
            webDriverOperations.click(id("calcMonthDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getStartDateResult")),
                    is("2013/04/01"));
            assertThat(webDriverOperations.getText(id("getEndDateResult")), is(
                    "2013/02/01"));
        }

        webDriverOperations.back();
        clearCheckTermPeriodText();

        // 指定した日時を設定したPeriodクラスから指定した単一期間（月のみ）を増減した場合に、指定した値分増減した日付が取得する。
        {
            webDriverOperations.appendText(id("targetIncDecDate"),
                    "2013/04/01");
            webDriverOperations.appendText(id("targetIncreaseMonthNum"), "1");
            webDriverOperations.appendText(id("targetDecreaseMonthNum"), "1");
            webDriverOperations.click(id("calcMonthDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getStartDateResult")),
                    is("2013/05/01"));
            assertThat(webDriverOperations.getText(id("getEndDateResult")), is(
                    "2013/03/01"));
        }
    }

    /**
     * <ul>
     * <li>Periodクラスを使用して複数項目(Any field Period)を指定した日数を増減した場合に、指定した値分の日付が取得できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0402002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0402002"));
        }

        // 指定した日時を設定したPeriodクラスから指定した単一期間（月、日）を増減した場合に、指定した値分増減した日付が取得する。
        {
            webDriverOperations.appendText(id("targetIncDecDate"),
                    "2013/03/01");
            webDriverOperations.appendText(id("targetIncreaseMonthNum"), "1");
            webDriverOperations.appendText(id("targetDecreaseMonthNum"), "1");
            webDriverOperations.appendText(id("targetIncreaseDayNum"), "10");
            webDriverOperations.appendText(id("targetDecreaseDayNum"), "10");
            webDriverOperations.click(id("calcMonthAndDayDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getStartDateResult")),
                    is("2013/04/11"));
            assertThat(webDriverOperations.getText(id("getEndDateResult")), is(
                    "2013/01/22"));
        }

        webDriverOperations.back();
        clearCheckTermPeriodText();

        // 指定した日時を設定したPeriodクラスから指定した単一期間（月、日）を増減した場合に、指定した値分増減した日付が取得する。
        {
            webDriverOperations.appendText(id("targetIncDecDate"),
                    "2013/04/01");
            webDriverOperations.appendText(id("targetIncreaseMonthNum"), "1");
            webDriverOperations.appendText(id("targetDecreaseMonthNum"), "1");
            webDriverOperations.appendText(id("targetIncreaseDayNum"), "10");
            webDriverOperations.appendText(id("targetDecreaseDayNum"), "10");
            webDriverOperations.click(id("calcMonthAndDayDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getStartDateResult")),
                    is("2013/05/11"));
            assertThat(webDriverOperations.getText(id("getEndDateResult")), is(
                    "2013/02/19"));
        }
    }

    /**
     * 入力項目（Periodクラス確認）削除処理
     */
    private void clearCheckTermPeriodText() {
        webDriverOperations.clearText(id("targetIncDecDate"));
        webDriverOperations.clearText(id("targetIncreaseMonthNum"));
        webDriverOperations.clearText(id("targetDecreaseMonthNum"));
        webDriverOperations.clearText(id("targetIncreaseDayNum"));
        webDriverOperations.clearText(id("targetDecreaseDayNum"));
    }

    /**
     * <ul>
     * <li>JSPにJodaのformatタグを使用した場合、DateTimeオブジェクトをフォーマットして表示することを確認する。</li>
     * </ul>
     */
    @Ignore("JODA Tag Library は JakartaEE10 に対応していない")
    @Test
    public void testDTOP0501001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0501001"));
        }

        // 生成したDateTimeオブジェクトについて、JSPにJodaタグを利用してフォーマット表示する。
        {
            webDriverOperations.click(id("jspTagDateTime"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is(
                    "2013/12/09 13:50:12.100 +09:00"));
            assertThat(webDriverOperations.getText(id("getResultStyle")), is(
                    "13/12/09 13:50:12"));
        }
    }

    /**
     * <ul>
     * <li>JSPにJodaのformatタグを使用した場合、LocalDateTimeオブジェクトをフォーマットして表示することを確認する。</li>
     * </ul>
     */
    @Ignore("JODA Tag Library は JakartaEE10 に対応していない")
    @Test
    public void testDTOP0501002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0501002"));
        }

        // 生成したLocalDateTimeオブジェクトについて、JSPにJodaタグを利用してフォーマット表示すること。
        {
            webDriverOperations.click(id("jspTagLocalDateTime"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is(
                    "2013/12/09 13:50:12.100"));
            assertThat(webDriverOperations.getText(id("getResultStyle")), is(
                    "13/12/09 13:50:12"));
        }
    }

    /**
     * <ul>
     * <li>JSPにJodaのformatタグを使用した場合、LocalDateオブジェクトをフォーマットして表示することを確認する。</li>
     * </ul>
     */
    @Ignore("JODA Tag Library は JakartaEE10 に対応していない")
    @Test
    public void testDTOP0501003() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0501003"));
        }

        // 生成したLocalDateオブジェクトについて、JSPにJodaタグを利用してフォーマット表示すること。
        {
            webDriverOperations.click(id("jspTagLocalDate"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is(
                    "2013/12/09"));
            assertThat(webDriverOperations.getText(id("getResultStyle")), is(
                    "13/12/09"));
        }
    }

    /**
     * <ul>
     * <li>JSPにJodaのformatタグを使用した場合、LocalTimeオブジェクトをフォーマットして表示することを確認する。</li>
     * </ul>
     */
    @Ignore("JODA Tag Library は JakartaEE10 に対応していない")
    @Test
    public void testDTOP0501004() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0501004"));
        }

        // 生成したLocalTimeオブジェクトについて、JSPにJodaタグを利用してフォーマット表示すること。
        {
            webDriverOperations.click(id("jspTagLocalTime"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is(
                    "13:50:12"));
            assertThat(webDriverOperations.getText(id("getResultStyle")), is(
                    "13:50:12"));
        }
    }

    /**
     * <ul>
     * <li>Calendarクラスで和暦を扱い、書式"Gyy.MM.dd"で出力できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0601001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0601001"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is(
                    "H25.12.09"));
        }
    }

    /**
     * <ul>
     * <li>Calendarクラスで和暦を扱い、書式"GGGGyy/MM/dd"で出力できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0601002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0601002"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is(
                    "平成25/12/09"));
        }
    }

    /**
     * <ul>
     * <li>書式"Gyy.MM.dd"の和暦文字列から日時情報をCalendarクラスに設定し、表示できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0601003() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0601003"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is(
                    "H25.12.09"));
        }
    }

    /**
     * <ul>
     * <li>書式"GGGGyy/MM/dd"の和暦文字列から日時情報をCalendarクラスに設定し、表示できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testDTOP0601004() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("dtop0601004"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("getDateResult")), is(
                    "平成25/12/09"));
        }
    }

}
