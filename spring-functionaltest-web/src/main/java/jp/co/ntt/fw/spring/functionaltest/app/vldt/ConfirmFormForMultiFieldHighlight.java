/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import jp.co.ntt.fw.spring.functionaltest.domain.cmmn.validation.ConfirmForMultiFieldHighlight;

@ConfirmForMultiFieldHighlight(field = "password")
public class ConfirmFormForMultiFieldHighlight implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty
    private String password;

    @NotEmpty
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
