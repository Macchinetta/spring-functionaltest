/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.djpa;

import java.util.List;

public class CartForm {

    private List<ItemForm> items;

    private String memo;

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<ItemForm> getItems() {
        return items;
    }

    public void setItems(List<ItemForm> items) {
        this.items = items;
    }

}
