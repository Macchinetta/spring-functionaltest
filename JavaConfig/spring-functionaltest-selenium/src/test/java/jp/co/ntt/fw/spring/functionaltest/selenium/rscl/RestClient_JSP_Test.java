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
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

//Thymeleaf版未実装のためJSPのみ実行
public class RestClient_JSP_Test extends FunctionTestSupport {

    /**
     * <ul>
     * <li>RSCL0101001 ： RestTemplateのgetForObjectメソッドを使用したGETの実装</li>
     * <li>RSCL1201001 ： RESTFulなURLをgetForObjectメソッドで使用する場合の実装</li>
     * </ul>
     */
    @Test
    public void testRSCL0101001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0101001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "0101");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test_0101"));
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
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0102001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "0102");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test_0102"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*", ".*RSCL0102001 : " + MediaType.APPLICATION_XML_VALUE);
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*", ".*RSCL0102001 : " + HttpStatus.OK.toString());
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
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0103001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "0103");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test_0103"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*", ".*RSCL0103001 : " + HttpStatus.OK.toString());
    }

    /**
     * <ul>
     * <li>RestTemplateのexchangeメソッドを使用したGETの実装（ヘッダの設定あり）</li>
     * </ul>
     */
    @Test
    public void testRSCL0104001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0104001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "0104");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test_0104"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*",
                ".*RSCL0104001 : " + MediaType.APPLICATION_JSON_VALUE);
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*", ".*RSCL0104001 : " + HttpStatus.OK.toString());
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
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0105001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "collection");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")),
                is("RESTクライアント処理結果【ユーザリスト情報出力】"));

        // 出力内容をチェック
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*", ".*RSCL0105001 : " + HttpStatus.OK.toString());

        int count = 0;
        for (WebElement elements : webDriverOperations.getWebDriver()
                .findElements(By.id("name*"))) {
            count++;
            assertThat(elements.getAttribute("value"), is("test" + count));
            assertThat(webDriverOperations.getText(id("age" + count)), is("2" + count));
        }
    }

    /**
     * <ul>
     * <li>RestTemplateのpostForObjectメソッドを使用したPOSTの実装</li>
     * <li>Content-Lengthなし</li>
     * </ul>
     */
    @Test
    public void testRSCL0106001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0106001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "");
        webDriverOperations.overrideText(id("name"), "test0106");
        webDriverOperations.overrideText(id("age"), "20");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test0106_created"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // Spring 6.1からdefaultではContent-Lengthをヘッダに付与しない
        dbLogAssertOperations.assertContainsByRegexMessage("..*RSCLController",
                "Content-Length : null");
    }

    /**
     * <ul>
     * <li>RestTemplateのpostForObjectメソッドを使用したPOSTの実装</li>
     * <li>Content-Lengthあり</li>
     * </ul>
     */
    @Test
    public void testRSCL0106002() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0106002"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "");
        webDriverOperations.overrideText(id("name"), "test0106");
        webDriverOperations.overrideText(id("age"), "20");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        webDriverOperations.waitForDisplayed(id("screenTitle"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test0106_created"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // Spring 6.1からdefaultではContent-Lengthをヘッダに付与しない
        dbLogAssertOperations.assertContainsByRegexMessage("..*RSCLController",
                "Content-Length : \\d+");
    }

    /**
     * <ul>
     * <li>RestTemplateのpostForEntityメソッドを使用したPOSTの実装</li>
     * </ul>
     */
    @Test
    public void testRSCL0107001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0107001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "");
        webDriverOperations.overrideText(id("name"), "test0107");
        webDriverOperations.overrideText(id("age"), "20");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test0107_created"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*", ".*RSCL0107001 : " + MediaType.APPLICATION_XML_VALUE);
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*", ".*RSCL0107001 : " + HttpStatus.OK.toString());

    }

    /**
     * <ul>
     * <li>RestTemplateのexchangeメソッドを使用したPOSTの実装</li>
     * </ul>
     */
    @Test
    public void testRSCL0108001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0108001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "");
        webDriverOperations.overrideText(id("name"), "test0108");
        webDriverOperations.overrideText(id("age"), "20");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test0108_created"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*", ".*RSCL0108001 : " + HttpStatus.OK.toString());
    }

    /**
     * <ul>
     * <li>RestTemplateのexchangeメソッドを使用したPOSTの実装(ヘッダ設定あり)</li>
     * </ul>
     */
    @Test
    public void testRSCL0109001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0109001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "");
        webDriverOperations.overrideText(id("name"), "test0109");
        webDriverOperations.overrideText(id("age"), "20");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test0109_created"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*", ".*RSCL0109001 : " + HttpStatus.OK.toString());
    }

    /**
     * <ul>
     * <li>RestTemplateのdeleteメソッドを使用したDELETEの実装</li>
     * </ul>
     */
    @Test
    public void testRSCL0110001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0110001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "12345");

        // 削除ボタン押下
        webDriverOperations.click(id("delete"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果"));

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
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0111001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "12345");

        // 削除ボタン押下
        webDriverOperations.click(id("delete"));

        // 出力内容をチェック
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*",
                ".*RSCL0111001 : " + HttpStatus.NO_CONTENT.toString());
    }

    /**
     * <ul>
     * <li>RestTemplateのputメソッドを使用したPUTの実装</li>
     * </ul>
     */
    @Test
    public void testRSCL0112001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0112001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "");
        webDriverOperations.overrideText(id("name"), "test0112");
        webDriverOperations.overrideText(id("age"), "20");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果"));

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
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0113001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "");
        webDriverOperations.overrideText(id("name"), "test0113");
        webDriverOperations.overrideText(id("age"), "20");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果"));

        // 出力内容をチェック
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*", ".*RSCL0113001 : " + HttpStatus.OK.toString());
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
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0201001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("message"), "send byte");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【文字リスト出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("strListSize")), is("2"));
        assertThat(webDriverOperations.getText(id("str1")), is("send byte"));
        assertThat(webDriverOperations.getText(id("str2")), is("_received"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*", ".*RSCL0201001 : " + HttpStatus.OK.toString());
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
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0202001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("message"), "sendString");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【文字列出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("rcvString")), is("sendString_received"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*", ".*RSCL0202001 : " + MediaType.TEXT_PLAIN_VALUE);
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*", ".*RSCL0202001 : " + HttpStatus.OK.toString());
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
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0203001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("message"), "send resource");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【文字リスト出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("strListSize")), is("2"));
        assertThat(webDriverOperations.getText(id("str1")), is("send resource"));
        assertThat(webDriverOperations.getText(id("str2")), is("_received"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*", ".*RSCL0203001 : " + HttpStatus.OK.toString());
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
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0204001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("name"), "test0204");
        webDriverOperations.overrideText(id("age"), "20");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test0204_created"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*", ".*RSCL0204001 : " + MediaType.APPLICATION_XML_VALUE);
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*", ".*RSCL0204001 : " + HttpStatus.OK.toString());
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
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0205001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("name"), "test0205");
        webDriverOperations.overrideText(id("age"), "20");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test0205_received"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*",
                ".*RSCL0205001 : " + MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*", ".*RSCL0205001 : " + HttpStatus.OK.toString());
    }

    /**
     * <ul>
     * <li>AllEncompassingFormHttpMessageConverterを利用した送受信の確認</li> <br>
     * （メディアタイプがmultipart/form-dataの場合） <br>
     * HTTPボディ⇒MultiValueMap変換が失敗することを確認する。
     * </ul>
     */
    @Test
    public void testRSCL0206001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0206001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("name"), "test0206");
        webDriverOperations.overrideText(id("age"), "20");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // システムエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("System Error!"));
        // 期待するエラーが発生しているかエラーログチェック
        dbLogAssertOperations.assertContainsByRegexStackTrace(
                ".*org.springframework.web.HttpMediaTypeNotSupportedException");

    }

    /**
     * <ul>
     * <li>AtomFeedHttpMessageConverterを利用した送受信確認</li> <br>
     * Feedの送受信を確認する。
     * </ul>
     */
    @Ignore("ガイドラインでは紹介に留めており、主要機能でもないため、試験は省略する。")
    public void testRSCL0207001() {}

    /**
     * <ul>
     * <li>RssChannelHttpMessageConverterを利用した送受信確認</li> <br>
     * Channelの送受信を確認する。
     * </ul>
     */
    @Ignore("ガイドラインでは紹介に留めており、主要機能でもないため、試験は省略する。")
    public void testRSCL0208001() {}

    /**
     * <ul>
     * <li>MappingJackson2HttpMessageConverterを利用した送受信確認</li> <br>
     * JavaBean ⇔ JSON変換を確認する。
     * </ul>
     */
    @Test
    public void testRSCL0209001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0209001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("name"), "test0209");
        webDriverOperations.overrideText(id("age"), "20");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("test0209_created"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*",
                ".*RSCL0209001 : " + MediaType.APPLICATION_JSON_VALUE);
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*", ".*RSCL0209001 : " + HttpStatus.OK.toString());
    }

    /**
     * <ul>
     * <li>MappingJackson2XmlHttpMessageConverterを利用した送受信確認</li> <br>
     * JavaBeanの送受信を確認する。
     * </ul>
     */
    @Ignore("ガイドラインでは紹介に留めており、主要機能でもないため、試験は省略する。")
    public void testRSCL0210001() {}

    /**
     * <ul>
     * <li>Jaxb2RootElementHttpMessageConverterを利用した送受信確認</li> <br>
     * JavaBean ⇔ XML変換を確認する。
     * </ul>
     */
    @Test
    public void testRSCL0211001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0211001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("name"), "test0211");
        webDriverOperations.overrideText(id("age"), "20");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        assertThat(webDriverOperations.getText(id("userName")), is("test0211_created"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*", ".*RSCL0211001 : " + MediaType.APPLICATION_XML_VALUE);
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*", ".*RSCL0211001 : " + HttpStatus.OK.toString());
    }

    /**
     * <ul>
     * <li>GsonHttpMessageConverterを利用した送受信確認</li> <br>
     * JavaBeanの送受信を確認する。
     * </ul>
     */
    @Ignore("ガイドラインでは紹介に留めており、主要機能でもないため、試験は省略する。")
    public void testRSCL0212001() {}

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
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0303001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("username"), "rscluser");
        webDriverOperations.overrideText(id("password"), "password1234");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*", ".*RSCL0303001 : " + HttpStatus.OK.toString());
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
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0402001"));

        // 送信ボタン押下
        webDriverOperations.click(id("occur"));

        // 期待するエラーが発生しているかエラーログチェック
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(webDriverOperations.getXTrack(),
                null, "[e.sf.fw.9001]..*404",
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
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0403001"));

        // 送信ボタン押下
        webDriverOperations.click(id("occur"));

        // 期待するエラーが発生しているかエラーログチェック
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(webDriverOperations.getXTrack(),
                null, "[e.sf.fw.9001]..*500",
                "org\\.springframework\\.web\\.client\\.HttpServerErrorException");
    }

    /**
     * <ul>
     * <li>DefaultResponseErrorHandlerの挙動確認（未定義のコード(ユーザ定義のカスタムコード)の場合）</li> <br>
     * UnknownHttpStatusCodeExceptionをキャッチすることの確認
     * </ul>
     */
    @Ignore("未定義のレスポンスコードの時にUnknownHttpStatusCodeExceptionが発生するバグ(SPR-16108)はSpring 4.3.13で修正されたためスキップ")
    @Test
    public void testRSCL0404001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0404001"));

        // 送信ボタン押下
        webDriverOperations.click(id("occur"));

        // 期待するエラーが発生しているかログチェック
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(webDriverOperations.getXTrack(),
                null, "[e.sf.fw.9001]..*Unknown status code \\[901\\].*",
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
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0405001"));

        // 送信ボタン押下
        webDriverOperations.click(id("occur"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*",
                ".*RSCL0405001 : HttpStatus = " + HttpStatus.OK.toString() + ", RetryCount = 2");
    }

    /**
     * <ul>
     * <li>例外ハンドラの拡張の確認</li>
     * </ul>
     */
    @Test
    public void testRSCL0406001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0406001"));

        // 送信ボタン押下
        webDriverOperations.click(id("occur"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*",
                ".*RSCL0406001 : HttpStatus = " + HttpStatus.OK.toString() + ", RetryCount = 2");
    }

    /**
     * <ul>
     * <li>コネクションタイムアウトの場合</li>
     * </ul>
     */
    @Test
    public void testRSCL0501001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0501001"));

        // 送信ボタン押下
        webDriverOperations.click(id("occur"));

        // 期待するエラーが発生しているかログチェック
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(webDriverOperations.getXTrack(),
                null, "I/O error on GET request for..*",
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
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0502001"));

        // 送信ボタン押下
        webDriverOperations.click(id("occur"));

        // 期待するエラーが発生しているかログチェック
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(webDriverOperations.getXTrack(),
                null, "I/O error on GET request for..*",
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
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0601001"));

        // 送信ボタン押下
        webDriverOperations.click(id("set"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));
    }

    /**
     * <ul>
     * <li>RSCL0604001 ： setConnectTimeoutメソッドの動作確認</li>
     * </ul>
     */
    @Test
    public void testRSCL0604001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0601007"));

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
                ".*org.apache.hc.client5.http.ConnectTimeoutException.*Connect timed out");
    }

    /**
     * <ul>
     * <li>RSCL0605001 ： setResponseTimeoutメソッドの動作確認</li>
     * </ul>
     */
    @Test
    public void testRSCL0605001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0601004"));

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
        dbLogAssertOperations
                .assertContainsByRegexStackTrace(".*java.net.SocketTimeoutException.*");
    }

    /**
     * <ul>
     * <li>RSCL0606001 ： setSoTimeoutメソッドの動作確認</li>
     * <li>サーバ側でSSLハンドシェイクの処理を待ち続けてしまうため、 再度テストを実施する場合はサーバ側がタイムアウトするのを待つか再起動する必要がある。</li>
     * </ul>
     * 
     * @throws InterruptedException
     */
    @Test
    public void testRSCL0606001() throws InterruptedException {

        // 正常に動作することを確認
        {
            // メニュー画面の操作
            webDriverOperations.click(id("rscl_jsp"));
            webDriverOperations.click(id("rscl0601008"));

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
            webDriverOperations.click(id("rscl_jsp"));
            webDriverOperations.click(id("rscl0601009"));

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
                    ".*org.apache.hc.client5.http.ConnectTimeoutException.*8994.*Read timed out");
        }

    }

    /**
     * <ul>
     * <li>ファイルアップロード</li>
     * </ul>
     */
    @Test
    public void testRSCL0901001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl0901001"));

        // 送信ボタン押下
        webDriverOperations.click(id("upload"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("uploadTest"));
        assertThat(webDriverOperations.getText(id("userAge")), is("31"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*", ".*RSCL0901001 : " + HttpStatus.OK.toString());
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
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl1102001"));

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // RESTAPI呼び出し後の各インターセプタの呼び出しカウント数は、RESTAPI呼び出し前の＋１のはず。
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*LoggingInterceptor.*", "LoggingInterceptor Called!");
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
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl1103002"));

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // システムエラー画面に遷移
        assertThat(webDriverOperations.getTitle(), is("System Error!"));
        // 期待するエラーが発生しているかエラーログチェック
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(webDriverOperations.getXTrack(),
                null, "[e.sf.fw.9001]..*401",
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
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl1104001"));

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック

        // 期待するエラーが発生しているかログチェック
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(webDriverOperations.getXTrack(),
                null, ".*e\\.sf\\.rc\\.8001.*",
                "org\\.terasoluna\\.gfw.common\\.exception\\.BusinessException");
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*AccessCtrlInterceptor.*", "AccessCtrlInterceptor Obstruction File Called!");
    }

    /**
     * <ul>
     * <li>ClientHttpRequestInterceptorを使用した通信処理のリトライ</li>
     * </ul>
     */
    @Test
    public void testRSCL1105001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl1105001"));

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        // RESTAPI呼び出し前の各インターセプタの呼び出しカウント数取得
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*AccessCtrlInterceptor.*", "AccessCtrlInterceptor Called!");
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*AccessCtrlInterceptor.*", "AccessCtrlInterceptor SuccessCount : 2");
    }

    /**
     * <ul>
     * <li>RSCL1401001 :
     * HttpComponentsClientHttpRequestFactoryを使用してProxyホストとポートを指定する場合(Basic認証あり)</li>
     * </ul>
     */
    @Test
    public void testRSCL1401001() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl1401001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "proxyAuth");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test_proxyAuth"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*",
                ".*RSCL1401001 : Headers containsKey 'Pass-Internal-Proxy' is true");
    }

    /**
     * <ul>
     * <li>RSCL1401002 :
     * HttpComponentsClientHttpRequestFactoryを使用してProxyホストとポートを指定する場合(Basic認証なし)</li>
     * </ul>
     */
    @Test
    public void testRSCL1401002() {
        // メニュー画面の操作
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl1401002"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "1401002");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test_1401002"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*",
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
        webDriverOperations.click(id("rscl_jsp"));
        webDriverOperations.click(id("rscl1402001"));

        // 入力条件設定
        webDriverOperations.overrideText(id("path"), "1402");

        // 送信ボタン押下
        webDriverOperations.click(id("send"));

        // 確認画面に遷移したことをチェック
        assertThat(webDriverOperations.getText(id("screenTitle")), is("RESTクライアント処理結果【ユーザ情報出力】"));
        // 出力内容をチェック
        assertThat(webDriverOperations.getText(id("userName")), is("test_1402"));
        assertThat(webDriverOperations.getText(id("userAge")), is("20"));

        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexMessage(webDriverOperations.getXTrack(),
                "..*RestClientServiceImpl.*",
                ".*RSCL1402001 : Headers containsKey 'Pass-Internal-Proxy' is true");
    }

}
