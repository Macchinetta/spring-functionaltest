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
package jp.co.ntt.fw.spring.functionaltest.selenium.athn;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

import java.io.IOException;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

public class AuthenticationTest extends FunctionTestSupport {

    @After
    public void afterTest() {
        // ログアウトしていない場合次のテストに影響が出るので、セッションを削除する
        webDriverOperations.deleteCookie("JSESSIONID");
    }

    /**
     * <ul>
     * <li>作成したログインフォームが適用されることを確認する。</li>
     * <li>作成したログインフォームが適用され、認証機能が機能することを確認する。</li>
     * </ul>
     */
    @Test
    public void testATHN0102001() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("athn0102001"));

        // ログイン画面の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "ログインフォーム(DefaultFormAuthentication)"));

        // 入力条件設定
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring4321");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 認証失敗後のデフォルトメッセージの確認
        assertThat(webDriverOperations.getText(id("loginError")), is(
                "Bad credentials"));

        // 機能毎のトップページを表示
        webDriverOperations.displayPage(getPackageRootUrl());

        // ログインしていないことの確認
        assertThat(webDriverOperations.getText(id("AuthenticateUserName")), is(
                "ゲスト"));

    }

    /**
     * <ul>
     * <li>認証成功後、認証前にリクエストしたリクエストパスに遷移するlことを確認する。</li>
     * <li>MVCのコントローラから認証情報を引数として受け取り、アクセスが行えることを確認する。（ATHN1001001）</li>
     * </ul>
     */
    @Test
    public void testATHN0201001() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("athn0201001"));

        // ログイン画面の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "ログインフォーム（DefaultAuthenticationSeccess）"));

        // 入力条件設定
        webDriverOperations.overrideText(id("uid"), "Josh");
        webDriverOperations.overrideText(id("pwd"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 認証後のユーザ情報の確認
        assertThat(webDriverOperations.getText(id("username")), is("Josh"));

        // HttpSessionRequestCache の matchingRequestParameterName が初期値"continue"で登録されるようになった
        // https://github.com/spring-projects/spring-security/commit/f84f08c4b9da4f326de1fed2772ae5e582d7cdf7
        // フレームワークとしては影響ないが、ユーザによっては影響を受ける可能性がある

        // パスの確認
        assertThat(webDriverOperations.getCurrentUrl(), is(applicationContextUrl
                + "/athn/0201/001?loginSuccess&continue"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

        // ログインしていないことの確認
        assertThat(webDriverOperations.getText(id("AuthenticateUserName")), is(
                "ゲスト"));

    }

    /**
     * <ul>
     * <li>認証成功時の遷移先を指定しない場合は、認証成功後、Spring Securityのデフォルト遷移先に遷移することを確認する。</li>
     * </ul>
     */
    @Test
    public void testATHN0201002() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("athn0201002"));

        // ログイン画面の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "ログインフォーム（DefaultAuthenticationSeccess）"));

        // 入力条件設定
        webDriverOperations.overrideText(id("uid"), "Josh");
        webDriverOperations.overrideText(id("pwd"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // パスの確認（アプリケーションルート）
        assertThat(webDriverOperations.getCurrentUrl(), is(applicationContextUrl
                + "/"));

        // 機能テストTOPの確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "Spring Functional Test Top Page"));
    }

    /**
     * <ul>
     * <li>認証失敗時遷移先のデフォルト動作を確認する。</li>
     * </ul>
     */
    @Test
    public void testATHN0301001() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("athn0301001"));

        // ログイン画面の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "ログインフォーム（DefaultAuthenticationFailure）"));

        // 入力条件設定
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring9999");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 認証失敗後のデフォルトメッセージの確認
        assertThat(webDriverOperations.getText(id("loginError")), is(
                "Bad credentials"));

        // パスの確認
        assertThat(webDriverOperations.getCurrentUrl(), is(applicationContextUrl
                + "/athn/0301/001/login?error"));

        // 機能毎のトップページを表示
        webDriverOperations.displayPage(getPackageRootUrl());

        // ログインしていないことの確認
        assertThat(webDriverOperations.getText(id("AuthenticateUserName")), is(
                "ゲスト"));

    }

    /**
     * <ul>
     * <li>DBに格納した情報で正常に認証されることを確認する。</li>
     * <li>DBに格納した情報で正常に認証されないことを確認する。</li>
     * </ul>
     */
    @Test
    public void testATHN0401001() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("athn0401001"));

        // ログイン画面の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "ログインフォーム（DbFormAuthentication）"));

        // 入力条件設定（認証成功）
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 認証後のユーザ情報の確認
        assertThat(webDriverOperations.getText(id("username")), is("Josh"));

        // パスの確認（認証成功）
        assertThat(webDriverOperations.getCurrentUrl(), is(applicationContextUrl
                + "/athn/0401/001?loginSuccess"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

        // ログインしていないことの確認
        assertThat(webDriverOperations.getText(id("AuthenticateUserName")), is(
                "ゲスト"));

        // メニュー画面の操作
        webDriverOperations.click(id("athn0401001"));

        // 入力条件設定（認証失敗）
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 認証失敗後のデフォルトメッセージの確認
        assertThat(webDriverOperations.getText(id("loginError")), is(
                "Bad credentials"));

        // パスの確認
        assertThat(webDriverOperations.getCurrentUrl(), is(applicationContextUrl
                + "/athn/0401/001/login?error"));

        // 機能毎のトップページを表示
        webDriverOperations.displayPage(getPackageRootUrl());

        // ログインしていないことの確認
        assertThat(webDriverOperations.getText(id("AuthenticateUserName")), is(
                "ゲスト"));

        // メニュー画面の操作
        webDriverOperations.click(id("athn0401001"));

        // 入力条件設定（認証失敗）
        webDriverOperations.overrideText(id("username"), "America");
        webDriverOperations.overrideText(id("password"), "spring");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 認証失敗後のデフォルトメッセージの確認
        assertThat(webDriverOperations.getText(id("loginError")), is(
                "Bad credentials"));

        // パスの確認
        assertThat(webDriverOperations.getCurrentUrl(), is(applicationContextUrl
                + "/athn/0401/001/login?error"));

        // 機能毎のトップページを表示
        webDriverOperations.displayPage(getPackageRootUrl());

        // ログインしていないことの確認
        assertThat(webDriverOperations.getText(id("AuthenticateUserName")), is(
                "ゲスト"));

    }

    /**
     * <ul>
     * <li>AuthenticationManagerにPasswordEncoderを明示的に設定する。</li>
     * <li>DBに格納した情報で正常に認証されることを確認する。</li>
     * <li>DBに格納した情報で正常に認証されないことを確認する。</li>
     * </ul>
     */
    @Test
    public void testATHN0402001() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("athn0402001"));

        // ログイン画面の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "ログインフォーム（DbFormAuthentication with PasswordEncoder specified）"));

        // 入力条件設定（認証成功）
        webDriverOperations.overrideText(id("username"), "Smith");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 認証後のユーザ情報の確認
        assertThat(webDriverOperations.getText(id("username")), is("Smith"));

        // パスの確認（認証成功）
        assertThat(webDriverOperations.getCurrentUrl(), is(applicationContextUrl
                + "/athn/0402/001?loginSuccess"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

        // ログインしていないことの確認
        assertThat(webDriverOperations.getText(id("AuthenticateUserName")), is(
                "ゲスト"));

        // メニュー画面の操作
        webDriverOperations.click(id("athn0402001"));

        // 入力条件設定（認証失敗）
        webDriverOperations.overrideText(id("username"), "Smith");
        webDriverOperations.overrideText(id("password"), "spring");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 認証失敗後のデフォルトメッセージの確認
        assertThat(webDriverOperations.getText(id("loginError")), is(
                "Bad credentials"));

        // パスの確認
        assertThat(webDriverOperations.getCurrentUrl(), is(applicationContextUrl
                + "/athn/0402/001/login?error"));

        // 機能毎のトップページを表示
        webDriverOperations.displayPage(getPackageRootUrl());

        // ログインしていないことの確認
        assertThat(webDriverOperations.getText(id("AuthenticateUserName")), is(
                "ゲスト"));

        // メニュー画面の操作
        webDriverOperations.click(id("athn0402001"));

        // 入力条件設定（認証失敗）
        webDriverOperations.overrideText(id("username"), "America");
        webDriverOperations.overrideText(id("password"), "spring");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 認証失敗後のデフォルトメッセージの確認
        assertThat(webDriverOperations.getText(id("loginError")), is(
                "Bad credentials"));

        // パスの確認
        assertThat(webDriverOperations.getCurrentUrl(), is(applicationContextUrl
                + "/athn/0402/001/login?error"));

        // 機能毎のトップページを表示
        webDriverOperations.displayPage(getPackageRootUrl());

        // ログインしていないことの確認
        assertThat(webDriverOperations.getText(id("AuthenticateUserName")), is(
                "ゲスト"));

    }

    /**
     * <ul>
     * <li>AuthenticationManagerにハッシュ化アルゴリズムを明示的に設定する。</li>
     * <li>DBに格納した情報で正常に認証されることを確認する。</li>
     * <li>DBに格納した情報で正常に認証されないことを確認する。</li>
     * </ul>
     */
    @Test
    public void testATHN0402004() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("athn0402004"));

        // ログイン画面の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "ログインフォーム（DbFormAuthentication with hash type specified）"));

        // 入力条件設定（認証成功）
        webDriverOperations.overrideText(id("username"), "Smith");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 認証後のユーザ情報の確認
        assertThat(webDriverOperations.getText(id("username")), is("Smith"));

        // パスの確認（認証成功）
        assertThat(webDriverOperations.getCurrentUrl(), is(applicationContextUrl
                + "/athn/0402/004?loginSuccess"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

        // ログインしていないことの確認
        assertThat(webDriverOperations.getText(id("AuthenticateUserName")), is(
                "ゲスト"));

        // メニュー画面の操作
        webDriverOperations.click(id("athn0402004"));

        // 入力条件設定（認証失敗）
        webDriverOperations.overrideText(id("username"), "Smith");
        webDriverOperations.overrideText(id("password"), "spring");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 認証失敗後のデフォルトメッセージの確認
        assertThat(webDriverOperations.getText(id("loginError")), is(
                "Bad credentials"));

        // パスの確認
        assertThat(webDriverOperations.getCurrentUrl(), is(applicationContextUrl
                + "/athn/0402/004/login?error"));

        // 機能毎のトップページを表示
        webDriverOperations.displayPage(getPackageRootUrl());

        // ログインしていないことの確認
        assertThat(webDriverOperations.getText(id("AuthenticateUserName")), is(
                "ゲスト"));

        // メニュー画面の操作
        webDriverOperations.click(id("athn0402004"));

        // 入力条件設定（認証失敗）
        webDriverOperations.overrideText(id("username"), "America");
        webDriverOperations.overrideText(id("password"), "spring");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 認証失敗後のデフォルトメッセージの確認
        assertThat(webDriverOperations.getText(id("loginError")), is(
                "Bad credentials"));

        // パスの確認
        assertThat(webDriverOperations.getCurrentUrl(), is(applicationContextUrl
                + "/athn/0402/004/login?error"));

        // 機能毎のトップページを表示
        webDriverOperations.displayPage(getPackageRootUrl());

        // ログインしていないことの確認
        assertThat(webDriverOperations.getText(id("AuthenticateUserName")), is(
                "ゲスト"));

    }

    /**
     * <ul>
     * <li>パスワードをBCryptPasswordEncoderでハッシュ化できることを確認。</li>
     * </ul>
     */
    @Test
    public void testATHN0501001() {

        // メニュー画面の操作

        webDriverOperations.click(id("athn0501001"));

        // 管理者情報入力

        webDriverOperations.appendText(id("username"), "John");
        webDriverOperations.appendText(id("password"), "spring1234");
        webDriverOperations.click(id("create"));

        // 管理者情報内容確認
        assertThat(webDriverOperations.getText(id("getAdministratorName")), is(
                "John"));
        // ハッシュ化されたパスワードはBCryptPasswordEncodeされているか
        assertTrue(Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}")
                .matcher(webDriverOperations.getText(id(
                        "getAfterEncodePassword"))).matches());
        assertThat(webDriverOperations.getText(id("getBeforeEncodePassword")),
                is("spring1234"));
    }

    /**
     * <ul>
     * <li>BCryptPasswordEncoderでハッシュ化したパスワードを使用した場合、認証することができることを確認。</li>
     * </ul>
     */
    @Test
    public void testATHN0501002() {

        // テスト実行順不定の為、管理者登録処理から実施
        // メニュー画面の操作
        {
            webDriverOperations.click(id("athn0501001"));
        }

        // 管理者情報入力
        {
            webDriverOperations.appendText(id("username"), "Adam");
            webDriverOperations.appendText(id("password"), "spring1234");
            webDriverOperations.click(id("create"));
        }

        // 管理者情報内容確認
        String encodePassword;

        assertThat(webDriverOperations.getText(id("getAdministratorName")), is(
                "Adam"));
        // DBの中身と一致することを確認する為、事前に取得
        encodePassword = webDriverOperations.getText(id(
                "getAfterEncodePassword"));
        // ハッシュ化されたパスワードはBCryptPasswordEncodeされているか
        assertTrue(Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}")
                .matcher(webDriverOperations.getText(id(
                        "getAfterEncodePassword"))).matches());
        assertThat(webDriverOperations.getText(id("getBeforeEncodePassword")),
                is("spring1234"));

        // パスワードハッシュメニュー画面に戻る
        webDriverOperations.click(id("goAthnMenu"));

        // メニュー画面の操作
        webDriverOperations.click(id("athn0501002"));

        // ログイン情報入力

        webDriverOperations.appendText(id("username"), "Adam");
        webDriverOperations.appendText(id("password"), "spring1234");
        webDriverOperations.click(id("login"));

        // 管理者情報内容確認
        assertThat(webDriverOperations.getText(id("username")), is("Adam"));
        // ハッシュ化されたパスワードはBCryptPasswordEncodeされているか
        assertTrue(Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}")
                .matcher(webDriverOperations.getText(id("password")))
                .matches());
        // 表示されていたパスワードと一致すること
        assertThat(webDriverOperations.getText(id("password")), is(
                encodePassword));

        // ログアウトしてセッションを削除
        webDriverOperations.click(id("logout"));

        // メニュー画面の操作
        webDriverOperations.click(id("athn0501002"));

        // ログイン情報入力
        webDriverOperations.appendText(id("username"), "Adam");
        webDriverOperations.appendText(id("password"), "spring5432");
        webDriverOperations.click(id("login"));

        // 認証エラーとなることを確認
        assertThat(webDriverOperations.getText(id("loginError")), is(
                "Bad credentials"));

        // ログアウトしてセッションを削除
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>パスワードをPbkdf2PasswordEncoderでハッシュ化できることを確認。</li>
     * </ul>
     */
    @Test
    public void testATHN0501003() {

        // メニュー画面の操作

        webDriverOperations.click(id("athn0501003"));

        // 管理者情報入力

        webDriverOperations.appendText(id("username"), "Pola");
        webDriverOperations.appendText(id("password"), "spring1234");
        webDriverOperations.click(id("create"));

        // 管理者情報内容確認
        assertThat(webDriverOperations.getText(id("getAdministratorName")), is(
                "Pola"));
        // ハッシュ化されたパスワードはPbkdf2PasswordEncodeされているか
        assertTrue(Pattern.compile("[./0-9A-Za-z]{96}").matcher(
                webDriverOperations.getText(id("getAfterEncodePassword")))
                .matches());
        assertThat(webDriverOperations.getText(id("getBeforeEncodePassword")),
                is("spring1234"));
    }

    /**
     * <ul>
     * <li>Pbkdf2PasswordEncoderでハッシュ化したパスワードを使用した場合、認証することができることを確認。</li>
     * </ul>
     */
    @Test
    public void testATHN0501004() {

        // テスト実行順不定の為、管理者登録処理から実施
        // メニュー画面の操作
        {
            webDriverOperations.click(id("athn0501003"));
        }

        // 管理者情報入力
        {
            webDriverOperations.appendText(id("username"), "Pole");
            webDriverOperations.appendText(id("password"), "spring1234");
            webDriverOperations.click(id("create"));
        }

        // 管理者情報内容確認
        String encodePassword;

        assertThat(webDriverOperations.getText(id("getAdministratorName")), is(
                "Pole"));
        // DBの中身と一致することを確認する為、事前に取得
        encodePassword = webDriverOperations.getText(id(
                "getAfterEncodePassword"));
        // ハッシュ化されたパスワードはPbkdf2PasswordEncodeされているか
        assertTrue(Pattern.compile("[./0-9A-Za-z]{96}").matcher(
                webDriverOperations.getText(id("getAfterEncodePassword")))
                .matches());
        assertThat(webDriverOperations.getText(id("getBeforeEncodePassword")),
                is("spring1234"));

        // パスワードハッシュメニュー画面に戻る
        webDriverOperations.click(id("goAthnMenu"));

        // メニュー画面の操作
        webDriverOperations.click(id("athn0501004"));

        // ログイン情報入力

        webDriverOperations.appendText(id("username"), "Pole");
        webDriverOperations.appendText(id("password"), "spring1234");
        webDriverOperations.click(id("login"));

        // 管理者情報内容確認
        assertThat(webDriverOperations.getText(id("username")), is("Pole"));
        // ハッシュ化されたパスワードはBCryptPasswordEncodeされているか
        assertTrue(Pattern.compile("[./0-9A-Za-z]{96}").matcher(
                webDriverOperations.getText(id("password"))).matches());
        // 表示されていたパスワードと一致すること
        assertThat(webDriverOperations.getText(id("password")), is(
                encodePassword));

        // ログアウトしてセッションを削除
        webDriverOperations.click(id("logout"));

        // メニュー画面の操作
        webDriverOperations.click(id("athn0501004"));

        // ログイン情報入力
        webDriverOperations.appendText(id("username"), "Pole");
        webDriverOperations.appendText(id("password"), "spring5432");
        webDriverOperations.click(id("login"));

        // 認証エラーとなることを確認
        assertThat(webDriverOperations.getText(id("loginError")), is(
                "Bad credentials"));

        // ログアウトしてセッションを削除
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>パスワードをSCryptPasswordEncoderでハッシュ化できることを確認。</li>
     * </ul>
     */
    @Test
    public void testATHN0501005() {

        // メニュー画面の操作

        webDriverOperations.click(id("athn0501005"));

        // 管理者情報入力

        webDriverOperations.appendText(id("username"), "Susan");
        webDriverOperations.appendText(id("password"), "spring1234");
        webDriverOperations.click(id("create"));

        // 管理者情報内容確認
        assertThat(webDriverOperations.getText(id("getAdministratorName")), is(
                "Susan"));
        // ハッシュ化されたパスワードはSCryptPasswordEncodeされているか
        assertTrue(Pattern.compile(
                "\\$100801\\$[./0-9A-Za-z+/-]{22}==\\$[./0-9A-Za-z+/-]{43}=")
                .matcher(webDriverOperations.getText(id(
                        "getAfterEncodePassword"))).matches());
        assertThat(webDriverOperations.getText(id("getBeforeEncodePassword")),
                is("spring1234"));
    }

    /**
     * <ul>
     * <li>SCryptPasswordEncoderでハッシュ化したパスワードを使用した場合、認証することができることを確認。</li>
     * </ul>
     */
    @Test
    public void testATHN0501006() {

        // テスト実行順不定の為、管理者登録処理から実施
        // メニュー画面の操作
        {
            webDriverOperations.click(id("athn0501005"));
        }

        // 管理者情報入力
        {
            webDriverOperations.appendText(id("username"), "Steven");
            webDriverOperations.appendText(id("password"), "spring1234");
            webDriverOperations.click(id("create"));
        }

        // 管理者情報内容確認
        String encodePassword;

        assertThat(webDriverOperations.getText(id("getAdministratorName")), is(
                "Steven"));
        // DBの中身と一致することを確認する為、事前に取得
        encodePassword = webDriverOperations.getText(id(
                "getAfterEncodePassword"));
        // ハッシュ化されたパスワードはSCryptPasswordEncodeされているか
        assertTrue(Pattern.compile(
                "\\$100801\\$[./0-9A-Za-z+/-]{22}==\\$[./0-9A-Za-z+/-]{43}=")
                .matcher(webDriverOperations.getText(id(
                        "getAfterEncodePassword"))).matches());
        assertThat(webDriverOperations.getText(id("getBeforeEncodePassword")),
                is("spring1234"));

        // パスワードハッシュメニュー画面に戻る
        webDriverOperations.click(id("goAthnMenu"));

        // メニュー画面の操作
        webDriverOperations.click(id("athn0501006"));

        // ログイン情報入力

        webDriverOperations.appendText(id("username"), "Steven");
        webDriverOperations.appendText(id("password"), "spring1234");
        webDriverOperations.click(id("login"));

        // 管理者情報内容確認
        assertThat(webDriverOperations.getText(id("username")), is("Steven"));
        // ハッシュ化されたパスワードはSCryptPasswordEncodeされているか
        assertTrue(Pattern.compile(
                "\\$100801\\$[./0-9A-Za-z+/-]{22}==\\$[./0-9A-Za-z+/-]{43}=")
                .matcher(webDriverOperations.getText(id("password")))
                .matches());
        // 表示されていたパスワードと一致すること
        assertThat(webDriverOperations.getText(id("password")), is(
                encodePassword));

        // ログアウトしてセッションを削除
        webDriverOperations.click(id("logout"));

        // メニュー画面の操作
        webDriverOperations.click(id("athn0501006"));

        // ログイン情報入力
        webDriverOperations.appendText(id("username"), "Steven");
        webDriverOperations.appendText(id("password"), "spring5432");
        webDriverOperations.click(id("login"));

        // 認証エラーとなることを確認
        assertThat(webDriverOperations.getText(id("loginError")), is(
                "Bad credentials"));

        // ログアウトしてセッションを削除
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>パスワードをArgon2PasswordEncoderでハッシュ化できることを確認。</li>
     * </ul>
     */
    @Test
    public void testATHN0501007() {

        // メニュー画面の操作

        webDriverOperations.click(id("athn0501007"));

        // 管理者情報入力

        webDriverOperations.appendText(id("username"), "Smith");
        webDriverOperations.appendText(id("password"), "spring1234");
        webDriverOperations.click(id("create"));

        // 管理者情報内容確認
        assertThat(webDriverOperations.getText(id("getAdministratorName")), is(
                "Smith"));
        // ハッシュ化されたパスワードはArgon2PasswordEncodeされているか
        assertTrue(Pattern.compile(
                "\\$argon2id\\$v=19\\$m=16384,t=2,p=1\\$[0-9A-Za-z+/]{22}\\$[0-9A-Za-z+/]{43}")
                .matcher(webDriverOperations.getText(id(
                        "getAfterEncodePassword"))).matches());
        assertThat(webDriverOperations.getText(id("getBeforeEncodePassword")),
                is("spring1234"));
    }

    /**
     * <ul>
     * <li>Argon2PasswordEncoderでハッシュ化したパスワードを使用した場合、認証することができることを確認。</li>
     * </ul>
     */
    @Test
    public void testATHN0501008() {

        // テスト実行順不定の為、管理者登録処理から実施
        // メニュー画面の操作
        {
            webDriverOperations.click(id("athn0501007"));
        }

        // 管理者情報入力
        {
            webDriverOperations.appendText(id("username"), "Wesson");
            webDriverOperations.appendText(id("password"), "spring1234");
            webDriverOperations.click(id("create"));
        }

        // 管理者情報内容確認
        String encodePassword;

        assertThat(webDriverOperations.getText(id("getAdministratorName")), is(
                "Wesson"));
        // DBの中身と一致することを確認する為、事前に取得
        encodePassword = webDriverOperations.getText(id(
                "getAfterEncodePassword"));
        // ハッシュ化されたパスワードはArgon2PasswordEncodeされているか
        assertTrue(Pattern.compile(
                "\\$argon2id\\$v=19\\$m=16384,t=2,p=1\\$[0-9A-Za-z+/]{22}\\$[0-9A-Za-z+/]{43}")
                .matcher(webDriverOperations.getText(id(
                        "getAfterEncodePassword"))).matches());
        assertThat(webDriverOperations.getText(id("getBeforeEncodePassword")),
                is("spring1234"));

        // パスワードハッシュメニュー画面に戻る
        webDriverOperations.click(id("goAthnMenu"));

        // メニュー画面の操作
        webDriverOperations.click(id("athn0501008"));

        // ログイン情報入力

        webDriverOperations.appendText(id("username"), "Wesson");
        webDriverOperations.appendText(id("password"), "spring1234");
        webDriverOperations.click(id("login"));

        // 管理者情報内容確認
        assertThat(webDriverOperations.getText(id("username")), is("Wesson"));
        // ハッシュ化されたパスワードはArgon2PasswordEncodeされているか
        assertTrue(Pattern.compile(
                "\\$argon2id\\$v=19\\$m=16384,t=2,p=1\\$[0-9A-Za-z+/]{22}\\$[0-9A-Za-z+/]{43}")
                .matcher(webDriverOperations.getText(id("password")))
                .matches());
        // 表示されていたパスワードと一致すること
        assertThat(webDriverOperations.getText(id("password")), is(
                encodePassword));

        // ログアウトしてセッションを削除
        webDriverOperations.click(id("logout"));

        // メニュー画面の操作
        webDriverOperations.click(id("athn0501008"));

        // ログイン情報入力
        webDriverOperations.appendText(id("username"), "Wesson");
        webDriverOperations.appendText(id("password"), "spring5432");
        webDriverOperations.click(id("login"));

        // 認証エラーとなることを確認
        assertThat(webDriverOperations.getText(id("loginError")), is(
                "Bad credentials"));

        // ログアウトしてセッションを削除
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>パスワードをDelegatingPasswordEncoder（Pbkdf2PasswordEncoderに委譲）でハッシュ化できることを確認。</li>
     * </ul>
     */
    @Test
    public void testATHN0502001() {

        // メニュー画面の操作

        webDriverOperations.click(id("athn0502001"));

        // 管理者情報入力

        webDriverOperations.appendText(id("username"), "Jack");
        webDriverOperations.appendText(id("password"), "spring1234");
        webDriverOperations.click(id("create"));

        // 管理者情報内容確認
        assertThat(webDriverOperations.getText(id("getAdministratorName")), is(
                "Jack"));
        // ハッシュ化されたパスワードはPbkdf2PasswordEncodeされているか
        assertTrue(Pattern.compile("\\{pbkdf2\\}[./0-9A-Za-z]{96}").matcher(
                webDriverOperations.getText(id("getAfterEncodePassword")))
                .matches());
        assertThat(webDriverOperations.getText(id("getBeforeEncodePassword")),
                is("spring1234"));
    }

    /**
     * <ul>
     * <li>DelegatingPasswordEncoder（Pbkdf2PasswordEnxoderに委譲）でハッシュ化したパスワードを使用した場合、認証することができることを確認。</li>
     * </ul>
     */
    @Test
    public void testATHN0502002() {

        // テスト実行順不定の為、管理者登録処理から実施
        // メニュー画面の操作
        {
            webDriverOperations.click(id("athn0502001"));
        }

        // 管理者情報入力
        {
            webDriverOperations.appendText(id("username"), "Ann");
            webDriverOperations.appendText(id("password"), "spring1234");
            webDriverOperations.click(id("create"));
        }

        // 管理者情報内容確認
        String encodePassword;

        assertThat(webDriverOperations.getText(id("getAdministratorName")), is(
                "Ann"));
        // DBの中身と一致することを確認する為、事前に取得
        encodePassword = webDriverOperations.getText(id(
                "getAfterEncodePassword"));
        // ハッシュ化されたパスワードはBCryptPasswordEncodeされているか
        assertTrue(Pattern.compile("\\{pbkdf2\\}[./0-9A-Za-z]{96}").matcher(
                webDriverOperations.getText(id("getAfterEncodePassword")))
                .matches());
        assertThat(webDriverOperations.getText(id("getBeforeEncodePassword")),
                is("spring1234"));

        // パスワードハッシュメニュー画面に戻る
        webDriverOperations.click(id("goAthnMenu"));

        // メニュー画面の操作
        webDriverOperations.click(id("athn0502002"));

        // ログイン情報入力

        webDriverOperations.appendText(id("username"), "Ann");
        webDriverOperations.appendText(id("password"), "spring1234");
        webDriverOperations.click(id("login"));

        // 管理者情報内容確認
        assertThat(webDriverOperations.getText(id("username")), is("Ann"));
        // ハッシュ化されたパスワードはPbkdf2PasswordEncodeされているか
        assertTrue(Pattern.compile("\\{pbkdf2\\}[./0-9A-Za-z]{96}").matcher(
                webDriverOperations.getText(id("password"))).matches());
        // 表示されていたパスワードと一致すること
        assertThat(webDriverOperations.getText(id("password")), is(
                encodePassword));

        // ログアウトしてセッションを削除
        webDriverOperations.click(id("logout"));

        // メニュー画面の操作
        webDriverOperations.click(id("athn0502002"));

        // ログイン情報入力
        webDriverOperations.appendText(id("username"), "Ann");
        webDriverOperations.appendText(id("password"), "spring5432");
        webDriverOperations.click(id("login"));

        // 認証エラーとなることを確認
        assertThat(webDriverOperations.getText(id("loginError")), is(
                "Bad credentials"));

        // ログアウトしてセッションを削除
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>BCryptアルゴリズムでハッシュ化したパスワードをDelegatingPasswordEncoderで認証することができることを確認。</li>
     * </ul>
     */
    @Test
    public void testATHN0502003() {

        // メニュー画面の操作
        webDriverOperations.click(id("athn0502002"));

        // ログイン情報入力

        webDriverOperations.appendText(id("username"), "Tom");
        webDriverOperations.appendText(id("password"), "spring1234");
        webDriverOperations.click(id("login"));

        // 管理者情報内容確認
        assertThat(webDriverOperations.getText(id("username")), is("Tom"));
        // ハッシュ化されたパスワードはBCryptPasswordEncodeされているか
        assertTrue(Pattern.compile(
                "\\{bcrypt\\}\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}").matcher(
                        webDriverOperations.getText(id("password"))).matches());

        // ログアウトしてセッションを削除
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>SCryptアルゴリズムでハッシュ化したパスワードをDelegatingPasswordEncoderで認証することができることを確認。</li>
     * </ul>
     */
    @Test
    public void testATHN0502004() {

        // メニュー画面の操作
        webDriverOperations.click(id("athn0502002"));

        // ログイン情報入力

        webDriverOperations.appendText(id("username"), "Dave");
        webDriverOperations.appendText(id("password"), "spring1234");
        webDriverOperations.click(id("login"));

        // 管理者情報内容確認
        assertThat(webDriverOperations.getText(id("username")), is("Dave"));
        // ハッシュ化されたパスワードはSCryptPasswordEncodeされているか
        assertTrue(Pattern.compile(
                "\\{scrypt\\}\\$100801\\$[./0-9A-Za-z+/-]{22}==\\$[./0-9A-Za-z+/-]{43}=")
                .matcher(webDriverOperations.getText(id("password")))
                .matches());

        // ログアウトしてセッションを削除
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>Argon2アルゴリズムでハッシュ化したパスワードをDelegatingPasswordEncoderで認証することができることを確認。</li>
     * </ul>
     */
    @Test
    public void testATHN0502005() {

        // メニュー画面の操作
        webDriverOperations.click(id("athn0502002"));

        // ログイン情報入力

        webDriverOperations.appendText(id("username"), "Beretta");
        webDriverOperations.appendText(id("password"), "spring1234");
        webDriverOperations.click(id("login"));

        // 管理者情報内容確認
        assertThat(webDriverOperations.getText(id("username")), is("Beretta"));
        // ハッシュ化されたパスワードはArgon2PasswordEncodeされているか
        assertTrue(Pattern.compile(
                "\\{argon2\\}\\$argon2id\\$v=19\\$m=16384,t=2,p=1\\$[0-9A-Za-z+/]{22}\\$[0-9A-Za-z+/]{43}")
                .matcher(webDriverOperations.getText(id("password")))
                .matches());

        // ログアウトしてセッションを削除
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>AuthenticationSuccessEventのイベントハンドラが正常に実行されることを確認する。</li>
     * <li>AuthenticationSuccessEventLister</li>
     * <li>SessionFixationProtectionEventLister</li>
     * <li>InteractiveAuthenticationSuccessEventLister</li>
     * </ul>
     */
    @Test
    public void testATHN0601001() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("athn0601001"));

        // ログイン画面の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "ログインフォーム(AuthEventHandle)"));

        // 入力条件設定（認証成功）
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // イベントハンドラが実行されることを確認(ログ)
        {
            dbLogAssertOperations.waitForAssertion(1000);
            dbLogAssertOperations.assertContainsByRegexMessage(
                    ".*AuthenticationEventListeners",
                    "Autnenticated. username : Josh");
        }

        // イベントハンドラが実行されることを確認(ログ)
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(
                    ".*AuthenticationEventListeners",
                    "Session changed. sessionId : *");
        }

        // イベントハンドラが実行されることを確認(ログ)
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(
                    ".*AuthenticationEventListeners",
                    "Autnenticate completed. username : Josh");
        }

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>AuthenticationFailureEventのイベントハンドラが正常に実行されることを確認する。</li>
     * <li>AuthenticationFailureBadCredentialsEventLister</li>
     * <li>AuthenticationFailureLockedEventLister</li>
     * <li>AuthenticationFailureExpiredEventLister</li>
     * <li>AuthenticationFailureCredentialsExpiredEventLister</li>
     * </ul>
     */
    @Test
    public void testATHN0602001() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("athn0602001"));

        // ログイン画面の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "ログインフォーム(AuthEventHandle)"));

        // 入力条件設定（認証失敗：パスワード誤り）
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1111");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // イベントハンドラが実行されることを確認(ログ)
        {
            dbLogAssertOperations.waitForAssertion(1000);
            dbLogAssertOperations.assertContainsByRegexMessage(
                    ".*AuthenticationEventListeners",
                    "Bad credentials is detected. username : Josh");
        }

        // 入力条件設定（認証失敗：無効ユーザ）
        webDriverOperations.overrideText(id("username"), "Claire");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // イベントハンドラが実行されることを確認(ログ)
        {
            dbLogAssertOperations.waitForAssertion(1000);
            dbLogAssertOperations.assertContainsByRegexMessage(
                    ".*AuthenticationEventListeners",
                    "User deisabled is detected. username : Claire");
        }

        // 入力条件設定（認証失敗：アカウントロック）
        webDriverOperations.overrideText(id("username"), "Rock");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // イベントハンドラが実行されることを確認(ログ)
        {
            dbLogAssertOperations.waitForAssertion(1000);
            dbLogAssertOperations.assertContainsByRegexMessage(
                    ".*AuthenticationEventListeners",
                    "User locked is detected. username : Rock");
        }

        // 入力条件設定（認証失敗：アカウント有効期限切れ）
        webDriverOperations.overrideText(id("username"), "Edword");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // イベントハンドラが実行されることを確認(ログ)
        {
            dbLogAssertOperations.waitForAssertion(1000);
            dbLogAssertOperations.assertContainsByRegexMessage(
                    ".*AuthenticationEventListeners",
                    "Authentication expired is detected. username : Edword");
        }

        // 入力条件設定（認証失敗パスワード有効期限切れ）
        webDriverOperations.overrideText(id("username"), "Jenkins");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // イベントハンドラが実行されることを確認(ログ)
        {
            dbLogAssertOperations.waitForAssertion(1000);
            dbLogAssertOperations.assertContainsByRegexMessage(
                    ".*AuthenticationEventListeners",
                    "Credentials expired is detected. username : Jenkins");
        }

    }

    /**
     * <ul>
     * <li>AuthenticationFailureEventのイベントハンドラが正常に実行されることを確認する。</li>
     * <li>AuthenticationFailureServiceExceptionEventLister</li>
     * </ul>
     */
    @Test
    public void testATHN0602002() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("athn0602002"));

        // ログイン画面の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "ログインフォーム(AuthenticationServiceException)"));

        // 入力条件設定（認証失敗：パスワード誤り）
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // イベントハンドラが実行されることを確認(ログ)
        {
            dbLogAssertOperations.waitForAssertion(1000);
            dbLogAssertOperations.assertContainsByRegexMessage(
                    ".*AuthenticationEventListeners",
                    "ServiceException is detected. username : Josh");
        }
    }

    /**
     * <ul>
     * <li>ログアウトのデフォルト定義で正常にログアウトできることを確認する</li>
     * </ul>
     */
    @Test
    public void testATHN0701001() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("athn0701001"));

        // ログイン画面の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "ログインフォーム(loginForLogout)"));

        // 入力条件設定（認証成功）
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 認証後のユーザ情報の確認
        assertThat(webDriverOperations.getText(id("username")), is("Josh"));

        // パスの確認（認証成功）
        assertThat(webDriverOperations.getCurrentUrl(), is(applicationContextUrl
                + "/athn/0701/001?loginSuccess"));

        // ログアウトボタン押下
        // TODO:
        // GitLabの環境でclickを使用してSpringSecurityのデフォルトのログイン画面を表示しようとするとTimeOutExceptionが発生する
        // 暫定的にforceClickを使用して回避している。
        // https://github.com/SeleniumHQ/selenium/issues/9528 で検討中のため対応され次第取り込む
        webDriverOperations.forceClick(id("logout"));

        webDriverOperations.waitForDisplayed(textToBe(By.xpath("//h2"),
                "Please sign in"));

        // パスの確認（ログアウト成功）
        assertThat(webDriverOperations.getCurrentUrl(), is(applicationContextUrl
                + "/login?logout"));

        // 機能トップへ
        webDriverOperations.getWebDriver().get(applicationContextUrl + "/athn");

        // ログインしていないことの確認
        assertThat(webDriverOperations.getText(id("AuthenticateUserName")), is(
                "ゲスト"));

    }

    /**
     * <ul>
     * <li>ログアウト後にクッキーが削除されることを確認する。</li>
     * </ul>
     */
    @Test
    public void testATHN0702001() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("athn0702001"));

        // ログイン画面の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "ログインフォーム(loginForLogoutDeleteCookie)"));

        // 入力条件設定（認証成功）
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 認証後のユーザ情報の確認
        assertThat(webDriverOperations.getText(id("username")), is("Josh"));

        // パスの確認（認証成功）
        assertThat(webDriverOperations.getCurrentUrl(), is(applicationContextUrl
                + "/athn/0702/001?loginSuccess"));

        // ログアウトボタン押下
        // TODO:
        // GitLabの環境でclickを使用してSpringSecurityのデフォルトのログイン画面を表示しようとするとTimeOutExceptionが発生する
        // 暫定的にforceClickを使用して回避している。
        // https://github.com/SeleniumHQ/selenium/issues/9528 で検討中のため対応され次第取り込む
        webDriverOperations.forceClick(id("logout"));

        webDriverOperations.waitForDisplayed(textToBe(By.xpath("//h2"),
                "Please sign in"));

        // パスの確認（ログアウト成功）
        assertThat(webDriverOperations.getCurrentUrl(), is(applicationContextUrl
                + "/login?logout"));

        // 機能トップへ
        webDriverOperations.getWebDriver().get(applicationContextUrl + "/athn");

        // ログインしていないことの確認
        assertThat(webDriverOperations.getText(id("AuthenticateUserName")), is(
                "ゲスト"));
    }

    /**
     * <ul>
     * <li>Javaから認証情報へのアクセスが行えることを確認する。</li>
     * <li>DBに格納した情報で正常に認証されないことを確認する。</li>
     * </ul>
     */
    @Test
    public void testATHN0901001() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("athn0901001"));

        // ログイン画面の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "ログインフォーム(loginForDispAutentication)"));

        // 入力条件設定（認証成功）
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 認証後のユーザ情報の確認
        assertThat(webDriverOperations.getText(id("username")), is("Josh"));

        assertThat(webDriverOperations.getText(id("lastname")), is(
                "Josh Last"));
        assertThat(webDriverOperations.getText(id("useruuid")), is(
                "000000000000000000000000000000000001"));

        // パスの確認（認証成功）
        assertThat(webDriverOperations.getCurrentUrl(), is(applicationContextUrl
                + "/athn/0901/001?loginSuccess"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>認証成功時の遷移先の遷移先変更が正常に動作することを確認する。</li>
     * </ul>
     */
    @Test
    public void testATHN1201001() throws IOException {
        // メニュー画面の操作
        webDriverOperations.click(id("athn1201001"));

        // ログイン画面の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "ログインフォーム(CustomizedAuthSucessUrl)"));

        // 入力条件設定（認証成功）
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 認証後のユーザ情報の確認
        assertThat(webDriverOperations.getText(id("username")), is("Josh"));

        // パスの確認（認証成功）
        assertThat(webDriverOperations.getCurrentUrl(), is(applicationContextUrl
                + "/athn/1201/001?loginSuccess"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>認証成功時イベントハンドラが正常に機能することを確認する。</li>
     * </ul>
     */
    @Test
    public void testATHN1202001() throws IOException {
        // メニュー画面の操作
        webDriverOperations.click(id("athn1202001"));

        // ログイン画面の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "ログインフォーム(CustomizedAuthSucessHandler)"));

        // 入力条件設定（認証成功）
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 認証後のユーザ情報の確認
        assertThat(webDriverOperations.getText(id("username")), is("Josh"));

        // パスの確認（認証成功）
        assertThat(webDriverOperations.getCurrentUrl(), is(applicationContextUrl
                + "/athn/1202/001?loginSuccess"));

        // イベントハンドラが実行されることを確認(ログ)
        {
            dbLogAssertOperations.waitForAssertion(1000);
            dbLogAssertOperations.assertContainsByRegexMessage(
                    ".*MyAuthenticationSuccessHandler",
                    "Excute MyAuthenticationSuccessHandler. username : Josh");
        }

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));
    }

    /**
     * <ul>
     * <li>ExceptionMappingAuthenticationFailureHandlerが正常に動作することを確認する。</li>
     * <li>BadCredentialsException</li>
     * <li>UsernameNotFoundException</li>
     * <li>DisabledException</li>
     * <li>デフォルト</li>
     * </ul>
     */
    @Test
    public void testATHN1302001() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("athn1302001"));

        // ログイン画面の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "ログインフォーム(loginForCustomizedAuthFailure)"));

        // 入力条件設定（認証失敗：パスワード誤り）
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1111");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // パスの確認（認証エラー：パスワード誤り）
        assertThat(webDriverOperations.getCurrentUrl(), is(applicationContextUrl
                + "/athn/badCredentials"));

        // 戻る押下
        webDriverOperations.click(id("back"));

        // 入力条件設定（認証失敗：無効ユーザ）
        webDriverOperations.overrideText(id("username"), "Claire");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // パスの確認（認証エラー：無効ユーザ）
        assertThat(webDriverOperations.getCurrentUrl(), is(applicationContextUrl
                + "/athn/disabled"));

        // 戻る押下
        webDriverOperations.click(id("back"));

        // 入力条件設定（認証失敗：不明ユーザ）
        webDriverOperations.overrideText(id("username"), "Rob");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // パスの確認（認証エラー：不明ユーザ）
        assertThat(webDriverOperations.getCurrentUrl(), is(applicationContextUrl
                + "/athn/usernameNotFound"));

        // 戻る押下
        webDriverOperations.click(id("back"));

        // 入力条件設定（認証失敗：内部エラー(デフォルト)）
        webDriverOperations.overrideText(id("username"), "connectionerror");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // パスの確認（認証エラー：不明ユーザ(デフォルト)）
        assertThat(webDriverOperations.getCurrentUrl(), is(applicationContextUrl
                + "/athn/systemError"));

    }

    /**
     * <ul>
     * <li>ログアウト時イベントハンドラが正常に機能することを確認する。</li>
     * </ul>
     */
    @Test
    public void testATHN1501002() throws IOException {
        // メニュー画面の操作
        webDriverOperations.click(id("athn1501002"));

        // ログイン画面の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "ログインフォーム(CustomizedLogout)"));

        // 入力条件設定（認証成功）
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 認証後のユーザ情報の確認
        assertThat(webDriverOperations.getText(id("username")), is("Josh"));

        // パスの確認（認証成功）
        assertThat(webDriverOperations.getCurrentUrl(), is(applicationContextUrl
                + "/athn/1501/001?loginSuccess"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

        // イベントハンドラが実行されることを確認(ログ)
        {
            dbLogAssertOperations.waitForAssertion(1000);
            dbLogAssertOperations.assertContainsByRegexMessage(
                    ".*MyLogoutSuccessHandler",
                    "Excute MyLogoutSuccessHandler. username : Josh");
        }

    }

    /**
     * <ul>
     * <li>認証時にシステムエラーが発生した際の処理を確認する。</li>
     * <li>システムエラー発生時の画面表示が正常に行われることを確認する。</li>
     * </ul>
     */
    @Test
    public void testATHN1601001() throws IOException {
        // メニュー画面の操作
        webDriverOperations.click(id("athn1601001"));

        // ログイン画面の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "ログインフォーム(AutenticationSystemError)"));

        // 入力条件設定（認証失敗）
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1111");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // パスの確認（認証失敗：認証エラー）
        assertThat(webDriverOperations.getCurrentUrl(), is(applicationContextUrl
                + "/athn/1601/001/login?error"));

        // ログイン画面の確認
        assertThat(webDriverOperations.getText(id("loginError")), is(
                "Bad credentials"));

        // 入力条件設定（認証成功）
        webDriverOperations.overrideText(id("username"), "connectionerror");
        webDriverOperations.overrideText(id("password"), "spring1111");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // パスの確認（認証失敗：システムエラー）
        assertThat(webDriverOperations.getCurrentUrl(), is(applicationContextUrl
                + "/athn/1601/001/login?systemError"));

        // エラー画面の確認
        assertThat(webDriverOperations.getText(id("loginSystemError")), is(
                "システムエラーが発生しました。"));

    }

    /**
     * <ul>
     * <li>認証の元となる情報を独自実装で取得し、認証が成功することを確認する。</li>
     * </ul>
     */
    @Test
    public void testATHN1701001() throws IOException {

        dbLogAssertOperations.waitForAssertion(1000);
        // メニュー画面の操作
        webDriverOperations.click(id("athn1701001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 認証成功に独自実装した不可情報である住所が取得できることの確認
        assertThat(webDriverOperations.getText(id("customerName")), is("Josh"));
        assertThat(webDriverOperations.getText(id("customerAddress")), is(
                "san diego"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>ID/パスワード＋もう一つのキー（会社識別子）で認証がが成功することを確認する。</li>
     * </ul>
     */
    @Test
    public void testATHN1702001() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("athn1702001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("companyId"), "mister");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("companyIdLogin"));

        // 認証失敗時に設定したエラーメッセージが表示できることの確認
        assertThat(webDriverOperations.getText(id(
                "customerCompanyIdLoginError")), is("Bad credentials"));

        // 入力条件設定
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("companyId"), "MASTER");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("companyIdLogin"));

        // 認証成功のログインした認証情報であるcompanyIdの確認
        assertThat(webDriverOperations.getText(id("companyId")), is("MASTER"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>BeanValidationによるログイン画面の入力チェックが機能することを確認する。</li>
     * <li>BeanValidationを使用しても、正常にSpringScurityの認証処理が機能することを確認する。</li>
     * </ul>
     */
    @Test
    public void testATHN1703001() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("athn1703001"));

        // ログイン画面の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "ログインフォーム（BeanValidation）"));

        // 入力条件設定(Validationエラー)
        webDriverOperations.overrideText(id("username"), "");
        webDriverOperations.overrideText(id("password"), "");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 入力エラーメッセージの確認
        assertThat(webDriverOperations.getText(id("usernameError")), is(
                "値を入力してください。"));

        // 入力エラーメッセージの確認
        assertThat(webDriverOperations.getText(id("passwordError")), is(
                "値を入力してください。"));

        // 入力条件設定(認証エラー)
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring5432");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 認証失敗後のデフォルトメッセージの確認
        assertThat(webDriverOperations.getText(id("loginError")), is(
                "Bad credentials"));

        // 入力条件設定(Validationエラー)
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 認証後のユーザ情報の確認
        assertThat(webDriverOperations.getText(id("username")), is("Josh"));

        // パスの確認（認証成功）
        assertThat(webDriverOperations.getCurrentUrl(), is(applicationContextUrl
                + "/athn/1703/001?loginSuccess"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>パスワードをDelegatingPasswordEncoder（イテレーションカウントを変更したPbkdf2PasswordEncoderに委譲）でハッシュ化できることを確認。</li>
     * </ul>
     */
    @Test
    public void testATHN1801001() {

        // メニュー画面の操作

        webDriverOperations.click(id("athn1801001"));

        // 管理者情報入力

        webDriverOperations.appendText(id("username"), "Carl");
        webDriverOperations.appendText(id("password"), "spring1234");
        webDriverOperations.click(id("create"));

        // 管理者情報内容確認
        assertThat(webDriverOperations.getText(id("getAdministratorName")), is(
                "Carl"));
        // ハッシュ化されたパスワードはPbkdf2PasswordEncodeされているか
        assertTrue(Pattern.compile("\\{pbkdf2\\}[./0-9A-Za-z]{96}").matcher(
                webDriverOperations.getText(id("getAfterEncodePassword")))
                .matches());
        assertThat(webDriverOperations.getText(id("getBeforeEncodePassword")),
                is("spring1234"));
    }

    /**
     * <ul>
     * <li>イテレーションカウントを変更したPbkdf2PasswordEncoderでハッシュ化したパスワードをDelegatingPasswordEncoderで認証できることを確認。</li>
     * </ul>
     */
    @Test
    public void testATHN1801002() {

        // テスト実行順不定の為、管理者登録処理から実施
        // メニュー画面の操作
        {
            webDriverOperations.click(id("athn1801001"));
        }

        // 管理者情報入力
        {
            webDriverOperations.appendText(id("username"), "Samson");
            webDriverOperations.appendText(id("password"), "spring1234");
            webDriverOperations.click(id("create"));
        }

        // 管理者情報内容確認
        String encodePassword;

        assertThat(webDriverOperations.getText(id("getAdministratorName")), is(
                "Samson"));
        // DBの中身と一致することを確認する為、事前に取得
        encodePassword = webDriverOperations.getText(id(
                "getAfterEncodePassword"));
        // ハッシュ化されたパスワードはPbkdf2PasswordEncoderでエンコードされているか
        assertTrue(Pattern.compile("\\{pbkdf2\\}[./0-9A-Za-z]{96}").matcher(
                webDriverOperations.getText(id("getAfterEncodePassword")))
                .matches());
        assertThat(webDriverOperations.getText(id("getBeforeEncodePassword")),
                is("spring1234"));

        // パスワードハッシュメニュー画面に戻る
        webDriverOperations.click(id("goAthnMenu"));

        // メニュー画面の操作
        webDriverOperations.click(id("athn1801002"));

        // ログイン情報入力

        webDriverOperations.appendText(id("username"), "Samson");
        webDriverOperations.appendText(id("password"), "spring1234");
        webDriverOperations.click(id("login"));

        // 管理者情報が表示されるまで待機
        webDriverOperations.waitForDisplayed(ExpectedConditions
                .textToBePresentInElementLocated(By.id("screenTitle"),
                        "DelegatingPasswordEncoderを使用して認証"));

        // 管理者情報内容確認
        assertThat(webDriverOperations.getText(id("username")), is("Samson"));
        // ハッシュ化されたパスワードはPbkdf2PasswordEncodeされているか
        assertTrue(Pattern.compile("\\{pbkdf2\\}[./0-9A-Za-z]{96}").matcher(
                webDriverOperations.getText(id("password"))).matches());
        // 表示されていたパスワードと一致すること
        assertThat(webDriverOperations.getText(id("password")), is(
                encodePassword));

        // ログアウトしてセッションを削除
        webDriverOperations.click(id("logout"));

        // メニュー画面の操作
        webDriverOperations.click(id("athn1801002"));

        // ログイン情報入力

        webDriverOperations.appendText(id("username"), "Samson");
        webDriverOperations.appendText(id("password"), "spring5432");
        webDriverOperations.click(id("login"));

        // エラーページが表示されるまで待機
        webDriverOperations.waitForDisplayed(id("loginError"));

        // 認証エラーとなることを確認
        assertThat(webDriverOperations.getText(id("loginError")), is(
                "Bad credentials"));

        // ログアウトしてセッションを削除
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>BCryptPasswordEncoderでハッシュ化したパスワードをDelegatingPasswordEncoderで認証することができることを確認。</li>
     * </ul>
     */
    @Test
    public void testATHN1801003() {

        // テスト実行順不定の為、管理者登録処理から実施
        // メニュー画面の操作
        {
            webDriverOperations.click(id("athn0501001"));
        }

        // 管理者情報入力
        {
            webDriverOperations.appendText(id("username"), "Billy");
            webDriverOperations.appendText(id("password"), "spring1234");
            webDriverOperations.click(id("create"));
        }

        // 管理者情報内容確認
        String encodePassword;

        assertThat(webDriverOperations.getText(id("getAdministratorName")), is(
                "Billy"));
        // DBの中身と一致することを確認する為、事前に取得
        encodePassword = webDriverOperations.getText(id(
                "getAfterEncodePassword"));
        // ハッシュ化されたパスワードはBCryptPasswordEncodeされているか
        assertTrue(Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}")
                .matcher(webDriverOperations.getText(id(
                        "getAfterEncodePassword"))).matches());
        assertThat(webDriverOperations.getText(id("getBeforeEncodePassword")),
                is("spring1234"));

        // パスワードハッシュメニュー画面に戻る
        webDriverOperations.click(id("goAthnMenu"));

        // メニュー画面の操作
        webDriverOperations.click(id("athn1801003"));

        // ログイン情報入力

        webDriverOperations.appendText(id("username"), "Billy");
        webDriverOperations.appendText(id("password"), "spring1234");
        webDriverOperations.click(id("login"));

        // 管理者情報内容確認
        assertThat(webDriverOperations.getText(id("username")), is("Billy"));
        // ハッシュ化されたパスワードはBCryptPasswordEncodeされているか
        assertTrue(Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}")
                .matcher(webDriverOperations.getText(id("password")))
                .matches());
        // 表示されていたパスワードと一致すること
        assertThat(webDriverOperations.getText(id("password")), is(
                encodePassword));

        // ログアウトしてセッションを削除
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>パスワードをMessageDigestPasswordEncoderでハッシュ化できることを確認。</li>
     * </ul>
     */
    @Test
    public void testATHN1901001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("athn1901001"));
        }

        // 管理者情報入力
        {
            webDriverOperations.appendText(id("username"), "Shigeru");
            webDriverOperations.appendText(id("password"), "spring1234");
            webDriverOperations.click(id("create"));
        }

        // 管理者情報内容確認
        {
            assertThat(webDriverOperations.getText(id("getAdministratorName")),
                    is("Shigeru"));
            // ハッシュ化されたパスワードはMessageDigestPasswordEncodeされているか
            assertTrue(webDriverOperations.getText(id("getAfterEncodePassword"))
                    .matches("\\{\\S{1,}\\}[0-9a-f]{128}"));
            assertThat(webDriverOperations.getText(id(
                    "getBeforeEncodePassword")), is("spring1234"));
        }
    }

    /**
     * <ul>
     * <li>MessageDigestPasswordEncoderでハッシュ化したパスワードを使用した場合、認証することができることを確認。</li>
     * </ul>
     */
    @Test
    public void testATHN1901002() {

        // テスト実行順不定の為、管理者登録処理から実施
        // メニュー画面の操作
        {
            webDriverOperations.click(id("athn1901001"));
        }

        // 管理者情報入力
        {
            webDriverOperations.appendText(id("username"), "Akira");
            webDriverOperations.appendText(id("password"), "spring1234");
            webDriverOperations.click(id("create"));
        }

        // 管理者情報内容確認
        String encodePassword;
        {
            assertThat(webDriverOperations.getText(id("getAdministratorName")),
                    is("Akira"));
            // DBの中身と一致することを確認する為、事前に取得
            encodePassword = webDriverOperations.getText(id(
                    "getAfterEncodePassword"));
            // ハッシュ化されたパスワードはMessageDigestPasswordEncodeされているか
            assertTrue(webDriverOperations.getText(id("getAfterEncodePassword"))
                    .matches("\\{\\S{1,}\\}[0-9a-f]{128}"));
            assertThat(webDriverOperations.getText(id(
                    "getBeforeEncodePassword")), is("spring1234"));
        }

        // パスワードハッシュメニュー画面に戻る
        webDriverOperations.click(id("goAthnMenu"));

        // メニュー画面の操作
        {
            webDriverOperations.click(id("athn1901002"));
        }

        // ログイン情報入力
        {
            webDriverOperations.appendText(id("username"), "Akira");
            webDriverOperations.appendText(id("password"), "spring1234");
            webDriverOperations.click(id("login"));
        }

        // 管理者情報内容確認
        {
            assertThat(webDriverOperations.getText(id("username")), is(
                    "Akira"));
            // ハッシュ化されたパスワードはMessageDigestPasswordEncodeされているか
            assertTrue(webDriverOperations.getText(id("password")).matches(
                    "\\{\\S{1,}\\}[0-9a-f]{128}"));
            // 表示されていたパスワードと一致すること
            assertThat(webDriverOperations.getText(id("password")), is(
                    encodePassword));
        }

        // ログアウトしてセッションを削除
        {
            webDriverOperations.click(id("logout"));
        }

        // メニュー画面の操作
        webDriverOperations.click(id("athn1801002"));

        // ログイン情報入力
        webDriverOperations.appendText(id("username"), "Akira");
        webDriverOperations.appendText(id("password"), "spring5432");
        webDriverOperations.click(id("login"));

        // 認証エラーとなることを確認
        assertThat(webDriverOperations.getText(id("loginError")), is(
                "Bad credentials"));

        // ログアウトしてセッションを削除
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>パスワードをDelegatingPasswordEncoder(MessageDigestPasswordEncoderに委譲)でハッシュ化できることを確認。</li>
     * </ul>
     */
    @Test
    public void testATHN1901003() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("athn1901003"));
        }

        // 管理者情報入力
        {
            webDriverOperations.appendText(id("username"), "Satoshi");
            webDriverOperations.appendText(id("password"), "spring1234");
            webDriverOperations.click(id("create"));
        }

        // 管理者情報内容確認
        {
            assertThat(webDriverOperations.getText(id("getAdministratorName")),
                    is("Satoshi"));
            // ハッシュ化されたパスワードはShaPasswordEncodeされているか
            assertTrue(webDriverOperations.getText(id("getAfterEncodePassword"))
                    .matches("\\{MD\\}\\{\\S{1,}\\}[0-9a-f]{128}"));
            assertThat(webDriverOperations.getText(id(
                    "getBeforeEncodePassword")), is("spring1234"));
        }
    }

    /**
     * <ul>
     * <li>DelegatingPasswordEncoder(MessageDigestPasswordEncoderに委譲)でハッシュ化したパスワードを使用した場合、認証することができることを確認。</li>
     * </ul>
     */
    @Test
    public void testATHN1901004() {

        // テスト実行順不定の為、管理者登録処理から実施
        // メニュー画面の操作
        {
            webDriverOperations.click(id("athn1901003"));
        }

        // 管理者情報入力
        {
            webDriverOperations.appendText(id("username"), "Megumi");
            webDriverOperations.appendText(id("password"), "spring1234");
            webDriverOperations.click(id("create"));
        }

        // 管理者情報内容確認
        String encodePassword;
        {
            assertThat(webDriverOperations.getText(id("getAdministratorName")),
                    is("Megumi"));
            // DBの中身と一致することを確認する為、事前に取得
            encodePassword = webDriverOperations.getText(id(
                    "getAfterEncodePassword"));
            // ハッシュ化されたパスワードはMessageDigestPasswordEncodeされているか
            assertTrue(webDriverOperations.getText(id("getAfterEncodePassword"))
                    .matches("\\{MD\\}\\{\\S{1,}\\}[0-9a-f]{128}"));
            assertThat(webDriverOperations.getText(id(
                    "getBeforeEncodePassword")), is("spring1234"));
        }

        // パスワードハッシュメニュー画面に戻る
        webDriverOperations.click(id("goAthnMenu"));

        // メニュー画面の操作
        {
            webDriverOperations.click(id("athn1901004"));
        }

        // ログイン情報入力
        {
            webDriverOperations.appendText(id("username"), "Megumi");
            webDriverOperations.appendText(id("password"), "spring1234");
            webDriverOperations.click(id("login"));
        }

        // 管理者情報内容確認
        {
            assertThat(webDriverOperations.getText(id("username")), is(
                    "Megumi"));
            // ハッシュ化されたパスワードはMessageDigestPasswordEncodeされているか
            assertTrue(webDriverOperations.getText(id("password")).matches(
                    "\\{MD\\}\\{\\S{1,}\\}[0-9a-f]{128}"));
            // 表示されていたパスワードと一致すること
            assertThat(webDriverOperations.getText(id("password")), is(
                    encodePassword));
        }

        // ログアウトしてセッションを削除
        {
            webDriverOperations.click(id("logout"));
        }

        // メニュー画面の操作
        webDriverOperations.click(id("athn1901004"));

        // ログイン情報入力
        webDriverOperations.appendText(id("username"), "Megumi");
        webDriverOperations.appendText(id("password"), "spring5432");
        webDriverOperations.click(id("login"));

        // 認証エラーとなることを確認
        assertThat(webDriverOperations.getText(id("loginError")), is(
                "Bad credentials"));

        // ログアウトしてセッションを削除
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>RememberMe機能を有効にしてブラウザを落とした後に再度別ブラウザで認証済み情報が取得できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testATHN2201001() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("athn2201001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 認証成功後の画面遷移確認
        assertThat(webDriverOperations.getText(id("username")), is("Josh"));

        // sessionのクッキーを削除することにより、別セッションで実行する。
        webDriverOperations.deleteCookie("JSESSIONID");

        // TOP画面
        webDriverOperations.click(id("springTestTop"));

        webDriverOperations.click(id("athnLink"));

        webDriverOperations.click(id("athn2201001showInfo"));

        // RememberMe機能を使用しているので、ログインせずにログイン後の情報が見れることの確認
        assertThat(webDriverOperations.getText(id("username")), is("Josh"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>RememberMe機能を無効にしてブラウザを落とした後に再度別ブラウザで認証済み情報が取得できないことを確認する。</li>
     * </ul>
     */
    @Test
    public void testATHN2201002() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("athn2201001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");
        // RememberMeのチェックを外す
        webDriverOperations.click(id("remember-me"));

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 認証成功後の画面遷移確認
        assertThat(webDriverOperations.getText(id("username")), is("Josh"));

        webDriverOperations.deleteCookie("JSESSIONID");

        webDriverOperations.click(id("springTestTop"));

        webDriverOperations.click(id("athnLink"));

        webDriverOperations.click(id("athn2201001showInfo"));

        // RememberMe機能を使用していないので、ログインせずにログイン後の情報が見れないことの確認
        assertThat(webDriverOperations.getText(id("username")), is(""));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>LogoutSuccessEventのイベントハンドラが正常に実行されることを確認する。</li>
     * <li>LogoutSuccessEventLister</li>
     * </ul>
     */
    @Test
    public void testATHN2301001() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("athn2301001"));

        // ログイン画面の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "ログインフォーム(LogoutSuccessEventHandle)"));

        // 入力条件設定（認証成功）
        webDriverOperations.overrideText(id("username"), "Emily");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 認証後のユーザ情報の確認
        assertThat(webDriverOperations.getText(id("username")), is("Emily"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

        // ログインしていないことの確認
        assertThat(webDriverOperations.getText(id("AuthenticateUserName")), is(
                "ゲスト"));

        // イベントハンドラが実行されることを確認(ログ)
        {
            dbLogAssertOperations.waitForAssertion(1000);
            dbLogAssertOperations.assertContainsByRegexMessage(
                    ".*AuthenticationEventListeners",
                    "Logout completed. username : Emily");
        }

    }

}
