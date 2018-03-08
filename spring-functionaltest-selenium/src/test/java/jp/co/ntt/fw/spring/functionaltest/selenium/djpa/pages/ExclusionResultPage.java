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
