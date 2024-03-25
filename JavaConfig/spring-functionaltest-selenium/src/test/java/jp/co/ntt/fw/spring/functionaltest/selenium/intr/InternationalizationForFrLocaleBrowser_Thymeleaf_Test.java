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

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

public class InternationalizationForFrLocaleBrowser_Thymeleaf_Test extends
                                                        FunctionTestSupport {

    private static String VIEW_TYPE = "thymeleaf";

    public InternationalizationForFrLocaleBrowser_Thymeleaf_Test() {
        disableDefaultWebDriver();
    }

    /**
     * <ul>
     * <li>Springのロケールを解決する機能にサポートロケールとデフォルトロケールを定義し、<br/>
     * サポートロケールを指定した場合、指定したロケールが使用されることを確認する。</li>
     * </ul>
     */
    @Test
    public void testINTR0101005() {
        // フランス語Locale
        WebDriver frDriver = webDriverCreator.createLocaleSpecifiedDriver(
                "fr, FR");
        setCurrentWebDriver(frDriver);

        webDriverOperations.click(id("intr0101005_" + VIEW_TYPE));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("nom"));
        assertThat(webDriverOperations.getText(id("gender")), is("gender"));
        assertThat(webDriverOperations.getText(id("age")), is("Ã¢ge"));

    }

    /**
     * <ul>
     * <li>ブラウザのロケール、JVMのロケール、WebサーバのOSのロケールが異なる場合、<br/>
     * ブラウザに設定されたロケールで値が表示されることの確認。</li>
     * </ul>
     */
    @Ignore("【手動実行】 WebサーバのJVMのロケールを'en'に変更する必要があるため。")
    @Test
    public void testINTR0102001() {
        // フランス語Locale
        WebDriver frDriver = webDriverCreator.createLocaleSpecifiedDriver(
                "fr, FR");
        setCurrentWebDriver(frDriver);

        webDriverOperations.click(id("intr0102001_" + VIEW_TYPE));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("nom"));
        assertThat(webDriverOperations.getText(id("gender")), is("gender"));
        assertThat(webDriverOperations.getText(id("age")), is("âge"));
        assertThat(webDriverOperations.getText(id("ageValue")), is("20"));

        webDriverOperations.saveScreenCapture();
    }
}
