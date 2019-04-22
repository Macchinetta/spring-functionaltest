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
package jp.co.ntt.fw.spring.functionaltest.selenium.spsm;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.id;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;
import jp.co.ntt.fw.spring.functionaltest.selenium.WebDriverOperations;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.RestTemplate;

public class SpringSecuritySessionManaegementTest extends FunctionTestSupport {

    @Inject
    protected RestTemplate restTemplate;

    @Value("${selenium.applicationContextUrl}")
    protected String applicationContextUrl;

    /**
     * <ul>
     * <li>ログイン後にSessionIDが変更されていることを確認する。</li>
     * <li>ログイン時にSession情報が破棄されないことを確認する。</li>
     * </ul>
     */
    @Test
    public void testSPSC0202001() throws IOException, URISyntaxException {
        // メニュー画面の操作
        webDriverOperations.click(id("spsm0202001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // セッションの確認
        assertThat(webDriverOperations.getText(id("session")), is(
                "This is Session"));

        String beforeCoookie = webDriverOperations.getCookie("JSESSIONID")
                .getValue();

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // セッションの確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "ログイン完了画面"));

        // ログイン前後でcookieIdが変更されれていることを確認
        assertThat(webDriverOperations.getCookie("JSESSIONID").getValue(), not(
                beforeCoookie));

        // セッション情報が引き継がれていないことを確認する。
        assertThat(webDriverOperations.getText(id("session")), is(
                "This is Session"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));
    }

    /**
     * <ul>
     * <li>ログイン後にSessionIDが変更されていることを確認する。</li>
     * <li>ログイン時にSession情報が破棄されることを確認する。</li>
     * </ul>
     */
    @Test
    public void testSPSC0202002() throws IOException, URISyntaxException {

        // メニュー画面の操作
        webDriverOperations.click(id("spsm0202002"));

        // 入力条件設定
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // セッションの確認
        assertThat(webDriverOperations.getText(id("session")), is(
                "This is Session"));

        String beforeCoookie = webDriverOperations.getCookie("JSESSIONID")
                .getValue();

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // セッションの確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "ログイン完了画面"));

        // ログイン前後でcookieIdが変更されれていることを確認
        assertThat(webDriverOperations.getCookie("JSESSIONID").getValue(), not(
                beforeCoookie));

        // セッション情報が引き継がれていないことを確認する。
        assertThat(webDriverOperations.getText(id("session")), is(""));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>セッション生成機能が正常に機能することを確認する。（セッションを使用しない）</li>
     * </ul>
     */
    @Test
    public void testSPSC0301004() throws IOException, URISyntaxException {

        String userid = "Josh";
        String password = "spring1234";

        String plainCredentials = userid + ":" + password;
        String base64Credentials = new String(Base64.encode(plainCredentials
                .getBytes(StandardCharsets.UTF_8)));

        RequestEntity<Void> requestEntity = RequestEntity.get(
                new URI(applicationContextUrl + "/spsm/0301/001?afterLogin"))
                .header("Authorization", "Basic " + base64Credentials).build();

        ResponseEntity<byte[]> responseEntity = restTemplate.exchange(
                requestEntity, byte[].class);

        org.springframework.http.HttpStatus status = responseEntity
                .getStatusCode();
        assertThat(status, is(org.springframework.http.HttpStatus.OK));

        // セッション操作が行われてないことを確認する(作成)
        dbLogAssertOperations.waitForAssertion(1000);
        dbLogAssertOperations.assertNotContainsByRegexMessage(
                ".*HttpSessionEventLoggingListener", " * sessionCreated * ");
        // セッション操作が行われてないことを確認する(登録)
        dbLogAssertOperations.assertNotContainsByRegexMessage(
                ".*HttpSessionEventLoggingListener", " * attributeAdded * ");
        // セッション操作が行われてないことを確認する(変更)
        dbLogAssertOperations.assertNotContainsByRegexMessage(
                ".*HttpSessionEventLoggingListener", " * attributeReplaced * ");

    }

    /**
     * <ul>
     * <li>無効セッション検知機能の除外パス指定が正常に機能することを確認する。</li>
     * </ul>
     */
    @Test
    public void testSPSM0403001() throws IOException, URISyntaxException {

        // メニュー画面の操作
        webDriverOperations.click(id("spsm0403001001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // sessionのクッキーを削除することにより、別セッションで実行する。
        webDriverOperations.deleteCookie("JSESSIONID");

        // ログアウト押下
        webDriverOperations.click(id("logout"));

        // 無効セッションエラーとなることを確認する
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "Invalid Session Error"));

        // 機能毎のトップページを表示
        webDriverOperations.displayPage(getPackageRootUrl());

        // メニュー画面の操作
        webDriverOperations.click(id("spsm0403001002"));

        // 入力条件設定
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // sessionのクッキーを削除することにより、別セッションで実行する。
        webDriverOperations.deleteCookie("JSESSIONID");

        // ログアウト押下
        webDriverOperations.click(id("logout"));

        // 無効セッションエラーであっても遷移可能（ログインフォームに戻れる）であることを確認する。
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "forbidden Error!"));
    }

    /**
     * <ul>
     * <li>1ユーザが保持できる最大セッション数を制御できる機能を利用し、1ユーザの最大セッション数を超過した場合、新規ログインを受け付けないことを確認する。</li>
     * <li>ログイン後にセッション情報を削除し、新しいセッションでアクセスする。別セッションでのログインは無効となることを確認する。</li>
     * <li>spring securityのconcurrency-controlを使った設定の確認(後勝ち)</li>
     * </ul>
     */
    @Test
    public void testSPSM0501001() throws IOException {

        WebDriverOperations browser1 = webDriverOperations;

        // メニュー画面の操作
        browser1.click(id("spsm0501001"));

        // 入力条件設定
        browser1.overrideText(id("username"), "Josh");
        browser1.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        browser1.click(id("login"));

        // 認証後のユーザ情報の確認
        assertThat(browser1.getText(id("username")), is("Josh"));

        WebDriverOperations browser2 = newWebDriverOperations();

        // メニュー画面の操作
        browser2.click(id("spsm0501001"));

        // 入力条件設定
        browser2.overrideText(id("username"), "Josh");
        browser2.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        browser2.click(id("login"));

        // 認証後のユーザ情報の確認
        assertThat(browser2.getText(id("username")), is("Josh"));

        WebDriverOperations browser3 = newWebDriverOperations();

        // メニュー画面の操作
        browser3.click(id("spsm0501001"));

        // 入力条件設定
        browser3.overrideText(id("username"), "Josh");
        browser3.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        browser3.click(id("login"));

        // 認証後のユーザ情報の確認
        assertThat(browser3.getText(id("username")), is("Josh"));

        // 1画面目で認証要の画面に遷移する。そして、先頭画面に遷移することを確認。
        // TOP画面
        browser1.displayPage(getPackageRootUrl());

        browser1.click(id("spsm0501001nologin"));

        // 認証失敗後の画面遷移確認
        assertThat(browser1.getText(id("screenTitle")), is(
                "Spring Functional Test Top Page"));

        // 後処理
        browser1.click(id("spsmLink"));

        // メニュー画面の操作
        browser1.click(id("spsm0501001"));

        // ログアウトボタン押下
        browser1.click(id("logout"));

        // ログアウトボタン押下
        browser2.click(id("logout"));

        // ログアウトボタン押下
        browser3.click(id("logout"));

        quitWebDriver(browser2);

        quitWebDriver(browser3);

    }

    /**
     * <ul>
     * <li>1ユーザが保持できる最大セッション数を制御できる機能を利用し、1ユーザの最大セッション数を超過した場合、新規ログインを受け付けないことを確認する。</li>
     * <li>ログイン後にセッション情報を削除し、新しいセッションでアクセスする。別セッションでのログインは無効となることを確認する。</li>
     * <li>spring securityのconcurrency-controlを使った設定の確認(先勝ち)</li>
     * </ul>
     */
    @Test
    public void testSPSM0501002() throws IOException {

        WebDriverOperations browser1 = webDriverOperations;

        // メニュー画面の操作
        browser1.click(id("spsm0501002"));

        // 入力条件設定
        browser1.overrideText(id("username"), "Josh");
        browser1.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        browser1.click(id("login"));

        // 認証後のユーザ情報の確認
        assertThat(browser1.getText(id("username")), is("Josh"));

        WebDriverOperations browser2 = newWebDriverOperations();

        // メニュー画面の操作
        browser2.click(id("spsm0501002"));

        // 入力条件設定
        browser2.overrideText(id("username"), "Josh");
        browser2.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        browser2.click(id("login"));

        // 認証後のユーザ情報の確認
        assertThat(browser2.getText(id("username")), is("Josh"));

        WebDriverOperations browser3 = newWebDriverOperations();

        // メニュー画面の操作
        browser3.click(id("spsm0501002"));

        // 入力条件設定
        browser3.overrideText(id("username"), "Josh");
        browser3.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        browser3.click(id("login"));

        // 認証失敗後の画面遷移確認
        assertThat(browser3.getText(id("loginError")), is(
                "Maximum sessions of 2 for this principal exceeded"));

        // ログアウトボタン押下
        browser1.click(id("logout"));

        // ログアウトボタン押下
        browser2.click(id("logout"));

        quitWebDriver(browser2);

        quitWebDriver(browser3);

    }

}
