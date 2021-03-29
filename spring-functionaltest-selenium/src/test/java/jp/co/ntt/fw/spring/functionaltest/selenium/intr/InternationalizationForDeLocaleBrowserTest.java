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
package jp.co.ntt.fw.spring.functionaltest.selenium.intr;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.id;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;
import jp.co.ntt.fw.spring.functionaltest.selenium.TemplateEngineName;

public class InternationalizationForDeLocaleBrowserTest extends
                                                        FunctionTestSupport {

    private static WebDriver deDriver;

    public InternationalizationForDeLocaleBrowserTest() {
        disableDefaultWebDriver();
    }

    @Before
    public void setUpWebDriver() {
        if (deDriver == null) {
            deDriver = webDriverCreator.createLocaleSpecifiedDriver("de, DE");
        }
        setCurrentWebDriver(deDriver);
    }

    /**
     * <ul>
     * <li>ブラウザのロケールと一致するロケールのメッセージプロパティファイルがない場合、<br/>
     * デフォルトのメッセージプロパティファイルから値が取得され、ブラウザに表示されることの確認。</li>
     * </ul>
     */
    @Test
    public void testINTR0101003() {
        // ドイツ語Local
        webDriverOperations.click(id("intr0101003"));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("名前"));
        assertThat(webDriverOperations.getText(id("gender")), is("性別"));
        assertThat(webDriverOperations.getText(id("age")), is("年齢"));
        assertThat(webDriverOperations.getText(id("ageValue")), is("⑳"));

    }

    /**
     * <ul>
     * <li>ブラウザのロケールと一致するロケールのメッセージプロパティファイルがない場合かつデフォルトのメッセージプロパティファイルもない場合</li>
     * <li>JSP版はJspTagException がスローされることの確認。</li>
     * <li>Thymeleaf版はメッセージIDがそのまま出力されることの確認。</li>
     * </ul>
     */
    @Test
    public void testINTR0101004() {
        //template engineの取得
        TemplateEngineName name = webDriverOperations.getTemplateEngineName();

        // ドイツ語Locale
        webDriverOperations.click(id("intr0101004"));

        if (name == TemplateEngineName.JSP) {
            // 出力メッセージの確認
            assertThat(webDriverOperations.getTitle(), is(
                    "Unhandled System Error!"));

            dbLogAssertOperations.waitForAssertion();
            dbLogAssertOperations.assertContainsByRegexStackTrace(
                    "Caused by: javax\\.servlet\\.jsp\\.JspTagException: No message found under code.*for locale.*\\.");
        } else {
            assertThat(webDriverOperations.getText(By.id("screenTitle")), is(
                    "??title.intr.customerDetails_de??"));
        }

    }

    /**
     * <ul>
     * <li>Springのロケールを解決する機能にサポートロケールとデフォルトロケールを定義し、<br/>
     * サポート対象外のロケールを指定した場合、デフォルトロケールが使用されることを確認する。</li>
     * </ul>
     */
    @Test
    public void testINTR0101007() {
        // ドイツ語Local
        webDriverOperations.click(id("intr0101007"));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("Name"));
        assertThat(webDriverOperations.getText(id("gender")), is("Gender"));
        assertThat(webDriverOperations.getText(id("age")), is("Age"));

    }

}
