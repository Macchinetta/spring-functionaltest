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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.AfterClass;

public abstract class FunctionTestSupportForMultiBrowser extends FunctionTestSupport {

    private static final Map<Integer, WebDriverOperations> webDriverOperationsMap =
            new ConcurrentHashMap<Integer, WebDriverOperations>();

    @AfterClass
    public static void tearDownWebDriverOperationsMap() {
        webDriverOperationsMap.clear();
    }

    /**
     * デフォルトコンストラクタ。
     * <p>
     * デフォルトブラウザを使用しない(起動しない)設定にする。
     * </p>
     */
    protected FunctionTestSupportForMultiBrowser() {
        super();
        disableDefaultWebDriver();
    }

    /**
     * 指定したIDのWebDriverを操作するためのオブジェクトをセットアップする。
     * <p>
     * 指定したIDのWebDriverが起動していない場合は、新たにブラウザを起動する。
     * </p>
     * @param webDriverId WebDriverを識別するためのID
     * @return セットアップしたWebDriverを操作するためのオブジェクト
     */
    protected final WebDriverOperations setUpWebDriverOperations(int webDriverId) {
        WebDriverOperations operations = null;
        if (webDriverOperationsMap.containsKey(webDriverId)) {
            operations = webDriverOperationsMap.get(webDriverId);
        } else {
            operations = newWebDriverOperations();
            webDriverOperationsMap.put(webDriverId, operations);
        }
        operations.displayPage(getPackageRootUrl());
        return operations;
    }

    /**
     * 指定されたブラウザ(WebDriverOperationsに関連付られているWebDirver)をquitする。
     * <p>
     * {@link #setUpWebDriverOperations()}を使って起動したブラウザ(WebDriverOperationsに関連付られているWebDirver)をquitする場合は、このメソッドを呼び出すこと。
     * </p>
     * @param webDriverOperations quitするWebDriverと関連付られているWebDriverOperationsインスタンス
     */
    protected final void quitWebDriverWebDriverOperations(int webDriverId) {
        if (webDriverOperationsMap.containsKey(webDriverId)) {
            quitWebDriver(getWebDriverOperations(webDriverId));
            webDriverOperationsMap.remove(webDriverId);
        }
    }

    /**
     * 指定したIDのWebDriverを操作するための操作オブジェクトを取得する。
     * @param webDriverId WebDriverを識別するためのID
     * @return 指定したIDのWebDriverを操作するためのオブジェクト
     */
    protected final WebDriverOperations getWebDriverOperations(int webDriverId) {
        return webDriverOperationsMap.get(webDriverId);
    }

}
