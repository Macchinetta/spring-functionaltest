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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.annotation.IfProfileValue;

import jp.co.ntt.fw.spring.functionaltest.selenium.ApServerName;
import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

//Thymeleaf版未実装のためJSPのみ実行
@IfProfileValue(name = "test.environment.view", values = { "jsp" })
public class Oauth2Test extends FunctionTestSupport {

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
            webDriverOperations.setDefaultTimeoutForImplicitlyWait(30);
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

    private void inputResourceOwnerCredential() {
        webDriverOperations.overrideText(id("username"), "demo");
        webDriverOperations.overrideText(id("password"), "demo");
        webDriverOperations.click(id("send"));
    }

    /**
     * <ul>
     * <li>Check response from resource server when using the authorization code grant(GET).</li>
     * </ul>
     */
    @Test
    public void testOTH20101001() {

        // Menu operation
        webDriverOperations.click(id("oth20101001"));
        authServerLogin();
        // Approve resource access
        webDriverOperations.click(id("scope.READ_approve"));
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("title")), is(
                TITLE_GET_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("token")), not(""));
        assertThat(webDriverOperations.getText(id("name")), is("testClient"));
    }

    /**
     * <ul>
     * <li>Check response from resource server when using the authorization code grant(POST).</li>
     * </ul>
     */

    @Test
    public void testOTH20101002() {

        // Menu operation
        webDriverOperations.click(id("oth20101002"));
        authServerLogin();
        // Approve resource access
        webDriverOperations.click(id("scope.CREATE_approve"));
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("title")), is(
                TITLE_POST_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("token")), not(""));
        assertThat(webDriverOperations.getText(id("name")), is("demo"));
    }

    /**
     * <ul>
     * <li>Check response from resource server when using the authorization code grant(POST).</li>
     * </ul>
     */
    @Test
    public void testOTH20101003() {

        // Menu operation
        webDriverOperations.click(id("oth20101003"));
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
     * <li>Check response from resource server when using the authorization code grant(DELETE).</li>
     * </ul>
     */
    @Test
    public void testOTH20101004() {

        // Menu operation
        webDriverOperations.click(id("oth20101004"));
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
     * <li>Check response from resource server when using the authorization code grant(DELETE).<br/>
     * In this test, request to resource server will be sent twice.</li>
     * </ul>
     */
    @Test
    public void testOTH20101005() {

        // Menu operation
        webDriverOperations.click(id("oth20101001"));
        authServerLogin();
        // Approve resource access
        webDriverOperations.click(id("scope.READ_approve"));
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("title")), is(
                TITLE_GET_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("token")), not(""));

        String token_before = webDriverOperations.getText(id("token"));

        // Approve resource access
        webDriverOperations.click(id("springTestTop"));
        webDriverOperations.click(id("oth2Link"));

        webDriverOperations.click(id("oth20101001"));

        assertThat(webDriverOperations.getText(id("title")), is(
                TITLE_GET_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("token")), not(""));

        String token_after = webDriverOperations.getText(id("token"));
        assertThat(token_after, is(token_before));

    }

    /**
     * <ul>
     * <li>Checking the log when the scope(GET) is denied.<br/>
     * In this test, the authorization code grant is used.</li>
     * </ul>
     */
    @Test
    public void testOTH20101006() {

        // Menu operation
        webDriverOperations.click(id("oth20101001"));
        authServerLogin();
        // Deny resource access
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "accessDeniedPage"));
        assertThat(webDriverOperations.getText(xpath(
                "//div[@class='alert alert-danger']/ul[1]/li[1]")), is(
                        "[e.sf.oth2.9002] Access Denied error!"));
        assertThat(webDriverOperations.getText(xpath(
                "//div[@class='alert alert-danger']/ul[2]/li[1]")), is(
                        "User denied access"));
        // Check error log
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                "org.terasoluna.gfw.common.exception.ExceptionLogger",
                "User denied access",
                "org\\.springframework\\.security\\.oauth2\\.common\\.exceptions\\.UserDeniedAuthorizationException");
        dbLogAssertOperations.assertContainsByRegexMessage(
                "jp.co.ntt.fw.spring.functionaltest.app.oth2.OAuth2ExceptionHandler",
                "error=access_denied, error_description=User denied access");

    }

    /**
     * <ul>
     * <li>Checking the log when the scope(POST) is denied.<br/>
     * In this test, the authorization code grant is used.</li>
     * </ul>
     */
    @Test
    public void testOTH20101007() {

        // Menu operation
        webDriverOperations.click(id("oth20101002"));
        authServerLogin();
        // Deny resource access
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "accessDeniedPage"));
        assertThat(webDriverOperations.getText(xpath(
                "//div[@class='alert alert-danger']/ul[1]/li[1]")), is(
                        "[e.sf.oth2.9002] Access Denied error!"));
        assertThat(webDriverOperations.getText(xpath(
                "//div[@class='alert alert-danger']/ul[2]/li[1]")), is(
                        "User denied access"));
        // Check error log
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                "org.terasoluna.gfw.common.exception.ExceptionLogger",
                "User denied access",
                "org\\.springframework\\.security\\.oauth2\\.common\\.exceptions\\.UserDeniedAuthorizationException");
        dbLogAssertOperations.assertContainsByRegexMessage(
                "jp.co.ntt.fw.spring.functionaltest.app.oth2.OAuth2ExceptionHandler",
                "error=access_denied, error_description=User denied access");

    }

    /**
     * <ul>
     * <li>Checking the log when the scope(PUT) is denied.<br/>
     * In this test, the authorization code grant is used.</li>
     * </ul>
     */
    @Test
    public void testOTH20101008() {

        // Menu operation
        webDriverOperations.click(id("oth20101003"));
        authServerLogin();
        // Deny resource access
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "accessDeniedPage"));
        assertThat(webDriverOperations.getText(xpath(
                "//div[@class='alert alert-danger']/ul[1]/li[1]")), is(
                        "[e.sf.oth2.9002] Access Denied error!"));
        assertThat(webDriverOperations.getText(xpath(
                "//div[@class='alert alert-danger']/ul[2]/li[1]")), is(
                        "User denied access"));
        // Check error log
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                "org.terasoluna.gfw.common.exception.ExceptionLogger",
                "User denied access",
                "org\\.springframework\\.security\\.oauth2\\.common\\.exceptions\\.UserDeniedAuthorizationException");
        dbLogAssertOperations.assertContainsByRegexMessage(
                "jp.co.ntt.fw.spring.functionaltest.app.oth2.OAuth2ExceptionHandler",
                "error=access_denied, error_description=User denied access");

    }

    /**
     * <ul>
     * <li>Checking the log when the scope(DELETE) is denied.<br/>
     * In this test, the authorization code grant is used.</li>
     * </ul>
     */
    @Test
    public void testOTH20101009() {

        // Menu operation
        webDriverOperations.click(id("oth20101004"));
        authServerLogin();
        // Deny resource access
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "accessDeniedPage"));
        assertThat(webDriverOperations.getText(xpath(
                "//div[@class='alert alert-danger']/ul[1]/li[1]")), is(
                        "[e.sf.oth2.9002] Access Denied error!"));
        assertThat(webDriverOperations.getText(xpath(
                "//div[@class='alert alert-danger']/ul[2]/li[1]")), is(
                        "User denied access"));
        // Check error log
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                "org.terasoluna.gfw.common.exception.ExceptionLogger",
                "User denied access",
                "org\\.springframework\\.security\\.oauth2\\.common\\.exceptions\\.UserDeniedAuthorizationException");
        dbLogAssertOperations.assertContainsByRegexMessage(
                "jp.co.ntt.fw.spring.functionaltest.app.oth2.OAuth2ExceptionHandler",
                "error=access_denied, error_description=User denied access");

    }

    /**
     * <ul>
     * <li>Check response from resource server when using the authorization code grant(DELETE:not registered scope in
     * client).</li>
     * </ul>
     */
    @Test
    public void testOTH20101010() {

        // Menu operation
        webDriverOperations.click(id("oth20101010"));
        authServerLogin();

        assertThat(webDriverOperations.getTitle(), is("System Error!"));
        assertThat(webDriverOperations.getText(xpath(
                "//div[@class='alert alert-danger']/ul[1]/li[1]")), is(
                        "[e.sf.oth2.9001] System error occurred!"));
        assertThat(webDriverOperations.getText(xpath(
                "//div[@class='alert alert-danger']/ul[2]/li[1]")), is(
                        "Invalid scope: CREATE"));
        dbLogAssertOperations.assertContainsByRegexMessage(
                "jp.co.ntt.fw.spring.functionaltest.app.oth2.OAuth2ExceptionHandler",
                "error=invalid_scope, error_description=Invalid scope: CREATE");
    }

    /**
     * <ul>
     * <li>Check response from resource server when using the implicit grant(GET).</li>
     * </ul>
     */
    @Test
    public void testOTH20102001() {

        // Menu operation
        webDriverOperations.click(id("oth20102001"));
        // Approve resource access
        webDriverOperations.click(id("scope.READ_approve"));
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("title")), is(
                "implicit grant(scope:READ)"));
    }

    /**
     * <ul>
     * <li>Check response from resource server when using the implicit grant(POST).</li>
     * </ul>
     */
    @Test
    public void testOTH20102002() {

        // Menu operation
        webDriverOperations.click(id("oth20102002"));
        // Approve resource access
        webDriverOperations.click(id("scope.CREATE_approve"));
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("title")), is(
                "implicit grant(scope:CREATE)"));
    }

    /**
     * <ul>
     * <li>Check response from resource server when using the implicit grant(PUT).</li>
     * </ul>
     */
    @Test
    public void testOTH20102003() {

        // Menu operation
        webDriverOperations.click(id("oth20102003"));
        // Approve resource access
        webDriverOperations.click(id("scope.UPDATE_approve"));
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("title")), is(
                "implicit grant(scope:UPDATE)"));
    }

    /**
     * <ul>
     * <li>Check response from resource server when using the implicit grant(DELETE).</li>
     * </ul>
     */
    @Test
    public void testOTH20102004() {

        // Menu operation
        webDriverOperations.click(id("oth20102004"));
        // Approve resource access
        webDriverOperations.click(id("scope.DELETE_approve"));
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("title")), is(
                "implicit grant(scope:DELETE)"));
    }

    /**
     * <ul>
     * <li>Check response from resource server when using the implicit grant(GET).<br/>
     * In this test, request to resource server will be sent twice.</li>
     * </ul>
     */
    @Test
    public void testOTH20102005() {

        // Menu operation
        webDriverOperations.click(id("oth20102001"));
        authServerLogin();
        // Approve resource access
        webDriverOperations.click(id("scope.READ_approve"));
        webDriverOperations.click(id("authorize"));

        // Approve resource access
        webDriverOperations.click(id("springTestTop"));
        webDriverOperations.click(id("oth2Link"));

        webDriverOperations.click(id("oth20102001"));

        assertThat(webDriverOperations.getText(id("title")), is(
                "implicit grant(scope:READ)"));
    }

    /**
     * <ul>
     * <li>Checking the log when the scope(GET) is denied.<br/>
     * In this test, the implicit grant is used.</li>
     * </ul>
     */
    @Test
    public void testOTH20102006() {

        // quit browser
        quitCurrentWebDriver();
        setUpDefaultWebDriver();
        setUp();

        // Menu operation
        webDriverOperations.click(id("oth20102001"));
        authServerLogin();
        // Approve resource access
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "accessDeniedPage"));
        assertThat(webDriverOperations.getText(xpath(
                "//div[@class='alert alert-danger']/ul[1]/li[1]")), is(
                        "[e.sf.oth2.9002] Access Denied error!"));
        assertThat(webDriverOperations.getText(xpath(
                "//div[@class='alert alert-danger']/ul[2]/li[1]")), is(
                        "User denied access"));
        dbLogAssertOperations.assertContainsByRegexMessage(
                "jp.co.ntt.fw.spring.functionaltest.app.oth2.OAuth2ErrorController",
                "error=access_denied, error_description=User denied access");

    }

    /**
     * <ul>
     * <li>Checking the log when the scope(POST) is denied.<br/>
     * In this test, the implicit grant is used.</li>
     * </ul>
     */
    @Test
    public void testOTH20102007() {

        // Menu operation
        webDriverOperations.click(id("oth20102002"));
        // Approve resource access
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "accessDeniedPage"));
        assertThat(webDriverOperations.getText(xpath(
                "//div[@class='alert alert-danger']/ul[1]/li[1]")), is(
                        "[e.sf.oth2.9002] Access Denied error!"));
        assertThat(webDriverOperations.getText(xpath(
                "//div[@class='alert alert-danger']/ul[2]/li[1]")), is(
                        "User denied access"));
        dbLogAssertOperations.assertContainsByRegexMessage(
                "jp.co.ntt.fw.spring.functionaltest.app.oth2.OAuth2ErrorController",
                "error=access_denied, error_description=User denied access");
    }

    /**
     * <ul>
     * <li>Checking the log when the scope(PUT) is denied.<br/>
     * In this test, the implicit grant is used.</li>
     * </ul>
     */
    @Test
    public void testOTH20102008() {

        // Menu operation
        webDriverOperations.click(id("oth20102003"));
        // Approve resource access
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "accessDeniedPage"));
        assertThat(webDriverOperations.getText(xpath(
                "//div[@class='alert alert-danger']/ul[1]/li[1]")), is(
                        "[e.sf.oth2.9002] Access Denied error!"));
        assertThat(webDriverOperations.getText(xpath(
                "//div[@class='alert alert-danger']/ul[2]/li[1]")), is(
                        "User denied access"));
        dbLogAssertOperations.assertContainsByRegexMessage(
                "jp.co.ntt.fw.spring.functionaltest.app.oth2.OAuth2ErrorController",
                "error=access_denied, error_description=User denied access");

    }

    /**
     * <ul>
     * <li>Checking the log when the scope(DELETE) is denied.<br/>
     * In this test, the implicit grant is used.</li>
     * </ul>
     */
    @Test
    public void testOTH20102009() {

        // Menu operation
        webDriverOperations.click(id("oth20102004"));
        // Approve resource access
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "accessDeniedPage"));
        assertThat(webDriverOperations.getText(xpath(
                "//div[@class='alert alert-danger']/ul[1]/li[1]")), is(
                        "[e.sf.oth2.9002] Access Denied error!"));
        assertThat(webDriverOperations.getText(xpath(
                "//div[@class='alert alert-danger']/ul[2]/li[1]")), is(
                        "User denied access"));
        dbLogAssertOperations.assertContainsByRegexMessage(
                "jp.co.ntt.fw.spring.functionaltest.app.oth2.OAuth2ErrorController",
                "error=access_denied, error_description=User denied access");

    }

    /**
     * <ul>
     * <li>Checking the log when the scope(DELETE) is denied.<br/>
     * In this test, the implicit grant is used.</li>
     * </ul>
     */
    @Test
    public void testOTH20102010() {

        // Menu operation
        webDriverOperations.click(id("oth20102010"));

        authServerLogin();

        assertThat(webDriverOperations.getTitle(), is("System Error!"));
        assertThat(webDriverOperations.getText(xpath(
                "//div[@class='alert alert-danger']/ul[1]/li[1]")), is(
                        "[e.sf.oth2.9001] System error occurred!"));
        assertThat(webDriverOperations.getText(xpath(
                "//div[@class='alert alert-danger']/ul[2]/li[1]")), is(
                        "Invalid scope: DELETE"));
        dbLogAssertOperations.assertContainsByRegexMessage(
                "jp.co.ntt.fw.spring.functionaltest.app.oth2.OAuth2ErrorController",
                "error=invalid_scope, error_description=Invalid scope: DELETE");
    }

    /**
     * <ul>
     * <li>Check response from resource server when using the client credentials grant(GET).</li>
     * </ul>
     */
    @Test
    public void testOTH20103001() {

        // Menu operation
        webDriverOperations.click(id("oth20103001"));

        authServerLogin();

        assertThat(webDriverOperations.getText(id("title")), is(
                TITLE_GET_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("token")), not(""));
    }

    /**
     * <ul>
     * <li>Check response from resource server when using the client credentials grant(POST).</li>
     * </ul>
     */
    @Test
    public void testOTH20103002() {

        // Menu operation
        webDriverOperations.click(id("oth20103002"));

        authServerLogin();

        assertThat(webDriverOperations.getText(id("title")), is(
                TITLE_POST_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("token")), not(""));
        assertThat(webDriverOperations.getText(id("name")), is("testClient"));
    }

    /**
     * <ul>
     * <li>Check response from resource server when using the client credentials grant(PUT).</li>
     * </ul>
     */
    @Test
    public void testOTH20103003() {

        // Menu operation
        webDriverOperations.click(id("oth20103003"));

        authServerLogin();

        assertThat(webDriverOperations.getText(id("title")), is(
                TITLE_PUT_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("token")), not(""));
    }

    /**
     * <ul>
     * <li>Check response from resource server when using the client credentials grant(DELETE).</li>
     * </ul>
     */
    @Test
    public void testOTH20103004() {

        // Menu operation
        webDriverOperations.click(id("oth20103004"));

        authServerLogin();

        assertThat(webDriverOperations.getText(id("title")), is(
                TITLE_DELETE_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("token")), not(""));
    }

    /**
     * <ul>
     * <li>Check response from resource server when using the client credentials grant(DELETE:not registered in client).</li>
     * </ul>
     */
    @Test
    public void testOTH20103005() {

        // Menu operation
        webDriverOperations.click(id("oth20103005"));
        authServerLogin();

        assertThat(webDriverOperations.getTitle(), is("System Error!"));
        assertThat(webDriverOperations.getText(xpath(
                "//div[@class='alert alert-danger']/ul[1]/li[1]")), is(
                        "[e.sf.oth2.9001] System error occurred!"));
        assertThat(webDriverOperations.getText(xpath(
                "//div[@class='alert alert-danger']/ul[2]/li[1]")), is(
                        "Access token denied."));
        // Check error log
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                "org.terasoluna.gfw.common.exception.ExceptionLogger",
                "Access token denied",
                "org\\.springframework\\.security\\.oauth2\\.client\\.resource\\.OAuth2AccessDeniedException");
        dbLogAssertOperations.assertContainsByRegexMessage(
                "jp.co.ntt.fw.spring.functionaltest.app.oth2.OAuth2ExceptionHandler",
                "error=access_denied, error_description=Access token denied.");
    }

    /**
     * <ul>
     * <li>Check response from resource server when using the resource owner password password grant(GET).</li>
     * </ul>
     */
    @Test
    public void testOTH20104001() {

        // Menu operation
        webDriverOperations.click(id("oth20104001"));

        // Input : Resource Owner Credential
        inputResourceOwnerCredential();

        // Resource Owner Credential Grant Menu operation
        webDriverOperations.click(id("oth20104001"));

        assertThat(webDriverOperations.getText(id("title")), is(
                TITLE_GET_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("token")), not(""));
    }

    /**
     * <ul>
     * <li>Check response from resource server when using the resource owner password password grant(POST).</li>
     * </ul>
     */
    @Test
    public void testOTH20104002() {

        // Menu operation
        webDriverOperations.click(id("oth20104002"));

        // Input : Resource Owner Credential
        inputResourceOwnerCredential();

        // Resource Owner Credential Grant Menu operation
        webDriverOperations.click(id("oth20104002"));

        assertThat(webDriverOperations.getText(id("title")), is(
                TITLE_POST_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("token")), not(""));
    }

    /**
     * <ul>
     * <li>Check response from resource server when using the resource owner password password grant(PUT).</li>
     * </ul>
     */
    @Test
    public void testOTH20104003() {

        // Menu operation
        webDriverOperations.click(id("oth20104003"));

        // Input : Resource Owner Credential
        inputResourceOwnerCredential();

        // Resource Owner Credential Grant Menu operation
        webDriverOperations.click(id("oth20104003"));

        assertThat(webDriverOperations.getText(id("title")), is(
                TITLE_PUT_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("token")), not(""));
    }

    /**
     * <ul>
     * <li>Check response from resource server when using the resource owner password password grant(DELETE).</li>
     * </ul>
     */
    @Test
    public void testOTH20104004() {

        // Menu operation
        webDriverOperations.click(id("oth20104004"));

        // Input : Resource Owner Credential
        inputResourceOwnerCredential();

        // Resource Owner Credential Grant Menu operation
        webDriverOperations.click(id("oth20104004"));

        assertThat(webDriverOperations.getText(id("title")), is(
                TITLE_DELETE_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("token")), not(""));
    }

    /**
     * <ul>
     * <li>Check response from resource server when using the resource owner password password grant(DELETE).</li>
     * </ul>
     */
    @Test
    public void testOTH20104005() {

        // Menu operation
        webDriverOperations.click(id("oth20104005"));

        // Input : Resource Owner Credential
        inputResourceOwnerCredential();

        // Resource Owner Credential Grant Menu operation
        webDriverOperations.click(id("oth20104005"));

        assertThat(webDriverOperations.getTitle(), is("System Error!"));
        assertThat(webDriverOperations.getText(xpath(
                "//div[@class='alert alert-danger']/ul[1]/li[1]")), is(
                        "[e.sf.oth2.9001] System error occurred!"));
        assertThat(webDriverOperations.getText(xpath(
                "//div[@class='alert alert-danger']/ul[2]/li[1]")), is(
                        "Access token denied."));
        // Check error log
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                "org.terasoluna.gfw.common.exception.ExceptionLogger",
                "Access token denied",
                "org\\.springframework\\.security\\.oauth2\\.client\\.resource\\.OAuth2AccessDeniedException");
        dbLogAssertOperations.assertContainsByRegexMessage(
                "jp.co.ntt.fw.spring.functionaltest.app.oth2.OAuth2ResourceOwnerPasswordCredentialsGrantExceptionHandler",
                "error=access_denied, error_description=Access token denied.");
    }

    /**
     * <ul>
     * <li>Check token value when using the authorization code and the token is revoked.</li>
     * </ul>
     */
    @Test
    public void testOTH20701001() {

        // Menu operation
        webDriverOperations.click(id("oth20101001"));
        authServerLogin();
        // Approve resource access
        webDriverOperations.click(id("scope.READ_approve"));
        webDriverOperations.click(id("scope.DELETE_approve"));
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("title")), is(
                TITLE_GET_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("token")), not(""));

        String token_before = webDriverOperations.getText(id("token"));

        webDriverOperations.click(id("springTestTop"));
        webDriverOperations.click(id("oth2Link"));
        // Menu operation
        webDriverOperations.click(id("oth20701001"));

        assertThat(webDriverOperations.getText(id("result")), is("success"));

        webDriverOperations.click(id("springTestTop"));
        webDriverOperations.click(id("oth2Link"));

        // Menu operation
        webDriverOperations.click(id("oth20101001"));
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

    /**
     * <ul>
     * <li>Check token value when using the implicit and the token is revoked.</li>
     * </ul>
     */
    @Test
    public void testOTH20701003() {

        // Menu operation
        webDriverOperations.click(id("oth20102001"));
        authServerLogin();
        // Approve resource access
        webDriverOperations.click(id("scope.READ_approve"));
        webDriverOperations.click(id("authorize"));

        // Wait until desplayed
        webDriverOperations.waitForDisplayed(ExpectedConditions
                .textToBePresentInElement(webDriverOperations.getWebDriver()
                        .findElement(id("message")),
                        "{\"testId\":\"123\",\"method\":\"GET\",\"result\":\"Success\",\"businessId\":null,\"companyId\":null,\"principalString\":\"testClient\"}"));

        assertThat(webDriverOperations.getText(id("message")), is(
                "{\"testId\":\"123\",\"method\":\"GET\",\"result\":\"Success\",\"businessId\":null,\"companyId\":null,\"principalString\":\"testClient\"}"));

        String token_before = webDriverOperations.getText(id("token"));

        webDriverOperations.click(id("springTestTop"));
        webDriverOperations.click(id("oth2Link"));

        webDriverOperations.click(id("oth20701003"));
        webDriverOperations.getTitle();
        Long tokenSize = Long.parseLong(String.valueOf(webDriverOperations
                .getJavascriptExecutor().executeScript(
                        "return oauth2Func.getTokens(\"todo\").length")));
        assertThat(tokenSize, is(Long.valueOf(0)));

        webDriverOperations.click(id("springTestTop"));
        webDriverOperations.click(id("oth2Link"));

        // Menu operation
        webDriverOperations.click(id("oth20102001"));

        // Wait until desplayed
        webDriverOperations.waitForDisplayed(ExpectedConditions
                .textToBePresentInElement(webDriverOperations.getWebDriver()
                        .findElement(id("message")),
                        "{\"testId\":\"123\",\"method\":\"GET\",\"result\":\"Success\",\"businessId\":null,\"companyId\":null,\"principalString\":\"testClient\"}"));

        assertThat(webDriverOperations.getText(id("message")), is(
                "{\"testId\":\"123\",\"method\":\"GET\",\"result\":\"Success\",\"businessId\":null,\"companyId\":null,\"principalString\":\"testClient\"}"));

        String token_after = webDriverOperations.getText(id("token"));

        assertThat(token_before, is(token_after));
    }

    /**
     * <ul>
     * <li>Check token value when using the authorization code and only the token is revoked.</li>
     * </ul>
     */
    @Test
    public void testOTH20701004() {

        // Menu operation
        webDriverOperations.click(id("oth20101001"));
        authServerLogin();
        // Approve resource access
        webDriverOperations.click(id("scope.READ_approve"));
        webDriverOperations.click(id("scope.CREATE_approve"));
        webDriverOperations.click(id("scope.UPDATE_approve"));
        webDriverOperations.click(id("scope.DELETE_approve"));
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("title")), is(
                TITLE_GET_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("token")), not(""));

        String token_before = webDriverOperations.getText(id("token"));

        webDriverOperations.click(id("springTestTop"));
        webDriverOperations.click(id("oth2Link"));
        // Menu operation
        webDriverOperations.click(id("oth20701004"));

        assertThat(webDriverOperations.getText(id("result")), is("success"));

        webDriverOperations.click(id("springTestTop"));
        webDriverOperations.click(id("oth2Link"));

        // Menu operation
        webDriverOperations.click(id("oth20101001"));

        assertThat(webDriverOperations.getText(id("title")), is(
                TITLE_GET_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("token")), not(""));

        String token_after = webDriverOperations.getText(id("token"));

        assertThat(token_before, not(token_after));
    }

    /**
     * <ul>
     * <li>Check response from resource server when using the authorization code grant(GET).<br/>
     * Scope parameter contains "READ", "DELETE", "CREATE, "UPDATE".</li>
     * </ul>
     */
    @Test
    public void testOTH20501001() {

        // Menu operation
        webDriverOperations.click(id("oth20101001"));
        authServerLogin();
        // Approve resource access
        webDriverOperations.click(id("scope.READ_approve"));
        webDriverOperations.click(id("scope.CREATE_approve"));
        webDriverOperations.click(id("scope.UPDATE_approve"));
        webDriverOperations.click(id("scope.DELETE_approve"));
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("title")), is(
                TITLE_GET_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("token")), not(""));
    }

    /**
     * <ul>
     * <li>Check response from resource server when using the authorization code grant(GET).<br/>
     * Scope parameter contains "READ", "DELETE".</li>
     * </ul>
     */
    @Test
    public void testOTH20502001() {

        // Menu operation
        webDriverOperations.click(id("oth20502001"));
        authServerLogin();
        // Approve resource access
        webDriverOperations.click(id("scope.READ_approve"));
        webDriverOperations.click(id("scope.DELETE_approve"));
        assertThat(webDriverOperations.exists(id("scope.UPDATE_approve")), is(
                false));
        assertThat(webDriverOperations.exists(id("scope.CREATE_approve")), is(
                false));

        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("title")), is(
                TITLE_GET_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("token")), not(""));
    }

    /**
     * <ul>
     * <li>Checking the log when using the authorization code grant(GET).<br/>
     * ClientSecret parameter is the illegal value.</li>
     * </ul>
     */
    @Test
    public void testOTH21101002() {

        // Menu operation
        webDriverOperations.click(id("oth21101002"));
        authServerLogin();
        // Approve resource access
        webDriverOperations.click(id("scope.READ_approve"));
        webDriverOperations.click(id("authorize"));

        String messagePattern = "Error requesting access token.";
        String stackTracePattern = "Caused by: org\\.springframework\\.web\\.client\\.HttpClientErrorException: 401 .*";
        if (webDriverOperations.getApServerName() == ApServerName.WEBLOGIC) {
            messagePattern = "Access token denied.";
            stackTracePattern = "Caused by: org\\.springframework\\.security\\.oauth2\\.common\\.exceptions\\.OAuth2Exception: Bad credentials";
        }

        assertThat(webDriverOperations.getTitle(), is("System Error!"));
        assertThat(webDriverOperations.getText(xpath(
                "//div[@class='alert alert-danger']/ul[1]/li[1]")), is(
                        "[e.sf.oth2.9001] System error occurred!"));
        assertThat(webDriverOperations.getText(xpath(
                "//div[@class='alert alert-danger']/ul[2]/li[1]")), is(
                        messagePattern));
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                "org.terasoluna.gfw.common.exception.ExceptionLogger",
                messagePattern,
                "org\\.springframework\\.security\\.oauth2\\.client\\.resource\\.OAuth2AccessDeniedException");
        dbLogAssertOperations.assertContainsByRegexStackTrace(
                stackTracePattern);
    }

    /**
     * <ul>
     * <li>Checking the log when using the authorization code grant(GET).<br/>
     * Resource Owner Password is the illegal value.</li>
     * </ul>
     */
    @Test
    public void testOTH21101003() {

        // quit browser
        quitCurrentWebDriver();
        setUpDefaultWebDriver();
        setUp();

        // Menu operation
        webDriverOperations.click(id("oth20101001"));

        webDriverOperations.exists(id("loginWith"));

        // Resource Owner login
        webDriverOperations.overrideText(id("username"), "demo");
        webDriverOperations.overrideText(id("password"), "invalid_password");
        webDriverOperations.click(id("send"));

        assertTrue(webDriverOperations.exists(id("loginWith")));
    }

    /**
     * <ul>
     * <li>Checking the log when using the authorization code grant(GET).<br/>
     * resource id parameter is the illegal value.</li>
     * </ul>
     */
    @Test
    public void testOTH21101004() {

        // Menu operation
        webDriverOperations.click(id("oth21101004"));
        authServerLogin();
        // Approve resource access
        webDriverOperations.click(id("scope.READ_approve"));
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getTitle(), is("System Error!"));
        assertThat(webDriverOperations.getText(xpath(
                "//div[@class='alert alert-danger']/ul[1]/li[1]")), is(
                        "[e.sf.oth2.9001] System error occurred!"));
        assertThat(webDriverOperations.getText(xpath(
                "//div[@class='alert alert-danger']/ul[2]/li[1]")), is(
                        "Invalid token does not contain resource id (todoResource)"));
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                "org.terasoluna.gfw.common.exception.ExceptionLogger",
                "Invalid token does not contain resource id \\(todoResource\\)",
                "org\\.springframework\\.security\\.oauth2\\.client\\.resource\\.OAuth2AccessDeniedException");
    }

    /**
     * <ul>
     * <li>Check response from resource server when using the resource owner password password grant(GET).</li>
     * </ul>
     */
    @Test
    public void testOTH21101008() {

        // Menu operation
        webDriverOperations.click(id("oth20104001"));

        // Input : Resource Owner Credential(illegal username)
        webDriverOperations.overrideText(id("username"), "illegal_id");
        webDriverOperations.overrideText(id("password"), "demo");
        webDriverOperations.click(id("send"));

        // Resource Owner Credential Grant Menu operation
        webDriverOperations.click(id("oth20104001"));

        assertThat(webDriverOperations.getText(id("screenTitle")), is(
                "accessDeniedPage"));
        assertThat(webDriverOperations.getText(xpath(
                "//div[@class='alert alert-danger']/ul[1]/li[1]")), is(
                        "[e.sf.oth2.9002] Access Denied error!"));
        assertThat(webDriverOperations.getText(xpath(
                "//div[@class='alert alert-danger']/ul[2]/li[1]")), is(
                        "Access token denied."));
        dbLogAssertOperations.waitForAssertion();
        dbLogAssertOperations.assertContainsByRegexExceptionMessage(null,
                "org.terasoluna.gfw.common.exception.ExceptionLogger",
                "Access token denied.",
                "org\\.springframework\\.security\\.oauth2\\.client\\.resource\\.OAuth2AccessDeniedException");
        dbLogAssertOperations.assertContainsByRegexStackTrace(
                "Caused by: org\\.springframework\\.security\\.oauth2\\.common\\.exceptions\\.InvalidGrantException: Bad credentials");
        dbLogAssertOperations.assertContainsByRegexMessage(
                "jp.co.ntt.fw.spring.functionaltest.app.oth2.OAuth2ResourceOwnerPasswordCredentialsGrantExceptionHandler",
                "error=access_denied, error_description=Access token denied.");
    }

    @Override
    protected String getPackageRootUrl() {
        return serverUrlOth2 + "/" + contextName + "/oth2/";
    }
}
