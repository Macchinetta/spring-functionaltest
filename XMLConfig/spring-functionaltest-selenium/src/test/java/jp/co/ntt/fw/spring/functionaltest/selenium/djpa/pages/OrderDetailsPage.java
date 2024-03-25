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
package jp.co.ntt.fw.spring.functionaltest.selenium.djpa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import jp.co.ntt.fw.spring.functionaltest.selenium.Page;
import jp.co.ntt.fw.spring.functionaltest.selenium.WebElementOperations;

public class OrderDetailsPage implements Page<OrderDetailsPage> {

    public WebDriver getDriver() {
        return driver;
    }

    private WebDriver driver;

    @CacheLookup
    @FindBy(id = "itCode_1")
    private WebElement itemCode1;

    @CacheLookup
    @FindBy(id = "itCode_2")
    private WebElement itemCode2;

    @CacheLookup
    @FindBy(id = "itCode_3")
    private WebElement itemCode3;

    @CacheLookup
    @FindBy(id = "itCode_4")
    private WebElement itemCode4;

    @CacheLookup
    @FindBy(id = "itQty_1")
    private WebElement itemQty1;

    @CacheLookup
    @FindBy(id = "itQty_2")
    private WebElement itemQty2;

    @CacheLookup
    @FindBy(id = "itQty_3")
    private WebElement itemQty3;

    @CacheLookup
    @FindBy(id = "itQty_4")
    private WebElement itemQty4;

    @CacheLookup
    @FindBy(id = "orderSumId")
    private WebElement orderSumId;

    @CacheLookup
    @FindBy(id = "orderSumAmt")
    private WebElement orderSumAmt;

    @CacheLookup
    @FindBy(id = "orderSumDelFlag")
    private WebElement orderSumDelFlag;

    @CacheLookup
    @FindBy(id = "orderSumMemo")
    private WebElement orderSumMemo;

    @CacheLookup
    @FindBy(id = "orderSumStatus")
    private WebElement orderSumStatus;

    @CacheLookup
    @FindBy(id = "add")
    private WebElement addToOrderBtn;

    @CacheLookup
    @FindBy(id = "orderStatus.statusName")
    private WebElement statusNameEle;

    @CacheLookup
    @FindBy(id = "back")
    private WebElement back2OrdersBtn;

    @CacheLookup
    @FindBy(id = "updateStatus")
    private WebElement updateStatusBtn;

    @CacheLookup
    @FindBy(id = "item1")
    private WebElement itemDetail1Link;

    @CacheLookup
    @FindBy(id = "item2")
    private WebElement itemDetail2Link;

    @CacheLookup
    @FindBy(id = "item3")
    private WebElement itemDetail3Link;

    @CacheLookup
    @FindBy(id = "item4")
    private WebElement itemDetail4Link;

    @CacheLookup
    @FindBy(id = "updateByQuery")
    private WebElement updateByQueryBtn;

    @CacheLookup
    @FindBy(id = "updateByQueryErr")
    private WebElement updateByQueryErrBtn;

    @CacheLookup
    @FindBy(id = "updateByQueryClear")
    private WebElement updateByQueryClearBtn;

    @CacheLookup
    @FindBy(id = "itStat_1")
    private WebElement itStatus1;

    @CacheLookup
    @FindBy(id = "itStat_2")
    private WebElement itStatus2;

    @CacheLookup
    @FindBy(id = "itStat_3")
    private WebElement itStatus3;

    @CacheLookup
    @FindBy(id = "deleteEntityByQuery")
    private WebElement deleteEntityByQueryBtn;

    public OrderDetailsPage(WebDriver driver2) {
        this.driver = driver2;
        reload();
    }

    @Override
    public OrderDetailsPage reload() {
        PageFactory.initElements(driver, this);
        return this;
    }

    public OrderEntryPage addItemToOrder() {
        addToOrderBtn.click();
        OrderEntryPage orderEntryPage = new OrderEntryPage(driver);
        return orderEntryPage;
    }

    public OrdersPage backToOrdersPage() {
        back2OrdersBtn.click();
        OrdersPage ordersPage = new OrdersPage(driver);
        return ordersPage;
    }

    public ItemPage updateByQuery() {
        updateByQueryBtn.click();
        ItemPage itemPage = new ItemPage(driver);
        return itemPage;
    }

    public SystemErrorPage updateByQueryErrorScenario() {
        updateByQueryErrBtn.click();
        SystemErrorPage systemErrorPage = new SystemErrorPage(driver);
        return systemErrorPage;
    }

    public ItemPage updateByQueryWithClearON() {
        updateByQueryClearBtn.click();
        ItemPage itemPage = new ItemPage(driver);
        return itemPage;
    }

    public OrdersPage deleteByQueryWithClearON() {
        deleteEntityByQueryBtn.click();
        OrdersPage ordersPage = new OrdersPage(driver);
        return ordersPage;
    }

    public String isItemRevmovable(Integer itemNum) {
        String removable = "No";
        switch (itemNum) {
        case 1:
            removable = WebElementOperations.getElementTextValue(itStatus1);
            break;
        case 2:
            removable = WebElementOperations.getElementTextValue(itStatus2);
            break;
        case 3:
            removable = WebElementOperations.getElementTextValue(itStatus3);
            break;

        default:
            break;
        }

        return removable;
    }

    public void clearStatusField() {
        statusNameEle.clear();
    }

    public OrdersPage updateStatus() {
        updateStatusBtn.click();
        OrdersPage ordersPage = new OrdersPage(driver);
        return ordersPage;
    }

    public void setStatus(String statusName) {
        WebElementOperations.setValue(statusNameEle, statusName);
    }

    public String getOrderSummaryOrderId() {
        return WebElementOperations.getElementTextValue(orderSumId);
    }

    public String getOrderSummaryOrderAmount() {
        return WebElementOperations.getElementTextValue(orderSumAmt);
    }

    public String getOrderSummaryOrderDelFlag() {
        return WebElementOperations.getElementTextValue(orderSumDelFlag);
    }

    public String getOrderSummaryOrderMemo() {
        return WebElementOperations.getElementTextValue(orderSumMemo);
    }

    public String getOrderSummaryOrderStatus() {
        return WebElementOperations.getElementTextValue(orderSumStatus);
    }

    public String getItemCodeValue(Integer itemNum) {
        String value = "";
        switch (itemNum) {
        case 1:
            value = WebElementOperations.getElementTextValue(itemCode1);
            break;
        case 2:
            value = WebElementOperations.getElementTextValue(itemCode2);
            break;
        case 3:
            value = WebElementOperations.getElementTextValue(itemCode3);
            break;
        case 4:
            value = WebElementOperations.getElementTextValue(itemCode4);
            break;

        default:
            break;
        }

        return value;
    }

    public boolean isItemPresent(Integer itemNumber) {
        boolean present = false;
        switch (itemNumber) {
        case 1:
            present = WebElementOperations.isElementPresent(itemCode1);
            break;
        case 2:
            present = WebElementOperations.isElementPresent(itemCode2);
            break;
        case 3:
            present = WebElementOperations.isElementPresent(itemCode3);
            break;
        case 4:
            present = WebElementOperations.isElementPresent(itemCode4);
            break;
        default:
            break;
        }

        return present;
    }

    public ItemPage displayItemDetail(Integer itemNumber) {
        ItemPage ItemPage = new ItemPage(driver);
        switch (itemNumber) {
        case 1:
            itemDetail1Link.click();
            break;
        case 2:
            itemDetail2Link.click();
            break;
        case 3:
            itemDetail3Link.click();
            break;
        case 4:
            itemDetail4Link.click();
            break;
        default:
            break;
        }
        return ItemPage;
    }

    public String getItemQuantity(Integer itemNumber) {
        String qty = "";
        switch (itemNumber) {
        case 1:
            qty = WebElementOperations.getElementTextValue(itemQty1);
            break;
        case 2:
            qty = WebElementOperations.getElementTextValue(itemQty2);
            break;
        case 3:
            qty = WebElementOperations.getElementTextValue(itemQty3);
            break;
        case 4:
            qty = WebElementOperations.getElementTextValue(itemQty4);
            break;
        default:
            break;
        }

        return qty;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

}
