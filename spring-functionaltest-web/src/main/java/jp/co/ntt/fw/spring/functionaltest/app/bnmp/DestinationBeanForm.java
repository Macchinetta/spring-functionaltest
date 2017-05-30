/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.bnmp;

import java.io.Serializable;

public class DestinationBeanForm implements Serializable {

    /**  */
    private static final long serialVersionUID = 1L;

    private String destinationFirstName;

    private String destinationLastName;

    private Integer destinationAge;

    public String getDestinationFirstName() {
        return destinationFirstName;
    }

    public void setDestinationFirstName(String destinationFirstName) {
        this.destinationFirstName = destinationFirstName;
    }

    public String getDestinationLastName() {
        return destinationLastName;
    }

    public void setDestinationLastName(String destinationLastName) {
        this.destinationLastName = destinationLastName;
    }

    public Integer getDestinationAge() {
        return destinationAge;
    }

    public void setDestinationAge(Integer destinationAge) {
        this.destinationAge = destinationAge;
    }
}
