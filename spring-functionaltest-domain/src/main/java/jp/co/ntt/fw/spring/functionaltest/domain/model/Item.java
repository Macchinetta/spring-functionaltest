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

import java.io.Serializable;

public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    private String itemId;

    private String itemName;

    private int price;

    private String overviewDescription;

    private String detailDescription;

    private ItemImage itemImage;

    private ItemStock itemStock;

    public Item() {

    }

    public Item(String itemId) {
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOverviewDescription() {
        return overviewDescription;
    }

    public void setOverviewDescription(String overviewDescription) {
        this.overviewDescription = overviewDescription;
    }

    public String getDetailDescription() {
        return detailDescription;
    }

    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription;
    }

    public ItemImage getItemImage() {
        return itemImage;
    }

    public void setItemImage(ItemImage itemImage) {
        this.itemImage = itemImage;
    }

    public ItemStock getItemStock() {
        return itemStock;
    }

    public void setItemStock(ItemStock itemStock) {
        this.itemStock = itemStock;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item other = (Item) obj;
        if (itemId == null) {
            if (other.itemId != null)
                return false;
        } else if (!itemId.equals(other.itemId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Item [itemId=" + itemId + ", itemName=" + itemName + ", price="
                + price + ", overviewDescription=" + overviewDescription
                + ", detailDescription=" + detailDescription + ", itemImage="
                + itemImage + ", itemStock=" + itemStock + ", super.hashCode()="
                + super.hashCode() + "]";
    }

}
