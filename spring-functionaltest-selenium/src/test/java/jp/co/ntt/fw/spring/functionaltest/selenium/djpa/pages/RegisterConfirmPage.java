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

public class RegisterConfirmPage implements Page<RegisterConfirmPage> {

    private WebDriver driver;

    @CacheLookup
    @FindBy(id = "getRegisterSession")
    private WebElement getRegisterSessionBtn;

    public RegisterConfirmPage(WebDriver driver2) {
        this.driver = driver2;
        reload();
    }

    @Override
    public RegisterConfirmPage reload() {

        PageFactory.initElements(driver, this);
        return this;
    }

    public SystemErrorPage accessOutOfLazyFetchScope() {
        SystemErrorPage systemErrorPage = new SystemErrorPage(driver);
        getRegisterSessionBtn.click();
        return systemErrorPage;

    }

}
