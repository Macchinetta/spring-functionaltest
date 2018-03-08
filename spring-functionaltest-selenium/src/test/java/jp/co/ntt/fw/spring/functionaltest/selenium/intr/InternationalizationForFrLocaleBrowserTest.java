/*
 * Copyright 2014-2018 NTT Corporation.
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
 */
package jp.co.ntt.fw.spring.functionaltest.selenium.intr;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.openqa.selenium.By.*;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.test.annotation.IfProfileValue;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

//Thymeleaf版未実装のためJSPのみ実行
@IfProfileValue(name = "test.environment.view", values = { "jsp" })
public class InternationalizationForFrLocaleBrowserTest extends
                                                        FunctionTestSupport {

    public InternationalizationForFrLocaleBrowserTest() {
        disableDefaultWebDriver();
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

        webDriverOperations.click(id("intr0102001"));

        // 出力メッセージの確認
        assertThat(webDriverOperations.getText(id("name")), is("nom"));
        assertThat(webDriverOperations.getText(id("gender")), is("gender"));
        assertThat(webDriverOperations.getText(id("age")), is("âge"));
        assertThat(webDriverOperations.getText(id("ageValue")), is("20"));

        webDriverOperations.saveScreenCapture();
    }
}
