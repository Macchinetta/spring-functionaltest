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
@Table(name = "t_delivery_status")
public class JPADeliveryStatus {
    /**
     * delivery_status VARCHAR(128)
     */

    @Id
    @Column(name = "delivery_status", nullable = false)
    private String deliveryStatus;

}
