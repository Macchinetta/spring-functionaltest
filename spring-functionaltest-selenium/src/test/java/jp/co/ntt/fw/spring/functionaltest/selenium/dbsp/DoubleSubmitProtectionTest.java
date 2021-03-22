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
package jp.co.ntt.fw.spring.functionaltest.selenium.dbsp;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.id;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;
import jp.co.ntt.fw.spring.functionaltest.selenium.WebDriverOperations;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public class DoubleSubmitProtectionTest extends FunctionTestSupport {

    @Value("${selenium.dbsp.waitForDownload.offsetSeconds:0}")
    private int offsetSecondsOfWaitForDownload;

    @Value("${selenium.app.download.pdf}")
    private String downloadPdfFileName;

    private static Path downloadTempDirectory;

    @Inject
    protected RestTemplate restTemplate;

    @BeforeClass
    public static void setUpClass() throws IOException {
        downloadTempDirectory = Files.createTempDirectory("springtest-dbsp-")
                .toAbsolutePath();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        deleteFilesRecursively(downloadTempDirectory);
    }

    /**
     * <ul>
     * <li>PRGパターンが正常に適用できていることを確認する。
     * </ul>
     **/
    @Test
    public void testDBSP0201001() throws IOException {

        webDriverOperations.click(id("dbsp0201001"));

        // 登録画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "ユーザ登録画面"));

        webDriverOperations.appendText(id("firstName"), "Taro");
        webDriverOperations.appendText(id("lastName"), "Yamada");
        webDriverOperations.appendText(id("filePath"), downloadTempDirectory
                .toString());

        webDriverOperations.click(id("confirm"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "ユーザ登録確認画面"));

        webDriverOperations.click(id("create"));

        // 登録完了画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "ユーザ登録完了画面"));
        // ${f:h(output)}の確認
        assertThat(webDriverOperations.getText(id("output")), is(
                "result register..."));
        // redirect がURLに含まれていること
        assertThat(webDriverOperations.getCurrentUrl(), is(containsString(
                "?complete")));

        // テスト用ファイルを削除
        File file = downloadTempDirectory.resolve("userData.txt").toFile();
        file.delete();
    }

    /**
     * <ul>
     * <li>完了画面でブラウザのリロード機能を実行しても、更新処理が再実行されないことを確認する。
     * </ul>
     **/
    @Test
    public void testDBSP0201002() throws IOException {

        webDriverOperations.click(id("dbsp0201001"));

        // 登録画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "ユーザ登録画面"));

        webDriverOperations.appendText(id("firstName"), "Taro");
        webDriverOperations.appendText(id("lastName"), "Yamada");
        webDriverOperations.appendText(id("filePath"), downloadTempDirectory
                .toString());

        webDriverOperations.click(id("confirm"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "ユーザ登録確認画面"));

        webDriverOperations.click(id("create"));

        // 登録完了画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "ユーザ登録完了画面"));
        // ${f:h(output)}の確認
        assertThat(webDriverOperations.getText(id("output")), is(
                "result register..."));
        // ?completeがURLに含まれていること
        assertThat(webDriverOperations.getCurrentUrl(), is(containsString(
                "?complete")));

        // ファイルを削除
        File file = downloadTempDirectory.resolve("userData.txt").toFile();
        file.delete();

        // ブラウザの再読み込み
        webDriverOperations.refresh();
        // ファイル書き込み処理が再実行されていないこと
        assertFalse(file.exists());

    }

    /**
     * <ul>
     * <li>トランザクショントークンチェックを実装し、正規の画面遷移が実行できることを確認する。
     * </ul>
     **/
    @Test
    public void testDBSP0301001() throws IOException {
        /** トランザクショントークンタグのvalue値 */
        String valueText = null;
        /** 生成されたTokenKey */
        String tokenKey = null;
        /** 生成されたTokenValue */
        String tokenValue = null;
        String[] buttonNames = { "dbsp0301001", "second", "third", "fourth",
                "fifth" };
        String[] screenTitles = { "firstView", "secondView", "thirdView",
                "fourthView", "fifthView" };
        for (int i = 0; i < 5; i++) {
            if (i >= 2) {
                // 第二画面から第四画面ではトランザクショントークンを確認する。
                // トランザクショントークンの確認
                valueText = (String) webDriverOperations.getJavascriptExecutor()
                        .executeScript(
                                "return document.getElementsByName('_TRANSACTION_TOKEN')[0].value;");
                assertNotNull(valueText);
                if (i == 2) {
                    // 第二画面の場合はTokenKey,TokenValueの存在とNameSpaceの値を確認する。
                    assertThat(getNameSpace(valueText), is("create"));
                    tokenKey = getTokenKey(valueText);
                    assertNotNull(tokenKey);
                    tokenValue = getTokenValue(valueText);
                    assertNotNull(tokenValue);
                } else {
                    // 第三、第四画面では、前の画面のTokenKeyが引き継がれていること、TokenValueが変更されていることも確認する。
                    checkToken(valueText, "create", tokenKey, tokenValue);
                    tokenKey = getTokenKey(valueText);
                    tokenValue = getTokenValue(valueText);
                }
            }
            // 次の画面へ遷移
            webDriverOperations.click(id(buttonNames[i]));
            // 各画面へ遷移したことをチェック
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    screenTitles[i]));
        }
        // 第5画面の場合はトランザクショントークンがないことを確認
        assertNull(webDriverOperations.getJavascriptExecutor().executeScript(
                "return document.getElementsByName('_TRANSACTION_TOKEN')[0];"));
    }

    /**
     * <ul>
     * <li>トランザクショントークンチェックを実装することで、不正な画面遷移を防止できることを確認する。
     * </ul>
     **/
    @Test
    public void testDBSP0301002() throws IOException {
        webDriverOperations.click(id("dbsp0301001"));
        webDriverOperations.click(id("second"));
        webDriverOperations.click(id("third"));
        webDriverOperations.click(id("fourth"));
        // 第四画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "fourthView"));

        webDriverOperations.getWebDriver().navigate().back();
        // エラーの確認
        {
            // エラー画面表示まで待機
            String expectedTitle = "ページ読み込みエラー";
            webDriverOperations.waitForDisplayed(ExpectedConditions
                    .titleContains(expectedTitle));

            assertThat(webDriverOperations.getTitle(), is(expectedTitle));
        }
    }

    /**
     * <ul>
     * <li>トランザクショントークンチェックを実装することで、正規の画面遷移を伴わない不正なリクエストによってデータが更新できないことを確認する。
     * </ul>
     **/
    @Test
    public void testDBSP0301003() throws IOException {
        webDriverOperations.click(id("dbsp0301003"));
        webDriverOperations.click(id("fourth"));
        // エラーの確認
        {
            // エラー画面表示まで待機
            String expectedTitle = "Transaction Token Error!";
            webDriverOperations.waitForDisplayed(ExpectedConditions
                    .titleContains(expectedTitle));

            assertThat(webDriverOperations.getTitle(), is(expectedTitle));
        }
    }

    /**
     * <ul>
     * <li>ブラウザの再読み込みを行い、二重送信を試みる。トランザクショントークンエラーになることを確認する。
     * </ul>
     **/
    @Test
    public void testDBSP0301004() throws IOException {
        webDriverOperations.click(id("dbsp0301001"));
        webDriverOperations.click(id("second"));
        webDriverOperations.click(id("third"));
        webDriverOperations.click(id("fourth"));
        // 第四画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "fourthView"));

        // ブラウザの再読み込み
        webDriverOperations.refresh();

        // エラーの確認
        {
            // エラー画面表示まで待機
            String expectedTitle = "Transaction Token Error!";
            webDriverOperations.waitForDisplayed(ExpectedConditions
                    .titleContains(expectedTitle));
            assertThat(webDriverOperations.getTitle(), is(expectedTitle));
        }
    }

    /**
     * <ul>
     * <li>クライアントへ画面を返却しない処理を含むユースケースにおいて、トランザクショントークンチェックを実装しても正常に画面遷移が行えることを確認する。
     * </ul>
     **/
    @Test
    public void testDBSP0301005() throws IOException {
        webDriverOperations.click(id("dbsp0301005"));
        // 画面で情報入力
        webDriverOperations.overrideText(By.id("name"), "山田");
        webDriverOperations.overrideText(By.id("address"), "東京");
        webDriverOperations.overrideText(By.id("birthdate"), "19700101");
        // PDFファイルダウンロード
        webDriverOperations.click(By.id("downloadButton"));
        waitForDownloaded();
        // ダウンロードされたファイルを読み込む
        File file = new File(downloadTempDirectory
                .toString(), downloadPdfFileName);
        // ダウンロードファイルを削除
        file.delete();

        webDriverOperations.click(id("fourth"));
        // 第四画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "fourthView"));
    }

    /**
     * <ul>
     * <li><sec:cache-control />の設定を除外することで、トランザクショントークンエラー画面が表示できることを確認する。
     * </ul>
     **/
    @Test
    public void testDBSP0302002() throws IOException {
        webDriverOperations.click(id("dbsp0302002"));
        webDriverOperations.click(id("second"));
        webDriverOperations.click(id("third"));
        webDriverOperations.click(id("fourth"));
        // 第四画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "fourthView"));

        webDriverOperations.getWebDriver().navigate().back();
        // 第三画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "thirdView"));

        // ブラウザの再読み込み
        webDriverOperations.refresh();

        // エラーの確認
        {
            // エラー画面表示まで待機
            String expectedTitle = "Transaction Token Error!";
            webDriverOperations.waitForDisplayed(ExpectedConditions
                    .titleContains(expectedTitle));
            assertThat(webDriverOperations.getTitle(), is(expectedTitle));
        }

    }

    /**
     * <ul>
     * <li>NameSpaceが設定されていない時はグローバルトークン用のNameSpaceが自動で設定されていることを確認する。
     * </ul>
     **/
    @Test
    public void testDBSP0303001() throws IOException {
        /** トランザクショントークンタグのvalue値 */
        String valueText = null;
        /** 生成されたTokenKey */
        String tokenKey = null;
        /** 生成されたTokenValue */
        String tokenValue = null;
        String[] buttonNames = { "dbsp0302002", "second", "third", "fourth",
                "fifth" };
        String[] screenTitles = { "firstView", "secondView", "thirdView",
                "fourthView", "fifthView" };

        for (int i = 0; i < 5; i++) {
            if (i >= 2) {
                // 第二画面から第四画面ではトランザクショントークンを確認する。
                // トランザクショントークンの確認
                valueText = (String) webDriverOperations.getJavascriptExecutor()
                        .executeScript(
                                "return document.getElementsByName('_TRANSACTION_TOKEN')[0].value;");
                assertNotNull(valueText);

                if (i == 2) {
                    // 第二画面の場合はTokenKey,TokenValueの存在とNameSpaceの値を確認する。
                    assertThat(getNameSpace(valueText), is("globalToken"));
                    tokenKey = getTokenKey(valueText);
                    assertNotNull(tokenKey);
                    tokenValue = getTokenValue(valueText);
                    assertNotNull(tokenValue);
                } else {
                    // 第三、第四画面では、前の画面のTokenKeyが引き継がれていること、TokenValueが変更されていることも確認する。
                    checkToken(valueText, "globalToken", tokenKey, tokenValue);
                    tokenKey = getTokenKey(valueText);
                    tokenValue = getTokenValue(valueText);
                }
            }
            // 各画面から次の画面へ遷移
            webDriverOperations.click(id(buttonNames[i]));
            // 各画面へ遷移したことをチェック
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    screenTitles[i]));
        }
    }

    /**
     * <ul>
     * <li>トランザクショントークンを削除する際は、実行された日時が最も古いものから順に削除することを確認する。
     * </ul>
     **/
    @Test
    public void testDBSP0303002() throws IOException {
        WebDriverOperations[] browsers = new WebDriverOperations[11];
        browsers[0] = webDriverOperations;

        for (int i = 1; i < 11; i++) {
            // 同一セッションで新しいブラウザを立ち上げる
            browsers[i] = newWebDriverOperations();
            browsers[i].getWebDriver().manage().addCookie(browsers[0].getCookie(
                    "JSESSIONID"));
        }
        String[] buttonNames = { "dbsp0302002", "second", "third" };
        String[] screenTitles = { "firstView", "secondView", "thirdView" };

        for (WebDriverOperations webDriverOperations : browsers) {
            for (int i = 0; i < 3; i++) {
                // 画面遷移
                webDriverOperations.click(id(buttonNames[i]));
                // 各画面へ遷移したことをチェック
                assertThat(webDriverOperations.getText(id("screenTitle")), is(
                        screenTitles[i]));
            }
        }
        browsers[0].click(id("fourth"));
        // 一番目のブラウザは第四画面に遷移できず、トランザクショントークンエラーとなることをチェック
        // エラーの確認
        {
            // エラー画面表示まで待機
            String expectedTitle = "Transaction Token Error!";
            browsers[0].waitForDisplayed(ExpectedConditions.titleContains(
                    expectedTitle));
            assertThat(browsers[0].getTitle(), is(expectedTitle));
        }
        // 二番目以降のブラウザは正常に次の画面へ遷移できることを確認
        for (int i = 1; i < 11; i++) {
            // 画面遷移
            browsers[i].click(id("fourth"));
            // 各画面へ遷移したことをチェック
            assertThat(browsers[i].getText(id("screenTitle")), is(
                    "fourthView"));
        }
    }

    /**
     * <ul>
     * <li>NameSpaceごとに保持できるトランザクショントークンの上限数が設定できることを確認する。
     * </ul>
     **/
    @Test
    public void testDBSP0303003() throws IOException {
        WebDriverOperations[] browsers = new WebDriverOperations[2];
        browsers[0] = webDriverOperations;
        // 同一セッションで新しいブラウザを立ち上げる
        browsers[1] = newWebDriverOperations();
        browsers[1].getWebDriver().manage().addCookie(browsers[0].getCookie(
                "JSESSIONID"));
        String[] buttonNames = { "dbsp0303003", "second", "third" };
        String[] screenTitles = { "firstView", "secondView", "thirdView" };

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                browsers[j].click(id(buttonNames[i]));
                if (i == 2 && j == 0) {
                    // ブラウザ1では第三画面に遷移できず、トランザクショントークンエラーとなることをチェック
                    // エラーの確認
                    {
                        // エラー画面表示まで待機
                        String expectedTitle = "Transaction Token Error!";
                        browsers[j].waitForDisplayed(ExpectedConditions
                                .titleContains(expectedTitle));
                        assertThat(browsers[j].getTitle(), is(expectedTitle));
                    }
                } else {
                    assertThat(browsers[j].getText(id("screenTitle")), is(
                            screenTitles[i]));
                }
            }
        }
    }

    /**
     * <ul>
     * <li>NameSpaceを設けることで、並行して操作できることを確認する。
     * </ul>
     **/
    @Test
    public void testDBSP0303004() throws IOException {
        WebDriverOperations[] browsers = new WebDriverOperations[2];
        browsers[0] = webDriverOperations;
        // 同一セッションで新しいブラウザを立ち上げる
        browsers[1] = newWebDriverOperations();
        browsers[1].getWebDriver().manage().addCookie(browsers[0].getCookie(
                "JSESSIONID"));
        String[] buttonNames = { "second", "third", "fourth", "fifth" };
        String[] screenTitles = { "secondView", "thirdView", "fourthView",
                "fifthView" };

        // Namespaceは未設定のため、globalToken
        browsers[0].click(id("dbsp0303003"));
        // Namespaceはcreate
        browsers[1].click(id("dbsp0303004"));
        for (WebDriverOperations browser : browsers) {
            assertThat(browser.getText(id("screenTitle")), is("firstView"));
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                browsers[j].click(id(buttonNames[i]));
                assertThat(browsers[j].getText(id("screenTitle")), is(
                        screenTitles[i]));
            }
        }
    }

    private void waitForDownloaded() {
        try {
            TimeUnit.SECONDS.sleep(1 + offsetSecondsOfWaitForDownload);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void deleteFilesRecursively(Path dir) throws Exception {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
                    deleteFilesRecursively(entry);
                } else {
                    Files.deleteIfExists(entry);
                }
            }
            Files.deleteIfExists(dir);
        }
    }

    /**
     * トランザクショントークンタグのvalue文字列からNameSpaceの部分を抜き出して返却する。
     * @param valueText
     * @return
     */
    private String getNameSpace(String valueText) {
        return valueText.substring(0, valueText.indexOf("~"));
    }

    /**
     * トランザクショントークンのvalue文字列からTokenKeyの部分を抜き出して返却する。
     * @param valueText
     * @return
     */
    private String getTokenKey(String valueText) {
        int startIndex = valueText.indexOf("~") + 1;
        return valueText.substring(startIndex, valueText.indexOf("~",
                startIndex));
    }

    /**
     * トランザクショントークンのvalue文字列からTokenValueの部分を抜き出して返却する。
     * @param valueText
     * @return
     */
    private String getTokenValue(String valueText) {
        return valueText.substring(valueText.lastIndexOf("~") + 1);
    }

    /**
     * トランザクショントークンの確認を行う。
     * @param valueText トランザクショントークンタグのvalue文字列
     * @param nameSpace 期待されるNameSpace
     * @param tokenKey 前回のTokenKey
     * @param tokenValue 前回のTokenValue
     */
    private void checkToken(String valueText, String nameSpace, String tokenKey,
            String tokenValue) {
        // NameSpaceの確認
        assertThat(getNameSpace(valueText), is(nameSpace));
        // TokenKeyの確認
        assertThat(getTokenKey(valueText), is(tokenKey));
        // TokenValueの確認
        assertThat(getTokenValue(valueText), is(not(tokenValue)));
        return;
    }
}
