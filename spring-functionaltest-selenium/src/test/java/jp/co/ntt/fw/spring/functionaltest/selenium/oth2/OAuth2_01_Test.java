/*
 * Copyright(c) 2025 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.selenium.oth2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

public class OAuth2_01_Test extends FunctionTestSupport {

    @After
    public void teraDown() {
        // 認可不要の処理を呼び出し、作成されているタスクは削除しておく
        clean();

        // 試験の度に認可サーバーの認証が実行されるようにWebDriverは都度落とす
        super.quitCurrentWebDriver();
    }

    @Override
    protected String getPackageRootUrl() {
        return super.applicationContextUrl + "-" + "oauth2-client";
    }

    /**
     * タスクの全削除
     */
    private void clean() {
        webDriverOperations.displayPage(getPackageRootUrl());
        webDriverOperations.click(By.id("todoappNoAuth"));

        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Todo List - 認可処理なし（試験用）"));

        webDriverOperations.click(By.id("deleteAll"));
        webDriverOperations.displayPage(getPackageRootUrl());
    }

    /**
     * ダミータスク作成
     * 
     * @param count 作成数（1～5）
     */
    private void createDummyTask(int count) {
        assert (0 < count && count < 6);

        webDriverOperations.displayPage(getPackageRootUrl());
        webDriverOperations.click(By.id("todoappNoAuth"));

        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Todo List - 認可処理なし（試験用）"));

        for (int i = 0; i < count; i++) {
            webDriverOperations.appendText(id("todoTitle"), "dummy_" + (i + 1));
            webDriverOperations.click(id("create"));
        }

        webDriverOperations.displayPage(getPackageRootUrl());
    }

    /**
     * 認可サーバの認証
     * 
     * @param username ユーザ名
     * @param password パスワード
     */
    private void login(String username, String password) {
        // keycloakの認証画面が出力されるまで待機
        webDriverOperations.waitForDisplayed(
                ExpectedConditions.textToBePresentInElementLocated(By.id("kc-page-title"), "Sign in to your account"));

        webDriverOperations.appendText(id("username"), username);
        webDriverOperations.appendText(id("password"), password);
        webDriverOperations.click(id("kc-login"));
    }

    /**
     * <ul>
     * <li>大項目ID : 01 : 認可コードグラント</li>
     * <li>中項目ID : 01 : 全スコープに権限を持つクライアントに対する操作</li>
     * <li>小項目ID : 001 : READスコープ(intercepturl)</li>
     * <li>TODO一覧画面が表示できること。</li>
     * </ul>
     */
    @Test
    public void testOTH20101001() {
        // TODOアプリ - 認可処理要 - 認可コードグラント
        webDriverOperations.click(By.id("authorizationCode"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "認可コードグラント"));

        // registration_allとintercepturlにチェックを入れてlistを押下
        webDriverOperations.click(By.cssSelector("input[name='registrationId'][value='registration_all']"));
        webDriverOperations.click(By.cssSelector("input[name='resourceProtect'][value='intercepturl']"));
        webDriverOperations.click(By.name("list"));

        // login
        login("demo", "demo");

        // TODO一覧画面が表示されていること
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Todo List"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*OAuth2TokenServiceImpl",
                ".*Scopes = \\[READ, DELETE, CREATE, UPDATE\\].*");
    }

    /**
     * <ul>
     * <li>大項目ID : 01 : 認可コードグラント</li>
     * <li>中項目ID : 01 : 全スコープに権限を持つクライアントに対する操作</li>
     * <li>小項目ID : 002 : CREATEスコープ(intercepturl)</li>
     * <li>タスクが作成できること。</li>
     * </ul>
     */
    @Test
    public void testOTH20101002() {
        // TODOアプリ - 認可処理要 - 認可コードグラント
        webDriverOperations.click(By.id("authorizationCode"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "認可コードグラント"));

        // registration_allとintercepturlにチェックを入れてlistを押下
        webDriverOperations.click(By.cssSelector("input[name='registrationId'][value='registration_all']"));
        webDriverOperations.click(By.cssSelector("input[name='resourceProtect'][value='intercepturl']"));
        webDriverOperations.click(By.name("list"));

        // login
        login("demo", "demo");

        // TODO一覧画面が表示されていること
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Todo List"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*OAuth2TokenServiceImpl",
                ".*Scopes = \\[READ, DELETE, CREATE, UPDATE\\].*");

        // TODOタスク作成
        webDriverOperations.appendText(id("todoTitle"), "sample");
        webDriverOperations.click(id("create"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*TraceLoggingInterceptor",
                ".*text=Created successfully!.*");

        // 一覧に作成したタスクが表示されていること
        String text = webDriverOperations.getText(By.xpath("//div[3]/ul/li"));
        assertTrue(text, text.startsWith("sample"));
    }

    /**
     * <ul>
     * <li>大項目ID : 01 : 認可コードグラント</li>
     * <li>中項目ID : 01 : 全スコープに権限を持つクライアントに対する操作</li>
     * <li>小項目ID : 003 : UPDATEスコープ(intercepturl)</li>
     * <li>タスクが更新できること。</li>
     * </ul>
     */
    @Test
    public void testOTH20101003() {
        // 事前データ作成
        createDummyTask(1);

        // TODOアプリ - 認可処理要 - 認可コードグラント
        webDriverOperations.click(By.id("authorizationCode"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "認可コードグラント"));

        // registration_allとintercepturlにチェックを入れてlistを押下
        webDriverOperations.click(By.cssSelector("input[name='registrationId'][value='registration_all']"));
        webDriverOperations.click(By.cssSelector("input[name='resourceProtect'][value='intercepturl']"));
        webDriverOperations.click(By.name("list"));

        // login
        login("demo", "demo");

        // TODO一覧画面が表示されていること
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Todo List"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*OAuth2TokenServiceImpl",
                ".*Scopes = \\[READ, DELETE, CREATE, UPDATE\\].*");

        // TODOタスク更新
        webDriverOperations.click(By.xpath("//div[3]/ul/li/form[1]/button"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*TraceLoggingInterceptor",
                ".*text=Finished successfully!.*");

        // Finishボタンが消えていること
        String text = webDriverOperations.getText(By.xpath("//div[3]/ul/li"));
        assertTrue(text, text.matches("dummy_1(?!.*\\bFinish\\b).*Delete"));
    }

    /**
     * <ul>
     * <li>大項目ID : 01 : 認可コードグラント</li>
     * <li>中項目ID : 01 : 全スコープに権限を持つクライアントに対する操作</li>
     * <li>小項目ID : 004 : DELETEスコープ(intercepturl)</li>
     * <li>タスクが削除できること。</li>
     * </ul>
     */
    @Test
    public void testOTH20101004() {
        // 事前データ作成
        createDummyTask(1);

        // TODOアプリ - 認可処理要 - 認可コードグラント
        webDriverOperations.click(By.id("authorizationCode"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "認可コードグラント"));

        // registration_allとintercepturlにチェックを入れてlistを押下
        webDriverOperations.click(By.cssSelector("input[name='registrationId'][value='registration_all']"));
        webDriverOperations.click(By.cssSelector("input[name='resourceProtect'][value='intercepturl']"));
        webDriverOperations.click(By.name("list"));

        // login
        login("demo", "demo");

        // TODO一覧画面が表示されていること
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Todo List"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*OAuth2TokenServiceImpl",
                ".*Scopes = \\[READ, DELETE, CREATE, UPDATE\\].*");

        // TODOタスク削除
        webDriverOperations.click(By.xpath("//div[3]/ul/li/form[2]/button"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*TraceLoggingInterceptor",
                ".*text=Deleted successfully!.*");

        // 一覧にタスクが表示されていないこと
        assertFalse("要素が存在する。", webDriverOperations.exists(By.xpath("//div[3]/ul/li")));
    }

    /**
     * <ul>
     * <li>大項目ID : 01 : 認可コードグラント</li>
     * <li>中項目ID : 01 : 全スコープに権限を持つクライアントに対する操作</li>
     * <li>小項目ID : 005 : READスコープ(MethodAnnotation)</li>
     * <li>TODO一覧画面が表示できること。</li>
     * </ul>
     */
    @Test
    public void testOTH20101005() {
        // TODOアプリ - 認可処理要 - 認可コードグラント
        webDriverOperations.click(By.id("authorizationCode"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "認可コードグラント"));

        // registration_allとannotationにチェックを入れてlistを押下
        webDriverOperations.click(By.cssSelector("input[name='registrationId'][value='registration_all']"));
        webDriverOperations.click(By.cssSelector("input[name='resourceProtect'][value='annotation']"));
        webDriverOperations.click(By.name("list"));

        // login
        login("demo", "demo");

        // TODO一覧画面が表示されていること
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Todo List"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*OAuth2TokenServiceImpl",
                ".*Scopes = \\[READ, DELETE, CREATE, UPDATE\\].*");
    }

    /**
     * <ul>
     * <li>大項目ID : 01 : 認可コードグラント</li>
     * <li>中項目ID : 01 : 全スコープに権限を持つクライアントに対する操作</li>
     * <li>小項目ID : 006 : CREATEスコープ(MethodAnnotation)</li>
     * <li>タスクが作成できること。</li>
     * </ul>
     */
    @Test
    public void testOTH20101006() {
        // TODOアプリ - 認可処理要 - 認可コードグラント
        webDriverOperations.click(By.id("authorizationCode"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "認可コードグラント"));

        // registration_allとannotationにチェックを入れてlistを押下
        webDriverOperations.click(By.cssSelector("input[name='registrationId'][value='registration_all']"));
        webDriverOperations.click(By.cssSelector("input[name='resourceProtect'][value='annotation']"));
        webDriverOperations.click(By.name("list"));

        // login
        login("demo", "demo");

        // TODO一覧画面が表示されていること
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Todo List"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*OAuth2TokenServiceImpl",
                ".*Scopes = \\[READ, DELETE, CREATE, UPDATE\\].*");

        // TODOタスク作成
        webDriverOperations.appendText(id("todoTitle"), "sample");
        webDriverOperations.click(id("create"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*TraceLoggingInterceptor",
                ".*text=Created successfully!.*");

        // 一覧に作成したタスクが表示されていること
        String text = webDriverOperations.getText(By.xpath("//div[3]/ul/li"));
        assertTrue(text, text.startsWith("sample"));
    }

    /**
     * <ul>
     * <li>大項目ID : 01 : 認可コードグラント</li>
     * <li>中項目ID : 01 : 全スコープに権限を持つクライアントに対する操作</li>
     * <li>小項目ID : 007 : UPDATEスコープ(MethodAnnotation)</li>
     * <li>タスクが更新できること。</li>
     * </ul>
     */
    @Test
    public void testOTH20101007() {
        // 事前データ作成
        createDummyTask(1);

        // TODOアプリ - 認可処理要 - 認可コードグラント
        webDriverOperations.click(By.id("authorizationCode"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "認可コードグラント"));

        // registration_allとannotationにチェックを入れてlistを押下
        webDriverOperations.click(By.cssSelector("input[name='registrationId'][value='registration_all']"));
        webDriverOperations.click(By.cssSelector("input[name='resourceProtect'][value='annotation']"));
        webDriverOperations.click(By.name("list"));

        // login
        login("demo", "demo");

        // TODO一覧画面が表示されていること
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Todo List"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*OAuth2TokenServiceImpl",
                ".*Scopes = \\[READ, DELETE, CREATE, UPDATE\\].*");

        // TODOタスク更新
        webDriverOperations.click(By.xpath("//div[3]/ul/li/form[1]/button"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*TraceLoggingInterceptor",
                ".*text=Finished successfully!.*");

        // Finishボタンが消えていること
        String text = webDriverOperations.getText(By.xpath("//div[3]/ul/li"));
        assertTrue(text, text.matches("dummy_1(?!.*\\bFinish\\b).*Delete"));
    }

    /**
     * <ul>
     * <li>大項目ID : 01 : 認可コードグラント</li>
     * <li>中項目ID : 01 : 全スコープに権限を持つクライアントに対する操作</li>
     * <li>小項目ID : 008 : DELETEスコープ(MethodAnnotation)</li>
     * <li>タスクが削除できること。</li>
     * </ul>
     */
    @Test
    public void testOTH20101008() {
        // 事前データ作成
        createDummyTask(1);

        // TODOアプリ - 認可処理要 - 認可コードグラント
        webDriverOperations.click(By.id("authorizationCode"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "認可コードグラント"));

        // registration_allとannotationにチェックを入れてlistを押下
        webDriverOperations.click(By.cssSelector("input[name='registrationId'][value='registration_all']"));
        webDriverOperations.click(By.cssSelector("input[name='resourceProtect'][value='annotation']"));
        webDriverOperations.click(By.name("list"));

        // login
        login("demo", "demo");

        // TODO一覧画面が表示されていること
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Todo List"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*OAuth2TokenServiceImpl",
                ".*Scopes = \\[READ, DELETE, CREATE, UPDATE\\].*");

        // TODOタスク削除
        webDriverOperations.click(By.xpath("//div[3]/ul/li/form[2]/button"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*TraceLoggingInterceptor",
                ".*text=Deleted successfully!.*");

        // 一覧にタスクが表示されていないこと
        assertFalse("要素が存在する。", webDriverOperations.exists(By.xpath("//div[3]/ul/li")));
    }

    /**
     * <ul>
     * <li>大項目ID : 01 : 認可コードグラント</li>
     * <li>中項目ID : 02 : READスコープのみ権限を持つクライアントに対する操作</li>
     * <li>小項目ID : 001 : READスコープ(intercepturl)</li>
     * <li>TODO一覧画面が表示できること。</li>
     * </ul>
     */
    @Test
    public void testOTH20102001() {
        // TODOアプリ - 認可処理要 - 認可コードグラント
        webDriverOperations.click(By.id("authorizationCode"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "認可コードグラント"));

        // registration_readとintercepturlにチェックを入れてlistを押下
        webDriverOperations.click(By.cssSelector("input[name='registrationId'][value='registration_read']"));
        webDriverOperations.click(By.cssSelector("input[name='resourceProtect'][value='intercepturl']"));
        webDriverOperations.click(By.name("list"));

        // login
        login("demo", "demo");

        // TODO一覧画面が表示されていること
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Todo List"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*OAuth2TokenServiceImpl",
                ".*Scopes = \\[READ\\].*");
    }

    /**
     * <ul>
     * <li>大項目ID : 01 : 認可コードグラント</li>
     * <li>中項目ID : 02 : READスコープのみ権限を持つクライアントに対する操作</li>
     * <li>小項目ID : 002 : CREATEスコープ(intercepturl)</li>
     * <li>タスクが作成できないこと。</li>
     * </ul>
     */
    @Test
    public void testOTH20102002() {
        // TODOアプリ - 認可処理要 - 認可コードグラント
        webDriverOperations.click(By.id("authorizationCode"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "認可コードグラント"));

        // registration_readとintercepturlにチェックを入れてlistを押下
        webDriverOperations.click(By.cssSelector("input[name='registrationId'][value='registration_read']"));
        webDriverOperations.click(By.cssSelector("input[name='resourceProtect'][value='intercepturl']"));
        webDriverOperations.click(By.name("list"));

        // login
        login("demo", "demo");

        // TODO一覧画面が表示されていること
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Todo List"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*OAuth2TokenServiceImpl",
                ".*Scopes = \\[READ\\].*");

        // TODOタスク作成
        webDriverOperations.appendText(id("todoTitle"), "sample");
        webDriverOperations.click(id("create"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*ExceptionLogger",
                ".*e.sf.fw.2001.*");

        // 一覧にタスクが表示されていないこと
        assertFalse("要素が存在する。", webDriverOperations.exists(By.xpath("//div[3]/ul/li")));
    }

    /**
     * <ul>
     * <li>大項目ID : 01 : 認可コードグラント</li>
     * <li>中項目ID : 02 : READスコープのみ権限を持つクライアントに対する操作</li>
     * <li>小項目ID : 003 : UPDATEスコープ(intercepturl)</li>
     * <li>タスクが更新できないこと。</li>
     * </ul>
     */
    @Test
    public void testOTH20102003() {
        // 事前データ作成
        createDummyTask(1);

        // TODOアプリ - 認可処理要 - 認可コードグラント
        webDriverOperations.click(By.id("authorizationCode"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "認可コードグラント"));

        // registration_readとintercepturlにチェックを入れてlistを押下
        webDriverOperations.click(By.cssSelector("input[name='registrationId'][value='registration_read']"));
        webDriverOperations.click(By.cssSelector("input[name='resourceProtect'][value='intercepturl']"));
        webDriverOperations.click(By.name("list"));

        // login
        login("demo", "demo");

        // TODO一覧画面が表示されていること
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Todo List"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*OAuth2TokenServiceImpl",
                ".*Scopes = \\[READ\\].*");

        // TODOタスク更新
        webDriverOperations.click(By.xpath("//div[3]/ul/li/form[1]/button"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*ExceptionLogger",
                ".*e.sf.fw.2001.*");

        // Finishボタンが消えていないこと
        String text = webDriverOperations.getText(By.xpath("//div[3]/ul/li"));
        assertFalse(text, text.matches("dummy_1(?!.*\\bFinish\\b).*Delete"));
    }

    /**
     * <ul>
     * <li>大項目ID : 01 : 認可コードグラント</li>
     * <li>中項目ID : 02 : READスコープのみ権限を持つクライアントに対する操作</li>
     * <li>小項目ID : 004 : DELETEスコープ(intercepturl)</li>
     * <li>タスクが更新できないこと。</li>
     * </ul>
     */
    @Test
    public void testOTH20102004() {
        // 事前データ作成
        createDummyTask(1);

        // TODOアプリ - 認可処理要 - 認可コードグラント
        webDriverOperations.click(By.id("authorizationCode"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "認可コードグラント"));

        // registration_readとintercepturlにチェックを入れてlistを押下
        webDriverOperations.click(By.cssSelector("input[name='registrationId'][value='registration_read']"));
        webDriverOperations.click(By.cssSelector("input[name='resourceProtect'][value='intercepturl']"));
        webDriverOperations.click(By.name("list"));

        // login
        login("demo", "demo");

        // TODO一覧画面が表示されていること
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Todo List"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*OAuth2TokenServiceImpl",
                ".*Scopes = \\[READ\\].*");

        // TODOタスク削除
        webDriverOperations.click(By.xpath("//div[3]/ul/li/form[2]/button"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*ExceptionLogger",
                ".*e.sf.fw.2001.*");

        // Dummy_1が消えていないこと
        String text = webDriverOperations.getText(By.xpath("//div[3]/ul/li"));
        assertTrue(text, text.startsWith("dummy_1"));
    }

    /**
     * <ul>
     * <li>大項目ID : 01 : 認可コードグラント</li>
     * <li>中項目ID : 02 : READスコープのみ権限を持つクライアントに対する操作</li>
     * <li>小項目ID : 005 : READスコープ(MethodAnnotation)</li>
     * <li>TODO一覧画面が表示できること。</li>
     * </ul>
     */
    @Test
    public void testOTH20102005() {
        // TODOアプリ - 認可処理要 - 認可コードグラント
        webDriverOperations.click(By.id("authorizationCode"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "認可コードグラント"));

        // registration_readとannotationにチェックを入れてlistを押下
        webDriverOperations.click(By.cssSelector("input[name='registrationId'][value='registration_read']"));
        webDriverOperations.click(By.cssSelector("input[name='resourceProtect'][value='annotation']"));
        webDriverOperations.click(By.name("list"));

        // login
        login("demo", "demo");

        // TODO一覧画面が表示されていること
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Todo List"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*OAuth2TokenServiceImpl",
                ".*Scopes = \\[READ\\].*");
    }

    /**
     * <ul>
     * <li>大項目ID : 01 : 認可コードグラント</li>
     * <li>中項目ID : 02 : READスコープのみ権限を持つクライアントに対する操作</li>
     * <li>小項目ID : 006 : CREATEスコープ(MethodAnnotation)</li>
     * <li>タスクが作成できないこと。</li>
     * </ul>
     */
    @Test
    public void testOTH20102006() {
        // TODOアプリ - 認可処理要 - 認可コードグラント
        webDriverOperations.click(By.id("authorizationCode"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "認可コードグラント"));

        // registration_readとannotationにチェックを入れてlistを押下
        webDriverOperations.click(By.cssSelector("input[name='registrationId'][value='registration_read']"));
        webDriverOperations.click(By.cssSelector("input[name='resourceProtect'][value='annotation']"));
        webDriverOperations.click(By.name("list"));

        // login
        login("demo", "demo");

        // TODO一覧画面が表示されていること
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Todo List"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*OAuth2TokenServiceImpl",
                ".*Scopes = \\[READ\\].*");

        // TODOタスク作成
        webDriverOperations.appendText(id("todoTitle"), "sample");
        webDriverOperations.click(id("create"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*ExceptionLogger",
                ".*e.sf.fw.2001.*");

        // 一覧にタスクが表示されていないこと
        assertFalse("要素が存在する。", webDriverOperations.exists(By.xpath("//div[3]/ul/li")));
    }

    /**
     * <ul>
     * <li>大項目ID : 01 : 認可コードグラント</li>
     * <li>中項目ID : 02 : READスコープのみ権限を持つクライアントに対する操作</li>
     * <li>小項目ID : 007 : UPDATEスコープ(MethodAnnotation)</li>
     * <li>タスクが更新できないこと。</li>
     * </ul>
     */
    @Test
    public void testOTH20102007() {
        // 事前データ作成
        createDummyTask(1);

        // TODOアプリ - 認可処理要 - 認可コードグラント
        webDriverOperations.click(By.id("authorizationCode"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "認可コードグラント"));

        // registration_readとannotationにチェックを入れてlistを押下
        webDriverOperations.click(By.cssSelector("input[name='registrationId'][value='registration_read']"));
        webDriverOperations.click(By.cssSelector("input[name='resourceProtect'][value='annotation']"));
        webDriverOperations.click(By.name("list"));

        // login
        login("demo", "demo");

        // TODO一覧画面が表示されていること
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Todo List"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*OAuth2TokenServiceImpl",
                ".*Scopes = \\[READ\\].*");

        // TODOタスク更新
        webDriverOperations.click(By.xpath("//div[3]/ul/li/form[1]/button"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*ExceptionLogger",
                ".*e.sf.fw.2001.*");

        // Finishボタンが消えていないこと
        String text = webDriverOperations.getText(By.xpath("//div[3]/ul/li"));
        assertFalse(text, text.matches("dummy_1(?!.*\\bFinish\\b).*Delete"));
    }

    /**
     * <ul>
     * <li>大項目ID : 01 : 認可コードグラント</li>
     * <li>中項目ID : 02 : READスコープのみ権限を持つクライアントに対する操作</li>
     * <li>小項目ID : 008 : DELETEスコープ(MethodAnnotation)</li>
     * <li>タスクが更新できないこと。</li>
     * </ul>
     */
    @Test
    public void testOTH20102008() {
        // 事前データ作成
        createDummyTask(1);

        // TODOアプリ - 認可処理要 - 認可コードグラント
        webDriverOperations.click(By.id("authorizationCode"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "認可コードグラント"));

        // registration_readとannotationにチェックを入れてlistを押下
        webDriverOperations.click(By.cssSelector("input[name='registrationId'][value='registration_read']"));
        webDriverOperations.click(By.cssSelector("input[name='resourceProtect'][value='annotation']"));
        webDriverOperations.click(By.name("list"));

        // login
        login("demo", "demo");

        // TODO一覧画面が表示されていること
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Todo List"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*OAuth2TokenServiceImpl",
                ".*Scopes = \\[READ\\].*");

        // TODOタスク削除
        webDriverOperations.click(By.xpath("//div[3]/ul/li/form[2]/button"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*ExceptionLogger",
                ".*e.sf.fw.2001.*");

        // Dummy_1が消えていないこと
        String text = webDriverOperations.getText(By.xpath("//div[3]/ul/li"));
        assertTrue(text, text.startsWith("dummy_1"));
    }

    /**
     * <ul>
     * <li>大項目ID : 01 : 認可コードグラント</li>
     * <li>中項目ID : 03 : 設定スコープと認可サーバ側の設定が合わないクライアントに対する操作</li>
     * <li>小項目ID : 001 : READスコープ(intercepturl) ・・・ 認証時にエラー</li>
     * <li>Resource Not Foundとなること。</li>
     * </ul>
     */
    @Test
    public void testOTH20103001() {
        // TODOアプリ - 認可処理要 - 認可コードグラント
        webDriverOperations.click(By.id("authorizationCode"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "認可コードグラント"));

        // registration_wrongとintercepturlにチェックを入れてlistを押下
        webDriverOperations.click(By.cssSelector("input[name='registrationId'][value='registration_wrong']"));
        webDriverOperations.click(By.cssSelector("input[name='resourceProtect'][value='intercepturl']"));
        webDriverOperations.click(By.name("list"));

        // 手動確認観点：このタイミングで認可サーバーのログに以下が出力される
        // KC-SERVICES0093: Invalid parameter value for: scope

        // Resource Not Found Error画面が表示されていること
        webDriverOperations.waitForDisplayed(textToBe(By.xpath("//div/h1"), "Resource Not Found Error!"));
    }

    /**
     * <ul>
     * <li>大項目ID : 01 : 認可コードグラント</li>
     * <li>中項目ID : 03 : 設定スコープと認可サーバ側の設定が合わないクライアントに対する操作</li>
     * <li>小項目ID : 002 : READスコープ(MethodAnnotation) ・・・ 認証時にエラー</li>
     * <li>Resource Not Foundとなること。</li>
     * </ul>
     */
    @Ignore("リソースサーバーまで到達しないためtestOTH20103001と内容が一緒となる。")
    @Test
    public void testOTH20103002() {
        // TODOアプリ - 認可処理要 - 認可コードグラント
        webDriverOperations.click(By.id("authorizationCode"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "認可コードグラント"));

        // registration_wrongとannotationにチェックを入れてlistを押下
        webDriverOperations.click(By.cssSelector("input[name='registrationId'][value='registration_wrong']"));
        webDriverOperations.click(By.cssSelector("input[name='resourceProtect'][value='annotation']"));
        webDriverOperations.click(By.name("list"));

        // 手動確認観点：このタイミングで認可サーバーのログに以下が出力される
        // KC-SERVICES0093: Invalid parameter value for: scope

        // Resource Not Found Error画面が表示されていること
        webDriverOperations.waitForDisplayed(textToBe(By.xpath("//div/h1"), "Resource Not Found Error!"));
    }

    /**
     * <ul>
     * <li>大項目ID : 01 : 認可コードグラント</li>
     * <li>中項目ID : 04 : 認可サーバ側は全スコープを許容するが、クライアント側でスコープを絞っている場合の操作</li>
     * <li>小項目ID : 001 : READスコープ(intercepturl) ・・・ 正常</li>
     * <li>TODOList画面が表示されること。</li>
     * </ul>
     */
    @Test
    public void testOTH20104001() {
        // TODOアプリ - 認可処理要 - 認可コードグラント
        webDriverOperations.click(By.id("authorizationCode"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "認可コードグラント"));

        // registration_partialとintercepturlにチェックを入れてlistを押下
        webDriverOperations.click(By.cssSelector("input[name='registrationId'][value='registration_partial']"));
        webDriverOperations.click(By.cssSelector("input[name='resourceProtect'][value='intercepturl']"));
        webDriverOperations.click(By.name("list"));

        // login
        login("demo", "demo");

        // TODO一覧画面が表示されていること
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Todo List"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*OAuth2TokenServiceImpl",
                ".*Scopes = \\[READ, DELETE\\].*");
    }

    /**
     * <ul>
     * <li>大項目ID : 01 : 認可コードグラント</li>
     * <li>中項目ID : 04 : 認可サーバ側は全スコープを許容するが、クライアント側でスコープを絞っている場合の操作</li>
     * <li>小項目ID : 002 : CREATEスコープ(intercepturl) ・・・ リソースサーバ側でエラー</li>
     * <li>タスクが作成できないこと。</li>
     * </ul>
     */
    @Test
    public void testOTH20104002() {
        // TODOアプリ - 認可処理要 - 認可コードグラント
        webDriverOperations.click(By.id("authorizationCode"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "認可コードグラント"));

        // registration_partialとintercepturlにチェックを入れてlistを押下
        webDriverOperations.click(By.cssSelector("input[name='registrationId'][value='registration_partial']"));
        webDriverOperations.click(By.cssSelector("input[name='resourceProtect'][value='intercepturl']"));
        webDriverOperations.click(By.name("list"));

        // login
        login("demo", "demo");

        // TODO一覧画面が表示されていること
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Todo List"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*OAuth2TokenServiceImpl",
                ".*Scopes = \\[READ, DELETE\\].*");

        // TODOタスク作成
        webDriverOperations.appendText(id("todoTitle"), "sample");
        webDriverOperations.click(id("create"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*ExceptionLogger",
                ".*e.sf.fw.2001.*");

        // 一覧にタスクが表示されていないこと
        assertFalse("要素が存在する。", webDriverOperations.exists(By.xpath("//div[3]/ul/li")));
    }

    /**
     * <ul>
     * <li>大項目ID : 01 : 認可コードグラント</li>
     * <li>中項目ID : 04 : 認可サーバ側は全スコープを許容するが、クライアント側でスコープを絞っている場合の操作</li>
     * <li>小項目ID : 003 : UPDATEスコープ(intercepturl) ・・・ リソースサーバ側でエラー</li>
     * <li>タスクが更新できないこと。</li>
     * </ul>
     */
    @Test
    public void testOTH20104003() {
        // 事前データ作成
        createDummyTask(1);

        // TODOアプリ - 認可処理要 - 認可コードグラント
        webDriverOperations.click(By.id("authorizationCode"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "認可コードグラント"));

        // registration_partialとintercepturlにチェックを入れてlistを押下
        webDriverOperations.click(By.cssSelector("input[name='registrationId'][value='registration_partial']"));
        webDriverOperations.click(By.cssSelector("input[name='resourceProtect'][value='intercepturl']"));
        webDriverOperations.click(By.name("list"));

        // login
        login("demo", "demo");

        // TODO一覧画面が表示されていること
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Todo List"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*OAuth2TokenServiceImpl",
                ".*Scopes = \\[READ, DELETE\\].*");

        // TODOタスク更新
        webDriverOperations.click(By.xpath("//div[3]/ul/li/form[1]/button"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*ExceptionLogger",
                ".*e.sf.fw.2001.*");

        // Finishボタンが消えていないこと
        String text = webDriverOperations.getText(By.xpath("//div[3]/ul/li"));
        assertFalse(text, text.matches("dummy_1(?!.*\\bFinish\\b).*Delete"));
    }

    /**
     * <ul>
     * <li>大項目ID : 01 : 認可コードグラント</li>
     * <li>中項目ID : 04 : 認可サーバ側は全スコープを許容するが、クライアント側でスコープを絞っている場合の操作</li>
     * <li>小項目ID : 004 : DELETEスコープ(intercepturl) ・・・ 正常</li>
     * <li>タスクが削除できること。</li>
     * </ul>
     */
    @Test
    public void testOTH20104004() {
        // 事前データ作成
        createDummyTask(1);

        // TODOアプリ - 認可処理要 - 認可コードグラント
        webDriverOperations.click(By.id("authorizationCode"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "認可コードグラント"));

        // registration_partialとintercepturlにチェックを入れてlistを押下
        webDriverOperations.click(By.cssSelector("input[name='registrationId'][value='registration_partial']"));
        webDriverOperations.click(By.cssSelector("input[name='resourceProtect'][value='intercepturl']"));
        webDriverOperations.click(By.name("list"));

        // login
        login("demo", "demo");

        // TODO一覧画面が表示されていること
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Todo List"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*OAuth2TokenServiceImpl",
                ".*Scopes = \\[READ, DELETE\\].*");

        // TODOタスク削除
        webDriverOperations.click(By.xpath("//div[3]/ul/li/form[2]/button"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*TraceLoggingInterceptor",
                ".*text=Deleted successfully!.*");

        // 一覧にタスクが表示されていないこと
        assertFalse("要素が存在する。", webDriverOperations.exists(By.xpath("//div[3]/ul/li")));
    }

    /**
     * <ul>
     * <li>大項目ID : 01 : 認可コードグラント</li>
     * <li>中項目ID : 04 : 認可サーバ側は全スコープを許容するが、クライアント側でスコープを絞っている場合の操作</li>
     * <li>小項目ID : 005 : GETスコープ(MethodAnnotation) ・・・ 正常</li>
     * <li>TODOList画面が表示されること。</li>
     * </ul>
     */
    @Test
    public void testOTH20104005() {
        // TODOアプリ - 認可処理要 - 認可コードグラント
        webDriverOperations.click(By.id("authorizationCode"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "認可コードグラント"));

        // registration_partialとannotationにチェックを入れてlistを押下
        webDriverOperations.click(By.cssSelector("input[name='registrationId'][value='registration_partial']"));
        webDriverOperations.click(By.cssSelector("input[name='resourceProtect'][value='annotation']"));
        webDriverOperations.click(By.name("list"));

        // login
        login("demo", "demo");

        // TODO一覧画面が表示されていること
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Todo List"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*OAuth2TokenServiceImpl",
                ".*Scopes = \\[READ, DELETE\\].*");
    }

    /**
     * <ul>
     * <li>大項目ID : 01 : 認可コードグラント</li>
     * <li>中項目ID : 04 : 認可サーバ側は全スコープを許容するが、クライアント側でスコープを絞っている場合の操作</li>
     * <li>小項目ID : 006 : CREATEスコープ(MethodAnnotation) ・・・ リソースサーバ側でエラー</li>
     * <li>タスクが作成できないこと。</li>
     * </ul>
     */
    @Test
    public void testOTH20104006() {
        // TODOアプリ - 認可処理要 - 認可コードグラント
        webDriverOperations.click(By.id("authorizationCode"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "認可コードグラント"));

        // registration_partialとannotationにチェックを入れてlistを押下
        webDriverOperations.click(By.cssSelector("input[name='registrationId'][value='registration_partial']"));
        webDriverOperations.click(By.cssSelector("input[name='resourceProtect'][value='annotation']"));
        webDriverOperations.click(By.name("list"));

        // login
        login("demo", "demo");

        // TODO一覧画面が表示されていること
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Todo List"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*OAuth2TokenServiceImpl",
                ".*Scopes = \\[READ, DELETE\\].*");

        // TODOタスク作成
        webDriverOperations.appendText(id("todoTitle"), "sample");
        webDriverOperations.click(id("create"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*ExceptionLogger",
                ".*e.sf.fw.2001.*");

        // 一覧にタスクが表示されていないこと
        assertFalse("要素が存在する。", webDriverOperations.exists(By.xpath("//div[3]/ul/li")));
    }

    /**
     * <ul>
     * <li>大項目ID : 01 : 認可コードグラント</li>
     * <li>中項目ID : 04 : 認可サーバ側は全スコープを許容するが、クライアント側でスコープを絞っている場合の操作</li>
     * <li>小項目ID : 007 : UPDATEスコープ(MethodAnnotation) ・・・ リソースサーバ側でエラー</li>
     * <li>タスクが更新できないこと。</li>
     * </ul>
     */
    @Test
    public void testOTH20104007() {
        // 事前データ作成
        createDummyTask(1);

        // TODOアプリ - 認可処理要 - 認可コードグラント
        webDriverOperations.click(By.id("authorizationCode"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "認可コードグラント"));

        // registration_partialとannotationにチェックを入れてlistを押下
        webDriverOperations.click(By.cssSelector("input[name='registrationId'][value='registration_partial']"));
        webDriverOperations.click(By.cssSelector("input[name='resourceProtect'][value='annotation']"));
        webDriverOperations.click(By.name("list"));

        // login
        login("demo", "demo");

        // TODO一覧画面が表示されていること
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Todo List"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*OAuth2TokenServiceImpl",
                ".*Scopes = \\[READ, DELETE\\].*");

        // TODOタスク更新
        webDriverOperations.click(By.xpath("//div[3]/ul/li/form[1]/button"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*ExceptionLogger",
                ".*e.sf.fw.2001.*");

        // Finishボタンが消えていないこと
        String text = webDriverOperations.getText(By.xpath("//div[3]/ul/li"));
        assertFalse(text, text.matches("dummy_1(?!.*\\bFinish\\b).*Delete"));
    }

    /**
     * <ul>
     * <li>大項目ID : 01 : 認可コードグラント</li>
     * <li>中項目ID : 04 : 認可サーバ側は全スコープを許容するが、クライアント側でスコープを絞っている場合の操作</li>
     * <li>小項目ID : 008 : DELETEスコープ(MethodAnnotation) ・・・ 正常</li>
     * <li>タスクが削除できること。</li>
     * </ul>
     */
    @Test
    public void testOTH20104008() {
        // 事前データ作成
        createDummyTask(1);

        // TODOアプリ - 認可処理要 - 認可コードグラント
        webDriverOperations.click(By.id("authorizationCode"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "認可コードグラント"));

        // registration_partialとannotationにチェックを入れてlistを押下
        webDriverOperations.click(By.cssSelector("input[name='registrationId'][value='registration_partial']"));
        webDriverOperations.click(By.cssSelector("input[name='resourceProtect'][value='annotation']"));
        webDriverOperations.click(By.name("list"));

        // login
        login("demo", "demo");

        // TODO一覧画面が表示されていること
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Todo List"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*OAuth2TokenServiceImpl",
                ".*Scopes = \\[READ, DELETE\\].*");

        // TODOタスク削除
        webDriverOperations.click(By.xpath("//div[3]/ul/li/form[2]/button"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(), ".*TraceLoggingInterceptor",
                ".*text=Deleted successfully!.*");

        // 一覧にタスクが表示されていないこと
        assertFalse("要素が存在する。", webDriverOperations.exists(By.xpath("//div[3]/ul/li")));
    }

}