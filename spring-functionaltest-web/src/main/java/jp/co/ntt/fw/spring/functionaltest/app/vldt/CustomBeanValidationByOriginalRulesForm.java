/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import java.io.Serializable;

import jp.co.ntt.fw.spring.functionaltest.domain.cmmn.validation.ISBN13;

public class CustomBeanValidationByOriginalRulesForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @ISBN13
    private String isbnCode;

    public String getIsbnCode() {
        return isbnCode;
    }

    public void setIsbnCode(String isbnCode) {
        this.isbnCode = isbnCode;
    }
}
