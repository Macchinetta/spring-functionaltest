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
package jp.co.ntt.fw.spring.functionaltest.selenium.aply;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.id;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

public class ApplicationLayer_Thymeleaf_Test extends FunctionTestSupport {
    
    private static WebDriver driver;

    @Value("/${selenium.contextName}")
    private String contextRoot;

    private static String VIEW_TYPE = "thymeleaf";

    public ApplicationLayer_Thymeleaf_Test() {
        disableDefaultWebDriver();
    }

    @Before
    public void setUp() {
        // 日本語ロケールのブラウザを起動
        {
            if (driver == null) {
                driver = webDriverCreator.createLocaleSpecifiedDriver("ja");
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
     * <li>リクエスト処理メソッドに実行条件のリクエストパスを設定できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testAPLY0101001() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0101_" + VIEW_TYPE));
        }
        // Controllerの実装方法画面の操作
        {
            webDriverOperations.click(id("aply0101001"));
            webDriverOperations.click(id("aply0101001_1"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@GetMapping(value = \"0101/001_1\")」のメソッドが実行されました。"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // Controllerの実装方法画面の操作
        {
            webDriverOperations.back();
            webDriverOperations.click(id("aply0101001_2"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@PostMapping(value = \"0101/001_2\")」のメソッドが実行されました。"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }
        // Controllerの実装方法画面の操作
        {
            webDriverOperations.back();
            webDriverOperations.click(id("aply0101001_3get"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@RequestMapping(value = \"0101/001_3\", method = {RequestMethod.GET, RequestMethod.POST})」のメソッドが実行されました。"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // Controllerの実装方法画面の操作
        {
            webDriverOperations.back();
            webDriverOperations.click(id("aply0101001_3post"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@RequestMapping(value = \"0101/001_3\", method = {RequestMethod.GET, RequestMethod.POST})」のメソッドが実行されました。"));
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
     * <li>リクエスト処理メソッドに実行条件のHTTPメソッドを設定できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testAPLY0101002() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0101_" + VIEW_TYPE));
        }
        // Controllerの実装方法画面の操作
        {
            webDriverOperations.click(id("aply0101002"));
            webDriverOperations.click(id("aply0101002hello"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@GetMapping(value = \"0101/002/hello\")」のメソッドが実行されました。"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // Controllerの実装方法画面の操作
        {
            webDriverOperations.back();
            webDriverOperations.click(id("aply0101002nihao"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@GetMapping(value = {\"0101/002/nihao\", \"0101/002/bonjour\"})」のメソッドが実行されました。"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // Controllerの実装方法画面の操作
        {
            webDriverOperations.back();
            webDriverOperations.click(id("aply0101002bonjour"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@GetMapping(value = {\"0101/002/nihao\", \"0101/002/bonjour\"})」のメソッドが実行されました。"));
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
     * <li>リクエスト処理メソッドに実行条件のリクエストパラメータを設定できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testAPLY0101003() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0101_" + VIEW_TYPE));
        }
        // Controllerの実装方法画面の操作
        {
            webDriverOperations.click(id("aply0101003"));
            webDriverOperations.click(id("aply0101003_1"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@GetMapping(value = \"0101/003\", params = \"form\")」のメソッドが実行されました。"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // Controllerの実装方法画面の操作
        {
            webDriverOperations.back();
            webDriverOperations.click(id("aply0101003_2"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@GetMapping(value = \"0101/003\", params = {\"form\", \"formType=foo\"})」のメソッドが実行されました。"));
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
     * <li>リクエスト処理メソッドに実行条件のリクエストパラメータの否定を設定できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testAPLY0101004() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0101_" + VIEW_TYPE));
        }
        // Controllerの実装方法画面の操作
        {
            webDriverOperations.click(id("aply0101004"));
            webDriverOperations.click(id("aply0101004_1"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@GetMapping(value = \"0101/004\", params = \"!form\")」のメソッドが実行されました。"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // Controllerの実装方法画面の操作
        {
            webDriverOperations.back();
            webDriverOperations.click(id("aply0101004_2"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@GetMapping(value = \"0101/004\", params = {\"form\", \"formType!=foo\"})」のメソッドが実行されました。"));
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
     * <li>リクエスト処理メソッドの引数にのリクエストパスの一部を設定できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testAPLY0102001() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0101_" + VIEW_TYPE));
        }
        // Controllerの実装方法画面の操作
        {
            webDriverOperations.click(id("aply0102001"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@GetMapping(value = \"0102/001/{id}/{version}\")」のメソッドが実行されました。(id=id1,version=1)"));
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
     * <li>リクエスト処理メソッドの引数にリクエストパスの一部を設定できること(DEGUGモードのみの動作)を確認する。</li>
     * </ul>
     */
    @Test
    public void testAPLY0102002() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0101_" + VIEW_TYPE));
        }
        // Controllerの実装方法画面の操作
        {
            webDriverOperations.click(id("aply0102002"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@GetMapping(value = \"0102/002/{id}/{version}\")」のメソッドが実行されました。(id=id2,version=2)"));
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
     * <li>リクエスト処理メソッドの引数にリクエストパラメータを設定できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testAPLY0102003() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0101_" + VIEW_TYPE));
        }
        // Controllerの実装方法画面の操作
        {
            webDriverOperations.click(id("aply0102003"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@GetMapping(value = \"0102/003\")」のメソッドが実行されました。(id=12,name=taro,age=null,genderCode=unknown)"));
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
     * <li>リクエスト処理メソッドの引数にのリクエストパラメータを設定できることを確認する(required=false指定がないときのエラー確認)。</li>
     * </ul>
     */
    @Test
    public void testAPLY0102004() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0101_" + VIEW_TYPE));
        }
        // Controllerの実装方法画面の操作
        {
            webDriverOperations.click(id("aply0102004"));
        }

        // Controllerの実装方法画面の確認
        {
            // エラー画面に遷移
            assertThat(webDriverOperations.getTitle(), is("Request Error!"));
        }
        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    "org.terasoluna.gfw.common.exception.ExceptionLogger", ".*",
                    "org\\.springframework\\.web\\.bind\\.MissingServletRequestParameterException\\.*");

        }
    }

    /**
     * <ul>
     * <li>リクエスト処理メソッドの引数にリダイレクト情報オブジェクトを設定し、リダイレクト先に渡せることを確認する。</li>
     * </ul>
     */
    @Test
    public void testAPLY0102005() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0101_" + VIEW_TYPE));
        }
        // Controllerの実装方法画面の操作
        {
            webDriverOperations.click(id("aply0102005"));
        }

        // Controllerの実装方法画面の確認
        {
            // URLがリダイレクト先のものになっていること。
            assertTrue(webDriverOperations.getCurrentUrl().contains(VIEW_TYPE
                    + "/0102/005?complete"));
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@GetMapping(value = \"0102/005complete\")」のメソッドが実行されました。"));
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
     * <li>リクエスト処理メソッドの引数にリダイレクト情報オブジェクトを設定し、リダイレクト先URLに使用する値を設定できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testAPLY0102006() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0101_" + VIEW_TYPE));
        }
        // Controllerの実装方法画面の操作
        {
            webDriverOperations.click(id("aply0102006"));
        }

        // Controllerの実装方法画面の確認
        {
            // URLがリダイレクト先のものになっていること。
            assertTrue(webDriverOperations.getCurrentUrl().contains(VIEW_TYPE
                    + "/0102/006?complete&id=redirect_id"));
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@GetMapping(value = \"0102/006complete\")」のメソッドが実行されました。(id=redirect_id)"));
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
     * <li>リクエスト処理メソッドの引数にCookie情報から取得した値を設定できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testAPLY0102007() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0101_" + VIEW_TYPE));
        }
        // Controllerの実装方法画面の操作
        {
            webDriverOperations.click(id("aply0102007"));
        }

        // Controllerの実装方法画面の確認
        {
            assertTrue(webDriverOperations.getText(id("messages")).startsWith(
                    "「@GetMapping(value = \"0102/007\")」のメソッドが実行されました。(cookies[JSESSIONID]="));
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
     * <li>リクエスト処理メソッドの引数にHTTP応答オブジェクトを渡し、Cookie値を設定できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testAPLY0102008() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0101_" + VIEW_TYPE));
        }
        // Controllerの実装方法画面の操作
        {
            webDriverOperations.click(id("aply0102008"));
            webDriverOperations.click(id("setCookie"));
        }

        // Controllerの実装方法画面の操作
        {
            webDriverOperations.click(id("confirm"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@GetMapping(value = \"0102/008\")」のメソッドが実行されました。(cookies[hello]=helloworld!)"));
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
     * <li>共通Bean格納メソッドを作成し、リクエスト処理メソッドの共通情報Beanを設定できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testAPLY0102009() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0101_" + VIEW_TYPE));
        }
        // Controllerの実装方法画面の操作
        {
            webDriverOperations.click(id("aply0102009"));
        }

        // Controllerの実装方法画面の確認
        {
            assertTrue(webDriverOperations.getText(id("messages")).startsWith(
                    "「@GetMapping(value = \"0102/009\")」のメソッドが実行されました。(commonParam1=111, commonParam2=222)"));
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
     * <li>共通Bean格納メソッドを作成し、リクエスト処理メソッドの共通情報Beanを設定できることを確認する。(0102009とは別コントローラで確認)</li>
     * </ul>
     */
    @Test
    public void testAPLY0102010() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0101_" + VIEW_TYPE));
        }
        // Controllerの実装方法画面の操作
        {
            webDriverOperations.click(id("aply0102010"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@GetMapping(value = \"0102/010\")」のメソッドが実行されました。(commonParam1=123, commonParam2=456)"));
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
     * <li>フォームオブジェクトのフィールドに数値書式"#,#"を設定してバインド時に変換できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testAPLY0201001() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0201001_" + VIEW_TYPE));
        }
        // フォームオブジェクトの実装 入力画面の操作
        {
            assertThat(webDriverOperations.getInputFieldValue(id("value1")), is(
                    "1,234,567"));

            webDriverOperations.click(id("submit"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("value1")), is(
                    "1234567"));
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
     * <li>フォームオブジェクトのフィールドに数値書式(NUMBER)を設定してバインド時に変換できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testAPLY0201002() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0201002_" + VIEW_TYPE));
        }
        // フォームオブジェクトの実装 入力画面の操作
        {
            assertThat(webDriverOperations.getInputFieldValue(id("value2")), is(
                    "2,345,678"));

            webDriverOperations.click(id("submit"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("value2")), is(
                    "2345678"));
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
     * <li>フォームオブジェクトのフィールドに数値書式(CURRENCY)を設定してバインド時に変換できることを確認する。</li>
     * </ul>
     */
    @Ignore("クライアントのロケールによって、通貨単位が$になったり￥になったり、国コードのないロケールだと通常意味不明のデフォルト通貨単位になり、利用シーンが考えられないためスキップ")
    @Test
    public void testAPLY0201003() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0201003_" + VIEW_TYPE));
        }
        // フォームオブジェクトの実装 入力画面の操作
        {
            assertThat(webDriverOperations.getWebDriver().findElement(id(
                    "value3")).getAttribute("value"), is("$3,456,789.00"));

            webDriverOperations.click(id("submit"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("value3")), is(
                    "3456789"));
        }
        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError();
        }
    }

    /**
     * <ul>
     * <li>フォームオブジェクトのフィールドに数値書式(PERCENT)を設定してバインド時に変換できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testAPLY0201004() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0201004_" + VIEW_TYPE));
        }
        // フォームオブジェクトの実装 入力画面の操作
        {
            assertThat(webDriverOperations.getInputFieldValue(id("value4")), is(
                    "44%"));

            webDriverOperations.click(id("submit"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("value4")), is("0.44"));
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
     * <li>フォームオブジェクトのフィールドに日付書式(yyyyMMdd)を設定してバインド時に変換できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testAPLY0201005() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0201005_" + VIEW_TYPE));
        }
        // フォームオブジェクトの実装 入力画面の操作
        {
            assertThat(webDriverOperations.getInputFieldValue(id("value5")), is(
                    "19550505"));

            webDriverOperations.click(id("submit"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("value5")), is(
                    "Thu May 05 00:00:00 JST 1955"));
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
     * <li>値変換メソッドを作成し、編纂された値がバインド時できることを確認する(属性名の指定なし)。</li>
     * </ul>
     */
    @Test
    public void testAPLY0201006() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0201006_" + VIEW_TYPE));
        }
        // フォームオブジェクトの実装 入力画面の操作
        {
            assertThat(webDriverOperations.getInputFieldValue(id("value6")), is(
                    "6,666,666,666"));

            webDriverOperations.click(id("submit"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("value6")), is(
                    "6666666666"));
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
     * <li>値変換メソッドを作成し、編纂された値がバインド時できることを確認する(属性名の指定あり)。</li>
     * </ul>
     */
    @Test
    public void testAPLY0201007() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0201007_" + VIEW_TYPE));
        }
        // フォームオブジェクトの実装 入力画面の操作
        {
            assertThat(webDriverOperations.getInputFieldValue(id("value7")), is(
                    "￥7,777,777,777"));

            webDriverOperations.click(id("submit"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("value7")), is(
                    "7777777777"));
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
     * <li>フォームオブジェクト初期化メソッドで生成されたオブジェクトがモデルに格納できることを確認する(属性名なし)。</li>
     * </ul>
     */
    @Test
    public void testAPLY0202001() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0202001_" + VIEW_TYPE));
        }
        // フォームオブジェクトの初期化 確認画面の操作
        {
            assertThat(webDriverOperations.getText(id(
                    "formObjectForm.initConfirmValue")), is(
                            "@ModelAttribute属性名無し初期設定値"));
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
     * <li>フォームオブジェクト初期化メソッドで生成されたオブジェクトがモデルに格納できることを確認する(属性名あり)。</li>
     * </ul>
     */
    @Test
    public void testAPLY0202002() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0202002_" + VIEW_TYPE));
        }
        // フォームオブジェクトの初期化 確認画面の操作
        {
            assertThat(webDriverOperations.getText(id(
                    "formObjectForm2.initConfirmValue")), is(
                            "@ModelAttribute属性名あり初期設定値"));
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
     * <li>フォームオブジェクト初期化メソッドに引数を設定し、引数を格納したオブジェクトがモデルに格納できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testAPLY0202003() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0202002_" + VIEW_TYPE));
        }
        // フォームオブジェクトの初期化 確認画面の操作
        {
            String assertText = webDriverOperations.getText(id(
                    "formObjectForm3.initConfirmValue"));
            assertThat(assertText.length(), Matchers.greaterThan(0));
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
     * <li>リクエスト処理メソッドの引数にフォームオブジェクトを設定できることを確認する(属性名の指定なし)。</li>
     * </ul>
     */
    @Test
    public void testAPLY0203001() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0203001_" + VIEW_TYPE));
        }
        // フォームオブジェクトの実装 入力画面の操作
        {
            webDriverOperations.overrideText(id("value1"), "87,654,321");
            webDriverOperations.overrideText(id("value2"), "23,456,789");
            // webDriverOperations.overrideText(id("value3"), "$34,567,890");
            webDriverOperations.overrideText(id("value4"), "44%");
            webDriverOperations.overrideText(id("value5"), "19550505");
            webDriverOperations.overrideText(id("value6"),
                    "666,666,666,666,666");

            webDriverOperations.click(id("submit"));
        }

        // フォームオブジェクトの実装 完了画面の確認
        {
            assertThat(webDriverOperations.getText(id("value1")), is(
                    "87654321"));
            assertThat(webDriverOperations.getText(id("value2")), is(
                    "23456789"));
            // assertThat(webDriverOperations.getText(id("value3")),
            // is("34567890"));
            assertThat(webDriverOperations.getText(id("value4")), is("0.44"));
            assertThat(webDriverOperations.getText(id("value5")), is(
                    "Thu May 05 00:00:00 JST 1955"));
            assertThat(webDriverOperations.getText(id("value6")), is(
                    "666666666666666"));
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
     * <li>リクエスト処理メソッドの引数にフォームオブジェクトを設定できることを確認する(属性名の指定あり)。</li>
     * </ul>
     */
    @Test
    public void testAPLY0203002() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0203002_" + VIEW_TYPE));
        }
        // フォームオブジェクトの実装 入力画面の操作
        {
            webDriverOperations.overrideText(id("value1"), "87,654,321");
            webDriverOperations.overrideText(id("value2"), "23,456,789");
            // webDriverOperations.overrideText(id("value3"), "$34,567,890");
            webDriverOperations.overrideText(id("value4"), "44%");
            webDriverOperations.overrideText(id("value5"), "19550505");
            webDriverOperations.overrideText(id("value6"),
                    "666,666,666,666,666");
            webDriverOperations.overrideText(id("value7"),
                    "￥777,777,777,777,777");

            webDriverOperations.click(id("submit"));
        }

        // フォームオブジェクトの実装 完了画面の確認
        {
            assertThat(webDriverOperations.getText(id("value1")), is(
                    "87654321"));
            assertThat(webDriverOperations.getText(id("value2")), is(
                    "23456789"));
            // assertThat(webDriverOperations.getText(id("value3")),
            // is("34567890"));
            assertThat(webDriverOperations.getText(id("value4")), is("0.44"));
            assertThat(webDriverOperations.getText(id("value5")), is(
                    "Thu May 05 00:00:00 JST 1955"));
            assertThat(webDriverOperations.getText(id("value6")), is(
                    "666666666666666"));
            assertThat(webDriverOperations.getText(id("value7")), is(
                    "777777777777777"));
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
     * <li>フォームオブジェクトへのバインドエラーが発生しないことを確認する(hasError())。</li>
     * </ul>
     */
    @Test
    public void testAPLY0203003() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0203003_" + VIEW_TYPE));
        }
        // フォームオブジェクトの実装 入力画面の操作
        {
            webDriverOperations.overrideText(id("value1"), "87,654,321");
            webDriverOperations.overrideText(id("value2"), "23,456,789");
            // webDriverOperations.overrideText(id("value3"), "$34,567,890");
            webDriverOperations.overrideText(id("value4"), "44%");
            webDriverOperations.overrideText(id("value5"), "19550505");
            webDriverOperations.overrideText(id("value6"),
                    "666,666,666,666,666");

            webDriverOperations.click(id("submit"));
        }

        // フォームオブジェクトの実装 完了画面の確認
        {
            assertThat(webDriverOperations.getText(id("value1")), is(
                    "87654321"));
            assertThat(webDriverOperations.getText(id("value2")), is(
                    "23456789"));
            // assertThat(webDriverOperations.getText(id("value3")),
            // is("34567890"));
            assertThat(webDriverOperations.getText(id("value4")), is("0.44"));
            assertThat(webDriverOperations.getText(id("value5")), is(
                    "Thu May 05 00:00:00 JST 1955"));
            assertThat(webDriverOperations.getText(id("value6")), is(
                    "666666666666666"));
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
     * <li>フォームオブジェクトへのバインドエラーが発生することを確認する(hasError())。</li>
     * </ul>
     */
    @Test
    public void testAPLY0203004() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0203004_" + VIEW_TYPE));
        }
        // フォームオブジェクトの実装 入力画面の操作
        {
            webDriverOperations.overrideText(id("value1"), "");
            webDriverOperations.overrideText(id("value2"), "23,456,789");
            // webDriverOperations.overrideText(id("value3"), "$34,567,890");
            webDriverOperations.overrideText(id("value4"), "44%");
            webDriverOperations.overrideText(id("value5"), "19550505");
            webDriverOperations.overrideText(id("value6"),
                    "666,666,666,666,666");

            webDriverOperations.click(id("submit"));
        }

        // フォームオブジェクトの実装 入力画面の確認
        {
            assertThat(webDriverOperations.getText(id("value1.errors")), is(
                    "null は許可されていません"));
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
     * <li>フォームオブジェクトへのバインドエラーが発生しないことを確認する(hasGlobalError())。</li>
     * </ul>
     */
    @Test
    public void testAPLY0203005() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0203005_" + VIEW_TYPE));
        }
        // フォームオブジェクトの実装 入力画面の操作
        {
            webDriverOperations.overrideText(id("value1"), "87,654,321");
            webDriverOperations.overrideText(id("value2"), "23,456,789");
            // webDriverOperations.overrideText(id("value3"), "$34,567,890");
            webDriverOperations.overrideText(id("value4"), "44%");
            webDriverOperations.overrideText(id("value5"), "19550505");
            webDriverOperations.overrideText(id("value6"),
                    "666,666,666,666,666");

            webDriverOperations.click(id("submit"));
        }

        // フォームオブジェクトの実装 完了画面の確認
        {
            assertThat(webDriverOperations.getText(id("value1")), is(
                    "87654321"));
            assertThat(webDriverOperations.getText(id("value2")), is(
                    "23456789"));
            // assertThat(webDriverOperations.getText(id("value3")),
            // is("34567890"));
            assertThat(webDriverOperations.getText(id("value4")), is("0.44"));
            assertThat(webDriverOperations.getText(id("value5")), is(
                    "Thu May 05 00:00:00 JST 1955"));
            assertThat(webDriverOperations.getText(id("value6")), is(
                    "666666666666666"));
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
     * <li>フォームオブジェクトへのバインドエラーが発生することを確認する(hasGlobalError())。</li>
     * </ul>
     */
    @Test
    public void testAPLY0203006() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0203006_" + VIEW_TYPE));
        }
        // フォームオブジェクトの実装 入力画面の操作
        {
            webDriverOperations.overrideText(id("value1"), "12,345,678");
            webDriverOperations.overrideText(id("value2"), "23,456,789");
            // webDriverOperations.overrideText(id("value3"), "$34,567,890");
            webDriverOperations.overrideText(id("value4"), "44%");
            webDriverOperations.overrideText(id("value5"), "19550505");
            webDriverOperations.overrideText(id("value6"),
                    "666,666,666,666,666");

            webDriverOperations.click(id("submit"));
        }

        // フォームオブジェクトの実装 入力画面の確認
        {
            assertThat(webDriverOperations.getText(id("formObjectForm.errors")),
                    is("value1 must be larger than value2."));
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
     * <li>フォームオブジェクトへのバインドエラーが発生しないことを確認する(hasFieldError())。</li>
     * </ul>
     */
    @Test
    public void testAPLY0203007() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0203007_" + VIEW_TYPE));
        }
        // フォームオブジェクトの実装 入力画面の操作
        {
            webDriverOperations.overrideText(id("value1"), "87,654,321");
            webDriverOperations.overrideText(id("value2"), "23,456,789");
            // webDriverOperations.overrideText(id("value3"), "$34,567,890");
            webDriverOperations.overrideText(id("value4"), "44%");
            webDriverOperations.overrideText(id("value5"), "19550505");
            webDriverOperations.overrideText(id("value6"),
                    "666,666,666,666,666");

            webDriverOperations.click(id("submit"));
        }

        // フォームオブジェクトの実装 完了画面の確認
        {
            assertThat(webDriverOperations.getText(id("value1")), is(
                    "87654321"));
            assertThat(webDriverOperations.getText(id("value2")), is(
                    "23456789"));
            // assertThat(webDriverOperations.getText(id("value3")),
            // is("34567890"));
            assertThat(webDriverOperations.getText(id("value4")), is("0.44"));
            assertThat(webDriverOperations.getText(id("value5")), is(
                    "Thu May 05 00:00:00 JST 1955"));
            assertThat(webDriverOperations.getText(id("value6")), is(
                    "666666666666666"));
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
     * <li>フォームオブジェクトへのバインドエラーが発生することを確認する(hasFieldError())。</li>
     * </ul>
     */
    @Test
    public void testAPLY0203008() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0203008_" + VIEW_TYPE));
        }
        // フォームオブジェクトの実装 入力画面の操作
        {
            webDriverOperations.overrideText(id("value1"), "");
            webDriverOperations.overrideText(id("value2"), "23,456,789");
            // webDriverOperations.overrideText(id("value3"), "$34,567,890");
            webDriverOperations.overrideText(id("value4"), "44%");
            webDriverOperations.overrideText(id("value5"), "19550505");
            webDriverOperations.overrideText(id("value6"),
                    "666,666,666,666,666");

            webDriverOperations.click(id("submit"));
        }

        // フォームオブジェクトの実装 入力画面の確認
        {
            assertThat(webDriverOperations.getText(id("value1.errors")), is(
                    "null は許可されていません"));
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
     * <li>フォームオブジェクトへのバインドエラーが発生しないことを確認する(hasFieldError())。</li>
     * </ul>
     */
    @Test
    public void testAPLY0203009() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0203007_" + VIEW_TYPE));
        }
        // フォームオブジェクトの実装 入力画面の操作
        {
            webDriverOperations.overrideText(id("value1"), "87,654,321");
            webDriverOperations.overrideText(id("value2"), "23,456,789");
            // webDriverOperations.overrideText(id("value3"), "$34,567,890");
            webDriverOperations.overrideText(id("value4"), "44%");
            webDriverOperations.overrideText(id("value5"), "19550505");
            webDriverOperations.overrideText(id("value6"),
                    "666,666,666,666,666");

            webDriverOperations.click(id("submit"));
        }

        // フォームオブジェクトの実装 完了画面の確認
        {
            assertThat(webDriverOperations.getText(id("value1")), is(
                    "87654321"));
            assertThat(webDriverOperations.getText(id("value2")), is(
                    "23456789"));
            // assertThat(webDriverOperations.getText(id("value3")),
            // is("34567890"));
            assertThat(webDriverOperations.getText(id("value4")), is("0.44"));
            assertThat(webDriverOperations.getText(id("value5")), is(
                    "Thu May 05 00:00:00 JST 1955"));
            assertThat(webDriverOperations.getText(id("value6")), is(
                    "666666666666666"));
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
     * <li>フォームオブジェクトへのバインドエラーが発生することを確認する(hasFieldError())。</li>
     * </ul>
     */
    @Test
    public void testAPLY0203010() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0203008_" + VIEW_TYPE));
        }
        // フォームオブジェクトの実装 入力画面の操作
        {
            webDriverOperations.overrideText(id("value1"), "87,654,321");
            webDriverOperations.overrideText(id("value2"), "23,456,789");
            // webDriverOperations.overrideText(id("value3"), "");
            webDriverOperations.overrideText(id("value4"), "");
            webDriverOperations.overrideText(id("value5"), "19550505");
            webDriverOperations.overrideText(id("value6"),
                    "666,666,666,666,666");

            webDriverOperations.click(id("submit"));
        }

        // フォームオブジェクトの実装 入力画面の確認
        {
            assertThat(webDriverOperations.getText(id("value4.errors")), is(
                    "null は許可されていません"));
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
     * <li>リクエスト処理メソッドの実行前に処理されるサーブレットフィルタを設定できること。</li>
     * </ul>
     */
    @Test
    public void testAPLY0401001() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0401001_" + VIEW_TYPE));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
            dbLogAssertOperations.assertContainsByRegexMessage(
                    "jp.co.ntt.fw.spring.functionaltest.app.aply.LoggingIpAddressFilter",
                    "Client IP Adress:");
        }

        // 確認画面に遷移(基本ログ確認の為、ダミー)
        {
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "共通処理の実装 確認完了"));
        }
    }

    /**
     * <ul>
     * <li>リクエスト処理メソッドの実行前に処理されるサーブレットフィルタを設定できること。（Spring FrameworkのBean形式、別の設定方法）</li>
     * </ul>
     */
    @Test
    public void testAPLY0401002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0401002_" + VIEW_TYPE));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
            dbLogAssertOperations.assertContainsByRegexMessage(
                    "jp.co.ntt.fw.spring.functionaltest.app.aply.LoggingIpAddressFilter",
                    "Client IP Adress:");
        }

        // 確認画面に遷移(基本ログ確認の為、ダミー)
        {
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "共通処理の実装 確認完了"));
        }
    }

    /**
     * <ul>
     * <li>リクエスト処理メソッドの共通処理を実行順序を指定して複数設定できること。</li>
     * </ul>
     */
    @Test
    public void testAPLY0401003() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0401003_" + VIEW_TYPE));
        }

        // ログが出力されていること
        List<String> list;
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
            list = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*APLY04CommonController.*",
                    "\\[APLY0403003.*");

            // 順番通りに出力されていること。
            assertThat(list.size(), is(6));
            assertThat(list.get(0).toString(), is(
                    "[APLY040300301]APLY04CommonControllerAdvice01 initBinder Method Called"));
            assertThat(list.get(1).toString(), is(
                    "[APLY040300302]APLY04CommonControllerAdvice01 setUpCommonParameters01 Method Called"));
            assertThat(list.get(2).toString(), is(
                    "[APLY040300303]APLY04CommonControllerAdvice02 initBinder Method Called"));
            assertThat(list.get(3).toString(), is(
                    "[APLY040300304]APLY04CommonControllerAdvice02 setUpCommonParameters02 Method Called"));
            assertThat(list.get(4).toString(), is(
                    "[APLY040300305]APLY04CommonControllerAdvice03 initBinder Method Called"));
            assertThat(list.get(5).toString(), is(
                    "[APLY040300306]APLY04CommonControllerAdvice03 setUpCommonParameters03 Method Called"));
        }

        // エラー画面に遷移（例外処理を実施している為）
        {
            assertThat(webDriverOperations.getText(By.xpath(
                    "//div[2]/div/ul/li")), is("エラーにより、異常終了しました。"));
        }
    }

    /**
     * <ul>
     * <li>リクエスト処理メソッドの共通処理をハンドラインタセプタで設定できること。（正常処理）</li>
     * </ul>
     */
    @Test
    public void testAPLY0402001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0402001_" + VIEW_TYPE));
        }

        // ログが出力されていること
        List<String> list;
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
            list = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*SuccessLoggingInterceptor", "\\[APLY0402.*");

            // 順番通りに出力されていること。
            assertThat(list.size(), is(3));
            assertThat(list.get(0).toString(), is(
                    "[APLY0402_01] preHandle method called"));
            assertThat(list.get(1).toString(), is(
                    "[APLY0402_02] postHandle method called"));
            assertThat(list.get(2).toString(), is(
                    "[APLY0402_03] afterCompletion method called"));
        }

        // 確認画面に遷移(基本ログ確認の為、ダミー)
        {
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "共通処理の実装 確認完了"));
        }
    }

    /**
     * <ul>
     * <li>モデルに格納された値を表示できること。（th:text属性）</li>
     * </ul>
     */
    @Test
    public void testAPLY1001001() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1001001_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafのth:textを利用してエスケープ処理した文字列を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "<font color='red'>Sample</font>"));
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
     * <li>モデルに格納された数値を指定した書式で表示できること。（#numbers.formatIntegerメソッド）</li>
     * </ul>
     */
    @Test
    public void testAPLY1001002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1001002_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#numbers.formatIntegerを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "7,777"));
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
     * <li>モデルに格納された数値を指定した書式で表示できること。（#numbers.formatDecimalメソッド）</li>
     * </ul>
     */
    @Test
    public void testAPLY1001003() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1001003_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#numbers.formatDecimalを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "3,333.55"));
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
     * <li>モデルに格納された数値を指定した書式で表示できること。（#numbers.formatPercentメソッド）</li>
     * </ul>
     */
    @Test
    public void testAPLY1001004() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1001004_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#numbers.formatPercentを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "34.50%"));
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
     * <li>モデルに格納された日時を指定した書式で表示できること。（#dates.formatメソッド）</li>
     * </ul>
     */
    @Test
    public void testAPLY1001005() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1001005_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#dates.formatを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "2013/12/09"));
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
     * <li>モデルに格納された日時を指定した書式で表示できること。（#calendars.formatメソッド）</li>
     * </ul>
     */
    @Test
    public void testAPLY1001006() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1001006_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#calendars.formatを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "2013/12/09"));
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
     * <li>モデルに格納された数値を指定した書式で表示できること。（#numbers.arrayFormatIntegerメソッド）</li>
     * </ul>
     */
    @Test
    public void testAPLY1001007() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1001007_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#numbers.arrayFormatIntegerを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "1,111, 2,222, 3,333"));
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
     * <li>モデルに格納された数値を指定した書式で表示できること。（#numbers.listFormatIntegerメソッド）</li>
     * </ul>
     */
    @Test
    public void testAPLY1001008() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1001008_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#numbers.listFormatIntegerを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "4,444, 5,555, 6,666"));
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
     * <li>モデルに格納された数値を指定した書式で表示できること。（#numbers.setFormatIntegerメソッド）</li>
     * </ul>
     */
    @Test
    public void testAPLY1001009() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1001009_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#numbers.setFormatIntegerを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "7,777, 8,888, 9,999"));
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
     * <li>モデルに格納された数値を指定した書式で表示できること。（#numbers.arrayFormatDecimalメソッド）</li>
     * </ul>
     */
    @Test
    public void testAPLY1001010() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1001010_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#numbers.arrayFormatDecimalを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "1,111.10, 2,222.20, 3,333.30"));
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
     * <li>モデルに格納された数値を指定した書式で表示できること。（#numbers.listFormatDecimalメソッド）</li>
     * </ul>
     */
    @Test
    public void testAPLY1001011() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1001011_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#numbers.listFormatDecimalを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "4,444.40, 5,555.50, 6,666.60"));
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
     * <li>モデルに格納された数値を指定した書式で表示できること。（#numbers.setFormatDecimalメソッド）</li>
     * </ul>
     */
    @Test
    public void testAPLY1001012() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1001012_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#numbers.setFormatDecimalを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "7,777.70, 8,888.80, 9,999.90"));
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
     * <li>モデルに格納された数値を指定した書式で表示できること。（#numbers.arrayFormatPercentメソッド）</li>
     * </ul>
     */
    @Test
    public void testAPLY1001013() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1001013_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#numbers.arrayFormatPercentを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "11.10%, 22.20%, 33.30%"));
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
     * <li>モデルに格納された数値を指定した書式で表示できること。（#numbers.listFormatPercentメソッド）</li>
     * </ul>
     */
    @Test
    public void testAPLY1001014() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1001014_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#numbers.listFormatPercentを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "44.40%, 55.50%, 66.60%"));
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
     * <li>モデルに格納された数値を指定した書式で表示できること。（#numbers.setFormatPercentメソッド）</li>
     * </ul>
     */
    @Test
    public void testAPLY1001015() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1001015_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#numbers.setFormatPercentを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "77.70%, 88.80%, 99.90%"));
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
     * <li>モデルに格納された数値を指定した書式で表示できること。（#numbers.formatIntegerメソッド）</li>
     * <li>千の位の区切り文字 "." POINT</li>
     * </ul>
     */
    @Test
    public void testAPLY1001016() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1001016_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#numbers.formatIntegerを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "7.777"));
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
     * <li>モデルに格納された数値を指定した書式で表示できること。（#numbers.formatIntegerメソッド）</li>
     * <li>千の位の区切り文字 " " WHITESPACE</li>
     * </ul>
     */
    @Test
    public void testAPLY1001018() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1001018_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#numbers.formatIntegerを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "7 777"));
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
     * <li>モデルに格納された数値を指定した書式で表示できること。（#numbers.formatIntegerメソッド）</li>
     * <li>千の位の区切り文字 "" NONE</li>
     * </ul>
     */
    @Test
    public void testAPLY1001019() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1001019_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#numbers.formatIntegerを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is("7777"));
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
     * <li>モデルに格納された数値を指定した書式で表示できること。（#numbers.formatIntegerメソッド）</li>
     * <li>千の位の区切り文字 "." DEFAULT(Local依存)</li>
     * </ul>
     */
    @Test
    public void testAPLY1001020() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1001020_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#numbers.formatIntegerを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "7,777"));
            assertThat(webDriverOperations.getText(id("message3")),
                    containsString("ja"));
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
     * <li>モデルに格納された数値を指定した書式で表示できること。（#numbers.formatDecimalメソッド）</li>
     * <li>小数点 "," COMMA</li>
     * </ul>
     */
    @Test
    public void testAPLY1001022() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1001022_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#numbers.formatDecimalを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "3,333,55"));
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
     * <li>モデルに格納された数値を指定した書式で表示できること。（#numbers.formatDecimalメソッド）</li>
     * <li>小数点 " " WHITESPACE</li>
     * </ul>
     */
    @Test
    public void testAPLY1001023() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1001023_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#numbers.formatDecimalを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "3,333 55"));
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
     * <li>モデルに格納された数値を指定した書式で表示できること。（#numbers.formatDecimalメソッド）</li>
     * <li>小数点 "" NONE</li>
     * <li>小数点に NONEは使用すべきでない為、"?"が付与される。</li>
     * </ul>
     * @see org.thymeleaf.util.NumberUtils#computeDecimalFormatSymbols(NumberPointType, NumberPointType, Locale)
     */
    @Test
    public void testAPLY1001024() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1001024_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#numbers.formatDecimalを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "3,333?55"));
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
     * <li>モデルに格納された数値を指定した書式で表示できること。（#numbers.formatDecimalメソッド）</li>
     * <li>小数点 "." DEFAULT(Locale依存)</li>
     * </ul>
     */
    @Test
    public void testAPLY1001025() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1001025_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#numbers.formatDecimalを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "3,333.55"));
            assertThat(webDriverOperations.getText(id("message3")),
                    containsString("ja"));
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
     * <li>モデルに格納された数値を指定した書式で表示できること。（#numbers.formatIntegerメソッド）</li>
     * <li>最小桁数を指定し、指定した桁数になることを確認する。</li>
     * </ul>
     */
    @Test
    public void testAPLY1001026() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1001026_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#numbers.formatIntegerを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is("099"));
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
     * <li>モデルに格納された日時を指定した書式で表示できること。（#dates.arrayformatメソッド）</li>
     * </ul>
     */
    @Test
    public void testAPLY1001027() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1001027_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#dates.arrayFormatを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "2013/12/09, 2013/12/10, 2013/12/11"));
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
     * <li>モデルに格納された日時を指定した書式で表示できること。（#dates.listformatメソッド）</li>
     * </ul>
     */
    @Test
    public void testAPLY1001028() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1001028_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#dates.listFormatを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "2013/12/09, 2013/12/10, 2013/12/11"));
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
     * <li>モデルに格納された日時を指定した書式で表示できること。（#dates.setformatメソッド）</li>
     * </ul>
     */
    @Test
    public void testAPLY1001029() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1001029_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#dates.setFormatを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "2013/12/09, 2013/12/10, 2013/12/11"));
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
     * <li>モデルに格納された日時を指定した書式で表示できること。（#calendars.arrayformatメソッド）</li>
     * </ul>
     */
    @Test
    public void testAPLY1001030() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1001030_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#calendars.arrayFormatを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "2013/12/09, 2013/12/10, 2013/12/11"));
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
     * <li>モデルに格納された日時を指定した書式で表示できること。（#calendars.listformatメソッド）</li>
     * </ul>
     */
    @Test
    public void testAPLY1001031() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1001031_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#calendars.listFormatを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "2013/12/09, 2013/12/10, 2013/12/11"));
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
     * <li>モデルに格納された日時を指定した書式で表示できること。（#calendars.setformatメソッド）</li>
     * </ul>
     */
    @Test
    public void testAPLY1001032() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1001032_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#calendars.setFormatを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "2013/12/09, 2013/12/10, 2013/12/11"));
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
     * <li>リンクURL式を利用してURLを生成できること。(コンテキストルートからの相対パス)</li>
     * </ul>
     */
    @Test
    public void testAPLY1002001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1002001_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message2")), is(
                    contextRoot + "-aply/" + VIEW_TYPE + "/1002/user/address"));
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
     * <li>リンクURL式を利用してURLを生成できること。(現在のページからの相対パス)</li>
     * </ul>
     */
    @Test
    public void testAPLY1002002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1002002_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message2")), is(VIEW_TYPE
                    + "/1002/user/address"));
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
     * <li>リンクURL式を利用してURLを生成できること。(パラメータの使用)</li>
     * </ul>
     */
    @Test
    public void testAPLY1002003() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1002003_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message2")), is(
                    contextRoot + "-aply/" + VIEW_TYPE + "/user/address?id=3"));
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
     * <li>リンクURL式を利用してURLを生成できること。(パス内での変数式の使用)</li>
     * </ul>
     */
    @Test
    public void testAPLY1002004() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1002004_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message2")), is(
                    contextRoot + "-aply/" + VIEW_TYPE + "/3/address"));
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
     * <li>#mvc.uriメソッドを利用してURLを生成できること。</li>
     * </ul>
     */
    @Test
    public void testAPLY1002005() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1002005_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message2")), is(
                    contextRoot + "-aply/" + VIEW_TYPE + "/1002/address"));
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
     * <li>リンクURL式を利用してURLを生成できること。(パラメータの使用、変数値がnull評価)</li>
     * </ul>
     */
    @Test
    public void testAPLY1002006() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1002006_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message2")), is(
                    contextRoot + "-aply/" + VIEW_TYPE + "/user//address"));
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
     * <li>プロパティファイルに格納されたメッセージを表示できること。（メッセージ式#{}）</li>
     * </ul>
     */
    @Test
    public void testAPLY1003001() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1003001_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafのメッセージ式を利用して文字列を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "注文ステータス"));
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
     * <li>モデルに格納された値と文字列を結合して表示できること。（+演算子使用）</li>
     * </ul>
     */
    @Test
    public void testAPLY1003002() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1003002_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "+演算子を利用して値と文字列を結合した文字列を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "Message : Hello World!"));
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
     * <li>モデルに格納された値と文字列を結合して表示できること。（パイプ（|）使用）</li>
     * </ul>
     */
    @Test
    public void testAPLY1003003() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1003003_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "パイプ(|)を利用して値と文字列を結合した文字列を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "Message : Hello World!"));
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
     * <li>モデルに格納された値と文字列を結合して表示できること。（変数値がnull評価）</li>
     * </ul>
     */
    @Test
    public void testAPLY1003004() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1003004_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "+演算子を利用して、nullと評価された値と文字列を結合した文字列を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "Message : null"));
            assertThat(webDriverOperations.getText(id("message3")), is(
                    "パイプ(|)を利用して、nullと評価された値と文字列を結合した文字列を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message4")), is(
                    "Message : null"));
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
     * <li>モデルに格納された値により条件分岐して表示切替ができること。（等価演算子を用いた条件式）</li>
     * </ul>
     */
    @Test
    public void testAPLY1004001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1004001_" + VIEW_TYPE));
        }

        // モデルに含まれる値を入力(user.ageが存在する場合)
        {
            webDriverOperations.appendText(id("age"), "20");
            webDriverOperations.click(id("comparator"));
        }

        // リクエスト処理メソッドの応答に対応する画面(user.ageが存在する場合)
        {
            assertThat(webDriverOperations.getText(id("message2")), is("20"));
        }

        // モデルに含まれる値を再度入力(user.ageが存在しない場合)
        {
            webDriverOperations.back();
            webDriverOperations.overrideText(id("age"), "");
            webDriverOperations.click(id("comparator"));
        }

        // リクエスト処理メソッドの応答に対応する画面(user.ageが存在しない場合)
        {
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "no age specified"));
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
     * <li>モデルに格納された値により条件分岐して表示切替ができること。（比較演算子を用いた条件式）</li>
     * </ul>
     */
    @Test
    public void testAPLY1004002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1004002_" + VIEW_TYPE));
        }

        // モデルに含まれる値を入力(user.ageが12以上の場合)
        {
            webDriverOperations.appendText(id("age"), "20");
            webDriverOperations.click(id("arithmeticOperation"));
        }

        // リクエスト処理メソッドの応答に対応する画面(user.ageが12以上の場合)
        {
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "adult"));
        }

        // モデルに含まれる値を再度入力(user.ageが12未満の場合)
        {
            webDriverOperations.back();
            webDriverOperations.overrideText(id("age"), "4");
            webDriverOperations.click(id("arithmeticOperation"));
        }

        // リクエスト処理メソッドの応答に対応する画面(user.ageが12未満の場合)
        {
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "child"));
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
     * <li>モデルに格納された値により条件分岐して表示切替ができること。（デフォルト式）</li>
     * </ul>
     */
    @Test
    public void testAPLY1004003() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1004003_" + VIEW_TYPE));
        }

        // モデルに含まれる値を入力(user.ageが存在する場合)
        {
            webDriverOperations.appendText(id("age"), "20");
            webDriverOperations.click(id("defaultExpressions"));
        }

        // リクエスト処理メソッドの応答に対応する画面(user.ageが存在する場合)
        {
            assertThat(webDriverOperations.getText(id("message2")), is("20"));
        }

        // モデルに含まれる値を再度入力(user.ageが存在しない場合)
        {
            webDriverOperations.back();
            webDriverOperations.overrideText(id("age"), "");
            webDriverOperations.click(id("defaultExpressions"));
        }

        // リクエスト処理メソッドの応答に対応する画面(user.ageが存在しない場合)
        {
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "no age specified"));
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
     * <li>モデルに格納された値により条件分岐して表示切替ができること。（th:if形式）</li>
     * </ul>
     */
    @Test
    public void testAPLY1005001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1005001_" + VIEW_TYPE));
        }

        // モデルに含まれる値を入力(th:ifがtrueの場合)
        {
            webDriverOperations.appendText(id("conditions"), "complete");
            webDriverOperations.click(id("thIfFormat"));
        }

        // リクエスト処理メソッドの応答に対応する画面(th:ifがtrueの場合)
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "「complete」と入力された場合の分岐"));
        }

        // モデルに含まれる値を再度入力(th:ifがfalseの場合)
        {
            webDriverOperations.back();
            webDriverOperations.overrideText(id("conditions"), "incomplete");
            webDriverOperations.click(id("thIfFormat"));
        }

        // リクエスト処理メソッドの応答に対応する画面(<c:if>がfalseの場合)
        {
            // メッセージが存在しないことを確認
            assertThat(webDriverOperations.getText(id("message1")), not(
                    "「complete」と入力された場合の分岐"));
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
     * <li>モデルに格納された値により条件分岐して表示切替ができること。（th:switch形式）</li>
     * </ul>
     */
    @Test
    public void testAPLY1005002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1005002_" + VIEW_TYPE));
        }

        // モデルに含まれる値を入力(th:case="gold"の場合)
        {
            webDriverOperations.appendText(id("conditions"), "gold");
            webDriverOperations.click(id("thSwitchFormat"));
        }

        // リクエスト処理メソッドの応答に対応する画面(th:case="gold"の場合)
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "「gold」と入力された場合の分岐"));
        }

        // モデルに含まれる値を再度入力(th:case="silver"の場合)
        {
            webDriverOperations.back();
            webDriverOperations.overrideText(id("conditions"), "silver");
            webDriverOperations.click(id("thSwitchFormat"));
        }

        // リクエスト処理メソッドの応答に対応する画面(th:case="silver"の場合)
        {
            // メッセージが存在しないことを確認
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "「silver」と入力された場合の分岐"));
        }

        // モデルに含まれる値を再度入力(th:case="*"の場合)
        {
            webDriverOperations.back();
            webDriverOperations.overrideText(id("conditions"), "bronze");
            webDriverOperations.click(id("thSwitchFormat"));
        }

        // リクエスト処理メソッドの応答に対応する画面(th:case="*"の場合)
        {
            // メッセージが存在しないことを確認
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "「gold」と「silver」以外の入力の場合の分岐"));
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
     * <li>モデルに格納されたコレクション型の値を画面表示表示できること。(statusなし)</li>
     * </ul>
     */
    @Test
    public void testAPLY1006001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1006001_" + VIEW_TYPE));
        }

        // モデルに含まれるコレクション型の表示を設定する。（th:each>形式）
        {
            webDriverOperations.appendText(id("thymeleafFormList0.userName"),
                    "山田太郎");
            webDriverOperations.appendText(id("thymeleafFormList0.age"), "60");
            webDriverOperations.appendText(id("thymeleafFormList0.conditions"),
                    "old");
            webDriverOperations.appendText(id("thymeleafFormList1.userName"),
                    "佐藤次郎");
            webDriverOperations.appendText(id("thymeleafFormList1.age"), "30");
            webDriverOperations.appendText(id("thymeleafFormList1.conditions"),
                    "middle");
            webDriverOperations.appendText(id("thymeleafFormList2.userName"),
                    "田中花子");
            webDriverOperations.appendText(id("thymeleafFormList2.age"), "15");
            webDriverOperations.appendText(id("thymeleafFormList2.conditions"),
                    "young");
            webDriverOperations.click(id("collectionInModel"));
        }

        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(By.xpath(
                    "//table/tbody/tr[2]/td[1]")), is("山田太郎"));
            assertThat(webDriverOperations.getText(By.xpath(
                    "//table/tbody/tr[2]/td[2]")), is("60"));
            assertThat(webDriverOperations.getText(By.xpath(
                    "//table/tbody/tr[2]/td[3]")), is("old"));
            assertThat(webDriverOperations.getText(By.xpath(
                    "//table/tbody/tr[3]/td[1]")), is("佐藤次郎"));
            assertThat(webDriverOperations.getText(By.xpath(
                    "//table/tbody/tr[3]/td[2]")), is("30"));
            assertThat(webDriverOperations.getText(By.xpath(
                    "//table/tbody/tr[3]/td[3]")), is("middle"));
            assertThat(webDriverOperations.getText(By.xpath(
                    "//table/tbody/tr[4]/td[1]")), is("田中花子"));
            assertThat(webDriverOperations.getText(By.xpath(
                    "//table/tbody/tr[4]/td[2]")), is("15"));
            assertThat(webDriverOperations.getText(By.xpath(
                    "//table/tbody/tr[4]/td[3]")), is("young"));
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
     * <li>モデルに格納されたコレクション型の値を画面表示表示できること。(statusあり)</li>
     * </ul>
     */
    @Test
    public void testAPLY1006002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1006002_" + VIEW_TYPE));
        }

        // モデルに含まれるコレクション型の表示を設定する。（th:each>形式）
        {
            webDriverOperations.appendText(id("thymeleafFormList0.userName"),
                    "山田太郎");
            webDriverOperations.appendText(id("thymeleafFormList0.age"), "60");
            webDriverOperations.appendText(id("thymeleafFormList0.conditions"),
                    "old");
            webDriverOperations.appendText(id("thymeleafFormList1.userName"),
                    "佐藤次郎");
            webDriverOperations.appendText(id("thymeleafFormList1.age"), "30");
            webDriverOperations.appendText(id("thymeleafFormList1.conditions"),
                    "middle");
            webDriverOperations.appendText(id("thymeleafFormList2.userName"),
                    "田中花子");
            webDriverOperations.appendText(id("thymeleafFormList2.age"), "15");
            webDriverOperations.appendText(id("thymeleafFormList2.conditions"),
                    "young");
            webDriverOperations.click(id("collectionInModelUsingStatus"));
        }

        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(By.xpath(
                    "//table/tbody/tr[2]/td[1]")), is("1"));
            assertThat(webDriverOperations.getText(By.xpath(
                    "//table/tbody/tr[2]/td[2]")), is("山田太郎"));
            assertThat(webDriverOperations.getText(By.xpath(
                    "//table/tbody/tr[2]/td[3]")), is("60"));
            assertThat(webDriverOperations.getText(By.xpath(
                    "//table/tbody/tr[2]/td[4]")), is("old"));
            assertThat(webDriverOperations.getText(By.xpath(
                    "//table/tbody/tr[3]/td[1]")), is("2"));
            assertThat(webDriverOperations.getText(By.xpath(
                    "//table/tbody/tr[3]/td[2]")), is("佐藤次郎"));
            assertThat(webDriverOperations.getText(By.xpath(
                    "//table/tbody/tr[3]/td[3]")), is("30"));
            assertThat(webDriverOperations.getText(By.xpath(
                    "//table/tbody/tr[3]/td[4]")), is("middle"));
            assertThat(webDriverOperations.getText(By.xpath(
                    "//table/tbody/tr[4]/td[1]")), is("3"));
            assertThat(webDriverOperations.getText(By.xpath(
                    "//table/tbody/tr[4]/td[2]")), is("田中花子"));
            assertThat(webDriverOperations.getText(By.xpath(
                    "//table/tbody/tr[4]/td[3]")), is("15"));
            assertThat(webDriverOperations.getText(By.xpath(
                    "//table/tbody/tr[4]/td[4]")), is("young"));
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
     * <li>オブジェクト名を省略してプロパティを指定できること。（th:object形式）</li>
     * </ul>
     */
    @Test
    public void testAPLY1007001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1007001_" + VIEW_TYPE));
        }

        // モデルに含まれる値を入力
        {
            webDriverOperations.appendText(id("userName"), "山田太郎");
            webDriverOperations.click(id("thObject"));
        }

        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message2")), is("山田太郎"));
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
     * <li>ローカル変数を定義できること。（th:with形式）</li>
     * </ul>
     */
    @Test
    public void testAPLY1007002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1007002_" + VIEW_TYPE));
        }

        // モデルに含まれる値を入力
        {
            webDriverOperations.appendText(id("userName"), "山田太郎");
            webDriverOperations.click(id("thWith"));
        }

        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "Hello, 山田太郎"));
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
     * <li>フォームオブジェクトのプロパティをHTMLフォームへバインドできること。</li>
     * </ul>
     */
    @Test
    public void testAPLY1008001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1008001_" + VIEW_TYPE));
        }

        // JSPでモデルに含まれるフォームオブジェクトの表示を設定する。
        {
            webDriverOperations.appendText(id("userName"), "佐藤太郎");
            webDriverOperations.appendText(id("age"), "40");
            webDriverOperations.appendText(id("conditions"), "platinum");
            webDriverOperations.click(id("bindFormObject"));
        }

        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("getUserName")), is(
                    "佐藤太郎"));
            assertThat(webDriverOperations.getText(id("getAge")), is("40"));
            assertThat(webDriverOperations.getText(id("getConditions")), is(
                    "platinum"));
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
     * <li>モデルに格納された数値を指定した書式で表示できること。（#aggregates.sumメソッド）</li>
     * </ul>
     */
    @Test
    public void testAPLY1009001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1009001_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#aggregates.sumを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is("1368"));
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
     * <li>モデルに格納された数値を指定した書式で表示できること。（#aggregates.avgメソッド）</li>
     * </ul>
     */
    @Test
    public void testAPLY1009002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply1009002_" + VIEW_TYPE));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "Thymeleafが提供している#aggregates.avgを利用して数値を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is("456"));
        }
        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }
    }
}
