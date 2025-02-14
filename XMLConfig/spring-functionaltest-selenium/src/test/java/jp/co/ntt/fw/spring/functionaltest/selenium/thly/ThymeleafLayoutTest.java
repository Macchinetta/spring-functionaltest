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
package jp.co.ntt.fw.spring.functionaltest.selenium.thly;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.id;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

public class ThymeleafLayoutTest extends FunctionTestSupport {

    /**
     * <ul>
     * <li>th:insertの動作を確認する。</li>
     * </ul>
     */
    @Test
    public void testTHLY0101001() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("thly0101001"));
        }

        // フラグメントのHTMLが挿入されていることの確認
        {
            assertTrue(webDriverOperations.exists(By.xpath(
                    "id('authority-input')/div[@class='col-md-4']/select/option[@value='STAFF_MNG']")));
            assertTrue(webDriverOperations.exists(By.xpath(
                    "id('authority-input')/div[@class='col-md-4']/select/option[@value='SHOW_SHOPPING_MNG']")));
        }
    }

    /**
     * <ul>
     * <li>th:replaceの動作を確認する。</li>
     * </ul>
     */
    @Test
    public void testTHLY0101002() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("thly0101002"));
        }

        // 入力
        {
            webDriverOperations.overrideText(By.id("firstName"), "Ichiro");
            webDriverOperations.overrideText(By.id("lastName"), "Suzuki");
            webDriverOperations.select(By.id("authority"), "Stock Management");
            webDriverOperations.click(By.name("register"));
        }

        // 登録完了画面に遷移したことをチェック
        {
            assertThat(webDriverOperations.getText(By.id("bodyTitle")), is("Register Result"));
        }

        // フラグメントのHTMLが挿入されていることの確認
        {
            assertTrue(webDriverOperations.exists(By.xpath(
                    "id('authority-input')/div[@class='col-md-4']/select[@disabled='disabled']/option[@value='STAFF_MNG']")));
            assertTrue(webDriverOperations.exists(By.xpath(
                    "id('authority-input')/div[@class='col-md-4']/select[@disabled='disabled']/option[@value='SHOW_SHOPPING_MNG']")));
            assertTrue(webDriverOperations.exists(By.xpath(
                    "id('authority-input')/div[@class='col-md-4']/select[@disabled='disabled']/option[@value='STOCK_MNG' and @selected='selected']")));
            assertFalse(webDriverOperations
                    .exists(By.xpath("id('authority-input')/div[@class='col-md-4']/span")));
        }
    }

    /**
     * <ul>
     * <li>th:includeの動作を確認する。</li>
     * </ul>
     */
    @Test
    public void testTHLY0101003() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("thly0101003"));
        }

        // フラグメントのコンテンツのみ挿入されていることの確認
        {
            assertFalse(webDriverOperations
                    .exists(By.xpath("id('authority-input')/div[@class='col-md-4']/div/div")));
            assertTrue(webDriverOperations.exists(By.xpath(
                    "id('authority-input')/div[@class='col-md-4']/div/select/option[@value='STAFF_MNG']")));
            assertTrue(webDriverOperations.exists(By.xpath(
                    "id('authority-input')/div[@class='col-md-4']/div/select/option[@value='SHOW_SHOPPING_MNG']")));
            WebElement selectElement = webDriverOperations.getWebDriver().findElement(
                    By.xpath("id('authority-input')/div[@class='col-md-4']/div/select"));
            assertThat(selectElement.getAttribute("disabled"), is(nullValue()));
            assertThat(selectElement.getAttribute("style"), is(""));
        }
    }

    /**
     * <ul>
     * <li>th:fragmentで定義したフラグメントで引数（変数）をとることができることを確認する。</li>
     * </ul>
     */
    @Test
    public void testTHLY0101004() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("thly0101004"));
        }

        // 入力
        {
            webDriverOperations.overrideText(By.id("firstName"), "Ichiro");
            webDriverOperations.overrideText(By.id("lastName"), "Suzuki");
            webDriverOperations.select(By.id("authority"), "Stock Management");
            webDriverOperations.click(By.name("update"));
        }

        // 変更完了画面に遷移したことをチェック
        {
            assertThat(webDriverOperations.getText(By.id("bodyTitle")), is("Update Result"));
        }

        // フラグメント部分のHTMLで引数として受け取った値（変数）を出力できることの確認
        {
            assertTrue(webDriverOperations.exists(By.xpath(
                    "id('authority-input')/div[@class='col-md-4']/div/select[@disabled='disabled' and @style='background-color: whitesmoke;']/option[@value='STAFF_MNG']")));
            assertTrue(webDriverOperations.exists(By.xpath(
                    "id('authority-input')/div[@class='col-md-4']/div/select[@disabled='disabled' and @style='background-color: whitesmoke;']/option[@value='SHOW_SHOPPING_MNG']")));
            assertTrue(webDriverOperations.exists(By.xpath(
                    "id('authority-input')/div[@class='col-md-4']/div/select[@disabled='disabled' and @style='background-color: whitesmoke;']/option[@value='STOCK_MNG' and @selected='selected']")));
        }
    }

    /**
     * <ul>
     * <li>th:fragmentで定義したフラグメントで引数（フラグメント）をとることができることを確認する。</li>
     * </ul>
     */
    @Test
    public void testTHLY0101005() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("thly0101005"));
        }

        // フラグメント部分のHTMLで引数として受け取った値（フラグメント）を出力できることの確認
        {
            assertTrue(webDriverOperations.exists(By.xpath(
                    "//form[@method='post']//div[@id='authority-input']/div[@class='col-md-4']/select[@class='form-control']/option[@value='STAFF_MNG']")));

            assertTrue(webDriverOperations.exists(By.xpath(
                    "//form[@method='post']//div[@id='authority-input']/div[@class='col-md-4']/select[@class='form-control']/option[@value='SHOW_SHOPPING_MNG']")));
            List<WebElement> optionElements = webDriverOperations.getWebDriver().findElements(
                    By.xpath("id('authority-input')/div[@class='col-md-4']/select/option"));
            assertThat(optionElements.size(), is(5));
        }

        // 入力
        {
            webDriverOperations.overrideText(By.id("firstName"), "Ichiro");
            webDriverOperations.overrideText(By.id("lastName"), "Suzuki");
            webDriverOperations.select(By.id("authority"), "Stock Management");
            webDriverOperations.click(By.name("search"));
        }

        // 検索完了画面に遷移したことをチェック
        {
            assertThat(webDriverOperations.getText(By.id("bodyTitle")), is("Search Result"));
        }

        // フラグメント部分のHTMLで引数として受け取った値（フラグメント）を出力できることの確認
        {
            assertTrue(webDriverOperations.exists(By.xpath(
                    "//form[@method='post']//div[@id='authority-input']/div[@class='col-md-4']/select[@class='form-control']/option[@value='STAFF_MNG']")));

            assertTrue(webDriverOperations.exists(By.xpath(
                    "//form[@method='post']//div[@id='authority-input']/div[@class='col-md-4']/select[@class='form-control']/option[@value='SHOW_SHOPPING_MNG']")));
            List<WebElement> optionElements = webDriverOperations.getWebDriver().findElements(
                    By.xpath("id('authority-input')/div[@class='col-md-4']/select/option"));
            assertThat(optionElements.size(), is(5));
        }
    }

    /**
     * <ul>
     * <li>レイアウトを適用した画面を表示した場合、定義したレイアウトが有効になることを確認する。</li>
     * </ul>
     */
    @Test
    public void testTHLY0201001() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("thly0201001"));
        }

        // レイアウトが適用された画面が表示されることの確認
        {
            // template.html
            assertThat(webDriverOperations.getTitle(), is("Create Staff Information"));
            // header.html
            assertThat(webDriverOperations.getText(By.xpath("//body/h1/a")),
                    is("Staff Management System"));
            // createForm.html
            assertThat(webDriverOperations.getText(By.xpath("//body/h2")),
                    is("Create Staff Information"));
            // footer.html
            assertThat(webDriverOperations.getText(By.xpath("//body/p")),
                    is("Copyright \u00A9 20XX CompanyName"));
        }
    }

    /**
     * <ul>
     * <li>個別画面にtitleタグを定義せず、no-operasion-token"_"をパラメータとして渡した場合、テンプレートで定義したデフォルトのタイトルが有効になることを確認する。</li>
     * </ul>
     */
    @Test
    public void testTHLY0201002() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("thly0201002"));
        }

        // レイアウトが適用された画面が表示されTitleタグがデフォルトのままであることの確認
        {
            // template.html
            assertThat(webDriverOperations.getTitle(), is("Staff Management System"));
            // header.html
            assertThat(webDriverOperations.getText(By.xpath("//body/h1/a")),
                    is("Staff Management System"));
            // createForm.html
            assertThat(webDriverOperations.getText(By.xpath("//body/h2")),
                    is("Create Staff Information"));
            // footer.html
            assertThat(webDriverOperations.getText(By.xpath("//body/p")),
                    is("Copyright \u00A9 20XX CompanyName"));
        }
    }

    /**
     * <ul>
     * <li>フラグメントエクスプレッションの~{}を省略してもフラグメントが取得できることを確認する。</li>
     * </ul>
     */
    @Test
    public void testTHLY0201003() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("thly0201003"));
        }

        // レイアウトが適用された画面が表示されることの確認
        {
            // template.html
            assertThat(webDriverOperations.getTitle(), is("Create Staff Information"));
            // header.html
            assertThat(webDriverOperations.getText(By.xpath("//body/h1/a")),
                    is("Staff Management System"));
            // createForm.html
            assertThat(webDriverOperations.getText(By.xpath("//body/h2")),
                    is("Create Staff Information"));
            // footer.html
            assertThat(webDriverOperations.getText(By.xpath("//body/p")),
                    is("Copyright \u00A9 20XX CompanyName"));
        }
    }

    /**
     * <ul>
     * <li>任意のパラメータを個別画面からテンプレートに渡せることを確認する。</li>
     * </ul>
     */
    @Test
    public void testTHLY0201004() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("thly0201004"));
        }

        // レイアウトが適用された画面が表示され追加パラメータが埋め込まれることの確認
        {
            // template.html
            assertThat(webDriverOperations.getTitle(), is("Create Staff Information"));
            // header.html
            assertThat(webDriverOperations.getText(By.xpath("//body/h1/a")),
                    is("Staff Management System"));
            // createForm.html
            assertThat(webDriverOperations.getText(By.xpath("//body/h2")),
                    is("Create Staff Information"));
            // footer.html
            assertThat(webDriverOperations.getText(By.xpath("//body/p")),
                    is("Copyright \u00A9 20XX CompanyName"));
            // script tag
            assertTrue(webDriverOperations.exists(
                    By.xpath("//head/script[contains(@src,'/resources/app/js/sample.js')]")));
            List<WebElement> scriptElements =
                    webDriverOperations.getWebDriver().findElements(By.xpath("//head/script"));
            assertThat(scriptElements.size(), is(1));
        }
    }

    /**
     * <ul>
     * <li>作成したHTML部品(メッセージパネル)が適用されることを確認する。</li>
     * </ul>
     */
    @Test
    public void testTHLY0301001() throws IOException {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("thly0301001"));
        }

        // 部品化したメッセージパネルが表示されていることの確認
        {
            assertTrue(webDriverOperations
                    .exists(By.xpath("//div[@class='error']/div[@class='alert alert-error']")));
            assertThat(webDriverOperations.getText(By.xpath("//div[@class='error']/span")),
                    is("[e.sf.fw.8001] Business error occurred!"));
            assertThat(webDriverOperations.getText(By.xpath("//div[@class='error']/div/ul/li")),
                    is("Create Staff error occurred!"));
        }
    }

}
