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
package jp.co.ntt.fw.spring.functionaltest.selenium.dbsp;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import java.io.IOException;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupportForMultiBrowser;
import jp.co.ntt.fw.spring.functionaltest.selenium.WebDriverOperations;

public class DoubleSubmitProtectionMultipleBrowser_Thymeleaf_Test
        extends FunctionTestSupportForMultiBrowser {

    private static final String VIEW_TYPE = "thymeleaf";

    /**
     * <ul>
     * <li>トランザクショントークンを削除する際は、実行された日時が最も古いものから順に削除することを確認する。</li>
     * </ul>
     **/
    @Test
    public void testDBSP0303002() throws IOException {
        WebDriverOperations[] browsers = new WebDriverOperations[11];
        browsers[0] = setUpWebDriverOperations(0);
        Cookie cookie =
                browsers[0].changeCookieDomainName(browsers[0].getCookie("JSESSIONID"), null);

        for (int i = 1; i < 11; i++) {
            // 同一セッションで新しいブラウザを立ち上げる
            browsers[i] = setUpWebDriverOperations(i);
            browsers[i].getWebDriver().manage().addCookie(cookie);
        }
        String[] buttonNames = {"dbsp0302002_" + VIEW_TYPE, "second", "third"};
        String[] screenTitles = {"firstView", "secondView", "thirdView"};

        for (WebDriverOperations webDriverOperations : browsers) {
            for (int i = 0; i < 3; i++) {
                // 画面遷移
                webDriverOperations.click(id(buttonNames[i]));
                // 各画面へ遷移したことをチェック
                assertThat(webDriverOperations.getText(id("screenTitle")), is(screenTitles[i]));
            }
        }
        browsers[0].click(id("fourth"));
        // 一番目のブラウザは第四画面に遷移できず、トランザクショントークンエラーとなることをチェック
        // エラーの確認
        {
            // エラー画面表示まで待機
            String expectedTitle = "Transaction Token Error!";
            browsers[0].waitForDisplayed(titleIs(expectedTitle));
        }
        // 二番目以降のブラウザは正常に次の画面へ遷移できることを確認
        for (int i = 1; i < 11; i++) {
            // 画面遷移
            browsers[i].click(id("fourth"));
            // 各画面へ遷移したことをチェック
            browsers[i].waitForDisplayed(textToBe(id("screenTitle"), "fourthView"));
        }
    }

    /**
     * <ul>
     * <li>NameSpaceごとに保持できるトランザクショントークンの上限数が設定できることを確認する。</li>
     * </ul>
     **/
    @Test
    public void testDBSP0303003() throws IOException {
        WebDriverOperations[] browsers = new WebDriverOperations[2];
        browsers[0] = setUpWebDriverOperations(0);
        Cookie cookie =
                browsers[0].changeCookieDomainName(browsers[0].getCookie("JSESSIONID"), null);
        // 同一セッションで新しいブラウザを立ち上げる
        browsers[1] = setUpWebDriverOperations(1);
        browsers[1].getWebDriver().manage().addCookie(cookie);
        String[] buttonNames = {"dbsp0303003_" + VIEW_TYPE, "second", "third"};
        String[] screenTitles = {"firstView", "secondView", "thirdView"};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                browsers[j].click(id(buttonNames[i]));
                if (i == 2 && j == 0) {
                    // ブラウザ1では第三画面に遷移できず、トランザクショントークンエラーとなることをチェック
                    // エラーの確認
                    {
                        // エラー画面表示まで待機
                        String expectedTitle = "Transaction Token Error!";
                        assertThat(browsers[j].getTitle(), is(expectedTitle));
                    }
                } else {
                    assertThat(browsers[j].getText(id("screenTitle")), is(screenTitles[i]));
                }
            }
        }
    }

    /**
     * <ul>
     * <li>NameSpaceを設けることで、並行して操作できることを確認する。</li>
     * <li>NameSpaceを設けた場合、1つのコントローラで複数ユースケースのトランザクショントークンチェックができることを確認する。</li>
     * </ul>
     **/
    @Test
    public void testDBSP0303004() throws IOException {
        String[] valueTexts = new String[2];
        WebDriverOperations[] browsers = new WebDriverOperations[2];
        browsers[0] = setUpWebDriverOperations(0);
        Cookie cookie =
                browsers[0].changeCookieDomainName(browsers[0].getCookie("JSESSIONID"), null);
        // 同一セッションで新しいブラウザを立ち上げる
        browsers[1] = setUpWebDriverOperations(1);
        browsers[1].getWebDriver().manage().addCookie(cookie);
        String[] buttonNames = {"second", "third", "fourth", "fifth"};
        String[] screenTitles = {"secondView", "thirdView", "fourthView", "fifthView"};

        // NameSpaceは未設定のため、globalToken
        browsers[0].click(id("dbsp0303003_" + VIEW_TYPE));
        // NameSpaceはcreate
        browsers[1].click(id("dbsp0303004_" + VIEW_TYPE));
        for (WebDriverOperations browser : browsers) {
            browser.waitForDisplayed(textToBe(id("screenTitle"), "firstView"));
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                browsers[j].click(id(buttonNames[i]));
                browsers[j].waitForDisplayed(textToBe(id("screenTitle"), screenTitles[i]));
                // トランザクショントークンを保持する第二、第三、第四画面にて、トークンのNameSpace情報を取得する。
                if (i < 3) {
                    valueTexts[j] = (String) browsers[j].getJavascriptExecutor().executeScript(
                            "return document.getElementsByName('_TRANSACTION_TOKEN')[0].value;");
                }
            }

            if (i < 3) {
                // 2つのブラウザにおけるトランザクショントークンのNameSpaceが異なることを確認
                assertThat(getNameSpace(valueTexts[0]), is("globalToken"));
                assertThat(getNameSpace(valueTexts[1]), is("create"));
            }
        }
    }

    /**
     * トランザクショントークンタグのvalue文字列からNameSpaceの部分を抜き出して返却する。
     * 
     * @param valueText
     * @return
     */
    private String getNameSpace(String valueText) {
        return valueText.substring(0, valueText.indexOf("~"));
    }
}
