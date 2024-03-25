/*
 * Copyright(c) 2024 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * @author btundes
 */

@Entity
@Table(name = "t_delivery_order")
public class JPADeliveryOrder {
    /**
     * delivery_no INTEGER ,delivery_type_id INTEGER ,sender_name VARCHAR(256) ,sender_address VARCHAR(1024) ,reciever_name
     * VARCHAR(256) ,reciever_address VARCHAR(1024) ,accept_datetime DATETIME ,completion_datetime DATETIME ,delivery_driver
     * VARCHAR(256) ,delivery_status VARCHAR(128) ,CONSTRAINT pk_delivery_order PRIMARY KEY (delivery_no) ,CONSTRAINT
     * fk_delivery_type_id FOREIGN KEY (delivery_type_id) REFERENCES m_delivery_type(delivery_type_id) ,CONSTRAINT
     * fk_delivery_status FOREIGN KEY (delivery_status) REFERENCES t_delivery_status(delivery_status)
     */

    @SequenceGenerator(name = "GEN_DEL_NO", sequenceName = "s_jpa_delivery_no", allocationSize = 1, initialValue = 3)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_DEL_NO")
    @Id
    @Column(name = "delivery_no", nullable = false, length = 10)
    private int deliverNumber;

    @Column(name = "delivery_type_id")
    private Integer deliveryType;

    @Column(name = "sender_name", length = 1024)
    private String senderName;

    @Column(name = "sender_address", length = 1024)
    private String senderAddress;

    @Column(name = "reciever_name", length = 1024)
    private String receiverName;

    @Column(name = "reciever_address", length = 1024)
    private String receiverAddress;

    @Column(name = "accept_datetime")
    private Date acceptDateTime;

    @Column(name = "completion_datetime")
    private Date completionDateTime;

    @Column(name = "delivery_driver", length = 1024)
    private String deliveryDriver;

    @Column(name = "delivery_status")
    private String deliveryStatus;

    @ManyToOne(targetEntity = JPADeliveryStatus.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "delivery_status", insertable = false, updatable = false)
    JPADeliveryStatus jpaDeliveryStatus;

    @ManyToOne(targetEntity = JPADeliveryType.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "delivery_type_id", insertable = false, updatable = false)
    JPADeliveryType jpaDeliveryType;

    public int getDeliverNumber() {
        return deliverNumber;
    }

    public void setDeliverNumber(int deliverNumber) {
        this.deliverNumber = deliverNumber;
    }

    public Integer getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Integer deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public Date getAcceptDateTime() {
        return acceptDateTime;
    }

    public void setAcceptDateTime(Date acceptDateTime) {
        this.acceptDateTime = acceptDateTime;
    }

    public Date getCompletionDateTime() {
        return completionDateTime;
    }

    public void setCompletionDateTime(Date completionDateTime) {
        this.completionDateTime = completionDateTime;
    }

    public String getDeliveryDriver() {
        return deliveryDriver;
    }

    public void setDeliveryDriver(String deliveryDriver) {
        this.deliveryDriver = deliveryDriver;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public JPADeliveryStatus getJpaDeliveryStatus() {
        return jpaDeliveryStatus;
    }

    public void setJpaDeliveryStatus(JPADeliveryStatus jpaDeliveryStatus) {
        this.jpaDeliveryStatus = jpaDeliveryStatus;
    }

    public JPADeliveryType getJpaDeliveryType() {
        return jpaDeliveryType;
    }

    public void setJpaDeliveryType(JPADeliveryType jpaDeliveryType) {
        this.jpaDeliveryType = jpaDeliveryType;
    }

}
