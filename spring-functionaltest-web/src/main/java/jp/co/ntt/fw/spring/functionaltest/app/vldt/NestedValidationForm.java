/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class NestedValidationForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Valid
    private AddressForm reciverAddress;

    public AddressForm getReciverAddress() {
        return reciverAddress;
    }

    public void setReciverAddress(AddressForm reciverAddress) {
        this.reciverAddress = reciverAddress;
    }
}
