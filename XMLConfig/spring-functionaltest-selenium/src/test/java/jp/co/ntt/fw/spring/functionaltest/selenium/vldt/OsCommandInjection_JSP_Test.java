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
package jp.co.ntt.fw.spring.functionaltest.selenium.vldt;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.openqa.selenium.By.id;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import jp.co.ntt.fw.spring.functionaltest.selenium.BrowserLocale;

/**
 * VLDT 入力チェックテスト<br>
 * <p>
 * VLDT07 OSコマンドインジェクション
 * </p>
 */
public class OsCommandInjection_JSP_Test extends ValidationTestSupport {

    private static WebDriver driver;

    private BrowserLocale currentLocale = BrowserLocale.ENGLISH_US;

    private static final String VIEW_TYPE = "jsp";

    public OsCommandInjection_JSP_Test() {
        super.disableDefaultWebDriver();
    }

    @Before
    public void setUp() {
        if (driver == null) {
            driver = webDriverCreator.createLocaleSpecifiedDriver(currentLocale);
        }
        super.setCurrentWebDriver(driver);
    }

    /**
     * VLDT0701001
     * <ul>
     * <li>実行コマンドに実行を許可するコマンドが指定された場合、コマンド実行正常を返却できることを確認する</li>
     * </ul>
     */
    @Test
    public void testVLDT0701001() {
        String testId = "vldt0701001";
        String target = "cmdStr";

        // テスト画面表示
        {
            webDriverOperations.click(id(testId + "_" + VIEW_TYPE));
        }

        // 実施条件1
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(target), "batch00.sh");
                webDriverOperations.click(id(ID_VALIDATE));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.exists(id(target + ID_ERRORS)), is(false));
            }
        }

        // 実施条件2
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(target), "batch09.sh");
                webDriverOperations.click(id(ID_VALIDATE));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.exists(id(target + ID_ERRORS)), is(false));
            }
        }
    }

    /**
     * VLDT0701002
     * <ul>
     * <li>実行コマンドに実行が許可されないコマンドが指定された場合、チェックエラーを返却できることを確認する</li>
     * </ul>
     */
    @Test
    public void testVLDT0701002() {
        String testId = "vldt0701002";
        String target = "cmdStr";
        String errorMessage = "permit command name: batch00.sh - batch09.sh";

        // テスト画面表示
        {
            webDriverOperations.click(id(testId + "_" + VIEW_TYPE));
        }

        // 実施条件1
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(target), "../batch00.sh");
                webDriverOperations.click(id(ID_VALIDATE));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.getText(id(target + ID_ERRORS)), is(errorMessage));
            }
        }

        // 実施条件2
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(target), "reboot");
                webDriverOperations.click(id(ID_VALIDATE));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.getText(id(target + ID_ERRORS)), is(errorMessage));
            }
        }
    }

    /**
     * VLDT0702001
     * <ul>
     * <li>実行引数にアプリケーションで許可する文字列が指定された場合、コマンド実行正常を返却できることを確認する</li>
     * </ul>
     */
    @Test
    public void testVLDT0702001() {
        String testId = "vldt0702001";
        String target = "arg";

        // テスト画面表示
        {
            webDriverOperations.click(id(testId + "_" + VIEW_TYPE));
        }

        // 実施条件1
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(target), "a=1");
                webDriverOperations.click(id(ID_VALIDATE));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.exists(id(target + ID_ERRORS)), is(false));
            }
        }

        // 実施条件2
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(target), "Hello_01");
                webDriverOperations.click(id(ID_VALIDATE));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.exists(id(target + ID_ERRORS)), is(false));
            }
        }
    }

    /**
     * VLDT0702002
     * <ul>
     * <li>実行引数にアプリケーションで許可されない文字列が指定された場合、チェックエラーを返却できることを確認する</li>
     * </ul>
     */
    @Test
    public void testVLDT0702002() {
        String testId = "vldt0702002";
        String target = "arg";
        String errorMessage = "permit parameter characters and symbols: alphanumeric, =, _";

        // テスト画面表示
        {
            webDriverOperations.click(id(testId + "_" + VIEW_TYPE));
        }

        // 実施条件1
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(target), "exec.sh; cat /etc/passwd");
                webDriverOperations.click(id(ID_VALIDATE));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.getText(id(target + ID_ERRORS)), is(errorMessage));
            }
        }

        // 実施条件2
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(target), "; reboot");
                webDriverOperations.click(id(ID_VALIDATE));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.getText(id(target + ID_ERRORS)), is(errorMessage));
            }
        }
    }
}
