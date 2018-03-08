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
package jp.co.ntt.fw.spring.functionaltest.selenium.djpa.pages;

import jp.co.ntt.fw.spring.functionaltest.selenium.Page;
import jp.co.ntt.fw.spring.functionaltest.selenium.WebElementOperations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderEntryPage implements Page<OrderEntryPage> {

    private WebDriver driver;

    @CacheLookup
    @FindBy(id = "items0.quantity")
    private WebElement item1Qty;

    @CacheLookup
    @FindBy(id = "items1.quantity")
    private WebElement item2Qty;

    @CacheLookup
    @FindBy(id = "items2.quantity")
    private WebElement item3Qty;

    @CacheLookup
    @FindBy(id = "place")
    private WebElement placeOrderBtn;

    @CacheLookup
    @FindBy(id = "memo")
    private WebElement orderComment;

    @CacheLookup
    @FindBy(id = "addtoOrder")
    private WebElement addToExistOrderBtn;

    @CacheLookup
    @FindBy(id = "directItem2Order")
    private WebElement addItemToOrderBtn;

    public OrderEntryPage(WebDriver driver2) {
        this.driver = driver2;
        reload();
    }

    @Override
    public OrderEntryPage reload() {

        PageFactory.initElements(driver, this);
        return this;
    }

    public void setQuantity(Integer itemNumber, String qauantity) {
        switch (itemNumber) {
        case 1:
            WebElementOperations.setValue(item1Qty, qauantity);
            break;
        case 2:
            WebElementOperations.setValue(item2Qty, qauantity);
            break;
        case 3:
            WebElementOperations.setValue(item3Qty, qauantity);
            break;
        default:
            break;
        }
    }

    public void setOrderMemo(String memo) {
        WebElementOperations.setValue(orderComment, memo);
    }

    public OrdersPage placeOrder() {
        placeOrderBtn.click();
        OrdersPage ordersPage = new OrdersPage(driver);
        return ordersPage;
    }

    public OrdersPage addToExistingOrder() {
        addToExistOrderBtn.click();
        OrdersPage ordersPage = new OrdersPage(driver);
        return ordersPage;
    }

    public OrdersPage addDirectDependentEntity() {
        addItemToOrderBtn.click();
        OrdersPage ordersPage = new OrdersPage(driver);
        return ordersPage;
    }

}
