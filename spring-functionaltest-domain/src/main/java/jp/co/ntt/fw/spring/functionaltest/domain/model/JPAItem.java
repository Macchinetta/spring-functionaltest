/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "m_item_jpa")
public class JPAItem {
    /*
     * CREATE TABLE m_item_jpa ( code CHAR(10), name VARCHAR(256), price INTEGER, CONSTRAINT m_item_jpa_pk PRIMARY KEY(code) );
     */

    @Id
    @Column(name = "code", nullable = false, length = 10)
    private String itemCode;

    @Column(name = "name", length = 256)
    private String itemName;

    @Column(name = "price")
    private Integer itemPrice;

    @Transient
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Integer itemPrice) {
        this.itemPrice = itemPrice;
    }

}
