/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.excn;

import java.io.Serializable;

public class StockForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String itemCode;

    private String itemName;

    private int quantity;

    private long version;

    private int purchasingQuantity;

    private long sleepMillis;

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public int getPurchasingQuantity() {
        return purchasingQuantity;
    }

    public void setPurchasingQuantity(int purchasingQuantity) {
        this.purchasingQuantity = purchasingQuantity;
    }

    public long getSleepMillis() {
        return sleepMillis;
    }

    public void setSleepMillis(long sleepMillis) {
        this.sleepMillis = sleepMillis;
    }
}
