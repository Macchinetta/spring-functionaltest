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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * WebDriver処理のリスナークラス
 */
public class WaitWebDriverEventListener extends WebDriverEventListenerAdapter {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected Wait<WebDriver> wait = null;

    protected String xTrack;

    boolean radioButton;

    @Value("${selenium.webDriver.locatorWait}")
    protected long webDriverLocatorWait;

    @Value("${selenium.webDriver.xTrackWait}")
    protected long webDriverXtrackWait;

    @Value("${selenium.webDriver.locatorSleepWait}")
    protected long webDriverLocatorSleepWait;

    @Value("${selenium.webDriver.xTraxkSleepWait}")
    protected long webDriverXtrackSleepWait;

    /**
     * findElement()前処理 引数の要素が表示されるまで待機する<br>
     * locatorがラジオボタンの要素の場合、radioButtonをtureにする
     */
    @Override
    public void beforeFindBy(By by, WebElement webElement,
            WebDriver webDriver) {
        try {
            radioButton = false;
            if (by.toString().matches(".*scope.*_approve.*")) {
                radioButton = true;
            }
            wait = new WebDriverWait(webDriver, webDriverLocatorWait, webDriverLocatorSleepWait);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (TimeoutException e) {
            logger.debug("Locator hasn't change in default time");
        }
    }

    /**
     * click()前処理 フッター部に表示されているxTrackを取得する<br>
     * 当該ページにxTrackが表示されない場合は空値を設定する
     */
    @Override
    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
        try {
            xTrack = webDriver.findElement(By.id("xTrack")).getText();
        } catch (NoSuchElementException e) {
            logger.info("XTrack doesn't exist on the page");
            xTrack = "";
        }
    }

    /**
     * click()後処理 フッター部に表示されているxTrackの値が変更するまで待機する<br>
     * クリックする要素がラジオボタンの場合、待機処理を行わない
     */
    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        if (!radioButton) {
            try {
                wait = new WebDriverWait(webDriver, webDriverXtrackWait, webDriverXtrackSleepWait);
                wait.until(ExpectedConditions.invisibilityOfElementWithText(By
                        .id("xTrack"), xTrack));
            } catch (TimeoutException e) {
                logger.debug("XTrack hasn't change in default time");
            }
        }
    }

}
