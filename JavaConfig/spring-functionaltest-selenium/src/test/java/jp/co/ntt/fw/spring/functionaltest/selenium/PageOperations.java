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

import org.openqa.selenium.WebDriver;
import org.springframework.beans.BeanUtils;

public class PageOperations {

    private PageOperations() {
        // NOP
    }

    public static <P extends Page<P>> P loadNextPage(Page<?> currentPage, Class<P> nextPage,
            WebDriver driver) {
        if (nextPage == currentPage.getClass()) {
            return nextPage.cast(currentPage).reload();
        }
        try {
            return BeanUtils.instantiateClass(nextPage.getConstructor(WebDriver.class), driver);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException(e);
        }
    }

}
