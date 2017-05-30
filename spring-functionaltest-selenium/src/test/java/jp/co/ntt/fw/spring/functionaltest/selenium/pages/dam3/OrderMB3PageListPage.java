/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.selenium.pages.dam3;

import jp.co.ntt.fw.spring.functionaltest.selenium.Page;
import jp.co.ntt.fw.spring.functionaltest.selenium.WebElementOperations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderMB3PageListPage implements Page<OrderMB3PageListPage> {

    private WebDriver driver;

    @CacheLookup
    @FindBy(id = "statusName_4")
    private WebElement statusName4;

    @CacheLookup
    @FindBy(id = "statusName_7")
    private WebElement statusName7;

    @CacheLookup
    @FindBy(id = "itemCode_4")
    private WebElement itemCode4;

    @CacheLookup
    @FindBy(id = "itemCode_7")
    private WebElement itemCode7;

    @CacheLookup
    @FindBy(id = "itemName_4")
    private WebElement itemName4;

    @CacheLookup
    @FindBy(id = "itemName_7")
    private WebElement itemName7;

    @CacheLookup
    @FindBy(id = "categoryCode_4")
    private WebElement categorCode4;

    @CacheLookup
    @FindBy(id = "categoryCode_7")
    private WebElement categorCode7;

    @CacheLookup
    @FindBy(id = "categoryName_4")
    private WebElement categoryName4;

    @CacheLookup
    @FindBy(id = "categoryName_7")
    private WebElement categoryName7;

    @CacheLookup
    @FindBy(id = "memo_4")
    private WebElement memo4;

    @CacheLookup
    @FindBy(id = "memo_7")
    private WebElement memo7;

    @CacheLookup
    @FindBy(id = "checkbox_ITM0000001")
    private WebElement checkbox_ITM0000001;

    @CacheLookup
    @FindBy(id = "itemCodeSearch")
    private WebElement itemCodeSearch;

    public OrderMB3PageListPage(WebDriver driver2) {
        this.driver = driver2;
        reload();
    }

    @Override
    public OrderMB3PageListPage reload() {
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
        case 4:
            statusName = WebElementOperations.getElementTextValue(statusName4);
            itemCode = WebElementOperations.getElementTextValue(itemCode4);
            itemName = WebElementOperations.getElementTextValue(itemName4);
            categoryCode = WebElementOperations
                    .getElementTextValue(categorCode4);
            categoryName = WebElementOperations
                    .getElementTextValue(categoryName4);
            memo = WebElementOperations.getElementTextValue(memo4);
            break;
        case 7:
            statusName = WebElementOperations.getElementTextValue(statusName7);
            itemCode = WebElementOperations.getElementTextValue(itemCode7);
            itemName = WebElementOperations.getElementTextValue(itemName7);
            categoryCode = WebElementOperations
                    .getElementTextValue(categorCode7);
            categoryName = WebElementOperations
                    .getElementTextValue(categoryName7);
            memo = WebElementOperations.getElementTextValue(memo7);
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

    public OrderMB3PageListPage checkITM0000001() {
        OrderMB3PageListPage orderMB3PageListPage = new OrderMB3PageListPage(driver);
        checkbox_ITM0000001.click();
        return orderMB3PageListPage;
    }

    public OrderMB3PageListPage clickItemCodeSearch() {
        OrderMB3PageListPage orderMB3PageListPage = new OrderMB3PageListPage(driver);
        itemCodeSearch.click();
        return orderMB3PageListPage;
    }

}
