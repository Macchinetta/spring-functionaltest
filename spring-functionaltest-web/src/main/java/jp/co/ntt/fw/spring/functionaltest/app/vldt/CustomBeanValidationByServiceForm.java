/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import java.io.Serializable;

import jp.co.ntt.fw.spring.functionaltest.domain.cmmn.validation.UnusedUserId;

public class CustomBeanValidationByServiceForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @UnusedUserId
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
