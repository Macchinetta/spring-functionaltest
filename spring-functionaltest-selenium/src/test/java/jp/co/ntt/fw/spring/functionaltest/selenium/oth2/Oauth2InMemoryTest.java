/*
 * Copyright 2014-2018 NTT Corporation.
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
 */
package jp.co.ntt.fw.spring.functionaltest.selenium.oth2;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.openqa.selenium.By.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.annotation.IfProfileValue;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

//Thymeleaf版未実装のためJSPのみ実行
@IfProfileValue(name = "test.environment.view", values = { "jsp" })
public class Oauth2InMemoryTest extends FunctionTestSupport {

    /** Title of GET Operation Result Page. */
    private static final String TITLE_GET_OPERATION = "GETメソッドの結果";

    /** Title of POST Operation Result Page. */
    private static final String TITLE_POST_OPERATION = "POSTメソッドの結果";

    /** Title of PUT Operation Result Page. */
    private static final String TITLE_PUT_OPERATION = "PUTメソッドの結果";

    /** Title of DELETE Operation Result Page. */
    private static final String TITLE_DELETE_OPERATION = "DELETEメソッドの結果";

    @Value("${selenium.serverUrl.oth2}")
    protected String serverUrlOth2;

    @Before
    public void setUp() {
        // トップ画面での操作
        {
            webDriverOperations.getWebDriver().manage().window().maximize();
            initialize();
        }
    }

    private void initialize() {

        if (webDriverOperations.exists(id("loginWith"))) {

            // Client login
            webDriverOperations.overrideText(id("username"), "demo");
            webDriverOperations.overrideText(id("password"), "demo");
            webDriverOperations.click(id("send"));

        }

        // Clear
        webDriverOperations.click(id("oth20000001"));
        webDriverOperations.click(id("springTestTop"));
        webDriverOperations.click(id("oth2Link"));

    }

    private void authServerLogin() {

        if (webDriverOperations.exists(id("loginWith"))) {

            // Client login
            webDriverOperations.overrideText(id("username"), "demo");
            webDriverOperations.overrideText(id("password"), "demo");
            webDriverOperations.click(id("send"));

        }

    }

    /**
     * <ul>
     * <li>Check response from resource server when using the authorization code grant(GET).<br/>
     * Accessing resource server is using InMemoryTokenStore.</li>
     * </ul>
     */
    @Test
    public void testOTH20201001() {

        // Menu operation
        webDriverOperations.click(id("oth20201001"));
        authServerLogin();
        // Approve resource access
        webDriverOperations.click(id("scope.READ_approve"));
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("title")), is(
                TITLE_GET_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("token")), not(""));
    }

    /**
     * <ul>
     * <li>Check response from resource server when using the authorization code grant(POST).<br/>
     * Accessing resource server is using InMemoryTokenStore.</li>
     * </ul>
     */
    @Test
    public void testOTH20201002() {

        // Menu operation
        webDriverOperations.click(id("oth20201002"));
        authServerLogin();
        // Approve resource access
        webDriverOperations.click(id("scope.CREATE_approve"));
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("title")), is(
                TITLE_POST_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("token")), not(""));
    }

    /**
     * <ul>
     * <li>Check response from resource server when using the authorization code grant(PUT).<br/>
     * Accessing resource server is using InMemoryTokenStore.</li>
     * </ul>
     */
    @Test
    public void testOTH20201003() {

        // Menu operation
        webDriverOperations.click(id("oth20201003"));
        authServerLogin();
        // Approve resource access
        webDriverOperations.click(id("scope.UPDATE_approve"));
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("title")), is(
                TITLE_PUT_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("token")), not(""));
    }

    /**
     * <ul>
     * <li>Check response from resource server when using the authorization code grant(DELETE).<br/>
     * Accessing resource server is using InMemoryTokenStore.</li>
     * </ul>
     */
    @Test
    public void testOTH20201004() {

        // Menu operation
        webDriverOperations.click(id("oth20201004"));
        authServerLogin();
        // Approve resource access
        webDriverOperations.click(id("scope.DELETE_approve"));
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("title")), is(
                TITLE_DELETE_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("token")), not(""));
    }

    /**
     * <ul>
     * <li>Check token value when using the client credentials and the token is revoked.</li> Accessing resource server is using
     * InMemoryTokenStore.</li>
     * </ul>
     */
    @Test
    public void testOTH20701002() {

        // Menu operation
        webDriverOperations.click(id("oth20201001"));
        authServerLogin();

        assertThat(webDriverOperations.getText(id("title")), is(
                TITLE_GET_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("token")), not(""));

        String token_before = webDriverOperations.getText(id("token"));

        webDriverOperations.click(id("springTestTop"));
        webDriverOperations.click(id("oth2Link"));
        // Menu operation
        webDriverOperations.click(id("oth20701002"));

        assertThat(webDriverOperations.getText(id("result")), is("success"));

        webDriverOperations.click(id("springTestTop"));
        webDriverOperations.click(id("oth2Link"));

        // Menu operation
        webDriverOperations.click(id("oth20201001"));
        authServerLogin();
        // Approve resource access
        webDriverOperations.click(id("scope.READ_approve"));
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("title")), is(
                TITLE_GET_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("token")), not(""));

        String token_after = webDriverOperations.getText(id("token"));

        assertThat(token_before, not(token_after));
    }

    @Override
    protected String getPackageRootUrl() {
        return serverUrlOth2 + "/" + contextName + "/oth2/";
    }
}
