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
package jp.co.ntt.fw.spring.functionaltest.selenium.vldt;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.id;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Value;

public class MethodValidationTest extends FunctionTestSupport {

    @Value("${selenium.vldt.waitForLogAssertion.duration}")
    protected int waitForAssertionNum;

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

    private void setInputString(By by, String value) {
        webDriverOperations.overrideText(by, value);
    }

    private String getTextString(By by) {
        return webDriverOperations.getText(by);
    }

    private void waitForAssertion(long waitTime) {
        dbLogAssertOperations.waitForAssertion(waitTime);
    }

    public void assertNotContainsWarnAndError() {
        dbLogAssertOperations.assertNotContainsWarnAndError(webDriverOperations
                .getXTrack());
    }

    /**
     * <ul>
     * <li>入力チェックエラー(@NotNull)なしを返却できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0601001() {

        // 疑似アプリ画面表示
        {
            clickLink(id("vldt0601001"));
        }

        // データ入力
        {
            setInputString(id("userId"), "visit");
            clickButton(id("register"));
        }

        // 結果確認
        {
            assertThat(getTextString(id("userId")), is("visit"));
        }

        // ログ確認(エラーがないこと)
        {
            waitForAssertion(waitForAssertionNum);
            assertNotContainsWarnAndError();
        }
    }

    /**
     * <ul>
     * <li>入力チェックエラー(@NotNull)で入力が""(空文字)でチェックエラーなし
     * <li>(@NotNull)で出力が""(空文字)でチェックエラーなしを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0601002() {

        // 疑似アプリ画面表示
        {
            clickLink(id("vldt0601002"));
        }

        // データ入力
        {
            setInputString(id("userId"), "");
            clickButton(id("register"));
        }

        // 結果確認
        {
            assertThat(getTextString(id("userId")), is(""));
        }

        // ログ確認(エラーがないこと)
        {
            waitForAssertion(waitForAssertionNum);
            assertNotContainsWarnAndError();
        }
    }

    /**
     * <ul>
     * <li>入力チェックエラー(@NotNull)で入力がnullの場合、チェックエラーを返却できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0601003() {

        // 疑似アプリ画面表示
        {
            clickLink(id("vldt0601003"));
        }

        // データ入力
        {
            setInputString(id("userId"), "beforeNull");
            clickButton(id("register"));
        }

        // 結果確認
        {
            assertThat(webDriverOperations.getTitle(), is("System Error!"));
        }

        // ログ確認(エラー)
        {
            waitForAssertion(waitForAssertionNum);
            List<String> list = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*ConstraintViolationExceptionHandler",
                    "\\[ConstraintViolationImpl*");
            assertThat(list.size(), is(1));
            String logMessage = list.get(0).toString();
            assertTrue(logMessage.contains(
                    "interpolatedMessage='must not be null',"));
            assertTrue(logMessage.contains("propertyPath=convertUserId.arg0,")
                    | logMessage.contains(
                            "propertyPath=convertUserId.userId,"));
            assertTrue(logMessage.contains(
                    "messageTemplate='{javax.validation.constraints.NotNull.message}'"));
        }
    }

    /**
     * <ul>
     * <li>入力チェックエラー(@NotNull)で出力がnullの場合、チェックエラーを返却できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0601004() {

        // 疑似アプリ画面表示
        {
            clickLink(id("vldt0601004"));
        }

        // データ入力
        {
            setInputString(id("userId"), "afterNull");
            clickButton(id("register"));
        }

        // 結果確認
        {
            assertThat(webDriverOperations.getTitle(), is("System Error!"));
        }

        // ログ確認(エラー)
        {
            waitForAssertion(waitForAssertionNum);
            List<String> list = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*ConstraintViolationExceptionHandler",
                    "\\[ConstraintViolationImpl*");
            assertThat(list.size(), is(1));
            String logMessage = list.get(0).toString();
            assertTrue(logMessage.contains(
                    "interpolatedMessage='must not be null', propertyPath=convertUserId.<return value>,"));
            assertTrue(logMessage.contains(
                    "messageTemplate='{javax.validation.constraints.NotNull.message}'"));
        }
    }

    /**
     * <ul>
     * <li>入出力があるJavaBeanの場合、チェックエラーなしを返却できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0602001() {

        // 疑似アプリ画面表示
        {
            clickLink(id("vldt0602001"));
        }

        // データ入力
        {
            setInputString(id("visitDate"), "20131211");
            setInputString(id("visitMessage"), "visitMessage");
            setInputString(id("userId"), "visitUserId");
            clickButton(id("register"));
        }

        // 結果確認
        {
            assertThat(getTextString(id("userId")), is("visitUserId"));
            assertThat(getTextString(id("userName")), is("visitUserId"));
            assertThat(getTextString(id("dateOfBirth")), is(
                    "Wed Dec 11 00:00:00 JST 2013"));
        }

        // ログ確認(エラーがないこと)
        {
            waitForAssertion(waitForAssertionNum);
            assertNotContainsWarnAndError();
        }
    }

    /**
     * <ul>
     * <li>入出力があるJavaBeanの場合、チェックエラーを返却できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0602002() {

        // 疑似アプリ画面表示
        {
            clickLink(id("vldt0602002"));
        }

        // データ入力
        {
            setInputString(id("visitDate"), "20501211");
            setInputString(id("visitMessage"), "visitMessage");
            setInputString(id("userId"), "visitUserId");
            clickButton(id("register"));
        }

        // 結果確認
        {
            assertThat(webDriverOperations.getTitle(), is("System Error!"));
        }

        // ログ確認
        {
            waitForAssertion(waitForAssertionNum);
            List<String> list = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*ConstraintViolationExceptionHandler",
                    "\\[ConstraintViolationImpl*");
            assertThat(list.size(), is(1));
            String logMessage = list.get(0).toString();
            assertTrue(logMessage.contains(
                    "interpolatedMessage='must be a past date',"));
            assertTrue(logMessage.contains(
                    "propertyPath=convertUserInfo.arg0.visitDate,") | logMessage
                            .contains(
                                    "propertyPath=convertUserInfo.userInfoUseBeanInput.visitDate"));
            assertTrue(logMessage.contains(
                    "messageTemplate='{javax.validation.constraints.Past.message}'"));
        }
    }

    /**
     * <ul>
     * <li>入出力があるJavaBeanの場合、チェックエラーを返却できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0602003() {

        // 疑似アプリ画面表示
        {
            clickLink(id("vldt0602003"));
        }

        // データ入力
        {
            setInputString(id("visitDate"), "20131211");
            setInputString(id("visitMessage"), "visitDateNull");
            setInputString(id("userId"), "visitUserId");
            clickButton(id("register"));
        }

        // 結果確認
        {
            assertThat(webDriverOperations.getTitle(), is("System Error!"));
        }

        // ログ確認
        {
            waitForAssertion(waitForAssertionNum);
            List<String> list = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*ConstraintViolationExceptionHandler",
                    "\\[ConstraintViolationImpl*");
            assertThat(list.size(), is(1));
            String logMessage = list.get(0).toString();
            assertTrue(logMessage.contains(
                    "interpolatedMessage='must not be null',"));
            assertTrue(logMessage.contains(
                    "propertyPath=convertUserInfo.arg0.visitDate,") | logMessage
                            .contains(
                                    "propertyPath=convertUserInfo.userInfoUseBeanInput.visitDate,"));
            assertTrue(logMessage.contains(
                    "messageTemplate='{javax.validation.constraints.NotNull.message}'"));
        }
    }

    /**
     * <ul>
     * <li>入出力があるJavaBeanの場合、チェックエラーを返却できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0602004() {

        // 疑似アプリ画面表示
        {
            clickLink(id("vldt0602004"));
        }

        // データ入力
        {
            setInputString(id("visitDate"), "20131211");
            setInputString(id("visitMessage"), "visitMessageNull");
            setInputString(id("userId"), "visitUserId");
            clickButton(id("register"));
        }

        // 結果確認
        {
            assertThat(webDriverOperations.getTitle(), is("System Error!"));
        }

        // ログ確認
        {
            waitForAssertion(waitForAssertionNum);
            List<String> list = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*ConstraintViolationExceptionHandler",
                    "\\[ConstraintViolationImpl*");
            assertThat(list.size(), is(1));
            String logMessage = list.get(0).toString();
            assertTrue(logMessage.contains(
                    "interpolatedMessage='must not be null',"));
            assertTrue(logMessage.contains(
                    "propertyPath=convertUserInfo.arg0.visitMessage,")
                    | logMessage.contains(
                            "propertyPath=convertUserInfo.userInfoUseBeanInput.visitMessage,"));
            assertTrue(logMessage.contains(
                    "messageTemplate='{javax.validation.constraints.NotNull.message}'"));
        }
    }

    /**
     * <ul>
     * <li>入出力があるJavaBeanの場合、チェックエラーを返却できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0602005() {

        // 疑似アプリ画面表示
        {
            clickLink(id("vldt0602005"));
        }

        // データ入力
        {
            setInputString(id("visitDate"), "20131211");
            setInputString(id("visitMessage"), "inputObjectNull");
            setInputString(id("userId"), "visitUserId");
            clickButton(id("register"));
        }

        // 結果確認
        {
            assertThat(webDriverOperations.getTitle(), is("System Error!"));
        }

        // ログ確認
        {
            waitForAssertion(waitForAssertionNum);
            List<String> list = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*ConstraintViolationExceptionHandler",
                    "\\[ConstraintViolationImpl*");
            assertThat(list.size(), is(1));
            String logMessage = list.get(0).toString();
            assertTrue(logMessage.contains(
                    "interpolatedMessage='must not be null', propertyPath=convertUserInfo.arg0,"));
            assertTrue(logMessage.contains(
                    "messageTemplate='{javax.validation.constraints.NotNull.message}'"));
        }
    }

    /**
     * <ul>
     * <li>入出力があるJavaBeanの場合、チェックエラーを返却できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0602006() {

        // 疑似アプリ画面表示
        {
            clickLink(id("vldt0602006"));
        }

        // データ入力
        {
            setInputString(id("visitDate"), "20131211");
            setInputString(id("visitMessage"), "outputObjectNull");
            setInputString(id("userId"), "visitUserId");
            clickButton(id("register"));
        }

        // 結果確認
        {
            assertThat(webDriverOperations.getTitle(), is("System Error!"));
        }

        // ログ確認
        {
            waitForAssertion(waitForAssertionNum);
            List<String> list = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*ConstraintViolationExceptionHandler",
                    "\\[ConstraintViolationImpl*");
            assertThat(list.size(), is(1));
            String logMessage = list.get(0).toString();
            assertTrue(logMessage.contains(
                    "interpolatedMessage='must not be null', propertyPath=convertUserInfo.<return value>,"));
            assertTrue(logMessage.contains(
                    "messageTemplate='{javax.validation.constraints.NotNull.message}'"));
        }
    }

    /**
     * <ul>
     * <li>入出力があるJavaBeanの場合、チェックエラーを返却できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0602007() {

        // 疑似アプリ画面表示
        {
            clickLink(id("vldt0602007"));
        }

        // データ入力
        {
            setInputString(id("visitDate"), "20131211");
            setInputString(id("visitMessage"), "userIdNull");
            setInputString(id("userId"), "visitUserId");
            clickButton(id("register"));
        }

        // 結果確認
        {
            assertThat(webDriverOperations.getTitle(), is("System Error!"));
        }

        // ログ確認
        {
            waitForAssertion(waitForAssertionNum);
            List<String> list = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*ConstraintViolationExceptionHandler",
                    "\\[ConstraintViolationImpl*");
            assertThat(list.size(), is(1));
            String logMessage = list.get(0).toString();
            assertTrue(logMessage.contains(
                    "interpolatedMessage='must not be null', propertyPath=convertUserInfo.<return value>.userInfo.userId"));
            assertTrue(logMessage.contains(
                    "messageTemplate='{javax.validation.constraints.NotNull.message}'"));
        }
    }

    /**
     * <ul>
     * <li>入出力があるJavaBeanの場合、チェックエラーを返却できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0602008() {

        // 疑似アプリ画面表示
        {
            clickLink(id("vldt0602008"));
        }

        // データ入力
        {
            setInputString(id("visitDate"), "20131211");
            setInputString(id("visitMessage"), "userNameNull");
            setInputString(id("userId"), "visitUserId");
            clickButton(id("register"));
        }

        // 結果確認
        {
            assertThat(webDriverOperations.getTitle(), is("System Error!"));
        }

        // ログ確認
        {
            waitForAssertion(waitForAssertionNum);
            List<String> list = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*ConstraintViolationExceptionHandler",
                    "\\[ConstraintViolationImpl*");
            assertThat(list.size(), is(1));
            String logMessage = list.get(0).toString();
            assertTrue(logMessage.contains(
                    "interpolatedMessage='must not be null', propertyPath=convertUserInfo.<return value>.userInfo.userName"));
            assertTrue(logMessage.contains(
                    "messageTemplate='{javax.validation.constraints.NotNull.message}'"));
        }
    }

    /**
     * <ul>
     * <li>入出力があるJavaBeanの場合、チェックエラーを返却できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0602009() {

        // 疑似アプリ画面表示
        {
            clickLink(id("vldt0602009"));
        }

        // データ入力
        {
            setInputString(id("visitDate"), "20131211");
            setInputString(id("visitMessage"), "dateOfBirthFuture");
            setInputString(id("userId"), "visitUserId");
            clickButton(id("register"));
        }

        // 結果確認
        {
            assertThat(webDriverOperations.getTitle(), is("System Error!"));
        }

        // ログ確認
        {
            waitForAssertion(waitForAssertionNum);
            List<String> list = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*ConstraintViolationExceptionHandler",
                    "\\[ConstraintViolationImpl*");
            assertThat(list.size(), is(1));
            String logMessage = list.get(0).toString();
            assertTrue(logMessage.contains(
                    "interpolatedMessage='must be a past date', propertyPath=convertUserInfo.<return value>.userInfo.dateOfBirth"));
            assertTrue(logMessage.contains(
                    "messageTemplate='{javax.validation.constraints.Past.message}'"));
        }
    }
}
