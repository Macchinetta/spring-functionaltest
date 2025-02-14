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
package jp.co.ntt.fw.spring.functionaltest.selenium.cspr;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;
import java.io.IOException;
import org.junit.Test;
import org.openqa.selenium.By;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

public class CSRFProtection_JSP_Test extends FunctionTestSupport {

    private static String VIEW_TYPE = "jsp";

    @Inject
    protected RestTemplate restTemplate;

    /**
     * <ul>
     * <li>CSRFトークンチェックを無効にした場合、CSRFトークンチェックが行われないことを確認する。</li>
     * </ul>
     **/
    @Test
    public void testCSPR0101001() throws IOException {

        webDriverOperations.click(id("cspr0101001_" + VIEW_TYPE));

        // 登録画面に遷移したことをチェック
        webDriverOperations.waitForDisplayed(textToBe(id("screenTitle"), "ユーザ登録画面(CSRF無効)"));

        webDriverOperations.appendText(id("userName"), "山田 太郎");
        webDriverOperations.appendText(id("email"), "yamada_tarou@example.com");
        webDriverOperations.appendText(id("password"), "spring12345");
        webDriverOperations.appendText(id("confirmPassword"), "spring12345");

        // セッションを削除
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Cookie",
                "JSESSIONID=" + webDriverOperations.getCookie("JSESSIONID").getValue());
        restTemplate.exchange(getPackageRootUrl() + "/deleteSession", HttpMethod.GET,
                new HttpEntity<byte[]>(requestHeaders), byte[].class);

        webDriverOperations.saveScreenCapture();

        webDriverOperations.click(id("confirm"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("ユーザ登録確認画面(CSRF無効)"));
    }

    /**
     * <ul>
     * <li>CSRFトークンを自動で埋め込んだ場合、リクエストを送信できることを確認。</li>
     * </ul>
     **/
    @Test
    public void testCSPR0201001() throws IOException {

        webDriverOperations.click(id("cspr0201001_" + VIEW_TYPE));

        // 登録画面に遷移したことをチェック
        webDriverOperations.waitForDisplayed(textToBe(id("screenTitle"), "ユーザ登録画面"));

        webDriverOperations.appendText(id("userName"), "山田 太郎");
        webDriverOperations.appendText(id("email"), "yamada_tarou@example.com");
        webDriverOperations.appendText(id("password"), "spring12345");
        webDriverOperations.appendText(id("confirmPassword"), "spring12345");

        // CSRFトークンが設定されていることを確認
        String csrfToken = webDriverOperations.getJavascriptExecutor()
                .executeScript("return document.getElementsByName('_csrf')[1].value").toString();
        assertThat(csrfToken,
                matchesPattern("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}"));

        webDriverOperations.click(id("confirm"));

        // 確認画面に遷移したことをチェック
        webDriverOperations.waitForDisplayed(textToBe(id("screenTitle"), "ユーザ登録確認画面"));

    }

    /**
     * <ul>
     * <li>CSRFトークンを明示的に埋め込んだ場合、リクエストを送信できることを確認。</li>
     * </ul>
     **/
    @Test
    public void testCSPR0201002() throws IOException {

        webDriverOperations.click(id("cspr0201002_" + VIEW_TYPE));

        // 登録画面に遷移したことをチェック
        webDriverOperations
                .waitForDisplayed(textToBe(id("screenTitle"), "ユーザ登録画面(html標準のformタグを使用/POST)"));

        webDriverOperations.appendText(id("userName"), "山田 太郎");
        webDriverOperations.appendText(id("email"), "yamada_tarou@example.com");
        webDriverOperations.appendText(id("password"), "spring12345");
        webDriverOperations.appendText(id("confirmPassword"), "spring12345");

        // CSRFトークンが設定されていることを確認
        String csrfToken = webDriverOperations.getJavascriptExecutor()
                .executeScript("return document.getElementsByName('_csrf')[1].value").toString();
        assertThat(csrfToken,
                matchesPattern("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}"));

        webDriverOperations.click(id("confirm"));

        // 確認画面に遷移したことをチェック
        webDriverOperations
                .waitForDisplayed(textToBe(id("screenTitle"), "ユーザ登録確認画面(html標準のformタグを使用/POST)"));

    }

    /**
     * <ul>
     * <li>htmlのformタグを使用する場合、アドレスバーにCSRFトークンの値を出さないでリクエストを送信できることを確認。</li>
     * </ul>
     **/
    @Test
    public void testCSPR0201003() throws IOException {

        webDriverOperations.click(id("cspr0201003_" + VIEW_TYPE));

        // 登録画面に遷移したことをチェック
        webDriverOperations
                .waitForDisplayed(textToBe(id("screenTitle"), "ユーザ登録画面(html標準のformタグを使用/GET)"));

        webDriverOperations.appendText(id("userName"), "山田 太郎");
        webDriverOperations.appendText(id("email"), "yamada_tarou@example.com");
        webDriverOperations.appendText(id("password"), "spring12345");
        webDriverOperations.appendText(id("confirmPassword"), "spring12345");

        webDriverOperations.saveScreenCapture();

        webDriverOperations.click(id("confirm"));

        // 確認画面に遷移したことをチェック
        webDriverOperations
                .waitForDisplayed(textToBe(id("screenTitle"), "ユーザ登録確認画面(html標準のformタグを使用/GET)"));

        // _csrf がURLに含まれていないこと
        assertThat(webDriverOperations.getCurrentUrl(), is(not(containsString("_csrf"))));

    }

    /**
     * <ul>
     * <li>springのformタグを使用する場合、アドレスバーにCSRFトークンの値を出さないでリクエストを送信できることを確認。</li>
     * </ul>
     **/
    @Test
    public void testCSPR0201004() throws IOException {

        webDriverOperations.click(id("cspr0201004_" + VIEW_TYPE));

        // 登録画面に遷移したことをチェック
        webDriverOperations
                .waitForDisplayed(textToBe(id("screenTitle"), "ユーザ登録画面(Springのformタグを使用)"));

        webDriverOperations.appendText(id("userName"), "山田 太郎");
        webDriverOperations.appendText(id("email"), "yamada_tarou@example.com");
        webDriverOperations.appendText(id("password"), "spring12345");
        webDriverOperations.appendText(id("confirmPassword"), "spring12345");

        webDriverOperations.saveScreenCapture();

        webDriverOperations.click(id("confirm"));

        // 確認画面に遷移したことをチェック
        webDriverOperations
                .waitForDisplayed(textToBe(id("screenTitle"), "ユーザ登録確認画面(Springのformタグを使用)"));

        // _csrf がURLに含まれていないこと
        assertThat(webDriverOperations.getCurrentUrl(), is(not(containsString("_csrf"))));

    }

    /**
     * <ul>
     * <li>springのformタグを使用する場合、アドレスバーにCSRFトークンの値を出さないでリクエストを送信できることを確認。<br>
     * 別途独自のspring-mvc.xmlを用意し、この試験実行時のみ独自のspring-mvc.xmlを適用すること。</li>
     * </ul>
     **/
    @Test
    public void testCSPR0201005() throws IOException {

        webDriverOperations.click(id("cspr0201005_" + VIEW_TYPE));

        // 登録画面に遷移したことをチェック
        webDriverOperations
                .waitForDisplayed(textToBe(id("screenTitle"), "ユーザ登録画面(Springのformタグを使用)"));

        webDriverOperations.appendText(id("userName"), "山田 太郎");
        webDriverOperations.appendText(id("email"), "yamada_tarou@example.com");
        webDriverOperations.appendText(id("password"), "spring12345");
        webDriverOperations.appendText(id("confirmPassword"), "spring12345");

        webDriverOperations.saveScreenCapture();

        webDriverOperations.click(id("confirm"));

        // 確認画面に遷移したことをチェック
        webDriverOperations
                .waitForDisplayed(textToBe(id("screenTitle"), "ユーザ登録確認画面(Springのformタグを使用)"));

        // _csrf がURLに含まれていないこと
        assertThat(webDriverOperations.getCurrentUrl(), is(not(containsString("_csrf"))));

    }

    /**
     * <ul>
     * <li>CSRFトークンを改ざんして埋め込んだ場合、CSRFトークンチェックエラーになることを確認。</li>
     * </ul>
     **/
    @Test
    public void testCSPR0201006() throws IOException {

        webDriverOperations.click(id("cspr0201006_" + VIEW_TYPE));

        // 登録画面に遷移したことをチェック
        webDriverOperations.waitForDisplayed(textToBe(id("screenTitle"), "ユーザ登録画面"));

        webDriverOperations.appendText(id("userName"), "山田 太郎");
        webDriverOperations.appendText(id("email"), "yamada_tarou@example.com");
        webDriverOperations.appendText(id("password"), "spring12345");
        webDriverOperations.appendText(id("confirmPassword"), "spring12345");

        // CSRFトークンを書き換え
        webDriverOperations.getJavascriptExecutor().executeScript(
                "document.getElementsByName('_csrf')[1].setAttribute('type', 'text');");
        webDriverOperations.getJavascriptExecutor().executeScript(
                "document.getElementsByName('_csrf')[1].value = '123456abc9876abc';");

        webDriverOperations.saveScreenCapture();

        webDriverOperations.click(id("confirm"));

        // CSRFトークンエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("invalid CSRF Error!"));

        try {
            MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
            map.add("_csrf", "123456abc9876abc");

            restOperations.postForObject(getPackageRootUrl(), map, String.class);
        } catch (HttpClientErrorException e) {
            // ステータスコード 403が返却されることを確認。
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        }
    }

    /**
     * <ul>
     * <li>セッションタイムアウトになった場合、CSRFトークンチェックエラーになることを確認。</li>
     * </ul>
     **/
    @Test
    public void testCSPR0201007() throws IOException {

        webDriverOperations.click(id("cspr0201007_" + VIEW_TYPE));

        // 登録画面に遷移したことをチェック
        webDriverOperations.waitForDisplayed(textToBe(id("screenTitle"), "ユーザ登録画面"));

        webDriverOperations.appendText(id("userName"), "山田 太郎");
        webDriverOperations.appendText(id("email"), "yamada_tarou@example.com");
        webDriverOperations.appendText(id("password"), "spring12345");
        webDriverOperations.appendText(id("confirmPassword"), "spring12345");

        // セッションを削除
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Cookie",
                "JSESSIONID=" + webDriverOperations.getCookie("JSESSIONID").getValue());
        restTemplate.exchange(getPackageRootUrl() + "/deleteSession", HttpMethod.GET,
                new HttpEntity<byte[]>(requestHeaders), byte[].class);

        webDriverOperations.saveScreenCapture();

        webDriverOperations.click(id("confirm"));

        // missing CSRF Token Error画面に遷移
        assertThat(webDriverOperations.getTitle(), is("missing CSRF Token Error!"));

        try {
            MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
            map.add("_csrf", "123456abc9876abc");

            restOperations.postForObject(getPackageRootUrl(), map, String.class);
        } catch (HttpClientErrorException e) {
            // ステータスコード 403が返却されることを確認。
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        }
    }

    /**
     * <ul>
     * <li>CSRFトークンを改ざんして埋め込んだ場合、CSRFトークンチェックエラーになることを確認。</li>
     * <li>deniedHandlerを利用しない場合、ステータスコードに従ったエラー画面に遷移することを確認。</li>
     * </ul>
     **/
    @Test
    public void testCSPR0201008() throws IOException {

        webDriverOperations.click(id("cspr0201008_" + VIEW_TYPE));

        // 登録画面に遷移したことをチェック
        webDriverOperations
                .waitForDisplayed(textToBe(id("screenTitle"), "ユーザ登録画面(DeniedHandler未設定)"));

        webDriverOperations.appendText(id("userName"), "山田 太郎");
        webDriverOperations.appendText(id("email"), "yamada_tarou@example.com");
        webDriverOperations.appendText(id("password"), "spring12345");
        webDriverOperations.appendText(id("confirmPassword"), "spring12345");

        // CSRFトークンを書き換え
        webDriverOperations.getJavascriptExecutor().executeScript(
                "document.getElementsByName('_csrf')[1].setAttribute('type', 'text');");
        webDriverOperations.getJavascriptExecutor().executeScript(
                "document.getElementsByName('_csrf')[1].value = '123456abc9876abc';");

        webDriverOperations.saveScreenCapture();

        webDriverOperations.click(id("confirmNotUseDeniedHandler"));

        // CSRFトークンエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("forbidden Error!"));

        try {
            MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
            map.add("_csrf", "123456abc9876abc");

            restOperations.postForObject(getPackageRootUrl(), map, String.class);
        } catch (HttpClientErrorException e) {
            // ステータスコード 403が返却されることを確認。
            assertThat(e.getStatusCode(), is(HttpStatus.FORBIDDEN));
        }
    }

    /**
     * <ul>
     * <li>Ajaxを利用してHTTPリクエストヘッダーからCSRFトークンを取得する場合、POSTで送信することができることを確認。</li>
     * </ul>
     **/
    @Test
    public void testCSPR0301001() throws IOException {

        webDriverOperations.click(id("cspr0301001_" + VIEW_TYPE));

        webDriverOperations.overrideText(id("username"), "yamadat");
        webDriverOperations.overrideText(id("password"), "spring1234");
        webDriverOperations.click(id("committerLogin"));

        webDriverOperations.overrideText(id("email"), "spring_yamada@example.com");
        webDriverOperations.overrideText(id("url"), "http://example.com/yamada");
        webDriverOperations.overrideText(id("company"), "spring con.inc");
        webDriverOperations.overrideText(id("location"), "埼玉");

        webDriverOperations.click(id("edit"));

        webDriverOperations.saveScreenCapture();

        // 要素が見つかるまでアサーションを待つ
        webDriverOperations.waitForDisplayed(By.xpath("//div[2]/div/div[2]/ul/li"));

        // 取得結果が正常に返却されていることを確認
        assertThat(webDriverOperations.getText(By.xpath("//div[2]/div/div[2]/ul/li")),
                is("アカウントの更新が成功しました。"));

        // データ戻し
        webDriverOperations.click(id("springTestTop"));
        webDriverOperations.click(id("cspr0301001_" + VIEW_TYPE));

        webDriverOperations.overrideText(id("email"), "yamada_tarou@example.com");
        webDriverOperations.overrideText(id("url"), "http://example.com/");
        webDriverOperations.overrideText(id("company"), "Spring Test.Inc");
        webDriverOperations.overrideText(id("location"), "東京 豊洲");

        webDriverOperations.click(id("edit"));

        // ログアウト
        webDriverOperations.click(id("logoutButton"));

    }

    /**
     * <ul>
     * <li>Ajaxを利用してHTTPリクエストヘッダーからCSRFトークンを取得する場合、GETで送信することができることを確認。</li>
     * </ul>
     **/
    @Test
    public void testCSPR0301002() throws IOException {

        webDriverOperations.click(id("cspr0301002_" + VIEW_TYPE));

        webDriverOperations.appendText(id("username"), "yamada");
        webDriverOperations.click(id("searchBtn"));

        // 要素が見つかるまでアサーションを待つ
        webDriverOperations.waitForDisplayed(By.xpath("//td"));

        // 取得結果が正常に返却されていることを確認
        assertThat(webDriverOperations.getText(By.xpath("//td")), is("1"));
        assertThat(webDriverOperations.getText(By.xpath("//td[2]")), is("yamadah"));
        assertThat(webDriverOperations.getText(By.xpath("//td[3]")),
                is("yamada_hanako@example.com"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td")), is("2"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[2]")), is("yamadat"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[3]")),
                is("yamada_tarou@example.com"));

    }

    /**
     * <ul>
     * <li>Ajaxを利用してHTTPリクエストヘッダーから改ざんしたCSRFトークンを取得する場合、POSTで送信後、エラーになることを確認。</li>
     * </ul>
     **/
    @Test
    public void testCSPR0301003() throws IOException {

        webDriverOperations.click(id("cspr0301003_" + VIEW_TYPE));

        webDriverOperations.overrideText(id("username"), "yamadat");
        webDriverOperations.overrideText(id("password"), "spring1234");
        webDriverOperations.click(id("committerLogin"));

        webDriverOperations.overrideText(id("email"), "spring_yamada@example.com");
        webDriverOperations.overrideText(id("url"), "http://example.com/yamada");
        webDriverOperations.overrideText(id("company"), "spring con.inc");
        webDriverOperations.overrideText(id("location"), "埼玉");

        // CSRFTokenの書き換え
        webDriverOperations.getJavascriptExecutor().executeScript(
                "document.getElementsByName('_csrf')[0].setAttribute('content', '123456abc9876abc');");

        webDriverOperations.click(id("edit"));

        // 要素が見つかるまでアサーションを待つ
        webDriverOperations.waitForDisplayed(By.xpath("//div[2]/div/div[2]/ul/li"));

        // CSRFトークンエラーとなっていることを確認
        assertThat(webDriverOperations.getText(id("returnStatus")), is("ステータスコード:403"));
        assertThat(webDriverOperations.getText(By.xpath("//div[2]/div/div[2]/ul/li")),
                is("CSRF攻撃を検出しました！"));

        webDriverOperations.saveScreenCapture();

        // ログアウト
        webDriverOperations.click(id("logoutButton"));
    }

    /**
     * <ul>
     * <li>Ajaxを利用してHTTPリクエストヘッダーから改ざんしたCSRFトークンを取得する場合、GETで送信することができることを確認。</li>
     * </ul>
     **/
    @Test
    public void testCSPR0301004() throws IOException {

        webDriverOperations.click(id("cspr0301004_" + VIEW_TYPE));

        // CSRFTokenの書き換え
        webDriverOperations.getJavascriptExecutor().executeScript(
                "document.getElementsByName('_csrf')[0].setAttribute('content', '123456abc9876abc');");

        webDriverOperations.appendText(id("username"), "yamada");
        webDriverOperations.click(id("searchBtn"));

        // 要素が見つかるまでアサーションを待つ
        webDriverOperations.waitForDisplayed(By.xpath("//td"));

        // 取得結果が正常に返却されていることを確認
        assertThat(webDriverOperations.getText(By.xpath("//td")), is("1"));
        assertThat(webDriverOperations.getText(By.xpath("//td[2]")), is("yamadah"));
        assertThat(webDriverOperations.getText(By.xpath("//td[3]")),
                is("yamada_hanako@example.com"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td")), is("2"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[2]")), is("yamadat"));
        assertThat(webDriverOperations.getText(By.xpath("//tr[2]/td[3]")),
                is("yamada_tarou@example.com"));

    }

    /**
     * <ul>
     * <li>マルチパートリクエストを送信する場合、MultipartFilterを使用して、ファイルアップロードできることを確認。</li>
     * </ul>
     **/
    @Test
    public void testCSPR0401001() throws IOException {

        webDriverOperations.click(id("cspr0401001_" + VIEW_TYPE));

        webDriverOperations.referUploadFile(id("multipartFile"),
                new ClassPathResource("testdata/cspr/customerList.csv").getFile());

        // CSRFトークンが設定されていることを確認
        String csrfToken = webDriverOperations.getJavascriptExecutor()
                .executeScript("return document.getElementsByName('_csrf')[1].value").toString();
        assertThat(csrfToken,
                matchesPattern("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}"));

        webDriverOperations.click(id("uploadButton"));

        // 完了画面に遷移したことをチェック
        webDriverOperations.waitForDisplayed(textToBe(id("screenTitle"), "顧客一括登録完了画面"));

    }

    /**
     * <ul>
     * <li>マルチパートリクエストを送信する場合、クエリパラメータでCSRFトークンを送信して、ファイルアップロードできることを確認。</li>
     * </ul>
     **/
    @Test
    public void testCSPR0401002() throws IOException {

        webDriverOperations.click(id("cspr0401002_" + VIEW_TYPE));

        webDriverOperations.referUploadFile(id("multipartFile"),
                new ClassPathResource("testdata/cspr/customerList.csv").getFile());

        webDriverOperations.saveScreenCapture();
        webDriverOperations.click(id("uploadButton"));

        // クエリパラメータにCSRFTokenが設定されていることを確認
        assertThat(webDriverOperations.getCurrentUrl(), is(containsString("_csrf")));

        // 完了画面に遷移したことをチェック
        webDriverOperations.waitForDisplayed(textToBe(id("screenTitle"), "顧客一括登録完了画面"));

    }
}
