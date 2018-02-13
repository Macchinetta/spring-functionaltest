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
package jp.co.ntt.fw.spring.functionaltest.selenium.intr;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.openqa.selenium.By.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.test.annotation.IfProfileValue;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

//Thymeleaf版未実装のためJSPのみ実行
@IfProfileValue(name = "test.environment.view", values = { "jsp" })
public class InternationalizationForEnLocaleBrowserTest extends
                                                        FunctionTestSupport {

    private static WebDriver enDriver;

    public InternationalizationForEnLocaleBrowserTest() {
        disableDefaultWebDriver();

    }

    @Before
    public void setUpWebDriver() {
        if (enDriver == null) {
            enDriver = webDriverCreator.createLocaleSpecifiedDriver("en");
        }
        setCurrentWebDriver(enDriver);
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
        webDriverOperations.click(id("intr0101001"));

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
        webDriverOperations.click(id("intr0101002"));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("Name"));
        assertThat(webDriverOperations.getText(id("gender")), is("Gender"));
        assertThat(webDriverOperations.getText(id("age")), is("Age"));
        assertThat(webDriverOperations.getText(id("ageValue")), is("20"));

        webDriverOperations.saveScreenCapture("ForEnLocaleBrowser");
    }

}
