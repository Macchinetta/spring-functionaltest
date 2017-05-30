/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class DefineMessageUsingValidatedValueByValidationMessagesForm implements
                                                                     Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Pattern(regexp = "[0-9]+")
    private String telNumber;

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }
}
