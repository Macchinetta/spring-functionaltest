/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.selenium.stpr;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.id;
import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class StringProcessingTest extends FunctionTestSupport {

    private static WebDriver deDriver;

    public StringProcessingTest() {
        disableDefaultWebDriver();
    }

    @Before
    public void setUp() {
        if (deDriver == null) {
            deDriver = webDriverCreator.createLocaleSpecifiedDriver("ja, JP");
        }
        setCurrentWebDriver(deDriver);

        // トップ画面での操作
        {
            webDriverOperations.getWebDriver().manage().window().maximize();
        }
    }

    /**
     * <ul>
     * <li>半角空白を除いた文字列を取得できることを確認する</li>
     * </ul>
     */
    @Test
    public void testSTPR0101001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("stpr0101001"));
        }
        // 入力
        {
            webDriverOperations.overrideText(id("targetText"), " Hello World!");
            webDriverOperations.click(id("trim"));
        }
        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("resultString")),
                    is("\"Hello World!\""));
        }
    }

    /**
     * <ul>
     * <li>前方から指定した文字を除いた文字列を取得できることを確認する</li>
     * </ul>
     */
    @Test
    public void testSTPR0101002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("stpr0101002"));
        }
        // 入力
        {
            webDriverOperations.overrideText(id("targetText"), " Hello World!");
            webDriverOperations.overrideText(id("trimText"), " ");
            webDriverOperations.click(id("trim"));
        }
        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("resultString")),
                    is("\"Hello World!\""));
        }
    }

    /**
     * <ul>
     * <li>後方から指定した文字を除いた文字列を取得できることを確認する</li>
     * </ul>
     */
    @Test
    public void testSTPR0101003() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("stpr0101003"));
        }
        // 入力
        {
            webDriverOperations.overrideText(id("targetText"), " Hello World!");
            webDriverOperations.overrideText(id("trimText"), "!");
            webDriverOperations.click(id("trim"));
        }

        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("resultString")),
                    is("\" Hello World\""));
        }
    }

    /**
     * <ul>
     * <li>指定したフォーマットでパディングされた文字列を取得できることを確認する</li>
     * </ul>
     */
    @Test
    public void testSTPR0201001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("stpr0201001"));
        }
        // 入力
        {
            webDriverOperations.overrideText(id("targetValueInt"), "1");
            webDriverOperations.overrideText(id("format"), "%03d");
            webDriverOperations.click(id("padding"));
        }
        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("resultString")),
                    is("001"));
        }
    }

    /**
     * <ul>
     * <li>サプレスされた文字列を取得できることを確認する</li>
     * </ul>
     */
    @Test
    public void testSTPR0201002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("stpr0201002"));
        }
        // 入力
        {
            webDriverOperations.overrideText(id("targetValueStr"), "001");
            webDriverOperations.overrideText(id("format"), "^0+(?!$)");
            webDriverOperations.click(id("suppress"));
        }
        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("resultString")), is("1"));
        }
    }

    /**
     * <ul>
     * <li>サプレスされた文字列を取得できることを確認する</li>
     * </ul>
     */
    @Test
    public void testSTPR0201003() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("stpr0201003"));
        }
        // 入力
        {
            webDriverOperations.overrideText(id("targetValueStr"), "000");
            webDriverOperations.overrideText(id("format"), "^0+(?!$)");
            webDriverOperations.click(id("suppress"));
        }
        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("resultString")), is("0"));
        }
    }

    /**
     * <ul>
     * <li>[stpr0301001]サロゲートペアを考慮した文字列長の取得ができることを確認</li>
     * </ul>
     */
    @Test
    public void testSTPR0301001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("stpr0301001"));
        }
        // 入力
        {
            webDriverOperations.overrideText(id("targetValue"), "𠮷田太郎");
            webDriverOperations.click(id("get"));
        }
        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("resultLength")), is("5"));
            assertThat(webDriverOperations
                    .getText(id("resultLengthConsideringSurrogate")), is("4"));
        }
    }

    /**
     * <ul>
     * <li>サロゲートペアを考慮した指定範囲の文字列取得ができることを確認</li>
     * </ul>
     */
    @Test
    public void testSTPR0302001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("stpr0302001"));
        }
        // 入力
        {
            webDriverOperations.overrideText(id("targetValue"), "𠮷田 太郎");
            webDriverOperations.overrideText(id("startIndex"), "0");
            webDriverOperations.overrideText(id("endIndex"), "2");
            webDriverOperations.click(id("get"));
        }
        // 取得した文字列を確認
        {
            assertThat(webDriverOperations
                    .getText(id("resultStringConsideringSurrogate")), is("𠮷田"));
            assertThat(webDriverOperations.getText(id("resultString")),
                    is("𠮷"));
        }
    }

    /**
     * <ul>
     * <li>サロゲートペアを考慮した文字列分割ができることを確認</li>
     * </ul>
     */
    @Test
    public void testSTPR0303001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("stpr0303001"));
        }
        // 入力
        {
            webDriverOperations.overrideText(id("targetValue"), "𠮷田 太郎");
            webDriverOperations.overrideText(id("splitRegex"), " ");
            webDriverOperations.click(id("split"));
        }
        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("resultString")),
                    is("[𠮷田, 太郎]"));
        }
    }

    /**
     * <ul>
     * <li>サロゲートペアを考慮した文字列分割ができることを確認</li>
     * </ul>
     */
    @Test
    public void testSTPR0303002() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("stpr0303002"));
        }
        // 入力
        {
            webDriverOperations.overrideText(id("targetValue"), "あいう𠮷えお");
            webDriverOperations.overrideText(id("splitRegex"), "𠮷");
            webDriverOperations.click(id("split"));
        }
        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("resultString")),
                    is("[あいう, えお]"));
        }
    }

    /**
     * <ul>
     * <li>Javaのバージョンによるsplitの挙動の違いを確認</li>
     * </ul>
     */
    @Test
    public void testSTPR0304001() {

        String answerString = "";

        // メニュー画面の操作
        {
            webDriverOperations.click(id("stpr0304001"));
        }
        // 入力
        {
            webDriverOperations.overrideText(id("targetValue"), "ABC");
            webDriverOperations.overrideText(id("splitRegex"), "");
            webDriverOperations.click(id("split"));
        }
        // 取得した文字列を確認
        {
            if (webDriverOperations.isJvm8OrLater()) {
                answerString = "[A, B, C]";
            } else {
                answerString = "[, A, B, C]";
            }

            assertThat(webDriverOperations.getText(id("resultString")),
                    is(answerString));
        }
    }

    /**
     * <ul>
     * <li>正規化形式（NFD）で正規化できることを確認</li>
     * </ul>
     */
    @Test
    public void testSTPR0401001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("stpr0401001"));
        }
        // 入力
        {
            webDriverOperations.overrideText(id("targetValue"), "モジﾓｼﾞ");
            webDriverOperations.select(id("normalizationForm"), "NFD");
            webDriverOperations.click(id("normalizer"));
        }
        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("resultString")),
                    is("モシ\u3099ﾓｼﾞ"));
        }
    }

    /**
     * <ul>
     * <li>正規化形式（NFC）で正規化できることを確認</li>
     * </ul>
     */
    @Test
    public void testSTPR0402001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("stpr0402001"));
        }
        // 入力
        {
            webDriverOperations.overrideText(id("targetValue"), "モシ\u3099ﾓｼﾞ");
            webDriverOperations.select(id("normalizationForm"), "NFC");
            webDriverOperations.click(id("normalizer"));
        }
        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("resultString")),
                    is("モジﾓｼﾞ"));
        }
    }

    /**
     * <ul>
     * <li>正規化形式（NFKD）で正規化できることを確認</li>
     * </ul>
     */
    @Test
    public void testSTPR0403001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("stpr0403001"));
        }
        // 入力
        {
            webDriverOperations.overrideText(id("targetValue"), "モジﾓｼﾞ");
            webDriverOperations.select(id("normalizationForm"), "NFKD");
            webDriverOperations.click(id("normalizer"));
        }
        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("resultString")),
                    is("モシ\u3099モシ\u3099"));
        }
    }

    /**
     * <ul>
     * <li>正規化形式（NFKC）で正規化できることを確認</li>
     * </ul>
     */
    @Test
    public void testSTPR0404001() {

        // メニュー画面の操作
        {
            webDriverOperations.click(id("stpr0404001"));
        }
        // 入力
        {
            webDriverOperations.overrideText(id("targetValue"), "モシ\u3099ﾓｼﾞ");
            webDriverOperations.select(id("normalizationForm"), "NFKC");
            webDriverOperations.click(id("normalizer"));
        }
        // 取得した文字列を確認
        {
            assertThat(webDriverOperations.getText(id("resultString")),
                    is("モジモジ"));
        }
    }

}
