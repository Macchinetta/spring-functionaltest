/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class SimpleValidationByHibernateValidatorForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
