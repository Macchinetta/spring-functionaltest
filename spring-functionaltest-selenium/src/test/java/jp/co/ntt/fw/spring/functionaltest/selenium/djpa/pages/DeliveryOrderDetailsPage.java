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

public class DeliveryOrderDetailsPage implements
                                      Page<DeliveryOrderDetailsPage> {

    public WebDriver getDriver() {
        return driver;
    }

    private WebDriver driver;

    @CacheLookup
    @FindBy(id = "deliveryType")
    private WebElement deliveryType;

    @CacheLookup
    @FindBy(id = "senderName")
    private WebElement senderName;

    @CacheLookup
    @FindBy(id = "senderAddress")
    private WebElement senderAddress;

    @CacheLookup
    @FindBy(id = "receiverName")
    private WebElement receiverName;

    @CacheLookup
    @FindBy(id = "receiverAddress")
    private WebElement receiverAddress;

    @CacheLookup
    @FindBy(id = "acceptDateTime")
    private WebElement acceptDateTime;

    @CacheLookup
    @FindBy(id = "completionDateTime")
    private WebElement completionDateTime;

    @CacheLookup
    @FindBy(id = "deliveryDriver")
    private WebElement deliveryDriver;

    @CacheLookup
    @FindBy(id = "deliveryStatus")
    private WebElement deliveryStatus;

    @CacheLookup
    @FindBy(id = "deliverNumber")
    private WebElement deliverNumber;

    @CacheLookup
    @FindBy(id = "cancel")
    private WebElement orderListPageBtn;

    @CacheLookup
    @FindBy(id = "add")
    private WebElement addEntityBtn;

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
    @FindBy(id = "orderSumId")
    private WebElement orderSumId;

    @CacheLookup
    @FindBy(id = "orderSumAmt")
    private WebElement orderSumAmt;

    @CacheLookup
    @FindBy(id = "orderSumMemo")
    private WebElement orderSumMemo;

    @CacheLookup
    @FindBy(id = "orderSumStatus")
    private WebElement orderSumStatus;

    public DeliveryOrderDetailsPage(WebDriver driver2) {
        this.driver = driver2;
        reload();
    }

    @Override
    public DeliveryOrderDetailsPage reload() {
        PageFactory.initElements(driver, this);
        return this;
    }

    public String getOrderSummaryOrderId() {
        return WebElementOperations.getElementTextValue(orderSumId);
    }

    public String getOrderSummaryOrderAmount() {
        return WebElementOperations.getElementTextValue(orderSumAmt);
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

    public String getDeliveryType() {
        return WebElementOperations.getValue(deliveryType);
    }

    public String getSenderName() {
        return WebElementOperations.getValue(senderName);
    }

    public String getSenderAddress() {
        return WebElementOperations.getValue(senderAddress);
    }

    public String getReceiverName() {
        return WebElementOperations.getValue(receiverName);
    }

    public String getReceiverAddress() {
        return WebElementOperations.getValue(receiverAddress);
    }

    public String getAcceptDateTime() {
        return WebElementOperations.getValue(acceptDateTime);
    }

    public String getCompletionDateTime() {
        return WebElementOperations.getValue(completionDateTime);
    }

    public String getDeliveryDriver() {
        return WebElementOperations.getValue(deliveryDriver);
    }

    public String getDeliveryStatus() {
        return WebElementOperations.getValue(deliveryStatus);
    }

    public String getDeliverNumber() {
        return WebElementOperations.getValue(deliverNumber);
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void setDeliveryType(String deliveryType) {
        WebElementOperations.setValue(this.deliveryType, deliveryType);
    }

    public void setSenderName(String senderName) {
        WebElementOperations.setValue(this.senderName, senderName);
    }

    public void setSenderAddress(String senderAddress) {
        WebElementOperations.setValue(this.senderAddress, senderAddress);
    }

    public void setReceiverName(String receiverName) {
        WebElementOperations.setValue(this.receiverName, receiverName);
    }

    public void setReceiverAddress(String receiverAddress) {
        WebElementOperations.setValue(this.receiverAddress, receiverAddress);
    }

    public void setAcceptDateTime(String acceptDateTime) {
        WebElementOperations.setValue(this.acceptDateTime, acceptDateTime);
    }

    public void setCompletionDateTime(String completionDateTime) {
        WebElementOperations.setValue(this.completionDateTime,
                completionDateTime);
    }

    public void setDeliveryDriver(String deliveryDriver) {
        WebElementOperations.setValue(this.deliveryDriver, deliveryDriver);
    }

    public void setDeliveryStatus(String deliveryStatus) {
        WebElementOperations.setValue(this.deliveryStatus, deliveryStatus);
    }

    public void setDeliverNumber(String deliverNumber) {
        WebElementOperations.setValue(this.deliverNumber, deliverNumber);
    }

    public OrderDeliveryListPage showOrderListPage() {
        orderListPageBtn.click();
        OrderDeliveryListPage orderDeliveryListPage = new OrderDeliveryListPage(driver);
        return orderDeliveryListPage;
    }

    public DeliveryOrderDetailsPage addEntityBySave() {
        addEntityBtn.click();
        DeliveryOrderDetailsPage orderDetailsPage = new DeliveryOrderDetailsPage(driver);
        return orderDetailsPage;
    }

}
