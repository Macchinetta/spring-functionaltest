/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import java.io.Serializable;

import javax.validation.constraints.Size;

public class SimpleValidationByJSR303Form implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(min = 1, max = 20)
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
