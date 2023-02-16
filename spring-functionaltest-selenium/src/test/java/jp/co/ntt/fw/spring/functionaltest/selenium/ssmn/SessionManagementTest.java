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
package jp.co.ntt.fw.spring.functionaltest.selenium.ssmn;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

public class SessionManagementTest extends FunctionTestSupport {

    @Inject
    protected RestTemplate restTemplate;

    @Before
    public void setUp() {
        // 試験実施前に、ブラウザのセッションIDを削除
        webDriverOperations.deleteCookie("JSESSIONID");
    }

    @After
    public void tearDown() {
        // なし
    }

    /**
     * <ul>
     * <li>同Controller内の複数画面間の遷移時に、@ModelAttribute アノテーションが付与されたメソッドで生成されたセッションオブジェクトについて、格納、取得、破棄することができること。</li>
     * </ul>
     */
    @Test
    public void testSSMN0301001() {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("ssmn0301001"));
        }

        // Formオブジェクトがセッションに格納されることを確認(ログ)
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*HttpSessionEventLoggingListener",
                    "SESSIONID#.* attributeAdded : memberForm=jp\\.co\\.ntt\\.fw\\.spring\\.functionaltest\\.app\\.ssmn\\.MemberForm");
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 入力画面1で項目入力
        {
            webDriverOperations.appendText(id("firstName"), "やまだ");
            webDriverOperations.appendText(id("lastName"), "はなこ");
            webDriverOperations.appendText(id("firstNameKana"), "ヤマダ");
            webDriverOperations.appendText(id("lastNameKana"), "ハナコ");
            webDriverOperations.appendText(id("age"), "20");
            webDriverOperations.select(id("gender"), "女");
            webDriverOperations.click(id("addressForm"));
        }

        // @ModelAttribute アノテーションが付与されたメソッドが呼ばれないこと
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsByRegexMessage(
                    webDriverOperations.getXTrack(), ".*SSMN0301001Controller",
                    "ModelAttribute Method Called");
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 入力画面2で項目入力
        {
            webDriverOperations.appendText(id("zipCode"), "222-2222");
            webDriverOperations.appendText(id("state"), "東京都");
            webDriverOperations.appendText(id("city"), "多摩");
            webDriverOperations.appendText(id("address"), "１－１－１");
            webDriverOperations.click(id("otherForm"));
        }

        // @ModelAttribute アノテーションが付与されたメソッドが呼ばれないこと
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsByRegexMessage(
                    webDriverOperations.getXTrack(), ".*SSMN0301001Controller",
                    "ModelAttribute Method Called");
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 入力画面3で項目入力
        {
            webDriverOperations.appendText(id("occupation"), "会社員");
            webDriverOperations.appendText(id("mailAddress"),
                    "hoge@hoge.co.jp");
            webDriverOperations.click(id("confirm"));
        }

        // @ModelAttribute アノテーションが付与されたメソッドが呼ばれないこと
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsByRegexMessage(
                    webDriverOperations.getXTrack(), ".*SSMN0301001Controller",
                    "ModelAttribute Method Called");
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 確認画面から完了画面に遷移
        {
            assertThat(webDriverOperations.getText(id("firstName")), is("やまだ"));
            assertThat(webDriverOperations.getText(id("lastName")), is("はなこ"));
            assertThat(webDriverOperations.getText(id("firstNameKana")), is(
                    "ヤマダ"));
            assertThat(webDriverOperations.getText(id("lastNameKana")), is(
                    "ハナコ"));
            assertThat(webDriverOperations.getText(id("age")), is("20"));
            assertThat(webDriverOperations.getText(id("gender")), is("女"));
            assertThat(webDriverOperations.getText(id("zipCode")), is(
                    "222-2222"));
            assertThat(webDriverOperations.getText(id("state")), is("東京都"));
            assertThat(webDriverOperations.getText(id("city")), is("多摩"));
            assertThat(webDriverOperations.getText(id("address")), is("１－１－１"));
            assertThat(webDriverOperations.getText(id("occupation")), is(
                    "会社員"));
            assertThat(webDriverOperations.getText(id("mailAddress")), is(
                    "hoge@hoge.co.jp"));
            // hidden項目から確認
            assertThat(((JavascriptExecutor) webDriverOperations.getWebDriver())
                    .executeScript("return arguments[0].innerHTML",
                            webDriverOperations.getWebDriver().findElement(id(
                                    "checkFormInSession"))).toString(), is(
                                            "jp.co.ntt.fw.spring.functionaltest.app.ssmn.MemberForm"));
            webDriverOperations.getWebDriver().findElement(By.xpath(
                    "(//button[@value='Submit'])[2]")).click();
        }

        // Formオブジェクトがセッションから破棄されることを確認(ログ)
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*HttpSessionEventLoggingListener",
                    "SESSIONID#.* attributeRemoved : memberForm=jp\\.co\\.ntt\\.fw\\.spring\\.functionaltest\\.app\\.ssmn\\.MemberForm");
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 完了画面
        {
            assertThat(webDriverOperations.getText(id("firstName")), is("やまだ"));
            assertThat(webDriverOperations.getText(id("lastName")), is("はなこ"));
            assertThat(webDriverOperations.getText(id("firstNameKana")), is(
                    "ヤマダ"));
            assertThat(webDriverOperations.getText(id("lastNameKana")), is(
                    "ハナコ"));
            assertThat(webDriverOperations.getText(id("age")), is("20"));
            assertThat(webDriverOperations.getText(id("gender")), is("女"));
            assertThat(webDriverOperations.getText(id("zipCode")), is(
                    "222-2222"));
            assertThat(webDriverOperations.getText(id("state")), is("東京都"));
            assertThat(webDriverOperations.getText(id("city")), is("多摩"));
            assertThat(webDriverOperations.getText(id("address")), is("１－１－１"));
            assertThat(webDriverOperations.getText(id("occupation")), is(
                    "会社員"));
            assertThat(webDriverOperations.getText(id("mailAddress")), is(
                    "hoge@hoge.co.jp"));
            // セッションから破棄されていることを(hidden項目から)確認
            assertThat(((JavascriptExecutor) webDriverOperations.getWebDriver())
                    .executeScript("return arguments[0].innerHTML",
                            webDriverOperations.getWebDriver().findElement(id(
                                    "checkFormInSession"))).toString(), is(""));
        }
    }

    /**
     * <ul>
     * <li>同Controller内の複数画面間の遷移時に、Modelオブジェクトに設定されたセッションオブジェクトについて、格納、取得、破棄することができること。（セッションオブジェクトの型を指定する場合)</li>
     * </ul>
     */
    @Test
    public void testSSMN0301002() {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("ssmn0301002"));
        }

        // 入力画面1で項目入力
        {
            webDriverOperations.appendText(id("firstName"), "やまだ");
            webDriverOperations.appendText(id("lastName"), "じろう");
            webDriverOperations.appendText(id("firstNameKana"), "ヤマダ");
            webDriverOperations.appendText(id("lastNameKana"), "ジロウ");
            webDriverOperations.appendText(id("age"), "25");
            webDriverOperations.select(id("gender"), "男");
            webDriverOperations.click(id("addressForm"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 入力画面2で項目入力
        {
            webDriverOperations.appendText(id("zipCode"), "333-3333");
            webDriverOperations.appendText(id("state"), "埼玉県");
            webDriverOperations.appendText(id("city"), "浦和");
            webDriverOperations.appendText(id("address"), "１－１－１");
            webDriverOperations.click(id("otherForm"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 入力画面3で項目入力
        {
            webDriverOperations.appendText(id("occupation"), "会社員");
            webDriverOperations.appendText(id("mailAddress"),
                    "fuga@fuga.co.jp");
            webDriverOperations.click(id("confirm"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 確認画面から完了画面に遷移
        {
            assertThat(webDriverOperations.getText(id("firstName")), is("やまだ"));
            assertThat(webDriverOperations.getText(id("lastName")), is("じろう"));
            assertThat(webDriverOperations.getText(id("firstNameKana")), is(
                    "ヤマダ"));
            assertThat(webDriverOperations.getText(id("lastNameKana")), is(
                    "ジロウ"));
            assertThat(webDriverOperations.getText(id("age")), is("25"));
            assertThat(webDriverOperations.getText(id("gender")), is("男"));
            assertThat(webDriverOperations.getText(id("zipCode")), is(
                    "333-3333"));
            assertThat(webDriverOperations.getText(id("state")), is("埼玉県"));
            assertThat(webDriverOperations.getText(id("city")), is("浦和"));
            assertThat(webDriverOperations.getText(id("address")), is("１－１－１"));
            assertThat(webDriverOperations.getText(id("occupation")), is(
                    "会社員"));
            assertThat(webDriverOperations.getText(id("mailAddress")), is(
                    "fuga@fuga.co.jp"));
            // hidden項目から確認
            assertThat(((JavascriptExecutor) webDriverOperations.getWebDriver())
                    .executeScript("return arguments[0].innerHTML",
                            webDriverOperations.getWebDriver().findElement(id(
                                    "checkFormInSession"))).toString(), is(
                                            "jp.co.ntt.fw.spring.functionaltest.app.ssmn.MemberForm"));
            webDriverOperations.getWebDriver().findElement(By.xpath(
                    "(//button[@value='Submit'])[2]")).click();
        }

        // Formオブジェクトがセッションから破棄されることを確認(ログ)
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*HttpSessionEventLoggingListener",
                    "SESSIONID#.* attributeRemoved : memberForm=jp\\.co\\.ntt\\.fw\\.spring\\.functionaltest\\.app\\.ssmn\\.MemberForm");
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 完了画面
        {
            assertThat(webDriverOperations.getText(id("firstName")), is("やまだ"));
            assertThat(webDriverOperations.getText(id("lastName")), is("じろう"));
            assertThat(webDriverOperations.getText(id("firstNameKana")), is(
                    "ヤマダ"));
            assertThat(webDriverOperations.getText(id("lastNameKana")), is(
                    "ジロウ"));
            assertThat(webDriverOperations.getText(id("age")), is("25"));
            assertThat(webDriverOperations.getText(id("gender")), is("男"));
            assertThat(webDriverOperations.getText(id("zipCode")), is(
                    "333-3333"));
            assertThat(webDriverOperations.getText(id("state")), is("埼玉県"));
            assertThat(webDriverOperations.getText(id("city")), is("浦和"));
            assertThat(webDriverOperations.getText(id("address")), is("１－１－１"));
            assertThat(webDriverOperations.getText(id("occupation")), is(
                    "会社員"));
            assertThat(webDriverOperations.getText(id("mailAddress")), is(
                    "fuga@fuga.co.jp"));
            // セッションから破棄されていることを(hidden項目から)確認
            assertThat(((JavascriptExecutor) webDriverOperations.getWebDriver())
                    .executeScript("return arguments[0].innerHTML",
                            webDriverOperations.getWebDriver().findElement(id(
                                    "checkFormInSession"))).toString(), is(""));
        }
    }

    /**
     * <ul>
     * <li>同Controller内の複数画面間の遷移時に、Modelオブジェクトに設定されたセッションオブジェクトについて、格納、取得、破棄することができること。（セッションオブジェクトの属性名を指定する場合)</li>
     * </ul>
     */
    @Test
    public void testSSMN0301003() {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("ssmn0301003"));
        }

        // 入力画面1で項目入力
        {
            webDriverOperations.appendText(id("firstName"), "たなか");
            webDriverOperations.appendText(id("lastName"), "いもこ");
            webDriverOperations.appendText(id("firstNameKana"), "タナカ");
            webDriverOperations.appendText(id("lastNameKana"), "イモコ");
            webDriverOperations.appendText(id("age"), "23");
            webDriverOperations.select(id("gender"), "女");
            webDriverOperations.click(id("addressForm"));
        }

        // Formオブジェクトがセッションから破棄されることを確認(ログ)
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 入力画面2で項目入力
        {
            webDriverOperations.appendText(id("zipCode"), "444-4444");
            webDriverOperations.appendText(id("state"), "神奈川県");
            webDriverOperations.appendText(id("city"), "川崎");
            webDriverOperations.appendText(id("address"), "１－１－１");
            webDriverOperations.click(id("otherForm"));
        }

        // Formオブジェクトがセッションから破棄されることを確認(ログ)
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 入力画面3で項目入力
        {
            webDriverOperations.appendText(id("occupation"), "その他");
            webDriverOperations.appendText(id("mailAddress"),
                    "hoge@fuga.co.jp");
            webDriverOperations.click(id("confirm"));
        }

        // Formオブジェクトがセッションから破棄されることを確認(ログ)
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 確認画面から完了画面に遷移
        {
            assertThat(webDriverOperations.getText(id("firstName")), is("たなか"));
            assertThat(webDriverOperations.getText(id("lastName")), is("いもこ"));
            assertThat(webDriverOperations.getText(id("firstNameKana")), is(
                    "タナカ"));
            assertThat(webDriverOperations.getText(id("lastNameKana")), is(
                    "イモコ"));
            assertThat(webDriverOperations.getText(id("age")), is("23"));
            assertThat(webDriverOperations.getText(id("gender")), is("女"));
            assertThat(webDriverOperations.getText(id("zipCode")), is(
                    "444-4444"));
            assertThat(webDriverOperations.getText(id("state")), is("神奈川県"));
            assertThat(webDriverOperations.getText(id("city")), is("川崎"));
            assertThat(webDriverOperations.getText(id("address")), is("１－１－１"));
            assertThat(webDriverOperations.getText(id("occupation")), is(
                    "その他"));
            assertThat(webDriverOperations.getText(id("mailAddress")), is(
                    "hoge@fuga.co.jp"));
            // hidden項目から確認
            assertThat(((JavascriptExecutor) webDriverOperations.getWebDriver())
                    .executeScript("return arguments[0].innerHTML",
                            webDriverOperations.getWebDriver().findElement(id(
                                    "checkFormInSession"))).toString(), is(
                                            "jp.co.ntt.fw.spring.functionaltest.app.ssmn.MemberForm"));
            webDriverOperations.getWebDriver().findElement(By.xpath(
                    "(//button[@value='Submit'])[2]")).click();
        }

        // Formオブジェクトがセッションから破棄されることを確認(ログ)
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*HttpSessionEventLoggingListener",
                    "SESSIONID#.* attributeRemoved : memberForm=jp\\.co\\.ntt\\.fw\\.spring\\.functionaltest\\.app\\.ssmn\\.MemberForm");
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 完了画面
        {
            assertThat(webDriverOperations.getText(id("firstName")), is("たなか"));
            assertThat(webDriverOperations.getText(id("lastName")), is("いもこ"));
            assertThat(webDriverOperations.getText(id("firstNameKana")), is(
                    "タナカ"));
            assertThat(webDriverOperations.getText(id("lastNameKana")), is(
                    "イモコ"));
            assertThat(webDriverOperations.getText(id("age")), is("23"));
            assertThat(webDriverOperations.getText(id("gender")), is("女"));
            assertThat(webDriverOperations.getText(id("zipCode")), is(
                    "444-4444"));
            assertThat(webDriverOperations.getText(id("state")), is("神奈川県"));
            assertThat(webDriverOperations.getText(id("city")), is("川崎"));
            assertThat(webDriverOperations.getText(id("address")), is("１－１－１"));
            assertThat(webDriverOperations.getText(id("occupation")), is(
                    "その他"));
            assertThat(webDriverOperations.getText(id("mailAddress")), is(
                    "hoge@fuga.co.jp"));
            // セッションから破棄されていることを(hidden項目から)確認
            assertThat(((JavascriptExecutor) webDriverOperations.getWebDriver())
                    .executeScript("return arguments[0].innerHTML",
                            webDriverOperations.getWebDriver().findElement(id(
                                    "checkFormInSession"))).toString(), is(""));
        }
    }

    /**
     * <ul>
     * <li>セッションオブジェクトをControllerの処理メソッドの引数に@ModelAttributeアノテーションを付けずに取得する時、そのセッションオブジェクトが
     * Modelに存在しない場合は、新しいオブジェクトが生成されること。</li>
     * </ul>
     */
    @Test
    public void testSSMN0301004() {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("ssmn0301004"));
        }

        // 入力画面1で項目入力
        {
            webDriverOperations.appendText(id("firstName"), "たなか");
            webDriverOperations.appendText(id("lastName"), "いもこ");
            webDriverOperations.appendText(id("firstNameKana"), "タナカ");
            webDriverOperations.appendText(id("lastNameKana"), "イモコ");
            webDriverOperations.appendText(id("age"), "23");
            webDriverOperations.select(id("gender"), "女");
            webDriverOperations.click(id("addressForm"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // セッションを削除
        // (Controllerの処理メソッドの引数にセッションオブジェクト取得する時、そのセッションオブジェクトが Modelに存在しないようにする為。)
        {
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.set("Cookie", "JSESSIONID=" + webDriverOperations
                    .getCookie("JSESSIONID").getValue());
            restTemplate.exchange(applicationContextUrl
                    + "/ssmn/0301/deleteSession", HttpMethod.GET,
                    new HttpEntity<byte[]>(requestHeaders), byte[].class);
        }

        // 入力画面2で項目入力
        {
            webDriverOperations.appendText(id("zipCode"), "444-4444");
            webDriverOperations.appendText(id("state"), "神奈川県");
            webDriverOperations.appendText(id("city"), "川崎");
            webDriverOperations.appendText(id("address"), "１－１－１");
            webDriverOperations.click(id("otherForm"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 入力画面3で項目入力
        {
            webDriverOperations.appendText(id("occupation"), "その他");
            webDriverOperations.appendText(id("mailAddress"),
                    "hoge@fuga.co.jp");
            webDriverOperations.click(id("confirm"));
        }

        // 確認画面に、セッションに格納したFormの中身が表示されていること。
        // ->入力1画面で入力した内容は表示されないこと。
        // ->入力2画面で入力した内容が表示されること。
        {
            assertThat(webDriverOperations.getText(id("firstName")), is(""));
            assertThat(webDriverOperations.getText(id("lastName")), is(""));
            assertThat(webDriverOperations.getText(id("firstNameKana")), is(
                    ""));
            assertThat(webDriverOperations.getText(id("lastNameKana")), is(""));
            assertThat(webDriverOperations.getText(id("age")), is(""));
            assertThat(webDriverOperations.getText(id("gender")), is(""));
            assertThat(webDriverOperations.getText(id("zipCode")), is(
                    "444-4444"));
            assertThat(webDriverOperations.getText(id("state")), is("神奈川県"));
            assertThat(webDriverOperations.getText(id("city")), is("川崎"));
            assertThat(webDriverOperations.getText(id("address")), is("１－１－１"));
            assertThat(webDriverOperations.getText(id("occupation")), is(
                    "その他"));
            assertThat(webDriverOperations.getText(id("mailAddress")), is(
                    "hoge@fuga.co.jp"));
            // hidden項目から確認
            assertThat(((JavascriptExecutor) webDriverOperations.getWebDriver())
                    .executeScript("return arguments[0].innerHTML",
                            webDriverOperations.getWebDriver().findElement(id(
                                    "checkFormInSession"))).toString(), is(
                                            "jp.co.ntt.fw.spring.functionaltest.app.ssmn.MemberForm"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }
    }

    /**
     * <ul>
     * <li>セッションオブジェクトをControllerの処理メソッドの引数に@ModelAttributeアノテーションを付けて取得する時、そのセッションオブジェクトが Modelに存在しない場合は、例外が発生すること。</li>
     * </ul>
     */
    @Test
    public void testSSMN0301005() {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("ssmn0301005"));
        }

        // 入力画面1で項目入力
        {
            webDriverOperations.appendText(id("firstName"), "たなか");
            webDriverOperations.appendText(id("lastName"), "いもこ");
            webDriverOperations.appendText(id("firstNameKana"), "タナカ");
            webDriverOperations.appendText(id("lastNameKana"), "イモコ");
            webDriverOperations.appendText(id("age"), "23");
            webDriverOperations.select(id("gender"), "女");
        }

        // 事前にセッションを削除
        // (Controllerの処理メソッドの引数にセッションオブジェクト取得する時、そのセッションオブジェクトが Modelに存在しないようにする為。)
        {
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.set("Cookie", "JSESSIONID=" + webDriverOperations
                    .getCookie("JSESSIONID").getValue());
            restTemplate.exchange(applicationContextUrl
                    + "/ssmn/0301/deleteSession", HttpMethod.GET,
                    new HttpEntity<byte[]>(requestHeaders), byte[].class);
        }

        // 入力画面2へ遷移
        {
            webDriverOperations.click(id("addressForm"));
        }

        // クライアントエラー画面にHttpSessionRequiredExcetionに関するエラーメッセージが表示されること。
        {
            assertThat(webDriverOperations.getText(By.xpath(
                    "//div[2]/div/ul/li")), is(
                            "[e.sf.cmmn.8002] 不正なリクエストが送信されました。"));
        }
    }

    /**
     * <ul>
     * <li>入力画面表示前の処理で、セッションを破棄した結果、ウィザードを途中で中止して(メニューに戻る)、再度入力画面を表示した場合に、入力項目が破棄されていることを確認する。</li>
     * </ul>
     */
    @Test
    public void testSSMN0301006() {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("ssmn0301006"));
        }

        // 入力画面1で項目入力
        {
            webDriverOperations.appendText(id("firstName"), "たなか");
            webDriverOperations.appendText(id("lastName"), "いもこ");
            webDriverOperations.appendText(id("firstNameKana"), "タナカ");
            webDriverOperations.appendText(id("lastNameKana"), "イモコ");
            webDriverOperations.appendText(id("age"), "23");
            webDriverOperations.select(id("gender"), "女");
            webDriverOperations.click(id("addressForm"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 入力画面2から入力画面1へ戻る
        {
            webDriverOperations.click(id("redoPersonal"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 入力画面1からメニューに戻る
        {
            webDriverOperations.click(id("menu"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 再度メニュー画面から入力画面1へ遷移
        {
            webDriverOperations.click(id("ssmn0301006"));
        }

        // 再度入力1画面へ遷移した時、入力1画面の入力項目が破棄されていること。
        {
            assertThat(webDriverOperations.getText(id("firstName")), is(""));
            assertThat(webDriverOperations.getText(id("lastName")), is(""));
            assertThat(webDriverOperations.getText(id("firstNameKana")), is(
                    ""));
            assertThat(webDriverOperations.getText(id("lastNameKana")), is(""));
            assertThat(webDriverOperations.getText(id("age")), is(""));
            // デフォルト設定（男）であること
            assertThat(new Select(webDriverOperations.getWebDriver()
                    .findElement(id("gender"))).getFirstSelectedOption()
                            .getText(), is("男"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }
    }

    /**
     * <ul>
     * <li>同Controller内の複数画面間の遷移時に、@ModelAttribute アノテーションを付与しているメソッドで生成されたオブジェクトについて、@ModelAttribute
     * アノテーションを指定しない場合は、リクエストの度にメソッドが呼ばれること。</li>
     * </ul>
     */
    @Test
    public void testSSMN0301007() {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("ssmn0301007"));
        }

        // @ModelAttribute アノテーションが付与されたメソッドが呼ばれること
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(
                    webDriverOperations.getXTrack(), ".*SSMN0301007Controller",
                    "ModelAttribute Method Called");
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 入力画面1で項目入力
        {
            webDriverOperations.appendText(id("firstName"), "やまだ");
            webDriverOperations.appendText(id("lastName"), "はなこ");
            webDriverOperations.appendText(id("firstNameKana"), "ヤマダ");
            webDriverOperations.appendText(id("lastNameKana"), "ハナコ");
            webDriverOperations.appendText(id("age"), "20");
            webDriverOperations.select(id("gender"), "女");
            webDriverOperations.click(id("addressForm"));
        }

        // @ModelAttribute アノテーションが付与されたメソッドが呼ばれること
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(
                    webDriverOperations.getXTrack(), ".*SSMN0301007Controller",
                    "ModelAttribute Method Called");
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 入力画面2で項目入力
        {
            webDriverOperations.appendText(id("zipCode"), "222-2222");
            webDriverOperations.appendText(id("state"), "東京都");
            webDriverOperations.appendText(id("city"), "多摩");
            webDriverOperations.appendText(id("address"), "１－１－１");
            webDriverOperations.click(id("otherForm"));
        }

        // @ModelAttribute アノテーションが付与されたメソッドが呼ばれること
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(
                    webDriverOperations.getXTrack(), ".*SSMN0301007Controller",
                    "ModelAttribute Method Called");
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 入力画面3で項目入力
        {
            webDriverOperations.appendText(id("occupation"), "会社員");
            webDriverOperations.appendText(id("mailAddress"),
                    "hoge@hoge.co.jp");
            webDriverOperations.click(id("confirm"));
        }

        // @ModelAttribute アノテーションが付与されたメソッドが呼ばれること
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(
                    webDriverOperations.getXTrack(), ".*SSMN0301007Controller",
                    "ModelAttribute Method Called");
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 確認画面から完了画面に遷移
        {
            assertThat(webDriverOperations.getText(id("firstName")), is("やまだ"));
            assertThat(webDriverOperations.getText(id("lastName")), is("はなこ"));
            assertThat(webDriverOperations.getText(id("firstNameKana")), is(
                    "ヤマダ"));
            assertThat(webDriverOperations.getText(id("lastNameKana")), is(
                    "ハナコ"));
            assertThat(webDriverOperations.getText(id("age")), is("20"));
            assertThat(webDriverOperations.getText(id("gender")), is("女"));
            assertThat(webDriverOperations.getText(id("zipCode")), is(
                    "222-2222"));
            assertThat(webDriverOperations.getText(id("state")), is("東京都"));
            assertThat(webDriverOperations.getText(id("city")), is("多摩"));
            assertThat(webDriverOperations.getText(id("address")), is("１－１－１"));
            assertThat(webDriverOperations.getText(id("occupation")), is(
                    "会社員"));
            assertThat(webDriverOperations.getText(id("mailAddress")), is(
                    "hoge@hoge.co.jp"));
            // hidden項目から確認
            assertThat(((JavascriptExecutor) webDriverOperations.getWebDriver())
                    .executeScript("return arguments[0].innerHTML",
                            webDriverOperations.getWebDriver().findElement(id(
                                    "checkFormInSession"))).toString(), is(
                                            "jp.co.ntt.fw.spring.functionaltest.app.ssmn.MemberForm"));
            webDriverOperations.getWebDriver().findElement(By.xpath(
                    "(//button[@value='Submit'])[2]")).click();
        }

        // Formオブジェクトがセッションから破棄されることを確認(ログ)
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*HttpSessionEventLoggingListener",
                    "SESSIONID#.* attributeRemoved : memberForm=jp\\.co\\.ntt\\.fw\\.spring\\.functionaltest\\.app\\.ssmn\\.MemberForm");
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 完了画面
        {
            assertThat(webDriverOperations.getText(id("firstName")), is("やまだ"));
            assertThat(webDriverOperations.getText(id("lastName")), is("はなこ"));
            assertThat(webDriverOperations.getText(id("firstNameKana")), is(
                    "ヤマダ"));
            assertThat(webDriverOperations.getText(id("lastNameKana")), is(
                    "ハナコ"));
            assertThat(webDriverOperations.getText(id("age")), is("20"));
            assertThat(webDriverOperations.getText(id("gender")), is("女"));
            assertThat(webDriverOperations.getText(id("zipCode")), is(
                    "222-2222"));
            assertThat(webDriverOperations.getText(id("state")), is("東京都"));
            assertThat(webDriverOperations.getText(id("city")), is("多摩"));
            assertThat(webDriverOperations.getText(id("address")), is("１－１－１"));
            assertThat(webDriverOperations.getText(id("occupation")), is(
                    "会社員"));
            assertThat(webDriverOperations.getText(id("mailAddress")), is(
                    "hoge@hoge.co.jp"));
            // セッションから破棄されていることを(hidden項目から)確認
            assertThat(((JavascriptExecutor) webDriverOperations.getWebDriver())
                    .executeScript("return arguments[0].innerHTML",
                            webDriverOperations.getWebDriver().findElement(id(
                                    "checkFormInSession"))).toString(), is(""));
        }
    }

    /**
     * <ul>
     * <li>セッションオブジェクトをハンドラメソッド内でModelオブジェクトから取得した場合、リクエストパラメータのバインドがされないこと。</li>
     * </ul>
     */
    @Test
    public void testSSMN0301008() {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("ssmn0301008"));
        }

        // 入力画面1で項目入力
        {
            webDriverOperations.appendText(id("firstName"), "やまだ");
            webDriverOperations.appendText(id("lastName"), "はなこ");
            webDriverOperations.appendText(id("firstNameKana"), "ヤマダ");
            webDriverOperations.appendText(id("lastNameKana"), "ハナコ");
            webDriverOperations.appendText(id("age"), "20");
            webDriverOperations.select(id("gender"), "女");
            webDriverOperations.click(id("addressForm"));
        }

        // 入力画面2で項目入力
        {
            webDriverOperations.appendText(id("zipCode"), "222-2222");
            webDriverOperations.appendText(id("state"), "東京都");
            webDriverOperations.appendText(id("city"), "多摩");
            webDriverOperations.appendText(id("address"), "１－１－１");
            webDriverOperations.click(id("otherForm"));
        }

        // 入力画面3で項目入力
        {
            webDriverOperations.appendText(id("occupation"), "会社員");
            webDriverOperations.appendText(id("mailAddress"),
                    "hoge@hoge.co.jp");
            webDriverOperations.click(id("confirm"));
        }

        // 確認画面から完了画面に遷移
        {
            // リクエストパラメータによる変更前の状態確認
            assertThat(webDriverOperations.getText(id("firstName")), is("やまだ"));
            assertThat(webDriverOperations.getText(id("lastName")), is("はなこ"));
            assertThat(webDriverOperations.getText(id("firstNameKana")), is(
                    "ヤマダ"));
            assertThat(webDriverOperations.getText(id("lastNameKana")), is(
                    "ハナコ"));
            assertThat(webDriverOperations.getText(id("age")), is("20"));
            assertThat(webDriverOperations.getText(id("gender")), is("女"));
            assertThat(webDriverOperations.getText(id("zipCode")), is(
                    "222-2222"));
            assertThat(webDriverOperations.getText(id("state")), is("東京都"));
            assertThat(webDriverOperations.getText(id("city")), is("多摩"));
            assertThat(webDriverOperations.getText(id("address")), is("１－１－１"));
            assertThat(webDriverOperations.getText(id("occupation")), is(
                    "会社員"));
            assertThat(webDriverOperations.getText(id("mailAddress")), is(
                    "hoge@hoge.co.jp"));

            webDriverOperations.getWebDriver().findElement(By.xpath(
                    "(//button[@value='Submit'])[2]")).click();
        }

        // 完了画面
        {
            // リクエストパラメータによる変更後の状態確認
            assertThat(webDriverOperations.getText(id("firstName")), is("やまだ"));
            assertThat(webDriverOperations.getText(id("lastName")), is("はなこ"));
            assertThat(webDriverOperations.getText(id("firstNameKana")), is(
                    "ヤマダ"));
            assertThat(webDriverOperations.getText(id("lastNameKana")), is(
                    "ハナコ"));
            assertThat(webDriverOperations.getText(id("age")), is("20"));
            assertThat(webDriverOperations.getText(id("gender")), is("女"));
            assertThat(webDriverOperations.getText(id("zipCode")), is(
                    "222-2222"));
            assertThat(webDriverOperations.getText(id("state")), is("東京都"));
            assertThat(webDriverOperations.getText(id("city")), is("多摩"));
            assertThat(webDriverOperations.getText(id("address")), is("１－１－１"));
            assertThat(webDriverOperations.getText(id("occupation")), is(
                    "会社員"));
            assertThat(webDriverOperations.getText(id("mailAddress")), is(
                    "hoge@hoge.co.jp"));
        }
    }

    /**
     * <ul>
     * <li>@SessionAttributeで管理しているオブジェクトを@ModelAttribute付きの引数として受け取るハンドラメソッドに対して、@SessionAttributeで管理しているオブジェクトと同名のリクエストパラメータを投げても
     * 、@ModelAttribute付きの引数にはセッションオブジェクトがバインドされていること。</li>
     * </ul>
     */
    @Test
    public void testSSMN0301009() {
        // @ModelAttributeのbinding属性がtrue時のテスト
        // メニュー画面の操作
        {
            webDriverOperations.click(id("ssmn0301009"));
        }

        // 入力画面1で項目入力
        {
            webDriverOperations.appendText(id("firstName"), "やまだ");
            webDriverOperations.appendText(id("lastName"), "はなこ");
            webDriverOperations.appendText(id("firstNameKana"), "ヤマダ");
            webDriverOperations.appendText(id("lastNameKana"), "ハナコ");
            webDriverOperations.appendText(id("age"), "20");
            webDriverOperations.select(id("gender"), "女");
            webDriverOperations.click(id("addressForm"));
        }

        // 入力画面2で項目入力
        {
            webDriverOperations.appendText(id("zipCode"), "222-2222");
            webDriverOperations.appendText(id("state"), "東京都");
            webDriverOperations.appendText(id("city"), "多摩");
            webDriverOperations.appendText(id("address"), "１－１－１");
            webDriverOperations.click(id("otherForm"));
        }

        // 入力画面3で項目入力
        {
            webDriverOperations.appendText(id("occupation"), "会社員");
            webDriverOperations.appendText(id("mailAddress"),
                    "hoge@hoge.co.jp");
            webDriverOperations.click(id("confirm"));
        }

        // 確認画面から完了画面に遷移
        {
            // リクエストパラメータによる変更前の状態確認
            assertThat(webDriverOperations.getText(id("firstName")), is("やまだ"));
            assertThat(webDriverOperations.getText(id("lastName")), is("はなこ"));
            assertThat(webDriverOperations.getText(id("firstNameKana")), is(
                    "ヤマダ"));
            assertThat(webDriverOperations.getText(id("lastNameKana")), is(
                    "ハナコ"));
            assertThat(webDriverOperations.getText(id("age")), is("20"));
            assertThat(webDriverOperations.getText(id("gender")), is("女"));
            assertThat(webDriverOperations.getText(id("zipCode")), is(
                    "222-2222"));
            assertThat(webDriverOperations.getText(id("state")), is("東京都"));
            assertThat(webDriverOperations.getText(id("city")), is("多摩"));
            assertThat(webDriverOperations.getText(id("address")), is("１－１－１"));
            assertThat(webDriverOperations.getText(id("occupation")), is(
                    "会社員"));
            assertThat(webDriverOperations.getText(id("mailAddress")), is(
                    "hoge@hoge.co.jp"));

            webDriverOperations.getWebDriver().findElement(By.xpath(
                    "(//button[@type='submit'])[2]")).click();
        }

        // 完了画面
        {
            // リクエストパラメータによる変更後の状態確認
            assertThat(webDriverOperations.getText(id("firstName")), is("佐藤"));
            assertThat(webDriverOperations.getText(id("lastName")), is("次郎"));
            assertThat(webDriverOperations.getText(id("firstNameKana")), is(
                    "サトウ"));
            assertThat(webDriverOperations.getText(id("lastNameKana")), is(
                    "ジロウ"));
            assertThat(webDriverOperations.getText(id("age")), is("90"));
            assertThat(webDriverOperations.getText(id("gender")), is("男"));
            assertThat(webDriverOperations.getText(id("zipCode")), is(
                    "123-1234"));
            assertThat(webDriverOperations.getText(id("state")), is("神奈川県"));
            assertThat(webDriverOperations.getText(id("city")), is("西浦和"));
            assertThat(webDriverOperations.getText(id("address")), is(
                    "100-100-100"));
            assertThat(webDriverOperations.getText(id("occupation")), is("無職"));
            assertThat(webDriverOperations.getText(id("mailAddress")), is(
                    "a@a.co.jp"));

            // メニュー画面へ戻る
            webDriverOperations.click(id("menu"));
        }
        // @ModelAttributeのbinding属性がfalse時のテスト
        // メニュー画面の操作
        {
            webDriverOperations.click(id("ssmn0301009"));
        }

        // 入力画面1で項目入力
        {
            webDriverOperations.appendText(id("firstName"), "やまだ");
            webDriverOperations.appendText(id("lastName"), "はなこ");
            webDriverOperations.appendText(id("firstNameKana"), "ヤマダ");
            webDriverOperations.appendText(id("lastNameKana"), "ハナコ");
            webDriverOperations.appendText(id("age"), "20");
            webDriverOperations.select(id("gender"), "女");
            webDriverOperations.click(id("addressForm"));
        }

        // 入力画面2で項目入力
        {
            webDriverOperations.appendText(id("zipCode"), "222-2222");
            webDriverOperations.appendText(id("state"), "東京都");
            webDriverOperations.appendText(id("city"), "多摩");
            webDriverOperations.appendText(id("address"), "１－１－１");
            webDriverOperations.click(id("otherForm"));
        }

        // 入力画面3で項目入力
        {
            webDriverOperations.appendText(id("occupation"), "会社員");
            webDriverOperations.appendText(id("mailAddress"),
                    "hoge@hoge.co.jp");
            webDriverOperations.click(id("confirm"));
        }

        // 確認画面から完了画面に遷移
        {
            // リクエストパラメータによる変更前の状態確認
            assertThat(webDriverOperations.getText(id("firstName")), is("やまだ"));
            assertThat(webDriverOperations.getText(id("lastName")), is("はなこ"));
            assertThat(webDriverOperations.getText(id("firstNameKana")), is(
                    "ヤマダ"));
            assertThat(webDriverOperations.getText(id("lastNameKana")), is(
                    "ハナコ"));
            assertThat(webDriverOperations.getText(id("age")), is("20"));
            assertThat(webDriverOperations.getText(id("gender")), is("女"));
            assertThat(webDriverOperations.getText(id("zipCode")), is(
                    "222-2222"));
            assertThat(webDriverOperations.getText(id("state")), is("東京都"));
            assertThat(webDriverOperations.getText(id("city")), is("多摩"));
            assertThat(webDriverOperations.getText(id("address")), is("１－１－１"));
            assertThat(webDriverOperations.getText(id("occupation")), is(
                    "会社員"));
            assertThat(webDriverOperations.getText(id("mailAddress")), is(
                    "hoge@hoge.co.jp"));

            webDriverOperations.getWebDriver().findElement(By.xpath(
                    "(//button[@type='submit'])[3]")).click();
        }

        // 完了画面
        {
            // リクエストパラメータによる変更後の状態確認
            assertThat(webDriverOperations.getText(id("firstName")), is("やまだ"));
            assertThat(webDriverOperations.getText(id("lastName")), is("はなこ"));
            assertThat(webDriverOperations.getText(id("firstNameKana")), is(
                    "ヤマダ"));
            assertThat(webDriverOperations.getText(id("lastNameKana")), is(
                    "ハナコ"));
            assertThat(webDriverOperations.getText(id("age")), is("20"));
            assertThat(webDriverOperations.getText(id("gender")), is("女"));
            assertThat(webDriverOperations.getText(id("zipCode")), is(
                    "222-2222"));
            assertThat(webDriverOperations.getText(id("state")), is("東京都"));
            assertThat(webDriverOperations.getText(id("city")), is("多摩"));
            assertThat(webDriverOperations.getText(id("address")), is("１－１－１"));
            assertThat(webDriverOperations.getText(id("occupation")), is(
                    "会社員"));
            assertThat(webDriverOperations.getText(id("mailAddress")), is(
                    "hoge@hoge.co.jp"));

            // メニュー画面へ戻る
            webDriverOperations.click(id("menu"));

        }
        // @ModelAttributeのbinding属性が無指定時のテスト
        // メニュー画面の操作
        {
            webDriverOperations.click(id("ssmn0301009"));
        }

        // 入力画面1で項目入力
        {
            webDriverOperations.appendText(id("firstName"), "やまだ");
            webDriverOperations.appendText(id("lastName"), "はなこ");
            webDriverOperations.appendText(id("firstNameKana"), "ヤマダ");
            webDriverOperations.appendText(id("lastNameKana"), "ハナコ");
            webDriverOperations.appendText(id("age"), "20");
            webDriverOperations.select(id("gender"), "女");
            webDriverOperations.click(id("addressForm"));
        }

        // 入力画面2で項目入力
        {
            webDriverOperations.appendText(id("zipCode"), "222-2222");
            webDriverOperations.appendText(id("state"), "東京都");
            webDriverOperations.appendText(id("city"), "多摩");
            webDriverOperations.appendText(id("address"), "１－１－１");
            webDriverOperations.click(id("otherForm"));
        }

        // 入力画面3で項目入力
        {
            webDriverOperations.appendText(id("occupation"), "会社員");
            webDriverOperations.appendText(id("mailAddress"),
                    "hoge@hoge.co.jp");
            webDriverOperations.click(id("confirm"));
        }

        // 確認画面から完了画面に遷移
        {
            // リクエストパラメータによる変更前の状態確認
            assertThat(webDriverOperations.getText(id("firstName")), is("やまだ"));
            assertThat(webDriverOperations.getText(id("lastName")), is("はなこ"));
            assertThat(webDriverOperations.getText(id("firstNameKana")), is(
                    "ヤマダ"));
            assertThat(webDriverOperations.getText(id("lastNameKana")), is(
                    "ハナコ"));
            assertThat(webDriverOperations.getText(id("age")), is("20"));
            assertThat(webDriverOperations.getText(id("gender")), is("女"));
            assertThat(webDriverOperations.getText(id("zipCode")), is(
                    "222-2222"));
            assertThat(webDriverOperations.getText(id("state")), is("東京都"));
            assertThat(webDriverOperations.getText(id("city")), is("多摩"));
            assertThat(webDriverOperations.getText(id("address")), is("１－１－１"));
            assertThat(webDriverOperations.getText(id("occupation")), is(
                    "会社員"));
            assertThat(webDriverOperations.getText(id("mailAddress")), is(
                    "hoge@hoge.co.jp"));

            webDriverOperations.getWebDriver().findElement(By.xpath(
                    "(//button[@type='submit'])[4]")).click();
        }

        // 完了画面
        {
            // リクエストパラメータによる変更後の状態確認
            assertThat(webDriverOperations.getText(id("firstName")), is("佐藤"));
            assertThat(webDriverOperations.getText(id("lastName")), is("次郎"));
            assertThat(webDriverOperations.getText(id("firstNameKana")), is(
                    "サトウ"));
            assertThat(webDriverOperations.getText(id("lastNameKana")), is(
                    "ジロウ"));
            assertThat(webDriverOperations.getText(id("age")), is("90"));
            assertThat(webDriverOperations.getText(id("gender")), is("男"));
            assertThat(webDriverOperations.getText(id("zipCode")), is(
                    "123-1234"));
            assertThat(webDriverOperations.getText(id("state")), is("神奈川県"));
            assertThat(webDriverOperations.getText(id("city")), is("西浦和"));
            assertThat(webDriverOperations.getText(id("address")), is(
                    "100-100-100"));
            assertThat(webDriverOperations.getText(id("occupation")), is("無職"));
            assertThat(webDriverOperations.getText(id("mailAddress")), is(
                    "a@a.co.jp"));
        }
    }

    /**
     * <ul>
     * <li>@SessionAttributesを使ったウィザード形式の画面遷移で、セッションの生成、取得、削除を行う。</li>
     * </ul>
     */
    @Test
    public void testSSMN0302001() {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("ssmn0302001"));
        }

        // 入力画面1で項目入力
        {
            webDriverOperations.appendText(id("firstName"), "さとう");
            webDriverOperations.appendText(id("lastName"), "ごろう");
            webDriverOperations.appendText(id("firstNameKana"), "サトウ");
            webDriverOperations.appendText(id("lastNameKana"), "ゴロウ");
            webDriverOperations.appendText(id("age"), "56");
            webDriverOperations.select(id("gender"), "男");
            webDriverOperations.click(id("addressForm"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 入力画面2で項目入力
        {
            webDriverOperations.appendText(id("zipCode"), "555-5555");
            webDriverOperations.appendText(id("state"), "千葉県");
            webDriverOperations.appendText(id("city"), "浦安");
            webDriverOperations.appendText(id("address"), "１－１－１");
            webDriverOperations.click(id("otherForm"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 入力画面3で項目入力
        {
            webDriverOperations.appendText(id("occupation"), "会社員");
            webDriverOperations.appendText(id("mailAddress"),
                    "fuga@moge.co.jp");
            webDriverOperations.click(id("confirm"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 確認画面から完了画面に遷移
        {
            assertThat(webDriverOperations.getText(id("firstName")), is("さとう"));
            assertThat(webDriverOperations.getText(id("lastName")), is("ごろう"));
            assertThat(webDriverOperations.getText(id("firstNameKana")), is(
                    "サトウ"));
            assertThat(webDriverOperations.getText(id("lastNameKana")), is(
                    "ゴロウ"));
            assertThat(webDriverOperations.getText(id("age")), is("56"));
            assertThat(webDriverOperations.getText(id("gender")), is("男"));
            assertThat(webDriverOperations.getText(id("zipCode")), is(
                    "555-5555"));
            assertThat(webDriverOperations.getText(id("state")), is("千葉県"));
            assertThat(webDriverOperations.getText(id("city")), is("浦安"));
            assertThat(webDriverOperations.getText(id("address")), is("１－１－１"));
            assertThat(webDriverOperations.getText(id("occupation")), is(
                    "会社員"));
            assertThat(webDriverOperations.getText(id("mailAddress")), is(
                    "fuga@moge.co.jp"));
            // hidden項目から確認
            assertThat(((JavascriptExecutor) webDriverOperations.getWebDriver())
                    .executeScript("return arguments[0].innerHTML",
                            webDriverOperations.getWebDriver().findElement(id(
                                    "checkFormInSession"))).toString(), is(
                                            "jp.co.ntt.fw.spring.functionaltest.app.ssmn.MemberForm"));
            webDriverOperations.getWebDriver().findElement(By.xpath(
                    "(//button[@value='Submit'])[2]")).click();
        }

        // Formオブジェクトがセッションから破棄されることを確認(ログ)
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*HttpSessionEventLoggingListener",
                    "SESSIONID#.* attributeRemoved : memberForm=jp\\.co\\.ntt\\.fw\\.spring\\.functionaltest\\.app\\.ssmn\\.MemberForm");
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 完了画面
        {
            assertThat(webDriverOperations.getText(id("firstName")), is("さとう"));
            assertThat(webDriverOperations.getText(id("lastName")), is("ごろう"));
            assertThat(webDriverOperations.getText(id("firstNameKana")), is(
                    "サトウ"));
            assertThat(webDriverOperations.getText(id("lastNameKana")), is(
                    "ゴロウ"));
            assertThat(webDriverOperations.getText(id("age")), is("56"));
            assertThat(webDriverOperations.getText(id("gender")), is("男"));
            assertThat(webDriverOperations.getText(id("zipCode")), is(
                    "555-5555"));
            assertThat(webDriverOperations.getText(id("state")), is("千葉県"));
            assertThat(webDriverOperations.getText(id("city")), is("浦安"));
            assertThat(webDriverOperations.getText(id("address")), is("１－１－１"));
            assertThat(webDriverOperations.getText(id("occupation")), is(
                    "会社員"));
            assertThat(webDriverOperations.getText(id("mailAddress")), is(
                    "fuga@moge.co.jp"));
            // セッションから破棄されていることを(hidden項目から)確認
            assertThat(((JavascriptExecutor) webDriverOperations.getWebDriver())
                    .executeScript("return arguments[0].innerHTML",
                            webDriverOperations.getWebDriver().findElement(id(
                                    "checkFormInSession"))).toString(), is(""));
        }
    }

    /**
     * <ul>
     * <li>@SessionAttributesを使ったウィザード形式の画面遷移で、入力チェックエラーが発生する。</li>
     * </ul>
     */
    @Test
    public void testSSMN0302002() {
        // メニュー画面の操作
        {
            webDriverOperations.click(id("ssmn0302002"));
        }

        // 入力画面1で項目入力
        {
            webDriverOperations.appendText(id("firstName"), "やました");
            webDriverOperations.appendText(id("lastName"), "はなこ");
            webDriverOperations.appendText(id("firstNameKana"), "ヤマシタ");
            webDriverOperations.appendText(id("lastNameKana"), "ハナコ");
            webDriverOperations.appendText(id("age"), "21");
            webDriverOperations.select(id("gender"), "女");
            webDriverOperations.click(id("addressForm"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 入力画面2で項目入力
        {
            webDriverOperations.appendText(id("zipCode"), "666-6666");
            webDriverOperations.appendText(id("state"), "神奈川県");
            webDriverOperations.appendText(id("city"), "相模原");
            webDriverOperations.appendText(id("address"), "１－１－１");
            webDriverOperations.click(id("otherForm"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 入力画面3で項目入力(入力チェックエラー発生)
        {
            webDriverOperations.appendText(id("occupation"), "会社員");
            webDriverOperations.appendText(id("mailAddress"), "hogemoge.co.jp");
            webDriverOperations.click(id("confirm"));

            assertThat(webDriverOperations.getText(id("mailAddress.errors")),
                    is("無効なメールアドレスです。"));
        }

        // 入力画面3で項目入力(入力チェックOK)
        {
            webDriverOperations.overrideText(id("mailAddress"),
                    "hoge@moge.co.jp");
            webDriverOperations.click(id("confirm"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 確認画面から完了画面に遷移
        {
            assertThat(webDriverOperations.getText(id("firstName")), is(
                    "やました"));
            assertThat(webDriverOperations.getText(id("lastName")), is("はなこ"));
            assertThat(webDriverOperations.getText(id("firstNameKana")), is(
                    "ヤマシタ"));
            assertThat(webDriverOperations.getText(id("lastNameKana")), is(
                    "ハナコ"));
            assertThat(webDriverOperations.getText(id("age")), is("21"));
            assertThat(webDriverOperations.getText(id("gender")), is("女"));
            assertThat(webDriverOperations.getText(id("zipCode")), is(
                    "666-6666"));
            assertThat(webDriverOperations.getText(id("state")), is("神奈川県"));
            assertThat(webDriverOperations.getText(id("city")), is("相模原"));
            assertThat(webDriverOperations.getText(id("address")), is("１－１－１"));
            assertThat(webDriverOperations.getText(id("occupation")), is(
                    "会社員"));
            assertThat(webDriverOperations.getText(id("mailAddress")), is(
                    "hoge@moge.co.jp"));
            // hidden項目から確認
            assertThat(((JavascriptExecutor) webDriverOperations.getWebDriver())
                    .executeScript("return arguments[0].innerHTML",
                            webDriverOperations.getWebDriver().findElement(id(
                                    "checkFormInSession"))).toString(), is(
                                            "jp.co.ntt.fw.spring.functionaltest.app.ssmn.MemberForm"));
            webDriverOperations.getWebDriver().findElement(By.xpath(
                    "(//button[@value='Submit'])[2]")).click();
        }

        // Formオブジェクトがセッションから破棄されることを確認(ログ)
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexMessage(
                    webDriverOperations.getXTrack(),
                    ".*HttpSessionEventLoggingListener",
                    "SESSIONID#.* attributeRemoved : memberForm=jp\\.co\\.ntt\\.fw\\.spring\\.functionaltest\\.app\\.ssmn\\.MemberForm");
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 完了画面
        {
            assertThat(webDriverOperations.getText(id("firstName")), is(
                    "やました"));
            assertThat(webDriverOperations.getText(id("lastName")), is("はなこ"));
            assertThat(webDriverOperations.getText(id("firstNameKana")), is(
                    "ヤマシタ"));
            assertThat(webDriverOperations.getText(id("lastNameKana")), is(
                    "ハナコ"));
            assertThat(webDriverOperations.getText(id("age")), is("21"));
            assertThat(webDriverOperations.getText(id("gender")), is("女"));
            assertThat(webDriverOperations.getText(id("zipCode")), is(
                    "666-6666"));
            assertThat(webDriverOperations.getText(id("state")), is("神奈川県"));
            assertThat(webDriverOperations.getText(id("city")), is("相模原"));
            assertThat(webDriverOperations.getText(id("address")), is("１－１－１"));
            assertThat(webDriverOperations.getText(id("occupation")), is(
                    "会社員"));
            assertThat(webDriverOperations.getText(id("mailAddress")), is(
                    "hoge@moge.co.jp"));
            // セッションから破棄されていることを(hidden項目から)確認
            assertThat(((JavascriptExecutor) webDriverOperations.getWebDriver())
                    .executeScript("return arguments[0].innerHTML",
                            webDriverOperations.getWebDriver().findElement(id(
                                    "checkFormInSession"))).toString(), is(""));
        }
    }

    /**
     * <ul>
     * <li>sessionの定義方法にcomponent-scanを使用してsessionスコープにbeanを格納した場合、格納、取得、破棄することができること。</li>
     * </ul>
     */
    @Test
    public void testSSMN0401001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("ssmn0401001"));
        }

        // 商品一覧画面で商品選択
        {
            webDriverOperations.click(id("itemLink_1"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 商品画面でカートに追加(入力1画面)
        {
            webDriverOperations.click(id("add"));
            waitAdded();
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 商品一覧画面に戻る
        {
            webDriverOperations.click(id("continueShoppingLink"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 商品一覧画面で商品選択
        {
            webDriverOperations.click(id("itemLink_2"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 商品画面でカートに追加(入力2画面)
        {
            webDriverOperations.click(id("add"));
            waitAdded();
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 注文画面に遷移
        {
            webDriverOperations.click(cssSelector(
                    "span.glyphicon.glyphicon-shopping-cart"));
        }

        // 注文画面で注文数を入力し注文確認画面へ
        {
            webDriverOperations.overrideText(id("cartItemForms0.quantity"),
                    "2");
            webDriverOperations.overrideText(id("cartItemForms1.quantity"),
                    "3");
            webDriverOperations.click(id("order"));
        }

        // （確認1）確認画面にセッションに格納したFormの中身が表示されていること
        // (入力1画面、入力2画面で入力した内容が表示されること)
        {
            // 入力1画面で入力した内容
            assertThat(webDriverOperations.getText(cssSelector("td.quantity")),
                    is("2"));
            // 入力2画面で入力した内容
            assertThat(webDriverOperations.getWebDriver().findElement(By.xpath(
                    "//div[@id='wrapper']/form/table/tbody/tr[2]/td[4]"))
                    .getText(), is("3"));
            // hidden項目から確認
            assertThat(((JavascriptExecutor) webDriverOperations.getWebDriver())
                    .executeScript("return arguments[0].innerHTML",
                            webDriverOperations.getWebDriver().findElement(id(
                                    "checkFormInSession"))).toString(), is(
                                            "jp.co.ntt.fw.spring.functionaltest.app.ssmn.Cart"));
            webDriverOperations.click(cssSelector("button.btn.btn-default"));
        }

        // （確認2）完了画面にセッションに格納したFormが存在しないこと旨のメッセージが表示されていること。
        // (セッションオブジェクトが破棄されていること)

        {

            // セッションから破棄されていることを(hidden項目から)確認
            assertThat(((JavascriptExecutor) webDriverOperations.getWebDriver())
                    .executeScript("return arguments[0].innerHTML",
                            webDriverOperations.getWebDriver().findElement(id(
                                    "checkFormInSession"))).toString(), is(
                                            "0"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }
    }

    /**
     * <ul>
     * <li>sessionの定義方法にBean定義ファイルを使用してsessionスコープにbeanを格納した場合、格納、取得、破棄することができること。</li>
     * </ul>
     */
    @Test
    public void testSSMN0401002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("ssmn0401002"));
        }

        // 商品一覧画面で商品選択
        {
            webDriverOperations.click(id("itemLink_3"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 商品画面でカートに追加(入力1画面)
        {
            webDriverOperations.click(id("add"));
            waitAdded();
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 商品一覧画面に戻る
        {
            webDriverOperations.click(id("continueShoppingLink"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 商品一覧画面で商品選択
        {
            webDriverOperations.click(id("itemLink_4"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 商品画面でカートに追加(入力2画面)
        {
            webDriverOperations.click(id("add"));
            waitAdded();
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 注文画面に遷移
        {
            webDriverOperations.click(cssSelector(
                    "span.glyphicon.glyphicon-shopping-cart"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 注文画面で注文数を入力し注文確認画面へ
        {
            webDriverOperations.overrideText(id("cartItemForms0.quantity"),
                    "4");
            webDriverOperations.overrideText(id("cartItemForms1.quantity"),
                    "5");
            webDriverOperations.click(id("order"));
        }

        // （確認1）確認画面にセッションに格納したFormの中身が表示されていること
        // (入力1画面、入力2画面で入力した内容が表示されること)
        {
            // 入力1画面で入力した内容
            assertThat(webDriverOperations.getText(cssSelector("td.quantity")),
                    is("4"));
            // 入力2画面で入力した内容
            assertThat(webDriverOperations.getWebDriver().findElement(By.xpath(
                    "//div[@id='wrapper']/form/table/tbody/tr[2]/td[4]"))
                    .getText(), is("5"));
            // hidden項目から確認
            assertThat(((JavascriptExecutor) webDriverOperations.getWebDriver())
                    .executeScript("return arguments[0].innerHTML",
                            webDriverOperations.getWebDriver().findElement(id(
                                    "checkFormInSession"))).toString(), is(
                                            "jp.co.ntt.fw.spring.functionaltest.app.ssmn.Cart"));
            webDriverOperations.click(cssSelector("button.btn.btn-default"));
        }

        // （確認2）完了画面にセッションに格納したFormが存在しないこと旨のメッセージが表示されていること。
        // (セッションオブジェクトが破棄されていること)
        {
            // セッションから破棄されていることを(hidden項目から)確認
            assertThat(((JavascriptExecutor) webDriverOperations.getWebDriver())
                    .executeScript("return arguments[0].innerHTML",
                            webDriverOperations.getWebDriver().findElement(id(
                                    "checkFormInSession"))).toString(), is(
                                            "0"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }
    }

    /**
     * <ul>
     * <li>sessionスコープにbeanを格納し、セッションに格納したbeanを利用後、beanを削除する。</li>
     * </ul>
     */
    @Test
    public void testSSMN0402001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("ssmn0402001"));
        }

        // 商品一覧画面で商品選択
        {
            webDriverOperations.click(id("itemLink_5"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 商品画面でカートに追加
        {
            webDriverOperations.click(id("add"));
            waitAdded();
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 商品一覧画面に戻る
        {
            webDriverOperations.click(id("continueShoppingLink"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 商品一覧画面で商品選択
        {
            webDriverOperations.click(id("itemLink_6"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 商品画面でカートに追加
        {
            webDriverOperations.click(id("add"));
            waitAdded();
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 注文画面に遷移
        {
            webDriverOperations.click(cssSelector(
                    "span.glyphicon.glyphicon-shopping-cart"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 注文画面で注文数を入力し注文確認画面へ
        {
            webDriverOperations.overrideText(id("cartItemForms0.quantity"),
                    "6");
            webDriverOperations.overrideText(id("cartItemForms1.quantity"),
                    "7");
            webDriverOperations.click(id("order"));
        }

        // （確認1）注文画面にて、商品画面、カート画面で入力した項目が表示されること。
        {
            // 入力1画面で入力した内容
            assertThat(webDriverOperations.getText(cssSelector("td.quantity")),
                    is("6"));
            // 入力2画面で入力した内容
            assertThat(webDriverOperations.getWebDriver().findElement(By.xpath(
                    "//div[@id='wrapper']/form/table/tbody/tr[2]/td[4]"))
                    .getText(), is("7"));
            // hidden項目から確認
            assertThat(((JavascriptExecutor) webDriverOperations.getWebDriver())
                    .executeScript("return arguments[0].innerHTML",
                            webDriverOperations.getWebDriver().findElement(id(
                                    "checkFormInSession"))).toString(), is(
                                            "jp.co.ntt.fw.spring.functionaltest.app.ssmn.Cart"));
            webDriverOperations.click(cssSelector("button.btn.btn-default"));
        }

        // （確認2）注文完了画面にセッションに格納したFormが存在しないこと旨のメッセージが表示されていること。
        // (セッションオブジェクトが破棄されていること)
        {
            // セッションから破棄されていることを(hidden項目から)確認
            assertThat(((JavascriptExecutor) webDriverOperations.getWebDriver())
                    .executeScript("return arguments[0].innerHTML",
                            webDriverOperations.getWebDriver().findElement(id(
                                    "checkFormInSession"))).toString(), is(
                                            "0"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }
    }

    /**
     * <ul>
     * <li>同Controller内の複数画面間の遷移時に、@ModelAttribute アノテーションが付与されたメソッドで生成されたセッションオブジェクトについて、格納、取得、破棄することができること。</li>
     * </ul>
     */
    @Ignore("seleniumで自動化(ブラウザタブ移動)できないため。")
    @Test
    public void testSSMN0601001() {

        // このテストケースはFirefoxのみ動作可能
        if (!(webDriverOperations.getWebDriver() instanceof FirefoxDriver)) {
            throw new UnsupportedOperationException("Firefox以外のブラウザでこの試験実施はできません。");
        }

        // 同一セッションを実現するため、タブで2つ起動
        WebElement body;

        {
            body = webDriverOperations.getWebDriver().findElement(By.tagName(
                    "body"));
            body.sendKeys(Keys.chord(Keys.CONTROL, "t"));
            webDriverOperations.displayPage(getPackageRootUrl());
        }

        // メニュー画面の操作
        {
            webDriverOperations.click(id("ssmn060100101"));
        }

        // 入力画面で項目入力
        {
            webDriverOperations.appendText(id("firstName"), "やまだ");
            webDriverOperations.appendText(id("lastName"), "はなこ");
            webDriverOperations.appendText(id("firstNameKana"), "ヤマダ");
            webDriverOperations.appendText(id("lastNameKana"), "ハナコ");
            webDriverOperations.appendText(id("age"), "20");
            webDriverOperations.appendText(id("gender"), "女");
            webDriverOperations.appendText(id("zipCode"), "222-2222");
            webDriverOperations.appendText(id("state"), "東京都");
            webDriverOperations.appendText(id("city"), "多摩");
            webDriverOperations.appendText(id("address"), "１－１－１");
            webDriverOperations.appendText(id("occupation"), "会社員");
            webDriverOperations.appendText(id("mailAddress"),
                    "hoge@hoge.co.jp");
            webDriverOperations.click(id("confirm"));
        }

        // もう一つのタブの入力操作実施
        {
            body.sendKeys(Keys.chord(Keys.CONTROL, "0"));
            webDriverOperations.click(id("ssmn060100102"));
            webDriverOperations.click(id("confirm"));
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 1つめのタブの処理実施(delay)
        {
            webDriverOperations.getWebDriver().findElement(By.xpath(
                    "(//button[@value='Submit'])[2]")).click();
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // 2つめのタブの処理実施(normal)
        {
            body.sendKeys(Keys.chord(Keys.CONTROL, "2"));
            webDriverOperations.getWebDriver().findElement(By.xpath(
                    "(//button[@value='Submit'])[2]")).click();
        }

        // ログの確認
        {
            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertNotContainsWarnAndError(
                    webDriverOperations.getXTrack());
        }

        // セッション同期ができているか確認
        {

        }
    }

    private void waitAdded() {
        webDriverOperations.waitForDisplayed(ExpectedConditions
                .textToBePresentInElementLocated(By.id("resultMessage"),
                        "選択した商品をカートに追加しました。"));
    }

}
