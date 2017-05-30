/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa;

import java.util.List;

public class DeliveryOrderCriteria {

    private Integer deliveryNumber;

    private List<String> deliveryStatus;

    private String deliveryType;

    public Integer getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(Integer deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public List<String> getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(List<String> deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

}
