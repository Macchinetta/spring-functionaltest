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
package jp.co.ntt.fw.spring.functionaltest.selenium.rscl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.openqa.selenium.By.*;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.IfProfileValue;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

//Thymeleaf版未実装のためJSPのみ実行
@IfProfileValue(name = "test.environment.view", values = { "jsp" })
public class RestClientTest extends FunctionTestSupport {

    // RSCL1302001アサート用
    @Value("${rscl.asyncRestTemplate.queueCapacity}")
    int queueCapacity;

    // RSCL1302001アサート用
    @Value("${rscl.asyncRestTemplate.maxPoolSize}")
    int maxPoolSize;

    /**
     * <ul>
     * <li>RSCL0101001 ： RestTemplateのgetForObjectメソッドを使用したGETの実装</li>
     * <li>RSCL1201001 ： RESTFulなURLをgetForObjectメソッドで使用する場合の実装</li>
     * </ul>
     */
    @Test
    public void testRSCL0101001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl0101001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "0101");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is(
                "test_0101"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));
    }

    /**
     * <ul>
     * <li>RestTemplateのgetForEntityメソッドを使用したGETの実装</li>
     * </ul>
     */
    @Test
    public void testRSCL0102001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl0102001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "0102");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is(
                "test_0102"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*", ".*RSCL0102001 : "
                        + MediaType.APPLICATION_XML.toString());
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*", ".*RSCL0102001 : "
                        + HttpStatus.OK.toString());
    }

    /**
     * <ul>
     * <li>RSCL0103001 ： RestTemplateのexchangeメソッドを使用したGETの実装</li>
     * <li>RSCL1202001 ： RESTFulなURLをexchangeメソッドで使用した場合の実装</li>
     * </ul>
     */
    @Test
    public void testRSCL0103001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl0103001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "0103");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is(
                "test_0103"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*", ".*RSCL0103001 : "
                        + HttpStatus.OK.toString());
    }

    /**
     * <ul>
     * <li>RestTemplateのexchangeメソッドを使用したGETの実装（ヘッダの設定あり）</li>
     * </ul>
     */
    @Test
    public void testRSCL0104001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl0104001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "0104");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is(
                "test_0104"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*", ".*RSCL0104001 : "
                        + MediaType.APPLICATION_JSON.toString());
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*", ".*RSCL0104001 : "
                        + HttpStatus.OK.toString());
    }

    /**
     * <ul>
     * <li>RSCL0105001 ： RestTemplateのexchangeメソッドを使用したGETの実装（複数レコード）</li>
     * <li>RSCL1101001 ： 任意のHttpMessageConverter（Jaxb2CollectionHttpMessageConverter）を登録する方法</li>
     * </ul>
     */
    @Test
    public void testRSCL0105001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl0105001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "collection");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【ユーザリスト情報出力】"));

        // 出力内容をチェック
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*", ".*RSCL0105001 : "
                        + HttpStatus.OK.toString());

        int count = 0;
        for (WebElement elements : webDriverOperations.getWebDriver()
                .findElements(By.id("name*"))) {
            count++;
            assertThat(elements.getAttribute("value"), is("test" + count));
            assertThat(webDriverOperations.getText(id("age" + count)), is("2"
                    + count));
        }
    }

    /**
     * <ul>
     * <li>RestTemplateのpostForObjectメソッドを使用したPOSTの実装</li>
     * </ul>
     */
    @Test
    public void testRSCL0106001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl0106001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "");
        webDriverOperations.overrideText(id("name"), "test0106");
        webDriverOperations.overrideText(id("age"), "20");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is(
                "test0106_created"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));
    }

    /**
     * <ul>
     * <li>RestTemplateのpostForEntityメソッドを使用したPOSTの実装</li>
     * </ul>
     */
    @Test
    public void testRSCL0107001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl0107001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "");
        webDriverOperations.overrideText(id("name"), "test0107");
        webDriverOperations.overrideText(id("age"), "20");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is(
                "test0107_created"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*", ".*RSCL0107001 : "
                        + MediaType.APPLICATION_XML.toString());
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*", ".*RSCL0107001 : "
                        + HttpStatus.OK.toString());

    }

    /**
     * <ul>
     * <li>RestTemplateのexchangeメソッドを使用したPOSTの実装</li>
     * </ul>
     */
    @Test
    public void testRSCL0108001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl0108001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "");
        webDriverOperations.overrideText(id("name"), "test0108");
        webDriverOperations.overrideText(id("age"), "20");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is(
                "test0108_created"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*", ".*RSCL0108001 : "
                        + HttpStatus.OK.toString());
    }

    /**
     * <ul>
     * <li>RestTemplateのexchangeメソッドを使用したPOSTの実装(ヘッダ設定あり)</li>
     * </ul>
     */
    @Test
    public void testRSCL0109001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl0109001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "");
        webDriverOperations.overrideText(id("name"), "test0109");
        webDriverOperations.overrideText(id("age"), "20");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is(
                "test0109_created"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*", ".*RSCL0109001 : "
                        + HttpStatus.OK.toString());
    }

    /**
     * <ul>
     * <li>RestTemplateのdeleteメソッドを使用したDELETEの実装</li>
     * </ul>
     */
    @Test
    public void testRSCL0110001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl0110001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "12345");

        // 削除ボタン押下
        webDriverOperations.click(id("delete"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果"));

        // 検証すべきものが無い。例外が発生しなければOK。

    }

    /**
     * <ul>
     * <li>RestTemplateのexchangeメソッドを使用したDELETEの実装</li>
     * </ul>
     */
    @Test
    public void testRSCL0111001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl0111001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "12345");

        // 削除ボタン押下
        webDriverOperations.click(id("delete"));

        // 出力内容をチェック
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*", ".*RSCL0111001 : "
                        + HttpStatus.NO_CONTENT.toString());
    }

    /**
     * <ul>
     * <li>RestTemplateのputメソッドを使用したPUTの実装</li>
     * </ul>
     */
    @Test
    public void testRSCL0112001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl0112001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "");
        webDriverOperations.overrideText(id("name"), "test0112");
        webDriverOperations.overrideText(id("age"), "20");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果"));

        // 検証すべきものが無い。例外が発生しなければOK。
    }

    /**
     * <ul>
     * <li>RestTemplateのexchangeメソッドを使用したPUTの実装</li>
     * </ul>
     */
    @Test
    public void testRSCL0113001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl0113001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "");
        webDriverOperations.overrideText(id("name"), "test0113");
        webDriverOperations.overrideText(id("age"), "20");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果"));

        // 出力内容をチェック
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*", ".*RSCL0113001 : "
                        + HttpStatus.OK.toString());
    }

    /**
     * <ul>
     * <li>RSCL0201001 : ByteArrayHttpMessageConverterを利用した送受信確認</li>
     * <li>RSCL1001001 : ファイルダウンロード</li> <br>
     * HTTPボディ⇔byte[]変換を確認する。
     * </ul>
     */
    @Test
    public void testRSCL0201001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl0201001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("message"), "send byte");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【文字リスト出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("strListSize")), is("2"));
        assertThat(webDriverOperations.getText(id("str1")), is("send byte"));
        assertThat(webDriverOperations.getText(id("str2")), is("_received"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*", ".*RSCL0201001 : "
                        + HttpStatus.OK.toString());
    }

    /**
     * <ul>
     * <li>StringHttpMessageConverterを利用した送受信確認</li> <br>
     * HTTPボディ⇔文字列変換を確認する。
     * </ul>
     */
    @Test
    public void testRSCL0202001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl0202001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("message"), "sendString");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【文字列出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("rcvString")), is(
                "sendString_received"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*", ".*RSCL0202001 : "
                        + MediaType.TEXT_PLAIN.toString());
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*", ".*RSCL0202001 : "
                        + HttpStatus.OK.toString());
    }

    /**
     * <ul>
     * <li>ResourceHttpMessageConverterを利用した送受信確認</li> <br>
     * HTTPボディ⇔Resource変換を確認する。
     * </ul>
     */
    @Test
    public void testRSCL0203001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl0203001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("message"), "send resource");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【文字リスト出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("strListSize")), is("2"));
        assertThat(webDriverOperations.getText(id("str1")), is(
                "send resource"));
        assertThat(webDriverOperations.getText(id("str2")), is("_received"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*", ".*RSCL0203001 : "
                        + HttpStatus.OK.toString());
    }

    /**
     * <ul>
     * <li>SourceHttpMessageConverterを利用した送受信確認</li> <br>
     * HTTPボディ⇔Source変換を確認する。
     * </ul>
     */
    @Test
    public void testRSCL0204001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl0204001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("name"), "test0204");
        webDriverOperations.overrideText(id("age"), "20");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is(
                "test0204_created"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*", ".*RSCL0204001 : "
                        + MediaType.APPLICATION_XML.toString());
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*", ".*RSCL0204001 : "
                        + HttpStatus.OK.toString());
    }

    /**
     * <ul>
     * <li>AllEncompassingFormHttpMessageConverterを利用した送受信の確認</li> <br>
     * （メディアタイプがapplication/x-www-form-urlencodedの場合） <br>
     * HTTPボディ⇔MultiValueMap変換を確認する。
     * </ul>
     */
    @Test
    public void testRSCL0205001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl0205001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("name"), "test0205");
        webDriverOperations.overrideText(id("age"), "20");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is(
                "test0205_received"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*", ".*RSCL0205001 : "
                        + MediaType.APPLICATION_FORM_URLENCODED.toString());
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*", ".*RSCL0205001 : "
                        + HttpStatus.OK.toString());
    }

    /**
     * <ul>
     * <li>AllEncompassingFormHttpMessageConverterを利用した送受信確認</li> <br>
     * MultiValueMap<String, Object>の送受信を確認する。
     * </ul>
     */
    @Ignore("現状、AllEncompassingFormHttpMessageConverterは、MultiValueMap<String, Object>の受信をサポートしていないため、試験不能。")
    public void testRSCL0206001() {
    }

    /**
     * <ul>
     * <li>AtomFeedHttpMessageConverterを利用した送受信確認</li> <br>
     * Feedの送受信を確認する。
     * </ul>
     */
    @Ignore("ガイドラインでは紹介に留めており、主要機能でもないため、試験は省略する。")
    public void testRSCL0207001() {
    }

    /**
     * <ul>
     * <li>RssChannelHttpMessageConverterを利用した送受信確認</li> <br>
     * Channelの送受信を確認する。
     * </ul>
     */
    @Ignore("ガイドラインでは紹介に留めており、主要機能でもないため、試験は省略する。")
    public void testRSCL0208001() {
    }

    /**
     * <ul>
     * <li>MappingJackson2HttpMessageConverterを利用した送受信確認</li> <br>
     * JavaBean ⇔ JSON変換を確認する。
     * </ul>
     */
    @Test
    public void testRSCL0209001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl0209001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("name"), "test0209");
        webDriverOperations.overrideText(id("age"), "20");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is(
                "test0209_created"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*", ".*RSCL0209001 : "
                        + MediaType.APPLICATION_JSON.toString());
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*", ".*RSCL0209001 : "
                        + HttpStatus.OK.toString());
    }

    /**
     * <ul>
     * <li>MappingJackson2XmlHttpMessageConverterを利用した送受信確認</li> <br>
     * JavaBeanの送受信を確認する。
     * </ul>
     */
    @Ignore("ガイドラインでは紹介に留めており、主要機能でもないため、試験は省略する。")
    public void testRSCL0210001() {
    }

    /**
     * <ul>
     * <li>Jaxb2RootElementHttpMessageConverterを利用した送受信確認</li> <br>
     * JavaBean ⇔ XML変換を確認する。
     * </ul>
     */
    @Test
    public void testRSCL0211001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl0211001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("name"), "test0211");
        webDriverOperations.overrideText(id("age"), "20");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is(
                "test0211_created"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*", ".*RSCL0211001 : "
                        + MediaType.APPLICATION_XML.toString());
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*", ".*RSCL0211001 : "
                        + HttpStatus.OK.toString());
    }

    /**
     * <ul>
     * <li>GsonHttpMessageConverterを利用した送受信確認</li> <br>
     * JavaBeanの送受信を確認する。
     * </ul>
     */
    @Ignore("ガイドラインでは紹介に留めており、主要機能でもないため、試験は省略する。")
    public void testRSCL0212001() {
    }

    /**
     * <ul>
     * <li>HTTPヘッダへのContext-Typeの設定</li>
     * </ul>
     */
    @Ignore("RSCL0201001の試験で確認可能であるため、テスト不要。")
    @Test
    public void testRSCL0301001() {
        testRSCL0201001();
    }

    /**
     * <ul>
     * <li>HTTPヘッダへのAcceptの設定</li>
     * </ul>
     */
    @Ignore("RSCL0201001の試験で確認可能であるため、テスト不要。")
    @Test
    public void testRSCL0302001() {
        testRSCL0201001();
    }

    /**
     * <ul>
     * <li>RSCL0303001 : 任意のヘッダー（Authenticationヘッダ）の設定</li>
     * <li>RSCL0801001 : 認証（Basic認証）を要求するAPIへアクセスする場合</li>
     * </ul>
     */
    @Test
    public void testRSCL0303001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl0303001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("username"), "rscluser");
        webDriverOperations.overrideText(id("password"), "password1234");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*", ".*RSCL0303001 : "
                        + HttpStatus.OK.toString());
    }

    /**
     * <ul>
     * <li>ステータスコード：正常系(2xx)が返却される場合の確認</li>
     * </ul>
     */
    @Ignore("RSCL0201001の試験で確認可能であるため、テスト不要。")
    @Test
    public void testRSCL0401001() {
        testRSCL0201001();
    }

    /**
     * <ul>
     * <li>DefaultResponseErrorHandlerの挙動確認（クライアントエラー系(4xx)の場合）</li> <br>
     * HttpClientErrorExceptionをキャッチすることの確認
     * </ul>
     */
    @Test
    public void testRSCL0402001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl0402001"));

        // 送信ボタン押下
        webDriverOperations.click(id("occur"));

        // 期待するエラーが発生しているかエラーログチェック
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(
                webDriverOperations.getXTrack(), null, "[e.sf.cmmn.9001]..*404",
                "org\\.springframework\\.web\\.client\\.HttpClientErrorException");
    }

    /**
     * <ul>
     * <li>DefaultResponseErrorHandlerの挙動確認（サーバエラー系(5xx)の場合）</li> <br>
     * HttpServerErrorExceptionをキャッチすることの確認
     * </ul>
     */
    @Test
    public void testRSCL0403001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl0403001"));

        // 送信ボタン押下
        webDriverOperations.click(id("occur"));

        // 期待するエラーが発生しているかエラーログチェック
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(
                webDriverOperations.getXTrack(), null, "[e.sf.cmmn.9001]..*500",
                "org\\.springframework\\.web\\.client\\.HttpServerErrorException");
    }

    /**
     * <ul>
     * <li>DefaultResponseErrorHandlerの挙動確認（未定義のコード(ユーザ定義のカスタムコード)の場合）</li> <br>
     * UnknownHttpStatusCodeExceptionをキャッチすることの確認
     * </ul>
     */
    @Test
    public void testRSCL0404001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl0404001"));

        // 送信ボタン押下
        webDriverOperations.click(id("occur"));

        // 期待するエラーが発生しているかログチェック
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(
                webDriverOperations.getXTrack(), null,
                "[e.sf.cmmn.9001]..*Unknown status code \\[901\\].*",
                "org\\.springframework\\.web\\.client\\.UnknownHttpStatusCodeException");
    }

    /**
     * <ul>
     * <li>個別に例外をハンドリングする場合の確認</li>
     * </ul>
     */
    @Test
    public void testRSCL0405001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl0405001"));

        // 送信ボタン押下
        webDriverOperations.click(id("occur"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*",
                ".*RSCL0405001 : HttpStatus = " + HttpStatus.OK.toString()
                        + ", RetryCount = 2");
    }

    /**
     * <ul>
     * <li>例外ハンドラの拡張の確認</li>
     * </ul>
     */
    @Test
    public void testRSCL0406001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl0406001"));

        // 送信ボタン押下
        webDriverOperations.click(id("occur"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*",
                ".*RSCL0406001 : HttpStatus = " + HttpStatus.OK.toString()
                        + ", RetryCount = 2");
    }

    /**
     * <ul>
     * <li>コネクションタイムアウトの場合</li>
     * </ul>
     */
    @Test
    public void testRSCL0501001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl0501001"));

        // 送信ボタン押下
        webDriverOperations.click(id("occur"));

        // 期待するエラーが発生しているかログチェック
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(
                webDriverOperations.getXTrack(), null,
                "I/O error on GET request for..*",
                "org\\.springframework\\.web\\.client\\.ResourceAccessException");
    }

    /**
     * <ul>
     * <li>リードタイムアウトの場合</li>
     * </ul>
     */
    @Test
    public void testRSCL0502001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl0502001"));

        // 送信ボタン押下
        webDriverOperations.click(id("occur"));

        // 期待するエラーが発生しているかログチェック
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(
                webDriverOperations.getXTrack(), null,
                "I/O error on GET request for..*",
                "org\\.springframework\\.web\\.client\\.ResourceAccessException");
    }

    /**
     * <ul>
     * <li>RSCL0601001 ： HTTPSを要求するAPIへアクセスする場合</li>
     * <li>RSCL0701001 : 自己署名証明書を使用する場合</li>
     * </ul>
     */
    @Test
    public void testRSCL0601001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl0601001"));

        // 送信ボタン押下
        webDriverOperations.click(id("set"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));
    }

    /**
     * <ul>
     * <li>ファイルアップロード</li>
     * </ul>
     */
    @Test
    public void testRSCL0901001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl0901001"));

        // 送信ボタン押下
        webDriverOperations.click(id("upload"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is(
                "uploadTest"));
        assertThat(webDriverOperations.getText(id("userAge")), is("31"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*", ".*RSCL0901001 : "
                        + HttpStatus.OK.toString());
    }

    /**
     * <ul>
     * <li>Jaxb2CollectionHttpMessageConverterの確認</li>
     * </ul>
     */
    @Ignore("RSCL0105001の試験で確認可能であるため、テスト不要。")
    @Test
    public void testRSCL1101001() {
        testRSCL0105001();
    }

    /**
     * <ul>
     * <li>RSCL1102001 : ClientHttpRequestInterceptorを使用したロギング</li>
     * <li>RSCL1103001 : ClientHttpRequestInterceptorを使用したBasic認証</li>
     * </ul>
     */
    @Test
    public void testRSCL1102001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl1102001"));

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // RESTAPI呼び出し後の各インターセプタの呼び出しカウント数は、RESTAPI呼び出し前の＋１のはず。
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*LoggingInterceptor.*",
                "LoggingInterceptor Called!");
    }

    /**
     * <ul>
     * <li>ClientHttpRequestInterceptorを使用したBasic認証で認証成功</li>
     * </ul>
     */
    @Ignore("RSCL1102001の試験で確認可能であるため、テスト不要。")
    @Test
    public void testRSCL1103001() {
        testRSCL1102001();
    }

    /**
     * <ul>
     * <li>ClientHttpRequestInterceptorを使用したBasic認証で認証失敗</li>
     * </ul>
     */
    @Test
    public void testRSCL1103002() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl1103002"));

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // システムエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("System Error!"));
        // 期待するエラーが発生しているかエラーログチェック
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(
                webDriverOperations.getXTrack(), null, "[e.sf.cmmn.9001]..*401",
                "org\\.springframework\\.web\\.client\\.HttpClientErrorException");
    }

    /**
     * <ul>
     * <li>ClientHttpRequestInterceptorを使用したサーバとの通信の閉塞制御</li>
     * </ul>
     */
    @Test
    public void testRSCL1104001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl1104001"));

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック

        // 期待するエラーが発生しているかログチェック
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(
                webDriverOperations.getXTrack(), null, ".*e\\.rc\\.fw\\.8001.*",
                "org\\.terasoluna\\.gfw.common\\.exception\\.BusinessException");
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*AccessCtrlInterceptor.*",
                "AccessCtrlInterceptor Obstruction File Called!");
    }

    /**
     * <ul>
     * <li>ClientHttpRequestInterceptorを使用した通信処理のリトライ</li>
     * </ul>
     */
    @Test
    public void testRSCL1105001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl1105001"));

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // RESTAPI呼び出し前の各インターセプタの呼び出しカウント数取得
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*AccessCtrlInterceptor.*",
                "AccessCtrlInterceptor Called!");
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*AccessCtrlInterceptor.*",
                "AccessCtrlInterceptor SuccessCount : 2");
    }

    /**
     * <ul>
     * <li>AsyncRestTemplateを使用した実装</li>
     * </ul>
     * @throws InterruptedException
     */
    @Test
    public void testRSCL1301001() throws InterruptedException {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl1301001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "1301");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is(
                "test_1301"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*", ".*RSCL1301001 : "
                        + HttpStatus.OK.toString());
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*",
                ".*RSCL1301001 : CountDownLatch = 0");
    }

    /**
     * AsyncRestTemplateを使用した際のスレッド数の制限の設定
     * @throws InterruptedException
     */
    @Test
    public void testRSCL1302001() throws InterruptedException {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl1302001"));

        int thredCapacity = queueCapacity + maxPoolSize;

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 出力内容をチェック
        // TaskRejectedExceptionが発生したか確認
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(
                webDriverOperations.getXTrack(), null,
                ".*java\\.util\\.concurrent\\.ThreadPoolExecutor..*did not accept task: "
                        + "org\\.springframework\\.http\\.client\\.SimpleBufferingAsyncClientHttpRequest..*",
                "org\\.springframework\\.core\\.task\\.TaskRejectedException");
        // RestAPIの呼び出し回数確認
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*",
                ".*RSCL1302001 : CallCount = ..*, SuccessCount = ..*, FinishedCount = ..*");
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*",
                ".*RSCL1302001 : CallCount - FinishedCount = " + thredCapacity
                        + ", SuccessCount - FinishedCount = " + thredCapacity);
    }

    /**
     * <ul>
     * <li>RSCL1303001 : 非同期リクエストの前とレスポンスが返却された後に共通処理を適用できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testRSCL1303001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl1303001"));

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertFalse(webDriverOperations.exists(className("alert-error")));
        assertThat(webDriverOperations.getText(id("userName")), is("test"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // 各InterceptorとCallbakの呼び出しをチェック
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*AsyncChainInterceptor.*",
                "AsyncChainInterceptor Called!");
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*AsyncLoggingInterceptor.*",
                "AsyncLoggingInterceptor Called!");
        dbLogAssertOperations.assertContainsByRegexMessage(
                "..*AsyncLoggingInterceptor.*",
                "Request Header \\{Accept=\\[application/xml, text/xml, "
                        + "application/json, application/\\*\\+xml, "
                        + "application/\\*\\+json\\], Content\\-Length=\\[0\\]\\}");
        dbLogAssertOperations.assertContainsByRegexMessage(
                "..*AsyncLoggingInterceptor.*", "Request Body ");
        dbLogAssertOperations.assertContainsByRegexMessage(
                "..*AsyncLoggingInterceptor.*", "onSuccess Called!");
        dbLogAssertOperations.assertContainsByRegexMessage(
                "..*AsyncChainInterceptor.*", "onSuccess Called!");
        dbLogAssertOperations.assertContainsByRegexMessage(
                "..*InterceptorsAsyncRestClientServiceImpl.*",
                "onSuccess Called!");
        // 呼び出し順をチェック
        Long chainInterceptEventId = dbLogAssertOperations
                .getLogEventIdByRegexMessage(null, "..*AsyncChainInterceptor.*",
                        "AsyncChainInterceptor Called!").get(0);
        Long loggingInterceptEventId = dbLogAssertOperations
                .getLogEventIdByRegexMessage(null,
                        "..*AsyncLoggingInterceptor.*",
                        "AsyncLoggingInterceptor Called!").get(0);
        assertThat(chainInterceptEventId, lessThan(loggingInterceptEventId));

        Long loggingCallbackEventId = dbLogAssertOperations
                .getLogEventIdByRegexMessage(null,
                        "..*AsyncLoggingInterceptor.*", "onSuccess Called!")
                .get(0);
        Long chainCallbackEventId = dbLogAssertOperations
                .getLogEventIdByRegexMessage(null, "..*AsyncChainInterceptor.*",
                        "onSuccess Called!").get(0);
        Long serviceCallbackEventId = dbLogAssertOperations
                .getLogEventIdByRegexMessage(null,
                        "..*InterceptorsAsyncRestClientServiceImpl.*",
                        "onSuccess Called!").get(0);
        assertThat(loggingCallbackEventId, lessThan(chainCallbackEventId));
        assertThat(chainCallbackEventId, lessThan(serviceCallbackEventId));
    }

    /**
     * <ul>
     * <li>RSCL1303002 : 非同期リクエストのステータスコードが正常系(2xx)以外の場合の挙動を確認する。</li>
     * </ul>
     */
    @Test
    public void testRSCL1303002() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl1303002"));

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(className("alert-error")), is(
                "user information can not be acquired"));
        assertThat(webDriverOperations.getText(id("userName")), is(""));
        assertThat(webDriverOperations.getText(id("userAge")), is(""));

        // 各InterceptorとCallbakの呼び出しをチェック
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*AsyncChainInterceptor.*",
                "AsyncChainInterceptor Called!");
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*AsyncLoggingInterceptor.*",
                "AsyncLoggingInterceptor Called!");
        dbLogAssertOperations.assertContainsByRegexMessage(
                "..*AsyncLoggingInterceptor.*",
                "Request Header \\{Accept=\\[application/xml, text/xml, "
                        + "application/json, application/\\*\\+xml, "
                        + "application/\\*\\+json\\], Content\\-Length=\\[0\\]\\}");
        dbLogAssertOperations.assertContainsByRegexMessage(
                "..*AsyncLoggingInterceptor.*", "Request Body ");
        dbLogAssertOperations.assertContainsByRegexMessage(
                "..*AsyncLoggingInterceptor.*", "onSuccess Called!");
        dbLogAssertOperations.assertContainsByRegexMessage(
                "..*AsyncChainInterceptor.*", "onSuccess Called!");
        dbLogAssertOperations.assertContainsByRegexMessage(
                "..*InterceptorsAsyncRestClientServiceImpl.*",
                "onFailure Called!");
        // 呼び出し順をチェック
        Long chainInterceptEventId = dbLogAssertOperations
                .getLogEventIdByRegexMessage(null, "..*AsyncChainInterceptor.*",
                        "AsyncChainInterceptor Called!").get(0);
        Long loggingInterceptEventId = dbLogAssertOperations
                .getLogEventIdByRegexMessage(null,
                        "..*AsyncLoggingInterceptor.*",
                        "AsyncLoggingInterceptor Called!").get(0);
        assertThat(chainInterceptEventId, lessThan(loggingInterceptEventId));

        Long loggingCallbackEventId = dbLogAssertOperations
                .getLogEventIdByRegexMessage(null,
                        "..*AsyncLoggingInterceptor.*", "onSuccess Called!")
                .get(0);
        Long chainCallbackEventId = dbLogAssertOperations
                .getLogEventIdByRegexMessage(null, "..*AsyncChainInterceptor.*",
                        "onSuccess Called!").get(0);
        Long serviceCallbackEventId = dbLogAssertOperations
                .getLogEventIdByRegexMessage(null,
                        "..*InterceptorsAsyncRestClientServiceImpl.*",
                        "onFailure Called!").get(0);
        assertThat(loggingCallbackEventId, lessThan(chainCallbackEventId));
        assertThat(chainCallbackEventId, lessThan(serviceCallbackEventId));
    }

    /**
     * <ul>
     * <li>RSCL1303003 : 非同期リクエストが行われる前に例外（接続エラーなど）が発生した場合に、例外をハンドリングできることを確認する。</li>
     * </ul>
     */
    @Test
    public void testRSCL1303003() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl1303003"));

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(className("alert-error")), is(
                "user information can not be acquired"));
        assertThat(webDriverOperations.getText(id("userName")), is(""));
        assertThat(webDriverOperations.getText(id("userAge")), is(""));

        // 各InterceptorとCallbakの呼び出しをチェック
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*AsyncChainInterceptor.*",
                "AsyncChainInterceptor Called!");
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*AsyncLoggingInterceptor.*",
                "AsyncLoggingInterceptor Called!");
        dbLogAssertOperations.assertContainsByRegexMessage(
                "..*AsyncLoggingInterceptor.*",
                "Request Header \\{Accept=\\[application/xml, text/xml, "
                        + "application/json, application/\\*\\+xml, "
                        + "application/\\*\\+json\\], Content\\-Length=\\[0\\]\\}");
        dbLogAssertOperations.assertContainsByRegexMessage(
                "..*AsyncLoggingInterceptor.*", "Request Body ");
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(
                webDriverOperations.getXTrack(), null,
                "java\\.net\\.SocketTimeoutException",
                "java\\.util\\.concurrent\\.ExecutionException");
        dbLogAssertOperations.assertContainsByRegexMessage(
                "..*AsyncLoggingInterceptor.*", "onFailure Called!");
        dbLogAssertOperations.assertContainsByRegexMessage(
                "..*AsyncLoggingInterceptor.*", "Communication Error");
        dbLogAssertOperations.assertContainsByRegexMessage(
                "..*AsyncChainInterceptor.*", "onFailure Called!");
        dbLogAssertOperations.assertContainsByRegexMessage(
                "..*InterceptorsAsyncRestClientServiceImpl.*",
                "onFailure Called!");
        // 呼び出し順をチェック
        Long chainInterceptEventId = dbLogAssertOperations
                .getLogEventIdByRegexMessage(null, "..*AsyncChainInterceptor.*",
                        "AsyncChainInterceptor Called!").get(0);
        Long loggingInterceptEventId = dbLogAssertOperations
                .getLogEventIdByRegexMessage(null,
                        "..*AsyncLoggingInterceptor.*",
                        "AsyncLoggingInterceptor Called!").get(0);
        assertThat(chainInterceptEventId, lessThan(loggingInterceptEventId));

        Long loggingCallbackEventId = dbLogAssertOperations
                .getLogEventIdByRegexMessage(null,
                        "..*AsyncLoggingInterceptor.*", "onFailure Called!")
                .get(0);
        Long chainCallbackEventId = dbLogAssertOperations
                .getLogEventIdByRegexMessage(null, "..*AsyncChainInterceptor.*",
                        "onFailure Called!").get(0);
        Long serviceCallbackEventId = dbLogAssertOperations
                .getLogEventIdByRegexMessage(null,
                        "..*InterceptorsAsyncRestClientServiceImpl.*",
                        "onFailure Called!").get(0);
        assertThat(loggingCallbackEventId, lessThan(chainCallbackEventId));
        assertThat(chainCallbackEventId, lessThan(serviceCallbackEventId));
    }

    /**
     * <ul>
     * <li>RSCL1303004 : 非同期リクエストのレスポンス返却時に例外（読み込みタイムアウト）が発生した場合に、例外がハンドリングできることを確認する。</li>
     * </ul>
     */
    @Test
    public void testRSCL1303004() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl1303004"));

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(className("alert-error")), is(
                "user information can not be acquired"));
        assertThat(webDriverOperations.getText(id("userName")), is(""));
        assertThat(webDriverOperations.getText(id("userAge")), is(""));

        // 各InterceptorとCallbakの呼び出しをチェック
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*AsyncChainInterceptor.*",
                "AsyncChainInterceptor Called!");
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*AsyncLoggingInterceptor.*",
                "AsyncLoggingInterceptor Called!");
        dbLogAssertOperations.assertContainsByRegexMessage(
                "..*AsyncLoggingInterceptor.*",
                "Request Header \\{Accept=\\[application/xml, text/xml, "
                        + "application/json, application/\\*\\+xml, "
                        + "application/\\*\\+json\\], Content\\-Length=\\[0\\]\\}");
        dbLogAssertOperations.assertContainsByRegexMessage(
                "..*AsyncLoggingInterceptor.*", "Request Body ");
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(
                webDriverOperations.getXTrack(), null,
                "java\\.net\\.SocketTimeoutException",
                "java\\.util\\.concurrent\\.ExecutionException");
        dbLogAssertOperations.assertContainsByRegexMessage(
                "..*AsyncLoggingInterceptor.*", "onFailure Called!");
        dbLogAssertOperations.assertContainsByRegexMessage(
                "..*AsyncLoggingInterceptor.*", "Communication Error");
        dbLogAssertOperations.assertContainsByRegexMessage(
                "..*AsyncChainInterceptor.*", "onFailure Called!");
        dbLogAssertOperations.assertContainsByRegexMessage(
                "..*InterceptorsAsyncRestClientServiceImpl.*",
                "onFailure Called!");
        // 呼び出し順をチェック
        Long chainInterceptEventId = dbLogAssertOperations
                .getLogEventIdByRegexMessage(null, "..*AsyncChainInterceptor.*",
                        "AsyncChainInterceptor Called!").get(0);
        Long loggingInterceptEventId = dbLogAssertOperations
                .getLogEventIdByRegexMessage(null,
                        "..*AsyncLoggingInterceptor.*",
                        "AsyncLoggingInterceptor Called!").get(0);
        assertThat(chainInterceptEventId, lessThan(loggingInterceptEventId));

        Long loggingCallbackEventId = dbLogAssertOperations
                .getLogEventIdByRegexMessage(null,
                        "..*AsyncLoggingInterceptor.*", "onFailure Called!")
                .get(0);
        Long chainCallbackEventId = dbLogAssertOperations
                .getLogEventIdByRegexMessage(null, "..*AsyncChainInterceptor.*",
                        "onFailure Called!").get(0);
        Long serviceCallbackEventId = dbLogAssertOperations
                .getLogEventIdByRegexMessage(null,
                        "..*InterceptorsAsyncRestClientServiceImpl.*",
                        "onFailure Called!").get(0);
        assertThat(loggingCallbackEventId, lessThan(chainCallbackEventId));
        assertThat(chainCallbackEventId, lessThan(serviceCallbackEventId));
    }

    /**
     * <ul>
     * <li>RSCL1401001 : HttpComponentsClientHttpRequestFactoryを使用してProxyホストとポートを指定する場合(Basic認証あり)</li>
     * </ul>
     */
    @Test
    public void testRSCL1401001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl1401001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "proxyAuth");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is(
                "test_proxyAuth"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*",
                ".*RSCL1401001 : Headers containsKey 'Pass-Internal-Proxy' is true");
    }

    /**
     * <ul>
     * <li>RSCL1401002 : HttpComponentsClientHttpRequestFactoryを使用してProxyホストとポートを指定する場合(Basic認証なし)</li>
     * </ul>
     */
    @Test
    public void testRSCL1401002() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl1401002"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "1401002");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is(
                "test_1401002"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*",
                ".*RSCL1401002 : Headers containsKey 'Pass-Internal-Proxy' is true");
    }

    /**
     * <ul>
     * <li>RSCL1402001 : SimpleClientHttpRequestFactoryを使用してProxyホストとポートを指定する場合</li>
     * </ul>
     */
    @Test
    public void testRSCL1402001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl1402001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "1402");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is(
                "test_1402"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations
                .getXTrack(), "..*RestClientServiceImpl.*",
                ".*RSCL1402001 : Headers containsKey 'Pass-Internal-Proxy' is true");
    }
}
