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
package jp.co.ntt.fw.spring.functionaltest.selenium.exhn;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.id;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

public class ExceptionHandlingTest extends FunctionTestSupport {

    /**
     * <ul>
     * <li>ビジネス例外を発生させ、処理が継続されないことを確認する。</li>
     * </ul>
     */
    @Test
    public void testEXHN0201001() throws IOException {

        webDriverOperations.click(id("exhn0201001"));

        webDriverOperations.overrideText(id("employeeName"), "Tom");
        webDriverOperations.overrideText(id("email"), "Joshua@example.com");
        webDriverOperations.overrideText(id("address"), "New York");

        webDriverOperations.saveScreenCapture();

        webDriverOperations.click(id("update"));

        // ビジネスエラー画面に遷移
        assertThat(webDriverOperations.getText(id("businessError")), is(
                "業務エラー画面"));

        webDriverOperations.saveScreenCapture();

        webDriverOperations.click(id("index"));
        webDriverOperations.click(id("exhn0201001"));

        // 変更されていないことを確認
        assertThat(webDriverOperations.getInputFieldValue(id("employeeName")),
                is("Josh"));
        assertThat(webDriverOperations.getInputFieldValue(id("email")), is(
                "josh@example.com"));
        assertThat(webDriverOperations.getInputFieldValue(id("address")), is(
                "sandiego"));

    }

    /**
     * <ul>
     * <li>システム例外を発生させ、処理が継続されないことを確認する。</li>
     * </ul>
     */
    @Test
    public void testEXHN0202001() throws IOException {
        webDriverOperations.click(id("deleteDbLink"));
        webDriverOperations.click(id("exhn0202001"));

        webDriverOperations.overrideText(id("title"), "Spring Contents");
        webDriverOperations.referUploadFile(id("multipartFile"),
                new ClassPathResource("testdata/exhn/exhn.html").getFile());
        webDriverOperations.saveScreenCapture();

        webDriverOperations.click(id("uploadButton"));

        // システム例外を発生させるため、作成した一時ファイルを削除
        String temporaryFileName = webDriverOperations.getText(id(
                "uploadTemporaryFileId"));
        // RestTemplate で削除リクエストを投げる
        restOperations.getForEntity(applicationContextUrl
                + "/exhn/delete?temporaryFileName=" + temporaryFileName,
                String.class);

        webDriverOperations.click(id("uploadButton"));

        // システムエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("System Error!"));

        webDriverOperations.saveScreenCapture();

        webDriverOperations.displayPage(getPackageRootUrl());
        webDriverOperations.click(id("exhn0202001"));

        // 登録されていないことを確認
        assertThat(webDriverOperations.getText(id("count")), is("登録件数:0"));
    }

    /**
     * <ul>
     * <li>例外を発生させ、キャッチしてログ出力し、処理が継続されることを確認する。</li>
     * </ul>
     * @throws InterruptedException
     */
    @Test
    public void testEXHN0203001() throws IOException, InterruptedException {

        webDriverOperations.click(id("deleteDbLink"));
        webDriverOperations.click(id("exhn0203001"));

        webDriverOperations.overrideText(id("title"), "Spring Contents");
        webDriverOperations.referUploadFile(id("multipartFile"),
                new ClassPathResource("testdata/exhn/exhn.html").getFile());
        webDriverOperations.saveScreenCapture();

        webDriverOperations.click(id("uploadButton"));

        // IOエラーを発生させるため、作成した一時ファイルを完了ディレクトリにコピーしておく
        String temporaryFileName = webDriverOperations.getText(id(
                "uploadTemporaryFileId"));

        // RestTemplate で一時ファイルをコピーするリクエストを投げる
        restOperations.getForEntity(applicationContextUrl
                + "/exhn/copy/temporaryFile?temporaryFileName="
                + temporaryFileName, void.class);

        webDriverOperations.click(id("uploadButton"));

        // 完了画面に遷移すること
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "記事登録完了画面"));
        webDriverOperations.saveScreenCapture();

        webDriverOperations.displayPage(getPackageRootUrl());
        webDriverOperations.click(id("exhn0203001"));

        // エラーログが出力されていること
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                "org.terasoluna.gfw.common.exception.ExceptionLogger",
                "[e.sf.cmmn.9001]..*", "..*FileAlreadyExistsException..*");

        // 登録されていることを確認
        assertThat(webDriverOperations.getText(id("count")), is("登録件数:1"));
    }

    /**
     * <ul>
     * <li>Controllerのメソッド単位で例外がハンドリングされることを確認する。</li>
     * </ul>
     */
    @Test
    public void testEXHN0301001() throws IOException {

        webDriverOperations.click(id("exhn0301001"));

        webDriverOperations.overrideText(id("employeeName"), "Tom");
        webDriverOperations.overrideText(id("email"), "Joshua@example.com");
        webDriverOperations.overrideText(id("address"), "New York");

        webDriverOperations.saveScreenCapture();

        webDriverOperations.click(id("update"));

        // エラーメッセージ確認
        assertThat(webDriverOperations.getText(By.xpath("//div[2]/div/div/ul")),
                is("After exception message from catch"));

    }

    /**
     * <ul>
     * <li>@ExceptionHandlerを使用して、Controlloer単位でExceptionがハンドリングされることを確認する。 Controller単位であることを確認するために2つのメソッドで実行する。</li>
     * </ul>
     */
    @Test
    public void testEXHN0302001() throws IOException {

        webDriverOperations.click(id("exhn0302001First"));

        webDriverOperations.overrideText(id("employeeName"), "Tom");
        webDriverOperations.overrideText(id("email"), "Joshua@example.com");
        webDriverOperations.overrideText(id("address"), "New York");

        webDriverOperations.saveScreenCapture();

        webDriverOperations.click(id("update"));

        // エラーメッセージ確認
        assertThat(webDriverOperations.getText(By.xpath("//div[2]/div/div/ul")),
                is("Exception message from EXHN"));
        webDriverOperations.saveScreenCapture();

        webDriverOperations.click(id("backToIndex"));

        webDriverOperations.click(id("exhn0302001Second"));

        webDriverOperations.overrideText(id("employeeName"), "Tom");
        webDriverOperations.overrideText(id("email"), "Joshua@example.com");
        webDriverOperations.overrideText(id("address"), "New York");

        webDriverOperations.saveScreenCapture();

        webDriverOperations.click(id("update"));

        // エラーメッセージ確認
        assertThat(webDriverOperations.getText(By.xpath("//div[2]/div/div/ul")),
                is("Exception message from EXHN"));
        webDriverOperations.saveScreenCapture();

    }

    /**
     * <ul>
     * <li>@ExceptionHandlerを使用して、Controllerのメソッド単位で致命的なエラーがサーブレットコンテナに再スローされることを確認する。</li>
     * </ul>
     */
    @Test
    public void testEXHN0302002() throws IOException {

        webDriverOperations.click(id("exhn0302002"));

        dbLogAssertOperations.waitForAssertion();

        // NestedServletExceptionをハンドリングした時に出力するログが存在することを確認
        dbLogAssertOperations.assertContainsByMessage(
                "jp.co.ntt.fw.spring.functionaltest.app.exhn.EXHN03Controller",
                "NestedServletException Occured");

        // web.xmlの<error-page>に定義したエラー画面に遷移することを確認(SystemExceptionResolverのdefaultErrorViewに定義した画面に遷移しないこと)
        assertThat(webDriverOperations.getTitle(), is(
                "Unhandled System Error!"));

    }

    /**
     * <ul>
     * <li>例外ハンドリング用クラスであるSystemExceptionResolverの設定値を変更しない場合、ハンドリング可能なことを確認する。</li>
     * </ul>
     */
    @Test(expected = HttpServerErrorException.class)
    public void testEXHN0601001() throws IOException {

        webDriverOperations.click(id("exhn0601001"));

        webDriverOperations.overrideText(id("title"), "Spring Contents");
        webDriverOperations.referUploadFile(id("multipartFile"),
                new ClassPathResource("testdata/exhn/exhn.html").getFile());
        webDriverOperations.saveScreenCapture();

        webDriverOperations.click(id("uploadButton"));

        // システム例外を発生させるため、作成した一時ファイルを削除
        String temporaryFileName = webDriverOperations.getText(id(
                "uploadTemporaryFileId"));
        // RestTemplate で削除リクエストを投げる
        restOperations.getForEntity(applicationContextUrl
                + "/exhn/delete?temporaryFileName=" + temporaryFileName,
                String.class);

        webDriverOperations.click(id("uploadButton"));

        // システムエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("System Error!"));

        assertThat(webDriverOperations.getText(By.xpath("//div[2]/div/ul/li")),
                is("[e.sf.exhn.9000] System error occurred!"));
        assertThat(webDriverOperations.getText(By.xpath("//ul[2]/li")),
                containsString("not found upload file."));
        webDriverOperations.saveScreenCapture();

        try {
            restOperations.getForEntity(applicationContextUrl
                    + "/exhn/0601/register?uploadTemporaryFileId="
                    + temporaryFileName
                    + "&title=test&fileName=exhn.html&upload", String.class);
        } catch (HttpServerErrorException e) {
            // X-Exception-Code が設定されていることを確認
            assertThat(e.getResponseHeaders().getFirst("X-Exception-Code"), is(
                    "e.sf.exhn.9000"));
            throw e;
        }
    }

    /**
     * <ul>
     * <li>例外ハンドリング用クラスであるSystemExceptionResolverの設定値を変更した場合、ハンドリング可能なことを確認する。</li>
     * </ul>
     */
    @Test(expected = HttpServerErrorException.class)
    public void testEXHN0601002() throws IOException {

        webDriverOperations.click(id("exhn0601002"));

        webDriverOperations.overrideText(id("title"), "Spring Contents");
        webDriverOperations.referUploadFile(id("multipartFile"),
                new ClassPathResource("testdata/exhn/exhn.html").getFile());
        webDriverOperations.saveScreenCapture();

        webDriverOperations.click(id("uploadButton"));

        // システム例外を発生させるため、作成した一時ファイルを削除
        String temporaryFileName = webDriverOperations.getText(id(
                "uploadTemporaryFileId"));
        // RestTemplate で削除リクエストを投げる
        restOperations.getForEntity(applicationContextUrl
                + "/exhn/delete?temporaryFileName=" + temporaryFileName,
                String.class);

        webDriverOperations.click(id("uploadButton"));

        // システムエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("System Error!"));

        assertThat(webDriverOperations.getText(By.xpath("//div[2]/div/ul/li")),
                is("[e.sf.exhn.9000] System error occurred!"));
        assertThat(webDriverOperations.getText(By.xpath("//ul[2]/li")),
                containsString("not found upload file."));
        webDriverOperations.saveScreenCapture();

        try {
            restOperations.getForEntity(applicationContextUrl
                    + "/exhn/0601/002/register?uploadTemporaryFileId="
                    + temporaryFileName
                    + "&title=test&fileName=exhn.html&upload", String.class);
        } catch (HttpServerErrorException e) {
            // ヘッダに設定された値の整合性確認
            assertThat(e.getResponseHeaders().getFirst("X-Error-Code"), is(
                    "e.sf.exhn.9000"));

            List<String> cacheControlList = Arrays.asList(
                    "no-cache, no-store, max-age=0, must-revalidate",
                    "no-store");
            assertThat(e.getResponseHeaders().get("Cache-Control"), is(
                    cacheControlList));
            assertThat(e.getResponseHeaders().getExpires(), is(-1L));
            assertThat(e.getResponseHeaders().getPragma(), is("no-cache"));

            throw e;
        }
    }

    /**
     * <ul>
     * <li>例外ハンドリング用クラスであるSystemExceptionResolverでビジネス例外メッセージの属性名を変更しない場合、デフォルトの属性名でメッセージを出力することができることを確認する。</li>
     * </ul>
     */
    @Test
    public void testEXHN0601003() throws IOException {

        webDriverOperations.click(id("exhn0601003"));

        webDriverOperations.overrideText(id("employeeName"), "Tom");
        webDriverOperations.overrideText(id("email"), "Joshua@example.com");
        webDriverOperations.overrideText(id("address"), "New York");

        webDriverOperations.saveScreenCapture();

        webDriverOperations.click(id("update"));

        // ビジネスエラー画面に遷移
        assertThat(webDriverOperations.getText(id("businessError")), is(
                "業務エラー画面"));
        assertThat(webDriverOperations.getText(By.xpath(
                "//div[2]/div/div/div[2]/ul/li")), is(
                        "Business Exception occurred!!"));

    }

    /**
     * <ul>
     * <li>例外ハンドリング用クラスであるSystemExceptionResolverでビジネス例外メッセージの属性名を変更した場合、変更した属性名でメッセージを出力することができることを確認する。</li>
     * </ul>
     */
    @Test
    public void testEXHN0601004() throws IOException {

        webDriverOperations.click(id("exhn0601004"));

        webDriverOperations.overrideText(id("employeeName"), "Tom");
        webDriverOperations.overrideText(id("email"), "Joshua@example.com");
        webDriverOperations.overrideText(id("address"), "New York");

        webDriverOperations.saveScreenCapture();

        webDriverOperations.click(id("update"));

        // ビジネスエラー画面に遷移
        assertThat(webDriverOperations.getText(id("businessError")), is(
                "業務エラー画面"));
        assertThat(webDriverOperations.getText(By.xpath(
                "//div[2]/div/div/div[2]/ul/li")), is(
                        "Business Exception occurred!!"));

    }

    /**
     * <ul>
     * <li>例外ハンドリング用クラスであるSystemExceptionResolverで致命的なエラーの除外設定をした場合、web.xmlの<error-page>に定義したシステムエラー画面に遷移することを確認する。</li>
     * </ul>
     */
    @Test
    public void testEXHN0601005() throws IOException {

        webDriverOperations.click(id("exhn0601005"));

        // web.xmlの<error-page>に定義したエラー画面に遷移することを確認(SystemExceptionResolverのdefaultErrorViewに定義した画面に遷移しないこと)
        assertThat(webDriverOperations.getTitle(), is(
                "Unhandled System Error!"));

    }

    /**
     * <ul>
     * <li>NoSuchRequestHandlingMethodException発生時にHTTPステータスが404になることを確認する。</li>
     * </ul>
     */
    @Test(expected = HttpClientErrorException.class)
    public void testEXHN0701001() throws IOException {
        try {
            restOperations.getForEntity(applicationContextUrl
                    + "/exhn/0701/001", String.class);
        } catch (HttpClientErrorException e) {
            // 404 が返却されていること
            assertThat(e.getStatusCode(), is(HttpStatus.NOT_FOUND));

            throw e;
        }
    }

    /**
     * <ul>
     * <li>HttpRequestMethodNotSupportedException発生時ににHTTPステータスが405になることを確認する。</li>
     * </ul>
     */
    @Test(expected = HttpClientErrorException.class)
    public void testEXHN0701002() throws IOException {
        try {
            restOperations.headForHeaders(applicationContextUrl
                    + "/exhn/0701/002");
        } catch (HttpClientErrorException e) {
            // 405 が返却されていること
            assertThat(e.getStatusCode(), is(HttpStatus.METHOD_NOT_ALLOWED));

            throw e;
        }
    }

    /**
     * <ul>
     * <li>HttpMediaTypeNotSupportedException発生時ににHTTPステータスが415になることを確認する。</li>
     * </ul>
     */
    @Test(expected = HttpClientErrorException.class)
    public void testEXHN0701003() throws IOException {
        try {
            restOperations.getForEntity(applicationContextUrl
                    + "/exhn/0701/003", String.class);
        } catch (HttpClientErrorException e) {
            // 415 が返却されていること
            assertThat(e.getStatusCode(), is(
                    HttpStatus.UNSUPPORTED_MEDIA_TYPE));

            throw e;
        }
    }

    /**
     * <ul>
     * <li>HttpMediaTypeNotAcceptableExceptionn発生時ににHTTPステータスが406になることを確認する。</li>
     * </ul>
     */
    @Test(expected = HttpClientErrorException.class)
    public void testEXHN0701004() throws IOException {
        try {
            restOperations.getForEntity(applicationContextUrl
                    + "/exhn/0701/004", String.class);
        } catch (HttpClientErrorException e) {
            // 406 が返却されていること
            assertThat(e.getStatusCode(), is(HttpStatus.NOT_ACCEPTABLE));

            throw e;
        }
    }

    /**
     * <ul>
     * <li>MissingServletRequestParameterException発生時ににHTTPステータスが400になることを確認する。</li>
     * </ul>
     */
    @Test(expected = HttpClientErrorException.class)
    public void testEXHN0701005() throws IOException {
        try {
            restOperations.getForEntity(applicationContextUrl
                    + "/exhn/0701/005", String.class);
        } catch (HttpClientErrorException e) {
            // 400 が返却されていること
            assertThat(e.getStatusCode(), is(HttpStatus.BAD_REQUEST));

            throw e;
        }
    }

    /**
     * <ul>
     * <li>ServletRequestBindingException発生時ににHTTPステータスが400になることを確認する。</li>
     * </ul>
     */
    @Test(expected = HttpClientErrorException.class)
    public void testEXHN0701006() throws IOException {
        try {
            restOperations.getForEntity(applicationContextUrl
                    + "/exhn/0701/006", String.class);
        } catch (HttpClientErrorException e) {
            // 400 が返却されていること
            assertThat(e.getStatusCode(), is(HttpStatus.BAD_REQUEST));

            throw e;
        }
    }

    /**
     * <ul>
     * <li>ConversionNotSupportedExceptionn発生時ににHTTPステータスが500になることを確認する。</li>
     * </ul>
     */
    @Test(expected = HttpServerErrorException.class)
    public void testEXHN0701007() throws IOException {
        try {
            restOperations.getForEntity(applicationContextUrl
                    + "/exhn/0701/007", String.class);
        } catch (HttpServerErrorException e) {
            // 500 が返却されていること
            assertThat(e.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));

            throw e;
        }
    }

    /**
     * <ul>
     * <li>TypeMismatchExceptionn発生時ににHTTPステータスが400になることを確認する。</li>
     * </ul>
     */
    @Test(expected = HttpClientErrorException.class)
    public void testEXHN0701008() throws IOException {
        try {
            restOperations.getForEntity(applicationContextUrl
                    + "/exhn/0701/008?id=a", String.class);
        } catch (HttpClientErrorException e) {
            // 400 が返却されていること
            assertThat(e.getStatusCode(), is(HttpStatus.BAD_REQUEST));

            throw e;
        }
    }

    /**
     * <ul>
     * <li>HttpMessageNotReadableException発生時ににHTTPステータスが400になることを確認する。</li>
     * </ul>
     */
    @Test(expected = HttpClientErrorException.class)
    public void testEXHN0701009() throws IOException {
        try {
            restOperations.getForEntity(applicationContextUrl
                    + "/exhn/0701/009", String.class);
        } catch (HttpClientErrorException e) {
            // 400 が返却されていること
            assertThat(e.getStatusCode(), is(HttpStatus.BAD_REQUEST));

            throw e;
        }
    }

    /**
     * <ul>
     * <li>HttpMessageNotWritableException発生時ににHTTPステータスが500になることを確認する。</li>
     * </ul>
     */
    @Test(expected = HttpServerErrorException.class)
    public void testEXHN0701010() throws IOException {
        try {
            restOperations.getForEntity(applicationContextUrl
                    + "/exhn/0701/010", String.class);
        } catch (HttpServerErrorException e) {
            // 500 が返却されていること
            assertThat(e.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));

            throw e;
        }
    }

    /**
     * <ul>
     * <li>MethodArgumentNotValidException発生時ににHTTPステータスが400になることを確認する。</li>
     * </ul>
     */
    @Test(expected = HttpClientErrorException.class)
    public void testEXHN0701011() throws IOException {
        try {
            restOperations.getForEntity(applicationContextUrl
                    + "/exhn/0701/011", String.class);
        } catch (HttpClientErrorException e) {
            // 400 が返却されていること
            assertThat(e.getStatusCode(), is(HttpStatus.BAD_REQUEST));

            throw e;
        }
    }

    /**
     * <ul>
     * <li>MissingServletRequestPartException発生時ににHTTPステータスが400になることを確認する。</li>
     * </ul>
     */
    @Test(expected = HttpClientErrorException.class)
    public void testEXHN0701012() throws IOException {
        try {
            restOperations.getForEntity(applicationContextUrl
                    + "/exhn/0701/012", String.class);
        } catch (HttpClientErrorException e) {
            // 400 が返却されていること
            assertThat(e.getStatusCode(), is(HttpStatus.BAD_REQUEST));

            throw e;
        }
    }

    /**
     * <ul>
     * <li>BindException発生時ににHTTPステータスが400になることを確認する。</li>
     * </ul>
     */
    @Test(expected = HttpClientErrorException.class)
    public void testEXHN0701013() throws IOException {
        try {
            restOperations.getForEntity(applicationContextUrl
                    + "/exhn/0701/013", String.class);
        } catch (HttpClientErrorException e) {
            // 400 が返却されていること
            assertThat(e.getStatusCode(), is(HttpStatus.BAD_REQUEST));

            throw e;
        }
    }

    /**
     * <ul>
     * <li>Handlerが見つからない場合にNoHandlerFoundException発生することを確認する。</li>
     * <li>NoHandlerFoundException発生時にHTTPステータスが404になることを確認する。</li>
     * </ul>
     */
    @Test(expected = HttpClientErrorException.class)
    public void testEXHN0701014() throws IOException {
        try {
            // 存在しないパスを指定する。
            restOperations.getForEntity(applicationContextUrl
                    + "/exhn/0701/014", String.class);

            Assert.fail();
        } catch (HttpClientErrorException e) {
            // 404 が返却されていること
            assertThat(e.getStatusCode(), is(HttpStatus.NOT_FOUND));

            // サーバでNoHandlerFoundExceptionをキャッチしていること。
            dbLogAssertOperations.waitForAssertion(100);
            dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                    "org.terasoluna.gfw.common.exception.ExceptionLogger",
                    "No handler found for .*",
                    "org\\.springframework\\.web\\.servlet\\.NoHandlerFoundException\\.*");

            throw e;
        }
    }

    /**
     * <ul>
     * <li>throwExceptionIfNoHandlerFoundプロパティが設定されたDispatcherServletで、正常にリスクエストが処理されるかを確認する。</li>
     * </ul>
     */
    @Test
    public void testEXHN0701015() throws IOException {
        try {
            ResponseEntity<String> res = restOperations.getForEntity(
                    applicationContextUrl + "/exhn/0701/015", String.class);

            assertThat(res.getStatusCode(), is(HttpStatus.OK));
        } catch (HttpClientErrorException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * <ul>
     * <li>HandlerExceptionResolverLoggingInterceptorを拡張し、ログレベルの判定ロジックを変えられる事を確認する。</li>
     * </ul>
     */
    @Test
    public void testEXHN0801001() throws IOException {

        webDriverOperations.click(id("exhn0801001"));

        // ERROR Level Log
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessageAndLevelsAndLogger(
                "\\[e.sf.cmmn.9001\\] Runtime Exception occurred!!", "ERROR",
                "org.terasoluna.gfw.common.exception.ExceptionLogger");
        dbLogAssertOperations.assertContainsByRegexMessageAndLevelsAndLogger(
                "\\[e.sf.cmmn.9001\\] Runtime Exception occurred!!", "ERROR",
                "org.terasoluna.gfw.common.exception.ExceptionLogger.Monitoring");

    }

    @After
    public void deleteTempDirectoryAndFiles() {
        restOperations.getForEntity(applicationContextUrl + "/exhn/delete/all",
                void.class);
    }
}
