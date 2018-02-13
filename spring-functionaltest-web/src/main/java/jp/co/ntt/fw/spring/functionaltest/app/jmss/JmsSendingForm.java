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
package jp.co.ntt.fw.spring.functionaltest.app.jmss;

import java.io.Serializable;

public class JmsSendingForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String jmsTodoId;

    private String testCase;

    public String getJmsTodoId() {
        return jmsTodoId;
    }

    public void setJmsTodoId(String jmsTodoId) {
        this.jmsTodoId = jmsTodoId;
    }

    public String getTestCase() {
        return testCase;
    }

    public void setTestCase(String testCase) {
        this.testCase = testCase;
    }

}
