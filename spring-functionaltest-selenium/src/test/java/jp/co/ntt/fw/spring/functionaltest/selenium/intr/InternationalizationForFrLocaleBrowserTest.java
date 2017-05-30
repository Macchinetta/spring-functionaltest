/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.selenium.intr;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.id;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

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
        WebDriver frDriver = webDriverCreator
                .createLocaleSpecifiedDriver("fr, FR");
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
