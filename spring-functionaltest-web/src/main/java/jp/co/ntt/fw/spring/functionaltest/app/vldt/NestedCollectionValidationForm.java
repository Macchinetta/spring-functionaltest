/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import java.io.Serializable;
import java.util.LinkedList;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class NestedCollectionValidationForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Valid
    private LinkedList<AddressForm> addresses;

    public LinkedList<AddressForm> getAddresses() {
        return addresses;
    }

    public void setAddresses(LinkedList<AddressForm> addresses) {
        this.addresses = addresses;
    }
}
