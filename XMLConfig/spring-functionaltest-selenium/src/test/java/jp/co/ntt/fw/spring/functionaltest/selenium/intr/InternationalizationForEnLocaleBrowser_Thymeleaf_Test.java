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
package jp.co.ntt.fw.spring.functionaltest.selenium.intr;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.openqa.selenium.By.id;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import jp.co.ntt.fw.spring.functionaltest.selenium.BrowserLocale;
import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

public class InternationalizationForEnLocaleBrowser_Thymeleaf_Test extends FunctionTestSupport {

    private static WebDriver driver;

    private static final String VIEW_TYPE = "thymeleaf";

    public InternationalizationForEnLocaleBrowser_Thymeleaf_Test() {
        disableDefaultWebDriver();

    }

    @Before
    public void setUpWebDriver() {
        if (driver == null) {
            driver = webDriverCreator.createLocaleSpecifiedDriver(BrowserLocale.ENGLISH_US);
        }
        setCurrentWebDriver(driver);
    }

    /**
     * <ul>
     * <li>Springのロケールを解決する機能をBean定義した場合、JVMとブラウザのロケールでは、 <br>
     * ブラウザのロケールが優先されることを確認、 <br>
     * マルチバイト文字が設定値どおり出力可能なことを確認する。</li>
     * </ul>
     */
    @Test
    public void testINTR0101001() {
        // 英語Locale
        webDriverOperations.click(id("intr0101001_" + VIEW_TYPE));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("Name"));
        assertThat(webDriverOperations.getText(id("gender")), is("Gender"));
        assertThat(webDriverOperations.getText(id("age")), is("Age"));
        assertThat(webDriverOperations.getText(id("ageValue")), is("20"));

        webDriverOperations.saveScreenCapture("ForEnLocaleBrowser");
    }

    /**
     * <ul>
     * <li>Springのロケールを解決する機能をBean定義を省略した場合、<br/>
     * JVMとブラウザのロケールでは、ブラウザのロケールが優先されることを確認する。</li>
     * </ul>
     */
    @Test
    public void testINTR0101002() {
        // 英語Locale
        webDriverOperations.click(id("intr0101002_" + VIEW_TYPE));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("Name"));
        assertThat(webDriverOperations.getText(id("gender")), is("Gender"));
        assertThat(webDriverOperations.getText(id("age")), is("Age"));
        assertThat(webDriverOperations.getText(id("ageValue")), is("20"));

        webDriverOperations.saveScreenCapture("ForEnLocaleBrowser");
    }

    /**
     * <ul>
     * <li>Springのロケールを解決する機能にサポートロケールとデフォルトロケールを定義し、<br/>
     * サポートロケールを指定した場合、指定したロケールが使用されることを確認する。</li>
     * </ul>
     */
    @Test
    public void testINTR0101005() {
        // 英語Locale
        webDriverOperations.click(id("intr0101005_" + VIEW_TYPE));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("Name"));
        assertThat(webDriverOperations.getText(id("gender")), is("Gender"));
        assertThat(webDriverOperations.getText(id("age")), is("Age"));

    }

}
