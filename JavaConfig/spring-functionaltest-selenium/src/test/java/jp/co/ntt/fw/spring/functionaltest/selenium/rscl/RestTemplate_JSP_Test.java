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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

import org.junit.Test;
import org.openqa.selenium.By;
import org.springframework.http.MediaType;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

/**
 * RestTemplateを利用したテスト
 */
public class RestTemplate_JSP_Test extends FunctionTestSupport {

    @Override
    protected String getPackageRootUrl() {
        return super.getPackageRootUrl() + "-client";
    }

    // OverView関連のテスト

    /**
     * Overview関連 - ClientHttpRequestFactoryの設定確認
     * <ul>
     * <li>RSCL0101001 ： Content-lengthの取得（バッファリングなし）</li>
     * <li>対象API：/api/v1/users</li>
     * <li>対象メソッド：POST</li>
     * </ul>
     */
    @Test
    public void testRSCL0101001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpContentLength1"));

        // 入力条件設定
        webDriverOperations.overrideText(id("name"), "test0101");
        webDriverOperations.overrideText(id("age"), "30");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test0101_created"));
        assertThat(webDriverOperations.getText(id("userAge")), is("30"));

        // Spring 6.1からdefaultではContent-Lengthをヘッダに付与しない
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController",
                "Content-Length : null");
    }

    /**
     * Overview関連 - ClientHttpRequestFactoryの設定確認
     * <ul>
     * <li>RSCL0101001 ： Content-lengthの取得（バッファリングあり）</li>
     * <li>対象API：/api/v1/users</li>
     * <li>対象メソッド：POST</li>
     * </ul>
     */
    @Test
    public void testRSCL0102001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpContentLength2"));

        // 入力条件設定
        webDriverOperations.overrideText(id("name"), "test0102");
        webDriverOperations.overrideText(id("age"), "30");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test0102_created"));
        assertThat(webDriverOperations.getText(id("userAge")), is("30"));

        // Spring 6.1からdefaultではContent-Lengthをヘッダに付与しない
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController",
                "Content-Length : \\d+");
    }

    /**
     * Overview関連 - HttpMessageConverterの設定確認
     * <ul>
     * <li>RSCL0201001 ： HttpMessageConverter（ByteArrayHttpMessageConverter）</li>
     * HTTPボディ⇔byte[]変換を確認する。
     * <li>対象API：/api/v1/converter/str</li>
     * <li>対象メソッド：POST</li> *
     * </ul>
     */
    @Test
    public void testRSCL0201001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpConverter1"));

        // 入力条件設定
        webDriverOperations.overrideText(id("message"), "send byte");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【文字リスト出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("strListSize")), is("2"));
        assertThat(webDriverOperations.getText(id("str1")), is("send byte"));
        assertThat(webDriverOperations.getText(id("str2")), is("_received"));

        // Content-Typeヘッダのログを確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*ConverterAPIController",
                "Call API:str");
        dbLogAssertOperations.assertContainsByRegexMessage("..*ConverterAPIController",
                "Content-Type : " + MediaType.APPLICATION_OCTET_STREAM_VALUE);
    }

    /**
     * Overview関連 - HttpMessageConverterの設定確認
     * <ul>
     * <li>RSCL0202001 ： HttpMessageConverter（StringHttpMessageConverter）</li>
     * <li>対象API：/api/v1/converter/str</li>
     * <li>対象メソッド：POST</li> * HTTPボディ⇔文字列変換を確認する。
     * </ul>
     */
    @Test
    public void testRSCL0202001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpConverter2"));

        // 入力条件設定
        webDriverOperations.overrideText(id("message"), "sendString");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【文字列出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("rcvString")), is("sendString_received"));

        // Content-Type、Acceptヘッダのログを確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*ConverterAPIController",
                "Call API:str");
        dbLogAssertOperations.assertContainsByRegexMessage("..*ConverterAPIController",
                "Content-Type : " + MediaType.TEXT_PLAIN_VALUE);
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*ConverterRestTemplateServiceImpl.*",
                ".*Content-Type : " + MediaType.TEXT_PLAIN_VALUE);
    }

    /**
     * Overview関連 - HttpMessageConverterの設定確認
     * <ul>
     * <li>RSCL0203001 ：HttpMessageConverter（ResourceHttpMessageConverter）</li>
     * <li>対象API：/api/v1/converter/str</li>
     * <li>対象メソッド：POST</li> HTTPボディ⇔Resource変換を確認する。
     * </ul>
     */
    @Test
    public void testRSCL0203001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpConverter3"));

        // 入力条件設定
        webDriverOperations.overrideText(id("message"), "send resource");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【文字リスト出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("strListSize")), is("2"));
        assertThat(webDriverOperations.getText(id("str1")), is("send resource"));
        assertThat(webDriverOperations.getText(id("str2")), is("_received"));

        // Content-Typeヘッダのログを確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*ConverterAPIController",
                "Call API:str");
        dbLogAssertOperations.assertContainsByRegexMessage("..*ConverterAPIController",
                "Content-Type : " + MediaType.APPLICATION_OCTET_STREAM_VALUE);
    }

    /**
     * Overview関連 - HttpMessageConverterの設定確認
     * <ul>
     * <li>RSCL0204001 ：HttpMessageConverter（SourceHttpMessageConverter）</li>
     * <li>対象API：/api/v1/users</li>
     * <li>対象メソッド：POST</li> HTTPボディ⇔Source変換を確認する。
     * </ul>
     */
    @Test
    public void testRSCL0204001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpConverter4"));

        // 入力条件設定
        webDriverOperations.overrideText(id("name"), "test0204");
        webDriverOperations.overrideText(id("age"), "20");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test0204_created"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // Content-Type、Acceptヘッダのログを確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController",
                "Call API:create");
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController",
                "Content-Type : " + MediaType.APPLICATION_XML_VALUE);
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*ConverterRestTemplateServiceImpl.*",
                ".*Content-Type : " + MediaType.APPLICATION_XML_VALUE);
    }

    /**
     * Overview関連 - HttpMessageConverterの設定確認
     * <ul>
     * <li>RSCL0205001：HttpMessageConverter（AllEncompassingFormHttpMessageConverter-変換成功）</li>
     * <li>対象API：/api/v1/converter/map</li>
     * <li>対象メソッド：POST</li> HTTPボディ⇔MultiValueMap変換を確認する。
     * </ul>
     */
    @Test
    public void testRSCL0205001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpConverter5"));

        // 入力条件設定
        webDriverOperations.overrideText(id("name"), "test0205");
        webDriverOperations.overrideText(id("age"), "20");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test0205_received"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // Content-Type、Acceptヘッダのログを確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*ConverterAPIController",
                "Call API:map");
        dbLogAssertOperations.assertContainsByRegexMessage("..*ConverterAPIController",
                "Content-Type : " + MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*ConverterRestTemplateServiceImpl.*",
                ".*Content-Type : " + MediaType.APPLICATION_FORM_URLENCODED_VALUE);
    }

    /**
     * Overview関連 - HttpMessageConverterの設定確認
     * <ul>
     * <li>RSCL0206001：HttpMessageConverter（AllEncompassingFormHttpMessageConverter-変換失敗）</li>
     * <li>対象API：/api/v1/converter/multipart</li>
     * <li>対象メソッド：POST</li> HTTPボディ⇒MultiValueMap変換が失敗することを確認する。
     * </ul>
     */
    @Test
    public void testRSCL0206001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpConverter6"));

        // 入力条件設定
        webDriverOperations.overrideText(id("name"), "test0206");
        webDriverOperations.overrideText(id("age"), "20");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // システムエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("System Error!"));
        // 期待するエラーが発生しているかエラーログチェック
        dbLogAssertOperations.assertContainsByRegexStackTrace(
                ".*org.springframework.web.HttpMediaTypeNotSupportedException",
                greaterThanOrEqualTo(1L));

    }

    /**
     * Overview関連 - HttpMessageConverterの設定確認
     * <ul>
     * <li>RSCL0207001：HttpMessageConverter（MappingJackson2HttpMessageConverter）</li>
     * <li>対象API：/api/v1/users</li>
     * <li>対象メソッド：POST</li> JavaBean ⇔ JSON変換を確認する。
     * </ul>
     */
    @Test
    public void testRSCL0207001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpConverter7"));

        // 入力条件設定
        webDriverOperations.overrideText(id("name"), "test0209");
        webDriverOperations.overrideText(id("age"), "20");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("test0209_created"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // Content-Type、Acceptヘッダのログを確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController",
                "Call API:create");
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController",
                "Content-Type : " + MediaType.APPLICATION_JSON_VALUE);
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*ConverterRestTemplateServiceImpl.*",
                ".*Content-Type : " + MediaType.APPLICATION_JSON_VALUE);
    }

    /**
     * Overview関連 - HttpMessageConverterの設定確認
     * <ul>
     * <li>RSCL0208001：HttpMessageConverter（Jaxb2RootElementHttpMessageConverter）</li>
     * <li>対象API：/api/v1/users</li>
     * <li>対象メソッド：POST</li> JavaBean ⇔ XML変換を確認する。
     * </ul>
     */
    @Test
    public void testRSCL0208001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpConverter8"));

        // 入力条件設定
        webDriverOperations.overrideText(id("name"), "test0211");
        webDriverOperations.overrideText(id("age"), "20");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("test0211_created"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // Content-Type、Acceptヘッダのログを確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController",
                "Call API:create");
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController",
                "Content-Type : " + MediaType.APPLICATION_XML_VALUE);
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*ConverterRestTemplateServiceImpl.*",
                ".*Content-Type : " + MediaType.APPLICATION_XML_VALUE);
    }

    // HowToUse関連のテスト

    /**
     * HowToUse関連 - BaseURLの設定確認
     * <ul>
     * <li>RSCL0301001：BaseURL（相対URL）</li>
     * <li>対象API：/api/v1/users/single</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL0301001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpBaseUrl1"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("test"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController",
                "Call API:getSingle");
    }

    /**
     * HowToUse関連 - BaseURLの設定確認
     * <ul>
     * <li>RSCL0302001：BaseURL（絶対URL）</li>
     * <li>対象API：/api/v1/users/single</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL0302001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpBaseUrl2"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("test2"));
        assertThat(webDriverOperations.getText(id("userAge")), is("25"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPI2Controller",
                "Call API:getSingle");
    }

    /**
     * HowToUse関連 - HTTPメソッドの設定確認
     * <ul>
     * <li>RSCL0401001：GET送信-単一件取得（正常）</li>
     * <li>対象API：/api/v1/users/{id}</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL0401001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpGet"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("test_00001"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController",
                "Call API:getUser");
    }

    /**
     * HowToUse関連 - HTTPメソッドの設定確認
     * <ul>
     * <li>RSCL0402001：GET送信-複数件取得（正常）</li>
     * <li>対象API：/api/v1/users</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL0402001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpGetList"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")),
                is("RESTクライアント処理結果【ユーザリスト情報出力】"));
        assertThat(webDriverOperations.getText(id("userListSize")), is("5"));
        assertThat(webDriverOperations.getText(id("name1")), is("test1"));
        assertThat(webDriverOperations.getText(id("age1")), is("21"));
        assertThat(webDriverOperations.getText(id("name2")), is("test2"));
        assertThat(webDriverOperations.getText(id("age2")), is("22"));
        assertThat(webDriverOperations.getText(id("name3")), is("test3"));
        assertThat(webDriverOperations.getText(id("age3")), is("23"));
        assertThat(webDriverOperations.getText(id("name4")), is("test4"));
        assertThat(webDriverOperations.getText(id("age4")), is("24"));
        assertThat(webDriverOperations.getText(id("name5")), is("test5"));
        assertThat(webDriverOperations.getText(id("age5")), is("25"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController", "Call API:list");
    }

    /**
     * HowToUse関連 - HTTPメソッドの設定確認
     * <ul>
     * <li>RSCL0403001：POST送信（正常）</li>
     * <li>対象API：/api/v1/users</li>
     * <li>対象メソッド：POST</li>
     * </ul>
     */
    @Test
    public void testRSCL0403001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpPost"));

        // 入力条件設定
        webDriverOperations.overrideText(id("name"), "test0403");
        webDriverOperations.overrideText(id("age"), "20");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("test0403_created"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController",
                "Call API:create");
    }

    /**
     * HowToUse関連 - HTTPメソッドの設定確認
     * <ul>
     * <li>RSCL0404001：PUT送信（正常）</li>
     * <li>対象API：/api/v1/users</li>
     * <li>対象メソッド：PUT</li>
     * </ul>
     */
    @Test
    public void testRSCL0404001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpPut"));

        // 入力条件設定
        webDriverOperations.overrideText(id("name"), "test0404");
        webDriverOperations.overrideText(id("age"), "20");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("test0404_put"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController", "Call API:put");
    }

    /**
     * HowToUse関連 - HTTPメソッドの設定確認
     * <ul>
     * <li>RSCL0405001：PATCH送信（正常）</li>
     * <li>対象API：/api/v1/users</li>
     * <li>対象メソッド：PATCH</li>
     * </ul>
     */
    @Test
    public void testRSCL0405001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpPatch"));

        // 入力条件設定
        webDriverOperations.overrideText(id("name"), "test0405");
        webDriverOperations.overrideText(id("age"), "20");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("test0405_patch"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController",
                "Call API:patch");
    }

    /**
     * HowToUse関連 - HTTPメソッドの設定確認
     * <ul>
     * <li>RSCL0406001：DELETE送信（正常）</li>
     * <li>対象API：/api/v1/users</li>
     * <li>対象メソッド：DELETE</li>
     * </ul>
     */
    @Test
    public void testRSCL0406001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpDelete"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController",
                "Call API:delete:00001");
    }

    /**
     * HowToUse関連 - HTTPメソッドの設定確認
     * <ul>
     * <li>RSCL0407001：HEAD送信（正常）</li>
     * <li>対象API：/api/v1/users</li>
     * <li>対象メソッド：HEAD</li>
     * </ul>
     */
    @Test
    public void testRSCL0407001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpHead"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController",
                "Call API:head:00001");
    }

    /**
     * HowToUse関連 - エラーハンドリングの設定確認
     * <ul>
     * <li>RSCL0501001：エラーハンドリング（2xx系）</li>
     * <li>対象API：/api/v1/exception/{status}</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL0501001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpErrorHandle1"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("exceptionTest"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*ExceptionAPIController",
                "Call API:exception:200");
        // 処理結果の確認
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*ErrorHandleRestTemplateServiceImpl.*", "Success:name:exceptionTest, age:20");
    }

    /**
     * HowToUse関連 - エラーハンドリングの設定確認
     * <ul>
     * <li>RSCL0502001：エラーハンドリング（4xx系）</li>
     * <li>対象API：/api/v1/exception/{status}</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL0502001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpErrorHandle2"));

        // システムエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("System Error!"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*ExceptionAPIController",
                "Call API:exception:404");

        // エラーログの確認
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*ErrorHandleRestTemplateServiceImpl.*", "Client Error:httpStatus:404.*");
        dbLogAssertOperations.assertContainsByRegexStackTrace(
                ".*org.springframework.web.client.HttpClientErrorException.*404.*",
                greaterThanOrEqualTo(1L));

    }

    /**
     * HowToUse関連 - エラーハンドリングの設定確認
     * <ul>
     * <li>RSCL0503001：エラーハンドリング（5xx系）</li>
     * <li>対象API：/api/v1/exception/{status}</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL0503001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpErrorHandle3"));

        // システムエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("System Error!"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*ExceptionAPIController",
                "Call API:exception:500");

        // エラーログの確認
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*ErrorHandleRestTemplateServiceImpl.*", "Server Error:httpStatus:500.*");
        dbLogAssertOperations.assertContainsByRegexStackTrace(
                ".*org.springframework.web.client.HttpServerErrorException.*500.*",
                greaterThanOrEqualTo(1L));
    }

    /**
     * HowToUse関連 - エラーハンドリングの設定確認
     * <ul>
     * <li>RSCL0504001：エラーハンドリング（Unknown）</li>
     * <li>対象API：/api/v1/exception/{status}</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL0504001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpErrorHandle4"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("exceptionTest"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*ExceptionAPIController",
                "Call API:exception:901");
        // 処理結果の確認
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*ErrorHandleRestTemplateServiceImpl.*", "Success:name:exceptionTest, age:20");
    }

    /**
     * HowToUse関連 - エラーハンドリングの設定確認
     * <ul>
     * <li>RSCL0505001：エラーリトライ（成功）</li>
     * <li>対象API：/api/v1/exception/retry/{throwSuccessRecord}</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL0505001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpErrorHandle5"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("test"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*ExceptionAPIController",
                "Call API:retry:0");
        dbLogAssertOperations.assertContainsByRegexMessage("..*ExceptionAPIController",
                "Call API:retry:1");
        dbLogAssertOperations.assertContainsByRegexMessage("..*ExceptionAPIController",
                "Call API:retry:2");

        // 処理結果の確認(リトライ回数)
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*ErrorHandleRestTemplateServiceImpl.*",
                "Success: name:test, age:20, retryCount = 2");
    }

    /**
     * HowToUse関連 - エラーハンドリングの設定確認
     * <ul>
     * <li>RSCL0506001：エラーリトライ（失敗）</li>
     * <li>対象API：/api/v1/exception/retry/{throwSuccessRecord}</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL0506001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpErrorHandle6"));

        // システムエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("System Error!"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*ExceptionAPIController",
                "Call API:retry:0");
        dbLogAssertOperations.assertContainsByRegexMessage("..*ExceptionAPIController",
                "Call API:retry:1");
        dbLogAssertOperations.assertContainsByRegexMessage("..*ExceptionAPIController",
                "Call API:retry:2");

        // エラーログの確認
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*ErrorHandleRestTemplateServiceImpl.*", "Max retry count reached");
        dbLogAssertOperations.assertContainsByRegexStackTrace(
                ".*org.springframework.web.client.HttpServerErrorException.*503.*",
                greaterThanOrEqualTo(1L));

    }

    /**
     * HowToUse関連 - エラーハンドリングの設定確認
     * <ul>
     * <li>RSCL0507001：ステータスコードハンドリング（2xx系）</li>
     * <li>対象API：/api/v1/exception/{status}</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL0507001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpStatusHandle1"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("exceptionTest"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*ExceptionAPIController",
                "Call API:exception:200");
        // 処理結果の確認
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*ErrorHandleRestTemplateServiceImpl.*",
                "Success:name:exceptionTest, age:20, httpStatus:200");
    }

    /**
     * HowToUse関連 - エラーハンドリングの設定確認
     * <ul>
     * <li>RSCL0508001：ステータスコードハンドリング（4xx系）</li>
     * <li>対象API：/api/v1/exception/{status}</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL0508001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpStatusHandle2"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is(emptyString()));
        assertThat(webDriverOperations.getText(id("userAge")), is(emptyString()));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*ExceptionAPIController",
                "Call API:exception:404");
        // 処理結果の確認
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*ErrorHandleRestTemplateServiceImpl.*", "Client Error:httpStatus:404");
    }

    /**
     * HowToUse関連 - エラーハンドリングの設定確認
     * <ul>
     * <li>RSCL0509001：ステータスコードハンドリング（5xx系）</li>
     * <li>対象API：/api/v1/exception/{status}</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL0509001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpStatusHandle3"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is(emptyString()));
        assertThat(webDriverOperations.getText(id("userAge")), is(emptyString()));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*ExceptionAPIController",
                "Call API:exception:500");
        // 処理結果の確認
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*ErrorHandleRestTemplateServiceImpl.*", "Server Error:httpStatus:500");
    }

    /**
     * HowToUse関連 - エラーハンドリングの設定確認
     * <ul>
     * <li>RSCL0510001：ステータスコードハンドリング（Unknown）</li>
     * <li>対象API：/api/v1/exception/{status}</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL0510001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpStatusHandle4"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("exceptionTest"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*ExceptionAPIController",
                "Call API:exception:901");
        // 処理結果の確認
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*ErrorHandleRestTemplateServiceImpl.*",
                "Unknown:name:exceptionTest, age:20, httpStatus:901");
    }

    /**
     * HowToUse関連 - エラーハンドリングの設定確認
     * <ul>
     * <li>RSCL0511001：ステータスコードハンドリング：NoOpResponseErrorHandler使用（2xx系）</li>
     * <li>対象API：/api/v1/exception/{status}</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL0511001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpStatusHandle5"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("exceptionTest"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*ExceptionAPIController",
                "Call API:exception:200");
        // 処理結果の確認
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*ErrorHandleRestTemplateServiceImpl.*",
                "NoOp Success:name:exceptionTest, age:20, httpStatus:200");
    }

    /**
     * HowToUse関連 - エラーハンドリングの設定確認
     * <ul>
     * <li>RSCL0512001：ステータスコードハンドリング：NoOpResponseErrorHandler使用（4xx系）の確認</li>
     * <li>対象API：/api/v1/exception/{status}</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL0512001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpStatusHandle6"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is(emptyString()));
        assertThat(webDriverOperations.getText(id("userAge")), is(emptyString()));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*ExceptionAPIController",
                "Call API:exception:404");
        // 処理結果の確認
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*ErrorHandleRestTemplateServiceImpl.*", "NoOp Client Error:httpStatus:404");
    }

    /**
     * HowToUse関連 - エラーハンドリングの設定確認
     * <ul>
     * <li>RSCL0513001：ステータスコードハンドリング：NoOpResponseErrorHandler使用（5xx系）の確認</li>
     * <li>対象API：/api/v1/exception/{status}</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL0513001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpStatusHandle7"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is(emptyString()));
        assertThat(webDriverOperations.getText(id("userAge")), is(emptyString()));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*ExceptionAPIController",
                "Call API:exception:500");
        // 処理結果の確認
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*ErrorHandleRestTemplateServiceImpl.*", "NoOp Server Error:httpStatus:500");
    }

    /**
     * HowToUse関連 - エラーハンドリングの設定確認
     * <ul>
     * <li>RSCL0514001：ステータスコードハンドリング：NoOpResponseErrorHandler使用（Unknown）の確認</li>
     * <li>対象API：/api/v1/exception/{status}</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL0514001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpStatusHandle8"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("exceptionTest"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*ExceptionAPIController",
                "Call API:exception:901");
        // 処理結果の確認
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*ErrorHandleRestTemplateServiceImpl.*",
                "NoOp Unknown:name:exceptionTest, age:20, httpStatus:901");
    }

    /**
     * HowToUse関連 - エラーハンドリングの設定確認
     * <ul>
     * <li>RSCL0515001：ステータスコードリトライ（成功）</li>
     * <li>対象API：/api/v1/exception/retry/{throwSuccessRecord}</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL0515001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpStatusHandle9"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("test"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*ExceptionAPIController",
                "Call API:retry:0");
        dbLogAssertOperations.assertContainsByRegexMessage("..*ExceptionAPIController",
                "Call API:retry:1");
        dbLogAssertOperations.assertContainsByRegexMessage("..*ExceptionAPIController",
                "Call API:retry:2");

        // 処理結果の確認(リトライ回数)
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*ErrorHandleRestTemplateServiceImpl.*",
                "Success: name:test, age:20, retryCount = 2");
    }

    /**
     * HowToUse関連 - エラーハンドリングの設定確認
     * <ul>
     * <li>RSCL0516001：エラーリトライ（失敗）</li>
     * <li>対象API：/api/v1/exception/retry/{throwSuccessRecord}</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL0516001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpStatusHandle10"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is(emptyString()));
        assertThat(webDriverOperations.getText(id("userAge")), is(emptyString()));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*ExceptionAPIController",
                "Call API:retry:0");
        dbLogAssertOperations.assertContainsByRegexMessage("..*ExceptionAPIController",
                "Call API:retry:1");
        dbLogAssertOperations.assertContainsByRegexMessage("..*ExceptionAPIController",
                "Call API:retry:2");

        // エラーログの確認
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*ErrorHandleRestTemplateServiceImpl.*", "Max retry count reached");
    }

    /**
     * HowToUse関連 - 共通リクエストヘッダの設定確認
     * <ul>
     * <li>RSCL0601001：共通リクエストヘッダーの設定</li>
     * <li>対象API：/api/v1/headers/custom</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL0601001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpCommonRequestHeader"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("test"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*RequestHeaderAPIController",
                "Call API:customHeader");

        // リクエストヘッダの確認
        dbLogAssertOperations.assertContainsByRegexMessage("..*RequestHeaderAPIController",
                "Custom-Header: CustomHeaderValue");
    }

    /**
     * HowToUse関連 - 認証の設定確認
     * <ul>
     * <li>RSCL0701001：認証成功（Basic）</li>
     * <li>対象API：/api/v1/auth/basic</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL0701001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpAuth1"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("test"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*AuthAPIController",
                "Call API:basic");

        // リクエストヘッダの確認
        dbLogAssertOperations.assertContainsByRegexMessage("..*AuthAPIController",
                "Authorization: Basic .*");
    }

    /**
     * HowToUse関連 - 認証の設定確認
     * <ul>
     * <li>RSCL0702001：認証失敗（Basic）</li>
     * <li>対象API：/api/v1/auth/basic</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL0702001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpAuth2"));

        // システムエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("System Error!"));

        // エラーログの確認
        dbLogAssertOperations.assertContainsByRegexStackTrace(
                ".*org.springframework.web.client.HttpClientErrorException.*401.*",
                greaterThanOrEqualTo(1L));
    }

    /**
     * HowToUse関連 - 認証の設定確認
     * <ul>
     * <li>RSCL0703001：認証未設定（Basic）</li>
     * <li>対象API：/api/v1/auth/basic</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL0703001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpAuth3"));

        // システムエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("System Error!"));

        // エラーログの確認
        dbLogAssertOperations.assertContainsByRegexStackTrace(
                ".*org.springframework.web.client.HttpClientErrorException.*401.*",
                greaterThanOrEqualTo(1L));
    }

    /**
     * HowToUse関連 - HttpComponentsClientHttpRequestFactoryのタイムアウトの設定確認
     * <ul>
     * <li>RSCL0801001：Timeout（Connect Timeout）</li>
     * <li>対象API：/api/v1/timeout/readTimeout</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL0801001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpTimeoutConnectTimeout"));

        // システムエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("System Error!"));

        // エラーログの確認
        dbLogAssertOperations.assertContainsByRegexStackTrace(
                ".*org.springframework.web.client.ResourceAccessException.*Connect timed out.*",
                greaterThanOrEqualTo(1L));
        dbLogAssertOperations.assertContainsByRegexStackTrace(
                ".*Caused by: org.apache.hc.client5.http.ConnectTimeoutException.*",
                greaterThanOrEqualTo(1L));

    }

    /**
     * HowToUse関連 - HttpComponentsClientHttpRequestFactoryのタイムアウトの設定確認
     * <ul>
     * <li>RSCL0801001：Timeout（Read Timeout）</li>
     * <li>対象API：/api/v1/timeout/readTimeout</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL0802001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpTimeoutReadTimeout"));

        // システムエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("System Error!"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*TimeoutAPIController",
                "Call API:readTimeout");

        // エラーログの確認
        dbLogAssertOperations.assertContainsByRegexStackTrace(
                ".*org.springframework.web.client.ResourceAccessException.*Read timed out.*",
                greaterThanOrEqualTo(1L));
        dbLogAssertOperations.assertContainsByRegexStackTrace(
                ".*Caused by: java.net.SocketTimeoutException.*", greaterThanOrEqualTo(1L));

    }

    /**
     * HowToUse関連 - ファイルアップロードの確認
     * <ul>
     * <li>RSCL0901001：ファイルアップロード</li>
     * <li>対象API：/api/v1/users/upload</li>
     * <li>対象メソッド：POST</li>
     * </ul>
     */
    @Test
    public void testRSCL0901001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpFileUpload"));

        // 送信ボタン押下
        webDriverOperations.click(id("upload"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("uploadTest"));
        assertThat(webDriverOperations.getText(id("userAge")), is("31"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController",
                "Call API:upload");
    }

    /**
     * HowToUse関連 - ファイルダウンロードの確認
     * <ul>
     * <li>RSCL1001001：ファイルダウンロード（byte）</li>
     * <li>対象API：/api/v1/users/download</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL1001001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpFileDownload1"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")),
                is("RESTクライアント処理結果【ユーザリスト情報出力】"));
        assertThat(webDriverOperations.getText(id("userListSize")), is("1000"));
        // 最初と最後のデータのみ確認
        assertThat(webDriverOperations.getText(id("name1")), is("test0"));
        assertThat(webDriverOperations.getText(id("age1")), is("30"));
        assertThat(webDriverOperations.getText(id("name1000")), is("test999"));
        assertThat(webDriverOperations.getText(id("age1000")), is("30"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController",
                "Call API:download");
    }

    /**
     * HowToUse関連 - ファイルダウンロードの確認
     * <ul>
     * <li>RSCL1002001：ファイルダウンロード（Resource）</li>
     * <li>対象API：/api/v1/users/download</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL1002001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpFileDownload2"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")),
                is("RESTクライアント処理結果【ユーザリスト情報出力】"));
        assertThat(webDriverOperations.getText(id("userListSize")), is("1000"));
        // 最初と最後のデータのみ確認
        assertThat(webDriverOperations.getText(id("name1")), is("test0"));
        assertThat(webDriverOperations.getText(id("age1")), is("30"));
        assertThat(webDriverOperations.getText(id("name1000")), is("test999"));
        assertThat(webDriverOperations.getText(id("age1000")), is("30"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController",
                "Call API:download");
    }

    /**
     * HowToUse関連 - ファイルダウンロードの確認
     * <ul>
     * <li>RSCL1003001：ファイルダウンロード（InputStream：正常）</li>
     * <li>対象API：/api/v1/users/download</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL1003001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpFileDownload3"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")),
                is("RESTクライアント処理結果【ユーザリスト情報出力】"));
        assertThat(webDriverOperations.getText(id("userListSize")), is("1000"));
        // 最初と最後のデータのみ確認
        assertThat(webDriverOperations.getText(id("name1")), is("test0"));
        assertThat(webDriverOperations.getText(id("age1")), is("30"));
        assertThat(webDriverOperations.getText(id("name1000")), is("test999"));
        assertThat(webDriverOperations.getText(id("age1000")), is("30"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController",
                "Call API:download");
    }

    /**
     * HowToUse関連 - ファイルダウンロードの確認
     * <ul>
     * <li>RSCL1004001：ファイルダウンロード（InputStream：エラー）</li>
     * <li>対象API：/api/v1/users/download/{statusCode}</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL1004001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpFileDownload4"));

        // システムエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("System Error!"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController",
                "Call API:download:404");

        // エラーログの確認
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*DownloadRestTemplateServiceImpl.*", "Download Error.");
        dbLogAssertOperations.assertContainsByRegexStackTrace(
                ".*org.springframework.web.client.HttpClientErrorException.*404.*",
                greaterThanOrEqualTo(1L));
    }

    /**
     * HowToUse関連 - ファイルダウンロードの確認
     * <ul>
     * <li>RSCL1005001：レスポンス情報取得（正常）</li>
     * <li>対象API：/api/v1/users/download</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL1005001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpFileDownload5"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")),
                is("RESTクライアント処理結果【ユーザリスト情報出力】"));
        assertThat(webDriverOperations.getText(id("userListSize")), is("1000"));
        // 最初と最後のデータのみ確認
        assertThat(webDriverOperations.getText(id("name1")), is("test0"));
        assertThat(webDriverOperations.getText(id("age1")), is("30"));
        assertThat(webDriverOperations.getText(id("name1000")), is("test999"));
        assertThat(webDriverOperations.getText(id("age1000")), is("30"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController",
                "Call API:download");

        // レスポンスの取得確認
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*DownloadRestTemplateServiceImpl.*", "HttpStatus: 200");
    }

    /**
     * HowToUse関連 - ファイルダウンロードの確認
     * <ul>
     * <li>RSCL1006001：レスポンス情報取得（エラー）</li>
     * <li>対象API：/api/v1/users/download/{statusCode}</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL1006001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpFileDownload6"));

        // システムエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("System Error!"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController",
                "Call API:download:404");

        // エラーログの確認
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*DownloadRestTemplateServiceImpl.*", "Download Error.");
        dbLogAssertOperations.assertContainsByRegexStackTrace(
                ".*org.springframework.web.client.HttpClientErrorException.*404.*",
                greaterThanOrEqualTo(1L));
    }

    // HowToExtends関連

    /**
     * HowToExtends関連 - レスポンス取得、リクエスト設定
     * <ul>
     * <li>RSCL1101001：レスポンス情報取得</li>
     * <li>対象API：/api/v1/users/single</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL1101001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpResponse"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【レスポンス情報出力】"));
        assertThat(webDriverOperations.getText(id("httpStatus")), is("200"));
        assertThat(webDriverOperations.getText(id("responseHeader")), is(not(emptyString())));
        assertThat(webDriverOperations.getText(id("responseBody")), is("User [name=test, age=20]"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController",
                "Call API:getSingle");
    }

    /**
     * HowToExtends関連 - レスポンス取得、リクエスト設定
     * <ul>
     * <li>RSCL1102001：リクエスト情報設定</li>
     * <li>対象API：/api/v1/headers/custom</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL1102001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpRequest"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【レスポンス情報出力】"));
        assertThat(webDriverOperations.getText(id("httpStatus")), is("200"));
        assertThat(webDriverOperations.getText(id("responseHeader")), is(not(emptyString())));
        assertThat(webDriverOperations.getText(id("responseBody")), is("User [name=test, age=20]"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*RequestHeaderAPIController",
                "Call API:customHeader");

        // リクエストヘッダの確認
        dbLogAssertOperations.assertContainsByRegexMessage("..*RequestHeaderAPIController",
                "Custom-Header: Custom-Value");
    }

    /**
     * HowToExtends関連 - 個別リクエストヘッダ
     * <ul>
     * <li>RSCL1201001：リクエストヘッダーの設定（Content-Type）</li>
     * <li>対象API：/api/v1/headers/contenttype</li>
     * <li>対象メソッド：POST</li>
     * </ul>
     */
    @Test
    public void testRSCL1201001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpRequestHeader1"));

        // 入力条件設定
        webDriverOperations.overrideText(id("name"), "test1201");
        webDriverOperations.overrideText(id("age"), "35");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("test1201_created"));
        assertThat(webDriverOperations.getText(id("userAge")), is("35"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*RequestHeaderAPIController",
                "Call API:contentTypeHeader");

        // リクエストヘッダの確認
        dbLogAssertOperations.assertContainsByRegexMessage("..*RequestHeaderAPIController",
                "Content-Type: " + MediaType.APPLICATION_JSON_VALUE);
    }

    /**
     * HowToExtends関連 - 個別リクエストヘッダ
     * <ul>
     * <li>RSCL1202001：リクエストヘッダーの設定（Accept）</li>
     * <li>対象API：/api/v1/headers/accept</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL1202001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpRequestHeader2"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("test"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*RequestHeaderAPIController",
                "Call API:acceptHeader");

        // リクエストヘッダの確認
        dbLogAssertOperations.assertContainsByRegexMessage("..*RequestHeaderAPIController",
                "Accept: " + MediaType.APPLICATION_JSON_VALUE);
    }

    /**
     * HowToExtends関連 - 個別リクエストヘッダ
     * <ul>
     * <li>RSCL1203001：リクエストヘッダーの設定（Custom）</li>
     * <li>対象API：/api/v1/headers/custom</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL1203001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpRequestHeader3"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("test"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*RequestHeaderAPIController",
                "Call API:customHeader");

        // リクエストヘッダの確認
        dbLogAssertOperations.assertContainsByRegexMessage("..*RequestHeaderAPIController",
                "Custom-Header: CustomValue");
    }

    /**
     * HowToExtends関連 - 埋め込みパラメータ
     * <ul>
     * <li>RSCL1301001：埋め込みパラメータの設定（LocalDate⇒String）</li>
     * <li>対象API：/api/v1/orders/{orderDate}</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL1301001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpUriVariables"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【注文情報出力】"));
        assertThat(webDriverOperations.getText(id("orderId")), is("00001"));
        assertThat(webDriverOperations.getText(id("orderDate")), is("20250101"));
        assertThat(webDriverOperations.getText(id("orderItem")), is("Sample Item"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*OrderAPIController",
                "Call API:getOrderByDate");

        // 埋め込みパラメータの確認
        dbLogAssertOperations.assertContainsByRegexMessage("..*OrderAPIController",
                "orderDate:20250101");

        // 変換中のログを確認
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*CustomUriBuilderFactory.*", "\\[CustomUriBuilder\\] before \\[2025-01-01\\]");
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*CustomUriBuilderFactory.*", "\\[CustomUriBuilder\\] after \\[20250101\\]");
    }

    /**
     * HowToExtends関連 - SSL自己署名証明書
     * <ul>
     * <li>RSCL1401001：HTTPSを要求するAPIへアクセスする設定</li>
     * <li>対象API：https://localhost:8991/api/v1/rscl1（0秒待機）</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL1401001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpSslSelfSigned1"));

        // 送信ボタン押下
        webDriverOperations.click(id("set"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));
    }

    // HowToExtends関連 - SSL自己署名証明書の1402001、1403001はMultiBrowserTestで実施

    /**
     * HowToExtends関連 - SSL自己署名証明書
     * <ul>
     * <li>RSCL1404001：setConnectTimeoutメソッドの動作確認</li>
     * <li>対象API：https://111.111.111.111:8080/api/v1/rscl1（URLが存在しない）</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL1404001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpSslSelfSigned7"));

        // 送信ボタン押下
        webDriverOperations.click(id("set"));

        // データ反映までの待ち時間
        webDriverOperations.waitForDisplayed(textToBe(By.xpath("//h1"), "System Error!"));

        // システムエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("System Error!"));
        // 期待するエラーが発生しているかエラーログチェック
        dbLogAssertOperations.assertContainsByRegexMessage(
                "org.terasoluna.gfw.common.exception.ExceptionLogger.Monitoring",
                "\\[e.sf.fw.9001\\].* failed: Connect timed out");
        dbLogAssertOperations.assertContainsByRegexStackTrace(
                ".*org.apache.hc.client5.http.ConnectTimeoutException.*Connect timed out",
                greaterThanOrEqualTo(1L));
    }

    /**
     * HowToExtends関連 - SSL自己署名証明書
     * <ul>
     * <li>RSCL1405001：setResponseTimeoutメソッドの動作確認</li>
     * <li>対象API：https://localhost:8991/api/v1/rscl4（20秒待機）</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL1405001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpSslSelfSigned4"));

        // 送信ボタン押下
        webDriverOperations.click(id("set"));

        // データ反映までの待ち時間
        webDriverOperations.setDefaultTimeoutForImplicitlyWait(30);
        webDriverOperations.waitForDisplayed(textToBe(By.xpath("//h1"), "System Error!"));
        webDriverOperations.setDefaultTimeoutForImplicitlyWait(10);

        // システムエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("System Error!"));
        // 期待するエラーが発生しているかエラーログチェック
        dbLogAssertOperations.assertContainsByRegexMessage(
                "org.terasoluna.gfw.common.exception.ExceptionLogger.Monitoring",
                "\\[e.sf.fw.9001\\].*: Read timed out");
        dbLogAssertOperations.assertContainsByRegexStackTrace(".*java.net.SocketTimeoutException.*",
                greaterThanOrEqualTo(1L));
    }

    /**
     * HowToExtends関連 - SSL自己署名証明書
     * <ul>
     * <li>RSCL1406001：setHandshakeTimeoutMillisecondsメソッドの動作確認</li>
     * <li>サーバ側でSSLハンドシェイクの処理を待ち続けてしまうため、
     * 再度テストを実施する場合はサーバ側がタイムアウトするのを待つか再起動する必要がある。</li>
     * <li>対象API：https://localhost:8994/api/v1/rscl1（20秒待機）</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL1406001() throws InterruptedException {

        // 正常に動作することを確認
        {
            // メニュー画面の操作
            webDriverOperations.click(id("restClient_jsp"));
            webDriverOperations.click(id("rstpSslSelfSigned8"));

            // 送信ボタン押下
            webDriverOperations.click(id("set"));

            // 確認画面に遷移したことをチェック
            assertThat(webDriverOperations.getText(id("screenTitle")),
                    is("RESTクライアント処理結果【ユーザ情報出力】"));
            // 出力内容をチェック
            assertThat(webDriverOperations.getText(id("userName")), is("test"));
            assertThat(webDriverOperations.getText(id("userAge")), is("20"));
        }

        // SoTimeoutによるタイムアウト
        {
            webDriverOperations.displayPage(getPackageRootUrl());

            // メニュー画面の操作
            webDriverOperations.click(id("restClient_jsp"));
            webDriverOperations.click(id("rstpSslSelfSigned9"));

            // 送信ボタン押下
            webDriverOperations.click(id("set"));

            // データ反映までの待ち時間
            webDriverOperations.waitForDisplayed(textToBe(By.xpath("//h1"), "System Error!"));

            // システムエラー画面に遷移
            assertThat(webDriverOperations.getTitle(), is("System Error!"));
            // 期待するエラーが発生しているかエラーログチェック
            dbLogAssertOperations.assertContainsByRegexMessage(
                    "org.terasoluna.gfw.common.exception.ExceptionLogger.Monitoring",
                    "\\[e.sf.fw.9001\\].*: Read timed out");
            dbLogAssertOperations.assertContainsByRegexStackTrace(
                    ".*org.apache.hc.client5.http.ConnectTimeoutException.*8994.*Read timed out",
                    greaterThanOrEqualTo(1L));
        }

    }

    /**
     * HowToExtends関連 - カスタムコンバータ
     * <ul>
     * <li>RSCL1601001：カスタムコンバータの設定</li>
     * <li>対象API：/api/v1/converter/csv</li>
     * <li>対象メソッド：GET</li> CSV⇒List<User>
     * </ul>
     */
    @Test
    public void testRSCL1601001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpCustomConverter1"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")),
                is("RESTクライアント処理結果【ユーザリスト情報出力】"));
        assertThat(webDriverOperations.getText(id("userListSize")), is("5"));
        assertThat(webDriverOperations.getText(id("name1")), is("testUser1"));
        assertThat(webDriverOperations.getText(id("age1")), is("30"));
        assertThat(webDriverOperations.getText(id("name2")), is("testUser2"));
        assertThat(webDriverOperations.getText(id("age2")), is("31"));
        assertThat(webDriverOperations.getText(id("name3")), is("testUser3"));
        assertThat(webDriverOperations.getText(id("age3")), is("32"));
        assertThat(webDriverOperations.getText(id("name4")), is("testUser4"));
        assertThat(webDriverOperations.getText(id("age4")), is("33"));
        assertThat(webDriverOperations.getText(id("name5")), is("testUser5"));
        assertThat(webDriverOperations.getText(id("age5")), is("34"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*ConverterAPIController",
                "Call API:csv");

        // リクエストヘッダの確認
        dbLogAssertOperations.assertContainsByRegexMessage("..*ConverterAPIController",
                "Accept: text/csv");
    }

    /**
     * HowToExtends関連 - カスタムコンバータ
     * <ul>
     * <li>RSCL1602001：カスタムコンバータの設定（application/json）</li>
     * <li>対象API：/api/v1/converter/json</li>
     * <li>対象メソッド：POST</li>
     * </ul>
     */
    @Test
    public void testRSCL1602001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpCustomConverter2"));

        // 入力条件設定
        webDriverOperations.overrideText(id("name"), "test1601");
        webDriverOperations.overrideText(id("age"), "33");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("test1601_created"));
        assertThat(webDriverOperations.getText(id("userAge")), is("33"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*ConverterAPIController",
                "Call API:postJson");

        // リクエストヘッダの確認
        dbLogAssertOperations.assertContainsByRegexMessage("..*ConverterAPIController",
                "Content-Type: " + MediaType.APPLICATION_JSON_VALUE);
        dbLogAssertOperations.assertContainsByRegexMessage("..*ConverterAPIController",
                "Accept: " + MediaType.APPLICATION_JSON_VALUE);

        // Converterが呼ばれたかの確認
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*LoggingMappingJackson2HttpMessageConverter.*",
                "writeInternal called: jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User");
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*LoggingMappingJackson2HttpMessageConverter.*",
                "read called: jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User");

    }

    /**
     * HowToExtends関連 - リクエスト送信前後処理
     * <ul>
     * <li>RSCL1701001：ロギングの設定</li>
     * <li>対象API：/api/v1/users</li>
     * <li>対象メソッド：POST</li>
     * </ul>
     */
    @Test
    public void testRSCL1701001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpInterceptors"));

        // 入力条件設定
        webDriverOperations.overrideText(id("name"), "test1701");
        webDriverOperations.overrideText(id("age"), "32");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("test1701_created"));
        assertThat(webDriverOperations.getText(id("userAge")), is("32"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController",
                "Call API:create");

        // Interceptorのログの確認
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*LoggingInterceptor.*", "Request Header .*");
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*LoggingInterceptor.*", "Request Body .*");
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*LoggingInterceptor.*", "Response Header .*");
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*LoggingInterceptor.*", "Response Status Code 200");
    }

    /**
     * HowToExtends関連 - リクエスト送信前後処理
     * <ul>
     * <li>RSCL1702001：インターセプターの実行順序</li>
     * <li>対象API：/api/v1/users/single</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL1702001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpInterceptors2"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("test"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController",
                "Call API:getSingle");

        // Interceptorの実行順序をログで確認
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*CustomOrder1Interceptor.*",
                "orderList: \\[CustomOrder1Interceptor Start!, CustomOrder2Interceptor Start!, CustomOrder2Interceptor End!, CustomOrder1Interceptor End!\\]");
    }

    // Appendix関連

    /**
     * Appendix関連 - RESTful URL
     * <ul>
     * <li>RSCL1801001：RESTful URLの設定（getForObject）</li>
     * <li>対象API：/api/v1/users/{userId}</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL1801001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpRestfulUrl1"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("test_0001"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController",
                "Call API:getUser");
        // 埋め込みパラメータの確認
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController", "id: 0001");
    }

    /**
     * Appendix関連 - RESTful URL
     * <ul>
     * <li>RSCL1802001：RESTful URLの設定（exchange）</li>
     * <li>対象API：/api/v1/users/{action}</li>
     * <li>対象メソッド：POST</li>
     * </ul>
     */
    @Test
    public void testRSCL1802001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpRestfulUrl2"));

        // 入力条件設定
        webDriverOperations.overrideText(id("name"), "test1802");
        webDriverOperations.overrideText(id("age"), "31");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("test1802_created"));
        assertThat(webDriverOperations.getText(id("userAge")), is("31"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController",
                "Call API:createParam");
        // 埋め込みパラメータの確認
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController",
                "Action: create");
    }

    /**
     * Appendix関連 - Proxy
     * <ul>
     * <li>RSCL1901001：Proxyの設定（HttpComponentsClientHttpRequestFactory）</li>
     * <li>対象API：/api/v1/users/single</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL1901001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpProxy1"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("test"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController",
                "Call API:getSingle");

        // レスポンスヘッダの確認
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*ProxyRestTemplateServiceImpl.*",
                "Proxy : Headers containsKey 'Pass-Internal-Proxy' is true");

        // Proxy経由の確認
        dbLogAssertOperations.assertContainsByRegexMessage("..*InternalProxyServlet",
                "Pass without authentication. The path is :.*");

    }

    /**
     * Appendix関連 - Proxy
     * <ul>
     * <li>RSCL1902001：Proxyの設定（ユーザ、パスワードあり-認証成功）</li>
     * <li>対象API：/api/v1/users/single</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL1902001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpProxy2"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("test2"));
        assertThat(webDriverOperations.getText(id("userAge")), is("21"));

        // 対象のAPIが呼び出されていることをログで確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage("..*UserAPIController",
                "Call API:getSingleProxyAuth");

        // レスポンスヘッダの確認
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*ProxyRestTemplateServiceImpl.*",
                "Proxy : Headers containsKey 'Pass-Internal-Proxy' is true");

        // Proxy経由の確認
        dbLogAssertOperations.assertContainsByRegexMessage("..*InternalProxyServlet",
                "Authentication succeeded for user: rsclproxyuser");
    }

    /**
     * Appendix関連 - Proxy
     * <ul>
     * <li>RSCL1903001：Proxyの設定（ユーザ、パスワードあり-認証失敗）</li>
     * <li>対象API：/api/v1/users/single</li>
     * <li>対象メソッド：GET</li>
     * </ul>
     */
    @Test
    public void testRSCL1903001() {
        // メニュー画面の操作
        webDriverOperations.click(id("restClient_jsp"));
        webDriverOperations.click(id("rstpProxy3"));

        // システムエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("System Error!"));

        // Proxy経由の確認
        dbLogAssertOperations.assertContainsByRegexMessage("..*InternalProxyServlet",
                "Authentication failed for user: rsclproxyuser");

        // エラーログの確認
        dbLogAssertOperations.assertContainsByRegexStackTrace(
                ".*org.springframework.web.client.HttpClientErrorException.*401.*",
                greaterThanOrEqualTo(1L));
    }
}
