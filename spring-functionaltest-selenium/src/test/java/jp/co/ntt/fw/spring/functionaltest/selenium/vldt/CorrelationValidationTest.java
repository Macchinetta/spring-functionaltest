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

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.openqa.selenium.By.id;

import java.util.HashMap;
import java.util.Map;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

/**
 * VLDT 入力チェックテスト<br>
 * <p>
 * VLDT02 相関項目チェックのテストケース
 * </p>
 */
public class CorrelationValidationTest extends FunctionTestSupport {

    private static WebDriver driver;

    private String validate = "validate";

    private String errors = ".errors";

    private String currentLocale = "en";

    private static Map<String, String> localeDateFormat;

    public CorrelationValidationTest() {
        localeDateFormat = new HashMap<String, String>();
        localeDateFormat.put("ja", "yyyy/MM/dd");
        localeDateFormat.put("en", "MM/dd/yyyy");

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
     * VLDT0201001
     * <ul>
     * <li>入力チェック用インタフェースを実装したクラスを使用した場合、複数フィールドの相関チェックが行えることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0201001() {
        String testId = "vldt0201001";
        String[] targets = { "password", "confirmPassword" };
        String errorMessage = "password and confirm password must be same.";

        // テスト画面表示
        {
            webDriverOperations.click(id(testId));
        }

        // 実施条件1
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(targets[0]), "Spring1234");
                webDriverOperations.overrideText(id(targets[1]), "Spring1234");
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.exists(id(targets[0] + errors)),
                        is(false));
            }
        }

        // 実施条件2
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(targets[0]), "Spring1234");
                webDriverOperations.overrideText(id(targets[1]),
                        "differentPassword");
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.getText(id(targets[0] + errors)),
                        is(errorMessage));
            }
        }
    }

    /**
     * VLDT0201002
     * <ul>
     * <li>入力チェック用インタフェースを実装したクラスを使用した場合、複数フィールドの相関チェックが行えることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0201002() {
        String testId = "vldt0201002";
        String[][] targets = { { "password", "confirmPassword" }, { "age",
                "dateOfBirth" } };
        String[] errorMessages = {
                "password and confirm password must be same.",
                "Age and Date of Birth is inconsistent." };
        String[] validates = { "validateUser", "validateUserDetails" };

        // テスト画面表示
        {
            webDriverOperations.click(id(testId));
        }

        // 実施条件1
        {
            int param = 0;

            // テスト実行
            {
                webDriverOperations.overrideText(id(targets[param][0]),
                        "Spring1234");
                webDriverOperations.overrideText(id(targets[param][1]),
                        "Spring1234");
                webDriverOperations.click(id(validates[param]));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.exists(id(targets[param][0]
                        + errors)), is(false));
            }
        }

        // 実施条件2
        {
            int param = 0;

            // テスト実行
            {
                webDriverOperations.overrideText(id(targets[param][0]),
                        "Spring1234");
                webDriverOperations.overrideText(id(targets[param][1]),
                        "differentPassword");
                webDriverOperations.click(id(validates[param]));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.getText(id(targets[param][0]
                        + errors)), is(errorMessages[param]));
            }
        }

        // 実施条件3
        {
            int param = 1;
            LocalDate dt = new LocalDate();

            // テスト実行
            {
                webDriverOperations.overrideText(id(targets[param][0]), "20");
                webDriverOperations.overrideText(id(targets[param][1]), dt
                        .minusYears(20).toString(localeDateFormat.get(
                                currentLocale)));
                webDriverOperations.click(id(validates[param]));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.exists(id(targets[param][0]
                        + errors)), is(false));
            }
        }

        // 実施条件4
        {
            int param = 1;
            LocalDate dt = new LocalDate();

            // テスト実行
            {
                webDriverOperations.overrideText(id(targets[param][0]), "20");
                webDriverOperations.overrideText(id(targets[param][1]), dt
                        .minusYears(19).toString(localeDateFormat.get(
                                currentLocale)));
                webDriverOperations.click(id(validates[param]));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.getText(id(targets[param][0]
                        + errors)), is(errorMessages[param]));
            }
        }
    }

    /**
     * VLDT0201003
     * <ul>
     * <li>複数フィールドの相関チェックでエラーになった際、全フィールドのハイライト表示とパスワードにのみエラーメッセージが表示されることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0201003() {
        String testId = "vldt0201003";
        String[] targets = { "password", "confirmPassword" };
        String errorMessage = "password and confirm password must be same.";

        // テスト画面表示
        {
            webDriverOperations.click(id(testId));
        }

        // 実施条件1
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(targets[0]), "Spring1234");
                webDriverOperations.overrideText(id(targets[1]), "Spring1234");
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.exists(id(targets[0] + errors)),
                        is(false));
                assertThat(webDriverOperations.exists(id(targets[1] + errors)),
                        is(false));
            }
        }

        // 実施条件2
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(targets[0]), "Spring1234");
                webDriverOperations.overrideText(id(targets[1]),
                        "differentPassword");
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                // エラーメッセージ確認
                assertThat(webDriverOperations.getText(id(targets[0] + errors)),
                        is(errorMessage));
                assertThat(webDriverOperations.getText(id(targets[1] + errors)),
                        isEmptyString());
                // CSS適用確認
                assertThat(webDriverOperations.getWebDriver().findElement(id(
                        targets[0])).getAttribute("class"), is(
                                "form-control error-input"));
                assertThat(webDriverOperations.getWebDriver().findElement(id(
                        targets[1])).getAttribute("class"), is(
                                "form-control error-input"));
            }
        }
    }

    /**
     * VLDT0202001
     * <ul>
     * <li>JabaBeanのクラスレベルに指定できるBean Validationを作成した場合、JavaBeanのフィールド値同士の相関チェックができることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0202001() {
        String testId = "vldt0202001";
        String[] targets = { "password", "confirmPassword" };
        String errorMessage = "not match the confirmation field.";

        // テスト画面表示
        {
            webDriverOperations.click(id(testId));
        }

        // 実施条件1
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(targets[0]), "Spring1234");
                webDriverOperations.overrideText(id(targets[1]), "Spring1234");
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.exists(id(targets[0] + errors)),
                        is(false));
            }
        }

        // 実施条件2
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(targets[0]), "Spring1234");
                webDriverOperations.overrideText(id(targets[1]),
                        "differentPassword");
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.getText(id(targets[0] + errors)),
                        is(errorMessage));
            }
        }
    }

    /**
     * VLDT0202002
     * <ul>
     * <li>複数フィールドの相関チェックでエラーになった際、全フィールドのハイライト表示とパスワードにのみエラーメッセージが表示されることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0202002() {
        String testId = "vldt0202002";
        String[] targets = { "password", "confirmPassword" };
        String errorMessage = "not match the confirmation field.";

        // テスト画面表示
        {
            webDriverOperations.click(id(testId));
        }

        // 実施条件1
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(targets[0]), "Spring1234");
                webDriverOperations.overrideText(id(targets[1]), "Spring1234");
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.exists(id(targets[0] + errors)),
                        is(false));
                assertThat(webDriverOperations.exists(id(targets[1] + errors)),
                        is(false));
            }
        }

        // 実施条件2
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(targets[0]), "Spring1234");
                webDriverOperations.overrideText(id(targets[1]),
                        "differentPassword");
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                // エラーメッセージ確認
                assertThat(webDriverOperations.getText(id(targets[0] + errors)),
                        is(errorMessage));
                assertThat(webDriverOperations.getText(id(targets[1] + errors)),
                        isEmptyString());
                // CSS適用確認
                assertThat(webDriverOperations.getWebDriver().findElement(id(
                        targets[0])).getAttribute("class"), is(
                                "form-control error-input"));
                assertThat(webDriverOperations.getWebDriver().findElement(id(
                        targets[1])).getAttribute("class"), is(
                                "form-control error-input"));
            }
        }
    }
}
