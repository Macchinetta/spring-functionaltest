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
package jp.co.ntt.fw.spring.functionaltest.selenium.lggn;

import static org.openqa.selenium.By.id;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.test.annotation.IfProfileValue;

import jp.co.ntt.fw.spring.functionaltest.selenium.BrowserLocale;
import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

//Thymeleaf版未実装のためJSPのみ実行
@IfProfileValue(name = "test.environment.view", values = {"jsp"})
public class LggnTest extends FunctionTestSupport {

    private static WebDriver driver;

    public LggnTest() {
        disableDefaultWebDriver();
    }

    @Before
    public void setUp() {
        // 日本語ロケールのブラウザを起動
        {
            if (driver == null) {
                driver = webDriverCreator.createLocaleSpecifiedDriver(BrowserLocale.JAPAN);
            }
            setCurrentWebDriver(driver);
        }

        // トップ画面での操作
        {
            webDriverOperations.getWebDriver().manage().window().maximize();
        }
    }

    /**
     * <ul>
     * <li>「[DEBUG]IDなしのログが出力されること」を確認する。</li>
     * </ul>
     */
    @Test
    public void testLGGN0401001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("lggn0401001"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                    "^jp.co.ntt.fw.spring.functionaltest.app.lggn.LGGN0401Controller$",
                    "^debug log$");
            dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                    "^jp.co.ntt.fw.spring.functionaltest.app.lggn.LGGN0401Controller$",
                    "^debug log args:\\{\\}$");
            dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                    "^jp.co.ntt.fw.spring.functionaltest.app.lggn.LGGN0401Controller$",
                    "^debug log args:param1$");
        }

    }

    /**
     * <ul>
     * <li>「[INFO]IDつきのログが出力されること」を確認する。</li>
     * </ul>
     */
    @Test
    public void testLGGN0401002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("lggn0401002"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                    "^jp.co.ntt.fw.spring.functionaltest.app.lggn.LGGN0401Controller$",
                    "^\\[i.sf.fw.1001\\], This message is Info-Level. \\{0\\}$");
            dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                    "^jp.co.ntt.fw.spring.functionaltest.app.lggn.LGGN0401Controller$",
                    "^\\[i.sf.fw.1001\\], This message is Info-Level. replace_value_1$");
            dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                    "^jp.co.ntt.fw.spring.functionaltest.app.lggn.LGGN0401Controller$",
                    "^\\[i.sf.fw.1001\\], This message is Info-Level. replace_value_1_1$");
        }

    }

    /**
     * <ul>
     * <li>「[WARM]IDつきのログが出力されること」を確認する。</li>
     * </ul>
     */
    @Test
    public void testLGGN0401003() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("lggn0401003"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                    "^jp.co.ntt.fw.spring.functionaltest.app.lggn.LGGN0401Controller$",
                    "^\\[w.sf.fw.2001\\], This message is Warn-Level. \\{0\\}$");
            dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                    "^jp.co.ntt.fw.spring.functionaltest.app.lggn.LGGN0401Controller$",
                    "^\\[w.sf.fw.2001\\], This message is Warn-Level. replace_value_2$");
            dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                    "^jp.co.ntt.fw.spring.functionaltest.app.lggn.LGGN0401Controller$",
                    "^\\[w.sf.fw.2001\\], This message is Warn-Level. replace_value_2_1$");
        }

    }

    /**
     * <ul>
     * <li>「[ERROR]IDつきのログが出力されること」を確認する。</li>
     * </ul>
     */
    @Test
    public void testLGGN0401004() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("lggn0401004"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                    "^jp.co.ntt.fw.spring.functionaltest.app.lggn.LGGN0401Controller$",
                    "^\\[e.sf.fw.3001\\], This message is Error-Level. \\{0\\}$");
            dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                    "^jp.co.ntt.fw.spring.functionaltest.app.lggn.LGGN0401Controller$",
                    "^\\[e.sf.fw.3001\\], This message is Error-Level. replace_value_3$");
            dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                    "^jp.co.ntt.fw.spring.functionaltest.app.lggn.LGGN0401Controller$",
                    "^\\[e.sf.fw.3001\\], This message is Error-Level. replace_value_3_1$");
        }

    }

    /**
     * <ul>
     * <li>「[TRACE]IDつきのログが出力されること」を確認する。</li>
     * </ul>
     */
    @Test
    public void testLGGN0401005() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("lggn0401005"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                    "^jp.co.ntt.fw.spring.functionaltest.app.lggn.LGGN0401Controller$",
                    "^\\[t.sf.fw.4001\\], This message is Trace-Level. \\{0\\}$");
            dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                    "^jp.co.ntt.fw.spring.functionaltest.app.lggn.LGGN0401Controller$",
                    "^\\[t.sf.fw.4001\\], This message is Trace-Level. replace_value_4$");
            dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                    "^jp.co.ntt.fw.spring.functionaltest.app.lggn.LGGN0401Controller$",
                    "^\\[t.sf.fw.4001\\], This message is Trace-Level. replace_value_4_1$");
        }

    }

    /**
     * <ul>
     * <li>「[i.sf.fw.1002] UNDEFINED-MESSAGE"と出力されること」を確認する。</li>
     * </ul>
     */
    @Test
    public void testLGGN0401006() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("lggn0401006"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                    "^jp.co.ntt.fw.spring.functionaltest.app.lggn.LGGN0401Controller$",
                    "^\\[i.sf.fw.1002\\], UNDEFINED-MESSAGE id:i.sf.fw.1002 arg:\\[\\]$");
            dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                    "^jp.co.ntt.fw.spring.functionaltest.app.lggn.LGGN0401Controller$",
                    "^\\[i.sf.fw.1002\\], UNDEFINED-MESSAGE id:i.sf.fw.1002 arg:\\[replace_value_5\\]$");
            dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                    "^jp.co.ntt.fw.spring.functionaltest.app.lggn.LGGN0401Controller$",
                    "^\\[i.sf.fw.1002\\], UNDEFINED-MESSAGE id:i.sf.fw.1002 arg:\\[replace_value_5_1, replace_value_5_2\\]$");
        }

    }

    /**
     * <ul>
     * <li>「"This message is Trace-Level. replace_value_7"と出力されること」を確認する。</li>
     * </ul>
     */
    @Test
    public void testLGGN0401007() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("lggn0401007"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                    "^jp.co.ntt.fw.spring.functionaltest.app.lggn.LGGN0401Controller$",
                    "^This message is Trace-Level. replace_value_7$");
        }

    }

    /**
     * <ul>
     * <li>「[WARM]ExceptionとIDつきのログが出力されること」を確認する。</li>
     * </ul>
     */
    @Test
    public void testLGGN0401008() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("lggn0401008"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                    "^jp.co.ntt.fw.spring.functionaltest.app.lggn.LGGN0401Controller$",
                    "^\\[w.sf.fw.2001\\], This message is Warn-Level. replace_value_2$");
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null, null, ".*",
                    "org\\.terasoluna\\.gfw.common\\.exception\\.BusinessException");

        }

    }

    /**
     * <ul>
     * <li>「[Error]ExceptionとIDつきのログが出力されること」を確認する。</li>
     * </ul>
     */
    @Test
    public void testLGGN0401009() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("lggn0401009"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                    "^jp.co.ntt.fw.spring.functionaltest.app.lggn.LGGN0401Controller$",
                    "^\\[e.sf.fw.3001\\], This message is Error-Level. replace_value_3$");
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null, null, ".*",
                    "org\\.terasoluna\\.gfw.common\\.exception\\.BusinessException");
        }

    }

    /**
     * <ul>
     * <li>「"[(message-id)], (message)"のログが出力されること」を確認する。</li>
     * </ul>
     */
    @Test
    public void testLGGN0402001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("lggn0402001"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                    "^org.terasoluna.gfw.common.exception.ExceptionLogger$",
                    "^\\[e.sf.fw.5001\\], SystemException occurred!$");
        }

    }

    /**
     * <ul>
     * <li>「"[(message-id)] (message)"のログが出力されること」を確認する。</li>
     * </ul>
     */
    @Test
    public void testLGGN0402002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("lggn0402002"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                    "^org.terasoluna.gfw.common.exception.ExceptionLogger$",
                    "^\\[e.sf.fw.5001\\] SystemException occurred!$");
        }

    }

}
