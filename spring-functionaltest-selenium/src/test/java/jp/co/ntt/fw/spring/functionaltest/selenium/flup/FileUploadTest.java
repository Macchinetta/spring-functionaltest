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
package jp.co.ntt.fw.spring.functionaltest.selenium.flup;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.id;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.util.StreamUtils;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

public class FileUploadTest extends FunctionTestSupport {

    @Value("${selenium.flup.waitForDeletedByScheduler.offsetSeconds:0}")
    private int offsetSecondsOfWaitForDeletedByScheduler;

    /**
     * <ul>
     * <li>Servlet3.0のアップロード機能とSpringMVCを連携した場合に、ファイルがアップロードできることを確認する。</li>
     * </ul>
     */
    @Test
    public void testFLUP0101001() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("flup0101001"));
        }
        // 単一ファイルアップロード画面の操作
        {
            webDriverOperations.referUploadFile(id("multipartFile"),
                    new ClassPathResource("testdata/flup/日本語ファイル.txt")
                            .getFile());

            webDriverOperations.click(id("uploadButton"));
        }

        // アップロードファイルの確認
        {
            assertThat(webDriverOperations.getText(id("fileNameText")), is(
                    "日本語ファイル.txt"));
            assertThat(webDriverOperations.getText(id("fileContentText")), is(
                    "日本語コンテンツ"));
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
     * <li>設定ファイルにアップロードを許可する1ファイルの最大バイト数を指定した場合に、アップロードが行えることを確認する。</li>
     * </ul>
     */
    @Test
    public void testFLUP0102001() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("flup0102001"));
        }
        // 単一ファイルアップロード画面の操作
        {

            webDriverOperations.referUploadFile(id("multipartFile"),
                    new ClassPathResource("testdata/flup/マルチバイト含む255バイトファイル.txt")
                            .getFile());

            webDriverOperations.click(id("uploadButton"));
        }

        // アップロードファイルの確認
        {
            assertThat(webDriverOperations.getText(id("fileNameText")), is(
                    "マルチバイト含む255バイトファイル.txt"));
            assertThat(webDriverOperations.getText(id("fileContentText")), is(
                    "マルチバイト含む255バイトファイルaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa1234567"));
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
     * <li>設定ファイルにアップロードを許可する1ファイルの最大バイト数を指定した場合に、アップロードが行えることを確認する。</li>
     * </ul>
     */
    @Ignore("testFLUP0102001で確認済みのため。")
    public void testFLUP0102002() throws IOException {
        testFLUP0102001();
    }

    /**
     * <ul>
     * <li>一時ファイルが作成されないサイズのファイルをアップロードした場合に、アップロードが行えることを確認する。</li>
     * </ul>
     */
    @Test
    public void testFLUP0102003() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("flup0102003"));
        }
        // 単一ファイルアップロード画面の操作
        {
            webDriverOperations.referUploadFile(id("multipartFile"),
                    new ClassPathResource("testdata/flup/マルチバイト含む63バイトファイル.txt")
                            .getFile());

            webDriverOperations.click(id("uploadButton"));
        }

        // アップロードファイルの確認
        {
            assertThat(webDriverOperations.getText(id("fileNameText")), is(
                    "マルチバイト含む63バイトファイル.txt"));
            assertThat(webDriverOperations.getText(id("fileContentText")), is(
                    "マルチバイト含む63バイト以下のファイル1234567"));
            // TODO 一時ファイルが作成されたか否かの確認については保留中

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
     * <li>一時ファイルが作成されるサイズのファイルをアップロードした場合に、アップロードが行えることを確認する。</li>
     * </ul>
     */
    @Test
    public void testFLUP0102004() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("flup0102004"));
        }
        // 単一ファイルアップロード画面の操作
        {

            webDriverOperations.referUploadFile(id("multipartFile"),
                    new ClassPathResource("testdata/flup/マルチバイト含む65バイトファイル.txt")
                            .getFile());

            webDriverOperations.click(id("uploadButton"));
        }

        // アップロードファイルの確認
        {
            assertThat(webDriverOperations.getText(id("fileNameText")), is(
                    "マルチバイト含む65バイトファイル.txt"));
            assertThat(webDriverOperations.getText(id("fileContentText")), is(
                    "マルチバイト含む64バイトより大ファイル123456789"));
            // TODO 一時ファイルが作成されたか否かの確認については保留中
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
     * <li>一時ファイルが作成されないサイズと同じサイズのファイルをアップロードした場合に、アップロードが行えることを確認する。</li>
     * </ul>
     */
    @Test
    public void testFLUP0102005() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("flup0102005"));
        }
        // 単一ファイルアップロード画面の操作
        {

            webDriverOperations.referUploadFile(id("multipartFile"),
                    new ClassPathResource("testdata/flup/マルチバイト含む64バイトファイル.txt")
                            .getFile());

            webDriverOperations.click(id("uploadButton"));
        }

        // アップロードファイルの確認
        {
            assertThat(webDriverOperations.getText(id("fileNameText")), is(
                    "マルチバイト含む64バイトファイル.txt"));
            assertThat(webDriverOperations.getText(id("fileContentText")), is(
                    "マルチバイト含む64バイト丁度のファイル12345678"));
            // TODO 一時ファイルが作成されたか否かの確認については保留中
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
     * <li>一時ファイルを出力するディレクトリを変更した場合に、アップロードが行えることを確認する。</li>
     * </ul>
     */
    @Test
    @Ignore("あらかじめ一時ファイルを出力するディレクトリを作成する必要があり、自動化が不可能であるため。")
    public void testFLUP0102006() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("flup0102006"));
        }
        // 単一ファイルアップロード画面の操作
        {

            webDriverOperations.referUploadFile(id("multipartFile"),
                    new ClassPathResource("testdata/flup/マルチバイト含む255バイトファイル.txt")
                            .getFile());

            webDriverOperations.click(id("uploadButton"));
        }

        // アップロードファイルの確認
        {
            assertThat(webDriverOperations.getText(id("uploadedFileName")), is(
                    "マルチバイト含む255バイトファイル.txt"));
            assertThat(webDriverOperations.getText(id("uploadedContent")), is(
                    "マルチバイト含む255バイトファイルaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa1234567"));
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
     * <li>1ファイルの許容サイズを超えるファイルをアップロードした場合に、設定した例外ハンドリングどおりの動作となることを確認する。</li>
     * </ul>
     */
    @Test
    public void testFLUP0103001() throws IOException {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("flup0103001"));
        }
        // 単一ファイルアップロード画面の操作
        {

            webDriverOperations.referUploadFile(id("multipartFile"),
                    new ClassPathResource("testdata/flup/マルチバイト含む257バイトファイル.txt")
                            .getFile());

            webDriverOperations.click(id("uploadButton"));
        }

        // アップロードエラーの確認
        {
            assertThat(webDriverOperations.getTitle(), is(
                    "File Upload Error!"));
        }
        // ステータスコードの確認
        {
            assertThat(webDriverOperations.getInputFieldValue(id("statusCode")),
                    is(String.valueOf(HttpStatus.BAD_REQUEST.value())));
        }
        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(
                    webDriverOperations.getXTrack(),
                    "org.terasoluna.gfw.common.exception.ExceptionLogger", ".*",
                    "org\\.springframework\\.web\\.multipart\\.*");
        }

        // サイズが制限以内のファイルはアップロードできることの確認

        dbLogCleaner.cleanupAll();

        // TOP画面へ遷移
        webDriverOperations.displayPage(getPackageRootUrl());

        // メニュー画面の操作
        {
            webDriverOperations.click(id("flup0103001"));
        }
        // 単一ファイルアップロード画面の操作
        {

            webDriverOperations.referUploadFile(id("multipartFile"),
                    new ClassPathResource("testdata/flup/マルチバイト含む64バイトファイル.txt")
                            .getFile());

            webDriverOperations.click(id("uploadButton"));
        }

        // アップロードファイルの確認
        {
            assertThat(webDriverOperations.getText(id("fileNameText")), is(
                    "マルチバイト含む64バイトファイル.txt"));
            assertThat(webDriverOperations.getText(id("fileContentText")), is(
                    "マルチバイト含む64バイト丁度のファイル12345678"));
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
     * <li>リクエストの許容サイズを超えるファイルをアップロードした場合に、設定した例外ハンドリングどおりの動作となることを確認する。</li>
     * </ul>
     */
    @Test
    public void testFLUP0103002() throws IOException {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("flup0103002"));
        }
        // 単一ファイルアップロード画面の操作
        {

            webDriverOperations.referUploadFile(id("multipartFile"),
                    new ClassPathResource("testdata/flup/マルチバイト含む1024バイトファイル.txt")
                            .getFile());

            webDriverOperations.click(id("uploadButton"));
        }

        // アップロードエラーの確認
        {
            assertThat(webDriverOperations.getTitle(), is(
                    "File Upload Error!"));
        }
        // ステータスコードの確認
        {
            assertThat(webDriverOperations.getInputFieldValue(id("statusCode")),
                    is(String.valueOf(HttpStatus.BAD_REQUEST.value())));
        }
        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(
                    webDriverOperations.getXTrack(),
                    "org.terasoluna.gfw.common.exception.ExceptionLogger", ".*",
                    "org\\.springframework\\.web\\.multipart\\.*");
        }

        // サイズが制限以内のファイルはアップロードできることの確認

        dbLogCleaner.cleanupAll();

        // TOP画面へ遷移
        webDriverOperations.displayPage(getPackageRootUrl());

        // メニュー画面の操作
        {
            webDriverOperations.click(id("flup0103001"));
        }
        // 単一ファイルアップロード画面の操作
        {

            webDriverOperations.referUploadFile(id("multipartFile"),
                    new ClassPathResource("testdata/flup/マルチバイト含む64バイトファイル.txt")
                            .getFile());

            webDriverOperations.click(id("uploadButton"));
        }

        // アップロードファイルの確認
        {
            assertThat(webDriverOperations.getText(id("fileNameText")), is(
                    "マルチバイト含む64バイトファイル.txt"));
            assertThat(webDriverOperations.getText(id("fileContentText")), is(
                    "マルチバイト含む64バイト丁度のファイル12345678"));
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
     * <li>単一ファイルのアップロード時、フォームオブジェクトにバインドして受け取る場合に、アップロードを実施できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testFLUP0201001() throws IOException {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("flup0201001"));
        }
        // 単一ファイルアップロード画面の操作
        {

            webDriverOperations.referUploadFile(id("multipartFile"),
                    new ClassPathResource("testdata/flup/日本語ファイル.txt")
                            .getFile());

            webDriverOperations.click(id("uploadButton"));
        }

        // アップロードファイルの確認
        {
            assertThat(webDriverOperations.getText(id("fileNameText")), is(
                    "日本語ファイル.txt"));
            assertThat(webDriverOperations.getText(id("fileContentText")), is(
                    "日本語コンテンツ"));
        }
        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }
    }

    @Test
    public void testFLUP0202001() throws IOException {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("flup0202001"));
        }
        // 単一ファイルアップロード画面の操作
        {

            webDriverOperations.referUploadFile(id("multipartFile"),
                    new ClassPathResource("testdata/flup/日本語ファイル.txt")
                            .getFile());

            webDriverOperations.click(id("uploadButton"));

        }

        // アップロードファイルの確認
        {
            assertThat(webDriverOperations.getText(id("fileNameText")), is(
                    "日本語ファイル.txt"));
            assertThat(webDriverOperations.getText(id("fileContentText")), is(
                    "日本語コンテンツ"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 戻る
        {
            webDriverOperations.getWebDriver().navigate().back();
        }
        // 単一ファイルアップロード画面の再操作
        {
            // hiddenパラメータのトークンを変更
            JavascriptExecutor jse = (JavascriptExecutor) webDriverOperations
                    .getWebDriver();
            jse.executeScript(
                    "document.getElementsByName('_TRANSACTION_TOKEN')[0].setAttribute('type', 'text');");
            jse.executeScript(
                    "document.getElementsByName('_TRANSACTION_TOKEN')[0].value = 'flup/0202~abcdefg';");
            webDriverOperations.click(id("uploadButton"));
        }
        // トランザクションエラーの確認
        {
            // エラー画面表示まで待機
            String expectedTitle = "Transaction Token Error!";
            webDriverOperations.waitForDisplayed(ExpectedConditions
                    .titleContains(expectedTitle));

            assertThat(webDriverOperations.getTitle(), is(expectedTitle));
        }
        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(
                    "org.terasoluna.gfw.common.exception.ExceptionLogger",
                    "\\[e.sf.cmmn.9001\\] Invalid Transaction Token Exception \\!\\!\\!");
        }
    }

    /**
     * <ul>
     * <li>単一ファイルの場合にて、ファイルの中身が選択されていない場合に、入力エラーになることを確認する。</li>
     * </ul>
     */
    @Test
    public void testFLUP0301001() throws IOException {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("flup0301001"));
        }
        // 単一ファイルアップロード画面の操作
        {

            // アップロードファイルを選択しない。
            webDriverOperations.click(id("uploadButton"));
        }

        // エラーメッセージの確認
        {
            assertThat(webDriverOperations.getText(id("multipartFile.errors")),
                    is("コンテンツファイルにファイルが選択されていません。"));
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
     * <li>単一ファイルの場合にて、空ファイルが選択されている場合に、入力エラーになることを確認する。</li>
     * </ul>
     */
    @Test
    public void testFLUP0301002() throws IOException {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("flup0301002"));
        }
        // 単一ファイルアップロード画面の操作
        {

            webDriverOperations.referUploadFile(id("multipartFile"),
                    new ClassPathResource("testdata/flup/0バイトファイル.txt")
                            .getFile());
            webDriverOperations.click(id("uploadButton"));
        }

        // エラーメッセージの確認
        {
            assertThat(webDriverOperations.getText(id("multipartFile.errors")),
                    is("コンテンツファイルに指定されたファイルが空ファイルです。"));
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
     * <li>単一ファイルの場合にて、許容サイズを超えるファイルが選択されている場合に、入力エラーになることを確認する。</li>
     * </ul>
     */
    @Test
    public void testFLUP0301003() throws IOException {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("flup0301003"));
        }
        // 単一ファイルアップロード画面の操作
        {

            webDriverOperations.referUploadFile(id("multipartFile"),
                    new ClassPathResource("testdata/flup/マルチバイト含む255バイトファイル.txt")
                            .getFile());
            webDriverOperations.click(id("uploadButton"));
        }

        // エラーメッセージの確認
        {
            assertThat(webDriverOperations.getText(id("multipartFile.errors")),
                    is("コンテンツファイルに指定されたファイルが大きすぎます。250バイト以下のファイルを指定してください。"));
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
     * <li>複数ファイルのアップロード時、フォームオブジェクトにバインドして受け取る場合に、アップロードを実施できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testFLUP0401001() throws IOException {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("flup0401001"));
        }
        // 複数ファイルアップロード画面の操作
        {

            webDriverOperations.referUploadFile(id(
                    "uploadForms0.multipartFile"),
                    new ClassPathResource("testdata/flup/日本語ファイル.txt")
                            .getFile());

            webDriverOperations.referUploadFile(id(
                    "uploadForms1.multipartFile"),
                    new ClassPathResource("testdata/flup/日本語ファイル２.txt")
                            .getFile());

            webDriverOperations.click(id("uploadButton"));
        }

        // アップロードファイルの確認
        {

            assertThat(webDriverOperations.getText(id("fileNameText0")), is(
                    "日本語ファイル.txt"));
            assertThat(webDriverOperations.getText(id("fileContentText0")), is(
                    "日本語コンテンツ"));

            assertThat(webDriverOperations.getText(id("fileNameText1")), is(
                    "日本語ファイル２.txt"));
            assertThat(webDriverOperations.getText(id("fileContentText1")), is(
                    "日本語コンテンツ２"));
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
     * <li>複数ファイルの場合にて、空ファイルが選択されている場合に、入力エラーになることを確認する。</li>
     * </ul>
     */
    @Test
    public void testFLUP0401002() throws IOException {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("flup0401002"));
        }
        // 複数ファイルアップロード画面の操作
        {

            // アップロードファイルを選択しない。

            webDriverOperations.click(id("uploadButton"));
        }

        // エラーメッセージの確認
        {
            assertThat(webDriverOperations.getText(id(
                    "uploadForms0.multipartFile.errors")), is(
                            "コンテンツファイル1にファイルが選択されていません。"));
            assertThat(webDriverOperations.getText(id(
                    "uploadForms1.multipartFile.errors")), is(
                            "コンテンツファイル2にファイルが選択されていません。"));
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
     * <li>複数ファイルの場合にて、ファイルの中身が選択されていない場合に、入力エラーになることを確認する。</li>
     * </ul>
     */
    @Test
    public void testFLUP0401003() throws IOException {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("flup0401003"));
        }
        // 複数ファイルアップロード画面の操作
        {

            webDriverOperations.referUploadFile(id(
                    "uploadForms0.multipartFile"),
                    new ClassPathResource("testdata/flup/0バイトファイル.txt")
                            .getFile());

            webDriverOperations.referUploadFile(id(
                    "uploadForms1.multipartFile"),
                    new ClassPathResource("testdata/flup/0バイトファイル.txt")
                            .getFile());

            webDriverOperations.click(id("uploadButton"));
        }

        // エラーメッセージの確認
        {
            assertThat(webDriverOperations.getText(id(
                    "uploadForms0.multipartFile.errors")), is(
                            "コンテンツファイル1に指定されたファイルが空ファイルです。"));
            assertThat(webDriverOperations.getText(id(
                    "uploadForms1.multipartFile.errors")), is(
                            "コンテンツファイル2に指定されたファイルが空ファイルです。"));
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
     * <li>複数ファイルの場合にて、許容サイズを超えるファイルが選択されている場合に、入力エラーになることを確認する。</li>
     * </ul>
     */
    @Test
    public void testFLUP0401004() throws IOException {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("flup0401004"));
        }
        // 複数ファイルアップロード画面の操作
        {

            webDriverOperations.referUploadFile(id(
                    "uploadForms0.multipartFile"),
                    new ClassPathResource("testdata/flup/マルチバイト含む255バイトファイル.txt")
                            .getFile());

            webDriverOperations.referUploadFile(id(
                    "uploadForms1.multipartFile"),
                    new ClassPathResource("testdata/flup/マルチバイト含む255バイトファイル.txt")
                            .getFile());

            webDriverOperations.click(id("uploadButton"));
        }

        // エラーメッセージの確認
        {
            assertThat(webDriverOperations.getText(id(
                    "uploadForms0.multipartFile.errors")), is(
                            "コンテンツファイル1に指定されたファイルが大きすぎます。250バイト以下のファイルを指定してください。"));
            assertThat(webDriverOperations.getText(id(
                    "uploadForms1.multipartFile.errors")), is(
                            "コンテンツファイル2に指定されたファイルが大きすぎます。250バイト以下のファイルを指定してください。"));
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
     * <li>HTML5のmultiple属性を使用して、複数ファイルのアップロード時、フォームオブジェクトにバインドして受け取る場合に、アップロードを実施できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testFLUP0402001() throws IOException {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("flup0402001"));
        }
        // 複数ファイルアップロード画面の操作
        {
            // TODO multipleの指定方法が不明。1ファイルのみアップロード
            webDriverOperations.referUploadFile(id("multipartFiles"),
                    new ClassPathResource("testdata/flup/日本語ファイル.txt")
                            .getFile());

            // webDriverOperations.referUploadFile(id("uploadForms1.multipartFile"),
            // new ClassPathResource("testdata/flup/日本語ファイル２.txt")
            // .getFile());

            webDriverOperations.click(id("uploadButton"));
        }

        // アップロードファイルの確認
        {

            assertThat(webDriverOperations.getText(id("fileNameText0")), is(
                    "日本語ファイル.txt"));
            assertThat(webDriverOperations.getText(id("fileContentText0")), is(
                    "日本語コンテンツ"));

            // assertThat(uploadedFileNameElements.get(1).getText(), is("日本語ファイル２.txt"));
            // assertThat(uploadedFileContentElements.get(1).getText(), is("日本語コンテンツ２"));
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
     * <li>HTML5のmultiple属性を使用して、複数ファイルの場合にて、空ファイルが選択されている場合に、入力エラーになることを確認する。</li>
     * </ul>
     */
    @Test
    public void testFLUP0402002() throws IOException {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("flup0402002"));
        }
        // 複数ファイルアップロード画面の操作
        {
            // TODO multipleの指定方法が不明。1ファイルのみアップロード
            webDriverOperations.referUploadFile(id("multipartFiles"),
                    new ClassPathResource("testdata/flup/0バイトファイル.txt")
                            .getFile());

            // webDriverOperations.referUploadFile(id("uploadForms1.multipartFile"),
            // new ClassPathResource("testdata/flup/0バイトファイル.txt")
            // .getFile());

            webDriverOperations.click(id("uploadButton"));
        }

        // エラーメッセージの確認
        {
            assertThat(webDriverOperations.getText(id("multipartFiles.errors")),
                    is("コンテンツファイルに指定されたファイルが空ファイルです。"));
            // assertThat(webDriverOperations.getText(id("uploadForms1.multipartFile.errors")),
            // is("ContentsFile2 is empty."));
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
     * <li>仮ファイルを作成後、正式な保存ディレクトへ保存できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testFLUP0501001() throws IOException {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("flup0501001"));
        }
        // 仮ファイルアップロード画面の操作
        {

            webDriverOperations.referUploadFile(id("multipartFile"),
                    new ClassPathResource("testdata/flup/日本語ファイル.txt")
                            .getFile());

            webDriverOperations.click(id("confirmButton"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 仮ファイルアップロード確認画面の操作
        String temporaryFileId = null;
        {

            temporaryFileId = webDriverOperations.getText(id(
                    "temporaryFileIdText"));

            // 仮アップロードファイルが存在したらOK
            assertTrue(existsUploadedTemporaryFile(temporaryFileId));
            assertThat(webDriverOperations.getText(id("fileNameText")), is(
                    "日本語ファイル.txt"));
            assertThat(webDriverOperations.getText(id("descriptionText")), is(
                    ""));
            webDriverOperations.click(id("uploadButton"));
        }
        // 仮ファイルアップロード完了画面の確認
        {
            String fileId = webDriverOperations.getText(id("fileIdText"));

            // 正式ディレクトリにアップロードファイルが存在したらOK
            assertTrue(existsUploadedFile(fileId));
            // 仮アップロードファイルが存在したらNG
            assertFalse(existsUploadedTemporaryFile(temporaryFileId));

            assertThat(webDriverOperations.getText(id("fileNameText")), is(
                    "日本語ファイル.txt"));

            assertThat(webDriverOperations.getText(id("fileContentText")), is(
                    "日本語コンテンツ"));
            assertThat(webDriverOperations.getText(id("descriptionText")), is(
                    ""));
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
     * <li>仮ファイルを作成後、正式なDBへ保存できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testFLUP0501002() throws IOException {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("flup0501002"));
        }
        // 仮ファイルアップロード画面の操作
        {

            webDriverOperations.referUploadFile(id("multipartFile"),
                    new ClassPathResource("testdata/flup/日本語ファイル.txt")
                            .getFile());

            String description = new StringBuilder().append(
                    "確認画面遷移時に一時ディレクトリへ仮アップロードを行う。").append("\n").append("\n")
                    .append("\r").append(
                            "その後、確認画面でアップロードボタンを押下すると、アップロードしたファイルがデータベースに登録されます。")
                    .toString();
            webDriverOperations.overrideTextArea(id("description"),
                    description);

            webDriverOperations.click(id("confirmButton"));
        }

        // 確認画面、完了画面に表示される値(期待値)
        String expectedDescription = new StringBuilder().append(
                "確認画面遷移時に一時ディレクトリへ仮アップロードを行う。").append("\n").append("\n")
                .append("\n").append(
                        "その後、確認画面でアップロードボタンを押下すると、アップロードしたファイルがデータベースに登録されます。")
                .toString();

        // 仮ファイルアップロード確認画面の操作
        String temporaryFileId = null;
        {
            temporaryFileId = webDriverOperations.getText(id(
                    "temporaryFileIdText"));
            // 仮アップロードファイルが存在したらOK
            assertTrue(existsUploadedTemporaryFile(temporaryFileId));
            assertThat(webDriverOperations.getText(id("fileNameText")), is(
                    "日本語ファイル.txt"));
            assertThat(webDriverOperations.getText(id("descriptionText")), is(
                    expectedDescription));

            webDriverOperations.click(id("uploadButton"));
        }
        // 仮ファイルアップロード完了画面の確認
        String fileId;
        {
            fileId = webDriverOperations.getText(id("fileIdText"));

            assertThat(webDriverOperations.getText(id("fileNameText")), is(
                    "日本語ファイル.txt"));

            assertThat(webDriverOperations.getText(id("fileContentText")), is(
                    "日本語コンテンツ"));

            assertThat(webDriverOperations.getText(id("descriptionText")), is(
                    expectedDescription));

        }
        // アップロード確認
        {
            byte[] uploadFileData = restOperations.getForObject(
                    getPackageRootUrl() + "/files?download&fileId=" + fileId,
                    byte[].class);
            assertThat(new String(uploadFileData, "UTF-8"), is("日本語コンテンツ"));
        }
        // 一時ファイルの存在確認
        {
            // 仮アップロードファイルが存在したらNG
            assertFalse(existsUploadedTemporaryFile(temporaryFileId));
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
     * <li>タスクスケジューラ機能を使った場合に、仮アップロード時のファイルが削除されることを確認する。</li>
     * </ul>
     */
    @Test
    public void testFLUP0601001() throws IOException {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("flup0601001"));
        }
        // 仮ファイルアップロード画面の操作
        {

            webDriverOperations.referUploadFile(id("multipartFile"),
                    new ClassPathResource("testdata/flup/日本語ファイル.txt")
                            .getFile());

            webDriverOperations.click(id("confirmButton"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 仮ファイルアップロード確認画面の操作
        String temporaryFileId = null;
        {

            temporaryFileId = webDriverOperations.getText(id(
                    "temporaryFileIdText"));
            // 仮アップロードファイルが存在したらOK
            assertTrue(existsUploadedTemporaryFile(temporaryFileId));
            assertThat(webDriverOperations.getText(id("fileNameText")), is(
                    "日本語ファイル.txt"));

            // 仮ファイルの削除スケジューラを有効に設定
            restOperations.getForEntity(applicationContextUrl
                    + "/flup/0601/001?enableScheduler", String.class);

            // 仮ファイル削除のタスクスケジューラが動く間隔の12秒待機
            try {
                TimeUnit.SECONDS.sleep(12
                        + offsetSecondsOfWaitForDeletedByScheduler);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 仮ファイルの確認
            webDriverOperations.click(id("confirmTemporaryDirectory"));
        }
        // 一時ディレクトリのファイル一覧画面の確認
        {
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "ファイルはありません。"));
            // 仮アップロードファイルが存在しなかったらOK
            assertFalse(existsUploadedTemporaryFile(temporaryFileId));
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
     * <li>同期的に削除を呼び出した場合に、仮アップロード時のファイルが削除されることを確認する。</li>
     * </ul>
     */
    @Test
    public void testFLUP0601002() throws IOException {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("flup0601002"));
        }
        // 仮ファイルアップロード画面の操作
        {

            webDriverOperations.referUploadFile(id("multipartFile"),
                    new ClassPathResource("testdata/flup/日本語ファイル.txt")
                            .getFile());

            webDriverOperations.click(id("confirmButton"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 仮ファイルアップロード確認画面の操作
        String temporaryFileId = null;
        {

            temporaryFileId = webDriverOperations.getText(id(
                    "temporaryFileIdText"));
            // 仮アップロードファイルが存在したらOK
            assertTrue(existsUploadedTemporaryFile(temporaryFileId));
            assertThat(webDriverOperations.getText(id("fileNameText")), is(
                    "日本語ファイル.txt"));

            // 仮ファイルの確認
            webDriverOperations.click(id("confirmTemporaryDirectory"));
        }
        // 一時ディレクトリのファイル一覧画面の確認
        {
            assertThat(webDriverOperations.getText(id("messages")), is(""));
            // まだ仮アップロードファイルが存在したらOK
            assertTrue(existsUploadedTemporaryFile(temporaryFileId));
            // 仮ファイル削除呼び出し
            webDriverOperations.click(id("deleteAllButton"));
        }
        // 一時ディレクトリのファイル一覧画面の再確認
        {
            // 最新状態を確認
            webDriverOperations.click(id("refreshButton"));
            assertThat(webDriverOperations.getText(id("messages")), is(
                    "ファイルはありません。"));
            // 仮アップロードファイルが存在しなかったらOK
            assertFalse(existsUploadedTemporaryFile(temporaryFileId));
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
     * <li>アップロードしたファイルへのアクセス方法に制限を設けた場合、スクリプトが実行されれないことを確認する。</li>
     * </ul>
     */
    @Test
    public void testFLUP0801001() throws IOException, InterruptedException {

        String downloadTempDirectory = Files.createTempDirectory(
                "springtest-flup-").toAbsolutePath().toString();

        // ファイルダウンロードの設定をしたWebDriverの生成
        {
            WebDriver newDriver = webDriverCreator.createDownloadableWebDriver(
                    downloadTempDirectory);
            quitWebDriver(webDriverOperations);
            setCurrentWebDriver(newDriver);
        }

        // メニュー画面の操作
        {
            webDriverOperations.click(id("flup0801001"));
        }
        // 単一ファイルアップロード画面の操作
        {

            webDriverOperations.referUploadFile(id("multipartFile"),
                    new ClassPathResource("testdata/flup/index.jsp").getFile());

            webDriverOperations.click(id("uploadButton"));
        }

        // ファイルアップロード完了画面の確認
        {
            assertThat(webDriverOperations.getText(id("fileNameText")), is(
                    "index.jsp"));

            // ダウンロード
            webDriverOperations.click(id("downloadButton"));
            TimeUnit.SECONDS.sleep(1);

            // フダウンロードしたファイルの確認
            assertThat(StreamUtils.copyToByteArray(
                    new FileInputStream(downloadTempDirectory + "/index.jsp")),
                    is(StreamUtils.copyToByteArray(
                            new ClassPathResource("testdata/flup/index.jsp")
                                    .getInputStream())));
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
     * <li>アップロードするファイルの形式が許可する拡張子の場合、アップロードできることを確認する。</li>
     * </ul>
     */
    @Test
    public void testFLUP0802001() throws IOException {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("flup0802001"));
        }
        // 単一ファイルアップロード画面の操作
        {

            webDriverOperations.referUploadFile(id("multipartFile"),
                    new ClassPathResource("testdata/flup/日本語ファイル.txt")
                            .getFile());

            webDriverOperations.click(id("uploadButton"));
        }

        // アップロードファイルの確認
        {
            assertThat(webDriverOperations.getText(id("fileNameText")), is(
                    "日本語ファイル.txt"));
            assertThat(webDriverOperations.getText(id("fileContentText")), is(
                    "日本語コンテンツ"));
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
     * <li>アップロードするファイルの形式が許可する拡張子でない場合、アップロードできないことを確認する。</li>
     * </ul>
     */
    @Test
    public void testFLUP0802002() throws IOException {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("flup0802001"));
        }
        // 単一ファイルアップロード画面の操作
        {

            webDriverOperations.referUploadFile(id("multipartFile"),
                    new ClassPathResource("testdata/flup/index.jsp").getFile());

            webDriverOperations.click(id("uploadButton"));
        }

        // アップロードファイルの確認
        {
            assertThat(webDriverOperations.getText(id("multipartFile.errors")),
                    is("コンテンツファイルに指定されたファイルをアップロードする事ができません。拡張子を確認してください。"));
        }
        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }
    }

    /**
     * 仮アップロードしたファイルがサーバ上に存在するか確認する。
     * @param fileId ファイルを識別するためのID
     * @return 指定したファイルがサーバ上に存在する場合は<code>true</code>を返却する。404(Not Found)がサーバから返却された場合は<code>false</code>
     *         を返却するが、それ以外のHTTPステータスの場合は例外を再スローする。
     */
    private boolean existsUploadedTemporaryFile(String fileId) {
        String uri = applicationContextUrl + "/uploadedTemporaryFiles/{fileId}";
        return existsWebResource(uri, fileId);
    }

    /**
     * 本アップロードしたファイルがサーバ上に存在するか確認する。
     * @param fileId ファイルを識別するためのID
     * @return 指定したファイルがサーバ上に存在する場合は<code>true</code>を返却する。404(Not Found)がサーバから返却された場合は<code>false</code>
     *         を返却するが、それ以外のHTTPステータスの場合は例外を再スローする。
     */
    private boolean existsUploadedFile(String fileId) {
        String uri = applicationContextUrl + "/uploadedFiles/{fileId}";
        return existsWebResource(uri, fileId);
    }

}
