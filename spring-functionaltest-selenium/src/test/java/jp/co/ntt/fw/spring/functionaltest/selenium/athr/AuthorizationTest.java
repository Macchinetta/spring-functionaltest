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
package jp.co.ntt.fw.spring.functionaltest.selenium.athr;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.isOneOf;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.id;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.inject.Inject;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import jp.co.ntt.fw.spring.functionaltest.selenium.ApServerName;
import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

public class AuthorizationTest extends FunctionTestSupport {

    @Inject
    protected RestTemplate restTemplate;

    /**
     * <ul>
     * <li>WEBリソースへの認可設定（未認証否許可）が正常に機能することを確認する。</li>
     * <li>WEBリソースへの認可設定（認証許可）が正常に機能することを確認する。</li>
     * <p>
     * isAuthenticated()
     * </p>
     * </ul>
     */
    @Test
    public void testATHR0201001() throws IOException {
        // メニュー画面の操作
        webDriverOperations.click(id("athr0201001"));

        // ログイン画面の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "Staff Login Page"));

        // 入力条件設定
        // JoshはROLE_USER
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // ユーザ情報が見えるページに遷移できることの確認
        assertThat(webDriverOperations.getText(id("username")), is("Josh"));

        // 機能毎のトップページを表示
        webDriverOperations.displayPage(getPackageRootUrl());

        // メニュー画面の操作
        webDriverOperations.click(id("athr0201001"));

        // ユーザ情報が見えるページに遷移できることの確認
        assertThat(webDriverOperations.getText(id("username")), is("Josh"));

        // 次のテストに影響するためログアウトする
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>
     * spring-mvc側で、suffix-pattern="true"、trailing-slash="true"(両方ともtrueがデフォルト値)を設定したリクエストマッピングに対するspring-security側の認可設定（未認証否許可
     * ）が正常に機能することを確認する</li>
     * </ul>
     */
    @Test
    public void testATHR0201002() throws IOException {
        {
            // メニュー画面の操作
            webDriverOperations.click(id("athr0201002"));

            // ログイン画面の確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "Staff Login Page"));

            // 入力条件設定
            webDriverOperations.overrideText(id("username"), "Josh");
            webDriverOperations.overrideText(id("password"), "spring1234");

            // ログインボタン押下
            webDriverOperations.click(id("login"));

            // ユーザ情報が見えるページに遷移できることの確認
            assertThat(webDriverOperations.getText(id("username")), is("Josh"));

            // 機能毎のトップページを表示
            webDriverOperations.displayPage(getPackageRootUrl());

            // メニュー画面の操作
            webDriverOperations.click(id("athr0201002"));

            // ユーザ情報が見えるページに遷移できることの確認
            // JoshはROLE_USERのみ
            assertThat(webDriverOperations.getText(id("username")), is("Josh"));
            assertThat(webDriverOperations.getText(id("userRoles")), is(
                    "[ROLE_USER]"));

            // 次のテストに影響するためログアウトする
            webDriverOperations.click(id("logout"));

            // 機能毎のトップページを表示
            webDriverOperations.displayPage(getPackageRootUrl());
        }
        {
            // メニュー画面の操作
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0201/002/restrict.do");
            // ログイン画面の確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "Staff Login Page"));

            // 機能毎のトップページを表示
            webDriverOperations.displayPage(getPackageRootUrl());
        }
        {
            // メニュー画面の操作
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0201/002/restrict");
            // ログイン画面の確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "Staff Login Page"));

            // 機能毎のトップページを表示
            webDriverOperations.displayPage(getPackageRootUrl());
        }
        {
            // メニュー画面の操作
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0201/002/restrict/");
            // ログイン画面の確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "Staff Login Page"));

            // 機能毎のトップページを表示
            webDriverOperations.displayPage(getPackageRootUrl());
        }
        {
            // メニュー画面の操作
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0201/002/restrict.do/");
            // タイトルの確認
            assertThat(webDriverOperations.getTitle(), is(
                    "Resource Not Found Error!"));

            // 機能毎のトップページを表示
            webDriverOperations.displayPage(getPackageRootUrl());
        }
        {
            // メニュー画面の操作
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0201/002/restrict%20");

            ApServerName apServerName = webDriverOperations.getApServerName();
            // WebSphere Liberty/Traditionalでは末尾の%20をトリムしてしまうので
            // ログインページに遷移する。詳細は #1027
            if (apServerName == ApServerName.WEBSPHERELP
                    || apServerName == ApServerName.WEBSPHERETR) {
                // ログインページのタイトルの確認
                assertThat(webDriverOperations.getText(id("screenTitle")), is(
                        "Staff Login Page"));
            } else {
                // エラーページのタイトルの確認
                assertThat(webDriverOperations.getTitle(), is(
                        "Resource Not Found Error!"));
            }
        }
        {
            // メニュー画面の操作
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0201/002/restrictA");
            // タイトルの確認
            assertThat(webDriverOperations.getTitle(), is(
                    "Resource Not Found Error!"));

            // 機能毎のトップページを表示
            webDriverOperations.displayPage(getPackageRootUrl());

            // ログイン完了後の遷移先情報がセッションに格納されてしまい、後続のテストに影響するため、セッションを除去する
            webDriverOperations.deleteCookie("JSESSIONID");
        }
    }

    /**
     * <ul>
     * <li>認可設定が大文字・小文字までリクエストマッチングを行い、正常に機能するか確認する</li>
     * </ul>
     */
    @Test
    public void testATHR0201004() throws IOException {
        {
            // メニュー画面の操作
            webDriverOperations.click(id("athr0201003"));

            // ログイン画面の確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "Staff Login Page"));

            // 入力条件設定
            webDriverOperations.overrideText(id("username"), "Josh");
            webDriverOperations.overrideText(id("password"), "spring1234");

            // ログインボタン押下
            webDriverOperations.click(id("login"));

            // ログイン完了画面のタイトル確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "showForAccessSuccessfully"));

            // 機能毎のトップページを表示
            webDriverOperations.displayPage(getPackageRootUrl());

            // メニュー画面の操作
            webDriverOperations.click(id("athr0201003"));

            // ログイン完了画面のタイトル確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "showForAccessSuccessfully"));

            // 次のテストに影響するためログアウトする
            webDriverOperations.click(id("logout"));

            // 機能毎のトップページを表示
            webDriverOperations.displayPage(getPackageRootUrl());
        }
        {
            // メニュー画面の操作
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0201/004/showForAccessSuccessfully");
            // ログイン画面の確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "Staff Login Page"));

            // 機能毎のトップページを表示
            webDriverOperations.displayPage(getPackageRootUrl());
        }
        {
            // メニュー画面の操作
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0201/004/showforaccesssuccessfully");
            // タイトルの確認
            assertThat(webDriverOperations.getTitle(), is(
                    "Resource Not Found Error!"));

            // 機能毎のトップページを表示
            webDriverOperations.displayPage(getPackageRootUrl());

            // ログイン完了後の遷移先情報がセッションに格納されてしまい、後続のテストに影響するため、セッションを除去する
            webDriverOperations.deleteCookie("JSESSIONID");
        }
    }

    /**
     * <ul>
     * <li>メソッド認可設定が正常に機能することを確認する。</li>
     * <li>メソッド引数による認可設定が正常に機能することを確認する。</li>
     * </ul>
     */
    @Test
    public void testATHR0301001() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("athr0301001"));

        // 入力条件設定
        // JackはROLE_ADMIN
        webDriverOperations.overrideText(id("username"), "Jack");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 入力条件設定
        webDriverOperations.overrideText(id("device"), "eth0");
        webDriverOperations.overrideText(id("executeUser"), "Jack");

        // 参照処理実行押下
        webDriverOperations.click(id("select"));

        // 参照結果確認
        assertThat(webDriverOperations.getText(id("device")), is("eth0"));
        assertThat(webDriverOperations.getText(id("broadcast")), is(
                "192.168.10.255"));
        assertThat(webDriverOperations.getText(id("ipaddr")), is(
                "192.168.10.10"));
        assertThat(webDriverOperations.getText(id("netmask")), is(
                "255.255.255.0"));
        assertThat(webDriverOperations.getText(id("network")), is(
                "192.168.10.0"));
        assertThat(webDriverOperations.getText(id("onboot")), is("yes"));
        assertThat(webDriverOperations.getText(id("deviceType")), is(
                "Ethernet"));
        assertThat(webDriverOperations.getText(id("gateway")), is(
                "192.168.10.1"));
        assertThat(webDriverOperations.getText(id("owner")), is("Jack"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

        // メニュー画面の操作
        webDriverOperations.click(id("athr0301001"));

        // 入力条件設定
        // KenはROLE_STAFF
        webDriverOperations.overrideText(id("username"), "Jack");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 入力条件設定
        webDriverOperations.overrideText(id("device"), "eth2");
        webDriverOperations.overrideText(id("executeUser"), "Josh");

        // 参照処理実行押下
        webDriverOperations.click(id("select"));

        // 参照結果確認
        assertThat(webDriverOperations.getText(id("device")), is("eth2"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

        // メニュー画面の操作
        webDriverOperations.click(id("athr0301001"));

        // 入力条件設定
        // KenはROLE_STAFF
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 入力条件設定
        webDriverOperations.overrideText(id("device"), "eth2");
        webDriverOperations.overrideText(id("executeUser"), "Josh");

        // 参照処理実行押下
        webDriverOperations.click(id("select"));

        // 参照結果確認:同じownerであれば検索可能
        assertThat(webDriverOperations.getText(id("device")), is("eth2"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

        // メニュー画面の操作
        webDriverOperations.click(id("athr0301001"));

        // 入力条件設定
        // KenはROLE_STAFF
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 入力条件設定
        webDriverOperations.overrideText(id("device"), "eth1" + "");
        webDriverOperations.overrideText(id("executeUser"), "Ken");

        // 参照処理実行押下
        webDriverOperations.click(id("select"));

        // 'ROLE_STAFF'ではアクセスできないことの確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "methodAccessDeniedPage"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>認可の設定のtaglibが正常に機能することを確認する。(JSP要素)</li>
     * </ul>
     */
    @Test
    public void testATHR0401001() throws IOException {
        // メニュー画面の操作
        webDriverOperations.click(id("athr0401001"));

        // 入力条件設定
        // JackはROLE_ADMIN
        webDriverOperations.overrideText(id("username"), "Jack");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 'ROLE_ADMIN'では指定タグ内の文言が表示されないことの確認
        assertThat(webDriverOperations.getText(id("staffRole")), is(
                "This screen is for ROLE_ADMIN"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

        // メニュー画面の操作
        webDriverOperations.click(id("athr0401001"));

        // 入力条件設定
        // KenはROLE_STAFF
        webDriverOperations.overrideText(id("username"), "Ken");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 'ROLE_STAFF'では指定タグ内の文言が表示されないことの確認
        assertThat(webDriverOperations.getText(id("staffRole")), is(""));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>認可の設定のtaglibが正常に機能することを確認する。(URL)</li>
     * </ul>
     */
    @Test
    public void testATHR0402001() throws IOException {
        // メニュー画面の操作
        webDriverOperations.click(id("athr0401001"));

        // 入力条件設定
        // JackはROLE_ADMIN
        webDriverOperations.overrideText(id("username"), "Jack");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 'ROLE_ADMIN'では指定リンクが表示されることの確認
        assertThat(webDriverOperations.getText(id("staffRoleLink")), is(
                "Go To ATHR Top Page"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

        // メニュー画面の操作
        webDriverOperations.click(id("athr0401001"));

        // 入力条件設定
        // KenはROLE_STAFF
        webDriverOperations.overrideText(id("username"), "Ken");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 'ROLE_STAFF'ではリンクが表示されないことの確認
        assertThat(webDriverOperations.getText(id("staffRoleLink")), is(""));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>JSPにおいて認可判定の結果を変数に格納し、使用することができることを確認する。</li>
     * </ul>
     */
    @Test
    public void testATHR0403001() throws IOException {
        // メニュー画面の操作
        webDriverOperations.click(id("athr0401001"));

        // 入力条件設定
        // JackはROLE_ADMIN
        webDriverOperations.overrideText(id("username"), "Jack");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 'ROLE_ADMIN'では指定リンクが表示されることの確認
        assertThat(webDriverOperations.getText(id("staffRoleVarLink")), is(
                "Go To ATHR Top Page(variable)"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

        // メニュー画面の操作
        webDriverOperations.click(id("athr0401001"));

        // 入力条件設定
        // KenはROLE_STAFF
        webDriverOperations.overrideText(id("username"), "Ken");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 'ROLE_STAFF'ではリンクが表示されないことの確認
        assertThat(webDriverOperations.getText(id("staffRoleVarLink")), is(""));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>アクセスポリシーを適用するWebリソースの指定が正常に機能することを確認する。()</li>
     * </ul>
     */
    @Test
    public void testATHR0601001() throws IOException {
        // メニュー画面の操作
        webDriverOperations.click(id("athr0601001"));

        // 入力条件設定
        // SamはACCOUNT_USER
        webDriverOperations.overrideText(id("username"), "Sam");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 'ACCOUNT_MANAGER''ACCOUNT_USER'がアクセスできるリンクをクリック
        webDriverOperations.click(id("accountsLink"));

        // アクセス許可の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "アクセスポリシに対応するユーザのみが表示できるページ（ROLE）"));

        // 戻る
        webDriverOperations.click(id("back"));

        // 'ACCOUNT_MANAGER'がアクセスできるリンクをクリック
        webDriverOperations.click(id("mangerLink"));

        // アクセスエラーの確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "forbidden Error!"));

        // 機能毎のトップページを表示
        webDriverOperations.displayPage(getPackageRootUrl());

        // メニュー画面の操作
        webDriverOperations.click(id("athr0601001"));

        // 入力条件設定
        // RockはACCOUNT_Manager
        webDriverOperations.overrideText(id("username"), "Rock");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 'ACCOUNT_MANAGER'がアクセスできるリンクをクリック
        webDriverOperations.click(id("mangerLink"));

        // アクセス許可の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "アクセスポリシに対応するユーザのみが表示できるページ（ROLE）"));

        // 戻る
        webDriverOperations.click(id("back"));

        // 認証済みユーザアクセスできるリンクをクリック
        webDriverOperations.click(id("allLink"));

        // アクセス許可の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "アクセスポリシに対応するユーザのみが表示できるページ（ROLE）"));

        // 戻る
        webDriverOperations.click(id("back"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));
    }

    /**
     * <ul>
     * <li>アクセスポリシーを適用するWebリソースの指定が正常に機能することを確認する。(許可)</li>
     * </ul>
     */
    @Test
    public void testATHR0601002() throws IOException {
        // メニュー画面の操作
        webDriverOperations.click(id("athr0601002"));

        // 入力条件設定
        // RockはACCOUNT_MANAGER
        webDriverOperations.overrideText(id("username"), "Rock");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 'ACCOUNT_MANAGER'がアクセスできるリンクをクリック
        webDriverOperations.click(id("managerLink"));

        // アクセス許可の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "アクセスポリシに対応するユーザのみが表示できるページ（LOCAL IPADDRESS）"));

        // 戻る
        webDriverOperations.click(id("back"));

        // 'ROLE_CONFIGURATION_MANAGER'+ localhostがアクセスできるリンクをクリック
        webDriverOperations.click(id("configLink"));

        // アクセスエラーの確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "forbidden Error!"));

        // 機能毎のトップページを表示
        webDriverOperations.displayPage(getPackageRootUrl());

        // メニュー画面の操作
        webDriverOperations.click(id("athr0601002"));

        // 入力条件設定
        // FordはROLE_CONFIGURATION_MANAGER
        webDriverOperations.overrideText(id("username"), "Ford");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 'ACCOUNT_MANAGER'がアクセスできるリンクをクリック
        webDriverOperations.click(id("managerLink"));

        // アクセスエラーの確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "forbidden Error!"));

        // 機能毎のトップページを表示
        webDriverOperations.displayPage(getPackageRootUrl());

        // メニュー画面の操作
        webDriverOperations.click(id("athr0601002"));

        // 入力条件設定
        // FordはROLE_CONFIGURATION_MANAGER
        webDriverOperations.overrideText(id("username"), "Ford");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 'ROLE_CONFIGURATION_MANAGER'+ localhostがアクセスできるリンクをクリック
        webDriverOperations.click(id("configLink"));

        // アクセス許可の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "アクセスポリシに対応するユーザのみが表示できるページ（LOCAL IPADDRESS）"));

        // 戻る
        webDriverOperations.click(id("back"));

        // 'ADMIN'がアクセスできるリンクをクリック
        webDriverOperations.click(id("adminLink"));

        // アクセスエラーの確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "forbidden Error!"));

        // 機能毎のトップページを表示
        webDriverOperations.displayPage(getPackageRootUrl());

        // メニュー画面の操作
        webDriverOperations.click(id("athr0601002"));

        // 入力条件設定
        // JackはROLE_ADMIN
        webDriverOperations.overrideText(id("username"), "Jack");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 'ROLE_ADMIN'がアクセスできるリンクをクリック
        webDriverOperations.click(id("adminLink"));

        // アクセス許可の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "アクセスポリシに対応するユーザのみが表示できるページ（LOCAL IPADDRESS）"));

        // 戻る
        webDriverOperations.click(id("back"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));
    }

    /**
     * <ul>
     * <li>アクセスポリシーを適用するWebリソースの指定が正常に機能することを確認する。(IP拒否)</li>
     * </ul>
     */
    @Test
    public void testATHR0601003() throws IOException {
        // メニュー画面の操作
        webDriverOperations.click(id("athr0601003"));

        // 入力条件設定
        // FordはROLE_CONFIGURATION_MANAGER
        webDriverOperations.overrideText(id("username"), "Ford");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 'ACCOUNT_MANAGER'がアクセスできるリンクをクリック
        webDriverOperations.click(id("managerLink"));

        // アクセスエラーの確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "forbidden Error!"));

        // 機能毎のトップページを表示
        webDriverOperations.displayPage(getPackageRootUrl());

        // メニュー画面の操作
        webDriverOperations.click(id("athr0601003"));

        // 入力条件設定
        // FordはROLE_CONFIGURATION_MANAGER
        webDriverOperations.overrideText(id("username"), "Ford");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 'ROLE_CONFIGURATION_MANAGER'+ localhostがアクセスできるリンクをクリック
        webDriverOperations.click(id("configLink"));

        // アクセスエラーの確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "forbidden Error!"));

        // 機能毎のトップページを表示
        webDriverOperations.displayPage(getPackageRootUrl());

        // メニュー画面の操作
        webDriverOperations.click(id("athr0601003"));

        // FordはROLE_CONFIGURATION_MANAGER
        webDriverOperations.overrideText(id("username"), "Ford");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 'ADMIN'がアクセスできるリンクをクリック
        webDriverOperations.click(id("adminLink"));

        // アクセスエラーの確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "forbidden Error!"));

        // 機能毎のトップページを表示
        webDriverOperations.displayPage(getPackageRootUrl());

        // メニュー画面の操作
        webDriverOperations.click(id("athr0601003"));

        // 入力条件設定
        // JackはROLE_ADMIN
        webDriverOperations.overrideText(id("username"), "Jack");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 'ROLE_ADMIN'がアクセスできるリンクをクリック
        webDriverOperations.click(id("adminLink"));

        // アクセス許可の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "アクセスポリシに対応するユーザのみが表示できるページ(NOT LOCAL IPADDRESS)"));

        // 戻る
        webDriverOperations.click(id("back"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));
    }

    /**
     * <ul>
     * <li>アクセスポリシーを適用するWebリソースの指定が正常に機能することを確認する。(すべて拒否)</li>
     * </ul>
     */
    @Test
    public void testATHR0601004() throws IOException {
        // メニュー画面の操作
        webDriverOperations.click(id("athr0601004"));

        // 入力条件設定
        // JoshはROLE_USER
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 'USER''ADMIN'がアクセスできるリンクをクリック
        webDriverOperations.click(id("accountsLink"));

        // アクセス許可の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "アクセスポリシに対応するユーザのみが表示できるページ（DENY ALL）"));

        // 戻る
        webDriverOperations.click(id("back"));

        // 'USER''ADMIN'がアクセスできるリンクをクリック
        webDriverOperations.click(id("mangerLink"));

        // アクセスエラーの確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "forbidden Error!"));

        // 機能毎のトップページを表示
        webDriverOperations.displayPage(getPackageRootUrl());

        // メニュー画面の操作
        webDriverOperations.click(id("athr0601004"));

        // 入力条件設定
        // JoshはROLE_USER
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // すべて拒否されるリンクをクリック
        webDriverOperations.click(id("denyLink"));

        // アクセスエラーの確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "forbidden Error!"));

        // 機能毎のトップページを表示
        webDriverOperations.displayPage(getPackageRootUrl());

        // メニュー画面の操作
        webDriverOperations.click(id("athr0601004"));

        // 入力条件設定
        // JoshはROLE_ADMIN
        webDriverOperations.overrideText(id("username"), "Jack");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 'USER''ADMIN'がアクセスできるリンクをクリック
        webDriverOperations.click(id("accountsLink"));

        // アクセス許可の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "アクセスポリシに対応するユーザのみが表示できるページ（DENY ALL）"));

        // 戻る
        webDriverOperations.click(id("back"));

        // 'USER''ADMIN'がアクセスできるリンクをクリック
        webDriverOperations.click(id("mangerLink"));

        // アクセス許可の確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "アクセスポリシに対応するユーザのみが表示できるページ（DENY ALL）"));

        // 戻る
        webDriverOperations.click(id("back"));

        // すべて拒否されるリンクをクリック
        webDriverOperations.click(id("denyLink"));

        // アクセスエラーの確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "forbidden Error!"));

        // 機能毎のトップページを表示
        webDriverOperations.displayPage(getPackageRootUrl());

        // メニュー画面の操作
        webDriverOperations.click(id("athr0601004"));

        // ログアウト
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>パス変数を使用するアクセスポリシーの認可/否認可が正常に機能することを確認する。</li>
     * </ul>
     */
    @Test
    public void testATHR0601019() throws IOException {
        // メニュー画面の操作
        webDriverOperations.click(id("athr0601019"));

        // 入力条件設定
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // "Josh"でログインしたことを確認
        assertThat(webDriverOperations.getText(id("loginUserName")), is(
                "Josh"));

        // パターン１：Kosh.html
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/019/account/Kosh.html");

            // 否認可を受け、アクセス拒否画面が表示されていることを確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "forbidden Error!"));
        }
        // パターン２：Kosh/a
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/019/account/Kosh/a");

            // 否認可を受け、アクセス拒否画面が表示されていることを確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "forbidden Error!"));
        }
        // パターン３：Kosh/
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/019/account/Kosh/");

            // 否認可を受け、アクセス拒否画面が表示されていることを確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "forbidden Error!"));
        }
        // パターン４：Kosh
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/019/account/Kosh");

            // 否認可を受け、アクセス拒否画面が表示されていることを確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "forbidden Error!"));
        }
        // パターン５：Josh.html
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/019/account/Josh.html");

            // 否認可を受け、アクセス拒否画面が表示されていることを確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "forbidden Error!"));
        }
        // パターン６：Josh/a
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/019/account/Josh/a");

            // 認可を受け、リソースなしエラー画面が表示されていることを確認
            assertThat(webDriverOperations.getTitle(), is(
                    "Resource Not Found Error!"));
        }
        // パターン７：Josh/
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/019/account/Josh/");

            // 認可を受け、ユーザ情報画面が表示されていることを確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "ユーザ情報詳細"));

            // ユーザ情報が見えるページに遷移できることの確認
            assertThat(webDriverOperations.getText(id("username")), is("Josh"));
        }
        // パターン８：Josh
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/019/account/Josh");

            // 認可を受け、ユーザ情報画面が表示されていることを確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "ユーザ情報詳細"));

            // ユーザ情報が見えるページに遷移できることの確認
            assertThat(webDriverOperations.getText(id("username")), is("Josh"));
        }
        // ログアウト
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>パス変数を使用するアクセスポリシーの認可/否認可が正常に機能することを確認する。</li>
     * </ul>
     */
    @Test
    public void testATHR0601020() throws IOException {
        // メニュー画面の操作
        webDriverOperations.click(id("athr0601020"));

        // 入力条件設定
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // "Josh"でログインしたことを確認
        assertThat(webDriverOperations.getText(id("loginUserName")), is(
                "Josh"));

        // パターン１：Kosh.html
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/020/account/Kosh.html");

            // 否認可を受け、アクセス拒否画面が表示されていることを確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "forbidden Error!"));
        }
        // パターン２：Kosh/a
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/020/account/Kosh/a");

            // 認可を受け、リソースなしエラー画面が表示されていることを確認
            assertThat(webDriverOperations.getTitle(), is(
                    "Resource Not Found Error!"));
        }
        // パターン３：Kosh/
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/020/account/Kosh/");

            // 否認可を受け、アクセス拒否画面が表示されていることを確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "forbidden Error!"));
        }
        // パターン４：Kosh
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/020/account/Kosh");

            // 否認可を受け、アクセス拒否画面が表示されていることを確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "forbidden Error!"));
        }
        // パターン５：Josh.html
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/020/account/Josh.html");

            // 否認可を受け、アクセス拒否画面が表示されていることを確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "forbidden Error!"));
        }
        // パターン６：Josh/a
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/020/account/Josh/a");

            // 認可を受け、リソースなしエラー画面が表示されていることを確認
            assertThat(webDriverOperations.getTitle(), is(
                    "Resource Not Found Error!"));
        }
        // パターン７：Josh/
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/020/account/Josh/");

            // 認可を受け、ユーザ情報画面が表示されていることを確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "ユーザ情報詳細"));
            // ユーザ情報が見えるページに遷移できることの確認
            assertThat(webDriverOperations.getText(id("username")), is("Josh"));
        }
        // パターン８：Josh
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/020/account/Josh");

            // 認可を受け、ユーザ情報画面が表示されていることを確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "ユーザ情報詳細"));

            // ユーザ情報が見えるページに遷移できることの確認
            assertThat(webDriverOperations.getText(id("username")), is("Josh"));
        }
        // ログアウト
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>パス変数を使用したアクセスポリシの設定にて、拡張子を含むアクセスポリシの設定を拡張子を含まないアクセスポリシ設定の前におくことで正常に機能することを確認する。</li>
     * </ul>
     */
    @Test
    public void testATHR0601021() throws IOException {
        // メニュー画面の操作
        webDriverOperations.click(id("athr0601021"));

        // 入力条件設定
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // "Josh"でログインしたことを確認
        assertThat(webDriverOperations.getText(id("loginUserName")), is(
                "Josh"));

        // パターン１：Kosh.html
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/021/account/Kosh.html");

            // 否認可を受け、アクセス拒否画面が表示されていることを確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "forbidden Error!"));
        }
        // パターン２：Kosh/a
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/021/account/Kosh/a");

            // 否認可を受け、アクセス拒否画面が表示されていることを確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "forbidden Error!"));
        }
        // パターン３：Kosh/
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/021/account/Kosh/");

            // 否認可を受け、アクセス拒否画面が表示されていることを確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "forbidden Error!"));
        }
        // パターン４：Kosh
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/021/account/Kosh");

            // 否認可を受け、アクセス拒否画面が表示されていることを確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "forbidden Error!"));
        }
        // パターン５：Josh.html
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/021/account/Josh.html");

            // 認可を受け、ユーザ情報画面が表示されていることを確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "ユーザ情報詳細"));

            // ユーザ情報が見えるページに遷移できることの確認
            assertThat(webDriverOperations.getText(id("username")), is("Josh"));
        }
        // パターン６：Josh/a
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/021/account/Josh/a");

            // 認可を受け、リソースなしエラー画面が表示されていることを確認
            assertThat(webDriverOperations.getTitle(), is(
                    "Resource Not Found Error!"));
        }
        // パターン７：Josh/
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/021/account/Josh/");

            // 認可を受け、ユーザ情報画面が表示されていることを確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "ユーザ情報詳細"));

            // ユーザ情報が見えるページに遷移できることの確認
            assertThat(webDriverOperations.getText(id("username")), is("Josh"));
        }
        // パターン８：Josh
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/021/account/Josh");

            // 認可を受け、ユーザ情報画面が表示されていることを確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "ユーザ情報詳細"));

            // ユーザ情報が見えるページに遷移できることの確認
            assertThat(webDriverOperations.getText(id("username")), is("Josh"));
        }
        // ログアウト
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>パス変数を使用したアクセスポリシの設定にて、拡張子を含むアクセスポリシの設定を拡張子を含まないアクセスポリシ設定の前におくことで正常に機能することを確認する。</li>
     * </ul>
     */
    @Test
    public void testATHR0601022() throws IOException {
        // メニュー画面の操作
        webDriverOperations.click(id("athr0601022"));

        // 入力条件設定
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // "Josh"でログインしたことを確認
        assertThat(webDriverOperations.getText(id("loginUserName")), is(
                "Josh"));

        // パターン１：Kosh.html
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/022/account/Kosh.html");

            // 否認可を受け、アクセス拒否画面が表示されていることを確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "forbidden Error!"));
        }
        // パターン２：Kosh/a
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/022/account/Kosh/a");

            // 認可を受け、リソースなしエラー画面が表示されていることを確認
            assertThat(webDriverOperations.getTitle(), is(
                    "Resource Not Found Error!"));
        }
        // パターン３：Kosh/
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/022/account/Kosh/");

            // 否認可を受け、アクセス拒否画面が表示されていることを確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "forbidden Error!"));
        }
        // パターン４：Kosh
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/022/account/Kosh");

            // 否認可を受け、アクセス拒否画面が表示されていることを確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "forbidden Error!"));
        }
        // パターン５：Josh.html
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/022/account/Josh.html");

            // 認可を受け、ユーザ情報画面が表示されていることを確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "ユーザ情報詳細"));

            // ユーザ情報が見えるページに遷移できることの確認
            assertThat(webDriverOperations.getText(id("username")), is("Josh"));
        }
        // パターン６：Josh/a
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/022/account/Josh/a");

            // 認可を受け、リソースなしエラー画面が表示されていることを確認
            assertThat(webDriverOperations.getTitle(), is(
                    "Resource Not Found Error!"));
        }
        // パターン７：Josh/
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/022/account/Josh/");

            // 認可を受け、ユーザ情報画面が表示されていることを確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "ユーザ情報詳細"));

            // ユーザ情報が見えるページに遷移できることの確認
            assertThat(webDriverOperations.getText(id("username")), is("Josh"));
        }
        // パターン８：Josh
        {
            webDriverOperations.displayPage(getPackageRootUrl()
                    + "0601/022/account/Josh");

            // 認可を受け、ユーザ情報画面が表示されていることを確認
            assertThat(webDriverOperations.getText(id("screenTitle")), is(
                    "ユーザ情報詳細"));

            // ユーザ情報が見えるページに遷移できることの確認
            assertThat(webDriverOperations.getText(id("username")), is("Josh"));
        }
        // ログアウト
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>アクセスポリシーを適用するWebリソースの指定が正常に機能することを確認する。(PostAuthorize)</li>
     * </ul>
     */
    @Test
    public void testATHR0602003() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("athr0602003"));

        // 入力条件設定
        // KenはROLE_STAFF
        webDriverOperations.overrideText(id("username"), "Ken");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 入力条件設定
        webDriverOperations.overrideText(id("device"), "eth0");

        // 参照処理実行押下
        webDriverOperations.click(id("select"));

        // 'ROLE_STAFF'はOwnerが時IDのDeviceにしかアクセスできないことの確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "methodHierarchyAccessDeniedPage"));

        // 機能毎のトップページを表示
        webDriverOperations.displayPage(getPackageRootUrl());

        // メニュー画面の操作
        webDriverOperations.click(id("athr0602003"));

        // ログアウト押下
        webDriverOperations.click(id("logout"));

        // メニュー画面の操作
        webDriverOperations.click(id("athr0602003"));

        // 入力条件設定
        // KenはROLE_STAFF
        webDriverOperations.overrideText(id("username"), "Ken");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 入力条件設定
        webDriverOperations.overrideText(id("device"), "eth1");

        // 参照処理実行押下
        webDriverOperations.click(id("select"));

        assertThat(webDriverOperations.getText(id("device")), is("eth1"));
        assertThat(webDriverOperations.getText(id("owner")), is("Ken"));

        // ログアウト押下
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>リクエスト方式により、認可エラー時の処理が使い分けれられることを確認する。</li>
     * </ul>
     * @throws InterruptedException
     */
    @Test
    public void testATHR0701001() throws IOException, InterruptedException {

        // メニュー画面の操作
        webDriverOperations.click(id("athr0701001"));

        // 入力条件設定
        // KenはROLE_STAFF
        webDriverOperations.overrideText(id("username"), "Ken");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // ajax送信ボタン押下
        webDriverOperations.click(id("ajaxBtn"));

        // 要素が見つかるまでアサーションを待つ
        webDriverOperations.waitForDisplayed(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(
                        "//div[2]/div/div/ul/li[1]")));

        // 戻り値の確認
        assertThat(webDriverOperations.getText(id("returnStatus")), is(
                "ステータスコード:403"));

        // 要素が見つかるまでアサーションを待つ
        webDriverOperations.waitForDisplayed(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(
                        "//div[2]/div/div/ul/li[2]")));

        // 戻り値の確認
        assertThat(webDriverOperations.getText(id("contentType")), isOneOf(
                "application/json", "application/json;charset=UTF-8",
                "application/json; charset=UTF-8"));

        // アクセス拒否リンク押下
        webDriverOperations.click(id("denyLink"));

        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "AccessDeniedPagePge"));

        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>個別指定したAuthenticationEntryPointが正常に機能することを確認する（ リソース別に異なるAuthenticationEntryPointを使用する）。</li>
     * </ul>
     * @throws URISyntaxException
     */
    @Test
    public void testATHR0802001() throws IOException, URISyntaxException {

        // メニュー画面の操作
        webDriverOperations.click(id("athr0701001"));

        // 入力条件設定
        // KenはROLE_STAFF
        webDriverOperations.overrideText(id("username"), "Ken");
        webDriverOperations.overrideText(id("password"), "spring5431");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 通常の認証エラーの確認
        assertThat(webDriverOperations.getText(id("loginError")), is(
                "Bad credentials"));

        RequestEntity<Void> requestEntity = RequestEntity.get(
                new URI(applicationContextUrl + "/athr/0802/001/api")).build();

        try {
            // RESTでAPIをたたく
            restTemplate.exchange(requestEntity, byte[].class);
        } catch (HttpClientErrorException ex) {
            // 認証エラーとなることを確認
            assertThat(HttpStatus.UNAUTHORIZED, is(ex.getStatusCode()));
        }

        dbLogAssertOperations.waitForAssertion(1000);
        // ログが出力されていることを確認
        dbLogAssertOperations.assertContainsByRegexMessage(
                ".*AjaxAuthenticationEntryPoint",
                "Execute AjaxAuthenticationEntryPoint. RequestetURI is /"
                        + contextName + "/athr/0802/001/api");

        dbLogAssertOperations.assertNotContainsByRegexMessage(
                ".*AjaxAuthenticationEntryPoint",
                "Execute AjaxAuthenticationEntryPoint. RequestetURI is /"
                        + contextName + "/athr/0802/001/aftreLogin");

    }

    /**
     * <ul>
     * <li>権限階層でログイン後、閲覧可能なページにアクセス可能なことをを確認する。</li>
     * <p>
     * ROLE_STAFFの権限が必要なページを閲覧する。<br>
     * ROLE_ADMIN > ROLE_STAFF > ROLE_USER<br>
     * 持っている権限の右側の権限が必要なページは閲覧可能<br>
     * </p>
     * </ul>
     */
    @Test
    public void testATHR0901001() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("athr0901001"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

        // メニュー画面の操作
        webDriverOperations.click(id("athr0901001"));

        // 入力条件設定
        // JackはROLE_ADMIN
        webDriverOperations.overrideText(id("username"), "Jack");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 'ROLE_ADMIN'では指定タグ内の文言が表示されることの確認
        assertThat(webDriverOperations.getText(id("roleHierarchy")), is(
                "This feature is for ROLE_ADMIN or ROLE_STAFF"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

        // メニュー画面の操作
        webDriverOperations.click(id("athr0901001"));

        // 入力条件設定
        // KenはROLE_STAFF
        webDriverOperations.overrideText(id("username"), "Ken");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 'ROLE_STAFF'では指定タグ内の文言が表示されることの確認
        assertThat(webDriverOperations.getText(id("roleHierarchy")), is(
                "This feature is for ROLE_ADMIN or ROLE_STAFF"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

        // メニュー画面の操作
        webDriverOperations.click(id("athr0901001"));

        // 入力条件設定
        // JoshはROLE_USER
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 'ROLE_USER'ではアクセスできないことの確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "hierarchyAccessDeniedPage"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>権限階層でログイン後、閲覧可能なページにアクセス可能なことをを確認する。</li>
     * <p>
     * ROLE_STAFFの権限が必要なページを閲覧する。<br>
     * ROLE_ADMIN > ROLE_STAFF > ROLE_USER<br>
     * 持っている権限の右側の権限が必要なページは閲覧可能<br>
     * </p>
     * </ul>
     */
    @Test
    public void testATHR0901002() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("athr0901002"));

        // 入力条件設定
        // JackはROLE_ADMIN
        webDriverOperations.overrideText(id("username"), "Jack");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 'ROLE_ADMIN'では指定タグ内の文言が表示されることの確認
        assertThat(webDriverOperations.getText(id("roleHierarchy")), is(
                "This feature is for ROLE_ADMIN or ROLE_STAFF"));

        // ログアウトボタン押下This feature is for ROLE_ADMIN or ROLE_STAFF
        webDriverOperations.click(id("logout"));

        // メニュー画面の操作
        webDriverOperations.click(id("athr0901002"));

        // 入力条件設定
        // KenはROLE_STAFF
        webDriverOperations.overrideText(id("username"), "Ken");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 'ROLE_STAFF'では指定タグ内の文言が表示されることの確認
        assertThat(webDriverOperations.getText(id("roleHierarchy")), is(
                "This feature is for ROLE_ADMIN or ROLE_STAFF"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

        // メニュー画面の操作
        webDriverOperations.click(id("athr0901002"));

        // 入力条件設定
        // JoshはROLE_USER
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 'ROLE_USER'ではアクセスできないことの確認
        assertThat(webDriverOperations.getText(id("roleHierarchy")), is(""));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

    }

    /**
     * <ul>
     * <li>権限階層でログイン後、閲覧可能なページにアクセス可能なことをを確認する。</li>
     * <p>
     * ROLE_STAFFの権限が必要なページを閲覧する。<br>
     * ROLE_ADMIN > ROLE_STAFF > ROLE_USER<br>
     * 持っている権限の右側の権限が必要なページは閲覧可能<br>
     * </p>
     * </ul>
     */
    @Test
    public void testATHR0901003() throws IOException {

        // メニュー画面の操作
        webDriverOperations.click(id("athr0901003"));

        // 入力条件設定
        // JackはROLE_ADMIN
        webDriverOperations.overrideText(id("username"), "Jack");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 入力条件設定
        webDriverOperations.overrideText(id("device"), "eth0");

        // 参照処理実行押下
        webDriverOperations.click(id("select"));

        // 参照結果確認
        assertThat(webDriverOperations.getText(id("device")), is("eth0"));
        assertThat(webDriverOperations.getText(id("broadcast")), is(
                "192.168.10.255"));
        assertThat(webDriverOperations.getText(id("ipaddr")), is(
                "192.168.10.10"));
        assertThat(webDriverOperations.getText(id("netmask")), is(
                "255.255.255.0"));
        assertThat(webDriverOperations.getText(id("network")), is(
                "192.168.10.0"));
        assertThat(webDriverOperations.getText(id("onboot")), is("yes"));
        assertThat(webDriverOperations.getText(id("deviceType")), is(
                "Ethernet"));
        assertThat(webDriverOperations.getText(id("gateway")), is(
                "192.168.10.1"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

        // メニュー画面の操作
        webDriverOperations.click(id("athr0901003"));

        // 入力条件設定
        // KenはROLE_STAFF
        webDriverOperations.overrideText(id("username"), "Ken");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 入力条件設定
        webDriverOperations.overrideText(id("device"), "eth1");

        // 参照処理実行押下
        webDriverOperations.click(id("select"));

        // 参照結果確認
        assertThat(webDriverOperations.getText(id("device")), is("eth1"));
        assertThat(webDriverOperations.getText(id("broadcast")), is(
                "192.168.11.255"));
        assertThat(webDriverOperations.getText(id("ipaddr")), is(
                "192.168.11.10"));
        assertThat(webDriverOperations.getText(id("netmask")), is(
                "255.255.255.0"));
        assertThat(webDriverOperations.getText(id("network")), is(
                "192.168.11.0"));
        assertThat(webDriverOperations.getText(id("onboot")), is("yes"));
        assertThat(webDriverOperations.getText(id("deviceType")), is(
                "Ethernet"));
        assertThat(webDriverOperations.getText(id("gateway")), is(
                "192.168.11.1"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

        // メニュー画面の操作
        webDriverOperations.click(id("athr0901003"));

        // 入力条件設定
        // JoshはROLE_USER
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        webDriverOperations.overrideText(id("device"), "eth1");

        // 参照処理実行押下
        webDriverOperations.click(id("select"));

        // 'ROLE_USER'ではアクセスできないことの確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "methodHierarchyAccessDeniedPage"));

        // メニュー画面の操作
        webDriverOperations.displayPage(getPackageRootUrl());
        webDriverOperations.click(id("athr0901003"));
        // ログアウト
        webDriverOperations.click(id("logout"));

        webDriverOperations.click(id("athr0901003"));
        // 入力条件設定
        // JackはROLE_ADMIN
        webDriverOperations.overrideText(id("username"), "Jack");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 登録処理実行押下
        webDriverOperations.click(id("insert"));

        // 入力条件設定
        webDriverOperations.overrideText(id("device"), "eth6");
        webDriverOperations.overrideText(id("broadcast"), "192.168.16.255");
        webDriverOperations.overrideText(id("ipaddr"), "192.168.16.10");
        webDriverOperations.overrideText(id("netmask"), "255.255.255.0");
        webDriverOperations.overrideText(id("network"), "192.168.16.0");
        webDriverOperations.overrideText(id("onboot"), "yes");
        webDriverOperations.overrideText(id("deviceType"), "Ethernet");
        webDriverOperations.overrideText(id("gateway"), "192.168.16.1");
        webDriverOperations.overrideText(id("owner"), "Jack");

        // 登録処理実行押下
        webDriverOperations.click(id("register"));

        // 登録結果確認
        assertThat(webDriverOperations.getText(id("device")), is("eth6"));
        assertThat(webDriverOperations.getText(id("broadcast")), is(
                "192.168.16.255"));
        assertThat(webDriverOperations.getText(id("ipaddr")), is(
                "192.168.16.10"));
        assertThat(webDriverOperations.getText(id("netmask")), is(
                "255.255.255.0"));
        assertThat(webDriverOperations.getText(id("network")), is(
                "192.168.16.0"));
        assertThat(webDriverOperations.getText(id("onboot")), is("yes"));
        assertThat(webDriverOperations.getText(id("deviceType")), is(
                "Ethernet"));
        assertThat(webDriverOperations.getText(id("gateway")), is(
                "192.168.16.1"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

        // メニュー画面の操作
        webDriverOperations.click(id("athr0901003"));

        // 入力条件設定
        // KenはROLE_STAFF
        webDriverOperations.overrideText(id("username"), "Ken");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 参照処理実行押下
        webDriverOperations.click(id("insert"));

        // 入力条件設定
        webDriverOperations.overrideText(id("device"), "eth7");
        webDriverOperations.overrideText(id("broadcast"), "192.168.17.255");
        webDriverOperations.overrideText(id("ipaddr"), "192.168.17.10");
        webDriverOperations.overrideText(id("netmask"), "255.255.255.0");
        webDriverOperations.overrideText(id("network"), "192.168.17.0");
        webDriverOperations.overrideText(id("onboot"), "yes");
        webDriverOperations.overrideText(id("deviceType"), "Ethernet");
        webDriverOperations.overrideText(id("gateway"), "192.168.17.1");
        webDriverOperations.overrideText(id("owner"), "Ken");

        // 登録処理実行押下
        webDriverOperations.click(id("register"));

        // 登録結果確認
        assertThat(webDriverOperations.getText(id("device")), is("eth7"));
        assertThat(webDriverOperations.getText(id("broadcast")), is(
                "192.168.17.255"));
        assertThat(webDriverOperations.getText(id("ipaddr")), is(
                "192.168.17.10"));
        assertThat(webDriverOperations.getText(id("netmask")), is(
                "255.255.255.0"));
        assertThat(webDriverOperations.getText(id("network")), is(
                "192.168.17.0"));
        assertThat(webDriverOperations.getText(id("onboot")), is("yes"));
        assertThat(webDriverOperations.getText(id("deviceType")), is(
                "Ethernet"));
        assertThat(webDriverOperations.getText(id("gateway")), is(
                "192.168.17.1"));

        // ログアウトボタン押下
        webDriverOperations.click(id("logout"));

        // メニュー画面の操作
        webDriverOperations.click(id("athr0901003"));

        // 入力条件設定
        // JoshはROLE_USER
        webDriverOperations.overrideText(id("username"), "Josh");
        webDriverOperations.overrideText(id("password"), "spring1234");

        // ログインボタン押下
        webDriverOperations.click(id("login"));

        // 参照処理実行押下
        webDriverOperations.click(id("insert"));

        // 入力条件設定
        webDriverOperations.overrideText(id("device"), "eth8");
        webDriverOperations.overrideText(id("broadcast"), "192.168.18.255");
        webDriverOperations.overrideText(id("ipaddr"), "192.168.18.10");
        webDriverOperations.overrideText(id("netmask"), "255.255.255.0");
        webDriverOperations.overrideText(id("network"), "192.168.18.0");
        webDriverOperations.overrideText(id("onboot"), "yes");
        webDriverOperations.overrideText(id("deviceType"), "Ethernet");
        webDriverOperations.overrideText(id("gateway"), "192.168.18.1");
        webDriverOperations.overrideText(id("owner"), "Josh");

        // 登録処理実行押下
        webDriverOperations.click(id("register"));

        // 'ROLE_USER'ではアクセスできないことの確認
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "methodHierarchyAccessDeniedPage"));

        // 先頭へ戻る
        webDriverOperations.click(id("logout"));

    }
}
