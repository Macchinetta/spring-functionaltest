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
package jp.co.ntt.fw.spring.functionaltest.selenium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WebElementOperations {
    private WebElementOperations() {
        // NOP
    }

    public static <P extends Page<P>> P input(P page, WebElement element,
            String value) {
        element.clear();
        element.sendKeys(value);
        return page;
    }

    public static <P extends Page<P>> P select(P page, List<WebElement> radios,
            boolean value) {
        radios.get(value ? 0 : 1).click();
        return page;
    }

    public static <P extends Page<P>> P check(P page,
            List<WebElement> checkboxes, String... selectValues) {
        Set<String> valueSet = new HashSet<>(Arrays.asList(selectValues));
        for (WebElement checkbox : checkboxes) {
            if (valueSet.contains(getValue(checkbox)) && !checkbox
                    .isSelected()) {
                checkbox.click();
            } else if (checkbox.isSelected()) {
                checkbox.click();
            }
        }
        return page;
    }

    public static String getValue(WebElement item) {
        return item.getAttribute("value");
    }

    public static String getElementTextValue(WebElement item) {
        return item.getText();
    }

    public static void setValue(WebElement item, String val) {
        item.sendKeys(val);
    }

    /**
     * ドロップダウンメニューから指定した項目を選択する。
     * @param item ドロップダウンメニュー(selectタグ)の要素
     * @param val 選択する項目
     */
    public static void setSort(WebElement item, String val) {
        Select dropdown = new Select(item);
        dropdown.selectByVisibleText(val);
    }

    /**
     * 指定した要素(セレクト)を選択する。
     * @param by 要素(テキスト項目)を探すための識別子
     */
    public static void select(WebElement webElement, String value) {
        new Select(webElement).selectByVisibleText(value);
    }

    /**
     * Check whether the given element is present on the web page.
     * @param webElement
     * @return
     */
    public static Boolean isElementPresent(WebElement webElement) {
        Boolean visible = false;
        try {
            webElement.isDisplayed();
            visible = true;
        } catch (NoSuchElementException nse) {
            visible = false;
        }
        return visible;
    }

}
