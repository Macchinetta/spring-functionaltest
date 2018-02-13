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
package jp.co.ntt.fw.spring.functionaltest.selenium.vldt;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.openqa.selenium.By.id;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

/**
 * VLDT 入力チェックテスト<br>
 * <p>
 * VLDT01 単項目チェックのテストケース
 * </p>
 */
public class SimpleValidationTest extends FunctionTestSupport {

    private static WebDriver driver;

    private String validate = "validate";

    private String errors = ".errors";

    private String dot = ".";

    private String br = "\n";

    private String currentLocale = "en";

    private static Map<String, String> localeDateFormat = new HashMap<String, String>();

    public SimpleValidationTest() {
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
     * VLDT0101001
     * <ul>
     * <li>入力値が1文字以上20文字以下のフィールドに範囲外の文字数を入力した場合、入力チェックエラーを返却できることを確認する。(JSR303標準)</li>
     * </ul>
     */
    @Test
    public void testVLDT0101001() {
        String testId = "vldt0101001";
        String target = "userName";
        String errorMessage = "size must be between 1 and 20";

        // テスト画面表示
        {
            webDriverOperations.click(id(testId));
        }

        // 実施条件1
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(target),
                        "SpringTestSpringTest");
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
                webDriverOperations.overrideText(id(target),
                        "SpringTestSpringTestS");
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
                webDriverOperations.clearText(id(target));
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
     * VLDT0101002
     * <ul>
     * <li>入力が必須のフィールドにblanｋが設定されていた場合、入力チェックエラーを返却できることを確認する。(Hibernate Validator)</li>
     * </ul>
     */
    @Test
    public void testVLDT0101002() {
        String testId = "vldt0101002";
        String target = "address";
        String errorMessage = "may not be empty";

        // テスト画面表示
        {
            webDriverOperations.click(id(testId));
        }

        // 実施条件1
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(target), "J");
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
     * VLDT0101003
     * <ul>
     * <li>Springのformタグ内にまとめてエラーメッセージを出す場合、エラーメッセージをまとめて出力することができることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0101003() {
        String testId = "vldt0101003";
        String target = "displayInsideMessagesForm";
        List<String> errorMessages = new ArrayList<String>();
        errorMessages.add("size must be between 1 and 20");
        errorMessages.add("size must be between 1 and 50");
        errorMessages.add("may not be null");

        // テスト画面表示
        {
            webDriverOperations.click(id(testId));
        }

        // 実施条件1
        {
            // テスト実行
            {
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                String[] messages = webDriverOperations.getText(id(target
                        + errors)).split(br);
                assertThat(messages.length, is(errorMessages.size()));
                for (String error : messages) {
                    assertTrue(errorMessages.indexOf(error) > -1);
                }
            }
        }
    }

    /**
     * VLDT0101004
     * <ul>
     * <li>Springのformタグ外にまとめてエラーメッセージを出す場合、バインドされたオブジェクトにアクセスできるタグを 使用することでエラーメッセージを任意の場所にまとめて出力することができることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0101004() {
        String testId = "vldt0101004";
        String target = "null";
        List<String> errorMessages = new ArrayList<String>();
        errorMessages.add("size must be between 1 and 20");
        errorMessages.add("size must be between 1 and 50");
        errorMessages.add("may not be null");

        // テスト画面表示
        {
            webDriverOperations.click(id(testId));
        }

        // 実施条件1
        {
            // テスト実行
            {
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                String[] messages = webDriverOperations.getText(id(target
                        + errors)).split(br);
                assertThat(messages.length, is(errorMessages.size()));
                for (String error : messages) {
                    assertTrue(errorMessages.indexOf(error) > -1);
                }
            }
        }
    }

    /**
     * VLDT0101005
     * <ul>
     * <li>Springのformタグ外に指定した項目だけエラーメッセージを出す場合、バインドされたオブジェクトにアクセスできるタグを 使用することでエラーメッセージを任意の場所にまとめて出力することができることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0101005() {
        String testId = "vldt0101005";
        String[] targets = { "userName", "email", "age" };
        String[] errorMessages = { "size must be between 1 and 20",
                "size must be between 1 and 50", "may not be null" };

        // テスト画面表示
        {
            webDriverOperations.click(id(testId));
        }

        // 実施条件1
        {
            // テスト実行
            {
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                for (int i = 0; i < targets.length; i++) {
                    assertThat(webDriverOperations.getText(id(targets[i]
                            + errors)), is(errorMessages[i]));
                }
            }
        }
    }

    /**
     * VLDT0101006
     * <ul>
     * <li>JSR303、Hibernate Validatorのチェックルールを指定した場合、フィールド値の検証が行えることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0101006() {
        String testId = "vldt0101006";
        String[] targets = { "notnull", "nullvalue", "pattern", "min", "max",
                "decimalmin", "decimalmax", "size", "digits", "truevalue",
                "falsevalue", "future", "past", "creditcardnumber", "email",
                "url", "notblank", "notempty" };
        String[] errorMessages = { "may not be null", "must be null",
                "must match \"\\d{6}\"", "must be greater than or equal to 100",
                "must be less than or equal to 100",
                "must be greater than or equal to 0.0",
                "must be less than or equal to 99999.99",
                "size must be between 2 and 3",
                "numeric value out of bounds (<6 digits>.<2 digits> expected)",
                "must be true", "must be false", "must be in the future",
                "must be in the past", "invalid credit card number",
                "not a well-formed email address", "must be a valid URL",
                "may not be empty", "may not be empty" };

        // テスト画面表示
        {
            webDriverOperations.click(id(testId));
        }

        // 実施条件1
        {
            // テスト実行
            {
                LocalDate ld = new LocalDate();

                webDriverOperations.overrideText(id(targets[1]), "a");
                webDriverOperations.overrideText(id(targets[2]), "1234567");
                webDriverOperations.overrideText(id(targets[3]), "99");
                webDriverOperations.overrideText(id(targets[4]), "101");
                webDriverOperations.overrideText(id(targets[5]), "-0.1");
                webDriverOperations.overrideText(id(targets[6]), "100000.00");
                webDriverOperations.overrideText(id(targets[7]), "1234");
                webDriverOperations.overrideText(id(targets[8]), "123456.125");
                webDriverOperations.select(id(targets[9]), "False");
                webDriverOperations.select(id(targets[10]), "True");
                webDriverOperations.overrideText(id(targets[11]), ld.minusDays(
                        1).toString(localeDateFormat.get(currentLocale)));
                webDriverOperations.overrideText(id(targets[12]), ld.plusDays(1)
                        .toString(localeDateFormat.get(currentLocale)));
                webDriverOperations.overrideText(id(targets[13]),
                        "1234567890123456");
                webDriverOperations.overrideText(id(targets[14]), "aaa@aaa.");
                webDriverOperations.overrideText(id(targets[15]),
                        "htt://aaa.com/");
                webDriverOperations.clearText(id(targets[16]));
                webDriverOperations.clearText(id(targets[17]));
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                for (int i = 0; i < targets.length; i++) {
                    assertThat(webDriverOperations.getText(id(targets[i]
                            + errors)), is(errorMessages[i]));
                }
            }
        }
    }

    /**
     * VLDT0101007
     * <ul>
     * <li>inclusive属性を省略の場合、エラーメッセージを出力しないことを確認する。<br>
     * inclusive属性(false)の場合、エラーメッセージを出力しないことを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0101007() {
        String testId = "vldt0101007";
        String priceInput = "99.9999999999";

        // テスト画面表示
        {
            webDriverOperations.click(id(testId));
        }

        // 実施条件1
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id("priceDefault"),
                        priceInput);
                webDriverOperations.overrideText(id("priceFalse"), priceInput);
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.exists(id("priceDefault"
                        + errors)), is(false));
                assertThat(webDriverOperations.exists(id("priceFalse"
                        + errors)), is(false));
            }
        }
    }

    /**
     * VLDT0101008
     * <ul>
     * <li>inclusive属性を省略の場合、エラーメッセージを出力しないことを確認する。<br>
     * inclusive属性(false)の場合、エラーメッセージを出力することができることを確認する。
     * </ul>
     */
    @Test
    public void testVLDT0101008() {
        String testId = "vldt0101008";
        String priceInput = "100.0000000000";
        String errorMessage = "must be less than 100";

        // テスト画面表示
        {
            webDriverOperations.click(id(testId));
        }

        // 実施条件1
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id("priceDefault"),
                        priceInput);
                webDriverOperations.overrideText(id("priceFalse"), priceInput);
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.exists(id("priceDefault"
                        + errors)), is(false));
                assertThat(webDriverOperations.getText(id("priceFalse"
                        + errors)), is(errorMessage));
            }
        }
    }

    /**
     * VLDT0101009
     * <ul>
     * <li>inclusive属性を省略の場合、エラーメッセージを出力することができることを確認する。<br>
     * inclusive属性(false)の場合、エラーメッセージを出力することができることを確認する。
     * </ul>
     */
    @Test
    public void testVLDT0101009() {
        String testId = "vldt0101009";
        String priceInput = "100.0000000001";
        String errorPriceDefaultMessage = "must be less than or equal to 100";
        String errorPriceFalseMessage = "must be less than 100";

        // テスト画面表示
        {
            webDriverOperations.click(id(testId));
        }

        // 実施条件1
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id("priceDefault"),
                        priceInput);
                webDriverOperations.overrideText(id("priceFalse"), priceInput);
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.getText(id("priceDefault"
                        + errors)), is(errorPriceDefaultMessage));
                assertThat(webDriverOperations.getText(id("priceFalse"
                        + errors)), is(errorPriceFalseMessage));
            }
        }
    }

    /**
     * VLDT0102001
     * <ul>
     * <li>ネストしたJavaBeanにBeanValidationのアノテーションを付与した場合、ネスト先のフィールドも入力チェックできることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0102001() {
        String testId = "vldt0102001";
        String nestedFormName = "reciverAddress";
        String[] targets = { "userName", "postCode", "address" };
        String[] errorMessages = { "size must be between 1 and 50",
                "size must be between 1 and 10",
                "size must be between 1 and 100" };

        // テスト画面表示
        {
            webDriverOperations.click(id(testId));
        }

        // 実施条件1
        {
            // テスト実行
            {
                for (int i = 0; i < targets.length; i++) {
                    webDriverOperations.overrideText(id(nestedFormName + dot
                            + targets[i]), "SpringTest");
                }
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                for (int i = 0; i < targets.length; i++) {
                    assertThat(webDriverOperations.exists(id(nestedFormName
                            + dot + targets[i] + errors)), is(false));
                }
            }
        }

        // 実施条件2
        {
            // テスト実行
            {
                for (int i = 0; i < targets.length; i++) {
                    webDriverOperations.clearText(id(nestedFormName + dot
                            + targets[i]));
                }
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                for (int i = 0; i < targets.length; i++) {
                    assertThat(webDriverOperations.getText(id(nestedFormName
                            + dot + targets[i] + errors)), is(
                                    errorMessages[i]));
                }
            }
        }
    }

    /**
     * VLDT0102002
     * <ul>
     * <li>コレクション型でネストしたJavaBeanにBeanValidationのアノテーションを付与した場合、ネスト先のフィールドも入力チェックできることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0102002() {
        String testId = "vldt0102002";
        String nestedFormName = "addresses";
        int nestedFormCount = 3;
        String[] targets = { "userName", "postCode", "address" };
        String[] errorMessages = { "size must be between 1 and 50",
                "size must be between 1 and 10",
                "size must be between 1 and 100" };

        // テスト画面表示
        {
            webDriverOperations.click(id(testId));
        }

        // 実施条件1
        {
            // 前提条件設定
            {
                for (int i = 0; i < nestedFormCount - 1; i++) {
                    webDriverOperations.click(id("add"));
                }
            }

            // テスト実行
            {
                for (int i = 0; i < nestedFormCount; i++) {
                    for (int j = 0; j < targets.length; j++) {
                        webDriverOperations.overrideText(id(nestedFormName + i
                                + dot + targets[j]), "SpringTest");
                    }
                }
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                for (int i = 0; i < nestedFormCount; i++) {
                    for (int j = 0; j < targets.length; j++) {
                        assertThat(webDriverOperations.exists(id(nestedFormName
                                + i + dot + targets[j] + errors)), is(false));
                    }
                }
            }
        }

        // 実施条件2
        {
            // 前提条件設定
            {
                for (int i = 0; i < nestedFormCount - 1; i++) {
                    webDriverOperations.click(id("add"));
                }
            }

            // テスト実行
            {
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                for (int i = 0; i < nestedFormCount; i++) {
                    for (int j = 0; j < targets.length; j++) {
                        assertThat(webDriverOperations.getText(id(nestedFormName
                                + i + dot + targets[j] + errors)), is(
                                        errorMessages[j]));
                    }
                }
            }
        }
    }

    /**
     * VLDT0103001
     * <ul>
     * <li>バリデーショングループを作成し、Controllerにデフォルトグループを定義した場合、一つのフィールドに対してグループごとに入力チェックできることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0103001() {
        String testId = "vldt0103001";
        String target = "age";
        String groupKey = "country";
        String[] groups = { "Japan", "Singapore" };
        String[] errorMessages = { "must be greater than or equal to 20",
                "must be greater than or equal to 21" };

        // テスト画面表示
        {
            webDriverOperations.click(id(testId));
        }

        // 実施条件1
        {
            int groupValue = 0;

            // テスト実行
            {
                webDriverOperations.overrideText(id(target), "20");
                webDriverOperations.select(id(groupKey), groups[groupValue]);
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
            int groupValue = 0;

            // テスト実行
            {
                webDriverOperations.overrideText(id(target), "19");
                webDriverOperations.select(id(groupKey), groups[groupValue]);
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.getText(id(target + errors)), is(
                        errorMessages[groupValue]));
            }
        }

        // 実施条件3
        {
            int groupValue = 1;

            // テスト実行
            {
                webDriverOperations.overrideText(id(target), "21");
                webDriverOperations.select(id(groupKey), groups[groupValue]);
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.exists(id(target + errors)), is(
                        false));
            }
        }

        // 実施条件4
        {
            int groupValue = 1;

            // テスト実行
            {
                webDriverOperations.overrideText(id(target), "20");
                webDriverOperations.select(id(groupKey), groups[groupValue]);
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.getText(id(target + errors)), is(
                        errorMessages[groupValue]));
            }
        }
    }

    /**
     * VLDT0103002
     * <ul>
     * <li>バリデーショングループを作成し、フォームクラスにデフォルトグループを定義した場合、一つのフィールドに対してグループごとに入力チェックできることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0103002() {
        String testId = "vldt0103002";
        String target = "age";
        String groupKey = "country";
        String[] groups = { "Japan", "Singapore", "France" };
        String[] errorMessages = { "must be greater than or equal to 20",
                "must be greater than or equal to 21",
                "must be greater than or equal to 18" };

        // テスト画面表示
        {
            webDriverOperations.click(id(testId));
        }

        // 実施条件1
        {
            int groupValue = 0;

            // テスト実行
            {
                webDriverOperations.overrideText(id(target), "20");
                webDriverOperations.select(id(groupKey), groups[groupValue]);
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
            int groupValue = 0;

            // テスト実行
            {
                webDriverOperations.overrideText(id(target), "19");
                webDriverOperations.select(id(groupKey), groups[groupValue]);
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.getText(id(target + errors)), is(
                        errorMessages[groupValue]));
            }
        }

        // 実施条件3
        {
            int groupValue = 1;

            // テスト実行
            {
                webDriverOperations.overrideText(id(target), "21");
                webDriverOperations.select(id(groupKey), groups[groupValue]);
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.exists(id(target + errors)), is(
                        false));
            }
        }

        // 実施条件4
        {
            int groupValue = 1;

            // テスト実行
            {
                webDriverOperations.overrideText(id(target), "20");
                webDriverOperations.select(id(groupKey), groups[groupValue]);
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.getText(id(target + errors)), is(
                        errorMessages[groupValue]));
            }
        }

        // 実施条件5
        {
            int groupValue = 2;

            // テスト実行
            {
                webDriverOperations.overrideText(id(target), "18");
                webDriverOperations.select(id(groupKey), groups[groupValue]);
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.exists(id(target + errors)), is(
                        false));
            }
        }

        // 実施条件6
        {
            int groupValue = 2;

            // テスト実行
            {
                webDriverOperations.overrideText(id(target), "17");
                webDriverOperations.select(id(groupKey), groups[groupValue]);
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.getText(id(target + errors)), is(
                        errorMessages[groupValue]));
            }
        }
    }

    /**
     * VLDT0103003
     * <ul>
     * <li>入力チェックをControllerでチェックする機構を利用した場合、認証オブジェクトが持つ権限情報を元に入力チェックのグループ切り替えが行えることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0103003() {
        String testId = "vldt0103003";
        String target = "deliveryAddress";
        String errorMessage = "may not be null";

        // テスト画面表示
        {
            webDriverOperations.click(id(testId));
        }

        // 実施条件1
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(target), "tokyo");
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
                webDriverOperations.overrideText(id("username"), "Josh");
                webDriverOperations.overrideText(id("password"), "spring1234");
                webDriverOperations.click(id("login"));
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.exists(id(target + errors)), is(
                        false));
            }
        }
    }

    /**
     * VLDT0104001
     * <ul>
     * <li>フォームオブジェクトにnullをバインドできる設定を追加した場合、nullをフォームオブジェクトにバインドされることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0104001() {
        String testId = "vldt0104001";
        String target = "userName";
        String errorMessage = "may not be null";

        // テスト画面表示
        {
            webDriverOperations.click(id(testId));
        }

        // 実施条件1
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(target), "S");
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
