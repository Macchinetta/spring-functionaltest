/*
 * Copyright 2014-2017 NTT Corporation.
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
 *
 */
package jp.co.ntt.fw.spring.functionaltest.selenium.dmly;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.openqa.selenium.By.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.annotation.IfProfileValue;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

//Thymeleaf版未実装のためJSPのみ実行
@IfProfileValue(name = "test.environment.view", values = { "jsp" })
public class DomainLayerTest extends FunctionTestSupport {

    @Value("${selenium.dmly.retryForSetSelectString.max:3}")
    private int maxRetryForSetSelectString;

    @Before
    public void setUp() {
        // トップ画面での操作
        {
            webDriverOperations.getWebDriver().manage().window().maximize();
        }
    }

    private void clickLink(By by) {
        webDriverOperations.click(by);
    }

    private void clickButton(By by) {
        webDriverOperations.click(by);
    }

    private String getInputString(By by) {
        return webDriverOperations.getInputFieldValue(by);
    }

    private void setInputString(By by, String value) {
        webDriverOperations.overrideText(by, value);
    }

    private void setSelectString(By by, String value) {
        webDriverOperations.select(by, value);
    }

    private void setSelectStringWithCheck(By by, String visible, String value) {
        for (int count = 0; count < maxRetryForSetSelectString; count++) {
            setSelectString(by, visible);
            if (getInputString(by).equals(value)) {
                break;
            }
        }
    }

    private List<String> getValueList(By by) {
        List<WebElement> elementList = webDriverOperations.getWebDriver()
                .findElements(by);
        List<String> valueList = new ArrayList<String>();
        for (WebElement element : elementList) {
            valueList.add(element.getText());
        }
        return valueList;
    }

    private String getTextString(By by) {
        return webDriverOperations.getText(by);
    }

    private void confirmDeliveryOrderEmpty(String no) {
        setInputString(id("displayDeliveryNo"), no);
        clickButton(id("display"));

        assertThat(getInputString(id("deliveryNo")), is(""));
        assertThat(getInputString(id("deliveryTypeName")), is(""));
        assertThat(getInputString(id("senderName")), is(""));
        assertThat(getInputString(id("senderAddress")), is(""));
        assertThat(getInputString(id("recieverName")), is(""));
        assertThat(getInputString(id("recieverAddress")), is(""));
        assertThat(getInputString(id("acceptDatetime")), is(""));
        assertThat(getInputString(id("completionDatetime")), is(""));
        assertThat(getInputString(id("deliveryDriver")), is(""));
        assertThat(getInputString(id("deliveryStatus")), is(""));

        clickButton(id("back"));
    }

    private void confirmDeliveryOrder(String no) {
        setInputString(id("displayDeliveryNo"), no);
        clickButton(id("display"));

        assertThat(getInputString(id("deliveryNo")), is(no));
        assertThat(getInputString(id("deliveryTypeName")), is("通常"));
        assertThat(getInputString(id("senderName")), is("送り主名" + no));
        assertThat(getInputString(id("senderAddress")), is("送り主住所" + no));
        assertThat(getInputString(id("recieverName")), is("送り先名" + no));
        assertThat(getInputString(id("recieverAddress")), is("送り先住所" + no));
        assertThat(getInputString(id("acceptDatetime")), is(String.format(
                "%s/%s/%s %s:%s:%s", "2014", "01", no, no, no, no)));
        assertThat(getInputString(id("completionDatetime")), is(""));
        assertThat(getInputString(id("deliveryDriver")), is("ドライバー" + no));
        assertThat(getInputString(id("deliveryStatus")), is("受付"));

        clickButton(id("back"));
    }

    /**
     * <ul>
     * <li>１テーブルに閉じた業務データをEntityに設定できること。</li>
     * </ul>
     */
    @Test
    public void testDMLY0101001() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0101001"));
        }

        // 登録画面表示
        {
            clickButton(id("form"));

            setSelectStringWithCheck(id("deliveryTypeName"), "", "");
            assertThat(getInputString(id("deliveryTypeName")), is(""));
            setSelectStringWithCheck(id("deliveryTypeName"), "通常", "通常");
            assertThat(getInputString(id("deliveryTypeName")), is("通常"));
            setSelectStringWithCheck(id("deliveryTypeName"), "速配", "速配");
            assertThat(getInputString(id("deliveryTypeName")), is("速配"));
            setSelectStringWithCheck(id("deliveryTypeName"), "クール", "クール");
            assertThat(getInputString(id("deliveryTypeName")), is("クール"));
            setSelectStringWithCheck(id("deliveryTypeName"), "メール", "メール");
            assertThat(getInputString(id("deliveryTypeName")), is("メール"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

    }

    /**
     * <ul>
     * <li>他テーブルの関連データを持つ業務データをEntityに設定できること。</li>
     * </ul>
     */
    @Test
    public void testDMLY0101002() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0101002"));
        }

        // 登録画面表示
        {
            List<String> deliveryTypeNameList = getValueList(id(
                    "deliveryType"));

            assertThat(deliveryTypeNameList.size(), is(5));
            assertThat(deliveryTypeNameList.get(0), is("通常"));
            assertThat(deliveryTypeNameList.get(1), is("通常"));
            assertThat(deliveryTypeNameList.get(2), is("通常"));
            assertThat(deliveryTypeNameList.get(3), is("通常"));
            assertThat(deliveryTypeNameList.get(4), is("通常"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

    }

    /**
     * <ul>
     * <li>コード系テーブルのデータをEntityで設定できること。</li>
     * </ul>
     */
    @Test
    public void testDMLY0101003() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0101003"));
        }

        // 登録画面表示
        {
            clickButton(id("form"));

            setSelectStringWithCheck(id("deliveryStatus"), "", "");
            assertThat(getInputString(id("deliveryStatus")), is(""));
            setSelectStringWithCheck(id("deliveryStatus"), "受付", "受付");
            assertThat(getInputString(id("deliveryStatus")), is("受付"));
            setSelectStringWithCheck(id("deliveryStatus"), "配達中", "配達中");
            assertThat(getInputString(id("deliveryStatus")), is("配達中"));
            setSelectStringWithCheck(id("deliveryStatus"), "完了", "完了");
            assertThat(getInputString(id("deliveryStatus")), is("完了"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

    }

    /**
     * <ul>
     * <li>業務データの１件取得ができること。</li>
     * </ul>
     */
    @Test
    public void testDMLY0201001() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0201001"));
        }

        // 登録画面表示
        {
            clickLink(id("deliveryNo_1"));

            assertThat(getInputString(id("deliveryNo")), is("1"));
            assertThat(getInputString(id("deliveryTypeName")), is("通常"));
            assertThat(getInputString(id("senderName")), is("送り主名1"));
            assertThat(getInputString(id("senderAddress")), is("送り主住所1"));
            assertThat(getInputString(id("recieverName")), is("送り先名1"));
            assertThat(getInputString(id("recieverAddress")), is("送り先住所1"));
            assertThat(getInputString(id("acceptDatetime")), is(
                    "2014/01/01 01:01:01"));
            assertThat(getInputString(id("completionDatetime")), is(""));
            assertThat(getInputString(id("deliveryDriver")), is("ドライバー1"));
            assertThat(getInputString(id("deliveryStatus")), is("受付"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

    }

    /**
     * <ul>
     * <li>業務データの存在確認ができること。</li>
     * </ul>
     */
    @Ignore("testDMLY0201001と同時消化")
    public void testDMLY0201002() {
        testDMLY0201001();
    }

    /**
     * <ul>
     * <li>業務データの全件取得ができること。</li>
     * </ul>
     */
    @Ignore("testDMLY0101001と同時消化")
    public void testDMLY0201003() {
        testDMLY0101001();
    }

    /**
     * <ul>
     * <li>業務データのページ指定（範囲指定）による全件取得ができること。</li>
     * </ul>
     */
    @Ignore("testDMLY0101002と同時消化")
    public void testDMLY0201004() {
        testDMLY0101002();
    }

    /**
     * <ul>
     * <li>業務データの件数取得ができること。</li>
     * </ul>
     */
    @Ignore("testDMLY0101002と同時消化")
    public void testDMLY0201005() {
        testDMLY0101002();
    }

    /**
     * <ul>
     * <li>業務データの追加ができること。</li>
     * </ul>
     */
    @Test
    public void testDMLY0201006() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0201006"));
        }

        // 登録画面表示
        {
            clickButton(id("form"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // オーダー登録
        {
            setInputString(id("deliveryNo"), "10");
            setSelectString(id("deliveryTypeName"), "通常");
            setInputString(id("senderName"), "送り主名10");
            setInputString(id("senderAddress"), "送り主住所10");
            setInputString(id("recieverName"), "送り先名10");
            setInputString(id("recieverAddress"), "送り先住所10");
            setInputString(id("acceptDatetime"), "2014/01/10 10:10:10");
            setInputString(id("completionDatetime"), "");
            setInputString(id("deliveryDriver"), "ドライバー10");
            setSelectString(id("deliveryStatus"), "受付");
            clickButton(id("register"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 確認
        {
            assertThat(getInputString(id("deliveryNo")), is("10"));
            assertThat(getInputString(id("deliveryTypeName")), is("通常"));
            assertThat(getInputString(id("senderName")), is("送り主名10"));
            assertThat(getInputString(id("senderAddress")), is("送り主住所10"));
            assertThat(getInputString(id("recieverName")), is("送り先名10"));
            assertThat(getInputString(id("recieverAddress")), is("送り先住所10"));
            assertThat(getInputString(id("acceptDatetime")), is(
                    "2014/01/10 10:10:10"));
            assertThat(getInputString(id("completionDatetime")), is(""));
            assertThat(getInputString(id("deliveryDriver")), is("ドライバー10"));
            assertThat(getInputString(id("deliveryStatus")), is("受付"));
        }

    }

    /**
     * <ul>
     * <li>業務データの更新ができること。</li>
     * </ul>
     */
    @Test
    public void testDMLY0201007() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0201007"));
        }

        // 事前確認
        {
            clickLink(id("deliveryNo_1"));

            assertThat(getInputString(id("deliveryNo")), is("1"));
            assertThat(getInputString(id("deliveryTypeName")), is("通常"));
            assertThat(getInputString(id("senderName")), is("送り主名1"));
            assertThat(getInputString(id("senderAddress")), is("送り主住所1"));
            assertThat(getInputString(id("recieverName")), is("送り先名1"));
            assertThat(getInputString(id("recieverAddress")), is("送り先住所1"));
            assertThat(getInputString(id("acceptDatetime")), is(
                    "2014/01/01 01:01:01"));
            assertThat(getInputString(id("completionDatetime")), is(""));
            assertThat(getInputString(id("deliveryDriver")), is("ドライバー1"));
            assertThat(getInputString(id("deliveryStatus")), is("受付"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 更新
        {
            setSelectString(id("deliveryTypeName"), "通常");
            setInputString(id("senderName"), "送り主名11");
            setInputString(id("senderAddress"), "送り主住所11");
            setInputString(id("recieverName"), "送り先名11");
            setInputString(id("recieverAddress"), "送り先住所11");
            setInputString(id("acceptDatetime"), "2014/01/11 11:11:11");
            setInputString(id("completionDatetime"), "");
            setInputString(id("deliveryDriver"), "ドライバー11");
            setSelectString(id("deliveryStatus"), "配達中");
            clickButton(id("update"));
        }

        // 更新確認
        {
            assertThat(getInputString(id("deliveryNo")), is("1"));
            assertThat(getInputString(id("deliveryTypeName")), is("通常"));
            assertThat(getInputString(id("senderName")), is("送り主名11"));
            assertThat(getInputString(id("senderAddress")), is("送り主住所11"));
            assertThat(getInputString(id("recieverName")), is("送り先名11"));
            assertThat(getInputString(id("recieverAddress")), is("送り先住所11"));
            assertThat(getInputString(id("acceptDatetime")), is(
                    "2014/01/11 11:11:11"));
            assertThat(getInputString(id("completionDatetime")), is(""));
            assertThat(getInputString(id("deliveryDriver")), is("ドライバー11"));
            assertThat(getInputString(id("deliveryStatus")), is("配達中"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

    }

    /**
     * <ul>
     * <li>業務データの削除ができること。</li>
     * </ul>
     */
    @Test
    public void testDMLY0201008() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0201008"));
        }

        // 事前確認
        {
            clickLink(id("deliveryNo_1"));

            assertThat(getInputString(id("deliveryNo")), is("1"));
            assertThat(getInputString(id("deliveryTypeName")), is("通常"));
            assertThat(getInputString(id("senderName")), is("送り主名1"));
            assertThat(getInputString(id("senderAddress")), is("送り主住所1"));
            assertThat(getInputString(id("recieverName")), is("送り先名1"));
            assertThat(getInputString(id("recieverAddress")), is("送り先住所1"));
            assertThat(getInputString(id("acceptDatetime")), is(
                    "2014/01/01 01:01:01"));
            assertThat(getInputString(id("completionDatetime")), is(""));
            assertThat(getInputString(id("deliveryDriver")), is("ドライバー1"));
            assertThat(getInputString(id("deliveryStatus")), is("受付"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 削除
        {
            clickButton(id("delete"));
        }

        // 削除確認
        {
            assertThat(getInputString(id("deliveryNo")), is(""));
            assertThat(getInputString(id("deliveryTypeName")), is(""));
            assertThat(getInputString(id("senderName")), is(""));
            assertThat(getInputString(id("senderAddress")), is(""));
            assertThat(getInputString(id("recieverName")), is(""));
            assertThat(getInputString(id("recieverAddress")), is(""));
            assertThat(getInputString(id("acceptDatetime")), is(""));
            assertThat(getInputString(id("completionDatetime")), is(""));
            assertThat(getInputString(id("deliveryDriver")), is(""));
            assertThat(getInputString(id("deliveryStatus")), is(""));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

    }

    /**
     * <ul>
     * <li>Serviceに業務データの操作処理を設定できることの確認。</li>
     * </ul>
     */
    @Ignore("testDMLY0101001と同時消化")
    public void testDMLY0301001() {
        testDMLY0101001();
    }

    /**
     * <ul>
     * <li>Serviceに画面からのイベント処理を設定できることの確認。</li>
     * </ul>
     */
    @Ignore("testDMLY0101001と同時消化")
    public void testDMLY0302001() {
        testDMLY0101001();
    }

    /**
     * <ul>
     * <li>Serviceにシグネチャを制限したメソッドを設定できることの確認。</li>
     * </ul>
     */
    @Test
    public void testDMLY0303001() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0303001"));
        }

        // 受付日時で更新(BLogic呼出し)
        {
            setInputString(id("fromAcceptDatetime"), "2014/01/03 00:00:00");
            setInputString(id("toAcceptDatetime"), "2014/01/04 23:59:59");
            setInputString(id("updateCompletionDatetime"),
                    "2014/01/06 15:15:15");
            clickButton(id("updateCriteria"));
        }

        // 確認
        {
            List<String> completionDatetimeList = getValueList(id(
                    "completionDatetime"));

            assertThat(completionDatetimeList.size(), not(0));
            assertThat(completionDatetimeList.get(2), is(
                    "2014/01/06 15:15:15"));
            assertThat(completionDatetimeList.get(3), is(
                    "2014/01/06 15:15:15"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

    }

    /**
     * <ul>
     * <li>PROPAGATION_REQUIREDでトランザクションを開始すること。</li>
     * </ul>
     */
    @Test
    public void testDMLY0401001() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401001"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("successRequired"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrder("11");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

    }

    /**
     * <ul>
     * <li>PROPAGATION_REQUIREDでトランザクションを開始すること。(rollback)</li>
     * </ul>
     */
    @Test
    public void testDMLY0401002() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401002"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("rollbackRequired"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrderEmpty("10");
            confirmDeliveryOrderEmpty("11");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    null, ".*ProcessController exception catch\\..*",
                    ".*DataIntegrityViolationException.*");
        }

    }

    /**
     * <ul>
     * <li>PROPAGATION_REQUIREDで既存トランザクションに参加すること。</li>
     * </ul>
     */
    @Test
    public void testDMLY0401003() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401003"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("successRequiredRequired"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrder("11");
            confirmDeliveryOrder("12");
            confirmDeliveryOrder("13");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

    }

    /**
     * <ul>
     * <li>PROPAGATION_REQUIREDで既存トランザクションに参加すること。（ロールバック確認）</li>
     * </ul>
     */
    @Test
    public void testDMLY0401004() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401004"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("rollbackRequiredRequired"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrderEmpty("10");
            confirmDeliveryOrderEmpty("11");
            confirmDeliveryOrderEmpty("12");
            confirmDeliveryOrderEmpty("13");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    null, ".*ProcessController exception catch\\..*",
                    ".*DataIntegrityViolationException.*");
        }

    }

    /**
     * <ul>
     * <li>PROPAGATION_REQUIRES_NEWでトランザクションを開始すること。</li>
     * </ul>
     */
    @Test
    public void testDMLY0401005() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401005"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("successRequiresNew"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrder("11");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

    }

    /**
     * <ul>
     * <li>PROPAGATION_REQUIRES_NEWでトランザクションを開始すること。(rollback)</li>
     * </ul>
     */
    @Test
    public void testDMLY0401006() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401006"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("rollbackRequiresNew"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrderEmpty("10");
            confirmDeliveryOrderEmpty("11");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    null, ".*ProcessController exception catch\\..*",
                    ".*DataIntegrityViolationException.*");
        }

    }

    /**
     * <ul>
     * <li>PROPAGATION_REQUIRES_NEWで新しいトランザクションを開始すること。</li>
     * </ul>
     */
    @Test
    public void testDMLY0401007() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401007"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("successRequiredRequiresNew"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrder("11");
            confirmDeliveryOrder("12");
            confirmDeliveryOrder("13");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

    }

    /**
     * <ul>
     * <li>PROPAGATION_REQUIRES_NEWで新しいトランザクションを開始すること。（ロールバック確認）。</li>
     * </ul>
     */
    @Test
    public void testDMLY0401008() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401008"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("rollbackRequiredRequiresNew"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrder("11");
            confirmDeliveryOrderEmpty("12");
            confirmDeliveryOrderEmpty("13");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    null, ".*PropagationService exception catch\\..*",
                    ".*DataIntegrityViolationException.*");
        }

    }

    /**
     * <ul>
     * <li>PROPAGATION_REQUIRES_NEWで新しいトランザクションを開始すること。（ロールバック確認その２）。</li>
     * </ul>
     */
    @Test
    public void testDMLY0401009() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401009"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("rollbackRequiredRequiresNew2"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrderEmpty("10");
            confirmDeliveryOrder("11");
            confirmDeliveryOrder("12");
            confirmDeliveryOrderEmpty("13");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    null, ".*ProcessController exception catch\\..*",
                    ".*DataIntegrityViolationException.*");
        }

    }

    /**
     * <ul>
     * <li>PROPAGATION_SUPPORTSでトランザクションを行わないこと。</li>
     * </ul>
     */
    @Test
    public void testDMLY0401010() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401010"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("successSupports"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // "h2" では期待通り動作しない
        if ("h2".equals(databaseId)) {
            return;
        }

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrder("11");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

    }

    /**
     * <ul>
     * <li>PROPAGATION_SUPPORTSでトランザクションを行わないこと。(rollback)</li>
     * </ul>
     */
    @Test
    public void testDMLY0401011() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401011"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("rollbackSupports"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // "h2" では期待通り動作しない
        if ("h2".equals(databaseId)) {
            return;
        }

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrderEmpty("11");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    null, ".*ProcessController exception catch\\..*",
                    ".*DataIntegrityViolationException.*");
        }

    }

    /**
     * <ul>
     * <li>PROPAGATION_SUPPORTSで既存トランザクションに参加すること。</li>
     * </ul>
     */
    @Test
    public void testDMLY0401012() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401012"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("successRequiredSupports"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrder("11");
            confirmDeliveryOrder("12");
            confirmDeliveryOrder("13");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

    }

    /**
     * <ul>
     * <li>PROPAGATION_SUPPORTSで既存トランザクションに参加すること。（ロールバック確認）</li>
     * </ul>
     */
    @Test
    public void testDMLY0401013() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401013"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("rollbackRequiredSupports"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrderEmpty("10");
            confirmDeliveryOrderEmpty("11");
            confirmDeliveryOrderEmpty("12");
            confirmDeliveryOrderEmpty("13");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    null, ".*ProcessController exception catch\\..*",
                    ".*DataIntegrityViolationException.*");
        }

    }

    /**
     * <ul>
     * <li>PROPAGATION_NOT_SUPPORTEDでトランザクションを行わないこと。</li>
     * </ul>
     */
    @Test
    public void testDMLY0401014() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401014"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("successNotSupported"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // "h2" では期待通り動作しない
        if ("h2".equals(databaseId)) {
            return;
        }

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrder("11");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

    }

    /**
     * <ul>
     * <li>PROPAGATION_NOT_SUPPORTEDでトランザクションを行わないこと。(rollback)</li>
     * </ul>
     */
    @Test
    public void testDMLY0401015() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401015"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("rollbackNotSupported"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // "h2" では期待通り動作しない
        if ("h2".equals(databaseId)) {
            return;
        }

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrderEmpty("11");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    null, ".*ProcessController exception catch\\..*",
                    ".*DataIntegrityViolationException.*");
        }

    }

    /**
     * <ul>
     * <li>PROPAGATION_NOT_SUPPORTEDでトランザクションを行わないこと。</li>
     * </ul>
     */
    @Test
    public void testDMLY0401016() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401016"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("successRequiredNotSupported"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // "h2" では期待通り動作しない
        if ("h2".equals(databaseId)) {
            return;
        }

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrder("11");
            confirmDeliveryOrder("12");
            confirmDeliveryOrder("13");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

    }

    /**
     * <ul>
     * <li>PROPAGATION_NOT_SUPPORTEDでトランザクションを行わないこと。(rollback)</li>
     * </ul>
     */
    @Test
    public void testDMLY0401017() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401017"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("rollbackRequiredNotSupported"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // "h2" では期待通り動作しない
        if ("h2".equals(databaseId)) {
            return;
        }

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrder("11");
            confirmDeliveryOrder("12");
            confirmDeliveryOrderEmpty("13");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    null, ".*PropagationService exception catch\\..*",
                    ".*DataIntegrityViolationException.*");
        }

    }

    /**
     * <ul>
     * <li>PROPAGATION_MANDATORYで例外が発生すること。</li>
     * </ul>
     */
    @Test
    public void testDMLY0401018() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401018"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("exceptionMandatory"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrderEmpty("10");
            confirmDeliveryOrderEmpty("11");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    null, ".*ProcessController exception catch\\..*",
                    ".*IllegalTransactionStateException.*");
        }

    }

    /**
     * <ul>
     * <li>PROPAGATION_MANDATORYで既存トランザクションに参加すること。</li>
     * </ul>
     */
    @Test
    public void testDMLY0401019() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401019"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("successRequiredMandatory"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrder("11");
            confirmDeliveryOrder("12");
            confirmDeliveryOrder("13");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

    }

    /**
     * <ul>
     * <li>PROPAGATION_MANDATORYで既存トランザクションに参加すること。（ロールバック確認）</li>
     * </ul>
     */
    @Test
    public void testDMLY0401020() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401020"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("rollbackRequiredMandatory"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrderEmpty("10");
            confirmDeliveryOrderEmpty("11");
            confirmDeliveryOrderEmpty("12");
            confirmDeliveryOrderEmpty("13");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    null, ".*ProcessController exception catch\\..*",
                    ".*DataIntegrityViolationException.*");
        }

    }

    /**
     * <ul>
     * <li>PROPAGATION_NEVERでトランザクションを行わないこと。</li>
     * </ul>
     */
    @Test
    public void testDMLY0401021() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401021"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("successNever"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // "h2" では期待通り動作しない
        if ("h2".equals(databaseId)) {
            return;
        }

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrder("11");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

    }

    /**
     * <ul>
     * <li>PROPAGATION_NEVERでトランザクションを行わないこと。(rollback)</li>
     * </ul>
     */
    @Test
    public void testDMLY0401022() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401022"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("rollbackNever"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // "h2" では期待通り動作しない
        if ("h2".equals(databaseId)) {
            return;
        }

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrderEmpty("11");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    null, ".*ProcessController exception catch\\..*",
                    ".*DataIntegrityViolationException.*");
        }

    }

    /**
     * <ul>
     * <li>PROPAGATION_NEVERで例外が発生すること。</li>
     * </ul>
     */
    @Test
    public void testDMLY0401023() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401023"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("exceptionRequiredNever"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrder("11");
            confirmDeliveryOrderEmpty("12");
            confirmDeliveryOrderEmpty("13");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    null, ".*PropagationService exception catch\\..*",
                    ".*IllegalTransactionStateException.*");
        }

    }

    /**
     * <ul>
     * <li>PROPAGATION_NESTEDでトランザクションを開始すること。</li>
     * </ul>
     */
    @Test
    public void testDMLY0401024() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401024"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("successNested"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrder("11");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

    }

    /**
     * <ul>
     * <li>PROPAGATION_NESTEDでトランザクションを開始すること。(rollback)</li>
     * </ul>
     */
    @Test
    public void testDMLY0401025() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401025"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("rollbackNested"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrderEmpty("10");
            confirmDeliveryOrderEmpty("11");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    null, ".*ProcessController exception catch\\..*",
                    ".*DataIntegrityViolationException.*");
        }

    }

    /**
     * <ul>
     * <li>PROPAGATION_NESTEDで部分トランザクションを開始すること。</li>
     * </ul>
     */
    @Test
    public void testDMLY0401026() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401026"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("successRequiredNested"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrder("11");
            confirmDeliveryOrder("12");
            confirmDeliveryOrder("13");
        }

        // ログ確認
        // "oracle" ではPhysicalConnection#releaseSavepoint()で
        // "SQLException: サポートされない機能です"とエラーが出る。
        if ("oracle".equals(databaseId)) {
            return;
        }

        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

    }

    /**
     * <ul>
     * <li>PROPAGATION_NESTEDで部分トランザクションを開始すること。（ロールバック確認）</li>
     * </ul>
     */
    @Test
    public void testDMLY0401027() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401027"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("rollbackRequiredNested"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrder("11");
            confirmDeliveryOrderEmpty("12");
            confirmDeliveryOrderEmpty("13");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    null, ".*PropagationService exception catch\\..*",
                    ".*DataIntegrityViolationException.*");
        }

    }

    /**
     * <ul>
     * <li>PROPAGATION_NESTEDで部分トランザクションを開始すること。（ロールバック確認その２）</li>
     * </ul>
     */
    @Test
    public void testDMLY0401028() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401028"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("rollbackRequiredNested2"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrderEmpty("10");
            confirmDeliveryOrderEmpty("11");
            confirmDeliveryOrderEmpty("12");
            confirmDeliveryOrderEmpty("13");
        }

        // ログ確認
        // "oracle" ではPhysicalConnection#releaseSavepoint()で
        // "SQLException: サポートされない機能です"とエラーが出る。
        if ("oracle".equals(databaseId)) {
            return;
        }

        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    null, ".*ProcessController exception catch\\..*",
                    ".*DataIntegrityViolationException.*");
        }

    }

    /**
     * <ul>
     * <li>PROPAGATION_NESTEDで部分トランザクションを開始すること。（ロールバック確認その３）</li>
     * </ul>
     */
    @Test
    public void testDMLY0401029() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401029"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("rollbackRequiredNested3"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrderEmpty("11");
            confirmDeliveryOrderEmpty("12");
            confirmDeliveryOrder("13");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    null, ".*PropagationService exception catch\\..*",
                    ".*DataIntegrityViolationException.*");
        }

    }

    /**
     * <ul>
     * <li>javax.transaction.Transactional(Transactional.TxType.REQUIRED)でトランザクションを開始すること。</li>
     * </ul>
     */
    @Test
    public void testDMLY0401030() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401030"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("successRequiredJTA12"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrder("11");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

    }

    /**
     * <ul>
     * <li>javax.transaction.Transactional(Transactional.TxType.REQUIRED)でトランザクションを開始すること。(rollback)</li>
     * </ul>
     */
    @Test
    public void testDMLY0401031() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401031"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("rollbackRequiredJTA12"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrderEmpty("10");
            confirmDeliveryOrderEmpty("11");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    null, ".*ProcessController exception catch\\..*",
                    ".*DataIntegrityViolationException.*");
        }

    }

    /**
     * <ul>
     * <li>javax.transaction.Transactional(Transactional.TxType.REQUIRED)で既存トランザクションに参加すること。</li>
     * </ul>
     */
    @Test
    public void testDMLY0401032() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401032"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("successRequiredRequiredJTA12"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrder("11");
            confirmDeliveryOrder("12");
            confirmDeliveryOrder("13");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

    }

    /**
     * <ul>
     * <li>javax.transaction.Transactional(Transactional.TxType.REQUIRED)で既存トランザクションに参加すること。（ロールバック確認）</li>
     * </ul>
     */
    @Test
    public void testDMLY0401033() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401033"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("rollbackRequiredRequiredJTA12"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrderEmpty("10");
            confirmDeliveryOrderEmpty("11");
            confirmDeliveryOrderEmpty("12");
            confirmDeliveryOrderEmpty("13");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    null, ".*ProcessController exception catch\\..*",
                    ".*DataIntegrityViolationException.*");
        }

    }

    /**
     * <ul>
     * <li>javax.transaction.Transactional(Transactional.TxType.REQUIRES_NEW)でトランザクションを開始すること。</li>
     * </ul>
     */
    @Test
    public void testDMLY0401034() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401034"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("successRequiresNewJTA12"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrder("11");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

    }

    /**
     * <ul>
     * <li>javax.transaction.Transactional(Transactional.TxType.REQUIRES_NEW)でトランザクションを開始すること。（ロールバック確認）</li>
     * </ul>
     */
    @Test
    public void testDMLY0401035() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401035"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("rollbackRequiresNewJTA12"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrderEmpty("10");
            confirmDeliveryOrderEmpty("11");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    null, ".*ProcessController exception catch\\..*",
                    ".*DataIntegrityViolationException.*");
        }

    }

    /**
     * <ul>
     * <li>javax.transaction.Transactional(Transactional.TxType.REQUIRES_NEW)で新しいトランザクションを開始すること。</li>
     * </ul>
     */
    @Test
    public void testDMLY0401036() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401036"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("successRequiredRequiresNewJTA12"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrder("11");
            confirmDeliveryOrder("12");
            confirmDeliveryOrder("13");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

    }

    /**
     * <ul>
     * <li>javax.transaction.Transactional(Transactional.TxType.REQUIRES_NEW)で新しいトランザクションを開始すること。（ロールバック確認）</li>
     * </ul>
     */
    @Test
    public void testDMLY0401037() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401037"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("rollbackRequiredRequiresNewJTA12"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrder("11");
            confirmDeliveryOrderEmpty("12");
            confirmDeliveryOrderEmpty("13");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    null, ".*PropagationService exception catch\\..*",
                    ".*DataIntegrityViolationException.*");
        }

    }

    /**
     * <ul>
     * <li>PROPAGATION_REQUIRES_NEWで新しいトランザクションを開始すること。（ロールバック確認その２）。</li>
     * </ul>
     */
    @Test
    public void testDMLY0401038() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401038"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("rollbackRequiredRequiresNew2JTA12"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrderEmpty("10");
            confirmDeliveryOrder("11");
            confirmDeliveryOrder("12");
            confirmDeliveryOrderEmpty("13");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    null, ".*ProcessController exception catch\\..*",
                    ".*DataIntegrityViolationException.*");
        }

    }

    /**
     * <ul>
     * <li>javax.transaction.Transactional(Transactional.TxType.SUPPORTS)でトランザクションを行わないこと。</li>
     * </ul>
     */
    @Test
    public void testDMLY0401039() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401039"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("successSupportsJTA12"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // "h2" では期待通り動作しない
        if ("h2".equals(databaseId)) {
            return;
        }

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrder("11");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

    }

    /**
     * <ul>
     * <li>javax.transaction.Transactional(Transactional.TxType.SUPPORTS)でトランザクションを行わないこと。（ロールバック確認）</li>
     * </ul>
     */
    @Test
    public void testDMLY0401040() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401040"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("rollbackSupportsJTA12"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // "h2" では期待通り動作しない
        if ("h2".equals(databaseId)) {
            return;
        }

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrderEmpty("11");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    null, ".*ProcessController exception catch\\..*",
                    ".*DataIntegrityViolationException.*");
        }

    }

    /**
     * <ul>
     * <li>javax.transaction.Transactional(Transactional.TxType.SUPPORTS)で既存トランザクションに参加すること。</li>
     * </ul>
     */
    @Test
    public void testDMLY0401041() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401041"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("successRequiredSupportsJTA12"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrder("11");
            confirmDeliveryOrder("12");
            confirmDeliveryOrder("13");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

    }

    /**
     * <ul>
     * <li>javax.transaction.Transactional(Transactional.TxType.SUPPORTS)で既存トランザクションに参加すること。（ロールバック確認）</li>
     * </ul>
     */
    @Test
    public void testDMLY0401042() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401042"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("rollbackRequiredSupportsJTA12"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrderEmpty("10");
            confirmDeliveryOrderEmpty("11");
            confirmDeliveryOrderEmpty("12");
            confirmDeliveryOrderEmpty("13");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    null, ".*ProcessController exception catch\\..*",
                    ".*DataIntegrityViolationException.*");
        }

    }

    /**
     * <ul>
     * <li>javax.transaction.Transactional(Transactional.TxType.NOT_SUPPORTED)でトランザクションを行わないこと。</li>
     * </ul>
     */
    @Test
    public void testDMLY0401043() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401043"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("successNotSupportedJTA12"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // "h2" では期待通り動作しない
        if ("h2".equals(databaseId)) {
            return;
        }

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrder("11");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

    }

    /**
     * <ul>
     * <li>javax.transaction.Transactional(Transactional.TxType.NOT_SUPPORTED)でトランザクションを行わないこと。（ロールバック確認）</li>
     * </ul>
     */
    @Test
    public void testDMLY0401044() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401044"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("rollbackNotSupportedJTA12"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // "h2" では期待通り動作しない
        if ("h2".equals(databaseId)) {
            return;
        }

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrderEmpty("11");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    null, ".*ProcessController exception catch\\..*",
                    ".*DataIntegrityViolationException.*");
        }

    }

    /**
     * <ul>
     * <li>javax.transaction.Transactional(Transactional.TxType.NOT_SUPPORTED)でトランザクションを行わないこと。</li>
     * </ul>
     */
    @Test
    public void testDMLY0401045() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401045"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("successRequiredNotSupportedJTA12"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // "h2" では期待通り動作しない
        if ("h2".equals(databaseId)) {
            return;
        }

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrder("11");
            confirmDeliveryOrder("12");
            confirmDeliveryOrder("13");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

    }

    /**
     * <ul>
     * <li>javax.transaction.Transactional(Transactional.TxType.NOT_SUPPORTED)でトランザクションを行わないこと。（ロールバック確認）</li>
     * </ul>
     */
    @Test
    public void testDMLY0401046() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401046"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("rollbackRequiredNotSupportedJTA12"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // "h2" では期待通り動作しない
        if ("h2".equals(databaseId)) {
            return;
        }

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrder("11");
            confirmDeliveryOrder("12");
            confirmDeliveryOrderEmpty("13");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    null, ".*PropagationService exception catch\\..*",
                    ".*DataIntegrityViolationException.*");
        }

    }

    /**
     * <ul>
     * <li>javax.transaction.Transactional(Transactional.TxType.MANDATORY)で例外が発生すること。</li>
     * </ul>
     */
    @Test
    public void testDMLY0401047() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401047"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("exceptionMandatoryJTA12"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrderEmpty("10");
            confirmDeliveryOrderEmpty("11");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    null, ".*ProcessController exception catch\\..*",
                    ".*IllegalTransactionStateException.*");
        }

    }

    /**
     * <ul>
     * <li>javax.transaction.Transactional(Transactional.TxType.MANDATORY)で既存トランザクションに参加すること。</li>
     * </ul>
     */
    @Test
    public void testDMLY0401048() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401048"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("successRequiredMandatoryJTA12"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrder("11");
            confirmDeliveryOrder("12");
            confirmDeliveryOrder("13");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

    }

    /**
     * <ul>
     * <li>javax.transaction.Transactional(Transactional.TxType.MANDATORY)で既存トランザクションに参加すること。（ロールバック確認）</li>
     * </ul>
     */
    @Test
    public void testDMLY0401049() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401049"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("rollbackRequiredMandatoryJTA12"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrderEmpty("10");
            confirmDeliveryOrderEmpty("11");
            confirmDeliveryOrderEmpty("12");
            confirmDeliveryOrderEmpty("13");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    null, ".*ProcessController exception catch\\..*",
                    ".*DataIntegrityViolationException.*");
        }

    }

    /**
     * <ul>
     * <li>javax.transaction.Transactional(Transactional.TxType.NEVER)でトランザクションを行わないこと。</li>
     * </ul>
     */
    @Test
    public void testDMLY0401050() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401050"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("successNeverJTA12"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // "h2" では期待通り動作しない
        if ("h2".equals(databaseId)) {
            return;
        }

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrder("11");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

    }

    /**
     * <ul>
     * <li>javax.transaction.Transactional(Transactional.TxType.NEVER)でトランザクションを行わないこと。（ロールバック確認）</li>
     * </ul>
     */
    @Test
    public void testDMLY0401051() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401051"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("rollbackNeverJTA12"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // "h2" では期待通り動作しない
        if ("h2".equals(databaseId)) {
            return;
        }

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrderEmpty("11");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    null, ".*ProcessController exception catch\\..*",
                    ".*DataIntegrityViolationException.*");
        }

    }

    /**
     * <ul>
     * <li>javax.transaction.Transactional(Transactional.TxType.NEVER)で例外が発生すること。</li>
     * </ul>
     */
    @Test
    public void testDMLY0401052() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dmly0401052"));
        }

        // 処理開始ボタン押下
        {
            clickButton(id("exceptionRequiredNeverJTA12"));
        }

        String databaseId = getTextString(id("databaseId"));
        assertThat(databaseId, not(""));

        // 確認
        {
            confirmDeliveryOrder("10");
            confirmDeliveryOrder("11");
            confirmDeliveryOrderEmpty("12");
            confirmDeliveryOrderEmpty("13");
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    null, ".*PropagationService exception catch\\..*",
                    ".*IllegalTransactionStateException.*");
        }

    }

    /**
     * <ul>
     * <li>トランザクション管理が有効にできること。</li>
     * </ul>
     */
    @Ignore("testDMLY0401001～testDMLY0401028で同時消化")
    public void testDMLY0402001() {
        testDMLY0401001();
        testDMLY0401002();
        testDMLY0401003();
        testDMLY0401004();
        testDMLY0401005();
        testDMLY0401006();
        testDMLY0401007();
        testDMLY0401008();
        testDMLY0401009();
        testDMLY0401010();
        testDMLY0401011();
        testDMLY0401012();
        testDMLY0401013();
        testDMLY0401014();
        testDMLY0401015();
        testDMLY0401016();
        testDMLY0401017();
        testDMLY0401018();
        testDMLY0401019();
        testDMLY0401020();
        testDMLY0401021();
        testDMLY0401022();
        testDMLY0401023();
        testDMLY0401024();
        testDMLY0401025();
        testDMLY0401026();
        testDMLY0401027();
        testDMLY0401028();
        testDMLY0401029();
        testDMLY0401030();
        testDMLY0401031();
        testDMLY0401032();
        testDMLY0401033();
        testDMLY0401034();
        testDMLY0401035();
        testDMLY0401036();
        testDMLY0401037();
        testDMLY0401038();
        testDMLY0401039();
        testDMLY0401040();
        testDMLY0401041();
        testDMLY0401042();
        testDMLY0401043();
        testDMLY0401044();
        testDMLY0401045();
        testDMLY0401046();
        testDMLY0401047();
        testDMLY0401048();
        testDMLY0401049();
        testDMLY0401050();
        testDMLY0401051();
        testDMLY0401052();
    }

}
