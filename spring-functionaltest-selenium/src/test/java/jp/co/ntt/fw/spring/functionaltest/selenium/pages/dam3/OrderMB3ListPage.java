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
package jp.co.ntt.fw.spring.functionaltest.selenium.pages.dam3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import jp.co.ntt.fw.spring.functionaltest.selenium.Page;
import jp.co.ntt.fw.spring.functionaltest.selenium.WebElementOperations;

public class OrderMB3ListPage implements Page<OrderMB3ListPage> {

    private WebDriver driver;

    @CacheLookup
    @FindBy(id = "statusName_1")
    private WebElement statusName1;

    @CacheLookup
    @FindBy(id = "statusName_2")
    private WebElement statusName2;

    @CacheLookup
    @FindBy(id = "statusName_3")
    private WebElement statusName3;

    @CacheLookup
    @FindBy(id = "statusName_4")
    private WebElement statusName4;

    @CacheLookup
    @FindBy(id = "itemCode_1")
    private WebElement itemCode1;

    @CacheLookup
    @FindBy(id = "itemCode_2")
    private WebElement itemCode2;

    @CacheLookup
    @FindBy(id = "itemCode_3")
    private WebElement itemCode3;

    @CacheLookup
    @FindBy(id = "itemCode_4")
    private WebElement itemCode4;

    @CacheLookup
    @FindBy(id = "itemName_1")
    private WebElement itemName1;

    @CacheLookup
    @FindBy(id = "itemName_2")
    private WebElement itemName2;

    @CacheLookup
    @FindBy(id = "itemName_3")
    private WebElement itemName3;

    @CacheLookup
    @FindBy(id = "itemName_4")
    private WebElement itemName4;

    @CacheLookup
    @FindBy(id = "categoryCode_1")
    private WebElement categorCode1;

    @CacheLookup
    @FindBy(id = "categoryCode_2")
    private WebElement categorCode2;

    @CacheLookup
    @FindBy(id = "categoryCode_3")
    private WebElement categorCode3;

    @CacheLookup
    @FindBy(id = "categoryCode_4")
    private WebElement categorCode4;

    @CacheLookup
    @FindBy(id = "categoryName_1")
    private WebElement categoryName1;

    @CacheLookup
    @FindBy(id = "categoryName_2")
    private WebElement categoryName2;

    @CacheLookup
    @FindBy(id = "categoryName_3")
    private WebElement categoryName3;

    @CacheLookup
    @FindBy(id = "categoryName_4")
    private WebElement categoryName4;

    @CacheLookup
    @FindBy(id = "memo_1")
    private WebElement memo1;

    @CacheLookup
    @FindBy(id = "memo_2")
    private WebElement memo2;

    @CacheLookup
    @FindBy(id = "memo_3")
    private WebElement memo3;

    @CacheLookup
    @FindBy(id = "memo_4")
    private WebElement memo4;

    @CacheLookup
    @FindBy(id = "itemCode")
    private WebElement itemCode;

    @CacheLookup
    @FindBy(id = "catDisplay")
    private WebElement catDisplayBtn;

    @CacheLookup
    @FindBy(id = "code_1")
    private WebElement catCodeForItem1;

    @CacheLookup
    @FindBy(id = "code_2")
    private WebElement catCodeForItem2;

    @CacheLookup
    @FindBy(id = "name_1")
    private WebElement catNameForItem1;

    @CacheLookup
    @FindBy(id = "name_2")
    private WebElement catNameForItem2;

    @CacheLookup
    @FindBy(id = "catDisplayLazy")
    private WebElement catDisplayLazyBtn;

    public OrderMB3ListPage(WebDriver driver2) {
        this.driver = driver2;
        reload();
    }

    @Override
    public OrderMB3ListPage reload() {
        PageFactory.initElements(driver, this);
        return this;
    }

    public String getOrderDetails(int orderNumber) {
        StringBuilder stringBuilder = new StringBuilder();
        String delimiter = ", ";
        String statusName = "";
        String itemCode = "";
        String itemName = "";
        String categoryCode = "";
        String categoryName = "";
        String memo = "";
        switch (orderNumber) {
        case 1:
            statusName = WebElementOperations.getElementTextValue(statusName1);
            itemCode = WebElementOperations.getElementTextValue(itemCode1);
            itemName = WebElementOperations.getElementTextValue(itemName1);
            categoryCode = WebElementOperations.getElementTextValue(
                    categorCode1);
            categoryName = WebElementOperations.getElementTextValue(
                    categoryName1);
            memo = WebElementOperations.getElementTextValue(memo1);
            break;
        case 2:
            statusName = WebElementOperations.getElementTextValue(statusName2);
            itemCode = WebElementOperations.getElementTextValue(itemCode2);
            itemName = WebElementOperations.getElementTextValue(itemName2);
            categoryCode = WebElementOperations.getElementTextValue(
                    categorCode2);
            categoryName = WebElementOperations.getElementTextValue(
                    categoryName2);
            memo = WebElementOperations.getElementTextValue(memo2);
            break;
        case 3:
            statusName = WebElementOperations.getElementTextValue(statusName3);
            itemCode = WebElementOperations.getElementTextValue(itemCode3);
            itemName = WebElementOperations.getElementTextValue(itemName3);
            categoryCode = WebElementOperations.getElementTextValue(
                    categorCode3);
            categoryName = WebElementOperations.getElementTextValue(
                    categoryName3);
            memo = WebElementOperations.getElementTextValue(memo3);
            break;
        case 4:
            statusName = WebElementOperations.getElementTextValue(statusName4);
            itemCode = WebElementOperations.getElementTextValue(itemCode4);
            itemName = WebElementOperations.getElementTextValue(itemName4);
            categoryCode = WebElementOperations.getElementTextValue(
                    categorCode4);
            categoryName = WebElementOperations.getElementTextValue(
                    categoryName4);
            memo = WebElementOperations.getElementTextValue(memo4);
            break;

        default:
            break;
        }
        stringBuilder.append(statusName);
        stringBuilder.append(delimiter);
        stringBuilder.append(itemCode);
        stringBuilder.append(delimiter);
        stringBuilder.append(itemName);
        stringBuilder.append(delimiter);
        stringBuilder.append(categoryCode);
        stringBuilder.append(delimiter);
        stringBuilder.append(categoryName);
        stringBuilder.append(delimiter);
        stringBuilder.append(memo);

        return stringBuilder.toString();
    }

    public void setItemCode(String itemCodeVal) {
        WebElementOperations.setValue(itemCode, itemCodeVal);
    }

    public OrderMB3ListPage fetchRelatedEntity() {
        OrderMB3ListPage orderMB3ListPage = new OrderMB3ListPage(driver);
        catDisplayBtn.click();
        return orderMB3ListPage;
    }

    public String getRelatedEntityCategoryDeatils(int srNum) {
        String catCode = "";
        String catName = "";
        String delimiter = ", ";
        StringBuilder stringBuilder = new StringBuilder();
        switch (srNum) {
        case 1:
            catCode = WebElementOperations.getElementTextValue(catCodeForItem1);
            catName = WebElementOperations.getElementTextValue(catNameForItem1);
            break;

        case 2:
            catCode = WebElementOperations.getElementTextValue(catCodeForItem2);
            catName = WebElementOperations.getElementTextValue(catNameForItem2);
            break;
        }

        stringBuilder.append(catCode);
        stringBuilder.append(delimiter);
        stringBuilder.append(catName);

        return stringBuilder.toString();
    }

    public OrderMB3ListPage fetchRelatedEntityLazy() {
        OrderMB3ListPage orderMB3ListPage = new OrderMB3ListPage(driver);
        catDisplayLazyBtn.click();
        return orderMB3ListPage;
    }

}
