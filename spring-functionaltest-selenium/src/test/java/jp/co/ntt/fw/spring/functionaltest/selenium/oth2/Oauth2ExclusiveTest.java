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
package jp.co.ntt.fw.spring.functionaltest.selenium.oth2;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupportForMultiBrowser;
import jp.co.ntt.fw.spring.functionaltest.selenium.WebDriverOperations;

public class Oauth2ExclusiveTest extends FunctionTestSupportForMultiBrowser {

    /** Title of GET Operation Result Page. */
    private static final String TITLE_GET_OPERATION = "GETメソッドの結果";

    /** Title of POST Operation Result Page. */
    private static final String TITLE_POST_OPERATION = "POSTメソッドの結果";

    @Value("${selenium.serverUrl.oth2}")
    protected String serverUrlOth2;

    private WebDriverOperations setUpWebDriverAndLogin(int webDriverId,
            String userName) {
        // quit browser
        quitWebDriverWebDriverOperations(webDriverId);

        // Set up WebDriver
        WebDriverOperations operations = setUpWebDriverOperations(webDriverId);

        // Login
        operations.overrideText(id("username"), userName);
        operations.overrideText(id("password"), "demo");
        operations.click(id("send"));
        return operations;

    }

    private void initialize(WebDriverOperations operations) {
        // Clear
        operations.click(id("oth20000001"));
        operations.click(id("springTestTop"));
        operations.click(id("oth2Link"));

    }

    private void authServerLoginWithWebDriver(WebDriverOperations operations,
            String userName) {
        // AuthServer login
        operations.overrideText(id("username"), userName);
        operations.overrideText(id("password"), "demo");
        operations.click(id("send"));
        operations.waitForDisplayed(textToBe(xpath("//h1"), "OAuth Approval"));
    }

    private void inputResourceOwnerCredential(WebDriverOperations operations) {
        operations.overrideText(id("username"), "demo");
        operations.overrideText(id("password"), "demo");
        operations.click(id("send"));
    }

    /**
     * <ul>
     * <li>Check response from resource server when using the authorization code grant(GET,POST).<br/>
     * requesting with GET and POST at the same time with the different screen.</li>
     * </ul>
     * @throws InterruptedException
     */
    @Test
    public void testOTH21201001() throws InterruptedException {

        // setup WebDriver for demo user
        WebDriverOperations operationsGet = setUpWebDriverAndLogin(0, "demo");
        initialize(operationsGet);
        // setup WebDriver for demo2 user
        WebDriverOperations operationsPost = setUpWebDriverAndLogin(1, "demo2");

        operationsGet.click(id("oth20101001"));
        authServerLoginWithWebDriver(operationsGet, "demo");

        operationsGet.click(id("scope.READ_approve"));
        operationsGet.click(id("authorize"));
        operationsGet.waitForDisplayed(textToBe(id("title"),
                TITLE_GET_OPERATION));
        assertThat(operationsGet.getText(id("response")), is("Success"));

        operationsPost.click(id("oth20101002"));
        authServerLoginWithWebDriver(operationsPost, "demo2");

        operationsPost.click(id("scope.CREATE_approve"));
        operationsPost.click(id("authorize"));
        operationsPost.waitForDisplayed(textToBe(id("title"),
                TITLE_POST_OPERATION));
        assertThat(operationsPost.getText(id("response")), is("Success"));

        // access again with "demo" user
        operationsGet.click(id("springTestTop"));
        operationsGet.click(id("oth2Link"));
        operationsGet.click(id("oth20101001"));
        operationsGet.waitForDisplayed(textToBe(id("title"),
                TITLE_GET_OPERATION));
        assertThat(operationsGet.getText(id("response")), is("Success"));

    }

    /**
     * <ul>
     * <li>Check response from resource server when using the authorization code grant from two clients with same user.<br/>
     * requesting with GET and POST at the same time with the different screen.</li>
     * </ul>
     * @throws InterruptedException
     */
    @Test
    public void testOTH21201002() throws InterruptedException {

        // setup WebDriver for demo user
        WebDriverOperations operationsGet = setUpWebDriverAndLogin(0, "demo");
        initialize(operationsGet);
        operationsGet.click(id("oth20101001"));
        authServerLoginWithWebDriver(operationsGet, "demo");

        operationsGet.click(id("scope.READ_approve"));
        operationsGet.click(id("authorize"));
        operationsGet.waitForDisplayed(textToBe(id("title"),
                TITLE_GET_OPERATION));
        assertThat(operationsGet.getText(id("response")), is("Success"));

        // setup WebDriver for demo user
        WebDriverOperations operationsGetOtherClient = setUpWebDriverAndLogin(1,
                "demo");
        operationsGetOtherClient.click(id("oth20104001"));
        inputResourceOwnerCredential(operationsGetOtherClient);

        // Resource Owner Credential Grant Menu operation
        operationsGetOtherClient.click(id("oth20104001"));

        operationsGetOtherClient.waitForDisplayed(textToBe(id("title"),
                TITLE_GET_OPERATION));
        assertThat(operationsGetOtherClient.getText(id("response")), is(
                "Success"));

    }

    /**
     * <ul>
     * <li>Check response from resource server when using the authorization code grant from two clients with same user.<br/>
     * requesting with GET and POST at the same time with the different screen.</li>
     * </ul>
     * @throws InterruptedException
     */
    @Test
    public void testOTH21201003() throws InterruptedException {

        // setup WebDriver for demo user
        WebDriverOperations operationsGet = setUpWebDriverAndLogin(0, "demo");
        initialize(operationsGet);
        operationsGet.click(id("oth20101001"));
        authServerLoginWithWebDriver(operationsGet, "demo");

        operationsGet.click(id("scope.READ_approve"));
        operationsGet.click(id("scope.CREATE_approve"));
        operationsGet.click(id("scope.UPDATE_approve"));
        operationsGet.click(id("scope.DELETE_approve"));
        operationsGet.click(id("authorize"));
        operationsGet.waitForDisplayed(textToBe(id("title"),
                TITLE_GET_OPERATION));
        assertThat(operationsGet.getText(id("response")), is("Success"));

        String token_before = operationsGet.getText(id("token"));

        operationsGet.click(id("springTestTop"));
        operationsGet.click(id("oth2Link"));

        // setup WebDriver for demo user
        operationsGet.click(id("oth20201001"));
        authServerLoginWithWebDriver(operationsGet, "demo");

        operationsGet.click(id("scope.READ_approve"));
        operationsGet.click(id("authorize"));
        operationsGet.waitForDisplayed(textToBe(id("title"),
                TITLE_GET_OPERATION));
        assertThat(operationsGet.getText(id("response")), is("Success"));

        String token_after = operationsGet.getText(id("token"));

        assertThat(token_before, not(token_after));

    }

    @Override
    protected String getPackageRootUrl() {
        return serverUrlOth2 + "/" + contextName + "/oth2/";
    }

}
