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
package jp.co.ntt.fw.spring.functionaltest.selenium.aply;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.id;

import java.io.IOException;
import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ApplicationLayerTest extends FunctionTestSupport {

    private static WebDriver driver;

    public ApplicationLayerTest() {
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
            webDriverOperations.click(id("aply0101"));
        }
        // Controllerの実装方法画面の操作
        {
            webDriverOperations.click(id("aply0101001"));
            webDriverOperations.click(id("aply0101001hello"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@RequestMapping(value = \"0101/001/hello\")」のメソッドが実行されました。"));
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
            webDriverOperations.click(id("aply0101001nihao"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@RequestMapping(value = {\"0101/001/nihao\", \"0101/001/bonjour\"})」のメソッドが実行されました。"));
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
            webDriverOperations.click(id("aply0101001bonjour"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@RequestMapping(value = {\"0101/001/nihao\", \"0101/001/bonjour\"})」のメソッドが実行されました。"));
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
            webDriverOperations.click(id("aply0101"));
        }
        // Controllerの実装方法画面の操作
        {
            webDriverOperations.click(id("aply0101002"));
            webDriverOperations.click(id("aply0101002_1"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@RequestMapping(value = \"0101/002_1\", method = RequestMethod.POST)」のメソッドが実行されました。"));
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
            webDriverOperations.click(id("aply0101002_2post"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@RequestMapping(value = \"0101/002_2\", method = {RequestMethod.GET, RequestMethod.POST})」のメソッドが実行されました。"));
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
            webDriverOperations.click(id("aply0101002_2get"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@RequestMapping(value = \"0101/002_2\", method = {RequestMethod.GET, RequestMethod.POST})」のメソッドが実行されました。"));
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
            webDriverOperations.click(id("aply0101"));
        }
        // Controllerの実装方法画面の操作
        {
            webDriverOperations.click(id("aply0101003"));
            webDriverOperations.click(id("aply0101003_1"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@RequestMapping(value = \"0101/003\", params = \"form\")」のメソッドが実行されました。"));
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
                    "「@RequestMapping(value = \"0101/003\", params = {\"form\", \"formType=foo\"})」のメソッドが実行されました。"));
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
            webDriverOperations.click(id("aply0101"));
        }
        // Controllerの実装方法画面の操作
        {
            webDriverOperations.click(id("aply0101004"));
            webDriverOperations.click(id("aply0101004_1"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@RequestMapping(value = \"0101/004\", params = \"!form\")」のメソッドが実行されました。"));
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
                    "「@RequestMapping(value = \"0101/004\", params = {\"form\", \"formType!=foo\"})」のメソッドが実行されました。"));
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
            webDriverOperations.click(id("aply0101"));
        }
        // Controllerの実装方法画面の操作
        {
            webDriverOperations.click(id("aply0102001"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@RequestMapping(value = \"0102/001/{id}/{version}\")」のメソッドが実行されました。(id=id1,version=1)"));
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
            webDriverOperations.click(id("aply0101"));
        }
        // Controllerの実装方法画面の操作
        {
            webDriverOperations.click(id("aply0102002"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@RequestMapping(value = \"0102/002/{id}/{version}\")」のメソッドが実行されました。(id=id2,version=2)"));
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
            webDriverOperations.click(id("aply0101"));
        }
        // Controllerの実装方法画面の操作
        {
            webDriverOperations.click(id("aply0102003"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@RequestMapping(value = \"0102/003\")」のメソッドが実行されました。(id=12,name=taro,age=null,genderCode=unknown)"));
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
            webDriverOperations.click(id("aply0101"));
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
            webDriverOperations.click(id("aply0101"));
        }
        // Controllerの実装方法画面の操作
        {
            webDriverOperations.click(id("aply0102005"));
        }

        // Controllerの実装方法画面の確認
        {
            // URLがリダイレクト先のものになっていること。
            assertTrue(webDriverOperations.getCurrentUrl().endsWith(
                    "aply/0102/005?complete"));
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@RequestMapping(value = \"0102/005complete\")」のメソッドが実行されました。"));
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
            webDriverOperations.click(id("aply0101"));
        }
        // Controllerの実装方法画面の操作
        {
            webDriverOperations.click(id("aply0102006"));
        }

        // Controllerの実装方法画面の確認
        {
            // URLがリダイレクト先のものになっていること。
            assertTrue(webDriverOperations.getCurrentUrl().endsWith(
                    "aply/0102/006?complete&id=redirect_id"));
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@RequestMapping(value = \"0102/006complete\")」のメソッドが実行されました。(id=redirect_id)"));
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
            webDriverOperations.click(id("aply0101"));
        }
        // Controllerの実装方法画面の操作
        {
            webDriverOperations.click(id("aply0102007"));
        }

        // Controllerの実装方法画面の確認
        {
            assertTrue(webDriverOperations.getText(id("messages")).startsWith(
                    "「@RequestMapping(value = \"0102/007\")」のメソッドが実行されました。(cookies[JSESSIONID]="));
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
            webDriverOperations.click(id("aply0101"));
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
                    "「@RequestMapping(value = \"0102/008\")」のメソッドが実行されました。(cookies[hello]=helloworld!)"));
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
            webDriverOperations.click(id("aply0101"));
        }
        // Controllerの実装方法画面の操作
        {
            webDriverOperations.click(id("aply0102009"));
        }

        // Controllerの実装方法画面の確認
        {
            assertTrue(webDriverOperations.getText(id("messages")).startsWith(
                    "「@RequestMapping(value = \"0102/009\")」のメソッドが実行されました。(commonParam1=111, commonParam2=222)"));
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
            webDriverOperations.click(id("aply0101"));
        }
        // Controllerの実装方法画面の操作
        {
            webDriverOperations.click(id("aply0102010"));
        }

        // Controllerの実装方法画面の確認
        {
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "「@RequestMapping(value = \"0102/010\")」のメソッドが実行されました。(commonParam1=123, commonParam2=456)"));
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
            webDriverOperations.click(id("aply0201001"));
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
            webDriverOperations.click(id("aply0201002"));
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

        // // メニュー画面の操作
        // {
        // webDriverOperations.click(id("aply0201003"));
        // }
        // // フォームオブジェクトの実装 入力画面の操作
        // {
        // assertThat(webDriverOperations.getWebDriver().findElement(
        // id("value3")).getAttribute("value"), is("$3,456,789.00"));
        //
        // webDriverOperations.click(id("submit"));
        // }
        //
        // // Controllerの実装方法画面の確認
        // {
        // assertThat(webDriverOperations.getText(id("value3")), is("3456789"));
        // }
        // // ログの確認
        // {
        // dbLogAssertOperations.waitForAssertion();
        // dbLogAssertOperations.assertNotContainsWarnAndError();
        // }
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
            webDriverOperations.click(id("aply0201004"));
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
            webDriverOperations.click(id("aply0201005"));
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
            webDriverOperations.click(id("aply0201006"));
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
            webDriverOperations.click(id("aply0201007"));
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
            webDriverOperations.click(id("aply0202001"));
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
            webDriverOperations.click(id("aply0202002"));
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
            webDriverOperations.click(id("aply0202002"));
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
            webDriverOperations.click(id("aply0203001"));
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
            webDriverOperations.click(id("aply0203002"));
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
            webDriverOperations.click(id("aply0203003"));
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
            webDriverOperations.click(id("aply0203004"));
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
                    "may not be null"));
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
            webDriverOperations.click(id("aply0203005"));
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
            webDriverOperations.click(id("aply0203006"));
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
            webDriverOperations.click(id("aply0203007"));
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
            webDriverOperations.click(id("aply0203008"));
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
                    "may not be null"));
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
            webDriverOperations.click(id("aply0203007"));
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
            webDriverOperations.click(id("aply0203008"));
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
                    "may not be null"));
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
     * <li>共通JSPを設定できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testAPLY0301001() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0301001"));
        }
        // 共通JSPをインクルードした画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "共通JSPで宣言しているタグライブラリを利用してエスケープ処理した文字列を以下に表示します。"));
            assertThat(webDriverOperations.getText(id("message2")), is(
                    "<input name='test'>"));
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
     * <li>モデルに格納された値を表示できること。（$f:h()形式）</li>
     * </ul>
     */
    @Test
    public void testAPLY0302001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0302001"));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "共通部品で提供しているEL式用のHTMLエスケープ関数を利用して文字列を以下に表示します。"));
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
     * <li>モデルに格納された値を表示できること。（<c:out value=${}>形式）</li>
     * </ul>
     */
    @Test
    public void testAPLY0302002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0302002"));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "JSTLのJSPタグライブラリから提供しているHTMLエスケープ関数を利用して文字列を以下に表示します。"));
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
     * <li>モデルに格納された数値を指定した書式で表示できること。（<fmt:formatNumber value="${}" pattern="">形式）</li>
     * </ul>
     */
    @Test
    public void testAPLY0302003() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0302003"));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "JSTLのJSPタグライブラリから提供している数値フォーマットを利用して数値を以下に表示します。"));
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
     * <li>モデルに格納された日時を指定した書式で表示できること。（fmt:formatDate value="${}" pattern="">形式）</li>
     * </ul>
     */
    @Test
    public void testAPLY0302004() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0302004"));
        }
        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "JSTLのJSPタグライブラリから提供している日付フォーマットを利用して数値を以下に表示します。"));
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
     * <li>モデルに格納された値により条件分岐して表示切替ができること。（c:if形式）</li>
     * </ul>
     */
    @Test
    public void testAPLY0303001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0303001"));
        }

        // JSPでモデルに含まれる値を入力(<c:if>がtrueの場合)
        {
            webDriverOperations.appendText(id("conditions"), "complete");
            webDriverOperations.click(id("cIfFormat"));
        }

        // リクエスト処理メソッドの応答に対応する画面(<c:if>がtrueの場合)
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "「complete」と入力された場合の分岐"));
        }

        // JSPでモデルに含まれる値を再度入力(<c:if>がfalseの場合)
        {
            webDriverOperations.back();
            webDriverOperations.overrideText(id("conditions"), "incomplete");
            webDriverOperations.click(id("cIfFormat"));
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
     * <li>モデルに格納された値により条件分岐して表示切替ができること。（c:when/c:otherwise形式）</li>
     * </ul>
     */
    @Test
    public void testAPLY0303002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0303002"));
        }

        // JSPでモデルに含まれる値を入力(<c:choose>が"gold"の場合)
        {
            webDriverOperations.appendText(id("conditions"), "gold");
            webDriverOperations.click(id("cChooseFormat"));
        }

        // リクエスト処理メソッドの応答に対応する画面(<c:choose>が"gold"の場合)
        {
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "「gold」と入力された場合の分岐"));
        }

        // JSPでモデルに含まれる値を再度入力(<c:choose>が"silver"の場合)
        {
            webDriverOperations.back();
            webDriverOperations.overrideText(id("conditions"), "silver");
            webDriverOperations.click(id("cChooseFormat"));
        }

        // リクエスト処理メソッドの応答に対応する画面(<c:choose>が"silver"の場合)
        {
            // メッセージが存在しないことを確認
            assertThat(webDriverOperations.getText(id("message1")), is(
                    "「silver」と入力された場合の分岐"));
        }

        // JSPでモデルに含まれる値を再度入力(<c:otherwise>の場合)
        {
            webDriverOperations.back();
            webDriverOperations.overrideText(id("conditions"), "bronze");
            webDriverOperations.click(id("cChooseFormat"));
        }

        // リクエスト処理メソッドの応答に対応する画面(<c:otherwise>の場合)
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
     * <li>モデルに格納されたコレクション型の値を画面表示表示できること。</li>
     * </ul>
     */
    @Test
    public void testAPLY0304001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0304001"));
        }

        // JSPでモデルに含まれるコレクション型の表示を設定する。（<c:forEach>形式）
        {
            webDriverOperations.appendText(id("jspFormList0.firstName"), "山田");
            webDriverOperations.appendText(id("jspFormList0.lastName"), "太郎");
            webDriverOperations.appendText(id("jspFormList1.firstName"), "佐藤");
            webDriverOperations.appendText(id("jspFormList1.lastName"), "次郎");
            webDriverOperations.appendText(id("jspFormList2.firstName"), "田中");
            webDriverOperations.appendText(id("jspFormList2.lastName"), "花子");
            webDriverOperations.click(id("collectionInModel"));
        }

        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("getFirstName1")), is(
                    "山田"));
            assertThat(webDriverOperations.getText(id("getLastName1")), is(
                    "太郎"));
            assertThat(webDriverOperations.getText(id("getFirstName2")), is(
                    "佐藤"));
            assertThat(webDriverOperations.getText(id("getLastName2")), is(
                    "次郎"));
            assertThat(webDriverOperations.getText(id("getFirstName3")), is(
                    "田中"));
            assertThat(webDriverOperations.getText(id("getLastName3")), is(
                    "花子"));
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
     * <li>フォームオブジェクトからHTMLフォームへバインドできること。</li>
     * </ul>
     */
    @Test
    public void testAPLY0305001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0305001"));
        }

        // JSPでモデルに含まれるフォームオブジェクトの表示を設定する。
        {
            webDriverOperations.appendText(id("firstName"), "佐藤");
            webDriverOperations.appendText(id("lastName"), "太郎");
            webDriverOperations.click(id("bindFormObject"));
        }

        // リクエスト処理メソッドの応答に対応する画面
        {
            assertThat(webDriverOperations.getText(id("getFirstName")), is(
                    "佐藤"));
            assertThat(webDriverOperations.getText(id("getLastName")), is(
                    "太郎"));
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
            webDriverOperations.click(id("aply0401001"));
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
            webDriverOperations.click(id("aply0401002"));
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
            webDriverOperations.click(id("aply0401003"));
        }

        // ログが出力されていること
        List<String> list;
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
            list = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*APLY04CommonController.*", "\\[APLY0403003.*");

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
     * <li>リクエスト処理メソッドの共通処理を実行順序を指定して複数設定できること。<br>
     * (@ControllerAdvice(annotations の確認)</li>
     * </ul>
     */
    @Test
    public void testAPLY0401004() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0401004"));
        }

        // エラー画面に遷移（例外処理を実施している為）
        {
            assertThat(webDriverOperations.getText(By.xpath(
                    "//div[2]/div/ul/li")), is(
                            "[e.sf.cmmn.8003] エラーにより、異常終了しました。"));
        }

        // @RestController がついていないControllerの呼び出し
        // ログが出力されていないこと
        List<String> list;
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
            list = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*ApiGlobalExceptionHandler.*", "\\[APLY0401004.*");

            // 順番通りに出力されていること。
            assertThat(list.size(), is(0));
        }

        // @RestController がついているControllerの呼び出し
        String urlStr = webDriverOperations.getCurrentUrl();
        webDriverOperations.getWebDriver().get(urlStr);
        webDriverOperations.displayPage(urlStr + "/02");

        // エラー画面に遷移（例外処理を実施している為）
        {
            assertThat(webDriverOperations.getText(By.xpath(
                    "//div[2]/div/ul/li")), is(
                            "[e.sf.cmmn.8003] エラーにより、異常終了しました。"));
        }

        // ログが出力されていること
        List<String> list2;
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
            list2 = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*LoginFormModelAttributeSetter.*", "\\[APLY0401004.*");

            // 順番通りに出力されていること。
            assertThat(list2.size(), is(2));
            assertThat(list2.get(0).toString(), is(
                    "[APLY0401004]LoginFormModelAttributeSetter initBinder Method Called"));
            assertThat(list2.get(1).toString(), is(
                    "[APLY0401004]LoginFormModelAttributeSetter setUpCommonParameters01 Method Called"));
        }

    }

    /**
     * <ul>
     * <li>リクエスト処理メソッドの共通処理を実行順序を指定して複数設定できること。<br>
     * (@ControllerAdvice(assignableTypes の確認)</li>
     * </ul>
     */
    @Test
    public void testAPLY0401005() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0401005"));
        }

        // エラー画面に遷移（例外処理を実施している為）
        {
            assertThat(webDriverOperations.getText(By.xpath(
                    "//div[2]/div/ul/li")), is(
                            "[e.sf.cmmn.8003] エラーにより、異常終了しました。"));
        }

        // assignableTypes で指定していないControllerの呼び出し
        // ログが出力されていないこと
        List<String> list;
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
            list = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(), ".*ISODateApplicable.*",
                    "\\[APLY0401005.*");

            // 順番通りに出力されていること。
            assertThat(list.size(), is(0));
        }

        // assignableTypes で指定したControllerの呼び出し
        String urlStr = webDriverOperations.getCurrentUrl();
        webDriverOperations.getWebDriver().get(urlStr);
        webDriverOperations.displayPage(urlStr + "/02");

        // エラー画面に遷移（例外処理を実施している為）
        {
            assertThat(webDriverOperations.getText(By.xpath(
                    "//div[2]/div/ul/li")), is(
                            "[e.sf.cmmn.8003] エラーにより、異常終了しました。"));
        }

        // ログが出力されていること
        List<String> list2;
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
            list2 = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(), ".*ISODateInitBinder.*",
                    "\\[APLY0401005.*");

            // 順番通りに出力されていること。
            assertThat(list2.size(), is(2));
            assertThat(list2.get(0).toString(), is(
                    "[APLY0401005]ISODateInitBinder initBinder Method Called"));
            assertThat(list2.get(1).toString(), is(
                    "[APLY0401005]ISODateInitBinder setUpCommonParameters01 Method Called"));
        }

    }

    /**
     * <ul>
     * <li>リクエスト処理メソッドの共通処理を実行順序を指定して複数設定できること。<br>
     * (@ControllerAdvice(basePackageClasses の確認)</li>
     * </ul>
     */
    @Test
    public void testAPLY0401006() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0401006"));
        }

        // エラー画面に遷移（例外処理を実施している為）
        {
            assertThat(webDriverOperations.getText(By.xpath(
                    "//div[2]/div/ul/li")), is(
                            "[e.sf.cmmn.8003] エラーにより、異常終了しました。"));
        }

        // basePackageClasses の一つ上の階層のControllerの呼び出し
        // ログが出力されていないこと
        List<String> list;
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
            list = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*AppGlobalExceptionHandler.*", "\\[APLY0401006.*");

            assertThat(list.size(), is(0));
        }

        // basePackageClasses で指定したControllerの呼び出し
        String urlbase = webDriverOperations.getCurrentUrl();

        webDriverOperations.displayPage(urlbase + "/02");

        // エラー画面に遷移（例外処理を実施している為）
        {
            assertThat(webDriverOperations.getText(By.xpath(
                    "//div[2]/div/ul/li")), is(
                            "[e.sf.cmmn.8003] エラーにより、異常終了しました。"));
        }

        // ログが出力されていること
        List<String> list2;
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
            list2 = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*AppGlobalExceptionHandler.*", "\\[APLY0401006.*");

            assertThat(list2.size(), is(2));
            assertThat(list2.get(0).toString(), is(
                    "[APLY0401006]AppGlobalExceptionHandler initBinder Method Called"));
            assertThat(list2.get(1).toString(), is(
                    "[APLY0401006]AppGlobalExceptionHandler setUpCommonParameters01 Method Called"));
        }

        // assignableTypes で指定したController（同じフォルダ）の呼び出し
        webDriverOperations.displayPage(urlbase + "/03");

        // エラー画面に遷移（例外処理を実施している為）
        {
            assertThat(webDriverOperations.getText(By.xpath(
                    "//div[2]/div/ul/li")), is(
                            "[e.sf.cmmn.8003] エラーにより、異常終了しました。"));
        }

        // ログが出力されていること
        List<String> list3;
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
            list3 = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*AppGlobalExceptionHandler.*", "\\[APLY0401006.*");

            assertThat(list3.size(), is(2));
            assertThat(list3.get(0).toString(), is(
                    "[APLY0401006]AppGlobalExceptionHandler initBinder Method Called"));
            assertThat(list3.get(1).toString(), is(
                    "[APLY0401006]AppGlobalExceptionHandler setUpCommonParameters01 Method Called"));
        }

        // assignableTypes で指定したController（サブフォルダ）の呼び出し
        webDriverOperations.displayPage(urlbase + "/04");

        // エラー画面に遷移（例外処理を実施している為）
        {
            assertThat(webDriverOperations.getText(By.xpath(
                    "//div[2]/div/ul/li")), is(
                            "[e.sf.cmmn.8003] エラーにより、異常終了しました。"));
        }

        // ログが出力されていること
        List<String> list4;
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
            list4 = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*AppGlobalExceptionHandler.*", "\\[APLY0401006.*");

            assertThat(list4.size(), is(2));
            assertThat(list4.get(0).toString(), is(
                    "[APLY0401006]AppGlobalExceptionHandler initBinder Method Called"));
            assertThat(list4.get(1).toString(), is(
                    "[APLY0401006]AppGlobalExceptionHandler setUpCommonParameters01 Method Called"));
        }
    }

    /**
     * <ul>
     * <li>リクエスト処理メソッドの共通処理を実行順序を指定して複数設定できること。<br>
     * (@ControllerAdvice(basePackageClasses の確認)</li>
     * </ul>
     */
    @Test
    public void testAPLY0401007() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0401007"));
        }

        // エラー画面に遷移（例外処理を実施している為）
        {
            assertThat(webDriverOperations.getText(By.xpath(
                    "//div[2]/div/ul/li")), is(
                            "[e.sf.cmmn.8003] エラーにより、異常終了しました。"));
        }

        // basePackageClasses の一つ上の階層のControllerの呼び出し
        // ログが出力されていないこと
        List<String> list;
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
            list = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*AppGlobalExceptionHandler.*", "\\[APLY0401007.*");

            assertThat(list.size(), is(0));
        }

        // basePackageClasses で指定したControllerの呼び出し
        String urlbase = webDriverOperations.getCurrentUrl();

        webDriverOperations.displayPage(urlbase + "/02");

        // エラー画面に遷移（例外処理を実施している為）
        {
            assertThat(webDriverOperations.getText(By.xpath(
                    "//div[2]/div/ul/li")), is(
                            "[e.sf.cmmn.8003] エラーにより、異常終了しました。"));
        }

        // ログが出力されていること
        List<String> list2;
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
            list2 = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*AppGlobalExceptionHandler.*", "\\[APLY0401007.*");

            assertThat(list2.size(), is(2));
            assertThat(list2.get(0).toString(), is(
                    "[APLY0401007]AppGlobalExceptionHandler initBinder Method Called"));
            assertThat(list2.get(1).toString(), is(
                    "[APLY0401007]AppGlobalExceptionHandler setUpCommonParameters01 Method Called"));
        }

        // assignableTypes で指定したController（同じフォルダ）の呼び出し
        webDriverOperations.displayPage(urlbase + "/03");

        // エラー画面に遷移（例外処理を実施している為）
        {
            assertThat(webDriverOperations.getText(By.xpath(
                    "//div[2]/div/ul/li")), is(
                            "[e.sf.cmmn.8003] エラーにより、異常終了しました。"));
        }

        // ログが出力されていること
        List<String> list3;
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
            list3 = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*AppGlobalExceptionHandler.*", "\\[APLY0401007.*");

            assertThat(list3.size(), is(2));
            assertThat(list3.get(0).toString(), is(
                    "[APLY0401007]AppGlobalExceptionHandler initBinder Method Called"));
            assertThat(list3.get(1).toString(), is(
                    "[APLY0401007]AppGlobalExceptionHandler setUpCommonParameters01 Method Called"));
        }

        // assignableTypes で指定したController（サブフォルダ）の呼び出し
        webDriverOperations.displayPage(urlbase + "/04");

        // エラー画面に遷移（例外処理を実施している為）
        {
            assertThat(webDriverOperations.getText(By.xpath(
                    "//div[2]/div/ul/li")), is(
                            "[e.sf.cmmn.8003] エラーにより、異常終了しました。"));
        }

        // ログが出力されていること
        List<String> list4;
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
            list4 = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*AppGlobalExceptionHandler.*", "\\[APLY0401007.*");

            assertThat(list4.size(), is(2));
            assertThat(list4.get(0).toString(), is(
                    "[APLY0401007]AppGlobalExceptionHandler initBinder Method Called"));
            assertThat(list4.get(1).toString(), is(
                    "[APLY0401007]AppGlobalExceptionHandler setUpCommonParameters01 Method Called"));
        }
    }

    /**
     * <ul>
     * <li>リクエスト処理メソッドの共通処理を実行順序を指定して複数設定できること。<br>
     * (@ControllerAdvice(basePackages の確認)</li>
     * </ul>
     */
    @Test
    public void testAPLY0401008() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0401008"));
        }

        // エラー画面に遷移（例外処理を実施している為）
        {
            assertThat(webDriverOperations.getText(By.xpath(
                    "//div[2]/div/ul/li")), is(
                            "[e.sf.cmmn.8003] エラーにより、異常終了しました。"));
        }

        // basePackages の一つ上の階層のControllerの呼び出し
        // ログが出力されていないこと
        List<String> list;
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
            list = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*AppGlobalExceptionHandler.*", "\\[APLY0401008.*");

            assertThat(list.size(), is(0));
        }

        // basePackages で指定したControllerの呼び出し
        String urlbase = webDriverOperations.getCurrentUrl();

        webDriverOperations.displayPage(urlbase + "/02");

        // エラー画面に遷移（例外処理を実施している為）
        {
            assertThat(webDriverOperations.getText(By.xpath(
                    "//div[2]/div/ul/li")), is(
                            "[e.sf.cmmn.8003] エラーにより、異常終了しました。"));
        }

        // ログが出力されていること
        List<String> list2;
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
            list2 = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*AppGlobalExceptionHandler.*", "\\[APLY0401008.*");

            assertThat(list2.size(), is(2));
            assertThat(list2.get(0).toString(), is(
                    "[APLY0401008]AppGlobalExceptionHandler initBinder Method Called"));
            assertThat(list2.get(1).toString(), is(
                    "[APLY0401008]AppGlobalExceptionHandler setUpCommonParameters01 Method Called"));
        }

        // basePackages で指定したControllerの呼び出し
        webDriverOperations.displayPage(urlbase + "/03");

        // エラー画面に遷移（例外処理を実施している為）
        {
            assertThat(webDriverOperations.getText(By.xpath(
                    "//div[2]/div/ul/li")), is(
                            "[e.sf.cmmn.8003] エラーにより、異常終了しました。"));
        }

        // ログが出力されていること
        List<String> list3;
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
            list3 = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*AppGlobalExceptionHandler.*", "\\[APLY0401008.*");

            assertThat(list3.size(), is(2));
            assertThat(list3.get(0).toString(), is(
                    "[APLY0401008]AppGlobalExceptionHandler initBinder Method Called"));
            assertThat(list3.get(1).toString(), is(
                    "[APLY0401008]AppGlobalExceptionHandler setUpCommonParameters01 Method Called"));
        }

        // basePackages で指定したController（サブフォルダ）の呼び出し
        webDriverOperations.displayPage(urlbase + "/04");

        // エラー画面に遷移（例外処理を実施している為）
        {
            assertThat(webDriverOperations.getText(By.xpath(
                    "//div[2]/div/ul/li")), is(
                            "[e.sf.cmmn.8003] エラーにより、異常終了しました。"));
        }

        // ログが出力されていること
        List<String> list4;
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
            list4 = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*AppGlobalExceptionHandler.*", "\\[APLY0401008.*");

            assertThat(list4.size(), is(2));
            assertThat(list4.get(0).toString(), is(
                    "[APLY0401008]AppGlobalExceptionHandler initBinder Method Called"));
            assertThat(list4.get(1).toString(), is(
                    "[APLY0401008]AppGlobalExceptionHandler setUpCommonParameters01 Method Called"));
        }

    }

    /**
     * <ul>
     * <li>リクエスト処理メソッドの共通処理を実行順序を指定して複数設定できること。<br>
     * (@ControllerAdvice(value の確認)</li>
     * </ul>
     */
    @Test
    public void testAPLY0401009() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0401009"));
        }

        // エラー画面に遷移（例外処理を実施している為）
        {
            assertThat(webDriverOperations.getText(By.xpath(
                    "//div[2]/div/ul/li")), is(
                            "[e.sf.cmmn.8003] エラーにより、異常終了しました。"));
        }

        // value の一つ上の階層のControllerの呼び出し
        // ログが出力されていないこと
        List<String> list;
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
            list = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*AppGlobalExceptionHandler.*", "\\[APLY0401009.*");

            assertThat(list.size(), is(0));
        }

        // value で指定したControllerの呼び出し
        String urlbase = webDriverOperations.getCurrentUrl();

        webDriverOperations.displayPage(urlbase + "/02");

        // エラー画面に遷移（例外処理を実施している為）
        {
            assertThat(webDriverOperations.getText(By.xpath(
                    "//div[2]/div/ul/li")), is(
                            "[e.sf.cmmn.8003] エラーにより、異常終了しました。"));
        }

        // ログが出力されていること
        List<String> list2;
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
            list2 = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*AppGlobalExceptionHandler.*", "\\[APLY0401009.*");

            assertThat(list2.size(), is(2));
            assertThat(list2.get(0).toString(), is(
                    "[APLY0401009]AppGlobalExceptionHandler initBinder Method Called"));
            assertThat(list2.get(1).toString(), is(
                    "[APLY0401009]AppGlobalExceptionHandler setUpCommonParameters01 Method Called"));
        }

        // value で指定したControllerの呼び出し
        webDriverOperations.displayPage(urlbase + "/03");

        // エラー画面に遷移（例外処理を実施している為）
        {
            assertThat(webDriverOperations.getText(By.xpath(
                    "//div[2]/div/ul/li")), is(
                            "[e.sf.cmmn.8003] エラーにより、異常終了しました。"));
        }

        // ログが出力されていること
        List<String> list3;
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
            list3 = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*AppGlobalExceptionHandler.*", "\\[APLY0401009.*");

            assertThat(list3.size(), is(2));
            assertThat(list3.get(0).toString(), is(
                    "[APLY0401009]AppGlobalExceptionHandler initBinder Method Called"));
            assertThat(list3.get(1).toString(), is(
                    "[APLY0401009]AppGlobalExceptionHandler setUpCommonParameters01 Method Called"));
        }

        // value で指定したController（サブフォルダ）の呼び出し
        webDriverOperations.displayPage(urlbase + "/04");

        // エラー画面に遷移（例外処理を実施している為）
        {
            assertThat(webDriverOperations.getText(By.xpath(
                    "//div[2]/div/ul/li")), is(
                            "[e.sf.cmmn.8003] エラーにより、異常終了しました。"));
        }

        // ログが出力されていること
        List<String> list4;
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
            list4 = dbLogAssertOperations.getLogByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*AppGlobalExceptionHandler.*", "\\[APLY0401009.*");

            assertThat(list4.size(), is(2));
            assertThat(list4.get(0).toString(), is(
                    "[APLY0401009]AppGlobalExceptionHandler initBinder Method Called"));
            assertThat(list4.get(1).toString(), is(
                    "[APLY0401009]AppGlobalExceptionHandler setUpCommonParameters01 Method Called"));
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
            webDriverOperations.click(id("aply0402001"));
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
     * <li>リクエスト処理メソッドの共通処理をハンドラインタセプタで設定できること。（正常処理）</li>
     * </ul>
     */
    @Test
    public void testAPLY0402002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("aply0402002"));
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
            assertThat(list.size(), is(2));
            assertThat(list.get(0).toString(), is(
                    "[APLY0402_01] preHandle method called"));
            assertThat(list.get(1).toString(), is(
                    "[APLY0402_03] afterCompletion method called"));
        }

        // エラー画面に遷移（例外処理を実施している為）
        {
            assertThat(webDriverOperations.getText(By.xpath(
                    "//div[2]/div/ul/li")), is(
                            "[e.sf.cmmn.8003] エラーにより、異常終了しました。"));
        }
    }
}
