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
package jp.co.ntt.fw.spring.functionaltest.selenium.xspr;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

public class XSSProtectionTest extends FunctionTestSupport {

    private boolean acceptNextAlert = true;

    /**
     * <ul>
     * <li>出力された値が「<script>alert("XSS Attack!")</script>」であること</li>
     * <li>alertのダイアログが出力されていないこと</li>
     * </ul>
     */
    @Test
    public void testXSPR0101001() {

        webDriverOperations.click(By.id("xspr0101"));
        webDriverOperations.overrideText(By.id("text-output"),
                "<script>alert(\"XSS Attack!\")</script>");
        webDriverOperations.click(By.id("btn-output"));

        // It is an error if the dialog alert has gone out
        assertThat(webDriverOperations.getText(By.id("xssOutput")), is(
                "<script>alert(\"XSS Attack!\")</script>"));

    }

    /**
     * <ul>
     * <li>出力された値が、「<script>alert('XSS Attack!')</script>」であること</li>
     * <li>alertのダイアログが出力されていないこと</li>
     * </ul>
     */
    @Test
    public void testXSPR0101002() {

        webDriverOperations.click(By.id("xspr0101"));
        webDriverOperations.overrideText(By.id("text-output"),
                "<script>alert('XSS Attack!')</script>");
        webDriverOperations.click(By.id("btn-output"));

        // It is an error if the dialog alert has gone out
        assertThat(webDriverOperations.getText(By.id("xssOutput")), is(
                "<script>alert('XSS Attack!')</script>"));

    }

    /**
     * <ul>
     * <li>出力された値が、「Spring Framework」であること</li>
     * <li>alertのダイアログが出力されていないこと</li>
     * </ul>
     */
    @Test
    public void testXSPR0101003() {

        webDriverOperations.click(By.id("xspr0101"));
        webDriverOperations.overrideText(By.id("text-output"),
                "Spring Framework");
        webDriverOperations.click(By.id("btn-output"));
        assertThat(webDriverOperations.getText(By.id("xssOutput")), is(
                "Spring Framework"));

    }

    /**
     * <ul>
     * <li>出力された値が、「';alert('XSS Attack!');aaa='message」であること</li>
     * </ul>
     */
    @Test
    public void testXSPR0201001() {

        webDriverOperations.click(By.id("xspr0201001"));
        webDriverOperations.click(By.id("write"));

        assertThat(closeAlertAndGetItsText(), is(
                "';alert('XSS Attack!');aaa='message"));

    }

    /**
     * <ul>
     * <li>出力された値が、「';alert("XSS Attack!");aaa='message」であること</li>
     * </ul>
     */
    @Test
    public void testXSPR0201002() {

        webDriverOperations.click(By.id("xspr0201002"));
        webDriverOperations.click(By.id("write"));

        assertThat(closeAlertAndGetItsText(), is(
                "';alert(\"XSS Attack!\");aaa='message"));

    }

    /**
     * <ul>
     * <li>出力された値が、「Spring Framework」 であること</li>
     * </ul>
     */
    @Test
    public void testXSPR0201003() {

        webDriverOperations.click(By.id("xspr0201003"));
        webDriverOperations.click(By.id("write"));

        assertThat(closeAlertAndGetItsText(), is("Spring Framework"));

    }

    /**
     * <li>出力された値が、「output is '); alert('XSS Attack!'); //.」であること</li>
     */
    @Test
    public void testXSPR0301001() {

        webDriverOperations.click(By.id("xspr0301001"));
        webDriverOperations.click(By.id("write"));

        assertThat(closeAlertAndGetItsText(), is(
                "output is '); alert('XSS Attack!'); //."));

    }

    /**
     * <li>出力された値が、「output is '); alert("XSS Attack!"); //.」であること</li>
     */
    @Test
    public void testXSPR0301002() {

        webDriverOperations.click(By.id("xspr0301002"));
        webDriverOperations.click(By.id("write"));

        assertThat(closeAlertAndGetItsText(), is(
                "output is '); alert(\"XSS Attack!\"); //."));

    }

    /**
     * <li>出力された値が、「output is Spring Framework.」であること</li>
     */
    @Test
    public void testXSPR0301003() {

        webDriverOperations.click(By.id("xspr0301003"));
        webDriverOperations.click(By.id("write"));

        assertThat(closeAlertAndGetItsText(), is(
                "output is Spring Framework."));

    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = webDriverOperations.getWebDriver().switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

}
