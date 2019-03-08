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
package jp.co.ntt.fw.spring.functionaltest.selenium.spsc;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.openqa.selenium.By.*;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import jp.co.ntt.fw.spring.functionaltest.selenium.ApServerName;
import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

public class SpringSecurityTest extends FunctionTestSupport {

    @Inject
    protected RestTemplate restTemplate;

    @Value("${selenium.applicationContextUrl}")
    protected String applicationContextUrl;

    /**
     * <ul>
     * <li>Spring Securityの最小構成によるデフォルト動作の確認</li>
     * <li>Sepring Securityの最小構成でデフォルトのログインフォームが表示されること</li>
     * </ul>
     **/
    @Test
    public void testSPSC0101001() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("spsc0101001"));

        // デフォルトのログイン画面の確認
        // ユーザ
        assert (webDriverOperations.exists(name("username")));
        // パスワード
        assert (webDriverOperations.exists(name("password")));
        // パス
        assertThat(webDriverOperations.getCurrentUrl(), is(applicationContextUrl
                + "/login"));

    }

    /**
     * <ul>
     * <li>spring securityのHTTPヘッダ付与機能の確認</li>
     * <li>デフォルトの設定で以下以外のすべてのヘッダーができること。
     * <ul>
     * <li>Content Security Policy (CSP)</li>
     * <li>HTTP Public Key Pinning (HPKP)</li>
     * </ul>
     * </li>
     * </ul>
     *
     * <pre>
     * <sec:headers/>
     * </pre>
     **/
    @Test
    public void testSPSC0102001() throws IOException {

        // レスポンスヘッダを取得
        HttpHeaders requestHeaders = new HttpHeaders();
        ResponseEntity<byte[]> entity = restTemplate.exchange(
                applicationContextUrl + "/spsc/0102/001", HttpMethod.GET,
                new HttpEntity<byte[]>(requestHeaders), byte[].class);

        HttpHeaders requestHeaders2 = entity.getHeaders();
        Map<String, String> resultMap = requestHeaders2.toSingleValueMap();

        // レスポンスヘッダを確認
        assertThat(resultMap.get("Cache-Control"), is(
                "no-cache, no-store, max-age=0, must-revalidate"));
        assertThat(resultMap.get("Pragma"), is("no-cache"));
        assertThat(resultMap.get("Expires"), is("0"));
        assertThat(resultMap.get("X-Content-Type-Options"), is("nosniff"));
        assertThat(resultMap.get("X-Frame-Options"), is("DENY"));
        assertThat(resultMap.get("X-XSS-Protection"), is("1; mode=block"));
        assertNull(resultMap.get("Content-Security-Policy"));
        assertNull(resultMap.get("Content-Security-Policy-Report-Only"));
        assertNull(resultMap.get("Public-Key-Pins"));
        assertNull(resultMap.get("Public-Key-Pins-Report-Only"));

    }

    /**
     * <ul>
     * <li>spring securityのHTTPヘッダ付与機能の確認</li>
     * <li>デフォルトの設定ですべてのヘッダーが取得できないこと。</li>
     * </ul>
     *
     * <pre>
     * <sec:headers disabled="true"/>
     * </pre>
     **/
    @Test
    public void testSPSC0102002() throws IOException {

        // レスポンスヘッダを取得
        HttpHeaders requestHeaders = new HttpHeaders();
        ResponseEntity<byte[]> entity = restTemplate.exchange(
                applicationContextUrl + "/spsc/0102/002", HttpMethod.GET,
                new HttpEntity<byte[]>(requestHeaders), byte[].class);

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
        assertNull(resultMap.get("Public-Key-Pins"));
        assertNull(resultMap.get("Public-Key-Pins-Report-Only"));

    }

    /**
     * <ul>
     * <li>spring securityのHTTPヘッダ付与機能の確認</li>
     * <li>デフォルトの設定ですべてのヘッダーが取得できないこと。</li>
     * </ul>
     *
     * <pre>
     * <sec:headers defaults-disabled="true">
     * </pre>
     **/
    // @Test
    public void testSPSC0102003() throws IOException {

        // レスポンスヘッダを取得
        HttpHeaders requestHeaders = new HttpHeaders();
        ResponseEntity<byte[]> entity = restTemplate.exchange(
                applicationContextUrl + "/spsc/0102/003", HttpMethod.GET,
                new HttpEntity<byte[]>(requestHeaders), byte[].class);

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
        assertNull(resultMap.get("Public-Key-Pins"));
        assertNull(resultMap.get("Public-Key-Pins-Report-Only"));

    }

    /**
     * <ul>
     * <li>spring securityのHTTPヘッダ付与機能の確認</li>
     * <li>要素を付与したとき、そのヘッダーだけできること</li>
     *
     * <pre>
     * 	<sec:headers defaults-disabled="true">
     * 		<sec:cache-control />
     * 		<sec:content-type-options />
     * 		<sec:frame-options />
     * 		<sec:xss-protection />
     * 	</sec:headers>
     * </pre>
     *
     * </ul>
     **/
    @Test
    public void testSPSC0102004() throws IOException {

        // レスポンスヘッダを取得
        HttpHeaders requestHeaders = new HttpHeaders();
        ResponseEntity<byte[]> entity = restTemplate.exchange(
                applicationContextUrl + "/spsc/0102/004", HttpMethod.GET,
                new HttpEntity<byte[]>(requestHeaders), byte[].class);

        HttpHeaders requestHeaders2 = entity.getHeaders();
        Map<String, String> resultMap = requestHeaders2.toSingleValueMap();

        // レスポンスヘッダを確認
        assertThat(resultMap.get("Cache-Control"), is(
                "no-cache, no-store, max-age=0, must-revalidate"));
        assertThat(resultMap.get("Pragma"), is("no-cache"));
        assertThat(resultMap.get("Expires"), is("0"));
        assertThat(resultMap.get("X-Content-Type-Options"), is("nosniff"));
        assertThat(resultMap.get("X-Frame-Options"), is("DENY"));
        assertThat(resultMap.get("X-XSS-Protection"), is("1; mode=block"));
        assertNull(resultMap.get("Content-Security-Policy"));
        assertNull(resultMap.get("Content-Security-Policy-Report-Only"));
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
     *
     * </ul>
     **/
    @Test
    public void testSPSC0102006() throws IOException {

        // レスポンスヘッダを取得
        HttpHeaders requestHeaders = new HttpHeaders();
        ResponseEntity<byte[]> entity = restTemplate.exchange(
                applicationContextUrl + "/spsc/0102/006", HttpMethod.GET,
                new HttpEntity<byte[]>(requestHeaders), byte[].class);

        HttpHeaders requestHeaders2 = entity.getHeaders();
        Map<String, String> resultMap = requestHeaders2.toSingleValueMap();

        // レスポンスヘッダを確認
        assertThat(resultMap.get("X-Frame-Options"), is("SAMEORIGIN"));

    }

    /**
     * <ul>
     * <li>spring securityのHTTPヘッダ付与機能の確認</li>
     * <li>要素にカスタムヘッダを付与して、そのヘッダーが正しく取得できること</li>
     *
     * <pre>
     * <sec:header name="X-WebKit-CSP" value="default-src 'self'"/>
     * </pre>
     *
     * </ul>
     **/
    @Test
    public void testSPSC0103001() throws IOException {

        // レスポンスヘッダを取得
        HttpHeaders requestHeaders = new HttpHeaders();
        ResponseEntity<byte[]> entity = restTemplate.exchange(
                applicationContextUrl + "/spsc/0103/001", HttpMethod.GET,
                new HttpEntity<byte[]>(requestHeaders), byte[].class);

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
     *
     * </ul>
     **/
    @Test
    public void testSPSC0104001() throws IOException {

        // レスポンスヘッダを取得
        HttpHeaders requestHeaders = new HttpHeaders();
        ResponseEntity<byte[]> entity = restTemplate.exchange(
                applicationContextUrl + "/spsc/0104/001/notsecure/001",
                HttpMethod.GET, new HttpEntity<byte[]>(requestHeaders),
                byte[].class);

        HttpHeaders requestHeaders2 = entity.getHeaders();
        Map<String, String> resultMap = requestHeaders2.toSingleValueMap();

        // キャッシュコントロールされていないレスポンスヘッダを確認
        assertNull(resultMap.get("Cache-Control"));
        assertNull(resultMap.get("Pragma"));
        assertNull(resultMap.get("Expires"));

        entity = restTemplate.exchange(applicationContextUrl
                + "/spsc/0104/001/secure/001", HttpMethod.GET,
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
     *
     * </ul>
     **/
    @Test
    public void testSPSC0105001() throws IOException {

        // レスポンスヘッダを取得
        HttpHeaders requestHeaders = new HttpHeaders();
        ResponseEntity<byte[]> entity = restTemplate.exchange(
                applicationContextUrl + "/spsc/0105/001", HttpMethod.GET,
                new HttpEntity<byte[]>(requestHeaders), byte[].class);

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
     *
     * </ul>
     **/
    @Test
    public void testSPSC0105002() throws IOException {

        // レスポンスヘッダを取得
        HttpHeaders requestHeaders = new HttpHeaders();
        ResponseEntity<byte[]> entity = restTemplate.exchange(
                applicationContextUrl + "/spsc/0105/002", HttpMethod.GET,
                new HttpEntity<byte[]>(requestHeaders), byte[].class);

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
     *
     * </ul>
     **/
    @Test
    public void testSPSC0105003() throws IOException {

        // レスポンスヘッダを取得
        HttpHeaders requestHeaders = new HttpHeaders();
        ResponseEntity<byte[]> entity = restTemplate.exchange(
                applicationContextUrl + "/spsc/0105/003", HttpMethod.GET,
                new HttpEntity<byte[]>(requestHeaders), byte[].class);

        HttpHeaders responseHeaders = entity.getHeaders();
        Map<String, String> resultMap = responseHeaders.toSingleValueMap();

        // レスポンスヘッダを確認
        assertThat(resultMap.get("Content-Security-Policy"), is(
                "default-src 'self'; report-uri /csp_report;"));
        assertNull(resultMap.get("Content-Security-Policy-Report-Only"));
    }

    /**
     * <ul>
     * <li>サイトの証明書が一致しない場合にアクセスをブロックするようにブラウザに指示するヘッダの出力を確認する。</li>
     *
     * <pre>
     *  <sec:headers>
     *      <sec:hpkp report-only="false" max-age-seconds="1">
     *          <sec:pins>
     *              <sec:pin algorithm="sha256">d6qzRu9zOECb90Uez27xWltNsj0e1Md7GkYYkVoZWmM=</sec:pin>
     *              <sec:pin algorithm="sha256">E9CZ9INDbd+2eRQozYqqbQ2yXLVKB9+xcprMF+44U1g=</sec:pin>
     *          </sec:pins>
     *      </sec:hpkp>
     *  </sec:headers>
     * </pre>
     *
     * </ul>
     **/
    @Ignore("spring-functionaltest-webがHTTP環境のため実施不可")
    @Test
    public void testSPSC0106001() throws IOException {

        // レスポンスヘッダを取得
        HttpHeaders requestHeaders = new HttpHeaders();
        ResponseEntity<byte[]> entity = restTemplate.exchange(
                applicationContextUrl + "/spsc/0106/001", HttpMethod.GET,
                new HttpEntity<byte[]>(requestHeaders), byte[].class);

        HttpHeaders responseHeaders = entity.getHeaders();
        Map<String, String> resultMap = responseHeaders.toSingleValueMap();

        // レスポンスヘッダを確認
        assertThat(resultMap.get("Public-Key-Pins"), is("max-age=1 ;"
                + " pin-sha256=\"d6qzRu9zOECb90Uez27xWltNsj0e1Md7GkYYkVoZWmM=\" ;"
                + " pin-sha256=\"E9CZ9INDbd+2eRQozYqqbQ2yXLVKB9+xcprMF+44U1g=\""));
        assertNull(resultMap.get("Public-Key-Pins-Report-Only"));
    }

    /**
     * <ul>
     * <li>サイトの証明書が一致しない場合にアクセスをブロックせず報告するようにブラウザに指示するヘッダの出力を確認する。</li>
     *
     * <pre>
     *  <sec:headers>
     *      <sec:hpkp max-age-seconds="1" report-uri="https://www.example.net/hpkp-report">
     *          <sec:pins>
     *              <sec:pin algorithm="sha256">d6qzRu9zOECb90Uez27xWltNsj0e1Md7GkYYkVoZWmM=</sec:pin>
     *              <sec:pin algorithm="sha256">E9CZ9INDbd+2eRQozYqqbQ2yXLVKB9+xcprMF+44U1g=</sec:pin>
     *          </sec:pins>
     *      </sec:hpkp>
     *  </sec:headers>
     * </pre>
     *
     * </ul>
     **/
    @Ignore("spring-functionaltest-webがHTTP環境のため実施不可")
    @Test
    public void testSPSC0106002() throws IOException {

        // レスポンスヘッダを取得
        HttpHeaders requestHeaders = new HttpHeaders();
        ResponseEntity<byte[]> entity = restTemplate.exchange(
                applicationContextUrl + "/spsc/0106/002", HttpMethod.GET,
                new HttpEntity<byte[]>(requestHeaders), byte[].class);

        HttpHeaders responseHeaders = entity.getHeaders();
        Map<String, String> resultMap = responseHeaders.toSingleValueMap();

        // レスポンスヘッダを確認
        assertNull(resultMap.get("Public-Key-Pins"));
        assertThat(resultMap.get("Public-Key-Pins-Report-Only"), is(
                "max-age=1 ;"
                        + " pin-sha256=\"d6qzRu9zOECb90Uez27xWltNsj0e1Md7GkYYkVoZWmM=\" ;"
                        + " pin-sha256=\"E9CZ9INDbd+2eRQozYqqbQ2yXLVKB9+xcprMF+44U1g=\" ;"
                        + " report-uri=\"https://www.example.net/hpkp-report\""));
    }

    /**
     * <ul>
     * <li>サイトの証明書が一致しない場合にアクセスをブロックして報告するようにブラウザに指示するヘッダの出力を確認する。</li>
     *
     * <pre>
     *  <sec:headers>
     *      <sec:hpkp report-only="false" max-age-seconds="1" report-uri="https://www.example.net/hpkp-report">
     *          <sec:pins>
     *              <sec:pin algorithm="sha256">d6qzRu9zOECb90Uez27xWltNsj0e1Md7GkYYkVoZWmM=</sec:pin>
     *              <sec:pin algorithm="sha256">E9CZ9INDbd+2eRQozYqqbQ2yXLVKB9+xcprMF+44U1g=</sec:pin>
     *          </sec:pins>
     *      </sec:hpkp>
     *  </sec:headers>
     * </pre>
     *
     * </ul>
     **/
    @Ignore("spring-functionaltest-webがHTTP環境のため実施不可")
    @Test
    public void testSPSC0106003() throws IOException {

        // レスポンスヘッダを取得
        HttpHeaders requestHeaders = new HttpHeaders();
        ResponseEntity<byte[]> entity = restTemplate.exchange(
                applicationContextUrl + "/spsc/0106/003", HttpMethod.GET,
                new HttpEntity<byte[]>(requestHeaders), byte[].class);

        HttpHeaders responseHeaders = entity.getHeaders();
        Map<String, String> resultMap = responseHeaders.toSingleValueMap();

        // レスポンスヘッダを確認
        assertThat(resultMap.get("Public-Key-Pins"), is("max-age=1 ;"
                + " pin-sha256=\"d6qzRu9zOECb90Uez27xWltNsj0e1Md7GkYYkVoZWmM=\" ;"
                + " pin-sha256=\"E9CZ9INDbd+2eRQozYqqbQ2yXLVKB9+xcprMF+44U1g=\" ;"
                + " report-uri=\"https://www.example.net/hpkp-report\""));
        assertNull(resultMap.get("Public-Key-Pins-Report-Only"));
    }

}
