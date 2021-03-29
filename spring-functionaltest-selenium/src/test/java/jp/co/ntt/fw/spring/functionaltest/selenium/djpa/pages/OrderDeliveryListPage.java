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

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import jp.co.ntt.fw.spring.functionaltest.selenium.Page;
import jp.co.ntt.fw.spring.functionaltest.selenium.WebElementOperations;

public class OrderDeliveryListPage implements Page<OrderDeliveryListPage> {

    private WebDriver driver;

    @CacheLookup
    @FindBy(id = "djpaLink")
    private WebElement djpaLink;

    @CacheLookup
    @FindBy(id = "delOrder_1")
    private WebElement orderDel1Details;

    @CacheLookup
    @FindBy(id = "delOrder_2")
    private WebElement orderDel2Details;

    @CacheLookup
    @FindBy(id = "delOrder_3")
    private WebElement orderDel3Details;

    @CacheLookup
    @FindBy(id = "delOrder_4")
    private WebElement orderDel4Details;

    @CacheLookup
    @FindBy(id = "delOrder_7")
    private WebElement orderDel7Details;

    @CacheLookup
    @FindBy(id = "delOrder_8")
    private WebElement orderDel8Details;

    @CacheLookup
    @FindBy(id = "delOrder_15")
    private WebElement orderDel15Details;

    @CacheLookup
    @FindBy(id = "delOrder_20")
    private WebElement orderDel20Details;

    @CacheLookup
    @FindBy(id = "delOrderStatus")
    private WebElement orderStatus;

    @CacheLookup
    @FindBy(id = "form")
    private WebElement searchOrders;

    @CacheLookup
    @FindBy(id = "methodNameConven")
    private WebElement queryByMethodNameBtn;

    @CacheLookup
    @FindBy(id = "nativeQuery")
    private WebElement searchByNativeQueryBtn;

    @CacheLookup
    @FindBy(id = "likeSrch")
    private WebElement atQueryLikeSearchBtn;

    @CacheLookup
    @FindBy(id = "matchOption")
    private WebElement searchOption;

    @CacheLookup
    @FindBy(id = "deliverNumber")
    private WebElement deliverNumberInput;

    @CacheLookup
    @FindBy(id = "deliveryType")
    private WebElement deliveryTypeInput;

    @CacheLookup
    @FindBy(id = "matchCond")
    private WebElement searchEntityPageMatchCondBtn;

    @CacheLookup
    @FindBy(id = "qHint")
    private WebElement qHintQueryTimeoutBtn;

    @CacheLookup
    @FindBy(id = "dynaCond")
    private WebElement searchPageUsingDynaCondBtn;

    @CacheLookup
    @FindBy(xpath = "/html/body/div[2]/div/div[1]/ul/li[6]/a")
    private WebElement nextPageButton;

    @CacheLookup
    @FindBy(id = "escSrchDash")
    private WebElement escapeSearchUnderscoreBtn;

    @CacheLookup
    @FindBy(id = "escSrchMod")
    private WebElement escapeSearchModuloBtn;

    @CacheLookup
    @FindBy(id = "escapeSrchVal")
    private WebElement escapeSearchVal;

    @CacheLookup
    @FindBy(id = "srchMatchInLogic")
    private WebElement searchUsingMatchInLogicBtn;

    @CacheLookup
    @FindBy(id = "addMethodToIndRepo")
    private WebElement searchByAddedMethodBtn;

    public OrderDeliveryListPage(WebDriver driver2) {
        this.driver = driver2;
        reload();
    }

    @Override
    public OrderDeliveryListPage reload() {
        PageFactory.initElements(driver, this);
        return this;
    }

    public DeliveryOrderDetailsPage displayOrderDetails(Integer orderNo) {

        switch (orderNo) {
        case 1:
            orderDel1Details.click();
            break;
        case 2:
            orderDel2Details.click();
            break;
        case 3:
            orderDel3Details.click();
            break;
        case 4:
            orderDel4Details.click();
            break;
        case 7:
            orderDel7Details.click();
            break;
        case 8:
            orderDel8Details.click();
            break;
        case 15:
            orderDel15Details.click();
        case 20:
            orderDel20Details.click();
            break;
        default:
            break;
        }

        DeliveryOrderDetailsPage orderDetailsPage = new DeliveryOrderDetailsPage(driver);
        return orderDetailsPage;
    }

    public void setSearchValue(String statValue) {
        WebElementOperations.setValue(orderStatus, statValue);
    }

    public void setEscapeSearchValue(String value) {
        WebElementOperations.setValue(escapeSearchVal, value);
    }

    public void setDeliveryNumberValue(String deliveryNum) {
        WebElementOperations.setValue(deliverNumberInput, deliveryNum);
    }

    public void setDeliveryTypeValue(String delType) {
        WebElementOperations.setValue(deliveryTypeInput, delType);
    }

    public OrderDeliveryListPage searchByGivenStatus() {
        searchOrders.click();
        OrderDeliveryListPage orderListPage = new OrderDeliveryListPage(driver);
        return orderListPage;
    }

    public OrderDeliveryListPage escapeSearchModulo() {
        escapeSearchModuloBtn.click();
        OrderDeliveryListPage orderListPage = new OrderDeliveryListPage(driver);
        return orderListPage;

    }

    public OrderDeliveryListPage escapeSearchUnderscore() {
        escapeSearchUnderscoreBtn.click();
        OrderDeliveryListPage orderListPage = new OrderDeliveryListPage(driver);
        return orderListPage;

    }

    public OrderDeliveryListPage escapeSearchMatchInLogic() {
        searchUsingMatchInLogicBtn.click();
        OrderDeliveryListPage orderListPage = new OrderDeliveryListPage(driver);
        return orderListPage;

    }

    public OrderDeliveryListPage searchByMethodNameConvention() {
        queryByMethodNameBtn.click();
        OrderDeliveryListPage orderListPage = new OrderDeliveryListPage(driver);
        return orderListPage;

    }

    public OrderDeliveryListPage searchUsingNativeQuery() {
        searchByNativeQueryBtn.click();
        OrderDeliveryListPage orderListPage = new OrderDeliveryListPage(driver);
        return orderListPage;
    }

    public OrderDeliveryListPage atQueryLikeSearch() {
        atQueryLikeSearchBtn.click();
        OrderDeliveryListPage orderListPage = new OrderDeliveryListPage(driver);
        return orderListPage;
    }

    public OrderDeliveryListPage searchEnityPageMatchingConditions() {
        searchEntityPageMatchCondBtn.click();
        OrderDeliveryListPage orderListPage = new OrderDeliveryListPage(driver);
        return orderListPage;
    }

    public OrderDeliveryListPage qHintQueryTimeout() {
        qHintQueryTimeoutBtn.click();
        OrderDeliveryListPage orderListPage = new OrderDeliveryListPage(driver);
        return orderListPage;
    }

    public OrderDeliveryListPage searchPageUsingDynaConditions() {
        searchPageUsingDynaCondBtn.click();
        OrderDeliveryListPage orderListPage = new OrderDeliveryListPage(driver);
        return orderListPage;
    }

    public OrderDeliveryListPage gotoNextPage() {
        nextPageButton.click();
        OrderDeliveryListPage orderListPage = new OrderDeliveryListPage(driver);
        return orderListPage;

    }

    public OrderDeliveryListPage setSearchCriteria(String searchType) {
        WebElementOperations.setValue(searchOption, searchType);
        OrderDeliveryListPage orderListPage = new OrderDeliveryListPage(driver);
        return orderListPage;
    }

    public boolean isBookDetailLinkPresent(Integer bookNo) {
        boolean visible = false;
        WebElement orderUpdateLink = null;

        switch (bookNo) {
        case 1:
            orderUpdateLink = orderDel1Details;
            break;
        case 2:
            orderUpdateLink = orderDel2Details;
            break;
        case 3:
            orderUpdateLink = orderDel3Details;
            break;
        case 4:
            orderUpdateLink = orderDel4Details;
            break;
        case 7:
            orderUpdateLink = orderDel7Details;
            break;
        case 8:
            orderUpdateLink = orderDel8Details;
            break;
        case 15:
            orderUpdateLink = orderDel15Details;
            break;
        case 20:
            orderUpdateLink = orderDel20Details;
            break;
        default:
            break;
        }

        try {
            orderUpdateLink.isDisplayed();
            visible = true;
        } catch (NoSuchElementException nse) {
            visible = false;
        }
        return visible;
    }

    public OrdersPage searchByCustomMethodAddedTOIndRepo() {
        OrdersPage ordersPage = new OrdersPage(driver);
        searchByAddedMethodBtn.click();
        return ordersPage;
    }

}
