/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "t_order_item_jpa")
public class JPAOrderItem {
    /*
     * CREATE TABLE t_order_item_jpa ( order_id INTEGER, item_num INTEGER, item_code CHAR(10), quantity INTEGER, logical_delete
     * boolean, CONSTRAINT t_order_item_jpa_pk PRIMARY KEY(item_num), CONSTRAINT t_order_item_jpa_fk1 FOREIGN KEY(order_id)
     * REFERENCES t_order_jpa(id), CONSTRAINT t_order_item_jpa_fk2 FOREIGN KEY(item_code) REFERENCES m_item_jpa(code) );
     */
    @SequenceGenerator(name = "GEN_ORDER_ITEM_ID", sequenceName = "s_order_item_jpa", allocationSize = 1, initialValue = 7)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_ORDER_ITEM_ID")
    @Id
    @Column(name = "item_num")
    private Integer itemNumber;

    @Column(name = "order_id")
    private Integer orderId;

    @OneToOne(targetEntity = JPAItem.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "item_code")
    JPAItem orderItem;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "logical_delete")
    private Boolean logicalDelete;

    public Boolean getLogicalDelete() {
        return logicalDelete;
    }

    public void setLogicalDelete(Boolean logicalDelete) {
        this.logicalDelete = logicalDelete;
    }

    public Integer getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(Integer itemNumber) {
        this.itemNumber = itemNumber;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public JPAItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(JPAItem orderItem) {
        this.orderItem = orderItem;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
