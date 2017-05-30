/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.model;

import java.io.Serializable;
import java.util.List;

public class ItemMB3 implements Serializable {
    private static final long serialVersionUID = 1L;

    private String code;

    private String name;

    private int price;

    private List<CategoryMB3> categories;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<CategoryMB3> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryMB3> categories) {
        this.categories = categories;
    }
}
