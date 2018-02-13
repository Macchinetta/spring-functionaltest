/*
 * Copyright 2014-2017 NTT Corporation.
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
 *
 */
package jp.co.ntt.fw.spring.functionaltest.selenium.djpa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import jp.co.ntt.fw.spring.functionaltest.selenium.Page;
import jp.co.ntt.fw.spring.functionaltest.selenium.WebElementOperations;

public class StockPage implements Page<StockPage> {

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
    @FindBy(id = "purchasingQuantity")
    private WebElement purchaseQuantity;

    @CacheLookup
    @FindBy(id = "sleepMillis")
    private WebElement sleepTime;

    @CacheLookup
    @FindBy(id = "buy")
    private WebElement buyBtn;

    public StockPage(WebDriver driver2) {
        this.driver = driver2;
        reload();
    }

    @Override
    public StockPage reload() {

        PageFactory.initElements(driver, this);
        return this;
    }

    public void setPurchaseQuantity(Integer quantityVal) {
        purchaseQuantity.clear();
        WebElementOperations.setValue(purchaseQuantity, String.valueOf(
                quantityVal));
    }

    public void setSleepTime(long sleepTimeInMillisec) {
        sleepTime.clear();
        WebElementOperations.setValue(sleepTime, String.valueOf(
                sleepTimeInMillisec));
    }

    public ExclusionResultPage buyWithRowLock() {
        ExclusionResultPage exclusionResultPage = new ExclusionResultPage(driver);
        buyBtn.click();
        return exclusionResultPage;
    }

}
