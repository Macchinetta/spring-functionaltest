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
package jp.co.ntt.fw.spring.functionaltest.selenium.djpa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import jp.co.ntt.fw.spring.functionaltest.selenium.Page;
import jp.co.ntt.fw.spring.functionaltest.selenium.WebElementOperations;

public class OrdersPage implements Page<OrdersPage> {

    private WebDriver driver;

    @CacheLookup
    @FindBy(id = "orderId")
    private WebElement orderId;

    @CacheLookup
    @FindBy(id = "deleteId")
    private WebElement deleteOrderId;

    @CacheLookup
    @FindBy(id = "fetch")
    private WebElement fetchOrderDetailBtn;

    @CacheLookup
    @FindBy(id = "delete")
    private WebElement deleteOrderBtn;

    @CacheLookup
    @FindBy(id = "create")
    private WebElement placeOrderBtn;

    @CacheLookup
    @FindBy(id = "id1")
    private WebElement orderDetailLnk1;

    @CacheLookup
    @FindBy(id = "id2")
    private WebElement orderDetailLnk2;

    @CacheLookup
    @FindBy(id = "id3")
    private WebElement orderDetailLnk3;

    @CacheLookup
    @FindBy(id = "id4")
    private WebElement orderDetailLnk4;

    @CacheLookup
    @FindBy(id = "id5")
    private WebElement orderDetailLnk5;

    @CacheLookup
    @FindBy(id = "id6")
    private WebElement orderDetailLnk6;

    @CacheLookup
    @FindBy(id = "id7")
    private WebElement orderDetailLnk7;

    @CacheLookup
    @FindBy(id = "id8")
    private WebElement orderDetailLnk8;

    @CacheLookup
    @FindBy(id = "id9")
    private WebElement orderDetailLnk9;

    @CacheLookup
    @FindBy(id = "orderStatus.statusName")
    private WebElement srchStatus;

    @CacheLookup
    @FindBy(id = "searchByStatus")
    private WebElement searchByStatusBtn;

    @CacheLookup
    @FindBy(id = "memo_1")
    private WebElement order1Memo;

    @CacheLookup
    @FindBy(id = "memo_2")
    private WebElement order2Memo;

    @CacheLookup
    @FindBy(id = "memo_3")
    private WebElement order3Memo;

    @CacheLookup
    @FindBy(id = "memo_4")
    private WebElement order4Memo;

    @CacheLookup
    @FindBy(id = "amount_1")
    private WebElement order1Amount;

    @CacheLookup
    @FindBy(id = "amount_2")
    private WebElement order2Amount;

    @CacheLookup
    @FindBy(id = "amount_3")
    private WebElement order3Amount;

    @CacheLookup
    @FindBy(id = "amount_4")
    private WebElement order4Amount;

    @CacheLookup
    @FindBy(id = "status_1")
    private WebElement order1Status;

    @CacheLookup
    @FindBy(id = "status_2")
    private WebElement order2Status;

    @CacheLookup
    @FindBy(id = "status_3")
    private WebElement order3Status;

    @CacheLookup
    @FindBy(id = "status_4")
    private WebElement order4Status;

    @CacheLookup
    @FindBy(id = "fetchSummary")
    private WebElement fetchSummaryBtn;

    @CacheLookup
    @FindBy(id = "cmnCondFetch")
    private WebElement cmnCondEntityFetchBtn;

    public OrdersPage(WebDriver driver2) {
        this.driver = driver2;
        reload();
    }

    @Override
    public OrdersPage reload() {

        PageFactory.initElements(driver, this);
        return this;
    }

    public void setSearchOrderId(String orderIdVal) {
        WebElementOperations.setValue(orderId, orderIdVal);
    }

    public void setDeleteOrderId(String orderIdVal) {
        WebElementOperations.setValue(deleteOrderId, orderIdVal);
    }

    public void setSearchStatus(String statusVal) {
        WebElementOperations.setValue(srchStatus, statusVal);
    }

    public OrdersPage searchByJoinFetch() {
        OrdersPage ordersPage = new OrdersPage(driver);
        searchByStatusBtn.click();
        return ordersPage;
    }

    public OrdersPage fetchResultInCustomObject() {
        OrdersPage ordersPage = new OrdersPage(driver);
        fetchSummaryBtn.click();
        return ordersPage;
    }

    public OrderDetailsPage displayOrderDetailUsingCmnConditionSpecifiedOnEntity() {
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
        cmnCondEntityFetchBtn.click();
        return orderDetailsPage;

    }

    public OrderEntryPage createOrder() {
        placeOrderBtn.click();
        OrderEntryPage entryPage = new OrderEntryPage(driver);
        return entryPage;
    }

    public OrderDetailsPage displayOrderDetail() {
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
        fetchOrderDetailBtn.click();
        return orderDetailsPage;

    }

    public OrdersPage deleteOrder() {
        OrdersPage ordersPage = new OrdersPage(driver);
        deleteOrderBtn.click();
        return ordersPage;
    }

    public String getOrderAmount(Integer orderNum) {
        String amount = "";
        switch (orderNum) {
            case 1:
                amount = WebElementOperations.getElementTextValue(order1Amount);
                break;
            case 2:
                amount = WebElementOperations.getElementTextValue(order2Amount);
                break;
            case 3:
                amount = WebElementOperations.getElementTextValue(order3Amount);
                break;
            case 4:
                amount = WebElementOperations.getElementTextValue(order4Amount);
                break;
            default:
                break;
        }
        return amount;
    }

    public String getOrderMemo(Integer orderNum) {
        String memo = "";
        switch (orderNum) {
            case 1:
                memo = WebElementOperations.getElementTextValue(order1Memo);
                break;
            case 2:
                memo = WebElementOperations.getElementTextValue(order2Memo);
                break;
            case 3:
                memo = WebElementOperations.getElementTextValue(order3Memo);
                break;
            case 4:
                memo = WebElementOperations.getElementTextValue(order4Memo);
                break;
            default:
                break;
        }
        return memo;
    }

    public String getOrderStatus(Integer orderNum) {
        String status = "";
        switch (orderNum) {
            case 1:
                status = WebElementOperations.getElementTextValue(order1Status);
                break;
            case 2:
                status = WebElementOperations.getElementTextValue(order2Status);
                break;
            case 3:
                status = WebElementOperations.getElementTextValue(order3Status);
                break;
            case 4:
                status = WebElementOperations.getElementTextValue(order4Status);
                break;
            default:
                break;
        }
        return status;
    }

    public OrderDetailsPage displayOderDetail(Integer orderNum) {
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
        switch (orderNum) {
            case 1:
                orderDetailLnk1.click();
                break;
            case 2:
                orderDetailLnk2.click();
                break;
            case 3:
                orderDetailLnk3.click();
                break;
            case 4:
                orderDetailLnk4.click();
                break;
            case 5:
                orderDetailLnk5.click();
                break;
            case 6:
                orderDetailLnk6.click();
                break;
            case 7:
                orderDetailLnk7.click();
                break;
            case 8:
                orderDetailLnk8.click();
                break;
            default:
                break;
        }
        return orderDetailsPage;

    }

}
