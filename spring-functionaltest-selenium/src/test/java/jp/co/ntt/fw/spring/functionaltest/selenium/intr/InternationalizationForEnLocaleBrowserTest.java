/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.selenium.intr;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.id;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

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
