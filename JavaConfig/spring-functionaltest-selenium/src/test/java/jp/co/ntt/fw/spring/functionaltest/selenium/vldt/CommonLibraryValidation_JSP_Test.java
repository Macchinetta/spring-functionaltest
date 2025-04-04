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

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.openqa.selenium.By.id;
import org.junit.Test;
import org.openqa.selenium.By;

public class CommonLibraryValidation_JSP_Test extends ValidationTestSupport {

    private static final String VIEW_TYPE = "jsp";

    private void clickLink(By by) {
        webDriverOperations.click(by);
    }

    private void clickButton(By by) {
        webDriverOperations.click(by);
    }

    private void setInputString(By by, String value) {
        webDriverOperations.overrideText(by, value);
    }

    private String getTextString(By by) {
        return webDriverOperations.getText(by);
    }

    /**
     * VLDT0501001
     * <ul>
     * <li>共通ライブラリが提供するアノテーションのデフォルトメッセージを上書きできることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0501001() {

        // 疑似アプリ画面表示
        {
            clickLink(id("vldt0501001_" + VIEW_TYPE));
        }

        // codelist-Key入力
        {
            setInputString(By.id("key"), "5");
            clickButton(By.id("btn1"));
        }

        // 結果確認
        {
            assertThat(getTextString(By.id("errors")),
                    is("\"key\" must exist in code list of CL_GENDER."));
        }
    }

    /**
     * VLDT0502001
     * <ul>
     * <li>共通ライブラリが提供するアノテーションを利用し、コレクションに対して入力チェックが行えることを確認する。</li>
     * </ul>
     */
    @Test
    public void testVLDT0502001() {
        String testId = "vldt0502001";
        String target = "roles";
        String errorMessage1 = "\"roles[0]\" must exist in code list of CL_ROLE.";
        String errorMessage2 = "\"roles[1]\" must exist in code list of CL_ROLE.";

        // テスト画面表示
        {
            clickLink(id(testId + "_" + VIEW_TYPE));
        }
        // 実施条件1
        {
            // checkbox押下
            {
                webDriverOperations.click(id(target + "1"));
                webDriverOperations.click(id(target + "2"));
                webDriverOperations.click(id(ID_VALIDATE));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.exists(id(target + "*" + ID_ERRORS)), is(false));
            }
        }

        // 実施条件2
        {
            // checkbox内の値の書き換え
            {
                webDriverOperations.overrideText(id(target + "1"), "3");
                webDriverOperations.overrideText(id(target + "2"), "4");
                webDriverOperations.click(id(ID_VALIDATE));
            }

            // 結果確認
            {
                assertThat(webDriverOperations.getText(id(target + "*" + ID_ERRORS)),
                        containsString(errorMessage1));
                assertThat(webDriverOperations.getText(id(target + "*" + ID_ERRORS)),
                        containsString(errorMessage2));
            }
        }
    }
}
