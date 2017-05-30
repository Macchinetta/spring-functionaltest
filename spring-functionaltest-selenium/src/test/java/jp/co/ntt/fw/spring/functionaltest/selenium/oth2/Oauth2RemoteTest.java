/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.selenium.oth2;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.id;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

public class Oauth2RemoteTest extends FunctionTestSupport {

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
     * Accessing resource server is using remote token service.</li>
     */
    @Test
    public void testOTH20301001() {

        // Menu operation
        webDriverOperations.click(id("oth20301001"));
        authServerLogin();
        // Approve resource access
        webDriverOperations.click(id("scope.READ_approve"));
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("title")),
                is(TITLE_GET_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("clientAdditionalValue")),
                is("client_additional_value"));
        assertThat(webDriverOperations.getText(id("userAdditionalValue")),
                is("user_additional_value"));
        assertThat(webDriverOperations.getText(id("token")), not(""));
    }

    /**
     * <ul>
     * <li>Check response from resource server when using the authorization code grant(POST).<br/>
     * Accessing resource server is using remote token service.</li>
     */
    @Test
    public void testOTH20301002() {

        // Menu operation
        webDriverOperations.click(id("oth20301002"));
        authServerLogin();
        // Approve resource access
        webDriverOperations.click(id("scope.CREATE_approve"));
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("title")),
                is(TITLE_POST_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("clientAdditionalValue")),
                is("client_additional_value"));
        assertThat(webDriverOperations.getText(id("userAdditionalValue")),
                is("user_additional_value"));
        assertThat(webDriverOperations.getText(id("token")), not(""));
    }

    /**
     * <ul>
     * <li>Check response from resource server when using the authorization code grant(PUT).<br/>
     * Accessing resource server is using remote token service.</li>
     */
    @Test
    public void testOTH20301003() {

        // Menu operation
        webDriverOperations.click(id("oth20301003"));
        authServerLogin();
        // Approve resource access
        webDriverOperations.click(id("scope.UPDATE_approve"));
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("title")),
                is(TITLE_PUT_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("token")), not(""));
    }

    /**
     * <ul>
     * <li>Check response from resource server when using the authorization code grant(DELETE).<br/>
     * Accessing resource server is using remote token service.</li>
     * </ul>
     */
    @Test
    public void testOTH20301004() {

        // Menu operation
        webDriverOperations.click(id("oth20301004"));
        authServerLogin();
        // Approve resource access
        webDriverOperations.click(id("scope.DELETE_approve"));
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("title")),
                is(TITLE_DELETE_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("token")), not(""));
    }

    /**
     * <ul>
     * <li>Check response from resource server when using the authorization code grant(GET).<br/>
     * Re-access within the expiration date of the access token.</li>
     * </ul>
     */

    /**
     * <ul>
     * <li>Check response from resource server when using the authorization code grant(GET).<br/>
     * Accessing resource server is using remote token service.</li>
     */
    @Test
    public void testOTH21001002() {

        // Menu operation
        webDriverOperations.click(id("oth21001002"));
        authServerLogin();
        // Approve resource access
        webDriverOperations.click(id("scope.READ_approve"));
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("title")),
                is(TITLE_GET_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("token")), not(""));
        assertThat(webDriverOperations.getText(id("name")), is("demo"));
    }

    @Test
    public void testOTH20601001() {

        // Menu operation
        webDriverOperations.click(id("oth20301001"));
        authServerLogin();
        // Approve resource access
        webDriverOperations.click(id("scope.READ_approve"));
        webDriverOperations.click(id("scope.CREATE_approve"));
        webDriverOperations.click(id("scope.UPDATE_approve"));
        webDriverOperations.click(id("scope.DELETE_approve"));
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("title")),
                is(TITLE_GET_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("clientAdditionalValue")),
                is("client_additional_value"));
        assertThat(webDriverOperations.getText(id("userAdditionalValue")),
                is("user_additional_value"));
        String token_before = webDriverOperations.getText(id("token"));

        webDriverOperations.click(id("springTestTop"));

        webDriverOperations.click(id("oth2Link"));

        // Menu operation
        webDriverOperations.click(id("oth20301001"));

        assertThat(webDriverOperations.getText(id("title")),
                is(TITLE_GET_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("clientAdditionalValue")),
                is("client_additional_value"));
        assertThat(webDriverOperations.getText(id("userAdditionalValue")),
                is("user_additional_value"));
        String token_after = webDriverOperations.getText(id("token"));

        assertThat(token_before, is(token_after));
    }

    /**
     * <ul>
     * <li>Check response from resource server when using the authorization code grant(GET).<br/>
     * Re-access after access token's expiration date.</li>
     * </ul>
     */
    @Test
    public void testOTH20601002() throws InterruptedException {

        // Menu operation
        webDriverOperations.click(id("oth20301001"));
        authServerLogin();
        // Approve resource access
        webDriverOperations.click(id("scope.READ_approve"));
        webDriverOperations.click(id("scope.CREATE_approve"));
        webDriverOperations.click(id("scope.UPDATE_approve"));
        webDriverOperations.click(id("scope.DELETE_approve"));
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("title")),
                is(TITLE_GET_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("clientAdditionalValue")),
                is("client_additional_value"));
        assertThat(webDriverOperations.getText(id("userAdditionalValue")),
                is("user_additional_value"));
        String token_before = webDriverOperations.getText(id("token"));

        // sleep until access token will get expired
        Thread.sleep(10000);

        webDriverOperations.click(id("springTestTop"));

        webDriverOperations.click(id("oth2Link"));

        // Menu operation
        webDriverOperations.click(id("oth20301001"));

        assertThat(webDriverOperations.getText(id("title")),
                is(TITLE_GET_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("clientAdditionalValue")),
                is("client_additional_value"));
        assertThat(webDriverOperations.getText(id("userAdditionalValue")),
                is("user_additional_value"));
        String token_after = webDriverOperations.getText(id("token"));

        assertThat(token_before, not(token_after));

    }

    /**
     * <ul>
     * <li>Check response from resource server when using the authorization code grant(GET).<br/>
     * Re-access after refresh token's expiration date.</li>
     * </ul>
     */
    @Test
    public void testOTH20601003() throws InterruptedException {

        // Menu operation
        webDriverOperations.click(id("oth20301001"));
        authServerLogin();
        // Approve resource access
        webDriverOperations.click(id("scope.READ_approve"));
        webDriverOperations.click(id("scope.CREATE_approve"));
        webDriverOperations.click(id("scope.UPDATE_approve"));
        webDriverOperations.click(id("scope.DELETE_approve"));
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("title")),
                is(TITLE_GET_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("clientAdditionalValue")),
                is("client_additional_value"));
        assertThat(webDriverOperations.getText(id("userAdditionalValue")),
                is("user_additional_value"));
        String token_before = webDriverOperations.getText(id("token"));

        // sleep until refresh token will get expired
        Thread.sleep(20000);

        webDriverOperations.click(id("springTestTop"));

        webDriverOperations.click(id("oth2Link"));

        // Menu operation
        webDriverOperations.click(id("oth20301001"));

        assertThat(webDriverOperations.getText(id("title")),
                is(TITLE_GET_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("clientAdditionalValue")),
                is("client_additional_value"));
        assertThat(webDriverOperations.getText(id("userAdditionalValue")),
                is("user_additional_value"));
        String token_after = webDriverOperations.getText(id("token"));

        assertThat(token_before, not(token_after));

    }

    /**
     * <ul>
     * <li>Check response from resource server when using the authorization code grant(GET).<br/>
     * Re-access after approval's expiration date.</li>
     * </ul>
     */
    @Test
    public void testOTH20601004() throws InterruptedException {

        // Menu operation
        webDriverOperations.click(id("oth20301001"));
        authServerLogin();
        // Approve resource access
        webDriverOperations.click(id("scope.READ_approve"));
        webDriverOperations.click(id("scope.CREATE_approve"));
        webDriverOperations.click(id("scope.UPDATE_approve"));
        webDriverOperations.click(id("scope.DELETE_approve"));
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("title")),
                is(TITLE_GET_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("clientAdditionalValue")),
                is("client_additional_value"));
        assertThat(webDriverOperations.getText(id("userAdditionalValue")),
                is("user_additional_value"));
        String token_before = webDriverOperations.getText(id("token"));

        // sleep until approval info will get expired
        Thread.sleep(30000);

        webDriverOperations.click(id("springTestTop"));

        webDriverOperations.click(id("oth2Link"));

        // Menu operation
        webDriverOperations.click(id("oth20301001"));
        // Approve resource access
        webDriverOperations.click(id("scope.READ_approve"));
        webDriverOperations.click(id("scope.CREATE_approve"));
        webDriverOperations.click(id("scope.UPDATE_approve"));
        webDriverOperations.click(id("scope.DELETE_approve"));
        webDriverOperations.click(id("authorize"));

        assertThat(webDriverOperations.getText(id("title")),
                is(TITLE_GET_OPERATION));
        assertThat(webDriverOperations.getText(id("response")), is("Success"));
        assertThat(webDriverOperations.getText(id("clientAdditionalValue")),
                is("client_additional_value"));
        assertThat(webDriverOperations.getText(id("userAdditionalValue")),
                is("user_additional_value"));
        String token_after = webDriverOperations.getText(id("token"));

        assertThat(token_before, not(token_after));

    }

    @Override
    protected String getPackageRootUrl() {
        return serverUrlOth2 + "/" + contextName + "/oth2/";
    }
}
