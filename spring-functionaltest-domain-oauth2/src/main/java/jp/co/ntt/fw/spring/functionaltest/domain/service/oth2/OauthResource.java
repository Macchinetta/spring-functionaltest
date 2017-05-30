/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.oth2;

import java.io.Serializable;

public class OauthResource implements Serializable {

    private static final long serialVersionUID = 4346059288787010091L;

    private String testId;

    private String method;

    private String result;

    private String clientAdditionalValue;

    private String userAdditionalValue;

    private String principalString;

    public OauthResource() {
        setResult("Failure");
    }

    public OauthResource(String testId, String method) {
        setTestId(testId);
        setMethod(method);
        setResult("Failure");
    }

    public String getTestId() {
        return testId;
    }

    public final void setTestId(String testId) {
        this.testId = testId;
    }

    public String getMethod() {
        return method;
    }

    public final void setMethod(String method) {
        this.method = method;
    }

    public String getResult() {
        return result;
    }

    public final void setResult(String result) {
        this.result = result;
    }

    public void setResultSuccess() {
        setResult("Success");
    }

    public String getClientAdditionalValue() {
        return clientAdditionalValue;
    }

    public void setClientAdditionalValue(String clientAdditionalValue) {
        this.clientAdditionalValue = clientAdditionalValue;
    }

    public String getUserAdditionalValue() {
        return userAdditionalValue;
    }

    public void setUserAdditionalValue(String userAdditionalValue) {
        this.userAdditionalValue = userAdditionalValue;
    }

    public String getPrincipalString() {
        return principalString;
    }

    public void setPrincipalString(String principalString) {
        this.principalString = principalString;
    }

    @Override
    public String toString() {
        return "OauthResource [testId=" + testId + ", method=" + method
                + ", result=" + result + ", clientAdditionalValue="
                + clientAdditionalValue + ", userAdditionalValue="
                + userAdditionalValue + ", principalString=" + principalString
                + "]";
    }
}
