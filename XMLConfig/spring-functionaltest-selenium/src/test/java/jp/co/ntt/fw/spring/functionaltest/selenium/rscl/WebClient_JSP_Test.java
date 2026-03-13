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
package jp.co.ntt.fw.spring.functionaltest.selenium.rscl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.openqa.selenium.By.id;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

/**
 * WebClientを利用したテスト
 */
public class WebClient_JSP_Test extends FunctionTestSupport {

    private static final Logger logger = LoggerFactory.getLogger(WebClient_JSP_Test.class);

    @Override
    protected String getPackageRootUrl() {
        return super.getPackageRootUrl() + "-client";
    }

    // Appendix関連

    /**
     * Appendix - 非同期通信のデータ登録
     * <ul>
     * <li>RSCL4801001：CompletableFutureを利用したデータの登録（非同期）</li>
     * <li>対象API：/api/v1/async/bulk</li>
     * <li>対象メソッド：POST</li>
     * </ul>
     */
    @Test
    public void testRSCL4801001() {
        // メニュー画面の操作
        webDriverOperations.click(id("webClient_jsp"));
        webDriverOperations.click(id("wbclEntry1"));

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック（非同期モードで結果をTomcatに返却している）
        assertThat(webDriverOperations.getText(id("screenTitle")),
                is("RESTクライアント処理結果【レスポンス情報出力】"));
        assertThat(webDriverOperations.getText(id("httpStatus")), is("200"));
        assertThat(webDriverOperations.getText(id("responseHeader")), not(emptyString()));
        assertThat(webDriverOperations.getText(id("responseBody")), is("Result [count=20]"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*AsyncAPIController",
                "Call API:bulk");

        // 非同期の確認
        String mainThread = getThreadId("..*EntryWebClientServiceImpl", "Start Request Thread:.*");
        String eventLoopThread = getThreadId("..*RSCL_JSP_Helper", "Completed Request Thread:.*");
        logger.debug("Thread: {}, {}", mainThread, eventLoopThread);
        // 動作スレッドが異なることの証明（呼び出し前はメインスレッドで、リスナーはイベントループスレッドで動作していることを確認）
        assertThat(mainThread, not(eventLoopThread));

        // ログで処理結果の確認
        dbLogAssertOperations.assertContainsByRegexMessage("..*RSCL_JSP_Helper",
                "Successfully created users. statusCode: 200");
    }

    /**
     * Appendix - 非同期通信のデータ登録
     * <ul>
     * <li>RSCL4802001：CompletableFutureを利用したデータの登録（同期）</li>
     * <li>対象API：/api/v1/async/bulk</li>
     * <li>対象メソッド：POST</li>
     * </ul>
     */
    @Test
    public void testRSCL4802001() {
        // メニュー画面の操作
        webDriverOperations.click(id("webClient_jsp"));
        webDriverOperations.click(id("wbclEntry2"));

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")),
                is("RESTクライアント処理結果【レスポンス情報出力】"));
        assertThat(webDriverOperations.getText(id("httpStatus")), is("200"));
        assertThat(webDriverOperations.getText(id("responseHeader")), not(emptyString()));
        assertThat(webDriverOperations.getText(id("responseBody")), is("Result [count=20]"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*AsyncAPIController",
                "Call API:bulk");

        // 非同期の確認(同期だが厳密には非同期結果を待ち合わせしているだけなのでスレッドは異なる）
        String mainThread = getThreadId("..*EntryWebClientServiceImpl", "Start Request Thread:.*");
        String eventLoopThread = getThreadId("..*RSCL_JSP_48Controller", "Completed Request Thread:.*");
        logger.debug("Thread: {}, {}", mainThread, eventLoopThread);
        // 動作スレッドが異なることの証明（呼び出し前はメインスレッドで、リスナーはイベントループスレッドで動作していることを確認）
        assertThat(mainThread, not(eventLoopThread));

        // ログで処理結果の確認
        dbLogAssertOperations.assertContainsByRegexMessage("..*RSCL_JSP_48Controller",
                "Successfully created users. statusCode: 200");
    }

    /**
     * Appendix - エラーハンドリングの設定確認
     * <ul>
     * <li>RSCL4901001：アプリケーション全体でエラーハンドリング</li>
     * <li>対象API：/api/v1/async/exception/{status}</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL4901001() {
        // メニュー画面の操作
        webDriverOperations.click(id("webClient_jsp"));
        webDriverOperations.click(id("wbclError1"));

        // システムエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("System Error!"));
        assertThat(webDriverOperations.getText(id("statusCode")), is("StatusCode: 404"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*AsyncAPIController",
                "Call API:exception:404");

        // 非同期の確認
        String mainThread = getThreadId("..*ErrorHandleWebClientServiceImpl", "Start Request Thread:.*");
        String eventLoopThread = getThreadId("..*RSCL_JSP_Helper", "Completed Request Thread:.*");
        logger.debug("Thread: {}, {}", mainThread, eventLoopThread);
        // 動作スレッドが異なることの証明（呼び出し前はメインスレッドで、リスナーはイベントループスレッドで動作していることを確認）
        assertThat(mainThread, not(eventLoopThread));

        // エラーログの確認
        dbLogAssertOperations.assertContainsByRegexMessage("..*WebClientConfig",
                "DefaultStatusHandler StatusCode:404");
        dbLogAssertOperations.assertContainsByRegexStackTrace(
                ".*jp.co.ntt.fw.spring.functionaltest.app.cmmn.exception.CustomException.*DefaultStatusHandler StatusCode:404.*",
                greaterThanOrEqualTo(1L));

    }

    /**
     * Appendix - エラーハンドリングの設定確認
     * <ul>
     * <li>RSCL4902001：リクエスト毎でエラーハンドリング</li>
     * <li>対象API：/api/v1/async/exception/{status}</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL4902001() {
        // メニュー画面の操作
        webDriverOperations.click(id("webClient_jsp"));
        webDriverOperations.click(id("wbclError2"));

        // システムエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("System Error!"));
        assertThat(webDriverOperations.getText(id("statusCode")), is("StatusCode: 500"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*AsyncAPIController",
                "Call API:exception:500");

        // 非同期の確認
        String mainThread = getThreadId("..*ErrorHandleWebClientServiceImpl", "Start Request Thread:.*");
        String eventLoopThread = getThreadId("..*RSCL_JSP_Helper", "Completed Request Thread:.*");
        logger.debug("Thread: {}, {}", mainThread, eventLoopThread);
        // 動作スレッドが異なることの証明（呼び出し前はメインスレッドで、リスナーはイベントループスレッドで動作していることを確認）
        assertThat(mainThread, not(eventLoopThread));

        // エラーログの確認
        dbLogAssertOperations.assertContainsByRegexMessage("..*ErrorHandleWebClientServiceImpl",
                "StatusHandler StatusCode:500");
        dbLogAssertOperations.assertContainsByRegexStackTrace(
                ".*jp.co.ntt.fw.spring.functionaltest.app.cmmn.exception.CustomException.*StatusHandler StatusCode:500.*",
                greaterThanOrEqualTo(1L));

    }

    /**
     * Appendix - エラーハンドリングの設定確認
     * <ul>
     * <li>RSCL4903001：例外のエラーハンドリング（2xx系）</li>
     * <li>対象API：/api/v1/async/exception/{status}</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL4903001() {
        // メニュー画面の操作
        webDriverOperations.click(id("webClient_jsp"));
        webDriverOperations.click(id("wbclError3"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック（非同期モードで結果をTomcatに返却している）
        assertThat(webDriverOperations.getText(id("screenTitle")),
                is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("exceptionTest"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*AsyncAPIController",
                "Call API:exception:200");

        // 非同期の確認
        String mainThread = getThreadId("..*ErrorHandleWebClientServiceImpl", "Start Request Thread:.*");
        String eventLoopThread = getThreadId("..*RSCL_JSP_Helper", "Completed Request Thread:.*");
        logger.debug("Thread: {}, {}", mainThread, eventLoopThread);
        // 動作スレッドが異なることの証明（呼び出し前はメインスレッドで、リスナーはイベントループスレッドで動作していることを確認）
        assertThat(mainThread, not(eventLoopThread));

        // 処理結果の確認
        dbLogAssertOperations.assertContainsByRegexMessage(
                "..*RSCL_JSP_Helper.*", "Success StatusCode: 200");
    }

    /**
     * Appendix - エラーハンドリングの設定確認
     * <ul>
     * <li>RSCL4904001：例外のエラーハンドリング（4xx系）</li>
     * <li>対象API：/api/v1/async/exception/{status}</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL4904001() {
        // メニュー画面の操作
        webDriverOperations.click(id("webClient_jsp"));
        webDriverOperations.click(id("wbclError4"));

        // システムエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("System Error!"));
        assertThat(webDriverOperations.getText(id("statusCode")), is("StatusCode: 404"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*AsyncAPIController",
                "Call API:exception:404");

        // 非同期の確認
        String mainThread = getThreadId("..*ErrorHandleWebClientServiceImpl", "Start Request Thread:.*");
        String eventLoopThread = getThreadId("..*RSCL_JSP_Helper", "Completed Request Thread:.*");
        logger.debug("Thread: {}, {}", mainThread, eventLoopThread);
        // 動作スレッドが異なることの証明（呼び出し前はメインスレッドで、リスナーはイベントループスレッドで動作していることを確認）
        assertThat(mainThread, not(eventLoopThread));

        // エラーログの確認
        dbLogAssertOperations.assertContainsByRegexMessage("..*RSCL_JSP_Helper",
                "Error occurred while creating users");
        dbLogAssertOperations.assertContainsByRegexStackTrace(
                ".*org.springframework.web.reactive.function.client.WebClientResponseException.*404.*",
                greaterThanOrEqualTo(1L));

    }

    /**
     * Appendix - エラーハンドリングの設定確認
     * <ul>
     * <li>RSCL4905001：例外のエラーハンドリング（5xx系）</li>
     * <li>対象API：/api/v1/async/exception/{status}</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL4905001() {
        // メニュー画面の操作
        webDriverOperations.click(id("webClient_jsp"));
        webDriverOperations.click(id("wbclError5"));

        // システムエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("System Error!"));
        assertThat(webDriverOperations.getText(id("statusCode")), is("StatusCode: 500"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*AsyncAPIController",
                "Call API:exception:500");

        // 非同期の確認
        String mainThread = getThreadId("..*ErrorHandleWebClientServiceImpl", "Start Request Thread:.*");
        String eventLoopThread = getThreadId("..*RSCL_JSP_Helper", "Completed Request Thread:.*");
        logger.debug("Thread: {}, {}", mainThread, eventLoopThread);
        // 動作スレッドが異なることの証明（呼び出し前はメインスレッドで、リスナーはイベントループスレッドで動作していることを確認）
        assertThat(mainThread, not(eventLoopThread));

        // エラーログの確認
        dbLogAssertOperations.assertContainsByRegexMessage("..*RSCL_JSP_Helper",
                "Error occurred while creating users");
        dbLogAssertOperations.assertContainsByRegexStackTrace(
                ".*org.springframework.web.reactive.function.client.WebClientResponseException.*500.*",
                greaterThanOrEqualTo(1L));

    }

    /**
     * Appendix - エラーハンドリングの設定確認
     * <ul>
     * <li>RSCL4906001：例外のエラーハンドリング（Unknown）</li>
     * <li>対象API：/api/v1/async/exception/{status}</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     * ※不明コードに対する振る舞いがRestClientと異なりエラーとなる。
     */
    @Test
    public void testRSCL4906001() {
        // メニュー画面の操作
        webDriverOperations.click(id("webClient_jsp"));
        webDriverOperations.click(id("wbclError6"));

        // システムエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("System Error!"));
        assertThat(webDriverOperations.getText(id("statusCode")), is("StatusCode: 901"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*AsyncAPIController",
                "Call API:exception:901");

        // 非同期の確認
        String mainThread = getThreadId("..*ErrorHandleWebClientServiceImpl", "Start Request Thread:.*");
        String eventLoopThread = getThreadId("..*RSCL_JSP_Helper", "Completed Request Thread:.*");
        logger.debug("Thread: {}, {}", mainThread, eventLoopThread);
        // 動作スレッドが異なることの証明（呼び出し前はメインスレッドで、リスナーはイベントループスレッドで動作していることを確認）
        assertThat(mainThread, not(eventLoopThread));

        // エラーログの確認
        dbLogAssertOperations.assertContainsByRegexMessage("..*RSCL_JSP_Helper",
                "Error occurred while creating users");
        dbLogAssertOperations.assertContainsByRegexStackTrace(
                ".*org.springframework.web.reactive.function.client.UnknownHttpStatusCodeException.*901.*",
                greaterThanOrEqualTo(1L));

    }

    /**
     * Appendix - エラーハンドリングの設定確認
     * <ul>
     * <li>RSCL4907001：例外のエラーハンドリング（ストリーム中の例外）</li>
     * <li>対象API：/api/v1/async/largeData</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     * ※データを受け取る際のバッファサイズを極端に減らしてエラーを発生させている。
     */
    @Test
    public void testRSCL4907001() {
        // メニュー画面の操作
        webDriverOperations.click(id("webClient_jsp"));
        webDriverOperations.click(id("wbclError7"));

        // システムエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("System Error!"));
        assertThat(webDriverOperations.getText(id("statusCode")), is("StatusCode: 200"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*AsyncAPIController",
                "Call API:largeData");

        // 非同期の確認
        String mainThread = getThreadId("..*ErrorHandleWebClientServiceImpl", "Start Request Thread:.*");
        String eventLoopThread = getThreadId("..*RSCL_JSP_Helper", "Completed Request Thread:.*");
        logger.debug("Thread: {}, {}", mainThread, eventLoopThread);
        // 動作スレッドが異なることの証明（呼び出し前はメインスレッドで、リスナーはイベントループスレッドで動作していることを確認）
        assertThat(mainThread, not(eventLoopThread));

        // エラーログの確認
        dbLogAssertOperations.assertContainsByRegexMessage("..*RSCL_JSP_Helper",
                "Error occurred while creating users");
        dbLogAssertOperations.assertContainsByRegexStackTrace(
                ".*org.springframework.core.io.buffer.DataBufferLimitException: Exceeded limit on max bytes to buffer :.*",
                greaterThanOrEqualTo(1L));

    }

    /**
     * Appendix - リクエスト送信前の共通処理の適用
     * <ul>
     * <li>RSCL5001001：ExchangeFilterFunctionの設定(ロギング)</li>
     * <li>対象API：/api/v1/async</li>
     * <li>対象メソッド：POST</li>
     * </ul>
     */
    @Test
    public void testRSCL5001001() {
        // メニュー画面の操作
        webDriverOperations.click(id("webClient_jsp"));
        webDriverOperations.click(id("wbclFilter1"));

        // 入力条件設定
        webDriverOperations.overrideText(id("name"), "test5001");
        webDriverOperations.overrideText(id("age"), "33");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック（非同期モードで結果をTomcatに返却している）
        assertThat(webDriverOperations.getText(id("screenTitle")),
                is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("test5001_created"));
        assertThat(webDriverOperations.getText(id("userAge")), is("33"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*AsyncAPIController",
                "Call API:insert");

        // 非同期の確認
        String mainThread = getThreadId("..*FilterFunctionWebClientServiceImpl", "Start Request Thread:.*");
        String eventLoopThread = getThreadId("..*RSCL_JSP_Helper", "Completed Request Thread:.*");
        logger.debug("Thread: {}, {}", mainThread, eventLoopThread);
        // 動作スレッドが異なることの証明（呼び出し前はメインスレッドで、リスナーはイベントループスレッドで動作していることを確認）
        assertThat(mainThread, not(eventLoopThread));

        // ログで処理結果の確認
        dbLogAssertOperations.assertContainsByRegexMessage("..*RSCL_JSP_Helper",
                "Success StatusCode: 200");

        // インターセプターのログ確認
        dbLogAssertOperations.assertContainsByRegexMessage("..*LoggingExchangeFilterFunction",
                "External Request to .*/api/v1/async");
    }

    /**
     * Appendix - リクエスト送信前の共通処理の適用
     * <ul>
     * <li>RSCL5002001：ExchangeFilterFunctionの設定(ヘッダ確認)</li>
     * <li>対象API：/api/v1/async/header</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL5002001() {
        // メニュー画面の操作
        webDriverOperations.click(id("webClient_jsp"));
        webDriverOperations.click(id("wbclFilter2"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック（非同期モードで結果をTomcatに返却している）
        assertThat(webDriverOperations.getText(id("screenTitle")),
                is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("test"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*AsyncAPIController",
                "Call API:customHeader");

        // リクエストヘッダの確認
        dbLogAssertOperations.assertContainsByRegexMessage("..*AsyncAPIController",
                "Custom-Header: test value");

        // 非同期の確認
        String mainThread = getThreadId("..*FilterFunctionWebClientServiceImpl", "Start Request Thread:.*");
        String eventLoopThread = getThreadId("..*RSCL_JSP_Helper", "Completed Request Thread:.*");
        logger.debug("Thread: {}, {}", mainThread, eventLoopThread);
        // 動作スレッドが異なることの証明（呼び出し前はメインスレッドで、リスナーはイベントループスレッドで動作していることを確認）
        assertThat(mainThread, not(eventLoopThread));

        // ログで処理結果の確認
        dbLogAssertOperations.assertContainsByRegexMessage("..*RSCL_JSP_Helper",
                "Success StatusCode: 200");

        // インターセプターのログ確認
        dbLogAssertOperations.assertContainsByRegexMessage("..*SettingHeadersExchangeFilterFunction",
                "execute filter SettingHeadersExchangeFilterFunction.");
    }

    // 正規表現パターン：スレッドID抽出用
    private static final Pattern THREAD_ID_PATTERN = Pattern.compile("^(?:Start|Completed) Request Thread: \\s*(\\S+)$",
            Pattern.MULTILINE);

    private String getThreadId(String loggerNamePattern, String messagePattern) {
        StringBuilder sql = new StringBuilder();
        StringBuilder where = new StringBuilder();
        sql.append("SELECT e.formatted_message FROM logging_event e");
        where.append(" WHERE e.formatted_message REGEXP :message");
        where.append(" AND e.logger_name REGEXP :loggerName");
        sql.append(where);

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("loggerName", loggerNamePattern);
        params.addValue("message", messagePattern);

        String message = dbLogAssertOperations.getJdbcOperations().queryForObject(sql.toString(), params,
                String.class);
        Matcher m = THREAD_ID_PATTERN.matcher(message);
        return m.find() ? m.group(1) : null;
    }
}
