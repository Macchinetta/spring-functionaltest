/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.jmss;

import java.io.Serializable;

public class JmsReceivingForm implements Serializable {

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
