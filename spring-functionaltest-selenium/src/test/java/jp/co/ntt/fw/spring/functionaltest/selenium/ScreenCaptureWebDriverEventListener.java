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
package jp.co.ntt.fw.spring.functionaltest.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Captures screen using ScreenCapture class after navigation and click on button/link Please extend the class and override the
 * behavior as per requirement
 */
public class ScreenCaptureWebDriverEventListener extends
                                                 WebDriverEventListenerAdapter {

    protected final ScreenCapture screenCapture;

    public ScreenCaptureWebDriverEventListener(ScreenCapture screenCapture) {
        this.screenCapture = screenCapture;
    }

    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        // following statement is required for ensuring that every screen is captured.
        // some screens that do not have assertions or element access, need following
        // statement such that driver accesses the screen atleast once
        webDriver.findElement(By.tagName("body"));
        screenCapture.save(webDriver);
    }

    @Override
    public void afterNavigateTo(String url, WebDriver webDriver) {
        screenCapture.save(webDriver);
    }

}
