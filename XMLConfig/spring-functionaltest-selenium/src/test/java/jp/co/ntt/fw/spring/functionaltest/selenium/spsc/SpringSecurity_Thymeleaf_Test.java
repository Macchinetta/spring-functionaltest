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
package jp.co.ntt.fw.spring.functionaltest.selenium.spsc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

import java.io.IOException;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

public class SpringSecurity_Thymeleaf_Test extends FunctionTestSupport {

    @Inject
    protected RestTemplate restTemplate;

    private static String VIEW_TYPE = "thymeleaf";

    /**
     * <ul>
     * <li>Spring Securityの最小構成によるデフォルト動作の確認</li>
     * <li>Sepring Securityの最小構成でデフォルトのログインフォームが表示されること</li>
     * </ul>
     **/
    @Test
    public void testSPSC0101001() throws IOException {

        // メニュー画面の操作
        // TODO:
        // GitLabの環境でclickを使用してSpringSecurityのデフォルトのログイン画面を表示しようとするとTimeOutExceptionが発生する
        // 暫定的にforceClickを使用して回避している。
        // https://github.com/SeleniumHQ/selenium/issues/9528 で検討中のため対応され次第取り込む
        webDriverOperations.forceClick(id("spsc0101001_" + VIEW_TYPE));

        webDriverOperations.waitForDisplayed(textToBe(By.xpath("//h2"),
                "Please sign in"));

        // デフォルトのログイン画面の確認
        // ユーザ
        assert (webDriverOperations.exists(name("username")));
        // パスワード
        assert (webDriverOperations.exists(name("password")));
        // パス
        assertThat(webDriverOperations.getCurrentUrl(), is(getPackageRootUrl()
                + "/login"));

    }

    /**
     * <ul>
     * <li>spring securityのHTTPヘッダ付与機能の確認</li>
     * <li>Spring Securityがデフォルトで設定する以下のヘッダが出力されること。
     * <ul>
     * <li>Cache-Control (Pragma, Expires)</li>
     * <li>X-Frame-Options</li>
     * <li>X-Content-Type-Options</li>
     * <li>X-XSS-Protection</li>
     * <li>Strict-Transport-Security</li>
     * </ul>
     * </li>
     * </ul>
     *
     * <pre>
     * <sec:headers/>
     * </pre>
     **/
    @Test
    public void testSPSC0201001() throws IOException {

        // レスポンスヘッダを取得
        HttpHeaders requestHeaders = new HttpHeaders();
        ResponseEntity<byte[]> entity = restTemplate.exchange(
                getPackageRootUrl() + "/" + VIEW_TYPE + "/0201/001",
                HttpMethod.GET, new HttpEntity<byte[]>(requestHeaders),
                byte[].class);

        HttpHeaders requestHeaders2 = entity.getHeaders();
        Map<String, String> resultMap = requestHeaders2.toSingleValueMap();

        // レスポンスヘッダを確認
        assertThat(resultMap.get("Cache-Control"), is(
                "no-cache, no-store, max-age=0, must-revalidate"));
        assertThat(resultMap.get("Pragma"), is("no-cache"));
        assertThat(resultMap.get("Expires"), is("0"));
        assertThat(resultMap.get("X-Content-Type-Options"), is("nosniff"));
        assertThat(resultMap.get("X-Frame-Options"), is("DENY"));
        assertThat(resultMap.get("X-XSS-Protection"), is("0"));
        assertNull(resultMap.get("Content-Security-Policy"));
        assertNull(resultMap.get("Content-Security-Policy-Report-Only"));
        assertNull(resultMap.get("Referrer-Policy"));
        assertNull(resultMap.get("Permissions-Policy"));
        assertNull(resultMap.get("Clear-Site-Data"));
        assertNull(resultMap.get("Feature-Policy"));
        assertNull(resultMap.get("Public-Key-Pins"));
        assertNull(resultMap.get("Public-Key-Pins-Report-Only"));
    }

    /**
     * <ul>
     * <li>spring securityのHTTPヘッダ付与機能の確認</li>
     * <li>セキュリティヘッダがすべて無効化されていること。</li>
     * </ul>
     *
     * <pre>
     * <sec:headers disabled="true"/>
     * </pre>
     **/
    @Test
    public void testSPSC0201002() throws IOException {

        // レスポンスヘッダを取得
        HttpHeaders requestHeaders = new HttpHeaders();
        ResponseEntity<byte[]> entity = restTemplate.exchange(
                getPackageRootUrl() + "/" + VIEW_TYPE + "/0201/002",
                HttpMethod.GET, new HttpEntity<byte[]>(requestHeaders),
                byte[].class);

        HttpHeaders requestHeaders2 = entity.getHeaders();
        Map<String, String> resultMap = requestHeaders2.toSingleValueMap();

        // レスポンスヘッダを確認
        assertNull(resultMap.get("Cache-Control"));
        assertNull(resultMap.get("Pragma"));
        assertNull(resultMap.get("Expires"));
        assertNull(resultMap.get("X-Content-Type-Options"));
        assertNull(resultMap.get("X-Frame-Options"));
        assertNull(resultMap.get("X-XSS-Protection"));
        assertNull(resultMap.get("Content-Security-Policy"));
        assertNull(resultMap.get("Content-Security-Policy-Report-Only"));
        assertNull(resultMap.get("Referrer-Policy"));
        assertNull(resultMap.get("Permissions-Policy"));
        assertNull(resultMap.get("Clear-Site-Data"));
        assertNull(resultMap.get("Feature-Policy"));
        assertNull(resultMap.get("Public-Key-Pins"));
        assertNull(resultMap.get("Public-Key-Pins-Report-Only"));
    }

    /**
     * <ul>
     * <li>spring securityのHTTPヘッダ付与機能の確認</li>
     * <li>Spring SEcurityがデフォルトで設定するヘッダが出力されないこと。</li>
     * </ul>
     *
     * <pre>
     * <sec:headers defaults-disabled="true">
     * </pre>
     **/
    @Ignore("デプロイエラーとなるため試験不可")
    @Test
    public void testSPSC0201003() throws IOException {

        // レスポンスヘッダを取得
        HttpHeaders requestHeaders = new HttpHeaders();
        ResponseEntity<byte[]> entity = restTemplate.exchange(
                getPackageRootUrl() + "/" + VIEW_TYPE + "/0201/003",
                HttpMethod.GET, new HttpEntity<byte[]>(requestHeaders),
                byte[].class);

        HttpHeaders requestHeaders2 = entity.getHeaders();
        Map<String, String> resultMap = requestHeaders2.toSingleValueMap();

        // レスポンスヘッダを確認
        assertNull(resultMap.get("Cache-Control"));
        assertNull(resultMap.get("Pragma"));
        assertNull(resultMap.get("Expires"));
        assertNull(resultMap.get("X-Content-Type-Options"));
        assertNull(resultMap.get("X-Frame-Options"));
        assertNull(resultMap.get("X-XSS-Protection"));
        assertNull(resultMap.get("Content-Security-Policy"));
        assertNull(resultMap.get("Content-Security-Policy-Report-Only"));
        assertNull(resultMap.get("Referrer-Policy"));
        assertNull(resultMap.get("Permissions-Policy"));
        assertNull(resultMap.get("Clear-Site-Data"));
        assertNull(resultMap.get("Feature-Policy"));
        assertNull(resultMap.get("Public-Key-Pins"));
        assertNull(resultMap.get("Public-Key-Pins-Report-Only"));
    }

    /**
     * <ul>
     * <li>spring securityのHTTPヘッダ付与機能の確認</li>
     * <li>要素を付与したとき、そのヘッダーだけできること</li>
     *
     * <pre>
     *  <sec:headers defaults-disabled="true">
     *    <sec:cache-control />
     *    <sec:frame-options />
     *    <sec:content-type-options />
     *    <sec:hsts />
     *    <sec:content-security-policy policy-directives="default-src 'self'"/>
     *    <sec:referrer-policy/>
     *    <sec:permissions-policy policy="geolocation=(self)" />
     *  </sec:headers>
     * </pre>
     * </ul>
     **/
    @Test
    public void testSPSC0201004() throws IOException {

        // レスポンスヘッダを取得
        HttpHeaders requestHeaders = new HttpHeaders();
        ResponseEntity<byte[]> entity = restTemplate.exchange(
                getPackageRootUrl() + "/" + VIEW_TYPE + "/0201/004",
                HttpMethod.GET, new HttpEntity<byte[]>(requestHeaders),
                byte[].class);

        HttpHeaders requestHeaders2 = entity.getHeaders();
        Map<String, String> resultMap = requestHeaders2.toSingleValueMap();

        // レスポンスヘッダを確認
        assertThat(resultMap.get("Cache-Control"), is(
                "no-cache, no-store, max-age=0, must-revalidate"));
        assertThat(resultMap.get("Pragma"), is("no-cache"));
        assertThat(resultMap.get("Expires"), is("0"));
        assertThat(resultMap.get("X-Content-Type-Options"), is("nosniff"));
        assertThat(resultMap.get("X-Frame-Options"), is("DENY"));
        assertThat(resultMap.get("Content-Security-Policy"), is(
                "default-src 'self'"));
        assertNull(resultMap.get("Content-Security-Policy-Report-Only"));
        assertThat(resultMap.get("Referrer-Policy"), is("no-referrer"));
        assertThat(resultMap.get("Permissions-Policy"), is("geolocation=(self)"));
        assertNull(resultMap.get("Feature-Policy"));
        assertNull(resultMap.get("Public-Key-Pins"));
        assertNull(resultMap.get("Public-Key-Pins-Report-Only"));
    }

    /**
     * <ul>
     * <li>spring securityのHTTPヘッダ付与機能の確認</li>
     * <li>要素に属性を付与して、そのヘッダーが正しくできること</li>
     *
     * <pre>
     * <sec:frame-options policy="SAMEORIGIN" />
     * </pre>
     * </ul>
     **/
    @Test
    public void testSPSC0201006() throws IOException {

        // レスポンスヘッダを取得
        HttpHeaders requestHeaders = new HttpHeaders();
        ResponseEntity<byte[]> entity = restTemplate.exchange(
                getPackageRootUrl() + "/" + VIEW_TYPE + "/0201/006",
                HttpMethod.GET, new HttpEntity<byte[]>(requestHeaders),
                byte[].class);

        HttpHeaders requestHeaders2 = entity.getHeaders();
        Map<String, String> resultMap = requestHeaders2.toSingleValueMap();

        // レスポンスヘッダを確認
        assertThat(resultMap.get("X-Frame-Options"), is("SAMEORIGIN"));

    }

    /**
     * <ul>
     * <li>spring securityのHTTPヘッダ付与機能の確認</li>
     * <li>不要なものだけ無効化出来ること</li>
     *
     * <pre>
     * <sec:cache-control disabled="true"/>
     * </pre>
     * </ul>
     **/
    @Test
    public void testSPSC0201007() throws IOException {

        // レスポンスヘッダを取得
        HttpHeaders requestHeaders = new HttpHeaders();
        ResponseEntity<byte[]> entity = restTemplate.exchange(
                getPackageRootUrl() + "/" + VIEW_TYPE + "/0201/007",
                HttpMethod.GET, new HttpEntity<byte[]>(requestHeaders),
                byte[].class);

        HttpHeaders requestHeaders2 = entity.getHeaders();
        Map<String, String> resultMap = requestHeaders2.toSingleValueMap();

        // レスポンスヘッダを確認
        assertNull(resultMap.get("Cache-Control"));
    }

    /**
     * <ul>
     * <li>spring securityのHTTPヘッダ付与機能の確認</li>
     * <li>要素にカスタムヘッダを付与して、そのヘッダーが正しく取得できること</li>
     *
     * <pre>
     * <sec:header name="X-WebKit-CSP" value="default-src 'self'"/>
     * </pre>
     * </ul>
     **/
    @Test
    public void testSPSC0301001() throws IOException {

        // レスポンスヘッダを取得
        HttpHeaders requestHeaders = new HttpHeaders();
        ResponseEntity<byte[]> entity = restTemplate.exchange(
                getPackageRootUrl() + "/" + VIEW_TYPE + "/0301/001",
                HttpMethod.GET, new HttpEntity<byte[]>(requestHeaders),
                byte[].class);

        HttpHeaders requestHeaders2 = entity.getHeaders();
        Map<String, String> resultMap = requestHeaders2.toSingleValueMap();

        // レスポンスヘッダを確認
        assertThat(resultMap.get("X-WebKit-CSP"), is("default-src 'self'"));

    }

    /**
     * <ul>
     * <li>spring securityのHTTPヘッダ付与機能の確認</li>
     * <li>要素にカスタムヘッダを付与して、そのヘッダーが正しく取得できること</li>
     *
     * <pre>
     * <sec:header name="X-WebKit-CSP" value="default-src 'self'"/>
     * </pre>
     * </ul>
     **/
    @Ignore("リクエストパスがJSPパスに変更されたタイミングでパスマッチングを行い失敗するため実施不可(issues#93)")
    @Test
    public void testSPSC0401001() throws IOException {

        // レスポンスヘッダを取得
        HttpHeaders requestHeaders = new HttpHeaders();
        ResponseEntity<byte[]> entity = restTemplate.exchange(
                getPackageRootUrl() + "/" + VIEW_TYPE
                        + "/0401/001/notsecure/001", HttpMethod.GET,
                new HttpEntity<byte[]>(requestHeaders), byte[].class);

        HttpHeaders requestHeaders2 = entity.getHeaders();
        Map<String, String> resultMap = requestHeaders2.toSingleValueMap();

        // キャッシュコントロールされていないレスポンスヘッダを確認
        assertNull(resultMap.get("Cache-Control"));
        assertNull(resultMap.get("Pragma"));
        assertNull(resultMap.get("Expires"));

        entity = restTemplate.exchange(getPackageRootUrl() + "/" + VIEW_TYPE
                + "/0401/001/secure/001", HttpMethod.GET,
                new HttpEntity<byte[]>(requestHeaders), byte[].class);

        requestHeaders2 = entity.getHeaders();
        resultMap = requestHeaders2.toSingleValueMap();

        // キャッシュコントロールされているレスポンスヘッダを確認
        assertThat(resultMap.get("Cache-Control"), is(
                "no-cache, no-store, max-age=0, must-revalidate"));
        assertThat(resultMap.get("Pragma"), is("no-cache"));
        assertThat(resultMap.get("Expires"), is("0"));
    }

    /**
     * <ul>
     * <li>ホワイトリスト以外のコンテンツをブロックするようにブラウザに指示するヘッダの出力を確認する。</li>
     *
     * <pre>
     *  <sec:headers>
     *      <sec:content-security-policy policy-directives="default-src 'self'" />
     *  </sec:headers>
     * </pre>
     * </ul>
     **/
    @Test
    public void testSPSC0501001() throws IOException {

        // レスポンスヘッダを取得
        HttpHeaders requestHeaders = new HttpHeaders();
        ResponseEntity<byte[]> entity = restTemplate.exchange(
                getPackageRootUrl() + "/" + VIEW_TYPE + "/0501/001",
                HttpMethod.GET, new HttpEntity<byte[]>(requestHeaders),
                byte[].class);

        HttpHeaders responseHeaders = entity.getHeaders();
        Map<String, String> resultMap = responseHeaders.toSingleValueMap();

        // レスポンスヘッダを確認
        assertThat(resultMap.get("Content-Security-Policy"), is(
                "default-src 'self'"));
        assertNull(resultMap.get("Content-Security-Policy-Report-Only"));
    }

    /**
     * <ul>
     * <li>ホワイトリスト以外のコンテンツをブロックせず報告するようにブラウザに指示するヘッダーの出力を確認する。</li>
     *
     * <pre>
     *  <sec:headers>
     *      <sec:content-security-policy policy-directives="default-src 'self'; report-uri /csp_report;" report-only="true" />
     *  </sec:headers>
     * </pre>
     * </ul>
     **/
    @Test
    public void testSPSC0501002() throws IOException {

        // レスポンスヘッダを取得
        HttpHeaders requestHeaders = new HttpHeaders();
        ResponseEntity<byte[]> entity = restTemplate.exchange(
                getPackageRootUrl() + "/" + VIEW_TYPE + "/0501/002",
                HttpMethod.GET, new HttpEntity<byte[]>(requestHeaders),
                byte[].class);

        HttpHeaders responseHeaders = entity.getHeaders();
        Map<String, String> resultMap = responseHeaders.toSingleValueMap();

        // レスポンスヘッダを確認
        assertNull(resultMap.get("Content-Security-Policy"));
        assertThat(resultMap.get("Content-Security-Policy-Report-Only"), is(
                "default-src 'self'; report-uri /csp_report;"));
    }

    /**
     * <ul>
     * <li>ホワイトリスト以外のコンテンツをブロックして報告するようにブラウザに指示するヘッダの出力を確認する。</li>
     *
     * <pre>
     *  <sec:headers>
     *      <sec:content-security-policy policy-directives="default-src 'self'; report-uri /csp_report;" />
     *  </sec:headers>
     * </pre>
     * </ul>
     **/
    @Test
    public void testSPSC0501003() throws IOException {

        // レスポンスヘッダを取得
        HttpHeaders requestHeaders = new HttpHeaders();
        ResponseEntity<byte[]> entity = restTemplate.exchange(
                getPackageRootUrl() + "/" + VIEW_TYPE + "/0501/003",
                HttpMethod.GET, new HttpEntity<byte[]>(requestHeaders),
                byte[].class);

        HttpHeaders responseHeaders = entity.getHeaders();
        Map<String, String> resultMap = responseHeaders.toSingleValueMap();

        // レスポンスヘッダを確認
        assertThat(resultMap.get("Content-Security-Policy"), is(
                "default-src 'self'; report-uri /csp_report;"));
        assertNull(resultMap.get("Content-Security-Policy-Report-Only"));
    }

    /**
     * <ul>
     * <li>HTTPをHTTPSに置き換えるようブラウザに指示するヘッダの出力を確認する。</li>
     *
     * <pre>
     *  <sec:headers>
     *      <sec:content-security-policy policy-directives="upgrade-insecure-requests; default-src 'self';" />
     *  </sec:headers>
     * </pre>
     * </ul>
     **/
    @Test
    public void testSPSC0501004() throws IOException {

        // レスポンスヘッダを取得
        HttpHeaders requestHeaders = new HttpHeaders();
        ResponseEntity<byte[]> entity = restTemplate.exchange(
                getPackageRootUrl() + "/" + VIEW_TYPE + "/0501/004",
                HttpMethod.GET, new HttpEntity<byte[]>(requestHeaders),
                byte[].class);

        HttpHeaders responseHeaders = entity.getHeaders();
        Map<String, String> resultMap = responseHeaders.toSingleValueMap();

        // レスポンスヘッダを確認
        assertThat(resultMap.get("Content-Security-Policy"), is(
                "upgrade-insecure-requests; default-src 'self';"));
        assertNull(resultMap.get("Content-Security-Policy-Report-Only"));
    }

}
