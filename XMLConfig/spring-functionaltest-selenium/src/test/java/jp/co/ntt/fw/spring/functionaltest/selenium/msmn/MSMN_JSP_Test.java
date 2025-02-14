/*
 * Copyright(c) 2025 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.selenium.msmn;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

public class MSMN_JSP_Test extends FunctionTestSupport {

    private static final String VIEW_TYPE = "jsp";

    @Before
    public void setUp() {}

    /**
     * <ul>
     * <li>[MSMN0101001] プロパティを使用する際の設定</li>
     * <li>[MSMN0102001] プロパティに設定したメッセージの表示（ISO-8859-1）</li>
     * </ul>
     */
    @Test
    public void testMSMN0102001() {
        webDriverOperations.click(id("msmn0102001_" + VIEW_TYPE));
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Display YMD"));

        String year = webDriverOperations.getText(By.xpath("//*[@id=\"sampleForm\"]/label[1]"));
        String month = webDriverOperations.getText(By.xpath("//*[@id=\"sampleForm\"]/label[2]"));
        String day = webDriverOperations.getText(By.xpath("//*[@id=\"sampleForm\"]/label[3]"));

        assertThat(year, is("Year"));
        assertThat(month, is("Month"));
        assertThat(day, is("Day"));

        // 上記のみだとISO-8859-1かどうかわからないため、ユーザ名も確認
        String userName = webDriverOperations.getText(By.id("loginUserName"));
        assertThat(userName, is("ゲスト"));
    }

    /**
     * <ul>
     * <li>[MSMN0102002] プロパティに設定したメッセージの表示（UTF-8）</li>
     * </ul>
     */
    @Test
    public void testMSMN0102002() {
        webDriverOperations.click(id("msmn0102002_" + VIEW_TYPE));
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "Display YMD(UTF-8)"));

        String year = webDriverOperations.getText(By.xpath("//*[@id=\"sampleForm\"]/label[1]"));
        String month = webDriverOperations.getText(By.xpath("//*[@id=\"sampleForm\"]/label[2]"));
        String day = webDriverOperations.getText(By.xpath("//*[@id=\"sampleForm\"]/label[3]"));

        assertThat(year, is("年"));
        assertThat(month, is("月"));
        assertThat(day, is("日"));
    }

    /**
     * <ul>
     * <li>[MSMN0201001] 結果メッセージの表示（通常）</li>
     * </ul>
     */
    @Test
    public void testMSMN0201001() {
        webDriverOperations.click(id("msmn0201001_" + VIEW_TYPE));
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "ResultMessageの表示"));

        WebElement div = webDriverOperations.getWebDriver().findElement(By.xpath("//div[2]/div"));

        assertThat(div.getAttribute("class"), is("alert alert-error"));
        assertThat(div.getText(), is("There are inconsistencies in the data."));
    }

    /**
     * <ul>
     * <li>[MSMN0202001] 結果メッセージの表示（複数メッセージの設定）</li>
     * <li>[MSMN0203001] 結果メッセージの表示（プレースホルダー）</li>
     * <li>[MSMN0203002] 結果メッセージの表示（カンマあり）</li>
     * </ul>
     */
    @Test
    public void testMSMN0202001() {
        webDriverOperations.click(id("msmn0202001_" + VIEW_TYPE));
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "ResultMessageの表示"));

        WebElement div = webDriverOperations.getWebDriver().findElement(By.xpath("//div[2]/div"));
        assertThat(div.getAttribute("class"), is("alert alert-error"));

        List<WebElement> liElements = div.findElements(By.tagName("li"));
        assertThat(liElements.get(0).getText().trim(),
                is("There are inconsistencies in the data."));
        assertThat(liElements.get(1).getText().trim(),
                is("Cannot upload, Because the file size must be less than 1,024MB."));
    }

    /**
     * <ul>
     * <li>[MSMN0203003] 結果メッセージの表示（カンマなし）</li>
     * </ul>
     */
    @Test
    public void testMSMN0203003() {
        webDriverOperations.click(id("msmn0203003_" + VIEW_TYPE));
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "ResultMessageの表示"));

        WebElement div = webDriverOperations.getWebDriver().findElement(By.xpath("//div[2]/div"));

        assertThat(div.getAttribute("class"), is("alert alert-error"));
        assertThat(div.getText(),
                is("Cannot upload, Because the file size must be less than 1024MB."));
    }

    /**
     * <ul>
     * <li>[MSMN0204001] 結果メッセージの表示（メッセージタイプの設定）</li>
     * <li>[MSMN0205001] 結果メッセージの表示（属性の指定）</li>
     * <li>[MSMN0205001] 結果メッセージの表示（CSSの適用）</li>
     * </ul>
     */
    @Test
    public void testMSMN0205001() {
        webDriverOperations.click(id("msmn0205001_" + VIEW_TYPE));
        webDriverOperations
                .waitForDisplayed(textToBe(By.id("screenTitle"), "ResultMessageの表示（複数）"));

        List<WebElement> divs =
                webDriverOperations.getWebDriver().findElements(By.xpath("//div[2]/div"));

        assertThat(divs.get(0).getAttribute("class"), is("alert alert-success"));
        assertThat(getColer(divs.get(0)), is("#468847")); // デフォルト値のまま

        assertThat(divs.get(1).getAttribute("class"), is("alert alert-info"));
        assertThat(getColer(divs.get(1)), is("#2d7091")); // msmn.css

        assertThat(divs.get(2).getAttribute("class"), is("alert alert-warning"));
        assertThat(getColer(divs.get(2)), is("#e28327")); // msmn.css

        assertThat(divs.get(3).getAttribute("class"), is("alert alert-error"));
        assertThat(getColer(divs.get(3)), is("#d85030")); // msmn.css

        assertThat(divs.get(4).getAttribute("class"), is("alert alert-danger"));
        assertThat(getColer(divs.get(4)), is("#b94a48")); // デフォルト値のまま

        assertThat(divs.get(5).getAttribute("class"), is("alert alert-primary"));
        assertThat(getColer(divs.get(5)), is("#004085")); // msmn.css

        assertThat(divs.get(6).getAttribute("class"), is("alert alert-secondary"));
        assertThat(getColer(divs.get(6)), is("#383d41")); // msmn.css

        assertThat(divs.get(7).getAttribute("class"), is("alert alert-light"));
        assertThat(getColer(divs.get(7)), is("#818182")); // msmn.css

        assertThat(divs.get(8).getAttribute("class"), is("alert alert-dark"));
        assertThat(getColer(divs.get(8)), is("#1b1e21")); // msmn.css
    }

    /**
     * <ul>
     * <li>[MSMN0206001] 業務例外メッセージの表示（BusinessException）</li>
     * </ul>
     */
    @Test
    public void testMSMN0206001() {
        webDriverOperations.click(id("msmn0206_" + VIEW_TYPE));
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "業務例外メッセージ"));

        assertFalse("業務エラーメッセージがすでに表示されている。",
                webDriverOperations.exists(By.className("alert-error")));

        webDriverOperations.click(id("msmn0206001_" + VIEW_TYPE));
        webDriverOperations.waitForDisplayed(By.className("alert-error"));

        WebElement div = webDriverOperations.getWebDriver().findElement(By.xpath("//div[2]/div"));

        assertThat(div.getAttribute("class"), is("alert alert-error"));
        assertThat(div.getText(), is("There are inconsistencies in the data."));

        dbLogAssertOperations.assertContainsByRegexExceptionMessage(webDriverOperations.getXTrack(),
                null, "ResultMessages.*", "org.terasoluna.gfw.common.exception.BusinessException");
    }

    /**
     * <ul>
     * <li>[MSMN0206001] 業務例外メッセージの表示（ResourceNotFoundException）</li>
     * </ul>
     */
    @Test
    public void testMSMN0206002() {
        webDriverOperations.click(id("msmn0206_" + VIEW_TYPE));
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "業務例外メッセージ"));

        assertFalse("業務エラーメッセージがすでに表示されている。",
                webDriverOperations.exists(By.className("alert-error")));

        webDriverOperations.click(id("msmn0206002_" + VIEW_TYPE));
        webDriverOperations.waitForDisplayed(By.className("alert-error"));

        WebElement div = webDriverOperations.getWebDriver().findElement(By.xpath("//div[2]/div"));

        assertThat(div.getAttribute("class"), is("alert alert-error"));
        assertThat(div.getText(), is("There are inconsistencies in the data."));

        dbLogAssertOperations.assertContainsByRegexExceptionMessage(webDriverOperations.getXTrack(),
                null, "ResultMessages.*",
                "org.terasoluna.gfw.common.exception.ResourceNotFoundException");
    }

    /**
     * <ul>
     * <li>[MSMN0301001] 独自メッセージタイプの作成</li>
     * </ul>
     */
    @Test
    public void testMSMN0301001() {
        webDriverOperations.click(id("msmn0301001_" + VIEW_TYPE));
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "ResultMessageの表示"));

        WebElement div = webDriverOperations.getWebDriver().findElement(By.xpath("//div[2]/div"));

        assertThat(div.getAttribute("class"), is("alert alert-notice"));
        assertThat(div.getText(), is(
                "The recommended change interval has passed password. Please change your password."));
    }

    /**
     * <ul>
     * <li>[MSMN0401001] <t:messagesPanel>タグの属性変更（panelElement）</li>
     * <li>[MSMN0401002] <t:messagesPanel>タグの属性変更（panelClassName）</li>
     * <li>[MSMN0401003] <t:messagesPanel>タグの属性変更（panelTypeClassPrefix）</li>
     * <li>[MSMN0401004] <t:messagesPanel>タグの属性変更（messagesType）</li>
     * <li>[MSMN0401005] <t:messagesPanel>タグの属性変更（outerElement）</li>
     * <li>[MSMN0401006] <t:messagesPanel>タグの属性変更（innerElement）</li>
     * <li>[MSMN0401007] <t:messagesPanel>タグの属性変更（disableHtmlEscape=true）</li>
     * <li>[MSMN0401008] <t:messagesPanel>タグの属性変更（disableHtmlEscape=false）</li>
     * </ul>
     */
    @Test
    public void testMSMN0401001() {
        webDriverOperations.click(id("msmn04_" + VIEW_TYPE));
        webDriverOperations
                .waitForDisplayed(textToBe(By.id("screenTitle"), "messagesPanelタグの属性変更"));

        WebElement div1 = webDriverOperations.getWebDriver().findElement(By.id("panelElement"));
        assertThat(div1.findElement(By.tagName("span")).getAttribute("class"),
                is("alert alert-info"));

        WebElement div2 = webDriverOperations.getWebDriver()
                .findElement(By.id("panelClassName_panelTypeClassPrefix"));
        assertThat(div2.findElement(By.tagName("div")).getAttribute("class"), is("error"));

        WebElement div3 =
                webDriverOperations.getWebDriver().findElement(By.id("outerElement_innerElement"));
        List<WebElement> spans = div3.findElements(By.tagName("span"));
        assertThat(spans.get(0).getText(), is("There are inconsistencies in the data."));
        assertThat(spans.get(1).getText(),
                is("Cannot upload, Because the file size must be less than 1,024MB."));

        WebElement div4 =
                webDriverOperations.getWebDriver().findElement(By.id("disableHtmlEscape_true"));
        assertThat(div4.findElement(By.tagName("div")).getText(),
                is("Please confirm order content. If this orders submitted, cannot cancel."));

        WebElement div5 =
                webDriverOperations.getWebDriver().findElement(By.id("disableHtmlEscape_false"));
        assertThat(div5.findElement(By.tagName("div")).getText(), is(
                "Please confirm order content. <font style=\"color: red; font-size: 16px;\">If this orders submitted, cannot cancel.</font>"));
    }

    /**
     * <ul>
     * <li>[MSMN0501001] &lt;t:messagesPanel&gt;でjava.lang.Stringを表示する</li>
     * <li>[MSMN0501003] &lt;t:messagesPanel&gt;でjava.util.Listを表示する</li>
     * </ul>
     */
    @Test
    public void testMSMN0501001() {
        webDriverOperations.click(id("msmn05_" + VIEW_TYPE));
        webDriverOperations
                .waitForDisplayed(textToBe(By.id("screenTitle"), "ResultMessage以外のオブジェクト"));

        WebElement div1 = webDriverOperations.getWebDriver().findElement(By.id("StringMessages"));
        assertThat(div1.findElement(By.tagName("div")).getText(), is("String Message"));

        WebElement div2 = webDriverOperations.getWebDriver().findElement(By.id("ListMessages"));
        List<WebElement> spans = div2.findElements(By.tagName("li"));
        assertThat(spans.get(0).getText(), is("List Message1"));
        assertThat(spans.get(1).getText(), is("List Message2"));
    }

    /**
     * <ul>
     * <li>[MSMN0601002] 定数を使用したメッセージの表示</li>
     * </ul>
     */
    @Test
    public void testMSMN0601002() {
        webDriverOperations.click(id("msmn0601002_" + VIEW_TYPE));
        webDriverOperations.waitForDisplayed(textToBe(By.id("screenTitle"), "ResultMessageの表示"));

        WebElement div = webDriverOperations.getWebDriver().findElement(By.xpath("//div[2]/div"));
        assertThat(div.getAttribute("class"), is("alert alert-error"));

        List<WebElement> liElements = div.findElements(By.tagName("li"));
        assertThat(liElements.get(0).getText().trim(),
                is("There are inconsistencies in the data."));
        assertThat(liElements.get(1).getText().trim(),
                is("Cannot upload, Because the file size must be less than 1,024MB."));
    }

    /**
     * CSSでcolerを取得するとRGBで取得されるので、HEX変換した値を返却する。
     * 
     * @param element div
     * @return coler(HEX)
     */
    private String getColer(WebElement element) {
        String[] numbers =
                element.getCssValue("color").replace("rgb(", "").replace(")", "").split(", ");
        int r = Integer.parseInt(numbers[0]);
        int g = Integer.parseInt(numbers[1]);
        int b = Integer.parseInt(numbers[2]);

        return String.format("#%02x%02x%02x", r, g, b);
    }
}
