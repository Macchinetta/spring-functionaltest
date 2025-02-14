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
package jp.co.ntt.fw.spring.functionaltest.selenium.rscl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import jp.co.ntt.fw.spring.functionaltest.selenium.Page;
import jp.co.ntt.fw.spring.functionaltest.selenium.WebElementOperations;

public class ResultUserInfPage implements Page<ResultUserInfPage> {

    private WebDriver driver;

    @CacheLookup
    @FindBy(id = "screenTitle")
    private WebElement screenTitle;

    @CacheLookup
    @FindBy(id = "userName")
    private WebElement userName;

    @CacheLookup
    @FindBy(id = "userAge")
    private WebElement userAge;

    @CacheLookup
    @FindBy(id = "compleatDateTime")
    private WebElement compleatDateTime;

    @CacheLookup
    @FindBy(id = "executeTime")
    private WebElement executeTime;

    public ResultUserInfPage(WebDriver driver) {
        this.driver = driver;
        reload();
    }

    @Override
    public ResultUserInfPage reload() {
        PageFactory.initElements(driver, this);
        return this;
    }

    public String getScreenTitle() {
        return WebElementOperations.getElementTextValue(screenTitle);
    }

    public String getUserName() {
        return WebElementOperations.getElementTextValue(userName);
    }

    public String getUserAge() {
        return WebElementOperations.getElementTextValue(userAge);
    }

    public String getCompleatDateTime() {
        return WebElementOperations.getElementTextValue(compleatDateTime);
    }

    public String getExecuteTime() {
        return WebElementOperations.getElementTextValue(executeTime);
    }
}
