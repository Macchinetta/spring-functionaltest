/*
 * Copyright(c) 2025 NTT Corporation.
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

public enum BrowserLocale {

    FRANCE("fr, FR", "fr-FR", "fr-FR"), // フランス語
    GERMANY("de, DE", "de-DE", "de-DE"), // ドイツ語
    ENGLISH_US("en, US", "en-US", "en-US"), // 英語
    JAPAN("ja, JP", "ja-JP", "ja-JP"), // 日本語
    NONE("", "", ""); // 指定なし

    private final String firefoxValue;

    private final String edgeValue;

    private final String chromeValue;

    BrowserLocale(String firefoxValue, String edgeValue, String chromeValue) {
        this.firefoxValue = firefoxValue;
        this.edgeValue = edgeValue;
        this.chromeValue = chromeValue;
    }

    public String getForFirefox() {
        return this.firefoxValue;
    }

    public String getForEdge() {
        return this.edgeValue;
    }

    public String getForChrome() {
        return this.chromeValue;
    }
}
