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
import static org.openqa.selenium.By.id;

import java.util.HashMap;
import java.util.Map;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

/**
 * VLDT 入力チェックテスト<br>
 * <p>
 * VLDT04 独自のBeanValidationアノテーションの作成のテストケース
 * </p>
 */
public class CustomBeanValidationTest extends FunctionTestSupport {

    private static WebDriver driver;

    private String validate = "validate";

    private String errors = ".errors";

    private String currentLocale = "ja";

    private static Map<String, String> localeDateFormat;

    public CustomBeanValidationTest() {
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
     * VLDT0401001
     * <ul>
     * <li>既存ルールを組み合わせたBean Validationアノテーションを利用した場合、入力チェックが可能なことを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0401001() {
        String testId = "vldt0401001";
        String target = "userId";
        String errorMessage = "ユーザIDは、4文字以上20文字以下の半角英字で入力してください。";

        // テスト画面表示
        {
            webDriverOperations.click(id(testId));
        }

        // 実施条件1
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(target),
                        "abcdefghijklmnopqrst");
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.exists(id(target + errors)), is(
                        false));
            }
        }

        // 実施条件2
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(target), "abc");
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.getText(id(target + errors)), is(
                        errorMessage));
            }
        }

        // 実施条件3
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(target), "abc1");
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.getText(id(target + errors)), is(
                        errorMessage));
            }
        }

        // 実施条件4
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(target),
                        "abcdefghijklmnopqrstu");
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.getText(id(target + errors)), is(
                        errorMessage));
            }
        }
    }

    /**
     * VLDT0402001
     * <ul>
     * <li>Bean Validationインタフェースの実装クラスを作成した場合、独自アノテーションを付与した対象フィールドの入力チェックが行えることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0402001() {
        String testId = "vldt0402001";
        String target = "isbnCode";
        String errorMessage = "ISBNコードは、ISBN(International Standard Book Number)-13の形式で入力してください。";

        // テスト画面表示
        {
            webDriverOperations.click(id(testId));
        }

        // 実施条件1
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(target), "9784777517992");
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.exists(id(target + errors)), is(
                        false));
            }
        }

        // 実施条件2
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(target), "97847775a7992");
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.getText(id(target + errors)), is(
                        errorMessage));
            }
        }
    }

    /**
     * VLDT0402002
     * <ul>
     * <li>業務ロジックチェックを行う、Bean Validationインタフェースの実装クラスを作成した場合、 業務ロジックチェックの結果メッセージを入力フィールドの横に出力することができることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0402002() {
        String testId = "vldt0402002";
        String target = "userId";
        String errorMessage = "入力されたユーザIDは、既に使用されています。";

        // テスト画面表示
        {
            webDriverOperations.click(id(testId));
        }

        // 実施条件1
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(target), "Tom");
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.exists(id(target + errors)), is(
                        false));
            }
        }

        // 実施条件2
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(target), "Josh");
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.getText(id(target + errors)), is(
                        errorMessage));
            }
        }
    }
}
