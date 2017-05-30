/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.model;

import java.io.Serializable;

public class DeliveryType implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer deliveryTypeId;

    private String deliveryTypeName;

    public DeliveryType() {
    }

    public DeliveryType(Integer deliveryTypeId, String deliveryTypeName) {
        this.deliveryTypeId = deliveryTypeId;
        this.deliveryTypeName = deliveryTypeName;
    }

    public Integer getDeliveryTypeId() {
        return deliveryTypeId;
    }

    public void setDeliveryTypeId(Integer deliveryTypeId) {
        this.deliveryTypeId = deliveryTypeId;
    }

    public String getDeliveryTypeName() {
        return deliveryTypeName;
    }

    public void setDeliveryTypeName(String deliveryTypeName) {
        this.deliveryTypeName = deliveryTypeName;
    }

}
