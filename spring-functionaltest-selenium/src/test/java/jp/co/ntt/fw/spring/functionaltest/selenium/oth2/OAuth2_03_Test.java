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

import static org.openqa.selenium.By.id;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

public class OAuth2_03_Test extends FunctionTestSupport {

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
     * <li>大項目ID : 03 : その他異常系</li>
     * <li>中項目ID : 01 : 認可サーバからの応答がない</li>
     * <li>小項目ID : 001 : トークンエンドポイントの誤り</li>
     * <li>Resource Not Foundとなること。</li>
     * </ul>
     */
    @Test
    public void testOTH20301001() {
        // TODOアプリ - 例外処理
        webDriverOperations.click(By.id("errorHandler"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "例外処理"));

        // トークンエンドポイント不正
        webDriverOperations.click(By.id("wrongTokenEndpoint"));

        login("demo", "demo");

        // KeyCloakのログも特に出力はない

        // Resource Not Found Error画面が表示されていること
        webDriverOperations
                .waitForDisplayed(textToBe(By.xpath("//div/h1"), "Resource Not Found Error!"));
    }

    /**
     * <ul>
     * <li>大項目ID : 03 : その他異常系</li>
     * <li>中項目ID : 01 : 認可サーバからの応答がない</li>
     * <li>小項目ID : 002 : 認可エンドポイントの誤り</li>
     * <li>認可サーバー上で404となること。</li>
     * </ul>
     */
    @Test
    public void testOTH20301002() {
        // TODOアプリ - 例外処理
        webDriverOperations.click(By.id("errorHandler"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "例外処理"));

        // 認可エンドポイント不正
        webDriverOperations.click(By.id("wrongAuthEndpoint"));

        // login画面に到達しない
        webDriverOperations.waitForDisplayed(ExpectedConditions
                .textToBePresentInElementLocated(By.id("kc-page-title"), "We are sorry..."));
    }

    /**
     * <ul>
     * <li>大項目ID : 03 : その他異常系</li>
     * <li>中項目ID : 01 : 認可サーバからの応答がない</li>
     * <li>小項目ID : 003 : JWTエンドポイントの誤り</li>
     * <li>リソースサーバーでエラーとなること。</li>
     * </ul>
     */
    @Test
    public void testOTH20301003() {
        // TODOアプリ - 例外処理
        webDriverOperations.click(By.id("errorHandler"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "例外処理"));

        // JWTエンドポイント不正
        webDriverOperations.click(By.id("wrongJWTEndpoint"));

        login("demo", "demo");

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(".*ExceptionLogger$",
                "An error occurred while attempting to decode the Jwt: Couldn't retrieve remote JWK set:.*404");
    }

    /**
     * <ul>
     * <li>大項目ID : 03 : その他異常系</li>
     * <li>中項目ID : 02 : トークン有効期限が切れている</li>
     * <li>小項目ID : 001 : リソースサーバー側で有効期限切れ</li>
     * <li>JWT認証時に時間経過による認証エラーが発生すること。</li>
     * </ul>
     */
    @Test
    public void testOTH20302001() {
        // TODOアプリ - 例外処理
        webDriverOperations.click(By.id("errorHandler"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "例外処理"));

        // リクエスト送信時に遅延発生
        webDriverOperations.click(By.id("waitRequest"));

        try {
            login("demo", "demo");
        } catch (Exception e) {
            // ボタン押下の結果が返ってこないため、次の処理に行く前にこちらでタイムアウトが発生してしまう
            // webdriverがタイムアウトすること自体は試験の本質ではないので、ここでは例外を握りつぶしたうえで画面遷移の確認を行う
        }
        
        // ログアサート
        dbLogAssertOperations.waitForAssertion(180000L);
                
        // リソースサーバー側ではログが出力されないためクライアントサーバー側のログで判断する
        dbLogAssertOperations.assertContainsByRegexMessage(".*RestTemplateResponseErrorHandler",
                "HttpStatusCode:401.*error_description=\"An error occurred while attempting to decode the Jwt: Jwt expired at [-TZ:0-9]+\"");
    }

    /**
     * <ul>
     * <li>大項目ID : 03 : その他異常系</li>
     * <li>中項目ID : 03 : 認可サーバーを経由しない</li>
     * <li>小項目ID : 001 : クライアント側で認可サーバーに接続せずにリソースサーバーへアクセスする</li>
     * <li>リソースサーバからWWW-Authenticateヘッダが返却されること。</li>
     * </ul>
     */
    @Test
    public void testOTH20303001() {
        // TODOアプリ - 例外処理
        webDriverOperations.click(By.id("errorHandler"));

        // 認可コードグラント画面
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "例外処理"));

        // リクエスト送信時に遅延発生
        webDriverOperations.click(By.id("notThroughAnAuthorizedServer"));

        webDriverOperations.waitForDisplayed(textToBe(By.xpath("//div//h1"), "Business Error!"));

        // ログアサート
        dbLogAssertOperations.waitForAssertion();
        // リソースサーバー側ではログが出力されないためクライアントサーバー側のログで判断する
        dbLogAssertOperations.assertContainsByRegexMessage(".*RestTemplateResponseErrorHandler",
                "HttpStatusCode:401.*WWW-Authenticate:\"Bearer\"");
    }

}
