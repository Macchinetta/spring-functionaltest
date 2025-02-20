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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

public class OAuth2_02_Test extends FunctionTestSupport {
    // 試験開始時刻を記録
    Instant startTime = Instant.now();

    // 現在のKeycloak設定値は以下の通り
    // Access Token Lifespan : 90s
    // Client Session Idle : 2m
    // Client Session Max : 3m
    // SSO Session Idle : 3m
    // SSO Session Max : 5h

    @After
    public void teraDown() {
        // 試験の度に認可サーバーの認証が実行されるようにWebDriverは都度落とす
        super.quitCurrentWebDriver();
    }

    @Override
    protected String getPackageRootUrl() {
        return super.applicationContextUrl + "-" + "oauth2-client";
    }

    /**
     * 認可サーバの認証
     * 
     * @param username ユーザ名
     * @param password パスワード
     */
    private void login(String username, String password) {
        // keycloakの認証画面が出力されるまで待機
        webDriverOperations.waitForDisplayed(ExpectedConditions.textToBePresentInElementLocated(
                By.id("kc-page-title"), "Sign in to your account"));

        webDriverOperations.appendText(id("username"), username);
        webDriverOperations.appendText(id("password"), password);
        webDriverOperations.click(id("kc-login"));
    }

    /**
     * <ul>
     * <li>大項目ID : 02 : トークンに関する試験</li>
     * <li>中項目ID : 01 : アクセストークン（有効期限）</li>
     * <li>小項目ID : 001 : アクセストークンの有効期限内（0秒 ～ アクセストークンの有効期限-60秒）</li>
     * <li>アクセストークンが更新されていないこと。</li>
     * <li>アクセストークンの有効期限が更新されていないこと。</li>
     * </ul>
     * <ul>
     * <li>大項目ID : 02 : トークンに関する試験</li>
     * <li>中項目ID : 01 : アクセストークン（有効期限）</li>
     * <li>小項目ID : 002 : リフレッシュトークンが実行される（アクセストークンの有効期限-60秒～リフレッシュトークン有効期限内）</li>
     * <li>アクセストークンが更新されていること。</li>
     * <li>アクセストークンの有効期限が最大有効期限未満の場合更新されていること。</li>
     * <li>アクセストークンの有効期限が最大有効期限と同じ場合更新されていないこと。</li>
     * </ul>
     * <ul>
     * <li>大項目ID : 02 : トークンに関する試験</li>
     * <li>中項目ID : 01 : アクセストークン（有効期限）</li>
     * <li>小項目ID : 004 : アクセストークンの最大有効期限外</li>
     * <li>アクセストークンが更新されていること。</li>
     * <li>アクセストークンの最大有効期限が変更されていること。</li>
     * </ul>
     */
    @Test
    public void testOTH20201001() {
        // OTH20201001 - Start

        // TODOアプリ - 認可処理要 - アクセストークンチェック
        webDriverOperations.click(By.id("accessTokenCheck"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "認可コードグラント"));

        // registration_allとintercepturlにチェックを入れてlistを押下
        webDriverOperations
                .click(By.cssSelector("input[name='registrationId'][value='registration_all']"));
        webDriverOperations
                .click(By.cssSelector("input[name='resourceProtect'][value='intercepturl']"));
        webDriverOperations.click(By.name("list"));

        // login
        login("demo", "demo");

        // 認可コードグラント画面
        webDriverOperations
                .waitForDisplayed(textToBe(By.id("screenTitle"), "Todo List - TokenCheck"));

        // AccessTokenの情報が出力されていること
        String accessTokenIssued_1 = webDriverOperations.getText(By.id("accessTokenIssued"));
        String accessTokenExpires_1 = webDriverOperations.getText(By.id("accessTokenExpires"));
        String accessTokenScopes_1 = webDriverOperations.getText(By.id("accessTokenScopes"));
        String accessTokenType_1 = webDriverOperations.getText(By.id("accessTokenType"));
        String accessTokenValue_1 = webDriverOperations.getText(By.id("accessTokenValue"));

        // 10s待機
        waitForElapsedTime(startTime, 10);

        // listボタン押下で再取得
        webDriverOperations.click(By.id("list"));

        String accessTokenIssued_2 = webDriverOperations.getText(By.id("accessTokenIssued"));
        String accessTokenExpires_2 = webDriverOperations.getText(By.id("accessTokenExpires"));
        String accessTokenScopes_2 = webDriverOperations.getText(By.id("accessTokenScopes"));
        String accessTokenType_2 = webDriverOperations.getText(By.id("accessTokenType"));
        String accessTokenValue_2 = webDriverOperations.getText(By.id("accessTokenValue"));

        // 各値に変更がないこと
        assertThat(accessTokenIssued_2, is(accessTokenIssued_1));
        assertThat(accessTokenExpires_2, is(accessTokenExpires_1));
        assertThat(accessTokenScopes_2, is(accessTokenScopes_1));
        assertThat(accessTokenType_2, is(accessTokenType_1));
        assertThat(accessTokenValue_2, is(accessTokenValue_1));

        // OTH20201002 - Start

        // 25s待機（計35s）
        waitForElapsedTime(startTime, 35);

        // listボタン押下で再取得
        webDriverOperations.click(By.id("list"));

        String accessTokenIssued_3 = webDriverOperations.getText(By.id("accessTokenIssued"));
        String accessTokenExpires_3 = webDriverOperations.getText(By.id("accessTokenExpires"));
        String accessTokenScopes_3 = webDriverOperations.getText(By.id("accessTokenScopes"));
        String accessTokenType_3 = webDriverOperations.getText(By.id("accessTokenType"));
        String accessTokenValue_3 = webDriverOperations.getText(By.id("accessTokenValue"));

        // リフレッシュされること(Issued,Expires,Valueの更新)
        assertThat(accessTokenIssued_3, not(accessTokenIssued_2));
        assertThat(accessTokenExpires_3, not(accessTokenExpires_2));
        assertThat(accessTokenScopes_3, is(accessTokenScopes_2));
        assertThat(accessTokenType_3, is(accessTokenType_2));
        assertThat(accessTokenValue_3, not(accessTokenValue_2));

        // 100s待機（計135s）
        waitForElapsedTime(startTime, 135);

        // listボタン押下で再取得
        webDriverOperations.click(By.id("list"));

        String accessTokenIssued_4 = webDriverOperations.getText(By.id("accessTokenIssued"));
        String accessTokenExpires_4 = webDriverOperations.getText(By.id("accessTokenExpires"));
        String accessTokenScopes_4 = webDriverOperations.getText(By.id("accessTokenScopes"));
        String accessTokenType_4 = webDriverOperations.getText(By.id("accessTokenType"));
        String accessTokenValue_4 = webDriverOperations.getText(By.id("accessTokenValue"));

        // リフレッシュされること(Issued,Expires,Valueの更新)
        assertThat(accessTokenIssued_4, not(accessTokenIssued_3));
        assertThat(accessTokenExpires_4, not(accessTokenExpires_3));
        assertThat(accessTokenScopes_4, is(accessTokenScopes_3));
        assertThat(accessTokenType_4, is(accessTokenType_3));
        assertThat(accessTokenValue_4, not(accessTokenValue_3));

        // 30s待機（計165s）
        waitForElapsedTime(startTime, 165);

        // listボタン押下で再取得
        webDriverOperations.click(By.id("list"));

        String accessTokenIssued_5 = webDriverOperations.getText(By.id("accessTokenIssued"));
        String accessTokenExpires_5 = webDriverOperations.getText(By.id("accessTokenExpires"));
        String accessTokenScopes_5 = webDriverOperations.getText(By.id("accessTokenScopes"));
        String accessTokenType_5 = webDriverOperations.getText(By.id("accessTokenType"));
        String accessTokenValue_5 = webDriverOperations.getText(By.id("accessTokenValue"));

        // リフレッシュされること(Issued,Valueの更新)
        assertThat(accessTokenIssued_5, not(accessTokenIssued_4));
        // assertThat(accessTokenExpires_5, is(accessTokenExpires_4));
        assertThat(accessTokenScopes_5, is(accessTokenScopes_4));
        assertThat(accessTokenType_5, is(accessTokenType_4));
        assertThat(accessTokenValue_5, not(accessTokenValue_4));

        // 最初の認可時に accessTokenIssued_1 + Client Session Max で最長でどこまで有効なのかを決定している。
        // Access Token Issued + Access Token Lifespan > Client Session Max となった場合には値が変わらないはずだが、
        // Access Token Issued に引きずられ秒がずれることがある模様（認可サーバー側の問題）
        assertThat(accessTokenExpires_5, is(accessTokenExpires_4));

        // OTH20201004 - Start

        // 25s待機（計190s ・・・ Clientの最大有効期限越え）
        waitForElapsedTime(startTime, 190);

        // listボタン押下で再取得
        webDriverOperations.click(By.id("list"));

        String accessTokenIssued_6 = webDriverOperations.getText(By.id("accessTokenIssued"));
        String accessTokenExpires_6 = webDriverOperations.getText(By.id("accessTokenExpires"));
        String accessTokenScopes_6 = webDriverOperations.getText(By.id("accessTokenScopes"));
        String accessTokenType_6 = webDriverOperations.getText(By.id("accessTokenType"));
        String accessTokenValue_6 = webDriverOperations.getText(By.id("accessTokenValue"));

        // Clientのセッション保持期間が過ぎているため再処理される
        // リフレッシュされること(Issued,Expires,Valueの更新)
        assertThat(accessTokenIssued_6, not(accessTokenIssued_5));
        assertThat(accessTokenExpires_6, not(accessTokenExpires_5));
        assertThat(accessTokenScopes_6, is(accessTokenScopes_5));
        assertThat(accessTokenType_6, is(accessTokenType_5));
        assertThat(accessTokenValue_6, not(accessTokenValue_5));
    }

    /**
     * <ul>
     * <li>大項目ID : 02 : トークンに関する試験</li>
     * <li>中項目ID : 01 : アクセストークン（有効期限）</li>
     * <li>小項目ID : 003 : 再認可（リフレッシュトークン有効期限外）</li>
     * <li>アクセストークンが更新されていること。</li>
     * <li>アクセストークンの最大有効期限が変更されていること。</li>
     * </ul>
     */
    @Test
    public void testOTH20201003() {
        // TODOアプリ - 認可処理要 - アクセストークンチェック
        webDriverOperations.click(By.id("accessTokenCheck"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "認可コードグラント"));

        // registration_allとintercepturlにチェックを入れてlistを押下
        webDriverOperations
                .click(By.cssSelector("input[name='registrationId'][value='registration_all']"));
        webDriverOperations
                .click(By.cssSelector("input[name='resourceProtect'][value='intercepturl']"));
        webDriverOperations.click(By.name("list"));

        // login
        login("demo", "demo");

        // 認可コードグラント画面
        webDriverOperations
                .waitForDisplayed(textToBe(By.id("screenTitle"), "Todo List - TokenCheck"));

        // AccessTokenの情報が出力されていること
        String accessTokenIssued_1 = webDriverOperations.getText(By.id("accessTokenIssued"));
        String accessTokenExpires_1 = webDriverOperations.getText(By.id("accessTokenExpires"));
        String accessTokenScopes_1 = webDriverOperations.getText(By.id("accessTokenScopes"));
        String accessTokenType_1 = webDriverOperations.getText(By.id("accessTokenType"));
        String accessTokenValue_1 = webDriverOperations.getText(By.id("accessTokenValue"));

        // 125s待機
        webDriverOperations.suspend(125L, TimeUnit.SECONDS);

        // listボタン押下で再取得
        webDriverOperations.click(By.id("list"));

        String accessTokenIssued_2 = webDriverOperations.getText(By.id("accessTokenIssued"));
        String accessTokenExpires_2 = webDriverOperations.getText(By.id("accessTokenExpires"));
        String accessTokenScopes_2 = webDriverOperations.getText(By.id("accessTokenScopes"));
        String accessTokenType_2 = webDriverOperations.getText(By.id("accessTokenType"));
        String accessTokenValue_2 = webDriverOperations.getText(By.id("accessTokenValue"));

        // Clientのアイドル期間が過ぎているため再処理される
        // リフレッシュされること(Issued,Expires,Valueの更新)
        assertThat(accessTokenIssued_2, not(accessTokenIssued_1));
        assertThat(accessTokenExpires_2, not(accessTokenExpires_1));
        assertThat(accessTokenScopes_2, is(accessTokenScopes_1));
        assertThat(accessTokenType_2, is(accessTokenType_1));
        assertThat(accessTokenValue_2, not(accessTokenValue_1));
    }

    /**
     * <ul>
     * <li>大項目ID : 02 : トークンに関する試験</li>
     * <li>中項目ID : 02 : トークンの加工</li>
     * <li>小項目ID : 001 : 誤ったアクセストークンを設定</li>
     * <li>リソースサーバーでエラーとなること。</li>
     * </ul>
     */
    @Test
    public void testOTH20202001() {
        // TODOアプリ - 認可処理要 - アクセストークンチェック
        webDriverOperations.click(By.id("accessTokenCheck"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "認可コードグラント"));

        // registration_allとintercepturlにチェックを入れてlistを押下
        webDriverOperations
                .click(By.cssSelector("input[name='registrationId'][value='registration_all']"));
        webDriverOperations
                .click(By.cssSelector("input[name='resourceProtect'][value='intercepturl']"));
        webDriverOperations.click(By.name("list"));

        // login
        login("demo", "demo");

        // 認可コードグラント画面
        webDriverOperations
                .waitForDisplayed(textToBe(By.id("screenTitle"), "Todo List - TokenCheck"));

        webDriverOperations.click(id("tokenProcessing"));

        webDriverOperations.waitForDisplayed(textToBe(By.xpath("//div//h1"), "Business Error!"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion(10_000L);
        // リソースサーバー側ではログが出力されないためクライアントサーバー側のログで判断する
        dbLogAssertOperations.assertContainsByRegexMessage(".*RestTemplateResponseErrorHandler",
                "HttpStatusCode:401.*error_description=\"An error occurred while attempting to decode the Jwt: Signed JWT rejected: Invalid signature\"");
    }

    private void waitForElapsedTime(Instant startTime, long targetSeconds) {
        long waitTime = targetSeconds - Duration.between(startTime, Instant.now()).getSeconds();;
        if (waitTime > 0) {
            webDriverOperations.suspend(waitTime, TimeUnit.SECONDS);
        }
    }
}
