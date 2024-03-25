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
package jp.co.ntt.fw.spring.functionaltest.selenium.dbsp;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.openqa.selenium.By.id;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

public class DoubleSubmitProtection_JSP_Test extends FunctionTestSupport {

    private static final Logger logger = LoggerFactory.getLogger(
            DoubleSubmitProtection_JSP_Test.class);

    @Value("${selenium.dbsp.waitForDownload.offsetSeconds:0}")
    private int offsetSecondsOfWaitForDownload;

    @Value("${selenium.app.download.pdf}")
    private String downloadPdfFileName;

    private static Path downloadTempDirectory;

    private static String VIEW_TYPE = "jsp";

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
     * <li>PRGパターンが正常に適用できていることを確認する。</li>
     * </ul>
     **/
    @Test
    public void testDBSP0201001() throws IOException {

        webDriverOperations.click(id("dbsp0201001_" + VIEW_TYPE));

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
     * <li>完了画面でブラウザのリロード機能を実行しても、更新処理が再実行されないことを確認する。</li>
     * </ul>
     **/
    @Test
    public void testDBSP0201002() throws IOException {

        webDriverOperations.click(id("dbsp0201001_" + VIEW_TYPE));

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
     * <li>トランザクショントークンチェックを実装し、正規の画面遷移が実行できることを確認する。</li>
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
        String[] buttonNames = { "dbsp0301001_" + VIEW_TYPE, "second", "third", "fourth",
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
     * <li>トランザクショントークンチェックを実装することで、不正な画面遷移を防止できることを確認する。</li>
     * </ul>
     **/
    @Test
    public void testDBSP0301002() throws IOException {
        webDriverOperations.click(id("dbsp0301001_" + VIEW_TYPE));
        webDriverOperations.click(id("second"));
        webDriverOperations.click(id("third"));
        webDriverOperations.click(id("fourth"));
        // 第四画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "fourthView"));

        // back()処理が終了せずテストが進行不可になるため、例外処理を用いて継続させる。
        try {
            webDriverOperations.back();
        } catch (WebDriverException e) {
            logger.info("WebDriverException by navigation back");
        }

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
     * <li>トランザクショントークンチェックを実装することで、正規の画面遷移を伴わない不正なリクエストによってデータが更新できないことを確認する。</li>
     * </ul>
     **/
    @Test
    public void testDBSP0301003() throws IOException {
        webDriverOperations.click(id("dbsp0301003_" + VIEW_TYPE));
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
     * <li>ブラウザの再読み込みを行い、二重送信を試みる。トランザクショントークンエラーになることを確認する。</li>
     * </ul>
     **/
    @Test
    public void testDBSP0301004() throws IOException {
        webDriverOperations.click(id("dbsp0301001_" + VIEW_TYPE));
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
     * <li>クライアントへ画面を返却しない処理を含むユースケースにおいて、トランザクショントークンチェックを実装しても正常に画面遷移が行えることを確認する。</li>
     * </ul>
     **/
    @Test
    public void testDBSP0301005() throws IOException {
        webDriverOperations.click(id("dbsp0301005_" + VIEW_TYPE));
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
     * <li><sec:cache-control />の設定を除外することで、トランザクショントークンエラー画面が表示できることを確認する。</li>
     * </ul>
     **/
    @Test
    public void testDBSP0302002() throws IOException {
        webDriverOperations.click(id("dbsp0302002_" + VIEW_TYPE));
        webDriverOperations.click(id("second"));
        webDriverOperations.click(id("third"));
        webDriverOperations.click(id("fourth"));
        // 第四画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "fourthView"));

        // back()処理が正常に終了しないため、例外処理を用いてテストを継続する。
        try {
            webDriverOperations.back();
        } catch (TimeoutException e) {
            logger.info("navigation back wait");
        }

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
     * <li>NameSpaceが設定されていない時はグローバルトークン用のNameSpaceが自動で設定されていることを確認する。</li>
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
        String[] buttonNames = { "dbsp0302002_" + VIEW_TYPE, "second", "third", "fourth",
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
