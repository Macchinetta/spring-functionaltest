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

public class SystemErrorPage implements Page<SystemErrorPage> {

    private WebDriver driver;

    @CacheLookup
    @FindBy(xpath = "//*[@id='wrapper']/div/ul[2]/li")
    private WebElement errorMessageElement;

    public SystemErrorPage(WebDriver driver2) {
        this.driver = driver2;
        reload();
    }

    @Override
    public SystemErrorPage reload() {

        PageFactory.initElements(driver, this);
        return this;
    }

    public String getErrorMessage() {
        return WebElementOperations.getElementTextValue(errorMessageElement);
    }

}
