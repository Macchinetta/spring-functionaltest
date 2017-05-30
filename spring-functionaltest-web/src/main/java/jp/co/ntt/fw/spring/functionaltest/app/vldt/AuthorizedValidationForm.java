/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

public class AuthorizedValidationForm implements Serializable {

    private static final long serialVersionUID = 1L;

    public static interface Anonymous {
    };

    @NotNull(groups = Anonymous.class)
    private String deliveryAddress;

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
