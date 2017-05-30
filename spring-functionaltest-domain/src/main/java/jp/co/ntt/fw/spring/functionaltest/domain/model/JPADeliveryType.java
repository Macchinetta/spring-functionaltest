/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author btundes
 */

@Entity
@Table(name = "m_delivery_type")
public class JPADeliveryType {
    /**
     * delivery_type_id INTEGER ,delivery_type_name VARCHAR(1024) ,CONSTRAINT pk_delivery_type PRIMARY KEY (delivery_type_id) );
     */

    @Id
    @Column(name = "delivery_type_id", nullable = false)
    private Integer deliveryTypeId;

    @Column(name = "delivery_type_name", nullable = true, length = 1024)
    private String deliveryTypeName;

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
