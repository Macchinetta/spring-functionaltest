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
import static org.hamcrest.CoreMatchers.is;
import static org.openqa.selenium.By.id;

import java.util.HashMap;
import java.util.Map;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

/**
 * VLDT 入力チェックテスト<br>
 * <p>
 * VLDT03 エラーメッセージの定義のテストケース
 * </p>
 */
public class DefineValidationMessagesTest extends FunctionTestSupport {

    private static WebDriver driver;

    private String validate = "validate";

    private String errors = ".errors";

    private String currentLocale = "ja";

    private static Map<String, String> localeDateFormat;

    public DefineValidationMessagesTest() {
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
     * VLDT0301001
     * <ul>
     * <li>Bean Validationが管理するメッセージプロパティファイルにメッセージを定義した場合、入力チェックエラー時に設定したメッセージが出力されることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0301001() {
        String testId = "vldt0301001";
        String[] targets = { "userName", "email" };
        String[] errorMessages = { "ユーザ名 に設定できる文字数は 1 から 20 の範囲です。",
                "Email に設定された値はEmail形式ではありません。" };

        // テスト画面表示
        {
            webDriverOperations.click(id(testId));
        }

        // 実施条件1
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(targets[0]),
                        "SpringTestSpringTest");
                webDriverOperations.overrideText(id(targets[1]),
                        "spring@test.com");
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                for (int i = 0; i < targets.length; i++) {
                    assertThat(webDriverOperations.exists(id(targets[i]
                            + errors)), is(false));
                }
            }
        }

        // 実施条件2
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(targets[0]),
                        "SpringTestSpringTestS");
                webDriverOperations.overrideText(id(targets[1]),
                        "springtest.com");
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
     * VLDT0302001
     * <ul>
     * <li>Springが管理するメッセージプロパティファイルにメッセージを定義した場合、入力チェックエラー時に設定したメッセージが出力されることを確認する。</li>
     * <li>なお、エラーメッセージはVLDT0301001と同一だが、application-messages-vldt_ja.propertiesのメッセージが採用されている。</li>
     * </ul>
     */
    @Test
    public void testVLDT0302001() {
        String testId = "vldt0302001";
        String[] targets = { "userName", "email" };
        String[] errorMessages = { "ユーザ名 に設定できる文字数は 1 から 20 の範囲です。",
                "Email に設定された値はEmail形式ではありません。" };

        // テスト画面表示
        {
            webDriverOperations.click(id(testId));
        }

        // 実施条件1
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(targets[0]),
                        "SpringTestSpringTest");
                webDriverOperations.overrideText(id(targets[1]),
                        "spring@test.com");
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                for (int i = 0; i < targets.length; i++) {
                    assertThat(webDriverOperations.exists(id(targets[i]
                            + errors)), is(false));
                }
            }
        }

        // 実施条件2
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(targets[0]),
                        "SpringTestSpringTestS");
                webDriverOperations.overrideText(id(targets[1]),
                        "springtest.com");
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
     * VLDT0303001
     * <ul>
     * <li>Springが管理するメッセージプロパティファイルにメッセージを定義した場合、入力チェックエラー時に設定したメッセージが出力されることを確認する。</li>
     * <li>なお、エラーメッセージはVLDT0301001と同一だが、application-messages-vldt_ja.propertiesのメッセージが採用されている。</li>
     * </ul>
     */
    @Test
    public void testVLDT0303001() {
        String testId = "vldt0303001";
        String[] targets = { "shortValue", "intValue", "longValue",
                "floatValue", "doubleValue", "shortObject", "integerObject",
                "longObject", "floatObject", "doubleObject", "date", "datetime",
                "localdate", "booleanValue" };
        String[] errorMessages = { "\"{0}\" はshort型を入力してください。",
                "\"{0}\" はint型を入力してください。", "\"{0}\" はlong型を入力してください。",
                "\"{0}\" はfloat型を入力してください。", "\"{0}\" はdouble型を入力してください。",
                "\"{0}\" はShort型を入力してください。", "\"{0}\" はInteger型を入力してください。",
                "\"{0}\" はLong型を入力してください。", "\"{0}\" はFloat型を入力してください。",
                "\"{0}\" はDouble型を入力してください。", "\"{0}\" は日付ではありません。",
                "\"{0}\" は日付時刻ではありません。", "\"{0}\" は日付ではありません。",
                "\"{0}\" は無効です。", };

        // テスト画面表示
        {
            webDriverOperations.click(id(testId));
        }

        // 実施条件1
        {
            // テスト実行
            {
                DateTime dt = new DateTime();

                webDriverOperations.overrideText(id(targets[0]), "32767");
                webDriverOperations.overrideText(id(targets[1]), "2147483647");
                webDriverOperations.overrideText(id(targets[2]),
                        "9223372036854775807");
                webDriverOperations.overrideText(id(targets[3]),
                        "3.4028235E38");
                webDriverOperations.overrideText(id(targets[4]),
                        "1.7976931348623157E308");
                webDriverOperations.overrideText(id(targets[5]), "32767");
                webDriverOperations.overrideText(id(targets[6]), "2147483647");
                webDriverOperations.overrideText(id(targets[7]),
                        "9223372036854775807");
                webDriverOperations.overrideText(id(targets[8]),
                        "3.4028235E38");
                webDriverOperations.overrideText(id(targets[9]),
                        "1.7976931348623157E308");
                webDriverOperations.overrideText(id(targets[10]), dt.toString(
                        localeDateFormat.get(currentLocale)));
                webDriverOperations.overrideText(id(targets[11]), dt.toString(
                        localeDateFormat.get(currentLocale) + " HH:mm"));
                webDriverOperations.overrideText(id(targets[12]), dt.toString(
                        localeDateFormat.get(currentLocale)));
                webDriverOperations.overrideText(id(targets[13]), "true");
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                for (int i = 0; i < targets.length; i++) {
                    assertThat(webDriverOperations.exists(id(targets[i]
                            + errors)), is(false));
                }
            }
        }

        // 実施条件2
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(targets[0]), "SpringTest");
                webDriverOperations.overrideText(id(targets[1]), "SpringTest");
                webDriverOperations.overrideText(id(targets[2]), "SpringTest");
                webDriverOperations.overrideText(id(targets[3]), "SpringTest");
                webDriverOperations.overrideText(id(targets[4]), "SpringTest");
                webDriverOperations.overrideText(id(targets[5]), "SpringTest");
                webDriverOperations.overrideText(id(targets[6]), "SpringTest");
                webDriverOperations.overrideText(id(targets[7]), "SpringTest");
                webDriverOperations.overrideText(id(targets[8]), "SpringTest");
                webDriverOperations.overrideText(id(targets[9]), "SpringTest");
                webDriverOperations.overrideText(id(targets[10]), "SpringTest");
                webDriverOperations.overrideText(id(targets[11]), "SpringTest");
                webDriverOperations.overrideText(id(targets[12]), "SpringTest");
                webDriverOperations.overrideText(id(targets[13]), "SpringTest");
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                for (int i = 0; i < targets.length; i++) {
                    assertThat(webDriverOperations.getText(id(targets[i]
                            + errors)), is(errorMessages[i].replace("{0}",
                                    targets[i])));
                }
            }
        }
    }

    /**
     * VLDT0301004
     * <ul>
     * <li>Bean Validationが管理するメッセージプロパティファイルにnative2asciiを行わずにメッセージを定義した場合、入力チェックエラー時に設定したメッセージが出力されることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0304001() {
        String testId = "vldt0304001";
        String[] targets = { "userName", "email" };
        String[] errorMessages = {
                "not native2ascii message : ユーザ名 に設定できる文字数は 1 から 20 の範囲です。",
                "not native2ascii message : Email に設定された値はEmail形式ではありません。" };

        // テスト画面表示
        {
            webDriverOperations.click(id(testId));
        }

        // 実施条件1
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(targets[0]),
                        "SpringTestSpringTest");
                webDriverOperations.overrideText(id(targets[1]),
                        "spring@test.com");
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                for (int i = 0; i < targets.length; i++) {
                    assertThat(webDriverOperations.exists(id(targets[i]
                            + errors)), is(false));
                }
            }
        }

        // 実施条件2
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(targets[0]),
                        "SpringTestSpringTestS");
                webDriverOperations.overrideText(id(targets[1]),
                        "springtest.com");
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
     * VLDT0305001
     * <ul>
     * <li>Bean Validationが管理するメッセージプロパティファイルに${validatedValue}を含むメッセージを定義した場合、入力チェックエラー時に設定したメッセージに入力値が埋め込まれた状態で出力されることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0305001() {
        String testId = "vldt0305001";
        String target = "telNumber";
        String targetForm = "defineMessageUsingValidatedValueByValidationMessagesForm";
        String errorMessage = "telNumber に設定された値\"012-345-678\"は、無効な値です。";

        // テスト画面表示
        {
            webDriverOperations.click(id(testId));
        }

        // 実施条件1
        {
            // テスト実行
            {
                webDriverOperations.overrideText(id(target), "0123456789");
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
                webDriverOperations.overrideText(id(target), "012-345-678");
                webDriverOperations.click(id(validate));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.getText(id(targetForm + errors)),
                        is(errorMessage));
            }
        }

    }
}
