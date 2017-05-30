/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import jp.co.ntt.fw.spring.functionaltest.domain.cmmn.validation.Confirm;

@Confirm(field = "password")
public class ConfirmForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String password;

    @NotNull
    private String confirmPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
