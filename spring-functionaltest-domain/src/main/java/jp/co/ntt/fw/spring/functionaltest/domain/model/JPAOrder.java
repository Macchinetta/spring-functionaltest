/*
 * Copyright(c) 2014 NTT Corporation.
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

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_order_jpa")
public class JPAOrder {
    /**
     * CREATE TABLE t_order_jpa ( id INTEGER, status_code VARCHAR(10), memo VARCHAR(100), amount INTEGER, CONSTRAINT
     * t_order_jpa_pk PRIMARY KEY(id), CONSTRAINT t_order_jpa_fk FOREIGN KEY(status_code) REFERENCES c_order_status_jpa(code) );
     */

    @SequenceGenerator(name = "GEN_ORDER_ID", sequenceName = "s_order_jpa", allocationSize = 1, initialValue = 7)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_ORDER_ID")
    @Id
    @Column(name = "id", nullable = false, length = 10)
    private int orderId;

    @Column(name = "memo", nullable = true, length = 100)
    private String orderMemo;

    @Column(name = "amount")
    private Integer orderAmount;

    @Column(name = "is_logical_delete")
    private Boolean logicalDelete;

    @OneToMany(targetEntity = JPAOrderItem.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    @OrderBy
    List<JPAOrderItem> orderItem;

    @ManyToOne(targetEntity = JPAOrderStatus.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "status_code")
    JPAOrderStatus orderStatus;

    public Boolean getLogicalDelete() {
        return logicalDelete;
    }

    public void setLogicalDelete(Boolean logicalDelete) {
        this.logicalDelete = logicalDelete;
    }

    public JPAOrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(JPAOrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderMemo() {
        return orderMemo;
    }

    public void setOrderMemo(String orderMemo) {
        this.orderMemo = orderMemo;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public List<JPAOrderItem> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(List<JPAOrderItem> orderItem) {
        this.orderItem = orderItem;
    }

}
