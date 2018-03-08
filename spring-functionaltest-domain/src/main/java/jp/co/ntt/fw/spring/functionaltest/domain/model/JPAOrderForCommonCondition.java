/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "t_order_jpa")
@Where(clause = "is_logical_delete = false")
public class JPAOrderForCommonCondition {

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
    @Where(clause = "logical_delete = false")
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
