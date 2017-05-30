/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import java.io.Serializable;

import javax.validation.constraints.DecimalMax;

public class SimpleValidationByInclusiveForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @DecimalMax(value = "100")
    private String priceDefault;

    @DecimalMax(value = "100", inclusive = false)
    private String priceFalse;

    public String getPriceDefault() {
        return priceDefault;
    }

    public void setPriceDefault(String priceDefault) {
        this.priceDefault = priceDefault;
    }

    public String getPriceFalse() {
        return priceFalse;
    }

    public void setPriceFalse(String priceFalse) {
        this.priceFalse = priceFalse;
    }

}
