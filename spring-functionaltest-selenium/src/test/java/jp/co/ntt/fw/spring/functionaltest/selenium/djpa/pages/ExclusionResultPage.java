/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.selenium.djpa.pages;

import jp.co.ntt.fw.spring.functionaltest.selenium.Page;
import jp.co.ntt.fw.spring.functionaltest.selenium.WebElementOperations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ExclusionResultPage implements Page<ExclusionResultPage> {

    private WebDriver driver;

    @CacheLookup
    @FindBy(id = "result_itemCode")
    private WebElement itemCode;

    @CacheLookup
    @FindBy(id = "result_itemName")
    private WebElement itemName;

    @CacheLookup
    @FindBy(id = "result_quantity")
    private WebElement quantity;

    @CacheLookup
    @FindBy(id = "result_version")
    private WebElement version;

    @CacheLookup
    @FindBy(id = "databaseId")
    private WebElement databaseId;

    public ExclusionResultPage(WebDriver driver2) {
        this.driver = driver2;
        reload();
    }

    @Override
    public ExclusionResultPage reload() {

        PageFactory.initElements(driver, this);
        return this;
    }

    public String getDatabase() {
        return WebElementOperations.getElementTextValue(databaseId);
    }

    public String getVersion() {
        return WebElementOperations.getElementTextValue(version);
    }

    public String getQuantity() {
        return WebElementOperations.getElementTextValue(quantity);
    }

    public String getItemCode() {
        return WebElementOperations.getElementTextValue(itemCode);
    }

    public String getItemName() {
        return WebElementOperations.getElementTextValue(itemName);
    }

}
