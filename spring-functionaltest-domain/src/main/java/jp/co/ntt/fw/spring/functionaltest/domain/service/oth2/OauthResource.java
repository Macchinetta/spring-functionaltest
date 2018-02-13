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
package jp.co.ntt.fw.spring.functionaltest.domain.service.oth2;

import java.io.Serializable;

public class OauthResource implements Serializable {

    private static final long serialVersionUID = 4346059288787010091L;

    private String testId;

    private String method;

    private String result;

    private String businessId;

    private String companyId;

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

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setResultSuccess() {
        setResult("Success");
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
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
                + ", result=" + result + ", businessId=" + businessId
                + ", companyId=" + companyId + ", principalString="
                + principalString + "]";
    }
}
