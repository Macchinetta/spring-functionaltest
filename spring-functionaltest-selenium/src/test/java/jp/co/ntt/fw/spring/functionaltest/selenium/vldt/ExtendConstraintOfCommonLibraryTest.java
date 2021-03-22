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
package jp.co.ntt.fw.spring.functionaltest.selenium.vldt;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.id;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

/**
 * VLDT 入力チェックテスト<br>
 * <p>
 * VLDT08 共通ライブラリが用意する入力チェックルール
 * </p>
 */
public class ExtendConstraintOfCommonLibraryTest extends FunctionTestSupport {

    private static WebDriver driver;

    private String validate = "validate";

    private String errors = ".errors";

    private String currentLocale = "en";

    public ExtendConstraintOfCommonLibraryTest() {
        super.disableDefaultWebDriver();
    }

    @Before
    public void setUp() {
        if (driver == null) {
            driver = webDriverCreator.createLocaleSpecifiedDriver(
                    currentLocale);
        }
        super.setCurrentWebDriver(driver);
    }

    /**
     * VLDT0801001
     * <ul>
     * <li>共通ライブラリで提供している@Compareを拡張したアノテーションで入力チェックが出来ることを確認する</li>
     * </ul>
     */
    @Test
    public void testVLDT0801001() {
        String testId = "vldt0801001";
        String target = "password";
        String confirmedTarget = "confirmPassword";
        String errorMessage = "{left} and {right} must be same.";

        webDriverOperations.click(id(testId));
        // 同じ文字列を入力した場合はエラーが出ない
        webDriverOperations.overrideText(id(target), "password");
        webDriverOperations.overrideText(id(confirmedTarget), "password");
        webDriverOperations.click(id(validate));

        assertThat(webDriverOperations.exists(id(target + errors)), is(false));

        // 異なる文字列を入力した場合はエラーが出る
        webDriverOperations.overrideText(id(target), "password");
        webDriverOperations.overrideText(id(confirmedTarget),
                "confirmPassword");
        webDriverOperations.click(id(validate));

        assertThat(webDriverOperations.getText(id(target + errors)), is(
                errorMessage.replace("{left}", target).replace("{right}",
                        confirmedTarget)));
    }
}
