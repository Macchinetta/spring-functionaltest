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
package jp.co.ntt.fw.spring.functionaltest.selenium.dtac;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.id;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.test.annotation.IfProfileValue;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

//Thymeleaf版未実装のためJSPのみ実行
@IfProfileValue(name = "test.environment.view", values = { "jsp" })
public class DataAccessCommonTest extends FunctionTestSupport {

    private void clickLink(By by) {
        webDriverOperations.click(by);
    }

    private void clickButton(By by) {
        webDriverOperations.click(by);
    }

    private void setInputString(By by, String value) {
        webDriverOperations.overrideText(by, value);
    }

    private List<String> getValueList(By by) {
        List<WebElement> elementList = webDriverOperations.getWebDriver()
                .findElements(by);
        List<String> valueList = new ArrayList<String>();
        for (WebElement element : elementList) {
            valueList.add(element.getText());
        }
        return valueList;
    }

    /**
     * <ul>
     * <li>アプリケーションサーバで定義したDataSourceを使用する場合、データベースアクセスができることを確認。</li>
     * </ul>
     */
    @Test
    public void testDTAC0101001() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dtac0101001"));
        }

        // ログイン
        {
            setInputString(id("username"), "Jack");
            setInputString(id("password"), "spring1234");
            clickButton(id("login"));
        }

        // 確認
        {
            List<String> usernameList = getValueList(id("username"));
            assertThat(usernameList.size(), is(2));
            assertThat(usernameList.contains("Jack"), is(true));
            assertThat(usernameList.contains("Tom"), is(true));
            clickButton(id("back"));
        }

    }

    /**
     * <ul>
     * <li>Bean定義したDataSourceを使用する場合、データベースアクセスができることを確認。</li>
     * </ul>
     */
    @Ignore("ローカル実行時のH2で確認可能")
    public void testDTAC0101002() {
    }

    /**
     * <ul>
     * <li>データの分類毎にデータベースやスキーマが分かれている場合、それぞれデータベースアクセスができることを確認。</li>
     * </ul>
     */
    @Ignore("testDTAC0101001同時実行")
    public void testDTAC0102001() {
        // 静的なデータソース切り替えのガイドラインを記述したらここの試験も作成する。
    }

    /**
     * <ul>
     * <li>利用者によって使用するデータベースやスキーマが分かれている場合、それぞれデータベースアクセスができることを確認。</li>
     * </ul>
     */
    @Test
    public void testDTAC0102002() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dtac0102002"));
        }

        // データソース選択
        {
            clickButton(id("dataSourceOpen"));
        }

        // 確認
        {
            List<String> usernameList = getValueList(id("username"));
            assertThat(usernameList.size(), is(2));
            assertThat(usernameList.contains("Jack"), is(true));
            assertThat(usernameList.contains("Tom"), is(true));
        }

        // 疑似アプリ画面表示
        {
            webDriverOperations.displayPage(getPackageRootUrl());
        }

        // 疑似アプリ画面表示
        {
            clickLink(id("dtac0102002"));
        }

        // データソース選択
        {
            clickButton(id("dataSourceClose"));
        }

        // 確認
        {
            List<String> usernameList = getValueList(id("username"));
            assertThat(usernameList.size(), is(2));
            assertThat(usernameList.contains("Igor"), is(true));
            assertThat(usernameList.contains("Ken"), is(true));
        }
    }

    /**
     * <ul>
     * <li>JDBC例外が発生した場合に、データアクセス例外へ変換されることを確認。</li>
     * </ul>
     */
    @Test
    public void testDTAC0401001() {

        // 疑似アプリ画面表示
        {
            clickLink(id("dtac0401001"));
        }

        // ログイン
        {
            setInputString(id("username"), "Jack");
            setInputString(id("password"), "spring1234");
            clickButton(id("login"));
        }

        // ユーザ登録
        {
            clickButton(id("form"));
            setInputString(id("username"), "Jack");
            setInputString(id("password"), "Spring1234");
            setInputString(id("enabled"), "1");
            setInputString(id("authority"), "ROLE_STAFF");
            clickButton(id("register"));
        }

        // ログ確認
        {
            dbLogAssertOperations.waitForAssertion(1000);
            dbLogAssertOperations.assertContainsByRegexMessage(
                    ".*jp.co.ntt.fw.spring.functionaltest.app.dtac.DTACUserController.*",
                    ".*DuplicateKeyExeption occur!!.*");
            dbLogAssertOperations.assertContainsByRegexStackTrace(
                    ".*SQLErrorCodeSQLExceptionTranslator.*");
        }
    }

    /**
     * <ul>
     * <li>一意制約違反が発生した場合に、アプリケーションコードでハンドリングできることを確認。</li>
     * </ul>
     */
    @Ignore("testDTAC0401001同時実行")
    public void testDTAC0401002() {
    }

}
